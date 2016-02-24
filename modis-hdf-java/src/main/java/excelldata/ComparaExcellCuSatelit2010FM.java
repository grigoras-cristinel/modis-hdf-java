package excelldata;

import graph.GraphComparare;
import hdfextractor.StatiiMeteoBucuresti;
import hdfextractor.ozon.CalculeazaOzonLaSol;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Aceasta clasa va citi ozonul extras din satelit in dobson si va crea un
 * dataset cu ozonul din satelit si cel masurat la sol din fisierul excell
 * 
 * @author Grig
 * 
 */
public class ComparaExcellCuSatelit2010FM {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start ComparaExcellCuSatelit2010 fara meteo");
		ComparaExcellCuSatelit2010FM worker = new ComparaExcellCuSatelit2010FM();
		worker.execute();
		System.out.println("End");
	}

	/**
	 * 
	 */
	private void execute() {
		String excell_citiri = "Ozon2010.xls";
		String zona = "omi";
		String prefixFisierIntrare = "target/" + zona + "-2010-";
		System.out.println("Fisier intrare " + prefixFisierIntrare + " .");
		String prefixFisierIesire = "target/A2010/" + zona + "-compara-2010-";
		System.out.println("Fisier iesire " + prefixFisierIesire + " .");
		String formatKeyMap = "yyyy-MM-dd-HH";
		String meteoFileName = "meteo-2010.dat";
		double h = 10d;
		for (Entry<String, double[]> statie : StatiiMeteoBucuresti.statii
				.entrySet()) {
			/*
			 * Se inlocuieste "combined-statie-" cu numele altui fisier de date
			 * satelitare (OMI sau MODIS)
			 */
			String combinedIn = prefixFisierIntrare + statie.getKey() + ".txt";
			/*
			 * "comparare-" se modifica cu numele fisierului de iesire dorit
			 */
			String combinedOut = prefixFisierIesire + statie.getKey() + ".txt";
			TreeMap<String, Double> valoriOzonDobson = new TreeMap<String, Double>();
			TreeMap<String, Double> valoriTemp = new TreeMap<String, Double>();
			TreeMap<String, Double> valoriPresiune = new TreeMap<String, Double>();
			TreeMap<String, Integer> valoriNebulozitate = new TreeMap<String, Integer>();
			try {
				Reader readFileTemp = new InputStreamReader(
						new FileInputStream(meteoFileName));
				// 2009-01-01 00:00:00.0
				DateTimeFormatter fmtFileMeteo = DateTimeFormat.forPattern(
						"yyyy-MM-dd HH:mm:ss.S").withZone(DateTimeZone.UTC);
				int contorFm = 0;
				ArrayList<PerecheDouble> bootF0 = new ArrayList<PerecheDouble>();
				Scanner readFileTempScan = new Scanner(readFileTemp);
				while (readFileTempScan.hasNextLine()) {
					String line = readFileTempScan.nextLine();
					contorFm++;
					if (contorFm == 1) {
						continue;
					}
					Scanner rnl = new Scanner(line);
					rnl.useDelimiter(",");
					String data = rnl.next();
					DateTime timp = fmtFileMeteo.parseDateTime(data);
					String dateKey = timp.toString(formatKeyMap);
					int nebulozitate = 0;
					try {
						String snebulozitate = rnl.next();
						nebulozitate = Integer.parseInt(snebulozitate);
					} catch (Exception e) {
						System.err
								.println("Eroare fisier meteo, nebulozitate linia:"
										+ line);
					}
					valoriNebulozitate.put(dateKey, nebulozitate);
					double temperatura = 0d;
					try {
						String stemperatura = rnl.next();
						temperatura = Double.parseDouble(stemperatura);
					} catch (Exception e) {
						System.err
								.println("Eroare fisier meteo, temperatura linia:"
										+ line);
					}
					valoriTemp.put(dateKey, temperatura + 273.15d);
					double presiune = 0d;
					try {
						String spresiune = rnl.next();
						presiune = Double.parseDouble(spresiune);
					} catch (Exception e) {
						System.err
								.println("Eroare fisier meteo, presiune linia:"
										+ line);
					}
					valoriPresiune.put(dateKey, presiune);
				}
				readFileTemp.close();
				valoriOzonDobson = citesteValoriSatelitOzon(formatKeyMap,
						combinedIn);
				System.out.println(statie.getKey() + " Am gasit:"
						+ valoriOzonDobson.size());
				TreeMap<String, Double> valoriOzonExcell = citesteValoriMasurateOzon(
						excell_citiri, formatKeyMap, statie);
				System.out.println(statie.getKey() + " Am gasit excell:"
						+ valoriOzonExcell.size());
				PrintWriter outFile = new PrintWriter(new FileWriter(
						combinedOut));
				outFile.format(
						"%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\n",
						"Data " + formatKeyMap, " Ozon la statie ug ",
						" Ozon ug 10m ", " Cor. ug 10m ",
						" Valoare in dobson ", " Temperatura ", " Presiune ",
						" Inaltime amestec ", " Nebulozitate ");
				CalculeazaOzonLaSol calc = new CalculeazaOzonLaSol();
				GraphComparare grph = new GraphComparare(prefixFisierIesire
						+ zona + statie.getKey() + ".png");
				for (Entry<String, Double> ib1mas : valoriOzonExcell.entrySet()) {
					String luna = ib1mas.getKey().split("-")[1];
					DateTimeFormatter fmtK = DateTimeFormat.forPattern(
							formatKeyMap).withZone(DateTimeZone.UTC);
					DateTime data = fmtK.parseDateTime(ib1mas.getKey());
					if (valoriOzonDobson.containsKey(ib1mas.getKey())) {
						double calculeazaConcentratieugm3 = calc
								.calculeazaConcentratieugm3(
										Integer.parseInt(luna) - 1,
										valoriOzonDobson.get(ib1mas.getKey()),
										h);
						Double presiune = valoriPresiune.get(ib1mas.getKey());
						Double temperatura = valoriTemp.get(ib1mas.getKey());
						// grph.addData("temperatura", data, temperatura);
						// grph.addData("presiune", data, presiune);
						grph.addData("MC", data, ib1mas.getValue());
						double concentratieCuCorectie = calculeazaConcentratieugm3;
						if (presiune != null && temperatura != null) {
							concentratieCuCorectie = calculeazaConcentratieugm3
									* (1013 / presiune)
									* (temperatura / 273.15);
						}
						grph.addData("CC", data, concentratieCuCorectie);
						bootF0.add(new PerecheDouble(ib1mas.getValue(),
								concentratieCuCorectie));
						outFile.format(
								"%10s\t%5s\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16d\n",
								ib1mas.getKey().substring(0, 10), ib1mas
										.getKey().substring(11) + ":00",
								ib1mas.getValue(), calculeazaConcentratieugm3,
								concentratieCuCorectie,
								valoriOzonDobson.get(ib1mas.getKey()),
								temperatura, presiune, 0d,
								valoriNebulozitate.get(ib1mas.getKey()));
						outFile.flush();
					}
				}
				creazaFisierBoot(zona, prefixFisierIesire + "_boot_", bootF0,
						statie.getKey() + "_0", "O");
				outFile.close();
				grph.printChart(statie.getKey(), 2010);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	private TreeMap<String, Double> citesteValoriSatelitOzon(
			String formatKeyMap, String combinedIn)
			throws FileNotFoundException, IOException {
		TreeMap<String, Double> valoriOzonDobson = new TreeMap<String, Double>();
		Reader textFileReader = new InputStreamReader(new FileInputStream(
				combinedIn));
		Scanner rn = new Scanner(textFileReader);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm")
				.withZone(DateTimeZone.UTC);
		int contor = 0;
		while (rn.hasNextLine()) {
			String line = rn.nextLine();
			contor++;
			if (contor == 1) {
				continue;
			}
			Scanner rnl = new Scanner(line);
			String data = rnl.next();
			DateTime timp = fmt.parseDateTime(data);
			Double ozon = rnl.nextDouble();// CM
			String dateKey = timp.toString(formatKeyMap);
			if (ozon > 0) {
				if (valoriOzonDobson.containsKey(dateKey)) {
					double temp = valoriOzonDobson.get(dateKey);
					valoriOzonDobson.put(dateKey, (ozon + temp) / 2);
				} else {
					valoriOzonDobson.put(dateKey, ozon);
				}
			}
		}
		textFileReader.close();
		return valoriOzonDobson;
	}

	private TreeMap<String, Double> citesteValoriMasurateOzon(
			String excell_citiri, String formatKeyMap,
			Entry<String, double[]> statie) throws IOException,
			FileNotFoundException {
		Workbook wb = new HSSFWorkbook(new FileInputStream(excell_citiri));
		Sheet sheet = wb.getSheet("ozon");
		Iterator<Row> it = sheet.rowIterator();
		TreeMap<String, Double> valoriOzonExcell = new TreeMap<String, Double>();
		int numarColoanaStatie = -1;
		while (it.hasNext()) {
			Row row = it.next();
			if (row.getRowNum() == 0) {
				Iterator<Cell> itCel = row.cellIterator();
				while (itCel.hasNext()) {
					Cell cell = itCel.next();
					String strGasit = ExcellUtils.readStringFromCell(cell);
					if (StringUtils.equalsIgnoreCase(strGasit, statie.getKey())) {
						numarColoanaStatie = cell.getColumnIndex();
					}
				}
				continue;
			}
			if (row.getCell(1) != null && row.getCell(0) != null) {
				Date ceas = ExcellUtils.readTimeValueWithDefault(row, 1, null);
				if (ceas == null) {
					continue;
				}
				int time = ceas.getHours();
				Double masicInStatie = 0d;
				try {
					masicInStatie = ExcellUtils.readDouble(row,
							numarColoanaStatie);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				if (masicInStatie != null) {
					Date timp = ExcellUtils.readDateValueWithDefault(row, 0,
							null);
					DateTime usF = new DateTime(timp,
							DateTimeZone.forID("Europe/Bucharest"));
					usF = usF.withHourOfDay(time);
					String key = usF.toString(formatKeyMap);
					if (valoriOzonExcell.containsKey(key)) {
						double temp = valoriOzonExcell.get(key);
						valoriOzonExcell.put(key, (temp + masicInStatie) / 2);
					} else {
						valoriOzonExcell.put(key, masicInStatie);
					}
				}
			}
		}
		return valoriOzonExcell;
	}

	private void creazaFisierBoot(String satelit, String prefixFisierBoot,
			ArrayList<PerecheDouble> valori, String numeBoot1, String indice)
			throws IOException {
		PrintWriter bootFile = new PrintWriter(new FileWriter(prefixFisierBoot
				+ numeBoot1 + ".boxt"));
		bootFile.format("%-25s1YFNNNN\n", satelit + numeBoot1 + "-b.out");
		bootFile.format("%5d %5d %5d\n", valori.size(), 2, 1);
		bootFile.format("%5d\n", valori.size());
		bootFile.format("'%8s' '%8s'\n", "Masurat", "Calcul_" + indice);
		bootFile.format("'%20s'\n", satelit + numeBoot1);
		for (PerecheDouble perecheDouble : valori) {
			bootFile.format("%10.6f %10.6f\n", perecheDouble.v1,
					perecheDouble.v2);
		}
		bootFile.flush();
		bootFile.close();
	}
}
