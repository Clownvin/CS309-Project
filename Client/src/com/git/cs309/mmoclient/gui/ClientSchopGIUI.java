package com.git.cs309.mmoclient.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientSchopGIUI extends JPanel{
	
	private static final ClientShopGUI SINGLETON = new ClientShopGUI();
	
	private final JTextField shopName = new JTextField(ClientShop.getName());//shop name
	private final JTextField playerName=new JTextField(ClientPlayer.getName());//player name
	private final JPanel shopInvintory = new JPanel();
	private final JPanel playerInvintory = new JPanel();
	
	public ClientShopGUI() {
		init();
	}
	
	public void init(){
		this.setBounds(28, 500, 0, 0);
		this.setLayout(null);
		this.setOpaque(false);
	}
	
	public void show(){
		this.setPreferredSize(new Dimension(500, 500));
	}
	
	public void hide(){
		this.setPreferredSize(new Dimension(0, 0));
	}
	

}
