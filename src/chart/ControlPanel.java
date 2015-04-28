package chart;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Word;

import org.jfree.chart.ChartPanel;

import textLoad.TextExtractor;

public class ControlPanel extends JPanel {

	private static final String WCZYTAJ_DANE = "Wczytaj dane";

	private static final long serialVersionUID = -4471553980104053571L;

	private static final String FILE_NAME = "polski.txt";

	public ControlPanel(JFrame frame) {
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
					File file = fileChooser.getSelectedFile();

					TextExtractor textExtractor = new TextExtractor();
					List<Word> extractedWords = new ArrayList<Word>();
					extractedWords = textExtractor.readFile(file
							.getAbsolutePath());

					if (frame.getContentPane().getComponents().length == 2) {
						frame.getContentPane().remove(1);
					}
					ChartPanel chartPanel = MyChart
							.createChartPanel(extractedWords);
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
