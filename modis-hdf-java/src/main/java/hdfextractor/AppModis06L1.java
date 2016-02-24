package hdfextractor;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Asta e pentru ozon
 * @author Administrator
 *
 */

public class AppModis06L1 {
   /**
    * @param args
    */
   public static void main(String[] args) {
      /*
       * Read files ;Filter files ;Stocheaza structura; Statistica ;Calculeaza
       * albedo; Calculeaza SW; Calculeaza long wave
       */
      String anii[] = new String[] { "2008", "2009", "2010", "2011" };
      String sateliti[] = new String[] { "mod", "myd" };
      HashMap<String, HashSet<String>> historyProcesare = new HashMap<String, HashSet<String>>();
      for (int j = 0; j < anii.length; j++) {
         String anul = anii[j];
         for (int k = 0; k < sateliti.length; k++) {
            String satelit = sateliti[k];
            String inputPath = "d:/proiecte/2011-satelit-ozon/S06-extras/"
                  + satelit + "06_" + anul + "/";
            String outputPath = "d:/proiecte/2011-satelit-ozon/S06-final/"
                  + satelit + "06_" + anul + "/";
            File folderInput = new File(inputPath);
            HashMap<String, PrintWriter> outputFiles = new HashMap<String, PrintWriter>();
            TreeMap<String, double[]> statii = StatiiMeteoModis06.statii;
            for (String siteName : statii.keySet()) {
               File folderOutput = new File(outputPath);
               if (!folderOutput.exists()) {
                  folderOutput.mkdirs();
               }
               File fileOutput = new File(outputPath + satelit + "06_" + anul
                     + "_" + siteName + ".txt");
               System.out.println("Output file:" + fileOutput);
               try {
                  PrintWriter pw = new PrintWriter(fileOutput);
                  pw.println(Mod06TO.longHeader());
                  outputFiles.put(siteName, pw);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         }
      }
   }
}
