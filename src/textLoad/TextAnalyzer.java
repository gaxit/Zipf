package textLoad;

import java.util.List;

import model.Word;

public class TextAnalyzer {

	public double stdDev(List<Word> words, int threshold) {

		/*
		 * computes the standard deviation of the product of each word's frequency and rank for all words with
		 * a frequency greater then the threshold parameter
		 */
		
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
		
		return stdDev;
	}
	
	public int averageZipfValue(List<Word> words, int threshold)
	{
		/*
		 * computes average product of the frequency and rank of each word which appears with
		 * a frequency greater then the threshold parameter
		 */
		int avg=0;
		int nwords=0;
		for(int i=0;i<words.size();i++)
		{
			int inst=words.get(i).getInstances();
			if(inst>threshold)
			{
				avg+=inst*(i+1);
				nwords++;
			}
		}
		avg=avg/nwords;
		return avg;
	}
	
	public int mostFrequentWordAnalysis(List<Word> words, int n)
	{
		/*
		 * computes average product of the frequency and rank of the n most frequent words
		 */
		int sum=0;
		for(int i=0;i<n;i++)
		{
			sum+=words.get(i).getInstances()*(i+1);
		}
		sum=sum/n;
		return sum;
	}

}