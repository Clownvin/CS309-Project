package com.git.cs309.mmoclient.gui.interfaces;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DungionGUI extends JPanel{
	private static final DungionGUI INSTANCE = new DungionGUI();
	//will be given list of dungion names 
	
	public static final DungionGUI getInstance() {
		return INSTANCE;
	}
	
	public DungionGUI() {
		//JFrame frame = new JFrame("Simple List Example");
		this.setSize(400, 400);
		
		//Create a JList 
		String [] data = {"abc","def","ghi"}; //get list of dungion names
		JList <String>myList = new JList<String>(data);
		JScrollPane scrollPane = new JScrollPane(myList);
		
		
		
		
		
		this.add(scrollPane);
		
		
		//this.setLayout(null);
		//this.setOpaque(false);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	public void show(){
		this.setVisible(true);
	}
	
	public void hide(){
		this.setVisible(false);
	}
	
	

}
