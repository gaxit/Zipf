package main;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

import chart.ControlPanel;

public class MainZipfFrame {

	private static final String FRAME_TITLE = "Analizator prawa Zipf'a";

	public static void main(String[] args) throws IOException {

		JFrame frame = new JFrame(FRAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new ControlPanel(frame),
				BorderLayout.PAGE_START);
		frame.setVisible(true);
		frame.setSize(500, 550);
	}

}
