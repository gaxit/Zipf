package chart;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MyChart {

	private static final int CHART_HEIGHT = 400;
	private static final int CHART_WIDTH = 400;

	private static final String CHART_TITLE = "Wykres zale¿noœci s³ów od pozycji rankingowej";
	private static final String X_SERIES_LABEL = "Pozycja rankingowa";
	private static final String Y_SERIES_LABEL = "Iloœæ wyst¹pieñ";

	private static final boolean CHART_LEGEND = true;
	private static final boolean CHART_TOOLTIPS = true;
	private static final boolean CHART_URLS = false;

	private static final String WORDS = "S³owa";

	public static void main(final String[] args) {
		ChartPanel chartPanel = createChartPanel();
		prepareJFrame(chartPanel);
	}

	private static void prepareJFrame(ChartPanel chartPanel) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(chartPanel);
		frame.setVisible(true);
		frame.pack();
	}

	private static ChartPanel createChartPanel() {
		XYDataset dataset = createDataset();
		JFreeChart chart = createJFreeChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(CHART_WIDTH, CHART_HEIGHT));
		return chartPanel;
	}

	private static XYDataset createDataset() {
		final XYSeries series1 = new XYSeries(WORDS);

		final XYSeriesCollection dataset = new XYSeriesCollection();

		series1.add(1, 1);
		series1.add(2, 1);
		series1.add(3, 1);
		series1.add(4, 1);
		series1.add(5, 1);

		dataset.addSeries(series1);
		return dataset;
	}

	private static JFreeChart createJFreeChart(final XYDataset dataset) {
		final JFreeChart chart = ChartFactory.createScatterPlot(CHART_TITLE,
				X_SERIES_LABEL, Y_SERIES_LABEL, dataset,
				PlotOrientation.VERTICAL, CHART_LEGEND, CHART_TOOLTIPS,
				CHART_URLS);
		return chart;
	}
}
