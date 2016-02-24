package excelldata;

import hdfextractor.StatiiMeteoBucuresti;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Formatter;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import util.SunriseSunset;

/**
 * Aceasta clasa va citi ozonul extras din satelit in ug si va citi si NO2 si va
 * genera un fisier ce intra in OML cu date de background. Creaza date doar
 * pentru orele de zi si pune 0 noaptea.
 * 
 * @author Grig
 * 
 */
public class GenereazaBackgroundDeOzonSiNo2PtOML {
   /**
    * Logger for this class
    */
   private static final Log logger = LogFactory
                                         .getLog(GenereazaBackgroundDeOzonSiNo2PtOML.class);

   /**
    * @param args
    */
   public static void main(String[] args) {
      System.out
            .println("Start Genereaza Fisier Pt Oml Background din satelit");
      GenereazaBackgroundDeOzonSiNo2PtOML worker = new GenereazaBackgroundDeOzonSiNo2PtOML();
      worker.execute();
      System.out.println("End");
   }

   /**
	 * 
	 */
   private void execute() {
      String prefixFisierIntrare = "target/omi-2009-";
      System.out.println("Fisier intrare" + prefixFisierIntrare + " .");
      // String prefixFisierIesire = "target/omi-compara-2009-";
      // String prefixFisierIesire = "target/modis-statii-2009-";
      String prefixFisierIesire = "d:/proiecte/2011-satelit-ozon/oml/surse/background-omi-in-";
      System.out.println("Fisier iesire" + prefixFisierIesire + " .");
      double h = 10d;
      for (Entry<String, double[]> statie : StatiiMeteoBucuresti.statii
            .entrySet()) {
         /*
          * Se inlocuieste "combined-statie-" cu numele altui fisier de date
          * satelitare (OMI sau MODIS)
          */
         String combinedIn = prefixFisierIntrare + statie.getKey() + ".txt";
         /*
          * "comparare-" se modifica cu numele fisierului de iesire dorit
          */
         String combinedOut = prefixFisierIesire + statie.getKey() + ".csv";
         TreeMap<DateTime, Double> valoriOzonDobson = new TreeMap<DateTime, Double>();
         try {
            // 2009-01-01 00:00:00.0
            valoriOzonDobson = citesteValoriSatelitOzon(combinedIn);
            PrintWriter outFile = new PrintWriter(new FileWriter(combinedOut));
            printHeaderLines(outFile);
            DateTime startInterval = new DateTime(2009, 01, 1, 0, 0, 0, 0,
                  DateTimeZone.UTC);
            double initial = valoriOzonDobson.firstEntry().getValue();
            while (startInterval.getYear() == 2009) {
               Formatter lineToWrite = new Formatter();
               double valoareOzon = initial;
               if (valoriOzonDobson.containsKey(startInterval)) {
                  logger.debug("am gasit valoare:");
                  initial = valoriOzonDobson.get(startInterval);
               } else {
               }
               SunriseSunset suns = new SunriseSunset(statie.getValue()[0],
                     statie.getValue()[1], startInterval.toDate(), 2d);
               if (suns.isDaytime()) {
                  valoareOzon = initial;
               } else {
                  valoareOzon = 0d;
               }
               lineToWrite.format("%02d%02d%02d %02d %f %f %f 5",
                     startInterval.getYearOfCentury(),
                     startInterval.getMonthOfYear(),
                     startInterval.getDayOfMonth(),
                     startInterval.getHourOfDay(), 0d, 0d, valoareOzon);
               outFile.println(lineToWrite.out());
               startInterval = startInterval.plusHours(1);
            }
            outFile.flush();
            outFile.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   private void printHeaderLines(PrintWriter outFile) {
      outFile.println("Dummy data.");
      outFile
            .println("!! NB !! All units must be in �g/m3 and NOx concentration must be in NO2-units: �g NO2/m3 !!!");
      outFile
            .println("i.e. for NOx and NO2: 1 ppb = 1*0.53 �g/m3, and for O3: 1 ppb = 1*0.50 �g/m3.");
      outFile
            .println("e.g. NOx conc.: 10 �gNO/m3 + 10 �gNO2/m3 =(10*1.882/1.227 + 10) �gNO2/m3 =25.338 �gNO2/m3.");
      outFile.println("DATE   HR     NOx     NO2     O3      RAD");
      outFile.println("yymmdd hh     �g/m3   �g/m3   �g/m3   W/m2  ");
   }

   private TreeMap<DateTime, Double> citesteValoriSatelitOzon(String combinedIn)
         throws FileNotFoundException, IOException {
      TreeMap<DateTime, Double> valoriOzonDobson = new TreeMap<DateTime, Double>();
      Reader textFileReader = new InputStreamReader(new FileInputStream(
            combinedIn));
      Scanner rn = new Scanner(textFileReader);
      DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm")
            .withZone(DateTimeZone.UTC);
      int contor = 0;
      while (rn.hasNextLine()) {
         String line = rn.nextLine();
         contor++;
         if (contor == 1) {
            continue;
         }
         Scanner rnl = new Scanner(line);
         String data = rnl.next();
         DateTime timp = fmt.parseDateTime(data);
         Double ozoDobson = rnl.nextDouble();// CM
         Double temp = rnl.nextDouble();// CM
         Double pres = rnl.nextDouble();// CM
         Double ozon = rnl.nextDouble();// CM
         if (ozon > 0) {
            DateTime timpKey = timp.hourOfDay().roundFloorCopy();
            if (valoriOzonDobson.containsKey(timpKey)) {
               double oldVal = valoriOzonDobson.get(timpKey);
               valoriOzonDobson.put(timp, (ozon + oldVal) / 2);
            } else {
               valoriOzonDobson.put(timpKey, ozon);
            }
         }
      }
      textFileReader.close();
      return valoriOzonDobson;
   }
}
