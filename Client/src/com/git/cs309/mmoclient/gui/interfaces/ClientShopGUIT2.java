package com.git.cs309.mmoclient.gui.interfaces;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.git.cs309.mmoclient.Client;
import com.git.cs309.mmoclient.gui.interfaces.ClientShop;
import com.git.cs309.mmoclient.items.ItemContainer;
import com.git.cs309.mmoclient.items.ItemStack;

public class ClientShopGUIT2 extends JPanel{
	
	int shopID;
	ClientShop guiShop;
	
	public ClientShopGUIT2(String shopName, int shopID)
	{
		this.setSize(400, 400);
		ClientShop guiShop=new ClientShop(shopName, shopID);
		
		JTextArea nameOfShop = new JTextArea(guiShop.getName());
		this.add(nameOfShop);
		
		int shopInventorySize=guiShop.getNumberOfItems();

		ItemContainer inventoryStack=Client.getSelf().getInventory();
		int playerinvSize=inventoryStack.getFirstEmptyIndex();
		 
		JTextArea shopArea= new JTextArea();//shop inventory
		for(int i=0; i<shopInventorySize; i++)
		{
			shopArea.append(guiShop.getItemName(i));
		}
		JScrollPane shopSidescrollPane = new JScrollPane(shopArea);
		shopSidescrollPane.setBounds(0,0,150,400); //left side of screen
		this.add(shopSidescrollPane);
		
		JTextArea playerArea= new JTextArea();//shop inventory
		for(int i=0; i<shopInventorySize; i++)
		{
			Client.getSelf().getInventory().getItemStack(i).getItemName();
		} 
		JScrollPane playerSidescrollPane = new JScrollPane(playerArea);//player inventory 
		playerSidescrollPane.setBounds(250,0,400,150); //right side of screen
		this.add(playerSidescrollPane);
		
		JTextField shopInventory = new JTextField();
		for(int i=0; i<playerinvSize; i++)
		{
			shopInventory.setText(shopInventory.getText()+guiShop.getItemName(i));
		}
		this.add(shopInventory);
		
		JTextField whatItemToBuy =new JTextField("whatItem");
		this.add(whatItemToBuy);
		
		JTextField whatItemToSell =new JTextField("whatItem");
		this.add(whatItemToSell);
		
		JButton acceptButton = new JButton("accept");
		acceptButton.setBounds(150,0,100,100);
		this.add(acceptButton);
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send info to server
				//such as whatItemToBuy and whatItemToSell
				//TODO
			}
		});
		
		JButton declineButton = new JButton("decline");
		acceptButton.setBounds(150,100,100,100);
		this.add(declineButton);
		declineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
			}
		});
		
		
		JTextArea amountArea = new JTextArea("amount of transaction");
		acceptButton.setBounds(150,200,200,100);
		this.add(amountArea);
		
		
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
