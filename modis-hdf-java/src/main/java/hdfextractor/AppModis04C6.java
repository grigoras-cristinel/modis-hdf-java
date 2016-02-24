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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.joda.time.DateTime;

/**
 * Aplicatie care extrage datele de spre nori din HDF in coordonatele de statii
 * date si le pune in folderul out pentru fiecare statie.
 *
 * @author Grigoras Cristinel
 *
 *
 */
public class AppModis04C6 {
   private static String LOCATIE_FISERE_HDF = "t:/data/MOD06MYD06_C6";
   private static String LOCATIE_EXPORT = "t:/data/MOD004";
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
      AppModis04C6 runn = new AppModis04C6();
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
      PrintStream fdebug = new PrintStream(new File(LOCATIE_EXPORT + "/debug-" + satelit + anul + "-" + timestampStart
            + ".log"));
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
      String runPath = LOCATIE_EXPORT + "/" + this.getClass().getSimpleName() + "/";
      String runPathProgress = LOCATIE_EXPORT + "/H" + StatiiMeteoModis06.getMd5() + "/";
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
            TreeMap<String, double[]> statii = StatiiMeteoModis06.statii;
            debug.println("MD5 : " + StatiiMeteoModis06.getMd5());
            try {
               File[] deCitit = folderInput.listFiles((FileFilter) new WildcardFileFilter(satelit + "04_L2.A" + anul
                     + "???.????.006.*.hdf"));
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
                  initializeazaOutputFiles(runPath, anul, satelit, statii);
                  H4FileWorkerC6Mod04 cfile = null;
                  try {
                     cfile = new H4FileWorkerC6Mod04(file.getCanonicalPath(), statii);
                  } catch (Exception e) {
                     debug.println("File open exception:" + file.getCanonicalPath());
                     e.printStackTrace(debug);
                     continue;
                  }
                  debug.print(i + "\t File hdf executed:" + name + " :");
                  boolean ok = false;
                  for (String siteName : statii.keySet()) {
                     debug.print(" " + siteName);
                     if (cfile.hasPosition(siteName)) {
                        Mod04C06TO fworkv = cfile.getValori().get(siteName);
                        PrintWriter pw = outputFiles.get(siteName);
                        pw.println(fworkv.toLongLine());
                        debug.print("+ ");
                        ok = true;
                     } else {
                        debug.print("- ");
                        File tomake = new File(folderRunPathProgress, name);
                        tomake.createNewFile();
                     }
                  }
                  debug.println(";");
                  cfile.close();
                  for (PrintWriter pws : outputFiles.values()) {
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

   HashMap<String, PrintWriter> outputFiles;

   private void initializeazaOutputFiles(String runPath, String anul, String satelit, TreeMap<String, double[]> statii) {
      if (this.outputFiles != null) {
         return;
      }
      outputFiles = new HashMap<String, PrintWriter>();
      for (String siteName : statii.keySet()) {
         File folderOutput = new File(runPath);
         if (!folderOutput.exists()) {
            folderOutput.mkdirs();
         }
         File fileOutput = new File(runPath + siteName + "_" + anul + "_" + satelit + "04.txt");
         debug.println("Output file:" + fileOutput);
         try {
            PrintWriter pw = new PrintWriter(fileOutput);
            pw.println(Mod06C06TO.toLongHeader());
            outputFiles.put(siteName, pw);
         } catch (Exception e) {
            e.printStackTrace(debug);
         }
      }
   }
}
