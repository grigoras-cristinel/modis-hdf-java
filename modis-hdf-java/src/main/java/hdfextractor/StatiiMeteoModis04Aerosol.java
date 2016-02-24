package hdfextractor;

import java.util.TreeMap;

/**
 * Coordonate statii extragere date
 * 
 * @author Grigoras Cristinel
 * 
 */
public class StatiiMeteoModis04Aerosol {
	public static TreeMap<String, double[]> statii = new TreeMap<String, double[]>();
	static {
		// statii.put("anm_back", new double[] { 44.512585, 26.080127 });
		statii.put("EFORIE", new double[] { 44.0749500, 28.632222 });
		statii.put("INOE", new double[] { 44.348, 26.030 });
		// statii.put("CLUJ", new double[] { 46.768, 23.551 });
		// verifica coordonate
		// statii.put("TIMISOARA", new double[] { 45.746, 21.227 });
		statii.put("MOLDOVA", new double[] { 47.000, 28.816 });
		statii.put("IASI", new double[] { 44.348, 26.030 });
		statii.put("GLORIA", new double[] { 44.600, 29.360 });
	}
}
