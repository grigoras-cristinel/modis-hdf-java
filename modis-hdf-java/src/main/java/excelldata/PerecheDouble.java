package excelldata;

import java.io.Serializable;

/**
 * @author geo-track idx264
 * 
 */
public class PerecheDouble implements Serializable {
	public PerecheDouble(Double v1, Double v2) {
		super();
		this.v1 = v1;
		this.v2 = v2;
	}

	@Override
	public String toString() {
		return "PerecheDouble [v1=" + v1 + ", v2=" + v2 + "]";
	}

	public Double v1;
	public Double v2;
}
