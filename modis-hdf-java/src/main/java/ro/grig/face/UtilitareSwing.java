package ro.grig.face;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UtilitareSwing {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(UtilitareSwing.class);
   public static final InputVerifier VALIDJAVAIDENTIFIER = new InputVerifier() {

      @Override
      public boolean verify(JComponent input) {
         String tover = ((JTextField) input).getText();
         return isValidJavaIdentifier(tover);
      }
   };

   public static void dialogLocationSaver(final Container dialog, final Sc config) {
      final String windx = dialog.getClass().getSimpleName() + ".location.x";
      final String windy = dialog.getClass().getSimpleName() + ".location.y";
      String xold = config.getConfigValue(windx);
      String yold = config.getConfigValue(windy);
      if (StringUtils.isNumeric(xold) && StringUtils.isNumeric(yold)) {
         Point p = new Point(Integer.parseInt(xold), Integer.parseInt(yold));
         logger.debug("Restore {} location {}", dialog.getClass().getSimpleName(), p);
         dialog.setLocation(p);
      }
      dialog.addComponentListener(new ComponentAdapter() {

         @Override
         public void componentMoved(ComponentEvent e) {
            Point loc = e.getComponent().getLocationOnScreen();
            // logger.debug("Fereasta {} s-a miscat {}",
            // dialog.getClass().getSimpleName(), loc.toString());
            config.putGlobalValue(windx, (int) loc.getX() + "");
            config.putGlobalValue(windy, (int) loc.getY() + "");
         }
      });
   }

   public final static boolean isValidJavaIdentifier(String s) {
      // an empty or null string cannot be a valid identifier
      if (s == null || s.length() == 0) {
         return false;
      }
      char[] c = s.toCharArray();
      if (!Character.isJavaIdentifierStart(c[0])) {
         return false;
      }
      for (int i = 1; i < c.length; i++) {
         if (!Character.isJavaIdentifierPart(c[i])) {
            return false;
         }
      }
      return true;
   }
}
