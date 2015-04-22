package model;

import java.util.Comparator;

public class WordComparator implements Comparator<Word> {

	@Override
	public int compare(Word o1, Word o2) {
		return o2.getInstances() - o1.getInstances();
	}

}
