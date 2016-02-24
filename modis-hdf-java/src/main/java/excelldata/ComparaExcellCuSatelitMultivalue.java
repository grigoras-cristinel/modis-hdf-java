package excelldata;

import flanagan.analysis.Stat;
import graph.GraphComparare;
import hdfextractor.StatiiMeteoBucuresti;
import hdfextractor.ozon.CalculeazaOzonLaSol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import excelldata.CitesteFisierRadiatieGlobala.LinieRadiatieGlobala;

/**
 * Aceasta clasa va citi ozonul extras din satelit in dobson si va crea un
 * dataset cu ozonul din satelit si cel masurat la sol din fisierul excell
 * 
 * @author Grig
 * 
 */
public class ComparaExcellCuSatelitMultivalue {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(ComparaExcellCuSatelitMultivalue.class);
	private static boolean folosesteMaxim24hDinMedie8ore = true;
	private static boolean folosesteCorectieStatii = true;
	private static HashMap<String, Double> cor2Statii = new HashMap<String, Double>();
	static {
		cor2Statii.put("AN", 1.20);
		cor2Statii.put("B1", 1.11);
		cor2Statii.put("B2", 0.84);
		cor2Statii.put("B3", 0.93);
		cor2Statii.put("B4", 1.11);
		cor2Statii.put("B5", 0.93);
		cor2Statii.put("B6", 0.20);
		cor2Statii.put("B7", 0.28);
		cor2Statii.put("B8", 1.20);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start ComparaExcellCuSatelit2009. ");
		ComparaExcellCuSatelitMultivalue worker = new ComparaExcellCuSatelitMultivalue();
		String serie = "8OraRadiat";
		worker.execute(2009, serie, "modis");
		worker.execute(2009, serie, "combined");
		worker.execute(2009, serie, "omi");
		worker.execute(2010, serie, "modis");
		worker.execute(2010, serie, "combined");
		worker.execute(2010, serie, "omi");
		System.out.println("End");
	}

	/**
	 * 
	 */
	private void execute(int anul, String serie, String satelit) {
		String excell_citiri = "Ozon" + anul + ".xls";
		String excell_date_amestec = "zmix_" + anul + ".dat";
		boolean citesteAmestec = false;
		File amestecFile = new File(excell_date_amestec);
		if (amestecFile.exists()) {
			System.out.println("Fisier ozon si meteo" + excell_citiri + ","
					+ excell_date_amestec + " .");
			citesteAmestec = true;
		}
		TreeMap<DateTime, Double> vRadiatieGlobala = null;
		if (anul == 2009) {
			vRadiatieGlobala = new TreeMap<DateTime, Double>();
			CitesteFisierRadiatieGlobala redF = new CitesteFisierRadiatieGlobala();
			try {
				HashMap<DateTime, LinieRadiatieGlobala> gasit = redF
						.citesteFisier("d:/proiecte/2011-satelit-ozon/radiatie globala abuc afumati.xls");
				for (Entry<DateTime, LinieRadiatieGlobala> ite : gasit
						.entrySet()) {
					if (ite.getValue().valoareRadiatieGlobala != null) {
						vRadiatieGlobala.put(ite.getKey(),
								ite.getValue().valoareRadiatieGlobala);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String prefixFisierIntrare = "target/" + satelit + "-" + anul + "-";
		// String prefixFisierIntrare = "target/modis-2009-output-";
		// String prefixFisierIntrare = "target/modis-2009-output-";
		System.out.println("Fisier intrare" + prefixFisierIntrare + " .");
		String folderOutput = "target/" + anul + "-" + serie + "/";
		File folder = new File(folderOutput);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String prefixFisierIesire = folderOutput + satelit + "-compara-";
		String prefixFisierBoot = folderOutput + satelit + "-boot-";
		// String prefixFisierIesire = "target/modis-statii-2009-";
		// String prefixFisierIesire = "target/2009/modis-";
		System.out.println("Fisier iesire: " + prefixFisierIesire + " .");
		String formatKeyMap = "yyyy-MM-dd-HH";
		if (folosesteMaxim24hDinMedie8ore) {
			formatKeyMap = "yyyy-MM-dd";
		}
		double h = 10d;
		for (Entry<String, double[]> statie : StatiiMeteoBucuresti.statii
				.entrySet()) {
			/*
			 * Se inlocuieste "combined-statie-" cu numele altui fisier de date
			 * satelitare (OMI sau MODIS)
			 */
			GraphComparare graph = new GraphComparare(prefixFisierIesire
					+ statie.getKey() + ".png");
			String fisierOzonSatelit = prefixFisierIntrare + statie.getKey()
					+ ".txt";
			logger.debug("File:" + fisierOzonSatelit);
			/*
			 * "comparare-" se modifica cu numele fisierului de iesire dorit
			 */
			String combinedOut = prefixFisierIesire + statie.getKey() + ".txt";
			TreeMap<String, Double> valoriOzonDobson = new TreeMap<String, Double>();
			TreeMap<String, Double> valoriHAmestec = new TreeMap<String, Double>();
			TreeMap<DateTime, Double> vHAmestec = new TreeMap<DateTime, Double>();
			TreeMap<String, Double> valoriTemp = new TreeMap<String, Double>();
			TreeMap<String, Double> valoriPresiune = new TreeMap<String, Double>();
			TreeMap<String, Integer> valoriNebulozitate = new TreeMap<String, Integer>();
			TreeMap<DateTime, Double> vTemp = new TreeMap<DateTime, Double>();
			TreeMap<DateTime, Double> vPresiune = new TreeMap<DateTime, Double>();
			TreeMap<DateTime, Integer> vNebulozitate = new TreeMap<DateTime, Integer>();
			try {
				if (citesteAmestec) {
					vHAmestec = citesteInaltimeAmesteDinOML(excell_date_amestec);
					valoriHAmestec = transformaFaraMediereD(formatKeyMap,
							vHAmestec);
				}
				Reader readFileTemp = new InputStreamReader(
						new FileInputStream("meteo-" + anul + ".dat"));
				// 2009-01-01 00:00:00.0
				DateTimeFormatter fmtFileMeteo = DateTimeFormat.forPattern(
						"yyyy-MM-dd HH:mm:ss.S").withZone(DateTimeZone.UTC);
				int contorFm = 0;
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
					int nebulozitate = 0;
					try {
						String snebulozitate = rnl.next();
						nebulozitate = Integer.parseInt(snebulozitate);
						vNebulozitate.put(timp, nebulozitate);
					} catch (Exception e) {
						// System.err
						// .println("Eroare fisier meteo, nebulozitate linia:"
						// + line);
					}
					double temperatura = 0;
					try {
						String st = rnl.next();
						temperatura = Double.parseDouble(st);
						vTemp.put(timp, temperatura + 273.15);
					} catch (Exception e) {
						//
					}
					double presiune = 0;
					try {
						String st = rnl.next();
						presiune = Double.parseDouble(st);
						vPresiune.put(timp, presiune);
					} catch (Exception e) {
						//
					}
				}
				readFileTemp.close();
				valoriNebulozitate = transformaFaraMediere(formatKeyMap,
						vNebulozitate);
				for (Entry<DateTime, Double> a : vTemp.entrySet()) {
					// graph.addData("T(C)", a.getKey(), a.getValue() - 273.15);
				}
				valoriTemp = transformaFaraMediereD(formatKeyMap, vTemp);
				valoriPresiune = transformaFaraMediereD(formatKeyMap, vPresiune);
				TreeMap<DateTime, Double> valMasurat = citesteValoriMasurateOzon(
						excell_citiri, statie.getKey());
				TreeMap<DateTime, Double> vDobson = citesteValoriSatelitOzon(fisierOzonSatelit);
				valMasurat = aduceDoarPerechileDinPrima(valMasurat, vDobson);
				TreeMap<DateTime, Double> vDobsonReparat = reparaValoriSatelit(
						vDobson, valMasurat);
				TreeMap<String, Double> valoriOzonDobsonReparat = transformaFaraMediereD(
						formatKeyMap, vDobsonReparat);
				valoriOzonDobson = transformaFaraMediereD(formatKeyMap, vDobson);
				System.out.println(statie.getKey() + " Am gasit:"
						+ valoriOzonDobson.size() + " valori dobson:"
						+ valoriOzonDobson.toString());
				System.out.println(statie.getKey() + " Am rpart:"
						+ valoriOzonDobsonReparat.size() + " valori dobson:"
						+ valoriOzonDobsonReparat.toString());
				for (Entry<DateTime, Double> a : valMasurat.entrySet()) {
					// graph.addData("VM", a.getKey(), a.getValue());
				}
				TreeMap<String, Double> valoriOzonExcell = transformaFaraMediereD(
						formatKeyMap, valMasurat);
				System.out.println(statie.getKey() + " Am masur:"
						+ valoriOzonExcell.size() + " valori masura:"
						+ valoriOzonExcell.toString());
				double minPres = 0;
				/*
				 * Numarul de linii pentru boot
				 */
				ArrayList<PerecheDouble> bootF0 = new ArrayList<PerecheDouble>();
				ArrayList<PerecheDouble> bootFD = new ArrayList<PerecheDouble>();
				ArrayList<PerecheDouble> bootFA = new ArrayList<PerecheDouble>();
				ArrayList<PerecheDouble> bootFH = new ArrayList<PerecheDouble>();
				ArrayList<PerecheDouble> bootFC = new ArrayList<PerecheDouble>();
				ArrayList<PerecheDouble> bootRadiatie = new ArrayList<PerecheDouble>();
				for (Entry<String, Double> ib1mas : valoriOzonExcell.entrySet()) {
					if (valoriOzonDobson.containsKey(ib1mas.getKey())) {
						if (valoriPresiune.containsKey(ib1mas.getKey())) {
							minPres = Math.min(minPres,
									valoriPresiune.get(ib1mas.getKey()));
						}
					}
				}
				minPres = minPres - 50;
				PrintWriter outFile = new PrintWriter(new FileWriter(
						combinedOut));
				outFile.format(
						"%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\t%16s\n",
						"Data " + formatKeyMap, " Ozon la statie ug ",
						" Ozon ug 10m ", " Cor. ug 10m ",
						" Valoare in dobson ", " Temperatura ", " Presiune ",
						" Inaltime amestec ", " Nebulozitate ",
						" raport M/c*100");
				CalculeazaOzonLaSol calc = new CalculeazaOzonLaSol();
				Double raportMediu = null;
				Double BMediu = null;
				for (Entry<String, Double> ib1mas : valoriOzonExcell.entrySet()) {
					String luna = ib1mas.getKey().split("-")[1];
					DateTime timp = DateTimeFormat.forPattern(formatKeyMap)
							.withZone(DateTimeZone.UTC)
							.parseDateTime(ib1mas.getKey());
					if (valoriOzonDobson.containsKey(ib1mas.getKey())) {
						Double dobson = valoriOzonDobson.get(ib1mas.getKey());
						Double dobsonReparat = valoriOzonDobsonReparat
								.get(ib1mas.getKey());
						double concDinDobson = calc.calculeazaConcentratieugm3(
								Integer.parseInt(luna) - 1, dobson, h);
						// graph.addData("C(ug)", timp, concDinDobson);
						double concDinDobsonH2 = calc
								.calculeazaConcentratieugm3(
										Integer.parseInt(luna) - 1, dobson, 20);
						double concDinDobsonH5 = calc
								.calculeazaConcentratieugm3(
										Integer.parseInt(luna) - 1,
										dobsonReparat, h);
						graph.addData("CR(ug)", timp, concDinDobsonH5);
						double presiune = 1013;
						if (valoriPresiune.containsKey(ib1mas.getKey())) {
							presiune = valoriPresiune.get(ib1mas.getKey())
									- minPres;
						}
						// graph.addData("presiune/100", timp, presiune / 100);
						double temperatura = 273.15;
						if (valoriTemp.containsKey(ib1mas.getKey())) {
							temperatura = valoriTemp.get(ib1mas.getKey());
						}
						double concDinDobsonCuCorectie = concDinDobson
								* (1013 / presiune) * (temperatura / 273.15);
						double concDinDobsonH3 = calc
								.calculeazaConcentratieugm3(
										Integer.parseInt(luna) - 1,
										concDinDobsonCuCorectie, h)
								* (1013 / presiune) * (temperatura / 273.15);
						// graph.addData("CC(ug)", timp, concDinDobson);
						Double mas = ib1mas.getValue();
						graph.addData("Mas(ug)", timp, mas);
						double raportMasuratPeCalculat = (mas / concDinDobsonCuCorectie) * 100d;
						// graph.addData("M/C*100", timp,
						// raportMasuratPeCalculat);
						if (raportMediu == null) {
							raportMediu = raportMasuratPeCalculat;
						} else {
							raportMediu = (raportMediu + raportMasuratPeCalculat) / 2;
						}
						// graph.addData("Dobson", timp, dobson - 200);
						// graph.addData("CC(ug)", timp,
						// concDinDobsonCuCorectie);
						// graph.addData("CC1(ug)", timp,
						// concDinDobsonCuCorectie * 0.5);
						double concDinDobsonCuCorectie16 = concDinDobsonCuCorectie * 1.6;
						// graph.addData("CC2(ug)", timp,
						// concDinDobsonCuCorectie16);
						Double vamestec = null;
						Double B = null;
						if (citesteAmestec) {
							vamestec = valoriHAmestec.get(ib1mas.getKey());
							// graph.addData("Z*", timp, vamestec);
							B = (concDinDobson - mas) / vamestec;
							// graph.addData("Z*", timp, vamestec);
							if (BMediu == null) {
								BMediu = B;
							} else {
								BMediu = (BMediu + B) / 2;
							}
							double X = -0.03933866;
							double concDinDobsonZ = concDinDobson
									- (X * vamestec);
							double concDinDobsonZ1 = concDinDobson
									+ (0.030 * vamestec);
							bootFH.add(new PerecheDouble(mas, concDinDobsonZ1));
							graph.addData("CZ*", timp, concDinDobsonZ1);
						}
						bootF0.add(new PerecheDouble(mas, dobson));
						bootFA.add(new PerecheDouble(mas,
								concDinDobsonCuCorectie));
						if (folosesteCorectieStatii) {
							bootFC.add(new PerecheDouble(mas,
									concDinDobsonCuCorectie
											* cor2Statii.get(statie.getKey())));
						}
						if (vRadiatieGlobala != null) {
							if (vRadiatieGlobala.get(timp) != null) {
								bootRadiatie.add(new PerecheDouble(mas,
										vRadiatieGlobala.get(timp)));
							}
						}
						bootFD.add(new PerecheDouble(mas, concDinDobson));
						Integer nrbulo = valoriNebulozitate
								.get(ib1mas.getKey());
						if (folosesteMaxim24hDinMedie8ore) {
							outFile.format(
									"%10s\t%5s\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16d\t%16f\t%16f\n",
									ib1mas.getKey().substring(0, 10), "12:00",
									mas, concDinDobson,
									concDinDobsonCuCorectie, dobson,
									temperatura, presiune, vamestec, nrbulo,
									raportMasuratPeCalculat, B);
						} else {
							outFile.format(
									"%10s\t%5s\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16.8f\t%16d\t%16f\t%16f\n",
									ib1mas.getKey().substring(0, 10), ib1mas
											.getKey().substring(11) + ":00",
									mas, concDinDobson,
									concDinDobsonCuCorectie, dobson,
									temperatura, presiune, vamestec, nrbulo,
									raportMasuratPeCalculat, B);
						}
						outFile.flush();
					}
				}
				// creazaFisierBoot(satelit, prefixFisierBoot, bootF0,
				// statie.getKey() + "_0", "O");
				creazaFisierBoot(satelit, prefixFisierBoot, bootFA,
						statie.getKey() + "_A", "1");
				if (vRadiatieGlobala != null) {
					creazaFisierBoot(satelit, prefixFisierBoot, bootRadiatie,
							statie.getKey() + "_RG", "4");
				}
				if (citesteAmestec) {
					creazaFisierBoot(satelit, prefixFisierBoot, bootFH,
							statie.getKey() + "_H", "2");
				}
				if (folosesteCorectieStatii) {
					creazaFisierBoot(satelit, prefixFisierBoot, bootFC,
							statie.getKey() + "_C", "3");
				}
				// creazaFisierBoot(satelit, prefixFisierBoot, bootFD,
				// statie.getKey() + "_D", "4");
				graph.setWidth(5600);
				graph.printChart(
						"Satelit:" + satelit + " Statie" + statie.getKey(),
						anul);
				outFile.format("Valoare medie raport MAS/C = %16.8f .",
						raportMediu);
				outFile.format("Valoare medie raport B = %16.8f .", BMediu);
				outFile.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	private TreeMap<DateTime, Double> aduceDoarPerechileDinPrima(
			TreeMap<DateTime, Double> valMasurat,
			TreeMap<DateTime, Double> vDobson) {
		TreeMap<DateTime, Double> arrExistent = new TreeMap<DateTime, Double>();
		for (Entry<DateTime, Double> iter : vDobson.entrySet()) {
			if (valMasurat.containsKey(iter.getKey())) {
				arrExistent.put(iter.getKey(), valMasurat.get(iter.getKey()));
			}
		}
		return arrExistent;
	}

	/**
	 * Modifica datele de dobson ca sa le schimbe panta
	 * 
	 * @param vDobson
	 * @param valMasurat
	 * @return
	 */
	private TreeMap<DateTime, Double> reparaValoriSatelit(
			TreeMap<DateTime, Double> vDobson,
			TreeMap<DateTime, Double> valMasurat) {
		TreeMap<DateTime, PerecheDouble> arrExistent = new TreeMap<DateTime, PerecheDouble>();
		for (Entry<DateTime, Double> iter : vDobson.entrySet()) {
			if (valMasurat.containsKey(iter.getKey())) {
				arrExistent.put(
						iter.getKey(),
						new PerecheDouble(iter.getValue(), valMasurat.get(iter
								.getKey())));
			}
		}
		Double[] valMas = new Double[arrExistent.size()];
		DateTime[] time = new DateTime[arrExistent.size()];
		Double[] valDob = new Double[arrExistent.size()];
		int cont = 0;
		for (Entry<DateTime, PerecheDouble> iterable_element : arrExistent
				.entrySet()) {
			time[cont] = iterable_element.getKey();
			valDob[cont] = iterable_element.getValue().v1;
			valMas[cont] = iterable_element.getValue().v2;
			cont++;
		}
		for (int j = 1; j < time.length - 1; j++) {
			double rapM = valMas[j - 1] / valMas[j];
			double rapD = valDob[j - 1] / valDob[j];
			if (rapM > 1 && rapD < 1) {
				/*
				 * masurat scade mai repede decat satelit, scad valoarea curenta
				 * dobson
				 */
				if (rapD < 0.2) {
					double tf1 = valDob[j - 1] * 1.60;
					valDob[j - 1] = tf1;
				} else if (rapD < 0.5) {
					double tf1 = valDob[j - 1] * 1.40;
					valDob[j - 1] = tf1;
				} else if (rapD < 0.7) {
					double tf1 = valDob[j - 1] * 1.20;
					valDob[j - 1] = tf1;
				}
			}
			rapD = valDob[j - 1] / valDob[j];
			if (rapM < 1 && rapD > 1) {
				/*
				 * masurat creste mai repede decat satelit, cresc valoarea
				 * curenta dobson, scad valoarea anterioara
				 */
				if (rapD > 1.40) {
					double tf = valDob[j] * 1.40;
					valDob[j] = tf;
				} else if (rapD > 1.20) {
					double tf = valDob[j] * 1.20;
					valDob[j] = tf;
				}
			}
			// logger.debug(" RM:" + rapM + " RD:" + rapD + " Raport initial:"
			// + (rapM / rapD) + " RD final:"
			// + ((valDob[j - 1] / valDob[j])));
		}
		TreeMap<DateTime, Double> retval = new TreeMap<DateTime, Double>();
		for (int i = 0; i < time.length; i++) {
			retval.put(time[i], Math.round(valDob[i] * 10000) / 10000d);
		}
		return retval;
	}

	private PrintWriter creazaFisierBoot(String satelit,
			String prefixFisierBoot, int contorLinii, String numeBoot1,
			String indice) throws IOException {
		PrintWriter bootFile = new PrintWriter(new FileWriter(prefixFisierBoot
				+ numeBoot1 + ".boxt"));
		bootFile.format("%-25s1YFNNNN\n", satelit + numeBoot1 + "-b.out");
		bootFile.format("%5d %5d %5d\n", contorLinii, 2, 1);
		bootFile.format("%5d\n", contorLinii);
		bootFile.format("'%8s' '%8s'\n", "Masurat", "Calcul_" + indice);
		bootFile.format("'%20s'\n", satelit + numeBoot1);
		return bootFile;
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

	private TreeMap<DateTime, Double> citesteInaltimeAmesteDinOML(
			String excell_date_amestec) throws FileNotFoundException,
			IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("citesteInaltimeAmesteDinOML(String) - start"); //$NON-NLS-1$
		}
		TreeMap<DateTime, Double> retval = new TreeMap<DateTime, Double>();
		Reader readFileMeteoOML = new InputStreamReader(new FileInputStream(
				excell_date_amestec));
		DateTimeFormatter fmtFileMeteoOML = DateTimeFormat.forPattern("yyMMdd")
				.withZone(DateTimeZone.UTC);
		int contorFmOML = 0;
		Scanner scanFileMeteoOML = new Scanner(readFileMeteoOML);
		TreeMap<DateTime, Double> valoriHAmestec = new TreeMap<DateTime, Double>();
		while (scanFileMeteoOML.hasNextLine()) {
			String line = scanFileMeteoOML.nextLine();
			contorFmOML++;
			if (contorFmOML <= 10) {
				continue;
			}
			Scanner rnl = new Scanner(line);
			rnl.useDelimiter(",");
			String data = "0" + line.substring(0, 6).trim();
			String sora = line.substring(6, 9).trim();
			int ora = Integer.parseInt(sora.trim());
			DateTime timp = fmtFileMeteoOML.parseDateTime(data);
			timp = timp.withHourOfDay(ora).hourOfDay().roundFloorCopy();
			String sameste = line.substring(78, 83).trim();
			Double HAmestec = Double.valueOf(sameste);
			if (valoriHAmestec.containsKey(timp)) {
				double temp = valoriHAmestec.get(timp);
				temp = (temp + HAmestec) / 2;
			} else {
				valoriHAmestec.put(timp, HAmestec);
			}
		}
		readFileMeteoOML.close();
		DateTime[] keys = new DateTime[valoriHAmestec.size()];
		Double[] valori = new Double[valoriHAmestec.size()];
		Double[] valoriMediate = new Double[valoriHAmestec.size()];
		int contor = 0;
		for (Entry<DateTime, Double> row : valoriHAmestec.entrySet()) {
			keys[contor] = row.getKey();
			valori[contor] = row.getValue();
			contor++;
		}
		for (int p = 2; p < valori.length - 2; p++) {
			double medie = 0;
			int gasite = 0;
			for (int ps = -2; ps <= 2; ps++) {
				if (valori[p + ps] != null) {
					medie = medie + valori[p + ps];
					gasite++;
				}
			}
			if (gasite != 0) {
				medie = medie / gasite;
				valoriMediate[p] = medie;
			} else {
				valoriMediate[p] = null;
			}
		}
		if (folosesteMaxim24hDinMedie8ore) {
			for (int i = 0; i < keys.length; i++) {
				DateTime dt = keys[i];
				Double v = valoriMediate[i];
				DateTime day = dt.dayOfYear().roundFloorCopy();
				if (v != null) {
					if (retval.containsKey(day)) {
						Double ve = retval.get(day);
						ve = Math.max(v, ve);
						retval.put(day, Math.round(ve * 10000) / 10000d);
					} else {
						retval.put(day, Math.round(v * 10000) / 10000d);
					}
				}
			}
		} else {
			for (int i = 0; i < keys.length; i++) {
				DateTime dt = keys[i];
				Double v = valoriMediate[i];
				if (v != null) {
					if (retval.containsKey(dt)) {
						Double ve = retval.get(dt);
						ve = Math.max(v, ve);
						retval.put(dt, Math.round(ve * 10000) / 10000d);
					} else {
						retval.put(dt, Math.round(v * 10000) / 10000d);
					}
				}
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("citesteInaltimeAmesteDinOML(String) - end:" + retval.size()); //$NON-NLS-1$
		}
		return retval;
	}

	private TreeMap<DateTime, Double> citesteValoriSatelitOzon(String combinedIn)
			throws FileNotFoundException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("citesteValoriSatelitOzon(" + combinedIn + ") - start"); //$NON-NLS-1$
		}
		TreeMap<DateTime, Double> valoriOzon = new TreeMap<DateTime, Double>();
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
			timp = timp.hourOfDay().roundFloorCopy();
			Double ozon = rnl.nextDouble();// CM
			if (ozon > 0) {
				if (folosesteMaxim24hDinMedie8ore) {
					DateTime day = timp.dayOfYear().roundFloorCopy();
					if (valoriOzon.containsKey(day)) {
						double temp = valoriOzon.get(day);
						temp = Math.max(temp, ozon);
						valoriOzon.put(day, temp);
					} else {
						valoriOzon.put(day, Math.round(ozon * 10000) / 10000d);
					}
				} else {
					if (valoriOzon.containsKey(timp)) {
						double temp = valoriOzon.get(timp);
						valoriOzon.put(timp, (ozon + temp) / 2);
					} else {
						valoriOzon.put(timp, Math.round(ozon * 10000) / 10000d);
					}
				}
			}
		}
		textFileReader.close();
		if (logger.isDebugEnabled()) {
			logger.debug("citesteValoriSatelitOzon(String, String) - end.Linii citite=" + contor); //$NON-NLS-1$
		}
		return valoriOzon;
	}

	private TreeMap<String, Double> mediazaUnMapCuTimpPe8Ore(
			String formatKeyMap, TreeMap<DateTime, Double> valoriOzon) {
		TreeMap<String, Double> retval = new TreeMap<String, Double>();
		Iterator<DateTime> itTimp = valoriOzon.keySet().iterator();
		Integer dayOfYear = null;
		Integer year = null;
		double medie = 0d;
		while (itTimp.hasNext()) {
			DateTime timpul = itTimp.next();
			Double valoare = valoriOzon.get(timpul);
			Integer curentDay = timpul.getDayOfYear();
			if (dayOfYear == null) {
				if (timpul.getHourOfDay() >= 8 && timpul.getHourOfDay() <= 16) {
					medie = valoare;
					dayOfYear = curentDay;
					year = timpul.getYear();
				}
				continue;
			}
			if (!curentDay.equals(dayOfYear)) {
				String dateKey = new DateTime(DateTimeZone.UTC).withYear(year)
						.withDayOfYear(dayOfYear).withHourOfDay(12)
						.toString(formatKeyMap);
				retval.put(dateKey, medie);
				// System.out.println("Am o valoare mediata:" + dateKey);
				dayOfYear = null;
			}
			if (dayOfYear == null) {
				if (timpul.getHourOfDay() >= 8 && timpul.getHourOfDay() <= 16) {
					medie = valoare;
					dayOfYear = curentDay;
					year = timpul.getYear();
					continue;
				}
			}
			if (timpul.getHourOfDay() >= 8 && timpul.getHourOfDay() <= 16) {
				medie = (medie + valoare) / 2;
				dayOfYear = curentDay;
			}
		}
		return retval;
	}

	private TreeMap<String, Integer> mediazaUnMapCuTimpPe8OreInt(
			String formatKeyMap, TreeMap<DateTime, Integer> valoriOzon) {
		TreeMap<String, Integer> retval = new TreeMap<String, Integer>();
		Iterator<DateTime> itTimp = valoriOzon.keySet().iterator();
		Integer dayOfYear = null;
		int medie = 0;
		while (itTimp.hasNext()) {
			DateTime timpul = itTimp.next();
			Integer valoare = valoriOzon.get(timpul);
			Integer curentDay = timpul.getDayOfYear();
			if (dayOfYear == null) {
				if (timpul.getHourOfDay() >= 8 && timpul.getHourOfDay() <= 16) {
					medie = valoare;
					dayOfYear = curentDay;
				}
				continue;
			}
			if (!curentDay.equals(dayOfYear)) {
				String dateKey = new DateTime(DateTimeZone.UTC)
						.withDayOfYear(dayOfYear).withHourOfDay(12)
						.toString(formatKeyMap);
				retval.put(dateKey, medie);
				// System.out.println("Am o valoare mediata:" + dateKey);
				dayOfYear = null;
			}
			if (dayOfYear == null) {
				if (timpul.getHourOfDay() >= 8 && timpul.getHourOfDay() <= 16) {
					medie = valoare;
					dayOfYear = curentDay;
					continue;
				}
			}
			if (timpul.getHourOfDay() >= 8 && timpul.getHourOfDay() <= 16) {
				medie = (medie + valoare) / 2;
				dayOfYear = curentDay;
			}
		}
		return retval;
	}

	private TreeMap<String, Integer> transformaFaraMediere(String formatKeyMap,
			TreeMap<DateTime, Integer> valoriOzon) {
		TreeMap<String, Integer> retval = new TreeMap<String, Integer>();
		Iterator<DateTime> itTimp = valoriOzon.keySet().iterator();
		while (itTimp.hasNext()) {
			DateTime timpul = itTimp.next();
			Integer valoare = valoriOzon.get(timpul);
			String dateKey = timpul.toString(formatKeyMap);
			retval.put(dateKey, valoare);
		}
		return retval;
	}

	private TreeMap<String, Double> transformaFaraMediereD(String formatKeyMap,
			TreeMap<DateTime, Double> valoriOzon) {
		TreeMap<String, Double> retval = new TreeMap<String, Double>();
		Iterator<DateTime> itTimp = valoriOzon.keySet().iterator();
		while (itTimp.hasNext()) {
			DateTime timpul = itTimp.next();
			Double valoare = valoriOzon.get(timpul);
			String dateKey = timpul.toString(formatKeyMap);
			retval.put(dateKey, valoare);
		}
		return retval;
	}

	HashMap<String, TreeMap<DateTime, Double>> cacheCitiri = new HashMap<String, TreeMap<DateTime, Double>>();

	private TreeMap<DateTime, Double> citesteValoriMasurateOzon(
			String excell_citiri, String statie) throws IOException,
			FileNotFoundException {
		if (logger.isDebugEnabled()) {
			logger.debug("citesteValoriMasurateOzon(" + excell_citiri + ", "
					+ statie + ") - start"); //$NON-NLS-1$
		}
		if (cacheCitiri.containsKey(excell_citiri + statie)) {
			return cacheCitiri.get(excell_citiri + statie);
		}
		Workbook wb = new HSSFWorkbook(new FileInputStream(excell_citiri));
		Sheet sheet = wb.getSheet("ozon");
		Iterator<Row> it = sheet.rowIterator();
		TreeMap<DateTime, Double> vmasurat = new TreeMap<DateTime, Double>();
		int numarColoanaStatie = -1;
		while (it.hasNext()) {
			Row row = it.next();
			if (row.getRowNum() == 0) {
				Iterator<Cell> itCel = row.cellIterator();
				while (itCel.hasNext()) {
					Cell cell = itCel.next();
					String strGasit = ExcellUtils.readStringFromCell(cell);
					if (StringUtils.equalsIgnoreCase(strGasit, statie)) {
						numarColoanaStatie = cell.getColumnIndex();
					}
				}
				continue;
			}
			if (row.getCell(1) != null && row.getCell(0) != null) {
				Date data = ExcellUtils.readDateValueWithDefault(row, 0, null);
				if (data == null) {
					continue;
				}
				Date ora = ExcellUtils.readTimeValueWithDefault(row, 1, null);
				if (ora == null) {
					continue;
				}
				DateTime timp = new DateTime(data,
						DateTimeZone.forID("Europe/Bucharest"))
						.withHourOfDay(ora.getHours());
				timp = timp.hourOfDay().roundFloorCopy();
				Double masicInStatie = 0d;
				try {
					masicInStatie = ExcellUtils.readDouble(row,
							numarColoanaStatie);
				} catch (Exception e) {
					logger.error(
							"citesteValoriMasurateOzon(String, Entry<String,double[]>)", e); //$NON-NLS-1$
					System.out.println(e.getMessage());
					continue;
				}
				DateTime withZone = timp.withZone(DateTimeZone.UTC);
				vmasurat.put(withZone, masicInStatie);
			}
		}
		DateTime[] keys = new DateTime[vmasurat.size()];
		Double[] valori = new Double[vmasurat.size()];
		Double[] valoriMediate = new Double[vmasurat.size()];
		int contor = 0;
		for (Entry<DateTime, Double> row : vmasurat.entrySet()) {
			keys[contor] = row.getKey();
			valori[contor] = row.getValue();
			contor++;
		}
		double medieToate = calculeazaMedie(vmasurat);
		double stdDevTotal = calculeazaStandarDev(vmasurat);
		System.out.println("Medie = " + medieToate
				+ " Standard deviation total:" + stdDevTotal);
		for (int p = 4; p < valori.length - 5; p++) {
			double medie = 0;
			int gasite = 0;
			for (int ps = -4; ps <= 4; ps++) {
				if (valori[p + ps] != null) {
					medie = medie + valori[p + ps];
					gasite++;
				}
			}
			if (gasite != 0) {
				medie = medie / gasite;
				valoriMediate[p] = medie;
			} else {
				valoriMediate[p] = null;
			}
		}
		vmasurat.clear();
		if (folosesteMaxim24hDinMedie8ore) {
			TreeMap<DateTime, Double> workRez = new TreeMap<DateTime, Double>();
			for (int i = 0; i < keys.length; i++) {
				DateTime dt = keys[i];
				Double v = valoriMediate[i];
				DateTime day = dt.dayOfYear().roundFloorCopy();
				if (v != null) {
					if (workRez.containsKey(day)) {
						Double ve = workRez.get(day);
						ve = Math.max(v, ve);
						workRez.put(day, Math.round(ve * 10000) / 10000d);
					} else {
						workRez.put(day, Math.round(v * 10000) / 10000d);
					}
				}
			}
			double medie = calculeazaMedie(workRez);
			double stdDev = calculeazaStandarDev(workRez);
			double max = medie + (2 * stdDev);
			double min = medie - (2 * stdDev);
			TreeMap<DateTime, Double> retval = new TreeMap<DateTime, Double>();
			for (Entry<DateTime, Double> row : workRez.entrySet()) {
				if (row.getValue() > min && row.getValue() < max) {
					retval.put(row.getKey(), row.getValue());
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("citesteValoriMasurateOzon(String, Entry<String,double[]>) - end"); //$NON-NLS-1$
			}
			cacheCitiri.put(excell_citiri + statie, retval);
			return retval;
		} else {
			TreeMap<DateTime, Double> workRez = new TreeMap<DateTime, Double>();
			for (int i = 0; i < keys.length; i++) {
				DateTime dt = keys[i];
				Double v = valoriMediate[i];
				if (v != null) {
					workRez.put(dt, Math.round(v * 10000) / 10000d);
				}
			}
			double medie = calculeazaMedie(workRez);
			double stdDev = calculeazaStandarDev(workRez);
			double max = medie + (2 * stdDev);
			double min = medie - (2 * stdDev);
			TreeMap<DateTime, Double> retval = new TreeMap<DateTime, Double>();
			for (Entry<DateTime, Double> row : workRez.entrySet()) {
				if (row.getValue() > min && row.getValue() < max) {
					retval.put(row.getKey(), row.getValue());
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("citesteValoriMasurateOzon(String, Entry<String,double[]>) - end"); //$NON-NLS-1$
			}
			cacheCitiri.put(excell_citiri + statie, retval);
			return retval;
		}
	}

	private double calculeazaMedie(TreeMap<DateTime, Double> valori) {
		double[] vals = new double[valori.size()];
		int contor = 0;
		for (Entry<DateTime, Double> row : valori.entrySet()) {
			if (row.getValue() != null) {
				vals[contor] = row.getValue();
				contor++;
			}
		}
		vals = Arrays.copyOf(vals, contor);
		return Stat.mean(vals);
	}

	private double calculeazaStandarDev(TreeMap<DateTime, Double> valori) {
		double[] vals = new double[valori.size()];
		int contor = 0;
		for (Entry<DateTime, Double> row : valori.entrySet()) {
			if (row.getValue() != null) {
				vals[contor] = row.getValue();
				contor++;
			}
		}
		vals = Arrays.copyOf(vals, contor);
		return Stat.standardDeviation(vals);
	}
}
