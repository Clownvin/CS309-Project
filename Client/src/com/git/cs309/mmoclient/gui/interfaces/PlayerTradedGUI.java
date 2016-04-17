package com.git.cs309.mmoclient.gui.interfaces;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerTradedGUI extends JPanel{
	
	private static final PlayerTradedGUI INSTANCE = new PlayerTradedGUI();
	
	public static final PlayerTradedGUI getInstance() {
		return INSTANCE;
	}
	
	JButton yesButton = new JButton("yes");
	JButton noButton = new JButton("no");
	
	
	public PlayerTradedGUI()
	{
		this.setSize(400, 400);
		
		//show player inventory
				//text field for what of your items
		//two buttons (yes/no)
		
		JButton yesButton = new JButton("yes");
		this.add(yesButton);
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send info to server
				//TODO
				//ex from loging 
				//Client.getConnection().addOutgoingPacket(new LoginPacket(stuff));
				hide();
			}
		});
		
		JButton noButton = new JButton("no");
		this.add(noButton);
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
			}
		});
		
		JTextField whatItem= new JTextField();//by row number
		whatItem.setText("what row number of item to trade");
		this.add(whatItem);
		
		//this.setLayout(null);
		//this.setOpaque(false);
	}
	
	public void show(){
		this.setPreferredSize(new Dimension(500, 500));
	}
	
	public void hide(){
		this.setPreferredSize(new Dimension(0, 0));
	}

}
