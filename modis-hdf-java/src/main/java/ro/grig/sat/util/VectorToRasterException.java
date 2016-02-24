package ro.grig.sat.util;

import org.geotools.process.ProcessException;

public class VectorToRasterException extends ProcessException {
   public VectorToRasterException(String message) {
      super(message);
   }

   public VectorToRasterException(Exception ex) {
      super(ex);
   }
}
