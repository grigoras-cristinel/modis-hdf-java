package hdfextractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.joda.time.DateTime;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;

import ro.grig.sat.util.Util;

/**
 * Incarca datele din fisierul de intrare si scrie shape in fisierul de iesire
 *
 * @author Grigoras Cristinel
 *
 *
 */
public class Build2DShapeFromTxt {
   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(Build2DShapeFromTxt.class);
   private static String BROKER_HOSTNAME = "localhost";
   private static String INPUT_FOLDER = "t:/data/MOD06MYD06_C6";
   private static String OUTPUT_FOLDER = "t:/data/test06";
   private static String timestampStart = DateTime.now().toString("yyyyMMddHHmmss.SSS");
   private static int INPUT_CELLSIZE = 10000;// 1km

   /**
    * Doi parametri primul e satelitul iar al doilea e anul
    *
    * @param args
    * @throws FileNotFoundException
    */
   public static void main(String[] args) throws FileNotFoundException {
      logger.debug("Start"); //$NON-NLS-1$ //$NON-NLS-2$
      if (System.getProperty("input.folder") != null) {
         INPUT_FOLDER = System.getProperty("input.folder");
         logger.debug("input.folder folder setat din property :" + INPUT_FOLDER); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         logger.debug("input.folder  {}", INPUT_FOLDER); //$NON-NLS-1$ //$NON-NLS-2$
      }
      File folderLoguri = new File(INPUT_FOLDER + "/../DEBUG");
      if (!folderLoguri.exists()) {
         folderLoguri.mkdir();
      }
      String hname = "?";
      try {
         hname = InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      String debugfilelogname = INPUT_FOLDER + "/../DEBUG/debug-" + hname + "-" + timestampStart + ".log";
      updateLog4jConfiguration(debugfilelogname);
      if (System.getProperty("output.folder") != null) {
         OUTPUT_FOLDER = System.getProperty("output.folder");
         logger.debug("output.folder folder setat din property :" + OUTPUT_FOLDER); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         logger.debug("output.folder  {}", OUTPUT_FOLDER); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (System.getProperty("bkroker.hostname") != null) {
         BROKER_HOSTNAME = System.getProperty("bkroker.hostname");
         logger.debug("bkroker.hostname setat din property :" + BROKER_HOSTNAME); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         logger.debug("bkroker.hostname  default {}", BROKER_HOSTNAME); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (System.getProperty("input.cellsize") != null) {
         INPUT_CELLSIZE = Integer.parseInt(System.getProperty("input.cellsize"));
         logger.debug("input.cellsize folder setat din property :" + INPUT_CELLSIZE); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         logger.debug("input.cellsize default  {}, automatic detected by file names.", INPUT_CELLSIZE); //$NON-NLS-1$ //$NON-NLS-2$
      }
      Build2DShapeFromTxt runn = new Build2DShapeFromTxt();
      File folderExport = new File(OUTPUT_FOLDER);
      if (!folderExport.exists()) {
         folderExport.mkdir();
      }
      runn.runMethod(args);
      logger.debug("End."); //$NON-NLS-1$ //$NON-NLS-2$
   }

   class ShapeBuilderRun implements Runnable {
      private String filename;
      org.apache.http.HttpHost servhost;

      public ShapeBuilderRun(String filename, HttpHost servhost) {
         super();
         this.filename = filename;
         this.servhost = servhost;
      }

      @Override
      public void run() {
         try {
            File file = new File(filename);
            BufferedReader redbuf = new BufferedReader(new FileReader(file), 8192 * 10);
            ArrayList<double[]> valori = new ArrayList<>();
            String line = redbuf.readLine();
            String[] hnames = StringUtils.split(line, ",");
            while ((line = redbuf.readLine()) != null) {
               String[] split = StringUtils.split(line, ",");
               double[] thisline = new double[3];
               thisline[0] = Double.parseDouble(split[0]);
               thisline[1] = Double.parseDouble(split[1]);
               thisline[2] = Double.parseDouble(split[2]);
               valori.add(thisline);
            }
            logger.debug("File name {} has {} points.", filename, valori.size());
            redbuf.close();
            writeShapeFileAttribute(OUTPUT_FOLDER, file.getName(), hnames[2], valori);
            CloseableHttpClient client = HttpClients.createDefault();
            URIBuilder build = new URIBuilder();
            build.setPath("/cnf").setParameter("f", filename);
            HttpGet get2 = new HttpGet(build.build());
            get2.addHeader("system", InetAddress.getLocalHost().getHostName());
            CloseableHttpResponse resp2 = client.execute(servhost, get2);
            EntityUtils.toString(resp2.getEntity());
            resp2.close();
            terminate.incrementAndGet();
         } catch (Exception e) {
            logger.error("<no args>", e); //$NON-NLS-1$
         }
         limitWorkingRequests.release();
      }
   };

   Semaphore limitWorkingRequests;
   AtomicInteger executii = new AtomicInteger(0);
   AtomicInteger terminate = new AtomicInteger(0);

   private void runMethod(String[] args) {
      int processors = Runtime.getRuntime().availableProcessors();
      processors = 7;
      logger.debug("Procesoare gasite: {}", processors);
      ExecutorService torunproc = Executors.newFixedThreadPool(processors);
      limitWorkingRequests = new Semaphore(processors);
      int noFileReq = 5;
      int retries = 30;
      int connectionRefuzed = retries;
      boolean exit = false;
      do {
         try {
            if (limitWorkingRequests.tryAcquire(5, TimeUnit.SECONDS)) {
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
                  executii.incrementAndGet();
                  logger.debug("Found text file {}.", fileName);
                  torunproc.execute(new ShapeBuilderRun(fileName, servhost));
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
      torunproc.shutdown();
      logger.debug("End run methods, executii:{}, terminate:{} .", executii.get(), terminate.get());
   }

   private void writeShapeFileAttribute(String exportFolder, String fileName, String attribut,
         ArrayList<double[]> valori) throws Exception {
      File folderOutput = new File(exportFolder);
      if (!folderOutput.exists()) {
         folderOutput.mkdirs();
      }
      String fpas = FilenameUtils.getBaseName(fileName);
      String[] split = fpas.split("_");
      String variabila = split[0];
      String an = split[1];
      String finalFolder = exportFolder + "/" + variabila + "/" + an + "/";
      File finalFolderT = new File(finalFolder);
      if (!finalFolderT.exists()) {
         finalFolderT.mkdirs();
      }
      String finalFileName = finalFolder + FilenameUtils.getBaseName(fileName) + ".shp";
      logger.debug("Final file name: {}", finalFileName);
      double[][] dims = Util.find1KmDecDegree(valori);
      if (fileName.startsWith("AngDBLand")) {
         logger.debug("Write shape file {} use dims {}", fileName, "10Km");
         dims = Util.find10KmDecDegree(valori);
      } else if (fileName.startsWith("aod550")) {
         dims = Util.find10KmDecDegree(valori);
         logger.debug("Write shape file {} use dims {}", fileName, "10Km");
      } else if (fileName.startsWith("AngO1")) {
         dims = Util.find10KmDecDegree(valori);
         logger.debug("Write shape file {} use dims {}", fileName, "10Km");
      } else if (fileName.startsWith("AngO2")) {
         dims = Util.find10KmDecDegree(valori);
         logger.debug("Write shape file {} use dims {}", fileName, "10Km");
      } else if (fileName.startsWith("TypeLand")) {
         dims = Util.find10KmDecDegree(valori);
         logger.debug("Write shape file {} use dims {}", fileName, "10Km");
      } else if (fileName.startsWith("ctt")) {
         dims = Util.find1KmDecDegree(valori);
         logger.debug("Write shape file {} use dims {}", fileName, "1Km");
      } else if (fileName.startsWith("cot")) {
         dims = Util.find1KmDecDegree(valori);
         logger.debug("Write shape file {} use dims {}", fileName, "1Km");
      } else if (fileName.startsWith("cer")) {
         dims = Util.find1KmDecDegree(valori);
         logger.debug("Write shape file {} use dims {}", fileName, "1Km");
      } else if (fileName.startsWith("cwp")) {
         dims = Util.find1KmDecDegree(valori);
         logger.debug("Write shape file {} use dims {}", fileName, "1Km");
      } else if (fileName.startsWith("ctp")) {
         dims = Util.find1KmDecDegree(valori);
         logger.debug("Write shape file {} use dims {}", fileName, "1Km");
      } else if (INPUT_CELLSIZE == 1000) {
         dims = Util.find1KmDecDegree(valori);
         logger.debug("Write shape file {} use dims {}", fileName, "1Km");
      } else if (INPUT_CELLSIZE == 500) {
         dims = Util.find500mDecDegree(valori);
         logger.debug("Write shape file {} use dims {}", fileName, "500m");
      }
      File newFile = new File(finalFileName);
      // 4326 este wgs1984
      SimpleFeatureType TYPE = createFeatureType(attribut);
      ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
      Map<String, Serializable> params = new HashMap<String, Serializable>();
      params.put("url", newFile.toURI().toURL());
      params.put("create spatial index", Boolean.TRUE);
      ShapefileDataStore newDataStore = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
      newDataStore.createSchema(TYPE);
      CoordinateReferenceSystem Crs = CRS.decode("EPSG:4326");
      newDataStore.forceSchemaCRS(Crs);
      List<SimpleFeature> features = new ArrayList<SimpleFeature>();
      com.vividsolutions.jts.geom.GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
      SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
      ArrayList<double[]> vals = valori;
      for (int i = 0; i < dims.length; i++) {
         double[] centru = vals.get(i);
         double dist[] = dims[i];
         // clock TL
         Coordinate tl = new Coordinate(centru[1] - (dist[1] / 2), centru[0] + (dist[0] / 2));
         Coordinate tr = new Coordinate(centru[1] + (dist[1] / 2), centru[0] + (dist[0] / 2));
         Coordinate br = new Coordinate(centru[1] + (dist[1] / 2), centru[0] - (dist[0] / 2));
         Coordinate bl = new Coordinate(centru[1] - (dist[1] / 2), centru[0] - (dist[0] / 2));
         Polygon plg = geometryFactory.createPolygon(new Coordinate[] { tl, tr, br, bl, tl });
         featureBuilder.add(plg);
         featureBuilder.add(centru[2]);
         // logger.debug("Valoare 2 este: {}", centru[2]);
         SimpleFeature sfb = featureBuilder.buildFeature(null);
         features.add(sfb);
      }
      Transaction transaction = new DefaultTransaction("create");
      String typeName = newDataStore.getTypeNames()[0];
      SimpleFeatureSource featureSource = newDataStore.getFeatureSource(typeName);
      if (featureSource instanceof SimpleFeatureStore) {
         SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;
         SimpleFeatureCollection collection = new ListFeatureCollection(TYPE, features);
         featureStore.setTransaction(transaction);
         try {
            featureStore.addFeatures(collection);
            transaction.commit();
         } catch (Exception problem) {
            logger.error("String, String, String, ArrayList<double[]>", problem); //$NON-NLS-1$
            transaction.rollback();
         } finally {
            transaction.close();
         }
         newDataStore.dispose();
      } else {
         logger.debug("String, String, String, ArrayList<double[]> - {}", //$NON-NLS-1$
               typeName + " does not support read/write access"); //$NON-NLS-1$
      }
      MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
      MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
      logger.debug("Used memmory test: {} ", heapMemoryUsage.getUsed());
   }

   private static SimpleFeatureType createFeatureType(String attribut) {
      SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
      builder.setName("Location");
      builder.setCRS(DefaultGeographicCRS.WGS84); // <- Coordinate reference
                                                  // system
      // add attributes in order
      builder.add("the_geom", Polygon.class);
      builder.add(new String(attribut.trim()), Double.class); //
      // build the type
      final SimpleFeatureType LOCATION = builder.buildFeatureType();
      return LOCATION;
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
