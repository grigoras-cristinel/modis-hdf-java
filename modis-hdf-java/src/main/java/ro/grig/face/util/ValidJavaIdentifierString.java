package ro.grig.face.util;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class ValidJavaIdentifierString extends AbstractValidator {

   public ValidJavaIdentifierString(JDialog parent, JComponent c, String message) {
      super(parent, c, message);
   }

   @Override
   protected boolean validationCriteria(JComponent c) {
      String tover = ((JTextField) c).getText();
      return isValidJavaIdentifier(tover);
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
