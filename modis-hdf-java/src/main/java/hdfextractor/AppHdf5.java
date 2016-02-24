package hdfextractor;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.TreeMap;

import org.joda.time.DateTime;

import hdfextractor.ozon.CalculeazaOzonLaSol;

public class AppHdf5 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String anii[] = new String[] { "2008", "2009", "2010" };
		String sateliti[] = new String[] { "omi" };
		try {
			for (int j = 0; j < anii.length; j++) {
				String anul = anii[j];
				for (int k = 0; k < sateliti.length; k++) {
					String satelit = sateliti[k];
					String inputPath = "C:/DATE-Satelit/" + satelit + "_ozon_"
							+ anul + "/";
					File folderInput = new File(inputPath);
					System.out.println("Start folder:" + folderInput);
					HashMap<String, PrintWriter> outputFiles = new HashMap<String, PrintWriter>();
					TreeMap<String, double[]> statii = StatiiMeteoBucuresti.statii;
					for (String siteName : statii.keySet()) {
						File fileOutput = new File(inputPath + "out/ozon_"
								+ satelit + "_" + anul + "_" + siteName
								+ ".txt");
						System.out.println("Output file:" + fileOutput);
						try {
							PrintWriter pw = new PrintWriter(fileOutput);
							pw.println(OzonLineTO.shortHeader(siteName));
							outputFiles.put(siteName, pw);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					File[] deCitit = folderInput.listFiles();
					CalculeazaOzonLaSol calc1 = new CalculeazaOzonLaSol();
					for (int i = 0; i < deCitit.length; i++) {
						File file = deCitit[i];
						if (file.isDirectory()) {
							continue;
						}
						if (file.getName().endsWith("hdf")) {
							H4FileWorker cfile = null;
							try {
								cfile = new H4FileWorker(
										file.getCanonicalPath(), statii);
							} catch (Exception e) {
								System.out.println("File open exception:"
										+ file.getCanonicalPath());
								continue;
							}
							double mukg = 1.660538e-27;
							double inaltimeCalcul = 0;
							System.out.print("File hdf found:" + file.getName()
									+ " :");
							for (String siteName : statii.keySet()) {
								if (cfile.hasPosition(siteName)) {
									System.out.print(siteName);
									DateTime timp = cfile.findTimp(siteName);
									Double ozonDobson = cfile
											.findOzon(siteName);
									int luna = timp.getMonthOfYear() - 1;
									if (ozonDobson != null) {
										System.out.print("+");
										Double ozlasol = ozonDobson;
										Double ozlasol10 = calc1.calculeazaU(
												luna, ozonDobson, 10d);
										Double ozlasol2 = calc1.calculeazaU(
												luna, ozonDobson, 15d);
										Double ozlasol3 = calc1.calculeazaU(
												luna, ozonDobson, 20d);
										double dif2 = ozonDobson - ozlasol2;
										double dif3 = ozonDobson - ozlasol3;
										double v2_ugm3 = dif2 * 2.69e16 * mukg
												* 48 * 1e9 / 13.5;
										double v3_ugm3 = dif3 * 2.69e16 * mukg
												* 48 * 1e9 / 13.5;
										OzonLineTO l = new OzonLineTO();
										l.satelit = satelit;
										l.statie = siteName;
										l.data = timp;
										l.ozoneTotal = ozonDobson;
										l.ozone0m = ozlasol;
										l.ozone10m = ozlasol10;
										l.ozone15m = ozlasol2;
										l.ozone20m = ozlasol3;
										l.diferenta15m = dif2;
										l.diferenta20m = dif3;
										l.microGrame15m = v2_ugm3;
										l.microGrame20m = v3_ugm3;
										l.uprim = calc1.calculeazaUprim(luna,
												ozonDobson, inaltimeCalcul);
										l.tempK = cfile
												.findTemperaturaSuprafata(siteName);
										l.pres_pa = cfile
												.findPresiuneSuprafata(siteName);
										PrintWriter pw = outputFiles
												.get(siteName);
										pw.println(l.toLongLine());
									} else {
										System.out.print(" ");
									}
								}
							}
							System.out.println(";");
							cfile.close();
							for (PrintWriter pws : outputFiles.values()) {
								pws.flush();
							}
						}
						if (file.getName().endsWith("he5")) {
							H5FileWorker cfile = null;
							try {
								cfile = new H5FileWorker(
										file.getCanonicalPath(), statii);
							} catch (Exception e) {
								System.out.println("File open exception:"
										+ file.getCanonicalPath());
								continue;
							}
							double mukg = 1.660538e-27;
							double inaltimeCalcul = 0;
							System.out.print("File hdf5 found:"
									+ file.getName() + ":");
							for (String siteName : statii.keySet()) {
								if (cfile.hasPosition(siteName)) {
									System.out.print(siteName);
									DateTime timp = cfile.findTimp(siteName);
									Double ozonDobson = cfile
											.findOzon(siteName);
									int luna = timp.getMonthOfYear() - 1;
									if (ozonDobson != null) {
										System.out.print("+");
										Double ozlasol = ozonDobson;
										Double ozlasol2 = calc1.calculeazaU(
												luna, ozonDobson, 15d);
										Double ozlasol10 = calc1.calculeazaU(
												luna, ozonDobson, 10d);
										Double ozlasol3 = calc1.calculeazaU(
												luna, ozonDobson, 20d);
										double dif2 = ozonDobson - ozlasol2;
										double dif3 = ozonDobson - ozlasol3;
										double v2_ugm3 = dif2 * 2.69e16 * mukg
												* 48 * 1e9 / 13.5;
										double v3_ugm3 = dif3 * 2.69e16 * mukg
												* 48 * 1e9 / 13.5;
										OzonLineTO l = new OzonLineTO();
										l.satelit = satelit;
										l.statie = siteName;
										l.data = timp;
										l.ozoneTotal = ozonDobson;
										l.ozone0m = ozlasol;
										l.ozone10m = ozlasol10;
										l.ozone15m = ozlasol2;
										l.ozone20m = ozlasol3;
										l.diferenta15m = dif2;
										l.diferenta20m = dif3;
										l.microGrame15m = v2_ugm3;
										l.microGrame20m = v3_ugm3;
										l.uprim = calc1.calculeazaUprim(luna,
												ozonDobson, inaltimeCalcul);
										l.tempK = cfile
												.findTemperaturaSuprafata(siteName);
										l.pres_pa = cfile
												.findPresiuneSuprafata(siteName);
										PrintWriter pw = outputFiles
												.get(siteName);
										pw.println(l.toLongLine());
									} else {
										System.out.print(" ");
									}
								}
							}
							System.out.println(";");
							cfile.close();
							for (PrintWriter pws : outputFiles.values()) {
								pws.flush();
							}
							// infoPrintH4SDS(h4sds);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End");
	}
}
