package hdfextractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.MessageFormat;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import hdfextractor.ozon.CalculeazaOzonLaSol;
import hdfextractor.ozon.OmiMeasurementQualityFlags;
import hdfextractor.ozon.OmiProcessingQualityFlags;
import util.SynchronizedFileWriter;

/**
 * Hello world!
 * 
 */
public class OzonSatelitToateStatiileOmi {

   /**
    * Logger for this class
    */
   private static final Log logger = LogFactory.getLog(OzonSatelitToateStatiileOmi.class);

   /**
    * @param args
    */
   public static void main(String[] args) {
      if (logger.isDebugEnabled()) {
         logger.debug("main(String[]) - start"); //$NON-NLS-1$
      }
      Properties prop = new Properties();
      try {
         File propFile = new File("localsystem.properties");
         System.out.println("Proeprty file: " + propFile.getAbsolutePath());
         prop.load(new FileReader(propFile));
         System.out.println(MessageFormat.format("Local property ozon.input.omi-satelit-hdf-files.2009={0}",
               prop.getProperty("ozon.input.omi-satelit-hdf-files.2009")));
         System.out.println(
               MessageFormat.format("Local property ozon.output.omi={0}", prop.getProperty("ozon.output.omi")));
      } catch (FileNotFoundException e1) {
         e1.printStackTrace();
      } catch (Exception e1) {
         e1.printStackTrace();
      }
      System.out.println("Library path: " + System.getenv("java.library.path"));
      System.out.println("Class path: " + System.getenv("java.class.path"));
      boolean alegeDoarZiua = false;
      boolean eliminaToateErorile = true;
      String anul = "2009";
      File folder = new File(prop.getProperty("ozon.input.omi-satelit-hdf-files.2009"));
      String OUTPUT = prop.getProperty("ozon.output.omi");
      File wsfolder = new File(OUTPUT);
      if (!wsfolder.exists() && !wsfolder.isDirectory()) {
         wsfolder.mkdirs();
      }
      String prefixFisierIesire = OUTPUT + "/omi-" + anul + "-";
      System.out.println("Start folder files:" + folder);
      DateTimeFormatter formatScriereFisierIntermediar = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm")
            .withZone(DateTimeZone.UTC);
      try {
         TreeMap<String, SynchronizedFileWriter> fisiere = new TreeMap<String, SynchronizedFileWriter>();
         TreeMap<String, double[]> setStatii = StatiiMeteoBucuresti.statii;
         for (Entry<String, double[]> a : setStatii.entrySet()) {
            SynchronizedFileWriter writer = new SynchronizedFileWriter(prefixFisierIesire + a.getKey() + ".txt");
            fisiere.put(a.getKey(), writer);
            writer.print(String.format("%16s\t%16s\t%16s\t%16s", "DataTora", "ozon dobson", "T(k)", "p(pa)"));
            writer.print(String.format("\t%16s", "conc ug 0-10"));
            writer.print(String.format("\t%16s", "conc ug 0-15"));
            writer.print(String.format("\t%16s", "conc ug 0-20"));
            writer.print(String.format("\t%16s", "conc ug 0-30"));
            writer.print(String.format("\t%16s", "conc ug 0-40"));
            writer.print(String.format("\t%16s", "conc ug 0-50"));
            for (int ia = 20; ia < 100; ia = 10 + ia) {
               writer.print(String.format("\t%16s", "conc ug " + (ia - 10) + "-" + ia));
            }
            writer.print(String.format("\t%16s", "cloud fraction"));
            writer.print(String.format("\t%16s", "view angle"));
            writer.print(String.format("\t%16s", "poz along"));
            writer.print(String.format("\t%16s", "poz acros"));
            writer.print(String.format("\t%16s", "c. ug 10% 500m."));
            writer.println();
         }
         File[] deCitit = folder.listFiles();
         for (int i = 0; i < deCitit.length; i++) {
            File file = deCitit[i];
            if (file.isDirectory()) {
               continue;
            }
            logger.debug("File :" + file.getName());
            HDFFileWorker cfile = null;
            if (file.getName().endsWith("hdf")) {
               try {
                  cfile = new H4FileWorker(file.getCanonicalPath(), StatiiMeteoBucuresti.statii);
               } catch (Exception e) {
                  logger.error("main(String[])", e); //$NON-NLS-1$
                  System.out.println("Exceptie citire fisier:" + file.getCanonicalPath() + "; " + e.getMessage());
                  e.printStackTrace();
                  continue;
               }
            } else if (file.getName().endsWith("he5")) {
               try {
                  cfile = new H5FileWorker(file.getCanonicalPath(), StatiiMeteoBucuresti.statii);
               } catch (Exception e) {
                  logger.error("main(String[])", e); //$NON-NLS-1$
                  System.out.println("Exceptie citire fisier:" + file.getCanonicalPath() + "; " + e.getMessage());
                  e.printStackTrace();
                  continue;
               }
            } else {
               continue;
            }
            TreeMap<String, Double> valoriDobson = new TreeMap<String, Double>();
            TreeMap<String, Double> valoriTemperatura = new TreeMap<String, Double>();
            TreeMap<String, Double> valoriPresiune = new TreeMap<String, Double>();
            TreeMap<String, Double> valoriCloudFraction = new TreeMap<String, Double>();
            TreeMap<String, Float> valoriViewingZenithAngle = new TreeMap<String, Float>();
            boolean gasitSatelit = false;
            for (Entry<String, double[]> a : setStatii.entrySet()) {
               String siteName = a.getKey();
               if (cfile.hasPosition(siteName)) {
                  Double ozonDobson = cfile.findOzon(siteName);
                  Double temp = cfile.findTemperaturaSuprafata(siteName);
                  Double pres = cfile.findPresiuneSuprafata(siteName);
                  Double cloudFractionV = cfile.findCloudFraction(siteName);
                  Float viewingZenithAngle = cfile.findViewingZenithAngle(siteName);
                  if (ozonDobson != null) {
                     gasitSatelit = true;
                     valoriDobson.put(a.getKey(), ozonDobson);
                     valoriTemperatura.put(a.getKey(), temp);
                     valoriPresiune.put(a.getKey(), pres);
                     valoriCloudFraction.put(a.getKey(), cloudFractionV);
                     valoriViewingZenithAngle.put(a.getKey(), viewingZenithAngle);
                  } else {
                     valoriDobson.put(a.getKey(), -1d);
                     valoriTemperatura.put(a.getKey(), -1d);
                     valoriPresiune.put(a.getKey(), 1d);
                     valoriCloudFraction.put(a.getKey(), 0d);
                  }
               }
            }
            if (gasitSatelit) {
               DateTime timp = cfile.findTimpMediu();
               System.out.println("File: " + file.getName() + " Writer:" + timp + " ora:" + timp.getHourOfDay());
               CalculeazaOzonLaSol calc = new CalculeazaOzonLaSol();
               int luna = timp.getMonthOfYear() - 1;
               boolean adauga = true;
               int ora = timp.getHourOfDay();
               if (alegeDoarZiua) {
                  if (10 > ora || ora > 16) {
                     adauga = false;
                  }
               }
               if (adauga) {
                  for (Entry<String, Double> a : valoriDobson.entrySet()) {
                     String siteName = a.getKey();
                     OmiProcessingQualityFlags processingQualityFlags = cfile.getProcessingQualityFlags(siteName);
                     OmiMeasurementQualityFlags measurementQualityFlags = cfile.getQualityFlags(siteName);
                     if (eliminaToateErorile && processingQualityFlags.isTrue()) {
                        System.out
                              .println("      Missing data , flag value:" + processingQualityFlags.printTrueValues());
                        continue;
                     }
                     if (eliminaToateErorile && measurementQualityFlags.isTrue()) {
                        System.out.println(
                              "      Measurement error, flag value:" + measurementQualityFlags.printTrueValues());
                        continue;
                     }
                     Double ct2 = 0d;
                     if (cfile instanceof H5FileWorker) {
                        H5FileWorker n5f = (H5FileWorker) cfile;
                        ct2 = n5f.findCloudFraction(siteName);
                     }
                     if (ct2 == null) {
                        ct2 = 0d;
                     }
                     // if (eliminaToateErorile && ct2 > 0.20) {
                     // System.out.println(" Measurement error, too many
                     // clouds");
                     // continue;
                     // }
                     Float vza = cfile.findViewingZenithAngle(siteName);
                     long acrossPoz = cfile.findPozitie(siteName) % cfile.getLatime();
                     // if (eliminaToateErorile && (acrossPoz < 10 ||
                     // acrossPoz
                     // > 50)) {
                     // System.out.printf(" Measurement error, too far
                     // from
                     // nadir poz %d angle %f \n", acrossPoz,
                     // vza);
                     // continue;
                     // }
                     SynchronizedFileWriter writer = fisiere.get(siteName);
                     Double dobsonSatelit = valoriDobson.get(siteName);
                     if (dobsonSatelit > 0) {
                        writer.print(String.format("%16s\t%2$16.8f\t%3$16.8f\t%4$16.8f",
                              timp.toString(formatScriereFisierIntermediar), dobsonSatelit,
                              valoriTemperatura.get(siteName), valoriPresiune.get(siteName)));
                        Double dobsonH1 = calc.calculeazaConcentratieugm3(luna, dobsonSatelit, 10d);
                        Double dobsonH15 = calc.calculeazaConcentratieugm3(luna, dobsonSatelit, 15d);
                        Double dobsonH2 = calc.calculeazaConcentratieugm3(luna, dobsonSatelit, 20d);
                        Double dobsonH3 = calc.calculeazaConcentratieugm3(luna, dobsonSatelit, 30d);
                        Double dobsonH4 = calc.calculeazaConcentratieugm3(luna, dobsonSatelit, 40d);
                        Double dobsonH5 = calc.calculeazaConcentratieugm3(luna, dobsonSatelit, 50d);
                        writer.print(String.format("\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f", dobsonH1,
                              dobsonH15, dobsonH2, dobsonH3, dobsonH4, dobsonH5));
                        for (int ia = 20; ia < 100; ia = 10 + ia) {
                           writer.print(String.format("\t%16.8f",
                                 calc.calculeazaConcentratieugm3Interval10m(luna, dobsonSatelit, ia)));
                        }
                        writer.print(String.format("\t%16.8f", ct2));
                        writer.print(String.format("\t%16.8f", vza));
                        writer.print(String.format("\t%16d", cfile.findPozitie(siteName) / cfile.getLatime()));
                        writer.print(String.format("\t%16d", acrossPoz));
                        Double dobson10lasuta = calc
                              .calculeazaConcentratiePtDobsonCunoscutugm3((dobsonSatelit * 10 / 100), 500);
                        writer.print(String.format("\t%16.8f", dobson10lasuta));
                        writer.println();
                     }
                  }
               }
            }
            cfile.close();
         }
      } catch (Exception e) {
         logger.error("main(String[])", e); //$NON-NLS-1$
         e.printStackTrace();
         System.err.println("Exceptie in:" + e);
      } finally {
      }
      System.out.println("End");
      if (logger.isDebugEnabled()) {
         logger.debug("main(String[]) - end"); //$NON-NLS-1$
      }
   }
}
