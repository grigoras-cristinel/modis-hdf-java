package hdfextractor;

import org.apache.commons.lang3.math.NumberUtils;

public class TOBase {
	protected static Byte byteOrNull(String next) {
		if (next == null) {
			return null;
		}
		if ("null".equals(next)) {
			return null;
		}
		if (NumberUtils.isNumber(next)) {
			return Byte.decode(next.trim());
		}
		return null;
	}

	protected static Double doubleOrNull(String next) {
		if (next == null) {
			return null;
		}
		if ("null".equals(next)) {
			return null;
		}
		if (NumberUtils.isNumber(next)) {
			return Double.valueOf(next.trim());
		}
		return null;
	}

	protected static Long longOrNull(String next) {
		if (next == null) {
			return null;
		}
		if ("null".equals(next)) {
			return null;
		}
		if (NumberUtils.isNumber(next)) {
			return Long.decode(next.trim());
		}
		return null;
	}
}
