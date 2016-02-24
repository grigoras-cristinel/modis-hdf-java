package hdfextractor;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import ncsa.hdf.hdflib.HDFException;
import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.FileFormat;
import ncsa.hdf.object.HObject;
import ncsa.hdf.object.h4.H4File;
import ncsa.hdf.object.h4.H4Group;
import ncsa.hdf.object.h4.H4SDS;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import ro.grig.sat.util.Util;

/**
 * Extrage suprafete de variabile
 *
 * @author grig
 *
 */
public class H4FileWC6Mod06S implements Serializable {
   /**
    *
    */
   private static final long serialVersionUID = 5320908785707009038L;
   public static final double LNG_VEST = 19.80;
   public static final double LNG_EST = 30.04;
   public static final double LAT_NORD = 48.54;
   public static final double LAT_SUD = 43.22d;
   private static PrintStream debug = System.out;
   private TreeMap<String, H4DSWrapper> sheeturi = new TreeMap<String, H4DSWrapper>();
   private ArrayList<double[]> valoriCOT;
   private ArrayList<double[]> valoriCER;
   private String satelit;
   private DateTime firstDate;
   private H4File file;
   private ArrayList<double[]> valoriWP;
   private ArrayList<double[]> valoriTopP;
   private ArrayList<double[]> valoriTopT;

   public TreeMap<String, H4DSWrapper> getSheeturi() {
      return sheeturi;
   }

   public void setSheeturi(TreeMap<String, H4DSWrapper> sheeturi) {
      this.sheeturi = sheeturi;
   }

   public H4File getFile() {
      return file;
   }

   public void setFile(H4File file) {
      this.file = file;
   }

   H4FileWC6Mod06S(H4File h4file) throws Exception {
      super();
      this.file = h4file;
      file.open();
   }

   H4FileWC6Mod06S(String h4fileLocation) throws Exception {
      file = new H4File(h4fileLocation, FileFormat.READ);
      file.open();
   }

   public double[] lats() {
      double[] retval = new double[valoriCOT.size()];
      for (int i = 0; i < retval.length; i++) {
         retval[i] = valoriCOT.get(i)[0];
      }
      return retval;
   }

   public double[] lng() {
      double[] retval = new double[valoriCOT.size()];
      for (int i = 0; i < retval.length; i++) {
         retval[i] = valoriCOT.get(i)[1];
      }
      return retval;
   }

   public void close() {
      try {
         file.close();
      } catch (Exception e) {
      }
   }

   static H4DSWrapper extractFieldSheet(H4SDS h4sds, String field1) throws Exception, HDFException {
      H4DSWrapper wrap = new H4DSWrapper(field1, h4sds);
      for (int f1 = 0; f1 < h4sds.getMetadata().size(); f1++) {
         Attribute attr = (Attribute) h4sds.getMetadata().get(f1);
         if (attr.getName().equals("_FillValue")) {
            if (attr.getValue() instanceof double[]) {
               double[] vals = (double[]) attr.getValue();
               wrap.setFillValue(new Double(vals[0]));
            } else if (attr.getValue() instanceof short[]) {
               short[] vals = (short[]) attr.getValue();
               wrap.setFillValue(new Double(vals[0]));
            } else if (attr.getValue() instanceof float[]) {
               float[] vals = (float[]) attr.getValue();
               wrap.setFillValue(new Double(vals[0]));
            } else if (attr.getValue() instanceof byte[]) {
               byte[] vals = (byte[]) attr.getValue();
               wrap.setFillValue(new Double(vals[0]));
            } else {
               debug.println("Fill value este gresit. necunoscut:" + attr.getValue());
            }
         }
         if (attr.getName().equals("scale_factor")) {
            double[] vals = (double[]) attr.getValue();
            wrap.setScale(vals[0]);
         }
         if (attr.getName().equals("add_offset")) {
            double[] vals = (double[]) attr.getValue();
            wrap.setOffset(vals[0]);
         }
      }
      return wrap;
   }

   public void parseFile(PrintStream debug) throws Exception, OutOfMemoryError {
      this.debug = debug;
      debug.println("parseFile()");
      TreeMap<String, Integer> dims = Util.dimensionFieldsFromDescription(file);
      int cellAcross = dims.get("Cell_Across_Swath_5km");
      int cellAllong = dims.get("Cell_Along_Swath_5km");
      int cellAcross1km = dims.get("Cell_Across_Swath_1km");
      int cellAlong1km = dims.get("Cell_Along_Swath_1km");
      H4Group testGroup = (H4Group) file.get("/mod06/Geolocation Fields");
      Iterator<HObject> it = testGroup.getMemberList().iterator();
      while (it.hasNext()) {
         HObject hObject = it.next();
         if (hObject instanceof H4SDS) {
            H4SDS h4sds = (H4SDS) hObject;
            if ("Latitude".equals(h4sds.getName())) {
               sheeturi.put("Latitudine", new H4DSWrapper("Latitudine", h4sds));
            }
            if ("Longitude".equals(h4sds.getName())) {
               sheeturi.put("Longitudine", new H4DSWrapper("Longitudine", h4sds));
            }
         }
      }
      H4Group dataFieldsGrup = (H4Group) file.get("/mod06/Data Fields");
      Iterator<HObject> itDataFields = dataFieldsGrup.getMemberList().iterator();
      while (itDataFields.hasNext()) {
         HObject hObject = itDataFields.next();
         if (hObject instanceof H4SDS) {
            H4SDS h4sds = (H4SDS) hObject;
            for (String field : Mod06C6Constants.fieldNames) {
               if (field.equals(h4sds.getName())) {
                  H4DSWrapper wrap = extractFieldSheet(h4sds, field);
                  sheeturi.put(field, wrap);
                  break;
               }
            }
         }
      }
      ArrayList<Mod06C06TO> pozitiiGasite = new ArrayList<Mod06C06TO>();
      float[] lats = (float[]) sheeturi.get("Latitudine").getH4data().getData();
      float[] longs = (float[]) sheeturi.get("Longitudine").getH4data().getData();
      float[][] blats1km = Util.buildLats1km(lats, longs, cellAcross, cellAcross1km, cellAlong1km);
      {
         DateTime value = null;
         double[] vals = (double[]) sheeturi.get(Mod06C6Constants.SCAN_START_TIME).getH4data().getData();
         DateTime retval = new DateTime(DateTimeZone.UTC);
         retval = retval.withDate(1993, 1, 1).dayOfYear().roundFloorCopy();
         value = retval.plusSeconds((int) vals[2]);
         firstDate = value;
      }
      {
         valoriCOT = new ArrayList<double[]>();
         H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS);
         if (wrap != null) {
            for (int i = 0; i < blats1km[0].length; i++) {
               float latit = blats1km[0][i];
               float longit = blats1km[1][i];
               if (latit > LAT_SUD && latit < LAT_NORD && longit < LNG_EST && longit > LNG_VEST) {
                  Double data = wrap.findDoublePozitionData((long) i);
                  if (data != null) {
                     double[] v3 = new double[3];
                     v3[0] = latit;
                     v3[1] = longit;
                     v3[2] = data;
                     valoriCOT.add(v3);
                  }
               }
            }
         }
      }
      {
         valoriCER = new ArrayList<double[]>();
         H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS);
         if (wrap != null) {
            for (int i = 0; i < blats1km[0].length; i++) {
               float latit = blats1km[0][i];
               float longit = blats1km[1][i];
               if (latit > LAT_SUD && latit < LAT_NORD && longit < LNG_EST && longit > LNG_VEST) {
                  Double data = wrap.findDoublePozitionData((long) i);
                  if (data != null) {
                     double[] v3 = new double[3];
                     v3[0] = latit;
                     v3[1] = longit;
                     v3[2] = data;
                     valoriCER.add(v3);
                  }
               }
            }
         }
      }
      {
         valoriWP = new ArrayList<double[]>();
         H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH);
         if (wrap != null) {
            for (int i = 0; i < blats1km[0].length; i++) {
               float latit = blats1km[0][i];
               float longit = blats1km[1][i];
               if (latit > LAT_SUD && latit < LAT_NORD && longit < LNG_EST && longit > LNG_VEST) {
                  Double data = wrap.findDoublePozitionData((long) i);
                  if (data != null) {
                     double[] v3 = new double[3];
                     v3[0] = latit;
                     v3[1] = longit;
                     v3[2] = data;
                     valoriWP.add(v3);
                  }
               }
            }
         }
      }
      {
         valoriTopP = new ArrayList<double[]>();
         H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_PRESSURE_1KM);
         if (wrap != null) {
            for (int i = 0; i < blats1km[0].length; i++) {
               float latit = blats1km[0][i];
               float longit = blats1km[1][i];
               if (latit > LAT_SUD && latit < LAT_NORD && longit < LNG_EST && longit > LNG_VEST) {
                  Double data = wrap.findDoublePozitionData((long) i);
                  if (data != null) {
                     double[] v3 = new double[3];
                     v3[0] = latit;
                     v3[1] = longit;
                     v3[2] = data;
                     valoriTopP.add(v3);
                  }
               }
            }
         }
      }
      {
         valoriTopT = new ArrayList<double[]>();
         H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_TEMPERATURE_1KM);
         if (wrap != null) {
            for (int i = 0; i < blats1km[0].length; i++) {
               float latit = blats1km[0][i];
               float longit = blats1km[1][i];
               if (latit > LAT_SUD && latit < LAT_NORD && longit < LNG_EST && longit > LNG_VEST) {
                  Double data = wrap.findDoublePozitionData((long) i);
                  if (data != null) {
                     double[] v3 = new double[3];
                     v3[0] = latit;
                     v3[1] = longit;
                     v3[2] = data;
                     valoriTopT.add(v3);
                  }
               }
            }
         }
      }
   }

   public String getSatelit() {
      return satelit;
   }

   public void setSatelit(String satelit) {
      this.satelit = satelit;
   }

   public DateTime getFirstDate() {
      return firstDate;
   }

   public void setFirstDate(DateTime firstDate) {
      this.firstDate = firstDate;
   }

   public ArrayList<double[]> getValoriCOT() {
      return valoriCOT;
   }

   public void setValoriCOT(ArrayList<double[]> valoriCOT) {
      this.valoriCOT = valoriCOT;
   }

   public ArrayList<double[]> getValoriCER() {
      return valoriCER;
   }

   public void setValoriCER(ArrayList<double[]> valoriCER) {
      this.valoriCER = valoriCER;
   }

   public ArrayList<double[]> getValoriWP() {
      return valoriWP;
   }

   public void setValoriWP(ArrayList<double[]> valoriWP) {
      this.valoriWP = valoriWP;
   }

   public ArrayList<double[]> getValoriTopP() {
      return valoriTopP;
   }

   public void setValoriTopP(ArrayList<double[]> valoriTopP) {
      this.valoriTopP = valoriTopP;
   }

   public ArrayList<double[]> getValoriTopT() {
      return valoriTopT;
   }

   public void setValoriTopT(ArrayList<double[]> valoriTopT) {
      this.valoriTopT = valoriTopT;
   }
}