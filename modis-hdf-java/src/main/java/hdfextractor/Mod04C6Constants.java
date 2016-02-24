package hdfextractor;

import java.io.Serializable;
public class Mod04C6Constants implements Serializable {

	/**
	 * Field:Aerosol Cloud Mask 500 meter resolution 0= cloud 1= clear Tip:DFNT_INT16 Dim:2
	 */
	final static public String AEROSOL_CLDMASK_LAND_OCEAN = "Aerosol_Cldmask_Land_Ocean";
	/**
	 * Property name in TO aerosolCldmaskLandOcean
	 */
	final static public String P_AEROSOL_CLDMASK_LAND_OCEAN = "aerosolCldmaskLandOcean";
	/**
	 * Field:Cloud fraction from Land aerosol cloud mask from retrieved and  overcast pixels not including cirrus mask Tip:DFNT_INT16 Dim:2
	 */
	final static public String AEROSOL_CLOUD_FRACTION_LAND = "Aerosol_Cloud_Fraction_Land";
	/**
	 * Property name in TO aerosolCloudFractionLand
	 */
	final static public String P_AEROSOL_CLOUD_FRACTION_LAND = "aerosolCloudFractionLand";
	/**
	 * Field:Cloud fraction from Land aerosol cloud mask from retrieved and  overcast pixels not including cirrus mask Tip:DFNT_INT16 Dim:2
	 */
	final static public String AEROSOL_CLOUD_FRACTION_OCEAN = "Aerosol_Cloud_Fraction_Ocean";
	/**
	 * Property name in TO aerosolCloudFractionOcean
	 */
	final static public String P_AEROSOL_CLOUD_FRACTION_OCEAN = "aerosolCloudFractionOcean";
	/**
	 * Field:Aerosol Type: 1 = Continental, 2 = Moderate Absorption Fine, 3 = Strong Absorption Fine,4 = Weak Absorption Fine, 5 = Dust Coarse Tip:DFNT_INT16 Dim:2
	 */
	final static public String AEROSOL_TYPE_LAND = "Aerosol_Type_Land";
	/**
	 * Property name in TO aerosolTypeLand
	 */
	final static public String P_AEROSOL_TYPE_LAND = "aerosolTypeLand";
	/**
	 * Field:Calculated Angstrom Exponent for 0.55 vs 0.86 micron  for Average Solution Tip:DFNT_INT16 Dim:2
	 */
	final static public String ANGSTROM_EXPONENT_1_OCEAN = "Angstrom_Exponent_1_Ocean";
	/**
	 * Property name in TO angstromExponent1Ocean
	 */
	final static public String P_ANGSTROM_EXPONENT_1_OCEAN = "angstromExponent1Ocean";
	/**
	 * Field:Calculated Angstrom Exponent for 0.86 vs 2.13 micron for Average Solution Tip:DFNT_INT16 Dim:2
	 */
	final static public String ANGSTROM_EXPONENT_2_OCEAN = "Angstrom_Exponent_2_Ocean";
	/**
	 * Property name in TO angstromExponent2Ocean
	 */
	final static public String P_ANGSTROM_EXPONENT_2_OCEAN = "angstromExponent2Ocean";
	/**
	 * Field:Combined Dark Target, Deep Blue AOT at 0.55 micron for land and ocean. Tip:DFNT_INT16 Dim:2
	 */
	final static public String AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED = "AOD_550_Dark_Target_Deep_Blue_Combined";
	/**
	 * Property name in TO aod550DarkTargetDeepBlueCombined
	 */
	final static public String P_AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED = "aod550DarkTargetDeepBlueCombined";
	/**
	 * Field:Combined Dark Target, Deep Blue AOT at 0.55 micron Algorithm Flag (0=Dark Target, 1=Deep Blue, 2=Mixed) Tip:DFNT_INT16 Dim:2
	 */
	final static public String AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_ALGORITHM_FLAG = "AOD_550_Dark_Target_Deep_Blue_Combined_Algorithm_Flag";
	/**
	 * Property name in TO aod550DarkTargetDeepBlueCombinedAlgorithmFlag
	 */
	final static public String P_AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_ALGORITHM_FLAG = "aod550DarkTargetDeepBlueCombinedAlgorithmFlag";
	/**
	 * Field:Combined Dark Target, Deep Blue Aerosol Confidence Flag (0= No Confidence (or fill),  1= Marginal, 2= Good, 3= Very Good) Tip:DFNT_INT16 Dim:2
	 */
	final static public String AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_QA_FLAG = "AOD_550_Dark_Target_Deep_Blue_Combined_QA_Flag";
	/**
	 * Property name in TO aod550DarkTargetDeepBlueCombinedQaFlag
	 */
	final static public String P_AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_QA_FLAG = "aod550DarkTargetDeepBlueCombinedQaFlag";
	/**
	 * Field:Inferred Asymmetry_Factor for 'average' solution at 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
	 */
	final static public String ASYMMETRY_FACTOR_AVERAGE_OCEAN = "Asymmetry_Factor_Average_Ocean";
	/**
	 * Property name in TO asymmetryFactorAverageOcean
	 */
	final static public String P_ASYMMETRY_FACTOR_AVERAGE_OCEAN = "asymmetryFactorAverageOcean";
	/**
	 * Field:Inferred Asymmetry_Factor for 'best' solution at 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
	 */
	final static public String ASYMMETRY_FACTOR_BEST_OCEAN = "Asymmetry_Factor_Best_Ocean";
	/**
	 * Property name in TO asymmetryFactorBestOcean
	 */
	final static public String P_ASYMMETRY_FACTOR_BEST_OCEAN = "asymmetryFactorBestOcean";
	/**
	 * Field:Average Distance (number of pixels) to nearest pixel identified as cloudy from each clear pixel used for Aerosol Retrieval in 10 km retrieval box Tip:DFNT_INT16 Dim:2
	 */
	final static public String AVERAGE_CLOUD_PIXEL_DISTANCE_LAND_OCEAN = "Average_Cloud_Pixel_Distance_Land_Ocean";
	/**
	 * Property name in TO averageCloudPixelDistanceLandOcean
	 */
	final static public String P_AVERAGE_CLOUD_PIXEL_DISTANCE_LAND_OCEAN = "averageCloudPixelDistanceLandOcean";
	/**
	 * Field:Inferred Backscattering_Ratio for 'average' solution at 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
	 */
	final static public String BACKSCATTERING_RATIO_AVERAGE_OCEAN = "Backscattering_Ratio_Average_Ocean";
	/**
	 * Property name in TO backscatteringRatioAverageOcean
	 */
	final static public String P_BACKSCATTERING_RATIO_AVERAGE_OCEAN = "backscatteringRatioAverageOcean";
	/**
	 * Field:Inferred Backscattering_Ratio for 'best' solution at 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
	 */
	final static public String BACKSCATTERING_RATIO_BEST_OCEAN = "Backscattering_Ratio_Best_Ocean";
	/**
	 * Property name in TO backscatteringRatioBestOcean
	 */
	final static public String P_BACKSCATTERING_RATIO_BEST_OCEAN = "backscatteringRatioBestOcean";
	/**
	 * Field:Distance (number of pixels) to nearest pixel identified as cloudy (500 m resolution) Tip:DFNT_INT16 Dim:2
	 */
	final static public String CLOUD_PIXEL_DISTANCE_LAND_OCEAN = "Cloud_Pixel_Distance_Land_Ocean";
	/**
	 * Property name in TO cloudPixelDistanceLandOcean
	 */
	final static public String P_CLOUD_PIXEL_DISTANCE_LAND_OCEAN = "cloudPixelDistanceLandOcean";
	/**
	 * Field:Retrieved  AOT at 0.47, 0.55,0.66   micron Tip:DFNT_INT16 Dim:3
	 */
	final static public String CORRECTED_OPTICAL_DEPTH_LAND = "Corrected_Optical_Depth_Land";
	/**
	 * Property name in TO correctedOpticalDepthLand
	 */
	final static public String P_CORRECTED_OPTICAL_DEPTH_LAND = "correctedOpticalDepthLand";
	/**
	 * Field:Retrieved  AOT at 2.13   micron Tip:DFNT_INT16 Dim:2
	 */
	final static public String CORRECTED_OPTICAL_DEPTH_LAND_WAV2P1 = "Corrected_Optical_Depth_Land_wav2p1";
	/**
	 * Property name in TO correctedOpticalDepthLandWav2p1
	 */
	final static public String P_CORRECTED_OPTICAL_DEPTH_LAND_WAV2P1 = "correctedOpticalDepthLandWav2p1";
	/**
	 * Field:AOT at 0.55 micron for land  with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:2
	 */
	final static public String DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND = "Deep_Blue_Aerosol_Optical_Depth_550_Land";
	/**
	 * Property name in TO deepBlueAerosolOpticalDepth550Land
	 */
	final static public String P_DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND = "deepBlueAerosolOpticalDepth550Land";
	/**
	 * Field:Deep Blue AOT at 0.55 micron for land with higher quality data (Quality flag=2,3) Tip:DFNT_INT16 Dim:2
	 */
	final static public String DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_BEST_ESTIMATE = "Deep_Blue_Aerosol_Optical_Depth_550_Land_Best_Estimate";
	/**
	 * Property name in TO deepBlueAerosolOpticalDepth550LandBestEstimate
	 */
	final static public String P_DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_BEST_ESTIMATE = "deepBlueAerosolOpticalDepth550LandBestEstimate";
	/**
	 * Field:Estimated uncertainty (one-sigma confidence envelope) of Deep Blue AOT at 0.55 micron for land for all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:2
	 */
	final static public String DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_ESTIMATED_UNCERTAINTY = "Deep_Blue_Aerosol_Optical_Depth_550_Land_Estimated_Uncertainty";
	/**
	 * Property name in TO deepBlueAerosolOpticalDepth550LandEstimatedUncertainty
	 */
	final static public String P_DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_ESTIMATED_UNCERTAINTY = "deepBlueAerosolOpticalDepth550LandEstimatedUncertainty";
	/**
	 * Field:Deep Blue Aerosol Confidence Flag (0= No Confidence (or fill),  1= Marginal, 2= Good, 3= Very Good) Tip:DFNT_INT16 Dim:2
	 */
	final static public String DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_QA_FLAG = "Deep_Blue_Aerosol_Optical_Depth_550_Land_QA_Flag";
	/**
	 * Property name in TO deepBlueAerosolOpticalDepth550LandQaFlag
	 */
	final static public String P_DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_QA_FLAG = "deepBlueAerosolOpticalDepth550LandQaFlag";
	/**
	 * Field:Standard deviation of Deep Blue AOT at 0.55 micron for land with  all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:2
	 */
	final static public String DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_STD = "Deep_Blue_Aerosol_Optical_Depth_550_Land_STD";
	/**
	 * Property name in TO deepBlueAerosolOpticalDepth550LandStd
	 */
	final static public String P_DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_STD = "deepBlueAerosolOpticalDepth550LandStd";
	/**
	 * Field:Deep Blue Aerosol Algorithm Flag (0=DeepBlue, 1=Vegetated, 2=Mixed) Tip:DFNT_INT16 Dim:2
	 */
	final static public String DEEP_BLUE_ALGORITHM_FLAG_LAND = "Deep_Blue_Algorithm_Flag_Land";
	/**
	 * Property name in TO deepBlueAlgorithmFlagLand
	 */
	final static public String P_DEEP_BLUE_ALGORITHM_FLAG_LAND = "deepBlueAlgorithmFlagLand";
	/**
	 * Field:Deep Blue Angstrom Exponent for land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:2
	 */
	final static public String DEEP_BLUE_ANGSTROM_EXPONENT_LAND = "Deep_Blue_Angstrom_Exponent_Land";
	/**
	 * Property name in TO deepBlueAngstromExponentLand
	 */
	final static public String P_DEEP_BLUE_ANGSTROM_EXPONENT_LAND = "deepBlueAngstromExponentLand";
	/**
	 * Field:Cloud fraction from Deep Blue Aerosol cloud mask over land Tip:DFNT_INT16 Dim:2
	 */
	final static public String DEEP_BLUE_CLOUD_FRACTION_LAND = "Deep_Blue_Cloud_Fraction_Land";
	/**
	 * Property name in TO deepBlueCloudFractionLand
	 */
	final static public String P_DEEP_BLUE_CLOUD_FRACTION_LAND = "deepBlueCloudFractionLand";
	/**
	 * Field:Number of pixels used for AOT retrieval 0.55 micron for land Tip:DFNT_INT16 Dim:2
	 */
	final static public String DEEP_BLUE_NUMBER_PIXELS_USED_550_LAND = "Deep_Blue_Number_Pixels_Used_550_Land";
	/**
	 * Property name in TO deepBlueNumberPixelsUsed550Land
	 */
	final static public String P_DEEP_BLUE_NUMBER_PIXELS_USED_550_LAND = "deepBlueNumberPixelsUsed550Land";
	/**
	 * Field:AOT at 0.412, 0.47, and 0.66 micron for land  with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
	 */
	final static public String DEEP_BLUE_SPECTRAL_AEROSOL_OPTICAL_DEPTH_LAND = "Deep_Blue_Spectral_Aerosol_Optical_Depth_Land";
	/**
	 * Property name in TO deepBlueSpectralAerosolOpticalDepthLand
	 */
	final static public String P_DEEP_BLUE_SPECTRAL_AEROSOL_OPTICAL_DEPTH_LAND = "deepBlueSpectralAerosolOpticalDepthLand";
	/**
	 * Field:Deep Blue Single Scattering Albedo at 0.412, 0.47, and 0.66 micron  for land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
	 */
	final static public String DEEP_BLUE_SPECTRAL_SINGLE_SCATTERING_ALBEDO_LAND = "Deep_Blue_Spectral_Single_Scattering_Albedo_Land";
	/**
	 * Property name in TO deepBlueSpectralSingleScatteringAlbedoLand
	 */
	final static public String P_DEEP_BLUE_SPECTRAL_SINGLE_SCATTERING_ALBEDO_LAND = "deepBlueSpectralSingleScatteringAlbedoLand";
	/**
	 * Field:Deep Blue Surface Reflectance at 0.412, 0.47, and 0.66 micron  for land with all quality data (Quality flag=1,2,3) Tip:DFNT_INT16 Dim:3
	 */
	final static public String DEEP_BLUE_SPECTRAL_SURFACE_REFLECTANCE_LAND = "Deep_Blue_Spectral_Surface_Reflectance_Land";
	/**
	 * Property name in TO deepBlueSpectralSurfaceReflectanceLand
	 */
	final static public String P_DEEP_BLUE_SPECTRAL_SURFACE_REFLECTANCE_LAND = "deepBlueSpectralSurfaceReflectanceLand";
	/**
	 * Field:Average measured TOA reflectance after cloud screening at  0.412,  0.47, and 0.66 micron for land Tip:DFNT_INT16 Dim:3
	 */
	final static public String DEEP_BLUE_SPECTRAL_TOA_REFLECTANCE_LAND = "Deep_Blue_Spectral_TOA_Reflectance_Land";
	/**
	 * Property name in TO deepBlueSpectralToaReflectanceLand
	 */
	final static public String P_DEEP_BLUE_SPECTRAL_TOA_REFLECTANCE_LAND = "deepBlueSpectralToaReflectanceLand";
	/**
	 * Field:Retrieved AOT for 'average' solution at 0.55um For easy L3 processing Tip:DFNT_INT16 Dim:2
	 */
	final static public String EFFECTIVE_OPTICAL_DEPTH_0P55UM_OCEAN = "Effective_Optical_Depth_0p55um_Ocean";
	/**
	 * Property name in TO effectiveOpticalDepth0p55umOcean
	 */
	final static public String P_EFFECTIVE_OPTICAL_DEPTH_0P55UM_OCEAN = "effectiveOpticalDepth0p55umOcean";
	/**
	 * Field:Retrieved AOT for 'average' solution at 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
	 */
	final static public String EFFECTIVE_OPTICAL_DEPTH_AVERAGE_OCEAN = "Effective_Optical_Depth_Average_Ocean";
	/**
	 * Property name in TO effectiveOpticalDepthAverageOcean
	 */
	final static public String P_EFFECTIVE_OPTICAL_DEPTH_AVERAGE_OCEAN = "effectiveOpticalDepthAverageOcean";
	/**
	 * Field:Retrieved AOT for 'best' solution at 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
	 */
	final static public String EFFECTIVE_OPTICAL_DEPTH_BEST_OCEAN = "Effective_Optical_Depth_Best_Ocean";
	/**
	 * Property name in TO effectiveOpticalDepthBestOcean
	 */
	final static public String P_EFFECTIVE_OPTICAL_DEPTH_BEST_OCEAN = "effectiveOpticalDepthBestOcean";
	/**
	 * Field:Effective_Radius at 0.55 micron for 'best' (1) and 'average' (2) solutions Tip:DFNT_INT16 Dim:3
	 */
	final static public String EFFECTIVE_RADIUS_OCEAN = "Effective_Radius_Ocean";
	/**
	 * Property name in TO effectiveRadiusOcean
	 */
	final static public String P_EFFECTIVE_RADIUS_OCEAN = "effectiveRadiusOcean";
	/**
	 * Field:Spectral Fitting error for inversion over land Tip:DFNT_INT16 Dim:2
	 */
	final static public String FITTING_ERROR_LAND = "Fitting_Error_Land";
	/**
	 * Property name in TO fittingErrorLand
	 */
	final static public String P_FITTING_ERROR_LAND = "fittingErrorLand";
	/**
	 * Field:Glint Angle Tip:DFNT_INT16 Dim:2
	 */
	final static public String GLINT_ANGLE = "Glint_Angle";
	/**
	 * Property name in TO glintAngle
	 */
	final static public String P_GLINT_ANGLE = "glintAngle";
	/**
	 * Field:AOT at 0.55 micron for both ocean (Average) and land (corrected)  with all quality data (Quality flag=0,1,2,3) Tip:DFNT_INT16 Dim:2
	 */
	final static public String IMAGE_OPTICAL_DEPTH_LAND_AND_OCEAN = "Image_Optical_Depth_Land_And_Ocean";
	/**
	 * Property name in TO imageOpticalDepthLandAndOcean
	 */
	final static public String P_IMAGE_OPTICAL_DEPTH_LAND_AND_OCEAN = "imageOpticalDepthLandAndOcean";
	/**
	 * Field:Quality Flag for Land and ocean Aerosol retreivals 0= bad  1  = Marginal 2= Good 3=Very Good) Tip:DFNT_INT16 Dim:2
	 */
	final static public String LAND_OCEAN_QUALITY_FLAG = "Land_Ocean_Quality_Flag";
	/**
	 * Property name in TO landOceanQualityFlag
	 */
	final static public String P_LAND_OCEAN_QUALITY_FLAG = "landOceanQualityFlag";
	/**
	 * Field:Land_sea_Flag(based on MOD03 Landsea mask 0 = Ocean, 1 = Land and Ephemeral water 2 =Coastal) Tip:DFNT_INT16 Dim:2
	 */
	final static public String LAND_SEA_FLAG = "Land_sea_Flag";
	/**
	 * Property name in TO landSeaFlag
	 */
	final static public String P_LAND_SEA_FLAG = "landSeaFlag";
	/**
	 * Field:Residual of least squares fitting for inversion over land for best (1) and average (2) solutions Tip:DFNT_INT16 Dim:3
	 */
	final static public String LEAST_SQUARES_ERROR_OCEAN = "Least_Squares_Error_Ocean";
	/**
	 * Property name in TO leastSquaresErrorOcean
	 */
	final static public String P_LEAST_SQUARES_ERROR_OCEAN = "leastSquaresErrorOcean";
	/**
	 * Field:Estimated Column Mass(per area) using assumed mass extinction efficiency Tip:DFNT_FLOAT32 Dim:2
	 */
	final static public String MASS_CONCENTRATION_LAND = "Mass_Concentration_Land";
	/**
	 * Property name in TO massConcentrationLand
	 */
	final static public String P_MASS_CONCENTRATION_LAND = "massConcentrationLand";
	/**
	 * Field:Estimated Column Mass (per area) using assumed mass extinction coefficients for 'best' (1) and 'average' (2) solutions Tip:DFNT_FLOAT32 Dim:3
	 */
	final static public String MASS_CONCENTRATION_OCEAN = "Mass_Concentration_Ocean";
	/**
	 * Property name in TO massConcentrationOcean
	 */
	final static public String P_MASS_CONCENTRATION_OCEAN = "massConcentrationOcean";
	/**
	 * Field:Mean reflectance of pixels used for land retrieval at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP: 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
	 */
	final static public String MEAN_REFLECTANCE_LAND = "Mean_Reflectance_Land";
	/**
	 * Property name in TO meanReflectanceLand
	 */
	final static public String P_MEAN_REFLECTANCE_LAND = "meanReflectanceLand";
	/**
	 * Field:Mean reflectance of pixels used for ocean retrieval at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP: 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
	 */
	final static public String MEAN_REFLECTANCE_OCEAN = "Mean_Reflectance_Ocean";
	/**
	 * Property name in TO meanReflectanceOcean
	 */
	final static public String P_MEAN_REFLECTANCE_OCEAN = "meanReflectanceOcean";
	/**
	 * Field:Number of pixels used for land retrieval at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns (plus extra bands for NPP: 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
	 */
	final static public String NUMBER_PIXELS_USED_LAND = "Number_Pixels_Used_Land";
	/**
	 * Property name in TO numberPixelsUsedLand
	 */
	final static public String P_NUMBER_PIXELS_USED_LAND = "numberPixelsUsedLand";
	/**
	 * Field:Number of pixels used for ocean retrieval at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 Microns(plus extra bands for NPP: 0.412,0443,0.745 microns) Tip:DFNT_INT16 Dim:3
	 */
	final static public String NUMBER_PIXELS_USED_OCEAN = "Number_Pixels_Used_Ocean";
	/**
	 * Property name in TO numberPixelsUsedOcean
	 */
	final static public String P_NUMBER_PIXELS_USED_OCEAN = "numberPixelsUsedOcean";
	/**
	 * Field:Retrieved AOT (at 0.55 micron) partioned by mode index (for Average solution) Tip:DFNT_INT16 Dim:3
	 */
	final static public String OPTICAL_DEPTH_BY_MODELS_OCEAN = "Optical_Depth_by_models_ocean";
	/**
	 * Property name in TO opticalDepthByModelsOcean
	 */
	final static public String P_OPTICAL_DEPTH_BY_MODELS_OCEAN = "opticalDepthByModelsOcean";
	/**
	 * Field:AOT at 0.55 micron for both ocean (Average) (Quality flag=1,2,3) and land (corrected) (Quality flag=3) Tip:DFNT_INT16 Dim:2
	 */
	final static public String OPTICAL_DEPTH_LAND_AND_OCEAN = "Optical_Depth_Land_And_Ocean";
	/**
	 * Property name in TO opticalDepthLandAndOcean
	 */
	final static public String P_OPTICAL_DEPTH_LAND_AND_OCEAN = "opticalDepthLandAndOcean";
	/**
	 * Field:Retrieved AOT of large mode for 'average' solution at 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
	 */
	final static public String OPTICAL_DEPTH_LARGE_AVERAGE_OCEAN = "Optical_Depth_Large_Average_Ocean";
	/**
	 * Property name in TO opticalDepthLargeAverageOcean
	 */
	final static public String P_OPTICAL_DEPTH_LARGE_AVERAGE_OCEAN = "opticalDepthLargeAverageOcean";
	/**
	 * Field:Retrieved AOT of large mode for 'best' solution at 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
	 */
	final static public String OPTICAL_DEPTH_LARGE_BEST_OCEAN = "Optical_Depth_Large_Best_Ocean";
	/**
	 * Property name in TO opticalDepthLargeBestOcean
	 */
	final static public String P_OPTICAL_DEPTH_LARGE_BEST_OCEAN = "opticalDepthLargeBestOcean";
	/**
	 * Field:Fraction of AOT contributed by fine dominated model Tip:DFNT_INT16 Dim:2
	 */
	final static public String OPTICAL_DEPTH_RATIO_SMALL_LAND = "Optical_Depth_Ratio_Small_Land";
	/**
	 * Property name in TO opticalDepthRatioSmallLand
	 */
	final static public String P_OPTICAL_DEPTH_RATIO_SMALL_LAND = "opticalDepthRatioSmallLand";
	/**
	 * Field:Fraction of AOT (at 0.55 micron) contributed by fine mode for average solution Tip:DFNT_INT16 Dim:2
	 */
	final static public String OPTICAL_DEPTH_RATIO_SMALL_OCEAN_0p55MICRON = "Optical_Depth_Ratio_Small_Ocean_0.55micron";
	/**
	 * Property name in TO opticalDepthRatioSmallOcean0p55micron
	 */
	final static public String P_OPTICAL_DEPTH_RATIO_SMALL_OCEAN_0p55MICRON = "opticalDepthRatioSmallOcean0p55micron";
	/**
	 * Field:Retreived optical thickness for fine mode (Average solution) for 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
	 */
	final static public String OPTICAL_DEPTH_SMALL_AVERAGE_OCEAN = "Optical_Depth_Small_Average_Ocean";
	/**
	 * Property name in TO opticalDepthSmallAverageOcean
	 */
	final static public String P_OPTICAL_DEPTH_SMALL_AVERAGE_OCEAN = "opticalDepthSmallAverageOcean";
	/**
	 * Field:Retreived optical thickness for fine mode (best solution) for 0.47, 0.55,0.66,0.86,1.24,1.63,2.13 um Tip:DFNT_INT16 Dim:3
	 */
	final static public String OPTICAL_DEPTH_SMALL_BEST_OCEAN = "Optical_Depth_Small_Best_Ocean";
	/**
	 * Property name in TO opticalDepthSmallBestOcean
	 */
	final static public String P_OPTICAL_DEPTH_SMALL_BEST_OCEAN = "opticalDepthSmallBestOcean";
	/**
	 * Field:Inferred column number concentration (number per area) of particles larger than 0.03 micron for'best' (1) and 'average' (2) solutions Tip:DFNT_FLOAT32 Dim:3
	 */
	final static public String PSML003_OCEAN = "PSML003_Ocean";
	/**
	 * Property name in TO psml003Ocean
	 */
	final static public String P_PSML003_OCEAN = "psml003Ocean";
	/**
	 * Field:Runtime QA flags Tip:DFNT_INT8 Dim:3
	 */
	final static public String QUALITY_ASSURANCE_LAND = "Quality_Assurance_Land";
	/**
	 * Property name in TO qualityAssuranceLand
	 */
	final static public String P_QUALITY_ASSURANCE_LAND = "qualityAssuranceLand";
	/**
	 * Field:Run time QA flags Tip:DFNT_INT8 Dim:3
	 */
	final static public String QUALITY_ASSURANCE_OCEAN = "Quality_Assurance_Ocean";
	/**
	 * Property name in TO qualityAssuranceOcean
	 */
	final static public String P_QUALITY_ASSURANCE_OCEAN = "qualityAssuranceOcean";
	/**
	 * Field:TAI Time at Start of Scan replicated across the swath Tip:DFNT_FLOAT32 Dim:2
	 */
	final static public String SCAN_START_TIME = "Scan_Start_Time";
	/**
	 * Property name in TO scanStartTime
	 */
	final static public String P_SCAN_START_TIME = "scanStartTime";
	/**
	 * Field:Scattering Angle Tip:DFNT_INT16 Dim:2
	 */
	final static public String SCATTERING_ANGLE = "Scattering_Angle";
	/**
	 * Property name in TO scatteringAngle
	 */
	final static public String P_SCATTERING_ANGLE = "scatteringAngle";
	/**
	 * Field:Sensor_Azimuth Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
	 */
	final static public String SENSOR_AZIMUTH = "Sensor_Azimuth";
	/**
	 * Property name in TO sensorAzimuth
	 */
	final static public String P_SENSOR_AZIMUTH = "sensorAzimuth";
	/**
	 * Field:Sensor_Zenith Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
	 */
	final static public String SENSOR_ZENITH = "Sensor_Zenith";
	/**
	 * Property name in TO sensorZenith
	 */
	final static public String P_SENSOR_ZENITH = "sensorZenith";
	/**
	 * Field:Solar_Azimuth Angle, Cell to Sun Tip:DFNT_INT16 Dim:2
	 */
	final static public String SOLAR_AZIMUTH = "Solar_Azimuth";
	/**
	 * Property name in TO solarAzimuth
	 */
	final static public String P_SOLAR_AZIMUTH = "solarAzimuth";
	/**
	 * Field:Solar Zenith Angle, Cell to Sun Tip:DFNT_INT16 Dim:2
	 */
	final static public String SOLAR_ZENITH = "Solar_Zenith";
	/**
	 * Property name in TO solarZenith
	 */
	final static public String P_SOLAR_ZENITH = "solarZenith";
	/**
	 * Field:index identifying coarse mode from Look Up Table for 'best' solution Tip:DFNT_INT16 Dim:3
	 */
	final static public String SOLUTION_INDEX_OCEAN_LARGE = "Solution_Index_Ocean_Large";
	/**
	 * Property name in TO solutionIndexOceanLarge
	 */
	final static public String P_SOLUTION_INDEX_OCEAN_LARGE = "solutionIndexOceanLarge";
	/**
	 * Field:index identifying fine mode from Look Up Table for 'best' solution Tip:DFNT_INT16 Dim:3
	 */
	final static public String SOLUTION_INDEX_OCEAN_SMALL = "Solution_Index_Ocean_Small";
	/**
	 * Property name in TO solutionIndexOceanSmall
	 */
	final static public String P_SOLUTION_INDEX_OCEAN_SMALL = "solutionIndexOceanSmall";
	/**
	 * Field:Standard deviation of reflectance of pixels used for land retrieval at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns (plus extra bands for NPP: 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
	 */
	final static public String STD_REFLECTANCE_LAND = "STD_Reflectance_Land";
	/**
	 * Property name in TO stdReflectanceLand
	 */
	final static public String P_STD_REFLECTANCE_LAND = "stdReflectanceLand";
	/**
	 * Field:Standard deviation of reflectance of pixels used for ocean retrieval at 0.47,0.55,0.65,0.86,1.24,1.63,2.11 microns(plus extra bands for NPP: 0.412,0.443,0.745 Micron) Tip:DFNT_INT16 Dim:3
	 */
	final static public String STD_REFLECTANCE_OCEAN = "STD_Reflectance_Ocean";
	/**
	 * Property name in TO stdReflectanceOcean
	 */
	final static public String P_STD_REFLECTANCE_OCEAN = "stdReflectanceOcean";
	/**
	 * Field:Estimated Surface Reflectance at 0.47,0.66 and 2.13micron Tip:DFNT_INT16 Dim:3
	 */
	final static public String SURFACE_REFLECTANCE_LAND = "Surface_Reflectance_Land";
	/**
	 * Property name in TO surfaceReflectanceLand
	 */
	final static public String P_SURFACE_REFLECTANCE_LAND = "surfaceReflectanceLand";
	/**
	 * Field:Averaged topographic altitude (in km) for Land Tip:DFNT_INT16 Dim:2
	 */
	final static public String TOPOGRAPHIC_ALTITUDE_LAND = "Topographic_Altitude_Land";
	/**
	 * Property name in TO topographicAltitudeLand
	 */
	final static public String P_TOPOGRAPHIC_ALTITUDE_LAND = "topographicAltitudeLand";
	/**
	 * Field:Wind Speed based on NCEP reanalysis for Ocean Tip:DFNT_INT16 Dim:2
	 */
	final static public String WIND_SPEED_NCEP_OCEAN = "Wind_Speed_Ncep_Ocean";
	/**
	 * Property name in TO windSpeedNcepOcean
	 */
	final static public String P_WIND_SPEED_NCEP_OCEAN = "windSpeedNcepOcean";
	/**
	 * Lista campuri in acest tip
	 */
	final static public String[] fieldNames = new String[]{
			AEROSOL_CLDMASK_LAND_OCEAN, AEROSOL_CLOUD_FRACTION_LAND,
			AEROSOL_CLOUD_FRACTION_OCEAN, AEROSOL_TYPE_LAND,
			ANGSTROM_EXPONENT_1_OCEAN, ANGSTROM_EXPONENT_2_OCEAN,
			AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED,
			AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_ALGORITHM_FLAG,
			AOD_550_DARK_TARGET_DEEP_BLUE_COMBINED_QA_FLAG,
			ASYMMETRY_FACTOR_AVERAGE_OCEAN, ASYMMETRY_FACTOR_BEST_OCEAN,
			AVERAGE_CLOUD_PIXEL_DISTANCE_LAND_OCEAN,
			BACKSCATTERING_RATIO_AVERAGE_OCEAN,
			BACKSCATTERING_RATIO_BEST_OCEAN, CLOUD_PIXEL_DISTANCE_LAND_OCEAN,
			CORRECTED_OPTICAL_DEPTH_LAND, CORRECTED_OPTICAL_DEPTH_LAND_WAV2P1,
			DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND,
			DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_BEST_ESTIMATE,
			DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_ESTIMATED_UNCERTAINTY,
			DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_QA_FLAG,
			DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_STD,
			DEEP_BLUE_ALGORITHM_FLAG_LAND, DEEP_BLUE_ANGSTROM_EXPONENT_LAND,
			DEEP_BLUE_CLOUD_FRACTION_LAND,
			DEEP_BLUE_NUMBER_PIXELS_USED_550_LAND,
			DEEP_BLUE_SPECTRAL_AEROSOL_OPTICAL_DEPTH_LAND,
			DEEP_BLUE_SPECTRAL_SINGLE_SCATTERING_ALBEDO_LAND,
			DEEP_BLUE_SPECTRAL_SURFACE_REFLECTANCE_LAND,
			DEEP_BLUE_SPECTRAL_TOA_REFLECTANCE_LAND,
			EFFECTIVE_OPTICAL_DEPTH_0P55UM_OCEAN,
			EFFECTIVE_OPTICAL_DEPTH_AVERAGE_OCEAN,
			EFFECTIVE_OPTICAL_DEPTH_BEST_OCEAN, EFFECTIVE_RADIUS_OCEAN,
			FITTING_ERROR_LAND, GLINT_ANGLE,
			IMAGE_OPTICAL_DEPTH_LAND_AND_OCEAN, LAND_OCEAN_QUALITY_FLAG,
			LAND_SEA_FLAG, LEAST_SQUARES_ERROR_OCEAN, MASS_CONCENTRATION_LAND,
			MASS_CONCENTRATION_OCEAN, MEAN_REFLECTANCE_LAND,
			MEAN_REFLECTANCE_OCEAN, NUMBER_PIXELS_USED_LAND,
			NUMBER_PIXELS_USED_OCEAN, OPTICAL_DEPTH_BY_MODELS_OCEAN,
			OPTICAL_DEPTH_LAND_AND_OCEAN, OPTICAL_DEPTH_LARGE_AVERAGE_OCEAN,
			OPTICAL_DEPTH_LARGE_BEST_OCEAN, OPTICAL_DEPTH_RATIO_SMALL_LAND,
			OPTICAL_DEPTH_RATIO_SMALL_OCEAN_0p55MICRON,
			OPTICAL_DEPTH_SMALL_AVERAGE_OCEAN, OPTICAL_DEPTH_SMALL_BEST_OCEAN,
			PSML003_OCEAN, QUALITY_ASSURANCE_LAND, QUALITY_ASSURANCE_OCEAN,
			SCAN_START_TIME, SCATTERING_ANGLE, SENSOR_AZIMUTH, SENSOR_ZENITH,
			SOLAR_AZIMUTH, SOLAR_ZENITH, SOLUTION_INDEX_OCEAN_LARGE,
			SOLUTION_INDEX_OCEAN_SMALL, STD_REFLECTANCE_LAND,
			STD_REFLECTANCE_OCEAN, SURFACE_REFLECTANCE_LAND,
			TOPOGRAPHIC_ALTITUDE_LAND, WIND_SPEED_NCEP_OCEAN};
}