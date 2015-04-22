package textLoad;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import model.Word;

public class TextExtractor {

	String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	public ArrayList Extract(String filename) throws IOException
	{
		Map<String,Integer> mp = new HashMap<String,Integer>();
		
		String str = readFile(filename);
		
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreElements()) {
			String word=(String) st.nextElement();
			
			if (word.charAt(word.length()-1)==',')
			{
				word = word.substring(0, word.length()-1);
			}
			Object value=mp.get(word);
			if(value==null)
			{
				mp.put(word,0);
			}
			else
			{
				mp.put(word, mp.get(word)+1);
			}
		}
		
		ArrayList<Word> arrList=new ArrayList<Word>();
		
		for(String key: mp.keySet())
		{
			Word newWord=new Word(key);
		}
		
		Collections.sort(arrList);
		
		int sz=arrList.size();
		return arrList;
		

	}
	
}
