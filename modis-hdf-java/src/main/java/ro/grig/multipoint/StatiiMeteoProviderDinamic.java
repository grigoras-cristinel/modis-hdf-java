package ro.grig.multipoint;

import java.util.ArrayList;
import java.util.List;

import ro.grig.sat.util.StatieMeteoTO;

public class StatiiMeteoProviderDinamic {

   private static List<StatieMeteoTO> retval;

   public static List<StatieMeteoTO> statiiSelectate() {
      if (retval != null) {
         return retval;
      }
      retval = new ArrayList<StatieMeteoTO>();
      double x = 44.255051;
      int p = 442;
      while (x < 47.499) {
         p++;
         x += 0.2;
         double y = 23.032759;
         int r = 230;
         while (y < 27.743) {
            y += 0.2;
            r++;
            retval.add(new StatieMeteoTO("P_" + p + "_" + r, x, y));
         }
      }
      return retval;
   }
}
