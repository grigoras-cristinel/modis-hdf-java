package ro.grig.multipoint;

import hdfextractor.Mod04TO;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * Implementeaza ciclul de lucru cu baza de date
 * 
 * @author grig
 *
 */
public class DatabaseTools implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 5400496016297846840L;
   private Connection conexiune;
   private String sqlitedb = "d:/grig/work/multipoint/sqlite/multipoint.sqlite";
   public static String sqlInsert04Puncte = "insert into puncte ( "
         + " timp, databaseKey, hdfFileName, numePozitie, pozitie, pozitieAlong, pozitieAcross, "
         + " latitudine, statie, longitudine, squarSizeM, aerosolTypeLand, angstromExponent1Ocean0, "
         + " angstromExponent1Ocean1, angstromExponent2Ocean0, angstromExponent2Ocean1, angstromExponentLand, "
         + " cloudCondensationNucleiOcean0, cloudCondensationNucleiOcean1, cloudFractionLand, "
         + " cloudFractionOcean, cloudMaskQA, correctedOpticalDepthLand0, correctedOpticalDepthLand1, "
         + " correctedOpticalDepthLand2, correctedOpticalDepthLandwav2p1, criticalReflectanceLand0, "
         + " criticalReflectanceLand1, deepBlueAerosolOpticalDepth550Land, deepBlueAerosolOpticalDepth550LandSTD, "
         + " deepBlueAerosolOpticalDepthLand0, deepBlueAerosolOpticalDepthLand1, deepBlueAerosolOpticalDepthLand2, "
         + " deepBlueAerosolOpticalDepthLandSTD0, deepBlueAerosolOpticalDepthLandSTD1, deepBlueAerosolOpticalDepthLandSTD2, "
         + " deepBlueAngstromExponentLand, deepBlueMeanReflectanceLand0, deepBlueMeanReflectanceLand1, "
         + " deepBlueMeanReflectanceLand2, deepBlueNumberPixelsUsedLand0, deepBlueNumberPixelsUsedLand1, "
         + " deepBlueNumberPixelsUsedLand2, deepBlueSingleScatteringAlbedoLand0, deepBlueSingleScatteringAlbedoLand1, "
         + " deepBlueSingleScatteringAlbedoLand2, deepBlueSurfaceReflectanceLand0, deepBlueSurfaceReflectanceLand1, "
         + " deepBlueSurfaceReflectanceLand2, effectiveRadiusOcean0, effectiveRadiusOcean1, "
         + " errorCriticalReflectanceLand0, errorCriticalReflectanceLand1, errorPathRadianceLand0, "
         + " errorPathRadianceLand1, fittingErrorLand, imageOpticalDepthLandAndOcean, leastSquaresErrorOcean0, "
         + " leastSquaresErrorOcean1, massConcentrationLand, massConcentrationOcean0, massConcentrationOcean1, "
         + " meanReflectanceLandAll0, meanReflectanceLandAll1, meanReflectanceLandAll2, numberPixelsUsedLand0, "
         + " numberPixelsUsedLand1, numberPixelsUsedOcean, opticalDepthLandAndOcean, opticalDepthRatioSmallLand, "
         + " opticalDepthRatioSmallLandAndOcean, opticalDepthRatioSmallOcean055micron0, opticalDepthRatioSmallOcean055micron1, "
         + " opticalDepthSmallLand0, opticalDepthSmallLand1, opticalDepthSmallLand2, opticalDepthSmallLand3, "
         + " pathRadianceLand0, pathRadianceLand1, qualityWeightCriticalReflectanceLand0, qualityWeightCriticalReflectanceLand1, "
         + " qualityWeightPathRadianceLand0, qualityWeightPathRadianceLand1, scanStartTime, "
         + " scatteringAngle, sensorAzimuth, sensorZenith, solarAzimuth, solarZenith, solutionIndexOceanLarge0, "
         + " solutionIndexOceanLarge1, solutionIndexOceanSmall0, solutionIndexOceanSmall1, "
         + " standardDeviationReflectanceLandAll0, standardDeviationReflectanceLandAll1, standardDeviationReflectanceLandAll2, "
         + " surfaceReflectanceLand0, surfaceReflectanceLand1, surfaceReflectanceLand2 ) values ( "
         + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
         + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
         + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
         + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

   public DatabaseTools() {
      super();
   }

   public void start() throws Exception {
      System.out.println("Connect to database:" + sqlitedb);
      conexiune = DriverManager.getConnection("jdbc:sqlite:" + sqlitedb);
   }

   public void end() throws Throwable {
      System.out.println("Close database:" + sqlitedb);
      if (conexiune != null) {
         if (!conexiune.isClosed()) {
            conexiune.close();
            conexiune = null;
         }
      }
      super.finalize();
   }

   @Override
   protected void finalize() throws Throwable {
      System.out.println("Close database:" + sqlitedb);
      if (conexiune != null) {
         if (!conexiune.isClosed()) {
            conexiune.close();
         }
      }
      super.finalize();
   }

   private void isInitialized() {
      if (conexiune == null) {
         throw new RuntimeException("Conexiune baza de date neinitializata");
      }
      try {
         if (conexiune.isClosed()) {
            throw new RuntimeException("Conexiune baza de date neinitializata");
         }
      } catch (SQLException e) {
         throw new RuntimeException("Conexiune baza de date neinitializata", e);
      }
   }

   public void createTablePuncteIfNotExist() {
      isInitialized();
      StringBuilder stb = new StringBuilder("create table if not exists puncte ( ");
      List<String> constructii = new ArrayList<String>();
      Field[] fields = Mod04TO.class.getDeclaredFields();
      for (Field field : fields) {
         if (Modifier.isStatic(field.getModifiers())) {
            continue;
         }
         if (field.isAnnotationPresent(Transient.class)) {
            continue;
         }
         String fieldConst = field.getName() + " ";
         if (field.getType().equals(DateTime.class)) {
            fieldConst += "text ";
         } else if (field.getType().equals(Double.class)) {
            fieldConst += "float ";
         } else if (field.getType().equals(Long.class) || field.getType().equals(Long.TYPE)) {
            fieldConst += "integer ";
         } else if (field.getType().equals(String.class)) {
            fieldConst += "text ";
         } else if (field.getType().equals(Byte.class)) {
            fieldConst += "text ";
         } else if (field.getType().equals(Integer.class) || field.getType().equals(Integer.TYPE)) {
            fieldConst += "integer ";
         } else {
            System.out.print("................ " + field.getType() + "........");
         }
         if (field.isAnnotationPresent(Id.class)) {
            fieldConst += " primary key not null";
         }
         if (field.isAnnotationPresent(NotNull.class)) {
            fieldConst += " NOT NULL";
         }
         constructii.add(fieldConst);
      }
      Iterator<String> it = constructii.iterator();
      while (it.hasNext()) {
         String field = (String) it.next();
         stb.append(field);
         if (it.hasNext()) {
            stb.append(", \n");
         }
      }
      stb.append(")");
      // System.out.println("String " + stb.toString());
      try {
         Statement stm = conexiune.createStatement();
         stm.executeUpdate(stb.toString());
         stm.close();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public String generateSqlInsert() {
      StringBuilder retval = new StringBuilder("\"insert into puncte ( \"+\n");
      List<String> constructii = new ArrayList<String>();
      Field[] fields = Mod04TO.class.getDeclaredFields();
      for (Field field : fields) {
         if (Modifier.isStatic(field.getModifiers())) {
            continue;
         }
         if (field.isAnnotationPresent(Transient.class)) {
            continue;
         }
         String fieldConst = field.getName();
         constructii.add(fieldConst);
      }
      Iterator<String> it = constructii.iterator();
      int lco = 0;
      while (it.hasNext()) {
         String field = (String) it.next();
         if (lco == 0) {
            retval.append("\" ");
         }
         retval.append(field);
         lco += field.length() + 3;
         if (it.hasNext()) {
            retval.append(", ");
            if (lco > 80) {
               retval.append("\"+ \n");
               lco = 0;
            }
         }
      }
      retval.append(" ) values ( \"+ \n");
      Iterator<String> it1 = constructii.iterator();
      int lcm = 0;
      while (it1.hasNext()) {
         it1.next();
         if (lcm == 0) {
            retval.append("\"");
         }
         lcm += 3;
         retval.append("?");
         if (it1.hasNext()) {
            retval.append(", ");
            if (lcm >= 80) {
               retval.append("\"+\n ");
               lcm = 0;
            }
         }
      }
      retval.append(" )\" ");
      return retval.toString();
   }

   public String generateDaoInsert() {
      StringBuilder retval = new StringBuilder("//class to set fields\n");
      Field[] fields = Mod04TO.class.getDeclaredFields();
      int i = 1;
      for (Field field : fields) {
         if (Modifier.isStatic(field.getModifiers())) {
            continue;
         }
         if (field.isAnnotationPresent(Transient.class)) {
            continue;
         }
         String fieldConst = "";
         String sqlType = "-1";
         if (field.getType().equals(DateTime.class)) {
            fieldConst = "setString(";
            sqlType = "Types.VARCHAR";
         } else if (field.getType().equals(Double.class)) {
            fieldConst = "setDouble(";
            sqlType = "Types.DOUBLE";
         } else if (field.getType().equals(Long.class) || field.getType().equals(Long.TYPE)) {
            fieldConst = "setLong(";
            sqlType = "Types.INTEGER";
         } else if (field.getType().equals(String.class)) {
            fieldConst = "setString(";
            sqlType = "Types.VARCHAR";
         } else if (field.getType().equals(Byte.class)) {
            fieldConst = "setString( ";
            sqlType = "Types.VARCHAR";
         } else if (field.getType().equals(Integer.class) || field.getType().equals(Integer.TYPE)) {
            fieldConst = "setInt(";
            sqlType = "Types.INTEGER";
         } else {
            System.out.print("................ " + field.getType() + "........");
         }
         retval.append("if(to.get" + StringUtils.capitalize(field.getName()) + "() == null ) {\n");
         retval.append("     pstm.setNull(" + i + "," + sqlType + ");\n");
         retval.append("} else {\n");
         retval.append("      pstm." + fieldConst + i + ", to.get" + StringUtils.capitalize(field.getName()) + "());\n");
         retval.append("}\n");
         i++;
      }
      return retval.toString();
   }

   public String generateToStringMock() {
      StringBuilder retval = new StringBuilder("//print function to create mock\n");
      retval.append("public Mod04TO mockData(){\n Mod04TO mock=new Mod04TO();\n");
      Field[] fields = Mod04TO.class.getDeclaredFields();
      int i = 1;
      for (Field field : fields) {
         if (Modifier.isStatic(field.getModifiers())) {
            continue;
         }
         if (field.isAnnotationPresent(Transient.class)) {
            continue;
         }
         String capField = StringUtils.capitalize(field.getName());
         String fieldConst = "";
         if (field.getType().equals(DateTime.class) || field.getType().equals(String.class)
               || field.getType().equals(Byte.class)) {
            fieldConst = "(" + field.getName() + "==null?null:" + "\"\\\"\"+" + field.getName() + "+\"\\\"\"" + ")";
         } else if (field.getType().equals(Double.class)) {
            fieldConst = field.getName();
         } else if (field.getType().equals(Long.class) || field.getType().equals(Long.TYPE)) {
            fieldConst = field.getName();
         } else if (field.getType().equals(Integer.class) || field.getType().equals(Integer.TYPE)) {
            fieldConst = field.getName();
         } else {
            System.out.print("................ " + field.getType() + "........");
         }
         retval.append("+ \"mock.set" + capField + "(\"+" + fieldConst + "+\");\\n\"\n");
         i++;
      }
      return retval.toString();
   }

   public void dropTablePuncte() {
      isInitialized();
      try {
         Statement stm = conexiune.createStatement();
         stm.executeUpdate("drop table puncte");
         stm.close();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public boolean insertMod04(Mod04TO to) {
      isInitialized();
      PreparedStatement pstm;
      try {
         pstm = conexiune.prepareStatement(sqlInsert04Puncte);
         // class to set fields
         pstm.setString(1, to.getTimp().toString("yyyyMMddHHmm"));
         pstm.setString(2, to.getDatabaseKey());
         pstm.setString(3, to.getHdfFileName());
         if (to.getNumePozitie() == null) {
            pstm.setNull(4, Types.VARCHAR);
         } else {
            pstm.setString(4, to.getNumePozitie());
         }
         if (to.getPozitie() == null) {
            pstm.setNull(5, Types.INTEGER);
         } else {
            pstm.setLong(5, to.getPozitie());
         }
         if (to.getPozitieAlong() == null) {
            pstm.setNull(6, Types.INTEGER);
         } else {
            pstm.setLong(6, to.getPozitieAlong());
         }
         if (to.getPozitieAcross() == null) {
            pstm.setNull(7, Types.INTEGER);
         } else {
            pstm.setLong(7, to.getPozitieAcross());
         }
         if (to.getLatitudine() == null) {
            pstm.setNull(8, Types.DOUBLE);
         } else {
            pstm.setDouble(8, to.getLatitudine());
         }
         if (to.getStatie() == null) {
            pstm.setNull(9, Types.VARCHAR);
         } else {
            pstm.setString(9, to.getStatie());
         }
         if (to.getLongitudine() == null) {
            pstm.setNull(10, Types.DOUBLE);
         } else {
            pstm.setDouble(10, to.getLongitudine());
         }
         pstm.setInt(11, to.getSquarSizeM());
         if (to.getAerosolTypeLand() == null) {
            pstm.setNull(12, Types.DOUBLE);
         } else {
            pstm.setDouble(12, to.getAerosolTypeLand());
         }
         if (to.getAngstromExponent1Ocean0() == null) {
            pstm.setNull(13, Types.DOUBLE);
         } else {
            pstm.setDouble(13, to.getAngstromExponent1Ocean0());
         }
         if (to.getAngstromExponent1Ocean1() == null) {
            pstm.setNull(14, Types.DOUBLE);
         } else {
            pstm.setDouble(14, to.getAngstromExponent1Ocean1());
         }
         if (to.getAngstromExponent2Ocean0() == null) {
            pstm.setNull(15, Types.DOUBLE);
         } else {
            pstm.setDouble(15, to.getAngstromExponent2Ocean0());
         }
         if (to.getAngstromExponent2Ocean1() == null) {
            pstm.setNull(16, Types.DOUBLE);
         } else {
            pstm.setDouble(16, to.getAngstromExponent2Ocean1());
         }
         if (to.getAngstromExponentLand() == null) {
            pstm.setNull(17, Types.DOUBLE);
         } else {
            pstm.setDouble(17, to.getAngstromExponentLand());
         }
         if (to.getCloudCondensationNucleiOcean0() == null) {
            pstm.setNull(18, Types.DOUBLE);
         } else {
            pstm.setDouble(18, to.getCloudCondensationNucleiOcean0());
         }
         if (to.getCloudCondensationNucleiOcean1() == null) {
            pstm.setNull(19, Types.DOUBLE);
         } else {
            pstm.setDouble(19, to.getCloudCondensationNucleiOcean1());
         }
         if (to.getCloudFractionLand() == null) {
            pstm.setNull(20, Types.DOUBLE);
         } else {
            pstm.setDouble(20, to.getCloudFractionLand());
         }
         if (to.getCloudFractionOcean() == null) {
            pstm.setNull(21, Types.DOUBLE);
         } else {
            pstm.setDouble(21, to.getCloudFractionOcean());
         }
         if (to.getCloudMaskQA() == null) {
            pstm.setNull(22, Types.VARCHAR);
         } else {
            pstm.setString(22, to.getCloudMaskQA().toString());
         }
         if (to.getCorrectedOpticalDepthLand0() == null) {
            pstm.setNull(23, Types.DOUBLE);
         } else {
            pstm.setDouble(23, to.getCorrectedOpticalDepthLand0());
         }
         if (to.getCorrectedOpticalDepthLand1() == null) {
            pstm.setNull(24, Types.DOUBLE);
         } else {
            pstm.setDouble(24, to.getCorrectedOpticalDepthLand1());
         }
         if (to.getCorrectedOpticalDepthLand2() == null) {
            pstm.setNull(25, Types.DOUBLE);
         } else {
            pstm.setDouble(25, to.getCorrectedOpticalDepthLand2());
         }
         if (to.getCorrectedOpticalDepthLandwav2p1() == null) {
            pstm.setNull(26, Types.DOUBLE);
         } else {
            pstm.setDouble(26, to.getCorrectedOpticalDepthLandwav2p1());
         }
         if (to.getCriticalReflectanceLand0() == null) {
            pstm.setNull(27, Types.DOUBLE);
         } else {
            pstm.setDouble(27, to.getCriticalReflectanceLand0());
         }
         if (to.getCriticalReflectanceLand1() == null) {
            pstm.setNull(28, Types.DOUBLE);
         } else {
            pstm.setDouble(28, to.getCriticalReflectanceLand1());
         }
         if (to.getDeepBlueAerosolOpticalDepth550Land() == null) {
            pstm.setNull(29, Types.DOUBLE);
         } else {
            pstm.setDouble(29, to.getDeepBlueAerosolOpticalDepth550Land());
         }
         if (to.getDeepBlueAerosolOpticalDepth550LandSTD() == null) {
            pstm.setNull(30, Types.DOUBLE);
         } else {
            pstm.setDouble(30, to.getDeepBlueAerosolOpticalDepth550LandSTD());
         }
         if (to.getDeepBlueAerosolOpticalDepthLand0() == null) {
            pstm.setNull(31, Types.DOUBLE);
         } else {
            pstm.setDouble(31, to.getDeepBlueAerosolOpticalDepthLand0());
         }
         if (to.getDeepBlueAerosolOpticalDepthLand1() == null) {
            pstm.setNull(32, Types.DOUBLE);
         } else {
            pstm.setDouble(32, to.getDeepBlueAerosolOpticalDepthLand1());
         }
         if (to.getDeepBlueAerosolOpticalDepthLand2() == null) {
            pstm.setNull(33, Types.DOUBLE);
         } else {
            pstm.setDouble(33, to.getDeepBlueAerosolOpticalDepthLand2());
         }
         if (to.getDeepBlueAerosolOpticalDepthLandSTD0() == null) {
            pstm.setNull(34, Types.DOUBLE);
         } else {
            pstm.setDouble(34, to.getDeepBlueAerosolOpticalDepthLandSTD0());
         }
         if (to.getDeepBlueAerosolOpticalDepthLandSTD1() == null) {
            pstm.setNull(35, Types.DOUBLE);
         } else {
            pstm.setDouble(35, to.getDeepBlueAerosolOpticalDepthLandSTD1());
         }
         if (to.getDeepBlueAerosolOpticalDepthLandSTD2() == null) {
            pstm.setNull(36, Types.DOUBLE);
         } else {
            pstm.setDouble(36, to.getDeepBlueAerosolOpticalDepthLandSTD2());
         }
         if (to.getDeepBlueAngstromExponentLand() == null) {
            pstm.setNull(37, Types.DOUBLE);
         } else {
            pstm.setDouble(37, to.getDeepBlueAngstromExponentLand());
         }
         if (to.getDeepBlueMeanReflectanceLand0() == null) {
            pstm.setNull(38, Types.DOUBLE);
         } else {
            pstm.setDouble(38, to.getDeepBlueMeanReflectanceLand0());
         }
         if (to.getDeepBlueMeanReflectanceLand1() == null) {
            pstm.setNull(39, Types.DOUBLE);
         } else {
            pstm.setDouble(39, to.getDeepBlueMeanReflectanceLand1());
         }
         if (to.getDeepBlueMeanReflectanceLand2() == null) {
            pstm.setNull(40, Types.DOUBLE);
         } else {
            pstm.setDouble(40, to.getDeepBlueMeanReflectanceLand2());
         }
         if (to.getDeepBlueNumberPixelsUsedLand0() == null) {
            pstm.setNull(41, Types.DOUBLE);
         } else {
            pstm.setDouble(41, to.getDeepBlueNumberPixelsUsedLand0());
         }
         if (to.getDeepBlueNumberPixelsUsedLand1() == null) {
            pstm.setNull(42, Types.DOUBLE);
         } else {
            pstm.setDouble(42, to.getDeepBlueNumberPixelsUsedLand1());
         }
         if (to.getDeepBlueNumberPixelsUsedLand2() == null) {
            pstm.setNull(43, Types.DOUBLE);
         } else {
            pstm.setDouble(43, to.getDeepBlueNumberPixelsUsedLand2());
         }
         if (to.getDeepBlueSingleScatteringAlbedoLand0() == null) {
            pstm.setNull(44, Types.DOUBLE);
         } else {
            pstm.setDouble(44, to.getDeepBlueSingleScatteringAlbedoLand0());
         }
         if (to.getDeepBlueSingleScatteringAlbedoLand1() == null) {
            pstm.setNull(45, Types.DOUBLE);
         } else {
            pstm.setDouble(45, to.getDeepBlueSingleScatteringAlbedoLand1());
         }
         if (to.getDeepBlueSingleScatteringAlbedoLand2() == null) {
            pstm.setNull(46, Types.DOUBLE);
         } else {
            pstm.setDouble(46, to.getDeepBlueSingleScatteringAlbedoLand2());
         }
         if (to.getDeepBlueSurfaceReflectanceLand0() == null) {
            pstm.setNull(47, Types.DOUBLE);
         } else {
            pstm.setDouble(47, to.getDeepBlueSurfaceReflectanceLand0());
         }
         if (to.getDeepBlueSurfaceReflectanceLand1() == null) {
            pstm.setNull(48, Types.DOUBLE);
         } else {
            pstm.setDouble(48, to.getDeepBlueSurfaceReflectanceLand1());
         }
         if (to.getDeepBlueSurfaceReflectanceLand2() == null) {
            pstm.setNull(49, Types.DOUBLE);
         } else {
            pstm.setDouble(49, to.getDeepBlueSurfaceReflectanceLand2());
         }
         if (to.getEffectiveRadiusOcean0() == null) {
            pstm.setNull(50, Types.DOUBLE);
         } else {
            pstm.setDouble(50, to.getEffectiveRadiusOcean0());
         }
         if (to.getEffectiveRadiusOcean1() == null) {
            pstm.setNull(51, Types.DOUBLE);
         } else {
            pstm.setDouble(51, to.getEffectiveRadiusOcean1());
         }
         if (to.getErrorCriticalReflectanceLand0() == null) {
            pstm.setNull(52, Types.DOUBLE);
         } else {
            pstm.setDouble(52, to.getErrorCriticalReflectanceLand0());
         }
         if (to.getErrorCriticalReflectanceLand1() == null) {
            pstm.setNull(53, Types.DOUBLE);
         } else {
            pstm.setDouble(53, to.getErrorCriticalReflectanceLand1());
         }
         if (to.getErrorPathRadianceLand0() == null) {
            pstm.setNull(54, Types.DOUBLE);
         } else {
            pstm.setDouble(54, to.getErrorPathRadianceLand0());
         }
         if (to.getErrorPathRadianceLand1() == null) {
            pstm.setNull(55, Types.DOUBLE);
         } else {
            pstm.setDouble(55, to.getErrorPathRadianceLand1());
         }
         if (to.getFittingErrorLand() == null) {
            pstm.setNull(56, Types.DOUBLE);
         } else {
            pstm.setDouble(56, to.getFittingErrorLand());
         }
         if (to.getImageOpticalDepthLandAndOcean() == null) {
            pstm.setNull(57, Types.DOUBLE);
         } else {
            pstm.setDouble(57, to.getImageOpticalDepthLandAndOcean());
         }
         if (to.getLeastSquaresErrorOcean0() == null) {
            pstm.setNull(58, Types.DOUBLE);
         } else {
            pstm.setDouble(58, to.getLeastSquaresErrorOcean0());
         }
         if (to.getLeastSquaresErrorOcean1() == null) {
            pstm.setNull(59, Types.DOUBLE);
         } else {
            pstm.setDouble(59, to.getLeastSquaresErrorOcean1());
         }
         if (to.getMassConcentrationLand() == null) {
            pstm.setNull(60, Types.DOUBLE);
         } else {
            pstm.setDouble(60, to.getMassConcentrationLand());
         }
         if (to.getMassConcentrationOcean0() == null) {
            pstm.setNull(61, Types.DOUBLE);
         } else {
            pstm.setDouble(61, to.getMassConcentrationOcean0());
         }
         if (to.getMassConcentrationOcean1() == null) {
            pstm.setNull(62, Types.DOUBLE);
         } else {
            pstm.setDouble(62, to.getMassConcentrationOcean1());
         }
         if (to.getMeanReflectanceLandAll0() == null) {
            pstm.setNull(63, Types.DOUBLE);
         } else {
            pstm.setDouble(63, to.getMeanReflectanceLandAll0());
         }
         if (to.getMeanReflectanceLandAll1() == null) {
            pstm.setNull(64, Types.DOUBLE);
         } else {
            pstm.setDouble(64, to.getMeanReflectanceLandAll1());
         }
         if (to.getMeanReflectanceLandAll2() == null) {
            pstm.setNull(65, Types.DOUBLE);
         } else {
            pstm.setDouble(65, to.getMeanReflectanceLandAll2());
         }
         if (to.getNumberPixelsUsedLand0() == null) {
            pstm.setNull(66, Types.DOUBLE);
         } else {
            pstm.setDouble(66, to.getNumberPixelsUsedLand0());
         }
         if (to.getNumberPixelsUsedLand1() == null) {
            pstm.setNull(67, Types.DOUBLE);
         } else {
            pstm.setDouble(67, to.getNumberPixelsUsedLand1());
         }
         if (to.getNumberPixelsUsedOcean() == null) {
            pstm.setNull(68, Types.DOUBLE);
         } else {
            pstm.setDouble(68, to.getNumberPixelsUsedOcean());
         }
         if (to.getOpticalDepthLandAndOcean() == null) {
            pstm.setNull(69, Types.DOUBLE);
         } else {
            pstm.setDouble(69, to.getOpticalDepthLandAndOcean());
         }
         if (to.getOpticalDepthRatioSmallLand() == null) {
            pstm.setNull(70, Types.DOUBLE);
         } else {
            pstm.setDouble(70, to.getOpticalDepthRatioSmallLand());
         }
         if (to.getOpticalDepthRatioSmallLandAndOcean() == null) {
            pstm.setNull(71, Types.DOUBLE);
         } else {
            pstm.setDouble(71, to.getOpticalDepthRatioSmallLandAndOcean());
         }
         if (to.getOpticalDepthRatioSmallOcean055micron0() == null) {
            pstm.setNull(72, Types.DOUBLE);
         } else {
            pstm.setDouble(72, to.getOpticalDepthRatioSmallOcean055micron0());
         }
         if (to.getOpticalDepthRatioSmallOcean055micron1() == null) {
            pstm.setNull(73, Types.DOUBLE);
         } else {
            pstm.setDouble(73, to.getOpticalDepthRatioSmallOcean055micron1());
         }
         if (to.getOpticalDepthSmallLand0() == null) {
            pstm.setNull(74, Types.DOUBLE);
         } else {
            pstm.setDouble(74, to.getOpticalDepthSmallLand0());
         }
         if (to.getOpticalDepthSmallLand1() == null) {
            pstm.setNull(75, Types.DOUBLE);
         } else {
            pstm.setDouble(75, to.getOpticalDepthSmallLand1());
         }
         if (to.getOpticalDepthSmallLand2() == null) {
            pstm.setNull(76, Types.DOUBLE);
         } else {
            pstm.setDouble(76, to.getOpticalDepthSmallLand2());
         }
         if (to.getOpticalDepthSmallLand3() == null) {
            pstm.setNull(77, Types.DOUBLE);
         } else {
            pstm.setDouble(77, to.getOpticalDepthSmallLand3());
         }
         if (to.getPathRadianceLand0() == null) {
            pstm.setNull(78, Types.DOUBLE);
         } else {
            pstm.setDouble(78, to.getPathRadianceLand0());
         }
         if (to.getPathRadianceLand1() == null) {
            pstm.setNull(79, Types.DOUBLE);
         } else {
            pstm.setDouble(79, to.getPathRadianceLand1());
         }
         if (to.getQualityWeightCriticalReflectanceLand0() == null) {
            pstm.setNull(80, Types.DOUBLE);
         } else {
            pstm.setDouble(80, to.getQualityWeightCriticalReflectanceLand0());
         }
         if (to.getQualityWeightCriticalReflectanceLand1() == null) {
            pstm.setNull(81, Types.DOUBLE);
         } else {
            pstm.setDouble(81, to.getQualityWeightCriticalReflectanceLand1());
         }
         if (to.getQualityWeightPathRadianceLand0() == null) {
            pstm.setNull(82, Types.DOUBLE);
         } else {
            pstm.setDouble(82, to.getQualityWeightPathRadianceLand0());
         }
         if (to.getQualityWeightPathRadianceLand1() == null) {
            pstm.setNull(83, Types.DOUBLE);
         } else {
            pstm.setDouble(83, to.getQualityWeightPathRadianceLand1());
         }
         if (to.getScanStartTime() == null) {
            pstm.setNull(84, Types.DOUBLE);
         } else {
            pstm.setDouble(84, to.getScanStartTime());
         }
         if (to.getScatteringAngle() == null) {
            pstm.setNull(85, Types.DOUBLE);
         } else {
            pstm.setDouble(85, to.getScatteringAngle());
         }
         if (to.getSensorAzimuth() == null) {
            pstm.setNull(86, Types.DOUBLE);
         } else {
            pstm.setDouble(86, to.getSensorAzimuth());
         }
         if (to.getSensorZenith() == null) {
            pstm.setNull(87, Types.DOUBLE);
         } else {
            pstm.setDouble(87, to.getSensorZenith());
         }
         if (to.getSolarAzimuth() == null) {
            pstm.setNull(88, Types.DOUBLE);
         } else {
            pstm.setDouble(88, to.getSolarAzimuth());
         }
         if (to.getSolarZenith() == null) {
            pstm.setNull(89, Types.DOUBLE);
         } else {
            pstm.setDouble(89, to.getSolarZenith());
         }
         if (to.getSolutionIndexOceanLarge0() == null) {
            pstm.setNull(90, Types.DOUBLE);
         } else {
            pstm.setDouble(90, to.getSolutionIndexOceanLarge0());
         }
         if (to.getSolutionIndexOceanLarge1() == null) {
            pstm.setNull(91, Types.DOUBLE);
         } else {
            pstm.setDouble(91, to.getSolutionIndexOceanLarge1());
         }
         if (to.getSolutionIndexOceanSmall0() == null) {
            pstm.setNull(92, Types.DOUBLE);
         } else {
            pstm.setDouble(92, to.getSolutionIndexOceanSmall0());
         }
         if (to.getSolutionIndexOceanSmall1() == null) {
            pstm.setNull(93, Types.DOUBLE);
         } else {
            pstm.setDouble(93, to.getSolutionIndexOceanSmall1());
         }
         if (to.getStandardDeviationReflectanceLandAll0() == null) {
            pstm.setNull(94, Types.DOUBLE);
         } else {
            pstm.setDouble(94, to.getStandardDeviationReflectanceLandAll0());
         }
         if (to.getStandardDeviationReflectanceLandAll1() == null) {
            pstm.setNull(95, Types.DOUBLE);
         } else {
            pstm.setDouble(95, to.getStandardDeviationReflectanceLandAll1());
         }
         if (to.getStandardDeviationReflectanceLandAll2() == null) {
            pstm.setNull(96, Types.DOUBLE);
         } else {
            pstm.setDouble(96, to.getStandardDeviationReflectanceLandAll2());
         }
         if (to.getSurfaceReflectanceLand0() == null) {
            pstm.setNull(97, Types.DOUBLE);
         } else {
            pstm.setDouble(97, to.getSurfaceReflectanceLand0());
         }
         if (to.getSurfaceReflectanceLand1() == null) {
            pstm.setNull(98, Types.DOUBLE);
         } else {
            pstm.setDouble(98, to.getSurfaceReflectanceLand1());
         }
         if (to.getSurfaceReflectanceLand2() == null) {
            pstm.setNull(99, Types.DOUBLE);
         } else {
            pstm.setDouble(99, to.getSurfaceReflectanceLand2());
         }
         pstm.execute();
      } catch (Exception e) {
         System.out.print("mod04to content duplicat " + to.getDatabaseKey() + " file " + to.getHdfFileName());
      }
      return true;
   }
}
