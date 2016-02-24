package hdfextractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * O sa incarce fisierele extrase din aqua si modis 07 si le va face intr-un
 * singur folder sortate dupa data.
 * 
 * @author geo-track idx264
 * 
 */
public class CombineModisTerraAndAqua {
	public static void main(String[] args) {
		System.out.println("Start.");
		String anii[] = new String[] { "2008", "2009", "2010" };
		for (int i = 0; i < anii.length; i++) {
			String anul = anii[i];
			for (Entry<String, double[]> a : StatiiMeteoBucuresti.statii
					.entrySet()) {
				String mydIn = "C:/DATE-Satelit/myd07_L2_" + anul
						+ "/out/ozon_myd07_" + anul + "_" + a.getKey() + ".txt";
				String modIn = "C:/DATE-Satelit/mod07_L2_" + anul
						+ "/out/ozon_mod07_" + anul + "_" + a.getKey() + ".txt";
				String outCom = "target/A2012/modis/ozon_myd_mod_" + anul + "-"
						+ a.getKey() + ".txt";
				CombineModisTerraAndAqua wk;
				try {
					wk = new CombineModisTerraAndAqua(a.getKey(), mydIn, modIn,
							outCom);
					wk.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("End.");
	}

	private final Reader file1Reader;
	private final Reader file2Reader;
	private final String outFile;
	private final String site;

	public CombineModisTerraAndAqua(String site, String omiFile,
			String modisFile, String outputFile) throws Exception {
		super();
		this.site = site;
		file1Reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(omiFile)));
		file2Reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(modisFile)));
		this.outFile = outputFile;
	}

	public void execute() throws Exception {
		TreeMap<DateTime, OzonLineTO> mapDubluOzon = new TreeMap<DateTime, OzonLineTO>();
		Scanner file1Scanner = new Scanner(file1Reader);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm")
				.withZone(DateTimeZone.forID("Europe/Bucharest"));
		DateTimeFormatter fmto = DateTimeFormat
				.forPattern("yyyy-MM-dd'T'HH:mm").withZone(
						DateTimeZone.forID("Europe/Bucharest"));
		file1Scanner.nextLine();// prima linie
		while (file1Scanner.hasNextLine()) {
			String line = file1Scanner.nextLine();
			OzonLineTO l = new OzonLineTO();
			l = l.toOzonLineTOfromLongLine(line);
			mapDubluOzon.put(l.data, l);
		}
		Scanner modisScaner = new Scanner(file2Reader);
		modisScaner.nextLine();// prima linie
		while (modisScaner.hasNextLine()) {
			String line = modisScaner.nextLine();
			OzonLineTO l = new OzonLineTO();
			l = l.toOzonLineTOfromLongLine(line);
			mapDubluOzon.put(l.data, l);
		}
		PrintWriter prw = new PrintWriter(new File(outFile));
		prw.println(OzonLineTO.longHeader());
		for (Entry<DateTime, OzonLineTO> fg : mapDubluOzon.entrySet()) {
			prw.println(fg.getValue().toLongLine());
		}
		prw.flush();
	}
}
