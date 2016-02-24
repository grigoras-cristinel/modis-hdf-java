package hdfextractor;

import ncsa.hdf.object.h4.H4SDS;

public class H4DSWrapper {
   /**
    * Logger for this class
    */
   private String dataName;
   private H4SDS h4data;
   private Double fillValue;
   private Double scale;
   private Double offset;

   public H4DSWrapper(H4SDS h4data) {
      super();
      this.h4data = h4data;
   }

   public H4DSWrapper(String dataName, H4SDS h4data) {
      super();
      this.dataName = dataName;
      this.h4data = h4data;
   }

   public H4DSWrapper(String dataName, H4SDS h4data, Double fillValue) {
      super();
      this.dataName = dataName;
      this.h4data = h4data;
      this.fillValue = fillValue;
   }

   public H4DSWrapper(String dataName, H4SDS h4data, Double fillValue, Double scale, Double offset) {
      super();
      this.dataName = dataName;
      this.h4data = h4data;
      this.fillValue = fillValue;
      this.scale = scale;
      this.offset = offset;
   }

   public Byte findBytePozitionData(Long pozitie) throws OutOfMemoryError, Exception {
      Byte retval = null;
      byte[] valsh = (byte[]) getH4data().getData();
      short gasita = valsh[pozitie.intValue()];
      if (gasita != getFillValue()) {
         double ret = (gasita + Math.abs(offset)) * scale;
         retval = new Double(ret).byteValue();
      }
      return retval;
   }

   public Double findDoublePozitionData(int index[]) throws OutOfMemoryError, Exception {
      Double retval = null;
      H4SDS dataset = getH4data();
      int rank = dataset.getRank(); // number of dimension of the dataset
      long[] selected = dataset.getSelectedDims(); // the selected size of
      // the dataset
      long[] start = dataset.getStartDims(); // the off set of the
      // selection
      long[] stride = dataset.getStride(); // the stride of the dataset
      int[] selectedIndex = dataset.getSelectedIndex(); // the selected
      // dimensions
      // for display
      for (int i = 0; i < rank; i++) {
         selectedIndex[i] = i;
         selected[i] = 1;
         stride[i] = 1;
         start[i] = index[i];
      }
      long[] startDims = dataset.getStartDims();
      Object gasit = dataset.read();
      if (gasit == null) {
         return null;
      } else if (gasit instanceof short[]) {
         short gasita = ((short[]) gasit)[0];
         if (gasita != getFillValue()) {
            double ret = (gasita + Math.abs(offset)) * scale;
            retval = new Double(ret);
         }
      } else if (dataset.getData() instanceof byte[]) {
         byte[] valsh = (byte[]) gasit;
         short gasita = valsh[0];
         if (gasita != getFillValue()) {
            double ret = (gasita + Math.abs(offset)) * scale;
            retval = new Double(ret);
         }
      } else if (gasit instanceof double[]) {
         double[] valsh = (double[]) gasit;
         double gasita = valsh[0];
         if (gasita != getFillValue()) {
            double ret = (gasita + Math.abs(offset)) * scale;
            retval = new Double(ret);
         }
      } else if (gasit instanceof float[]) {
         float[] valsh = (float[]) dataset.getData();
         float gasita = valsh[0];
         if (gasita != getFillValue()) {
            double ret = (gasita + Math.abs(offset)) * scale;
            retval = new Double(ret);
         }
      } else {
         System.out.println("Tip data neimplementat:" + gasit);
      }
      return retval;
   }

   public Long findShortPozitionData(Long pozitie) throws OutOfMemoryError, Exception {
      Long retval = null;
      short[] valsh = (short[]) getH4data().getData();
      short gasita = valsh[pozitie.intValue()];
      System.out.println("Ce am gasit:" + gasita + offset + ":" + scale + "cc");
      if (gasita != getFillValue()) {
         double ret = (gasita + Math.abs(offset)) * scale;
         retval = new Double(ret).longValue();
      }
      return retval;
   }

   public String getDataName() {
      return dataName;
   }

   public Double getFillValue() {
      return fillValue;
   }

   public H4SDS getH4data() {
      return h4data;
   }

   public Double getOffset() {
      return offset;
   }

   public Double getScale() {
      return scale;
   }

   public void setDataName(String dataName) {
      this.dataName = dataName;
   }

   public void setFillValue(Double fillValue) {
      this.fillValue = fillValue;
   }

   public void setH4data(H4SDS h4data) {
      this.h4data = h4data;
   }

   public void setOffset(Double offset) {
      this.offset = offset;
   }

   public void setScale(Double scale) {
      this.scale = scale;
   }

   public Double findDoublePozitionData(Long pozitie) throws OutOfMemoryError, Exception {
      Double retval = null;
      H4SDS dataset = getH4data();
      if (dataset.getData() instanceof short[]) {
         short[] valsh = (short[]) dataset.getData();
         try {
            short gasita = valsh[pozitie.intValue()];
            if (gasita != getFillValue()) {
               double ret = (gasita + Math.abs(offset)) * scale;
               ret = Math.round(ret * 100000) / 100000d;
               retval = new Double(ret);
            }
         } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
         }
      } else if (dataset.getData() instanceof byte[]) {
         byte[] valsh = (byte[]) dataset.getData();
         short gasita = valsh[pozitie.intValue()];
         if (gasita != getFillValue()) {
            double ret = (gasita + Math.abs(offset)) * scale;
            ret = Math.round(ret * 100000) / 100000d;
            retval = new Double(ret);
         }
      } else if (dataset.getData() instanceof double[]) {
         double[] valsh = (double[]) dataset.getData();
         double gasita = valsh[pozitie.intValue()];
         if (gasita != getFillValue()) {
            double ret = (gasita + Math.abs(offset)) * scale;
            ret = Math.round(ret * 100000) / 100000d;
            retval = new Double(ret);
         }
      } else if (dataset.getData() instanceof float[]) {
         float[] valsh = (float[]) dataset.getData();
         float gasita = valsh[pozitie.intValue()];
         if (gasita != getFillValue()) {
            double ret = (gasita + Math.abs(offset)) * scale;
            ret = Math.round(ret * 10000) / 10000d;
            retval = new Double(ret);
         }
      } else {
         System.out.println("Tip data neimplementat:" + dataset.getData());
      }
      return retval;
   }
}
