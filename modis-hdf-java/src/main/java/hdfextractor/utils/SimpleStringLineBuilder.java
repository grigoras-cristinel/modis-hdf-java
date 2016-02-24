package hdfextractor.utils;

/**
 * @author Administrator
 *
 */
public class SimpleStringLineBuilder {
	StringBuilder builder = new StringBuilder();
	boolean start = true;
	private static String separator = "\t";

	/**
	 * @param value
	 * @return
	 */
	public SimpleStringLineBuilder append(Object value) {

		if (start) {
			builder.append(value);
			start = false;
		} else {
			builder.append(separator).append(value);
		}
		return this;
	}

	@Override
	public String toString() {
		return builder.toString();
	}
}
