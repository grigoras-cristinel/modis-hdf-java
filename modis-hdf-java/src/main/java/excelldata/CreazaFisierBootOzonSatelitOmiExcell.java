package excelldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import hdfextractor.OzonLineTO;
import hdfextractor.StatiiMeteoBucuresti;
import hdfextractor.ozon.CalculeazaOzonLaSol;

/**
 * Aceasta clasa va citi ozonul extras din satelit in dobson si va crea un
 * dataset cu ozonul din satelit si cel masurat la sol din fisierul excell
 * 
 * @author Grig
 * 
 */
public class CreazaFisierBootOzonSatelitOmiExcell {

	private static final String WORKSPACE = "D:/proiecte/java/workspace_ozon/hdfextractor/target/";
	private static final String DATESTATIE = "D:/proiecte/java/workspace_ozon/hdfextractor/data/";

	public CreazaFisierBootOzonSatelitOmiExcell(String string) {
		this.anul = string;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start ComparaExcellCuSatelit2009");
		String[] anii = new String[] { "2009" };
		for (String an : anii) {
			CreazaFisierBootOzonSatelitOmiExcell worker = new CreazaFisierBootOzonSatelitOmiExcell(an);
			worker.execute();
		}
		System.out.println("End");
	}

	TreeMap<String, double[]> statii = StatiiMeteoBucuresti.statii;
	String formatKeyMap = "yyyy-MM-dd-kk";
	DateTimeFormatter fmt = DateTimeFormat.forPattern(formatKeyMap).withZone(DateTimeZone.forOffsetHours(2));
	String anul;

	/**
	* 
	*/
	private void execute() {
		String workfolder = WORKSPACE + "";
		String prefixFisierIntrare = workfolder + "omi-" + anul + "-";
		// String prefixFisierIntrare = "target/modis-2009-output-";
		// String prefixFisierIntrare = "target/modis-2009-output-";
		System.out.println("Fisier intrare" + prefixFisierIntrare + " .");
		// String prefixFisierIesire = "target/omi-compara-2009-";
		// String prefixFisierIesire = "target/modis-statii-2009-";
		String prefixFisierIesire = workfolder + "boot-" + anul + "-";
		System.out.println("Fisier iesire" + prefixFisierIesire + " .");
		String excell_citiri = DATESTATIE + "Ozon2009.xls";
		System.out.println("Fisier masuratori statii" + excell_citiri + " .");
		double h = 10d;
		TreeMap<DateTime, LinieDateMeteoTO> meteo = citesteDateMeteo(anul);
		for (Entry<String, double[]> statie : statii.entrySet()) {
			/*
			 * Se inlocuieste "combined-statie-" cu numele altui fisier de date
			 * satelitare (OMI sau MODIS)
			 */
			String combinedIn = prefixFisierIntrare + statie.getKey() + ".txt";
			/*
			 * "comparare-" se modifica cu numele fisierului de iesire dorit
			 */
			String combinedOut = prefixFisierIesire + statie.getKey() + ".txt";
			ArrayList<PerecheDouble> bootF0 = new ArrayList<PerecheDouble>();
			ArrayList<PerecheDouble> bootF1 = new ArrayList<PerecheDouble>();
			int coloana = 2;
			switch (statie.getKey()) {
			case "B1":
				coloana = 2;
				break;
			case "B2":
				coloana = 3;
				break;
			case "B3":
				coloana = 4;
				break;
			case "B4":
				coloana = 5;
				break;
			case "B5":
				coloana = 6;
				break;
			case "B6":
				coloana = 7;
				break;
			case "B7":
				coloana = 8;
				break;
			case "B8":
				coloana = 9;
				break;
			case "AN":
				coloana = 10;
				break;
			default:
				break;
			}
			try {
				TreeMap<DateTime, OzonLineTO> valoriOzonDobson = new TreeMap<DateTime, OzonLineTO>();
				valoriOzonDobson = citesteValoriSatelitOzon(combinedIn);
				System.out.println("Satelit:" + combinedIn + "; Am gasit:" + valoriOzonDobson.size());
				TreeMap<DateTime, CitiriLineTO> valoriOzonExcell = citesteValoriMasurateOzonStatieNumarulColoanei(
						excell_citiri, coloana);
				System.out.println(" Am gasit excell:" + valoriOzonExcell.size());
				for (Entry<DateTime, OzonLineTO> ib1mas : valoriOzonDobson.entrySet()) {
					// System.err.println(" dobson data:" + ib1mas.getKey());
				}
				PrintWriter outFile = new PrintWriter(new FileWriter(combinedOut));
				outFile.format("%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\n", "Data " + formatKeyMap,
						" Ozon la statie ug ", " Ozon ug 10m ", " Cor. ug 10m ", " Valoare in dobson ", " Temperatura ",
						" Presiune ", " Inaltime amestec ", " Nebulozitate ");
				CalculeazaOzonLaSol calc = new CalculeazaOzonLaSol();
				for (Entry<DateTime, CitiriLineTO> ib1mas : valoriOzonExcell.entrySet()) {
					CitiriLineTO citVal = ib1mas.getValue();
					Integer luna = ib1mas.getKey().getMonthOfYear() - 1;
					if (valoriOzonDobson.containsKey(ib1mas.getKey())) {
						OzonLineTO ozSat = valoriOzonDobson.get(ib1mas.getKey());
						double calculeazaConcentratieugm3 = calc.calculeazaConcentratieugm3(luna, ozSat.ozoneTotal, h);
						Double presiune = meteo.get(ib1mas.getKey()).presiuneColoana;
						Double temperatura = meteo.get(ib1mas.getKey()).temperaturaCelsius + 273.15;
						if (presiune == null || temperatura == null) {
							System.out.println(ib1mas.getKey() + " Am null:" + presiune + "  -- " + temperatura);
							continue;
						}
						double concentratieCuCorectie = calculeazaConcentratieugm3 * (1013 / presiune)
								* (temperatura / 273.15);
						outFile.format("%10s\t%5s\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16d\n",
								ib1mas.getKey()
										.toString(DateTimeFormat.forPattern("yyyy-MM-dd")
												.withZone(DateTimeZone.forOffsetHours(2))),
								ib1mas.getKey()
										.toString(DateTimeFormat.forPattern("kk:00")
												.withZone(DateTimeZone.forOffsetHours(2))),
								citVal.cozon, calculeazaConcentratieugm3, concentratieCuCorectie, ozSat.microGrame15m,
								temperatura, presiune, 0d, 0);
						outFile.flush();
						PerecheDouble p = new PerecheDouble(citVal.cozon, concentratieCuCorectie);
						bootF0.add(p);
						PerecheDouble p1 = new PerecheDouble(citVal.cozon, calculeazaConcentratieugm3);
						bootF1.add(p1);
					}
				}
				creazaFisierBoot("omi" + anul, prefixFisierIesire + "_bt_", bootF0, statie.getKey() + "_0", "O");
				creazaFisierBoot("omi" + anul, prefixFisierIesire + "_bt_", bootF1, statie.getKey() + "_1", "1");
				outFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private TreeMap<DateTime, OzonLineTO> citesteValoriSatelitOzon(String combinedIn)
			throws FileNotFoundException, IOException {
		TreeMap<DateTime, OzonLineTO> valoriOzonDobson = new TreeMap<DateTime, OzonLineTO>();
		Reader textFileReader = new InputStreamReader(new FileInputStream(combinedIn));
		Scanner rn = new Scanner(textFileReader);
		int contor = 0;
		while (rn.hasNextLine()) {
			String line = rn.nextLine();
			contor++;
			if (contor == 1) {
				continue;
			}
			OzonLineTO l = new OzonLineTO();
			l = l.toOzonLineTOfromShortLine(line);
			l.data = l.data.hourOfDay().roundFloorCopy();
			if (l.ozoneTotal > 0) {
				if (valoriOzonDobson.containsKey(l.data)) {
					l.mean(valoriOzonDobson.get(l.data));
					valoriOzonDobson.put(l.data, l);
				} else {
					valoriOzonDobson.put(l.data, l);
				}
			}
		}
		textFileReader.close();
		return valoriOzonDobson;
	}

	private TreeMap<DateTime, CitiriLineTO> citesteValoriMasurateOzonStatieNumarulColoanei(String excell_citiri,
			int coloanaExcell) throws IOException, FileNotFoundException {
		Workbook wb = new HSSFWorkbook(new FileInputStream(excell_citiri));
		Sheet sheet = wb.getSheet("ozon");
		TreeMap<DateTime, CitiriLineTO> retval = new TreeMap<DateTime, CitiriLineTO>();
		Iterator<Row> it = sheet.rowIterator();
		while (it.hasNext()) {
			Row row = it.next();
			if (row.getRowNum() < 2) {
				/*
				 * header
				 */
				continue;
			}
			if (row.getCell(1) != null && row.getCell(0) != null) {
				CitiriLineTO res = new CitiriLineTO();
				String ceas = ExcellUtils.readStringValue(row, 1);
				if (ceas == null) {
					continue;
				}
				String timp = ExcellUtils.readStringValue(row, 0);
				DateTime usCeas = DateTimeFormat.forPattern("kk:mm").withZone(DateTimeZone.forOffsetHours(2))
						.parseDateTime(ceas);
				DateTime usF = DateTimeFormat.forPattern("dd/MM/YYYY").withZone(DateTimeZone.forOffsetHours(2))
						.parseDateTime(timp);
				if (usCeas.hourOfDay().get() == 0) {
					usF = usF.dayOfYear().addToCopy(1);
				}
				usF = usF.withTime(usCeas.getHourOfDay(), usCeas.getMinuteOfHour(), 0, 0);
				usF = usF.hourOfDay().roundFloorCopy();
				res.data = usF;
				// System.out.println(" Citesc:" + timp + " " + ceas + ": " +
				// usF);
				Double ozon = 0d;
				try {
					ozon = ExcellUtils.readDouble(row, coloanaExcell);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				Double temp2m = 0d;
				try {
					temp2m = ExcellUtils.readDouble(row, 12);
					if (temp2m != null) {
						res.areTemperatura = true;
						res.temperatura = temp2m + 273.15;
					}
				} catch (Exception e) {
					System.out.println("" + e.getMessage());
					continue;
				}
				Double pres = 0d;
				try {
					pres = ExcellUtils.readDouble(row, 14);
					if (pres != null) {
						res.arePresiune = true;
						res.presiune = pres;
					}
				} catch (Exception e) {
					System.out.println("" + e.getMessage());
					continue;
				}
				if (ozon != null) {
					res.areOzon = true;
					res.cozon = ozon;
					retval.put(usF, res);
				}
			}
		}
		return retval;
	}

	private TreeMap<DateTime, LinieDateMeteoTO> citesteDateMeteo(String anul) {
		TreeMap<DateTime, LinieDateMeteoTO> retval = new TreeMap<DateTime, LinieDateMeteoTO>();
		Reader readFileTemp;
		int cline = 0;
		try {
			readFileTemp = new InputStreamReader(new FileInputStream(DATESTATIE + "meteo-" + anul + ".dat"));
			// 2009-01-01 00:00:00.0
			DateTimeFormatter fmtFileMeteo = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S")
					.withZone(DateTimeZone.UTC);
			int contorFm = 0;
			Scanner readFileTempScan = new Scanner(readFileTemp);
			while (readFileTempScan.hasNextLine()) {
				LinieDateMeteoTO ldm = new LinieDateMeteoTO();
				cline++;
				String line = readFileTempScan.nextLine();
				contorFm++;
				if (contorFm == 1) {
					continue;
				}
				Scanner rnl = new Scanner(line);
				rnl.useDelimiter(",");
				String data = rnl.next();
				DateTime timp = fmtFileMeteo.parseDateTime(data);
				timp = timp.hourOfDay().roundFloorCopy();
				int nebulozitate = 0;
				try {
					String snebulozitate = rnl.next();
					nebulozitate = Integer.parseInt(snebulozitate);
				} catch (Exception e) {
					System.err.println("Eroare fisier meteo, nebulozitate linia:" + line);
				}
				ldm.nebulozitate = nebulozitate;
				double temperatura = rnl.nextDouble();
				ldm.temperaturaCelsius = temperatura;
				double presiune = rnl.nextDouble();
				ldm.presiuneColoana = presiune;
				retval.put(timp, ldm);
			}
			readFileTemp.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Linia " + cline);
		}
		return retval;
	}

	private void creazaFisierBoot(String satelit, String prefixFisierBoot, ArrayList<PerecheDouble> valori,
			String numeBoot1, String indice) throws IOException {
		PrintWriter bootFile = new PrintWriter(new FileWriter(prefixFisierBoot + numeBoot1 + ".boxt"));
		bootFile.format("%-25s1YFNNNN\n", satelit + numeBoot1 + "-b.out");
		bootFile.format("%5d %5d %5d\n", valori.size(), 2, 1);
		bootFile.format("%5d\n", valori.size());
		bootFile.format("'%8s' '%8s'\n", "Masurat", "Calcul_" + indice);
		bootFile.format("'%20s'\n", satelit + numeBoot1);
		for (PerecheDouble perecheDouble : valori) {
			bootFile.format("%10.6f %10.6f\n", perecheDouble.v1, perecheDouble.v2);
		}
		bootFile.flush();
		bootFile.close();
	}
}
