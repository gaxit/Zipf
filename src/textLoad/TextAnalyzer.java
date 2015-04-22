package textLoad;

import java.util.List;

import model.Word;

public class TextAnalyzer {

	public void stdDev(List<Word> words) {
		int threshold = 10; // minimalna ilosc wystapien slowa ktorego wymagamy
							// aby wykorzystac je w liczeniu
		double stdDev = 0.0;
		double zipfSum = 0.0;
		int valuesConsidered = 0;
		for (int i = 0; i < words.size(); i++) {
			int instances = words.get(i).getInstances();
			if (instances > threshold) {
				zipfSum += instances * (i + 1);
				valuesConsidered++;
			}
		}

		double avg = zipfSum / valuesConsidered;
		double stdDevSum = 0.0;

		for (int i = 0; i < words.size(); i++) {
			int instances = words.get(i).getInstances();
			if (instances > threshold) {
				double squaredDifference = (instances * i - avg)
						* (instances * i - avg);
				stdDevSum += squaredDifference;
			}
		}
		stdDev = stdDevSum / valuesConsidered;
		stdDev = Math.sqrt(stdDev);

		System.out.println("Odchylenie standardowe dla progu" + threshold
				+ " wynosi: " + stdDev);
	}

}