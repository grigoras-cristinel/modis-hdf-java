package excelldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CitesteFisierRadiatieGlobala {
	public class LinieRadiatieGlobala {
		public DateTime data;
		public Double valoareRadiatieGlobala;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CitesteFisierRadiatieGlobala worker = new CitesteFisierRadiatieGlobala();
		try {
			worker.citesteFisier("d:/proiecte/2011-satelit-ozon/radiatie globala abuc afumati.xls");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<DateTime, LinieRadiatieGlobala> citesteFisier(String filePath)
			throws Exception {
		HashMap<DateTime, LinieRadiatieGlobala> retval = new HashMap<DateTime, CitesteFisierRadiatieGlobala.LinieRadiatieGlobala>();
		Workbook wb = new HSSFWorkbook(new FileInputStream(filePath));
		Sheet sheet = wb.getSheet("meteo");
		DateTimeFormatter formatCitireDinExcell = DateTimeFormat.forPattern(
				"yyyy-MM-dd HH").withZone(DateTimeZone.UTC);
		Iterator<Row> it = sheet.rowIterator();
		while (it.hasNext()) {
			Row row = it.next();
			if (row.getRowNum() == 0) {
				continue;
			}
			LinieRadiatieGlobala linie = new LinieRadiatieGlobala();
			linie.data = formatCitireDinExcell
					.parseDateTime(ExcellUtils.readStringValue(row, 1))
					.hourOfDay().roundFloorCopy();
			linie.valoareRadiatieGlobala = 2 * ExcellUtils.readDoubleOrZero(
					row, 13);
			retval.put(linie.data, linie);
		}
		return retval;
	}
}
