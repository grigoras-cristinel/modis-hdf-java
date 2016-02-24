package ro.grig.multipoint;

import hdfextractor.AppModis04A;
import hdfextractor.ConfigurationLocalComputer;
import hdfextractor.H4FileWorkerMod04;
import hdfextractor.Mod04TO;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import ro.grig.sat.util.StatieMeteoTO;

//@formatter:off
/**
 * Produsl este modis 04
 * A. Propune punctele in care cauta.
 * 1. Cauta zilele care au cel putin doua rulari pentru aerosol in timpul zilei. 
 * 2. Prima rulare trebuie sa aiba aerosol si a doua sa aiba nori.
 * 
 * @author grig
 *
 */
//@formatter:on
public class MainRunExtractor {

   public static String HDFFILES_FOLDER = ConfigurationLocalComputer.CALE_DATE_SATELIT + "MOD04_C6/";
   public static String WORK_FOLDER = "d:/grig/work/multipoint/";

   /**
    * Doi parametri primul e satelitul iar al doilea e anul
    * 
    * @param args
    * @throws Throwable
    */
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws Throwable {
      System.out.println("//start extragere date din puncte");
      int test = 0;
      int contor = 0;
      System.out.println("args" + Arrays.toString(args));
      if (args.length < 2) {
         System.err.println("Numar argumente gresit!!! Trebuie 2 parametri  an si satelit");
         System.exit(1);
      }
      String anii[] = new String[] { args[1] };
      String sateliti[] = new String[] { args[0] };
      // String anii[] = new String[] { "2003", "2011" };
      // String sateliti[] = new String[] { "MOD", "MYD" };
      HashMap<String, HashSet<String>> historyProcesare = new HashMap<String, HashSet<String>>();
      File bkupFisereRulate = new File(WORK_FOLDER + AppModis04A.class.getSimpleName() + "6.dmp");
      if (bkupFisereRulate.exists()) {
         try {
            FileInputStream fin = new FileInputStream(bkupFisereRulate);
            ObjectInputStream oin = new ObjectInputStream(fin);
            historyProcesare = (HashMap<String, HashSet<String>>) oin.readObject();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      String runPath = WORK_FOLDER + "extras/";
      String runPathProgress = WORK_FOLDER + "extras/R1/";
      File folderRunPathProgress = new File(runPathProgress);
      folderRunPathProgress.mkdirs();
      DatabaseTools dtb = new DatabaseTools();
      dtb.start();
      for (int j = 0; j < anii.length; j++) {
         String anul = anii[j];
         for (int k = 0; k < sateliti.length; k++) {
            contor = 0;
            String satelit = sateliti[k];
            if (!historyProcesare.containsKey(anul + satelit)) {
               historyProcesare.put(anul + satelit, new HashSet<String>());
            }
            HashSet<String> tt = historyProcesare.get(anul + satelit);
            String outputPath = runPath;
            String pathname_output = outputPath;
            HashMap<String, CsvBeanWriter> outputFiles = new HashMap<String, CsvBeanWriter>();
            List<StatieMeteoTO> statii = StatiiMeteoProviderDinamic.statiiSelectate();
            for (StatieMeteoTO siteName : statii) {
               File folderOutput = new File(pathname_output);
               if (!folderOutput.exists()) {
                  folderOutput.mkdirs();
               }
               File fileOutput = new File(pathname_output + satelit + "04_" + anul + "_" + siteName.getCod() + ".txt");
               // System.out.println("Output file:" + fileOutput);
               try {
                  CsvBeanWriter pw = new CsvBeanWriter(new FileWriter(fileOutput), CsvPreference.STANDARD_PREFERENCE);
                  pw.writeHeader(Mod04TO.headers);
                  outputFiles.put(siteName.getCod(), pw);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
            String inputPath = HDFFILES_FOLDER;
            File folderInput = new File(inputPath);// MOD04_L2.A2003276.0835.006.2014329130644
            // satelit + "04_L2.A" + anul + "*.hdf"
            FileFilter fileFilter = new WildcardFileFilter(satelit + "04_L2.A" + anul + "*.hdf");
            try {
               File[] deCitit = folderInput.listFiles(fileFilter);
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
                     System.out.println(i + "\t File hdf found in folder run:" + name + " ");
                     continue;
                  }
                  if (tt.contains(name)) {
                     System.out.println(i + "\t File hdf found prelucrat:" + name + " ");
                     continue;
                  }
                  H4FileWorkerMod04 cfile = null;
                  try {
                     cfile = new H4FileWorkerMod04(file.getCanonicalPath(), statii);
                  } catch (Exception e) {
                     System.out.println("File open exception:" + file.getCanonicalPath() + " Msg=" + e.getMessage());
                     e.printStackTrace(System.out);
                     continue;
                  }
                  System.out.print(i + "\t File hdf executed:" + name + " :");
                  boolean ok = false;
                  int contBreak = 20;
                  for (StatieMeteoTO siteName : statii) {
                     if (cfile.hasPosition(siteName.getCod())) {
                        System.out.print("\n(" + siteName.getCod());
                        Mod04TO mod04to = cfile.getValori().get(siteName.getCod());
                        mod04to.setNumePozitie(siteName.getCod());
                        CsvBeanWriter pw = outputFiles.get(siteName.getCod());
                        pw.write(mod04to, Mod04TO.headers, Mod04TO.getWriteProcessors());
                        if (mod04to.getDeepBlueAerosolOpticalDepth550Land() != null) {
                           // System.out.println("");
                           // System.out.println(mod04to.toTestString());
                        }
                        mod04to.buildDatabaseKey();
                        System.out.print(" " + mod04to.getDatabaseKey() + " ) ");
                        dtb.insertMod04(mod04to);
                        ok = true;
                     } else {
                        // System.out.print("\n(" + siteName.getCod());
                        // System.out.print(" N/A) ");
                     }
                     contBreak--;
                     if (contBreak <= 0) {
                        contBreak = 20;
                     }
                  }
                  if (!ok) {
                     System.out.print(" NU ");
                     File tomake = new File(folderRunPathProgress, name);
                     tomake.createNewFile();
                  }
                  System.out.println(";");
                  cfile.close();
                  for (CsvBeanWriter pws : outputFiles.values()) {
                     pws.flush();
                  }
                  if (ok) {
                     contor++;
                     if (test > 0 && contor > test) {
                        break;
                     }
                  } else {
                     historyProcesare.get(anul + satelit).add(name);
                  }
               }
            } catch (Exception e) {
               e.printStackTrace();
               System.err.println("Exceptie in:" + e);
            } finally {
            }
         }
      }
      try {
         dtb.end();
         FileOutputStream fin = new FileOutputStream(bkupFisereRulate);
         ObjectOutputStream oin = new ObjectOutputStream(fin);
         oin.writeObject(historyProcesare);
         fin.flush();
      } catch (Exception e) {
         e.printStackTrace();
      }
      System.out.println("End");
   }
}
