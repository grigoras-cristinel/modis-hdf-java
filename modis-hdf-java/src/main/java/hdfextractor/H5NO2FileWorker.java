package hdfextractor;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.tree.DefaultMutableTreeNode;

import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.FileFormat;
import ncsa.hdf.object.HObject;
import ncsa.hdf.object.h4.H4Vdata;
import ncsa.hdf.object.h5.H5File;
import ncsa.hdf.object.h5.H5Group;
import ncsa.hdf.object.h5.H5ScalarDS;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import hdfextractor.ozon.OmiMeasurementQualityFlags;
import hdfextractor.ozon.OmiProcessingQualityFlags;

/**
 * Trebuie sa inglobeze munca pe un fisier penru valorile de no2
 * 
 * @author Grig
 * 
 */
public class H5NO2FileWorker implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7946061313120182163L;
	H5File file;
	Integer width;
	Integer height;
	TreeMap<String, double[]> puncte;
	TreeMap<String, Long> indexPozitii;
	TreeMap<String, DateTime> valoriTimp;
	H5ScalarDS totalValMas;
	TreeMap<String, Double> valMas;
	float totalValMasFillValue;
	double totalValMasScaleFactor;
	H5ScalarDS presiuneSuprafata;
	H5ScalarDS omiProcessingQuality;
	short omiProcessingQualityFillValue;
	TreeMap<String, OmiProcessingQualityFlags> valoriPQuality;
	H5ScalarDS omiMeasurementQualityFlags;
	TreeMap<String, OmiMeasurementQualityFlags> valoriMQuality;
	byte omiMeasurementQualityFlagsFillValue;
	TreeMap<String, Double> valoriPresiuneSuprafata;
	float presiuneSuprafataFillValue;
	double presiuneSuprafataScaleFactor;
	H5ScalarDS timpScanare;
	H5ScalarDS latitudine;
	H5ScalarDS longitudine;

	public TreeMap<String, double[]> getPuncte() {
		return puncte;
	}

	public void setPuncte(TreeMap<String, double[]> pozitii) {
		this.puncte = pozitii;
	}

	public H5NO2FileWorker(H5File h4file) throws Exception {
		super();
		this.file = h4file;
		file.open();
	}

	public H5NO2FileWorker(String h4fileLocation) throws Exception {
		file = new H5File(h4fileLocation, FileFormat.READ);
		file.open();
	}

	public H5NO2FileWorker(String canonicalPath,
			TreeMap<String, double[]> statii) throws Exception {
		this(canonicalPath);
		puncte = statii;
		parseFile();
	}

	public boolean hasPosition(String nume) throws Exception {
		if (puncte == null || !puncte.containsKey(nume)) {
			return false;
		}
		if (indexPozitii != null) {
			if (indexPozitii.get(nume) != null) {
				return true;
			}
			return false;
		}
		parseFile();
		if (indexPozitii != null) {
			if (indexPozitii.get(nume) != null) {
				return true;
			}
		}
		return false;
	}

	private void parseFile() throws Exception, OutOfMemoryError {
		H5Group testGroup = (H5Group) file
				.get("/HDFEOS/SWATHS/ColumnAmountNO2/Geolocation Fields");
		Iterator<HObject> it = testGroup.getMemberList().iterator();
		while (it.hasNext()) {
			HObject hObject = it.next();
			if (hObject instanceof H5ScalarDS) {
				H5ScalarDS h4sds = (H5ScalarDS) hObject;
				if ("Latitude".equals(h4sds.getName())) {
					latitudine = h4sds;
				}
				if ("Longitude".equals(h4sds.getName())) {
					longitudine = h4sds;
				}
				if ("Time".equals(h4sds.getName())) {
					timpScanare = h4sds;
				}
			}
		}
		H5Group dataFieldsGrup = (H5Group) file
				.get("/HDFEOS/SWATHS/ColumnAmountNO2/Data Fields");
		Iterator<HObject> itDataFields = dataFieldsGrup.getMemberList()
				.iterator();
		while (itDataFields.hasNext()) {
			HObject hObject = itDataFields.next();
			if (hObject instanceof H5ScalarDS) {
				H5ScalarDS h4sds = (H5ScalarDS) hObject;
				if ("ColumnAmountNO2".equals(h4sds.getName())) {
					totalValMas = h4sds;
					for (int f1 = 0; f1 < h4sds.getMetadata().size(); f1++) {
						Attribute attr = (Attribute) h4sds.getMetadata()
								.get(f1);
						if (attr.getName().equals("MissingValue")) {
							float[] vals = (float[]) attr.getValue();
							totalValMasFillValue = vals[0];
						}
						if (attr.getName().equals("ScaleFactor")) {
							double[] vals = (double[]) attr.getValue();
							totalValMasScaleFactor = vals[0];
						}
					}
				}
				if ("TerrainPressure".equals(h4sds.getName())) {
					presiuneSuprafata = h4sds;
					for (int f1 = 0; f1 < h4sds.getMetadata().size(); f1++) {
						Attribute attr = (Attribute) h4sds.getMetadata()
								.get(f1);
						if (attr.getName().equals("MissingValue")) {
							short[] vals = (short[]) attr.getValue();
							presiuneSuprafataFillValue = vals[0];
						}
						if (attr.getName().equals("ScaleFactor")) {
							double[] vals = (double[]) attr.getValue();
							presiuneSuprafataScaleFactor = vals[0];
						}
					}
				}
				if ("MeasurementQualityFlags".equals(h4sds.getName())) {
					omiMeasurementQualityFlags = h4sds;
					for (int f1 = 0; f1 < h4sds.getMetadata().size(); f1++) {
						Attribute attr = (Attribute) h4sds.getMetadata()
								.get(f1);
						if (attr.getName().equals("MissingValue")) {
							byte[] vals = (byte[]) attr.getValue();
							omiMeasurementQualityFlagsFillValue = vals[0];
						}
					}
				}
				if ("AMFQualityFlags".equals(h4sds.getName())) {
					omiProcessingQuality = h4sds;
					for (int f1 = 0; f1 < h4sds.getMetadata().size(); f1++) {
						Attribute attr = (Attribute) h4sds.getMetadata()
								.get(f1);
						if (attr.getName().equals("MissingValue")) {
							short[] vals = (short[]) attr.getValue();
							omiProcessingQualityFillValue = vals[0];
						}
					}
				}
			}
		}
		indexPozitii = new TreeMap<String, Long>();
		valMas = new TreeMap<String, Double>();
		valoriPQuality = new TreeMap<String, OmiProcessingQualityFlags>();
		valoriMQuality = new TreeMap<String, OmiMeasurementQualityFlags>();
		valoriTimp = new TreeMap<String, DateTime>();
		valoriPresiuneSuprafata = new TreeMap<String, Double>();
		for (Entry<String, double[]> oPozitie : puncte.entrySet()) {
			String numePozitie = oPozitie.getKey();
			double coord[] = oPozitie.getValue();
			double latit = coord[0];
			double longit = coord[1];
			float[] lats = (float[]) latitudine.getData();
			float[] longs = (float[]) longitudine.getData();
			long pozSearch = 0;
			double deltaSumF = 100;
			for (int i = 0; i < lats.length - 1; i++) {
				float f = lats[i];
				if (f > latit - 0.5 && latit + 0.5 > f) {
					double deltaLat = Math.abs(latit - f);
					float fo = longs[i];
					if (fo > longit - 0.5 && fo < longit + 0.5) {
						double mdelta = Math.abs(longit - fo);
						double deltaSumT = mdelta + deltaLat;
						if (deltaSumT < deltaSumF) {
							deltaSumF = deltaSumT;
							pozSearch = i;
						}
					}
				}
			}
			// System.out.println("Pozfound:" + pozSearch );
			// " DLA:"
			// + deltaLatF + " DLO:" + deltaLonF + " X:" + xPozLat + " Y:"
			// + yPozLat);
			indexPozitii.put(numePozitie, pozSearch);
			float[] valsh0 = (float[]) totalValMas.getData();
			DateTime value = null;
			double[] vals = (double[]) timpScanare.getData();
			width = valsh0.length / vals.length;
			// System.out.println("tscan"+vals.length+" valsh:"+valsh0.length);
			DateTime retval = new DateTime().withZone(DateTimeZone.UTC);
			retval = retval.withDate(1993, 1, 1).withTime(0, 0, 0, 0);
			int indexTimp = (int) (pozSearch / width);
			// System.out.println("indexTimp="+indexTimp);
			value = retval.plusSeconds((int) vals[indexTimp]);
			// System.out.println(""+value.toString());
			valoriTimp.put(numePozitie, value);
			{
				byte[] valsh = (byte[]) omiMeasurementQualityFlags.getData();
				valoriMQuality.put(numePozitie, new OmiMeasurementQualityFlags(
						valsh[indexTimp]));
			}
			{
				float[] valsh = (float[]) totalValMas.getData();
				float gasita = valsh[(int) pozSearch];
				if (gasita != totalValMasFillValue) {
					valMas.put(numePozitie, gasita * totalValMasScaleFactor);
				} else {
					valMas.put(numePozitie, null);
				}
			}
			{
				short[] valsh = (short[]) omiProcessingQuality.getData();
				float gasita = valsh[(int) pozSearch];
				if (gasita != omiProcessingQualityFillValue) {
					valoriPQuality.put(numePozitie,
							new OmiProcessingQualityFlags((int) gasita));
				} else {
					valMas.put(numePozitie, null);
				}
			}
			{
				short[] valsh = (short[]) presiuneSuprafata.getData();
				short gasita = valsh[(int) pozSearch];
				if (gasita != presiuneSuprafataFillValue) {
					valoriPresiuneSuprafata.put(numePozitie, gasita
							* presiuneSuprafataScaleFactor);
				} else {
					valoriPresiuneSuprafata.put(numePozitie, null);
				}
			}
		}
	}

	public void infoFileGrups() throws Exception {
		@SuppressWarnings("rawtypes")
		Enumeration nodes = file.getRootNode().children();
		while (nodes.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes
					.nextElement();
			Object obj = node.getUserObject();
			System.out.println("obj" + obj);
			if (obj instanceof H4Vdata) {
				H4Vdata vd = (H4Vdata) obj;
				System.out.println(vd.getName() + "" + vd.getData());
			} else if (obj instanceof H5Group) {
				H5Group grp = (H5Group) obj;
				System.out.println(grp.getName() + " "
						+ grp.getMetadata().size());
				Iterator<HObject> it = grp.getMemberList().iterator();
				while (it.hasNext()) {
					HObject hObject = it.next();
				}
			}
		}
		H5ScalarDS hObject = (H5ScalarDS) file
				.get("/HDFEOS/SWATHS/ColumnAmountNO2/Geolocation Fields/Longitude");
		descrieGrup((H5Group) file.get("/HDFEOS"));
		descrieGrup((H5Group) file.get("/HDFEOS/SWATHS"));
		descrieGrup((H5Group) file.get("/HDFEOS/SWATHS/ColumnAmountNO2"));
		descrieGrup((H5Group) file
				.get("/HDFEOS/SWATHS/ColumnAmountNO2/Data Fields"));
		descrieGrup((H5Group) file
				.get("/HDFEOS/SWATHS/ColumnAmountNO2/Geolocation Fields"));
	}

	private void descrieGrup(H5Group testGroup) {
		System.out.println("testGroup:" + testGroup.getFullName() + " size="
				+ testGroup.getMemberList().size());
		Iterator<HObject> it = testGroup.getMemberList().iterator();
		while (it.hasNext()) {
			HObject hObject = it.next();
			System.out.println(hObject.getFullName() + " type "
					+ hObject.getClass().getName());
		}
	}

	public void close() {
		try {
			file.close();
		} catch (Exception e) {
		}
	}

	public Long findPozitie(String nume) throws Exception {
		if (hasPosition(nume)) {
			return indexPozitii.get(nume);
		}
		return null;
	}

	/**
	 * @param nume
	 * @return null daca nu gaseste
	 * @throws Exception
	 */
	public Double findOzon(String nume) throws Exception {
		if (hasPosition(nume)) {
			return valMas.get(nume);
		}
		return null;
	}

	public Double findPresiuneSuprafata(String nume) throws Exception {
		if (hasPosition(nume)) {
			return valoriPresiuneSuprafata.get(nume);
		}
		return null;
	}

	public DateTime findTimp(String nume) throws Exception {
		if (hasPosition(nume)) {
			return valoriTimp.get(nume);
		}
		return null;
	}

	public DateTime findTimpMediu() throws Exception {
		long val = 0;
		for (Entry<String, double[]> st : puncte.entrySet()) {
			if (hasPosition(st.getKey())) {
				DateTime timpGasit = valoriTimp.get(st.getKey());
				if (val == 0) {
					val = timpGasit.getMillis();
				} else {
					val += timpGasit.getMillis();
					val = val / 2;
				}
			}
		}
		if (val > 0) {
			return new DateTime().withZone(DateTimeZone.UTC).withMillis(val);
		}
		return null;
	}

	public boolean hasOzon(String nume) throws Exception {
		if (hasPosition(nume)) {
			return valMas.get(nume) != null;
		}
		return false;
	}

	public TreeMap<String, Object> extractMetadata(H5File testFile)
			throws Exception {
		HObject hobj = (HObject) ((DefaultMutableTreeNode) testFile
				.getRootNode()).getUserObject();
		List metadataFile = hobj.getMetadata();
		System.out.println("Nume:" + hobj.getName());
		String coreMetadata = "";
		for (Iterator it = metadataFile.iterator(); it.hasNext();) {
			Attribute object = (Attribute) it.next();
			if ("CoreMetadata.0".equals(object.getName())) {
				coreMetadata = ((String[]) object.getValue())[0];
				break;
			}
		}
		if (StringUtils.isBlank(coreMetadata)) {
			return null;
		}
		TreeMap<String, Object> retval = new TreeMap<String, Object>();
		Scanner smare = new Scanner(coreMetadata);
		Stack<String> grup = new Stack<String>();
		String obiect = null;
		while (smare.hasNext()) {
			String line = smare.nextLine();
			if (StringUtils.isBlank(line)) {
				continue;
			}
			Scanner sline = new Scanner(line);
			sline.useDelimiter("=");
			while (sline.hasNext()) {
				String key = sline.next().trim();
				boolean hasValue = false;
				if ("GROUP".equals(key)) {
					hasValue = true;
					if (sline.hasNext()) {
						String value = sline.next().trim();
						grup.push(value);
					}
				}
				if ("END_GROUP".equals(key)) {
					if (sline.hasNext()) {
						String value = sline.next().trim();
						if (value.equals(grup.peek())) {
							grup.pop();
						}
					}
				}
				if ("OBJECT".equals(key)) {
					if (sline.hasNext()) {
						String value = sline.next().trim();
						obiect = value;
					}
				}
				if ("END-OBJECT".equals(key)) {
					obiect = null;
				}
				if ("VALUE".equals(key)) {
					if (sline.hasNext()) {
						String value = sline.next().trim();
						StringBuilder sb = new StringBuilder();
						Iterator<String> it = grup.iterator();
						while (it.hasNext()) {
							String type = it.next();
							if (StringUtils.isNotBlank(type)) {
								sb.append(type);
								sb.append("-");
							}
						}
						retval.put(sb.toString() + obiect, value);
					}
				}
			}
		}
		return retval;
	}

	public OmiMeasurementQualityFlags getQualityFlags(String nume)
			throws Exception {
		return valoriMQuality.get(nume);
	}

	public OmiProcessingQualityFlags getProcessingQualityFlags(String nume)
			throws Exception {
		return valoriPQuality.get(nume);
	}
}
