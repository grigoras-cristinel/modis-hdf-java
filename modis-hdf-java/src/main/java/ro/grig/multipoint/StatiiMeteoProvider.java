package ro.grig.multipoint;

import java.util.ArrayList;
import java.util.List;

import ro.grig.sat.util.StatieMeteoTO;

public class StatiiMeteoProvider {

   public static List<StatieMeteoTO> statiiSelectate() {
      List<StatieMeteoTO> retval = new ArrayList<StatieMeteoTO>();
      retval.add(new StatieMeteoTO("anm_front", 44.512585, 26.080127));
      retval.add(new StatieMeteoTO("w_olt_1", 44.953502, 24.268847));
      retval.add(new StatieMeteoTO("w_olt_2", 45.132044, 24.392443));
      retval.add(new StatieMeteoTO("p_pitesti_1", 44.856369, 24.904889));
      retval.add(new StatieMeteoTO("p_pitesti_2", 44.820706, 24.977268));
      retval.add(new StatieMeteoTO("p_pitesti_3", 44.855226, 24.915086));
      retval.add(new StatieMeteoTO("p_pitesti_4", 44.663664, 25.073262));
      return retval;
   }
}
