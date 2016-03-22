package grig.wsmodis;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.grig.paralel.InterprocessClient;
import ro.grig.paralel.RspHandler;

public abstract class RunProcessAbstractSupport implements ComunicateProcessStatus {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(RunProcessAbstractSupport.class);
   protected static String timestampStart = DateTime.now().toString("yyyyMMddHHmmssSSS");

   protected abstract void runProcess(String[] args);

   protected static void updateLog4jConfiguration(String logFile) {
      FileAppender fa = new FileAppender();
      fa.setName("DebugFileLogger");
      fa.setFile(logFile);
      fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
      fa.setThreshold(Level.DEBUG);
      fa.setAppend(true);
      fa.activateOptions();
      org.apache.log4j.Logger.getRootLogger().addAppender(fa);
   }

   private InterprocessClient client;
   private String jvmName;

   protected void initComunication() {
      logger.debug("Init comunication.");
      String property = System.getenv("intercomunication.port");
      if (property != null) {
         int port = Integer.decode(property);
         // initializez intercomunicarea prin nio socket
         logger.debug("Voi porni intercomunicarea pe localhost port {}", port);
         try {
            client = new InterprocessClient(InetAddress.getByName("localhost"), port);
            Thread t = new Thread(client);
            t.setDaemon(true);
            t.start();
         } catch (UnknownHostException e) {
            e.printStackTrace();
            client = null;
         } catch (IOException e) {
            e.printStackTrace();
            client = null;
         }
      }
      jvmName = ManagementFactory.getRuntimeMXBean().getName();
   }

   @Override
   public void sendMessage(String message) {
      if (client != null) {
         RspHandler handler = new RspHandler();
         String mesaj = jvmName + "::M::" + message;
         client.send(mesaj.getBytes(), handler);
      }
   }

   public void sendHeartBeat() {
      if (client != null) {
         RspHandler handler = new RspHandler();
         String mesaj = jvmName + "::B::";
         client.send(mesaj.getBytes(), handler);
      }
   }
}
