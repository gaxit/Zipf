package model;

public class Word {
	private String text;
	private int instances;

	public Word(String text, int instances) {
		this.text = text;
		this.instances = instances;
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
}
