package com.git.cs309.mmoclient.gui;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientShop {
	
	private static final long serialVersionUID;
	private final String shopName;
	private final int id;
	//private item[] shopItems; //change to array list
	ArrayList<ItemDefinition> shopItems = new ArrayList<ItemDefinition>(0);
	
	//murchents can have multiple shops and
	//several murchents could access the same shop 
	
	public ClientShop(final String shopName, final int id) {
		this.shopName = shopName;
		this.id = id;
	}

	public String getName()
	{
		return shopName;
	}
	
	public int getShopID()
	{
		return id;
	}
	
	public void addItem(ItemDefinition itemToAdd)
	{
		shopItems.add(itemToAdd);
	}
	
	public void addItemToIndex(int index, ItemDefinition itemToAdd)
	{
		shopItems.set(index, itemToAdd);
	}
	
	public ItemDefinition getItem(int indexOfItem)
	{
		return shopItems.get(indexOfItem);
	}
	
	public void removeItem(int index)
	{
		shopItems.set(index, null);
	}
	
	public String getItemName(int index)
	{
		return shopItems.get(index).getItemName();
	}
	
	

}
