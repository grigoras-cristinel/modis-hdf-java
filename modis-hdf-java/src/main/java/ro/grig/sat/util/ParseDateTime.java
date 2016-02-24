package ro.grig.sat.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class ParseDateTime extends CellProcessorAdaptor {
	@Override
	public Object execute(Object value, CsvContext context) {
		if (value == null || StringUtils.isEmpty(value.toString())) {
			return next.execute(value, context);
		}
		try {
			DateTimeFormatter df = DateTimeFormat.forPattern(
					"yyyy-MM-dd HH:mm:ss").withZoneUTC();
			DateTime dt = df.parseDateTime(value.toString());
			return next.execute(dt, context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new SuperCsvCellProcessorException(String.format(
				"Could not parse '%s' as a day", value), context, this);
	}

	public ParseDateTime() {
		super();
	}

	public ParseDateTime(CellProcessor next) {
		super(next);
	}
}
