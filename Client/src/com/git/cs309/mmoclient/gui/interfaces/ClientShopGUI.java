package com.git.cs309.mmoclient.gui.interfaces;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.git.cs309.mmoclient.gui.interfaces.ClientShop;
import com.git.cs309.mmoclient.items.ItemStack;

public class ClientShopGUI extends JPanel{
	
	int shopID;
	static JFrame frame = new JFrame("shop");
	ClientShop guiShop;
	
	public ClientShopGUI(String shopName, int shopID)
	{
		frame.setSize(400, 400);
		ClientShop guiShop=new ClientShop(shopName, shopID);
		
		frame.getContentPane().add(createPanel(guiShop));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	private static JPanel createPanel(ClientShop guiShop){
		
		JPanel panel = new JPanel();
		
		JTextArea nameOfShop = new JTextArea(guiShop.getName());
		panel.add(nameOfShop);
		
		int inventorySize=guiShop.getNumberOfItems();
		JTextField shopInventory = new JTextField();
		for(int i=0; i<inventorySize; i++)
		{
			
			shopInventory.setText(shopInventory.getText()+guiShop.getItemName(i));
		}
		panel.add(shopInventory);
		
		return panel;
	}
	
	
	public void addtems(ItemStack items) {
		
		guiShop.addItem(items);
		//chatArea.append(message + "\n");
		//chatArea.setCaretPosition(chatArea.getText().length());
	}
	
	public void show(){
		frame.setPreferredSize(new Dimension(500, 500));
	}
	
	public void hide(){
		frame.setPreferredSize(new Dimension(0, 0));
	}
	

}
