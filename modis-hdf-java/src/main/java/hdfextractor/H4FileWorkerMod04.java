package hdfextractor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import ro.grig.sat.util.StatieMeteoTO;

//@formatter:off
/**
 *
 * Parseaza un fiser HDF pentru modis04.
 *
 *  - Extrage valorile doar pentru campurile care au valori in grila si nu pentru
 * campurile care au alta dimensiune.
 *  - Exista sheeturi cu dimensiunea 3, la care nu stiu cum aleg campul
 *
 *
 * @author Grigoras Cristinel
 *
 */
//@formatter:on
public class H4FileWorkerMod04 implements Serializable {
   /**
	 *
	 */
   private static final long serialVersionUID = 1L;
   /** The Constant cellAcross. */
   private final static int cellAcross = Mod04Constants.DimCellAcrossSwath;
   @SuppressWarnings("unused")
   private final static int cellAlong = Mod04Constants.DimCellAlongSwath;
   H4File file;
   TreeMap<String, double[]> puncte;
   TreeMap<String, H4DSWrapper> sheeturi = new TreeMap<String, H4DSWrapper>();
   TreeMap<String, Mod04TO> valori = new TreeMap<String, Mod04TO>();

   public TreeMap<String, Mod04TO> getValori() {
      return valori;
   }

   public void setValori(TreeMap<String, Mod04TO> valori) {
      this.valori = valori;
   }

   public TreeMap<String, double[]> getPuncte() {
      return puncte;
   }

   public void setPuncte(TreeMap<String, double[]> pozitii) {
      this.puncte = pozitii;
   }

   public H4FileWorkerMod04(H4File h4file) throws Exception {
      super();
      this.file = h4file;
      file.open();
   }

   public H4FileWorkerMod04(String h4fileLocation) throws Exception {
      file = new H4File(h4fileLocation, FileFormat.READ);
      file.open();
   }

   public H4FileWorkerMod04(String canonicalPath, TreeMap<String, double[]> statii) throws Exception {
      this(canonicalPath);
      puncte = statii;
      parseFile();
   }

   public H4FileWorkerMod04(String canonicalPath, List<StatieMeteoTO> statiiList) throws Exception {
      this(canonicalPath);
      TreeMap<String, double[]> statii = new TreeMap<String, double[]>();
      for (StatieMeteoTO statieMeteoTO : statiiList) {
         statii.put(statieMeteoTO.getCod(), new double[] { statieMeteoTO.getLat(), statieMeteoTO.getLon() });
      }
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
      return false;
   }

   private void parseFile() throws Exception, OutOfMemoryError {
      System.out.println("parseFile()");
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
            for (String field : Mod04Constants.fieldNames) {
               if (field.equals(h4sds.getName())) {
                  H4DSWrapper wrap = extractFieldSheet(h4sds, field);
                  sheeturi.put(field, wrap);
                  break;
               }
            }
         }
      }
      ArrayList<Mod04TO> pozitiiGasite = new ArrayList<Mod04TO>();
      for (Entry<String, double[]> oPozitie : puncte.entrySet()) {
         String numePozitie = oPozitie.getKey();
         double coord[] = oPozitie.getValue();
         // System.out.print("Pozitie :" + oPozitie.getValue()[0] + " <> " +
         // oPozitie.getValue()[1] + "  ");
         if (coord.length == 2) {
            double latit = coord[0];
            double longit = coord[1];
            float[] lats = (float[]) sheeturi.get("Latitudine").getH4data().getData();
            float[] longs = (float[]) sheeturi.get("Longitudine").getH4data().getData();
            long pozSearch = -1;
            double[] findPoint = new double[2];
            /*
             * Calculez distanta minima fata de coordonatele date, suma
             * diferentelor fata de punct sa fie minima.
             */
            boolean gasit = false;
            double deltaSumF = 100; // delta este mare 100
            double space = 0.01;
            double mindist = 100;
            for (int i = 0; i < lats.length - 1; i++) {
               float lat = lats[i];
               float lon = longs[i];
               double dist = Math.sqrt((lat - latit) * (lat - latit) + (lon - longit) * (lon - longit));
               double min = Math.min(mindist, dist);
               if (min != mindist) {
                  mindist = min;
                  pozSearch = i;
                  findPoint[0] = lat;
                  findPoint[1] = lon;
               }
            }
            // System.out.print(" distanta minima:" + mindist);
            if (mindist < 0.05) {
               gasit = true;
            }
            // System.out.println(" gasit:" + gasit);
            long yPoz = pozSearch / cellAcross;
            long xPoz = pozSearch % cellAcross;
            // System.out.println("Pozfound:" + pozSearch + " F1:" + latf1 +
            // " DLA:"
            // + deltaLatF + " DLO:" + deltaLonF + " X:" + xPozLat + " Y:"
            // + yPozLat);
            if (gasit) {
               Mod04TO valoare = new Mod04TO();
               valori.put(numePozitie, valoare);
               valoare.setLatitudine(findPoint[0]);
               valoare.setLongitudine(findPoint[1]);
               valoare.setPozitieAcross(xPoz);
               valoare.setPozitieAlong(yPoz);
               valoare.setPozitie(pozSearch);
               valoare.setStatie(numePozitie);
               valoare.setHdfFileName(file.getName());
               pozitiiGasite.add(valoare);
            }
         }
      }
      for (Mod04TO valoare : pozitiiGasite) {
         long pozSearch = valoare.getPozitie();
         DateTime value = null;
         double[] vals = (double[]) sheeturi.get(Mod04Constants.SCAN_START_TIME).getH4data().getData();
         DateTime retval = new DateTime(DateTimeZone.UTC);
         retval = retval.withDate(1993, 1, 1).dayOfYear().roundFloorCopy();
         value = retval.plusSeconds((int) vals[(int) pozSearch]);
         valoare.setTimp(value);
         // Start
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.AEROSOL_TYPE_LAND);
            valoare.setAerosolTypeLand(wrap.findDoublePozitionData(pozSearch));
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.ANGSTROM_EXPONENT_1_OCEAN);
            valoare.setAngstromExponent1Ocean0(wrap.findDoublePozitionData(new int[] { 0,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.ANGSTROM_EXPONENT_1_OCEAN);
            valoare.setAngstromExponent1Ocean1(wrap.findDoublePozitionData(new int[] { 1,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.ANGSTROM_EXPONENT_2_OCEAN);
            valoare.setAngstromExponent2Ocean0(wrap.findDoublePozitionData(new int[] { 0,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.ANGSTROM_EXPONENT_2_OCEAN);
            valoare.setAngstromExponent2Ocean1(wrap.findDoublePozitionData(new int[] { 1,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.ANGSTROM_EXPONENT_LAND);
            if (wrap != null) {
               valoare.setAngstromExponentLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.CLOUD_CONDENSATION_NUCLEI_OCEAN);
            if (wrap != null) {
               valoare.setCloudCondensationNucleiOcean0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.CLOUD_CONDENSATION_NUCLEI_OCEAN);
            if (wrap != null) {
               valoare.setCloudCondensationNucleiOcean1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.CLOUD_FRACTION_LAND);
            if (wrap != null) {
               valoare.setCloudFractionLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.CLOUD_FRACTION_OCEAN);
            if (wrap != null) {
               valoare.setCloudFractionOcean(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.CLOUD_MASK_QA);
            if (wrap != null) {
               valoare.setCloudMaskQA(wrap.findBytePozitionData(pozSearch));
            }
         }
         // cazul 3=3
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.CORRECTED_OPTICAL_DEPTH_LAND);
            valoare.setCorrectedOpticalDepthLand0(wrap.findDoublePozitionData(new int[] { 0,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.CORRECTED_OPTICAL_DEPTH_LAND);
            valoare.setCorrectedOpticalDepthLand1(wrap.findDoublePozitionData(new int[] { 1,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.CORRECTED_OPTICAL_DEPTH_LAND);
            valoare.setCorrectedOpticalDepthLand2(wrap.findDoublePozitionData(new int[] { 2,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.CORRECTED_OPTICAL_DEPTH_LAND_WAV2P1);
            valoare.setCorrectedOpticalDepthLandwav2p1(wrap.findDoublePozitionData(pozSearch));
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.CRITICAL_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setCriticalReflectanceLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.CRITICAL_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setCriticalReflectanceLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepth550Land(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_STD);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepth550LandSTD(wrap.findDoublePozitionData(pozSearch));
            }
         }
         // cazul 3=3
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_LAND);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepthLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_LAND);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepthLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_LAND);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepthLand2(wrap.findDoublePozitionData(new int[] { 2,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=3
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_LAND_STD);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepthLandSTD0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_LAND_STD);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepthLandSTD1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_LAND_STD);
            if (wrap != null) {
               valoare.setDeepBlueAerosolOpticalDepthLandSTD2(wrap.findDoublePozitionData(new int[] { 2,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_ANGSTROM_EXPONENT_LAND);
            valoare.setDeepBlueAngstromExponentLand(wrap.findDoublePozitionData(pozSearch));
         }
         // cazul 3=3
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_MEAN_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setDeepBlueMeanReflectanceLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_MEAN_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setDeepBlueMeanReflectanceLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_MEAN_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setDeepBlueMeanReflectanceLand2(wrap.findDoublePozitionData(new int[] { 2,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=3
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_NUMBER_PIXELS_USED_LAND);
            if (wrap != null) {
               valoare.setDeepBlueNumberPixelsUsedLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_NUMBER_PIXELS_USED_LAND);
            if (wrap != null) {
               valoare.setDeepBlueNumberPixelsUsedLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_NUMBER_PIXELS_USED_LAND);
            if (wrap != null) {
               valoare.setDeepBlueNumberPixelsUsedLand2(wrap.findDoublePozitionData(new int[] { 2,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=3
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_SINGLE_SCATTERING_ALBEDO_LAND);
            if (wrap != null) {
               valoare.setDeepBlueSingleScatteringAlbedoLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_SINGLE_SCATTERING_ALBEDO_LAND);
            if (wrap != null) {
               valoare.setDeepBlueSingleScatteringAlbedoLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_SINGLE_SCATTERING_ALBEDO_LAND);
            if (wrap != null) {
               valoare.setDeepBlueSingleScatteringAlbedoLand2(wrap.findDoublePozitionData(new int[] { 2,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=3
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_SURFACE_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setDeepBlueSurfaceReflectanceLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_SURFACE_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setDeepBlueSurfaceReflectanceLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.DEEP_BLUE_SURFACE_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setDeepBlueSurfaceReflectanceLand2(wrap.findDoublePozitionData(new int[] { 2,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.EFFECTIVE_RADIUS_OCEAN);
            valoare.setEffectiveRadiusOcean0(wrap.findDoublePozitionData(new int[] { 0,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.EFFECTIVE_RADIUS_OCEAN);
            valoare.setEffectiveRadiusOcean1(wrap.findDoublePozitionData(new int[] { 1,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.ERROR_CRITICAL_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setErrorCriticalReflectanceLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.ERROR_CRITICAL_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setErrorCriticalReflectanceLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.ERROR_PATH_RADIANCE_LAND);
            if (wrap != null) {
               valoare.setErrorPathRadianceLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.ERROR_PATH_RADIANCE_LAND);
            if (wrap != null) {
               valoare.setErrorPathRadianceLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.FITTING_ERROR_LAND);
            if (wrap != null) {
               valoare.setFittingErrorLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.IMAGE_OPTICAL_DEPTH_LAND_AND_OCEAN);
            if (wrap != null) {
               valoare.setImageOpticalDepthLandAndOcean(wrap.findDoublePozitionData(pozSearch));
            }
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.LEAST_SQUARES_ERROR_OCEAN);
            if (wrap != null) {
               valoare.setLeastSquaresErrorOcean0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.LEAST_SQUARES_ERROR_OCEAN);
            if (wrap != null) {
               valoare.setLeastSquaresErrorOcean1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.MASS_CONCENTRATION_LAND);
            valoare.setMassConcentrationLand(wrap.findDoublePozitionData(pozSearch));
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.MASS_CONCENTRATION_OCEAN);
            valoare.setMassConcentrationOcean0(wrap.findDoublePozitionData(new int[] { 0,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.MASS_CONCENTRATION_OCEAN);
            valoare.setMassConcentrationOcean1(wrap.findDoublePozitionData(new int[] { 1,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         // cazul 3=3
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.MEAN_REFLECTANCE_LAND_ALL);
            if (wrap != null) {
               valoare.setMeanReflectanceLandAll0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.MEAN_REFLECTANCE_LAND_ALL);
            if (wrap != null) {
               valoare.setMeanReflectanceLandAll1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.MEAN_REFLECTANCE_LAND_ALL);
            if (wrap != null) {
               valoare.setMeanReflectanceLandAll2(wrap.findDoublePozitionData(new int[] { 2,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.NUMBER_PIXELS_USED_LAND);
            if (wrap != null) {
               valoare.setNumberPixelsUsedLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.NUMBER_PIXELS_USED_LAND);
            if (wrap != null) {
               valoare.setNumberPixelsUsedLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.NUMBER_PIXELS_USED_OCEAN);
            if (wrap != null) {
               valoare.setNumberPixelsUsedOcean(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.OPTICAL_DEPTH_LAND_AND_OCEAN);
            if (wrap != null) {
               valoare.setOpticalDepthLandAndOcean(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.OPTICAL_DEPTH_RATIO_SMALL_LAND);
            if (wrap != null) {
               valoare.setOpticalDepthRatioSmallLand(wrap.findDoublePozitionData(pozSearch));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.OPTICAL_DEPTH_RATIO_SMALL_LAND_AND_OCEAN);
            if (wrap != null) {
               valoare.setOpticalDepthRatioSmallLandAndOcean(wrap.findDoublePozitionData(pozSearch));
            }
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.OPTICAL_DEPTH_RATIO_SMALL_OCEAN_055MICRON);
            if (wrap != null) {
               valoare.setOpticalDepthRatioSmallOcean055micron0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.OPTICAL_DEPTH_RATIO_SMALL_OCEAN_055MICRON);
            if (wrap != null) {
               valoare.setOpticalDepthRatioSmallOcean055micron1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=4
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.OPTICAL_DEPTH_SMALL_LAND);
            if (wrap != null) {
               valoare.setOpticalDepthSmallLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.OPTICAL_DEPTH_SMALL_LAND);
            if (wrap != null) {
               valoare.setOpticalDepthSmallLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.OPTICAL_DEPTH_SMALL_LAND);
            if (wrap != null) {
               valoare.setOpticalDepthSmallLand2(wrap.findDoublePozitionData(new int[] { 2,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.OPTICAL_DEPTH_SMALL_LAND);
            if (wrap != null) {
               valoare.setOpticalDepthSmallLand3(wrap.findDoublePozitionData(new int[] { 3,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.PATH_RADIANCE_LAND);
            if (wrap != null) {
               valoare.setPathRadianceLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.PATH_RADIANCE_LAND);
            if (wrap != null) {
               valoare.setPathRadianceLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.QUALITYWEIGHT_CRITICAL_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setQualityWeightCriticalReflectanceLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.QUALITYWEIGHT_CRITICAL_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setQualityWeightCriticalReflectanceLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.QUALITYWEIGHT_PATH_RADIANCE_LAND);
            if (wrap != null) {
               valoare.setQualityWeightPathRadianceLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.QUALITYWEIGHT_PATH_RADIANCE_LAND);
            if (wrap != null) {
               valoare.setQualityWeightPathRadianceLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SCAN_START_TIME);
            valoare.setScanStartTime(wrap.findDoublePozitionData(pozSearch));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SCATTERING_ANGLE);
            valoare.setScatteringAngle(wrap.findDoublePozitionData(pozSearch));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SENSOR_AZIMUTH);
            valoare.setSensorAzimuth(wrap.findDoublePozitionData(pozSearch));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SENSOR_ZENITH);
            valoare.setSensorZenith(wrap.findDoublePozitionData(pozSearch));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SOLAR_AZIMUTH);
            valoare.setSolarAzimuth(wrap.findDoublePozitionData(pozSearch));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SOLAR_ZENITH);
            valoare.setSolarZenith(wrap.findDoublePozitionData(pozSearch));
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SOLUTION_INDEX_OCEAN_LARGE);
            valoare.setSolutionIndexOceanLarge0(wrap.findDoublePozitionData(new int[] { 0,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SOLUTION_INDEX_OCEAN_LARGE);
            valoare.setSolutionIndexOceanLarge1(wrap.findDoublePozitionData(new int[] { 1,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         // cazul 3=2
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SOLUTION_INDEX_OCEAN_SMALL);
            valoare.setSolutionIndexOceanSmall0(wrap.findDoublePozitionData(new int[] { 0,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SOLUTION_INDEX_OCEAN_SMALL);
            valoare.setSolutionIndexOceanSmall1(wrap.findDoublePozitionData(new int[] { 1,
                  valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
         }
         // cazul 3=3
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.STANDARD_DEVIATION_REFLECTANCE_LAND_ALL);
            if (wrap != null) {
               valoare.setStandardDeviationReflectanceLandAll0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.STANDARD_DEVIATION_REFLECTANCE_LAND_ALL);
            if (wrap != null) {
               valoare.setStandardDeviationReflectanceLandAll1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.STANDARD_DEVIATION_REFLECTANCE_LAND_ALL);
            if (wrap != null) {
               valoare.setStandardDeviationReflectanceLandAll2(wrap.findDoublePozitionData(new int[] { 2,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // cazul 3=3
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SURFACE_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setSurfaceReflectanceLand0(wrap.findDoublePozitionData(new int[] { 0,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SURFACE_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setSurfaceReflectanceLand1(wrap.findDoublePozitionData(new int[] { 1,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         {
            H4DSWrapper wrap = sheeturi.get(Mod04Constants.SURFACE_REFLECTANCE_LAND);
            if (wrap != null) {
               valoare.setSurfaceReflectanceLand2(wrap.findDoublePozitionData(new int[] { 2,
                     valoare.getPozitieAlong().intValue(), valoare.getPozitieAcross().intValue() }));
            }
         }
         // Stop
      }
   }

   private static H4DSWrapper extractFieldSheet(H4SDS h4sds, String field1) throws HDFException {
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

   private DateTime findTimp(String nume) throws Exception {
      if (hasPosition(nume)) {
         return valori.get(nume).getTimp();
      }
      return null;
   }
}
