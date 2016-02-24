package hdfextractor;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.tree.DefaultMutableTreeNode;

import ncsa.hdf.hdflib.HDFException;
import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.FileFormat;
import ncsa.hdf.object.HObject;
import ncsa.hdf.object.h4.H4File;
import ncsa.hdf.object.h4.H4Group;
import ncsa.hdf.object.h4.H4SDS;
import ncsa.hdf.object.h4.H4Vdata;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Parseaza un fiser HDF pentru modis06
 * 
 * @author Grigoras Cristinel
 * 
 */
public class H4FileWorkerMod06 implements Serializable {

   private static final String QUALITY_ASSURANCE_1KM = "Quality_Assurance_1km";
   private static final String CLOUD_MASK_1KM = "Cloud_Mask_1km";
   private static final String QUALITY_ASSURANCE_5KM = "Quality_Assurance_5km";
   private static final String CLOUD_MASK_5KM = "Cloud_Mask_5km";
   private static final String CIRRUS_REFLECTANCE = "Cirrus_Reflectance";
   private static final String CIRRUS_REFLECTANCE_FLAG = "Cirrus_Reflectance_Flag";
   private static final String CLOUD_MULTI_LAYER_FLAG = "Cloud_Multi_Layer_Flag";
   private static final String CLOUD_PHASE_OPTICAL_PROPERTIES = "Cloud_Phase_Optical_Properties";
   private static final String CLOUD_WATER_PATH_1621 = "Cloud_Water_Path_1621";
   private static final String CLOUD_WATER_PATH_UNCERTAINTY_1621 = "Cloud_Water_Path_Uncertainty_1621";
   private static final String CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_1621 = "Cloud_Optical_Thickness_Uncertainty_1621";
   private static final String CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_1621 = "Cloud_Effective_Radius_Uncertainty_1621";
   private static final String CLOUD_WATER_PATH_UNCERTAINTY = "Cloud_Water_Path_Uncertainty";
   private static final String CLOUD_OPTICAL_THICKNESS_UNCERTAINTY = "Cloud_Optical_Thickness_Uncertainty";
   private static final String CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY = "Cloud_Effective_Radius_Uncertainty";
   private static final String CLOUD_WATER_PATH = "Cloud_Water_Path";
   private static final String EFFECTIVE_RADIUS_DIFFERENCE = "Effective_Radius_Difference";
   private static final String CLOUD_OPTICAL_THICKNESS_1621 = "Cloud_Optical_Thickness_1621";
   private static final String CLOUD_EFFECTIVE_RADIUS_1621 = "Cloud_Effective_Radius_1621";
   private static final String CLOUD_OPTICAL_THICKNESS = "Cloud_Optical_Thickness";
   private static final String CLOUD_EFFECTIVE_RADIUS = "Cloud_Effective_Radius";
   private static final String CLOUD_PHASE_INFRARED = "Cloud_Phase_Infrared";
   private static final String RADIANCE_VARIANCE = "Radiance_Variance";
   private static final String SURFACE_TYPE = "Surface_Type";
   private static final String CLOUD_TOP_PRESSURE_FROM_RATIOS = "Cloud_Top_Pressure_From_Ratios";
   private static final String SPECTRAL_CLOUD_FORCING = "Spectral_Cloud_Forcing";
   private static final String CLOUD_TOP_PRESSURE_INFRARED = "Cloud_Top_Pressure_Infrared";
   private static final String CLOUD_EFFECTIVE_EMISSIVITY = "Cloud_Effective_Emissivity";
   private static final String CLOUD_FRACTION = "Cloud_Fraction";
   private static final String TROPOPAUSE_HEIGHT = "Tropopause_Height";
   private static final String CLOUD_HEIGHT_METHOD = "Cloud_Height_Method";
   private static final String CLOUD_TOP_TEMPERATURE = "Cloud_Top_Temperature";
   private static final String CLOUD_TOP_PRESSURE = "Cloud_Top_Pressure";
   private static final String PROCESSING_FLAG = "Processing_Flag";
   private static final String BRIGHTNESS_TEMPERATURE = "Brightness_Temperature";
   private static final String SOLAR_ZENITH = "Solar_Zenith";
   private static final String SOLAR_AZIMUTH = "Solar_Azimuth";
   private static final String SENSOR_ZENITH = "Sensor_Zenith";
   private static final String SENSOR_AZIMUTH = "Sensor_Azimuth";
   private static final String SURFACE_PRESSURE = "Surface_Pressure";
   private static final String SURFACE_TEMPERATURE = "Surface_Temperature";
   private static final String SCAN_START_TIME = "Scan_Start_Time";
   /**
	 *
	 */
   private static final long serialVersionUID = 1L;
   private static final String[] fieldNames = new String[] { SCAN_START_TIME, SOLAR_ZENITH, SOLAR_AZIMUTH,
         SENSOR_ZENITH, SENSOR_AZIMUTH, BRIGHTNESS_TEMPERATURE, SURFACE_TEMPERATURE, SURFACE_PRESSURE, PROCESSING_FLAG,
         CLOUD_HEIGHT_METHOD, CLOUD_TOP_PRESSURE, CLOUD_TOP_TEMPERATURE, TROPOPAUSE_HEIGHT, CLOUD_FRACTION,
         CLOUD_EFFECTIVE_EMISSIVITY, CLOUD_TOP_PRESSURE_INFRARED, SPECTRAL_CLOUD_FORCING,
         CLOUD_TOP_PRESSURE_FROM_RATIOS, SURFACE_TYPE, RADIANCE_VARIANCE, CLOUD_PHASE_INFRARED, CLOUD_EFFECTIVE_RADIUS,
         CLOUD_OPTICAL_THICKNESS, CLOUD_EFFECTIVE_RADIUS_1621, CLOUD_OPTICAL_THICKNESS_1621,
         EFFECTIVE_RADIUS_DIFFERENCE, CLOUD_WATER_PATH, CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY,
         CLOUD_OPTICAL_THICKNESS_UNCERTAINTY, CLOUD_WATER_PATH_UNCERTAINTY, CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_1621,
         CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_1621, CLOUD_WATER_PATH_1621, CLOUD_WATER_PATH_UNCERTAINTY_1621,
         CLOUD_PHASE_OPTICAL_PROPERTIES, CLOUD_MULTI_LAYER_FLAG, CIRRUS_REFLECTANCE, CIRRUS_REFLECTANCE_FLAG,
         CLOUD_MASK_5KM, QUALITY_ASSURANCE_5KM, CLOUD_MASK_1KM, QUALITY_ASSURANCE_1KM };
   private final static int cellAcross = 270;
   @SuppressWarnings("unused")
   private final static int cellAlong = 406;
   H4File file;
   TreeMap<String, double[]> puncte;
   HashMap<String, H4DSWrapper> sheeturi = new HashMap<String, H4DSWrapper>();
   TreeMap<String, Mod06TO> valori = new TreeMap<String, Mod06TO>();
   short presiuneSuprafataFillValue;
   double presiuneSuprafataScaleFactor;

   public TreeMap<String, double[]> getPuncte() {
      return puncte;
   }

   public void setPuncte(TreeMap<String, double[]> pozitii) {
      this.puncte = pozitii;
   }

   public H4FileWorkerMod06(H4File h4file) throws Exception {
      super();
      this.file = h4file;
      file.open();
   }

   public H4FileWorkerMod06(String h4fileLocation) throws Exception {
      file = new H4File(h4fileLocation, FileFormat.READ);
      file.open();
   }

   public H4FileWorkerMod06(String canonicalPath, TreeMap<String, double[]> statii) throws Exception {
      this(canonicalPath);
      puncte = statii;
      parseFile();
   }

   public boolean hasPosition(String nume) throws Exception {
      if (puncte == null || !puncte.containsKey(nume)) {
         return false;
      }
      if (valori.get(nume) != null) {
         if (valori.get(nume).getPozitie() != null) {
            return true;
         }
         return false;
      }
      if (valori.get(nume) != null) {
         if (valori.get(nume).getPozitie() != null) {
            return true;
         }
      }
      return false;
   }

   private void parseFile() throws Exception, OutOfMemoryError {
      // System.out.println("Parse file;");
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
            for (String field : fieldNames) {
               if (field.equals(h4sds.getName())) {
                  H4DSWrapper wrap = extractFieldSheet(h4sds, field);
                  sheeturi.put(field, wrap);
               }
            }
         }
      }
      float[] lats = (float[]) sheeturi.get("Latitudine").getH4data().getData();
      float[] longs = (float[]) sheeturi.get("Longitudine").getH4data().getData();
      H4DSWrapper wrapSurfaceTemperature = null;
      H4DSWrapper wrapScanStartTime = null;
      H4DSWrapper wrapSOLAR_ZENITH = null;
      H4DSWrapper wrapPROCESSING_FLAG = null;
      H4DSWrapper wrapSENSOR_AZIMUTH = null;
      H4DSWrapper wrapSENSOR_ZENITH = null;
      H4DSWrapper wrapCIRRUS_REFLECTANCE = null;
      H4DSWrapper wrapCIRRUS_REFLECTANCE_FLAG = null;
      H4DSWrapper wrapCLOUD_EFFECTIVE_EMISSIVITY = null;
      H4DSWrapper wrapCLOUD_EFFECTIVE_RADIUS = null;
      H4DSWrapper wrapCLOUD_EFFECTIVE_RADIUS_1621 = null;
      H4DSWrapper wrapCLOUD_EFFECTIVE_RADIUS_UNCERTAINTY = null;
      H4DSWrapper wrapCLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_1621 = null;
      H4DSWrapper wrapCLOUD_FRACTION = null;
      H4DSWrapper wrapCLOUD_HEIGHT_METHOD = null;
      H4DSWrapper wrapCLOUD_MASK_1KM = null;
      H4DSWrapper wrapCLOUD_MASK_5KM = null;
      H4DSWrapper wrapCLOUD_MULTI_LAYER_FLAG = null;
      H4DSWrapper wrapCLOUD_OPTICAL_THICKNESS = null;
      H4DSWrapper wrapCLOUD_OPTICAL_THICKNESS_1621 = null;
      H4DSWrapper wrapCLOUD_OPTICAL_THICKNESS_UNCERTAINTY = null;
      H4DSWrapper wrapCLOUD_OPTICAL_THICKNESS_UNCERTAINTY_1621 = null;
      H4DSWrapper wrapCLOUD_PHASE_INFRARED = null;
      H4DSWrapper wrapCLOUD_PHASE_OPTICAL_PROPERTIES = null;
      H4DSWrapper wrapCLOUD_TOP_PRESSURE = null;
      H4DSWrapper wrapCLOUD_TOP_PRESSURE_INFRARED = null;
      H4DSWrapper wrapCLOUD_TOP_TEMPERATURE = null;
      H4DSWrapper wrapCLOUD_WATER_PATH = null;
      H4DSWrapper wrapCLOUD_WATER_PATH_UNCERTAINTY = null;
      H4DSWrapper wrapCLOUD_WATER_PATH_1621 = null;
      H4DSWrapper wrapCLOUD_WATER_PATH_UNCERTAINTY_1621 = null;
      H4DSWrapper wrapQUALITY_ASSURANCE_1KM = null;
      H4DSWrapper wrapQUALITY_ASSURANCE_5KM = null;
      H4DSWrapper wrapSURFACE_PRESSURE = null;
      H4DSWrapper wrapSURFACE_TYPE = null;
      H4DSWrapper wrapTROPOPAUSE_HEIGHT = null;
      for (Entry<String, double[]> oPozitie : puncte.entrySet()) {
         String numePozitie = oPozitie.getKey();
         double coord[] = oPozitie.getValue();
         double latit = coord[0];
         double longit = coord[1];
         long pozSearch = 0;
         boolean gasit = false;
         double deltaSumF = 100;
         for (int i = 0; i < lats.length - 1; i++) {
            float f = lats[i];
            if (f > latit - 0.5 && latit + 0.5 > f) {
               double deltaLat = Math.abs(latit - f);
               float fo = longs[i];
               if (fo > longit - 0.5 && fo < longit + 0.5) {
                  double mdelta = Math.abs(longit - fo);
                  double deltaSumT = mdelta + deltaLat;
                  if (deltaSumT < deltaSumF) {
                     deltaSumF = deltaSumT;
                     pozSearch = i;
                     gasit = true;
                  }
               }
            }
         }
         long yPozLat = pozSearch / cellAcross;
         long xPozLat = pozSearch % cellAcross;
         // System.out.println("Pozfound:" + pozSearch + " F1:" + latf1 +
         // " DLA:"
         // + deltaLatF + " DLO:" + deltaLonF + " X:" + xPozLat + " Y:"
         // + yPozLat);
         if (gasit) {
            if (wrapScanStartTime == null) {
               // System.out.println("Init sheets");
               wrapScanStartTime = sheeturi.get(SCAN_START_TIME);
               wrapSurfaceTemperature = sheeturi.get(SURFACE_TEMPERATURE);
               wrapSOLAR_ZENITH = sheeturi.get(SOLAR_ZENITH);
               wrapPROCESSING_FLAG = sheeturi.get(PROCESSING_FLAG);
               wrapSENSOR_AZIMUTH = sheeturi.get(SENSOR_AZIMUTH);
               wrapSENSOR_ZENITH = sheeturi.get(SENSOR_ZENITH);
               wrapCIRRUS_REFLECTANCE = sheeturi.get(CIRRUS_REFLECTANCE);
               wrapCIRRUS_REFLECTANCE_FLAG = sheeturi.get(CIRRUS_REFLECTANCE_FLAG);
               wrapCLOUD_EFFECTIVE_EMISSIVITY = sheeturi.get(CLOUD_EFFECTIVE_EMISSIVITY);
               wrapCLOUD_EFFECTIVE_RADIUS = sheeturi.get(CLOUD_EFFECTIVE_RADIUS);
               wrapCLOUD_EFFECTIVE_RADIUS_1621 = sheeturi.get(CLOUD_EFFECTIVE_RADIUS_1621);
               wrapCLOUD_EFFECTIVE_RADIUS_UNCERTAINTY = sheeturi.get(CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY);
               wrapCLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_1621 = sheeturi.get(CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_1621);
               wrapCLOUD_FRACTION = sheeturi.get(CLOUD_FRACTION);
               wrapCLOUD_HEIGHT_METHOD = sheeturi.get(CLOUD_HEIGHT_METHOD);
               wrapCLOUD_MASK_1KM = sheeturi.get(CLOUD_MASK_1KM);
               wrapCLOUD_MASK_5KM = sheeturi.get(CLOUD_MASK_5KM);
               wrapCLOUD_MULTI_LAYER_FLAG = sheeturi.get(CLOUD_MULTI_LAYER_FLAG);
               wrapCLOUD_OPTICAL_THICKNESS = sheeturi.get(CLOUD_OPTICAL_THICKNESS);
               wrapCLOUD_OPTICAL_THICKNESS_1621 = sheeturi.get(CLOUD_OPTICAL_THICKNESS_1621);
               wrapCLOUD_OPTICAL_THICKNESS_UNCERTAINTY = sheeturi.get(CLOUD_OPTICAL_THICKNESS_UNCERTAINTY);
               wrapCLOUD_OPTICAL_THICKNESS_UNCERTAINTY_1621 = sheeturi.get(CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_1621);
               wrapCLOUD_PHASE_INFRARED = sheeturi.get(CLOUD_PHASE_INFRARED);
               wrapCLOUD_PHASE_OPTICAL_PROPERTIES = sheeturi.get(CLOUD_PHASE_OPTICAL_PROPERTIES);
               wrapCLOUD_TOP_PRESSURE = sheeturi.get(CLOUD_TOP_PRESSURE);
               wrapCLOUD_TOP_PRESSURE_INFRARED = sheeturi.get(CLOUD_TOP_PRESSURE_INFRARED);
               wrapCLOUD_TOP_TEMPERATURE = sheeturi.get(CLOUD_TOP_TEMPERATURE);
               wrapCLOUD_WATER_PATH = sheeturi.get(CLOUD_WATER_PATH);
               wrapCLOUD_WATER_PATH_UNCERTAINTY = sheeturi.get(CLOUD_WATER_PATH_UNCERTAINTY);
               wrapCLOUD_WATER_PATH_1621 = sheeturi.get(CLOUD_WATER_PATH_1621);
               wrapCLOUD_WATER_PATH_UNCERTAINTY_1621 = sheeturi.get(CLOUD_WATER_PATH_UNCERTAINTY_1621);
               wrapQUALITY_ASSURANCE_1KM = sheeturi.get(QUALITY_ASSURANCE_1KM);
               wrapQUALITY_ASSURANCE_5KM = sheeturi.get(QUALITY_ASSURANCE_5KM);
               wrapSURFACE_PRESSURE = sheeturi.get(SURFACE_PRESSURE);
               wrapSURFACE_TYPE = sheeturi.get(SURFACE_TYPE);
               wrapTROPOPAUSE_HEIGHT = sheeturi.get(TROPOPAUSE_HEIGHT);
            }
            Mod06TO valoare = new Mod06TO();
            valori.put(numePozitie, valoare);
            valoare.setPozitie(pozSearch);
            valoare.setNumePozitie(numePozitie);
            valoare.setHdfFileName(file.getName());
            DateTime value = null;
            double[] vals = (double[]) wrapScanStartTime.getH4data().getData();
            DateTime retval = new DateTime(DateTimeZone.UTC);
            retval = retval.withDate(1993, 1, 1).dayOfYear().roundFloorCopy();
            value = retval.plusSeconds((int) vals[(int) pozSearch]);
            valoare.setTimp(value);
            if (wrapSENSOR_AZIMUTH == null) {
            }
            {
               valoare.setSurfaceTemperature(wrapSurfaceTemperature.findShortPozitionData(pozSearch));
            }
            {
               valoare.setSolarZenith(wrapSOLAR_ZENITH.findShortPozitionData(pozSearch));
            }
            {
               H4DSWrapper wrap = sheeturi.get(SOLAR_AZIMUTH);
               valoare.setSolarAzimuth(wrap.findShortPozitionData(pozSearch));
            }
            {
               valoare.setProcessingFlag(wrapPROCESSING_FLAG.findBytePozitionData(pozSearch));
            }
            {
               valoare.setSensorAzimuth(wrapSENSOR_AZIMUTH.findShortPozitionData(pozSearch));
            }
            {
               valoare.setSensorZenith(wrapSENSOR_ZENITH.findShortPozitionData(pozSearch));
            }
            {
               valoare.setCirrusReflectance(wrapCIRRUS_REFLECTANCE.findDoublePozitionData(pozSearch));
            }
            {
               valoare.setCirrusReflectanceFlag(wrapCIRRUS_REFLECTANCE_FLAG.findBytePozitionData(pozSearch));
            }
            {
               valoare.setCloudEffectiveEmissivity(wrapCLOUD_EFFECTIVE_EMISSIVITY.findDoublePozitionData(pozSearch));
            }
            {
               valoare.setCloudEffectiveRadius(wrapCLOUD_EFFECTIVE_RADIUS.findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudEffectiveRadius1621(wrapCLOUD_EFFECTIVE_RADIUS_1621.findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudEffectiveRadiusUncertainty(wrapCLOUD_EFFECTIVE_RADIUS_UNCERTAINTY
                     .findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudEffectiveRadiusUncertainty1621(wrapCLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_1621
                     .findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudFraction(wrapCLOUD_FRACTION.findDoublePozitionData(pozSearch));
            }
            {
               valoare.setCloudHeightMethod(wrapCLOUD_HEIGHT_METHOD.findBytePozitionData(pozSearch));
            }
            {
               valoare.setCloudMask1km(wrapCLOUD_MASK_1KM.findBytePozitionData(pozSearch));
            }
            {
               valoare.setCloudMask5km(wrapCLOUD_MASK_5KM.findBytePozitionData(pozSearch));
            }
            {
               valoare.setCloudMultiLayerFlag(wrapCLOUD_MULTI_LAYER_FLAG.findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudOpticalThickness(wrapCLOUD_OPTICAL_THICKNESS.findDoublePozitionData(pozSearch));
            }
            {
               valoare.setCloudOpticalThickness1621(wrapCLOUD_OPTICAL_THICKNESS_1621.findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudOpticalThicknessUncertainty(wrapCLOUD_OPTICAL_THICKNESS_UNCERTAINTY
                     .findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudOpticalThicknessUncertainty1621(wrapCLOUD_OPTICAL_THICKNESS_UNCERTAINTY_1621
                     .findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudPhaseInfrared(wrapCLOUD_PHASE_INFRARED.findBytePozitionData(pozSearch));
            }
            {
               valoare.setCloudPhaseOpticalProperties(wrapCLOUD_PHASE_OPTICAL_PROPERTIES
                     .findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudTopPressure(wrapCLOUD_TOP_PRESSURE.findDoublePozitionData(pozSearch));
            }
            /*
             * { H4DSWrapper wrap = sheeturi
             * .get(CLOUD_TOP_PRESSURE_FROM_RATIOS);
             * valoare.setCloudTopPressureFromRatios(wrap
             * .findShortPozitionData(pozSearch)); }
             */
            {
               valoare.setCloudTopPressureInfrared(wrapCLOUD_TOP_PRESSURE_INFRARED.findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudTopTemperature(wrapCLOUD_TOP_TEMPERATURE.findDoublePozitionData(pozSearch));
            }
            {
               valoare.setCloudWaterPath(wrapCLOUD_WATER_PATH.findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudWaterPathUncertainty(wrapCLOUD_WATER_PATH_UNCERTAINTY.findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudWaterPath1621(wrapCLOUD_WATER_PATH_1621.findShortPozitionData(pozSearch));
            }
            {
               valoare.setCloudWaterPathUncertainty1621(wrapCLOUD_WATER_PATH_UNCERTAINTY_1621
                     .findShortPozitionData(pozSearch));
            }
            // {
            // H4DSWrapper wrap = sheeturi
            // .get(EFFECTIVE_RADIUS_DIFFERENCE);
            // valoare.setEffectiveRadiusDifference(wrap
            // .findShortPozitionData(pozSearch));
            // }
            {
               valoare.setQualityAssurance1km(wrapQUALITY_ASSURANCE_1KM.findBytePozitionData(pozSearch));
            }
            {
               valoare.setQualityAssurance5km(wrapQUALITY_ASSURANCE_5KM.findBytePozitionData(pozSearch));
            }
            // {
            // H4DSWrapper wrap = sheeturi.get(RADIANCE_VARIANCE);
            // valoare.setRadianceVariance(wrap
            // .findShortPozitionData(pozSearch));
            // }
            // {
            // H4DSWrapper wrap = sheeturi.get(SPECTRAL_CLOUD_FORCING);
            // valoare.setSpectralCloudForcing(wrap
            // .findShortPozitionData(pozSearch));
            // }
            {
               valoare.setSurfacePressure(wrapSURFACE_PRESSURE.findShortPozitionData(pozSearch));
            }
            {
               valoare.setSurfaceType(wrapSURFACE_TYPE.findShortPozitionData(pozSearch));
            }
            {
               valoare.setTropopauseHeight(wrapTROPOPAUSE_HEIGHT.findDoublePozitionData(pozSearch));
            }
         }
      }
   }

   private H4DSWrapper extractFieldSheet(H4SDS h4sds, String field1) throws HDFException {
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
            } else if (attr.getValue() instanceof byte[]) {
               byte[] vals = (byte[]) attr.getValue();
               wrap.setFillValue(new Double(vals[0]));
            } else {
               System.out.println("Fill value este gresit. necunoscut:" + attr.getValue());
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

   public void infoFileGrups() throws Exception {
      @SuppressWarnings("rawtypes")
      Enumeration nodes = file.getRootNode().children();
      while (nodes.hasMoreElements()) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes.nextElement();
         Object obj = node.getUserObject();
         if (obj instanceof H4Vdata) {
            H4Vdata vd = (H4Vdata) obj;
            System.out.println(vd.getName() + "" + vd.getData());
         } else if (obj instanceof H4Group) {
            H4Group grp = (H4Group) obj;
            System.out.println(grp.getName() + " " + grp.getMetadata().size());
            Iterator<HObject> it = grp.getMemberList().iterator();
            while (it.hasNext()) {
               HObject hObject = it.next();
            }
         }
      }
      H4Group testGroup = (H4Group) file.get("/mod07/Geolocation Fields");
      System.out.println("testGroup:" + testGroup.getFullName() + " size=" + testGroup.getMemberList().size());
      Iterator<HObject> it = testGroup.getMemberList().iterator();
      while (it.hasNext()) {
         HObject hObject = it.next();
         System.out.println(hObject.getFullName() + " type " + hObject.getClass().getName());
      }
   }

   public void close() {
      try {
         file.close();
      } catch (Exception e) {
      }
   }

   public Long findPozitie(String nume) throws Exception {
      if (hasPosition(nume)) {
         return valori.get(nume).getPozitie();
      }
      return null;
   }

   public Double findTemperaturaSuprafata(String nume) throws Exception {
      if (hasPosition(nume)) {
         if (valori.get(nume).getSurfaceTemperature() != null) {
            return new Double(valori.get(nume).getSurfaceTemperature());
         }
      }
      return null;
   }

   public Double findPresiuneSuprafata(String nume) throws Exception {
      if (hasPosition(nume)) {
         if (valori.get(nume).getSurfacePressure() != null) {
            return new Double(valori.get(nume).getSurfacePressure());
         }
      }
      return null;
   }

   public DateTime findTimp(String nume) throws Exception {
      if (hasPosition(nume)) {
         return valori.get(nume).getTimp();
      }
      return null;
   }

   public DateTime findTimpMediu() throws Exception {
      long val = 0;
      for (Entry<String, double[]> st : puncte.entrySet()) {
         if (hasPosition(st.getKey())) {
            DateTime timpGasit = valori.get(st.getKey()).getTimp();
            if (val == 0) {
               val = timpGasit.getMillis();
            } else {
               val += timpGasit.getMillis();
               val = val / 2;
            }
         }
      }
      if (val > 0) {
         return new DateTime().withZone(DateTimeZone.UTC).withMillis(val);
      }
      return null;
   }

   public boolean hasValue(String nume) throws Exception {
      if (hasPosition(nume)) {
         return valori.get(nume).getPozitie() != null;
      }
      return false;
   }

   public TreeMap<String, Object> extractMetadata(H4File testFile) throws Exception {
      HObject hobj = (HObject) ((DefaultMutableTreeNode) testFile.getRootNode()).getUserObject();
      List metadataFile = hobj.getMetadata();
      System.out.println("Nume:" + hobj.getName());
      String coreMetadata = "";
      for (Iterator it = metadataFile.iterator(); it.hasNext();) {
         Attribute object = (Attribute) it.next();
         if ("CoreMetadata.0".equals(object.getName())) {
            coreMetadata = ((String[]) object.getValue())[0];
            break;
         }
      }
      if (StringUtils.isBlank(coreMetadata)) {
         return null;
      }
      TreeMap<String, Object> retval = new TreeMap<String, Object>();
      Scanner smare = new Scanner(coreMetadata);
      Stack<String> grup = new Stack<String>();
      String obiect = null;
      while (smare.hasNext()) {
         String line = smare.nextLine();
         if (StringUtils.isBlank(line)) {
            continue;
         }
         Scanner sline = new Scanner(line);
         sline.useDelimiter("=");
         while (sline.hasNext()) {
            String key = sline.next().trim();
            boolean hasValue = false;
            if ("GROUP".equals(key)) {
               hasValue = true;
               if (sline.hasNext()) {
                  String value = sline.next().trim();
                  grup.push(value);
               }
            }
            if ("END_GROUP".equals(key)) {
               if (sline.hasNext()) {
                  String value = sline.next().trim();
                  if (value.equals(grup.peek())) {
                     grup.pop();
                  }
               }
            }
            if ("OBJECT".equals(key)) {
               if (sline.hasNext()) {
                  String value = sline.next().trim();
                  obiect = value;
               }
            }
            if ("END-OBJECT".equals(key)) {
               obiect = null;
            }
            if ("VALUE".equals(key)) {
               if (sline.hasNext()) {
                  String value = sline.next().trim();
                  StringBuilder sb = new StringBuilder();
                  Iterator<String> it = grup.iterator();
                  while (it.hasNext()) {
                     String type = it.next();
                     if (StringUtils.isNotBlank(type)) {
                        sb.append(type);
                        sb.append("-");
                     }
                  }
                  retval.put(sb.toString() + obiect, value);
               }
            }
         }
      }
      return retval;
   }
}
