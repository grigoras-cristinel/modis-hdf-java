package hdfextractor;

import org.joda.time.DateTimeZone;
import hdfextractor.utils.SimpleStringLineBuilder;
import java.io.Serializable;
import org.joda.time.DateTime;
public class Mod06C06TO implements Serializable {

	private DateTime timp = null;
	private String hdfFileName = "";
	private String numePozitie = "";
	private double latitudine;
	private double longitudine;
	private String statie;
	private EnumCloudType cloudType;
	/**
	 * Pozitia corespunzatoare pt 5 km
	 */
	private Long pozitie;
	/**
	 * Pozitia corespunzatoare pt 5 km perpendicular pe directie
	 */
	private Long pozitieAcross;
	/**
	 * Pozitia corespunzatoare pt 5 km pe directie
	 */
	private Long pozitieAlong;
	/**
	 * Pozitia corespunzatoare pt 1 km
	 */
	private Long pozitie1km;
	/**
	 * Distanta dintre punctul pe grila de 5km si cel pe grila de 1km
	 */
	private Double deltaPozitieKm;
	private Double aboveCloudWaterVapor094;
	private Double asymmetryParameterIce;
	private Double asymmetryParameterLiq;
	private Double[] atmCorrRefl = new Double[6];
	private Double atmCorrRefl0;
	private Double atmCorrRefl1;
	private Double atmCorrRefl2;
	private Double atmCorrRefl3;
	private Double atmCorrRefl4;
	private Double atmCorrRefl5;
	private Double[] brightnessTemperature = new Double[7];
	private Double brightnessTemperature0;
	private Double brightnessTemperature1;
	private Double brightnessTemperature2;
	private Double brightnessTemperature3;
	private Double brightnessTemperature4;
	private Double brightnessTemperature5;
	private Double brightnessTemperature6;
	private Double cirrusReflectance;
	private Byte cirrusReflectanceFlag;
	private Byte cloudEffectiveEmissivity;
	private Byte cloudEffectiveEmissivityDay;
	private Byte cloudEffectiveEmissivityNadir;
	private Byte cloudEffectiveEmissivityNadirDay;
	private Byte cloudEffectiveEmissivityNadirNight;
	private Byte cloudEffectiveEmissivityNight;
	private Double cloudEffectiveRadius;
	private Double cloudEffectiveRadius16;
	private Double cloudEffectiveRadius1621;
	private Double cloudEffectiveRadius1621Pcl;
	private Double cloudEffectiveRadius16Pcl;
	private Double cloudEffectiveRadius37;
	private Double cloudEffectiveRadius37Pcl;
	private Double cloudEffectiveRadiusPcl;
	private Double cloudEffectiveRadiusUncertainty;
	private Double cloudEffectiveRadiusUncertainty16;
	private Double cloudEffectiveRadiusUncertainty1621;
	private Double cloudEffectiveRadiusUncertainty37;
	private Double cloudEmiss111km;
	private Double cloudEmiss121km;
	private Double cloudEmiss131km;
	private Double cloudEmiss851km;
	private Byte cloudEmissivity1km;
	private Byte cloudFraction;
	private Byte cloudFractionDay;
	private Byte cloudFractionNadir;
	private Byte cloudFractionNadirDay;
	private Byte cloudFractionNadirNight;
	private Byte cloudFractionNight;
	private Byte cloudHeightMethod;
	private Byte[] cloudMask1km = new Byte[2];
	private Byte cloudMask1km0;
	private Byte cloudMask1km1;
	private Byte[] cloudMask5km = new Byte[2];
	private Byte cloudMask5km0;
	private Byte cloudMask5km1;
	private Double[] cloudMaskSpi = new Double[2];
	private Double cloudMaskSpi0;
	private Double cloudMaskSpi1;
	private Byte cloudMultiLayerFlag;
	private Double cloudOpticalThickness;
	private Double cloudOpticalThickness16;
	private Double cloudOpticalThickness1621;
	private Double cloudOpticalThickness1621Pcl;
	private Double cloudOpticalThickness16Pcl;
	private Double cloudOpticalThickness37;
	private Double cloudOpticalThickness37Pcl;
	private Double cloudOpticalThicknessPcl;
	private Double cloudOpticalThicknessUncertainty;
	private Double cloudOpticalThicknessUncertainty16;
	private Double cloudOpticalThicknessUncertainty1621;
	private Double cloudOpticalThicknessUncertainty37;
	private Byte cloudPhaseInfrared;
	private Byte cloudPhaseInfrared1km;
	private Byte cloudPhaseInfraredDay;
	private Byte cloudPhaseInfraredNight;
	private Byte cloudPhaseOpticalProperties;
	private Double cloudTopHeight;
	private Double cloudTopHeight1km;
	private Double cloudTopHeightNadir;
	private Double cloudTopHeightNadirDay;
	private Double cloudTopHeightNadirNight;
	private Byte cloudTopMethod1km;
	private Double cloudTopPressure;
	private Double cloudTopPressure1km;
	private Double cloudTopPressureDay;
	private Double[] cloudTopPressureFromRatios = new Double[5];
	private Double cloudTopPressureFromRatios0;
	private Double cloudTopPressureFromRatios1;
	private Double cloudTopPressureFromRatios2;
	private Double cloudTopPressureFromRatios3;
	private Double cloudTopPressureFromRatios4;
	private Double cloudTopPressureInfrared;
	private Double cloudTopPressureNadir;
	private Double cloudTopPressureNadirDay;
	private Double cloudTopPressureNadirNight;
	private Double cloudTopPressureNight;
	private Double cloudTopTemperature;
	private Double cloudTopTemperature1km;
	private Double cloudTopTemperatureDay;
	private Double cloudTopTemperatureNadir;
	private Double cloudTopTemperatureNadirDay;
	private Double cloudTopTemperatureNadirNight;
	private Double cloudTopTemperatureNight;
	private Double cloudWaterPath;
	private Double cloudWaterPath16;
	private Double cloudWaterPath1621;
	private Double cloudWaterPath1621Pcl;
	private Double cloudWaterPath16Pcl;
	private Double cloudWaterPath37;
	private Double cloudWaterPath37Pcl;
	private Double cloudWaterPathPcl;
	private Double cloudWaterPathUncertainty;
	private Double cloudWaterPathUncertainty16;
	private Double cloudWaterPathUncertainty1621;
	private Double cloudWaterPathUncertainty37;
	private Double extinctionEfficiencyIce;
	private Double extinctionEfficiencyLiq;
	private Byte irpCthConsistencyFlag1km;
	private Double irwLowCloudTemperatureFromCop;
	private Byte osTopFlag1km;
	private Byte[] qualityAssurance1km = new Byte[9];
	private Byte qualityAssurance1km0;
	private Byte qualityAssurance1km1;
	private Byte qualityAssurance1km2;
	private Byte qualityAssurance1km3;
	private Byte qualityAssurance1km4;
	private Byte qualityAssurance1km5;
	private Byte qualityAssurance1km6;
	private Byte qualityAssurance1km7;
	private Byte qualityAssurance1km8;
	private Byte[] qualityAssurance5km = new Byte[10];
	private Byte qualityAssurance5km0;
	private Byte qualityAssurance5km1;
	private Byte qualityAssurance5km2;
	private Byte qualityAssurance5km3;
	private Byte qualityAssurance5km4;
	private Byte qualityAssurance5km5;
	private Byte qualityAssurance5km6;
	private Byte qualityAssurance5km7;
	private Byte qualityAssurance5km8;
	private Byte qualityAssurance5km9;
	private Double radianceVariance;
	private Double[] retrievalFailureMetric = new Double[3];
	private Double retrievalFailureMetric0;
	private Double retrievalFailureMetric1;
	private Double retrievalFailureMetric2;
	private Double[] retrievalFailureMetric16 = new Double[3];
	private Double retrievalFailureMetric160;
	private Double retrievalFailureMetric161;
	private Double retrievalFailureMetric162;
	private Double[] retrievalFailureMetric1621 = new Double[3];
	private Double retrievalFailureMetric16210;
	private Double retrievalFailureMetric16211;
	private Double retrievalFailureMetric16212;
	private Double[] retrievalFailureMetric37 = new Double[3];
	private Double retrievalFailureMetric370;
	private Double retrievalFailureMetric371;
	private Double retrievalFailureMetric372;
	private Double scanStartTime;
	private Double sensorAzimuth;
	private Double sensorAzimuthDay;
	private Double sensorAzimuthNight;
	private Double sensorZenith;
	private Double sensorZenithDay;
	private Double sensorZenithNight;
	private Double singleScatterAlbedoIce;
	private Double singleScatterAlbedoLiq;
	private Double solarAzimuth;
	private Double solarAzimuthDay;
	private Double solarAzimuthNight;
	private Double solarZenith;
	private Double solarZenithDay;
	private Double solarZenithNight;
	private Double[] spectralCloudForcing = new Double[5];
	private Double spectralCloudForcing0;
	private Double spectralCloudForcing1;
	private Double spectralCloudForcing2;
	private Double spectralCloudForcing3;
	private Double spectralCloudForcing4;
	private Double surfacePressure;
	private Double surfaceTemperature;
	private Double surfaceTemperature1km;
	private Double tropopauseHeight;

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

	public EnumCloudType getCloudType() {
		return cloudType;
	}

	public void setCloudType(EnumCloudType cloudType) {
		this.cloudType = cloudType;
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

	public Long getPozitie1km() {
		return pozitie1km;
	}

	public void setPozitie1km(Long pozitie1km) {
		this.pozitie1km = pozitie1km;
	}

	public Double getDeltaPozitieKm() {
		return deltaPozitieKm;
	}

	public void setDeltaPozitieKm(Double deltaPozitieKm) {
		this.deltaPozitieKm = deltaPozitieKm;
	}

	/**
	 * Field:Above-cloud water vapor amount from 0.94um channel, ocean only, tau > 5. Tip:DFNT_INT16 Dim:2
	 */
	public Double getAboveCloudWaterVapor094() {
		return aboveCloudWaterVapor094;
	}

	/**
	 * Field:Above-cloud water vapor amount from 0.94um channel, ocean only, tau > 5. Tip:DFNT_INT16 Dim:2
	 */
	public void setAboveCloudWaterVapor094(Double aboveCloudWaterVapor094) {
		this.aboveCloudWaterVapor094 = aboveCloudWaterVapor094;
	}

	/**
	 * Field:Ice Asymmetry Parameter from the phase functions used to generate  the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public Double getAsymmetryParameterIce() {
		return asymmetryParameterIce;
	}

	/**
	 * Field:Ice Asymmetry Parameter from the phase functions used to generate  the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public void setAsymmetryParameterIce(Double asymmetryParameterIce) {
		this.asymmetryParameterIce = asymmetryParameterIce;
	}

	/**
	 * Field:Liquid Water Asymmetry Parameter from the phase functions used  to generate the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public Double getAsymmetryParameterLiq() {
		return asymmetryParameterLiq;
	}

	/**
	 * Field:Liquid Water Asymmetry Parameter from the phase functions used  to generate the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public void setAsymmetryParameterLiq(Double asymmetryParameterLiq) {
		this.asymmetryParameterLiq = asymmetryParameterLiq;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public Double[] getAtmCorrRefl() {
		return atmCorrRefl;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public void setAtmCorrRefl(Double[] atmCorrRefl) {
		this.atmCorrRefl = atmCorrRefl;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public Double getAtmCorrRefl0() {
		return atmCorrRefl0;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public void setAtmCorrRefl0(Double atmCorrRefl0) {
		this.atmCorrRefl0 = atmCorrRefl0;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public Double getAtmCorrRefl1() {
		return atmCorrRefl1;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public void setAtmCorrRefl1(Double atmCorrRefl1) {
		this.atmCorrRefl1 = atmCorrRefl1;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public Double getAtmCorrRefl2() {
		return atmCorrRefl2;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public void setAtmCorrRefl2(Double atmCorrRefl2) {
		this.atmCorrRefl2 = atmCorrRefl2;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public Double getAtmCorrRefl3() {
		return atmCorrRefl3;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public void setAtmCorrRefl3(Double atmCorrRefl3) {
		this.atmCorrRefl3 = atmCorrRefl3;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public Double getAtmCorrRefl4() {
		return atmCorrRefl4;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public void setAtmCorrRefl4(Double atmCorrRefl4) {
		this.atmCorrRefl4 = atmCorrRefl4;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public Double getAtmCorrRefl5() {
		return atmCorrRefl5;
	}

	/**
	 * Field:Atmospherically corrected reflectance used during cloud optical  and microphysical properties retrieval Tip:DFNT_INT16 Dim:3
	 */
	public void setAtmCorrRefl5(Double atmCorrRefl5) {
		this.atmCorrRefl5 = atmCorrRefl5;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public Double[] getBrightnessTemperature() {
		return brightnessTemperature;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public void setBrightnessTemperature(Double[] brightnessTemperature) {
		this.brightnessTemperature = brightnessTemperature;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public Double getBrightnessTemperature0() {
		return brightnessTemperature0;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public void setBrightnessTemperature0(Double brightnessTemperature0) {
		this.brightnessTemperature0 = brightnessTemperature0;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public Double getBrightnessTemperature1() {
		return brightnessTemperature1;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public void setBrightnessTemperature1(Double brightnessTemperature1) {
		this.brightnessTemperature1 = brightnessTemperature1;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public Double getBrightnessTemperature2() {
		return brightnessTemperature2;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public void setBrightnessTemperature2(Double brightnessTemperature2) {
		this.brightnessTemperature2 = brightnessTemperature2;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public Double getBrightnessTemperature3() {
		return brightnessTemperature3;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public void setBrightnessTemperature3(Double brightnessTemperature3) {
		this.brightnessTemperature3 = brightnessTemperature3;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public Double getBrightnessTemperature4() {
		return brightnessTemperature4;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public void setBrightnessTemperature4(Double brightnessTemperature4) {
		this.brightnessTemperature4 = brightnessTemperature4;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public Double getBrightnessTemperature5() {
		return brightnessTemperature5;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public void setBrightnessTemperature5(Double brightnessTemperature5) {
		this.brightnessTemperature5 = brightnessTemperature5;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public Double getBrightnessTemperature6() {
		return brightnessTemperature6;
	}

	/**
	 * Field:Observed Brightness Temperature from Cloudy Averaged Radiances in a 5x5 1-km Pixel Region Tip:DFNT_INT16 Dim:3
	 */
	public void setBrightnessTemperature6(Double brightnessTemperature6) {
		this.brightnessTemperature6 = brightnessTemperature6;
	}

	/**
	 * Field:Cirrus Reflectance Tip:DFNT_INT16 Dim:2
	 */
	public Double getCirrusReflectance() {
		return cirrusReflectance;
	}

	/**
	 * Field:Cirrus Reflectance Tip:DFNT_INT16 Dim:2
	 */
	public void setCirrusReflectance(Double cirrusReflectance) {
		this.cirrusReflectance = cirrusReflectance;
	}

	/**
	 * Field:Cirrus Reflectance Flag Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCirrusReflectanceFlag() {
		return cirrusReflectanceFlag;
	}

	/**
	 * Field:Cirrus Reflectance Flag Tip:DFNT_INT8 Dim:2
	 */
	public void setCirrusReflectanceFlag(Byte cirrusReflectanceFlag) {
		this.cirrusReflectanceFlag = cirrusReflectanceFlag;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudEffectiveEmissivity() {
		return cloudEffectiveEmissivity;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudEffectiveEmissivity(Byte cloudEffectiveEmissivity) {
		this.cloudEffectiveEmissivity = cloudEffectiveEmissivity;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval, Day Only Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudEffectiveEmissivityDay() {
		return cloudEffectiveEmissivityDay;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval, Day Only Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudEffectiveEmissivityDay(Byte cloudEffectiveEmissivityDay) {
		this.cloudEffectiveEmissivityDay = cloudEffectiveEmissivityDay;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval for Sensor Zenith (View) Angles <= 32 Degrees Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudEffectiveEmissivityNadir() {
		return cloudEffectiveEmissivityNadir;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval for Sensor Zenith (View) Angles <= 32 Degrees Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudEffectiveEmissivityNadir(
			Byte cloudEffectiveEmissivityNadir) {
		this.cloudEffectiveEmissivityNadir = cloudEffectiveEmissivityNadir;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval for Sensor Zenith (View) Angles <= 32 Degrees, Day Data Only Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudEffectiveEmissivityNadirDay() {
		return cloudEffectiveEmissivityNadirDay;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval for Sensor Zenith (View) Angles <= 32 Degrees, Day Data Only Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudEffectiveEmissivityNadirDay(
			Byte cloudEffectiveEmissivityNadirDay) {
		this.cloudEffectiveEmissivityNadirDay = cloudEffectiveEmissivityNadirDay;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval for Sensor Zenith (View) Angles <= 32 Degrees, Night Data Only Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudEffectiveEmissivityNadirNight() {
		return cloudEffectiveEmissivityNadirNight;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval for Sensor Zenith (View) Angles <= 32 Degrees, Night Data Only Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudEffectiveEmissivityNadirNight(
			Byte cloudEffectiveEmissivityNadirNight) {
		this.cloudEffectiveEmissivityNadirNight = cloudEffectiveEmissivityNadirNight;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval, Night Only Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudEffectiveEmissivityNight() {
		return cloudEffectiveEmissivityNight;
	}

	/**
	 * Field:Cloud Effective Emissivity from Cloud Top Pressure Retrieval, Night Only Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudEffectiveEmissivityNight(
			Byte cloudEffectiveEmissivityNight) {
		this.cloudEffectiveEmissivityNight = cloudEffectiveEmissivityNight;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadius() {
		return cloudEffectiveRadius;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadius(Double cloudEffectiveRadius) {
		this.cloudEffectiveRadius = cloudEffectiveRadius;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadius16() {
		return cloudEffectiveRadius16;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadius16(Double cloudEffectiveRadius16) {
		this.cloudEffectiveRadius16 = cloudEffectiveRadius16;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and band 6(1.6um)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadius1621() {
		return cloudEffectiveRadius1621;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and band 6(1.6um)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadius1621(Double cloudEffectiveRadius1621) {
		this.cloudEffectiveRadius1621 = cloudEffectiveRadius1621;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and band 6(1.6um)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadius1621Pcl() {
		return cloudEffectiveRadius1621Pcl;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and band 6(1.6um)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadius1621Pcl(
			Double cloudEffectiveRadius1621Pcl) {
		this.cloudEffectiveRadius1621Pcl = cloudEffectiveRadius1621Pcl;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadius16Pcl() {
		return cloudEffectiveRadius16Pcl;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadius16Pcl(Double cloudEffectiveRadius16Pcl) {
		this.cloudEffectiveRadius16Pcl = cloudEffectiveRadius16Pcl;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadius37() {
		return cloudEffectiveRadius37;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadius37(Double cloudEffectiveRadius37) {
		this.cloudEffectiveRadius37 = cloudEffectiveRadius37;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadius37Pcl() {
		return cloudEffectiveRadius37Pcl;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadius37Pcl(Double cloudEffectiveRadius37Pcl) {
		this.cloudEffectiveRadius37Pcl = cloudEffectiveRadius37Pcl;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadiusPcl() {
		return cloudEffectiveRadiusPcl;
	}

	/**
	 * Field:Cloud Particle Effective Radius two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadiusPcl(Double cloudEffectiveRadiusPcl) {
		this.cloudEffectiveRadiusPcl = cloudEffectiveRadiusPcl;
	}

	/**
	 * Field:Cloud Effective Particle Radius (from band 7(2.1um)) Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadiusUncertainty() {
		return cloudEffectiveRadiusUncertainty;
	}

	/**
	 * Field:Cloud Effective Particle Radius (from band 7(2.1um)) Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadiusUncertainty(
			Double cloudEffectiveRadiusUncertainty) {
		this.cloudEffectiveRadiusUncertainty = cloudEffectiveRadiusUncertainty;
	}

	/**
	 * Field:Cloud Effective Particle Radius (from band 6(1.6um) Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadiusUncertainty16() {
		return cloudEffectiveRadiusUncertainty16;
	}

	/**
	 * Field:Cloud Effective Particle Radius (from band 6(1.6um) Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadiusUncertainty16(
			Double cloudEffectiveRadiusUncertainty16) {
		this.cloudEffectiveRadiusUncertainty16 = cloudEffectiveRadiusUncertainty16;
	}

	/**
	 * Field:Cloud Effective Particle Radius Relative Uncertainty (Percent) using band 7(2.1um) and band 6(1.6um)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadiusUncertainty1621() {
		return cloudEffectiveRadiusUncertainty1621;
	}

	/**
	 * Field:Cloud Effective Particle Radius Relative Uncertainty (Percent) using band 7(2.1um) and band 6(1.6um)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadiusUncertainty1621(
			Double cloudEffectiveRadiusUncertainty1621) {
		this.cloudEffectiveRadiusUncertainty1621 = cloudEffectiveRadiusUncertainty1621;
	}

	/**
	 * Field:Cloud Effective Particle Radius  (from band 20(3.7um)) Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEffectiveRadiusUncertainty37() {
		return cloudEffectiveRadiusUncertainty37;
	}

	/**
	 * Field:Cloud Effective Particle Radius  (from band 20(3.7um)) Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEffectiveRadiusUncertainty37(
			Double cloudEffectiveRadiusUncertainty37) {
		this.cloudEffectiveRadiusUncertainty37 = cloudEffectiveRadiusUncertainty37;
	}

	/**
	 * Field:11 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEmiss111km() {
		return cloudEmiss111km;
	}

	/**
	 * Field:11 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEmiss111km(Double cloudEmiss111km) {
		this.cloudEmiss111km = cloudEmiss111km;
	}

	/**
	 * Field:12 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEmiss121km() {
		return cloudEmiss121km;
	}

	/**
	 * Field:12 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEmiss121km(Double cloudEmiss121km) {
		this.cloudEmiss121km = cloudEmiss121km;
	}

	/**
	 * Field:13.3 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEmiss131km() {
		return cloudEmiss131km;
	}

	/**
	 * Field:13.3 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEmiss131km(Double cloudEmiss131km) {
		this.cloudEmiss131km = cloudEmiss131km;
	}

	/**
	 * Field:8.5 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudEmiss851km() {
		return cloudEmiss851km;
	}

	/**
	 * Field:8.5 micron Cloud Emissivity at 1-km resolution from LEOCAT for All Clouds Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudEmiss851km(Double cloudEmiss851km) {
		this.cloudEmiss851km = cloudEmiss851km;
	}

	/**
	 * Field:Cloud Emissivity at 1-km resolution from LEOCAT Cloud Top Pressure Retrieval Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudEmissivity1km() {
		return cloudEmissivity1km;
	}

	/**
	 * Field:Cloud Emissivity at 1-km resolution from LEOCAT Cloud Top Pressure Retrieval Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudEmissivity1km(Byte cloudEmissivity1km) {
		this.cloudEmissivity1km = cloudEmissivity1km;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudFraction() {
		return cloudFraction;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudFraction(Byte cloudFraction) {
		this.cloudFraction = cloudFraction;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask, Day Only Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudFractionDay() {
		return cloudFractionDay;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask, Day Only Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudFractionDay(Byte cloudFractionDay) {
		this.cloudFractionDay = cloudFractionDay;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask for Sensor Zenith (View) Angles <= 32 Degrees Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudFractionNadir() {
		return cloudFractionNadir;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask for Sensor Zenith (View) Angles <= 32 Degrees Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudFractionNadir(Byte cloudFractionNadir) {
		this.cloudFractionNadir = cloudFractionNadir;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask for Sensor Zenith (View) Angles <= 32 Degrees, Day Data Only Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudFractionNadirDay() {
		return cloudFractionNadirDay;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask for Sensor Zenith (View) Angles <= 32 Degrees, Day Data Only Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudFractionNadirDay(Byte cloudFractionNadirDay) {
		this.cloudFractionNadirDay = cloudFractionNadirDay;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask for Sensor Zenith (View) Angles <= 32 Degrees, Night Data Only Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudFractionNadirNight() {
		return cloudFractionNadirNight;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask for Sensor Zenith (View) Angles <= 32 Degrees, Night Data Only Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudFractionNadirNight(Byte cloudFractionNadirNight) {
		this.cloudFractionNadirNight = cloudFractionNadirNight;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask, Night Only Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudFractionNight() {
		return cloudFractionNight;
	}

	/**
	 * Field:Cloud Fraction in Retrieval Region (5x5 1-km Pixels) from 1-km Cloud Mask, Night Only Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudFractionNight(Byte cloudFractionNight) {
		this.cloudFractionNight = cloudFractionNight;
	}

	/**
	 * Field:Index Indicating MODIS Bands Used for Cloud Top Pressure Retrieval Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudHeightMethod() {
		return cloudHeightMethod;
	}

	/**
	 * Field:Index Indicating MODIS Bands Used for Cloud Top Pressure Retrieval Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudHeightMethod(Byte cloudHeightMethod) {
		this.cloudHeightMethod = cloudHeightMethod;
	}

	/**
	 * Field:MODIS Cloud Mask, L2 MOD06 QA Plan Tip:DFNT_INT8 Dim:3
	 */
	public Byte[] getCloudMask1km() {
		return cloudMask1km;
	}

	/**
	 * Field:MODIS Cloud Mask, L2 MOD06 QA Plan Tip:DFNT_INT8 Dim:3
	 */
	public void setCloudMask1km(Byte[] cloudMask1km) {
		this.cloudMask1km = cloudMask1km;
	}

	/**
	 * Field:MODIS Cloud Mask, L2 MOD06 QA Plan Tip:DFNT_INT8 Dim:3
	 */
	public Byte getCloudMask1km0() {
		return cloudMask1km0;
	}

	/**
	 * Field:MODIS Cloud Mask, L2 MOD06 QA Plan Tip:DFNT_INT8 Dim:3
	 */
	public void setCloudMask1km0(Byte cloudMask1km0) {
		this.cloudMask1km0 = cloudMask1km0;
	}

	/**
	 * Field:MODIS Cloud Mask, L2 MOD06 QA Plan Tip:DFNT_INT8 Dim:3
	 */
	public Byte getCloudMask1km1() {
		return cloudMask1km1;
	}

	/**
	 * Field:MODIS Cloud Mask, L2 MOD06 QA Plan Tip:DFNT_INT8 Dim:3
	 */
	public void setCloudMask1km1(Byte cloudMask1km1) {
		this.cloudMask1km1 = cloudMask1km1;
	}

	/**
	 * Field:First Byte of MODIS Cloud Mask Plus Additional Stats for L3 (2nd Byte) Tip:DFNT_INT8 Dim:3
	 */
	public Byte[] getCloudMask5km() {
		return cloudMask5km;
	}

	/**
	 * Field:First Byte of MODIS Cloud Mask Plus Additional Stats for L3 (2nd Byte) Tip:DFNT_INT8 Dim:3
	 */
	public void setCloudMask5km(Byte[] cloudMask5km) {
		this.cloudMask5km = cloudMask5km;
	}

	/**
	 * Field:First Byte of MODIS Cloud Mask Plus Additional Stats for L3 (2nd Byte) Tip:DFNT_INT8 Dim:3
	 */
	public Byte getCloudMask5km0() {
		return cloudMask5km0;
	}

	/**
	 * Field:First Byte of MODIS Cloud Mask Plus Additional Stats for L3 (2nd Byte) Tip:DFNT_INT8 Dim:3
	 */
	public void setCloudMask5km0(Byte cloudMask5km0) {
		this.cloudMask5km0 = cloudMask5km0;
	}

	/**
	 * Field:First Byte of MODIS Cloud Mask Plus Additional Stats for L3 (2nd Byte) Tip:DFNT_INT8 Dim:3
	 */
	public Byte getCloudMask5km1() {
		return cloudMask5km1;
	}

	/**
	 * Field:First Byte of MODIS Cloud Mask Plus Additional Stats for L3 (2nd Byte) Tip:DFNT_INT8 Dim:3
	 */
	public void setCloudMask5km1(Byte cloudMask5km1) {
		this.cloudMask5km1 = cloudMask5km1;
	}

	/**
	 * Field:Dispersion in bands 1 (plane 1) and 2 (plane 2) from 250m reflectance  statistics of cloud mask Tip:DFNT_INT16 Dim:3
	 */
	public Double[] getCloudMaskSpi() {
		return cloudMaskSpi;
	}

	/**
	 * Field:Dispersion in bands 1 (plane 1) and 2 (plane 2) from 250m reflectance  statistics of cloud mask Tip:DFNT_INT16 Dim:3
	 */
	public void setCloudMaskSpi(Double[] cloudMaskSpi) {
		this.cloudMaskSpi = cloudMaskSpi;
	}

	/**
	 * Field:Dispersion in bands 1 (plane 1) and 2 (plane 2) from 250m reflectance  statistics of cloud mask Tip:DFNT_INT16 Dim:3
	 */
	public Double getCloudMaskSpi0() {
		return cloudMaskSpi0;
	}

	/**
	 * Field:Dispersion in bands 1 (plane 1) and 2 (plane 2) from 250m reflectance  statistics of cloud mask Tip:DFNT_INT16 Dim:3
	 */
	public void setCloudMaskSpi0(Double cloudMaskSpi0) {
		this.cloudMaskSpi0 = cloudMaskSpi0;
	}

	/**
	 * Field:Dispersion in bands 1 (plane 1) and 2 (plane 2) from 250m reflectance  statistics of cloud mask Tip:DFNT_INT16 Dim:3
	 */
	public Double getCloudMaskSpi1() {
		return cloudMaskSpi1;
	}

	/**
	 * Field:Dispersion in bands 1 (plane 1) and 2 (plane 2) from 250m reflectance  statistics of cloud mask Tip:DFNT_INT16 Dim:3
	 */
	public void setCloudMaskSpi1(Double cloudMaskSpi1) {
		this.cloudMaskSpi1 = cloudMaskSpi1;
	}

	/**
	 * Field:Cloud Multi Layer Identification From MODIS Shortwave Observations Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudMultiLayerFlag() {
		return cloudMultiLayerFlag;
	}

	/**
	 * Field:Cloud Multi Layer Identification From MODIS Shortwave Observations Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudMultiLayerFlag(Byte cloudMultiLayerFlag) {
		this.cloudMultiLayerFlag = cloudMultiLayerFlag;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThickness() {
		return cloudOpticalThickness;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThickness(Double cloudOpticalThickness) {
		this.cloudOpticalThickness = cloudOpticalThickness;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThickness16() {
		return cloudOpticalThickness16;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThickness16(Double cloudOpticalThickness16) {
		this.cloudOpticalThickness16 = cloudOpticalThickness16;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and band 6(1.6um)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThickness1621() {
		return cloudOpticalThickness1621;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and band 6(1.6um)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThickness1621(Double cloudOpticalThickness1621) {
		this.cloudOpticalThickness1621 = cloudOpticalThickness1621;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and band 6(1.6um)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThickness1621Pcl() {
		return cloudOpticalThickness1621Pcl;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and band 6(1.6um)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThickness1621Pcl(
			Double cloudOpticalThickness1621Pcl) {
		this.cloudOpticalThickness1621Pcl = cloudOpticalThickness1621Pcl;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThickness16Pcl() {
		return cloudOpticalThickness16Pcl;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um)  (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThickness16Pcl(Double cloudOpticalThickness16Pcl) {
		this.cloudOpticalThickness16Pcl = cloudOpticalThickness16Pcl;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThickness37() {
		return cloudOpticalThickness37;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThickness37(Double cloudOpticalThickness37) {
		this.cloudOpticalThickness37 = cloudOpticalThickness37;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThickness37Pcl() {
		return cloudOpticalThickness37Pcl;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThickness37Pcl(Double cloudOpticalThickness37Pcl) {
		this.cloudOpticalThickness37Pcl = cloudOpticalThickness37Pcl;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThicknessPcl() {
		return cloudOpticalThicknessPcl;
	}

	/**
	 * Field:Cloud Optical Thickness two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThicknessPcl(Double cloudOpticalThicknessPcl) {
		this.cloudOpticalThicknessPcl = cloudOpticalThicknessPcl;
	}

	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Optical_Thickness and Cloud_Effective_Radius results Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThicknessUncertainty() {
		return cloudOpticalThicknessUncertainty;
	}

	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Optical_Thickness and Cloud_Effective_Radius results Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThicknessUncertainty(
			Double cloudOpticalThicknessUncertainty) {
		this.cloudOpticalThicknessUncertainty = cloudOpticalThicknessUncertainty;
	}

	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Optical_Thickness_16 and Cloud_Effective_Radius_16 results Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThicknessUncertainty16() {
		return cloudOpticalThicknessUncertainty16;
	}

	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Optical_Thickness_16 and Cloud_Effective_Radius_16 results Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThicknessUncertainty16(
			Double cloudOpticalThicknessUncertainty16) {
		this.cloudOpticalThicknessUncertainty16 = cloudOpticalThicknessUncertainty16;
	}

	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty  (Percent) using band 7(2.1um) and band 6(1.6um)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThicknessUncertainty1621() {
		return cloudOpticalThicknessUncertainty1621;
	}

	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty  (Percent) using band 7(2.1um) and band 6(1.6um)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThicknessUncertainty1621(
			Double cloudOpticalThicknessUncertainty1621) {
		this.cloudOpticalThicknessUncertainty1621 = cloudOpticalThicknessUncertainty1621;
	}

	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Optical_Thickness_37 and Cloud_Effective_Radius_37 results Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudOpticalThicknessUncertainty37() {
		return cloudOpticalThicknessUncertainty37;
	}

	/**
	 * Field:Cloud Optical Thickness Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Optical_Thickness_37 and Cloud_Effective_Radius_37 results Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudOpticalThicknessUncertainty37(
			Double cloudOpticalThicknessUncertainty37) {
		this.cloudOpticalThicknessUncertainty37 = cloudOpticalThicknessUncertainty37;
	}

	/**
	 * Field:Cloud Phase from 8.5 and 11 um Bands Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudPhaseInfrared() {
		return cloudPhaseInfrared;
	}

	/**
	 * Field:Cloud Phase from 8.5 and 11 um Bands Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudPhaseInfrared(Byte cloudPhaseInfrared) {
		this.cloudPhaseInfrared = cloudPhaseInfrared;
	}

	/**
	 * Field:Cloud Phase at 1-km resolution from 8.5- 11 um BTDs and cloud emissivity ratios (12/11, 8.5/11, and 7.2/11 um) Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudPhaseInfrared1km() {
		return cloudPhaseInfrared1km;
	}

	/**
	 * Field:Cloud Phase at 1-km resolution from 8.5- 11 um BTDs and cloud emissivity ratios (12/11, 8.5/11, and 7.2/11 um) Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudPhaseInfrared1km(Byte cloudPhaseInfrared1km) {
		this.cloudPhaseInfrared1km = cloudPhaseInfrared1km;
	}

	/**
	 * Field:Cloud Phase from 8.5 and 11 um Bands, Day Only Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudPhaseInfraredDay() {
		return cloudPhaseInfraredDay;
	}

	/**
	 * Field:Cloud Phase from 8.5 and 11 um Bands, Day Only Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudPhaseInfraredDay(Byte cloudPhaseInfraredDay) {
		this.cloudPhaseInfraredDay = cloudPhaseInfraredDay;
	}

	/**
	 * Field:Cloud Phase from 8.5 and 11 um Bands, Night Only Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudPhaseInfraredNight() {
		return cloudPhaseInfraredNight;
	}

	/**
	 * Field:Cloud Phase from 8.5 and 11 um Bands, Night Only Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudPhaseInfraredNight(Byte cloudPhaseInfraredNight) {
		this.cloudPhaseInfraredNight = cloudPhaseInfraredNight;
	}

	/**
	 * Field:Cloud Phase Determination Used in Optical Thickness/Effective Radius Retrieval Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudPhaseOpticalProperties() {
		return cloudPhaseOpticalProperties;
	}

	/**
	 * Field:Cloud Phase Determination Used in Optical Thickness/Effective Radius Retrieval Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudPhaseOpticalProperties(Byte cloudPhaseOpticalProperties) {
		this.cloudPhaseOpticalProperties = cloudPhaseOpticalProperties;
	}

	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopHeight() {
		return cloudTopHeight;
	}

	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopHeight(Double cloudTopHeight) {
		this.cloudTopHeight = cloudTopHeight;
	}

	/**
	 * Field:Cloud Top Height at 1-km resolution from LEOCAT, Geopotential Height at Retrieved Cloud Top Pressure Level rounded to nearest 50 m Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopHeight1km() {
		return cloudTopHeight1km;
	}

	/**
	 * Field:Cloud Top Height at 1-km resolution from LEOCAT, Geopotential Height at Retrieved Cloud Top Pressure Level rounded to nearest 50 m Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopHeight1km(Double cloudTopHeight1km) {
		this.cloudTopHeight1km = cloudTopHeight1km;
	}

	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles  <=32 Degrees (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopHeightNadir() {
		return cloudTopHeightNadir;
	}

	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles  <=32 Degrees (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopHeightNadir(Double cloudTopHeightNadir) {
		this.cloudTopHeightNadir = cloudTopHeightNadir;
	}

	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles  <=32 Degrees, Day Data Only (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopHeightNadirDay() {
		return cloudTopHeightNadirDay;
	}

	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles  <=32 Degrees, Day Data Only (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopHeightNadirDay(Double cloudTopHeightNadirDay) {
		this.cloudTopHeightNadirDay = cloudTopHeightNadirDay;
	}

	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles  <=32 Degrees, Night Data Only (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopHeightNadirNight() {
		return cloudTopHeightNadirNight;
	}

	/**
	 * Field:Geopotential Height at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles  <=32 Degrees, Night Data Only (rounded to nearest 50 m) Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopHeightNadirNight(Double cloudTopHeightNadirNight) {
		this.cloudTopHeightNadirNight = cloudTopHeightNadirNight;
	}

	/**
	 * Field:Index Indicating the MODIS Band(s) Used to Produce the Cloud Top Pressure Result Tip:DFNT_INT8 Dim:2
	 */
	public Byte getCloudTopMethod1km() {
		return cloudTopMethod1km;
	}

	/**
	 * Field:Index Indicating the MODIS Band(s) Used to Produce the Cloud Top Pressure Result Tip:DFNT_INT8 Dim:2
	 */
	public void setCloudTopMethod1km(Byte cloudTopMethod1km) {
		this.cloudTopMethod1km = cloudTopMethod1km;
	}

	/**
	 * Field:Cloud Top Pressure Level (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopPressure() {
		return cloudTopPressure;
	}

	/**
	 * Field:Cloud Top Pressure Level (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopPressure(Double cloudTopPressure) {
		this.cloudTopPressure = cloudTopPressure;
	}

	/**
	 * Field:Cloud Top Pressure at 1-km resolution from LEOCAT, Cloud Top Pressure Level rounded to nearest 5 mb Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopPressure1km() {
		return cloudTopPressure1km;
	}

	/**
	 * Field:Cloud Top Pressure at 1-km resolution from LEOCAT, Cloud Top Pressure Level rounded to nearest 5 mb Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopPressure1km(Double cloudTopPressure1km) {
		this.cloudTopPressure1km = cloudTopPressure1km;
	}

	/**
	 * Field:Cloud Top Pressure Level, Day Only (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopPressureDay() {
		return cloudTopPressureDay;
	}

	/**
	 * Field:Cloud Top Pressure Level, Day Only (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopPressureDay(Double cloudTopPressureDay) {
		this.cloudTopPressureDay = cloudTopPressureDay;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public Double[] getCloudTopPressureFromRatios() {
		return cloudTopPressureFromRatios;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public void setCloudTopPressureFromRatios(
			Double[] cloudTopPressureFromRatios) {
		this.cloudTopPressureFromRatios = cloudTopPressureFromRatios;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public Double getCloudTopPressureFromRatios0() {
		return cloudTopPressureFromRatios0;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public void setCloudTopPressureFromRatios0(
			Double cloudTopPressureFromRatios0) {
		this.cloudTopPressureFromRatios0 = cloudTopPressureFromRatios0;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public Double getCloudTopPressureFromRatios1() {
		return cloudTopPressureFromRatios1;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public void setCloudTopPressureFromRatios1(
			Double cloudTopPressureFromRatios1) {
		this.cloudTopPressureFromRatios1 = cloudTopPressureFromRatios1;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public Double getCloudTopPressureFromRatios2() {
		return cloudTopPressureFromRatios2;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public void setCloudTopPressureFromRatios2(
			Double cloudTopPressureFromRatios2) {
		this.cloudTopPressureFromRatios2 = cloudTopPressureFromRatios2;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public Double getCloudTopPressureFromRatios3() {
		return cloudTopPressureFromRatios3;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public void setCloudTopPressureFromRatios3(
			Double cloudTopPressureFromRatios3) {
		this.cloudTopPressureFromRatios3 = cloudTopPressureFromRatios3;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public Double getCloudTopPressureFromRatios4() {
		return cloudTopPressureFromRatios4;
	}

	/**
	 * Field:Cloud Top Pressure Levels from Ratios of Bands 36/35, 35/34, 35/33, 34/33 from the CO2-slicing Algorithm Tip:DFNT_INT16 Dim:3
	 */
	public void setCloudTopPressureFromRatios4(
			Double cloudTopPressureFromRatios4) {
		this.cloudTopPressureFromRatios4 = cloudTopPressureFromRatios4;
	}

	/**
	 * Field:Cloud Top Pressure from IR Window Retrieval Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopPressureInfrared() {
		return cloudTopPressureInfrared;
	}

	/**
	 * Field:Cloud Top Pressure from IR Window Retrieval Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopPressureInfrared(Double cloudTopPressureInfrared) {
		this.cloudTopPressureInfrared = cloudTopPressureInfrared;
	}

	/**
	 * Field:Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopPressureNadir() {
		return cloudTopPressureNadir;
	}

	/**
	 * Field:Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopPressureNadir(Double cloudTopPressureNadir) {
		this.cloudTopPressureNadir = cloudTopPressureNadir;
	}

	/**
	 * Field:Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees (rounded to nearest 5 mb), Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopPressureNadirDay() {
		return cloudTopPressureNadirDay;
	}

	/**
	 * Field:Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees (rounded to nearest 5 mb), Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopPressureNadirDay(Double cloudTopPressureNadirDay) {
		this.cloudTopPressureNadirDay = cloudTopPressureNadirDay;
	}

	/**
	 * Field:Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees (rounded to nearest 5 mb), Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopPressureNadirNight() {
		return cloudTopPressureNadirNight;
	}

	/**
	 * Field:Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees (rounded to nearest 5 mb), Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopPressureNadirNight(Double cloudTopPressureNadirNight) {
		this.cloudTopPressureNadirNight = cloudTopPressureNadirNight;
	}

	/**
	 * Field:Cloud Top Pressure Level, Night Data Only (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopPressureNight() {
		return cloudTopPressureNight;
	}

	/**
	 * Field:Cloud Top Pressure Level, Night Data Only (rounded to nearest 5 mb) Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopPressureNight(Double cloudTopPressureNight) {
		this.cloudTopPressureNight = cloudTopPressureNight;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopTemperature() {
		return cloudTopTemperature;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopTemperature(Double cloudTopTemperature) {
		this.cloudTopTemperature = cloudTopTemperature;
	}

	/**
	 * Field:Cloud Top Temperature at 1-km resolution from LEOCAT, Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopTemperature1km() {
		return cloudTopTemperature1km;
	}

	/**
	 * Field:Cloud Top Temperature at 1-km resolution from LEOCAT, Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopTemperature1km(Double cloudTopTemperature1km) {
		this.cloudTopTemperature1km = cloudTopTemperature1km;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level, Day Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopTemperatureDay() {
		return cloudTopTemperatureDay;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level, Day Only Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopTemperatureDay(Double cloudTopTemperatureDay) {
		this.cloudTopTemperatureDay = cloudTopTemperatureDay;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopTemperatureNadir() {
		return cloudTopTemperatureNadir;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopTemperatureNadir(Double cloudTopTemperatureNadir) {
		this.cloudTopTemperatureNadir = cloudTopTemperatureNadir;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopTemperatureNadirDay() {
		return cloudTopTemperatureNadirDay;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopTemperatureNadirDay(
			Double cloudTopTemperatureNadirDay) {
		this.cloudTopTemperatureNadirDay = cloudTopTemperatureNadirDay;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopTemperatureNadirNight() {
		return cloudTopTemperatureNadirNight;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level for Sensor Zenith (View) Angles <= 32 Degrees, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopTemperatureNadirNight(
			Double cloudTopTemperatureNadirNight) {
		this.cloudTopTemperatureNadirNight = cloudTopTemperatureNadirNight;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level, Night Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudTopTemperatureNight() {
		return cloudTopTemperatureNight;
	}

	/**
	 * Field:Temperature from Ancillary Data at Retrieved Cloud Top Pressure Level, Night Only Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudTopTemperatureNight(Double cloudTopTemperatureNight) {
		this.cloudTopTemperatureNight = cloudTopTemperatureNight;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPath() {
		return cloudWaterPath;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPath(Double cloudWaterPath) {
		this.cloudWaterPath = cloudWaterPath;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPath16() {
		return cloudWaterPath16;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPath16(Double cloudWaterPath16) {
		this.cloudWaterPath16 = cloudWaterPath16;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and band 6(1.6um)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPath1621() {
		return cloudWaterPath1621;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and band 6(1.6um)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPath1621(Double cloudWaterPath1621) {
		this.cloudWaterPath1621 = cloudWaterPath1621;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and band 6(1.6um)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPath1621Pcl() {
		return cloudWaterPath1621Pcl;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and band 6(1.6um)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPath1621Pcl(Double cloudWaterPath1621Pcl) {
		this.cloudWaterPath1621Pcl = cloudWaterPath1621Pcl;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPath16Pcl() {
		return cloudWaterPath16Pcl;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 6(1.6um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPath16Pcl(Double cloudWaterPath16Pcl) {
		this.cloudWaterPath16Pcl = cloudWaterPath16Pcl;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPath37() {
		return cloudWaterPath37;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from best points: not failed in any way, not marked for clear sky restoral Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPath37(Double cloudWaterPath37) {
		this.cloudWaterPath37 = cloudWaterPath37;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPath37Pcl() {
		return cloudWaterPath37Pcl;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 20(3.7um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPath37Pcl(Double cloudWaterPath37Pcl) {
		this.cloudWaterPath37Pcl = cloudWaterPath37Pcl;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPathPcl() {
		return cloudWaterPathPcl;
	}

	/**
	 * Field:Column Water Path two-channel retrieval using band 7(2.1um) and either band 1(0.65um), 2(0.86um), or 5(1.2um) (specified in Quality_Assurance_1km)from points identified as either partly cloudy from 250m cloud mask test or 1km cloud edges Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPathPcl(Double cloudWaterPathPcl) {
		this.cloudWaterPathPcl = cloudWaterPathPcl;
	}

	/**
	 * Field:Cloud Water Path Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Water_Path result Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPathUncertainty() {
		return cloudWaterPathUncertainty;
	}

	/**
	 * Field:Cloud Water Path Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m based on the Cloud_Water_Path result Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPathUncertainty(Double cloudWaterPathUncertainty) {
		this.cloudWaterPathUncertainty = cloudWaterPathUncertainty;
	}

	/**
	 * Field:Cloud Water Path Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m using the VNSWIR-1.6um retrieval Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPathUncertainty16() {
		return cloudWaterPathUncertainty16;
	}

	/**
	 * Field:Cloud Water Path Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m using the VNSWIR-1.6um retrieval Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPathUncertainty16(
			Double cloudWaterPathUncertainty16) {
		this.cloudWaterPathUncertainty16 = cloudWaterPathUncertainty16;
	}

	/**
	 * Field:Cloud Water Path Relative Uncertainty  (Percent) using band 7(2.1um) and band 6(1.6um)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPathUncertainty1621() {
		return cloudWaterPathUncertainty1621;
	}

	/**
	 * Field:Cloud Water Path Relative Uncertainty  (Percent) using band 7(2.1um) and band 6(1.6um)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPathUncertainty1621(
			Double cloudWaterPathUncertainty1621) {
		this.cloudWaterPathUncertainty1621 = cloudWaterPathUncertainty1621;
	}

	/**
	 * Field:Cloud Water Path Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m using the VNSWIR-3.7um retrieval Tip:DFNT_INT16 Dim:2
	 */
	public Double getCloudWaterPathUncertainty37() {
		return cloudWaterPathUncertainty37;
	}

	/**
	 * Field:Cloud Water Path Relative Uncertainty (Percent)from both best points and points identified as cloud edge at 1km resolution or partly cloudy at 250m using the VNSWIR-3.7um retrieval Tip:DFNT_INT16 Dim:2
	 */
	public void setCloudWaterPathUncertainty37(
			Double cloudWaterPathUncertainty37) {
		this.cloudWaterPathUncertainty37 = cloudWaterPathUncertainty37;
	}

	/**
	 * Field:Ice Extinction Efficiency from the phase functions used to generate  the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public Double getExtinctionEfficiencyIce() {
		return extinctionEfficiencyIce;
	}

	/**
	 * Field:Ice Extinction Efficiency from the phase functions used to generate  the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public void setExtinctionEfficiencyIce(Double extinctionEfficiencyIce) {
		this.extinctionEfficiencyIce = extinctionEfficiencyIce;
	}

	/**
	 * Field:Liquid Water CE from the phase functions used to generate the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public Double getExtinctionEfficiencyLiq() {
		return extinctionEfficiencyLiq;
	}

	/**
	 * Field:Liquid Water CE from the phase functions used to generate the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public void setExtinctionEfficiencyLiq(Double extinctionEfficiencyLiq) {
		this.extinctionEfficiencyLiq = extinctionEfficiencyLiq;
	}

	/**
	 * Field:Indicates Cloud_Phase_Infrared_1km results changed to ice from water when cloud_top_method_1km reports valid band 36/35 CO2-slicing result (1=change) Tip:DFNT_INT8 Dim:2
	 */
	public Byte getIrpCthConsistencyFlag1km() {
		return irpCthConsistencyFlag1km;
	}

	/**
	 * Field:Indicates Cloud_Phase_Infrared_1km results changed to ice from water when cloud_top_method_1km reports valid band 36/35 CO2-slicing result (1=change) Tip:DFNT_INT8 Dim:2
	 */
	public void setIrpCthConsistencyFlag1km(Byte irpCthConsistencyFlag1km) {
		this.irpCthConsistencyFlag1km = irpCthConsistencyFlag1km;
	}

	/**
	 * Field:Low Cloud Temperature from IR Window retrieval  using cloud emissivity based on cloud optical thickness Tip:DFNT_INT16 Dim:2
	 */
	public Double getIrwLowCloudTemperatureFromCop() {
		return irwLowCloudTemperatureFromCop;
	}

	/**
	 * Field:Low Cloud Temperature from IR Window retrieval  using cloud emissivity based on cloud optical thickness Tip:DFNT_INT16 Dim:2
	 */
	public void setIrwLowCloudTemperatureFromCop(
			Double irwLowCloudTemperatureFromCop) {
		this.irwLowCloudTemperatureFromCop = irwLowCloudTemperatureFromCop;
	}

	/**
	 * Field:Upper Tropospheric/Lower Stratospheric (UTLS) Cloud Flag at 1-km  resolution - valid from -50 to +50 Degrees Latitude Tip:DFNT_INT8 Dim:2
	 */
	public Byte getOsTopFlag1km() {
		return osTopFlag1km;
	}

	/**
	 * Field:Upper Tropospheric/Lower Stratospheric (UTLS) Cloud Flag at 1-km  resolution - valid from -50 to +50 Degrees Latitude Tip:DFNT_INT8 Dim:2
	 */
	public void setOsTopFlag1km(Byte osTopFlag1km) {
		this.osTopFlag1km = osTopFlag1km;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte[] getQualityAssurance1km() {
		return qualityAssurance1km;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance1km(Byte[] qualityAssurance1km) {
		this.qualityAssurance1km = qualityAssurance1km;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance1km0() {
		return qualityAssurance1km0;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance1km0(Byte qualityAssurance1km0) {
		this.qualityAssurance1km0 = qualityAssurance1km0;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance1km1() {
		return qualityAssurance1km1;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance1km1(Byte qualityAssurance1km1) {
		this.qualityAssurance1km1 = qualityAssurance1km1;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance1km2() {
		return qualityAssurance1km2;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance1km2(Byte qualityAssurance1km2) {
		this.qualityAssurance1km2 = qualityAssurance1km2;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance1km3() {
		return qualityAssurance1km3;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance1km3(Byte qualityAssurance1km3) {
		this.qualityAssurance1km3 = qualityAssurance1km3;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance1km4() {
		return qualityAssurance1km4;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance1km4(Byte qualityAssurance1km4) {
		this.qualityAssurance1km4 = qualityAssurance1km4;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance1km5() {
		return qualityAssurance1km5;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance1km5(Byte qualityAssurance1km5) {
		this.qualityAssurance1km5 = qualityAssurance1km5;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance1km6() {
		return qualityAssurance1km6;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance1km6(Byte qualityAssurance1km6) {
		this.qualityAssurance1km6 = qualityAssurance1km6;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance1km7() {
		return qualityAssurance1km7;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance1km7(Byte qualityAssurance1km7) {
		this.qualityAssurance1km7 = qualityAssurance1km7;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance1km8() {
		return qualityAssurance1km8;
	}

	/**
	 * Field:Quality Assurance at 1x1 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance1km8(Byte qualityAssurance1km8) {
		this.qualityAssurance1km8 = qualityAssurance1km8;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte[] getQualityAssurance5km() {
		return qualityAssurance5km;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance5km(Byte[] qualityAssurance5km) {
		this.qualityAssurance5km = qualityAssurance5km;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance5km0() {
		return qualityAssurance5km0;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance5km0(Byte qualityAssurance5km0) {
		this.qualityAssurance5km0 = qualityAssurance5km0;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance5km1() {
		return qualityAssurance5km1;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance5km1(Byte qualityAssurance5km1) {
		this.qualityAssurance5km1 = qualityAssurance5km1;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance5km2() {
		return qualityAssurance5km2;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance5km2(Byte qualityAssurance5km2) {
		this.qualityAssurance5km2 = qualityAssurance5km2;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance5km3() {
		return qualityAssurance5km3;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance5km3(Byte qualityAssurance5km3) {
		this.qualityAssurance5km3 = qualityAssurance5km3;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance5km4() {
		return qualityAssurance5km4;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance5km4(Byte qualityAssurance5km4) {
		this.qualityAssurance5km4 = qualityAssurance5km4;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance5km5() {
		return qualityAssurance5km5;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance5km5(Byte qualityAssurance5km5) {
		this.qualityAssurance5km5 = qualityAssurance5km5;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance5km6() {
		return qualityAssurance5km6;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance5km6(Byte qualityAssurance5km6) {
		this.qualityAssurance5km6 = qualityAssurance5km6;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance5km7() {
		return qualityAssurance5km7;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance5km7(Byte qualityAssurance5km7) {
		this.qualityAssurance5km7 = qualityAssurance5km7;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance5km8() {
		return qualityAssurance5km8;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance5km8(Byte qualityAssurance5km8) {
		this.qualityAssurance5km8 = qualityAssurance5km8;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public Byte getQualityAssurance5km9() {
		return qualityAssurance5km9;
	}

	/**
	 * Field:Quality Assurance at 5x5 Resolution Tip:DFNT_INT8 Dim:3
	 */
	public void setQualityAssurance5km9(Byte qualityAssurance5km9) {
		this.qualityAssurance5km9 = qualityAssurance5km9;
	}

	/**
	 * Field:Band 31 Radiance Standard Deviation Tip:DFNT_INT16 Dim:2
	 */
	public Double getRadianceVariance() {
		return radianceVariance;
	}

	/**
	 * Field:Band 31 Radiance Standard Deviation Tip:DFNT_INT16 Dim:2
	 */
	public void setRadianceVariance(Double radianceVariance) {
		this.radianceVariance = radianceVariance;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 7 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double[] getRetrievalFailureMetric() {
		return retrievalFailureMetric;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 7 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric(Double[] retrievalFailureMetric) {
		this.retrievalFailureMetric = retrievalFailureMetric;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 7 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric0() {
		return retrievalFailureMetric0;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 7 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric0(Double retrievalFailureMetric0) {
		this.retrievalFailureMetric0 = retrievalFailureMetric0;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 7 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric1() {
		return retrievalFailureMetric1;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 7 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric1(Double retrievalFailureMetric1) {
		this.retrievalFailureMetric1 = retrievalFailureMetric1;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 7 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric2() {
		return retrievalFailureMetric2;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 7 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric2(Double retrievalFailureMetric2) {
		this.retrievalFailureMetric2 = retrievalFailureMetric2;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double[] getRetrievalFailureMetric16() {
		return retrievalFailureMetric16;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric16(Double[] retrievalFailureMetric16) {
		this.retrievalFailureMetric16 = retrievalFailureMetric16;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric160() {
		return retrievalFailureMetric160;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric160(Double retrievalFailureMetric160) {
		this.retrievalFailureMetric160 = retrievalFailureMetric160;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric161() {
		return retrievalFailureMetric161;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric161(Double retrievalFailureMetric161) {
		this.retrievalFailureMetric161 = retrievalFailureMetric161;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric162() {
		return retrievalFailureMetric162;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric162(Double retrievalFailureMetric162) {
		this.retrievalFailureMetric162 = retrievalFailureMetric162;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and band 7 Tip:DFNT_INT16 Dim:3
	 */
	public Double[] getRetrievalFailureMetric1621() {
		return retrievalFailureMetric1621;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and band 7 Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric1621(
			Double[] retrievalFailureMetric1621) {
		this.retrievalFailureMetric1621 = retrievalFailureMetric1621;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and band 7 Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric16210() {
		return retrievalFailureMetric16210;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and band 7 Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric16210(
			Double retrievalFailureMetric16210) {
		this.retrievalFailureMetric16210 = retrievalFailureMetric16210;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and band 7 Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric16211() {
		return retrievalFailureMetric16211;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and band 7 Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric16211(
			Double retrievalFailureMetric16211) {
		this.retrievalFailureMetric16211 = retrievalFailureMetric16211;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and band 7 Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric16212() {
		return retrievalFailureMetric16212;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 6 and band 7 Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric16212(
			Double retrievalFailureMetric16212) {
		this.retrievalFailureMetric16212 = retrievalFailureMetric16212;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 20 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double[] getRetrievalFailureMetric37() {
		return retrievalFailureMetric37;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 20 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric37(Double[] retrievalFailureMetric37) {
		this.retrievalFailureMetric37 = retrievalFailureMetric37;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 20 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric370() {
		return retrievalFailureMetric370;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 20 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric370(Double retrievalFailureMetric370) {
		this.retrievalFailureMetric370 = retrievalFailureMetric370;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 20 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric371() {
		return retrievalFailureMetric371;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 20 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric371(Double retrievalFailureMetric371) {
		this.retrievalFailureMetric371 = retrievalFailureMetric371;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 20 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public Double getRetrievalFailureMetric372() {
		return retrievalFailureMetric372;
	}

	/**
	 * Field:Retrievals and other information for points that failed to retrievevia standard solution logic for retrieval using band 20 and either band 1, 2, or 5 (specified in Quality_Assurance_1km) Tip:DFNT_INT16 Dim:3
	 */
	public void setRetrievalFailureMetric372(Double retrievalFailureMetric372) {
		this.retrievalFailureMetric372 = retrievalFailureMetric372;
	}

	/**
	 * Field:TAI time at start of scan replicated across the swath Tip:DFNT_FLOAT32 Dim:2
	 */
	public Double getScanStartTime() {
		return scanStartTime;
	}

	/**
	 * Field:TAI time at start of scan replicated across the swath Tip:DFNT_FLOAT32 Dim:2
	 */
	public void setScanStartTime(Double scanStartTime) {
		this.scanStartTime = scanStartTime;
	}

	/**
	 * Field:Sensor Azimuth Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
	 */
	public Double getSensorAzimuth() {
		return sensorAzimuth;
	}

	/**
	 * Field:Sensor Azimuth Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
	 */
	public void setSensorAzimuth(Double sensorAzimuth) {
		this.sensorAzimuth = sensorAzimuth;
	}

	/**
	 * Field:Sensor Azimuth Angle, Cell to Sensor, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getSensorAzimuthDay() {
		return sensorAzimuthDay;
	}

	/**
	 * Field:Sensor Azimuth Angle, Cell to Sensor, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setSensorAzimuthDay(Double sensorAzimuthDay) {
		this.sensorAzimuthDay = sensorAzimuthDay;
	}

	/**
	 * Field:Sensor Azimuth Angle, Cell to Sensor, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getSensorAzimuthNight() {
		return sensorAzimuthNight;
	}

	/**
	 * Field:Sensor Azimuth Angle, Cell to Sensor, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setSensorAzimuthNight(Double sensorAzimuthNight) {
		this.sensorAzimuthNight = sensorAzimuthNight;
	}

	/**
	 * Field:Sensor Zenith Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
	 */
	public Double getSensorZenith() {
		return sensorZenith;
	}

	/**
	 * Field:Sensor Zenith Angle, Cell to Sensor Tip:DFNT_INT16 Dim:2
	 */
	public void setSensorZenith(Double sensorZenith) {
		this.sensorZenith = sensorZenith;
	}

	/**
	 * Field:Sensor Zenith Angle, Cell to Sensor, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getSensorZenithDay() {
		return sensorZenithDay;
	}

	/**
	 * Field:Sensor Zenith Angle, Cell to Sensor, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setSensorZenithDay(Double sensorZenithDay) {
		this.sensorZenithDay = sensorZenithDay;
	}

	/**
	 * Field:Sensor Zenith Angle, Cell to Sensor, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getSensorZenithNight() {
		return sensorZenithNight;
	}

	/**
	 * Field:Sensor Zenith Angle, Cell to Sensor, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setSensorZenithNight(Double sensorZenithNight) {
		this.sensorZenithNight = sensorZenithNight;
	}

	/**
	 * Field:Ice single scatter albedo from the phase functions used to generate  the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public Double getSingleScatterAlbedoIce() {
		return singleScatterAlbedoIce;
	}

	/**
	 * Field:Ice single scatter albedo from the phase functions used to generate  the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public void setSingleScatterAlbedoIce(Double singleScatterAlbedoIce) {
		this.singleScatterAlbedoIce = singleScatterAlbedoIce;
	}

	/**
	 * Field:Liquid Water single scatter albedo from the phase functions used  to generate the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public Double getSingleScatterAlbedoLiq() {
		return singleScatterAlbedoLiq;
	}

	/**
	 * Field:Liquid Water single scatter albedo from the phase functions used  to generate the forward lookup tables Tip:DFNT_FLOAT32 Dim:2
	 */
	public void setSingleScatterAlbedoLiq(Double singleScatterAlbedoLiq) {
		this.singleScatterAlbedoLiq = singleScatterAlbedoLiq;
	}

	/**
	 * Field:Solar Azimuth Angle, Cell to Sun Tip:DFNT_INT16 Dim:2
	 */
	public Double getSolarAzimuth() {
		return solarAzimuth;
	}

	/**
	 * Field:Solar Azimuth Angle, Cell to Sun Tip:DFNT_INT16 Dim:2
	 */
	public void setSolarAzimuth(Double solarAzimuth) {
		this.solarAzimuth = solarAzimuth;
	}

	/**
	 * Field:Solar Azimuth Angle, Cell to Sun, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getSolarAzimuthDay() {
		return solarAzimuthDay;
	}

	/**
	 * Field:Solar Azimuth Angle, Cell to Sun, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setSolarAzimuthDay(Double solarAzimuthDay) {
		this.solarAzimuthDay = solarAzimuthDay;
	}

	/**
	 * Field:Solar Azimuth Angle, Cell to Sun, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getSolarAzimuthNight() {
		return solarAzimuthNight;
	}

	/**
	 * Field:Solar Azimuth Angle, Cell to Sun, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setSolarAzimuthNight(Double solarAzimuthNight) {
		this.solarAzimuthNight = solarAzimuthNight;
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
	 * Field:Solar Zenith Angle, Cell to Sun, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getSolarZenithDay() {
		return solarZenithDay;
	}

	/**
	 * Field:Solar Zenith Angle, Cell to Sun, Day Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setSolarZenithDay(Double solarZenithDay) {
		this.solarZenithDay = solarZenithDay;
	}

	/**
	 * Field:Solar Zenith Angle, Cell to Sun, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public Double getSolarZenithNight() {
		return solarZenithNight;
	}

	/**
	 * Field:Solar Zenith Angle, Cell to Sun, Night Data Only Tip:DFNT_INT16 Dim:2
	 */
	public void setSolarZenithNight(Double solarZenithNight) {
		this.solarZenithNight = solarZenithNight;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public Double[] getSpectralCloudForcing() {
		return spectralCloudForcing;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public void setSpectralCloudForcing(Double[] spectralCloudForcing) {
		this.spectralCloudForcing = spectralCloudForcing;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public Double getSpectralCloudForcing0() {
		return spectralCloudForcing0;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public void setSpectralCloudForcing0(Double spectralCloudForcing0) {
		this.spectralCloudForcing0 = spectralCloudForcing0;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public Double getSpectralCloudForcing1() {
		return spectralCloudForcing1;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public void setSpectralCloudForcing1(Double spectralCloudForcing1) {
		this.spectralCloudForcing1 = spectralCloudForcing1;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public Double getSpectralCloudForcing2() {
		return spectralCloudForcing2;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public void setSpectralCloudForcing2(Double spectralCloudForcing2) {
		this.spectralCloudForcing2 = spectralCloudForcing2;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public Double getSpectralCloudForcing3() {
		return spectralCloudForcing3;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public void setSpectralCloudForcing3(Double spectralCloudForcing3) {
		this.spectralCloudForcing3 = spectralCloudForcing3;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public Double getSpectralCloudForcing4() {
		return spectralCloudForcing4;
	}

	/**
	 * Field:Spectral Cloud Forcing (cloud minus clear radiance) Tip:DFNT_INT16 Dim:3
	 */
	public void setSpectralCloudForcing4(Double spectralCloudForcing4) {
		this.spectralCloudForcing4 = spectralCloudForcing4;
	}

	/**
	 * Field:Surface Pressure from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	public Double getSurfacePressure() {
		return surfacePressure;
	}

	/**
	 * Field:Surface Pressure from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	public void setSurfacePressure(Double surfacePressure) {
		this.surfacePressure = surfacePressure;
	}

	/**
	 * Field:Surface Temperature from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	public Double getSurfaceTemperature() {
		return surfaceTemperature;
	}

	/**
	 * Field:Surface Temperature from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	public void setSurfaceTemperature(Double surfaceTemperature) {
		this.surfaceTemperature = surfaceTemperature;
	}

	/**
	 * Field:Surface Temperature for Each 1-km MODIS Pixel Interplated from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	public Double getSurfaceTemperature1km() {
		return surfaceTemperature1km;
	}

	/**
	 * Field:Surface Temperature for Each 1-km MODIS Pixel Interplated from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	public void setSurfaceTemperature1km(Double surfaceTemperature1km) {
		this.surfaceTemperature1km = surfaceTemperature1km;
	}

	/**
	 * Field:Tropopause Height from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	public Double getTropopauseHeight() {
		return tropopauseHeight;
	}

	/**
	 * Field:Tropopause Height from Ancillary Data Tip:DFNT_INT16 Dim:2
	 */
	public void setTropopauseHeight(Double tropopauseHeight) {
		this.tropopauseHeight = tropopauseHeight;
	}

	public String toLongLine() {
		if (cloudTopPressure != null && cloudOpticalThickness != null) {
			cloudType = EnumCloudType.selectType(cloudTopPressure,
					cloudOpticalThickness);
		}
		SimpleStringLineBuilder builder = new SimpleStringLineBuilder();
		DateTime timp2 = timp.withZone(DateTimeZone.UTC);
		builder.append(timp2);
		builder.append(pozitie);
		builder.append(pozitie1km);
		builder.append(aboveCloudWaterVapor094);
		builder.append(asymmetryParameterIce);
		builder.append(asymmetryParameterLiq);
		builder.append(cirrusReflectance);
		builder.append(cirrusReflectanceFlag);
		builder.append(cloudEffectiveEmissivity);
		builder.append(cloudEffectiveEmissivityDay);
		builder.append(cloudEffectiveEmissivityNadir);
		builder.append(cloudEffectiveEmissivityNadirDay);
		builder.append(cloudEffectiveEmissivityNadirNight);
		builder.append(cloudEffectiveEmissivityNight);
		builder.append(cloudEffectiveRadius);
		builder.append(cloudEffectiveRadius16);
		builder.append(cloudEffectiveRadius1621);
		builder.append(cloudEffectiveRadius1621Pcl);
		builder.append(cloudEffectiveRadius16Pcl);
		builder.append(cloudEffectiveRadius37);
		builder.append(cloudEffectiveRadius37Pcl);
		builder.append(cloudEffectiveRadiusPcl);
		builder.append(cloudEffectiveRadiusUncertainty);
		builder.append(cloudEffectiveRadiusUncertainty16);
		builder.append(cloudEffectiveRadiusUncertainty1621);
		builder.append(cloudEffectiveRadiusUncertainty37);
		builder.append(cloudEmiss111km);
		builder.append(cloudEmiss121km);
		builder.append(cloudEmiss131km);
		builder.append(cloudEmiss851km);
		builder.append(cloudEmissivity1km);
		builder.append(cloudFraction);
		builder.append(cloudFractionDay);
		builder.append(cloudFractionNadir);
		builder.append(cloudFractionNadirDay);
		builder.append(cloudFractionNadirNight);
		builder.append(cloudFractionNight);
		builder.append(cloudHeightMethod);
		builder.append(cloudMultiLayerFlag);
		builder.append(cloudOpticalThickness);
		builder.append(cloudOpticalThickness16);
		builder.append(cloudOpticalThickness1621);
		builder.append(cloudOpticalThickness1621Pcl);
		builder.append(cloudOpticalThickness16Pcl);
		builder.append(cloudOpticalThickness37);
		builder.append(cloudOpticalThickness37Pcl);
		builder.append(cloudOpticalThicknessPcl);
		builder.append(cloudOpticalThicknessUncertainty);
		builder.append(cloudOpticalThicknessUncertainty16);
		builder.append(cloudOpticalThicknessUncertainty1621);
		builder.append(cloudOpticalThicknessUncertainty37);
		builder.append(cloudPhaseInfrared);
		builder.append(cloudPhaseInfrared1km);
		builder.append(cloudPhaseInfraredDay);
		builder.append(cloudPhaseInfraredNight);
		builder.append(cloudPhaseOpticalProperties);
		builder.append(cloudTopHeight);
		builder.append(cloudTopHeight1km);
		builder.append(cloudTopHeightNadir);
		builder.append(cloudTopHeightNadirDay);
		builder.append(cloudTopHeightNadirNight);
		builder.append(cloudTopMethod1km);
		builder.append(cloudTopPressure);
		builder.append(cloudTopPressure1km);
		builder.append(cloudTopPressureDay);
		builder.append(cloudTopPressureInfrared);
		builder.append(cloudTopPressureNadir);
		builder.append(cloudTopPressureNadirDay);
		builder.append(cloudTopPressureNadirNight);
		builder.append(cloudTopPressureNight);
		builder.append(cloudTopTemperature);
		builder.append(cloudTopTemperature1km);
		builder.append(cloudTopTemperatureDay);
		builder.append(cloudTopTemperatureNadir);
		builder.append(cloudTopTemperatureNadirDay);
		builder.append(cloudTopTemperatureNadirNight);
		builder.append(cloudTopTemperatureNight);
		builder.append(cloudWaterPath);
		builder.append(cloudWaterPath16);
		builder.append(cloudWaterPath1621);
		builder.append(cloudWaterPath1621Pcl);
		builder.append(cloudWaterPath16Pcl);
		builder.append(cloudWaterPath37);
		builder.append(cloudWaterPath37Pcl);
		builder.append(cloudWaterPathPcl);
		builder.append(cloudWaterPathUncertainty);
		builder.append(cloudWaterPathUncertainty16);
		builder.append(cloudWaterPathUncertainty1621);
		builder.append(cloudWaterPathUncertainty37);
		builder.append(extinctionEfficiencyIce);
		builder.append(extinctionEfficiencyLiq);
		builder.append(irpCthConsistencyFlag1km);
		builder.append(irwLowCloudTemperatureFromCop);
		builder.append(osTopFlag1km);
		builder.append(radianceVariance);
		builder.append(scanStartTime);
		builder.append(sensorAzimuth);
		builder.append(sensorAzimuthDay);
		builder.append(sensorAzimuthNight);
		builder.append(sensorZenith);
		builder.append(sensorZenithDay);
		builder.append(sensorZenithNight);
		builder.append(singleScatterAlbedoIce);
		builder.append(singleScatterAlbedoLiq);
		builder.append(solarAzimuth);
		builder.append(solarAzimuthDay);
		builder.append(solarAzimuthNight);
		builder.append(solarZenith);
		builder.append(solarZenithDay);
		builder.append(solarZenithNight);
		builder.append(surfacePressure);
		builder.append(surfaceTemperature);
		builder.append(surfaceTemperature1km);
		builder.append(tropopauseHeight);
		builder.append(numePozitie);
		builder.append(cloudType);
		builder.append(hdfFileName);
		return builder.toString();
	}

	public String extractParameters(String[] parameters) {
		if (cloudTopPressure != null && cloudOpticalThickness != null) {
			cloudType = EnumCloudType.selectType(cloudTopPressure,
					cloudOpticalThickness);
		}
		SimpleStringLineBuilder builder = new SimpleStringLineBuilder();
		DateTime timp2 = timp.withZone(DateTimeZone.UTC);
		builder.append(timp2);
		builder.append(pozitie);
		builder.append(pozitie1km);
		for (int i = 0; i < parameters.length; i++) {
			if ("cloudType".equals(parameters[i])) {
				builder.append(cloudType);
			}
			if ("aboveCloudWaterVapor094".equals(parameters[i])) {
				builder.append(aboveCloudWaterVapor094);
			}
			if ("asymmetryParameterIce".equals(parameters[i])) {
				builder.append(asymmetryParameterIce);
			}
			if ("asymmetryParameterLiq".equals(parameters[i])) {
				builder.append(asymmetryParameterLiq);
			}
			if ("atmCorrRefl".equals(parameters[i])) {
				builder.append(atmCorrRefl);
			}
			if ("brightnessTemperature".equals(parameters[i])) {
				builder.append(brightnessTemperature);
			}
			if ("cirrusReflectance".equals(parameters[i])) {
				builder.append(cirrusReflectance);
			}
			if ("cirrusReflectanceFlag".equals(parameters[i])) {
				builder.append(cirrusReflectanceFlag);
			}
			if ("cloudEffectiveEmissivity".equals(parameters[i])) {
				builder.append(cloudEffectiveEmissivity);
			}
			if ("cloudEffectiveEmissivityDay".equals(parameters[i])) {
				builder.append(cloudEffectiveEmissivityDay);
			}
			if ("cloudEffectiveEmissivityNadir".equals(parameters[i])) {
				builder.append(cloudEffectiveEmissivityNadir);
			}
			if ("cloudEffectiveEmissivityNadirDay".equals(parameters[i])) {
				builder.append(cloudEffectiveEmissivityNadirDay);
			}
			if ("cloudEffectiveEmissivityNadirNight".equals(parameters[i])) {
				builder.append(cloudEffectiveEmissivityNadirNight);
			}
			if ("cloudEffectiveEmissivityNight".equals(parameters[i])) {
				builder.append(cloudEffectiveEmissivityNight);
			}
			if ("cloudEffectiveRadius".equals(parameters[i])) {
				builder.append(cloudEffectiveRadius);
			}
			if ("cloudEffectiveRadius16".equals(parameters[i])) {
				builder.append(cloudEffectiveRadius16);
			}
			if ("cloudEffectiveRadius1621".equals(parameters[i])) {
				builder.append(cloudEffectiveRadius1621);
			}
			if ("cloudEffectiveRadius1621Pcl".equals(parameters[i])) {
				builder.append(cloudEffectiveRadius1621Pcl);
			}
			if ("cloudEffectiveRadius16Pcl".equals(parameters[i])) {
				builder.append(cloudEffectiveRadius16Pcl);
			}
			if ("cloudEffectiveRadius37".equals(parameters[i])) {
				builder.append(cloudEffectiveRadius37);
			}
			if ("cloudEffectiveRadius37Pcl".equals(parameters[i])) {
				builder.append(cloudEffectiveRadius37Pcl);
			}
			if ("cloudEffectiveRadiusPcl".equals(parameters[i])) {
				builder.append(cloudEffectiveRadiusPcl);
			}
			if ("cloudEffectiveRadiusUncertainty".equals(parameters[i])) {
				builder.append(cloudEffectiveRadiusUncertainty);
			}
			if ("cloudEffectiveRadiusUncertainty16".equals(parameters[i])) {
				builder.append(cloudEffectiveRadiusUncertainty16);
			}
			if ("cloudEffectiveRadiusUncertainty1621".equals(parameters[i])) {
				builder.append(cloudEffectiveRadiusUncertainty1621);
			}
			if ("cloudEffectiveRadiusUncertainty37".equals(parameters[i])) {
				builder.append(cloudEffectiveRadiusUncertainty37);
			}
			if ("cloudEmiss111km".equals(parameters[i])) {
				builder.append(cloudEmiss111km);
			}
			if ("cloudEmiss121km".equals(parameters[i])) {
				builder.append(cloudEmiss121km);
			}
			if ("cloudEmiss131km".equals(parameters[i])) {
				builder.append(cloudEmiss131km);
			}
			if ("cloudEmiss851km".equals(parameters[i])) {
				builder.append(cloudEmiss851km);
			}
			if ("cloudEmissivity1km".equals(parameters[i])) {
				builder.append(cloudEmissivity1km);
			}
			if ("cloudFraction".equals(parameters[i])) {
				builder.append(cloudFraction);
			}
			if ("cloudFractionDay".equals(parameters[i])) {
				builder.append(cloudFractionDay);
			}
			if ("cloudFractionNadir".equals(parameters[i])) {
				builder.append(cloudFractionNadir);
			}
			if ("cloudFractionNadirDay".equals(parameters[i])) {
				builder.append(cloudFractionNadirDay);
			}
			if ("cloudFractionNadirNight".equals(parameters[i])) {
				builder.append(cloudFractionNadirNight);
			}
			if ("cloudFractionNight".equals(parameters[i])) {
				builder.append(cloudFractionNight);
			}
			if ("cloudHeightMethod".equals(parameters[i])) {
				builder.append(cloudHeightMethod);
			}
			if ("cloudMask1km".equals(parameters[i])) {
				builder.append(cloudMask1km);
			}
			if ("cloudMask5km".equals(parameters[i])) {
				builder.append(cloudMask5km);
			}
			if ("cloudMaskSpi".equals(parameters[i])) {
				builder.append(cloudMaskSpi);
			}
			if ("cloudMultiLayerFlag".equals(parameters[i])) {
				builder.append(cloudMultiLayerFlag);
			}
			if ("cloudOpticalThickness".equals(parameters[i])) {
				builder.append(cloudOpticalThickness);
			}
			if ("cloudOpticalThickness16".equals(parameters[i])) {
				builder.append(cloudOpticalThickness16);
			}
			if ("cloudOpticalThickness1621".equals(parameters[i])) {
				builder.append(cloudOpticalThickness1621);
			}
			if ("cloudOpticalThickness1621Pcl".equals(parameters[i])) {
				builder.append(cloudOpticalThickness1621Pcl);
			}
			if ("cloudOpticalThickness16Pcl".equals(parameters[i])) {
				builder.append(cloudOpticalThickness16Pcl);
			}
			if ("cloudOpticalThickness37".equals(parameters[i])) {
				builder.append(cloudOpticalThickness37);
			}
			if ("cloudOpticalThickness37Pcl".equals(parameters[i])) {
				builder.append(cloudOpticalThickness37Pcl);
			}
			if ("cloudOpticalThicknessPcl".equals(parameters[i])) {
				builder.append(cloudOpticalThicknessPcl);
			}
			if ("cloudOpticalThicknessUncertainty".equals(parameters[i])) {
				builder.append(cloudOpticalThicknessUncertainty);
			}
			if ("cloudOpticalThicknessUncertainty16".equals(parameters[i])) {
				builder.append(cloudOpticalThicknessUncertainty16);
			}
			if ("cloudOpticalThicknessUncertainty1621".equals(parameters[i])) {
				builder.append(cloudOpticalThicknessUncertainty1621);
			}
			if ("cloudOpticalThicknessUncertainty37".equals(parameters[i])) {
				builder.append(cloudOpticalThicknessUncertainty37);
			}
			if ("cloudPhaseInfrared".equals(parameters[i])) {
				builder.append(cloudPhaseInfrared);
			}
			if ("cloudPhaseInfrared1km".equals(parameters[i])) {
				builder.append(cloudPhaseInfrared1km);
			}
			if ("cloudPhaseInfraredDay".equals(parameters[i])) {
				builder.append(cloudPhaseInfraredDay);
			}
			if ("cloudPhaseInfraredNight".equals(parameters[i])) {
				builder.append(cloudPhaseInfraredNight);
			}
			if ("cloudPhaseOpticalProperties".equals(parameters[i])) {
				builder.append(cloudPhaseOpticalProperties);
			}
			if ("cloudTopHeight".equals(parameters[i])) {
				builder.append(cloudTopHeight);
			}
			if ("cloudTopHeight1km".equals(parameters[i])) {
				builder.append(cloudTopHeight1km);
			}
			if ("cloudTopHeightNadir".equals(parameters[i])) {
				builder.append(cloudTopHeightNadir);
			}
			if ("cloudTopHeightNadirDay".equals(parameters[i])) {
				builder.append(cloudTopHeightNadirDay);
			}
			if ("cloudTopHeightNadirNight".equals(parameters[i])) {
				builder.append(cloudTopHeightNadirNight);
			}
			if ("cloudTopMethod1km".equals(parameters[i])) {
				builder.append(cloudTopMethod1km);
			}
			if ("cloudTopPressure".equals(parameters[i])) {
				builder.append(cloudTopPressure);
			}
			if ("cloudTopPressure1km".equals(parameters[i])) {
				builder.append(cloudTopPressure1km);
			}
			if ("cloudTopPressureDay".equals(parameters[i])) {
				builder.append(cloudTopPressureDay);
			}
			if ("cloudTopPressureFromRatios".equals(parameters[i])) {
				builder.append(cloudTopPressureFromRatios);
			}
			if ("cloudTopPressureInfrared".equals(parameters[i])) {
				builder.append(cloudTopPressureInfrared);
			}
			if ("cloudTopPressureNadir".equals(parameters[i])) {
				builder.append(cloudTopPressureNadir);
			}
			if ("cloudTopPressureNadirDay".equals(parameters[i])) {
				builder.append(cloudTopPressureNadirDay);
			}
			if ("cloudTopPressureNadirNight".equals(parameters[i])) {
				builder.append(cloudTopPressureNadirNight);
			}
			if ("cloudTopPressureNight".equals(parameters[i])) {
				builder.append(cloudTopPressureNight);
			}
			if ("cloudTopTemperature".equals(parameters[i])) {
				builder.append(cloudTopTemperature);
			}
			if ("cloudTopTemperature1km".equals(parameters[i])) {
				builder.append(cloudTopTemperature1km);
			}
			if ("cloudTopTemperatureDay".equals(parameters[i])) {
				builder.append(cloudTopTemperatureDay);
			}
			if ("cloudTopTemperatureNadir".equals(parameters[i])) {
				builder.append(cloudTopTemperatureNadir);
			}
			if ("cloudTopTemperatureNadirDay".equals(parameters[i])) {
				builder.append(cloudTopTemperatureNadirDay);
			}
			if ("cloudTopTemperatureNadirNight".equals(parameters[i])) {
				builder.append(cloudTopTemperatureNadirNight);
			}
			if ("cloudTopTemperatureNight".equals(parameters[i])) {
				builder.append(cloudTopTemperatureNight);
			}
			if ("cloudWaterPath".equals(parameters[i])) {
				builder.append(cloudWaterPath);
			}
			if ("cloudWaterPath16".equals(parameters[i])) {
				builder.append(cloudWaterPath16);
			}
			if ("cloudWaterPath1621".equals(parameters[i])) {
				builder.append(cloudWaterPath1621);
			}
			if ("cloudWaterPath1621Pcl".equals(parameters[i])) {
				builder.append(cloudWaterPath1621Pcl);
			}
			if ("cloudWaterPath16Pcl".equals(parameters[i])) {
				builder.append(cloudWaterPath16Pcl);
			}
			if ("cloudWaterPath37".equals(parameters[i])) {
				builder.append(cloudWaterPath37);
			}
			if ("cloudWaterPath37Pcl".equals(parameters[i])) {
				builder.append(cloudWaterPath37Pcl);
			}
			if ("cloudWaterPathPcl".equals(parameters[i])) {
				builder.append(cloudWaterPathPcl);
			}
			if ("cloudWaterPathUncertainty".equals(parameters[i])) {
				builder.append(cloudWaterPathUncertainty);
			}
			if ("cloudWaterPathUncertainty16".equals(parameters[i])) {
				builder.append(cloudWaterPathUncertainty16);
			}
			if ("cloudWaterPathUncertainty1621".equals(parameters[i])) {
				builder.append(cloudWaterPathUncertainty1621);
			}
			if ("cloudWaterPathUncertainty37".equals(parameters[i])) {
				builder.append(cloudWaterPathUncertainty37);
			}
			if ("extinctionEfficiencyIce".equals(parameters[i])) {
				builder.append(extinctionEfficiencyIce);
			}
			if ("extinctionEfficiencyLiq".equals(parameters[i])) {
				builder.append(extinctionEfficiencyLiq);
			}
			if ("irpCthConsistencyFlag1km".equals(parameters[i])) {
				builder.append(irpCthConsistencyFlag1km);
			}
			if ("irwLowCloudTemperatureFromCop".equals(parameters[i])) {
				builder.append(irwLowCloudTemperatureFromCop);
			}
			if ("osTopFlag1km".equals(parameters[i])) {
				builder.append(osTopFlag1km);
			}
			if ("qualityAssurance1km".equals(parameters[i])) {
				builder.append(qualityAssurance1km);
			}
			if ("qualityAssurance5km".equals(parameters[i])) {
				builder.append(qualityAssurance5km);
			}
			if ("radianceVariance".equals(parameters[i])) {
				builder.append(radianceVariance);
			}
			if ("retrievalFailureMetric".equals(parameters[i])) {
				builder.append(retrievalFailureMetric);
			}
			if ("retrievalFailureMetric16".equals(parameters[i])) {
				builder.append(retrievalFailureMetric16);
			}
			if ("retrievalFailureMetric1621".equals(parameters[i])) {
				builder.append(retrievalFailureMetric1621);
			}
			if ("retrievalFailureMetric37".equals(parameters[i])) {
				builder.append(retrievalFailureMetric37);
			}
			if ("scanStartTime".equals(parameters[i])) {
				builder.append(scanStartTime);
			}
			if ("sensorAzimuth".equals(parameters[i])) {
				builder.append(sensorAzimuth);
			}
			if ("sensorAzimuthDay".equals(parameters[i])) {
				builder.append(sensorAzimuthDay);
			}
			if ("sensorAzimuthNight".equals(parameters[i])) {
				builder.append(sensorAzimuthNight);
			}
			if ("sensorZenith".equals(parameters[i])) {
				builder.append(sensorZenith);
			}
			if ("sensorZenithDay".equals(parameters[i])) {
				builder.append(sensorZenithDay);
			}
			if ("sensorZenithNight".equals(parameters[i])) {
				builder.append(sensorZenithNight);
			}
			if ("singleScatterAlbedoIce".equals(parameters[i])) {
				builder.append(singleScatterAlbedoIce);
			}
			if ("singleScatterAlbedoLiq".equals(parameters[i])) {
				builder.append(singleScatterAlbedoLiq);
			}
			if ("solarAzimuth".equals(parameters[i])) {
				builder.append(solarAzimuth);
			}
			if ("solarAzimuthDay".equals(parameters[i])) {
				builder.append(solarAzimuthDay);
			}
			if ("solarAzimuthNight".equals(parameters[i])) {
				builder.append(solarAzimuthNight);
			}
			if ("solarZenith".equals(parameters[i])) {
				builder.append(solarZenith);
			}
			if ("solarZenithDay".equals(parameters[i])) {
				builder.append(solarZenithDay);
			}
			if ("solarZenithNight".equals(parameters[i])) {
				builder.append(solarZenithNight);
			}
			if ("spectralCloudForcing".equals(parameters[i])) {
				builder.append(spectralCloudForcing);
			}
			if ("surfacePressure".equals(parameters[i])) {
				builder.append(surfacePressure);
			}
			if ("surfaceTemperature".equals(parameters[i])) {
				builder.append(surfaceTemperature);
			}
			if ("surfaceTemperature1km".equals(parameters[i])) {
				builder.append(surfaceTemperature1km);
			}
			if ("tropopauseHeight".equals(parameters[i])) {
				builder.append(tropopauseHeight);
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
		builder.append("pozitie1km");
		for (int i = 0; i < parameters.length; i++) {
			if ("cloudType".equals(parameters[i])) {
				builder.append("cloudType");
			}
			if ("aboveCloudWaterVapor094".equals(parameters[i])) {
				builder.append("aboveCloudWaterVapor094");
			}
			if ("asymmetryParameterIce".equals(parameters[i])) {
				builder.append("asymmetryParameterIce");
			}
			if ("asymmetryParameterLiq".equals(parameters[i])) {
				builder.append("asymmetryParameterLiq");
			}
			if ("atmCorrRefl".equals(parameters[i])) {
				builder.append("atmCorrRefl");
			}
			if ("brightnessTemperature".equals(parameters[i])) {
				builder.append("brightnessTemperature");
			}
			if ("cirrusReflectance".equals(parameters[i])) {
				builder.append("cirrusReflectance");
			}
			if ("cirrusReflectanceFlag".equals(parameters[i])) {
				builder.append("cirrusReflectanceFlag");
			}
			if ("cloudEffectiveEmissivity".equals(parameters[i])) {
				builder.append("cloudEffectiveEmissivity");
			}
			if ("cloudEffectiveEmissivityDay".equals(parameters[i])) {
				builder.append("cloudEffectiveEmissivityDay");
			}
			if ("cloudEffectiveEmissivityNadir".equals(parameters[i])) {
				builder.append("cloudEffectiveEmissivityNadir");
			}
			if ("cloudEffectiveEmissivityNadirDay".equals(parameters[i])) {
				builder.append("cloudEffectiveEmissivityNadirDay");
			}
			if ("cloudEffectiveEmissivityNadirNight".equals(parameters[i])) {
				builder.append("cloudEffectiveEmissivityNadirNight");
			}
			if ("cloudEffectiveEmissivityNight".equals(parameters[i])) {
				builder.append("cloudEffectiveEmissivityNight");
			}
			if ("cloudEffectiveRadius".equals(parameters[i])) {
				builder.append("cloudEffectiveRadius");
			}
			if ("cloudEffectiveRadius16".equals(parameters[i])) {
				builder.append("cloudEffectiveRadius16");
			}
			if ("cloudEffectiveRadius1621".equals(parameters[i])) {
				builder.append("cloudEffectiveRadius1621");
			}
			if ("cloudEffectiveRadius1621Pcl".equals(parameters[i])) {
				builder.append("cloudEffectiveRadius1621Pcl");
			}
			if ("cloudEffectiveRadius16Pcl".equals(parameters[i])) {
				builder.append("cloudEffectiveRadius16Pcl");
			}
			if ("cloudEffectiveRadius37".equals(parameters[i])) {
				builder.append("cloudEffectiveRadius37");
			}
			if ("cloudEffectiveRadius37Pcl".equals(parameters[i])) {
				builder.append("cloudEffectiveRadius37Pcl");
			}
			if ("cloudEffectiveRadiusPcl".equals(parameters[i])) {
				builder.append("cloudEffectiveRadiusPcl");
			}
			if ("cloudEffectiveRadiusUncertainty".equals(parameters[i])) {
				builder.append("cloudEffectiveRadiusUncertainty");
			}
			if ("cloudEffectiveRadiusUncertainty16".equals(parameters[i])) {
				builder.append("cloudEffectiveRadiusUncertainty16");
			}
			if ("cloudEffectiveRadiusUncertainty1621".equals(parameters[i])) {
				builder.append("cloudEffectiveRadiusUncertainty1621");
			}
			if ("cloudEffectiveRadiusUncertainty37".equals(parameters[i])) {
				builder.append("cloudEffectiveRadiusUncertainty37");
			}
			if ("cloudEmiss111km".equals(parameters[i])) {
				builder.append("cloudEmiss111km");
			}
			if ("cloudEmiss121km".equals(parameters[i])) {
				builder.append("cloudEmiss121km");
			}
			if ("cloudEmiss131km".equals(parameters[i])) {
				builder.append("cloudEmiss131km");
			}
			if ("cloudEmiss851km".equals(parameters[i])) {
				builder.append("cloudEmiss851km");
			}
			if ("cloudEmissivity1km".equals(parameters[i])) {
				builder.append("cloudEmissivity1km");
			}
			if ("cloudFraction".equals(parameters[i])) {
				builder.append("cloudFraction");
			}
			if ("cloudFractionDay".equals(parameters[i])) {
				builder.append("cloudFractionDay");
			}
			if ("cloudFractionNadir".equals(parameters[i])) {
				builder.append("cloudFractionNadir");
			}
			if ("cloudFractionNadirDay".equals(parameters[i])) {
				builder.append("cloudFractionNadirDay");
			}
			if ("cloudFractionNadirNight".equals(parameters[i])) {
				builder.append("cloudFractionNadirNight");
			}
			if ("cloudFractionNight".equals(parameters[i])) {
				builder.append("cloudFractionNight");
			}
			if ("cloudHeightMethod".equals(parameters[i])) {
				builder.append("cloudHeightMethod");
			}
			if ("cloudMask1km".equals(parameters[i])) {
				builder.append("cloudMask1km");
			}
			if ("cloudMask5km".equals(parameters[i])) {
				builder.append("cloudMask5km");
			}
			if ("cloudMaskSpi".equals(parameters[i])) {
				builder.append("cloudMaskSpi");
			}
			if ("cloudMultiLayerFlag".equals(parameters[i])) {
				builder.append("cloudMultiLayerFlag");
			}
			if ("cloudOpticalThickness".equals(parameters[i])) {
				builder.append("cloudOpticalThickness");
			}
			if ("cloudOpticalThickness16".equals(parameters[i])) {
				builder.append("cloudOpticalThickness16");
			}
			if ("cloudOpticalThickness1621".equals(parameters[i])) {
				builder.append("cloudOpticalThickness1621");
			}
			if ("cloudOpticalThickness1621Pcl".equals(parameters[i])) {
				builder.append("cloudOpticalThickness1621Pcl");
			}
			if ("cloudOpticalThickness16Pcl".equals(parameters[i])) {
				builder.append("cloudOpticalThickness16Pcl");
			}
			if ("cloudOpticalThickness37".equals(parameters[i])) {
				builder.append("cloudOpticalThickness37");
			}
			if ("cloudOpticalThickness37Pcl".equals(parameters[i])) {
				builder.append("cloudOpticalThickness37Pcl");
			}
			if ("cloudOpticalThicknessPcl".equals(parameters[i])) {
				builder.append("cloudOpticalThicknessPcl");
			}
			if ("cloudOpticalThicknessUncertainty".equals(parameters[i])) {
				builder.append("cloudOpticalThicknessUncertainty");
			}
			if ("cloudOpticalThicknessUncertainty16".equals(parameters[i])) {
				builder.append("cloudOpticalThicknessUncertainty16");
			}
			if ("cloudOpticalThicknessUncertainty1621".equals(parameters[i])) {
				builder.append("cloudOpticalThicknessUncertainty1621");
			}
			if ("cloudOpticalThicknessUncertainty37".equals(parameters[i])) {
				builder.append("cloudOpticalThicknessUncertainty37");
			}
			if ("cloudPhaseInfrared".equals(parameters[i])) {
				builder.append("cloudPhaseInfrared");
			}
			if ("cloudPhaseInfrared1km".equals(parameters[i])) {
				builder.append("cloudPhaseInfrared1km");
			}
			if ("cloudPhaseInfraredDay".equals(parameters[i])) {
				builder.append("cloudPhaseInfraredDay");
			}
			if ("cloudPhaseInfraredNight".equals(parameters[i])) {
				builder.append("cloudPhaseInfraredNight");
			}
			if ("cloudPhaseOpticalProperties".equals(parameters[i])) {
				builder.append("cloudPhaseOpticalProperties");
			}
			if ("cloudTopHeight".equals(parameters[i])) {
				builder.append("cloudTopHeight");
			}
			if ("cloudTopHeight1km".equals(parameters[i])) {
				builder.append("cloudTopHeight1km");
			}
			if ("cloudTopHeightNadir".equals(parameters[i])) {
				builder.append("cloudTopHeightNadir");
			}
			if ("cloudTopHeightNadirDay".equals(parameters[i])) {
				builder.append("cloudTopHeightNadirDay");
			}
			if ("cloudTopHeightNadirNight".equals(parameters[i])) {
				builder.append("cloudTopHeightNadirNight");
			}
			if ("cloudTopMethod1km".equals(parameters[i])) {
				builder.append("cloudTopMethod1km");
			}
			if ("cloudTopPressure".equals(parameters[i])) {
				builder.append("cloudTopPressure");
			}
			if ("cloudTopPressure1km".equals(parameters[i])) {
				builder.append("cloudTopPressure1km");
			}
			if ("cloudTopPressureDay".equals(parameters[i])) {
				builder.append("cloudTopPressureDay");
			}
			if ("cloudTopPressureFromRatios".equals(parameters[i])) {
				builder.append("cloudTopPressureFromRatios");
			}
			if ("cloudTopPressureInfrared".equals(parameters[i])) {
				builder.append("cloudTopPressureInfrared");
			}
			if ("cloudTopPressureNadir".equals(parameters[i])) {
				builder.append("cloudTopPressureNadir");
			}
			if ("cloudTopPressureNadirDay".equals(parameters[i])) {
				builder.append("cloudTopPressureNadirDay");
			}
			if ("cloudTopPressureNadirNight".equals(parameters[i])) {
				builder.append("cloudTopPressureNadirNight");
			}
			if ("cloudTopPressureNight".equals(parameters[i])) {
				builder.append("cloudTopPressureNight");
			}
			if ("cloudTopTemperature".equals(parameters[i])) {
				builder.append("cloudTopTemperature");
			}
			if ("cloudTopTemperature1km".equals(parameters[i])) {
				builder.append("cloudTopTemperature1km");
			}
			if ("cloudTopTemperatureDay".equals(parameters[i])) {
				builder.append("cloudTopTemperatureDay");
			}
			if ("cloudTopTemperatureNadir".equals(parameters[i])) {
				builder.append("cloudTopTemperatureNadir");
			}
			if ("cloudTopTemperatureNadirDay".equals(parameters[i])) {
				builder.append("cloudTopTemperatureNadirDay");
			}
			if ("cloudTopTemperatureNadirNight".equals(parameters[i])) {
				builder.append("cloudTopTemperatureNadirNight");
			}
			if ("cloudTopTemperatureNight".equals(parameters[i])) {
				builder.append("cloudTopTemperatureNight");
			}
			if ("cloudWaterPath".equals(parameters[i])) {
				builder.append("cloudWaterPath");
			}
			if ("cloudWaterPath16".equals(parameters[i])) {
				builder.append("cloudWaterPath16");
			}
			if ("cloudWaterPath1621".equals(parameters[i])) {
				builder.append("cloudWaterPath1621");
			}
			if ("cloudWaterPath1621Pcl".equals(parameters[i])) {
				builder.append("cloudWaterPath1621Pcl");
			}
			if ("cloudWaterPath16Pcl".equals(parameters[i])) {
				builder.append("cloudWaterPath16Pcl");
			}
			if ("cloudWaterPath37".equals(parameters[i])) {
				builder.append("cloudWaterPath37");
			}
			if ("cloudWaterPath37Pcl".equals(parameters[i])) {
				builder.append("cloudWaterPath37Pcl");
			}
			if ("cloudWaterPathPcl".equals(parameters[i])) {
				builder.append("cloudWaterPathPcl");
			}
			if ("cloudWaterPathUncertainty".equals(parameters[i])) {
				builder.append("cloudWaterPathUncertainty");
			}
			if ("cloudWaterPathUncertainty16".equals(parameters[i])) {
				builder.append("cloudWaterPathUncertainty16");
			}
			if ("cloudWaterPathUncertainty1621".equals(parameters[i])) {
				builder.append("cloudWaterPathUncertainty1621");
			}
			if ("cloudWaterPathUncertainty37".equals(parameters[i])) {
				builder.append("cloudWaterPathUncertainty37");
			}
			if ("extinctionEfficiencyIce".equals(parameters[i])) {
				builder.append("extinctionEfficiencyIce");
			}
			if ("extinctionEfficiencyLiq".equals(parameters[i])) {
				builder.append("extinctionEfficiencyLiq");
			}
			if ("irpCthConsistencyFlag1km".equals(parameters[i])) {
				builder.append("irpCthConsistencyFlag1km");
			}
			if ("irwLowCloudTemperatureFromCop".equals(parameters[i])) {
				builder.append("irwLowCloudTemperatureFromCop");
			}
			if ("osTopFlag1km".equals(parameters[i])) {
				builder.append("osTopFlag1km");
			}
			if ("qualityAssurance1km".equals(parameters[i])) {
				builder.append("qualityAssurance1km");
			}
			if ("qualityAssurance5km".equals(parameters[i])) {
				builder.append("qualityAssurance5km");
			}
			if ("radianceVariance".equals(parameters[i])) {
				builder.append("radianceVariance");
			}
			if ("retrievalFailureMetric".equals(parameters[i])) {
				builder.append("retrievalFailureMetric");
			}
			if ("retrievalFailureMetric16".equals(parameters[i])) {
				builder.append("retrievalFailureMetric16");
			}
			if ("retrievalFailureMetric1621".equals(parameters[i])) {
				builder.append("retrievalFailureMetric1621");
			}
			if ("retrievalFailureMetric37".equals(parameters[i])) {
				builder.append("retrievalFailureMetric37");
			}
			if ("scanStartTime".equals(parameters[i])) {
				builder.append("scanStartTime");
			}
			if ("sensorAzimuth".equals(parameters[i])) {
				builder.append("sensorAzimuth");
			}
			if ("sensorAzimuthDay".equals(parameters[i])) {
				builder.append("sensorAzimuthDay");
			}
			if ("sensorAzimuthNight".equals(parameters[i])) {
				builder.append("sensorAzimuthNight");
			}
			if ("sensorZenith".equals(parameters[i])) {
				builder.append("sensorZenith");
			}
			if ("sensorZenithDay".equals(parameters[i])) {
				builder.append("sensorZenithDay");
			}
			if ("sensorZenithNight".equals(parameters[i])) {
				builder.append("sensorZenithNight");
			}
			if ("singleScatterAlbedoIce".equals(parameters[i])) {
				builder.append("singleScatterAlbedoIce");
			}
			if ("singleScatterAlbedoLiq".equals(parameters[i])) {
				builder.append("singleScatterAlbedoLiq");
			}
			if ("solarAzimuth".equals(parameters[i])) {
				builder.append("solarAzimuth");
			}
			if ("solarAzimuthDay".equals(parameters[i])) {
				builder.append("solarAzimuthDay");
			}
			if ("solarAzimuthNight".equals(parameters[i])) {
				builder.append("solarAzimuthNight");
			}
			if ("solarZenith".equals(parameters[i])) {
				builder.append("solarZenith");
			}
			if ("solarZenithDay".equals(parameters[i])) {
				builder.append("solarZenithDay");
			}
			if ("solarZenithNight".equals(parameters[i])) {
				builder.append("solarZenithNight");
			}
			if ("spectralCloudForcing".equals(parameters[i])) {
				builder.append("spectralCloudForcing");
			}
			if ("surfacePressure".equals(parameters[i])) {
				builder.append("surfacePressure");
			}
			if ("surfaceTemperature".equals(parameters[i])) {
				builder.append("surfaceTemperature");
			}
			if ("surfaceTemperature1km".equals(parameters[i])) {
				builder.append("surfaceTemperature1km");
			}
			if ("tropopauseHeight".equals(parameters[i])) {
				builder.append("tropopauseHeight");
			}
		}
		builder.append("numePozitie");
		builder.append("hdfFileName");
		return builder.toString();
	}

	public static String toLongHeader() {
		SimpleStringLineBuilder builder = new SimpleStringLineBuilder();
		builder.append("timp2");
		builder.append("pozitie");
		builder.append("aboveCloudWaterVapor094");
		builder.append("asymmetryParameterIce");
		builder.append("asymmetryParameterLiq");
		builder.append("cirrusReflectance");
		builder.append("cirrusReflectanceFlag");
		builder.append("cloudEffectiveEmissivity");
		builder.append("cloudEffectiveEmissivityDay");
		builder.append("cloudEffectiveEmissivityNadir");
		builder.append("cloudEffectiveEmissivityNadirDay");
		builder.append("cloudEffectiveEmissivityNadirNight");
		builder.append("cloudEffectiveEmissivityNight");
		builder.append("cloudEffectiveRadius");
		builder.append("cloudEffectiveRadius16");
		builder.append("cloudEffectiveRadius1621");
		builder.append("cloudEffectiveRadius1621Pcl");
		builder.append("cloudEffectiveRadius16Pcl");
		builder.append("cloudEffectiveRadius37");
		builder.append("cloudEffectiveRadius37Pcl");
		builder.append("cloudEffectiveRadiusPcl");
		builder.append("cloudEffectiveRadiusUncertainty");
		builder.append("cloudEffectiveRadiusUncertainty16");
		builder.append("cloudEffectiveRadiusUncertainty1621");
		builder.append("cloudEffectiveRadiusUncertainty37");
		builder.append("cloudEmiss111km");
		builder.append("cloudEmiss121km");
		builder.append("cloudEmiss131km");
		builder.append("cloudEmiss851km");
		builder.append("cloudEmissivity1km");
		builder.append("cloudFraction");
		builder.append("cloudFractionDay");
		builder.append("cloudFractionNadir");
		builder.append("cloudFractionNadirDay");
		builder.append("cloudFractionNadirNight");
		builder.append("cloudFractionNight");
		builder.append("cloudHeightMethod");
		builder.append("cloudMultiLayerFlag");
		builder.append("cloudOpticalThickness");
		builder.append("cloudOpticalThickness16");
		builder.append("cloudOpticalThickness1621");
		builder.append("cloudOpticalThickness1621Pcl");
		builder.append("cloudOpticalThickness16Pcl");
		builder.append("cloudOpticalThickness37");
		builder.append("cloudOpticalThickness37Pcl");
		builder.append("cloudOpticalThicknessPcl");
		builder.append("cloudOpticalThicknessUncertainty");
		builder.append("cloudOpticalThicknessUncertainty16");
		builder.append("cloudOpticalThicknessUncertainty1621");
		builder.append("cloudOpticalThicknessUncertainty37");
		builder.append("cloudPhaseInfrared");
		builder.append("cloudPhaseInfrared1km");
		builder.append("cloudPhaseInfraredDay");
		builder.append("cloudPhaseInfraredNight");
		builder.append("cloudPhaseOpticalProperties");
		builder.append("cloudTopHeight");
		builder.append("cloudTopHeight1km");
		builder.append("cloudTopHeightNadir");
		builder.append("cloudTopHeightNadirDay");
		builder.append("cloudTopHeightNadirNight");
		builder.append("cloudTopMethod1km");
		builder.append("cloudTopPressure");
		builder.append("cloudTopPressure1km");
		builder.append("cloudTopPressureDay");
		builder.append("cloudTopPressureInfrared");
		builder.append("cloudTopPressureNadir");
		builder.append("cloudTopPressureNadirDay");
		builder.append("cloudTopPressureNadirNight");
		builder.append("cloudTopPressureNight");
		builder.append("cloudTopTemperature");
		builder.append("cloudTopTemperature1km");
		builder.append("cloudTopTemperatureDay");
		builder.append("cloudTopTemperatureNadir");
		builder.append("cloudTopTemperatureNadirDay");
		builder.append("cloudTopTemperatureNadirNight");
		builder.append("cloudTopTemperatureNight");
		builder.append("cloudWaterPath");
		builder.append("cloudWaterPath16");
		builder.append("cloudWaterPath1621");
		builder.append("cloudWaterPath1621Pcl");
		builder.append("cloudWaterPath16Pcl");
		builder.append("cloudWaterPath37");
		builder.append("cloudWaterPath37Pcl");
		builder.append("cloudWaterPathPcl");
		builder.append("cloudWaterPathUncertainty");
		builder.append("cloudWaterPathUncertainty16");
		builder.append("cloudWaterPathUncertainty1621");
		builder.append("cloudWaterPathUncertainty37");
		builder.append("extinctionEfficiencyIce");
		builder.append("extinctionEfficiencyLiq");
		builder.append("irpCthConsistencyFlag1km");
		builder.append("irwLowCloudTemperatureFromCop");
		builder.append("osTopFlag1km");
		builder.append("radianceVariance");
		builder.append("scanStartTime");
		builder.append("sensorAzimuth");
		builder.append("sensorAzimuthDay");
		builder.append("sensorAzimuthNight");
		builder.append("sensorZenith");
		builder.append("sensorZenithDay");
		builder.append("sensorZenithNight");
		builder.append("singleScatterAlbedoIce");
		builder.append("singleScatterAlbedoLiq");
		builder.append("solarAzimuth");
		builder.append("solarAzimuthDay");
		builder.append("solarAzimuthNight");
		builder.append("solarZenith");
		builder.append("solarZenithDay");
		builder.append("solarZenithNight");
		builder.append("surfacePressure");
		builder.append("surfaceTemperature");
		builder.append("surfaceTemperature1km");
		builder.append("tropopauseHeight");
		builder.append("numePozitie");
		builder.append("cloudType");
		builder.append("hdfFileName");
		return builder.toString();
	}
}