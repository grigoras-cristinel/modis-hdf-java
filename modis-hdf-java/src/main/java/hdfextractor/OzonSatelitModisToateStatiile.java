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
import util.SynchronizedFileWriter;

/**
 * Hello world!
 * 
 */
public class OzonSatelitModisToateStatiile {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(OzonSatelitModisToateStatiile.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - start"); //$NON-NLS-1$
		}
		boolean alegeDoarZiua = false;
		DateTimeFormatter formatScriereFisierIntermediar = DateTimeFormat
				.forPattern("yyyy-MM-dd'T'HH:mm").withZone(DateTimeZone.UTC);
		String anul = "2010";
		File folderIntrare = new File("/proiecte/2011-satelit-ozon/Modis07-"
				+ anul + "/");
		System.out.println("Start folder files:" + folderIntrare);
		try {
			TreeMap<String, SynchronizedFileWriter> fisiere = new TreeMap<String, SynchronizedFileWriter>();
			TreeMap<String, double[]> setStatii = StatiiMeteoBucuresti.statii;
			for (Entry<String, double[]> a : setStatii.entrySet()) {
				SynchronizedFileWriter writer = new SynchronizedFileWriter(
						"target/modis-" + anul + "-" + a.getKey() + ".txt");
				fisiere.put(a.getKey(), writer);
				writer.print(String.format("%16s\t%16s\t%16s\t%16s",
						"DataTora", "ozon dobson", "T(k)", "p(pa)"));
				writer.print(String.format("\t%16s", "conc ug 0-10"));
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
			File[] deCitit = folderIntrare.listFiles();
			for (int i = 0; i < deCitit.length; i++) {
				File file = deCitit[i];
				if (file.isDirectory()) {
					continue;
				}
				if (!file.getName().endsWith("hdf")) {
					continue;
				}
				H4FileWorker cfile = null;
				try {
					cfile = new H4FileWorker(file.getCanonicalPath(),
							StatiiMeteoBucuresti.statii);
				} catch (Exception e) {
					logger.error("main(String[])", e); //$NON-NLS-1$
					System.out.println("Exceptie citire fisier:"
							+ file.getCanonicalPath() + "; " + e.getMessage());
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
						Double temp = cfile.findTemperaturaSuprafata(siteName);
						Double pres = cfile.findPresiuneSuprafata(siteName);
						if (ozonDobson != null) {
							gasitSatelit = true;
							valoriDobson.put(a.getKey(), ozonDobson);
							valoriTemperatura.put(a.getKey(), temp);
							valoriPresiune.put(a.getKey(), pres);
						} else {
							valoriDobson.put(a.getKey(), -1d);
							valoriTemperatura.put(a.getKey(), -1d);
							valoriPresiune.put(a.getKey(), 1d);
						}
					}
				}
				if (gasitSatelit) {
					DateTime timp = cfile.findTimpMediu();
					CalculeazaOzonLaSol calc = new CalculeazaOzonLaSol();
					int luna = timp.getMonthOfYear() - 1;
					boolean adauga = true;
					int ora = timp.getHourOfDay();
					if (alegeDoarZiua) {
						if (ora < 10) {
							adauga = false;
						}
						if (ora > 16) {
							adauga = false;
						}
					}
					if (adauga) {
						System.out.println("Writer:" + timp + " ora:"
								+ timp.getHourOfDay());
						for (Entry<String, Double> a : valoriDobson.entrySet()) {
							SynchronizedFileWriter writer = fisiere.get(a
									.getKey());
							Double dobsonSatelit = valoriDobson.get(a.getKey());
							if (dobsonSatelit > 0) {
								writer.print(String
										.format("%16s\t%2$16.8f\t%3$16.8f\t%4$16.8f",
												timp.toString(formatScriereFisierIntermediar),
												dobsonSatelit,
												valoriTemperatura.get(a
														.getKey()),
												valoriPresiune.get(a.getKey())));
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
