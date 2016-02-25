package com.git.cs309.mmoclient.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameMenuGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMenuGUI window = new GameMenuGUI();
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
	public GameMenuGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 161, 230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnReturnToGame = new JButton("return");
		btnReturnToGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReturnToGame.setBounds(28, 11, 89, 23);
		frame.getContentPane().add(btnReturnToGame);
		
		JButton btnOptions = new JButton("options");
		btnOptions.setBounds(28, 44, 89, 23);
		frame.getContentPane().add(btnOptions);
		
		JButton btnExit = new JButton("exit");
		btnExit.setBounds(28, 78, 89, 23);
		frame.getContentPane().add(btnExit);
	}

}
