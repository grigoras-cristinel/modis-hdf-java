package graph;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.DateRange;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.joda.time.DateTime;

public class GraphComparare {
	JFreeChart chart;
	String outFile;
	private final HashMap<String, TimeSeries> serii = new HashMap<String, TimeSeries>();

	public void addData(String serie, DateTime data, Double valoare) {
		TimeSeries toAdd = serii.get(serie);
		if (toAdd == null) {
			toAdd = new TimeSeries(serie);
			serii.put(serie, toAdd);
		}
		toAdd.addOrUpdate(new Hour(data.toDate()), valoare);
	}

	public GraphComparare(String outPng) {
		this.outFile = outPng;
	}

	int width = 1500;
	private int height = 1500;

	public void printChart(String title, int anul) {
		TimeSeriesCollection dset = new TimeSeriesCollection();
		for (Entry<String, TimeSeries> b : serii.entrySet()) {
			dset.addSeries(b.getValue());
		}
		dset.setDomainIsPointsInTime(true);
		chart = ChartFactory.createTimeSeriesChart(title, "Time(H)", "Values",
				dset, true, true, false);
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);
		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(false);
			renderer.setBaseShapesFilled(false);
		}
		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM HH"));
		axis.setAutoRange(true);
		axis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 1));
		axis.setRange(new DateRange(new DateTime(anul, 1, 1, 1, 1, 1, 1)
				.toDate(), new DateTime(anul + 1, 1, 1, 1, 1, 1, 1).toDate()));
		axis.setMinorTickMarksVisible(true);
		axis.setLabel("Timp");
		try {
			ChartUtilities.saveChartAsPNG(new File(this.outFile), chart, width,
					height);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setWidth(int i) {
		width = i;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}
}
