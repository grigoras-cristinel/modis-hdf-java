package hdfextractor;

import java.io.File;
import java.io.FileFilter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 * Aplicatie care extrage datele de spre nori din HDF in coordonatele de statii
 * date si le pune in folderul out pentru fiecare statie.
 *
 * @author Grigoras Cristinel
 *
 *
 */
public class AppModis06C6Test {
   private static String LOCATIE_FISERE_HDF = "t:/data/MOD06MYD06_C6";

   /**
    * Doi parametri primul e satelitul iar al doilea e anul
    *
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("args" + Arrays.toString(args));
      String inputPath = LOCATIE_FISERE_HDF;
      String outputPath = LOCATIE_FISERE_HDF + "/..";
      String pathname_output = outputPath;
      File folderInput = new File(inputPath);
      System.out.println("Start folder:" + folderInput);
      HashMap<String, PrintWriter> outputFiles = new HashMap<String, PrintWriter>();
      TreeMap<String, double[]> statii = StatiiMeteoModis06.statii;
      String[] params = new String[] { Mod06C6Constants.P_CIRRUS_REFLECTANCE,
            Mod06C6Constants.P_CLOUD_EFFECTIVE_RADIUS, Mod06C6Constants.P_CLOUD_FRACTION,
            Mod06C6Constants.P_CLOUD_TOP_TEMPERATURE, Mod06C6Constants.P_CLOUD_TOP_PRESSURE,
            Mod06C6Constants.P_CLOUD_OPTICAL_THICKNESS, Mod06C6Constants.P_CLOUD_MULTI_LAYER_FLAG };
      for (String siteName : statii.keySet()) {
         File folderOutput = new File(pathname_output);
         if (!folderOutput.exists()) {
            folderOutput.mkdirs();
         }
         File fileOutput = new File(pathname_output + "/" + siteName + ".txt");
         System.out.println("Output file:" + fileOutput);
         try {
            PrintWriter pw = new PrintWriter(fileOutput);
            pw.println(Mod06C06TO.toLongHeader());
            outputFiles.put(siteName, pw);
            PrintWriter pw1 = new PrintWriter(fileOutput + "x");
            pw1.println(Mod06C06TO.extractHeader(params));
            outputFiles.put(siteName + "x", pw1);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      try {
         File[] deCitit = folderInput
               .listFiles((FileFilter) new WildcardFileFilter("M?D06_L2.A2??????.????.006.*.hdf"));
         for (int i = 0; i < deCitit.length; i++) {
            File file = deCitit[i];
            if (file.isDirectory()) {
               continue;
            }
            final String name = file.getName();
            if (!name.endsWith("hdf")) {
               continue;
            }
            H4FileWorkerC6Mod06 cfile = null;
            try {
               cfile = new H4FileWorkerC6Mod06(file.getCanonicalPath(), statii);
            } catch (Exception e) {
               System.out.println("File open exception:" + file.getCanonicalPath());
               e.printStackTrace();
               continue;
            }
            System.out.print(i + "\t File hdf executed:" + name + " :");
            for (String siteName : statii.keySet()) {
               System.out.print(" " + siteName);
               if (cfile.hasPosition(siteName)) {
                  Mod06C06TO mod06to = cfile.getValori().get(siteName);
                  PrintWriter pw = outputFiles.get(siteName);
                  pw.println(mod06to.toLongLine());
                  PrintWriter pw1 = outputFiles.get(siteName + "x");
                  pw1.println(mod06to.extractParameters(params));
                  System.out.print("+ ");
               } else {
                  System.out.print("- ");
               }
            }
            System.out.println(";");
            cfile.close();
            for (PrintWriter pws : outputFiles.values()) {
               pws.flush();
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println("Exceptie in:" + e);
      } finally {
      }
      System.out.println("End");
   }
}
