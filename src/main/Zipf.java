package main;

import java.io.IOException;
import java.util.ArrayList;

import model.Word;
import textLoad.TextExtractor;

public class Zipf {

	public static void main(String[] args) throws IOException {
		TextExtractor ta = new TextExtractor();
		ArrayList<Word> al = ta.extract("test.txt");

	}
}
