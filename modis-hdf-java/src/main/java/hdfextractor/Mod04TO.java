package hdfextractor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.joda.time.DateTime;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

import ro.grig.sat.util.FmtDateTime;
import ro.grig.sat.util.ParseByte;
import ro.grig.sat.util.ParseDateTime;

/**
 * Pentru produsul modis 04, colectia 06, structura de salvare
 * 
 * @author Grigoras
 * 
 */
public class Mod04TO extends TOBase implements Serializable {

   private static final long serialVersionUID = 1L;
   @Transient
   public static String headers[] = new String[] { "timp",// 0
         "pozitie", // index 1
         "pozitieAlong",// index 2
         "pozitieAcross", // 3
         "latitudine",// 4
         "longitudine",// 5
         "statie",// 6
         "aerosolTypeLand",// 7
         "angstromExponent1Ocean0",// 8
         "angstromExponent1Ocean1",// 9
         "angstromExponent2Ocean0",// 10
         "angstromExponent2Ocean1",// 11
         "angstromExponentLand",// 12
         "cloudCondensationNucleiOcean0",// 13
         "cloudCondensationNucleiOcean1",// 14
         "cloudFractionLand",// 15
         "cloudFractionOcean",// 16
         "cloudMaskQA",// 17
         "correctedOpticalDepthLand0",// 18
         "correctedOpticalDepthLand1",// 19
         "correctedOpticalDepthLand2",// 20
         "correctedOpticalDepthLandwav2p1",// 21
         "criticalReflectanceLand0",// 22
         "criticalReflectanceLand1",// 23
         "deepBlueAerosolOpticalDepth550Land",// 24
         "deepBlueAerosolOpticalDepth550LandSTD",// 25
         "deepBlueAerosolOpticalDepthLand0",// 26
         "deepBlueAerosolOpticalDepthLand1",// 27
         "deepBlueAerosolOpticalDepthLand2",// 28
         "deepBlueAerosolOpticalDepthLandSTD0",// 29
         "deepBlueAerosolOpticalDepthLandSTD1",// 30
         "deepBlueAerosolOpticalDepthLandSTD2",// 31
         "deepBlueAngstromExponentLand",// 32
         "deepBlueMeanReflectanceLand0",// 33
         "deepBlueMeanReflectanceLand1",// 34
         "deepBlueMeanReflectanceLand2",// 35
         "deepBlueNumberPixelsUsedLand0",// 36
         "deepBlueNumberPixelsUsedLand1",// 37
         "deepBlueNumberPixelsUsedLand2",// 38
         "deepBlueSingleScatteringAlbedoLand0",// 39
         "deepBlueSingleScatteringAlbedoLand1",// 40
         "deepBlueSingleScatteringAlbedoLand2",// 41
         "deepBlueSurfaceReflectanceLand0",// 42
         "deepBlueSurfaceReflectanceLand1",// 43
         "deepBlueSurfaceReflectanceLand2",// 44
         "effectiveRadiusOcean0",// 45
         "effectiveRadiusOcean1",// 46
         "errorCriticalReflectanceLand0",// 47
         "errorCriticalReflectanceLand1",// 48
         "errorPathRadianceLand0",// 49
         "errorPathRadianceLand1",// 50
         "fittingErrorLand",// 51
         "imageOpticalDepthLandAndOcean",// 52
         "leastSquaresErrorOcean0",// 53
         "leastSquaresErrorOcean1",// 54
         "massConcentrationLand",// 55
         "massConcentrationOcean0",// 56
         "massConcentrationOcean1",// 57
         "meanReflectanceLandAll0",// 58
         "meanReflectanceLandAll1",// 59
         "meanReflectanceLandAll2",// 60
         "numberPixelsUsedLand0",// 61
         "numberPixelsUsedLand1",// 62
         "numberPixelsUsedOcean",// 63
         "opticalDepthLandAndOcean",// 64
         "opticalDepthRatioSmallLand",// 65
         "opticalDepthRatioSmallLandAndOcean",// 66
         "opticalDepthRatioSmallOcean055micron0",// 67
         "opticalDepthRatioSmallOcean055micron1",// 68
         "opticalDepthSmallLand0",// 69
         "opticalDepthSmallLand1",// 70
         "opticalDepthSmallLand2",// 71
         "opticalDepthSmallLand3",// 72
         "pathRadianceLand0",// 73
         "pathRadianceLand1",// 74
         "qualityWeightCriticalReflectanceLand0",// 75
         "qualityWeightCriticalReflectanceLand1",// 76
         "qualityWeightPathRadianceLand0",// 77
         "qualityWeightPathRadianceLand1",// 78
         "scanStartTime",// 79
         "scatteringAngle",// 80
         "sensorAzimuth",// 81
         "sensorZenith",// 82
         "solarAzimuth",// 83
         "solarZenith",// 84
         "solutionIndexOceanLarge0",// 85
         "solutionIndexOceanLarge1",// 86
         "solutionIndexOceanSmall0",// 87
         "solutionIndexOceanSmall1",// 88
         "standardDeviationReflectanceLandAll0",// 89
         "standardDeviationReflectanceLandAll1",// 90
         "standardDeviationReflectanceLandAll2",// 91
         "surfaceReflectanceLand0",// 92
         "surfaceReflectanceLand1",// 93
         "surfaceReflectanceLand2",// 94
         "numePozitie",// statia corespunzatoare a sol
         "hdfFileName" };

   public static CellProcessor[] getReadProcessors() {
      CellProcessor[] retval = new CellProcessor[] {// lista procesori
      new ParseDateTime(),// 1
            new NotNull(new ParseLong()),// 2
            new NotNull(new ParseLong()),// 3
            new NotNull(new ParseLong()),// 4
            new NotNull(new ParseDouble()),// 5
            new NotNull(new ParseDouble()),// 6
            new Optional(),// 7
            new Optional(new ParseDouble()),// 8
            new Optional(new ParseDouble()),// 9
            new Optional(new ParseDouble()),// 10
            new Optional(new ParseDouble()),// 11
            new Optional(new ParseDouble()),// 12
            new Optional(new ParseDouble()),// 13
            new Optional(new ParseDouble()),// 14
            new Optional(new ParseDouble()),// 15
            new Optional(new ParseDouble()),// 16
            new Optional(new ParseDouble()),// 17
            new Optional(new ParseByte()),// 18
            new Optional(new ParseDouble()),// 19
            new Optional(new ParseDouble()),// 20
            new Optional(new ParseDouble()),// 21
            new Optional(new ParseDouble()),// 22
            new Optional(new ParseDouble()),// 23
            new Optional(new ParseDouble()),// 24
            new Optional(new ParseDouble()),// 25
            new Optional(new ParseDouble()),// 26
            new Optional(new ParseDouble()),// 27
            new Optional(new ParseDouble()),// 28
            new Optional(new ParseDouble()),// 29
            new Optional(new ParseDouble()),// 30
            new Optional(new ParseDouble()),// 31
            new Optional(new ParseDouble()),// 32
            new Optional(new ParseDouble()),// 33
            new Optional(new ParseDouble()),// 34
            new Optional(new ParseDouble()),// 35
            new Optional(new ParseDouble()),// 36
            new Optional(new ParseDouble()),// 37
            new Optional(new ParseDouble()),// 38
            new Optional(new ParseDouble()),// 39
            new Optional(new ParseDouble()),// 40
            new Optional(new ParseDouble()),// 41
            new Optional(new ParseDouble()),// 42
            new Optional(new ParseDouble()),// 43
            new Optional(new ParseDouble()),// 44
            new Optional(new ParseDouble()),// 45
            new Optional(new ParseDouble()),// 46
            new Optional(new ParseDouble()),// 47
            new Optional(new ParseDouble()),// 48
            new Optional(new ParseDouble()),// 49
            new Optional(new ParseDouble()),// 50
            new Optional(new ParseDouble()),// 51
            new Optional(new ParseDouble()),// 52
            new Optional(new ParseDouble()),// 53
            new Optional(new ParseDouble()),// 54
            new Optional(new ParseDouble()),// 55
            new Optional(new ParseDouble()),// 56
            new Optional(new ParseDouble()),// 57
            new Optional(new ParseDouble()),// 58
            new Optional(new ParseDouble()),// 59
            new Optional(new ParseDouble()),// 60
            new Optional(new ParseDouble()),// 61
            new Optional(new ParseDouble()),// 62
            new Optional(new ParseDouble()),// 63
            new Optional(new ParseDouble()),// 64
            new Optional(new ParseDouble()),// 65
            new Optional(new ParseDouble()),// 66
            new Optional(new ParseDouble()),// 67
            new Optional(new ParseDouble()),// 68
            new Optional(new ParseDouble()),// 69
            new Optional(new ParseDouble()),// 70
            new Optional(new ParseDouble()),// 71
            new Optional(new ParseDouble()),// 72
            new Optional(new ParseDouble()),// 73
            new Optional(new ParseDouble()),// 74
            new Optional(new ParseDouble()),// 75
            new Optional(new ParseDouble()),// 76
            new Optional(new ParseDouble()),// 77
            new Optional(new ParseDouble()),// 78
            new Optional(new ParseDouble()),// 79
            new Optional(new ParseDouble()),// 80
            new Optional(new ParseDouble()),// 81
            new Optional(new ParseDouble()),// 82
            new Optional(new ParseDouble()),// 83
            new Optional(new ParseDouble()),// 84
            new Optional(new ParseDouble()),// 85
            new Optional(new ParseDouble()),// 86
            new Optional(new ParseDouble()),// 87
            new Optional(new ParseDouble()),// 88
            new Optional(new ParseDouble()),// 89
            new Optional(new ParseDouble()),// 90
            new Optional(new ParseDouble()),// 91
            new Optional(new ParseDouble()),// 92
            new Optional(new ParseDouble()),// 93
            new Optional(new ParseDouble()),// 94
            new Optional(new ParseDouble()),// 95
            new NotNull(),// 96
            new NotNull() };
      return retval;
   }

   public static CellProcessor[] getWriteProcessors() {
      CellProcessor[] retval = new CellProcessor[] { new FmtDateTime(),// 1
            new NotNull(new ParseLong()),// 2
            new Optional(new ParseLong()),// 3
            new Optional(),// 4
            new Optional(),// 5
            new Optional(),// 6
            new Optional(),// 7
            new Optional(),// 8
            new Optional(),// 9
            new Optional(),// 10
            new Optional(),// 11
            new Optional(),// 12
            new Optional(),// 13
            new Optional(),// 14
            new Optional(),// 15
            new Optional(),// 16
            new Optional(),// 17
            new Optional(),// 18
            new Optional(),// 19
            new Optional(),// 20
            new Optional(),// 21
            new Optional(),// 22
            new Optional(),// 23
            new Optional(),// 24
            new Optional(),// 25
            new Optional(),// 26
            new Optional(),// 27
            new Optional(),// 28
            new Optional(),// 29
            new Optional(),// 30
            new Optional(),// 31
            new Optional(),// 32
            new Optional(),// 33
            new Optional(),// 34
            new Optional(),// 35
            new Optional(),// 36
            new Optional(),// 37
            new Optional(),// 38
            new Optional(),// 39
            new Optional(),// 40
            new Optional(),// 41
            new Optional(),// 42
            new Optional(),// 43
            new Optional(),// 44
            new Optional(),// 45
            new Optional(),// 46
            new Optional(),// 47
            new Optional(),// 48
            new Optional(),// 49
            new Optional(),// 50
            new Optional(),// 51
            new Optional(),// 52
            new Optional(),// 53
            new Optional(),// 54
            new Optional(),// 55
            new Optional(),// 56
            new Optional(),// 57
            new Optional(),// 58
            new Optional(),// 59
            new Optional(),// 60
            new Optional(),// 61
            new Optional(),// 62
            new Optional(),// 63
            new Optional(),// 64
            new Optional(),// 65
            new Optional(),// 66
            new Optional(),// 67
            new Optional(),// 68
            new Optional(),// 69
            new Optional(),// 70
            new Optional(),// 71
            new Optional(),// 72
            new Optional(),// 73
            new Optional(),// 74
            new Optional(),// 75
            new Optional(),// 76
            new Optional(),// 77
            new Optional(),// 78
            new Optional(),// 79
            new Optional(),// 80
            new Optional(),// 81
            new Optional(),// 82
            new Optional(),// 83
            new Optional(),// 84
            new Optional(),// 85
            new Optional(),// 86
            new Optional(),// 87
            new Optional(),// 88
            new Optional(),// 89
            new Optional(),// 90
            new Optional(),// 91
            new Optional(),// 92
            new Optional(),// 93
            new Optional(),// 94
            new Optional(),// 95
            new Optional(),// 96
            new Optional() };
      return retval;
   }

   private DateTime timp = null;
   @Column
   @Id
   private String databaseKey;
   @javax.validation.constraints.NotNull
   private String hdfFileName = "";
   @javax.validation.constraints.NotNull
   private String numePozitie = "";
   @javax.validation.constraints.NotNull
   private Long pozitie;
   @javax.validation.constraints.NotNull
   private Long pozitieAlong;
   @javax.validation.constraints.NotNull
   private Long pozitieAcross;
   @javax.validation.constraints.NotNull
   private Double latitudine;
   @javax.validation.constraints.NotNull
   private String statie;
   @javax.validation.constraints.NotNull
   private Double longitudine;
   private int squarSizeM = 10000;
   // Start
   // Skip: ObiectDesc [numeCamp=Aerosol_Cldmask_Byproducts_Land,
   // tip=DFNT_INT16,
   // dimensiune=(Num_By_Products,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // Skip: ObiectDesc [numeCamp=Aerosol_Cldmask_Byproducts_Ocean,
   // tip=DFNT_INT16,
   // dimensiune=(Num_By_Products,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   private Double aerosolTypeLand;
   // cazul 3=2
   private Double angstromExponent1Ocean0;
   private Double angstromExponent1Ocean1;
   // cazul 3=2
   private Double angstromExponent2Ocean0;
   private Double angstromExponent2Ocean1;
   private Double angstromExponentLand;
   // Skip: ObiectDesc [numeCamp=Asymmetry_Factor_Average_Ocean,
   // tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // Skip: ObiectDesc [numeCamp=Asymmetry_Factor_Best_Ocean, tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // Skip: ObiectDesc [numeCamp=Backscattering_Ratio_Average_Ocean,
   // tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // Skip: ObiectDesc [numeCamp=Backscattering_Ratio_Best_Ocean,
   // tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // cazul 3=2
   private Double cloudCondensationNucleiOcean0;
   private Double cloudCondensationNucleiOcean1;
   private Double cloudFractionLand;
   private Double cloudFractionOcean;
   private Byte cloudMaskQA;
   // cazul 3=3
   private Double correctedOpticalDepthLand0;
   private Double correctedOpticalDepthLand1;
   private Double correctedOpticalDepthLand2;
   private Double correctedOpticalDepthLandwav2p1;
   // cazul 3=2
   private Double criticalReflectanceLand0;
   private Double criticalReflectanceLand1;
   private Double deepBlueAerosolOpticalDepth550Land;
   private Double deepBlueAerosolOpticalDepth550LandSTD;
   // cazul 3=3
   private Double deepBlueAerosolOpticalDepthLand0;
   private Double deepBlueAerosolOpticalDepthLand1;
   private Double deepBlueAerosolOpticalDepthLand2;
   // cazul 3=3
   private Double deepBlueAerosolOpticalDepthLandSTD0;
   private Double deepBlueAerosolOpticalDepthLandSTD1;
   private Double deepBlueAerosolOpticalDepthLandSTD2;
   private Double deepBlueAngstromExponentLand;
   // cazul 3=3
   private Double deepBlueMeanReflectanceLand0;
   private Double deepBlueMeanReflectanceLand1;
   private Double deepBlueMeanReflectanceLand2;
   // cazul 3=3
   private Double deepBlueNumberPixelsUsedLand0;
   private Double deepBlueNumberPixelsUsedLand1;
   private Double deepBlueNumberPixelsUsedLand2;
   // cazul 3=3
   private Double deepBlueSingleScatteringAlbedoLand0;
   private Double deepBlueSingleScatteringAlbedoLand1;
   private Double deepBlueSingleScatteringAlbedoLand2;
   // cazul 3=3
   private Double deepBlueSurfaceReflectanceLand0;
   private Double deepBlueSurfaceReflectanceLand1;
   private Double deepBlueSurfaceReflectanceLand2;
   // Skip: ObiectDesc [numeCamp=Effective_Optical_Depth_Average_Ocean,
   // tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // Skip: ObiectDesc [numeCamp=Effective_Optical_Depth_Best_Ocean,
   // tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // cazul 3=2
   private Double effectiveRadiusOcean0;
   private Double effectiveRadiusOcean1;
   // cazul 3=2
   private Double errorCriticalReflectanceLand0;
   private Double errorCriticalReflectanceLand1;
   // cazul 3=2
   private Double errorPathRadianceLand0;
   private Double errorPathRadianceLand1;
   private Double fittingErrorLand;
   private Double imageOpticalDepthLandAndOcean;
   // cazul 3=2
   private Double leastSquaresErrorOcean0;
   private Double leastSquaresErrorOcean1;
   private Double massConcentrationLand;
   // cazul 3=2
   private Double massConcentrationOcean0;
   private Double massConcentrationOcean1;
   // Skip: ObiectDesc [numeCamp=Mean_Reflectance_Land, tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Land,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // cazul 3=3
   private Double meanReflectanceLandAll0;
   private Double meanReflectanceLandAll1;
   private Double meanReflectanceLandAll2;
   // Skip: ObiectDesc [numeCamp=Mean_Reflectance_Ocean, tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // cazul 3=2
   private Double numberPixelsUsedLand0;
   private Double numberPixelsUsedLand1;
   private Double numberPixelsUsedOcean;
   private Double opticalDepthLandAndOcean;
   // Skip: ObiectDesc [numeCamp=Optical_Depth_Large_Average_Ocean,
   // tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // Skip: ObiectDesc [numeCamp=Optical_Depth_Large_Best_Ocean,
   // tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   private Double opticalDepthRatioSmallLand;
   private Double opticalDepthRatioSmallLandAndOcean;
   // cazul 3=2
   private Double opticalDepthRatioSmallOcean055micron0;
   private Double opticalDepthRatioSmallOcean055micron1;
   // Skip: ObiectDesc [numeCamp=Optical_Depth_Small_Average_Ocean,
   // tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // Skip: ObiectDesc [numeCamp=Optical_Depth_Small_Best_Ocean,
   // tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // cazul 3=4
   private Double opticalDepthSmallLand0;
   private Double opticalDepthSmallLand1;
   private Double opticalDepthSmallLand2;
   private Double opticalDepthSmallLand3;
   // Skip: ObiectDesc [numeCamp=Optical_Depth_by_models_ocean, tip=DFNT_INT16,
   // dimensiune=(Solution_Index,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 9
   // cazul 3=2
   private Double pathRadianceLand0;
   private Double pathRadianceLand1;
   // cazul 3=2
   private Double qualityWeightCriticalReflectanceLand0;
   private Double qualityWeightCriticalReflectanceLand1;
   // cazul 3=2
   private Double qualityWeightPathRadianceLand0;
   private Double qualityWeightPathRadianceLand1;
   // Skip: ObiectDesc [numeCamp=Quality_Assurance_Crit_Ref_Land,
   // tip=DFNT_INT8,
   // dimensiune=(Cell_Along_Swath,Cell_Across_Swath,QA_Byte_Land)] : first
   // dimension too big = 203
   // Skip: ObiectDesc [numeCamp=Quality_Assurance_Land, tip=DFNT_INT8,
   // dimensiune=(Cell_Along_Swath,Cell_Across_Swath,QA_Byte_Land)] : first
   // dimension too big = 203
   // Skip: ObiectDesc [numeCamp=Quality_Assurance_Ocean, tip=DFNT_INT8,
   // dimensiune=(Cell_Along_Swath,Cell_Across_Swath,QA_Byte_Ocean)] : first
   // dimension too big = 203
   // Skip: ObiectDesc [numeCamp=STD_Reflectance_Land, tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Land,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   // Skip: ObiectDesc [numeCamp=STD_Reflectance_Ocean, tip=DFNT_INT16,
   // dimensiune=(MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath)] : first
   // dimension too big = 7
   private Double scanStartTime;
   private Double scatteringAngle;
   private Double sensorAzimuth;
   private Double sensorZenith;
   private Double solarAzimuth;
   private Double solarZenith;
   // cazul 3=2
   private Double solutionIndexOceanLarge0;
   private Double solutionIndexOceanLarge1;
   // cazul 3=2
   private Double solutionIndexOceanSmall0;
   private Double solutionIndexOceanSmall1;
   // cazul 3=3
   private Double standardDeviationReflectanceLandAll0;
   private Double standardDeviationReflectanceLandAll1;
   private Double standardDeviationReflectanceLandAll2;
   // cazul 3=3
   private Double surfaceReflectanceLand0;
   private Double surfaceReflectanceLand1;
   private Double surfaceReflectanceLand2;

   public void buildDatabaseKey() {
      databaseKey = String.format("K%s%09d%09d", timp.toString("yyyyMMddHHmm"), Math.round(latitudine * 1000000),
            Math.round(longitudine * 1000000));
   }

   /**
    * Field:Aerosol_Type_Land Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
    * Tip: DFNT_INT16
    */
   public Double getAerosolTypeLand() {
      return aerosolTypeLand;
   }

   /**
    * Field:Angstrom_Exponent_1_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getAngstromExponent1Ocean0() {
      return angstromExponent1Ocean0;
   }

   /**
    * Field:Angstrom_Exponent_1_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getAngstromExponent1Ocean1() {
      return angstromExponent1Ocean1;
   }

   /**
    * Field:Angstrom_Exponent_2_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getAngstromExponent2Ocean0() {
      return angstromExponent2Ocean0;
   }

   /**
    * Field:Angstrom_Exponent_2_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getAngstromExponent2Ocean1() {
      return angstromExponent2Ocean1;
   }

   /**
    * Field:Angstrom_Exponent_Land Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getAngstromExponentLand() {
      return angstromExponentLand;
   }

   /**
    * Field:Cloud_Condensation_Nuclei_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
    */
   public Double getCloudCondensationNucleiOcean0() {
      return cloudCondensationNucleiOcean0;
   }

   /**
    * Field:Cloud_Condensation_Nuclei_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
    */
   public Double getCloudCondensationNucleiOcean1() {
      return cloudCondensationNucleiOcean1;
   }

   /**
    * Field:Cloud_Fraction_Land Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
    * Tip: DFNT_INT16
    */
   public Double getCloudFractionLand() {
      return cloudFractionLand;
   }

   /**
    * Field:Cloud_Fraction_Ocean Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getCloudFractionOcean() {
      return cloudFractionOcean;
   }

   /**
    * Field:Cloud_Mask_QA Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT8
    */
   public Byte getCloudMaskQA() {
      return cloudMaskQA;
   }

   /**
    * Field:Corrected_Optical_Depth_Land Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getCorrectedOpticalDepthLand0() {
      return correctedOpticalDepthLand0;
   }

   /**
    * Field:Corrected_Optical_Depth_Land Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getCorrectedOpticalDepthLand1() {
      return correctedOpticalDepthLand1;
   }

   /**
    * Field:Corrected_Optical_Depth_Land Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getCorrectedOpticalDepthLand2() {
      return correctedOpticalDepthLand2;
   }

   /**
    * Field:Corrected_Optical_Depth_Land_wav2p1 Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getCorrectedOpticalDepthLandwav2p1() {
      return correctedOpticalDepthLandwav2p1;
   }

   /**
    * Field:Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getCriticalReflectanceLand0() {
      return criticalReflectanceLand0;
   }

   /**
    * Field:Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getCriticalReflectanceLand1() {
      return criticalReflectanceLand1;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_550_Land Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getDeepBlueAerosolOpticalDepth550Land() {
      return deepBlueAerosolOpticalDepth550Land;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_550_Land_STD Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getDeepBlueAerosolOpticalDepth550LandSTD() {
      return deepBlueAerosolOpticalDepth550LandSTD;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueAerosolOpticalDepthLand0() {
      return deepBlueAerosolOpticalDepthLand0;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueAerosolOpticalDepthLand1() {
      return deepBlueAerosolOpticalDepthLand1;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueAerosolOpticalDepthLand2() {
      return deepBlueAerosolOpticalDepthLand2;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land_STD Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueAerosolOpticalDepthLandSTD0() {
      return deepBlueAerosolOpticalDepthLandSTD0;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land_STD Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueAerosolOpticalDepthLandSTD1() {
      return deepBlueAerosolOpticalDepthLandSTD1;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land_STD Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueAerosolOpticalDepthLandSTD2() {
      return deepBlueAerosolOpticalDepthLandSTD2;
   }

   /**
    * Field:Deep_Blue_Angstrom_Exponent_Land Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getDeepBlueAngstromExponentLand() {
      return deepBlueAngstromExponentLand;
   }

   /**
    * Field:Deep_Blue_Mean_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueMeanReflectanceLand0() {
      return deepBlueMeanReflectanceLand0;
   }

   /**
    * Field:Deep_Blue_Mean_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueMeanReflectanceLand1() {
      return deepBlueMeanReflectanceLand1;
   }

   /**
    * Field:Deep_Blue_Mean_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueMeanReflectanceLand2() {
      return deepBlueMeanReflectanceLand2;
   }

   /**
    * Field:Deep_Blue_Number_Pixels_Used_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueNumberPixelsUsedLand0() {
      return deepBlueNumberPixelsUsedLand0;
   }

   /**
    * Field:Deep_Blue_Number_Pixels_Used_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueNumberPixelsUsedLand1() {
      return deepBlueNumberPixelsUsedLand1;
   }

   /**
    * Field:Deep_Blue_Number_Pixels_Used_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueNumberPixelsUsedLand2() {
      return deepBlueNumberPixelsUsedLand2;
   }

   /**
    * Field:Deep_Blue_Single_Scattering_Albedo_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueSingleScatteringAlbedoLand0() {
      return deepBlueSingleScatteringAlbedoLand0;
   }

   /**
    * Field:Deep_Blue_Single_Scattering_Albedo_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueSingleScatteringAlbedoLand1() {
      return deepBlueSingleScatteringAlbedoLand1;
   }

   /**
    * Field:Deep_Blue_Single_Scattering_Albedo_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueSingleScatteringAlbedoLand2() {
      return deepBlueSingleScatteringAlbedoLand2;
   }

   /**
    * Field:Deep_Blue_Surface_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueSurfaceReflectanceLand0() {
      return deepBlueSurfaceReflectanceLand0;
   }

   /**
    * Field:Deep_Blue_Surface_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueSurfaceReflectanceLand1() {
      return deepBlueSurfaceReflectanceLand1;
   }

   /**
    * Field:Deep_Blue_Surface_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getDeepBlueSurfaceReflectanceLand2() {
      return deepBlueSurfaceReflectanceLand2;
   }

   /**
    * Field:Effective_Radius_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getEffectiveRadiusOcean0() {
      return effectiveRadiusOcean0;
   }

   /**
    * Field:Effective_Radius_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getEffectiveRadiusOcean1() {
      return effectiveRadiusOcean1;
   }

   /**
    * Field:Error_Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getErrorCriticalReflectanceLand0() {
      return errorCriticalReflectanceLand0;
   }

   /**
    * Field:Error_Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getErrorCriticalReflectanceLand1() {
      return errorCriticalReflectanceLand1;
   }

   /**
    * Field:Error_Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getErrorPathRadianceLand0() {
      return errorPathRadianceLand0;
   }

   /**
    * Field:Error_Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getErrorPathRadianceLand1() {
      return errorPathRadianceLand1;
   }

   /**
    * Field:Fitting_Error_Land Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
    * Tip: DFNT_INT16
    */
   public Double getFittingErrorLand() {
      return fittingErrorLand;
   }

   public String getHdfFileName() {
      return hdfFileName;
   }

   /**
    * Field:Image_Optical_Depth_Land_And_Ocean Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getImageOpticalDepthLandAndOcean() {
      return imageOpticalDepthLandAndOcean;
   }

   public Double getLatitudine() {
      return latitudine;
   }

   /**
    * Field:Least_Squares_Error_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getLeastSquaresErrorOcean0() {
      return leastSquaresErrorOcean0;
   }

   /**
    * Field:Least_Squares_Error_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getLeastSquaresErrorOcean1() {
      return leastSquaresErrorOcean1;
   }

   public Double getLongitudine() {
      return longitudine;
   }

   /**
    * Field:Mass_Concentration_Land Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
    */
   public Double getMassConcentrationLand() {
      return massConcentrationLand;
   }

   /**
    * Field:Mass_Concentration_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
    */
   public Double getMassConcentrationOcean0() {
      return massConcentrationOcean0;
   }

   /**
    * Field:Mass_Concentration_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
    */
   public Double getMassConcentrationOcean1() {
      return massConcentrationOcean1;
   }

   /**
    * Field:Mean_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getMeanReflectanceLandAll0() {
      return meanReflectanceLandAll0;
   }

   /**
    * Field:Mean_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getMeanReflectanceLandAll1() {
      return meanReflectanceLandAll1;
   }

   /**
    * Field:Mean_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getMeanReflectanceLandAll2() {
      return meanReflectanceLandAll2;
   }

   /**
    * Field:Number_Pixels_Used_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getNumberPixelsUsedLand0() {
      return numberPixelsUsedLand0;
   }

   /**
    * Field:Number_Pixels_Used_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getNumberPixelsUsedLand1() {
      return numberPixelsUsedLand1;
   }

   /**
    * Field:Number_Pixels_Used_Ocean Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getNumberPixelsUsedOcean() {
      return numberPixelsUsedOcean;
   }

   /**
    * Field:Optical_Depth_Land_And_Ocean Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getOpticalDepthLandAndOcean() {
      return opticalDepthLandAndOcean;
   }

   /**
    * Field:Optical_Depth_Ratio_Small_Land Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getOpticalDepthRatioSmallLand() {
      return opticalDepthRatioSmallLand;
   }

   /**
    * Field:Optical_Depth_Ratio_Small_Land_And_Ocean Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getOpticalDepthRatioSmallLandAndOcean() {
      return opticalDepthRatioSmallLandAndOcean;
   }

   /**
    * Field:Optical_Depth_Ratio_Small_Ocean_0.55micron Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getOpticalDepthRatioSmallOcean055micron0() {
      return opticalDepthRatioSmallOcean055micron0;
   }

   /**
    * Field:Optical_Depth_Ratio_Small_Ocean_0.55micron Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getOpticalDepthRatioSmallOcean055micron1() {
      return opticalDepthRatioSmallOcean055micron1;
   }

   /**
    * Field:Optical_Depth_Small_Land Dimensiune:
    * (Solution_4_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getOpticalDepthSmallLand0() {
      return opticalDepthSmallLand0;
   }

   /**
    * Field:Optical_Depth_Small_Land Dimensiune:
    * (Solution_4_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getOpticalDepthSmallLand1() {
      return opticalDepthSmallLand1;
   }

   /**
    * Field:Optical_Depth_Small_Land Dimensiune:
    * (Solution_4_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getOpticalDepthSmallLand2() {
      return opticalDepthSmallLand2;
   }

   /**
    * Field:Optical_Depth_Small_Land Dimensiune:
    * (Solution_4_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getOpticalDepthSmallLand3() {
      return opticalDepthSmallLand3;
   }

   /**
    * Field:Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getPathRadianceLand0() {
      return pathRadianceLand0;
   }

   /**
    * Field:Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getPathRadianceLand1() {
      return pathRadianceLand1;
   }

   public Long getPozitie() {
      return pozitie;
   }

   public Long getPozitieAcross() {
      return pozitieAcross;
   }

   public Long getPozitieAlong() {
      return pozitieAlong;
   }

   /**
    * Field:QualityWeight_Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getQualityWeightCriticalReflectanceLand0() {
      return qualityWeightCriticalReflectanceLand0;
   }

   /**
    * Field:QualityWeight_Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getQualityWeightCriticalReflectanceLand1() {
      return qualityWeightCriticalReflectanceLand1;
   }

   /**
    * Field:QualityWeight_Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getQualityWeightPathRadianceLand0() {
      return qualityWeightPathRadianceLand0;
   }

   /**
    * Field:QualityWeight_Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getQualityWeightPathRadianceLand1() {
      return qualityWeightPathRadianceLand1;
   }

   /**
    * Field:Scan_Start_Time Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
    * Tip: DFNT_FLOAT64
    */
   public Double getScanStartTime() {
      return scanStartTime;
   }

   /**
    * Field:Scattering_Angle Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
    * Tip: DFNT_INT16
    */
   public Double getScatteringAngle() {
      return scatteringAngle;
   }

   /**
    * Field:Sensor_Azimuth Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getSensorAzimuth() {
      return sensorAzimuth;
   }

   /**
    * Field:Sensor_Zenith Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getSensorZenith() {
      return sensorZenith;
   }

   /**
    * Field:Solar_Azimuth Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getSolarAzimuth() {
      return solarAzimuth;
   }

   /**
    * Field:Solar_Zenith Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public Double getSolarZenith() {
      return solarZenith;
   }

   /**
    * Field:Solution_Index_Ocean_Large Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getSolutionIndexOceanLarge0() {
      return solutionIndexOceanLarge0;
   }

   /**
    * Field:Solution_Index_Ocean_Large Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getSolutionIndexOceanLarge1() {
      return solutionIndexOceanLarge1;
   }

   /**
    * Field:Solution_Index_Ocean_Small Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getSolutionIndexOceanSmall0() {
      return solutionIndexOceanSmall0;
   }

   /**
    * Field:Solution_Index_Ocean_Small Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getSolutionIndexOceanSmall1() {
      return solutionIndexOceanSmall1;
   }

   public int getSquarSizeM() {
      return squarSizeM;
   }

   /**
    * Field:Standard_Deviation_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getStandardDeviationReflectanceLandAll0() {
      return standardDeviationReflectanceLandAll0;
   }

   /**
    * Field:Standard_Deviation_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getStandardDeviationReflectanceLandAll1() {
      return standardDeviationReflectanceLandAll1;
   }

   /**
    * Field:Standard_Deviation_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getStandardDeviationReflectanceLandAll2() {
      return standardDeviationReflectanceLandAll2;
   }

   public String getStatie() {
      return statie;
   }

   /**
    * Field:Surface_Reflectance_Land Dimensiune:
    * (Solution_2_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getSurfaceReflectanceLand0() {
      return surfaceReflectanceLand0;
   }

   /**
    * Field:Surface_Reflectance_Land Dimensiune:
    * (Solution_2_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getSurfaceReflectanceLand1() {
      return surfaceReflectanceLand1;
   }

   /**
    * Field:Surface_Reflectance_Land Dimensiune:
    * (Solution_2_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public Double getSurfaceReflectanceLand2() {
      return surfaceReflectanceLand2;
   }

   public DateTime getTimp() {
      return timp;
   }

   /**
    * Field:Aerosol_Type_Land Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
    * Tip: DFNT_INT16
    */
   public void setAerosolTypeLand(Double aerosolTypeLand) {
      this.aerosolTypeLand = aerosolTypeLand;
   }

   /**
    * Field:Angstrom_Exponent_1_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setAngstromExponent1Ocean0(Double angstromExponent1Ocean0) {
      this.angstromExponent1Ocean0 = angstromExponent1Ocean0;
   }

   /**
    * Field:Angstrom_Exponent_1_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setAngstromExponent1Ocean1(Double angstromExponent1Ocean1) {
      this.angstromExponent1Ocean1 = angstromExponent1Ocean1;
   }

   /**
    * Field:Angstrom_Exponent_2_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setAngstromExponent2Ocean0(Double angstromExponent2Ocean0) {
      this.angstromExponent2Ocean0 = angstromExponent2Ocean0;
   }

   /**
    * Field:Angstrom_Exponent_2_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setAngstromExponent2Ocean1(Double angstromExponent2Ocean1) {
      this.angstromExponent2Ocean1 = angstromExponent2Ocean1;
   }

   /**
    * Field:Angstrom_Exponent_Land Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setAngstromExponentLand(Double angstromExponentLand) {
      this.angstromExponentLand = angstromExponentLand;
   }

   /**
    * Field:Cloud_Condensation_Nuclei_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
    */
   public void setCloudCondensationNucleiOcean0(Double cloudCondensationNucleiOcean0) {
      this.cloudCondensationNucleiOcean0 = cloudCondensationNucleiOcean0;
   }

   /**
    * Field:Cloud_Condensation_Nuclei_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
    */
   public void setCloudCondensationNucleiOcean1(Double cloudCondensationNucleiOcean1) {
      this.cloudCondensationNucleiOcean1 = cloudCondensationNucleiOcean1;
   }

   /**
    * Field:Cloud_Fraction_Land Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
    * Tip: DFNT_INT16
    */
   public void setCloudFractionLand(Double cloudFractionLand) {
      this.cloudFractionLand = cloudFractionLand;
   }

   /**
    * Field:Cloud_Fraction_Ocean Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setCloudFractionOcean(Double cloudFractionOcean) {
      this.cloudFractionOcean = cloudFractionOcean;
   }

   /**
    * Field:Cloud_Mask_QA Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT8
    */
   public void setCloudMaskQA(Byte cloudMaskQA) {
      this.cloudMaskQA = cloudMaskQA;
   }

   /**
    * Field:Corrected_Optical_Depth_Land Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setCorrectedOpticalDepthLand0(Double correctedOpticalDepthLand0) {
      this.correctedOpticalDepthLand0 = correctedOpticalDepthLand0;
   }

   /**
    * Field:Corrected_Optical_Depth_Land Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setCorrectedOpticalDepthLand1(Double correctedOpticalDepthLand1) {
      this.correctedOpticalDepthLand1 = correctedOpticalDepthLand1;
   }

   /**
    * Field:Corrected_Optical_Depth_Land Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setCorrectedOpticalDepthLand2(Double correctedOpticalDepthLand2) {
      this.correctedOpticalDepthLand2 = correctedOpticalDepthLand2;
   }

   /**
    * Field:Corrected_Optical_Depth_Land_wav2p1 Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setCorrectedOpticalDepthLandwav2p1(Double correctedOpticalDepthLandwav2p1) {
      this.correctedOpticalDepthLandwav2p1 = correctedOpticalDepthLandwav2p1;
   }

   /**
    * Field:Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setCriticalReflectanceLand0(Double criticalReflectanceLand0) {
      this.criticalReflectanceLand0 = criticalReflectanceLand0;
   }

   /**
    * Field:Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setCriticalReflectanceLand1(Double criticalReflectanceLand1) {
      this.criticalReflectanceLand1 = criticalReflectanceLand1;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_550_Land Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setDeepBlueAerosolOpticalDepth550Land(Double deepBlueAerosolOpticalDepth550Land) {
      this.deepBlueAerosolOpticalDepth550Land = deepBlueAerosolOpticalDepth550Land;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_550_Land_STD Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setDeepBlueAerosolOpticalDepth550LandSTD(Double deepBlueAerosolOpticalDepth550LandSTD) {
      this.deepBlueAerosolOpticalDepth550LandSTD = deepBlueAerosolOpticalDepth550LandSTD;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueAerosolOpticalDepthLand0(Double deepBlueAerosolOpticalDepthLand0) {
      this.deepBlueAerosolOpticalDepthLand0 = deepBlueAerosolOpticalDepthLand0;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueAerosolOpticalDepthLand1(Double deepBlueAerosolOpticalDepthLand1) {
      this.deepBlueAerosolOpticalDepthLand1 = deepBlueAerosolOpticalDepthLand1;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueAerosolOpticalDepthLand2(Double deepBlueAerosolOpticalDepthLand2) {
      this.deepBlueAerosolOpticalDepthLand2 = deepBlueAerosolOpticalDepthLand2;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land_STD Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueAerosolOpticalDepthLandSTD0(Double deepBlueAerosolOpticalDepthLandSTD0) {
      this.deepBlueAerosolOpticalDepthLandSTD0 = deepBlueAerosolOpticalDepthLandSTD0;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land_STD Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueAerosolOpticalDepthLandSTD1(Double deepBlueAerosolOpticalDepthLandSTD1) {
      this.deepBlueAerosolOpticalDepthLandSTD1 = deepBlueAerosolOpticalDepthLandSTD1;
   }

   /**
    * Field:Deep_Blue_Aerosol_Optical_Depth_Land_STD Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueAerosolOpticalDepthLandSTD2(Double deepBlueAerosolOpticalDepthLandSTD2) {
      this.deepBlueAerosolOpticalDepthLandSTD2 = deepBlueAerosolOpticalDepthLandSTD2;
   }

   /**
    * Field:Deep_Blue_Angstrom_Exponent_Land Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setDeepBlueAngstromExponentLand(Double deepBlueAngstromExponentLand) {
      this.deepBlueAngstromExponentLand = deepBlueAngstromExponentLand;
   }

   /**
    * Field:Deep_Blue_Mean_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueMeanReflectanceLand0(Double deepBlueMeanReflectanceLand0) {
      this.deepBlueMeanReflectanceLand0 = deepBlueMeanReflectanceLand0;
   }

   /**
    * Field:Deep_Blue_Mean_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueMeanReflectanceLand1(Double deepBlueMeanReflectanceLand1) {
      this.deepBlueMeanReflectanceLand1 = deepBlueMeanReflectanceLand1;
   }

   /**
    * Field:Deep_Blue_Mean_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueMeanReflectanceLand2(Double deepBlueMeanReflectanceLand2) {
      this.deepBlueMeanReflectanceLand2 = deepBlueMeanReflectanceLand2;
   }

   /**
    * Field:Deep_Blue_Number_Pixels_Used_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueNumberPixelsUsedLand0(Double deepBlueNumberPixelsUsedLand0) {
      this.deepBlueNumberPixelsUsedLand0 = deepBlueNumberPixelsUsedLand0;
   }

   /**
    * Field:Deep_Blue_Number_Pixels_Used_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueNumberPixelsUsedLand1(Double deepBlueNumberPixelsUsedLand1) {
      this.deepBlueNumberPixelsUsedLand1 = deepBlueNumberPixelsUsedLand1;
   }

   /**
    * Field:Deep_Blue_Number_Pixels_Used_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueNumberPixelsUsedLand2(Double deepBlueNumberPixelsUsedLand2) {
      this.deepBlueNumberPixelsUsedLand2 = deepBlueNumberPixelsUsedLand2;
   }

   /**
    * Field:Deep_Blue_Single_Scattering_Albedo_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueSingleScatteringAlbedoLand0(Double deepBlueSingleScatteringAlbedoLand0) {
      this.deepBlueSingleScatteringAlbedoLand0 = deepBlueSingleScatteringAlbedoLand0;
   }

   /**
    * Field:Deep_Blue_Single_Scattering_Albedo_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueSingleScatteringAlbedoLand1(Double deepBlueSingleScatteringAlbedoLand1) {
      this.deepBlueSingleScatteringAlbedoLand1 = deepBlueSingleScatteringAlbedoLand1;
   }

   /**
    * Field:Deep_Blue_Single_Scattering_Albedo_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueSingleScatteringAlbedoLand2(Double deepBlueSingleScatteringAlbedoLand2) {
      this.deepBlueSingleScatteringAlbedoLand2 = deepBlueSingleScatteringAlbedoLand2;
   }

   /**
    * Field:Deep_Blue_Surface_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueSurfaceReflectanceLand0(Double deepBlueSurfaceReflectanceLand0) {
      this.deepBlueSurfaceReflectanceLand0 = deepBlueSurfaceReflectanceLand0;
   }

   /**
    * Field:Deep_Blue_Surface_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueSurfaceReflectanceLand1(Double deepBlueSurfaceReflectanceLand1) {
      this.deepBlueSurfaceReflectanceLand1 = deepBlueSurfaceReflectanceLand1;
   }

   /**
    * Field:Deep_Blue_Surface_Reflectance_Land Dimensiune:
    * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setDeepBlueSurfaceReflectanceLand2(Double deepBlueSurfaceReflectanceLand2) {
      this.deepBlueSurfaceReflectanceLand2 = deepBlueSurfaceReflectanceLand2;
   }

   /**
    * Field:Effective_Radius_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setEffectiveRadiusOcean0(Double effectiveRadiusOcean0) {
      this.effectiveRadiusOcean0 = effectiveRadiusOcean0;
   }

   /**
    * Field:Effective_Radius_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setEffectiveRadiusOcean1(Double effectiveRadiusOcean1) {
      this.effectiveRadiusOcean1 = effectiveRadiusOcean1;
   }

   /**
    * Field:Error_Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setErrorCriticalReflectanceLand0(Double errorCriticalReflectanceLand0) {
      this.errorCriticalReflectanceLand0 = errorCriticalReflectanceLand0;
   }

   /**
    * Field:Error_Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setErrorCriticalReflectanceLand1(Double errorCriticalReflectanceLand1) {
      this.errorCriticalReflectanceLand1 = errorCriticalReflectanceLand1;
   }

   /**
    * Field:Error_Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setErrorPathRadianceLand0(Double errorPathRadianceLand0) {
      this.errorPathRadianceLand0 = errorPathRadianceLand0;
   }

   /**
    * Field:Error_Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setErrorPathRadianceLand1(Double errorPathRadianceLand1) {
      this.errorPathRadianceLand1 = errorPathRadianceLand1;
   }

   /**
    * Field:Fitting_Error_Land Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
    * Tip: DFNT_INT16
    */
   public void setFittingErrorLand(Double fittingErrorLand) {
      this.fittingErrorLand = fittingErrorLand;
   }

   public void setHdfFileName(String hdfFileName) {
      this.hdfFileName = hdfFileName;
   }

   /**
    * Field:Image_Optical_Depth_Land_And_Ocean Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setImageOpticalDepthLandAndOcean(Double imageOpticalDepthLandAndOcean) {
      this.imageOpticalDepthLandAndOcean = imageOpticalDepthLandAndOcean;
   }

   public void setLatitudine(Double latitudine) {
      this.latitudine = latitudine;
   }

   /**
    * Field:Least_Squares_Error_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setLeastSquaresErrorOcean0(Double leastSquaresErrorOcean0) {
      this.leastSquaresErrorOcean0 = leastSquaresErrorOcean0;
   }

   /**
    * Field:Least_Squares_Error_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setLeastSquaresErrorOcean1(Double leastSquaresErrorOcean1) {
      this.leastSquaresErrorOcean1 = leastSquaresErrorOcean1;
   }

   public void setLongitudine(Double longitudine) {
      this.longitudine = longitudine;
   }

   /**
    * Field:Mass_Concentration_Land Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
    */
   public void setMassConcentrationLand(Double massConcentrationLand) {
      this.massConcentrationLand = massConcentrationLand;
   }

   /**
    * Field:Mass_Concentration_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
    */
   public void setMassConcentrationOcean0(Double massConcentrationOcean0) {
      this.massConcentrationOcean0 = massConcentrationOcean0;
   }

   /**
    * Field:Mass_Concentration_Ocean Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
    */
   public void setMassConcentrationOcean1(Double massConcentrationOcean1) {
      this.massConcentrationOcean1 = massConcentrationOcean1;
   }

   /**
    * Field:Mean_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setMeanReflectanceLandAll0(Double meanReflectanceLandAll0) {
      this.meanReflectanceLandAll0 = meanReflectanceLandAll0;
   }

   /**
    * Field:Mean_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setMeanReflectanceLandAll1(Double meanReflectanceLandAll1) {
      this.meanReflectanceLandAll1 = meanReflectanceLandAll1;
   }

   /**
    * Field:Mean_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setMeanReflectanceLandAll2(Double meanReflectanceLandAll2) {
      this.meanReflectanceLandAll2 = meanReflectanceLandAll2;
   }

   /**
    * Field:Number_Pixels_Used_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setNumberPixelsUsedLand0(Double numberPixelsUsedLand0) {
      this.numberPixelsUsedLand0 = numberPixelsUsedLand0;
   }

   /**
    * Field:Number_Pixels_Used_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setNumberPixelsUsedLand1(Double numberPixelsUsedLand1) {
      this.numberPixelsUsedLand1 = numberPixelsUsedLand1;
   }

   /**
    * Field:Number_Pixels_Used_Ocean Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setNumberPixelsUsedOcean(Double numberPixelsUsedOcean) {
      this.numberPixelsUsedOcean = numberPixelsUsedOcean;
   }

   /**
    * Field:Optical_Depth_Land_And_Ocean Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setOpticalDepthLandAndOcean(Double opticalDepthLandAndOcean) {
      this.opticalDepthLandAndOcean = opticalDepthLandAndOcean;
   }

   /**
    * Field:Optical_Depth_Ratio_Small_Land Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setOpticalDepthRatioSmallLand(Double opticalDepthRatioSmallLand) {
      this.opticalDepthRatioSmallLand = opticalDepthRatioSmallLand;
   }

   /**
    * Field:Optical_Depth_Ratio_Small_Land_And_Ocean Dimensiune:
    * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setOpticalDepthRatioSmallLandAndOcean(Double opticalDepthRatioSmallLandAndOcean) {
      this.opticalDepthRatioSmallLandAndOcean = opticalDepthRatioSmallLandAndOcean;
   }

   /**
    * Field:Optical_Depth_Ratio_Small_Ocean_0.55micron Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setOpticalDepthRatioSmallOcean055micron0(Double opticalDepthRatioSmallOcean055micron0) {
      this.opticalDepthRatioSmallOcean055micron0 = opticalDepthRatioSmallOcean055micron0;
   }

   /**
    * Field:Optical_Depth_Ratio_Small_Ocean_0.55micron Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setOpticalDepthRatioSmallOcean055micron1(Double opticalDepthRatioSmallOcean055micron1) {
      this.opticalDepthRatioSmallOcean055micron1 = opticalDepthRatioSmallOcean055micron1;
   }

   /**
    * Field:Optical_Depth_Small_Land Dimensiune:
    * (Solution_4_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setOpticalDepthSmallLand0(Double opticalDepthSmallLand0) {
      this.opticalDepthSmallLand0 = opticalDepthSmallLand0;
   }

   /**
    * Field:Optical_Depth_Small_Land Dimensiune:
    * (Solution_4_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setOpticalDepthSmallLand1(Double opticalDepthSmallLand1) {
      this.opticalDepthSmallLand1 = opticalDepthSmallLand1;
   }

   /**
    * Field:Optical_Depth_Small_Land Dimensiune:
    * (Solution_4_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setOpticalDepthSmallLand2(Double opticalDepthSmallLand2) {
      this.opticalDepthSmallLand2 = opticalDepthSmallLand2;
   }

   /**
    * Field:Optical_Depth_Small_Land Dimensiune:
    * (Solution_4_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setOpticalDepthSmallLand3(Double opticalDepthSmallLand3) {
      this.opticalDepthSmallLand3 = opticalDepthSmallLand3;
   }

   /**
    * Field:Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setPathRadianceLand0(Double pathRadianceLand0) {
      this.pathRadianceLand0 = pathRadianceLand0;
   }

   /**
    * Field:Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setPathRadianceLand1(Double pathRadianceLand1) {
      this.pathRadianceLand1 = pathRadianceLand1;
   }

   public void setPozitie(Long pozitie) {
      this.pozitie = pozitie;
   }

   public void setPozitieAcross(Long pozitieAcross) {
      this.pozitieAcross = pozitieAcross;
   }

   public void setPozitieAlong(Long pozitieAlong) {
      this.pozitieAlong = pozitieAlong;
   }

   /**
    * Field:QualityWeight_Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setQualityWeightCriticalReflectanceLand0(Double qualityWeightCriticalReflectanceLand0) {
      this.qualityWeightCriticalReflectanceLand0 = qualityWeightCriticalReflectanceLand0;
   }

   /**
    * Field:QualityWeight_Critical_Reflectance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setQualityWeightCriticalReflectanceLand1(Double qualityWeightCriticalReflectanceLand1) {
      this.qualityWeightCriticalReflectanceLand1 = qualityWeightCriticalReflectanceLand1;
   }

   /**
    * Field:QualityWeight_Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setQualityWeightPathRadianceLand0(Double qualityWeightPathRadianceLand0) {
      this.qualityWeightPathRadianceLand0 = qualityWeightPathRadianceLand0;
   }

   /**
    * Field:QualityWeight_Path_Radiance_Land Dimensiune:
    * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setQualityWeightPathRadianceLand1(Double qualityWeightPathRadianceLand1) {
      this.qualityWeightPathRadianceLand1 = qualityWeightPathRadianceLand1;
   }

   /**
    * Field:Scan_Start_Time Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
    * Tip: DFNT_FLOAT64
    */
   public void setScanStartTime(Double scanStartTime) {
      this.scanStartTime = scanStartTime;
   }

   /**
    * Field:Scattering_Angle Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
    * Tip: DFNT_INT16
    */
   public void setScatteringAngle(Double scatteringAngle) {
      this.scatteringAngle = scatteringAngle;
   }

   /**
    * Field:Sensor_Azimuth Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setSensorAzimuth(Double sensorAzimuth) {
      this.sensorAzimuth = sensorAzimuth;
   }

   /**
    * Field:Sensor_Zenith Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setSensorZenith(Double sensorZenith) {
      this.sensorZenith = sensorZenith;
   }

   /**
    * Field:Solar_Azimuth Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setSolarAzimuth(Double solarAzimuth) {
      this.solarAzimuth = solarAzimuth;
   }

   /**
    * Field:Solar_Zenith Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
    * DFNT_INT16
    */
   public void setSolarZenith(Double solarZenith) {
      this.solarZenith = solarZenith;
   }

   /**
    * Field:Solution_Index_Ocean_Large Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setSolutionIndexOceanLarge0(Double solutionIndexOceanLarge0) {
      this.solutionIndexOceanLarge0 = solutionIndexOceanLarge0;
   }

   /**
    * Field:Solution_Index_Ocean_Large Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setSolutionIndexOceanLarge1(Double solutionIndexOceanLarge1) {
      this.solutionIndexOceanLarge1 = solutionIndexOceanLarge1;
   }

   /**
    * Field:Solution_Index_Ocean_Small Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setSolutionIndexOceanSmall0(Double solutionIndexOceanSmall0) {
      this.solutionIndexOceanSmall0 = solutionIndexOceanSmall0;
   }

   /**
    * Field:Solution_Index_Ocean_Small Dimensiune:
    * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setSolutionIndexOceanSmall1(Double solutionIndexOceanSmall1) {
      this.solutionIndexOceanSmall1 = solutionIndexOceanSmall1;
   }

   public void setSquarSizeM(int squarSizeM) {
      this.squarSizeM = squarSizeM;
   }

   /**
    * Field:Standard_Deviation_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setStandardDeviationReflectanceLandAll0(Double standardDeviationReflectanceLandAll0) {
      this.standardDeviationReflectanceLandAll0 = standardDeviationReflectanceLandAll0;
   }

   /**
    * Field:Standard_Deviation_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setStandardDeviationReflectanceLandAll1(Double standardDeviationReflectanceLandAll1) {
      this.standardDeviationReflectanceLandAll1 = standardDeviationReflectanceLandAll1;
   }

   /**
    * Field:Standard_Deviation_Reflectance_Land_All Dimensiune:
    * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setStandardDeviationReflectanceLandAll2(Double standardDeviationReflectanceLandAll2) {
      this.standardDeviationReflectanceLandAll2 = standardDeviationReflectanceLandAll2;
   }

   public void setStatie(String statie) {
      this.statie = statie;
   }

   /**
    * Field:Surface_Reflectance_Land Dimensiune:
    * (Solution_2_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setSurfaceReflectanceLand0(Double surfaceReflectanceLand0) {
      this.surfaceReflectanceLand0 = surfaceReflectanceLand0;
   }

   /**
    * Field:Surface_Reflectance_Land Dimensiune:
    * (Solution_2_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setSurfaceReflectanceLand1(Double surfaceReflectanceLand1) {
      this.surfaceReflectanceLand1 = surfaceReflectanceLand1;
   }

   /**
    * Field:Surface_Reflectance_Land Dimensiune:
    * (Solution_2_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
    */
   public void setSurfaceReflectanceLand2(Double surfaceReflectanceLand2) {
      this.surfaceReflectanceLand2 = surfaceReflectanceLand2;
   }

   // Stop
   public void setTimp(DateTime timp) {
      this.timp = timp;
   }

   public String getNumePozitie() {
      return numePozitie;
   }

   public void setNumePozitie(String numePozitie) {
      this.numePozitie = numePozitie;
   }

   public String getDatabaseKey() {
      return databaseKey;
   }

   public void setDatabaseKey(String databaseKey) {
      this.databaseKey = databaseKey;
   }

   public String toTestString() {
      buildDatabaseKey();
      return "DateTimeFormatter a = DateTimeFormat.forPattern(\"yyyyMMddHHmm\");\n" + "mock.setTimp("
            + (timp == null ? null : "a.parseDateTime(\"" + timp.toString("yyyyMMddHHmm") + "\")") + ");\n"
            + "mock.setDatabaseKey(" + (databaseKey == null ? null : "\"" + databaseKey + "\"") + ");\n"
            + "mock.setHdfFileName(" + (hdfFileName == null ? null : "\"" + hdfFileName + "\"") + ");\n"
            + "mock.setNumePozitie(" + (numePozitie == null ? null : "\"" + numePozitie + "\"") + ");\n"
            + "mock.setPozitie(new Long(" + pozitie + "));\n" + "mock.setPozitieAlong(new Long(" + pozitieAlong
            + "));\n" + "mock.setPozitieAcross(new Long(" + pozitieAcross + "));\n" + "mock.setLatitudine("
            + latitudine + ");\n" + "mock.setStatie(" + (statie == null ? null : "\"" + statie + "\"") + ");\n"
            + "mock.setLongitudine(" + longitudine + ");\n" + "mock.setSquarSizeM(" + squarSizeM + ");\n"
            + "mock.setAerosolTypeLand(" + aerosolTypeLand + ");\n" + "mock.setAngstromExponent1Ocean0("
            + angstromExponent1Ocean0 + ");\n" + "mock.setAngstromExponent1Ocean1(" + angstromExponent1Ocean1 + ");\n"
            + "mock.setAngstromExponent2Ocean0(" + angstromExponent2Ocean0 + ");\n"
            + "mock.setAngstromExponent2Ocean1(" + angstromExponent2Ocean1 + ");\n" + "mock.setAngstromExponentLand("
            + angstromExponentLand + ");\n" + "mock.setCloudCondensationNucleiOcean0(" + cloudCondensationNucleiOcean0
            + ");\n" + "mock.setCloudCondensationNucleiOcean1(" + cloudCondensationNucleiOcean1 + ");\n"
            + "mock.setCloudFractionLand(" + cloudFractionLand + ");\n" + "mock.setCloudFractionOcean("
            + cloudFractionOcean + ");\n" + "mock.setCloudMaskQA("
            + (cloudMaskQA == null ? null : "(byte)" + cloudMaskQA) + ");\n" + "mock.setCorrectedOpticalDepthLand0("
            + correctedOpticalDepthLand0 + ");\n" + "mock.setCorrectedOpticalDepthLand1(" + correctedOpticalDepthLand1
            + ");\n" + "mock.setCorrectedOpticalDepthLand2(" + correctedOpticalDepthLand2 + ");\n"
            + "mock.setCorrectedOpticalDepthLandwav2p1(" + correctedOpticalDepthLandwav2p1 + ");\n"
            + "mock.setCriticalReflectanceLand0(" + criticalReflectanceLand0 + ");\n"
            + "mock.setCriticalReflectanceLand1(" + criticalReflectanceLand1 + ");\n"
            + "mock.setDeepBlueAerosolOpticalDepth550Land(" + deepBlueAerosolOpticalDepth550Land + ");\n"
            + "mock.setDeepBlueAerosolOpticalDepth550LandSTD(" + deepBlueAerosolOpticalDepth550LandSTD + ");\n"
            + "mock.setDeepBlueAerosolOpticalDepthLand0(" + deepBlueAerosolOpticalDepthLand0 + ");\n"
            + "mock.setDeepBlueAerosolOpticalDepthLand1(" + deepBlueAerosolOpticalDepthLand1 + ");\n"
            + "mock.setDeepBlueAerosolOpticalDepthLand2(" + deepBlueAerosolOpticalDepthLand2 + ");\n"
            + "mock.setDeepBlueAerosolOpticalDepthLandSTD0(" + deepBlueAerosolOpticalDepthLandSTD0 + ");\n"
            + "mock.setDeepBlueAerosolOpticalDepthLandSTD1(" + deepBlueAerosolOpticalDepthLandSTD1 + ");\n"
            + "mock.setDeepBlueAerosolOpticalDepthLandSTD2(" + deepBlueAerosolOpticalDepthLandSTD2 + ");\n"
            + "mock.setDeepBlueAngstromExponentLand(" + deepBlueAngstromExponentLand + ");\n"
            + "mock.setDeepBlueMeanReflectanceLand0(" + deepBlueMeanReflectanceLand0 + ");\n"
            + "mock.setDeepBlueMeanReflectanceLand1(" + deepBlueMeanReflectanceLand1 + ");\n"
            + "mock.setDeepBlueMeanReflectanceLand2(" + deepBlueMeanReflectanceLand2 + ");\n"
            + "mock.setDeepBlueNumberPixelsUsedLand0(" + deepBlueNumberPixelsUsedLand0 + ");\n"
            + "mock.setDeepBlueNumberPixelsUsedLand1(" + deepBlueNumberPixelsUsedLand1 + ");\n"
            + "mock.setDeepBlueNumberPixelsUsedLand2(" + deepBlueNumberPixelsUsedLand2 + ");\n"
            + "mock.setDeepBlueSingleScatteringAlbedoLand0(" + deepBlueSingleScatteringAlbedoLand0 + ");\n"
            + "mock.setDeepBlueSingleScatteringAlbedoLand1(" + deepBlueSingleScatteringAlbedoLand1 + ");\n"
            + "mock.setDeepBlueSingleScatteringAlbedoLand2(" + deepBlueSingleScatteringAlbedoLand2 + ");\n"
            + "mock.setDeepBlueSurfaceReflectanceLand0(" + deepBlueSurfaceReflectanceLand0 + ");\n"
            + "mock.setDeepBlueSurfaceReflectanceLand1(" + deepBlueSurfaceReflectanceLand1 + ");\n"
            + "mock.setDeepBlueSurfaceReflectanceLand2(" + deepBlueSurfaceReflectanceLand2 + ");\n"
            + "mock.setEffectiveRadiusOcean0(" + effectiveRadiusOcean0 + ");\n" + "mock.setEffectiveRadiusOcean1("
            + effectiveRadiusOcean1 + ");\n" + "mock.setErrorCriticalReflectanceLand0(" + errorCriticalReflectanceLand0
            + ");\n" + "mock.setErrorCriticalReflectanceLand1(" + errorCriticalReflectanceLand1 + ");\n"
            + "mock.setErrorPathRadianceLand0(" + errorPathRadianceLand0 + ");\n" + "mock.setErrorPathRadianceLand1("
            + errorPathRadianceLand1 + ");\n" + "mock.setFittingErrorLand(" + fittingErrorLand + ");\n"
            + "mock.setImageOpticalDepthLandAndOcean(" + imageOpticalDepthLandAndOcean + ");\n"
            + "mock.setLeastSquaresErrorOcean0(" + leastSquaresErrorOcean0 + ");\n"
            + "mock.setLeastSquaresErrorOcean1(" + leastSquaresErrorOcean1 + ");\n" + "mock.setMassConcentrationLand("
            + massConcentrationLand + ");\n" + "mock.setMassConcentrationOcean0(" + massConcentrationOcean0 + ");\n"
            + "mock.setMassConcentrationOcean1(" + massConcentrationOcean1 + ");\n"
            + "mock.setMeanReflectanceLandAll0(" + meanReflectanceLandAll0 + ");\n"
            + "mock.setMeanReflectanceLandAll1(" + meanReflectanceLandAll1 + ");\n"
            + "mock.setMeanReflectanceLandAll2(" + meanReflectanceLandAll2 + ");\n" + "mock.setNumberPixelsUsedLand0("
            + numberPixelsUsedLand0 + ");\n" + "mock.setNumberPixelsUsedLand1(" + numberPixelsUsedLand1 + ");\n"
            + "mock.setNumberPixelsUsedOcean(" + numberPixelsUsedOcean + ");\n" + "mock.setOpticalDepthLandAndOcean("
            + opticalDepthLandAndOcean + ");\n" + "mock.setOpticalDepthRatioSmallLand(" + opticalDepthRatioSmallLand
            + ");\n" + "mock.setOpticalDepthRatioSmallLandAndOcean(" + opticalDepthRatioSmallLandAndOcean + ");\n"
            + "mock.setOpticalDepthRatioSmallOcean055micron0(" + opticalDepthRatioSmallOcean055micron0 + ");\n"
            + "mock.setOpticalDepthRatioSmallOcean055micron1(" + opticalDepthRatioSmallOcean055micron1 + ");\n"
            + "mock.setOpticalDepthSmallLand0(" + opticalDepthSmallLand0 + ");\n" + "mock.setOpticalDepthSmallLand1("
            + opticalDepthSmallLand1 + ");\n" + "mock.setOpticalDepthSmallLand2(" + opticalDepthSmallLand2 + ");\n"
            + "mock.setOpticalDepthSmallLand3(" + opticalDepthSmallLand3 + ");\n" + "mock.setPathRadianceLand0("
            + pathRadianceLand0 + ");\n" + "mock.setPathRadianceLand1(" + pathRadianceLand1 + ");\n"
            + "mock.setQualityWeightCriticalReflectanceLand0(" + qualityWeightCriticalReflectanceLand0 + ");\n"
            + "mock.setQualityWeightCriticalReflectanceLand1(" + qualityWeightCriticalReflectanceLand1 + ");\n"
            + "mock.setQualityWeightPathRadianceLand0(" + qualityWeightPathRadianceLand0 + ");\n"
            + "mock.setQualityWeightPathRadianceLand1(" + qualityWeightPathRadianceLand1 + ");\n"
            + "mock.setScanStartTime(" + scanStartTime + ");\n" + "mock.setScatteringAngle(" + scatteringAngle + ");\n"
            + "mock.setSensorAzimuth(" + sensorAzimuth + ");\n" + "mock.setSensorZenith(" + sensorZenith + ");\n"
            + "mock.setSolarAzimuth(" + solarAzimuth + ");\n" + "mock.setSolarZenith(" + solarZenith + ");\n"
            + "mock.setSolutionIndexOceanLarge0(" + solutionIndexOceanLarge0 + ");\n"
            + "mock.setSolutionIndexOceanLarge1(" + solutionIndexOceanLarge1 + ");\n"
            + "mock.setSolutionIndexOceanSmall0(" + solutionIndexOceanSmall0 + ");\n"
            + "mock.setSolutionIndexOceanSmall1(" + solutionIndexOceanSmall1 + ");\n"
            + "mock.setStandardDeviationReflectanceLandAll0(" + standardDeviationReflectanceLandAll0 + ");\n"
            + "mock.setStandardDeviationReflectanceLandAll1(" + standardDeviationReflectanceLandAll1 + ");\n"
            + "mock.setStandardDeviationReflectanceLandAll2(" + standardDeviationReflectanceLandAll2 + ");\n"
            + "mock.setSurfaceReflectanceLand0(" + surfaceReflectanceLand0 + ");\n"
            + "mock.setSurfaceReflectanceLand1(" + surfaceReflectanceLand1 + ");\n"
            + "mock.setSurfaceReflectanceLand2(" + surfaceReflectanceLand2 + ");\n";
   }
}
