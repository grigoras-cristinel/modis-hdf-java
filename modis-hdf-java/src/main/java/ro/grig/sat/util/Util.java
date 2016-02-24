package ro.grig.sat.util;

import hdfextractor.GenereazaJavaDinStructuraHdfMOD06;
import hdfextractor.GenereazaJavaDinStructuraHdfMOD06.DimDesc;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import ncsa.hdf.hdflib.HDFException;
import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.h4.H4File;
import ncsa.hdf.object.h4.H4Group;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.UTMRef;

public class Util {
   /**
    * Logger for this class
    */
   private static final Logger logger = Logger.getLogger(Util.class);

   public class UnPoit {
      public float latidu;
      public float longit;
      public long pozitie = -1;
      public boolean gasit = false;
      public float mindist;
   }

   public static float metersToDecimalDegrees(double meters, double latitude) {
      return (float) (meters / (111.32 * 1000 * Math.cos(latitude * (Math.PI / 180))));
   }

   public static String printMatrix(float[][] matrice) {
      StringBuilder stb = new StringBuilder();
      int rows = matrice.length;
      int columns = matrice[0].length;
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < columns; j++) {
            stb.append(String.format("%09.6f ", matrice[i][j]));
         }
         stb.append("\n");
      }
      return stb.toString();
   }

   public static String printMatrix(float[][] matrice, int limit) {
      StringBuilder stb = new StringBuilder();
      int rows = matrice.length;
      int columns = matrice[0].length;
      for (int i = 0; i < rows && i < limit; i++) {
         for (int j = 0; j < columns && j < limit; j++) {
            stb.append(String.format("%09.6f ", matrice[i][j]));
         }
         stb.append("\n");
      }
      return stb.toString();
   }

   public static String printMatrix(float[] vector, int width) {
      StringBuilder stb = new StringBuilder();
      int rows = vector.length / width;
      int columns = width;
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < columns; j++) {
            stb.append(String.format("%09.6f ", vector[i * width + j]));
         }
         stb.append("\n");
      }
      return stb.toString();
   }

   public static float[][] buildMatrix(float[] lats, int cellAlong, int cellAcross) {
      // formeaza un array de 5 ori mai mare
      float[][] inarr = new float[cellAlong][cellAcross];
      for (int x = 0; x < cellAcross; x++) {
         for (int y = 0; y < cellAlong; y++) {
            int mind = (y * cellAcross) + x;
            inarr[y][x] = lats[mind];
         }
      }
      return inarr;
   }

   public static float[][] find1KmDecDegree(float[] lats, float[] longs) {
      float[][] retval = new float[lats.length][2];
      // CoordinatesConverter<LatLong, UTM> lalongtoutm =
      for (int i = 0; i < lats.length; i++) {
         float longit = longs[i];
         float lat = longs[i];
         LatLng pt0 = new LatLng(lat, longit);
         UTMRef utm0 = pt0.toUTMRef();
         UTMRef utm1 = new UTMRef(utm0.getEasting() + 1000, utm0.getNorthing() + 1000, utm0.getLatZone(),
               utm0.getLngZone());
         LatLng pt1 = utm1.toLatLng();
         float deltaLat = (float) Math.abs(pt1.getLat() - pt0.getLat());
         float deltaLon = (float) Math.abs(pt1.getLng() - pt0.getLng());
         retval[i][0] = deltaLat;
         retval[i][1] = deltaLon;
      }
      return retval;
   }

   public static double[][] find10KmDecDegree(double[] lats, double[] longs) {
      double[][] retval = new double[lats.length][2];
      // CoordinatesConverter<LatLong, UTM> lalongtoutm =
      for (int i = 0; i < lats.length; i++) {
         double longit = longs[i];
         double lat = longs[i];
         LatLng pt0 = new LatLng(lat, longit);
         UTMRef utm0 = pt0.toUTMRef();
         UTMRef utm1 = new UTMRef(utm0.getEasting() + 10000, utm0.getNorthing() + 10000, utm0.getLatZone(),
               utm0.getLngZone());
         LatLng pt1 = utm1.toLatLng();
         double deltaLat = Math.abs(pt1.getLat() - pt0.getLat());
         double deltaLon = Math.abs(pt1.getLng() - pt0.getLng());
         retval[i][0] = deltaLat;
         retval[i][1] = deltaLon;
      }
      return retval;
   }

   public static float[][] find500mDecDegree(float[] lats, float[] longs) {
      float[][] retval = new float[lats.length][2];
      // UTM last = null;
      for (int i = 0; i < lats.length; i++) {
         float longit = longs[i];
         float lat = longs[i];
         LatLng pt0 = new LatLng(lat, longit);
         UTMRef utm0 = pt0.toUTMRef();
         UTMRef utm1 = new UTMRef(utm0.getEasting() + 500, utm0.getNorthing() + 500, utm0.getLatZone(),
               utm0.getLngZone());
         LatLng pt1 = utm1.toLatLng();
         float deltaLat = (float) Math.abs(pt1.getLat() - pt0.getLat());
         float deltaLon = (float) Math.abs(pt1.getLng() - pt0.getLng());
         retval[i][0] = deltaLat;
         retval[i][1] = deltaLon;
      }
      return retval;
   }

   /**
    * @param lats
    * @param longs
    * @param cellAcrosInitial
    * @param cellAcrossFinal
    * @param cellAlongFinal
    * @return [0:1][v]
    */
   public static float[][] buildLats1km(float[] lats, float[] longs, int cellAcrosInitial, int cellAcrossFinal,
         int cellAlongFinal) {
      if (logger.isInfoEnabled()) {
         logger.info("buildLats"
               + lats.length
               + ":"
               + longs.length
               + " Parametri - cellAcrosInitial=" + cellAcrosInitial + ", cellAcrossFinal=" + cellAcrossFinal + ", cellAlongFinal=" + cellAlongFinal); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
      // float[][] arr5 = Util.buildMatrix(lats, cellAlong, cellAcross);
      // System.out.println(Util.printMatrix(arr5, 4));
      float[][] retvalMod = new float[2][cellAlongFinal * cellAcrossFinal];
      float[][] meter = Util.find1KmDecDegree(lats, longs);
      // System.out.println(Util.printMatrix(arr5));
      for (int p = 0; p < lats.length; p++) {
         int yPozz = p / cellAcrosInitial;
         long xPoz = p % cellAcrosInitial;
         // System.out.println(" val line col " + yPoz + " " + xPoz + " " +
         // lats[p]);
         for (int s = 0; s < 5; s++) {
            for (int u = 0; u < 5; u++) {
               retvalMod[0][(yPozz * 5 + u) * cellAcrossFinal + (p % cellAcrosInitial) * 5 + s] = lats[p] + (2 - u)
                     * meter[p][0];
               retvalMod[1][(yPozz * 5 + s) * cellAcrossFinal + (p % cellAcrosInitial) * 5 + u] = longs[p] + (-2 + u)
                     * meter[p][1];
            }
         }
      }
      // float[][] arr1 = Util.buildMatrix(retvalMod[1], cellAlong1km,
      // cellAcross1km);
      // float[][] arr2 = Util.buildMatrix(retvalMod[0], cellAlong1km,
      // cellAcross1km);
      // System.out.println(Util.printMatrix(arr1, 10));
      // System.out.println(Util.printMatrix(arr2, 10));
      return retvalMod;
   }

   public static float[][] buildLats500(float[] lats, float[] longs, int cellAcrosInitial, int cellAcrossFinal,
         int cellAlongFinal) {
      // float[][] arr5 = Util.buildMatrix(lats, cellAlong, cellAcross);
      // System.out.println(Util.printMatrix(arr5, 4));
      float[][] retvalMod = new float[2][cellAlongFinal * cellAcrossFinal];
      float[][] meter = Util.find500mDecDegree(lats, longs);
      // System.out.println(Util.printMatrix(arr5));
      for (int p = 0; p < lats.length; p++) {
         int yPozz = p / cellAcrosInitial;
         long xPoz = p % cellAcrosInitial;
         // System.out.println(" val line col " + yPoz + " " + xPoz + " " +
         // lats[p]);
         for (int s = 0; s < 5; s++) {
            for (int u = 0; u < 5; u++) {
               retvalMod[0][(yPozz * 5 + u) * cellAcrossFinal + (p % cellAcrosInitial) * 5 + s] = lats[p] + (2 - u)
                     * meter[p][0];
               retvalMod[1][(yPozz * 5 + s) * cellAcrossFinal + (p % cellAcrosInitial) * 5 + u] = longs[p] + (-2 + u)
                     * meter[p][1];
            }
         }
      }
      // float[][] arr1 = Util.buildMatrix(retvalMod[1], cellAlong1km,
      // cellAcross1km);
      // float[][] arr2 = Util.buildMatrix(retvalMod[0], cellAlong1km,
      // cellAcross1km);
      // System.out.println(Util.printMatrix(arr1, 10));
      // System.out.println(Util.printMatrix(arr2, 10));
      return retvalMod;
   }

   public static Util.UnPoit extractNearPoint(float[] lats, float[] longs, double[] coord, float marjaDegre) {
      double latit = coord[0];
      double longit = coord[1];
      Util.UnPoit findPoint = new Util().new UnPoit();
      double mindist = 100;
      for (int i = 0; i < lats.length - 1; i++) {
         float lat = lats[i];
         float lon = longs[i];
         double dist = Math.sqrt((lat - latit) * (lat - latit) + (lon - longit) * (lon - longit));
         double min = Math.min(mindist, dist);
         if (min != mindist) {
            mindist = min;
            findPoint.pozitie = i;
            findPoint.latidu = lat;
            findPoint.longit = lon;
         }
      }
      if (mindist < marjaDegre) {
         findPoint.gasit = true;
      }
      return findPoint;
   }

   public static void printCoodinateInCsv(PrintWriter pw1, float[][] blats1km) {
      float[] lats1 = blats1km[0];
      float[] longs1 = blats1km[1];
      for (int i = 0; i < longs1.length; i++) {
         pw1.printf("%15.12f,%15.12f\n", lats1[i], longs1[i]);
      }
   }

   public static void printCoodinateInCsv(PrintWriter pw1, float[] lats, float[] longs) {
      for (int i = 0; i < longs.length; i++) {
         pw1.printf("%15.12f,%15.12f\n", lats[i], longs[i]);
      }
   }

   public static TreeMap<String, Integer> dimensionFieldsFromDescription(H4File hfile) throws FileNotFoundException,
         HDFException {
      H4Group root = (H4Group) ((javax.swing.tree.DefaultMutableTreeNode) hfile.getRootNode()).getUserObject();
      String swatStructure = ((String[]) ((Attribute) root.getMetadata().get(1)).getValue())[0];
      Scanner fileScanner = new Scanner(swatStructure);
      TreeMap<String, Integer> dimensiuni = new TreeMap<String, Integer>();
      boolean startGrupDimensionField = false;
      boolean startDimObiect = false;
      DimDesc dimStruct = null;
      while (fileScanner.hasNextLine()) {
         String linie = fileScanner.nextLine();
         linie = linie.trim();
         if (StringUtils.equals("GROUP=Dimension", linie)) {
            startGrupDimensionField = true;
            continue;
         }
         if (startGrupDimensionField) {
            if (StringUtils.equals("END_GROUP=Dimension", linie)) {
               startGrupDimensionField = false;
               continue;
            }
            if (StringUtils.startsWith(linie, "OBJECT=")) {
               startDimObiect = true;
               dimStruct = new GenereazaJavaDinStructuraHdfMOD06().new DimDesc();
               continue;
            }
            if (startDimObiect) {
               if (StringUtils.startsWith(linie, "END_OBJECT=")) {
                  startDimObiect = false;
                  dimensiuni.put(dimStruct.dimName, dimStruct.dimValue);
                  continue;
               }
               if (StringUtils.startsWith(linie, "DimensionName=")) {
                  dimStruct.dimName = StringUtils.remove(linie.substring(14), "\"");
                  continue;
               }
               if (StringUtils.startsWith(linie, "Size=")) {
                  dimStruct.dimValue = Integer.decode(linie.substring(5));
                  continue;
               }
            }
         }
      }
      fileScanner.close();
      return dimensiuni;
   }

   /**
    * @param valori
    *           triplet, lat,lon,ceva...
    * @return
    */
   public static double[][] find500mDecDegree(ArrayList<double[]> valori) {
      double[][] retval = new double[valori.size()][2];
      // CoordinatesConverter<LatLong, UTM> lalongtoutm =
      for (int i = 0; i < retval.length; i++) {
         double longit = valori.get(i)[0];
         double lat = valori.get(i)[1];
         LatLng pt0 = new LatLng(lat, longit);
         UTMRef utm0 = pt0.toUTMRef();
         UTMRef utm1 = new UTMRef(utm0.getEasting() + 500, utm0.getNorthing() + 500, utm0.getLatZone(),
               utm0.getLngZone());
         LatLng pt1 = utm1.toLatLng();
         double deltaLat = Math.abs(pt1.getLat() - pt0.getLat());
         double deltaLon = Math.abs(pt1.getLng() - pt0.getLng());
         retval[i][0] = deltaLat;
         retval[i][1] = deltaLon;
      }
      return retval;
   }

   public static double[][] find1KmDecDegree(ArrayList<double[]> valori) {
      double[][] retval = new double[valori.size()][2];
      // CoordinatesConverter<LatLong, UTM> lalongtoutm =
      for (int i = 0; i < retval.length; i++) {
         double longit = valori.get(i)[0];
         double lat = valori.get(i)[1];
         LatLng pt0 = new LatLng(lat, longit);
         UTMRef utm0 = pt0.toUTMRef();
         UTMRef utm1 = new UTMRef(utm0.getEasting() + 1000, utm0.getNorthing() + 1000, utm0.getLatZone(),
               utm0.getLngZone());
         LatLng pt1 = utm1.toLatLng();
         double deltaLat = Math.abs(pt1.getLat() - pt0.getLat());
         double deltaLon = Math.abs(pt1.getLng() - pt0.getLng());
         retval[i][0] = deltaLat;
         retval[i][1] = deltaLon;
      }
      return retval;
   }

   public static double[][] find5KmDecDegree(ArrayList<double[]> valori) {
      double[][] retval = new double[valori.size()][2];
      // CoordinatesConverter<LatLong, UTM> lalongtoutm =
      for (int i = 0; i < retval.length; i++) {
         double longit = valori.get(i)[0];
         double lat = valori.get(i)[1];
         LatLng pt0 = new LatLng(lat, longit);
         UTMRef utm0 = pt0.toUTMRef();
         UTMRef utm1 = new UTMRef(utm0.getEasting() + 5000, utm0.getNorthing() + 5000, utm0.getLatZone(),
               utm0.getLngZone());
         LatLng pt1 = utm1.toLatLng();
         double deltaLat = Math.abs(pt1.getLat() - pt0.getLat());
         double deltaLon = Math.abs(pt1.getLng() - pt0.getLng());
         retval[i][0] = deltaLat;
         retval[i][1] = deltaLon;
      }
      return retval;
   }

   /**
    * @param valori
    *           triplet, lat,lon,ceva...
    * @return
    */
   public static double[][] find10KmDecDegree(ArrayList<double[]> valori) {
      double[][] retval = new double[valori.size()][2];
      // CoordinatesConverter<LatLong, UTM> lalongtoutm =
      for (int i = 0; i < retval.length; i++) {
         double longit = valori.get(i)[0];
         double lat = valori.get(i)[1];
         LatLng pt0 = new LatLng(lat, longit);
         UTMRef utm0 = pt0.toUTMRef();
         UTMRef utm1 = new UTMRef(utm0.getEasting() + 10000, utm0.getNorthing() + 10000, utm0.getLatZone(),
               utm0.getLngZone());
         LatLng pt1 = utm1.toLatLng();
         double deltaLat = Math.abs(pt1.getLat() - pt0.getLat());
         double deltaLon = Math.abs(pt1.getLng() - pt0.getLng());
         retval[i][0] = deltaLat;
         retval[i][1] = deltaLon;
      }
      return retval;
   }
}
