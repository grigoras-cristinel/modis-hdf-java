package hdfextractor;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import hdfextractor.ozon.OmiMeasurementQualityFlags;
import hdfextractor.ozon.OmiProcessingQualityFlags;
import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.FileFormat;
import ncsa.hdf.object.HObject;
import ncsa.hdf.object.h4.H4Vdata;
import ncsa.hdf.object.h5.H5File;
import ncsa.hdf.object.h5.H5Group;
import ncsa.hdf.object.h5.H5ScalarDS;

/**
 * Trebuie sa inglobeze munca pe un fisier
 * 
 * @author Grig
 * 
 */
public class H5FileWorker implements HDFFileWorker {

   /**
   * 
   */
   private static final long serialVersionUID = 7946061313120182163L;
   H5File file;
   Integer width;
   Integer height;
   TreeMap<String, double[]> puncte;
   TreeMap<String, Long> indexPozitii;
   TreeMap<String, DateTime> valoriTimp;
   H5ScalarDS totalOzon;
   TreeMap<String, Double> valoriOzon;
   float totalOzonFillValue;
   double totalOzonScaleFactor;
   H5ScalarDS temperaturaSuprafata;
   TreeMap<String, Double> valoriTemperaturaSuprafata;
   float temperaturaSuprafataFillValue;
   double temperaturaSuprafataScaleFactor;
   H5ScalarDS presiuneSuprafata;
   H5ScalarDS omiProcessingQuality;
   short omiProcessingQualityFillValue;
   TreeMap<String, OmiProcessingQualityFlags> valoriPQuality;
   H5ScalarDS omiMeasurementQualityFlags;
   TreeMap<String, OmiMeasurementQualityFlags> valoriMQuality;
   byte omiMeasurementQualityFlagsFillValue;
   TreeMap<String, Double> valoriPresiuneSuprafata;
   float presiuneSuprafataFillValue;
   double presiuneSuprafataScaleFactor;
   H5ScalarDS timpScanare;
   H5ScalarDS latitudine;
   H5ScalarDS longitudine;
   H5ScalarDS cloudFraction;
   H5ScalarDS viewingZenithAngle;
   TreeMap<String, Double> valoriCloudFraction;
   TreeMap<String, Float> valoriViewingZenithAngle;
   byte fillValueCloudFraction;
   double scaleFactorCloudFraction;

   public TreeMap<String, double[]> getPuncte() {
      return puncte;
   }

   public void setPuncte(TreeMap<String, double[]> pozitii) {
      this.puncte = pozitii;
   }

   public H5FileWorker(H5File h4file) throws Exception {
      super();
      this.file = h4file;
      file.open();
   }

   public H5FileWorker(String h4fileLocation) throws Exception {
      file = new H5File(h4fileLocation, FileFormat.READ);
      file.open();
   }

   public H5FileWorker(String canonicalPath, TreeMap<String, double[]> statii) throws Exception {
      this(canonicalPath);
      puncte = statii;
      parseFile();
   }

   @Override
   public boolean hasPosition(String nume) throws Exception {
      if (puncte == null || !puncte.containsKey(nume)) {
         return false;
      }
      if (indexPozitii != null) {
         if (indexPozitii.get(nume) != null) {
            return true;
         }
         return false;
      }
      parseFile();
      if (indexPozitii != null) {
         if (indexPozitii.get(nume) != null) {
            return true;
         }
      }
      return false;
   }

   private void parseFile() throws Exception, OutOfMemoryError {
      H5Group testGroup = (H5Group) file.get("/HDFEOS/SWATHS/ColumnAmountO3/Geolocation Fields");
      Iterator<HObject> it = testGroup.getMemberList().iterator();
      while (it.hasNext()) {
         HObject hObject = it.next();
         if (hObject instanceof H5ScalarDS) {
            H5ScalarDS h4sds = (H5ScalarDS) hObject;
            if ("Latitude".equals(h4sds.getName())) {
               latitudine = h4sds;
            }
            if ("Longitude".equals(h4sds.getName())) {
               longitudine = h4sds;
            }
            if ("Time".equals(h4sds.getName())) {
               timpScanare = h4sds;
            }
            if ("ViewingZenithAngle".equals(h4sds.getName())) {
               viewingZenithAngle = h4sds;
            }
         }
      }
      H5Group dataFieldsGrup = (H5Group) file.get("/HDFEOS/SWATHS/ColumnAmountO3/Data Fields");
      Iterator<HObject> itDataFields = dataFieldsGrup.getMemberList().iterator();
      while (itDataFields.hasNext()) {
         HObject hObject = itDataFields.next();
         if (hObject instanceof H5ScalarDS) {
            H5ScalarDS h5sds = (H5ScalarDS) hObject;
            if ("ColumnAmountO3".equals(h5sds.getName())) {
               totalOzon = h5sds;
               for (int f1 = 0; f1 < h5sds.getMetadata().size(); f1++) {
                  Attribute attr = (Attribute) h5sds.getMetadata().get(f1);
                  if (attr.getName().equals("MissingValue")) {
                     float[] vals = (float[]) attr.getValue();
                     totalOzonFillValue = vals[0];
                  }
                  if (attr.getName().equals("ScaleFactor")) {
                     double[] vals = (double[]) attr.getValue();
                     totalOzonScaleFactor = vals[0];
                  }
               }
            }
            if ("EffectiveTemperature".equals(h5sds.getName())) {
               temperaturaSuprafata = h5sds;
               for (int f1 = 0; f1 < h5sds.getMetadata().size(); f1++) {
                  Attribute attr = (Attribute) h5sds.getMetadata().get(f1);
                  if (attr.getName().equals("MissingValue")) {
                     byte[] vals = (byte[]) attr.getValue();
                     temperaturaSuprafataFillValue = vals[0];
                  }
                  if (attr.getName().equals("ScaleFactor")) {
                     double[] vals = (double[]) attr.getValue();
                     temperaturaSuprafataScaleFactor = vals[0];
                  }
               }
            }
            if ("TerrainPressure".equals(h5sds.getName())) {
               presiuneSuprafata = h5sds;
               for (int f1 = 0; f1 < h5sds.getMetadata().size(); f1++) {
                  Attribute attr = (Attribute) h5sds.getMetadata().get(f1);
                  if (attr.getName().equals("MissingValue")) {
                     short[] vals = (short[]) attr.getValue();
                     presiuneSuprafataFillValue = vals[0];
                  }
                  if (attr.getName().equals("ScaleFactor")) {
                     double[] vals = (double[]) attr.getValue();
                     presiuneSuprafataScaleFactor = vals[0];
                  }
               }
            }
            if ("MeasurementQualityFlags".equals(h5sds.getName())) {
               omiMeasurementQualityFlags = h5sds;
               for (int f1 = 0; f1 < h5sds.getMetadata().size(); f1++) {
                  Attribute attr = (Attribute) h5sds.getMetadata().get(f1);
                  if (attr.getName().equals("MissingValue")) {
                     byte[] vals = (byte[]) attr.getValue();
                     omiMeasurementQualityFlagsFillValue = vals[0];
                  }
               }
            }
            if ("CloudFraction".equals(h5sds.getName())) {
               cloudFraction = h5sds;
               for (int f1 = 0; f1 < h5sds.getMetadata().size(); f1++) {
                  Attribute attr = (Attribute) h5sds.getMetadata().get(f1);
                  if (attr.getName().equals("MissingValue")) {
                     byte[] vals = (byte[]) attr.getValue();
                     fillValueCloudFraction = vals[0];
                  }
                  if (attr.getName().equals("ScaleFactor")) {
                     double[] vals = (double[]) attr.getValue();
                     scaleFactorCloudFraction = vals[0];
                  }
               }
            }
            if ("ProcessingQualityFlags".equals(h5sds.getName())) {
               omiProcessingQuality = h5sds;
               for (int f1 = 0; f1 < h5sds.getMetadata().size(); f1++) {
                  Attribute attr = (Attribute) h5sds.getMetadata().get(f1);
                  if (attr.getName().equals("MissingValue")) {
                     short[] vals = (short[]) attr.getValue();
                     omiProcessingQualityFillValue = vals[0];
                  }
               }
            }
         }
      }
      indexPozitii = new TreeMap<String, Long>();
      valoriOzon = new TreeMap<String, Double>();
      valoriCloudFraction = new TreeMap<String, Double>();
      valoriPQuality = new TreeMap<String, OmiProcessingQualityFlags>();
      valoriMQuality = new TreeMap<String, OmiMeasurementQualityFlags>();
      valoriTimp = new TreeMap<String, DateTime>();
      valoriTemperaturaSuprafata = new TreeMap<String, Double>();
      valoriPresiuneSuprafata = new TreeMap<String, Double>();
      valoriViewingZenithAngle = new TreeMap<>();
      for (Entry<String, double[]> oPozitie : puncte.entrySet()) {
         String numePozitie = oPozitie.getKey();
         double coord[] = oPozitie.getValue();
         double latit = coord[0];
         double longit = coord[1];
         float[] lats = (float[]) latitudine.getData();
         float[] longs = (float[]) longitudine.getData();
         long pozSearch = 0;
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
                  }
               }
            }
         }
         // System.out.println("Pozfound:" + pozSearch );
         // " DLA:"
         // + deltaLatF + " DLO:" + deltaLonF + " X:" + xPozLat + " Y:"
         // + yPozLat);
         indexPozitii.put(numePozitie, pozSearch);
         float[] valsh0 = (float[]) totalOzon.getData();
         DateTime value = null;
         double[] vals = (double[]) timpScanare.getData();
         width = valsh0.length / vals.length;
         // System.out.println("tscan"+vals.length+" valsh:"+valsh0.length);
         DateTime retval = new DateTime().withZone(DateTimeZone.UTC);
         retval = retval.withDate(1993, 1, 1).withTime(0, 0, 0, 0);
         int indexTimp = (int) (pozSearch / width);
         // System.out.println("indexTimp="+indexTimp);
         value = retval.plusSeconds((int) vals[indexTimp]);
         // System.out.println(""+value.toString());
         valoriTimp.put(numePozitie, value);
         Float vza = ((float[]) viewingZenithAngle.getData())[(int) pozSearch];
         valoriViewingZenithAngle.put(numePozitie, vza);
         {
            byte[] valsh = (byte[]) omiMeasurementQualityFlags.getData();
            valoriMQuality.put(numePozitie, new OmiMeasurementQualityFlags(valsh[indexTimp]));
         }
         {
            float[] valsh = (float[]) totalOzon.getData();
            float gasita = valsh[(int) pozSearch];
            if (gasita != totalOzonFillValue) {
               valoriOzon.put(numePozitie, gasita * totalOzonScaleFactor);
            } else {
               valoriOzon.put(numePozitie, null);
            }
         }
         {
            byte[] valsh = (byte[]) cloudFraction.getData();
            byte gasita = valsh[(int) pozSearch];
            if (gasita != fillValueCloudFraction) {
               valoriCloudFraction.put(numePozitie, gasita * scaleFactorCloudFraction);
            } else {
               valoriCloudFraction.put(numePozitie, null);
            }
         }
         {
            short[] valsh = (short[]) omiProcessingQuality.getData();
            float gasita = valsh[(int) pozSearch];
            if (gasita != omiProcessingQualityFillValue) {
               valoriPQuality.put(numePozitie, new OmiProcessingQualityFlags((int) gasita));
            } else {
               valoriOzon.put(numePozitie, null);
            }
         }
         {
            byte[] valsh = (byte[]) temperaturaSuprafata.getData();
            short gasita = valsh[(int) pozSearch];
            if (gasita != temperaturaSuprafataFillValue) {
               valoriTemperaturaSuprafata.put(numePozitie, gasita * temperaturaSuprafataScaleFactor);
            } else {
               valoriTemperaturaSuprafata.put(numePozitie, null);
            }
         }
         {
            short[] valsh = (short[]) presiuneSuprafata.getData();
            short gasita = valsh[(int) pozSearch];
            if (gasita != presiuneSuprafataFillValue) {
               valoriPresiuneSuprafata.put(numePozitie, gasita * presiuneSuprafataScaleFactor);
            } else {
               valoriPresiuneSuprafata.put(numePozitie, null);
            }
         }
      }
   }

   public void infoFileGrups() throws Exception {
      @SuppressWarnings("rawtypes")
      Enumeration nodes = file.getRootNode().children();
      while (nodes.hasMoreElements()) {
         DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes.nextElement();
         Object obj = node.getUserObject();
         System.out.println("obj" + obj);
         if (obj instanceof H4Vdata) {
            H4Vdata vd = (H4Vdata) obj;
            System.out.println(vd.getName() + "" + vd.getData());
         } else if (obj instanceof H5Group) {
            H5Group grp = (H5Group) obj;
            System.out.println(grp.getName() + " " + grp.getMetadata().size());
            Iterator<HObject> it = grp.getMemberList().iterator();
            while (it.hasNext()) {
               HObject hObject = it.next();
            }
         }
      }
      H5ScalarDS hObject = (H5ScalarDS) file.get("/HDFEOS/SWATHS/ColumnAmountO3/Geolocation Fields/Longitude");
      descrieGrup((H5Group) file.get("/HDFEOS"));
      descrieGrup((H5Group) file.get("/HDFEOS/SWATHS"));
      descrieGrup((H5Group) file.get("/HDFEOS/SWATHS/ColumnAmountO3"));
      descrieGrup((H5Group) file.get("/HDFEOS/SWATHS/ColumnAmountO3/Data Fields"));
      descrieGrup((H5Group) file.get("/HDFEOS/SWATHS/ColumnAmountO3/Geolocation Fields"));
   }

   private void descrieGrup(H5Group testGroup) {
      System.out.println("testGroup:" + testGroup.getFullName() + " size=" + testGroup.getMemberList().size());
      Iterator<HObject> it = testGroup.getMemberList().iterator();
      while (it.hasNext()) {
         HObject hObject = it.next();
         System.out.println(hObject.getFullName() + " type " + hObject.getClass().getName());
      }
   }

   @Override
   public void close() {
      try {
         file.close();
      } catch (Exception e) {
      }
   }

   @Override
   public Long findPozitie(String nume) throws Exception {
      if (hasPosition(nume)) {
         return indexPozitii.get(nume);
      }
      return null;
   }

   /**
    * @param nume
    * @return null daca nu gaseste
    * @throws Exception
    */
   @Override
   public Double findOzon(String nume) throws Exception {
      if (hasPosition(nume)) {
         return valoriOzon.get(nume);
      }
      return null;
   }

   @Override
   public Double findTemperaturaSuprafata(String nume) throws Exception {
      if (hasPosition(nume)) {
         return valoriTemperaturaSuprafata.get(nume);
      }
      return null;
   }

   @Override
   public Double findPresiuneSuprafata(String nume) throws Exception {
      if (hasPosition(nume)) {
         return valoriPresiuneSuprafata.get(nume);
      }
      return null;
   }

   @Override
   public DateTime findTimp(String nume) throws Exception {
      if (hasPosition(nume)) {
         return valoriTimp.get(nume);
      }
      return null;
   }

   @Override
   public DateTime findTimpMediu() throws Exception {
      long val = 0;
      for (Entry<String, double[]> st : puncte.entrySet()) {
         if (hasPosition(st.getKey())) {
            DateTime timpGasit = valoriTimp.get(st.getKey());
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

   @Override
   public boolean hasOzon(String nume) throws Exception {
      if (hasPosition(nume)) {
         return valoriOzon.get(nume) != null;
      }
      return false;
   }

   public TreeMap<String, Object> extractMetadata(H5File testFile) throws Exception {
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

   @Override
   public OmiMeasurementQualityFlags getQualityFlags(String nume) throws Exception {
      return valoriMQuality.get(nume);
   }

   @Override
   public OmiProcessingQualityFlags getProcessingQualityFlags(String nume) throws Exception {
      return valoriPQuality.get(nume);
   }

   @Override
   public Double findCloudFraction(String nume) throws Exception {
      if (hasPosition(nume)) {
         return valoriCloudFraction.get(nume);
      }
      return null;
   }

   @Override
   public Float findViewingZenithAngle(String siteName) throws Exception {
      if (hasPosition(siteName)) {
         return valoriViewingZenithAngle.get(siteName);
      }
      return null;
   }

   @Override
   public Integer getLatime() {
      return width;
   }
}
