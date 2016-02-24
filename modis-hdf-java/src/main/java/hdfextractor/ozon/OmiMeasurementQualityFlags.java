package hdfextractor.ozon;

/**
 * 
 * 0 - missing data 1 - radiance measurement error flag 2 - radiance measurement
 * warning flag 3 - rebinned radiance measurement flag 4 - South Atlantic
 * anomaly flag 5 - irradiance measurement warning flag
 * 
 * @author geo-track idx264
 * 
 */
public class OmiMeasurementQualityFlags {
	boolean missingData;
	boolean radianceMeasurementErrorFlag;
	boolean radianceMeasurementWarningFlag;
	boolean rebinnedRadianceMeasurementFlag;
	boolean southAtlanticAnomalyFlag;
	boolean irradianceMeasurementWarningFlag;

	public OmiMeasurementQualityFlags(int cod) {
		if ((cod & 1) > 0) {
			missingData = true;
		}
		if ((cod & 2) > 0) {
			radianceMeasurementErrorFlag = true;
		}
		if ((cod & 4) > 0) {
			radianceMeasurementWarningFlag = true;
		}
		if ((cod & 8) > 0) {
			rebinnedRadianceMeasurementFlag = true;
		}
		if ((cod & 16) > 0) {
			southAtlanticAnomalyFlag = true;
		}
		if ((cod & 32) > 0) {
			irradianceMeasurementWarningFlag = true;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OmiMeasurementQualityFlags [missingData=");
		builder.append(missingData);
		builder.append(", radianceMeasurementErrorFlag=");
		builder.append(radianceMeasurementErrorFlag);
		builder.append(", radianceMeasurementWarningFlag=");
		builder.append(radianceMeasurementWarningFlag);
		builder.append(", rebinnedRadianceMeasurementFlag=");
		builder.append(rebinnedRadianceMeasurementFlag);
		builder.append(", southAtlanticAnomalyFlag=");
		builder.append(southAtlanticAnomalyFlag);
		builder.append(", irradianceMeasurementWarningFlag=");
		builder.append(irradianceMeasurementWarningFlag);
		builder.append("]");
		return builder.toString();
	}

	public boolean isTrue() {
		return radianceMeasurementErrorFlag || radianceMeasurementWarningFlag
				|| rebinnedRadianceMeasurementFlag || southAtlanticAnomalyFlag
				|| irradianceMeasurementWarningFlag;
	}

	public String printTrueValues() {
		StringBuilder builder = new StringBuilder();
		builder.append("OmiMeasurementQualityFlags [");
		if (missingData) {
			builder.append("missingData");
		}
		if (radianceMeasurementErrorFlag) {
			builder.append(", radianceMeasurementErrorFlag");
		}
		if (radianceMeasurementWarningFlag) {
			builder.append(", radianceMeasurementWarningFlag");
		}
		if (rebinnedRadianceMeasurementFlag) {
			builder.append(", rebinnedRadianceMeasurementFlag");
		}
		if (southAtlanticAnomalyFlag) {
			builder.append(", southAtlanticAnomalyFlag");
		}
		if (irradianceMeasurementWarningFlag) {
			builder.append(", irradianceMeasurementWarningFlag");
		}
		builder.append("]");
		return builder.toString();
	}
}
