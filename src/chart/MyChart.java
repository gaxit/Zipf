package chart;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MyChart {

	public static void main(final String[] args) {
		final MyChart demo = new MyChart("Multi Line Chart");
	}

	public MyChart(String text) {
		XYDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
		chartPanel.setVisible(true);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(chartPanel);
		frame.setVisible(true);
		frame.pack();
	}

	private XYDataset createDataset() {
		final XYSeries series1 = new XYSeries("SLMM");
		final XYSeries series2 = new XYSeries("FSPM");

		final XYSeriesCollection dataset = new XYSeriesCollection();

		series1.add(1, 1);
		series1.add(2, 1);
		series1.add(3, 1);
		series1.add(4, 1);
		series1.add(5, 1);

		series2.add(1, 3);
		series2.add(2, 3);
		series2.add(3, 3);
		series2.add(4, 3);
		series2.add(5, 3);

		dataset.addSeries(series1);
		dataset.addSeries(series2);
		return dataset;
	}

	private JFreeChart createChart(final XYDataset dataset) {
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Power Comparison", "Transaction", "Energy", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		return chart;
	}
}
