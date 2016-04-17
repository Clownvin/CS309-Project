package com.git.cs309.mmoclient.gui.interfaces;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.git.cs309.mmoclient.gui.interfaces.ClientShop;
import com.git.cs309.mmoclient.items.ItemStack;

public class ClientShopGUI extends JPanel{
	
	int shopID;
	ClientShop guiShop;
	
	public ClientShopGUI(String shopName, int shopID)
	{
		this.setSize(400, 400);
		ClientShop guiShop=new ClientShop(shopName, shopID);
		
		JTextArea nameOfShop = new JTextArea(guiShop.getName());
		this.add(nameOfShop);
		
		int inventorySize=guiShop.getNumberOfItems();
		JTextField shopInventory = new JTextField();
		for(int i=0; i<inventorySize; i++)
		{
			shopInventory.setText(shopInventory.getText()+guiShop.getItemName(i));
		}
		this.add(shopInventory);
		
		JTextField whatItemToBuy =new JTextField("whatItem");
		this.add(whatItemToBuy);
		
		JTextField whatItemToSell =new JTextField("whatItem");
		this.add(whatItemToSell);
		
		JButton buyButton = new JButton("Trade");
		this.add(buyButton);
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send info to server
				//such as whatItemToBuy and whatItemToSell
				//TODO
			}
		});
		
		this.setLayout(null);
		this.setVisible(true);
	}
	
	
	public void addtems(ItemStack items) {
		
		guiShop.addItem(items);
		//chatArea.append(message + "\n");
		//chatArea.setCaretPosition(chatArea.getText().length());
	}
	
	public void show(){
		this.setPreferredSize(new Dimension(500, 500));
	}
	
	public void hide(){
		this.setPreferredSize(new Dimension(0, 0));
	}
	

}
