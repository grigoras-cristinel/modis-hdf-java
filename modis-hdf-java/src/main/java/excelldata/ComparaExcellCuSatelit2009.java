package excelldata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import hdfextractor.StatiiMeteoBucuresti;
import hdfextractor.ozon.CalculeazaOzonLaSol;

/**
 * Aceasta clasa va citi ozonul extras din satelit in dobson si va crea un
 * dataset cu ozonul din satelit si cel masurat la sol din fisierul excell
 * 
 * @author Grig
 * 
 */
public class ComparaExcellCuSatelit2009 {

   /**
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("Start ComparaExcellCuSatelit2009");
      ComparaExcellCuSatelit2009 worker = new ComparaExcellCuSatelit2009();
      try {
         worker.execute();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      System.out.println("End");
   }

   String formatKeyMap = "yyyy-MM-dd-HH";

   /**
    * @throws IOException
    * 
    */
   private void execute() throws Exception {
      String excell_citiri = "data/Ozon2009.xls";
      String excell_date_amestec = "data/zmix_2009.dat";
      System.out.println("Fisier ozon si meteo" + excell_citiri + "," + excell_date_amestec + " .");
      String prefixFisierIntrare = "target/omi/omi-2009-";
      // String prefixFisierIntrare = "target/modis-2009-output-";
      // String prefixFisierIntrare = "target/modis-2009-output-";
      System.out.println("Fisier intrare" + prefixFisierIntrare + " .");
      // String prefixFisierIesire = "target/omi-compara-2009-";
      // String prefixFisierIesire = "target/modis-statii-2009-";
      String prFolder = new SimpleDateFormat("YYYYDDDHHmmss").format(new Date());
      String outputFolder = "target/omi-" + prFolder;
      File of = new File(outputFolder);
      if (!of.exists()) {
         of.mkdirs();
      }
      String prefixFisierIesire = outputFolder + "/omi-com-2009-";
      System.out.println(" Fisier iesire" + prefixFisierIesire + " .");
      PrintWriter readmeFile = new PrintWriter(new FileWriter(outputFolder + "/README.txt"));
      readmeFile.write("    Descriere rulare\n");
      readmeFile.write("Am rulat cu 10% din ozonul dobson pentru inaltimea de amestec din fisierul cu date meteo \n");
      readmeFile.write("Cor. prescuratarea de la corectie de temperatura\n");
      readmeFile.write("Coloanele cu 40 sunt cu 40% din ozon dobson in inaltimea de amestec\n");
      readmeFile.write(
            "- Am presupus ca ozonul contribuie 10% din valoarea dobson inzona inaltimii de amestec am calculat \n"
                  + " concentratie masica pentru acea valoare dobson \n");
      readmeFile.flush();
      for (Entry<String, double[]> statie : StatiiMeteoBucuresti.statii.entrySet()) {
         /*
          * Se inlocuieste "combined-statie-" cu numele altui fisier de date
          * satelitare (OMI sau MODIS)
          */
         String combinedIn = prefixFisierIntrare + statie.getKey() + ".txt";
         /*
          * "comparare-" se modifica cu numele fisierului de iesire dorit
          */
         String combinedOut = prefixFisierIesire + statie.getKey() + ".txt";
         TreeMap<String, Double> valoriOzonDobson = new TreeMap<String, Double>();
         TreeMap<String, Double> valoriHAmestec;
         ArrayList<PerecheDouble> bootF0 = new ArrayList<PerecheDouble>();
         ArrayList<PerecheDouble> bootF1 = new ArrayList<PerecheDouble>();
         TreeMap<String, Double> valoriTemp = new TreeMap<String, Double>();
         TreeMap<String, Double> valoriPresiune = new TreeMap<String, Double>();
         TreeMap<String, Integer> valoriNebulozitate = new TreeMap<String, Integer>();
         TreeMap<String, LinieDateMeteoTO> valoriMeteo = new TreeMap<String, LinieDateMeteoTO>();
         try {
            valoriHAmestec = citesteInaltimeAmesteDinOML(excell_date_amestec, formatKeyMap);
            valoriMeteo = citesteDateMeteo("2009", "B1");
            Reader readFileTemp = new InputStreamReader(new FileInputStream("data/meteo-2009-B1.dat"));
            // 2009-01-01 00:00:00.0
            DateTimeFormatter fmtFileMeteo = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S")
                  .withZone(DateTimeZone.UTC);
            int contorFm = 0;
            Scanner readFileTempScan = new Scanner(readFileTemp);
            while (readFileTempScan.hasNextLine()) {
               String line = readFileTempScan.nextLine();
               contorFm++;
               if (contorFm == 1) {
                  continue;
               }
               Scanner rnl = new Scanner(line);
               rnl.useDelimiter(",");
               String data = rnl.next();
               DateTime timp = fmtFileMeteo.parseDateTime(data);
               String dateKey = timp.toString(formatKeyMap);
               int nebulozitate = 0;
               try {
                  String snebulozitate = rnl.next();
                  nebulozitate = Integer.parseInt(snebulozitate);
               } catch (Exception e) {
                  System.err.println("Eroare fisier meteo, nebulozitate linia:" + line);
               }
               valoriNebulozitate.put(dateKey, nebulozitate);
               double temperatura = rnl.nextDouble();
               valoriTemp.put(dateKey, temperatura + 273.5d);
               double presiune = rnl.nextDouble();
               valoriPresiune.put(dateKey, presiune);
               rnl.close();
            }
            readFileTempScan.close();
            readFileTemp.close();
            valoriOzonDobson = citesteValoriSatelitOzon(formatKeyMap, combinedIn);
            System.out.println(statie.getKey() + " Am gasit:" + valoriOzonDobson.size());
            TreeMap<String, Double> valoriOzonExcell = citesteValoriMasurateOzon(excell_citiri, formatKeyMap, statie);
            System.out.println(statie.getKey() + " Am gasit excell:" + valoriOzonExcell.size());
            PrintWriter outFile = new PrintWriter(new FileWriter(combinedOut));
            outFile.format("%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\n", "Data " + formatKeyMap,
                  " Ozon la statie ug ", " Ozon ug 10% hm ", " Cor. ug 10%hm ", " Valoare in dobson ", " Temperatura ",
                  " Presiune ", " Inaltime amestec ", " Nebulozitate ", " Ozon ug 10 m ", " Cor. ug 10m ",
                  " Ozon ug 40% ha", " Cor. ug 40% ha");
            CalculeazaOzonLaSol calc = new CalculeazaOzonLaSol();
            for (Entry<String, Double> ib1mas : valoriOzonExcell.entrySet()) {
               String luna = ib1mas.getKey().split("-")[1];
               if (valoriOzonDobson.containsKey(ib1mas.getKey())) {
                  Double inaltimeAmestec = valoriHAmestec.get(ib1mas.getKey());
                  /*
                   * Adaugare dobson doar 10 la suta cu h amestec
                   */
                  double calculeazaConcentratieugm3 = calc.calculeazaConcentratiePtDobsonCunoscutugm3(
                        valoriOzonDobson.get(ib1mas.getKey()) * 10 / 100, inaltimeAmestec);
                  Double presiune = valoriPresiune.get(ib1mas.getKey());
                  Double temperatura = valoriTemp.get(ib1mas.getKey());
                  double concentratieCuCorectie = calculeazaConcentratieugm3 * (1013 / presiune)
                        * (temperatura / 273.15);
                  // 10 metri dupa formula
                  double calculeazaConcentratie10mugm3 = calc.calculeazaConcentratieugm3(Integer.parseInt(luna) - 1,
                        valoriOzonDobson.get(ib1mas.getKey()), 10);
                  double concentratieCuCorectie10m = calculeazaConcentratie10mugm3 * (1013 / presiune)
                        * (temperatura / 273.15);
                  // 40% cu h amestec
                  double calculeazaConcentratie40ugm3 = calc.calculeazaConcentratiePtDobsonCunoscutugm3(
                        valoriOzonDobson.get(ib1mas.getKey()) * 40 / 100, inaltimeAmestec);
                  double concentratieCuCorectie40 = calculeazaConcentratie40ugm3 * (1013 / presiune)
                        * (temperatura / 273.15);
                  outFile.format(
                        "%10s\t%5s\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16d\t%16.8f"
                              + "\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\n",
                        ib1mas.getKey().substring(0, 10), ib1mas.getKey().substring(11) + ":00", ib1mas.getValue(),
                        calculeazaConcentratieugm3, concentratieCuCorectie, valoriOzonDobson.get(ib1mas.getKey()),
                        temperatura, presiune, inaltimeAmestec, valoriNebulozitate.get(ib1mas.getKey()),
                        calculeazaConcentratie10mugm3, concentratieCuCorectie10m, calculeazaConcentratie40ugm3,
                        concentratieCuCorectie40);
                  outFile.flush();
                  PerecheDouble p = new PerecheDouble(ib1mas.getValue(), concentratieCuCorectie);
                  bootF0.add(p);
                  PerecheDouble p1 = new PerecheDouble(ib1mas.getValue(), calculeazaConcentratieugm3);
                  bootF1.add(p1);
               }
            }
            creazaFisierBoot("modis", prefixFisierIesire + "boot_", bootF0, statie.getKey() + "_0", "O");
            creazaFisierBoot("modis", prefixFisierIesire + "boot_", bootF1, statie.getKey() + "_1", "1");
            outFile.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   private TreeMap<String, LinieDateMeteoTO> citesteDateMeteo(String anul, String statie) throws Exception {
      TreeMap<String, LinieDateMeteoTO> retval = new TreeMap<String, LinieDateMeteoTO>();
      Reader readFileTemp = new InputStreamReader(new FileInputStream("data/meteo-" + anul + "-" + statie + ".dat"));
      // 2009-01-01 00:00:00.0
      DateTimeFormatter fmtFileMeteo = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S").withZone(DateTimeZone.UTC);
      int contorFm = 0;
      Scanner readFileTempScan = new Scanner(readFileTemp);
      while (readFileTempScan.hasNextLine()) {
         LinieDateMeteoTO ldm = new LinieDateMeteoTO();
         String line = readFileTempScan.nextLine();
         contorFm++;
         if (contorFm == 1) {
            continue;
         }
         Scanner rnl = new Scanner(line);
         rnl.useDelimiter(",");
         String data = rnl.next();
         DateTime timp = fmtFileMeteo.parseDateTime(data);
         String dateKey = timp.toString(formatKeyMap);
         int nebulozitate = 0;
         try {
            String snebulozitate = rnl.next();
            nebulozitate = Integer.parseInt(snebulozitate);
         } catch (Exception e) {
            System.err.println("Eroare fisier meteo, nebulozitate linia:" + line);
         }
         ldm.nebulozitate = nebulozitate;
         double temperatura = rnl.nextDouble();
         ldm.temperaturaCelsius = temperatura;
         double presiune = rnl.nextDouble();
         ldm.presiuneColoana = presiune;
         retval.put(dateKey, ldm);
      }
      readFileTemp.close();
      return null;
   }

   private TreeMap<String, Double> citesteInaltimeAmesteDinOML(String excell_date_amestec, String formatKeyMap)
         throws FileNotFoundException, IOException {
      TreeMap<String, Double> valoriHAmestec = new TreeMap<String, Double>();
      Reader readFileMeteoOML = new InputStreamReader(new FileInputStream(excell_date_amestec));
      // 2009-01-01 00:00:00.0
      DateTimeFormatter fmtFileMeteoOML = DateTimeFormat.forPattern("yyMMdd").withZone(DateTimeZone.UTC);
      int contorFmOML = 0;
      Scanner scanFileMeteoOML = new Scanner(readFileMeteoOML);
      while (scanFileMeteoOML.hasNextLine()) {
         String line = scanFileMeteoOML.nextLine();
         contorFmOML++;
         if (contorFmOML <= 10) {
            continue;
         }
         Scanner rnl = new Scanner(line);
         rnl.useDelimiter(",");
         String data = "0" + line.substring(0, 6).trim();
         String sora = line.substring(6, 9).trim();
         int ora = Integer.parseInt(sora.trim());
         DateTime timp = fmtFileMeteoOML.parseDateTime(data);
         timp = timp.withHourOfDay(ora);
         String dateKey = timp.toString(formatKeyMap);
         String sameste = line.substring(78, 83).trim();
         Double HAmestec = Double.valueOf(sameste);
         if (valoriHAmestec.containsKey(dateKey)) {
            double temp = valoriHAmestec.get(dateKey);
            temp = (temp + HAmestec) / 2;
         } else {
            valoriHAmestec.put(dateKey, HAmestec);
         }
      }
      readFileMeteoOML.close();
      return valoriHAmestec;
   }

   private TreeMap<String, Double> citesteValoriSatelitOzon(String formatKeyMap, String combinedIn)
         throws FileNotFoundException, IOException {
      TreeMap<String, Double> valoriOzonDobson = new TreeMap<String, Double>();
      Reader textFileReader = new InputStreamReader(new FileInputStream(combinedIn));
      Scanner rn = new Scanner(textFileReader);
      DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm").withZone(DateTimeZone.UTC);
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
         Double ozon = rnl.nextDouble();// CM
         String dateKey = timp.toString(formatKeyMap);
         if (ozon > 0) {
            if (valoriOzonDobson.containsKey(dateKey)) {
               double temp = valoriOzonDobson.get(dateKey);
               valoriOzonDobson.put(dateKey, (ozon + temp) / 2);
            } else {
               valoriOzonDobson.put(dateKey, ozon);
            }
         }
      }
      textFileReader.close();
      return valoriOzonDobson;
   }

   private TreeMap<String, Double> citesteValoriMasurateOzon(String excell_citiri, String formatKeyMap,
         Entry<String, double[]> statie) throws IOException, FileNotFoundException {
      Workbook wb = new HSSFWorkbook(new FileInputStream(excell_citiri));
      Sheet sheet = wb.getSheet("ozon");
      Iterator<Row> it = sheet.rowIterator();
      TreeMap<String, Double> valoriOzonExcell = new TreeMap<String, Double>();
      int numarColoanaStatie = -1;
      while (it.hasNext()) {
         Row row = it.next();
         if (row.getRowNum() == 0) {
            Iterator<Cell> itCel = row.cellIterator();
            while (itCel.hasNext()) {
               Cell cell = itCel.next();
               String strGasit = ExcellUtils.readStringFromCell(cell);
               if (StringUtils.equalsIgnoreCase(strGasit, statie.getKey())) {
                  numarColoanaStatie = cell.getColumnIndex();
               }
            }
            continue;
         }
         if (row.getCell(1) != null && row.getCell(0) != null) {
            Date ceas = ExcellUtils.readTimeValueWithDefault(row, 1, null);
            if (ceas == null) {
               continue;
            }
            int time = ceas.getHours();
            Double masicInStatie = 0d;
            try {
               masicInStatie = ExcellUtils.readDouble(row, numarColoanaStatie);
            } catch (Exception e) {
               System.out.println(e.getMessage());
               continue;
            }
            if (masicInStatie != null) {
               Date timp = ExcellUtils.readDateValueWithDefault(row, 0, null);
               DateTime usF = new DateTime(timp, DateTimeZone.forID("Europe/Bucharest"));
               usF = usF.withHourOfDay(time);
               String key = usF.toString(formatKeyMap);
               if (valoriOzonExcell.containsKey(key)) {
                  double temp = valoriOzonExcell.get(key);
                  valoriOzonExcell.put(key, (temp + masicInStatie) / 2);
               } else {
                  valoriOzonExcell.put(key, masicInStatie);
               }
            }
         }
      }
      return valoriOzonExcell;
   }

   private void creazaFisierBoot(String satelit, String prefixFisierBoot, ArrayList<PerecheDouble> valori,
         String numeBoot1, String indice) throws IOException {
      PrintWriter bootFile = new PrintWriter(new FileWriter(prefixFisierBoot + numeBoot1 + ".boxt"));
      bootFile.format("%-25s1YFNNNN\n", satelit + numeBoot1 + "-b.out");
      bootFile.format("%5d %5d %5d\n", valori.size(), 2, 1);
      bootFile.format("%5d\n", valori.size());
      bootFile.format("'%8s' '%8s'\n", "Masurat", "Calcul_" + indice);
      bootFile.format("'%20s'\n", satelit + numeBoot1);
      for (PerecheDouble perecheDouble : valori) {
         bootFile.format("%10.6f %10.6f\n", perecheDouble.v1, perecheDouble.v2);
      }
      bootFile.flush();
      bootFile.close();
   }
}
