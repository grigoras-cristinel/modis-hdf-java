package hdfextractor;

import java.io.File;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.joda.time.DateTime;

import hdfextractor.ozon.CalculeazaOzonLaSol;

/**
 * A prelucrat date de ozon pe nivele
 *
 */
public class App {
   /**
    * @param args
    */
   public static void main(String[] args) {
      File folder = new File("/proiecte/2011-satelit-ozon/Modis07/");
      File output = new File("target/output.txt");
      System.out.println("Start folder:" + folder);
      System.out.println("Output file:" + output);
      try {
         NumberFormat nf = new DecimalFormat("###0.000000");
         NumberFormat ne = new DecimalFormat("0.#####E000");
         PrintWriter pw = new PrintWriter(output);
         pw.print("// Data           \t");
         pw.print("     ozon   ");
         pw.print("    oz 0m ");
         pw.print("    oz 15 m ");
         pw.print("    oz 20 m ");
         pw.print("  diferenta ");
         pw.print("    ug/m3   ");
         pw.print("  diferenta ");
         pw.print("    ug/m3   ");
         pw.print("     u'     ");
         pw.print("     T(K)   ");
         pw.print("    P(hPa)  ");
         pw.println();
         File[] deCitit = folder.listFiles();
         CalculeazaOzonLaSol calc1 = new CalculeazaOzonLaSol();
         for (int i = 0; i < deCitit.length; i++) {
            File file = deCitit[i];
            if (file.isDirectory()) {
               continue;
            }
            if (!file.getName().endsWith("hdf")) {
               continue;
            }
            H4FileWorker cfile = null;
            try {
               cfile = new H4FileWorker(file.getCanonicalPath(), StatiiMeteo2.statii);
            } catch (Exception e) {
               System.out.println("File open exception:" + file.getCanonicalPath());
               continue;
            }
            String siteName = "B1";
            double mukg = 1.660538e-27;
            double inaltimeCalcul = 0;
            if (cfile.hasPosition(siteName)) {
               DateTime timp = cfile.findTimp(siteName);
               Double ozonDobson = cfile.findOzon(siteName);
               int luna = timp.getMonthOfYear() - 1;
               if (ozonDobson != null) {
                  Double ozlasol = ozonDobson;
                  Double ozlasol2 = calc1.calculeazaU(luna, ozonDobson, 15d);
                  Double ozlasol3 = calc1.calculeazaU(luna, ozonDobson, 20d);
                  double dif2 = ozonDobson - ozlasol2;
                  double dif3 = ozonDobson - ozlasol3;
                  double v2_ugm3 = dif2 * 2.69e16 * mukg * 48 * 1e9;
                  double v3_ugm3 = dif3 * 2.69e16 * mukg * 48 * 1e9;
                  pw.print(timp.toString("yyyy-MM-dd' 'HH:mm") + "\t");
                  pw.print(nf.format(ozonDobson) + "\t");
                  pw.print(nf.format(ozlasol) + "\t");
                  pw.print(nf.format(ozlasol2) + "\t");
                  pw.print(nf.format(ozlasol3) + "\t");
                  pw.print(nf.format(dif2) + "\t");
                  pw.print(nf.format(v2_ugm3) + "\t");
                  pw.print(nf.format(dif3) + "\t");
                  pw.print(nf.format(v3_ugm3) + "\t");
                  pw.print(nf.format(calc1.calculeazaUprim(luna, ozonDobson, inaltimeCalcul)) + "\t");
                  pw.print(nf.format(cfile.findTemperaturaSuprafata(siteName)) + "\t");
                  pw.print(nf.format(cfile.findPresiuneSuprafata(siteName)) + "\t");
                  pw.println();
               }
            }
            cfile.close();
            pw.flush();
            // infoPrintH4SDS(h4sds);
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println("Exceptie in:" + e);
      } finally {
      }
      System.out.println("End");
   }
}
