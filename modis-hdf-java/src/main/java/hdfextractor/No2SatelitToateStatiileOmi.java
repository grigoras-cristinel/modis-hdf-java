package hdfextractor;

import java.io.File;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import hdfextractor.ozon.CalculeazaOzonLaSol;
import hdfextractor.ozon.OmiMeasurementQualityFlags;
import hdfextractor.ozon.OmiProcessingQualityFlags;
import util.SynchronizedFileWriter;

/**
 * Hello world!
 * 
 */
public class No2SatelitToateStatiileOmi {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(No2SatelitToateStatiileOmi.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - start"); //$NON-NLS-1$
		}
		boolean alegeDoarZiua = false;
		boolean eliminaToateErorile = true;
		String anul = "2009";
		File folder = new File("/proiecte/2011-satelit-ozon/omino2-" + anul
				+ "/");
		String prefixFisierIesire = "target/omino2-" + anul + "-";
		System.out.println("Start folder files:" + folder);
		DateTimeFormatter formatScriereFisierIntermediar = DateTimeFormat
				.forPattern("yyyy-MM-dd'T'HH:mm").withZone(DateTimeZone.UTC);
		try {
			TreeMap<String, SynchronizedFileWriter> fisiere = new TreeMap<String, SynchronizedFileWriter>();
			TreeMap<String, double[]> setStatii = StatiiMeteoBucuresti.statii;
			for (Entry<String, double[]> a : setStatii.entrySet()) {
				SynchronizedFileWriter writer = new SynchronizedFileWriter(
						prefixFisierIesire + a.getKey() + ".txt");
				fisiere.put(a.getKey(), writer);
				writer.print(String.format("%16s\t%16s\t%16s\t%16s",
						"DataTora", "no2 molec/cm2", "T(k)", "p(pa)"));
				writer.print(String.format("\t%16s", "conc ug 0-10"));
				writer.print(String.format("\t%16s", "conc ug 0-15"));
				writer.print(String.format("\t%16s", "conc ug 0-20"));
				writer.print(String.format("\t%16s", "conc ug 0-30"));
				writer.print(String.format("\t%16s", "conc ug 0-40"));
				writer.print(String.format("\t%16s", "conc ug 0-50"));
				for (int ia = 20; ia < 100; ia = 10 + ia) {
					writer.print(String.format("\t%16s", "conc ug " + (ia - 10)
							+ "-" + ia));
				}
				writer.println();
			}
			File[] deCitit = folder.listFiles();
			for (int i = 0; i < deCitit.length; i++) {
				File file = deCitit[i];
				if (file.isDirectory()) {
					continue;
				}
				H5NO2FileWorker cfile = null;
				if (file.getName().endsWith("he5")) {
					try {
						cfile = new H5NO2FileWorker(file.getCanonicalPath(),
								StatiiMeteoBucuresti.statii);
					} catch (Exception e) {
						logger.error("main(String[])", e); //$NON-NLS-1$
						System.out.println("Exceptie citire fisier:"
								+ file.getCanonicalPath() + "; "
								+ e.getMessage());
						e.printStackTrace();
						continue;
					}
				} else {
					continue;
				}
				TreeMap<String, Double> valoriDobson = new TreeMap<String, Double>();
				TreeMap<String, Double> valoriTemperatura = new TreeMap<String, Double>();
				TreeMap<String, Double> valoriPresiune = new TreeMap<String, Double>();
				boolean gasitSatelit = false;
				for (Entry<String, double[]> a : setStatii.entrySet()) {
					String siteName = a.getKey();
					if (cfile.hasPosition(siteName)) {
						Double ozonDobson = cfile.findOzon(siteName);
						Double pres = cfile.findPresiuneSuprafata(siteName);
						if (ozonDobson != null) {
							gasitSatelit = true;
							valoriDobson.put(a.getKey(), ozonDobson);
							valoriPresiune.put(a.getKey(), pres);
						} else {
							valoriDobson.put(a.getKey(), -1d);
							valoriPresiune.put(a.getKey(), 1d);
						}
					}
				}
				if (gasitSatelit) {
					DateTime timp = cfile.findTimpMediu();
					System.out.println("File: " + file.getName() + " Writer:"
							+ timp + " ora:" + timp.getHourOfDay());
					CalculeazaOzonLaSol calc = new CalculeazaOzonLaSol();
					int luna = timp.getMonthOfYear() - 1;
					boolean adauga = true;
					int ora = timp.getHourOfDay();
					if (alegeDoarZiua) {
						if (10 > ora || ora > 16) {
							adauga = false;
						}
					}
					if (adauga) {
						for (Entry<String, Double> a : valoriDobson.entrySet()) {
							String siteName = a.getKey();
							OmiProcessingQualityFlags processingQualityFlags = cfile
									.getProcessingQualityFlags(siteName);
							OmiMeasurementQualityFlags measurementQualityFlags = cfile
									.getQualityFlags(siteName);
							if (eliminaToateErorile
									&& processingQualityFlags.isTrue()) {
								System.out
										.println("      Missing data , flag value:"
												+ processingQualityFlags
														.printTrueValues());
								continue;
							}
							if (eliminaToateErorile
									&& measurementQualityFlags.isTrue()) {
								System.out
										.println("      Measurement error, flag value:"
												+ measurementQualityFlags
														.printTrueValues());
								continue;
							}
							SynchronizedFileWriter writer = fisiere
									.get(siteName);
							Double dobsonSatelit = valoriDobson.get(siteName);
							if (dobsonSatelit > 0) {
								writer.print(String
										.format("%16s\t%2$16.8f\t%3$16.8f\t%4$16.8f",
												timp.toString(formatScriereFisierIntermediar),
												dobsonSatelit,
												valoriTemperatura.get(siteName),
												valoriPresiune.get(siteName)));
								Double dobsonH1 = calc
										.calculeazaConcentratieugm3(luna,
												dobsonSatelit, 10d);
								Double dobsonH2 = calc
										.calculeazaConcentratieugm3(luna,
												dobsonSatelit, 20d);
								Double dobsonH3 = calc
										.calculeazaConcentratieugm3(luna,
												dobsonSatelit, 30d);
								Double dobsonH4 = calc
										.calculeazaConcentratieugm3(luna,
												dobsonSatelit, 40d);
								Double dobsonH5 = calc
										.calculeazaConcentratieugm3(luna,
												dobsonSatelit, 50d);
								writer.print(String
										.format("\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f",
												dobsonH1, dobsonH2, dobsonH3,
												dobsonH4, dobsonH5));
								for (int ia = 20; ia < 100; ia = 10 + ia) {
									writer.print(String.format(
											"\t%16.8f",
											calc.calculeazaConcentratieugm3Interval10m(
													luna, dobsonSatelit, ia)));
								}
								writer.println();
							}
						}
					}
				}
				cfile.close();
			}
		} catch (Exception e) {
			logger.error("main(String[])", e); //$NON-NLS-1$
			e.printStackTrace();
			System.err.println("Exceptie in:" + e);
		} finally {
		}
		System.out.println("End");
		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - end"); //$NON-NLS-1$
		}
	}
}
