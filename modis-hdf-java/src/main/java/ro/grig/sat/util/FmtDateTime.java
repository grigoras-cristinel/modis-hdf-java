package ro.grig.sat.util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class FmtDateTime extends CellProcessorAdaptor {
	@Override
	public Object execute(Object value, CsvContext context) {
		if (!(value instanceof DateTime)) {
			throw new SuperCsvCellProcessorException(Date.class, value,
					context, this);
		}
		final DateTimeFormatter formatter;
		formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
				.withZoneUTC();
		String result = formatter.print((DateTime) value);
		return next.execute(result, context);
	}
}
