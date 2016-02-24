package excelldata;

import java.io.Serializable;

/**
 * @author geo-track idx264
 * 
 */
public class PerecheD implements Serializable {
	public PerecheD(double v1, double v2) {
		super();
		this.v1 = v1;
		this.v2 = v2;
	}

	@Override
	public String toString() {
		return "PerecheDouble [v1=" + v1 + ", v2=" + v2 + "]";
	}

	public double v1;
	public double v2;
}
