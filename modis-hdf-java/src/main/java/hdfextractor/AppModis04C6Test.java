package hdfextractor;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Aplicatie care extrage datele de spre nori din HDF in coordonatele de statii
 * date si le pune in folderul out pentru fiecare statie.
 *
 * @author Grigoras Cristinel
 *
 *
 */
public class AppModis04C6Test {
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
      String[] params = new String[] { Mod04C6Constants.AEROSOL_TYPE_LAND,
            Mod04C6Constants.AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED, Mod04C6Constants.ANGSTROM_EXPONENT_1_OCEAN,
            Mod04C6Constants.ANGSTROM_EXPONENT_2_OCEAN, Mod04C6Constants.EFFECTIVE_RADIUS_OCEAN,
            Mod04C6Constants.LAND_SEA_FLAG, Mod04C6Constants.OPTICAL_DEPTH_LAND_AND_OCEAN };
      for (String siteName : statii.keySet()) {
         File folderOutput = new File(pathname_output);
         if (!folderOutput.exists()) {
            folderOutput.mkdirs();
         }
         File fileOutput = new File(pathname_output + "/" + siteName + ".txt");
         System.out.println("Output file:" + fileOutput);
         try {
            PrintWriter pw = new PrintWriter(fileOutput);
            pw.println(Mod04C06TO.toLongHeader());
            outputFiles.put(siteName, pw);
            PrintWriter pw1 = new PrintWriter(fileOutput + "x");
            pw1.println(Mod04C06TO.extractHeader(params));
            outputFiles.put(siteName + "h", pw1);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      try {
         File[] deCitit = folderInput.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
               if ("MYD04_L2.A2003327.1045.006.2014010212202.hdf".equals(name)) {
                  return true;
               }
               return false;
            }
         });
         for (int i = 0; i < deCitit.length; i++) {
            File file = deCitit[i];
            if (file.isDirectory()) {
               continue;
            }
            final String name = file.getName();
            if (!name.endsWith("hdf")) {
               continue;
            }
            H4FileWorkerC6Mod04 cfile = null;
            try {
               cfile = new H4FileWorkerC6Mod04(file.getCanonicalPath(), statii);
            } catch (Exception e) {
               System.out.println("File open exception:" + file.getCanonicalPath());
               e.printStackTrace();
               continue;
            }
            System.out.print(i + "\tFile hdf executed:" + name + " :");
            boolean ok = false;
            for (String siteName : statii.keySet()) {
               System.out.print(" " + siteName);
               if (cfile.hasPosition(siteName)) {
                  Mod04C06TO mod06to = cfile.getValori().get(siteName);
                  PrintWriter pw = outputFiles.get(siteName);
                  pw.println(mod06to.toLongLine());
                  PrintWriter pw1 = outputFiles.get(siteName + "h");
                  pw1.println(mod06to.extractParameters(params));
                  System.out.print("+ ");
                  ok = true;
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
