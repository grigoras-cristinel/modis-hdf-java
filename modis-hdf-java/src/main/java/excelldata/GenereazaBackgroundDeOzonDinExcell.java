package excelldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Aceasta clasa va citi si no2 de la statie si va genera genera un fisier ce
 * intra in OML cu date de background
 *
 * @author Grig
 *
 */
public class GenereazaBackgroundDeOzonDinExcell {
   /**
    * Logger for this class
    */
   private static final Log logger = LogFactory.getLog(GenereazaBackgroundDeOzonDinExcell.class);

   /**
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("Start Genereaza Fisier Pt Oml Background din statie");
      GenereazaBackgroundDeOzonDinExcell worker = new GenereazaBackgroundDeOzonDinExcell();
      worker.execute();
      System.out.println("End");
   }

   /**
	 *
	 */
   private void execute() {
      String prefixFisierIntrare = "D:/java/workspace/hdfwork/hdfextractor/B8Balotesti.xls";
      System.out.println("Fisier intrare" + prefixFisierIntrare + " .");
      // String prefixFisierIesire = "target/omi-compara-2009-";
      // String prefixFisierIesire = "target/modis-statii-2009-";
      String prefixFisierIesire = "d:/proiecte/2011-satelit-ozon/oml/surse/";
      System.out.println("Fisier iesire" + prefixFisierIesire + " .");
      /*
       * Se inlocuieste "combined-statie-" cu numele altui fisier de date
       * satelitare (OMI sau MODIS)
       */
      String combinedIn = prefixFisierIntrare;
      /*
       * "comparare-" se modifica cu numele fisierului de iesire dorit
       */
      String combinedOut = prefixFisierIesire + "background-din-b8-i1.csv";
      TreeMap<DateTime, Double[]> valoriCitite = new TreeMap<DateTime, Double[]>();
      try {
         // 2009-01-01 00:00:00.0
         valoriCitite = citesteValoriMasurateOzon(combinedIn);
         PrintWriter outFile = new PrintWriter(new FileWriter(combinedOut));
         printHeaderLines(outFile);
         DateTime startInterval = new DateTime(2009, 01, 1, 0, 0, 0, 0, DateTimeZone.UTC);
         Double[] initial = valoriCitite.firstEntry().getValue();
         while (startInterval.getYear() == 2009) {
            Formatter lineToWrite = new Formatter();
            Double valoareOzon = initial[0];
            Double valoareNOx = initial[1];
            Double valoareNO2 = initial[2];
            if (valoriCitite.containsKey(startInterval)) {
               initial = valoriCitite.get(startInterval);
               logger.debug("am gasit valoare:" + Arrays.toString(initial));
            } else {
            }
            valoareOzon = initial[0];
            valoareNOx = initial[1];
            valoareNO2 = initial[2];
            double procnox = 0d;
            if (valoareNOx != null && valoareNO2 != null) {
               procnox = ((valoareNOx - valoareNO2) * (1.882 / 1.227)) + valoareNO2;
            }
            if (valoareNOx == null) {
               valoareNOx = 0d;
            }
            if (valoareNO2 == null) {
               valoareNO2 = 0d;
            }
            if (valoareOzon == null) {
               valoareOzon = 0d;
            }
            lineToWrite.format("%02d%02d%02d %02d %f %f %f 5", startInterval.getYearOfCentury(),
                  startInterval.getMonthOfYear(), startInterval.getDayOfMonth(), startInterval.getHourOfDay(), 0d, 0d,
                  valoareOzon);
            outFile.println(lineToWrite.out());
            startInterval = startInterval.plusHours(1);
         }
         outFile.flush();
         outFile.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void printHeaderLines(PrintWriter outFile) {
      outFile.println("Dummy data.");
      outFile.println("!! NB !! All units must be in �g/m3 and NOx concentration must be in NO2-units: g NO2/m3 !!!");
      outFile.println("i.e. for NOx and NO2: 1 ppb = 1*0.53 g/m3, and for O3: 1 ppb = 1*0.50 g/m3.");
      outFile.println("e.g. NOx conc.: 10 gNO/m3 + 10 �gNO2/m3 =(10*1.882/1.227 + 10) gNO2/m3 =25.338 gNO2/m3.");
      outFile.println("DATE   HR     NOx     NO2     O3      RAD");
      outFile.println("yymmdd hh     �g/m3   g/m3   g/m3   W/m2  ");
   }

   /**
    * Doar o singura statie B8
    *
    * @param excell_citiri
    * @param formatKeyMap
    * @param statie
    * @return
    * @throws IOException
    * @throws FileNotFoundException
    */
   private TreeMap<DateTime, Double[]> citesteValoriMasurateOzon(String excell_citiri) throws IOException,
         FileNotFoundException {
      Workbook wb = new HSSFWorkbook(new FileInputStream(excell_citiri));
      Sheet sheet = wb.getSheet("date");
      Iterator<Row> it = sheet.rowIterator();
      TreeMap<DateTime, Double[]> valoriOzonExcell = new TreeMap<DateTime, Double[]>();
      int numarColoanaOzon = 6;
      int numarColoanaNOx = 4;
      int numarColoanaNO2 = 3;
      while (it.hasNext()) {
         Row row = it.next();
         if (row.getRowNum() < 4) {
            logger.debug("Row value skiped:" + row.getCell(0));
            continue;
         }
         if (row.getCell(1) != null && row.getCell(0) != null) {
            Integer ceas = ExcellUtils.readIntegerValue(row, 1);
            if (ceas == null) {
               continue;
            }
            int time = ceas;
            Double valoareOzon = 0d;
            Double valoareNOx = 0d;
            Double valoareNO2 = 0d;
            try {
               valoareOzon = ExcellUtils.readDouble(row, numarColoanaOzon);
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            try {
               valoareNOx = ExcellUtils.readDouble(row, numarColoanaNOx);
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            try {
               valoareNO2 = ExcellUtils.readDouble(row, numarColoanaNO2);
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            Date timp = ExcellUtils.readDateValueWithDefault(row, 0, null);
            DateTime usF = new DateTime(timp, DateTimeZone.UTC);
            usF = usF.withHourOfDay(time - 1);
            DateTime timpKey = usF.hourOfDay().roundFloorCopy();
            if (valoriOzonExcell.containsKey(timpKey)) {
               Double[] temp = valoriOzonExcell.get(timpKey);
               temp[0] = (temp[0] + valoareOzon) / 2;
               temp[1] = (temp[1] + valoareNOx) / 2;
               temp[2] = (temp[2] + valoareNO2) / 2;
               valoriOzonExcell.put(timpKey, temp);
               System.out.println("Out sys:" + timpKey.toString() + "  " + Arrays.toString(temp));
            } else {
               Double[] temp = new Double[3];
               temp[0] = valoareOzon;
               temp[1] = valoareNOx;
               temp[2] = valoareNO2;
               valoriOzonExcell.put(timpKey, temp);
               System.out.println("Out sys:" + timpKey.toString() + "  " + Arrays.toString(temp));
            }
         }
      }
      return valoriOzonExcell;
   }
}
