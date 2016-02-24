package ro.grig.face;

import java.io.Serializable;

/**
 * PEntru a loga in ecranul principal al aplicatiei
 * 
 * @author grig
 *
 */
public class LogtextEvent implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private String text;

   public LogtextEvent(String text) {
      super();
      this.text = text;
   }

   public String getText() {
      return text;
   }

   public LogtextEvent() {
      super();
   }

   public void setText(String text) {
      this.text = text;
   }
}
