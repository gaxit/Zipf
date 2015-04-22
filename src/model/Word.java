package model;

public class Word implements Comparable {
	private String text;
	private int instances;
	
	public Word(String str)
	{
		text=str;
		instances=0;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getInstances() {
		return instances;
	}

	public void setInstances(int instances) {
		this.instances = instances;
	}

	public int compareTo(Word a, Word b) {
		return a.instances < b.instances ? -1 : a.instances == b.instances ? 0 : 1;
	}
/*
	public int compareTo(Word w) {
		return 0;
	}
*/

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
