package hdfextractor;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import hdfextractor.utils.SimpleStringLineBuilder;

public class Mod04C06TO implements Serializable {

   private DateTime timp = null;
   private String hdfFileName = "";
   private String numePozitie = "";
   private double latitudine;
   private double longitudine;
   private String statie;
   /**
    * Pozitia corespunzatoare pt 10 km
    */
   private Long pozitie;
   /**
    * Pozitia corespunzatoare pt 10 km perpendicular pe directie
    */
   private Long pozitieAcross;
   /**
    * Pozitia corespunzatoare pt 500 km pe directie
    */
   private Long pozitieAlong;
   /**
    * Pozitia corespunzatoare pt 500 m
    */
   private Long pozitie500;
   /**
    * Distanta dintre punctul pe grila de 10km si cel pe grila de 500m
    */
   private Double deltaPozitieM;
   private Double aerosolCldmaskLandOcean;
   private Double aerosolCloudFractionLand;
   private Double aerosolCloudFractionOcean;
   private Double aerosolTypeLand;
   private Double angstromExponent1Ocean;
   private Double angstromExponent2Ocean;
   private Double aod550DarkTargetDeepBlueCombined;
   private Double aod550DarkTargetDeepBlueCombinedAlgorithmFlag;
   private Double aod550DarkTargetDeepBlueCombinedQaFlag;
   private Double[] asymmetryFactorAverageOcean = new Double[7];
   private Double asymmetryFactorAverageOcean0;
   private Double asymmetryFactorAverageOcean1;
   private Double asymmetryFactorAverageOcean2;
   private Double asymmetryFactorAverageOcean3;
   private Double asymmetryFactorAverageOcean4;
   private Double asymmetryFactorAverageOcean5;
   private Double asymmetryFactorAverageOcean6;
   private Double[] asymmetryFactorBestOcean = new Double[7];
   private Double asymmetryFactorBestOcean0;
   private Double asymmetryFactorBestOcean1;
   private Double asymmetryFactorBestOcean2;
   private Double asymmetryFactorBestOcean3;
   private Double asymmetryFactorBestOcean4;
   private Double asymmetryFactorBestOcean5;
   private Double asymmetryFactorBestOcean6;
   private Double averageCloudPixelDistanceLandOcean;
   private Double[] backscatteringRatioAverageOcean = new Double[7];
   private Double backscatteringRatioAverageOcean0;
   private Double backscatteringRatioAverageOcean1;
   private Double backscatteringRatioAverageOcean2;
   private Double backscatteringRatioAverageOcean3;
   private Double backscatteringRatioAverageOcean4;
   private Double backscatteringRatioAverageOcean5;
   private Double backscatteringRatioAverageOcean6;
   private Double[] backscatteringRatioBestOcean = new Double[7];
   private Double backscatteringRatioBestOcean0;
   private Double backscatteringRatioBestOcean1;
   private Double backscatteringRatioBestOcean2;
   private Double backscatteringRatioBestOcean3;
   private Double backscatteringRatioBestOcean4;
   private Double backscatteringRatioBestOcean5;
   private Double backscatteringRatioBestOcean6;
   private Double cloudPixelDistanceLandOcean;
   private Double[] correctedOpticalDepthLand = new Double[3];
   private Double correctedOpticalDepthLand0;
   private Double correctedOpticalDepthLand1;
   private Double correctedOpticalDepthLand2;
   private Double correctedOpticalDepthLandWav2p1;
   private Double deepBlueAerosolOpticalDepth550Land;
   private Double deepBlueAerosolOpticalDepth550LandBestEstimate;
   private Double deepBlueAerosolOpticalDepth550LandEstimatedUncertainty;
   private Double deepBlueAerosolOpticalDepth550LandQaFlag;
   private Double deepBlueAerosolOpticalDepth550LandStd;
   private Double deepBlueAlgorithmFlagLand;
   private Double deepBlueAngstromExponentLand;
   private Double deepBlueCloudFractionLand;
   private Double deepBlueNumberPixelsUsed550Land;
   private Double[] deepBlueSpectralAerosolOpticalDepthLand = new Double[3];
   private Double deepBlueSpectralAerosolOpticalDepthLand0;
   private Double deepBlueSpectralAerosolOpticalDepthLand1;
   private Double deepBlueSpectralAerosolOpticalDepthLand2;
   private Double[] deepBlueSpectralSingleScatteringAlbedoLand = new Double[3];
   private Double deepBlueSpectralSingleScatteringAlbedoLand0;
   private Double deepBlueSpectralSingleScatteringAlbedoLand1;
   private Double deepBlueSpectralSingleScatteringAlbedoLand2;
   private Double[] deepBlueSpectralSurfaceReflectanceLand = new Double[3];
   private Double deepBlueSpectralSurfaceReflectanceLand0;
   private Double deepBlueSpectralSurfaceReflectanceLand1;
   private Double deepBlueSpectralSurfaceReflectanceLand2;
   private Double[] deepBlueSpectralToaReflectanceLand = new Double[3];
   private Double deepBlueSpectralToaReflectanceLand0;
   private Double deepBlueSpectralToaReflectanceLand1;
   private Double deepBlueSpectralToaReflectanceLand2;
   private Double effectiveOpticalDepth0p55umOcean;
   private Double[] effectiveOpticalDepthAverageOcean = new Double[7];
   private Double effectiveOpticalDepthAverageOcean0;
   private Double effectiveOpticalDepthAverageOcean1;
   private Double effectiveOpticalDepthAverageOcean2;
   private Double effectiveOpticalDepthAverageOcean3;
   private Double effectiveOpticalDepthAverageOcean4;
   private Double effectiveOpticalDepthAverageOcean5;
   private Double effectiveOpticalDepthAverageOcean6;
   private Double[] effectiveOpticalDepthBestOcean = new Double[7];
   private Double effectiveOpticalDepthBestOcean0;
   private Double effectiveOpticalDepthBestOcean1;
   private Double effectiveOpticalDepthBestOcean2;
   private Double effectiveOpticalDepthBestOcean3;
   private Double effectiveOpticalDepthBestOcean4;
   private Double effectiveOpticalDepthBestOcean5;
   private Double effectiveOpticalDepthBestOcean6;
   private Double[] effectiveRadiusOcean = new Double[2];
   private Double effectiveRadiusOcean0;
   private Double effectiveRadiusOcean1;
   private Double fittingErrorLand;
   private Double glintAngle;
   private Double imageOpticalDepthLandAndOcean;
   private Double landOceanQualityFlag;
   private Double landSeaFlag;
   private Double[] leastSquaresErrorOcean = new Double[2];
   private Double leastSquaresErrorOcean0;
   private Double leastSquaresErrorOcean1;
   private Double massConcentrationLand;
   private Double[] massConcentrationOcean = new Double[2];
   private Double massConcentrationOcean0;
   private Double massConcentrationOcean1;
   private Double[] meanReflectanceLand = new Double[10];
   private Double meanReflectanceLand0;
   private Double meanReflectanceLand1;
   private Double meanReflectanceLand2;
   private Double meanReflectanceLand3;
   private Double meanReflectanceLand4;
   private Double meanReflectanceLand5;
   private Double meanReflectanceLand6;
   private Double meanReflectanceLand7;
   private Double meanReflectanceLand8;
   private Double meanReflectanceLand9;
   private Double[] meanReflectanceOcean = new Double[10];
   private Double meanReflectanceOcean0;
   private Double meanReflectanceOcean1;
   private Double meanReflectanceOcean2;
   private Double meanReflectanceOcean3;
   private Double meanReflectanceOcean4;
   private Double meanReflectanceOcean5;
   private Double meanReflectanceOcean6;
   private Double meanReflectanceOcean7;
   private Double meanReflectanceOcean8;
   private Double meanReflectanceOcean9;
   private Double[] numberPixelsUsedLand = new Double[10];
   private Double numberPixelsUsedLand0;
   private Double numberPixelsUsedLand1;
   private Double numberPixelsUsedLand2;
   private Double numberPixelsUsedLand3;
   private Double numberPixelsUsedLand4;
   private Double numberPixelsUsedLand5;
   private Double numberPixelsUsedLand6;
   private Double numberPixelsUsedLand7;
   private Double numberPixelsUsedLand8;
   private Double numberPixelsUsedLand9;
   private Double[] numberPixelsUsedOcean = new Double[10];
   private Double numberPixelsUsedOcean0;
   private Double numberPixelsUsedOcean1;
   private Double numberPixelsUsedOcean2;
   private Double numberPixelsUsedOcean3;
   private Double numberPixelsUsedOcean4;
   private Double numberPixelsUsedOcean5;
   private Double numberPixelsUsedOcean6;
   private Double numberPixelsUsedOcean7;
   private Double numberPixelsUsedOcean8;
   private Double numberPixelsUsedOcean9;
   private Double[] opticalDepthByModelsOcean = new Double[9];
   private Double opticalDepthByModelsOcean0;
   private Double opticalDepthByModelsOcean1;
   private Double opticalDepthByModelsOcean2;
   private Double opticalDepthByModelsOcean3;
   private Double opticalDepthByModelsOcean4;
   private Double opticalDepthByModelsOcean5;
   private Double opticalDepthByModelsOcean6;
   private Double opticalDepthByModelsOcean7;
   private Double opticalDepthByModelsOcean8;
   private Double opticalDepthLandAndOcean;
   private Double[] opticalDepthLargeAverageOcean = new Double[7];
   private Double opticalDepthLargeAverageOcean0;
   private Double opticalDepthLargeAverageOcean1;
   private Double opticalDepthLargeAverageOcean2;
   private Double opticalDepthLargeAverageOcean3;
   private Double opticalDepthLargeAverageOcean4;
   private Double opticalDepthLargeAverageOcean5;
   private Double opticalDepthLargeAverageOcean6;
   private Double[] opticalDepthLargeBestOcean = new Double[7];
   private Double opticalDepthLargeBestOcean0;
   private Double opticalDepthLargeBestOcean1;
   private Double opticalDepthLargeBestOcean2;
   private Double opticalDepthLargeBestOcean3;
   private Double opticalDepthLargeBestOcean4;
   private Double opticalDepthLargeBestOcean5;
   private Double opticalDepthLargeBestOcean6;
   private Double opticalDepthRatioSmallLand;
   private Double opticalDepthRatioSmallOcean0p55micron;
   private Double[] opticalDepthSmallAverageOcean = new Double[7];
   private Double opticalDepthSmallAverageOcean0;
   private Double opticalDepthSmallAverageOcean1;
   private Double opticalDepthSmallAverageOcean2;
   private Double opticalDepthSmallAverageOcean3;
   private Double opticalDepthSmallAverageOcean4;
   private Double opticalDepthSmallAverageOcean5;
   private Double opticalDepthSmallAverageOcean6;
   private Double[] opticalDepthSmallBestOcean = new Double[7];
   private Double opticalDepthSmallBestOcean0;
   private Double opticalDepthSmallBestOcean1;
   private Double opticalDepthSmallBestOcean2;
   private Double opticalDepthSmallBestOcean3;
   private Double opticalDepthSmallBestOcean4;
   private Double opticalDepthSmallBestOcean5;
   private Double opticalDepthSmallBestOcean6;
   private Double[] psml003Ocean = new Double[2];
   private Double psml003Ocean0;
   private Double psml003Ocean1;
   private Byte[] qualityAssuranceLand = new Byte[6];
   private Byte qualityAssuranceLand0;
   private Byte qualityAssuranceLand1;
   private Byte qualityAssuranceLand2;
   private Byte qualityAssuranceLand3;
   private Byte qualityAssuranceLand4;
   private Byte qualityAssuranceLand5;
   private Byte[] qualityAssuranceOcean = new Byte[5];
   private Byte qualityAssuranceOcean0;
   private Byte qualityAssuranceOcean1;
   private Byte qualityAssuranceOcean2;
   private Byte qualityAssuranceOcean3;
   private Byte qualityAssuranceOcean4;
   private Double scanStartTime;
   private Double scatteringAngle;
   private Double sensorAzimuth;
   private Double sensorZenith;
   private Double solarAzimuth;
   private Double solarZenith;
   private Double[] solutionIndexOceanLarge = new Double[2];
   private Double solutionIndexOceanLarge0;
   private Double solutionIndexOceanLarge1;
   private Double[] solutionIndexOceanSmall = new Double[2];
   private Double solutionIndexOceanSmall0;
   private Double solutionIndexOceanSmall1;
   private Double[] stdReflectanceLand = new Double[10];
   private Double stdReflectanceLand0;
   private Double stdReflectanceLand1;
   private Double stdReflectanceLand2;
   private Double stdReflectanceLand3;
   private Double stdReflectanceLand4;
   private Double stdReflectanceLand5;
   private Double stdReflectanceLand6;
   private Double stdReflectanceLand7;
   private Double stdReflectanceLand8;
   private Double stdReflectanceLand9;
   private Double[] stdReflectanceOcean = new Double[10];
   private Double stdReflectanceOcean0;
   private Double stdReflectanceOcean1;
   private Double stdReflectanceOcean2;
   private Double stdReflectanceOcean3;
   private Double stdReflectanceOcean4;
   private Double stdReflectanceOcean5;
   private Double stdReflectanceOcean6;
   private Double stdReflectanceOcean7;
   private Double stdReflectanceOcean8;
   private Double stdReflectanceOcean9;
   private Double[] surfaceReflectanceLand = new Double[3];
   private Double surfaceReflectanceLand0;
   private Double surfaceReflectanceLand1;
   private Double surfaceReflectanceLand2;
   private Double topographicAltitudeLand;
   private Double windSpeedNcepOcean;

   public DateTime getTimp() {
      return timp;
   }

   public void setTimp(DateTime timp) {
      this.timp = timp;
   }

   public String getHdfFileName() {
      return hdfFileName;
   }

   public void setHdfFileName(String hdfFileName) {
      this.hdfFileName = hdfFileName;
   }

   public String getNumePozitie() {
      return numePozitie;
   }

   public void setNumePozitie(String numePozitie) {
      this.numePozitie = numePozitie;
   }

   public double getLatitudine() {
      return latitudine;
   }

   public void setLatitudine(double latitudine) {
      this.latitudine = latitudine;
   }

   public double getLongitudine() {
      return longitudine;
   }

   public void setLongitudine(double longitudine) {
      this.longitudine = longitudine;
   }

   public String getStatie() {
      return statie;
   }

   public void setStatie(String statie) {
      this.statie = statie;
   }

   public Long getPozitie() {
      return pozitie;
   }

   public void setPozitie(Long pozitie) {
      this.pozitie = pozitie;
   }

   public Long getPozitieAcross() {
      return pozitieAcross;
   }

   public void setPozitieAcross(Long pozitieAcross) {
      this.pozitieAcross = pozitieAcross;
   }

   public Long getPozitieAlong() {
      return pozitieAlong;
   }

   public void setPozitieAlong(Long pozitieAlong) {
      this.pozitieAlong = pozitieAlong;
   }

   public Long getPozitie500() {
      return pozitie500;
   }

   public void setPozitie500(Long pozitie500) {
      this.pozitie500 = pozitie500;
   }

   public Double getDeltaPozitieM() {
      return deltaPozitieM;
   }

   public void setDeltaPozitieM(Double deltaPozitieM) {
      this.deltaPozitieM = deltaPozitieM;
   }

   /**
    * Field:Aerosol Cloud Mask 500 meter resolution 0= cloud 1= clear
    * Tip:DFNT_INT16 Dim:2
    */
   public Double getAerosolCldmaskLandOcean() {
      return aerosolCldmaskLandOcean;
   }

   /**
    * Field:Aerosol Cloud Mask 500 meter resolution 0= cloud 1= clear
    * Tip:DFNT_INT16 Dim:2
    */
   public void setAerosolCldmaskLandOcean(Double aerosolCldmaskLandOcean) {
      this.aerosolCldmaskLandOcean = aerosolCldmaskLandOcean;
   }

   /**
    * Field:Cloud fraction from Land aerosol cloud mask from retrieved and
    * overcast pixels not including cirrus mask Tip:DFNT_INT16 Dim:2
    */
   public Double getAerosolCloudFractionLand() {
      return aerosolCloudFractionLand;
   }

   /**
    * Field:Cloud fraction from Land aerosol cloud mask from retrieved and
    * overcast pixels not including cirrus mask Tip:DFNT_INT16 Dim:2
    */
   public void setAerosolCloudFractionLand(Double aerosolCloudFractionLand) {
      this.aerosolCloudFractionLand = aerosolCloudFractionLand;
   }

   /**
    * Field:Cloud fraction from Land aerosol cloud mask from retrieved and
    * overcast pixels not including cirrus mask Tip:DFNT_INT16 Dim:2
    */
   public Double getAerosolCloudFractionOcean() {
      return aerosolCloudFractionOcean;
   }

   /**
    * Field:Cloud fraction from Land aerosol cloud mask from retrieved and
    * overcast pixels not including cirrus mask Tip:DFNT_INT16 Dim:2
    */
   public void setAerosolCloudFractionOcean(Double aerosolCloudFractionOcean) {
      this.aerosolCloudFractionOcean = aerosolCloudFractionOcean;
   }

   /**
    * Field:Aerosol Type: 1 = Continental, 2 = Moderate Absorption Fine, 3 =
    * Strong Absorption Fine,4 = Weak Absorption Fine, 5 = Dust Coarse
    * Tip:DFNT_INT16 Dim:2
    */
   public Double getAerosolTypeLand() {
      return aerosolTypeLand;
   }

   /**
    * Field:Aerosol Type: 1 = Continental, 2 = Moderate Absorption Fine, 3 =
    * Strong Absorption Fine,4 = Weak Absorption Fine, 5 = Dust Coarse
    * Tip:DFNT_INT16 Dim:2
    */
   public void setAerosolTypeLand(Double aerosolTypeLand) {
      this.aerosolTypeLand = aerosolTypeLand;
   }

   /**
    * Field:Calculated Angstrom Exponent for 0.55 vs 0.86 micron for Average
    * Solution Tip:DFNT_INT16 Dim:2
    */
   public Double getAngstromExponent1Ocean() {
      return angstromExponent1Ocean;
   }

   /**
    * Field:Calculated Angstrom Exponent for 0.55 vs 0.86 micron for Average
    * Solution Tip:DFNT_INT16 Dim:2
    */
   public void setAngstromExponent1Ocean(Double angstromExponent1Ocean) {
      this.angstromExponent1Ocean = angstromExponent1Ocean;
   }

   /**
    * Field:Calculated Angstrom Exponent for 0.86 vs 2.13 micron for Average
    * Solution Tip:DFNT_INT16 Dim:2
    */
   public Double getAngstromExponent2Ocean() {
      return angstromExponent2Ocean;
   }

   /**
    * Field:Calculated Angstrom Exponent for 0.86 vs 2.13 micron for Average
    * Solution Tip:DFNT_INT16 Dim:2
    */
   public void setAngstromExponent2Ocean(Double angstromExponent2Ocean) {
      this.angstromExponent2Ocean = angstromExponent2Ocean;
   }

   /**
    * Field:Combined Dark Target, Deep Blue AOT at 0.55 micron for land and
    * ocean. Tip:DFNT_INT16 Dim:2
    */
   public Double getAod550DarkTargetDeepBlueCombined() {
      return aod550DarkTargetDeepBlueCombined;
   }

   /**
    * Field:Combined Dark Target, Deep Blue AOT at 0.55 micron for land and
    * ocean. Tip:DFNT_INT16 Dim:2
    */
   public void setAod550DarkTargetDeepBlueCombined(Double aod550DarkTargetDeepBlueCombined) {
      this.aod550DarkTargetDeepBlueCombined = aod550DarkTargetDeepBlueCombined;
   }

   /**
    * Field:Combined Dark Target, Deep Blue AOT at 0.55 micron Algorithm Flag
    * (0=Dark Target, 1=Deep Blue, 2=Mixed) Tip:DFNT_INT16 Dim:2
    */
   public Double getAod550DarkTargetDeepBlueCombinedAlgorithmFlag() {
      return aod550DarkTargetDeepBlueCombinedAlgorithmFlag;
   }

   /**
    * Field:Combined Dark Target, Deep Blue AOT at 0.55 micron Algorithm Flag
    * (0=Dark Target, 1=Deep Blue, 2=Mixed) Tip:DFNT_INT16 Dim:2
    */
   public void setAod550DarkTargetDeepBlueCombinedAlgorithmFlag(Double aod550DarkTargetDeepBlueCombinedAlgorithmFlag) {
      this.aod550DarkTargetDeepBlueCombinedAlgorithmFlag = aod550DarkTargetDeepBlueCombinedAlgorithmFlag;
   }

   /**
    * Field:Combined Dark Target, Deep Blue Aerosol Confidence Flag (0= No
    * Confidence (or fill), 1= Marginal, 2= Good, 3= Very Good) Tip:DFNT_INT16
    * Dim:2
    */
   public Double getAod550DarkTargetDeepBlueCombinedQaFlag() {
      return aod550DarkTargetDeepBlueCombinedQaFlag;
   }

   /**
    * Field:Combined Dark Target, Deep Blue Aerosol Confidence Flag (0= No
    * Confidence (or fill), 1= Marginal, 2= Good, 3= Very Good) Tip:DFNT_INT16
    * Dim:2
    */
   public void setAod550DarkTargetDeepBlueCombinedQaFlag(Double aod550DarkTargetDeepBlueCombinedQaFlag) {
      this.aod550DarkTargetDeepBlueCombinedQaFlag = aod550DarkTargetDeepBlueCombinedQaFlag;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double[] getAsymmetryFactorAverageOcean() {
      return asymmetryFactorAverageOcean;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorAverageOcean(Double[] asymmetryFactorAverageOcean) {
      this.asymmetryFactorAverageOcean = asymmetryFactorAverageOcean;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorAverageOcean0() {
      return asymmetryFactorAverageOcean0;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorAverageOcean0(Double asymmetryFactorAverageOcean0) {
      this.asymmetryFactorAverageOcean0 = asymmetryFactorAverageOcean0;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorAverageOcean1() {
      return asymmetryFactorAverageOcean1;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorAverageOcean1(Double asymmetryFactorAverageOcean1) {
      this.asymmetryFactorAverageOcean1 = asymmetryFactorAverageOcean1;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorAverageOcean2() {
      return asymmetryFactorAverageOcean2;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorAverageOcean2(Double asymmetryFactorAverageOcean2) {
      this.asymmetryFactorAverageOcean2 = asymmetryFactorAverageOcean2;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorAverageOcean3() {
      return asymmetryFactorAverageOcean3;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorAverageOcean3(Double asymmetryFactorAverageOcean3) {
      this.asymmetryFactorAverageOcean3 = asymmetryFactorAverageOcean3;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorAverageOcean4() {
      return asymmetryFactorAverageOcean4;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorAverageOcean4(Double asymmetryFactorAverageOcean4) {
      this.asymmetryFactorAverageOcean4 = asymmetryFactorAverageOcean4;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorAverageOcean5() {
      return asymmetryFactorAverageOcean5;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorAverageOcean5(Double asymmetryFactorAverageOcean5) {
      this.asymmetryFactorAverageOcean5 = asymmetryFactorAverageOcean5;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorAverageOcean6() {
      return asymmetryFactorAverageOcean6;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorAverageOcean6(Double asymmetryFactorAverageOcean6) {
      this.asymmetryFactorAverageOcean6 = asymmetryFactorAverageOcean6;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double[] getAsymmetryFactorBestOcean() {
      return asymmetryFactorBestOcean;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorBestOcean(Double[] asymmetryFactorBestOcean) {
      this.asymmetryFactorBestOcean = asymmetryFactorBestOcean;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorBestOcean0() {
      return asymmetryFactorBestOcean0;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorBestOcean0(Double asymmetryFactorBestOcean0) {
      this.asymmetryFactorBestOcean0 = asymmetryFactorBestOcean0;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorBestOcean1() {
      return asymmetryFactorBestOcean1;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorBestOcean1(Double asymmetryFactorBestOcean1) {
      this.asymmetryFactorBestOcean1 = asymmetryFactorBestOcean1;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorBestOcean2() {
      return asymmetryFactorBestOcean2;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorBestOcean2(Double asymmetryFactorBestOcean2) {
      this.asymmetryFactorBestOcean2 = asymmetryFactorBestOcean2;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorBestOcean3() {
      return asymmetryFactorBestOcean3;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorBestOcean3(Double asymmetryFactorBestOcean3) {
      this.asymmetryFactorBestOcean3 = asymmetryFactorBestOcean3;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorBestOcean4() {
      return asymmetryFactorBestOcean4;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorBestOcean4(Double asymmetryFactorBestOcean4) {
      this.asymmetryFactorBestOcean4 = asymmetryFactorBestOcean4;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorBestOcean5() {
      return asymmetryFactorBestOcean5;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorBestOcean5(Double asymmetryFactorBestOcean5) {
      this.asymmetryFactorBestOcean5 = asymmetryFactorBestOcean5;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getAsymmetryFactorBestOcean6() {
      return asymmetryFactorBestOcean6;
   }

   /**
    * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setAsymmetryFactorBestOcean6(Double asymmetryFactorBestOcean6) {
      this.asymmetryFactorBestOcean6 = asymmetryFactorBestOcean6;
   }

   /**
    * Field:Average Distance (number of pixels) to nearest pixel identified as
    * cloudy from each clear pixel used for Aerosol Retrieval in 10 km retrieval
    * box Tip:DFNT_INT16 Dim:2
    */
   public Double getAverageCloudPixelDistanceLandOcean() {
      return averageCloudPixelDistanceLandOcean;
   }

   /**
    * Field:Average Distance (number of pixels) to nearest pixel identified as
    * cloudy from each clear pixel used for Aerosol Retrieval in 10 km retrieval
    * box Tip:DFNT_INT16 Dim:2
    */
   public void setAverageCloudPixelDistanceLandOcean(Double averageCloudPixelDistanceLandOcean) {
      this.averageCloudPixelDistanceLandOcean = averageCloudPixelDistanceLandOcean;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double[] getBackscatteringRatioAverageOcean() {
      return backscatteringRatioAverageOcean;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioAverageOcean(Double[] backscatteringRatioAverageOcean) {
      this.backscatteringRatioAverageOcean = backscatteringRatioAverageOcean;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioAverageOcean0() {
      return backscatteringRatioAverageOcean0;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioAverageOcean0(Double backscatteringRatioAverageOcean0) {
      this.backscatteringRatioAverageOcean0 = backscatteringRatioAverageOcean0;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioAverageOcean1() {
      return backscatteringRatioAverageOcean1;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioAverageOcean1(Double backscatteringRatioAverageOcean1) {
      this.backscatteringRatioAverageOcean1 = backscatteringRatioAverageOcean1;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioAverageOcean2() {
      return backscatteringRatioAverageOcean2;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioAverageOcean2(Double backscatteringRatioAverageOcean2) {
      this.backscatteringRatioAverageOcean2 = backscatteringRatioAverageOcean2;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioAverageOcean3() {
      return backscatteringRatioAverageOcean3;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioAverageOcean3(Double backscatteringRatioAverageOcean3) {
      this.backscatteringRatioAverageOcean3 = backscatteringRatioAverageOcean3;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioAverageOcean4() {
      return backscatteringRatioAverageOcean4;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioAverageOcean4(Double backscatteringRatioAverageOcean4) {
      this.backscatteringRatioAverageOcean4 = backscatteringRatioAverageOcean4;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioAverageOcean5() {
      return backscatteringRatioAverageOcean5;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioAverageOcean5(Double backscatteringRatioAverageOcean5) {
      this.backscatteringRatioAverageOcean5 = backscatteringRatioAverageOcean5;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioAverageOcean6() {
      return backscatteringRatioAverageOcean6;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioAverageOcean6(Double backscatteringRatioAverageOcean6) {
      this.backscatteringRatioAverageOcean6 = backscatteringRatioAverageOcean6;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double[] getBackscatteringRatioBestOcean() {
      return backscatteringRatioBestOcean;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioBestOcean(Double[] backscatteringRatioBestOcean) {
      this.backscatteringRatioBestOcean = backscatteringRatioBestOcean;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioBestOcean0() {
      return backscatteringRatioBestOcean0;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioBestOcean0(Double backscatteringRatioBestOcean0) {
      this.backscatteringRatioBestOcean0 = backscatteringRatioBestOcean0;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioBestOcean1() {
      return backscatteringRatioBestOcean1;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioBestOcean1(Double backscatteringRatioBestOcean1) {
      this.backscatteringRatioBestOcean1 = backscatteringRatioBestOcean1;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioBestOcean2() {
      return backscatteringRatioBestOcean2;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioBestOcean2(Double backscatteringRatioBestOcean2) {
      this.backscatteringRatioBestOcean2 = backscatteringRatioBestOcean2;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioBestOcean3() {
      return backscatteringRatioBestOcean3;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioBestOcean3(Double backscatteringRatioBestOcean3) {
      this.backscatteringRatioBestOcean3 = backscatteringRatioBestOcean3;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioBestOcean4() {
      return backscatteringRatioBestOcean4;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioBestOcean4(Double backscatteringRatioBestOcean4) {
      this.backscatteringRatioBestOcean4 = backscatteringRatioBestOcean4;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioBestOcean5() {
      return backscatteringRatioBestOcean5;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioBestOcean5(Double backscatteringRatioBestOcean5) {
      this.backscatteringRatioBestOcean5 = backscatteringRatioBestOcean5;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getBackscatteringRatioBestOcean6() {
      return backscatteringRatioBestOcean6;
   }

   /**
    * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setBackscatteringRatioBestOcean6(Double backscatteringRatioBestOcean6) {
      this.backscatteringRatioBestOcean6 = backscatteringRatioBestOcean6;
   }

   /**
    * Field:Distance (number of pixels) to nearest pixel identified as cloudy
    * (500 m resolution) Tip:DFNT_INT16 Dim:2
    */
   public Double getCloudPixelDistanceLandOcean() {
      return cloudPixelDistanceLandOcean;
   }

   /**
    * Field:Distance (number of pixels) to nearest pixel identified as cloudy
    * (500 m resolution) Tip:DFNT_INT16 Dim:2
    */
   public void setCloudPixelDistanceLandOcean(Double cloudPixelDistanceLandOcean) {
      this.cloudPixelDistanceLandOcean = cloudPixelDistanceLandOcean;
   }

   /**
    * Field:Retrieved AOT at 0.47, 0.55,0.66 micron Tip:DFNT_INT16 Dim:3
    */
   public Double[] getCorrectedOpticalDepthLand() {
      return correctedOpticalDepthLand;
   }

   /**
    * Field:Retrieved AOT at 0.47, 0.55,0.66 micron Tip:DFNT_INT16 Dim:3
    */
   public void setCorrectedOpticalDepthLand(Double[] correctedOpticalDepthLand) {
      this.correctedOpticalDepthLand = correctedOpticalDepthLand;
   }

   /**
    * Field:Retrieved AOT at 0.47, 0.55,0.66 micron Tip:DFNT_INT16 Dim:3
    */
   public Double getCorrectedOpticalDepthLand0() {
      return correctedOpticalDepthLand0;
   }

   /**
    * Field:Retrieved AOT at 0.47, 0.55,0.66 micron Tip:DFNT_INT16 Dim:3
    */
   public void setCorrectedOpticalDepthLand0(Double correctedOpticalDepthLand0) {
      this.correctedOpticalDepthLand0 = correctedOpticalDepthLand0;
   }

   /**
    * Field:Retrieved AOT at 0.47, 0.55,0.66 micron Tip:DFNT_INT16 Dim:3
    */
   public Double getCorrectedOpticalDepthLand1() {
      return correctedOpticalDepthLand1;
   }

   /**
    * Field:Retrieved AOT at 0.47, 0.55,0.66 micron Tip:DFNT_INT16 Dim:3
    */
   public void setCorrectedOpticalDepthLand1(Double correctedOpticalDepthLand1) {
      this.correctedOpticalDepthLand1 = correctedOpticalDepthLand1;
   }

   /**
    * Field:Retrieved AOT at 0.47, 0.55,0.66 micron Tip:DFNT_INT16 Dim:3
    */
   public Double getCorrectedOpticalDepthLand2() {
      return correctedOpticalDepthLand2;
   }

   /**
    * Field:Retrieved AOT at 0.47, 0.55,0.66 micron Tip:DFNT_INT16 Dim:3
    */
   public void setCorrectedOpticalDepthLand2(Double correctedOpticalDepthLand2) {
      this.correctedOpticalDepthLand2 = correctedOpticalDepthLand2;
   }

   /**
    * Field:Retrieved AOT at 2.13 micron Tip:DFNT_INT16 Dim:2
    */
   public Double getCorrectedOpticalDepthLandWav2p1() {
      return correctedOpticalDepthLandWav2p1;
   }

   /**
    * Field:Retrieved AOT at 2.13 micron Tip:DFNT_INT16 Dim:2
    */
   public void setCorrectedOpticalDepthLandWav2p1(Double correctedOpticalDepthLandWav2p1) {
      this.correctedOpticalDepthLandWav2p1 = correctedOpticalDepthLandWav2p1;
   }

   /**
    * Field:AOT at 0.55 micron for land with all quality data (Quality
    * flag=1,2,3) Tip:DFNT_INT16 Dim:2
    */
   public Double getDeepBlueAerosolOpticalDepth550Land() {
      return deepBlueAerosolOpticalDepth550Land;
   }

   /**
    * Field:AOT at 0.55 micron for land with all quality data (Quality
    * flag=1,2,3) Tip:DFNT_INT16 Dim:2
    */
   public void setDeepBlueAerosolOpticalDepth550Land(Double deepBlueAerosolOpticalDepth550Land) {
      this.deepBlueAerosolOpticalDepth550Land = deepBlueAerosolOpticalDepth550Land;
   }

   /**
    * Field:Deep Blue AOT at 0.55 micron for land with higher quality data
    * (Quality flag=2,3) Tip:DFNT_INT16 Dim:2
    */
   public Double getDeepBlueAerosolOpticalDepth550LandBestEstimate() {
      return deepBlueAerosolOpticalDepth550LandBestEstimate;
   }

   /**
    * Field:Deep Blue AOT at 0.55 micron for land with higher quality data
    * (Quality flag=2,3) Tip:DFNT_INT16 Dim:2
    */
   public void setDeepBlueAerosolOpticalDepth550LandBestEstimate(
         Double deepBlueAerosolOpticalDepth550LandBestEstimate) {
      this.deepBlueAerosolOpticalDepth550LandBestEstimate = deepBlueAerosolOpticalDepth550LandBestEstimate;
   }

   /**
    * Field:Estimated uncertainty (one-sigma confidence envelope) of Deep Blue
    * AOT at 0.55 micron for land for all quality data (Quality flag=1,2,3)
    * Tip:DFNT_INT16 Dim:2
    */
   public Double getDeepBlueAerosolOpticalDepth550LandEstimatedUncertainty() {
      return deepBlueAerosolOpticalDepth550LandEstimatedUncertainty;
   }

   /**
    * Field:Estimated uncertainty (one-sigma confidence envelope) of Deep Blue
    * AOT at 0.55 micron for land for all quality data (Quality flag=1,2,3)
    * Tip:DFNT_INT16 Dim:2
    */
   public void setDeepBlueAerosolOpticalDepth550LandEstimatedUncertainty(
         Double deepBlueAerosolOpticalDepth550LandEstimatedUncertainty) {
      this.deepBlueAerosolOpticalDepth550LandEstimatedUncertainty = deepBlueAerosolOpticalDepth550LandEstimatedUncertainty;
   }

   /**
    * Field:Deep Blue Aerosol Confidence Flag (0= No Confidence (or fill), 1=
    * Marginal, 2= Good, 3= Very Good) Tip:DFNT_INT16 Dim:2
    */
   public Double getDeepBlueAerosolOpticalDepth550LandQaFlag() {
      return deepBlueAerosolOpticalDepth550LandQaFlag;
   }

   /**
    * Field:Deep Blue Aerosol Confidence Flag (0= No Confidence (or fill), 1=
    * Marginal, 2= Good, 3= Very Good) Tip:DFNT_INT16 Dim:2
    */
   public void setDeepBlueAerosolOpticalDepth550LandQaFlag(Double deepBlueAerosolOpticalDepth550LandQaFlag) {
      this.deepBlueAerosolOpticalDepth550LandQaFlag = deepBlueAerosolOpticalDepth550LandQaFlag;
   }

   /**
    * Field:Standard deviation of Deep Blue AOT at 0.55 micron for land with all
    * quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:2
    */
   public Double getDeepBlueAerosolOpticalDepth550LandStd() {
      return deepBlueAerosolOpticalDepth550LandStd;
   }

   /**
    * Field:Standard deviation of Deep Blue AOT at 0.55 micron for land with all
    * quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:2
    */
   public void setDeepBlueAerosolOpticalDepth550LandStd(Double deepBlueAerosolOpticalDepth550LandStd) {
      this.deepBlueAerosolOpticalDepth550LandStd = deepBlueAerosolOpticalDepth550LandStd;
   }

   /**
    * Field:Deep Blue Aerosol Algorithm Flag (0=DeepBlue, 1=Vegetated, 2=Mixed)
    * Tip:DFNT_INT16 Dim:2
    */
   public Double getDeepBlueAlgorithmFlagLand() {
      return deepBlueAlgorithmFlagLand;
   }

   /**
    * Field:Deep Blue Aerosol Algorithm Flag (0=DeepBlue, 1=Vegetated, 2=Mixed)
    * Tip:DFNT_INT16 Dim:2
    */
   public void setDeepBlueAlgorithmFlagLand(Double deepBlueAlgorithmFlagLand) {
      this.deepBlueAlgorithmFlagLand = deepBlueAlgorithmFlagLand;
   }

   /**
    * Field:Deep Blue Angstrom Exponent for land with all quality data (Quality
    * flag=1,2,3) Tip:DFNT_INT16 Dim:2
    */
   public Double getDeepBlueAngstromExponentLand() {
      return deepBlueAngstromExponentLand;
   }

   /**
    * Field:Deep Blue Angstrom Exponent for land with all quality data (Quality
    * flag=1,2,3) Tip:DFNT_INT16 Dim:2
    */
   public void setDeepBlueAngstromExponentLand(Double deepBlueAngstromExponentLand) {
      this.deepBlueAngstromExponentLand = deepBlueAngstromExponentLand;
   }

   /**
    * Field:Cloud fraction from Deep Blue Aerosol cloud mask over land
    * Tip:DFNT_INT16 Dim:2
    */
   public Double getDeepBlueCloudFractionLand() {
      return deepBlueCloudFractionLand;
   }

   /**
    * Field:Cloud fraction from Deep Blue Aerosol cloud mask over land
    * Tip:DFNT_INT16 Dim:2
    */
   public void setDeepBlueCloudFractionLand(Double deepBlueCloudFractionLand) {
      this.deepBlueCloudFractionLand = deepBlueCloudFractionLand;
   }

   /**
    * Field:Number of pixels used for AOT retrieval 0.55 micron for land
    * Tip:DFNT_INT16 Dim:2
    */
   public Double getDeepBlueNumberPixelsUsed550Land() {
      return deepBlueNumberPixelsUsed550Land;
   }

   /**
    * Field:Number of pixels used for AOT retrieval 0.55 micron for land
    * Tip:DFNT_INT16 Dim:2
    */
   public void setDeepBlueNumberPixelsUsed550Land(Double deepBlueNumberPixelsUsed550Land) {
      this.deepBlueNumberPixelsUsed550Land = deepBlueNumberPixelsUsed550Land;
   }

   /**
    * Field:AOT at 0.412, 0.47, and 0.66 micron for land with all quality data
    * (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double[] getDeepBlueSpectralAerosolOpticalDepthLand() {
      return deepBlueSpectralAerosolOpticalDepthLand;
   }

   /**
    * Field:AOT at 0.412, 0.47, and 0.66 micron for land with all quality data
    * (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralAerosolOpticalDepthLand(Double[] deepBlueSpectralAerosolOpticalDepthLand) {
      this.deepBlueSpectralAerosolOpticalDepthLand = deepBlueSpectralAerosolOpticalDepthLand;
   }

   /**
    * Field:AOT at 0.412, 0.47, and 0.66 micron for land with all quality data
    * (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralAerosolOpticalDepthLand0() {
      return deepBlueSpectralAerosolOpticalDepthLand0;
   }

   /**
    * Field:AOT at 0.412, 0.47, and 0.66 micron for land with all quality data
    * (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralAerosolOpticalDepthLand0(Double deepBlueSpectralAerosolOpticalDepthLand0) {
      this.deepBlueSpectralAerosolOpticalDepthLand0 = deepBlueSpectralAerosolOpticalDepthLand0;
   }

   /**
    * Field:AOT at 0.412, 0.47, and 0.66 micron for land with all quality data
    * (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralAerosolOpticalDepthLand1() {
      return deepBlueSpectralAerosolOpticalDepthLand1;
   }

   /**
    * Field:AOT at 0.412, 0.47, and 0.66 micron for land with all quality data
    * (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralAerosolOpticalDepthLand1(Double deepBlueSpectralAerosolOpticalDepthLand1) {
      this.deepBlueSpectralAerosolOpticalDepthLand1 = deepBlueSpectralAerosolOpticalDepthLand1;
   }

   /**
    * Field:AOT at 0.412, 0.47, and 0.66 micron for land with all quality data
    * (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralAerosolOpticalDepthLand2() {
      return deepBlueSpectralAerosolOpticalDepthLand2;
   }

   /**
    * Field:AOT at 0.412, 0.47, and 0.66 micron for land with all quality data
    * (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralAerosolOpticalDepthLand2(Double deepBlueSpectralAerosolOpticalDepthLand2) {
      this.deepBlueSpectralAerosolOpticalDepthLand2 = deepBlueSpectralAerosolOpticalDepthLand2;
   }

   /**
    * Field:Deep Blue Single Scattering Albedo at 0.412, 0.47, and 0.66 micron
    * for land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double[] getDeepBlueSpectralSingleScatteringAlbedoLand() {
      return deepBlueSpectralSingleScatteringAlbedoLand;
   }

   /**
    * Field:Deep Blue Single Scattering Albedo at 0.412, 0.47, and 0.66 micron
    * for land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralSingleScatteringAlbedoLand(Double[] deepBlueSpectralSingleScatteringAlbedoLand) {
      this.deepBlueSpectralSingleScatteringAlbedoLand = deepBlueSpectralSingleScatteringAlbedoLand;
   }

   /**
    * Field:Deep Blue Single Scattering Albedo at 0.412, 0.47, and 0.66 micron
    * for land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralSingleScatteringAlbedoLand0() {
      return deepBlueSpectralSingleScatteringAlbedoLand0;
   }

   /**
    * Field:Deep Blue Single Scattering Albedo at 0.412, 0.47, and 0.66 micron
    * for land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralSingleScatteringAlbedoLand0(Double deepBlueSpectralSingleScatteringAlbedoLand0) {
      this.deepBlueSpectralSingleScatteringAlbedoLand0 = deepBlueSpectralSingleScatteringAlbedoLand0;
   }

   /**
    * Field:Deep Blue Single Scattering Albedo at 0.412, 0.47, and 0.66 micron
    * for land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralSingleScatteringAlbedoLand1() {
      return deepBlueSpectralSingleScatteringAlbedoLand1;
   }

   /**
    * Field:Deep Blue Single Scattering Albedo at 0.412, 0.47, and 0.66 micron
    * for land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralSingleScatteringAlbedoLand1(Double deepBlueSpectralSingleScatteringAlbedoLand1) {
      this.deepBlueSpectralSingleScatteringAlbedoLand1 = deepBlueSpectralSingleScatteringAlbedoLand1;
   }

   /**
    * Field:Deep Blue Single Scattering Albedo at 0.412, 0.47, and 0.66 micron
    * for land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralSingleScatteringAlbedoLand2() {
      return deepBlueSpectralSingleScatteringAlbedoLand2;
   }

   /**
    * Field:Deep Blue Single Scattering Albedo at 0.412, 0.47, and 0.66 micron
    * for land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralSingleScatteringAlbedoLand2(Double deepBlueSpectralSingleScatteringAlbedoLand2) {
      this.deepBlueSpectralSingleScatteringAlbedoLand2 = deepBlueSpectralSingleScatteringAlbedoLand2;
   }

   /**
    * Field:Deep Blue Surface Reflectance at 0.412, 0.47, and 0.66 micron for
    * land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double[] getDeepBlueSpectralSurfaceReflectanceLand() {
      return deepBlueSpectralSurfaceReflectanceLand;
   }

   /**
    * Field:Deep Blue Surface Reflectance at 0.412, 0.47, and 0.66 micron for
    * land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralSurfaceReflectanceLand(Double[] deepBlueSpectralSurfaceReflectanceLand) {
      this.deepBlueSpectralSurfaceReflectanceLand = deepBlueSpectralSurfaceReflectanceLand;
   }

   /**
    * Field:Deep Blue Surface Reflectance at 0.412, 0.47, and 0.66 micron for
    * land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralSurfaceReflectanceLand0() {
      return deepBlueSpectralSurfaceReflectanceLand0;
   }

   /**
    * Field:Deep Blue Surface Reflectance at 0.412, 0.47, and 0.66 micron for
    * land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralSurfaceReflectanceLand0(Double deepBlueSpectralSurfaceReflectanceLand0) {
      this.deepBlueSpectralSurfaceReflectanceLand0 = deepBlueSpectralSurfaceReflectanceLand0;
   }

   /**
    * Field:Deep Blue Surface Reflectance at 0.412, 0.47, and 0.66 micron for
    * land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralSurfaceReflectanceLand1() {
      return deepBlueSpectralSurfaceReflectanceLand1;
   }

   /**
    * Field:Deep Blue Surface Reflectance at 0.412, 0.47, and 0.66 micron for
    * land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralSurfaceReflectanceLand1(Double deepBlueSpectralSurfaceReflectanceLand1) {
      this.deepBlueSpectralSurfaceReflectanceLand1 = deepBlueSpectralSurfaceReflectanceLand1;
   }

   /**
    * Field:Deep Blue Surface Reflectance at 0.412, 0.47, and 0.66 micron for
    * land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralSurfaceReflectanceLand2() {
      return deepBlueSpectralSurfaceReflectanceLand2;
   }

   /**
    * Field:Deep Blue Surface Reflectance at 0.412, 0.47, and 0.66 micron for
    * land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralSurfaceReflectanceLand2(Double deepBlueSpectralSurfaceReflectanceLand2) {
      this.deepBlueSpectralSurfaceReflectanceLand2 = deepBlueSpectralSurfaceReflectanceLand2;
   }

   /**
    * Field:Average measured TOA reflectance after cloud screening at 0.412,
    * 0.47, and 0.66 micron for land Tip:DFNT_INT16 Dim:3
    */
   public Double[] getDeepBlueSpectralToaReflectanceLand() {
      return deepBlueSpectralToaReflectanceLand;
   }

   /**
    * Field:Average measured TOA reflectance after cloud screening at 0.412,
    * 0.47, and 0.66 micron for land Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralToaReflectanceLand(Double[] deepBlueSpectralToaReflectanceLand) {
      this.deepBlueSpectralToaReflectanceLand = deepBlueSpectralToaReflectanceLand;
   }

   /**
    * Field:Average measured TOA reflectance after cloud screening at 0.412,
    * 0.47, and 0.66 micron for land Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralToaReflectanceLand0() {
      return deepBlueSpectralToaReflectanceLand0;
   }

   /**
    * Field:Average measured TOA reflectance after cloud screening at 0.412,
    * 0.47, and 0.66 micron for land Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralToaReflectanceLand0(Double deepBlueSpectralToaReflectanceLand0) {
      this.deepBlueSpectralToaReflectanceLand0 = deepBlueSpectralToaReflectanceLand0;
   }

   /**
    * Field:Average measured TOA reflectance after cloud screening at 0.412,
    * 0.47, and 0.66 micron for land Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralToaReflectanceLand1() {
      return deepBlueSpectralToaReflectanceLand1;
   }

   /**
    * Field:Average measured TOA reflectance after cloud screening at 0.412,
    * 0.47, and 0.66 micron for land Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralToaReflectanceLand1(Double deepBlueSpectralToaReflectanceLand1) {
      this.deepBlueSpectralToaReflectanceLand1 = deepBlueSpectralToaReflectanceLand1;
   }

   /**
    * Field:Average measured TOA reflectance after cloud screening at 0.412,
    * 0.47, and 0.66 micron for land Tip:DFNT_INT16 Dim:3
    */
   public Double getDeepBlueSpectralToaReflectanceLand2() {
      return deepBlueSpectralToaReflectanceLand2;
   }

   /**
    * Field:Average measured TOA reflectance after cloud screening at 0.412,
    * 0.47, and 0.66 micron for land Tip:DFNT_INT16 Dim:3
    */
   public void setDeepBlueSpectralToaReflectanceLand2(Double deepBlueSpectralToaReflectanceLand2) {
      this.deepBlueSpectralToaReflectanceLand2 = deepBlueSpectralToaReflectanceLand2;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.55um For easy L3
    * processing Tip:DFNT_INT16 Dim:2
    */
   public Double getEffectiveOpticalDepth0p55umOcean() {
      return effectiveOpticalDepth0p55umOcean;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.55um For easy L3
    * processing Tip:DFNT_INT16 Dim:2
    */
   public void setEffectiveOpticalDepth0p55umOcean(Double effectiveOpticalDepth0p55umOcean) {
      this.effectiveOpticalDepth0p55umOcean = effectiveOpticalDepth0p55umOcean;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double[] getEffectiveOpticalDepthAverageOcean() {
      return effectiveOpticalDepthAverageOcean;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthAverageOcean(Double[] effectiveOpticalDepthAverageOcean) {
      this.effectiveOpticalDepthAverageOcean = effectiveOpticalDepthAverageOcean;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthAverageOcean0() {
      return effectiveOpticalDepthAverageOcean0;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthAverageOcean0(Double effectiveOpticalDepthAverageOcean0) {
      this.effectiveOpticalDepthAverageOcean0 = effectiveOpticalDepthAverageOcean0;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthAverageOcean1() {
      return effectiveOpticalDepthAverageOcean1;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthAverageOcean1(Double effectiveOpticalDepthAverageOcean1) {
      this.effectiveOpticalDepthAverageOcean1 = effectiveOpticalDepthAverageOcean1;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthAverageOcean2() {
      return effectiveOpticalDepthAverageOcean2;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthAverageOcean2(Double effectiveOpticalDepthAverageOcean2) {
      this.effectiveOpticalDepthAverageOcean2 = effectiveOpticalDepthAverageOcean2;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthAverageOcean3() {
      return effectiveOpticalDepthAverageOcean3;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthAverageOcean3(Double effectiveOpticalDepthAverageOcean3) {
      this.effectiveOpticalDepthAverageOcean3 = effectiveOpticalDepthAverageOcean3;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthAverageOcean4() {
      return effectiveOpticalDepthAverageOcean4;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthAverageOcean4(Double effectiveOpticalDepthAverageOcean4) {
      this.effectiveOpticalDepthAverageOcean4 = effectiveOpticalDepthAverageOcean4;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthAverageOcean5() {
      return effectiveOpticalDepthAverageOcean5;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthAverageOcean5(Double effectiveOpticalDepthAverageOcean5) {
      this.effectiveOpticalDepthAverageOcean5 = effectiveOpticalDepthAverageOcean5;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthAverageOcean6() {
      return effectiveOpticalDepthAverageOcean6;
   }

   /**
    * Field:Retrieved AOT for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthAverageOcean6(Double effectiveOpticalDepthAverageOcean6) {
      this.effectiveOpticalDepthAverageOcean6 = effectiveOpticalDepthAverageOcean6;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double[] getEffectiveOpticalDepthBestOcean() {
      return effectiveOpticalDepthBestOcean;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthBestOcean(Double[] effectiveOpticalDepthBestOcean) {
      this.effectiveOpticalDepthBestOcean = effectiveOpticalDepthBestOcean;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthBestOcean0() {
      return effectiveOpticalDepthBestOcean0;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthBestOcean0(Double effectiveOpticalDepthBestOcean0) {
      this.effectiveOpticalDepthBestOcean0 = effectiveOpticalDepthBestOcean0;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthBestOcean1() {
      return effectiveOpticalDepthBestOcean1;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthBestOcean1(Double effectiveOpticalDepthBestOcean1) {
      this.effectiveOpticalDepthBestOcean1 = effectiveOpticalDepthBestOcean1;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthBestOcean2() {
      return effectiveOpticalDepthBestOcean2;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthBestOcean2(Double effectiveOpticalDepthBestOcean2) {
      this.effectiveOpticalDepthBestOcean2 = effectiveOpticalDepthBestOcean2;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthBestOcean3() {
      return effectiveOpticalDepthBestOcean3;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthBestOcean3(Double effectiveOpticalDepthBestOcean3) {
      this.effectiveOpticalDepthBestOcean3 = effectiveOpticalDepthBestOcean3;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthBestOcean4() {
      return effectiveOpticalDepthBestOcean4;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthBestOcean4(Double effectiveOpticalDepthBestOcean4) {
      this.effectiveOpticalDepthBestOcean4 = effectiveOpticalDepthBestOcean4;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthBestOcean5() {
      return effectiveOpticalDepthBestOcean5;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthBestOcean5(Double effectiveOpticalDepthBestOcean5) {
      this.effectiveOpticalDepthBestOcean5 = effectiveOpticalDepthBestOcean5;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveOpticalDepthBestOcean6() {
      return effectiveOpticalDepthBestOcean6;
   }

   /**
    * Field:Retrieved AOT for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveOpticalDepthBestOcean6(Double effectiveOpticalDepthBestOcean6) {
      this.effectiveOpticalDepthBestOcean6 = effectiveOpticalDepthBestOcean6;
   }

   /**
    * Field:Effective_Radius at 0.55 micron for 'best' (1) and 'average' (2)
    * solutions Tip:DFNT_INT16 Dim:3
    */
   public Double[] getEffectiveRadiusOcean() {
      return effectiveRadiusOcean;
   }

   /**
    * Field:Effective_Radius at 0.55 micron for 'best' (1) and 'average' (2)
    * solutions Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveRadiusOcean(Double[] effectiveRadiusOcean) {
      this.effectiveRadiusOcean = effectiveRadiusOcean;
   }

   /**
    * Field:Effective_Radius at 0.55 micron for 'best' (1) and 'average' (2)
    * solutions Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveRadiusOcean0() {
      return effectiveRadiusOcean0;
   }

   /**
    * Field:Effective_Radius at 0.55 micron for 'best' (1) and 'average' (2)
    * solutions Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveRadiusOcean0(Double effectiveRadiusOcean0) {
      this.effectiveRadiusOcean0 = effectiveRadiusOcean0;
   }

   /**
    * Field:Effective_Radius at 0.55 micron for 'best' (1) and 'average' (2)
    * solutions Tip:DFNT_INT16 Dim:3
    */
   public Double getEffectiveRadiusOcean1() {
      return effectiveRadiusOcean1;
   }

   /**
    * Field:Effective_Radius at 0.55 micron for 'best' (1) and 'average' (2)
    * solutions Tip:DFNT_INT16 Dim:3
    */
   public void setEffectiveRadiusOcean1(Double effectiveRadiusOcean1) {
      this.effectiveRadiusOcean1 = effectiveRadiusOcean1;
   }

   /**
    * Field:Spectral Fitting error for inversion over land Tip:DFNT_INT16 Dim:2
    */
   public Double getFittingErrorLand() {
      return fittingErrorLand;
   }

   /**
    * Field:Spectral Fitting error for inversion over land Tip:DFNT_INT16 Dim:2
    */
   public void setFittingErrorLand(Double fittingErrorLand) {
      this.fittingErrorLand = fittingErrorLand;
   }

   /**
    * Field:Glint Angle Tip:DFNT_INT16 Dim:2
    */
   public Double getGlintAngle() {
      return glintAngle;
   }

   /**
    * Field:Glint Angle Tip:DFNT_INT16 Dim:2
    */
   public void setGlintAngle(Double glintAngle) {
      this.glintAngle = glintAngle;
   }

   /**
    * Field:AOT at 0.55 micron for both ocean (Average) and land (corrected)
    * with all quality data (Quality flag=0,1,2,3) Tip:DFNT_INT16 Dim:2
    */
   public Double getImageOpticalDepthLandAndOcean() {
      return imageOpticalDepthLandAndOcean;
   }

   /**
    * Field:AOT at 0.55 micron for both ocean (Average) and land (corrected)
    * with all quality data (Quality flag=0,1,2,3) Tip:DFNT_INT16 Dim:2
    */
   public void setImageOpticalDepthLandAndOcean(Double imageOpticalDepthLandAndOcean) {
      this.imageOpticalDepthLandAndOcean = imageOpticalDepthLandAndOcean;
   }

   /**
    * Field:Quality Flag for Land and ocean Aerosol retreivals 0= bad 1 =
    * Marginal 2= Good 3=Very Good) Tip:DFNT_INT16 Dim:2
    */
   public Double getLandOceanQualityFlag() {
      return landOceanQualityFlag;
   }

   /**
    * Field:Quality Flag for Land and ocean Aerosol retreivals 0= bad 1 =
    * Marginal 2= Good 3=Very Good) Tip:DFNT_INT16 Dim:2
    */
   public void setLandOceanQualityFlag(Double landOceanQualityFlag) {
      this.landOceanQualityFlag = landOceanQualityFlag;
   }

   /**
    * Field:Land_sea_Flag(based on MOD03 Landsea mask 0 = Ocean, 1 = Land and
    * Ephemeral water 2 =Coastal) Tip:DFNT_INT16 Dim:2
    */
   public Double getLandSeaFlag() {
      return landSeaFlag;
   }

   /**
    * Field:Land_sea_Flag(based on MOD03 Landsea mask 0 = Ocean, 1 = Land and
    * Ephemeral water 2 =Coastal) Tip:DFNT_INT16 Dim:2
    */
   public void setLandSeaFlag(Double landSeaFlag) {
      this.landSeaFlag = landSeaFlag;
   }

   /**
    * Field:Residual of least squares fitting for inversion over land for best
    * (1) and average (2) solutions Tip:DFNT_INT16 Dim:3
    */
   public Double[] getLeastSquaresErrorOcean() {
      return leastSquaresErrorOcean;
   }

   /**
    * Field:Residual of least squares fitting for inversion over land for best
    * (1) and average (2) solutions Tip:DFNT_INT16 Dim:3
    */
   public void setLeastSquaresErrorOcean(Double[] leastSquaresErrorOcean) {
      this.leastSquaresErrorOcean = leastSquaresErrorOcean;
   }

   /**
    * Field:Residual of least squares fitting for inversion over land for best
    * (1) and average (2) solutions Tip:DFNT_INT16 Dim:3
    */
   public Double getLeastSquaresErrorOcean0() {
      return leastSquaresErrorOcean0;
   }

   /**
    * Field:Residual of least squares fitting for inversion over land for best
    * (1) and average (2) solutions Tip:DFNT_INT16 Dim:3
    */
   public void setLeastSquaresErrorOcean0(Double leastSquaresErrorOcean0) {
      this.leastSquaresErrorOcean0 = leastSquaresErrorOcean0;
   }

   /**
    * Field:Residual of least squares fitting for inversion over land for best
    * (1) and average (2) solutions Tip:DFNT_INT16 Dim:3
    */
   public Double getLeastSquaresErrorOcean1() {
      return leastSquaresErrorOcean1;
   }

   /**
    * Field:Residual of least squares fitting for inversion over land for best
    * (1) and average (2) solutions Tip:DFNT_INT16 Dim:3
    */
   public void setLeastSquaresErrorOcean1(Double leastSquaresErrorOcean1) {
      this.leastSquaresErrorOcean1 = leastSquaresErrorOcean1;
   }

   /**
    * Field:Estimated Column Mass(per area) using assumed mass extinction
    * efficiency Tip:DFNT_FLOAT32 Dim:2
    */
   public Double getMassConcentrationLand() {
      return massConcentrationLand;
   }

   /**
    * Field:Estimated Column Mass(per area) using assumed mass extinction
    * efficiency Tip:DFNT_FLOAT32 Dim:2
    */
   public void setMassConcentrationLand(Double massConcentrationLand) {
      this.massConcentrationLand = massConcentrationLand;
   }

   /**
    * Field:Estimated Column Mass (per area) using assumed mass extinction
    * coefficients for 'best' (1) and 'average' (2) solutions Tip:DFNT_FLOAT32
    * Dim:3
    */
   public Double[] getMassConcentrationOcean() {
      return massConcentrationOcean;
   }

   /**
    * Field:Estimated Column Mass (per area) using assumed mass extinction
    * coefficients for 'best' (1) and 'average' (2) solutions Tip:DFNT_FLOAT32
    * Dim:3
    */
   public void setMassConcentrationOcean(Double[] massConcentrationOcean) {
      this.massConcentrationOcean = massConcentrationOcean;
   }

   /**
    * Field:Estimated Column Mass (per area) using assumed mass extinction
    * coefficients for 'best' (1) and 'average' (2) solutions Tip:DFNT_FLOAT32
    * Dim:3
    */
   public Double getMassConcentrationOcean0() {
      return massConcentrationOcean0;
   }

   /**
    * Field:Estimated Column Mass (per area) using assumed mass extinction
    * coefficients for 'best' (1) and 'average' (2) solutions Tip:DFNT_FLOAT32
    * Dim:3
    */
   public void setMassConcentrationOcean0(Double massConcentrationOcean0) {
      this.massConcentrationOcean0 = massConcentrationOcean0;
   }

   /**
    * Field:Estimated Column Mass (per area) using assumed mass extinction
    * coefficients for 'best' (1) and 'average' (2) solutions Tip:DFNT_FLOAT32
    * Dim:3
    */
   public Double getMassConcentrationOcean1() {
      return massConcentrationOcean1;
   }

   /**
    * Field:Estimated Column Mass (per area) using assumed mass extinction
    * coefficients for 'best' (1) and 'average' (2) solutions Tip:DFNT_FLOAT32
    * Dim:3
    */
   public void setMassConcentrationOcean1(Double massConcentrationOcean1) {
      this.massConcentrationOcean1 = massConcentrationOcean1;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double[] getMeanReflectanceLand() {
      return meanReflectanceLand;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceLand(Double[] meanReflectanceLand) {
      this.meanReflectanceLand = meanReflectanceLand;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceLand0() {
      return meanReflectanceLand0;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceLand0(Double meanReflectanceLand0) {
      this.meanReflectanceLand0 = meanReflectanceLand0;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceLand1() {
      return meanReflectanceLand1;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceLand1(Double meanReflectanceLand1) {
      this.meanReflectanceLand1 = meanReflectanceLand1;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceLand2() {
      return meanReflectanceLand2;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceLand2(Double meanReflectanceLand2) {
      this.meanReflectanceLand2 = meanReflectanceLand2;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceLand3() {
      return meanReflectanceLand3;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceLand3(Double meanReflectanceLand3) {
      this.meanReflectanceLand3 = meanReflectanceLand3;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceLand4() {
      return meanReflectanceLand4;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceLand4(Double meanReflectanceLand4) {
      this.meanReflectanceLand4 = meanReflectanceLand4;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceLand5() {
      return meanReflectanceLand5;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceLand5(Double meanReflectanceLand5) {
      this.meanReflectanceLand5 = meanReflectanceLand5;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceLand6() {
      return meanReflectanceLand6;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceLand6(Double meanReflectanceLand6) {
      this.meanReflectanceLand6 = meanReflectanceLand6;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceLand7() {
      return meanReflectanceLand7;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceLand7(Double meanReflectanceLand7) {
      this.meanReflectanceLand7 = meanReflectanceLand7;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceLand8() {
      return meanReflectanceLand8;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceLand8(Double meanReflectanceLand8) {
      this.meanReflectanceLand8 = meanReflectanceLand8;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceLand9() {
      return meanReflectanceLand9;
   }

   /**
    * Field:Mean reflectance of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceLand9(Double meanReflectanceLand9) {
      this.meanReflectanceLand9 = meanReflectanceLand9;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double[] getMeanReflectanceOcean() {
      return meanReflectanceOcean;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceOcean(Double[] meanReflectanceOcean) {
      this.meanReflectanceOcean = meanReflectanceOcean;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceOcean0() {
      return meanReflectanceOcean0;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceOcean0(Double meanReflectanceOcean0) {
      this.meanReflectanceOcean0 = meanReflectanceOcean0;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceOcean1() {
      return meanReflectanceOcean1;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceOcean1(Double meanReflectanceOcean1) {
      this.meanReflectanceOcean1 = meanReflectanceOcean1;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceOcean2() {
      return meanReflectanceOcean2;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceOcean2(Double meanReflectanceOcean2) {
      this.meanReflectanceOcean2 = meanReflectanceOcean2;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceOcean3() {
      return meanReflectanceOcean3;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceOcean3(Double meanReflectanceOcean3) {
      this.meanReflectanceOcean3 = meanReflectanceOcean3;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceOcean4() {
      return meanReflectanceOcean4;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceOcean4(Double meanReflectanceOcean4) {
      this.meanReflectanceOcean4 = meanReflectanceOcean4;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceOcean5() {
      return meanReflectanceOcean5;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceOcean5(Double meanReflectanceOcean5) {
      this.meanReflectanceOcean5 = meanReflectanceOcean5;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceOcean6() {
      return meanReflectanceOcean6;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceOcean6(Double meanReflectanceOcean6) {
      this.meanReflectanceOcean6 = meanReflectanceOcean6;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceOcean7() {
      return meanReflectanceOcean7;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceOcean7(Double meanReflectanceOcean7) {
      this.meanReflectanceOcean7 = meanReflectanceOcean7;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceOcean8() {
      return meanReflectanceOcean8;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceOcean8(Double meanReflectanceOcean8) {
      this.meanReflectanceOcean8 = meanReflectanceOcean8;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getMeanReflectanceOcean9() {
      return meanReflectanceOcean9;
   }

   /**
    * Field:Mean reflectance of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setMeanReflectanceOcean9(Double meanReflectanceOcean9) {
      this.meanReflectanceOcean9 = meanReflectanceOcean9;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double[] getNumberPixelsUsedLand() {
      return numberPixelsUsedLand;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedLand(Double[] numberPixelsUsedLand) {
      this.numberPixelsUsedLand = numberPixelsUsedLand;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedLand0() {
      return numberPixelsUsedLand0;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedLand0(Double numberPixelsUsedLand0) {
      this.numberPixelsUsedLand0 = numberPixelsUsedLand0;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedLand1() {
      return numberPixelsUsedLand1;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedLand1(Double numberPixelsUsedLand1) {
      this.numberPixelsUsedLand1 = numberPixelsUsedLand1;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedLand2() {
      return numberPixelsUsedLand2;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedLand2(Double numberPixelsUsedLand2) {
      this.numberPixelsUsedLand2 = numberPixelsUsedLand2;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedLand3() {
      return numberPixelsUsedLand3;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedLand3(Double numberPixelsUsedLand3) {
      this.numberPixelsUsedLand3 = numberPixelsUsedLand3;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedLand4() {
      return numberPixelsUsedLand4;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedLand4(Double numberPixelsUsedLand4) {
      this.numberPixelsUsedLand4 = numberPixelsUsedLand4;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedLand5() {
      return numberPixelsUsedLand5;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedLand5(Double numberPixelsUsedLand5) {
      this.numberPixelsUsedLand5 = numberPixelsUsedLand5;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedLand6() {
      return numberPixelsUsedLand6;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedLand6(Double numberPixelsUsedLand6) {
      this.numberPixelsUsedLand6 = numberPixelsUsedLand6;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedLand7() {
      return numberPixelsUsedLand7;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedLand7(Double numberPixelsUsedLand7) {
      this.numberPixelsUsedLand7 = numberPixelsUsedLand7;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedLand8() {
      return numberPixelsUsedLand8;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedLand8(Double numberPixelsUsedLand8) {
      this.numberPixelsUsedLand8 = numberPixelsUsedLand8;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedLand9() {
      return numberPixelsUsedLand9;
   }

   /**
    * Field:Number of pixels used for land retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedLand9(Double numberPixelsUsedLand9) {
      this.numberPixelsUsedLand9 = numberPixelsUsedLand9;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double[] getNumberPixelsUsedOcean() {
      return numberPixelsUsedOcean;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedOcean(Double[] numberPixelsUsedOcean) {
      this.numberPixelsUsedOcean = numberPixelsUsedOcean;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedOcean0() {
      return numberPixelsUsedOcean0;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedOcean0(Double numberPixelsUsedOcean0) {
      this.numberPixelsUsedOcean0 = numberPixelsUsedOcean0;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedOcean1() {
      return numberPixelsUsedOcean1;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedOcean1(Double numberPixelsUsedOcean1) {
      this.numberPixelsUsedOcean1 = numberPixelsUsedOcean1;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedOcean2() {
      return numberPixelsUsedOcean2;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedOcean2(Double numberPixelsUsedOcean2) {
      this.numberPixelsUsedOcean2 = numberPixelsUsedOcean2;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedOcean3() {
      return numberPixelsUsedOcean3;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedOcean3(Double numberPixelsUsedOcean3) {
      this.numberPixelsUsedOcean3 = numberPixelsUsedOcean3;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedOcean4() {
      return numberPixelsUsedOcean4;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedOcean4(Double numberPixelsUsedOcean4) {
      this.numberPixelsUsedOcean4 = numberPixelsUsedOcean4;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedOcean5() {
      return numberPixelsUsedOcean5;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedOcean5(Double numberPixelsUsedOcean5) {
      this.numberPixelsUsedOcean5 = numberPixelsUsedOcean5;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedOcean6() {
      return numberPixelsUsedOcean6;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedOcean6(Double numberPixelsUsedOcean6) {
      this.numberPixelsUsedOcean6 = numberPixelsUsedOcean6;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedOcean7() {
      return numberPixelsUsedOcean7;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedOcean7(Double numberPixelsUsedOcean7) {
      this.numberPixelsUsedOcean7 = numberPixelsUsedOcean7;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedOcean8() {
      return numberPixelsUsedOcean8;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedOcean8(Double numberPixelsUsedOcean8) {
      this.numberPixelsUsedOcean8 = numberPixelsUsedOcean8;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public Double getNumberPixelsUsedOcean9() {
      return numberPixelsUsedOcean9;
   }

   /**
    * Field:Number of pixels used for ocean retrieval at
    * 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP:
    * 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
    */
   public void setNumberPixelsUsedOcean9(Double numberPixelsUsedOcean9) {
      this.numberPixelsUsedOcean9 = numberPixelsUsedOcean9;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public Double[] getOpticalDepthByModelsOcean() {
      return opticalDepthByModelsOcean;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthByModelsOcean(Double[] opticalDepthByModelsOcean) {
      this.opticalDepthByModelsOcean = opticalDepthByModelsOcean;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthByModelsOcean0() {
      return opticalDepthByModelsOcean0;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthByModelsOcean0(Double opticalDepthByModelsOcean0) {
      this.opticalDepthByModelsOcean0 = opticalDepthByModelsOcean0;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthByModelsOcean1() {
      return opticalDepthByModelsOcean1;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthByModelsOcean1(Double opticalDepthByModelsOcean1) {
      this.opticalDepthByModelsOcean1 = opticalDepthByModelsOcean1;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthByModelsOcean2() {
      return opticalDepthByModelsOcean2;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthByModelsOcean2(Double opticalDepthByModelsOcean2) {
      this.opticalDepthByModelsOcean2 = opticalDepthByModelsOcean2;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthByModelsOcean3() {
      return opticalDepthByModelsOcean3;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthByModelsOcean3(Double opticalDepthByModelsOcean3) {
      this.opticalDepthByModelsOcean3 = opticalDepthByModelsOcean3;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthByModelsOcean4() {
      return opticalDepthByModelsOcean4;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthByModelsOcean4(Double opticalDepthByModelsOcean4) {
      this.opticalDepthByModelsOcean4 = opticalDepthByModelsOcean4;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthByModelsOcean5() {
      return opticalDepthByModelsOcean5;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthByModelsOcean5(Double opticalDepthByModelsOcean5) {
      this.opticalDepthByModelsOcean5 = opticalDepthByModelsOcean5;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthByModelsOcean6() {
      return opticalDepthByModelsOcean6;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthByModelsOcean6(Double opticalDepthByModelsOcean6) {
      this.opticalDepthByModelsOcean6 = opticalDepthByModelsOcean6;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthByModelsOcean7() {
      return opticalDepthByModelsOcean7;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthByModelsOcean7(Double opticalDepthByModelsOcean7) {
      this.opticalDepthByModelsOcean7 = opticalDepthByModelsOcean7;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthByModelsOcean8() {
      return opticalDepthByModelsOcean8;
   }

   /**
    * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average
    * solution) Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthByModelsOcean8(Double opticalDepthByModelsOcean8) {
      this.opticalDepthByModelsOcean8 = opticalDepthByModelsOcean8;
   }

   /**
    * Field:AOT at 0.55 micron for both ocean (Average) (Quality flag=1,2,3) and
    * land (corrected) (Quality flag=3) Tip:DFNT_INT16 Dim:2
    */
   public Double getOpticalDepthLandAndOcean() {
      return opticalDepthLandAndOcean;
   }

   /**
    * Field:AOT at 0.55 micron for both ocean (Average) (Quality flag=1,2,3) and
    * land (corrected) (Quality flag=3) Tip:DFNT_INT16 Dim:2
    */
   public void setOpticalDepthLandAndOcean(Double opticalDepthLandAndOcean) {
      this.opticalDepthLandAndOcean = opticalDepthLandAndOcean;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double[] getOpticalDepthLargeAverageOcean() {
      return opticalDepthLargeAverageOcean;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeAverageOcean(Double[] opticalDepthLargeAverageOcean) {
      this.opticalDepthLargeAverageOcean = opticalDepthLargeAverageOcean;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeAverageOcean0() {
      return opticalDepthLargeAverageOcean0;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeAverageOcean0(Double opticalDepthLargeAverageOcean0) {
      this.opticalDepthLargeAverageOcean0 = opticalDepthLargeAverageOcean0;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeAverageOcean1() {
      return opticalDepthLargeAverageOcean1;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeAverageOcean1(Double opticalDepthLargeAverageOcean1) {
      this.opticalDepthLargeAverageOcean1 = opticalDepthLargeAverageOcean1;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeAverageOcean2() {
      return opticalDepthLargeAverageOcean2;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeAverageOcean2(Double opticalDepthLargeAverageOcean2) {
      this.opticalDepthLargeAverageOcean2 = opticalDepthLargeAverageOcean2;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeAverageOcean3() {
      return opticalDepthLargeAverageOcean3;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeAverageOcean3(Double opticalDepthLargeAverageOcean3) {
      this.opticalDepthLargeAverageOcean3 = opticalDepthLargeAverageOcean3;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeAverageOcean4() {
      return opticalDepthLargeAverageOcean4;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeAverageOcean4(Double opticalDepthLargeAverageOcean4) {
      this.opticalDepthLargeAverageOcean4 = opticalDepthLargeAverageOcean4;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeAverageOcean5() {
      return opticalDepthLargeAverageOcean5;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeAverageOcean5(Double opticalDepthLargeAverageOcean5) {
      this.opticalDepthLargeAverageOcean5 = opticalDepthLargeAverageOcean5;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeAverageOcean6() {
      return opticalDepthLargeAverageOcean6;
   }

   /**
    * Field:Retrieved AOT of large mode for 'average' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeAverageOcean6(Double opticalDepthLargeAverageOcean6) {
      this.opticalDepthLargeAverageOcean6 = opticalDepthLargeAverageOcean6;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double[] getOpticalDepthLargeBestOcean() {
      return opticalDepthLargeBestOcean;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeBestOcean(Double[] opticalDepthLargeBestOcean) {
      this.opticalDepthLargeBestOcean = opticalDepthLargeBestOcean;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeBestOcean0() {
      return opticalDepthLargeBestOcean0;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeBestOcean0(Double opticalDepthLargeBestOcean0) {
      this.opticalDepthLargeBestOcean0 = opticalDepthLargeBestOcean0;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeBestOcean1() {
      return opticalDepthLargeBestOcean1;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeBestOcean1(Double opticalDepthLargeBestOcean1) {
      this.opticalDepthLargeBestOcean1 = opticalDepthLargeBestOcean1;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeBestOcean2() {
      return opticalDepthLargeBestOcean2;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeBestOcean2(Double opticalDepthLargeBestOcean2) {
      this.opticalDepthLargeBestOcean2 = opticalDepthLargeBestOcean2;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeBestOcean3() {
      return opticalDepthLargeBestOcean3;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeBestOcean3(Double opticalDepthLargeBestOcean3) {
      this.opticalDepthLargeBestOcean3 = opticalDepthLargeBestOcean3;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeBestOcean4() {
      return opticalDepthLargeBestOcean4;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeBestOcean4(Double opticalDepthLargeBestOcean4) {
      this.opticalDepthLargeBestOcean4 = opticalDepthLargeBestOcean4;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeBestOcean5() {
      return opticalDepthLargeBestOcean5;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeBestOcean5(Double opticalDepthLargeBestOcean5) {
      this.opticalDepthLargeBestOcean5 = opticalDepthLargeBestOcean5;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthLargeBestOcean6() {
      return opticalDepthLargeBestOcean6;
   }

   /**
    * Field:Retrieved AOT of large mode for 'best' solution at 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthLargeBestOcean6(Double opticalDepthLargeBestOcean6) {
      this.opticalDepthLargeBestOcean6 = opticalDepthLargeBestOcean6;
   }

   /**
    * Field:Fraction of AOT contributed by fine dominated model Tip:DFNT_INT16
    * Dim:2
    */
   public Double getOpticalDepthRatioSmallLand() {
      return opticalDepthRatioSmallLand;
   }

   /**
    * Field:Fraction of AOT contributed by fine dominated model Tip:DFNT_INT16
    * Dim:2
    */
   public void setOpticalDepthRatioSmallLand(Double opticalDepthRatioSmallLand) {
      this.opticalDepthRatioSmallLand = opticalDepthRatioSmallLand;
   }

   /**
    * Field:Fraction of AOT (at 0.55 micron) contributed by fine mode for
    * average solution Tip:DFNT_INT16 Dim:2
    */
   public Double getOpticalDepthRatioSmallOcean0p55micron() {
      return opticalDepthRatioSmallOcean0p55micron;
   }

   /**
    * Field:Fraction of AOT (at 0.55 micron) contributed by fine mode for
    * average solution Tip:DFNT_INT16 Dim:2
    */
   public void setOpticalDepthRatioSmallOcean0p55micron(Double opticalDepthRatioSmallOcean0p55micron) {
      this.opticalDepthRatioSmallOcean0p55micron = opticalDepthRatioSmallOcean0p55micron;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double[] getOpticalDepthSmallAverageOcean() {
      return opticalDepthSmallAverageOcean;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallAverageOcean(Double[] opticalDepthSmallAverageOcean) {
      this.opticalDepthSmallAverageOcean = opticalDepthSmallAverageOcean;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallAverageOcean0() {
      return opticalDepthSmallAverageOcean0;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallAverageOcean0(Double opticalDepthSmallAverageOcean0) {
      this.opticalDepthSmallAverageOcean0 = opticalDepthSmallAverageOcean0;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallAverageOcean1() {
      return opticalDepthSmallAverageOcean1;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallAverageOcean1(Double opticalDepthSmallAverageOcean1) {
      this.opticalDepthSmallAverageOcean1 = opticalDepthSmallAverageOcean1;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallAverageOcean2() {
      return opticalDepthSmallAverageOcean2;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallAverageOcean2(Double opticalDepthSmallAverageOcean2) {
      this.opticalDepthSmallAverageOcean2 = opticalDepthSmallAverageOcean2;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallAverageOcean3() {
      return opticalDepthSmallAverageOcean3;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallAverageOcean3(Double opticalDepthSmallAverageOcean3) {
      this.opticalDepthSmallAverageOcean3 = opticalDepthSmallAverageOcean3;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallAverageOcean4() {
      return opticalDepthSmallAverageOcean4;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallAverageOcean4(Double opticalDepthSmallAverageOcean4) {
      this.opticalDepthSmallAverageOcean4 = opticalDepthSmallAverageOcean4;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallAverageOcean5() {
      return opticalDepthSmallAverageOcean5;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallAverageOcean5(Double opticalDepthSmallAverageOcean5) {
      this.opticalDepthSmallAverageOcean5 = opticalDepthSmallAverageOcean5;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallAverageOcean6() {
      return opticalDepthSmallAverageOcean6;
   }

   /**
    * Field:Retreived optical thickness for fine mode (Average solution) for
    * 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallAverageOcean6(Double opticalDepthSmallAverageOcean6) {
      this.opticalDepthSmallAverageOcean6 = opticalDepthSmallAverageOcean6;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double[] getOpticalDepthSmallBestOcean() {
      return opticalDepthSmallBestOcean;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallBestOcean(Double[] opticalDepthSmallBestOcean) {
      this.opticalDepthSmallBestOcean = opticalDepthSmallBestOcean;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallBestOcean0() {
      return opticalDepthSmallBestOcean0;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallBestOcean0(Double opticalDepthSmallBestOcean0) {
      this.opticalDepthSmallBestOcean0 = opticalDepthSmallBestOcean0;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallBestOcean1() {
      return opticalDepthSmallBestOcean1;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallBestOcean1(Double opticalDepthSmallBestOcean1) {
      this.opticalDepthSmallBestOcean1 = opticalDepthSmallBestOcean1;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallBestOcean2() {
      return opticalDepthSmallBestOcean2;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallBestOcean2(Double opticalDepthSmallBestOcean2) {
      this.opticalDepthSmallBestOcean2 = opticalDepthSmallBestOcean2;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallBestOcean3() {
      return opticalDepthSmallBestOcean3;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallBestOcean3(Double opticalDepthSmallBestOcean3) {
      this.opticalDepthSmallBestOcean3 = opticalDepthSmallBestOcean3;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallBestOcean4() {
      return opticalDepthSmallBestOcean4;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallBestOcean4(Double opticalDepthSmallBestOcean4) {
      this.opticalDepthSmallBestOcean4 = opticalDepthSmallBestOcean4;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallBestOcean5() {
      return opticalDepthSmallBestOcean5;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallBestOcean5(Double opticalDepthSmallBestOcean5) {
      this.opticalDepthSmallBestOcean5 = opticalDepthSmallBestOcean5;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public Double getOpticalDepthSmallBestOcean6() {
      return opticalDepthSmallBestOcean6;
   }

   /**
    * Field:Retreived optical thickness for fine mode (best solution) for 0.47,
    * 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
    */
   public void setOpticalDepthSmallBestOcean6(Double opticalDepthSmallBestOcean6) {
      this.opticalDepthSmallBestOcean6 = opticalDepthSmallBestOcean6;
   }

   /**
    * Field:Inferred column number concentration (number per area) of particles
    * larger than 0.03 micron for'best' (1) and 'average' (2) solutions
    * Tip:DFNT_FLOAT32 Dim:3
    */
   public Double[] getPsml003Ocean() {
      return psml003Ocean;
   }

   /**
    * Field:Inferred column number concentration (number per area) of particles
    * larger than 0.03 micron for'best' (1) and 'average' (2) solutions
    * Tip:DFNT_FLOAT32 Dim:3
    */
   public void setPsml003Ocean(Double[] psml003Ocean) {
      this.psml003Ocean = psml003Ocean;
   }

   /**
    * Field:Inferred column number concentration (number per area) of particles
    * larger than 0.03 micron for'best' (1) and 'average' (2) solutions
    * Tip:DFNT_FLOAT32 Dim:3
    */
   public Double getPsml003Ocean0() {
      return psml003Ocean0;
   }

   /**
    * Field:Inferred column number concentration (number per area) of particles
    * larger than 0.03 micron for'best' (1) and 'average' (2) solutions
    * Tip:DFNT_FLOAT32 Dim:3
    */
   public void setPsml003Ocean0(Double psml003Ocean0) {
      this.psml003Ocean0 = psml003Ocean0;
   }

   /**
    * Field:Inferred column number concentration (number per area) of particles
    * larger than 0.03 micron for'best' (1) and 'average' (2) solutions
    * Tip:DFNT_FLOAT32 Dim:3
    */
   public Double getPsml003Ocean1() {
      return psml003Ocean1;
   }

   /**
    * Field:Inferred column number concentration (number per area) of particles
    * larger than 0.03 micron for'best' (1) and 'average' (2) solutions
    * Tip:DFNT_FLOAT32 Dim:3
    */
   public void setPsml003Ocean1(Double psml003Ocean1) {
      this.psml003Ocean1 = psml003Ocean1;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte[] getQualityAssuranceLand() {
      return qualityAssuranceLand;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceLand(Byte[] qualityAssuranceLand) {
      this.qualityAssuranceLand = qualityAssuranceLand;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte getQualityAssuranceLand0() {
      return qualityAssuranceLand0;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceLand0(Byte qualityAssuranceLand0) {
      this.qualityAssuranceLand0 = qualityAssuranceLand0;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte getQualityAssuranceLand1() {
      return qualityAssuranceLand1;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceLand1(Byte qualityAssuranceLand1) {
      this.qualityAssuranceLand1 = qualityAssuranceLand1;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte getQualityAssuranceLand2() {
      return qualityAssuranceLand2;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceLand2(Byte qualityAssuranceLand2) {
      this.qualityAssuranceLand2 = qualityAssuranceLand2;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte getQualityAssuranceLand3() {
      return qualityAssuranceLand3;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceLand3(Byte qualityAssuranceLand3) {
      this.qualityAssuranceLand3 = qualityAssuranceLand3;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte getQualityAssuranceLand4() {
      return qualityAssuranceLand4;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceLand4(Byte qualityAssuranceLand4) {
      this.qualityAssuranceLand4 = qualityAssuranceLand4;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte getQualityAssuranceLand5() {
      return qualityAssuranceLand5;
   }

   /**
    * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceLand5(Byte qualityAssuranceLand5) {
      this.qualityAssuranceLand5 = qualityAssuranceLand5;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte[] getQualityAssuranceOcean() {
      return qualityAssuranceOcean;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceOcean(Byte[] qualityAssuranceOcean) {
      this.qualityAssuranceOcean = qualityAssuranceOcean;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte getQualityAssuranceOcean0() {
      return qualityAssuranceOcean0;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceOcean0(Byte qualityAssuranceOcean0) {
      this.qualityAssuranceOcean0 = qualityAssuranceOcean0;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte getQualityAssuranceOcean1() {
      return qualityAssuranceOcean1;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceOcean1(Byte qualityAssuranceOcean1) {
      this.qualityAssuranceOcean1 = qualityAssuranceOcean1;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte getQualityAssuranceOcean2() {
      return qualityAssuranceOcean2;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceOcean2(Byte qualityAssuranceOcean2) {
      this.qualityAssuranceOcean2 = qualityAssuranceOcean2;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte getQualityAssuranceOcean3() {
      return qualityAssuranceOcean3;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceOcean3(Byte qualityAssuranceOcean3) {
      this.qualityAssuranceOcean3 = qualityAssuranceOcean3;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public Byte getQualityAssuranceOcean4() {
      return qualityAssuranceOcean4;
   }

   /**
    * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
    */
   public void setQualityAssuranceOcean4(Byte qualityAssuranceOcean4) {
      this.qualityAssuranceOcean4 = qualityAssuranceOcean4;
   }

   /**
    * Field:TAI Time at Start of Scan replicated across the swath
    * Tip:DFNT_FLOAT32 Dim:2
    */
   public Double getScanStartTime() {
      return scanStartTime;
   }

   /**
    * Field:TAI Time at Start of Scan replicated across the swath
    * Tip:DFNT_FLOAT32 Dim:2
    */
   public void setScanStartTime(Double scanStartTime) {
      this.scanStartTime = scanStartTime;
   }

   /**
    * Field:Scattering Angle Tip:DFNT_INT16 Dim:2
    */
   public Double getScatteringAngle() {
      return scatteringAngle;
   }

   /**
    * Field:Scattering Angle Tip:DFNT_INT16 Dim:2
    */
   public void setScatteringAngle(Double scatteringAngle) {
      this.scatteringAngle = scatteringAngle;
   }

   /**
    * Field:Sensor_Azimuth Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
    */
   public Double getSensorAzimuth() {
      return sensorAzimuth;
   }

   /**
    * Field:Sensor_Azimuth Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
    */
   public void setSensorAzimuth(Double sensorAzimuth) {
      this.sensorAzimuth = sensorAzimuth;
   }

   /**
    * Field:Sensor_Zenith Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
    */
   public Double getSensorZenith() {
      return sensorZenith;
   }

   /**
    * Field:Sensor_Zenith Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
    */
   public void setSensorZenith(Double sensorZenith) {
      this.sensorZenith = sensorZenith;
   }

   /**
    * Field:Solar_Azimuth Angle, Cell to Sun Tip:DFNT_INT16 Dim:2
    */
   public Double getSolarAzimuth() {
      return solarAzimuth;
   }

   /**
    * Field:Solar_Azimuth Angle, Cell to Sun Tip:DFNT_INT16 Dim:2
    */
   public void setSolarAzimuth(Double solarAzimuth) {
      this.solarAzimuth = solarAzimuth;
   }

   /**
    * Field:Solar Zenith Angle, Cell to Sun Tip:DFNT_INT16 Dim:2
    */
   public Double getSolarZenith() {
      return solarZenith;
   }

   /**
    * Field:Solar Zenith Angle, Cell to Sun Tip:DFNT_INT16 Dim:2
    */
   public void setSolarZenith(Double solarZenith) {
      this.solarZenith = solarZenith;
   }

   /**
    * Field:index identifying coarse mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public Double[] getSolutionIndexOceanLarge() {
      return solutionIndexOceanLarge;
   }

   /**
    * Field:index identifying coarse mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public void setSolutionIndexOceanLarge(Double[] solutionIndexOceanLarge) {
      this.solutionIndexOceanLarge = solutionIndexOceanLarge;
   }

   /**
    * Field:index identifying coarse mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public Double getSolutionIndexOceanLarge0() {
      return solutionIndexOceanLarge0;
   }

   /**
    * Field:index identifying coarse mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public void setSolutionIndexOceanLarge0(Double solutionIndexOceanLarge0) {
      this.solutionIndexOceanLarge0 = solutionIndexOceanLarge0;
   }

   /**
    * Field:index identifying coarse mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public Double getSolutionIndexOceanLarge1() {
      return solutionIndexOceanLarge1;
   }

   /**
    * Field:index identifying coarse mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public void setSolutionIndexOceanLarge1(Double solutionIndexOceanLarge1) {
      this.solutionIndexOceanLarge1 = solutionIndexOceanLarge1;
   }

   /**
    * Field:index identifying fine mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public Double[] getSolutionIndexOceanSmall() {
      return solutionIndexOceanSmall;
   }

   /**
    * Field:index identifying fine mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public void setSolutionIndexOceanSmall(Double[] solutionIndexOceanSmall) {
      this.solutionIndexOceanSmall = solutionIndexOceanSmall;
   }

   /**
    * Field:index identifying fine mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public Double getSolutionIndexOceanSmall0() {
      return solutionIndexOceanSmall0;
   }

   /**
    * Field:index identifying fine mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public void setSolutionIndexOceanSmall0(Double solutionIndexOceanSmall0) {
      this.solutionIndexOceanSmall0 = solutionIndexOceanSmall0;
   }

   /**
    * Field:index identifying fine mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public Double getSolutionIndexOceanSmall1() {
      return solutionIndexOceanSmall1;
   }

   /**
    * Field:index identifying fine mode from Look Up Table for 'best' solution
    * Tip:DFNT_INT16 Dim:3
    */
   public void setSolutionIndexOceanSmall1(Double solutionIndexOceanSmall1) {
      this.solutionIndexOceanSmall1 = solutionIndexOceanSmall1;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double[] getStdReflectanceLand() {
      return stdReflectanceLand;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceLand(Double[] stdReflectanceLand) {
      this.stdReflectanceLand = stdReflectanceLand;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceLand0() {
      return stdReflectanceLand0;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceLand0(Double stdReflectanceLand0) {
      this.stdReflectanceLand0 = stdReflectanceLand0;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceLand1() {
      return stdReflectanceLand1;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceLand1(Double stdReflectanceLand1) {
      this.stdReflectanceLand1 = stdReflectanceLand1;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceLand2() {
      return stdReflectanceLand2;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceLand2(Double stdReflectanceLand2) {
      this.stdReflectanceLand2 = stdReflectanceLand2;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceLand3() {
      return stdReflectanceLand3;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceLand3(Double stdReflectanceLand3) {
      this.stdReflectanceLand3 = stdReflectanceLand3;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceLand4() {
      return stdReflectanceLand4;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceLand4(Double stdReflectanceLand4) {
      this.stdReflectanceLand4 = stdReflectanceLand4;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceLand5() {
      return stdReflectanceLand5;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceLand5(Double stdReflectanceLand5) {
      this.stdReflectanceLand5 = stdReflectanceLand5;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceLand6() {
      return stdReflectanceLand6;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceLand6(Double stdReflectanceLand6) {
      this.stdReflectanceLand6 = stdReflectanceLand6;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceLand7() {
      return stdReflectanceLand7;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceLand7(Double stdReflectanceLand7) {
      this.stdReflectanceLand7 = stdReflectanceLand7;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceLand8() {
      return stdReflectanceLand8;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceLand8(Double stdReflectanceLand8) {
      this.stdReflectanceLand8 = stdReflectanceLand8;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceLand9() {
      return stdReflectanceLand9;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for land retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceLand9(Double stdReflectanceLand9) {
      this.stdReflectanceLand9 = stdReflectanceLand9;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double[] getStdReflectanceOcean() {
      return stdReflectanceOcean;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceOcean(Double[] stdReflectanceOcean) {
      this.stdReflectanceOcean = stdReflectanceOcean;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceOcean0() {
      return stdReflectanceOcean0;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceOcean0(Double stdReflectanceOcean0) {
      this.stdReflectanceOcean0 = stdReflectanceOcean0;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceOcean1() {
      return stdReflectanceOcean1;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceOcean1(Double stdReflectanceOcean1) {
      this.stdReflectanceOcean1 = stdReflectanceOcean1;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceOcean2() {
      return stdReflectanceOcean2;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceOcean2(Double stdReflectanceOcean2) {
      this.stdReflectanceOcean2 = stdReflectanceOcean2;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceOcean3() {
      return stdReflectanceOcean3;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceOcean3(Double stdReflectanceOcean3) {
      this.stdReflectanceOcean3 = stdReflectanceOcean3;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceOcean4() {
      return stdReflectanceOcean4;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceOcean4(Double stdReflectanceOcean4) {
      this.stdReflectanceOcean4 = stdReflectanceOcean4;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceOcean5() {
      return stdReflectanceOcean5;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceOcean5(Double stdReflectanceOcean5) {
      this.stdReflectanceOcean5 = stdReflectanceOcean5;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceOcean6() {
      return stdReflectanceOcean6;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceOcean6(Double stdReflectanceOcean6) {
      this.stdReflectanceOcean6 = stdReflectanceOcean6;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceOcean7() {
      return stdReflectanceOcean7;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceOcean7(Double stdReflectanceOcean7) {
      this.stdReflectanceOcean7 = stdReflectanceOcean7;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceOcean8() {
      return stdReflectanceOcean8;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceOcean8(Double stdReflectanceOcean8) {
      this.stdReflectanceOcean8 = stdReflectanceOcean8;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public Double getStdReflectanceOcean9() {
      return stdReflectanceOcean9;
   }

   /**
    * Field:Standard deviation of reflectance of pixels used for ocean retrieval
    * at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP:
    * 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
    */
   public void setStdReflectanceOcean9(Double stdReflectanceOcean9) {
      this.stdReflectanceOcean9 = stdReflectanceOcean9;
   }

   /**
    * Field:Estimated Surface Reflectance at 0.47,0.66 and 2.13micron
    * Tip:DFNT_INT16 Dim:3
    */
   public Double[] getSurfaceReflectanceLand() {
      return surfaceReflectanceLand;
   }

   /**
    * Field:Estimated Surface Reflectance at 0.47,0.66 and 2.13micron
    * Tip:DFNT_INT16 Dim:3
    */
   public void setSurfaceReflectanceLand(Double[] surfaceReflectanceLand) {
      this.surfaceReflectanceLand = surfaceReflectanceLand;
   }

   /**
    * Field:Estimated Surface Reflectance at 0.47,0.66 and 2.13micron
    * Tip:DFNT_INT16 Dim:3
    */
   public Double getSurfaceReflectanceLand0() {
      return surfaceReflectanceLand0;
   }

   /**
    * Field:Estimated Surface Reflectance at 0.47,0.66 and 2.13micron
    * Tip:DFNT_INT16 Dim:3
    */
   public void setSurfaceReflectanceLand0(Double surfaceReflectanceLand0) {
      this.surfaceReflectanceLand0 = surfaceReflectanceLand0;
   }

   /**
    * Field:Estimated Surface Reflectance at 0.47,0.66 and 2.13micron
    * Tip:DFNT_INT16 Dim:3
    */
   public Double getSurfaceReflectanceLand1() {
      return surfaceReflectanceLand1;
   }

   /**
    * Field:Estimated Surface Reflectance at 0.47,0.66 and 2.13micron
    * Tip:DFNT_INT16 Dim:3
    */
   public void setSurfaceReflectanceLand1(Double surfaceReflectanceLand1) {
      this.surfaceReflectanceLand1 = surfaceReflectanceLand1;
   }

   /**
    * Field:Estimated Surface Reflectance at 0.47,0.66 and 2.13micron
    * Tip:DFNT_INT16 Dim:3
    */
   public Double getSurfaceReflectanceLand2() {
      return surfaceReflectanceLand2;
   }

   /**
    * Field:Estimated Surface Reflectance at 0.47,0.66 and 2.13micron
    * Tip:DFNT_INT16 Dim:3
    */
   public void setSurfaceReflectanceLand2(Double surfaceReflectanceLand2) {
      this.surfaceReflectanceLand2 = surfaceReflectanceLand2;
   }

   /**
    * Field:Averaged topographic altitude (in km) for Land Tip:DFNT_INT16 Dim:2
    */
   public Double getTopographicAltitudeLand() {
      return topographicAltitudeLand;
   }

   /**
    * Field:Averaged topographic altitude (in km) for Land Tip:DFNT_INT16 Dim:2
    */
   public void setTopographicAltitudeLand(Double topographicAltitudeLand) {
      this.topographicAltitudeLand = topographicAltitudeLand;
   }

   /**
    * Field:Wind Speed based on NCEP reanalysis for Ocean Tip:DFNT_INT16 Dim:2
    */
   public Double getWindSpeedNcepOcean() {
      return windSpeedNcepOcean;
   }

   /**
    * Field:Wind Speed based on NCEP reanalysis for Ocean Tip:DFNT_INT16 Dim:2
    */
   public void setWindSpeedNcepOcean(Double windSpeedNcepOcean) {
      this.windSpeedNcepOcean = windSpeedNcepOcean;
   }

   public static String toLongHeader() {
      SimpleStringLineBuilder builder = new SimpleStringLineBuilder();
      builder.append("timp2");
      builder.append("pozitie");
      builder.append("pozitie500");
      builder.append("aerosolCldmaskLandOcean");
      builder.append("aerosolCloudFractionLand");
      builder.append("aerosolCloudFractionOcean");
      builder.append("aerosolTypeLand");
      builder.append("angstromExponent1Ocean");
      builder.append("angstromExponent2Ocean");
      builder.append("aod550DarkTargetDeepBlueCombined");
      builder.append("aod550DarkTargetDeepBlueCombinedAlgorithmFlag");
      builder.append("aod550DarkTargetDeepBlueCombinedQaFlag");
      builder.append("averageCloudPixelDistanceLandOcean");
      builder.append("cloudPixelDistanceLandOcean");
      builder.append("correctedOpticalDepthLandWav2p1");
      builder.append("deepBlueAerosolOpticalDepth550Land");
      builder.append("deepBlueAerosolOpticalDepth550LandBestEstimate");
      builder.append("deepBlueAerosolOpticalDepth550LandEstimatedUncertainty");
      builder.append("deepBlueAerosolOpticalDepth550LandQaFlag");
      builder.append("deepBlueAerosolOpticalDepth550LandStd");
      builder.append("deepBlueAlgorithmFlagLand");
      builder.append("deepBlueAngstromExponentLand");
      builder.append("deepBlueCloudFractionLand");
      builder.append("deepBlueNumberPixelsUsed550Land");
      builder.append("effectiveOpticalDepth0p55umOcean");
      builder.append("fittingErrorLand");
      builder.append("glintAngle");
      builder.append("imageOpticalDepthLandAndOcean");
      builder.append("landOceanQualityFlag");
      builder.append("landSeaFlag");
      builder.append("massConcentrationLand");
      builder.append("opticalDepthLandAndOcean");
      builder.append("opticalDepthRatioSmallLand");
      builder.append("opticalDepthRatioSmallOcean0p55micron");
      builder.append("scanStartTime");
      builder.append("scatteringAngle");
      builder.append("sensorAzimuth");
      builder.append("sensorZenith");
      builder.append("solarAzimuth");
      builder.append("solarZenith");
      builder.append("topographicAltitudeLand");
      builder.append("windSpeedNcepOcean");
      builder.append("numePozitie");
      builder.append("hdfFileName");
      return builder.toString();
   }

   public String toLongLine() {
      SimpleStringLineBuilder builder = new SimpleStringLineBuilder();
      DateTime timp2 = timp.withZone(DateTimeZone.UTC);
      builder.append(timp2);
      builder.append(pozitie);
      builder.append(pozitie500);
      builder.append(aerosolCldmaskLandOcean);
      builder.append(aerosolCloudFractionLand);
      builder.append(aerosolCloudFractionOcean);
      builder.append(aerosolTypeLand);
      builder.append(angstromExponent1Ocean);
      builder.append(angstromExponent2Ocean);
      builder.append(aod550DarkTargetDeepBlueCombined);
      builder.append(aod550DarkTargetDeepBlueCombinedAlgorithmFlag);
      builder.append(aod550DarkTargetDeepBlueCombinedQaFlag);
      builder.append(averageCloudPixelDistanceLandOcean);
      builder.append(cloudPixelDistanceLandOcean);
      builder.append(correctedOpticalDepthLandWav2p1);
      builder.append(deepBlueAerosolOpticalDepth550Land);
      builder.append(deepBlueAerosolOpticalDepth550LandBestEstimate);
      builder.append(deepBlueAerosolOpticalDepth550LandEstimatedUncertainty);
      builder.append(deepBlueAerosolOpticalDepth550LandQaFlag);
      builder.append(deepBlueAerosolOpticalDepth550LandStd);
      builder.append(deepBlueAlgorithmFlagLand);
      builder.append(deepBlueAngstromExponentLand);
      builder.append(deepBlueCloudFractionLand);
      builder.append(deepBlueNumberPixelsUsed550Land);
      builder.append(effectiveOpticalDepth0p55umOcean);
      builder.append(fittingErrorLand);
      builder.append(glintAngle);
      builder.append(imageOpticalDepthLandAndOcean);
      builder.append(landOceanQualityFlag);
      builder.append(landSeaFlag);
      builder.append(massConcentrationLand);
      builder.append(opticalDepthLandAndOcean);
      builder.append(opticalDepthRatioSmallLand);
      builder.append(opticalDepthRatioSmallOcean0p55micron);
      builder.append(scanStartTime);
      builder.append(scatteringAngle);
      builder.append(sensorAzimuth);
      builder.append(sensorZenith);
      builder.append(solarAzimuth);
      builder.append(solarZenith);
      builder.append(topographicAltitudeLand);
      builder.append(windSpeedNcepOcean);
      builder.append(numePozitie);
      builder.append(hdfFileName);
      return builder.toString();
   }

   public String extractParameters(String[] parameters) {
      SimpleStringLineBuilder builder = new SimpleStringLineBuilder();
      DateTime timp2 = timp.withZone(DateTimeZone.UTC);
      builder.append(timp2);
      builder.append(pozitie);
      builder.append(pozitie500);
      for (int i = 0; i < parameters.length; i++) {
         if ("aerosolCldmaskLandOcean".equals(parameters[i])) {
            builder.append(aerosolCldmaskLandOcean);
         }
         if ("aerosolCloudFractionLand".equals(parameters[i])) {
            builder.append(aerosolCloudFractionLand);
         }
         if ("aerosolCloudFractionOcean".equals(parameters[i])) {
            builder.append(aerosolCloudFractionOcean);
         }
         if ("aerosolTypeLand".equals(parameters[i])) {
            builder.append(aerosolTypeLand);
         }
         if ("angstromExponent1Ocean".equals(parameters[i])) {
            builder.append(angstromExponent1Ocean);
         }
         if ("angstromExponent2Ocean".equals(parameters[i])) {
            builder.append(angstromExponent2Ocean);
         }
         if ("aod550DarkTargetDeepBlueCombined".equals(parameters[i])) {
            builder.append(aod550DarkTargetDeepBlueCombined);
         }
         if ("aod550DarkTargetDeepBlueCombinedAlgorithmFlag".equals(parameters[i])) {
            builder.append(aod550DarkTargetDeepBlueCombinedAlgorithmFlag);
         }
         if ("aod550DarkTargetDeepBlueCombinedQaFlag".equals(parameters[i])) {
            builder.append(aod550DarkTargetDeepBlueCombinedQaFlag);
         }
         if ("asymmetryFactorAverageOcean".equals(parameters[i])) {
            builder.append(asymmetryFactorAverageOcean);
         }
         if ("asymmetryFactorBestOcean".equals(parameters[i])) {
            builder.append(asymmetryFactorBestOcean);
         }
         if ("averageCloudPixelDistanceLandOcean".equals(parameters[i])) {
            builder.append(averageCloudPixelDistanceLandOcean);
         }
         if ("backscatteringRatioAverageOcean".equals(parameters[i])) {
            builder.append(backscatteringRatioAverageOcean);
         }
         if ("backscatteringRatioBestOcean".equals(parameters[i])) {
            builder.append(backscatteringRatioBestOcean);
         }
         if ("cloudPixelDistanceLandOcean".equals(parameters[i])) {
            builder.append(cloudPixelDistanceLandOcean);
         }
         if ("correctedOpticalDepthLand".equals(parameters[i])) {
            builder.append(correctedOpticalDepthLand);
         }
         if ("correctedOpticalDepthLandWav2p1".equals(parameters[i])) {
            builder.append(correctedOpticalDepthLandWav2p1);
         }
         if ("deepBlueAerosolOpticalDepth550Land".equals(parameters[i])) {
            builder.append(deepBlueAerosolOpticalDepth550Land);
         }
         if ("deepBlueAerosolOpticalDepth550LandBestEstimate".equals(parameters[i])) {
            builder.append(deepBlueAerosolOpticalDepth550LandBestEstimate);
         }
         if ("deepBlueAerosolOpticalDepth550LandEstimatedUncertainty".equals(parameters[i])) {
            builder.append(deepBlueAerosolOpticalDepth550LandEstimatedUncertainty);
         }
         if ("deepBlueAerosolOpticalDepth550LandQaFlag".equals(parameters[i])) {
            builder.append(deepBlueAerosolOpticalDepth550LandQaFlag);
         }
         if ("deepBlueAerosolOpticalDepth550LandStd".equals(parameters[i])) {
            builder.append(deepBlueAerosolOpticalDepth550LandStd);
         }
         if ("deepBlueAlgorithmFlagLand".equals(parameters[i])) {
            builder.append(deepBlueAlgorithmFlagLand);
         }
         if ("deepBlueAngstromExponentLand".equals(parameters[i])) {
            builder.append(deepBlueAngstromExponentLand);
         }
         if ("deepBlueCloudFractionLand".equals(parameters[i])) {
            builder.append(deepBlueCloudFractionLand);
         }
         if ("deepBlueNumberPixelsUsed550Land".equals(parameters[i])) {
            builder.append(deepBlueNumberPixelsUsed550Land);
         }
         if ("deepBlueSpectralAerosolOpticalDepthLand".equals(parameters[i])) {
            builder.append(deepBlueSpectralAerosolOpticalDepthLand);
         }
         if ("deepBlueSpectralSingleScatteringAlbedoLand".equals(parameters[i])) {
            builder.append(deepBlueSpectralSingleScatteringAlbedoLand);
         }
         if ("deepBlueSpectralSurfaceReflectanceLand".equals(parameters[i])) {
            builder.append(deepBlueSpectralSurfaceReflectanceLand);
         }
         if ("deepBlueSpectralToaReflectanceLand".equals(parameters[i])) {
            builder.append(deepBlueSpectralToaReflectanceLand);
         }
         if ("effectiveOpticalDepth0p55umOcean".equals(parameters[i])) {
            builder.append(effectiveOpticalDepth0p55umOcean);
         }
         if ("effectiveOpticalDepthAverageOcean".equals(parameters[i])) {
            builder.append(effectiveOpticalDepthAverageOcean);
         }
         if ("effectiveOpticalDepthBestOcean".equals(parameters[i])) {
            builder.append(effectiveOpticalDepthBestOcean);
         }
         if ("effectiveRadiusOcean".equals(parameters[i])) {
            builder.append(effectiveRadiusOcean);
         }
         if ("fittingErrorLand".equals(parameters[i])) {
            builder.append(fittingErrorLand);
         }
         if ("glintAngle".equals(parameters[i])) {
            builder.append(glintAngle);
         }
         if ("imageOpticalDepthLandAndOcean".equals(parameters[i])) {
            builder.append(imageOpticalDepthLandAndOcean);
         }
         if ("landOceanQualityFlag".equals(parameters[i])) {
            builder.append(landOceanQualityFlag);
         }
         if ("landSeaFlag".equals(parameters[i])) {
            builder.append(landSeaFlag);
         }
         if ("leastSquaresErrorOcean".equals(parameters[i])) {
            builder.append(leastSquaresErrorOcean);
         }
         if ("massConcentrationLand".equals(parameters[i])) {
            builder.append(massConcentrationLand);
         }
         if ("massConcentrationOcean".equals(parameters[i])) {
            builder.append(massConcentrationOcean);
         }
         if ("meanReflectanceLand".equals(parameters[i])) {
            builder.append(meanReflectanceLand);
         }
         if ("meanReflectanceOcean".equals(parameters[i])) {
            builder.append(meanReflectanceOcean);
         }
         if ("numberPixelsUsedLand".equals(parameters[i])) {
            builder.append(numberPixelsUsedLand);
         }
         if ("numberPixelsUsedOcean".equals(parameters[i])) {
            builder.append(numberPixelsUsedOcean);
         }
         if ("opticalDepthByModelsOcean".equals(parameters[i])) {
            builder.append(opticalDepthByModelsOcean);
         }
         if ("opticalDepthLandAndOcean".equals(parameters[i])) {
            builder.append(opticalDepthLandAndOcean);
         }
         if ("opticalDepthLargeAverageOcean".equals(parameters[i])) {
            builder.append(opticalDepthLargeAverageOcean);
         }
         if ("opticalDepthLargeBestOcean".equals(parameters[i])) {
            builder.append(opticalDepthLargeBestOcean);
         }
         if ("opticalDepthRatioSmallLand".equals(parameters[i])) {
            builder.append(opticalDepthRatioSmallLand);
         }
         if ("opticalDepthRatioSmallOcean0p55micron".equals(parameters[i])) {
            builder.append(opticalDepthRatioSmallOcean0p55micron);
         }
         if ("opticalDepthSmallAverageOcean".equals(parameters[i])) {
            builder.append(opticalDepthSmallAverageOcean);
         }
         if ("opticalDepthSmallBestOcean".equals(parameters[i])) {
            builder.append(opticalDepthSmallBestOcean);
         }
         if ("psml003Ocean".equals(parameters[i])) {
            builder.append(psml003Ocean);
         }
         if ("qualityAssuranceLand".equals(parameters[i])) {
            builder.append(qualityAssuranceLand);
         }
         if ("qualityAssuranceOcean".equals(parameters[i])) {
            builder.append(qualityAssuranceOcean);
         }
         if ("scanStartTime".equals(parameters[i])) {
            builder.append(scanStartTime);
         }
         if ("scatteringAngle".equals(parameters[i])) {
            builder.append(scatteringAngle);
         }
         if ("sensorAzimuth".equals(parameters[i])) {
            builder.append(sensorAzimuth);
         }
         if ("sensorZenith".equals(parameters[i])) {
            builder.append(sensorZenith);
         }
         if ("solarAzimuth".equals(parameters[i])) {
            builder.append(solarAzimuth);
         }
         if ("solarZenith".equals(parameters[i])) {
            builder.append(solarZenith);
         }
         if ("solutionIndexOceanLarge".equals(parameters[i])) {
            builder.append(solutionIndexOceanLarge);
         }
         if ("solutionIndexOceanSmall".equals(parameters[i])) {
            builder.append(solutionIndexOceanSmall);
         }
         if ("stdReflectanceLand".equals(parameters[i])) {
            builder.append(stdReflectanceLand);
         }
         if ("stdReflectanceOcean".equals(parameters[i])) {
            builder.append(stdReflectanceOcean);
         }
         if ("surfaceReflectanceLand".equals(parameters[i])) {
            builder.append(surfaceReflectanceLand);
         }
         if ("topographicAltitudeLand".equals(parameters[i])) {
            builder.append(topographicAltitudeLand);
         }
         if ("windSpeedNcepOcean".equals(parameters[i])) {
            builder.append(windSpeedNcepOcean);
         }
      }
      builder.append(numePozitie);
      builder.append(hdfFileName);
      return builder.toString();
   }

   public static String extractHeader(String[] parameters) {
      SimpleStringLineBuilder builder = new SimpleStringLineBuilder();
      builder.append("timp2");
      builder.append("pozitie");
      builder.append("pozitie500");
      for (int i = 0; i < parameters.length; i++) {
         if ("aerosolCldmaskLandOcean".equals(parameters[i])) {
            builder.append("aerosolCldmaskLandOcean");
         }
         if ("aerosolCloudFractionLand".equals(parameters[i])) {
            builder.append("aerosolCloudFractionLand");
         }
         if ("aerosolCloudFractionOcean".equals(parameters[i])) {
            builder.append("aerosolCloudFractionOcean");
         }
         if ("aerosolTypeLand".equals(parameters[i])) {
            builder.append("aerosolTypeLand");
         }
         if ("angstromExponent1Ocean".equals(parameters[i])) {
            builder.append("angstromExponent1Ocean");
         }
         if ("angstromExponent2Ocean".equals(parameters[i])) {
            builder.append("angstromExponent2Ocean");
         }
         if ("aod550DarkTargetDeepBlueCombined".equals(parameters[i])) {
            builder.append("aod550DarkTargetDeepBlueCombined");
         }
         if ("aod550DarkTargetDeepBlueCombinedAlgorithmFlag".equals(parameters[i])) {
            builder.append("aod550DarkTargetDeepBlueCombinedAlgorithmFlag");
         }
         if ("aod550DarkTargetDeepBlueCombinedQaFlag".equals(parameters[i])) {
            builder.append("aod550DarkTargetDeepBlueCombinedQaFlag");
         }
         if ("asymmetryFactorAverageOcean".equals(parameters[i])) {
            builder.append("asymmetryFactorAverageOcean");
         }
         if ("asymmetryFactorBestOcean".equals(parameters[i])) {
            builder.append("asymmetryFactorBestOcean");
         }
         if ("averageCloudPixelDistanceLandOcean".equals(parameters[i])) {
            builder.append("averageCloudPixelDistanceLandOcean");
         }
         if ("backscatteringRatioAverageOcean".equals(parameters[i])) {
            builder.append("backscatteringRatioAverageOcean");
         }
         if ("backscatteringRatioBestOcean".equals(parameters[i])) {
            builder.append("backscatteringRatioBestOcean");
         }
         if ("cloudPixelDistanceLandOcean".equals(parameters[i])) {
            builder.append("cloudPixelDistanceLandOcean");
         }
         if ("correctedOpticalDepthLand".equals(parameters[i])) {
            builder.append("correctedOpticalDepthLand");
         }
         if ("correctedOpticalDepthLandWav2p1".equals(parameters[i])) {
            builder.append("correctedOpticalDepthLandWav2p1");
         }
         if ("deepBlueAerosolOpticalDepth550Land".equals(parameters[i])) {
            builder.append("deepBlueAerosolOpticalDepth550Land");
         }
         if ("deepBlueAerosolOpticalDepth550LandBestEstimate".equals(parameters[i])) {
            builder.append("deepBlueAerosolOpticalDepth550LandBestEstimate");
         }
         if ("deepBlueAerosolOpticalDepth550LandEstimatedUncertainty".equals(parameters[i])) {
            builder.append("deepBlueAerosolOpticalDepth550LandEstimatedUncertainty");
         }
         if ("deepBlueAerosolOpticalDepth550LandQaFlag".equals(parameters[i])) {
            builder.append("deepBlueAerosolOpticalDepth550LandQaFlag");
         }
         if ("deepBlueAerosolOpticalDepth550LandStd".equals(parameters[i])) {
            builder.append("deepBlueAerosolOpticalDepth550LandStd");
         }
         if ("deepBlueAlgorithmFlagLand".equals(parameters[i])) {
            builder.append("deepBlueAlgorithmFlagLand");
         }
         if ("deepBlueAngstromExponentLand".equals(parameters[i])) {
            builder.append("deepBlueAngstromExponentLand");
         }
         if ("deepBlueCloudFractionLand".equals(parameters[i])) {
            builder.append("deepBlueCloudFractionLand");
         }
         if ("deepBlueNumberPixelsUsed550Land".equals(parameters[i])) {
            builder.append("deepBlueNumberPixelsUsed550Land");
         }
         if ("deepBlueSpectralAerosolOpticalDepthLand".equals(parameters[i])) {
            builder.append("deepBlueSpectralAerosolOpticalDepthLand");
         }
         if ("deepBlueSpectralSingleScatteringAlbedoLand".equals(parameters[i])) {
            builder.append("deepBlueSpectralSingleScatteringAlbedoLand");
         }
         if ("deepBlueSpectralSurfaceReflectanceLand".equals(parameters[i])) {
            builder.append("deepBlueSpectralSurfaceReflectanceLand");
         }
         if ("deepBlueSpectralToaReflectanceLand".equals(parameters[i])) {
            builder.append("deepBlueSpectralToaReflectanceLand");
         }
         if ("effectiveOpticalDepth0p55umOcean".equals(parameters[i])) {
            builder.append("effectiveOpticalDepth0p55umOcean");
         }
         if ("effectiveOpticalDepthAverageOcean".equals(parameters[i])) {
            builder.append("effectiveOpticalDepthAverageOcean");
         }
         if ("effectiveOpticalDepthBestOcean".equals(parameters[i])) {
            builder.append("effectiveOpticalDepthBestOcean");
         }
         if ("effectiveRadiusOcean".equals(parameters[i])) {
            builder.append("effectiveRadiusOcean");
         }
         if ("fittingErrorLand".equals(parameters[i])) {
            builder.append("fittingErrorLand");
         }
         if ("glintAngle".equals(parameters[i])) {
            builder.append("glintAngle");
         }
         if ("imageOpticalDepthLandAndOcean".equals(parameters[i])) {
            builder.append("imageOpticalDepthLandAndOcean");
         }
         if ("landOceanQualityFlag".equals(parameters[i])) {
            builder.append("landOceanQualityFlag");
         }
         if ("landSeaFlag".equals(parameters[i])) {
            builder.append("landSeaFlag");
         }
         if ("leastSquaresErrorOcean".equals(parameters[i])) {
            builder.append("leastSquaresErrorOcean");
         }
         if ("massConcentrationLand".equals(parameters[i])) {
            builder.append("massConcentrationLand");
         }
         if ("massConcentrationOcean".equals(parameters[i])) {
            builder.append("massConcentrationOcean");
         }
         if ("meanReflectanceLand".equals(parameters[i])) {
            builder.append("meanReflectanceLand");
         }
         if ("meanReflectanceOcean".equals(parameters[i])) {
            builder.append("meanReflectanceOcean");
         }
         if ("numberPixelsUsedLand".equals(parameters[i])) {
            builder.append("numberPixelsUsedLand");
         }
         if ("numberPixelsUsedOcean".equals(parameters[i])) {
            builder.append("numberPixelsUsedOcean");
         }
         if ("opticalDepthByModelsOcean".equals(parameters[i])) {
            builder.append("opticalDepthByModelsOcean");
         }
         if ("opticalDepthLandAndOcean".equals(parameters[i])) {
            builder.append("opticalDepthLandAndOcean");
         }
         if ("opticalDepthLargeAverageOcean".equals(parameters[i])) {
            builder.append("opticalDepthLargeAverageOcean");
         }
         if ("opticalDepthLargeBestOcean".equals(parameters[i])) {
            builder.append("opticalDepthLargeBestOcean");
         }
         if ("opticalDepthRatioSmallLand".equals(parameters[i])) {
            builder.append("opticalDepthRatioSmallLand");
         }
         if ("opticalDepthRatioSmallOcean0p55micron".equals(parameters[i])) {
            builder.append("opticalDepthRatioSmallOcean0p55micron");
         }
         if ("opticalDepthSmallAverageOcean".equals(parameters[i])) {
            builder.append("opticalDepthSmallAverageOcean");
         }
         if ("opticalDepthSmallBestOcean".equals(parameters[i])) {
            builder.append("opticalDepthSmallBestOcean");
         }
         if ("psml003Ocean".equals(parameters[i])) {
            builder.append("psml003Ocean");
         }
         if ("qualityAssuranceLand".equals(parameters[i])) {
            builder.append("qualityAssuranceLand");
         }
         if ("qualityAssuranceOcean".equals(parameters[i])) {
            builder.append("qualityAssuranceOcean");
         }
         if ("scanStartTime".equals(parameters[i])) {
            builder.append("scanStartTime");
         }
         if ("scatteringAngle".equals(parameters[i])) {
            builder.append("scatteringAngle");
         }
         if ("sensorAzimuth".equals(parameters[i])) {
            builder.append("sensorAzimuth");
         }
         if ("sensorZenith".equals(parameters[i])) {
            builder.append("sensorZenith");
         }
         if ("solarAzimuth".equals(parameters[i])) {
            builder.append("solarAzimuth");
         }
         if ("solarZenith".equals(parameters[i])) {
            builder.append("solarZenith");
         }
         if ("solutionIndexOceanLarge".equals(parameters[i])) {
            builder.append("solutionIndexOceanLarge");
         }
         if ("solutionIndexOceanSmall".equals(parameters[i])) {
            builder.append("solutionIndexOceanSmall");
         }
         if ("stdReflectanceLand".equals(parameters[i])) {
            builder.append("stdReflectanceLand");
         }
         if ("stdReflectanceOcean".equals(parameters[i])) {
            builder.append("stdReflectanceOcean");
         }
         if ("surfaceReflectanceLand".equals(parameters[i])) {
            builder.append("surfaceReflectanceLand");
         }
         if ("topographicAltitudeLand".equals(parameters[i])) {
            builder.append("topographicAltitudeLand");
         }
         if ("windSpeedNcepOcean".equals(parameters[i])) {
            builder.append("windSpeedNcepOcean");
         }
      }
      builder.append("numePozitie");
      builder.append("hdfFileName");
      return builder.toString();
   }
}