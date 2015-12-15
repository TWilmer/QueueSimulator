package com.visteon.queuesimulator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}
	
	private Model mModel;
	public MainWindow(Model model) {
		initialize();
		mModel=model;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 648, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final ModelView panel = new ModelView();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		final JCheckBox chckbxUseapp = new JCheckBox("UseApp");
		panel_1.add(chckbxUseapp);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mModel.setUseApp(chckbxUseapp.isSelected());
				panel.setModel(mModel);
				
				mModel.start(panel);
			}
		});
		panel_1.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mModel.stop();
			}
		});
		panel_1.add(btnStop);
	}

	public void show() {
		frame.setVisible(true);
		
	}

}
