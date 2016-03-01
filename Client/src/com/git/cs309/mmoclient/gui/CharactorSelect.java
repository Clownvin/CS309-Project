package com.git.cs309.mmoclient.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public final class CharactorSelect extends JFrame{
		//initalize blank
	JPanel charactorSelectPanel;//= new JPanel;
	private static final CharactorSelect SINGLETON = new CharactorSelect();
	
	
	
	private void CharactorSelect(){
		charactorSelectPanel.setSize(300, 300);
		
		
	}
	//when recive paket aboult charactoys avalible
	public void addCharactorsToSelect()
	{
		
	}
	
	public void selectedCharactor(int charactorNumber)
	{
			//after button clicket=j InterfaceClickPacket(null, InterfaceClickPacket.CHARACTER_1_SLOT, 0,0);
	}

	
	public static CharactorSelect getSingleton() {
		return SINGLETON;
	}

}
