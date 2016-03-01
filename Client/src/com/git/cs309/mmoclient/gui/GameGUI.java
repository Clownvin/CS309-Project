package com.git.cs309.mmoclient.gui;

import static com.git.cs309.mmoclient.gui.gameConfig.title;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

import serverTest.Player;
import serverTest.ReadMapFile;

import com.engine.graphics.Engine;
import com.git.cs309.mmoclient.Client;
import com.git.cs309.mmoclient.UpdateThread;
import com.git.cs309.mmoclient.NPC.GetNPC;
import com.git.cs309.mmoclient.gui.mainFrame.MyPanel;
import com.git.cs309.mmoclient.gui.mainFrame.PanelListenner;
import com.git.cs309.mmoserver.packets.MessagePacket;
import com.git.cs309.mmoserver.packets.MovePacket;

public class GameGUI extends JFrame {

	JPanel panel;
	JPanel tpanel;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6023633314141474861L;
	
	private static final GameGUI SINGLETON = new GameGUI();
	private static final Thread repainter = new Thread() {
		@Override
		public void run() {
			while (SINGLETON.isVisible()) {
				try {
					Thread.sleep(16);
				} catch (InterruptedException e) 
				{
				}
				SINGLETON.repaint();
			}
		}
	};
	
	private GameGUI() {
		//initalize mapFactory in login
		//ReadMapFile.readfile("map1.map");
		
		//make data type calle entity, w/ abstract intety returns spritre.
		setTitle("Cilent header");
		add(Engine.getSingleton());
		setSize(3000, 3000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBounds(18, 5, 100, 100);
		panel.setBackground(Color.black);
		
		add(panel);
		
		//ex for message packet
		//send packets via Client.getConnection().addoutgoingpacket(new MessagePacket(null, all variable stuff));
		
		PanelListenner plis = new PanelListenner();
		this.addKeyListener(plis);
	}
	
	public static GameGUI getSingleton() {
		return SINGLETON;
	}
	
	@Override
	public void setVisible(boolean state) {
		super.setVisible(state);
		repainter.start();
	}
	
	
	//when get new map packet, make call MapFactory to get new map
	//when ever to make update to existing map, call map that is from map factory
	public static void initalizeMap(String NameOfTheMap)
	{
		
	}
	
	public static void updateMap()
	{
		
	}
	
	public static void entityUpdate()
	{
		
	}
	
	public static void addCharactertoMap(int extCharcharacterID, String extCharcharacterName, int extCharhealth, int extCharmaxHealth, int extCharuniqueID, int extCharx, int extChary) {
		// TODO Auto-generated method stub
	}
	
	public static void addObjecttoMap(int extObjstaticID, int extObjuniqueID, int extObjx, int extObjy, String extObjname) {
		// TODO Auto-generated method stub
	}
	
	public static void addPlayertoMap(int pCBoots, int pCCape, int pCChestPiece, byte pCGender, int pCGloves, int pCGloves2, int pCHeadPiece, int pCHealth, int pCLeftHand, int pCLeggings, int pCLevel, int pCMaxHealth, String pCName, int pCRightHand, int pCUniqueID, int pCX, int pCY) {
		// TODO Auto-generated method stub
	}
	
	public static void setID(int theID) {
		// TODO Auto-generated method stub
	}
	
	class PanelListenner extends KeyAdapter{
		
		//get read of keypressed, keep keyReleased
		//get my posisiton
		int myX;
		int myY;
		
		public void keyReleased(KeyEvent e){
				int code = e.getKeyCode();
				switch (code) {
				case KeyEvent.VK_UP:
					Client.getConnection().addOutgoingPacket(new MovePacket(null, myX,myY));
					break;
				case KeyEvent.VK_DOWN:
					Client.getConnection().addOutgoingPacket(new MovePacket(null, myX,myY));
					break;
				case KeyEvent.VK_LEFT:
					Client.getConnection().addOutgoingPacket(new MovePacket(null, myX,myY));
					break;
				case KeyEvent.VK_RIGHT:
					Client.getConnection().addOutgoingPacket(new MovePacket(null, myX,myY));
					break;
				default:
					break;
				}
		}
		
	}

	

}
