package hdfextractor.ozon;

public class OmiProcessingQualityFlags {
	public int value;
	public boolean _b0_failedConvergenceCheck;
	public boolean _b1_solarZenithAngleLatlonOutOfRange;
	public boolean _b2_cloud_pressure_less_than_low_range_of_table;
	public boolean _b3_cloud_pressure_greater_than_surface_pressure;
	public boolean _b4_matrix_inversion_failed;
	public boolean _b5_snow_ice;
	public boolean _b6_reflectivity_lt_0_or_gt_1;
	public boolean _b7_bad_radiances_detected;
	public boolean _b8_aerosol_index_flag;
	public boolean _b9_radiance_PixelQuality_error;
	public boolean _b10_radiance_PixelQuality_warning;
	public boolean _b11_irradiance_PixelQuality_error;
	public boolean _b12_irradiance_PixelQuality_warning;
	public boolean _b13_effective_surface_pressure_retrieved_because_cloud_fraction02;
	public boolean _b14_missing_data;
	public boolean _b15_geolocation_error;

	public OmiProcessingQualityFlags(int cod) {
		this.value = cod;
		if ((cod & 1) > 0) {
			_b0_failedConvergenceCheck = true;
		}
		if ((cod & 2) > 0) {
			_b1_solarZenithAngleLatlonOutOfRange = true;
		}
		if ((cod & 4) > 0) {
			_b2_cloud_pressure_less_than_low_range_of_table = true;
		}
		if ((cod & 8) > 0) {
			_b3_cloud_pressure_greater_than_surface_pressure = true;
		}
		if ((cod & 16) > 0) {
			_b4_matrix_inversion_failed = true;
		}
		if ((cod & 32) > 0) {
			_b5_snow_ice = true;
		}
		if ((cod & 64) > 0) {
			_b6_reflectivity_lt_0_or_gt_1 = true;
		}
		if ((cod & 128) > 0) {
			_b7_bad_radiances_detected = true;
		}
		if ((cod & 256) > 0) {
			_b8_aerosol_index_flag = true;
		}
		if ((cod & 512) > 0) {
			_b9_radiance_PixelQuality_error = true;
		}
		if ((cod & 1024) > 0) {
			_b10_radiance_PixelQuality_warning = true;
		}
		if ((cod & 2048) > 0) {
			_b11_irradiance_PixelQuality_error = true;
		}
		if ((cod & 4096) > 0) {
			_b12_irradiance_PixelQuality_warning = true;
		}
		if ((cod & 8192) > 0) {
			_b13_effective_surface_pressure_retrieved_because_cloud_fraction02 = true;
		}
		if ((cod & 16384) > 0) {
			_b14_missing_data = true;
		}
		if ((cod & 32784) > 0) {
			_b15_geolocation_error = true;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OmiProcessingQualityFlags [_b0_failedConvergenceCheck=");
		builder.append(_b0_failedConvergenceCheck);
		builder.append(", _b1_solarZenithAngleLatlonOutOfRange=");
		builder.append(_b1_solarZenithAngleLatlonOutOfRange);
		builder.append(", _b2_cloud_pressure_less_than_low_range_of_table=");
		builder.append(_b2_cloud_pressure_less_than_low_range_of_table);
		builder.append(", _b3_cloud_pressure_greater_than_surface_pressure=");
		builder.append(_b3_cloud_pressure_greater_than_surface_pressure);
		builder.append(", _b4_matrix_inversion_failed=");
		builder.append(_b4_matrix_inversion_failed);
		builder.append(", _b5_snow_ice=");
		builder.append(_b5_snow_ice);
		builder.append(", _b6_reflectivity_lt_0_or_gt_1=");
		builder.append(_b6_reflectivity_lt_0_or_gt_1);
		builder.append(", _b7_bad_radiances_detected=");
		builder.append(_b7_bad_radiances_detected);
		builder.append(", _b8_aerosol_index_flag=");
		builder.append(_b8_aerosol_index_flag);
		builder.append(", _b9_radiance_PixelQuality_error=");
		builder.append(_b9_radiance_PixelQuality_error);
		builder.append(", _b10_radiance_PixelQuality_warning=");
		builder.append(_b10_radiance_PixelQuality_warning);
		builder.append(", _b11_irradiance_PixelQuality_error=");
		builder.append(_b11_irradiance_PixelQuality_error);
		builder.append(", _b12_irradiance_PixelQuality_warning=");
		builder.append(_b12_irradiance_PixelQuality_warning);
		builder.append(", _b13_effective_surface_pressure_retrieved_because_cloud_fraction02=");
		builder.append(_b13_effective_surface_pressure_retrieved_because_cloud_fraction02);
		builder.append(", _b14_missing_data=");
		builder.append(_b14_missing_data);
		builder.append(", _b15_geolocation_error=");
		builder.append(_b15_geolocation_error);
		builder.append("]");
		return builder.toString();
	}

	public boolean isTrue() {
		return _b0_failedConvergenceCheck
				|| _b1_solarZenithAngleLatlonOutOfRange
				|| _b2_cloud_pressure_less_than_low_range_of_table
				|| _b3_cloud_pressure_greater_than_surface_pressure
				|| _b4_matrix_inversion_failed
				|| _b5_snow_ice
				|| _b6_reflectivity_lt_0_or_gt_1
				|| _b7_bad_radiances_detected
				|| _b8_aerosol_index_flag
				|| _b9_radiance_PixelQuality_error
				|| _b10_radiance_PixelQuality_warning
				|| _b11_irradiance_PixelQuality_error
				|| _b12_irradiance_PixelQuality_warning
				|| _b13_effective_surface_pressure_retrieved_because_cloud_fraction02
				|| _b14_missing_data || _b15_geolocation_error;
	}

	public String printTrueValues() {
		StringBuilder builder = new StringBuilder();
		builder.append("OmiProcessingQualityFlags [");
		if (_b0_failedConvergenceCheck) {
			builder.append("_b0_failedConvergenceCheck");
		}
		if (_b1_solarZenithAngleLatlonOutOfRange) {
			builder.append(", _b1_solarZenithAngleLatlonOutOfRange");
		}
		if (_b2_cloud_pressure_less_than_low_range_of_table) {
			builder.append(", _b2_cloud_pressure_less_than_low_range_of_table");
		}
		if (_b3_cloud_pressure_greater_than_surface_pressure) {
			builder.append(", _b3_cloud_pressure_greater_than_surface_pressure");
		}
		if (_b4_matrix_inversion_failed) {
			builder.append(", _b4_matrix_inversion_failed");
		}
		if (_b5_snow_ice) {
			builder.append(", _b5_snow_ice");
		}
		if (_b6_reflectivity_lt_0_or_gt_1) {
			builder.append(", _b6_reflectivity_lt_0_or_gt_1");
		}
		if (_b7_bad_radiances_detected) {
			builder.append(", _b7_bad_radiances_detected");
		}
		if (_b8_aerosol_index_flag) {
			builder.append(", _b8_aerosol_index_flag");
		}
		if (_b9_radiance_PixelQuality_error) {
			builder.append(", _b9_radiance_PixelQuality_error");
		}
		if (_b10_radiance_PixelQuality_warning) {
			builder.append(", _b10_radiance_PixelQuality_warning");
		}
		if (_b11_irradiance_PixelQuality_error) {
			builder.append(", _b11_irradiance_PixelQuality_error");
		}
		if (_b12_irradiance_PixelQuality_warning) {
			builder.append(", _b12_irradiance_PixelQuality_warning");
		}
		if (_b13_effective_surface_pressure_retrieved_because_cloud_fraction02) {
			builder.append(", _b13_effective_surface_pressure_retrieved_because_cloud_fraction02");
		}
		if (_b14_missing_data) {
			builder.append(", _b14_missing_data");
		}
		if (_b15_geolocation_error) {
			builder.append(", _b15_geolocation_error");
		}
		builder.append("]");
		return builder.toString();
	}
}
