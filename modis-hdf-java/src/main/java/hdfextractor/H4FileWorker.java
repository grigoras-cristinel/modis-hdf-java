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
import ncsa.hdf.object.h4.H4File;
import ncsa.hdf.object.h4.H4Group;
import ncsa.hdf.object.h4.H4SDS;
import ncsa.hdf.object.h4.H4Vdata;

/**
 * Trebuie sa inglobeze munca pe un fisier
 * 
 * @author Grig
 * 
 */
public class H4FileWorker implements HDFFileWorker {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private static int cellAcross = 270;
   @SuppressWarnings("unused")
   private static int cellAlong = 406;
   H4File file;
   TreeMap<String, double[]> puncte;
   TreeMap<String, Long> indexPozitii;
   TreeMap<String, DateTime> valoriTimp;
   H4SDS totalOzon;
   TreeMap<String, Double> valoriOzon;
   short totalOzonFillValue;
   double totalOzonScaleFactor;
   H4SDS temperaturaSuprafata;
   TreeMap<String, Double> valoriTemperaturaSuprafata;
   short temperaturaSuprafataFillValue;
   double temperaturaSuprafataScaleFactor;
   H4SDS presiuneSuprafata;
   TreeMap<String, Double> valoriPresiuneSuprafata;
   short presiuneSuprafataFillValue;
   double presiuneSuprafataScaleFactor;
   H4SDS timpScanare;
   H4SDS latitudine;
   H4SDS longitudine;

   public TreeMap<String, double[]> getPuncte() {
      return puncte;
   }

   public void setPuncte(TreeMap<String, double[]> pozitii) {
      this.puncte = pozitii;
   }

   public H4FileWorker(H4File h4file) throws Exception {
      super();
      this.file = h4file;
      file.open();
   }

   public H4FileWorker(String h4fileLocation) throws Exception {
      file = new H4File(h4fileLocation, FileFormat.READ);
      file.open();
   }

   public H4FileWorker(String canonicalPath, TreeMap<String, double[]> statii) throws Exception {
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
      H4Group testGroup = (H4Group) file.get("/mod07/Geolocation Fields");
      Iterator<HObject> it = testGroup.getMemberList().iterator();
      while (it.hasNext()) {
         HObject hObject = it.next();
         if (hObject instanceof H4SDS) {
            H4SDS h4sds = (H4SDS) hObject;
            if ("Latitude".equals(h4sds.getName())) {
               latitudine = h4sds;
            }
            if ("Longitude".equals(h4sds.getName())) {
               longitudine = h4sds;
            }
         }
      }
      H4Group dataFieldsGrup = (H4Group) file.get("/mod07/Data Fields");
      Iterator<HObject> itDataFields = dataFieldsGrup.getMemberList().iterator();
      while (itDataFields.hasNext()) {
         HObject hObject = itDataFields.next();
         if (hObject instanceof H4SDS) {
            H4SDS h4sds = (H4SDS) hObject;
            if ("Total_Ozone".equals(h4sds.getName())) {
               totalOzon = h4sds;
               for (int f1 = 0; f1 < h4sds.getMetadata().size(); f1++) {
                  Attribute attr = (Attribute) h4sds.getMetadata().get(f1);
                  if (attr.getName().equals("_FillValue")) {
                     short[] vals = (short[]) attr.getValue();
                     totalOzonFillValue = vals[0];
                  }
                  if (attr.getName().equals("scale_factor")) {
                     double[] vals = (double[]) attr.getValue();
                     totalOzonScaleFactor = vals[0];
                  }
               }
            }
            if ("Scan_Start_Time".equals(h4sds.getName())) {
               timpScanare = h4sds;
            }
            if ("Surface_Temperature".equals(h4sds.getName())) {
               temperaturaSuprafata = h4sds;
               for (int f1 = 0; f1 < h4sds.getMetadata().size(); f1++) {
                  Attribute attr = (Attribute) h4sds.getMetadata().get(f1);
                  if (attr.getName().equals("_FillValue")) {
                     short[] vals = (short[]) attr.getValue();
                     temperaturaSuprafataFillValue = vals[0];
                  }
                  if (attr.getName().equals("scale_factor")) {
                     double[] vals = (double[]) attr.getValue();
                     temperaturaSuprafataScaleFactor = vals[0];
                  }
               }
            }
            if ("Surface_Pressure".equals(h4sds.getName())) {
               presiuneSuprafata = h4sds;
               for (int f1 = 0; f1 < h4sds.getMetadata().size(); f1++) {
                  Attribute attr = (Attribute) h4sds.getMetadata().get(f1);
                  if (attr.getName().equals("_FillValue")) {
                     short[] vals = (short[]) attr.getValue();
                     presiuneSuprafataFillValue = vals[0];
                  }
                  if (attr.getName().equals("scale_factor")) {
                     double[] vals = (double[]) attr.getValue();
                     presiuneSuprafataScaleFactor = vals[0];
                  }
               }
            }
         }
      }
      indexPozitii = new TreeMap<String, Long>();
      valoriOzon = new TreeMap<String, Double>();
      valoriTimp = new TreeMap<String, DateTime>();
      valoriTemperaturaSuprafata = new TreeMap<String, Double>();
      valoriPresiuneSuprafata = new TreeMap<String, Double>();
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
         long yPozLat = pozSearch / cellAcross;
         long xPozLat = pozSearch % cellAcross;
         // System.out.println("Pozfound:" + pozSearch + " F1:" + latf1 +
         // " DLA:"
         // + deltaLatF + " DLO:" + deltaLonF + " X:" + xPozLat + " Y:"
         // + yPozLat);
         indexPozitii.put(numePozitie, pozSearch);
         DateTime value = null;
         double[] vals = (double[]) timpScanare.getData();
         DateTime retval = new DateTime();
         retval = retval.withDate(1993, 1, 1).withTime(0, 0, 0, 0);
         value = retval.plusSeconds((int) vals[(int) pozSearch]);
         valoriTimp.put(numePozitie, value);
         {
            short[] valsh = (short[]) totalOzon.getData();
            short gasita = valsh[(int) pozSearch];
            if (gasita != totalOzonFillValue) {
               valoriOzon.put(numePozitie, gasita * totalOzonScaleFactor);
            } else {
               valoriOzon.put(numePozitie, null);
            }
         }
         {
            short[] valsh = (short[]) temperaturaSuprafata.getData();
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

   @Override
   public OmiMeasurementQualityFlags getQualityFlags(String nume) throws Exception {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public OmiProcessingQualityFlags getProcessingQualityFlags(String nume) throws Exception {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Double findCloudFraction(String nume) throws Exception {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Float findViewingZenithAngle(String siteName) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Integer getLatime() {
      return cellAcross;
   }
}
