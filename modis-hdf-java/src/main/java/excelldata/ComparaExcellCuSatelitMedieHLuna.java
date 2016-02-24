package excelldata;

import hdfextractor.StatiiMeteoBucuresti;
import hdfextractor.ozon.CalculeazaOzonLaSol;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
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
public class ComparaExcellCuSatelitMedieHLuna {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start");
		ComparaExcellCuSatelitMedieHLuna worker = new ComparaExcellCuSatelitMedieHLuna();
		worker.execute();
		System.out.println("End");
	}

	/**
	 * 
	 */
	private void execute() {
		String excell_citiri = "Ozon2010.xls";
		double h = 10d;
		for (Entry<String, double[]> statie : StatiiMeteoBucuresti.statii
				.entrySet()) {
			String combinedIn = "target/combined-statie-" + statie.getKey()
					+ ".txt";
			String combinedOut = "target/comparare-H-luna-" + statie.getKey() + ".txt";
			TreeMap<String, Double> valoriOzonDobson = new TreeMap<String, Double>();
			try {
				Reader textFileReader = new InputStreamReader(
						new FileInputStream(combinedIn));
				Scanner rn = new Scanner(textFileReader);
				DateTimeFormatter fmt = DateTimeFormat.forPattern(
						"yyyy-MM-dd'T'HH:mm").withZone(DateTimeZone.UTC);
				String keyMapMedium = "yyyy-MM-HH";
				while (rn.hasNextLine()) {
					String line = rn.nextLine();
					Scanner rnl = new Scanner(line);
					String data = rnl.next();
					DateTime timp = fmt.parseDateTime(data);
					Double ozon = rnl.nextDouble();// CM
					String dateKey = timp.toString(keyMapMedium);
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
				System.out.println(statie.getKey() + " Am gasit:"
						+ valoriOzonDobson.size());
				Workbook wb = new HSSFWorkbook(new FileInputStream(
						excell_citiri));
				Sheet sheet = wb.getSheet("ozon");
				Iterator<Row> it = sheet.rowIterator();
				TreeMap<String, Double> valoriOzonExcell = new TreeMap<String, Double>();
				int numarColoanaStatie = -1;
				while (it.hasNext()) {
					Row row = (Row) it.next();
					if (row.getRowNum() == 0) {
						Iterator<Cell> itCel = row.cellIterator();
						while (itCel.hasNext()) {
							Cell cell = (Cell) itCel.next();
							String strGasit = ExcellUtils
									.readStringFromCell(cell);
							if (StringUtils.equalsIgnoreCase(strGasit,
									statie.getKey())) {
								numarColoanaStatie = cell.getColumnIndex();
							}
						}
						System.out.println(statie.getKey()
								+ " Am gasit coloana:" + numarColoanaStatie);
						continue;
					}
					if (row.getCell(1) != null && row.getCell(0) != null) {
						Date ceas = ExcellUtils.readTimeValueWithDefault(row,
								1, null);
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
						System.out.println("masurat" + masicInStatie);
						if (masicInStatie != null) {
							Date timp = ExcellUtils.readDateValueWithDefault(
									row, 0, null);
							DateTime usF = new DateTime(timp,
									DateTimeZone.forID("Europe/Bucharest"));
							usF = usF.withHourOfDay(time);
							String key = usF.toString(keyMapMedium);
							if (valoriOzonExcell.containsKey(key)) {
								double temp = valoriOzonExcell.get(key);
								valoriOzonExcell.put(key,
										(temp + masicInStatie) / 2);
							} else {
								valoriOzonExcell.put(key, masicInStatie);
							}
						}
					}
				}
				System.out.println(statie.getKey() + " Am gasit excell:"
						+ valoriOzonExcell.size());
				PrintWriter outFile = new PrintWriter(new FileWriter(
						combinedOut));
				CalculeazaOzonLaSol calc = new CalculeazaOzonLaSol();
				for (Entry<String, Double> ib1mas : valoriOzonExcell.entrySet()) {
					String luna = ib1mas.getKey().split("-")[1];
					if (valoriOzonDobson.containsKey(ib1mas.getKey())) {
						outFile.format("%16s\t%16.8f\t%16.8f\n",
								ib1mas.getKey(), ib1mas.getValue(), calc
										.calculeazaConcentratieugm3(
												Integer.parseInt(luna) - 1,
												valoriOzonDobson.get(ib1mas
														.getKey()), h));
						outFile.flush();
					}
				}
				outFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
