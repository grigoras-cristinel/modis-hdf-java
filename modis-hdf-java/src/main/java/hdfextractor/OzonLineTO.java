package hdfextractor;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author geo-track idx264
 * 
 */
public class OzonLineTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String statie;
	public String satelit;
	public DateTime data;
	public Double ozoneTotal;
	public Double ozone0m;
	public Double ozone10m;
	public Double ozone15m;
	public Double ozone20m;
	public Double diferenta15m;
	public Double microGrame15m;
	public Double diferenta20m;
	public Double microGrame20m;
	public Double uprim;
	public Double tempK;
	public Double pres_pa;
	DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm")
			.withZone(DateTimeZone.forOffsetHours(2));

	/**
	 * @param linie
	 *            fara statie si satelit
	 * @return
	 */
	public OzonLineTO toOzonLineTOfromShortLine(String linie) {
		OzonLineTO retval = new OzonLineTO();
		Scanner rnl = new Scanner(linie);
		retval.data = fmt.parseDateTime(rnl.next());
		retval.ozoneTotal = rnl.nextDouble();// ozon
		retval.ozone0m = rnl.nextDouble();// ozon
		retval.ozone10m = rnl.nextDouble();// ozon
		retval.ozone15m = rnl.nextDouble();// ozon
		retval.ozone20m = rnl.nextDouble();// ozon
		retval.diferenta15m = rnl.nextDouble();// ozon
		retval.microGrame15m = rnl.nextDouble();// ozon
		retval.diferenta20m = rnl.nextDouble();// ozon
		retval.microGrame20m = rnl.nextDouble();// ozon
		retval.uprim = rnl.nextDouble();// ozon
		retval.tempK = rnl.nextDouble();// ozon
		retval.pres_pa = rnl.nextDouble();// ozon
		return retval;
	}

	public OzonLineTO toOzonLineTOfromLongLine(String linie) {
		OzonLineTO retval = new OzonLineTO();
		Scanner rnl = new Scanner(linie);
		retval.satelit = rnl.next();
		retval.statie = rnl.next();
		retval.data = fmt.parseDateTime(rnl.next());
		retval.ozone0m = rnl.nextDouble();// ozon
		retval.ozoneTotal = rnl.nextDouble();// ozon
		retval.ozone10m = rnl.nextDouble();// ozon
		retval.ozone15m = rnl.nextDouble();// ozon
		retval.ozone20m = rnl.nextDouble();// ozon
		retval.diferenta15m = rnl.nextDouble();// ozon
		retval.microGrame15m = rnl.nextDouble();// ozon
		retval.diferenta20m = rnl.nextDouble();// ozon
		retval.microGrame20m = rnl.nextDouble();// ozon
		retval.uprim = rnl.nextDouble();// ozon
		retval.tempK = rnl.nextDouble();// ozon
		retval.pres_pa = rnl.nextDouble();// ozon
		return retval;
	}

	NumberFormat nf = new DecimalFormat("###0.000000");
	NumberFormat ne = new DecimalFormat("0.#####E000");

	public String toShortLine() {
		StringBuilder b = new StringBuilder();
		b.append(fmt.print(data) + "\t");
		b.append(nf.format(ozoneTotal) + "\t");
		b.append(nf.format(ozone0m) + "\t");
		b.append(nf.format(ozone10m) + "\t");
		b.append(nf.format(ozone15m) + "\t");
		b.append(nf.format(ozone20m) + "\t");
		b.append(nf.format(diferenta15m) + "\t");
		b.append(nf.format(microGrame15m) + "\t");
		b.append(nf.format(diferenta20m) + "\t");
		b.append(nf.format(microGrame20m) + "\t");
		b.append(nf.format(uprim) + "\t");
		b.append(nf.format(tempK) + "\t");
		b.append(nf.format(pres_pa) + "\t");
		return b.toString();
	}

	public String toLongLine() {
		StringBuilder b = new StringBuilder();
		b.append(satelit + "\t");
		b.append(statie + "\t");
		b.append(fmt.print(data) + "\t");
		b.append(nf.format(ozoneTotal) + "\t");
		b.append(nf.format(ozone0m) + "\t");
		b.append(nf.format(ozone10m) + "\t");
		b.append(nf.format(ozone15m) + "\t");
		b.append(nf.format(ozone20m) + "\t");
		b.append(nf.format(diferenta15m) + "\t");
		b.append(nf.format(microGrame15m) + "\t");
		b.append(nf.format(diferenta20m) + "\t");
		b.append(nf.format(microGrame20m) + "\t");
		b.append(nf.format(uprim) + "\t");
		b.append(nf.format(tempK) + "\t");
		b.append(nf.format(pres_pa) + "\t");
		return b.toString();
	}

	public static String shortHeader(String stat) {
		StringBuilder ret = new StringBuilder();
		ret.append("// Data___" + stat + "   \t");
		ret.append("     ozon   ");
		ret.append("    oz_0m   ");
		ret.append("    oz_15m  ");
		ret.append("    oz_20m  ");
		ret.append("  diferenta ");
		ret.append("    ug/m3   ");
		ret.append("  diferenta ");
		ret.append("    ug/m3   ");
		ret.append("     u'     ");
		ret.append("     T(K)   ");
		ret.append("    P(hPa)  ");
		return ret.toString();
	}

	public static String longHeader() {
		StringBuilder ret = new StringBuilder();
		ret.append("// satelit   \t");
		ret.append("// statie   \t");
		ret.append("// data      \t");
		ret.append("     ozon   ");
		ret.append("    oz_0m   ");
		ret.append("    oz_15m  ");
		ret.append("    oz_20m  ");
		ret.append("  diferenta ");
		ret.append("    ug/m3   ");
		ret.append("  diferenta ");
		ret.append("    ug/m3   ");
		ret.append("     u'     ");
		ret.append("     T(K)   ");
		ret.append("    P(hPa)  ");
		return ret.toString();
	}

	/**
	 * Mediaza valorile din obiectul curent cu cele din parametru
	 * 
	 * @param ozonLineTO
	 */
	public void mean(OzonLineTO ozonLineTO) {
		ozoneTotal = (ozoneTotal + ozonLineTO.ozoneTotal) / 2;
		ozone0m = (ozone0m + ozonLineTO.ozone0m) / 2;
		ozone10m = (ozone10m + ozonLineTO.ozone10m) / 2;
		ozone15m = (ozone15m + ozonLineTO.ozone15m) / 2;
		ozone20m = (ozone20m + ozonLineTO.ozone20m) / 2;
		diferenta15m = (diferenta15m + ozonLineTO.diferenta15m) / 2;
		diferenta20m = (diferenta20m + ozonLineTO.diferenta20m) / 2;
		microGrame15m = (microGrame15m + ozonLineTO.microGrame15m) / 2;
		microGrame20m = (microGrame20m + ozonLineTO.microGrame20m) / 2;
	}
}
