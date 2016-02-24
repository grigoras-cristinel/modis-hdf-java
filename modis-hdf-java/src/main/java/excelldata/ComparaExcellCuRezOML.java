package excelldata;

import hdfextractor.StatiiMeteoBucuresti;

import java.io.File;
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
public class ComparaExcellCuRezOML {
	private final int decalareInOreRezOml = 3;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start ComparaExcellCuRezOML fara meteo");
		ComparaExcellCuRezOML worker = new ComparaExcellCuRezOML();
		worker.execute();
		System.out.println("End");
	}

	/**
	 * 
	 */
	private void execute() {
		String excell_citiri = "Ozon2009.xls";
		// String[] prefix = new String[] { "11_sat_AN_0h", "1_satelit",
		// "1_statie", "10_satelit0h", "10_statie0h", "11_sat_AN_0h",
		// "11_statie_AN_0h", "origmas", "origomi" };
		// String[] prefix = new String[] { "1_satbad", "1_statbad" };
		// String[] prefix = new String[] { "2_satelit", "2_statie", "3_statie",
		// "8_statie", "9_statie", "10_S0h", "10_st0h" };
		String[] prefix = new String[] { "14", "15", "16", "17", "18", "18sta",
				"20satN1m", "20staN1m" };
		// String combinedIn =
		// "d:/proiecte/2011-satelit-ozon/oml/surse/dump-receptori-useomi.txt";
		for (String zona : prefix) {
			String combinedIn = "d:/proiecte/2011-satelit-ozon/oml/surse/dump-receptori_set_"
					+ zona + ".txt";
			File a = new File(combinedIn);
			if (!a.exists()) {
				throw new RuntimeException("Lipseste fisier:"
						+ a.getAbsolutePath());
			}
		}
		for (String zona : prefix) {
			String combinedIn = "d:/proiecte/2011-satelit-ozon/oml/surse/dump-receptori_set_"
					+ zona + ".txt";
			System.out.println("Fisier intrare " + combinedIn + " .");
			String prefixFisierIesire = "target/H2009/" + zona + "-compara-";
			System.out.println("Fisier iesire " + prefixFisierIesire + " .");
			for (Entry<String, double[]> statie : StatiiMeteoBucuresti.statii2
					.entrySet()) {
				/*
				 * "comparare-" se modifica cu numele fisierului de iesire dorit
				 */
				String combinedOut = prefixFisierIesire + statie.getKey()
						+ ".txt";
				TreeMap<DateTime, Double> valoriModelate = new TreeMap<DateTime, Double>();
				try {
					ArrayList<PerecheDouble> bootF0 = new ArrayList<PerecheDouble>();
					ArrayList<PerecheDouble> bootVara = new ArrayList<PerecheDouble>();
					ArrayList<PerecheDouble> bootIarna = new ArrayList<PerecheDouble>();
					TreeMap<DateTime, PerecheD> map1 = new TreeMap<DateTime, PerecheD>();
					valoriModelate = citesteValoriOzonOML(combinedIn, statie);
					System.out.println(statie.getKey() + " Am gasit:"
							+ valoriModelate.size());
					TreeMap<DateTime, Double> valoriMasurate = citesteValoriMasurateOzon(
							excell_citiri, statie);
					System.out.println(statie.getKey() + " Am gasit excell:"
							+ valoriMasurate.size());
					PrintWriter outFile = new PrintWriter(new FileWriter(
							combinedOut));
					outFile.format("%16s\t%16s\t\t%16s\t%16s\n", " Data ",
							" Ora ", " Ozon la statie ", " Ozon oml");
					DateTime cursor = new DateTime(2009, 01, 01, 01, 0, 0, 0);
					cursor = cursor.withZoneRetainFields(DateTimeZone.UTC);
					while (cursor.getYear() == 2009) {
						Double concentratieModelata = valoriModelate
								.get(cursor);
						Double concentratieMasurata = valoriMasurate
								.get(cursor);
						if (concentratieMasurata != null
								&& concentratieModelata != null
								&& concentratieModelata > 0) {
							outFile.format("%10s\t%5s\t\t%16.8f\t%16.8f\n",
									cursor.toString("yyMMdd"),
									cursor.toString("HH") + ":00",
									concentratieMasurata, concentratieModelata);
						} else if (concentratieMasurata == null
								&& concentratieModelata != null
								&& concentratieModelata > 0) {
							outFile.format("%10s\t%5s\t\t\t%16.8f\n",
									cursor.toString("yyMMdd"),
									cursor.toString("HH") + ":00",
									concentratieModelata);
						} else if (concentratieMasurata != null
								&& (concentratieModelata == null || concentratieModelata == 0)) {
							outFile.format("%10s\t%5s\t\t%16.8f\t\n",
									cursor.toString("yyMMdd"),
									cursor.toString("HH") + ":00",
									concentratieMasurata);
						} else {
							outFile.format("%10s\t%5s\t\t\t\n",
									cursor.toString("yyMMdd"),
									cursor.toString("HH") + ":00");
						}
						outFile.flush();
						cursor = cursor.plusHours(1);
					}
					for (Entry<DateTime, Double> ib1mas : valoriMasurate
							.entrySet()) {
						String luna = (ib1mas.getKey().getMonthOfYear() + 1)
								+ "";
						if (valoriModelate.containsKey(ib1mas.getKey())) {
							double concentratieModelata = valoriModelate
									.get(ib1mas.getKey());
							map1.put(ib1mas.getKey(),
									new PerecheD(ib1mas.getValue(),
											concentratieModelata));
							if (ib1mas.getValue() == 0) {
								continue;
							}
							if (concentratieModelata == 0) {
								continue;
							}
							PerecheDouble p = new PerecheDouble(
									ib1mas.getValue(), concentratieModelata);
							bootF0.add(p);
							switch (ib1mas.getKey().getMonthOfYear()) {
							case 1:
							case 2:
							case 3:
							case 10:
							case 11:
							case 12:
								bootIarna.add(p);
								break;
							default:
								bootVara.add(p);
							}
						}
					}
					creazaFisierBoot(zona, prefixFisierIesire + "_boot_",
							bootF0, statie.getKey() + "_0", "O");
					creazaFisierMediiLunare(zona,
							prefixFisierIesire + "_medl_", map1,
							statie.getKey(), "O");
					creazaFisierBoot(zona, prefixFisierIesire + "_boot_",
							bootVara, statie.getKey() + "_V", "O");
					creazaFisierBoot(zona, prefixFisierIesire + "_boot_",
							bootIarna, statie.getKey() + "_I", "O");
					outFile.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		}
	}

	private TreeMap<DateTime, Double> citesteValoriOzonOML(String combinedIn,
			Entry<String, double[]> statie) throws FileNotFoundException,
			IOException {
		TreeMap<DateTime, Double> valoriOzonDobson = new TreeMap<DateTime, Double>();
		Reader textFileReader = new InputStreamReader(new FileInputStream(
				combinedIn));
		Scanner rn = new Scanner(textFileReader);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyMMdd").withZone(
				DateTimeZone.UTC);
		int contor = 0;
		while (rn.hasNextLine()) {
			String line = rn.nextLine();
			contor++;
			if (contor <= 3) {
				continue;
			}
			Scanner rnl = new Scanner(line);
			String data = rnl.next();
			if (data.length() < 6) {
				data = "0" + data;
			}
			DateTime timp = fmt.parseDateTime(data);
			int ora = rnl.nextInt();
			timp = timp.withHourOfDay(ora);
			DateTime dateKey = timp.plusHours(decalareInOreRezOml).hourOfDay()
					.roundFloorCopy();
			double ozon = 0;
			rnl.nextInt(); // a doua ora
			if ("B1".equals(statie.getKey())) {
				int steper = 3;
				while (steper > 0) {
					ozon = rnl.nextDouble();
					steper--;
				}
			} else if ("B2".equals(statie.getKey())) {
				int steper = 6;
				while (steper > 0) {
					ozon = rnl.nextDouble();
					steper--;
				}
			} else if ("B3".equals(statie.getKey())) {
				int steper = 9;
				while (steper > 0) {
					ozon = rnl.nextDouble();
					steper--;
				}
			} else if ("B4".equals(statie.getKey())) {
				int steper = 12;
				while (steper > 0) {
					ozon = rnl.nextDouble();
					steper--;
				}
			} else if ("B5".equals(statie.getKey())) {
				int steper = 15;
				while (steper > 0) {
					ozon = rnl.nextDouble();
					steper--;
				}
			} else if ("B6".equals(statie.getKey())) {
				int steper = 18;
				while (steper > 0) {
					ozon = rnl.nextDouble();
					steper--;
				}
			} else if ("B7".equals(statie.getKey())) {
				int steper = 21;
				while (steper > 0) {
					ozon = rnl.nextDouble();
					steper--;
				}
			} else if ("B8".equals(statie.getKey())) {
				break;
			}
			valoriOzonDobson.put(dateKey, ozon);
		}
		textFileReader.close();
		return valoriOzonDobson;
	}

	private TreeMap<DateTime, Double> citesteValoriMasurateOzon(
			String excell_citiri, Entry<String, double[]> statie)
			throws IOException, FileNotFoundException {
		Workbook wb = new HSSFWorkbook(new FileInputStream(excell_citiri));
		Sheet sheet = wb.getSheet("ozon");
		Iterator<Row> it = sheet.rowIterator();
		TreeMap<DateTime, Double> valoriOzonExcell = new TreeMap<DateTime, Double>();
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
					DateTime usF = new DateTime(timp.getTime(),
							DateTimeZone.forID("Europe/Bucharest"))
							.withZoneRetainFields(DateTimeZone.UTC);
					usF = usF.withHourOfDay(time);
					DateTime key = usF;
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

	private void creazaFisierMediiLunare(String satelit,
			String prefixFisierBoot, TreeMap<DateTime, PerecheD> map1,
			String numeBoot1, String indice) throws IOException {
		PrintWriter bootFile = new PrintWriter(new FileWriter(prefixFisierBoot
				+ numeBoot1 + ".txt"));
		bootFile.format("%-25s\n", "medie lunara ");
		TreeMap<Integer, PerecheD> medii = new TreeMap<Integer, PerecheD>();
		for (Entry<DateTime, PerecheD> vol : map1.entrySet()) {
			int luna = vol.getKey().getMonthOfYear();
			PerecheD val = vol.getValue();
			if (medii.containsKey(luna)) {
				PerecheD exista = medii.get(luna);
				exista.v1 = ((exista.v1 + val.v1) / 2);
				exista.v2 = ((exista.v2 + val.v2) / 2);
			} else {
				medii.put(luna, val);
			}
		}
		for (Entry<Integer, PerecheD> vol : medii.entrySet()) {
			bootFile.format("%-4d %10.6f %10.6f\n", vol.getKey(),
					vol.getValue().v1, vol.getValue().v2);
		}
		bootFile.flush();
		bootFile.close();
	}
}
