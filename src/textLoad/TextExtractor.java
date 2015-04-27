package textLoad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Word;
import model.WordComparator;

public class TextExtractor {

	private void putToMap(Map<String, Integer> mp, String key) {
		key = key.trim();
		if ("".equals(key) || " ".equals(key) || "[".equals(key)
				|| "]".equals(key) || "/".equals(key) || "\\".equals(key)) {
			return;
		}
		Object value = mp.get(key);
		if (value == null) {
			mp.put(key, 1);
		} else {
			mp.put(key, mp.get(key) + 1);
		}
	}

	public List<Word> readFile(String fileName) throws IOException {
		Map<String, Integer> mp = new HashMap<String, Integer>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			String line = br.readLine();

			while (line != null) {
				String lineReplaced = line.replaceAll("[0-9]", " ");
				lineReplaced = lineReplaced.replaceAll("[:;.,!(){}*]", " ");
				lineReplaced = lineReplaced.toLowerCase();
				String[] wordTab = lineReplaced.split(" ");
				for (String word : wordTab) {
					putToMap(mp, word);
				}
				line = br.readLine();
			}
		} finally {
			br.close();
		}

		ArrayList<Word> arrList = new ArrayList<Word>();

		for (String key : mp.keySet()) {
			Word newWord = new Word(key, mp.get(key));
			arrList.add(newWord);
		}

		Collections.sort(arrList, new WordComparator());

		return arrList;
	}

}
