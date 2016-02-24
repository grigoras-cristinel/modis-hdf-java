package ro.grig.paralel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Citeste un folder de intrare si distribuie fisierele prin http la iesire
 *
 * @author grig
 *
 */
public class FileWorkBroker {
   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(FileWorkBroker.class);
   /**
    * Logger for this class
    */
   public static String INPUT_FOLDER = "T:/data/MOD06MYD06_C6_test/AppModis04C6S";
   public static String INPUT_FILE_PATTERN = "*.txt";
   private static String timestampStart = DateTime.now().toString("yyyyMMddHHmmss.SSS");
   private static final long AGETORETRY = 48000;
   private LinkedBlockingDeque<String> fileList = new LinkedBlockingDeque<>();
   private ConcurrentHashMap<String, Long> waitList = new ConcurrentHashMap<>();
   private ConcurrentHashMap<String, Boolean> completeList = new ConcurrentHashMap<>();
   AtomicInteger contor = new AtomicInteger(0);
   private static PrintWriter rezolvedFiles;

   public static void main(String[] args) {
      logger.debug("Start"); //$NON-NLS-1$ //$NON-NLS-2$
      if (System.getProperty("input.folder") != null) {
         INPUT_FOLDER = System.getProperty("input.folder");
         logger.debug("input.folder folder setat din property :" + INPUT_FOLDER); //$NON-NLS-1$ //$NON-NLS-2$
      }
      File folderLoguri = new File(INPUT_FOLDER + "/../DEBUG");
      if (!folderLoguri.exists()) {
         folderLoguri.mkdir();
      }
      String debugfilelogname = INPUT_FOLDER + "/../DEBUG" + "/fileBroker-" + timestampStart + ".log";
      updateLog4jConfiguration(debugfilelogname);
      if (System.getProperty("input.file.pattern") != null) {
         INPUT_FILE_PATTERN = System.getProperty("input.file.pattern");
         logger.debug("input.file.pattern folder setat din property :" + INPUT_FILE_PATTERN); //$NON-NLS-1$ //$NON-NLS-2$
      }
      String tempDir = System.getProperty("java.io.tmpdir");
      File rzf = new File(tempDir + "/rezolvedfileshistory.txt");
      if (!rzf.exists()) {
         try {
            rzf.createNewFile();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      logger.debug("Rezolved file is {}", rzf.getAbsoluteFile());
      FileWorkBroker fileWorkBroker = new FileWorkBroker();
      fileWorkBroker.readInputList(rzf);
      try {
         FileOutputStream fos = new FileOutputStream(rzf, true);
         rezolvedFiles = new PrintWriter(fos, true);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      fileWorkBroker.startServer();
      logger.debug("End server"); //$NON-NLS-1$ //$NON-NLS-2$
   }

   private void readInputList(File rzf) {
      logger.debug("Input folder: {} ", INPUT_FOLDER);
      File folderInput = new File(INPUT_FOLDER);
      Iterator<File> itfile = FileUtils.iterateFiles(folderInput,
            (IOFileFilter) new WildcardFileFilter(INPUT_FILE_PATTERN), TrueFileFilter.INSTANCE);
      while (itfile.hasNext()) {
         File finlist = (File) itfile.next();
         fileList.addLast(finlist.getAbsolutePath());
      }
      logger.debug("Am gasit {} fisiere cu masca {}", fileList.size(), INPUT_FILE_PATTERN);
      BufferedReader fr;
      int contorPr = 0;
      try {
         fr = new BufferedReader(new FileReader(rzf));
         String line;
         while ((line = fr.readLine()) != null) {
            if (fileList.remove(line)) {
               contorPr++;
            }
         }
         fr.close();
         logger.debug("Am gsit fisiere deja prelucrate in numar de {} ", contorPr);
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private void startServer() {
      try {
         HttpServer myserver = HttpServer.create(new InetSocketAddress(12000), 150);
         HttpContext ctx1 = myserver.createContext("/nextfile", new NextFileHandler());
         myserver.createContext("/cnf", new ConfirmFileHandler());
         myserver.createContext("/", new ConfirmFileHandler());
         myserver.start();
      } catch (IOException e) {
         logger.error("<no args>", e); //$NON-NLS-1$
      }
   }

   protected void requeOldFiles() {
      final long dn = DateTime.now().getMillis();
      final KeySetView<String, Long> keys = waitList.keySet();
      keys.forEach(new Consumer<String>() {
         @Override
         public void accept(String t) {
            long age = dn - waitList.get(t);
            if (age > AGETORETRY) {
               logger.debug("Please retry file:{} after age {} ", t, age);
               fileList.addLast(t);
               keys.remove(t);
            }
         }
      });
   }

   public class NextFileHandler implements HttpHandler {
      public NextFileHandler() {
         super();
      }

      @Override
      public void handle(HttpExchange exch) throws IOException {
         String masina = exch.getRequestHeaders().getFirst("system");
         logger.debug("Am primit cerere /nextfile cu header {}", masina);
         requeOldFiles();
         String val = fileList.pollFirst();
         if (val != null) {
            waitList.put(val, DateTime.now().getMillis());
            byte[] outdata = val.getBytes();
            exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, outdata.length);
            exch.getResponseBody().write(outdata);
            logger.debug("Am raspuns cerere /nextfile cu header {}, raspuns {}", masina, val);
         } else {
            val = ".";
            byte[] outdata = val.getBytes();
            exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, outdata.length);
            exch.getResponseBody().write(outdata);
         }
         exch.getRequestBody().close();
         exch.getResponseBody().close();
         exch.close();
         logger.debug("End cerere /nextfile nr: {} from  {}.", contor.incrementAndGet(), masina);
      }
   }

   public class ServerFileHandler implements HttpHandler {
      public ServerFileHandler() {
         super();
      }

      @Override
      public void handle(HttpExchange exch) throws IOException {
         String masina = exch.getRequestHeaders().getFirst("system");
         logger.debug("Am primit cerere {} cu header {}", exch.getRequestURI().toString(), masina);
         exch.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, "".getBytes().length);
         exch.getResponseBody().write("".getBytes());
         exch.getRequestBody().close();
         exch.getResponseBody().close();
         exch.close();
      }
   }

   public class ConfirmFileHandler implements HttpHandler {
      public ConfirmFileHandler() {
         super();
      }

      @Override
      public void handle(HttpExchange exch) throws IOException {
         String masina = exch.getRequestHeaders().getFirst("system");
         Map<String, String> pars = queryToMap(exch.getRequestURI().getQuery());
         String file = pars.get("f");
         logger.debug("Am primit cerere /cnf cu header {}, si fiser: {}", masina, file);
         String retval = "nosave";
         if (waitList.containsKey(file)) {
            completeList.put(file, true);
            waitList.remove(file);
            fileList.remove(file);
            retval = "ok";
         }
         requeOldFiles();
         byte[] outdata = retval.getBytes();
         exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, outdata.length);
         exch.getResponseBody().write(outdata);
         exch.getRequestBody().close();
         exch.getResponseBody().close();
         exch.close();
         writeRezolvedFiles(file);
         logger.debug("End cerere /cnf {}. Ramase in coada: {}", masina, fileList.size());
      }

      private void writeRezolvedFiles(String file) {
         synchronized (rezolvedFiles) {
            rezolvedFiles.println(file);
         }
      }
   }

   public static Map<String, String> queryToMap(String query) {
      Map<String, String> result = new HashMap<String, String>();
      for (String param : query.split("&")) {
         String pair[] = param.split("=");
         if (pair.length > 1) {
            result.put(pair[0], pair[1]);
         } else {
            result.put(pair[0], "");
         }
      }
      return result;
   }

   private static void updateLog4jConfiguration(String logFile) {
      FileAppender fa = new FileAppender();
      fa.setName("DebugFileLogger");
      fa.setFile(logFile);
      fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
      fa.setThreshold(Level.DEBUG);
      fa.setAppend(true);
      fa.activateOptions();
      org.apache.log4j.Logger.getRootLogger().addAppender(fa);
   }
}
