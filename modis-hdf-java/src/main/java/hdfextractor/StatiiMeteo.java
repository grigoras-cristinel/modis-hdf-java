package hdfextractor;

import java.util.TreeMap;

public class StatiiMeteo {
	public static TreeMap<String, double[]> statii = new TreeMap<String, double[]>();
	static {
		statii.put("anm_front", new double[] { 44.512585, 26.080127 });
		//statii.put("test", new double[] { 34.12105, 160.82788 });
		statii.put("anm_back", new double[] { 44.511529, 26.077809 });
	}

}
