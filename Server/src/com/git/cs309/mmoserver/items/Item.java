package com.git.cs309.mmoserver.items;


public class Item {
	protected volatile int cost;
	//might never be used
	protected volatile int weight;
	protected int itemID;
	protected String itemType;
	
	public Item(final int cost, final int weight, final int itemID, final String itemType)
	{
		
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public int getItemID()
	{
		return itemID;
	}
	
	public String getItemType()
	{
		return itemType;
	}
}
