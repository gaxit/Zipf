package chart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import model.Word;

import org.jfree.chart.ChartPanel;

import textLoad.TextAnalyzer;
import textLoad.TextExtractor;

public class ControlPanel extends JPanel {

	private JSpinner minDataSpinner;
	private JLabel wordsLabel;
	private JLabel stdDevLabel;
	private JLabel averageLabel;
	private JLabel coefficientLabel;

	private static final String WCZYTAJ_DANE = "Wczytaj dane";

	private static final long serialVersionUID = -4471553980104053571L;

	public ControlPanel(JFrame frame) {
		minDataSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		Box LabelBox = prepareAndAddResultLabels();

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		prepareAndAddLoadDataButton(frame);
		prepareAndAddMinDataSpinner();
		add(LabelBox);
		setResultLabelValues(0, 0, 0);
	}

	private Box prepareAndAddResultLabels() {
		Box box = Box.createVerticalBox();
		wordsLabel = new JLabel();
		stdDevLabel = new JLabel();
		averageLabel = new JLabel();
		coefficientLabel = new JLabel();
		box.add(wordsLabel);
		box.add(stdDevLabel);
		box.add(averageLabel);
		box.add(coefficientLabel);
		return box;
	}

	private void setResultLabelValues(int words, double average, double stdDev) {
		double coef = stdDev / average;
		if (average == 0) {
			coef = 0;
		}
		MathContext mathContext = new MathContext(4, RoundingMode.HALF_UP);
		BigDecimal averageBig = new BigDecimal(average, mathContext);
		BigDecimal stdDevBig = new BigDecimal(stdDev, mathContext);
		BigDecimal coefBig = new BigDecimal(coef, mathContext);
		wordsLabel.setText("Iloœæ s³ów: " + words);
		stdDevLabel.setText("Œrednia: " + averageBig.toString());
		averageLabel.setText("Odchylenie standardowe: " + stdDevBig.toString());
		coefficientLabel.setText("Odchylenie standardowe / œrednia : "
				+ coefBig.toString());
	}

	private void prepareAndAddMinDataSpinner() {
		JLabel label = new JLabel(
				"Elementy o minimalnym wystêpowaniu do pominiêcia: ");
		minDataSpinner.setMaximumSize(new Dimension(60, 20));
		Box box = Box.createHorizontalBox();
		box.add(label);
		box.add(minDataSpinner);
		add(box);
	}

	private void prepareAndAddLoadDataButton(JFrame frame) {
		JButton loadButton = new JButton(WCZYTAJ_DANE);
		add(loadButton);
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser fileChooser = new JFileChooser();
					int returnVal = fileChooser.showOpenDialog(frame);

					if (returnVal != JFileChooser.APPROVE_OPTION) {
						return;
					}

					int threshold = (Integer) minDataSpinner.getValue();
					File file = fileChooser.getSelectedFile();

					TextExtractor textExtractor = new TextExtractor();
					List<Word> extractedWords = new ArrayList<Word>();
					extractedWords = textExtractor.readFile(file
							.getAbsolutePath());

					TextAnalyzer textAnalyzer = new TextAnalyzer();
					List<Word> limitedWords = textAnalyzer
							.removeWordWithInstancesLessThanThreshold(
									extractedWords, threshold);

					double average = textAnalyzer.averageZipfValue(
							limitedWords, threshold);
					double stdDev = textAnalyzer
							.stdDev(limitedWords, threshold);

					setResultLabelValues(limitedWords.size(), average, stdDev);

					if (frame.getContentPane().getComponents().length == 2) {
						frame.getContentPane().remove(1);
					}
					ChartPanel chartPanel = MyChart
							.createChartPanel(limitedWords);
					frame.getContentPane().add(chartPanel,
							BorderLayout.PAGE_END);
					frame.revalidate();
				} catch (IOException e1) {
					System.out.println("ERROR: " + e1.getMessage());
				}
			}
		});
	}
}
