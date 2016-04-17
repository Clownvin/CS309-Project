package com.git.cs309.mmoclient.gui.interfaces;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.git.cs309.mmoclient.entity.character.player.Self;
import com.git.cs309.mmoclient.items.ItemContainer;

public class PlayerInvintoryGUI {
	private static final PlayerInvintoryGUI SINGLETON = new PlayerInvintoryGUI();
	static JFrame frame = new JFrame("inventory");
	
	
	public PlayerInvintoryGUI()
	{
		frame.setSize(400, 400);
		frame.getContentPane().add(createPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//this.setLayout(null);
		//this.setOpaque(false);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public JPanel createPanel(){
		
		JPanel panel = new JPanel();
		ItemContainer inventoryStack=Self.getInventory();
		int invSize=inventoryStack.getFirstEmptyIndex();
		
		String [] data;
		data= new String[invSize];
		
		for(int i=0; i< invSize; i++)
		{
			data[i]=inventoryStack.getItemStack(i).getItemName();;
		}
		
		//Create a JList 
		JList <String>myList = new JList<String>(data);
		JScrollPane scrollPane = new JScrollPane(myList);
		panel.add(scrollPane);
		
		return panel;
	}
	
	public void show(){
		frame.setPreferredSize(new Dimension(500, 500));
	}
	
	public void hide(){
		frame.setPreferredSize(new Dimension(0, 0));
	}
}
