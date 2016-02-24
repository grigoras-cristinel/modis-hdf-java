package hdfextractor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import org.joda.time.DateTime;

import hdfextractor.ozon.CalculeazaOzonLaSol;

/**
 * Hello world!
 * 
 */
public class MediiOrareLunareSatelit {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		File folder = new File("/proiecte/2011-satelit-ozon/500574598/");
		File output = new File("output.txt");
		File outputMedii = new File("output-medii-luna-ora.txt");
		System.out.println("Start folder:" + folder);
		HashMap<Integer, HashMap<Integer, Double>> mediiLunaOra = new HashMap<Integer, HashMap<Integer, Double>>();
		for (int i = 1; i <= 12; i++) {
			HashMap<Integer, Double> mediiOra = new HashMap<Integer, Double>();
			mediiLunaOra.put(i, mediiOra);
		}
		try {
			System.out.println("Output file:" + output.getCanonicalPath());
			System.out.println("Output file medii:"
					+ outputMedii.getCanonicalPath());
			NumberFormat nf = new DecimalFormat("###0.000000");
			NumberFormat ne = new DecimalFormat("0.#####E000");
			PrintWriter pw = new PrintWriter(output);
			pw.print("// Data           \t");
			pw.print("     ozon   ");
			pw.print("    oz 1.5m ");
			pw.print("    oz 15 m ");
			pw.print("  diferenta ");
			pw.print("    ug/m3   ");
			pw.print("     u'     ");
			pw.print("     T(K)   ");
			pw.print("    P(hPa)  ");
			pw.println();
			File[] deCitit = folder.listFiles();
			CalculeazaOzonLaSol calc1 = new CalculeazaOzonLaSol();
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
							StatiiMeteo.statii);
				} catch (Exception e) {
					System.out.println("File open exception:"
							+ file.getCanonicalPath());
					continue;
				}
				String siteName = "B1";
				double mukg = 1.660538e-27;
				double inaltimeCalcul = 1.5;
				if (cfile.hasPosition(siteName)) {
					DateTime timp = cfile.findTimp(siteName);
					Double ozonDobson = cfile.findOzon(siteName);
					int luna = timp.getMonthOfYear() - 1;
					if (ozonDobson != null) {
						Double ozlasol = calc1.calculeazaU(luna, ozonDobson,
								inaltimeCalcul);
						Double ozlasol2 = calc1.calculeazaU(luna, ozonDobson, 15d);
						double dif = ozlasol - ozlasol2;
						double ugm3 = dif * 2.69e16 * mukg * 48 * 1e9 / 13.5;
						pw.print(timp.toString("yyyy-MM-dd' 'HH:mm") + "\t");
						pw.print(nf.format(ozonDobson) + "\t");
						pw.print(nf.format(ozlasol) + "\t");
						pw.print(nf.format(ozlasol2) + "\t");
						pw.print(nf.format(dif) + "\t");
						pw.print(nf.format(ugm3) + "\t");
						pw.print(nf.format(calc1.calculeazaUprim(luna, ozonDobson,
								inaltimeCalcul)) + "\t");
						pw.print(nf.format(cfile.findTemperaturaSuprafata(siteName))
								+ "\t");
						pw.print(nf.format(cfile.findPresiuneSuprafata(siteName))
								+ "\t");
						pw.println();
						Double toput = mediiLunaOra.get(timp.getMonthOfYear()).get(
								timp.getHourOfDay());
						if (toput != null) {
							toput = (toput + ozonDobson) / 2;
						} else {
							toput = ozonDobson;
						}
						mediiLunaOra.get(timp.getMonthOfYear()).put(
								timp.getHourOfDay(), toput);
					}
				}
				cfile.close();
				pw.flush();
			}
			// printeaza medii
			System.out.println("Printeaza medii orare lunare:"
					+ outputMedii.getCanonicalPath());
			PrintWriter pmo = new PrintWriter(outputMedii);
			for (int h = 1; h <= 24; h++) {
				pmo.format("%02d \t", h);
				for (int m = 1; m <= 12; m++) {
					pmo.format("%05.5f \t",
							mediiLunaOra.get(m).get(h) != null ? mediiLunaOra.get(m)
									.get(h) : 0d);
				}
				pmo.format("\n");
			}
			pmo.flush();
			pmo.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Exceptie in:" + e);
		} finally {
		}
		System.out.println("End");
	}
}
