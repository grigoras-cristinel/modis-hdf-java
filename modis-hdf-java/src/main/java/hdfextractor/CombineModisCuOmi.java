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

public class CombineModisCuOmi {
	public static void main(String[] args) {
		System.out.println("Start.");
		for (Entry<String, double[]> a : StatiiMeteoBucuresti.statii.entrySet()) {
			String omiIn = "target/omi-2009-" + a.getKey() + ".txt";
			String modisIn = "target/modis-2009-" + a.getKey() + ".txt";
			String outCom = "target/combined-2009-" + a.getKey() + ".txt";
			CombineModisCuOmi wk;
			try {
				wk = new CombineModisCuOmi(omiIn, modisIn, outCom);
				wk.execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("End.");
	}

	private final Reader omiReader;
	private final Reader modisReader;
	private final String outFile;

	public CombineModisCuOmi(String omiFile, String modisFile, String outputFile)
			throws Exception {
		super();
		omiReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(omiFile)));
		modisReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(modisFile)));
		this.outFile = outputFile;
	}

	public void execute() throws Exception {
		TreeMap<DateTime, Double> mapDubluOzon = new TreeMap<DateTime, Double>();
		Scanner omiScaner = new Scanner(omiReader);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm")
				.withZone(DateTimeZone.forID("Europe/Bucharest"));
		DateTimeFormatter fmto = DateTimeFormat
				.forPattern("yyyy-MM-dd'T'HH:mm").withZone(
						DateTimeZone.forID("Europe/Bucharest"));
		omiScaner.nextLine();// prima linie
		while (omiScaner.hasNextLine()) {
			String line = omiScaner.nextLine();
			Scanner rnl = new Scanner(line);
			DateTime timp = fmt.parseDateTime(rnl.next());
			Double ozonAT = rnl.nextDouble();// ozon
			mapDubluOzon.put(timp, ozonAT);
		}
		Scanner modisScaner = new Scanner(modisReader);
		modisScaner.nextLine();// prima linie
		while (modisScaner.hasNextLine()) {
			String line = modisScaner.nextLine();
			Scanner rnl = new Scanner(line);
			DateTime timp = fmt.parseDateTime(rnl.next());
			Double ozonAT = rnl.nextDouble();// ozon
			mapDubluOzon.put(timp, ozonAT);
		}
		PrintWriter prw = new PrintWriter(new File(outFile));
		prw.format("%16s\t%16s\n", "Data", "Valoare");
		for (Entry<DateTime, Double> fg : mapDubluOzon.entrySet()) {
			prw.format("%16s\t%16.8f\n", fg.getKey().toString(fmto),
					fg.getValue());
		}
		prw.flush();
	}
}
