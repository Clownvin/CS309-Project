package com.git.cs309.mmoclient.gui.interfaces;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.git.cs309.mmoclient.entity.character.player.Self;
import com.git.cs309.mmoclient.items.ItemContainer;

public class EquitmentGUI  extends JPanel{
	private static final DungionGUI SINGLETON = new DungionGUI();
	static JFrame frame = new JFrame("self equitment");
	
	public EquitmentGUI()
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
		ItemContainer inventoryStack=Self.getEquipment();
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
		this.setPreferredSize(new Dimension(500, 500));
	}
	
	public void hide(){
		this.setPreferredSize(new Dimension(0, 0));
	}
}
