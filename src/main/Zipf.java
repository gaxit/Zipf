package main;

import java.io.IOException;
import java.util.ArrayList;

import model.Word;
import textLoad.TextAnalyzer;
import textLoad.TextExtractor;
import chart.MyChart;

public class Zipf {

	// private static final String FILE_NAME = "genezisEsperanto.txt";
	private static final String FILE_NAME = "genezisEsperantoTest.txt";

	public static void main(String[] args) throws IOException {
		TextExtractor textExtractor = new TextExtractor();
		ArrayList<Word> extractedWords = textExtractor.extract(FILE_NAME);

		MyChart myChart = new MyChart();
		myChart.prepareJFrame(extractedWords);

		display(extractedWords);
		TextAnalyzer textAnalyser = new TextAnalyzer();
		textAnalyser.stdDev(extractedWords);

	}

	private static void display(ArrayList<Word> aList) {
		for (Word w : aList) {
			System.out.println(w.getText() + " " + w.getInstances());
		}
	}
}
