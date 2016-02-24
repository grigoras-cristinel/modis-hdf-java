package hdfextractor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ncsa.hdf.hdflib.HDFException;
import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.FileFormat;
import ncsa.hdf.object.HObject;
import ncsa.hdf.object.h4.H4File;
import ncsa.hdf.object.h4.H4Group;
import ncsa.hdf.object.h4.H4SDS;
import ro.grig.sat.util.Util;

/**
 * Extrage date dar le extrage pe suprafata si e limitat la numarul de parametri
 * cautati
 *
 * @author grig
 *
 */
public class H4FileWC6Mod04S implements Serializable {
   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(H4FileWC6Mod04S.class);
   public static final double LNG_VEST = 19.80;
   public static final double LNG_EST = 30.04;
   public static final double LAT_NORD = 48.54;
   public static final double LAT_SUD = 43.22d;
   /**
    *
    */
   private static final long serialVersionUID = -5948605630975489490L;
   private TreeMap<String, H4DSWrapper> sheeturi = new TreeMap<String, H4DSWrapper>();
   public static HashMap<String, Integer> corespondenta = new HashMap<>();
   public static HashMap<String, String> shortname = new HashMap<>();

   static {
      shortname.put("Latitude", "Lat");
      shortname.put("Longitude", "Lng");
      corespondenta.put(Mod04C6Constants.OPTICAL_DEPTH_LAND_AND_OCEAN, 2);
      shortname.put(Mod04C6Constants.OPTICAL_DEPTH_LAND_AND_OCEAN, "aod550Dar");
      corespondenta.put(Mod04C6Constants.DEEP_BLUE_ANGSTROM_EXPONENT_LAND, 3);
      shortname.put(Mod04C6Constants.DEEP_BLUE_ANGSTROM_EXPONENT_LAND, "AngDBLand");
      corespondenta.put(Mod04C6Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_STD, 4);
      shortname.put(Mod04C6Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_STD, "aod550DBS");
      corespondenta.put(Mod04C6Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND, 5);
      shortname.put(Mod04C6Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND, "aod550DBC");
      corespondenta.put(Mod04C6Constants.ANGSTROM_EXPONENT_1_OCEAN, 6);
      shortname.put(Mod04C6Constants.ANGSTROM_EXPONENT_1_OCEAN, "AngDarkO1");
      corespondenta.put(Mod04C6Constants.ANGSTROM_EXPONENT_2_OCEAN, 7);
      shortname.put(Mod04C6Constants.ANGSTROM_EXPONENT_2_OCEAN, "AngDarkO2");
      corespondenta.put(Mod04C6Constants.AEROSOL_TYPE_LAND, 8);
      shortname.put(Mod04C6Constants.AEROSOL_TYPE_LAND, "AerTypLnd");
      corespondenta.put(Mod04C6Constants.DEEP_BLUE_ALGORITHM_FLAG_LAND, 9);
      shortname.put(Mod04C6Constants.DEEP_BLUE_ALGORITHM_FLAG_LAND, "LandTypDB");
      corespondenta.put(Mod04C6Constants.AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED, 10);
      shortname.put(Mod04C6Constants.AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED, "aod550DDC");
      corespondenta.put(Mod04C6Constants.AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_QA_FLAG, 11);
      shortname.put(Mod04C6Constants.AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_QA_FLAG, "aod550DDQ");
      corespondenta.put(Mod04C6Constants.AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_ALGORITHM_FLAG, 12);
      shortname.put(Mod04C6Constants.AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_ALGORITHM_FLAG, "aod550DDA");
   }

   /**
    * Punctele extrase pe suprafata romaniei, este de dimensiune 3
    */
   private HashMap<String, ArrayList<double[]>> valori = new HashMap<>();
   private String satelit;
   private DateTime firstDate;
   private H4File file;

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

   H4FileWC6Mod04S(H4File h4file) throws Exception {
      super();
      this.file = h4file;
      file.open();
   }

   H4FileWC6Mod04S(String h4fileLocation) throws Exception {
      file = new H4File(h4fileLocation, FileFormat.READ);
      file.open();
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
               logger.debug("H4SDS, String - {}", "Fill value este gresit. necunoscut:" + attr.getValue()); //$NON-NLS-1$ //$NON-NLS-2$
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

   public void parseFile() throws Exception, OutOfMemoryError {
      logger.debug("parseFile()");
      TreeMap<String, Integer> dims = Util.dimensionFieldsFromDescription(file);
      H4Group testGroup = (H4Group) file.get("/mod04/Geolocation Fields");
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
      H4Group dataFieldsGrup = (H4Group) file.get("/mod04/Data Fields");
      Iterator<HObject> itDataFields = dataFieldsGrup.getMemberList().iterator();
      while (itDataFields.hasNext()) {
         HObject hObject = itDataFields.next();
         if (hObject instanceof H4SDS) {
            H4SDS h4sds = (H4SDS) hObject;
            for (String field : Mod04C6Constants.fieldNames) {
               if (field.equals(h4sds.getName())) {
                  H4DSWrapper wrap = extractFieldSheet(h4sds, field);
                  sheeturi.put(field, wrap);
                  break;
               }
            }
         }
      }
      float[] lats = (float[]) sheeturi.get("Latitudine").getH4data().getData();
      float[] longs = (float[]) sheeturi.get("Longitudine").getH4data().getData();
      {
         DateTime value = null;
         double[] vals = (double[]) sheeturi.get(Mod04C6Constants.SCAN_START_TIME).getH4data().getData();
         DateTime retval = new DateTime(DateTimeZone.UTC);
         retval = retval.withDate(1993, 1, 1).dayOfYear().roundFloorCopy();
         value = retval.plusSeconds((int) vals[2]);
         firstDate = value;
      }
      for (Entry<String, Integer> ltoget : corespondenta.entrySet()) {
         logger.debug("Este parametrul: " + ltoget.getKey());
         ArrayList<double[]> valoriParam = new ArrayList<double[]>();
         H4DSWrapper wrap = sheeturi.get(ltoget.getKey());
         if (wrap != null) {
            for (int i = 0; i < lats.length; i++) {
               float latit = lats[i];
               float longit = longs[i];
               if (latit > LAT_SUD && latit < LAT_NORD && longit < LNG_EST && longit > LNG_VEST) {
                  Double data = wrap.findDoublePozitionData((long) i);
                  if (data != null) {
                     double[] v3 = new double[3];
                     v3[0] = latit;
                     v3[1] = longit;
                     v3[2] = data;
                     valoriParam.add(v3);
                  }
               }
            }
            if (valoriParam.size() > 10) {
               valori.put(ltoget.getKey(), valoriParam);
            }
         }
      }
   }

   public DateTime getFirstDate() {
      return firstDate;
   }

   public void setFirstDate(DateTime firstDate) {
      this.firstDate = firstDate;
   }

   public String getSatelit() {
      return satelit;
   }

   public void setSatelit(String satelit) {
      this.satelit = satelit;
   }

   public HashMap<String, ArrayList<double[]>> getValori() {
      return valori;
   }

   public void setValori(HashMap<String, ArrayList<double[]>> valori) {
      this.valori = valori;
   }
}