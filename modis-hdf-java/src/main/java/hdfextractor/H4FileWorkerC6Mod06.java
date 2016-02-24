package hdfextractor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import ncsa.hdf.hdflib.HDFException;
import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.FileFormat;
import ncsa.hdf.object.HObject;
import ncsa.hdf.object.h4.H4File;
import ncsa.hdf.object.h4.H4Group;
import ncsa.hdf.object.h4.H4SDS;
import ro.grig.sat.util.Util;

/**
 * @author grig
 *
 */
public class H4FileWorkerC6Mod06 implements Serializable {
   private TreeMap<String, H4DSWrapper> sheeturi = new TreeMap<String, H4DSWrapper>();
   private TreeMap<String, Mod06C06TO> valori = new TreeMap<String, Mod06C06TO>();
   private TreeMap<String, double[]> puncte;
   private H4File file;

   public TreeMap<String, H4DSWrapper> getSheeturi() {
      return sheeturi;
   }

   public void setSheeturi(TreeMap<String, H4DSWrapper> sheeturi) {
      this.sheeturi = sheeturi;
   }

   public TreeMap<String, Mod06C06TO> getValori() {
      return valori;
   }

   public void setValori(TreeMap<String, Mod06C06TO> valori) {
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

   H4FileWorkerC6Mod06(H4File h4file) throws Exception {
      super();
      this.file = h4file;
      file.open();
   }

   H4FileWorkerC6Mod06(String h4fileLocation) throws Exception {
      file = new H4File(h4fileLocation, FileFormat.READ);
      file.open();
   }

   public H4FileWorkerC6Mod06(String canonicalPath, TreeMap<String, double[]> statii) throws Exception {
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
      for (Entry<String, double[]> oPozitie : puncte.entrySet()) {
         String numePozitie = oPozitie.getKey();
         double coord[] = oPozitie.getValue();
         if (coord.length != 2) {
            throw new IllegalArgumentException("Am primit array de coordonate punct de dimensiune gresita");
         }
         Util.UnPoit findPoint5km = Util.extractNearPoint(lats, longs, coord, 0.05f);
         Util.UnPoit findPoint1km = Util.extractNearPoint(blats1km[0], blats1km[1], coord, 0.05f);
         if (findPoint5km.gasit) {
            long yPoz = findPoint5km.pozitie / cellAcross;
            long xPoz = findPoint5km.pozitie % cellAcross;
            Mod06C06TO valoare = new Mod06C06TO();
            valori.put(numePozitie, valoare);
            valoare.setLatitudine(findPoint5km.latidu);
            valoare.setLongitudine(findPoint5km.longit);
            valoare.setPozitieAcross(xPoz);
            valoare.setPozitieAlong(yPoz);
            valoare.setPozitie(findPoint5km.pozitie);
            valoare.setPozitie1km(findPoint1km.pozitie);
            valoare.setStatie(numePozitie);
            valoare.setNumePozitie(numePozitie);
            valoare.setHdfFileName(file.getName());
            pozitiiGasite.add(valoare);
         }
      }
      for (Mod06C06TO valoare : pozitiiGasite) {
         long pozSearch = valoare.getPozitie();
         long pozSearch1km = valoare.getPozitie1km();
         DateTime value = null;
         double[] vals = (double[]) sheeturi.get(Mod06C6Constants.SCAN_START_TIME).getH4data().getData();
         DateTime retval = new DateTime(DateTimeZone.UTC);
         retval = retval.withDate(1993, 1, 1).dayOfYear().roundFloorCopy();
         value = retval.plusSeconds((int) vals[(int) pozSearch]);
         valoare.setTimp(value);
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.ABOVE_CLOUD_WATER_VAPOR_094);
            if (wrap != null) {
               valoare.setAboveCloudWaterVapor094(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.ASYMMETRY_PARAMETER_ICE);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.ASYMMETRY_PARAMETER_LIQ);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.ATM_CORR_REFL);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.BRIGHTNESS_TEMPERATURE);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CIRRUS_REFLECTANCE);
            if (wrap != null) {
               valoare.setCirrusReflectance(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CIRRUS_REFLECTANCE_FLAG);
            if (wrap != null) {
               valoare.setCirrusReflectanceFlag(wrap.findBytePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_EMISSIVITY);
            if (wrap != null) {
               valoare.setCloudEffectiveEmissivity(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_EMISSIVITY_DAY);
            if (wrap != null) {
               valoare.setCloudEffectiveEmissivityDay(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_EMISSIVITY_NADIR);
            if (wrap != null) {
               valoare.setCloudEffectiveEmissivityNadir(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_EMISSIVITY_NADIR_DAY);
            if (wrap != null) {
               valoare.setCloudEffectiveEmissivityNadirDay(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_EMISSIVITY_NADIR_NIGHT);
            if (wrap != null) {
               valoare.setCloudEffectiveEmissivityNadirNight(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_EMISSIVITY_NIGHT);
            if (wrap != null) {
               valoare.setCloudEffectiveEmissivityNight(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS);
            if (wrap != null) {
               valoare.setCloudEffectiveRadius(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS_16);
            if (wrap != null) {
               valoare.setCloudEffectiveRadius16(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS_1621);
            if (wrap != null) {
               valoare.setCloudEffectiveRadius1621(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS_1621_PCL);
            if (wrap != null) {
               valoare.setCloudEffectiveRadius1621Pcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS_16_PCL);
            if (wrap != null) {
               valoare.setCloudEffectiveRadius16Pcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS_37);
            if (wrap != null) {
               valoare.setCloudEffectiveRadius37(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS_37_PCL);
            if (wrap != null) {
               valoare.setCloudEffectiveRadius37Pcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS_PCL);
            if (wrap != null) {
               valoare.setCloudEffectiveRadiusPcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY);
            if (wrap != null) {
               valoare.setCloudEffectiveRadiusUncertainty(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_16);
            if (wrap != null) {
               valoare.setCloudEffectiveRadiusUncertainty16(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_1621);
            if (wrap != null) {
               valoare.setCloudEffectiveRadiusUncertainty1621(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_37);
            if (wrap != null) {
               valoare.setCloudEffectiveRadiusUncertainty37(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EMISS11_1KM);
            if (wrap != null) {
               valoare.setCloudEmiss111km(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EMISS12_1KM);
            if (wrap != null) {
               valoare.setCloudEmiss121km(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EMISS13_1KM);
            if (wrap != null) {
               valoare.setCloudEmiss131km(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EMISS85_1KM);
            if (wrap != null) {
               valoare.setCloudEmiss851km(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_EMISSIVITY_1KM);
            if (wrap != null) {
               valoare.setCloudEmissivity1km(wrap.findBytePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_FRACTION);
            if (wrap != null) {
               valoare.setCloudFraction(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_FRACTION_DAY);
            if (wrap != null) {
               valoare.setCloudFractionDay(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_FRACTION_NADIR);
            if (wrap != null) {
               valoare.setCloudFractionNadir(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_FRACTION_NADIR_DAY);
            if (wrap != null) {
               valoare.setCloudFractionNadirDay(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_FRACTION_NADIR_NIGHT);
            if (wrap != null) {
               valoare.setCloudFractionNadirNight(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_FRACTION_NIGHT);
            if (wrap != null) {
               valoare.setCloudFractionNight(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_HEIGHT_METHOD);
            if (wrap != null) {
               valoare.setCloudHeightMethod(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_MASK_1KM);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_MASK_5KM);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_MASK_SPI);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_MULTI_LAYER_FLAG);
            if (wrap != null) {
               valoare.setCloudMultiLayerFlag(wrap.findBytePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS);
            if (wrap != null) {
               valoare.setCloudOpticalThickness(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS_16);
            if (wrap != null) {
               valoare.setCloudOpticalThickness16(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS_1621);
            if (wrap != null) {
               valoare.setCloudOpticalThickness1621(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS_1621_PCL);
            if (wrap != null) {
               valoare.setCloudOpticalThickness1621Pcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS_16_PCL);
            if (wrap != null) {
               valoare.setCloudOpticalThickness16Pcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS_37);
            if (wrap != null) {
               valoare.setCloudOpticalThickness37(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS_37_PCL);
            if (wrap != null) {
               valoare.setCloudOpticalThickness37Pcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS_PCL);
            if (wrap != null) {
               valoare.setCloudOpticalThicknessPcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS_UNCERTAINTY);
            if (wrap != null) {
               valoare.setCloudOpticalThicknessUncertainty(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_16);
            if (wrap != null) {
               valoare.setCloudOpticalThicknessUncertainty16(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_1621);
            if (wrap != null) {
               valoare.setCloudOpticalThicknessUncertainty1621(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_37);
            if (wrap != null) {
               valoare.setCloudOpticalThicknessUncertainty37(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_PHASE_INFRARED);
            if (wrap != null) {
               valoare.setCloudPhaseInfrared(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_PHASE_INFRARED_1KM);
            if (wrap != null) {
               valoare.setCloudPhaseInfrared1km(wrap.findBytePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_PHASE_INFRARED_DAY);
            if (wrap != null) {
               valoare.setCloudPhaseInfraredDay(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_PHASE_INFRARED_NIGHT);
            if (wrap != null) {
               valoare.setCloudPhaseInfraredNight(wrap.findBytePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_PHASE_OPTICAL_PROPERTIES);
            if (wrap != null) {
               valoare.setCloudPhaseOpticalProperties(wrap.findBytePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_HEIGHT);
            if (wrap != null) {
               valoare.setCloudTopHeight(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_HEIGHT_1KM);
            if (wrap != null) {
               valoare.setCloudTopHeight1km(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_HEIGHT_NADIR);
            if (wrap != null) {
               valoare.setCloudTopHeightNadir(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_HEIGHT_NADIR_DAY);
            if (wrap != null) {
               valoare.setCloudTopHeightNadirDay(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_HEIGHT_NADIR_NIGHT);
            if (wrap != null) {
               valoare.setCloudTopHeightNadirNight(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_METHOD_1KM);
            if (wrap != null) {
               valoare.setCloudTopMethod1km(wrap.findBytePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_PRESSURE);
            if (wrap != null) {
               valoare.setCloudTopPressure(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_PRESSURE_1KM);
            if (wrap != null) {
               valoare.setCloudTopPressure1km(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_PRESSURE_DAY);
            if (wrap != null) {
               valoare.setCloudTopPressureDay(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_PRESSURE_FROM_RATIOS);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_PRESSURE_INFRARED);
            if (wrap != null) {
               valoare.setCloudTopPressureInfrared(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_PRESSURE_NADIR);
            if (wrap != null) {
               valoare.setCloudTopPressureNadir(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_PRESSURE_NADIR_DAY);
            if (wrap != null) {
               valoare.setCloudTopPressureNadirDay(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_PRESSURE_NADIR_NIGHT);
            if (wrap != null) {
               valoare.setCloudTopPressureNadirNight(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_PRESSURE_NIGHT);
            if (wrap != null) {
               valoare.setCloudTopPressureNight(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_TEMPERATURE);
            if (wrap != null) {
               valoare.setCloudTopTemperature(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_TEMPERATURE_1KM);
            if (wrap != null) {
               valoare.setCloudTopTemperature1km(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_TEMPERATURE_DAY);
            if (wrap != null) {
               valoare.setCloudTopTemperatureDay(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_TEMPERATURE_NADIR);
            if (wrap != null) {
               valoare.setCloudTopTemperatureNadir(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_TEMPERATURE_NADIR_DAY);
            if (wrap != null) {
               valoare.setCloudTopTemperatureNadirDay(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_TEMPERATURE_NADIR_NIGHT);
            if (wrap != null) {
               valoare.setCloudTopTemperatureNadirNight(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_TOP_TEMPERATURE_NIGHT);
            if (wrap != null) {
               valoare.setCloudTopTemperatureNight(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH);
            if (wrap != null) {
               valoare.setCloudWaterPath(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH_16);
            if (wrap != null) {
               valoare.setCloudWaterPath16(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH_1621);
            if (wrap != null) {
               valoare.setCloudWaterPath1621(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH_1621_PCL);
            if (wrap != null) {
               valoare.setCloudWaterPath1621Pcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH_16_PCL);
            if (wrap != null) {
               valoare.setCloudWaterPath16Pcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH_37);
            if (wrap != null) {
               valoare.setCloudWaterPath37(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH_37_PCL);
            if (wrap != null) {
               valoare.setCloudWaterPath37Pcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH_PCL);
            if (wrap != null) {
               valoare.setCloudWaterPathPcl(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH_UNCERTAINTY);
            if (wrap != null) {
               valoare.setCloudWaterPathUncertainty(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH_UNCERTAINTY_16);
            if (wrap != null) {
               valoare.setCloudWaterPathUncertainty16(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH_UNCERTAINTY_1621);
            if (wrap != null) {
               valoare.setCloudWaterPathUncertainty1621(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.CLOUD_WATER_PATH_UNCERTAINTY_37);
            if (wrap != null) {
               valoare.setCloudWaterPathUncertainty37(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.EXTINCTION_EFFICIENCY_ICE);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.EXTINCTION_EFFICIENCY_LIQ);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.IRP_CTH_CONSISTENCY_FLAG_1KM);
            if (wrap != null) {
               valoare.setIrpCthConsistencyFlag1km(wrap.findBytePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.IRW_LOW_CLOUD_TEMPERATURE_FROM_COP);
            if (wrap != null) {
               valoare.setIrwLowCloudTemperatureFromCop(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.OS_TOP_FLAG_1KM);
            if (wrap != null) {
               valoare.setOsTopFlag1km(wrap.findBytePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.QUALITY_ASSURANCE_1KM);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.QUALITY_ASSURANCE_5KM);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.RADIANCE_VARIANCE);
            if (wrap != null) {
               valoare.setRadianceVariance(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.RETRIEVAL_FAILURE_METRIC);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.RETRIEVAL_FAILURE_METRIC_16);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.RETRIEVAL_FAILURE_METRIC_1621);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.RETRIEVAL_FAILURE_METRIC_37);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SCAN_START_TIME);
            if (wrap != null) {
               valoare.setScanStartTime(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SENSOR_AZIMUTH);
            if (wrap != null) {
               valoare.setSensorAzimuth(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SENSOR_AZIMUTH_DAY);
            if (wrap != null) {
               valoare.setSensorAzimuthDay(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SENSOR_AZIMUTH_NIGHT);
            if (wrap != null) {
               valoare.setSensorAzimuthNight(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SENSOR_ZENITH);
            if (wrap != null) {
               valoare.setSensorZenith(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SENSOR_ZENITH_DAY);
            if (wrap != null) {
               valoare.setSensorZenithDay(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SENSOR_ZENITH_NIGHT);
            if (wrap != null) {
               valoare.setSensorZenithNight(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SINGLE_SCATTER_ALBEDO_ICE);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SINGLE_SCATTER_ALBEDO_LIQ);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SOLAR_AZIMUTH);
            if (wrap != null) {
               valoare.setSolarAzimuth(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SOLAR_AZIMUTH_DAY);
            if (wrap != null) {
               valoare.setSolarAzimuthDay(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SOLAR_AZIMUTH_NIGHT);
            if (wrap != null) {
               valoare.setSolarAzimuthNight(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SOLAR_ZENITH);
            if (wrap != null) {
               valoare.setSolarZenith(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SOLAR_ZENITH_DAY);
            if (wrap != null) {
               valoare.setSolarZenithDay(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SOLAR_ZENITH_NIGHT);
            if (wrap != null) {
               valoare.setSolarZenithNight(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SPECTRAL_CLOUD_FORCING);
            if (wrap != null) {
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SURFACE_PRESSURE);
            if (wrap != null) {
               valoare.setSurfacePressure(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SURFACE_TEMPERATURE);
            if (wrap != null) {
               valoare.setSurfaceTemperature(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.SURFACE_TEMPERATURE_1KM);
            if (wrap != null) {
               valoare.setSurfaceTemperature1km(wrap.findDoublePozitionData(pozSearch1km));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod06C6Constants.TROPOPAUSE_HEIGHT);
            if (wrap != null) {
               valoare.setTropopauseHeight(wrap.findDoublePozitionData(pozSearch));
            }
         }
      }
   }
}