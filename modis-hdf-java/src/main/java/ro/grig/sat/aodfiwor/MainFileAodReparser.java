/**
 *
 */
package ro.grig.sat.aodfiwor;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import hdfextractor.Mod04C06TO;
import hdfextractor.StatiiMeteoModis06;

/**
 * Aceasta aplicatie va reciti
 *
 * @author Grigoras Cristinel
 *
 */
public class MainFileAodReparser {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(MainFileAodReparser.class);
   private static String path = "t:\\data\\AOD-MOD04-statii\\exp-statii\\AppModis04C6";

   /**
    * @param args
    */
   public static void main(String[] args) {
      new MainFileAodReparser().run(args);
   }

   class V2filter implements DirectoryStream.Filter<Path> {

      private String prefix;

      public V2filter(String prefix) {
         super();
         this.prefix = prefix;
      }

      @Override
      public boolean accept(Path entry) throws IOException {
         String name = entry.getFileName().toFile().getName();
         if (name.startsWith(prefix) && name.endsWith("_MOD04.txt")) {
            return true;
         }
         if (name.startsWith(prefix) && name.endsWith("_MYD04.txt")) {
            return true;
         }
         return false;
      }
   }

   protected void run(String[] args) {
      logger.debug("run(String[]) - start"); //$NON-NLS-1$
      TreeMap<String, double[]> statii = StatiiMeteoModis06.statii;
      for (String s1 : statii.keySet()) {
         File out = Paths.get(path, s1 + "-f1.txt").toFile();
         try {
            DirectoryStream<Path> files = Files.newDirectoryStream(Paths.get(path), new V2filter(s1));
            CsvListWriter wrt = new CsvListWriter(new FileWriter(out), CsvPreference.EXCEL_PREFERENCE);
            String[] split = Mod04C06TO.toLongHeader().split("\\t");
            logger.debug("Coloana 27 este : {}", split[27]);
            wrt.writeHeader(split);
            boolean first = false;
            for (Path path : files) {
               System.out.println(path.toString());
               CsvListReader red = new CsvListReader(new FileReader(path.toFile()), CsvPreference.TAB_PREFERENCE);
               red.getHeader(true);
               List<String> citit;
               while ((citit = red.read()) != null) {
                  for (int i = 0; i < citit.size(); i++) {
                     String sv = citit.get(i);
                     if ("null".equals(sv)) {
                        citit.set(i, "");
                     }
                  }
                  if (StringUtils.isNotEmpty(citit.get(27))) {
                     wrt.write(citit);
                  }
               }
               red.close();
            }
            wrt.flush();
            wrt.close();
         } catch (IOException e) {
            logger.error("run(String[])", e); //$NON-NLS-1$
            e.printStackTrace();
         }
      }
      logger.debug("run(String[]) - end"); //$NON-NLS-1$
   }
}
