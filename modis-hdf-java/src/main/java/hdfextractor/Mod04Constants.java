package hdfextractor;

public interface Mod04Constants {
	static final int DimCellAlongSwath = 203;
	static final int DimCellAcrossSwath = 135;
	static final int DimSolution3Land = 3;
	static final int DimSolution1Land = 2;
	static final int DimSolution2Land = 3;
	static final int DimSolution4Land = 4;
	static final int DimMODISBandLand = 7;
	static final int DimQAByteLand = 5;
	static final int DimNumByProducts = 7;
	static final int DimSolutionOcean = 2;
	static final int DimMODISBandOcean = 7;
	static final int DimSolutionIndex = 9;
	static final int DimQAByteOcean = 5;
	static final int DimNumDeepBlueWavelengths = 3;
	/**
	 * Field:MODIS_Band_Land Dimensiune: (MODIS_Band_Land) Tip: DFNT_INT32
	 */
	public static final String MODIS_BAND_LAND = "MODIS_Band_Land";
	/**
	 * Field:MODIS_Band_Ocean Dimensiune: (MODIS_Band_Ocean) Tip: DFNT_INT32
	 */
	public static final String MODIS_BAND_OCEAN = "MODIS_Band_Ocean";
	/**
	 * Field:Solution_1_Land Dimensiune: (Solution_1_Land) Tip: DFNT_INT32
	 */
	public static final String SOLUTION_1_LAND = "Solution_1_Land";
	/**
	 * Field:Solution_2_Land Dimensiune: (Solution_2_Land) Tip: DFNT_INT32
	 */
	public static final String SOLUTION_2_LAND = "Solution_2_Land";
	/**
	 * Field:Solution_3_Land Dimensiune: (Solution_3_Land) Tip: DFNT_INT32
	 */
	public static final String SOLUTION_3_LAND = "Solution_3_Land";
	/**
	 * Field:Solution_Ocean Dimensiune: (Solution_Ocean) Tip: DFNT_INT32
	 */
	public static final String SOLUTION_OCEAN = "Solution_Ocean";
	/**
	 * Field:Solution_Index Dimensiune: (Solution_Index) Tip: DFNT_INT32
	 */
	public static final String SOLUTION_INDEX = "Solution_Index";
	/**
	 * Field:Num_DeepBlue_Wavelengths Dimensiune: (Num_DeepBlue_Wavelengths)
	 * Tip: DFNT_INT32
	 */
	public static final String NUM_DEEPBLUE_WAVELENGTHS = "Num_DeepBlue_Wavelengths";
	/**
	 * Field:Scan_Start_Time Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
	 * Tip: DFNT_FLOAT64
	 */
	public static final String SCAN_START_TIME = "Scan_Start_Time";
	/**
	 * Field:Solar_Zenith Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
	 * DFNT_INT16
	 */
	public static final String SOLAR_ZENITH = "Solar_Zenith";
	/**
	 * Field:Solar_Azimuth Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
	 * DFNT_INT16
	 */
	public static final String SOLAR_AZIMUTH = "Solar_Azimuth";
	/**
	 * Field:Sensor_Zenith Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
	 * DFNT_INT16
	 */
	public static final String SENSOR_ZENITH = "Sensor_Zenith";
	/**
	 * Field:Sensor_Azimuth Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
	 * Tip: DFNT_INT16
	 */
	public static final String SENSOR_AZIMUTH = "Sensor_Azimuth";
	/**
	 * Field:Cloud_Mask_QA Dimensiune: (Cell_Along_Swath,Cell_Across_Swath) Tip:
	 * DFNT_INT8
	 */
	public static final String CLOUD_MASK_QA = "Cloud_Mask_QA";
	/**
	 * Field:Scattering_Angle Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
	 * Tip: DFNT_INT16
	 */
	public static final String SCATTERING_ANGLE = "Scattering_Angle";
	/**
	 * Field:Optical_Depth_Land_And_Ocean Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String OPTICAL_DEPTH_LAND_AND_OCEAN = "Optical_Depth_Land_And_Ocean";
	/**
	 * Field:Image_Optical_Depth_Land_And_Ocean Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String IMAGE_OPTICAL_DEPTH_LAND_AND_OCEAN = "Image_Optical_Depth_Land_And_Ocean";
	/**
	 * Field:Optical_Depth_Ratio_Small_Land_And_Ocean Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String OPTICAL_DEPTH_RATIO_SMALL_LAND_AND_OCEAN = "Optical_Depth_Ratio_Small_Land_And_Ocean";
	/**
	 * Field:Mean_Reflectance_Land_All Dimensiune:
	 * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String MEAN_REFLECTANCE_LAND_ALL = "Mean_Reflectance_Land_All";
	/**
	 * Field:Standard_Deviation_Reflectance_Land_All Dimensiune:
	 * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String STANDARD_DEVIATION_REFLECTANCE_LAND_ALL = "Standard_Deviation_Reflectance_Land_All";
	/**
	 * Field:Path_Radiance_Land Dimensiune:
	 * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String PATH_RADIANCE_LAND = "Path_Radiance_Land";
	/**
	 * Field:Error_Path_Radiance_Land Dimensiune:
	 * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String ERROR_PATH_RADIANCE_LAND = "Error_Path_Radiance_Land";
	/**
	 * Field:Critical_Reflectance_Land Dimensiune:
	 * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String CRITICAL_REFLECTANCE_LAND = "Critical_Reflectance_Land";
	/**
	 * Field:Error_Critical_Reflectance_Land Dimensiune:
	 * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String ERROR_CRITICAL_REFLECTANCE_LAND = "Error_Critical_Reflectance_Land";
	/**
	 * Field:QualityWeight_Path_Radiance_Land Dimensiune:
	 * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String QUALITYWEIGHT_PATH_RADIANCE_LAND = "QualityWeight_Path_Radiance_Land";
	/**
	 * Field:QualityWeight_Critical_Reflectance_Land Dimensiune:
	 * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String QUALITYWEIGHT_CRITICAL_REFLECTANCE_LAND = "QualityWeight_Critical_Reflectance_Land";
	/**
	 * Field:Aerosol_Type_Land Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
	 * Tip: DFNT_INT16
	 */
	public static final String AEROSOL_TYPE_LAND = "Aerosol_Type_Land";
	/**
	 * Field:Fitting_Error_Land Dimensiune: (Cell_Along_Swath,Cell_Across_Swath)
	 * Tip: DFNT_INT16
	 */
	public static final String FITTING_ERROR_LAND = "Fitting_Error_Land";
	/**
	 * Field:Surface_Reflectance_Land Dimensiune:
	 * (Solution_2_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String SURFACE_REFLECTANCE_LAND = "Surface_Reflectance_Land";
	/**
	 * Field:Corrected_Optical_Depth_Land Dimensiune:
	 * (Solution_3_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String CORRECTED_OPTICAL_DEPTH_LAND = "Corrected_Optical_Depth_Land";
	/**
	 * Field:Corrected_Optical_Depth_Land_wav2p1 Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String CORRECTED_OPTICAL_DEPTH_LAND_WAV2P1 = "Corrected_Optical_Depth_Land_wav2p1";
	/**
	 * Field:Optical_Depth_Small_Land Dimensiune:
	 * (Solution_4_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String OPTICAL_DEPTH_SMALL_LAND = "Optical_Depth_Small_Land";
	/**
	 * Field:Optical_Depth_Ratio_Small_Land Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String OPTICAL_DEPTH_RATIO_SMALL_LAND = "Optical_Depth_Ratio_Small_Land";
	/**
	 * Field:Number_Pixels_Used_Land Dimensiune:
	 * (Solution_1_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String NUMBER_PIXELS_USED_LAND = "Number_Pixels_Used_Land";
	/**
	 * Field:Mean_Reflectance_Land Dimensiune:
	 * (MODIS_Band_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String MEAN_REFLECTANCE_LAND = "Mean_Reflectance_Land";
	/**
	 * Field:STD_Reflectance_Land Dimensiune:
	 * (MODIS_Band_Land,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String STD_REFLECTANCE_LAND = "STD_Reflectance_Land";
	/**
	 * Field:Mass_Concentration_Land Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
	 */
	public static final String MASS_CONCENTRATION_LAND = "Mass_Concentration_Land";
	/**
	 * Field:Angstrom_Exponent_Land Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String ANGSTROM_EXPONENT_LAND = "Angstrom_Exponent_Land";
	/**
	 * Field:Cloud_Fraction_Land Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String CLOUD_FRACTION_LAND = "Cloud_Fraction_Land";
	/**
	 * Field:Quality_Assurance_Land Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath,QA_Byte_Land) Tip: DFNT_INT8
	 */
	public static final String QUALITY_ASSURANCE_LAND = "Quality_Assurance_Land";
	/**
	 * Field:Quality_Assurance_Crit_Ref_Land Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath,QA_Byte_Land) Tip: DFNT_INT8
	 */
	public static final String QUALITY_ASSURANCE_CRIT_REF_LAND = "Quality_Assurance_Crit_Ref_Land";
	/**
	 * Field:Aerosol_Cldmask_Byproducts_Land Dimensiune:
	 * (Num_By_Products,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String AEROSOL_CLDMASK_BYPRODUCTS_LAND = "Aerosol_Cldmask_Byproducts_Land";
	/**
	 * Field:Solution_Index_Ocean_Small Dimensiune:
	 * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String SOLUTION_INDEX_OCEAN_SMALL = "Solution_Index_Ocean_Small";
	/**
	 * Field:Solution_Index_Ocean_Large Dimensiune:
	 * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String SOLUTION_INDEX_OCEAN_LARGE = "Solution_Index_Ocean_Large";
	/**
	 * Field:Effective_Optical_Depth_Best_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String EFFECTIVE_OPTICAL_DEPTH_BEST_OCEAN = "Effective_Optical_Depth_Best_Ocean";
	/**
	 * Field:Effective_Optical_Depth_Average_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String EFFECTIVE_OPTICAL_DEPTH_AVERAGE_OCEAN = "Effective_Optical_Depth_Average_Ocean";
	/**
	 * Field:Optical_Depth_Small_Best_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String OPTICAL_DEPTH_SMALL_BEST_OCEAN = "Optical_Depth_Small_Best_Ocean";
	/**
	 * Field:Optical_Depth_Small_Average_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String OPTICAL_DEPTH_SMALL_AVERAGE_OCEAN = "Optical_Depth_Small_Average_Ocean";
	/**
	 * Field:Optical_Depth_Large_Best_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String OPTICAL_DEPTH_LARGE_BEST_OCEAN = "Optical_Depth_Large_Best_Ocean";
	/**
	 * Field:Optical_Depth_Large_Average_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String OPTICAL_DEPTH_LARGE_AVERAGE_OCEAN = "Optical_Depth_Large_Average_Ocean";
	/**
	 * Field:Mass_Concentration_Ocean Dimensiune:
	 * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
	 */
	public static final String MASS_CONCENTRATION_OCEAN = "Mass_Concentration_Ocean";
	/**
	 * Field:Effective_Radius_Ocean Dimensiune:
	 * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String EFFECTIVE_RADIUS_OCEAN = "Effective_Radius_Ocean";
	/**
	 * Field:Cloud_Condensation_Nuclei_Ocean Dimensiune:
	 * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_FLOAT32
	 */
	public static final String CLOUD_CONDENSATION_NUCLEI_OCEAN = "Cloud_Condensation_Nuclei_Ocean";
	/**
	 * Field:Asymmetry_Factor_Best_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String ASYMMETRY_FACTOR_BEST_OCEAN = "Asymmetry_Factor_Best_Ocean";
	/**
	 * Field:Asymmetry_Factor_Average_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String ASYMMETRY_FACTOR_AVERAGE_OCEAN = "Asymmetry_Factor_Average_Ocean";
	/**
	 * Field:Backscattering_Ratio_Best_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String BACKSCATTERING_RATIO_BEST_OCEAN = "Backscattering_Ratio_Best_Ocean";
	/**
	 * Field:Backscattering_Ratio_Average_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String BACKSCATTERING_RATIO_AVERAGE_OCEAN = "Backscattering_Ratio_Average_Ocean";
	/**
	 * Field:Angstrom_Exponent_1_Ocean Dimensiune:
	 * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String ANGSTROM_EXPONENT_1_OCEAN = "Angstrom_Exponent_1_Ocean";
	/**
	 * Field:Angstrom_Exponent_2_Ocean Dimensiune:
	 * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String ANGSTROM_EXPONENT_2_OCEAN = "Angstrom_Exponent_2_Ocean";
	/**
	 * Field:Least_Squares_Error_Ocean Dimensiune:
	 * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String LEAST_SQUARES_ERROR_OCEAN = "Least_Squares_Error_Ocean";
	/**
	 * Field:Optical_Depth_Ratio_Small_Ocean_0.55micron Dimensiune:
	 * (Solution_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String OPTICAL_DEPTH_RATIO_SMALL_OCEAN_055MICRON = "Optical_Depth_Ratio_Small_Ocean_0.55micron";
	/**
	 * Field:Optical_Depth_by_models_ocean Dimensiune:
	 * (Solution_Index,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String OPTICAL_DEPTH_BY_MODELS_OCEAN = "Optical_Depth_by_models_ocean";
	/**
	 * Field:Cloud_Fraction_Ocean Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String CLOUD_FRACTION_OCEAN = "Cloud_Fraction_Ocean";
	/**
	 * Field:Number_Pixels_Used_Ocean Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String NUMBER_PIXELS_USED_OCEAN = "Number_Pixels_Used_Ocean";
	/**
	 * Field:Mean_Reflectance_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String MEAN_REFLECTANCE_OCEAN = "Mean_Reflectance_Ocean";
	/**
	 * Field:STD_Reflectance_Ocean Dimensiune:
	 * (MODIS_Band_Ocean,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String STD_REFLECTANCE_OCEAN = "STD_Reflectance_Ocean";
	/**
	 * Field:Quality_Assurance_Ocean Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath,QA_Byte_Ocean) Tip: DFNT_INT8
	 */
	public static final String QUALITY_ASSURANCE_OCEAN = "Quality_Assurance_Ocean";
	/**
	 * Field:Aerosol_Cldmask_Byproducts_Ocean Dimensiune:
	 * (Num_By_Products,Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String AEROSOL_CLDMASK_BYPRODUCTS_OCEAN = "Aerosol_Cldmask_Byproducts_Ocean";
	/**
	 * Field:Deep_Blue_Aerosol_Optical_Depth_550_Land Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND = "Deep_Blue_Aerosol_Optical_Depth_550_Land";
	/**
	 * Field:Deep_Blue_Aerosol_Optical_Depth_Land Dimensiune:
	 * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
	 * DFNT_INT16
	 */
	public static final String DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_LAND = "Deep_Blue_Aerosol_Optical_Depth_Land";
	/**
	 * Field:Deep_Blue_Angstrom_Exponent_Land Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String DEEP_BLUE_ANGSTROM_EXPONENT_LAND = "Deep_Blue_Angstrom_Exponent_Land";
	/**
	 * Field:Deep_Blue_Single_Scattering_Albedo_Land Dimensiune:
	 * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
	 * DFNT_INT16
	 */
	public static final String DEEP_BLUE_SINGLE_SCATTERING_ALBEDO_LAND = "Deep_Blue_Single_Scattering_Albedo_Land";
	/**
	 * Field:Deep_Blue_Surface_Reflectance_Land Dimensiune:
	 * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
	 * DFNT_INT16
	 */
	public static final String DEEP_BLUE_SURFACE_REFLECTANCE_LAND = "Deep_Blue_Surface_Reflectance_Land";
	/**
	 * Field:Deep_Blue_Mean_Reflectance_Land Dimensiune:
	 * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
	 * DFNT_INT16
	 */
	public static final String DEEP_BLUE_MEAN_REFLECTANCE_LAND = "Deep_Blue_Mean_Reflectance_Land";
	/**
	 * Field:Deep_Blue_Number_Pixels_Used_Land Dimensiune:
	 * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
	 * DFNT_INT16
	 */
	public static final String DEEP_BLUE_NUMBER_PIXELS_USED_LAND = "Deep_Blue_Number_Pixels_Used_Land";
	/**
	 * Field:Deep_Blue_Aerosol_Optical_Depth_550_Land_STD Dimensiune:
	 * (Cell_Along_Swath,Cell_Across_Swath) Tip: DFNT_INT16
	 */
	public static final String DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_STD = "Deep_Blue_Aerosol_Optical_Depth_550_Land_STD";
	/**
	 * Field:Deep_Blue_Aerosol_Optical_Depth_Land_STD Dimensiune:
	 * (Num_DeepBlue_Wavelengths,Cell_Along_Swath,Cell_Across_Swath) Tip:
	 * DFNT_INT16
	 */
	public static final String DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_LAND_STD = "Deep_Blue_Aerosol_Optical_Depth_Land_STD";
	public static final String[] fieldNames = new String[] { MODIS_BAND_LAND,
			MODIS_BAND_OCEAN, SOLUTION_1_LAND, SOLUTION_2_LAND,
			SOLUTION_3_LAND, SOLUTION_OCEAN, SOLUTION_INDEX,
			NUM_DEEPBLUE_WAVELENGTHS, SCAN_START_TIME, SOLAR_ZENITH,
			SOLAR_AZIMUTH, SENSOR_ZENITH, SENSOR_AZIMUTH, CLOUD_MASK_QA,
			SCATTERING_ANGLE, OPTICAL_DEPTH_LAND_AND_OCEAN,
			IMAGE_OPTICAL_DEPTH_LAND_AND_OCEAN,
			OPTICAL_DEPTH_RATIO_SMALL_LAND_AND_OCEAN,
			MEAN_REFLECTANCE_LAND_ALL, STANDARD_DEVIATION_REFLECTANCE_LAND_ALL,
			PATH_RADIANCE_LAND, ERROR_PATH_RADIANCE_LAND,
			CRITICAL_REFLECTANCE_LAND, ERROR_CRITICAL_REFLECTANCE_LAND,
			QUALITYWEIGHT_PATH_RADIANCE_LAND,
			QUALITYWEIGHT_CRITICAL_REFLECTANCE_LAND, AEROSOL_TYPE_LAND,
			FITTING_ERROR_LAND, SURFACE_REFLECTANCE_LAND,
			CORRECTED_OPTICAL_DEPTH_LAND, CORRECTED_OPTICAL_DEPTH_LAND_WAV2P1,
			OPTICAL_DEPTH_SMALL_LAND, OPTICAL_DEPTH_RATIO_SMALL_LAND,
			NUMBER_PIXELS_USED_LAND, MEAN_REFLECTANCE_LAND,
			STD_REFLECTANCE_LAND, MASS_CONCENTRATION_LAND,
			ANGSTROM_EXPONENT_LAND, CLOUD_FRACTION_LAND,
			QUALITY_ASSURANCE_LAND, QUALITY_ASSURANCE_CRIT_REF_LAND,
			AEROSOL_CLDMASK_BYPRODUCTS_LAND, SOLUTION_INDEX_OCEAN_SMALL,
			SOLUTION_INDEX_OCEAN_LARGE, EFFECTIVE_OPTICAL_DEPTH_BEST_OCEAN,
			EFFECTIVE_OPTICAL_DEPTH_AVERAGE_OCEAN,
			OPTICAL_DEPTH_SMALL_BEST_OCEAN, OPTICAL_DEPTH_SMALL_AVERAGE_OCEAN,
			OPTICAL_DEPTH_LARGE_BEST_OCEAN, OPTICAL_DEPTH_LARGE_AVERAGE_OCEAN,
			MASS_CONCENTRATION_OCEAN, EFFECTIVE_RADIUS_OCEAN,
			CLOUD_CONDENSATION_NUCLEI_OCEAN, ASYMMETRY_FACTOR_BEST_OCEAN,
			ASYMMETRY_FACTOR_AVERAGE_OCEAN, BACKSCATTERING_RATIO_BEST_OCEAN,
			BACKSCATTERING_RATIO_AVERAGE_OCEAN, ANGSTROM_EXPONENT_1_OCEAN,
			ANGSTROM_EXPONENT_2_OCEAN, LEAST_SQUARES_ERROR_OCEAN,
			OPTICAL_DEPTH_RATIO_SMALL_OCEAN_055MICRON,
			OPTICAL_DEPTH_BY_MODELS_OCEAN, CLOUD_FRACTION_OCEAN,
			NUMBER_PIXELS_USED_OCEAN, MEAN_REFLECTANCE_OCEAN,
			STD_REFLECTANCE_OCEAN, QUALITY_ASSURANCE_OCEAN,
			AEROSOL_CLDMASK_BYPRODUCTS_OCEAN,
			DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND,
			DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_LAND,
			DEEP_BLUE_ANGSTROM_EXPONENT_LAND,
			DEEP_BLUE_SINGLE_SCATTERING_ALBEDO_LAND,
			DEEP_BLUE_SURFACE_REFLECTANCE_LAND,
			DEEP_BLUE_MEAN_REFLECTANCE_LAND, DEEP_BLUE_NUMBER_PIXELS_USED_LAND,
			DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_550_LAND_STD,
			DEEP_BLUE_AEROSOL_OPTICAL_DEPTH_LAND_STD };
}
