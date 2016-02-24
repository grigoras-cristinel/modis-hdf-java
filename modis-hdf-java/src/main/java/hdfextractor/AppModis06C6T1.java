package hdfextractor;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.joda.time.DateTime;

/**
 * Aplicatia testeaza fisierele daca se pot deschide,Integritate, nu parcurge
 * nimic.
 *
 * @author Grigoras Cristinel
 *
 *
 */
public class AppModis06C6T1 {
   private static String LOCATIE_FISERE_HDF = "t:/data/MOD06MYD06_C6";
   private static String LOCATIE_EXPORT = "t:/data/MOD06MYD06_C6_export";
   private static PrintStream debug;

   /**
    * Doi parametri primul e satelitul iar al doilea e anul
    *
    * @param args
    * @throws FileNotFoundException
    */
   public static void main(String[] args) throws FileNotFoundException {
      if (System.getProperty("locatie.fisiere.hdf") != null) {
         LOCATIE_FISERE_HDF = System.getProperty("locatie.fisiere.hdf");
         System.out.println("locatie.fisiere.hdf folder setat din property :" + LOCATIE_FISERE_HDF);
      }
      if (System.getProperty("locatie.export") != null) {
         LOCATIE_EXPORT = System.getProperty("locatie.export");
         System.out.println("locatie.export folder setat din property :" + LOCATIE_EXPORT);
      }
      AppModis06C6T1 runn = new AppModis06C6T1();
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
      runn.runMain(args);
   }

   private void runMain(String[] args) {
      debug.println("Test integritate");
      System.out.println("Start Test integritate");
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
      String runPath = LOCATIE_EXPORT + "/" + this.getClass().getSimpleName() + "/";
      for (int j = 0; j < anii.length; j++) {
         String anul = anii[j];
         for (int k = 0; k < sateliti.length; k++) {
            String satelit = sateliti[k];
            HashMap<String, HashSet<String>> historyProcesare = new HashMap<String, HashSet<String>>();
            File bkupFisereRulate = new File(LOCATIE_EXPORT + "/historyProcesare_" + this.getClass().getName() + ".dmp");
            if (bkupFisereRulate.exists()) {
               try {
                  FileInputStream fin = new FileInputStream(bkupFisereRulate);
                  ObjectInputStream oin = new ObjectInputStream(fin);
                  historyProcesare = (HashMap<String, HashSet<String>>) oin.readObject();
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
            contor = 0;
            if (!historyProcesare.containsKey(anul + satelit)) {
               historyProcesare.put(anul + satelit, new HashSet<String>());
            }
            HashSet<String> tt = historyProcesare.get(anul + satelit);
            String inputPath = LOCATIE_FISERE_HDF;
            String outputPath = runPath;
            File folderInput = new File(inputPath);
            debug.println("Start folder:" + folderInput);
            TreeMap<String, double[]> statii = StatiiMeteoModis06.statii;
            try {
               File[] deCitit = folderInput.listFiles((FileFilter) new WildcardFileFilter(satelit + "06_L2.A" + anul
                     + "*.hdf"));
               for (int i = 0; i < deCitit.length; i++) {
                  File file = deCitit[i];
                  if (file.isDirectory()) {
                     continue;
                  }
                  final String name = file.getName();
                  if (!name.endsWith("hdf")) {
                     continue;
                  }
                  if (tt.contains(name)) {
                     debug.println(i + "\t File hdf found prelucrat:" + name + " ");
                     continue;
                  }
                  H4FileWorkerC6Mod06 cfile = null;
                  try {
                     cfile = new H4FileWorkerC6Mod06(file.getCanonicalPath(), statii);
                  } catch (Exception e) {
                     debug.println("File open exception:" + file.getCanonicalPath() + " " + e.getMessage());
                     e.printStackTrace(debug);
                     continue;
                  }
                  debug.print(i + "\t File hdf executed:" + name + " :");
                  boolean ok = false;
                  for (String siteName : statii.keySet()) {
                     debug.print(" " + siteName);
                     if (cfile.hasPosition(siteName)) {
                        debug.print("+");
                        ok = true;
                     } else {
                        debug.print("-");
                     }
                  }
                  debug.println(";");
                  cfile.close();
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
               debug.println("Exceptie e:" + e.getMessage());
               e.printStackTrace(debug);
            } finally {
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
         }
      }
      debug.println("End");
      System.out.println("End Test integritate");
   }
}
