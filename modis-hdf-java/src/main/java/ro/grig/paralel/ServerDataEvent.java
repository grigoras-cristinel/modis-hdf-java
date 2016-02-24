package ro.grig.paralel;

import java.nio.channels.SocketChannel;

import ro.grig.face.InterProcesServer;

public class ServerDataEvent {

   public InterProcesServer server;
   public SocketChannel socket;
   public byte[] data;

   public ServerDataEvent(InterProcesServer server, SocketChannel socket, byte[] data) {
      this.server = server;
      this.socket = socket;
      this.data = data;
   }
}