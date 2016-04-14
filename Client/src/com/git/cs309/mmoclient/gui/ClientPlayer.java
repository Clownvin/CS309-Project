package com.git.cs309.mmoclient.gui;

import java.util.ArrayList;

public class ClientPlayer {
	int NO_GEAR = 0; // No gear in the slot with this value
	byte gender; //male=0, female=1
	int uniqueID;
	int x;
	int y;
	int health;
	int maxHealth;
	int level;
	int headPiece;
	int chestPiece;
	int leftHand;
	int rightHand;
	int cape;
	int leggings;
	int gloves;
	int boots;
	String name;
	ArrayList<ItemDefinition> inventoryItems = new ArrayList<ItemDefinition>(0);

	public void setID(int theID) {
		uniqueID=theID;
	}

	public int getID() {
		return uniqueID;
	}

	public void setBoots(int pCBoots) {
		boots=pCBoots;
	}
	
	public int getBoots(){
		return boots;
	}

	public void setCape(int pCCape) {
		cape=pCCape;
	}

	public int getCape(){
		return cape;
	}
	
	public void setChestPiece(int pCChestPiece) {
		chestPiece=pCChestPiece;
	}
	
	public int getChestPiece(){
		return chestPiece;
	}

	public void setGender(byte pCGender) {
		gender=pCGender;
	}
	
	public byte getGender(){
		return gender;
	}

	public void setGloves(int pCGloves) {
		gloves=pCGloves;
	}

	public int getGloves(){
		return gloves;
	}
	
	public void setHeadPiece(int pCHeadPiece) {
		headPiece=pCHeadPiece;
	}

	public int getHeadPice(){
		return headPiece;
	}
	
	public void setHealth(int pCHealth) {
		health=pCHealth;
	}

	public int getHealth(){
		return health;
	}
	
	public void setLeftHand(int pCLeftHand) {
		leftHand=pCLeftHand;
	}
	
	public int getLeftHand(){
		return leftHand;
	}

	public void setLeggings(int pCLeggings) {
		leggings=pCLeggings;
	}
	
	public int getLeggings(){
		return leggings;
	}

	public void setLevel(int pCLevel) {
		level=pCLevel;
	}

	public int getLevel(){
		return level;
	}
	
	public void setName(String pCName) {
		name=pCName;
	}
	
	public String getName(){
		return name;
	}

	public void setMaxHealth(int pCMaxHealth) {
		maxHealth=pCMaxHealth;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}

	public void setRightHand(int pCRightHand) {
		rightHand=pCRightHand;
	}
	
	public int getRightHand(){
		return rightHand;
	}

	public void setX(int pCX) {
		x=pCX;
	}
	
	public int getX() {
		return x;
	}

	public void setY(int pCY) {
		y=pCY;
	}

	public int getY() {
		return y;
	}
	
	public void addItem(ItemDefinition itemToAdd)
	{
		inventoryItems.add(itemToAdd);
	}
	
	public void addItemToIndex(int index, ItemDefinition itemToAdd)
	{
		inventoryItems.set(index, itemToAdd);
	}
	
	public ItemDefinition getItem(int indexOfItem)
	{
		return inventoryItems.get(indexOfItem);
	}
	
	public void removeItem(int index)
	{
		inventoryItems.set(index, null);
	}
	
	public String getItemName(int index)
	{
		return inventoryItems.get(index).getItemName();
	}

}
