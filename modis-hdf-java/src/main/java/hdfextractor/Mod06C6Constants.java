package hdfextractor;

import java.io.Serializable;
public class Mod06C6Constants implements Serializable {

	/**
	 * Field:Above-cloud water vapor amount from 0.94um channel, ocean only, tau > 5. Tip:DFNT_INT16 Dim:2
	 */
	final static public String ABOVE_CLOUD_WATER_VAPOR_094 = "Above_Cloud_Water_Vapor_094";
	/**
	 * Property name in TO aboveCloudWaterVapor094
	 */
	final static public String P_ABOVE_CLOUD_WATER_VAPOR_094 = "aboveCloudWaterVapor094";
	/**
	 * Field:Ice Asymmetry Parameter from the phase functions used to generate  the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	final static public String ASYMMETRY_PARAMETER_ICE = "Asymmetry_Parameter_Ice";
	/**
	 * Property name in TO asymmetryParameterIce
	 */
	final static public String P_ASYMMETRY_PARAMETER_ICE = "asymmetryParameterIce";
	/**
	 * Field:Liquid Water Asymmetry Parameter from the phase functions used  to generate the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	final static public String ASYMMETRY_PARAMETER_LIQ = "Asymmetry_Parameter_Liq";
	/**
	 * Property name in TO asymmetryParameterLiq
	 */
	final static public String P_ASYMMETRY_PARAMETER_LIQ = "asymmetryParameterLiq";
	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	final static public String ATM_CORR_REFL = "Atm_Corr_Refl";
	/**
	 * Property name in TO atmCorrRefl
	 */
	final static public String P_ATM_CORR_REFL = "atmCorrRefl";
	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	final static public String BRIGHTNESS_TEMPERATURE = "Brightness_Temperature";
	/**
	 * Property name in TO brightnessTemperature
	 */
	final static public String P_BRIGHTNESS_TEMPERATURE = "brightnessTemperature";
	/**
	 * Field:Cirrus Reflectance Tip:DFNT_INT16 Dim:2
	 */
	final static public String CIRRUS_REFLECTANCE = "Cirrus_Reflectance";
	/**
	 * Property name in TO cirrusReflectance
	 */
	final static public String P_CIRRUS_REFLECTANCE = "cirrusReflectance";
	/**
	 * Field:Cirrus Reflectance Flag Tip:DFNT_INT8 Dim:2
	 */
	final static public String CIRRUS_REFLECTANCE_FLAG = "Cirrus_Reflectance_Flag";
	/**
	 * Property name in TO cirrusReflectanceFlag
	 */
	final static public String P_CIRRUS_REFLECTANCE_FLAG = "cirrusReflectanceFlag";
	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_EMISSIVITY = "Cloud_Effective_Emissivity";
	/**
	 * Property name in TO cloudEffectiveEmissivity
	 */
	final static public String P_CLOUD_EFFECTIVE_EMISSIVITY = "cloudEffectiveEmissivity";
	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval, Day Only Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_EMISSIVITY_DAY = "Cloud_Effective_Emissivity_Day";
	/**
	 * Property name in TO cloudEffectiveEmissivityDay
	 */
	final static public String P_CLOUD_EFFECTIVE_EMISSIVITY_DAY = "cloudEffectiveEmissivityDay";
	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval for Sensor Zenith (View) Angles <= 32 Degrees Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_EMISSIVITY_NADIR = "Cloud_Effective_Emissivity_Nadir";
	/**
	 * Property name in TO cloudEffectiveEmissivityNadir
	 */
	final static public String P_CLOUD_EFFECTIVE_EMISSIVITY_NADIR = "cloudEffectiveEmissivityNadir";
	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval for Sensor Zenith (View) Angles <= 32 Degrees, Day Data Only Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_EMISSIVITY_NADIR_DAY = "Cloud_Effective_Emissivity_Nadir_Day";
	/**
	 * Property name in TO cloudEffectiveEmissivityNadirDay
	 */
	final static public String P_CLOUD_EFFECTIVE_EMISSIVITY_NADIR_DAY = "cloudEffectiveEmissivityNadirDay";
	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval for Sensor Zenith (View) Angles <= 32 Degrees, Night Data Only Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_EMISSIVITY_NADIR_NIGHT = "Cloud_Effective_Emissivity_Nadir_Night";
	/**
	 * Property name in TO cloudEffectiveEmissivityNadirNight
	 */
	final static public String P_CLOUD_EFFECTIVE_EMISSIVITY_NADIR_NIGHT = "cloudEffectiveEmissivityNadirNight";
	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval, Night Only Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_EMISSIVITY_NIGHT = "Cloud_Effective_Emissivity_Night";
	/**
	 * Property name in TO cloudEffectiveEmissivityNight
	 */
	final static public String P_CLOUD_EFFECTIVE_EMISSIVITY_NIGHT = "cloudEffectiveEmissivityNight";
	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS = "Cloud_Effective_Radius";
	/**
	 * Property name in TO cloudEffectiveRadius
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS = "cloudEffectiveRadius";
	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS_16 = "Cloud_Effective_Radius_16";
	/**
	 * Property name in TO cloudEffectiveRadius16
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS_16 = "cloudEffectiveRadius16";
	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and band 6(1.6um)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS_1621 = "Cloud_Effective_Radius_1621";
	/**
	 * Property name in TO cloudEffectiveRadius1621
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS_1621 = "cloudEffectiveRadius1621";
	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and band 6(1.6um)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS_1621_PCL = "Cloud_Effective_Radius_1621_PCL";
	/**
	 * Property name in TO cloudEffectiveRadius1621Pcl
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS_1621_PCL = "cloudEffectiveRadius1621Pcl";
	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS_16_PCL = "Cloud_Effective_Radius_16_PCL";
	/**
	 * Property name in TO cloudEffectiveRadius16Pcl
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS_16_PCL = "cloudEffectiveRadius16Pcl";
	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS_37 = "Cloud_Effective_Radius_37";
	/**
	 * Property name in TO cloudEffectiveRadius37
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS_37 = "cloudEffectiveRadius37";
	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS_37_PCL = "Cloud_Effective_Radius_37_PCL";
	/**
	 * Property name in TO cloudEffectiveRadius37Pcl
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS_37_PCL = "cloudEffectiveRadius37Pcl";
	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS_PCL = "Cloud_Effective_Radius_PCL";
	/**
	 * Property name in TO cloudEffectiveRadiusPcl
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS_PCL = "cloudEffectiveRadiusPcl";
	/**
	 * Field:Cloud Effective Particle Radius (from band 7(2.1um)) Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY = "Cloud_Effective_Radius_Uncertainty";
	/**
	 * Property name in TO cloudEffectiveRadiusUncertainty
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY = "cloudEffectiveRadiusUncertainty";
	/**
	 * Field:Cloud Effective Particle Radius (from band 6(1.6um) Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_16 = "Cloud_Effective_Radius_Uncertainty_16";
	/**
	 * Property name in TO cloudEffectiveRadiusUncertainty16
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_16 = "cloudEffectiveRadiusUncertainty16";
	/**
	 * Field:Cloud Effective Particle Radius Relative Uncertainty (Percent) using band 7(2.1um) and band 6(1.6um)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_1621 = "Cloud_Effective_Radius_Uncertainty_1621";
	/**
	 * Property name in TO cloudEffectiveRadiusUncertainty1621
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_1621 = "cloudEffectiveRadiusUncertainty1621";
	/**
	 * Field:Cloud Effective Particle Radius  (from band 20(3.7um)) Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_37 = "Cloud_Effective_Radius_Uncertainty_37";
	/**
	 * Property name in TO cloudEffectiveRadiusUncertainty37
	 */
	final static public String P_CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_37 = "cloudEffectiveRadiusUncertainty37";
	/**
	 * Field:11 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EMISS11_1KM = "cloud_emiss11_1km";
	/**
	 * Property name in TO cloudEmiss111km
	 */
	final static public String P_CLOUD_EMISS11_1KM = "cloudEmiss111km";
	/**
	 * Field:12 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EMISS12_1KM = "cloud_emiss12_1km";
	/**
	 * Property name in TO cloudEmiss121km
	 */
	final static public String P_CLOUD_EMISS12_1KM = "cloudEmiss121km";
	/**
	 * Field:13.3 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EMISS13_1KM = "cloud_emiss13_1km";
	/**
	 * Property name in TO cloudEmiss131km
	 */
	final static public String P_CLOUD_EMISS13_1KM = "cloudEmiss131km";
	/**
	 * Field:8.5 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_EMISS85_1KM = "cloud_emiss85_1km";
	/**
	 * Property name in TO cloudEmiss851km
	 */
	final static public String P_CLOUD_EMISS85_1KM = "cloudEmiss851km";
	/**
	 * Field:Cloud Emissivity at 1-km resolution from LEOCAT Cloud Top Pressure Retrieval Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_EMISSIVITY_1KM = "cloud_emissivity_1km";
	/**
	 * Property name in TO cloudEmissivity1km
	 */
	final static public String P_CLOUD_EMISSIVITY_1KM = "cloudEmissivity1km";
	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_FRACTION = "Cloud_Fraction";
	/**
	 * Property name in TO cloudFraction
	 */
	final static public String P_CLOUD_FRACTION = "cloudFraction";
	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask, Day Only Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_FRACTION_DAY = "Cloud_Fraction_Day";
	/**
	 * Property name in TO cloudFractionDay
	 */
	final static public String P_CLOUD_FRACTION_DAY = "cloudFractionDay";
	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask for Sensor Zenith (View) Angles <= 32 Degrees Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_FRACTION_NADIR = "Cloud_Fraction_Nadir";
	/**
	 * Property name in TO cloudFractionNadir
	 */
	final static public String P_CLOUD_FRACTION_NADIR = "cloudFractionNadir";
	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask for Sensor Zenith (View) Angles <= 32 Degrees, Day Data Only Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_FRACTION_NADIR_DAY = "Cloud_Fraction_Nadir_Day";
	/**
	 * Property name in TO cloudFractionNadirDay
	 */
	final static public String P_CLOUD_FRACTION_NADIR_DAY = "cloudFractionNadirDay";
	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask for Sensor Zenith (View) Angles <= 32 Degrees, Night Data Only Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_FRACTION_NADIR_NIGHT = "Cloud_Fraction_Nadir_Night";
	/**
	 * Property name in TO cloudFractionNadirNight
	 */
	final static public String P_CLOUD_FRACTION_NADIR_NIGHT = "cloudFractionNadirNight";
	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask, Night Only Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_FRACTION_NIGHT = "Cloud_Fraction_Night";
	/**
	 * Property name in TO cloudFractionNight
	 */
	final static public String P_CLOUD_FRACTION_NIGHT = "cloudFractionNight";
	/**
	 * Field:Index Indicating MODIS Bands Used for Cloud Top Pressure Retrieval Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_HEIGHT_METHOD = "Cloud_Height_Method";
	/**
	 * Property name in TO cloudHeightMethod
	 */
	final static public String P_CLOUD_HEIGHT_METHOD = "cloudHeightMethod";
	/**
	 * Field:MODIS Cloud Mask, L2 MOD06 QA Plan Tip:DFNT_INT8 Dim:3
	 */
	final static public String CLOUD_MASK_1KM = "Cloud_Mask_1km";
	/**
	 * Property name in TO cloudMask1km
	 */
	final static public String P_CLOUD_MASK_1KM = "cloudMask1km";
	/**
	 * Field:First Byte of MODIS Cloud Mask Plus Additional Stats for L3 (2nd Byte) Tip:DFNT_INT8 Dim:3
	 */
	final static public String CLOUD_MASK_5KM = "Cloud_Mask_5km";
	/**
	 * Property name in TO cloudMask5km
	 */
	final static public String P_CLOUD_MASK_5KM = "cloudMask5km";
	/**
	 * Field:Dispersion in bands 1 (plane 1) and 2 (plane 2) from 250m reflectance  statistics of cloud mask Tip:DFNT_INT16 Dim:3
	 */
	final static public String CLOUD_MASK_SPI = "Cloud_Mask_SPI";
	/**
	 * Property name in TO cloudMaskSpi
	 */
	final static public String P_CLOUD_MASK_SPI = "cloudMaskSpi";
	/**
	 * Field:Cloud Multi Layer Identification From MODIS Shortwave Observations Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_MULTI_LAYER_FLAG = "Cloud_Multi_Layer_Flag";
	/**
	 * Property name in TO cloudMultiLayerFlag
	 */
	final static public String P_CLOUD_MULTI_LAYER_FLAG = "cloudMultiLayerFlag";
	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS = "Cloud_Optical_Thickness";
	/**
	 * Property name in TO cloudOpticalThickness
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS = "cloudOpticalThickness";
	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS_16 = "Cloud_Optical_Thickness_16";
	/**
	 * Property name in TO cloudOpticalThickness16
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS_16 = "cloudOpticalThickness16";
	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and band 6(1.6um)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS_1621 = "Cloud_Optical_Thickness_1621";
	/**
	 * Property name in TO cloudOpticalThickness1621
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS_1621 = "cloudOpticalThickness1621";
	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and band 6(1.6um)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS_1621_PCL = "Cloud_Optical_Thickness_1621_PCL";
	/**
	 * Property name in TO cloudOpticalThickness1621Pcl
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS_1621_PCL = "cloudOpticalThickness1621Pcl";
	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS_16_PCL = "Cloud_Optical_Thickness_16_PCL";
	/**
	 * Property name in TO cloudOpticalThickness16Pcl
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS_16_PCL = "cloudOpticalThickness16Pcl";
	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS_37 = "Cloud_Optical_Thickness_37";
	/**
	 * Property name in TO cloudOpticalThickness37
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS_37 = "cloudOpticalThickness37";
	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS_37_PCL = "Cloud_Optical_Thickness_37_PCL";
	/**
	 * Property name in TO cloudOpticalThickness37Pcl
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS_37_PCL = "cloudOpticalThickness37Pcl";
	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS_PCL = "Cloud_Optical_Thickness_PCL";
	/**
	 * Property name in TO cloudOpticalThicknessPcl
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS_PCL = "cloudOpticalThicknessPcl";
	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Optical_Thickness and Cloud_Effective_Radius results Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS_UNCERTAINTY = "Cloud_Optical_Thickness_Uncertainty";
	/**
	 * Property name in TO cloudOpticalThicknessUncertainty
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS_UNCERTAINTY = "cloudOpticalThicknessUncertainty";
	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Optical_Thickness_16 and Cloud_Effective_Radius_16 results Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_16 = "Cloud_Optical_Thickness_Uncertainty_16";
	/**
	 * Property name in TO cloudOpticalThicknessUncertainty16
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_16 = "cloudOpticalThicknessUncertainty16";
	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty  (Percent) using band 7(2.1um) and band 6(1.6um)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_1621 = "Cloud_Optical_Thickness_Uncertainty_1621";
	/**
	 * Property name in TO cloudOpticalThicknessUncertainty1621
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_1621 = "cloudOpticalThicknessUncertainty1621";
	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Optical_Thickness_37 and Cloud_Effective_Radius_37 results Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_37 = "Cloud_Optical_Thickness_Uncertainty_37";
	/**
	 * Property name in TO cloudOpticalThicknessUncertainty37
	 */
	final static public String P_CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_37 = "cloudOpticalThicknessUncertainty37";
	/**
	 * Field:Cloud Phase from 8.5 and 11 um Bands Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_PHASE_INFRARED = "Cloud_Phase_Infrared";
	/**
	 * Property name in TO cloudPhaseInfrared
	 */
	final static public String P_CLOUD_PHASE_INFRARED = "cloudPhaseInfrared";
	/**
	 * Field:Cloud Phase at 1-km resolution from 8.5- 11 um BTDs and cloud emissivity ratios (12/11, 8.5/11, and 7.2/11 um) Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_PHASE_INFRARED_1KM = "Cloud_Phase_Infrared_1km";
	/**
	 * Property name in TO cloudPhaseInfrared1km
	 */
	final static public String P_CLOUD_PHASE_INFRARED_1KM = "cloudPhaseInfrared1km";
	/**
	 * Field:Cloud Phase from 8.5 and 11 um Bands, Day Only Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_PHASE_INFRARED_DAY = "Cloud_Phase_Infrared_Day";
	/**
	 * Property name in TO cloudPhaseInfraredDay
	 */
	final static public String P_CLOUD_PHASE_INFRARED_DAY = "cloudPhaseInfraredDay";
	/**
	 * Field:Cloud Phase from 8.5 and 11 um Bands, Night Only Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_PHASE_INFRARED_NIGHT = "Cloud_Phase_Infrared_Night";
	/**
	 * Property name in TO cloudPhaseInfraredNight
	 */
	final static public String P_CLOUD_PHASE_INFRARED_NIGHT = "cloudPhaseInfraredNight";
	/**
	 * Field:Cloud Phase Determination Used in Optical Thickness/Effective Radius Retrieval Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_PHASE_OPTICAL_PROPERTIES = "Cloud_Phase_Optical_Properties";
	/**
	 * Property name in TO cloudPhaseOpticalProperties
	 */
	final static public String P_CLOUD_PHASE_OPTICAL_PROPERTIES = "cloudPhaseOpticalProperties";
	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_HEIGHT = "Cloud_Top_Height";
	/**
	 * Property name in TO cloudTopHeight
	 */
	final static public String P_CLOUD_TOP_HEIGHT = "cloudTopHeight";
	/**
	 * Field:Cloud Top Height at 1-km resolution from LEOCAT, Geopotential Height at Retrieved Cloud Top Pressure Level rounded to nearest 50 m Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_HEIGHT_1KM = "cloud_top_height_1km";
	/**
	 * Property name in TO cloudTopHeight1km
	 */
	final static public String P_CLOUD_TOP_HEIGHT_1KM = "cloudTopHeight1km";
	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles  <=32 Degrees (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_HEIGHT_NADIR = "Cloud_Top_Height_Nadir";
	/**
	 * Property name in TO cloudTopHeightNadir
	 */
	final static public String P_CLOUD_TOP_HEIGHT_NADIR = "cloudTopHeightNadir";
	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles  <=32 Degrees, Day Data Only (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_HEIGHT_NADIR_DAY = "Cloud_Top_Height_Nadir_Day";
	/**
	 * Property name in TO cloudTopHeightNadirDay
	 */
	final static public String P_CLOUD_TOP_HEIGHT_NADIR_DAY = "cloudTopHeightNadirDay";
	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles  <=32 Degrees, Night Data Only (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_HEIGHT_NADIR_NIGHT = "Cloud_Top_Height_Nadir_Night";
	/**
	 * Property name in TO cloudTopHeightNadirNight
	 */
	final static public String P_CLOUD_TOP_HEIGHT_NADIR_NIGHT = "cloudTopHeightNadirNight";
	/**
	 * Field:Index Indicating the MODIS Band(s) Used to Produce the Cloud Top Pressure Result Tip:DFNT_INT8 Dim:2
	 */
	final static public String CLOUD_TOP_METHOD_1KM = "cloud_top_method_1km";
	/**
	 * Property name in TO cloudTopMethod1km
	 */
	final static public String P_CLOUD_TOP_METHOD_1KM = "cloudTopMethod1km";
	/**
	 * Field:Cloud Top Pressure Level (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_PRESSURE = "Cloud_Top_Pressure";
	/**
	 * Property name in TO cloudTopPressure
	 */
	final static public String P_CLOUD_TOP_PRESSURE = "cloudTopPressure";
	/**
	 * Field:Cloud Top Pressure at 1-km resolution from LEOCAT, Cloud Top Pressure Level rounded to nearest 5 mb Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_PRESSURE_1KM = "cloud_top_pressure_1km";
	/**
	 * Property name in TO cloudTopPressure1km
	 */
	final static public String P_CLOUD_TOP_PRESSURE_1KM = "cloudTopPressure1km";
	/**
	 * Field:Cloud Top Pressure Level, Day Only (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_PRESSURE_DAY = "Cloud_Top_Pressure_Day";
	/**
	 * Property name in TO cloudTopPressureDay
	 */
	final static public String P_CLOUD_TOP_PRESSURE_DAY = "cloudTopPressureDay";
	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	final static public String CLOUD_TOP_PRESSURE_FROM_RATIOS = "Cloud_Top_Pressure_From_Ratios";
	/**
	 * Property name in TO cloudTopPressureFromRatios
	 */
	final static public String P_CLOUD_TOP_PRESSURE_FROM_RATIOS = "cloudTopPressureFromRatios";
	/**
	 * Field:Cloud Top Pressure from IR Window Retrieval Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_PRESSURE_INFRARED = "Cloud_Top_Pressure_Infrared";
	/**
	 * Property name in TO cloudTopPressureInfrared
	 */
	final static public String P_CLOUD_TOP_PRESSURE_INFRARED = "cloudTopPressureInfrared";
	/**
	 * Field:Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_PRESSURE_NADIR = "Cloud_Top_Pressure_Nadir";
	/**
	 * Property name in TO cloudTopPressureNadir
	 */
	final static public String P_CLOUD_TOP_PRESSURE_NADIR = "cloudTopPressureNadir";
	/**
	 * Field:Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees (rounded to nearest 5 mb), Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_PRESSURE_NADIR_DAY = "Cloud_Top_Pressure_Nadir_Day";
	/**
	 * Property name in TO cloudTopPressureNadirDay
	 */
	final static public String P_CLOUD_TOP_PRESSURE_NADIR_DAY = "cloudTopPressureNadirDay";
	/**
	 * Field:Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees (rounded to nearest 5 mb), Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_PRESSURE_NADIR_NIGHT = "Cloud_Top_Pressure_Nadir_Night";
	/**
	 * Property name in TO cloudTopPressureNadirNight
	 */
	final static public String P_CLOUD_TOP_PRESSURE_NADIR_NIGHT = "cloudTopPressureNadirNight";
	/**
	 * Field:Cloud Top Pressure Level, Night Data Only (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_PRESSURE_NIGHT = "Cloud_Top_Pressure_Night";
	/**
	 * Property name in TO cloudTopPressureNight
	 */
	final static public String P_CLOUD_TOP_PRESSURE_NIGHT = "cloudTopPressureNight";
	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_TEMPERATURE = "Cloud_Top_Temperature";
	/**
	 * Property name in TO cloudTopTemperature
	 */
	final static public String P_CLOUD_TOP_TEMPERATURE = "cloudTopTemperature";
	/**
	 * Field:Cloud Top Temperature at 1-km resolution from LEOCAT, Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_TEMPERATURE_1KM = "cloud_top_temperature_1km";
	/**
	 * Property name in TO cloudTopTemperature1km
	 */
	final static public String P_CLOUD_TOP_TEMPERATURE_1KM = "cloudTopTemperature1km";
	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level, Day Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_TEMPERATURE_DAY = "Cloud_Top_Temperature_Day";
	/**
	 * Property name in TO cloudTopTemperatureDay
	 */
	final static public String P_CLOUD_TOP_TEMPERATURE_DAY = "cloudTopTemperatureDay";
	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_TEMPERATURE_NADIR = "Cloud_Top_Temperature_Nadir";
	/**
	 * Property name in TO cloudTopTemperatureNadir
	 */
	final static public String P_CLOUD_TOP_TEMPERATURE_NADIR = "cloudTopTemperatureNadir";
	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_TEMPERATURE_NADIR_DAY = "Cloud_Top_Temperature_Nadir_Day";
	/**
	 * Property name in TO cloudTopTemperatureNadirDay
	 */
	final static public String P_CLOUD_TOP_TEMPERATURE_NADIR_DAY = "cloudTopTemperatureNadirDay";
	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_TEMPERATURE_NADIR_NIGHT = "Cloud_Top_Temperature_Nadir_Night";
	/**
	 * Property name in TO cloudTopTemperatureNadirNight
	 */
	final static public String P_CLOUD_TOP_TEMPERATURE_NADIR_NIGHT = "cloudTopTemperatureNadirNight";
	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level, Night Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_TOP_TEMPERATURE_NIGHT = "Cloud_Top_Temperature_Night";
	/**
	 * Property name in TO cloudTopTemperatureNight
	 */
	final static public String P_CLOUD_TOP_TEMPERATURE_NIGHT = "cloudTopTemperatureNight";
	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH = "Cloud_Water_Path";
	/**
	 * Property name in TO cloudWaterPath
	 */
	final static public String P_CLOUD_WATER_PATH = "cloudWaterPath";
	/**
	 * Field:Column Water Path two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH_16 = "Cloud_Water_Path_16";
	/**
	 * Property name in TO cloudWaterPath16
	 */
	final static public String P_CLOUD_WATER_PATH_16 = "cloudWaterPath16";
	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and band 6(1.6um)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH_1621 = "Cloud_Water_Path_1621";
	/**
	 * Property name in TO cloudWaterPath1621
	 */
	final static public String P_CLOUD_WATER_PATH_1621 = "cloudWaterPath1621";
	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and band 6(1.6um)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH_1621_PCL = "Cloud_Water_Path_1621_PCL";
	/**
	 * Property name in TO cloudWaterPath1621Pcl
	 */
	final static public String P_CLOUD_WATER_PATH_1621_PCL = "cloudWaterPath1621Pcl";
	/**
	 * Field:Column Water Path two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH_16_PCL = "Cloud_Water_Path_16_PCL";
	/**
	 * Property name in TO cloudWaterPath16Pcl
	 */
	final static public String P_CLOUD_WATER_PATH_16_PCL = "cloudWaterPath16Pcl";
	/**
	 * Field:Column Water Path two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH_37 = "Cloud_Water_Path_37";
	/**
	 * Property name in TO cloudWaterPath37
	 */
	final static public String P_CLOUD_WATER_PATH_37 = "cloudWaterPath37";
	/**
	 * Field:Column Water Path two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH_37_PCL = "Cloud_Water_Path_37_PCL";
	/**
	 * Property name in TO cloudWaterPath37Pcl
	 */
	final static public String P_CLOUD_WATER_PATH_37_PCL = "cloudWaterPath37Pcl";
	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH_PCL = "Cloud_Water_Path_PCL";
	/**
	 * Property name in TO cloudWaterPathPcl
	 */
	final static public String P_CLOUD_WATER_PATH_PCL = "cloudWaterPathPcl";
	/**
	 * Field:Cloud Water Path Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Water_Path result Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH_UNCERTAINTY = "Cloud_Water_Path_Uncertainty";
	/**
	 * Property name in TO cloudWaterPathUncertainty
	 */
	final static public String P_CLOUD_WATER_PATH_UNCERTAINTY = "cloudWaterPathUncertainty";
	/**
	 * Field:Cloud Water Path Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m using the VNSWIR-1.6um retrieval Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH_UNCERTAINTY_16 = "Cloud_Water_Path_Uncertainty_16";
	/**
	 * Property name in TO cloudWaterPathUncertainty16
	 */
	final static public String P_CLOUD_WATER_PATH_UNCERTAINTY_16 = "cloudWaterPathUncertainty16";
	/**
	 * Field:Cloud Water Path Relative Uncertainty  (Percent) using band 7(2.1um) and band 6(1.6um)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH_UNCERTAINTY_1621 = "Cloud_Water_Path_Uncertainty_1621";
	/**
	 * Property name in TO cloudWaterPathUncertainty1621
	 */
	final static public String P_CLOUD_WATER_PATH_UNCERTAINTY_1621 = "cloudWaterPathUncertainty1621";
	/**
	 * Field:Cloud Water Path Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m using the VNSWIR-3.7um retrieval Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_WATER_PATH_UNCERTAINTY_37 = "Cloud_Water_Path_Uncertainty_37";
	/**
	 * Property name in TO cloudWaterPathUncertainty37
	 */
	final static public String P_CLOUD_WATER_PATH_UNCERTAINTY_37 = "cloudWaterPathUncertainty37";
	/**
	 * Field:Ice Extinction Efficiency from the phase functions used to generate  the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	final static public String EXTINCTION_EFFICIENCY_ICE = "Extinction_Efficiency_Ice";
	/**
	 * Property name in TO extinctionEfficiencyIce
	 */
	final static public String P_EXTINCTION_EFFICIENCY_ICE = "extinctionEfficiencyIce";
	/**
	 * Field:Liquid Water CE from the phase functions used to generate the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	final static public String EXTINCTION_EFFICIENCY_LIQ = "Extinction_Efficiency_Liq";
	/**
	 * Property name in TO extinctionEfficiencyLiq
	 */
	final static public String P_EXTINCTION_EFFICIENCY_LIQ = "extinctionEfficiencyLiq";
	/**
	 * Field:Indicates Cloud_Phase_Infrared_1km results changed to ice from water when cloud_top_method_1km reports valid band 36/35 CO2-slicing result (1=change) Tip:DFNT_INT8 Dim:2
	 */
	final static public String IRP_CTH_CONSISTENCY_FLAG_1KM = "IRP_CTH_Consistency_Flag_1km";
	/**
	 * Property name in TO irpCthConsistencyFlag1km
	 */
	final static public String P_IRP_CTH_CONSISTENCY_FLAG_1KM = "irpCthConsistencyFlag1km";
	/**
	 * Field:Low Cloud Temperature from IR Window retrieval  using cloud emissivity based on cloud optical thickness Tip:DFNT_INT16 Dim:2
	 */
	final static public String IRW_LOW_CLOUD_TEMPERATURE_FROM_COP = "IRW_Low_Cloud_Temperature_From_COP";
	/**
	 * Property name in TO irwLowCloudTemperatureFromCop
	 */
	final static public String P_IRW_LOW_CLOUD_TEMPERATURE_FROM_COP = "irwLowCloudTemperatureFromCop";
	/**
	 * Field:Upper Tropospheric/Lower Stratospheric (UTLS) Cloud Flag at 1-km  resolution - valid from -50 to +50 Degrees Latitude Tip:DFNT_INT8 Dim:2
	 */
	final static public String OS_TOP_FLAG_1KM = "os_top_flag_1km";
	/**
	 * Property name in TO osTopFlag1km
	 */
	final static public String P_OS_TOP_FLAG_1KM = "osTopFlag1km";
	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	final static public String QUALITY_ASSURANCE_1KM = "Quality_Assurance_1km";
	/**
	 * Property name in TO qualityAssurance1km
	 */
	final static public String P_QUALITY_ASSURANCE_1KM = "qualityAssurance1km";
	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	final static public String QUALITY_ASSURANCE_5KM = "Quality_Assurance_5km";
	/**
	 * Property name in TO qualityAssurance5km
	 */
	final static public String P_QUALITY_ASSURANCE_5KM = "qualityAssurance5km";
	/**
	 * Field:Band 31 Radiance Standard Deviation Tip:DFNT_INT16 Dim:2
	 */
	final static public String RADIANCE_VARIANCE = "Radiance_Variance";
	/**
	 * Property name in TO radianceVariance
	 */
	final static public String P_RADIANCE_VARIANCE = "radianceVariance";
	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 7 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	final static public String RETRIEVAL_FAILURE_METRIC = "Retrieval_Failure_Metric";
	/**
	 * Property name in TO retrievalFailureMetric
	 */
	final static public String P_RETRIEVAL_FAILURE_METRIC = "retrievalFailureMetric";
	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	final static public String RETRIEVAL_FAILURE_METRIC_16 = "Retrieval_Failure_Metric_16";
	/**
	 * Property name in TO retrievalFailureMetric16
	 */
	final static public String P_RETRIEVAL_FAILURE_METRIC_16 = "retrievalFailureMetric16";
	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and band 7 Tip:DFNT_INT16 Dim:3
	 */
	final static public String RETRIEVAL_FAILURE_METRIC_1621 = "Retrieval_Failure_Metric_1621";
	/**
	 * Property name in TO retrievalFailureMetric1621
	 */
	final static public String P_RETRIEVAL_FAILURE_METRIC_1621 = "retrievalFailureMetric1621";
	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 20 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	final static public String RETRIEVAL_FAILURE_METRIC_37 = "Retrieval_Failure_Metric_37";
	/**
	 * Property name in TO retrievalFailureMetric37
	 */
	final static public String P_RETRIEVAL_FAILURE_METRIC_37 = "retrievalFailureMetric37";
	/**
	 * Field:TAI time at start of scan replicated across the swath Tip:DFNT_FLOAT32 Dim:2
	 */
	final static public String SCAN_START_TIME = "Scan_Start_Time";
	/**
	 * Property name in TO scanStartTime
	 */
	final static public String P_SCAN_START_TIME = "scanStartTime";
	/**
	 * Field:Sensor Azimuth Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
	 */
	final static public String SENSOR_AZIMUTH = "Sensor_Azimuth";
	/**
	 * Property name in TO sensorAzimuth
	 */
	final static public String P_SENSOR_AZIMUTH = "sensorAzimuth";
	/**
	 * Field:Sensor Azimuth Angle, Cell to Sensor, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String SENSOR_AZIMUTH_DAY = "Sensor_Azimuth_Day";
	/**
	 * Property name in TO sensorAzimuthDay
	 */
	final static public String P_SENSOR_AZIMUTH_DAY = "sensorAzimuthDay";
	/**
	 * Field:Sensor Azimuth Angle, Cell to Sensor, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String SENSOR_AZIMUTH_NIGHT = "Sensor_Azimuth_Night";
	/**
	 * Property name in TO sensorAzimuthNight
	 */
	final static public String P_SENSOR_AZIMUTH_NIGHT = "sensorAzimuthNight";
	/**
	 * Field:Sensor Zenith Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
	 */
	final static public String SENSOR_ZENITH = "Sensor_Zenith";
	/**
	 * Property name in TO sensorZenith
	 */
	final static public String P_SENSOR_ZENITH = "sensorZenith";
	/**
	 * Field:Sensor Zenith Angle, Cell to Sensor, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String SENSOR_ZENITH_DAY = "Sensor_Zenith_Day";
	/**
	 * Property name in TO sensorZenithDay
	 */
	final static public String P_SENSOR_ZENITH_DAY = "sensorZenithDay";
	/**
	 * Field:Sensor Zenith Angle, Cell to Sensor, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String SENSOR_ZENITH_NIGHT = "Sensor_Zenith_Night";
	/**
	 * Property name in TO sensorZenithNight
	 */
	final static public String P_SENSOR_ZENITH_NIGHT = "sensorZenithNight";
	/**
	 * Field:Ice single scatter albedo from the phase functions used to generate  the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	final static public String SINGLE_SCATTER_ALBEDO_ICE = "Single_Scatter_Albedo_Ice";
	/**
	 * Property name in TO singleScatterAlbedoIce
	 */
	final static public String P_SINGLE_SCATTER_ALBEDO_ICE = "singleScatterAlbedoIce";
	/**
	 * Field:Liquid Water single scatter albedo from the phase functions used  to generate the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	final static public String SINGLE_SCATTER_ALBEDO_LIQ = "Single_Scatter_Albedo_Liq";
	/**
	 * Property name in TO singleScatterAlbedoLiq
	 */
	final static public String P_SINGLE_SCATTER_ALBEDO_LIQ = "singleScatterAlbedoLiq";
	/**
	 * Field:Solar Azimuth Angle, Cell to Sun Tip:DFNT_INT16 Dim:2
	 */
	final static public String SOLAR_AZIMUTH = "Solar_Azimuth";
	/**
	 * Property name in TO solarAzimuth
	 */
	final static public String P_SOLAR_AZIMUTH = "solarAzimuth";
	/**
	 * Field:Solar Azimuth Angle, Cell to Sun, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String SOLAR_AZIMUTH_DAY = "Solar_Azimuth_Day";
	/**
	 * Property name in TO solarAzimuthDay
	 */
	final static public String P_SOLAR_AZIMUTH_DAY = "solarAzimuthDay";
	/**
	 * Field:Solar Azimuth Angle, Cell to Sun, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String SOLAR_AZIMUTH_NIGHT = "Solar_Azimuth_Night";
	/**
	 * Property name in TO solarAzimuthNight
	 */
	final static public String P_SOLAR_AZIMUTH_NIGHT = "solarAzimuthNight";
	/**
	 * Field:Solar Zenith Angle, Cell to Sun Tip:DFNT_INT16 Dim:2
	 */
	final static public String SOLAR_ZENITH = "Solar_Zenith";
	/**
	 * Property name in TO solarZenith
	 */
	final static public String P_SOLAR_ZENITH = "solarZenith";
	/**
	 * Field:Solar Zenith Angle, Cell to Sun, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String SOLAR_ZENITH_DAY = "Solar_Zenith_Day";
	/**
	 * Property name in TO solarZenithDay
	 */
	final static public String P_SOLAR_ZENITH_DAY = "solarZenithDay";
	/**
	 * Field:Solar Zenith Angle, Cell to Sun, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	final static public String SOLAR_ZENITH_NIGHT = "Solar_Zenith_Night";
	/**
	 * Property name in TO solarZenithNight
	 */
	final static public String P_SOLAR_ZENITH_NIGHT = "solarZenithNight";
	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	final static public String SPECTRAL_CLOUD_FORCING = "Spectral_Cloud_Forcing";
	/**
	 * Property name in TO spectralCloudForcing
	 */
	final static public String P_SPECTRAL_CLOUD_FORCING = "spectralCloudForcing";
	/**
	 * Field:Surface Pressure from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	final static public String SURFACE_PRESSURE = "Surface_Pressure";
	/**
	 * Property name in TO surfacePressure
	 */
	final static public String P_SURFACE_PRESSURE = "surfacePressure";
	/**
	 * Field:Surface Temperature from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	final static public String SURFACE_TEMPERATURE = "Surface_Temperature";
	/**
	 * Property name in TO surfaceTemperature
	 */
	final static public String P_SURFACE_TEMPERATURE = "surfaceTemperature";
	/**
	 * Field:Surface Temperature for Each 1-km MODIS Pixel Interplated from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	final static public String SURFACE_TEMPERATURE_1KM = "surface_temperature_1km";
	/**
	 * Property name in TO surfaceTemperature1km
	 */
	final static public String P_SURFACE_TEMPERATURE_1KM = "surfaceTemperature1km";
	/**
	 * Field:Tropopause Height from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	final static public String TROPOPAUSE_HEIGHT = "Tropopause_Height";
	/**
	 * Property name in TO tropopauseHeight
	 */
	final static public String P_TROPOPAUSE_HEIGHT = "tropopauseHeight";
	/**
	 * Lista campuri in acest tip
	 */
	final static public String[] fieldNames = new String[]{
			ABOVE_CLOUD_WATER_VAPOR_094, ASYMMETRY_PARAMETER_ICE,
			ASYMMETRY_PARAMETER_LIQ, ATM_CORR_REFL, BRIGHTNESS_TEMPERATURE,
			CIRRUS_REFLECTANCE, CIRRUS_REFLECTANCE_FLAG,
			CLOUD_EFFECTIVE_EMISSIVITY, CLOUD_EFFECTIVE_EMISSIVITY_DAY,
			CLOUD_EFFECTIVE_EMISSIVITY_NADIR,
			CLOUD_EFFECTIVE_EMISSIVITY_NADIR_DAY,
			CLOUD_EFFECTIVE_EMISSIVITY_NADIR_NIGHT,
			CLOUD_EFFECTIVE_EMISSIVITY_NIGHT, CLOUD_EFFECTIVE_RADIUS,
			CLOUD_EFFECTIVE_RADIUS_16, CLOUD_EFFECTIVE_RADIUS_1621,
			CLOUD_EFFECTIVE_RADIUS_1621_PCL, CLOUD_EFFECTIVE_RADIUS_16_PCL,
			CLOUD_EFFECTIVE_RADIUS_37, CLOUD_EFFECTIVE_RADIUS_37_PCL,
			CLOUD_EFFECTIVE_RADIUS_PCL, CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY,
			CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_16,
			CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_1621,
			CLOUD_EFFECTIVE_RADIUS_UNCERTAINTY_37, CLOUD_EMISS11_1KM,
			CLOUD_EMISS12_1KM, CLOUD_EMISS13_1KM, CLOUD_EMISS85_1KM,
			CLOUD_EMISSIVITY_1KM, CLOUD_FRACTION, CLOUD_FRACTION_DAY,
			CLOUD_FRACTION_NADIR, CLOUD_FRACTION_NADIR_DAY,
			CLOUD_FRACTION_NADIR_NIGHT, CLOUD_FRACTION_NIGHT,
			CLOUD_HEIGHT_METHOD, CLOUD_MASK_1KM, CLOUD_MASK_5KM,
			CLOUD_MASK_SPI, CLOUD_MULTI_LAYER_FLAG, CLOUD_OPTICAL_THICKNESS,
			CLOUD_OPTICAL_THICKNESS_16, CLOUD_OPTICAL_THICKNESS_1621,
			CLOUD_OPTICAL_THICKNESS_1621_PCL, CLOUD_OPTICAL_THICKNESS_16_PCL,
			CLOUD_OPTICAL_THICKNESS_37, CLOUD_OPTICAL_THICKNESS_37_PCL,
			CLOUD_OPTICAL_THICKNESS_PCL, CLOUD_OPTICAL_THICKNESS_UNCERTAINTY,
			CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_16,
			CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_1621,
			CLOUD_OPTICAL_THICKNESS_UNCERTAINTY_37, CLOUD_PHASE_INFRARED,
			CLOUD_PHASE_INFRARED_1KM, CLOUD_PHASE_INFRARED_DAY,
			CLOUD_PHASE_INFRARED_NIGHT, CLOUD_PHASE_OPTICAL_PROPERTIES,
			CLOUD_TOP_HEIGHT, CLOUD_TOP_HEIGHT_1KM, CLOUD_TOP_HEIGHT_NADIR,
			CLOUD_TOP_HEIGHT_NADIR_DAY, CLOUD_TOP_HEIGHT_NADIR_NIGHT,
			CLOUD_TOP_METHOD_1KM, CLOUD_TOP_PRESSURE, CLOUD_TOP_PRESSURE_1KM,
			CLOUD_TOP_PRESSURE_DAY, CLOUD_TOP_PRESSURE_FROM_RATIOS,
			CLOUD_TOP_PRESSURE_INFRARED, CLOUD_TOP_PRESSURE_NADIR,
			CLOUD_TOP_PRESSURE_NADIR_DAY, CLOUD_TOP_PRESSURE_NADIR_NIGHT,
			CLOUD_TOP_PRESSURE_NIGHT, CLOUD_TOP_TEMPERATURE,
			CLOUD_TOP_TEMPERATURE_1KM, CLOUD_TOP_TEMPERATURE_DAY,
			CLOUD_TOP_TEMPERATURE_NADIR, CLOUD_TOP_TEMPERATURE_NADIR_DAY,
			CLOUD_TOP_TEMPERATURE_NADIR_NIGHT, CLOUD_TOP_TEMPERATURE_NIGHT,
			CLOUD_WATER_PATH, CLOUD_WATER_PATH_16, CLOUD_WATER_PATH_1621,
			CLOUD_WATER_PATH_1621_PCL, CLOUD_WATER_PATH_16_PCL,
			CLOUD_WATER_PATH_37, CLOUD_WATER_PATH_37_PCL, CLOUD_WATER_PATH_PCL,
			CLOUD_WATER_PATH_UNCERTAINTY, CLOUD_WATER_PATH_UNCERTAINTY_16,
			CLOUD_WATER_PATH_UNCERTAINTY_1621, CLOUD_WATER_PATH_UNCERTAINTY_37,
			EXTINCTION_EFFICIENCY_ICE, EXTINCTION_EFFICIENCY_LIQ,
			IRP_CTH_CONSISTENCY_FLAG_1KM, IRW_LOW_CLOUD_TEMPERATURE_FROM_COP,
			OS_TOP_FLAG_1KM, QUALITY_ASSURANCE_1KM, QUALITY_ASSURANCE_5KM,
			RADIANCE_VARIANCE, RETRIEVAL_FAILURE_METRIC,
			RETRIEVAL_FAILURE_METRIC_16, RETRIEVAL_FAILURE_METRIC_1621,
			RETRIEVAL_FAILURE_METRIC_37, SCAN_START_TIME, SENSOR_AZIMUTH,
			SENSOR_AZIMUTH_DAY, SENSOR_AZIMUTH_NIGHT, SENSOR_ZENITH,
			SENSOR_ZENITH_DAY, SENSOR_ZENITH_NIGHT, SINGLE_SCATTER_ALBEDO_ICE,
			SINGLE_SCATTER_ALBEDO_LIQ, SOLAR_AZIMUTH, SOLAR_AZIMUTH_DAY,
			SOLAR_AZIMUTH_NIGHT, SOLAR_ZENITH, SOLAR_ZENITH_DAY,
			SOLAR_ZENITH_NIGHT, SPECTRAL_CLOUD_FORCING, SURFACE_PRESSURE,
			SURFACE_TEMPERATURE, SURFACE_TEMPERATURE_1KM, TROPOPAUSE_HEIGHT};
}