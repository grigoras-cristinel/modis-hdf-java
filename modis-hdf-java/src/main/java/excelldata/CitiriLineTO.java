package excelldata;

import java.io.Serializable;

import org.joda.time.DateTime;

public class CitiriLineTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DateTime data;
	public boolean areOzon = false;

	@Override
	public String toString() {
		return "CitiriLineTO [data=" + data + ", areOzon=" + areOzon
				+ ", cozon=" + cozon + ", areTemperatura=" + areTemperatura
				+ ", temperatura=" + temperatura + ", arePresiune="
				+ arePresiune + ", presiune=" + presiune + "]";
	}

	public Double cozon;
	public boolean areTemperatura = false;
	public Double temperatura;
	public boolean arePresiune = false;
	public Double presiune;
}
