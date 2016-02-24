package hdfextractor;

import hdfextractor.utils.SimpleStringLineBuilder;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Grupez valorile pentru o valoare de MODIS 06
 * 
 * @author Grigoras Cristinel
 * 
 */
public class Mod06TO implements Serializable {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(Mod06TO.class);
	/**
	 *
	 */
	private static final long serialVersionUID = 4383002366170296591L;
	private DateTime timp = null;
	private String hdfFileName = "";
	private String numePozitie = "";

	public String getNumePozitie() {
		return numePozitie;
	}

	public void setNumePozitie(String numePozitie) {
		this.numePozitie = numePozitie;
	}

	public String getHdfFileName() {
		return hdfFileName;
	}

	public void setHdfFileName(String hdfFileName) {
		this.hdfFileName = hdfFileName;
	}

	public EnumCloudType getCloudType() {
		return cloudType;
	}

	public void setCloudType(EnumCloudType cloudType) {
		this.cloudType = cloudType;
	}

	private Long pozitie;
	private Long solarZenith;
	private Long solarAzimuth;
	private Long sensorZenith;
	private Long sensorAzimuth;
	private Long brightnessTemperature;
	private Long surfaceTemperature;
	private Long surfacePressure;
	private Byte processingFlag;
	private Byte cloudHeightMethod;
	private Double cloudTopPressure;
	private Double cloudTopTemperature;
	private Double tropopauseHeight;
	private Double cloudFraction;
	private Double cloudEffectiveEmissivity;
	private Long cloudTopPressureInfrared;
	private Long spectralCloudForcing;
	private Long cloudTopPressureFromRatios;
	private Long surfaceType;
	private Long radianceVariance;
	private Byte cloudPhaseInfrared;
	private Long cloudEffectiveRadius;
	private Double cloudOpticalThickness;
	private Long cloudEffectiveRadius1621;
	private Long cloudOpticalThickness1621;
	private Long effectiveRadiusDifference;
	private Long cloudWaterPath;
	private Long cloudWaterPath1621;
	private Long cloudEffectiveRadiusUncertainty;
	private Long cloudOpticalThicknessUncertainty;
	private Long cloudWaterPathUncertainty;
	private Long cloudEffectiveRadiusUncertainty1621;
	private Long cloudOpticalThicknessUncertainty1621;
	private Long cloudWaterPathUncertainty1621;
	private Long cloudPhaseOpticalProperties;
	private Long cloudMultiLayerFlag;
	private Double cirrusReflectance;
	private Byte cirrusReflectanceFlag;
	private Byte cloudMask5km;
	private Byte qualityAssurance5km;
	private Byte cloudMask1km;
	private Byte qualityAssurance1km;
	private EnumCloudType cloudType;

	public Long getSolarZenith() {
		return solarZenith;
	}

	public void setSolarZenith(Long solarZenith) {
		this.solarZenith = solarZenith;
	}

	public Long getSolarAzimuth() {
		return solarAzimuth;
	}

	public void setSolarAzimuth(Long solarAzimuth) {
		this.solarAzimuth = solarAzimuth;
	}

	public Long getSensorZenith() {
		return sensorZenith;
	}

	public void setSensorZenith(Long sensorZenith) {
		this.sensorZenith = sensorZenith;
	}

	public Long getSensorAzimuth() {
		return sensorAzimuth;
	}

	public void setSensorAzimuth(Long sensorAzimuth) {
		this.sensorAzimuth = sensorAzimuth;
	}

	public Long getBrightnessTemperature() {
		return brightnessTemperature;
	}

	public void setBrightnessTemperature(Long brightnessTemperature) {
		this.brightnessTemperature = brightnessTemperature;
	}

	public Long getSurfaceTemperature() {
		return surfaceTemperature;
	}

	public void setSurfaceTemperature(Long surfaceTemperature) {
		this.surfaceTemperature = surfaceTemperature;
	}

	public Long getSurfacePressure() {
		return surfacePressure;
	}

	public void setSurfacePressure(Long surfacePressure) {
		this.surfacePressure = surfacePressure;
	}

	public Byte getProcessingFlag() {
		return processingFlag;
	}

	public void setProcessingFlag(Byte processingFlag) {
		this.processingFlag = processingFlag;
	}

	public Byte getCloudHeightMethod() {
		return cloudHeightMethod;
	}

	public void setCloudHeightMethod(Byte cloudHeightMethod) {
		this.cloudHeightMethod = cloudHeightMethod;
	}

	public Double getCloudTopPressure() {
		return cloudTopPressure;
	}

	public void setCloudTopPressure(Double cloudTopPressure) {
		this.cloudTopPressure = cloudTopPressure;
	}

	public Double getCloudTopTemperature() {
		return cloudTopTemperature;
	}

	public void setCloudTopTemperature(Double cloudTopTemperature) {
		this.cloudTopTemperature = cloudTopTemperature;
	}

	public Double getTropopauseHeight() {
		return tropopauseHeight;
	}

	public void setTropopauseHeight(Double tropopauseHeight) {
		this.tropopauseHeight = tropopauseHeight;
	}

	public Double getCloudFraction() {
		return cloudFraction;
	}

	public void setCloudFraction(Double cloudFraction) {
		this.cloudFraction = cloudFraction;
	}

	public Double getCloudEffectiveEmissivity() {
		return cloudEffectiveEmissivity;
	}

	public void setCloudEffectiveEmissivity(Double cloudEffectiveEmissivity) {
		this.cloudEffectiveEmissivity = cloudEffectiveEmissivity;
	}

	public Long getCloudTopPressureInfrared() {
		return cloudTopPressureInfrared;
	}

	public void setCloudTopPressureInfrared(Long cloudTopPressureInfrared) {
		this.cloudTopPressureInfrared = cloudTopPressureInfrared;
	}

	public Long getSpectralCloudForcing() {
		return spectralCloudForcing;
	}

	public void setSpectralCloudForcing(Long spectralCloudForcing) {
		this.spectralCloudForcing = spectralCloudForcing;
	}

	public Long getCloudTopPressureFromRatios() {
		return cloudTopPressureFromRatios;
	}

	public void setCloudTopPressureFromRatios(Long cloudTopPressureFromRatios) {
		this.cloudTopPressureFromRatios = cloudTopPressureFromRatios;
	}

	public Long getSurfaceType() {
		return surfaceType;
	}

	public void setSurfaceType(Long surfaceType) {
		this.surfaceType = surfaceType;
	}

	public Long getRadianceVariance() {
		return radianceVariance;
	}

	public void setRadianceVariance(Long radianceVariance) {
		this.radianceVariance = radianceVariance;
	}

	public Byte getCloudPhaseInfrared() {
		return cloudPhaseInfrared;
	}

	public void setCloudPhaseInfrared(Byte cloudPhaseInfrared) {
		this.cloudPhaseInfrared = cloudPhaseInfrared;
	}

	public Long getCloudEffectiveRadius() {
		return cloudEffectiveRadius;
	}

	public void setCloudEffectiveRadius(Long cloudEffectiveRadius) {
		this.cloudEffectiveRadius = cloudEffectiveRadius;
	}

	public Double getCloudOpticalThickness() {
		return cloudOpticalThickness;
	}

	public void setCloudOpticalThickness(Double cloudOpticalThickness) {
		this.cloudOpticalThickness = cloudOpticalThickness;
	}

	public Long getCloudEffectiveRadius1621() {
		return cloudEffectiveRadius1621;
	}

	public void setCloudEffectiveRadius1621(Long cloudEffectiveRadius1621) {
		this.cloudEffectiveRadius1621 = cloudEffectiveRadius1621;
	}

	public Long getCloudOpticalThickness1621() {
		return cloudOpticalThickness1621;
	}

	public void setCloudOpticalThickness1621(Long cloudOpticalThickness1621) {
		this.cloudOpticalThickness1621 = cloudOpticalThickness1621;
	}

	public Long getEffectiveRadiusDifference() {
		return effectiveRadiusDifference;
	}

	public void setEffectiveRadiusDifference(Long effectiveRadiusDifference) {
		this.effectiveRadiusDifference = effectiveRadiusDifference;
	}

	public Long getCloudWaterPath() {
		return cloudWaterPath;
	}

	public void setCloudWaterPath(Long cloudWaterPath) {
		this.cloudWaterPath = cloudWaterPath;
	}

	public Long getCloudWaterPath1621() {
		return cloudWaterPath1621;
	}

	public void setCloudWaterPath1621(Long cloudWaterPath1621) {
		this.cloudWaterPath1621 = cloudWaterPath1621;
	}

	public Long getCloudEffectiveRadiusUncertainty() {
		return cloudEffectiveRadiusUncertainty;
	}

	public void setCloudEffectiveRadiusUncertainty(
			Long cloudEffectiveRadiusUncertainty) {
		this.cloudEffectiveRadiusUncertainty = cloudEffectiveRadiusUncertainty;
	}

	public Long getCloudOpticalThicknessUncertainty() {
		return cloudOpticalThicknessUncertainty;
	}

	public void setCloudOpticalThicknessUncertainty(
			Long cloudOpticalThicknessUncertainty) {
		this.cloudOpticalThicknessUncertainty = cloudOpticalThicknessUncertainty;
	}

	public Long getCloudWaterPathUncertainty() {
		return cloudWaterPathUncertainty;
	}

	public void setCloudWaterPathUncertainty(Long cloudWaterPathUncertainty) {
		this.cloudWaterPathUncertainty = cloudWaterPathUncertainty;
	}

	public Long getCloudEffectiveRadiusUncertainty1621() {
		return cloudEffectiveRadiusUncertainty1621;
	}

	public void setCloudEffectiveRadiusUncertainty1621(
			Long cloudEffectiveRadiusUncertainty1621) {
		this.cloudEffectiveRadiusUncertainty1621 = cloudEffectiveRadiusUncertainty1621;
	}

	public Long getCloudOpticalThicknessUncertainty1621() {
		return cloudOpticalThicknessUncertainty1621;
	}

	public void setCloudOpticalThicknessUncertainty1621(
			Long cloudOpticalThicknessUncertainty1621) {
		this.cloudOpticalThicknessUncertainty1621 = cloudOpticalThicknessUncertainty1621;
	}

	public Long getCloudWaterPathUncertainty1621() {
		return cloudWaterPathUncertainty1621;
	}

	public void setCloudWaterPathUncertainty1621(
			Long cloudWaterPathUncertainty1621) {
		this.cloudWaterPathUncertainty1621 = cloudWaterPathUncertainty1621;
	}

	public Long getCloudPhaseOpticalProperties() {
		return cloudPhaseOpticalProperties;
	}

	public void setCloudPhaseOpticalProperties(Long cloudPhaseOpticalProperties) {
		this.cloudPhaseOpticalProperties = cloudPhaseOpticalProperties;
	}

	public Long getCloudMultiLayerFlag() {
		return cloudMultiLayerFlag;
	}

	public void setCloudMultiLayerFlag(Long cloudMultiLayerFlag) {
		this.cloudMultiLayerFlag = cloudMultiLayerFlag;
	}

	public Double getCirrusReflectance() {
		return cirrusReflectance;
	}

	public void setCirrusReflectance(Double cirrusReflectance) {
		this.cirrusReflectance = cirrusReflectance;
	}

	public Byte getCirrusReflectanceFlag() {
		return cirrusReflectanceFlag;
	}

	public void setCirrusReflectanceFlag(Byte cirrusReflectanceFlag) {
		this.cirrusReflectanceFlag = cirrusReflectanceFlag;
	}

	public Byte getCloudMask5km() {
		return cloudMask5km;
	}

	public void setCloudMask5km(Byte cloudMask5km) {
		this.cloudMask5km = cloudMask5km;
	}

	public Byte getQualityAssurance5km() {
		return qualityAssurance5km;
	}

	public void setQualityAssurance5km(Byte qualityAssurance5km) {
		this.qualityAssurance5km = qualityAssurance5km;
	}

	public Byte getCloudMask1km() {
		return cloudMask1km;
	}

	public void setCloudMask1km(Byte cloudMask1km) {
		this.cloudMask1km = cloudMask1km;
	}

	public Byte getQualityAssurance1km() {
		return qualityAssurance1km;
	}

	public void setQualityAssurance1km(Byte qualityAssurance1km) {
		this.qualityAssurance1km = qualityAssurance1km;
	}

	public Long getPozitie() {
		return pozitie;
	}

	public void setPozitie(Long pozitie) {
		this.pozitie = pozitie;
	}

	public DateTime getTimp() {
		return timp;
	}

	public void setTimp(DateTime timp) {
		this.timp = timp;
	}

	public static String longHeader() {
		SimpleStringLineBuilder builder = new SimpleStringLineBuilder();
		builder.append("timp")
				.append("pozitie")
				.append("solarZenith")
				.append("solarAzimuth")
				.append("sensorZenith")
				.append("sensorAzimuth")
				// .append("brightnessTemperature")
				.append("surfaceTemperature").append("surfacePressure")
				.append("processingFlag").append("cloudHeightMethod")
				.append("cloudTopPressure").append("cloudTopTemperature")
				.append("tropopauseHeight").append("cloudFraction")
				.append("cloudEffectiveEmissivity")
				.append("cloudTopPressureInfrared")
				.append("spectralCloudForcing")
				.append("cloudTopPressureFromRatios").append("surfaceType")
				.append("radianceVariance").append("cloudPhaseInfrared")
				.append("cloudEffectiveRadius").append("cloudOpticalThickness")
				.append("cloudEffectiveRadius1621")
				.append("cloudOpticalThickness1621")
				.append("effectiveRadiusDifference").append("cloudWaterPath")
				.append("cloudWaterPath1621")
				.append("cloudEffectiveRadiusUncertainty")
				.append("cloudOpticalThicknessUncertainty")
				.append("cloudWaterPathUncertainty")
				.append("cloudEffectiveRadiusUncertainty1621")
				.append("cloudOpticalThicknessUncertainty1621")
				.append("cloudWaterPathUncertainty1621")
				.append("cloudPhaseOpticalProperties")
				.append("cloudMultiLayerFlag").append("cirrusReflectance")
				.append("cirrusReflectanceFlag").append("cloudMask5km")
				.append("qualityAssurance5km").append("cloudMask1km")
				.append("qualityAssurance1km").append("cloudType")
				.append("hdfFileName");
		return builder.toString();
	}

	private String dToI(Double val) {
		if (val != null) {
			return val.intValue() + "";
		}
		return null;
	}

	public String toLongLine() {
		if (cloudTopPressure != null && cloudOpticalThickness != null) {
			cloudType = EnumCloudType.selectType(cloudTopPressure,
					cloudOpticalThickness);
		}
		SimpleStringLineBuilder builder = new SimpleStringLineBuilder();
		DateTime timp2 = timp.withZone(DateTimeZone.UTC);
		builder.append(timp2)
				.append(pozitie)
				.append(solarZenith)
				.append(solarAzimuth)
				.append(sensorZenith)
				.append(sensorAzimuth)
				// .append(brightnessTemperature)
				.append(surfaceTemperature).append(surfacePressure)
				.append(processingFlag).append(cloudHeightMethod)
				.append(dToI(cloudTopPressure))
				.append(dToI(cloudTopTemperature))
				.append(dToI(tropopauseHeight)).append(cloudFraction)
				.append(cloudEffectiveEmissivity)
				.append(cloudTopPressureInfrared).append(spectralCloudForcing)
				.append(cloudTopPressureFromRatios).append(surfaceType)
				.append(radianceVariance).append(cloudPhaseInfrared)
				.append(cloudEffectiveRadius).append(cloudOpticalThickness)
				.append(cloudEffectiveRadius1621)
				.append(cloudOpticalThickness1621)
				.append(effectiveRadiusDifference).append(cloudWaterPath)
				.append(cloudWaterPath1621)
				.append(cloudEffectiveRadiusUncertainty)
				.append(cloudOpticalThicknessUncertainty)
				.append(cloudWaterPathUncertainty)
				.append(cloudEffectiveRadiusUncertainty1621)
				.append(cloudOpticalThicknessUncertainty1621)
				.append(cloudWaterPathUncertainty1621)
				.append(cloudPhaseOpticalProperties)
				.append(cloudMultiLayerFlag).append(cirrusReflectance)
				.append(cirrusReflectanceFlag).append(cloudMask5km)
				.append(qualityAssurance5km).append(cloudMask1km)
				.append(qualityAssurance1km).append(cloudType)
				.append(numePozitie).append(hdfFileName);
		return builder.toString();
	}

	public boolean isCompleteWater() {
		if (logger.isDebugEnabled()) {
			logger.debug("isComplete() - start"); //$NON-NLS-1$
		}
		if (timp == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end timp"); //$NON-NLS-1$
			}
			return false;
		}
		if (pozitie == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end pozitie"); //$NON-NLS-1$
			}
			return false;
		}
		if (solarZenith == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end solarZenith"); //$NON-NLS-1$
			}
			return false;
		}
		if (solarAzimuth == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end solarAzimuth"); //$NON-NLS-1$
			}
			return false;
		}
		if (sensorZenith == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end sensorZenith"); //$NON-NLS-1$
			}
			return false;
		}
		if (sensorAzimuth == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end sensorAzimuth"); //$NON-NLS-1$
			}
			return false;
		}
		if (surfaceTemperature == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end surfaceTemperature"); //$NON-NLS-1$
			}
			return false;
		}
		if (surfacePressure == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end surfacePressure"); //$NON-NLS-1$
			}
			return false;
		}
		if (processingFlag == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end processingFlag"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudHeightMethod == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudHeightMethod"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudTopPressure == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudTopPressure"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudTopTemperature == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudTopTemperature"); //$NON-NLS-1$
			}
			return false;
		}
		if (tropopauseHeight == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end tropopauseHeight"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudFraction == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudFraction"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudEffectiveEmissivity == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudEffectiveEmissivity"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudTopPressureInfrared == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudTopPressureInfrared"); //$NON-NLS-1$
			}
			return false;
		}
		if (surfaceType == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end surfaceType"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudEffectiveRadius == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudEffectiveRadius"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudOpticalThickness == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudOpticalThickness"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudWaterPath == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudWaterPath"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudEffectiveRadiusUncertainty == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudEffectiveRadiusUncertainty"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudOpticalThicknessUncertainty == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudOpticalThicknessUncertainty"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudWaterPathUncertainty == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudWaterPathUncertainty"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudPhaseOpticalProperties == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudPhaseOpticalProperties"); //$NON-NLS-1$
			}
			return false;
		}
		// if (cloudMultiLayerFlag == null) {
		// if (logger.isDebugEnabled()) {
		//            logger.debug("isComplete() - end cloudMultiLayerFlag"); //$NON-NLS-1$
		// }
		// return false;
		// }
		if (cirrusReflectance == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cirrusReflectance"); //$NON-NLS-1$
			}
			return false;
		}
		if (cirrusReflectanceFlag == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cirrusReflectanceFlag"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudMask5km == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudMask5km"); //$NON-NLS-1$
			}
			return false;
		}
		if (qualityAssurance5km == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end qualityAssurance5km"); //$NON-NLS-1$
			}
			return false;
		}
		if (cloudMask1km == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end cloudMask1km"); //$NON-NLS-1$
			}
			return false;
		}
		if (qualityAssurance1km == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("isComplete() - end qualityAssurance1km"); //$NON-NLS-1$
			}
			return false;
		}
		// if (radianceVariance == null) {
		// if (logger.isDebugEnabled()) {
		//            logger.debug("isComplete() - end radianceVariance"); //$NON-NLS-1$
		// }
		// return false;
		// }
		if (logger.isDebugEnabled()) {
			logger.debug("isComplete() - end ok"); //$NON-NLS-1$
		}
		return true;
	}
}
