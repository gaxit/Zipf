package main;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

import chart.ControlPanel;

public class MainZipfFrame {

	public static void main(String[] args) throws IOException {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new ControlPanel(frame),
				BorderLayout.PAGE_START);
		frame.setVisible(true);
		frame.setSize(500, 550);
	}

}
