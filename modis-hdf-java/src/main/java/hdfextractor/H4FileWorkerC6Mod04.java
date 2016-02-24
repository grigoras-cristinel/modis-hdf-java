package hdfextractor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
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

public class H4FileWorkerC6Mod04 implements Serializable {
   private TreeMap<String, H4DSWrapper> sheeturi = new TreeMap<String, H4DSWrapper>();
   private TreeMap<String, Mod04C06TO> valori = new TreeMap<String, Mod04C06TO>();
   private TreeMap<String, double[]> puncte;
   private H4File file;

   public TreeMap<String, H4DSWrapper> getSheeturi() {
      return sheeturi;
   }

   public void setSheeturi(TreeMap<String, H4DSWrapper> sheeturi) {
      this.sheeturi = sheeturi;
   }

   public TreeMap<String, Mod04C06TO> getValori() {
      return valori;
   }

   public void setValori(TreeMap<String, Mod04C06TO> valori) {
      this.valori = valori;
   }

   public TreeMap<String, double[]> getPuncte() {
      return puncte;
   }

   public void setPuncte(TreeMap<String, double[]> puncte) {
      this.puncte = puncte;
   }

   public H4File getFile() {
      return file;
   }

   public void setFile(H4File file) {
      this.file = file;
   }

   boolean hasPosition(String nume) throws Exception {
      if (puncte == null || !puncte.containsKey(nume)) {
         return false;
      }
      if (valori.get(nume) != null) {
         if (valori.get(nume).getPozitie() != null) {
            return true;
         }
         return false;
      }
      return false;
   }

   H4FileWorkerC6Mod04(H4File h4file) throws Exception {
      super();
      this.file = h4file;
      file.open();
   }

   H4FileWorkerC6Mod04(String h4fileLocation) throws Exception {
      file = new H4File(h4fileLocation, FileFormat.READ);
      file.open();
   }

   public H4FileWorkerC6Mod04(String canonicalPath, TreeMap<String, double[]> statii) throws Exception {
      this(canonicalPath);
      puncte = statii;
      parseFile();
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

   public void parseFile() throws Exception, OutOfMemoryError {
      System.out.println("parseFile()");
      TreeMap<String, Integer> dims = Util.dimensionFieldsFromDescription(file);
      int cellAcross = dims.get("Cell_Across_Swath");
      int cellAllong = dims.get("Cell_Along_Swath");
      int cellAcross500 = dims.get("Cell_Across_Swath_500");
      int cellAlong500 = dims.get("Cell_Along_Swath_500");
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
      ArrayList<Mod04C06TO> pozitiiGasite = new ArrayList<Mod04C06TO>();
      float[] lats = (float[]) sheeturi.get("Latitudine").getH4data().getData();
      float[] longs = (float[]) sheeturi.get("Longitudine").getH4data().getData();
      float[][] blats500 = Util.buildLats500(lats, longs, cellAcross, cellAcross500, cellAlong500);
      for (Entry<String, double[]> oPozitie : puncte.entrySet()) {
         String numePozitie = oPozitie.getKey();
         double coord[] = oPozitie.getValue();
         if (coord.length != 2) {
            throw new IllegalArgumentException("Am primit array de coordonate punct de dimensiune gresita");
         }
         Util.UnPoit findPoint5km = Util.extractNearPoint(lats, longs, coord, 0.05f);
         Util.UnPoit findPoint1km = Util.extractNearPoint(blats500[0], blats500[1], coord, 0.05f);
         if (findPoint5km.gasit) {
            long yPoz = findPoint5km.pozitie / cellAcross;
            long xPoz = findPoint5km.pozitie % cellAcross;
            Mod04C06TO valoare = new Mod04C06TO();
            valori.put(numePozitie, valoare);
            valoare.setLatitudine(findPoint5km.latidu);
            valoare.setLongitudine(findPoint5km.longit);
            valoare.setPozitieAcross(xPoz);
            valoare.setPozitieAlong(yPoz);
            valoare.setPozitie(findPoint5km.pozitie);
            valoare.setPozitie500(findPoint1km.pozitie);
            valoare.setStatie(numePozitie);
            valoare.setNumePozitie(numePozitie);
            valoare.setHdfFileName(file.getName());
            pozitiiGasite.add(valoare);
         }
      }
      for (Mod04C06TO valoare : pozitiiGasite) {
         long pozSearch = valoare.getPozitie();
         long pozSearch500 = valoare.getPozitie500();
         DateTime value = null;
         double[] vals = (double[]) sheeturi.get(Mod04C6Constants.SCAN_START_TIME).getH4data().getData();
         DateTime retval = new DateTime(DateTimeZone.UTC);
         retval = retval.withDate(1993, 1, 1).dayOfYear().roundFloorCopy();
         value = retval.plusSeconds((int) vals[(int) pozSearch]);
         valoare.setTimp(value);
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.AEROSOL_CLDMASK_LAND_OCEAN);
            if (wrap != null) {
               valoare.setAerosolCldmaskLandOcean(wrap.findDoublePozitionData(pozSearch500));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.AEROSOL_CLOUD_FRACTION_LAND);
            if (wrap != null) {
               valoare.setAerosolCloudFractionLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.AEROSOL_CLOUD_FRACTION_OCEAN);
            if (wrap != null) {
               valoare.setAerosolCloudFractionOcean(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.AEROSOL_TYPE_LAND);
            if (wrap != null) {
               valoare.setAerosolTypeLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.ANGSTROM_EXPONENT_1_OCEAN);
            if (wrap != null) {
               valoare.setAngstromExponent1Ocean(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.ANGSTROM_EXPONENT_2_OCEAN);
            if (wrap != null) {
               valoare.setAngstromExponent2Ocean(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED);
            if (wrap != null) {
               valoare.setAod550DarkTargetDeepBlueCombined(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_ALGORITHM_FLAG);
            if (wrap != null) {
               valoare.setAod550DarkTargetDeepBlueCombinedAlgorithmFlag(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_QA_FLAG);
            if (wrap != null) {
               valoare.setAod550DarkTargetDeepBlueCombinedQaFlag(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.ASYMMETRY_FACTOR_AVERAGE_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.ASYMMETRY_FACTOR_BEST_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.AVERAGE_CLOUD_PIXEL_DISTANCE_LAND_OCEAN);
            if (wrap != null) {
               valoare.setAverageCloudPixelDistanceLandOcean(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.BACKSCATTERING_RATIO_AVERAGE_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.BACKSCATTERING_RATIO_BEST_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.CLOUD_PIXEL_DISTANCE_LAND_OCEAN);
            if (wrap != null) {
               valoare.setCloudPixelDistanceLandOcean(wrap.findDoublePozitionData(pozSearch500));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.CORRECTED_OPTICAL_DEPTH_LAND);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.CORRECTED_OPTICAL_DEPTH_LAND_WAV2P1);
            if (wrap != null) {
               valoare.setCorrectedOpticalDepthLandWav2p1(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepth550Land(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_BEST_ESTIMATE);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepth550LandBestEstimate(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi
                  .get(Mod04C6Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_ESTIMATED_UNCERTAINTY);
            if (wrap != null) {
               valoare
                     .setDeepBlueAerosolOpticalDepth550LandEstimatedUncertainty(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_QA_FLAG);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepth550LandQaFlag(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_STD);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepth550LandStd(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_ALGORITHM_FLAG_LAND);
            if (wrap != null) {
               valoare.setDeepBlueAlgorithmFlagLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_ANGSTROM_EXPONENT_LAND);
            if (wrap != null) {
               valoare.setDeepBlueAngstromExponentLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_CLOUD_FRACTION_LAND);
            if (wrap != null) {
               valoare.setDeepBlueCloudFractionLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_NUMBER_PIXELS_USED_550_LAND);
            if (wrap != null) {
               valoare.setDeepBlueNumberPixelsUsed550Land(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_SPECTRAL_AEROSOL_OPTICAL_DEPTH_LAND);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_SPECTRAL_SINGLE_SCATTERING_ALBEDO_LAND);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_SPECTRAL_SURFACE_REFLECTANCE_LAND);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.DEEP_BLUE_SPECTRAL_TOA_REFLECTANCE_LAND);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.EFFECTIVE_OPTICAL_DEPTH_0P55UM_OCEAN);
            if (wrap != null) {
               valoare.setEffectiveOpticalDepth0p55umOcean(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.EFFECTIVE_OPTICAL_DEPTH_AVERAGE_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.EFFECTIVE_OPTICAL_DEPTH_BEST_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.EFFECTIVE_RADIUS_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.FITTING_ERROR_LAND);
            if (wrap != null) {
               valoare.setFittingErrorLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.GLINT_ANGLE);
            if (wrap != null) {
               valoare.setGlintAngle(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.IMAGE_OPTICAL_DEPTH_LAND_AND_OCEAN);
            if (wrap != null) {
               valoare.setImageOpticalDepthLandAndOcean(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.LAND_OCEAN_QUALITY_FLAG);
            if (wrap != null) {
               valoare.setLandOceanQualityFlag(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.LAND_SEA_FLAG);
            if (wrap != null) {
               valoare.setLandSeaFlag(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.LEAST_SQUARES_ERROR_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.MASS_CONCENTRATION_LAND);
            if (wrap != null) {
               valoare.setMassConcentrationLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.MASS_CONCENTRATION_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.MEAN_REFLECTANCE_LAND);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.MEAN_REFLECTANCE_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.NUMBER_PIXELS_USED_LAND);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.NUMBER_PIXELS_USED_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.OPTICAL_DEPTH_BY_MODELS_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.OPTICAL_DEPTH_LAND_AND_OCEAN);
            if (wrap != null) {
               valoare.setOpticalDepthLandAndOcean(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.OPTICAL_DEPTH_LARGE_AVERAGE_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.OPTICAL_DEPTH_LARGE_BEST_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.OPTICAL_DEPTH_RATIO_SMALL_LAND);
            if (wrap != null) {
               valoare.setOpticalDepthRatioSmallLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.OPTICAL_DEPTH_RATIO_SMALL_OCEAN_0p55MICRON);
            if (wrap != null) {
               valoare.setOpticalDepthRatioSmallOcean0p55micron(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.OPTICAL_DEPTH_SMALL_AVERAGE_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.OPTICAL_DEPTH_SMALL_BEST_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.PSML003_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.QUALITY_ASSURANCE_LAND);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.QUALITY_ASSURANCE_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.SCAN_START_TIME);
            if (wrap != null) {
               valoare.setScanStartTime(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.SCATTERING_ANGLE);
            if (wrap != null) {
               valoare.setScatteringAngle(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.SENSOR_AZIMUTH);
            if (wrap != null) {
               valoare.setSensorAzimuth(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.SENSOR_ZENITH);
            if (wrap != null) {
               valoare.setSensorZenith(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.SOLAR_AZIMUTH);
            if (wrap != null) {
               valoare.setSolarAzimuth(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.SOLAR_ZENITH);
            if (wrap != null) {
               valoare.setSolarZenith(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.SOLUTION_INDEX_OCEAN_LARGE);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.SOLUTION_INDEX_OCEAN_SMALL);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.STD_REFLECTANCE_LAND);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.STD_REFLECTANCE_OCEAN);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.SURFACE_REFLECTANCE_LAND);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.TOPOGRAPHIC_ALTITUDE_LAND);
            if (wrap != null) {
               valoare.setTopographicAltitudeLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04C6Constants.WIND_SPEED_NCEP_OCEAN);
            if (wrap != null) {
               valoare.setWindSpeedNcepOcean(wrap.findDoublePozitionData(pozSearch));
            }
         }
      }
   }
}