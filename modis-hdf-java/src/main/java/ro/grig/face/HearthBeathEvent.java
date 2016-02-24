package ro.grig.face;

import java.io.Serializable;

/**
 * PEntru a loga in ecranul principal al aplicatiei
 * 
 * @author grig
 *
 */
public class HearthBeathEvent implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private String text;
   private String procKey;

   public HearthBeathEvent(String text, String prockey) {
      super();
      this.procKey = prockey;
      this.text = text;
   }

   public String getText() {
      return text;
   }

   public HearthBeathEvent() {
      super();
   }

   public void setText(String text) {
      this.text = text;
   }

   public String getProcKey() {
      return procKey;
   }

   public void setProcKey(String procKey) {
      this.procKey = procKey;
   }
}
