package ro.grig.face;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import ro.grig.paralel.ChangeRequest;
import ro.grig.paralel.ServerDataEvent;

@Singleton
public class InterProcesServer extends Thread {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(InterProcesServer.class);
   private boolean stop = false;
   private Sc sc;
   // The channel on which we'll accept connections
   private ServerSocketChannel serverChannel;
   // The selector we'll be monitoring
   private Selector selector;
   // The buffer into which we'll read data when it's available
   private ByteBuffer readBuffer = ByteBuffer.allocate(8192);
   private RequestWorker worker;
   // A list of PendingChange instances
   private List<ChangeRequest> pendingChanges = new LinkedList<ChangeRequest>();
   // Maps a SocketChannel to a list of ByteBuffer instances
   private Map<SocketChannel, List<ByteBuffer>> pendingData = new HashMap<SocketChannel, List<ByteBuffer>>();

   class RequestWorker implements Runnable {

      private List<ServerDataEvent> queue = new LinkedList<ServerDataEvent>();

      public RequestWorker() {
      }

      public void processData(InterProcesServer server, SocketChannel socket, byte[] data, int count) {
         byte[] dataCopy = new byte[count];
         System.arraycopy(data, 0, dataCopy, 0, count);
         synchronized (queue) {
            queue.add(new ServerDataEvent(server, socket, dataCopy));
            queue.notify();
         }
      }

      @Override
      public void run() {
         logger.debug("RequestWorker run - start"); //$NON-NLS-1$
         ServerDataEvent dataEvent;
         while (true) {
            // Wait for data to become available
            synchronized (queue) {
               while (queue.isEmpty()) {
                  try {
                     queue.wait();
                  } catch (InterruptedException e) {
                     logger.warn("RequestWorker run - exception ignored", e); //$NON-NLS-1$
                  }
               }
               dataEvent = (ServerDataEvent) queue.remove(0);
            }
            // Write to input
            dataEvent.server.send(dataEvent.data);
         }
      }
   }

   public void send(byte[] data) {
      String msg = new String(data);
      if (StringUtils.contains(msg, "::B::")) {
         msg = StringUtils.remove(msg, "::B::");
         sc.postHeartBeath(msg);
      } else {
         sc.postLogMessage(msg);
      }
      this.selector.wakeup();
   }

   @Inject
   public InterProcesServer(Sc sc) {
      this.sc = sc;
      try {
         this.selector = initSelector();
         this.worker = new RequestWorker();
         new Thread(worker).start();
         this.start();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private Selector initSelector() throws IOException {
      InetAddress hostIPAddress = InetAddress.getByName("localhost");
      int port = 24000 + (int) Math.round((Math.random() * 10000));
      sc.setIntercomunicationPort(port);
      // Create a new selector
      Selector socketSelector = SelectorProvider.provider().openSelector();
      this.serverChannel = ServerSocketChannel.open();
      serverChannel.configureBlocking(false);
      InetSocketAddress isa = new InetSocketAddress(hostIPAddress, port);
      serverChannel.socket().bind(isa);
      serverChannel.register(socketSelector, SelectionKey.OP_ACCEPT);
      return socketSelector;
   }

   @Override
   public void run() {
      logger.debug("Run - start"); //$NON-NLS-1$
      while (!stop) {
         try {
            // Process any pending changes
            synchronized (this.pendingChanges) {
               Iterator<ChangeRequest> changes = this.pendingChanges.iterator();
               while (changes.hasNext()) {
                  ChangeRequest change = changes.next();
                  switch (change.type) {
                  case ChangeRequest.CHANGEOPS:
                     SelectionKey key = change.socket.keyFor(this.selector);
                     key.interestOps(change.ops);
                  }
               }
               this.pendingChanges.clear();
            }
            // Wait for an event one of the registered channels
            this.selector.select();
            // Iterate over the set of keys for which events are available
            Iterator<SelectionKey> selectedKeys = this.selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
               SelectionKey key = selectedKeys.next();
               selectedKeys.remove();
               if (!key.isValid()) {
                  continue;
               }
               // Check what event is available and deal with it
               if (key.isAcceptable()) {
                  this.accept(key);
               } else if (key.isReadable()) {
                  this.read(key);
               } else if (key.isWritable()) {
                  this.write(key);
               }
            }
         } catch (Exception e) {
            logger.error("Run", e); //$NON-NLS-1$
            e.printStackTrace();
         }
      }
      logger.debug("run - end"); //$NON-NLS-1$
   }

   private void accept(SelectionKey key) throws IOException {
      // For an accept to be pending the channel must be a server socket
      // channel.
      ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
      // Accept the connection and make it non-blocking
      SocketChannel socketChannel = serverSocketChannel.accept();
      socketChannel.socket();
      socketChannel.configureBlocking(false);
      // Register the new SocketChannel with our Selector, indicating
      // we'd like to be notified when there's data waiting to be read
      socketChannel.register(this.selector, SelectionKey.OP_READ);
   }

   private void read(SelectionKey key) throws IOException {
      SocketChannel socketChannel = (SocketChannel) key.channel();
      // Clear out our read buffer so it's ready for new data
      this.readBuffer.clear();
      // Attempt to read off the channel
      int numRead;
      try {
         numRead = socketChannel.read(this.readBuffer);
      } catch (IOException e) {
         // The remote forcibly closed the connection, cancel
         // the selection key and close the channel.
         key.cancel();
         socketChannel.close();
         return;
      }
      if (numRead == -1) {
         // Remote entity shut the socket down cleanly. Do the
         // same from our end and cancel the channel.
         key.channel().close();
         key.cancel();
         return;
      }
      // Hand the data off to our worker thread
      this.worker.processData(this, socketChannel, this.readBuffer.array(), numRead);
   }

   private void write(SelectionKey key) throws IOException {
      SocketChannel socketChannel = (SocketChannel) key.channel();
      synchronized (this.pendingData) {
         List<ByteBuffer> queue = this.pendingData.get(socketChannel);
         // Write until there's not more data ...
         while (!queue.isEmpty()) {
            ByteBuffer buf = (ByteBuffer) queue.get(0);
            socketChannel.write(buf);
            if (buf.remaining() > 0) {
               // ... or the socket's buffer fills up
               break;
            }
            queue.remove(0);
         }
         if (queue.isEmpty()) {
            key.interestOps(SelectionKey.OP_READ);
         }
      }
   }

   public boolean isStop() {
      return stop;
   }

   public void setStop(boolean stop) {
      this.stop = stop;
   }
}
