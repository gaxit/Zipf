package main;

import java.io.IOException;
import java.util.List;

import model.Word;
import textLoad.TextAnalyzer;
import textLoad.TextExtractor;
import chart.MyChart;

public class Zipf {

	private static final String FILE_NAME = "polski.txt";

	public static void main(String[] args) throws IOException {
		TextExtractor textExtractor = new TextExtractor();
		List<Word> extractedWords = textExtractor.readFile(FILE_NAME);

		MyChart myChart = new MyChart();
		myChart.prepareJFrame(extractedWords);

		// display(extractedWords);
		int threshold = 10;
		TextAnalyzer textAnalyzer = new TextAnalyzer();

		double average = textAnalyzer.averageZipfValue(extractedWords,
				threshold);
		double stdDev = textAnalyzer.stdDev(extractedWords, threshold);

		System.out.println("Œrednia Fi*Pi dla progu " + threshold + " wynosi "
				+ average);
		System.out.println("Odchylenie standardowe dla progu " + threshold
				+ " wynosi: " + stdDev);
		System.out.println("Stosunek odchylenia do œredniej Fi*Pi dla progu "
				+ threshold + " wynosi: " + stdDev / average);

	}

	private static void display(List<Word> aList) {
		for (Word w : aList) {
			System.out.println(w.getText() + " " + w.getInstances());
		}
	}
}
