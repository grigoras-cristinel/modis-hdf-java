package excelldata;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * @author geo-track idx264
 * 
 */
public class LinieDateMeteoTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DateTime data;
	public Double temperaturaCelsius;
	public Double presiuneColoana;
	public Integer nebulozitate;
}
