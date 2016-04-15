package com.git.cs309.mmoclient.gui.interfaces;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class DungionGUI extends JPanel{
	private static final DungionGUI SINGLETON = new DungionGUI();
	static JFrame frame = new JFrame("Simple List Example");
	//will be given list of dungion names 
	
	public DungionGUI() {
		//JFrame frame = new JFrame("Simple List Example");
		frame.setSize(400, 400);
		frame.getContentPane().add(createPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//this.setLayout(null);
		//this.setOpaque(false);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	private static JPanel createPanel(){
		
		JPanel panel = new JPanel();
		
		//Create a JList 
		String [] data = {"abc","def","ghi"};
		JList <String>myList = new JList<String>(data);
		JScrollPane scrollPane = new JScrollPane(myList);
		panel.add(scrollPane);
		
		return panel;
		
	}
	
	public void show(){
		this.setVisible(true);
	}
	
	public void hide(){
		this.setVisible(false);
	}
	
	

}
