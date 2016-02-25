package com.git.cs309.mmoclient.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class clientInGame {

	private JFrame frame;
	private JTextField txtChattext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientInGame window = new clientInGame();
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
	public clientInGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 878, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.YELLOW);
		
		JTextArea txtrMinimap = new JTextArea();
		txtrMinimap.setText("mini-map");
		txtrMinimap.setBounds(10, 144, 161, 111);
		frame.getContentPane().add(txtrMinimap);
		
		JTextArea txtrPartyArea = new JTextArea();
		txtrPartyArea.setText("party area");
		txtrPartyArea.setBounds(10, 11, 161, 111);
		frame.getContentPane().add(txtrPartyArea);
		
		JTextArea txtrGameGoesHere = new JTextArea();
		txtrGameGoesHere.setText("game goes here");
		txtrGameGoesHere.setBounds(181, 11, 671, 402);
		frame.getContentPane().add(txtrGameGoesHere);
		
		JTextArea txtrHealth = new JTextArea();
		txtrHealth.setText("health (ex:      155 / 200)");
		txtrHealth.setBounds(181, 422, 319, 22);
		frame.getContentPane().add(txtrHealth);
		
		JButton btnMenu = new JButton("menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//sendTOServer(socket, textField);
					//textField.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMenu.setBounds(763, 424, 89, 23);
		frame.getContentPane().add(btnMenu);
		
		JButton btnInvintory = new JButton("invintory");
		btnInvintory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//textField.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInvintory.setBounds(664, 424, 89, 23);
		frame.getContentPane().add(btnInvintory);
		
		JTextArea txtrChatHistory = new JTextArea();
		txtrChatHistory.setText("chat history");
		txtrChatHistory.setBounds(10, 266, 161, 144);
		frame.getContentPane().add(txtrChatHistory);
		
		txtChattext = new JTextField();
		txtChattext.setText("chatText");
		//enter button listener
		txtChattext.addKeyListener(
	            new KeyListener(){
	                public void keyPressed(KeyEvent e){
	                	if(e.getKeyCode() == KeyEvent.VK_ENTER){
	                		System.out.println("hi");
	                	      //outcome = input.getText();
	                		//send to server(txtChattext)
	                	}     
	                }

					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyTyped(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
	            }
	        );
		//end listener
		txtChattext.setBounds(10, 424, 161, 20);
		frame.getContentPane().add(txtChattext);
		txtChattext.setColumns(10);
		
		JTextArea txtrLevelxpnext = new JTextArea();
		txtrLevelxpnext.setText("ex: 5 (200/250)");
		txtrLevelxpnext.setBounds(510, 424, 148, 20);
		frame.getContentPane().add(txtrLevelxpnext);
	}
}
