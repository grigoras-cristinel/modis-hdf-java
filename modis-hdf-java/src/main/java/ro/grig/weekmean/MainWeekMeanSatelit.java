/**
 *
 */
package ro.grig.weekmean;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrNotNullOrEmpty;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import ro.grig.sat.util.ParseDateTime;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset.Entry;

//@formatter:off
/**
 * Articolul chinejilor
 * <p> Analysis of the weekly cycle of aerosol optical depth using
 *                AERONET and MODIS data doi:10.1029/2007JD009604, </p>
 *
 *
 * @author grig
 *
 */
//@formatter:on
public class MainWeekMeanSatelit {
   private static final String folderFisiereCSV = "d:/proiecte/Google Drive/2014-articol/S04AERONET/";

   /**
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("Medie saptamanala pt AOD pe satelit si MODIS ");
      RegexFileFilter ff = new RegexFileFilter(".*_2COMB\\.csv");
      ArrayListMultimap<String, CombDataTO> zilePeStatie = ArrayListMultimap.create();
      ArrayListMultimap<String, CombDataTO> zilePeSaptamana = ArrayListMultimap.create();
      ArrayList<CombDataTO> zileDate = new ArrayList<CombDataTO>();
      try {
         File folder = new File(folderFisiereCSV);
         File[] list = folder.listFiles((FileFilter) ff);
         HashMultiset<CombDataTO> weekSizeCounter = HashMultiset.create();
         for (File file : list) {
            System.out.println("Fisier " + file.getName());
            CsvBeanReader bread = new CsvBeanReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE);
            bread.getHeader(true);
            CombDataTO read = null;
            do {
               read = bread.read(CombDataTO.class, new String[] { "data", "year", "month", "dayOfYear", "statie",
                     "aeoronetAOD", "satelitAOD", "aeoronetAngstrom", "satelitAngstrom" }, new ParseDateTime(),
                     new NotNull(new ParseInt()), new NotNull(new ParseInt()), new NotNull(new ParseInt()),
                     new StrNotNullOrEmpty(), new Optional(new ParseDouble()), new Optional(new ParseDouble()),
                     new Optional(new ParseDouble()), new Optional(new ParseDouble()));
               if (read != null) {
                  // System.out.println("AS:" +
                  // read.getSatelitAngstrom());
                  if (read.getSatelitAngstrom() != null) {
                     read.makeWeekKEy();
                     weekSizeCounter.add(read);
                     zilePeStatie.put(read.getStatie(), read);
                     zilePeSaptamana.put(read.getWeekKey(), read);
                     zileDate.add(read);
                  }
               }
            } while (read != null);
            bread.close();
         }
         for (String statie : zilePeStatie.keySet()) {
            System.out.println("keymi " + statie + " size " + zilePeStatie.get(statie).size());
         }
         for (Entry<CombDataTO> entry : weekSizeCounter.entrySet()) {
            CombDataTO elm = entry.getElement();
            // System.out.println("Statie " + elm.getStatie() + " Week "
            // + elm.getWeekKey().substring(elm.getStatie().length() + 4) +
            // " Weeksize " + entry.getCount());
         }
         /*
          * Conditii: minim 2 zile in saptamana si una din ele sa fie intre
          * marti si vineri, iar una sambata si duminica
          */
         ArrayList<String> excluderi = new ArrayList<String>();
         for (Entry<CombDataTO> entry : weekSizeCounter.entrySet()) {
            CombDataTO elm = entry.getElement();
            if (entry.getCount() < 2) {
               System.out.println("Exclud Statie " + entry.getElement().getStatie() + " week "
                     + elm.getWeekKey().substring(elm.getStatie().length() + 4));
               excluderi.add(elm.getWeekKey());
               continue;
            }
            List<CombDataTO> inSaptamana = zilePeSaptamana.get(entry.getElement().getWeekKey());
            boolean gasit[] = new boolean[2];
            for (CombDataTO combDataTO : inSaptamana) {
               switch (combDataTO.getDayOfWeek()) {
               case 1:// Monday
               case 6:// Saturday
               case 7:// Sunday
                  gasit[1] = true;
                  break;
               // case 2:// Tuesday
               case 3:// Wedsday
               case 4:// Thursday
               case 5:// Friday
                  gasit[0] = true;
                  break;
               default:
                  break;
               }
            }
            if (gasit[0] && gasit[1]) {
               continue;
            } else {
               excluderi.add(elm.getWeekKey());
               System.out.println("Exclud saptamana neuniforma " + Arrays.toString(gasit) + "  "
                     + entry.getElement().getStatie() + " week "
                     + elm.getWeekKey().substring(elm.getStatie().length() + 4) + " total zile " + entry.getCount());
            }
         }
         System.out.println("Zile total " + zileDate.size());
         Iterator<CombDataTO> it = zileDate.iterator();
         while (it.hasNext()) {
            CombDataTO combDataTO = (CombDataTO) it.next();
            if (excluderi.contains(combDataTO.getWeekKey())) {
               it.remove();
            }
         }
         System.out.println("Zile ramase dupa excludere " + zileDate.size());
         weekSizeCounter.clear();
         for (CombDataTO statie : zileDate) {
            // System.out.println("Coduri saptamani " + statie.getWeekKey() +
            // " day " + statie.getDayOfYear());
            weekSizeCounter.add(statie);
         }
         System.out.println("Saptamani ramase dupa excludere " + weekSizeCounter.entrySet().size());
         HashMultimap<String, CombDataTO> saptamaniStatie = HashMultimap.create();
         for (Entry<CombDataTO> entry : weekSizeCounter.entrySet()) {
            CombDataTO elm = entry.getElement();
            saptamaniStatie.put(elm.getStatie(), elm);
         }
         for (java.util.Map.Entry<String, Collection<CombDataTO>> entry : saptamaniStatie.asMap().entrySet()) {
            System.out.println("Saptamani pt statia:" + entry.getKey() + " " + entry.getValue().size());
         }
         CombDataTO suma = new CombDataTO();
         suma.setMonth(0);
         /*
          * Statie an, saptamana, mean (saptamana, workday , weekend day)
          */
         HashMap<String, HashMap<Integer, HashMap<Integer, CombDataTO[]>>> st_an_sap = new HashMap<String, HashMap<Integer, HashMap<Integer, CombDataTO[]>>>();
         HashMap<String, CombDataTO[]> wms = new HashMap<String, CombDataTO[]>();
         for (CombDataTO elm : zileDate) {
            suma.add(elm);
            if (!wms.containsKey(elm.getStatie())) {
               wms.put(elm.getStatie(), initWeekMean());
            }
            wms.get(elm.getStatie())[elm.getDayOfWeek() - 1].add(elm);
            if (!st_an_sap.containsKey(elm.getStatie())) {
               HashMap<Integer, HashMap<Integer, CombDataTO[]>> im = new HashMap<Integer, HashMap<Integer, CombDataTO[]>>();
               st_an_sap.put(elm.getStatie(), im);
            }
            HashMap<Integer, HashMap<Integer, CombDataTO[]>> im = st_an_sap.get(elm.getStatie());
            if (!im.containsKey(elm.getYear())) {
               HashMap<Integer, CombDataTO[]> stm = new HashMap<Integer, CombDataTO[]>();
               im.put(elm.getYear(), stm);
            }
            HashMap<Integer, CombDataTO[]> stm = im.get(elm.getYear());
            if (!stm.containsKey(elm.getWeekOfYear())) {
               stm.put(elm.getWeekOfYear(), initBestMean());
            }
            stm.get(elm.getWeekOfYear())[0].add(elm);
            System.out.println("Merg peste valori:" + elm.toString());
            switch (elm.getDayOfWeek()) {
            case 1:// Monday
            case 6:// Saturday
            case 7:// Sunday
               stm.get(elm.getWeekOfYear())[1].add(elm);
               break;
            // case 2:// Tuesday
            case 3:// Wedsday
            case 4:// Thursday
            case 5:// Friday
               stm.get(elm.getWeekOfYear())[2].add(elm);
               break;
            default:
               break;
            }
         }
         System.out.println("Am insumat in " + suma.toString());
         CombDataTO mean = suma.buildMean();
         System.out.println("Am facut medie generala in: " + mean.toString());
         HashMap<String, DescriptiveStatistics> wci_aeronet_statie = new HashMap<String, DescriptiveStatistics>();
         HashMap<String, DescriptiveStatistics> aeronet_mean = new HashMap<String, DescriptiveStatistics>();
         HashMap<String, DescriptiveStatistics> satelit_mean = new HashMap<String, DescriptiveStatistics>();
         HashMap<String, DescriptiveStatistics> wci_satelit_statie = new HashMap<String, DescriptiveStatistics>();
         for (java.util.Map.Entry<String, HashMap<Integer, HashMap<Integer, CombDataTO[]>>> esta : st_an_sap.entrySet()) {
            HashMap<Integer, HashMap<Integer, CombDataTO[]>> estav = esta.getValue();
            DescriptiveStatistics stat_wci_statie_aeronet = new DescriptiveStatistics();
            DescriptiveStatistics stat_wci_statie_satelit = new DescriptiveStatistics();
            wci_aeronet_statie.put(esta.getKey(), stat_wci_statie_aeronet);
            wci_satelit_statie.put(esta.getKey(), stat_wci_statie_satelit);
            aeronet_mean.put(esta.getKey(), new DescriptiveStatistics());
            satelit_mean.put(esta.getKey(), new DescriptiveStatistics());
            for (java.util.Map.Entry<Integer, HashMap<Integer, CombDataTO[]>> ean : estav.entrySet()) {
               HashMap<Integer, CombDataTO[]> eanv = ean.getValue();
               for (java.util.Map.Entry<Integer, CombDataTO[]> ewe : eanv.entrySet()) {
                  CombDataTO[] ewev = ewe.getValue();
                  CombDataTO ewev_mean = ewev[0].buildMean();
                  aeronet_mean.get(esta.getKey()).addValue(ewev_mean.getAeoronetAOD());
                  satelit_mean.get(esta.getKey()).addValue(ewev_mean.getSatelitAOD());
                  CombDataTO ewev_mean_w = ewev[1].buildMean(); // weekend
                  CombDataTO ewev_mean_m = ewev[2].buildMean();// middle
                  double wci_w_aeronet = 100 * ((ewev_mean_m.getAeoronetAOD() - ewev_mean_w.getAeoronetAOD()) / ewev_mean
                        .getAeoronetAOD());
                  System.out.println("" + esta.getKey() + ":" + ean.getKey() + ":" + ewe.getKey() + ":"
                        + ewev_mean_m.getAeoronetAOD());
                  stat_wci_statie_aeronet.addValue(wci_w_aeronet);
                  double wci_w_modis = 100 * ((ewev_mean_m.getSatelitAOD() - ewev_mean_w.getSatelitAOD()) / ewev_mean
                        .getSatelitAOD());
                  stat_wci_statie_satelit.addValue(wci_w_modis);
               }
            }
         }
         System.out.println("Statie; WCI Aeronet ; WCI Satelit ; AOD mean AERONET ; AOD mean satelit ; Valori");
         for (java.util.Map.Entry<String, DescriptiveStatistics> ess : wci_aeronet_statie.entrySet()) {
            System.out.println("" + ess.getKey() + " ; " + wci_aeronet_statie.get(ess.getKey()).getMean() + " ; "
                  + wci_satelit_statie.get(ess.getKey()).getMean() + ";" + aeronet_mean.get(ess.getKey()).getMean()
                  + ";" + satelit_mean.get(ess.getKey()).getMean() + " ; " + satelit_mean.get(ess.getKey()).getN());
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static CombDataTO[] initWeekMean() {
      CombDataTO weekMean[] = new CombDataTO[7];
      for (int i = 0; i < weekMean.length; i++) {
         weekMean[i] = new CombDataTO();
         weekMean[i].setDayOfWeek(i + 1);
         weekMean[i].setMonth(0);
         weekMean[i].setAeoronetAngstrom(0d);
         weekMean[i].setAeoronetAOD(0d);
         weekMean[i].setSatelitAngstrom(0d);
         weekMean[i].setSatelitAOD(0d);
      }
      return weekMean;
   }

   private static CombDataTO[] initBestMean() {
      CombDataTO weekMean[] = new CombDataTO[3];
      for (int i = 0; i < weekMean.length; i++) {
         weekMean[i] = new CombDataTO();
         weekMean[i].setDayOfWeek(i + 1);
         weekMean[i].setMonth(0);
         weekMean[i].setAeoronetAngstrom(0d);
         weekMean[i].setAeoronetAOD(0d);
         weekMean[i].setSatelitAngstrom(0d);
         weekMean[i].setSatelitAOD(0d);
      }
      return weekMean;
   }
}
