package ro.grig.multipoint;

import hdfextractor.Mod04TO;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Mod04TOMock {

   public Mod04TO mockV1() {
      Mod04TO mock = new Mod04TO();
      DateTimeFormatter a = DateTimeFormat.forPattern("yyyyMMddHHmm");
      mock.setTimp(a.parseDateTime("201103080831"));
      mock.setDatabaseKey("K201103080831044481476026055500");
      mock.setHdfFileName("MOD04_L2.A2011067.0830.051.2011073035449.hdf");
      mock.setNumePozitie("anm_front");
      mock.setPozitie(new Long(6621));
      mock.setPozitieAlong(new Long(49));
      mock.setPozitieAcross(new Long(6));
      mock.setLatitudine(44.481475830078125);
      mock.setStatie("anm_front");
      mock.setLongitudine(26.055500030517578);
      mock.setSquarSizeM(10000);
      mock.setAerosolTypeLand(null);
      mock.setAngstromExponent1Ocean0(null);
      mock.setAngstromExponent1Ocean1(null);
      mock.setAngstromExponent2Ocean0(null);
      mock.setAngstromExponent2Ocean1(null);
      mock.setAngstromExponentLand(null);
      mock.setCloudCondensationNucleiOcean0(null);
      mock.setCloudCondensationNucleiOcean1(null);
      mock.setCloudFractionLand(1.0000000474974513);
      mock.setCloudFractionOcean(null);
      mock.setCloudMaskQA((byte) 55);
      mock.setCorrectedOpticalDepthLand0(null);
      mock.setCorrectedOpticalDepthLand1(null);
      mock.setCorrectedOpticalDepthLand2(null);
      mock.setCorrectedOpticalDepthLandwav2p1(null);
      mock.setCriticalReflectanceLand0(null);
      mock.setCriticalReflectanceLand1(null);
      mock.setDeepBlueAerosolOpticalDepth550Land(null);
      mock.setDeepBlueAerosolOpticalDepth550LandSTD(null);
      mock.setDeepBlueAerosolOpticalDepthLand0(null);
      mock.setDeepBlueAerosolOpticalDepthLand1(null);
      mock.setDeepBlueAerosolOpticalDepthLand2(null);
      mock.setDeepBlueAerosolOpticalDepthLandSTD0(null);
      mock.setDeepBlueAerosolOpticalDepthLandSTD1(null);
      mock.setDeepBlueAerosolOpticalDepthLandSTD2(null);
      mock.setDeepBlueAngstromExponentLand(null);
      mock.setDeepBlueMeanReflectanceLand0(null);
      mock.setDeepBlueMeanReflectanceLand1(null);
      mock.setDeepBlueMeanReflectanceLand2(null);
      mock.setDeepBlueNumberPixelsUsedLand0(null);
      mock.setDeepBlueNumberPixelsUsedLand1(null);
      mock.setDeepBlueNumberPixelsUsedLand2(null);
      mock.setDeepBlueSingleScatteringAlbedoLand0(null);
      mock.setDeepBlueSingleScatteringAlbedoLand1(null);
      mock.setDeepBlueSingleScatteringAlbedoLand2(null);
      mock.setDeepBlueSurfaceReflectanceLand0(null);
      mock.setDeepBlueSurfaceReflectanceLand1(null);
      mock.setDeepBlueSurfaceReflectanceLand2(null);
      mock.setEffectiveRadiusOcean0(null);
      mock.setEffectiveRadiusOcean1(null);
      mock.setErrorCriticalReflectanceLand0(null);
      mock.setErrorCriticalReflectanceLand1(null);
      mock.setErrorPathRadianceLand0(null);
      mock.setErrorPathRadianceLand1(null);
      mock.setFittingErrorLand(null);
      mock.setImageOpticalDepthLandAndOcean(null);
      mock.setLeastSquaresErrorOcean0(null);
      mock.setLeastSquaresErrorOcean1(null);
      mock.setMassConcentrationLand(null);
      mock.setMassConcentrationOcean0(null);
      mock.setMassConcentrationOcean1(null);
      mock.setMeanReflectanceLandAll0(null);
      mock.setMeanReflectanceLandAll1(null);
      mock.setMeanReflectanceLandAll2(null);
      mock.setNumberPixelsUsedLand0(null);
      mock.setNumberPixelsUsedLand1(null);
      mock.setNumberPixelsUsedOcean(null);
      mock.setOpticalDepthLandAndOcean(null);
      mock.setOpticalDepthRatioSmallLand(null);
      mock.setOpticalDepthRatioSmallLandAndOcean(null);
      mock.setOpticalDepthRatioSmallOcean055micron0(null);
      mock.setOpticalDepthRatioSmallOcean055micron1(null);
      mock.setOpticalDepthSmallLand0(null);
      mock.setOpticalDepthSmallLand1(null);
      mock.setOpticalDepthSmallLand2(null);
      mock.setOpticalDepthSmallLand3(null);
      mock.setPathRadianceLand0(null);
      mock.setPathRadianceLand1(null);
      mock.setQualityWeightCriticalReflectanceLand0(null);
      mock.setQualityWeightCriticalReflectanceLand1(null);
      mock.setQualityWeightPathRadianceLand0(null);
      mock.setQualityWeightPathRadianceLand1(null);
      mock.setScanStartTime(5.73726680703293E8);
      mock.setScatteringAngle(137.76999692060053);
      mock.setSensorAzimuth(93.59999790787697);
      mock.setSensorZenith(58.029998702928424);
      mock.setSolarAzimuth(144.4499967712909);
      mock.setSolarZenith(55.80999875254929);
      mock.setSolutionIndexOceanLarge0(null);
      mock.setSolutionIndexOceanLarge1(null);
      mock.setSolutionIndexOceanSmall0(null);
      mock.setSolutionIndexOceanSmall1(null);
      mock.setStandardDeviationReflectanceLandAll0(null);
      mock.setStandardDeviationReflectanceLandAll1(null);
      mock.setStandardDeviationReflectanceLandAll2(null);
      mock.setSurfaceReflectanceLand0(null);
      mock.setSurfaceReflectanceLand1(null);
      mock.setSurfaceReflectanceLand2(null);
      return mock;
   }

   public Mod04TO mockV2() {
      Mod04TO mock = new Mod04TO();
      return mock;
   }
}
