package chart;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;

import model.Word;

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

	public void prepareJFrame(List<Word> extractedWords) {
		ChartPanel chartPanel = createChartPanel(extractedWords);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(chartPanel);
		frame.setVisible(true);
		frame.pack();
	}

	private ChartPanel createChartPanel(List<Word> extractedWords) {
		XYDataset dataset = createDataset(extractedWords);
		JFreeChart chart = createJFreeChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(CHART_WIDTH, CHART_HEIGHT));
		return chartPanel;
	}

	private XYDataset createDataset(List<Word> extractedWords) {
		final XYSeries series1 = new XYSeries(WORDS);

		final XYSeriesCollection dataset = new XYSeriesCollection();

		int i = 1;
		for (Word word : extractedWords) {
			series1.add(i, word.getInstances());
			i++;
		}

		dataset.addSeries(series1);
		return dataset;
	}

	private JFreeChart createJFreeChart(final XYDataset dataset) {
		final JFreeChart chart = ChartFactory.createScatterPlot(CHART_TITLE,
				X_SERIES_LABEL, Y_SERIES_LABEL, dataset,
				PlotOrientation.VERTICAL, CHART_LEGEND, CHART_TOOLTIPS,
				CHART_URLS);
		return chart;
	}
}
