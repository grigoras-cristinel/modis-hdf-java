package hdfextractor;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
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

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;

import ro.grig.sat.util.Util;

/**
 * Aplicatie care extrage datele de spre nori din HDF in coordonatele romaniei
 * date si le pune in folderul out pentru fiecare statie. Construieste un shape
 * pentru fiecare fisier pentru o dimensiune.
 *
 * @author Grigoras Cristinel
 *
 *
 */
public class AppModis06C6S {
   private static String LOCATIE_FISERE_HDF = "t:/data/MOD06MYD06_C6";
   private static String LOCATIE_EXPORT = "t:/data/test06";
   private static PrintStream debug;

   /**
    * Doi parametri primul e satelitul iar al doilea e anul
    *
    * @param args
    * @throws FileNotFoundException
    */
   public static void main(String[] args) throws FileNotFoundException {
      System.out.println("Start");
      if (System.getProperty("locatie.fisiere.hdf") != null) {
         LOCATIE_FISERE_HDF = System.getProperty("locatie.fisiere.hdf");
         System.out.println("locatie.fisiere.hdf folder setat din property :" + LOCATIE_FISERE_HDF);
      }
      if (System.getProperty("locatie.export") != null) {
         LOCATIE_EXPORT = System.getProperty("locatie.export");
         System.out.println("locatie.export folder setat din property :" + LOCATIE_EXPORT);
      }
      AppModis06C6S runn = new AppModis06C6S();
      File folderExport = new File(LOCATIE_EXPORT);
      if (!folderExport.exists()) {
         folderExport.mkdir();
      }
      System.out.println("Trebuie 2 argumente [satelit,an], gasit args: " + Arrays.toString(args));
      if (args.length < 2) {
         System.exit(1);
      }
      String anul = args[0];
      String satelit = args[1];
      String timestampStart = DateTime.now().toString("yyyyMMddHHmmss.SSS");
      PrintStream fdebug = new PrintStream(
            new File(LOCATIE_EXPORT + "/debug-" + satelit + anul + "-" + timestampStart + ".log"));
      debug = fdebug;
      runn.runMethod(args);
      System.out.println("End.");
   }

   private void runMethod(String[] args) {
      int test = 0;
      int contor = 0;
      debug.println("Trebuie 2 argumete [satelit,an], gasit args: " + Arrays.toString(args));
      if (args.length < 2) {
         System.exit(1);
      }
      String anii[] = new String[] { args[1] };
      String sateliti[] = new String[] { args[0] };
      // String anii[] = new String[] { "2008" };
      // String sateliti[] = new String[] { "MOD" };
      HashMap<String, HashSet<String>> historyProcesare = new HashMap<String, HashSet<String>>();
      File bkupFisereRulate = new File(LOCATIE_EXPORT + "/historyProcesare_" + this.getClass().getName() + ".dmp");
      if (bkupFisereRulate.exists()) {
         try {
            FileInputStream fin = new FileInputStream(bkupFisereRulate);
            ObjectInputStream oin = new ObjectInputStream(fin);
            historyProcesare = (HashMap<String, HashSet<String>>) oin.readObject();
            oin.close();
         } catch (Exception e) {
            e.printStackTrace(debug);
         }
      }
      String exportFolder = LOCATIE_EXPORT + "/" + this.getClass().getSimpleName() + "/";
      String runPathProgress = LOCATIE_EXPORT + "/HS06/";
      File folderRunPathProgress = new File(runPathProgress);
      folderRunPathProgress.mkdirs();
      for (int j = 0; j < anii.length; j++) {
         String anul = anii[j];
         for (int k = 0; k < sateliti.length; k++) {
            contor = 0;
            String satelit = sateliti[k];
            if (!historyProcesare.containsKey(anul + satelit)) {
               historyProcesare.put(anul + satelit, new HashSet<String>());
            }
            HashSet<String> tt = historyProcesare.get(anul + satelit);
            String inputPath = LOCATIE_FISERE_HDF;
            File folderInput = new File(inputPath);
            debug.println("Start folder: " + folderInput);
            try {
               File[] deCitit = folderInput
                     .listFiles((FileFilter) new WildcardFileFilter(satelit + "06_L2.A" + anul + "???.????.006.*.hdf"));
               for (int i = 0; i < deCitit.length; i++) {
                  File file = deCitit[i];
                  if (file.isDirectory()) {
                     continue;
                  }
                  final String name = file.getName();
                  if (!name.endsWith("hdf")) {
                     continue;
                  }
                  if (folderRunPathProgress.list(new FilenameFilter() {
                     @Override
                     public boolean accept(File dir, String nume) {
                        if (name.equals(nume)) {
                           return true;
                        }
                        return false;
                     }
                  }).length > 0) {
                     debug.println(i + "\t File hdf found in folder run:" + name + " ");
                     continue;
                  }
                  if (tt.contains(name)) {
                     debug.println(i + "\t File hdf found prelucrat:" + name + " ");
                     continue;
                  }
                  H4FileWC6Mod06S cfile = null;
                  try {
                     cfile = new H4FileWC6Mod06S(file.getCanonicalPath());
                     cfile.setSatelit(satelit);
                  } catch (Exception e) {
                     debug.println("File open exception:" + file.getCanonicalPath());
                     e.printStackTrace(debug);
                     continue;
                  }
                  debug.print(i + "\t File hdf executed:" + name + " :");
                  cfile.parseFile(debug);
                  {
                     String attribut = "cot";
                     boolean ok = false;
                     ArrayList<double[]> valori = cfile.getValoriCOT();
                     if (valori != null && valori.size() > 10) {
                        debug.print(" valori exportate: " + valori.size() + ";");
                        ok = true;
                     }
                     if (ok) {
                        writeOutputFileAttribut(exportFolder, anul, cfile.getFirstDate().getMonthOfYear() + "", valori,
                              FilenameUtils.getBaseName(file.getName()), attribut);
                        // writeShapeFileAttribute(exportFolder, cfile,
                        // attribut, valori);
                     }
                  }
                  {
                     String attribut = "cer";
                     boolean ok = false;
                     ArrayList<double[]> valori = cfile.getValoriCER();
                     if (valori != null && valori.size() > 10) {
                        debug.print(" valori exportate: " + valori.size() + ";");
                        ok = true;
                     }
                     if (ok) {
                        writeOutputFileAttribut(exportFolder, anul, cfile.getFirstDate().getMonthOfYear() + "", valori,
                              FilenameUtils.getBaseName(file.getName()), attribut);
                        // writeShapeFileAttribute(exportFolder, cfile,
                        // attribut, valori);
                     }
                  }
                  {
                     String attribut = "cwp";
                     boolean ok = false;
                     ArrayList<double[]> valori = cfile.getValoriWP();
                     if (valori != null && valori.size() > 10) {
                        debug.print(" valori exportate: " + valori.size() + ";");
                        ok = true;
                     }
                     if (ok) {
                        writeOutputFileAttribut(exportFolder, anul, cfile.getFirstDate().getMonthOfYear() + "", valori,
                              FilenameUtils.getBaseName(file.getName()), attribut);
                        // writeShapeFileAttribute(exportFolder, cfile,
                        // attribut, valori);
                     }
                  }
                  {
                     String attribut = "ctp";
                     boolean ok = false;
                     ArrayList<double[]> valori = cfile.getValoriTopP();
                     if (valori != null && valori.size() > 10) {
                        debug.print(" valori exportate: " + valori.size() + ";");
                        ok = true;
                     }
                     if (ok) {
                        writeOutputFileAttribut(exportFolder, anul, cfile.getFirstDate().getMonthOfYear() + "", valori,
                              FilenameUtils.getBaseName(file.getName()), attribut);
                        // writeShapeFileAttribute(exportFolder, cfile,
                        // attribut, valori);
                     }
                  }
                  {
                     String attribut = "ctt";
                     boolean ok = false;
                     ArrayList<double[]> valori = cfile.getValoriTopT();
                     if (valori != null && valori.size() > 10) {
                        debug.print(" valori exportate: " + valori.size() + ";");
                        ok = true;
                     }
                     if (ok) {
                        writeOutputFileAttribut(exportFolder, anul, cfile.getFirstDate().getMonthOfYear() + "", valori,
                              FilenameUtils.getBaseName(file.getName()), attribut);
                        // writeShapeFileAttribute(exportFolder, cfile,
                        // attribut, valori);
                     }
                  }
               }
            } catch (Exception e) {
               e.printStackTrace(debug);
            } finally {
            }
         }
      }
      try {
         FileOutputStream fin = new FileOutputStream(bkupFisereRulate);
         ObjectOutputStream oin = new ObjectOutputStream(fin);
         oin.writeObject(historyProcesare);
         oin.close();
         fin.flush();
      } catch (Exception e) {
         e.printStackTrace(debug);
      }
      debug.println("End.");
   }

   private void writeOutputFileAttribut(String runPath, String anul, String luna, ArrayList<double[]> valori,
         String hfname, String attribut) throws Exception {
      File folderOutput = new File(runPath);
      if (!folderOutput.exists()) {
         folderOutput.mkdirs();
      }
      File fileOutput = new File(runPath + attribut + "_" + anul + "_" + luna + "_" + hfname + ".txt");
      debug.println("Output file:" + fileOutput);
      PrintWriter pw = new PrintWriter(fileOutput);
      pw.printf("Lat, Lon, " + attribut + " \n");
      for (double[] ds : valori) {
         pw.printf("%.10f,%.10f,%.10f\n", ds[0], ds[1], ds[2]);
      }
      pw.flush();
      pw.close();
   }

   private void writeShapeFileAttribute(String exportFolder, H4FileWC6Mod06S cfile, String attribut,
         ArrayList<double[]> valori) throws Exception {
      File folderOutput = new File(exportFolder);
      if (!folderOutput.exists()) {
         folderOutput.mkdirs();
      }
      DateTime fdt = cfile.getFirstDate();
      String str = fdt.toString("yyyy_MM_DDDHHmm");
      String key = attribut + "_" + cfile.getSatelit() + str;
      String fileName = exportFolder + key + ".shp";
      debug.println("Write shape file " + fileName);
      double[][] dims = Util.find1KmDecDegree(valori);
      if ("cot".equals(attribut)) {
         dims = Util.find1KmDecDegree(valori);
      } else if ("cer".equals(attribut)) {
         dims = Util.find1KmDecDegree(valori);
      }
      File newFile = new File(fileName);
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
         featureBuilder.add(key);
         featureBuilder.add(fdt.getYear());
         featureBuilder.add(fdt.getMonthOfYear());
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
            problem.printStackTrace();
            transaction.rollback();
         } finally {
            transaction.close();
         }
         /*
          * Pasul este de 0.0051
          * 
          * double step = 0.0051; int dx = (int)
          * (Math.round(H4FileWC6Mod04S.LNG_EST - H4FileWC6Mod04S.LNG_VEST) /
          * step); int dy = (int) Math.round((H4FileWC6Mod04S.LAT_NORD -
          * H4FileWC6Mod04S.LAT_SUD) / step); java.awt.Dimension longlatdim =
          * new java.awt.Dimension(dx, dy); DirectPosition2D dp2dll = new
          * DirectPosition2D(Crs, H4FileWC6Mod04S.LNG_VEST,
          * H4FileWC6Mod04S.LAT_SUD); DirectPosition2D dp2dtr = new
          * DirectPosition2D(Crs, H4FileWC6Mod04S.LNG_EST,
          * H4FileWC6Mod04S.LAT_NORD); Envelope2D romania = new
          * Envelope2D(dp2dll, dp2dtr); GridCoverage2D process =
          * VectorToRasterProcess.process(collection, attribut, longlatdim,
          * collection.getBounds(), "cov", null); RenderedImage im =
          * process.getRenderedImage(); File tifout = new File(exportFolder +
          * key + ".tif"); ImageIO.write(im, "tif", tifout); // GeoTiffWriter
          * gtw = new GeoTiffWriter(tifout); // gtw.write(process, null);
          */
         newDataStore.dispose();
      } else {
         System.out.println(typeName + " does not support read/write access");
      }
   }

   private static SimpleFeatureType createFeatureType(String attribut) {
      SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
      builder.setName("Location");
      builder.setCRS(DefaultGeographicCRS.WGS84); // <- Coordinate reference
                                                  // system
      // add attributes in order
      builder.add("the_geom", Polygon.class);
      builder.add(attribut, Double.class); //
      builder.add("dtKey", String.class); //
      builder.add("Year", Integer.class); //
      builder.add("Month", Integer.class); //
      // build the type
      final SimpleFeatureType LOCATION = builder.buildFeatureType();
      return LOCATION;
   }
}
