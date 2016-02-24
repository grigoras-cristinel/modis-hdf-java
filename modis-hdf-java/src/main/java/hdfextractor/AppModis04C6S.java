package hdfextractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import grig.wsmodis.RunProcessAbstractSupport;

/**
 * Aplicatie care extrage datele de spre nori din HDF in coordonatele romaniei
 * date si le pune in folderul out pentru fiecare statie.
 *
 * @author Grigoras Cristinel
 *
 *
 */
public class AppModis04C6S extends RunProcessAbstractSupport {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(AppModis04C6S.class);
   private static String OUTPUT_TEXT = "t:/data/test04";
   private static String BROKER_HOSTNAME = "localhost";

   /**
    * Doi parametri primul e satelitul iar al doilea e anul
    *
    * @param args
    * @throws FileNotFoundException
    */
   public static void main(String[] args) throws FileNotFoundException {
      logger.debug("Start");
      AppModis04C6S runn = new AppModis04C6S();
      runn.checkParametri(args);
      File folderLoguri = new File(OUTPUT_TEXT + "/../DEBUG");
      if (!folderLoguri.exists()) {
         folderLoguri.mkdir();
      }
      String hname = "?";
      try {
         hname = InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException e) {
         e.printStackTrace();
      }
      String debugfilelogname = OUTPUT_TEXT + "/../DEBUG/AppModis04C6S-" + hname + "-" + timestampStart + ".log";
      updateLog4jConfiguration(debugfilelogname);
      File folderExport = new File(OUTPUT_TEXT);
      if (!folderExport.exists()) {
         folderExport.mkdir();
      }
      runn.runProcess(args);
      logger.warn("End.");
   }

   protected void checkParametri(String[] args) {
      if (System.getProperty("locatie.export") != null) {
         OUTPUT_TEXT = System.getProperty("locatie.export");
         logger.debug("locatie.export folder setat din property :" + OUTPUT_TEXT);
      }
      if (System.getProperty("bkroker.hostname") != null) {
         BROKER_HOSTNAME = System.getProperty("bkroker.hostname");
         logger.debug("bkroker.hostname setat din property :" + BROKER_HOSTNAME); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         logger.debug("bkroker.hostname  default {}", BROKER_HOSTNAME); //$NON-NLS-1$ //$NON-NLS-2$
      }
   }

   protected void runProcess(String[] args) {
      String exportFolder = OUTPUT_TEXT + "/" + this.getClass().getSimpleName() + "/";
      int noFileReq = 5;
      int retries = 30;
      int connectionRefuzed = retries;
      boolean exit = false;
      do {
         try {
            CloseableHttpClient client = HttpClients.createDefault();
            org.apache.http.HttpHost servhost = new org.apache.http.HttpHost(BROKER_HOSTNAME, 12000);
            HttpGet get1 = new HttpGet("/nextfile");
            get1.addHeader("system", InetAddress.getLocalHost().getHostName());
            CloseableHttpResponse resp = client.execute(servhost, get1);
            String fileName = EntityUtils.toString(resp.getEntity());
            if (fileName.length() == 1) {
               logger.debug("Nu am fisiere, step {}.", noFileReq);
               Thread.sleep(2000);
               noFileReq--;
               if (noFileReq < 0) {
                  exit = true;
               }
            } else {
               try {
                  File file = new File(fileName);
                  if (file.isDirectory()) {
                     continue;
                  }
                  final String name = file.getName();
                  if (!name.endsWith("hdf")) {
                     continue;
                  }
                  String anul = name.substring(10, 14);
                  H4FileWC6Mod04S cfile = null;
                  try {
                     cfile = new H4FileWC6Mod04S(file.getCanonicalPath());
                  } catch (Exception e) {
                     logger.error("File open exception:" + file.getCanonicalPath(), e);
                     continue;
                  }
                  logger.debug("File hdf executed:{} :", fileName);
                  cfile.parseFile();
                  for (Entry<String, Integer> a : H4FileWC6Mod04S.corespondenta.entrySet()) {
                     boolean ok = false;
                     ArrayList<double[]> valoriOpticalDepth = cfile.getValori().get(a.getKey());
                     if (valoriOpticalDepth != null && valoriOpticalDepth.size() > 50) {
                        logger.debug("   Valori exportate: {}", valoriOpticalDepth.size());
                        ok = true;
                     }
                     if (ok) {
                        String attribut = H4FileWC6Mod04S.shortname.get(a.getKey());
                        writeOutputFileAttribut(exportFolder, anul, cfile.getFirstDate().getMonthOfYear() + "",
                              valoriOpticalDepth, FilenameUtils.getBaseName(file.getName()), attribut);
                     }
                  }
                  client = HttpClients.createDefault();
                  URIBuilder build = new URIBuilder();
                  build.setPath("/cnf").setParameter("f", fileName);
                  HttpGet get2 = new HttpGet(build.build());
                  get2.addHeader("system", InetAddress.getLocalHost().getHostName());
                  CloseableHttpResponse resp2 = client.execute(servhost, get2);
                  EntityUtils.toString(resp2.getEntity());
                  resp2.close();
               } catch (Exception e) {
                  logger.error("Exceptie ", e);
               } finally {
               }
            }
         } catch (org.apache.http.conn.HttpHostConnectException e) {
            logger.debug("No server is running. Wait for {} retries {} .", retries, connectionRefuzed);
            connectionRefuzed--;
            if (connectionRefuzed < 0) {
               exit = true;
            }
         } catch (IOException e) {
            logger.error("String[]", e); //$NON-NLS-1$
         } catch (InterruptedException e) {
            logger.error("String[]", e); //$NON-NLS-1$
         }
      } while (!exit);
      logger.debug("End.");
   }

   private void writeOutputFileAttribut(String runPath, String anul, String luna, ArrayList<double[]> valori,
         String hfname, String attribut) throws Exception {
      String outFolder = runPath + "/" + attribut + "/" + anul;
      File folderOutput = new File(outFolder);
      if (!folderOutput.exists()) {
         folderOutput.mkdirs();
      }
      File fileOutput = new File(outFolder + "/" + attribut + "_" + anul + "_" + luna + "_" + hfname + ".txt");
      logger.debug("Output file:" + fileOutput);
      PrintWriter pw = new PrintWriter(fileOutput);
      pw.printf("Lat, Lon, " + attribut + " \n");
      for (double[] ds : valori) {
         pw.printf("%.10f,%.10f,%.10f\n", ds[0], ds[1], ds[2]);
      }
      pw.flush();
      pw.close();
   }
}
