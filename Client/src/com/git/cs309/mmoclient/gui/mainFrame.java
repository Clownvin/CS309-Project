package com.git.cs309.mmoclient.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import map.GetMap;

import com.git.cs309.mmoclient.Client;
import com.git.cs309.mmoclient.UpdateThread;
import com.git.cs309.mmoclient.NPC.GetNPC;
import com.git.cs309.mmoclient.connection.Connection;
import com.git.cs309.mmoserver.packets.*;

import serverTest.Player;
import serverTest.ReadMapFile;

public class mainFrame extends JFrame implements gameConfig{
	static int tag = 1;
	JPanel panel;
	JPanel tpanel;
	public Socket socket;
	public int xplace;
	public int yplace;
	public int direcetion;
	private final Connection connection;
	
	public mainFrame() {
		//this is the run method
		try {
			//initalize the socket
			connection = new Connection(new Socket("proj-309-21.cs.iastate.edu", 43594));
			//socket = new Socket("localhost", 4444);
			//method to open connection
			//callServer(socket);
			try {
				init(socket);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void init(Socket socket){
		this.setTitle(title);
		this.setSize(frameX, frameY);
		this.setLayout(null);
		this.setDefaultCloseOperation(3);

		tpanel = settpanel();
		panel = setpanel();
		
		this.add(tpanel);
		this.add(panel);
		
		
		this.setVisible(true);
		
		PanelListenner plis = new PanelListenner();
		this.addKeyListener(plis);
		
		
		Player player = new Player();
		player.start();
		
		//added to send to recive from server
		//Thread t = new Thread(new ServerHandler(socket, xplace, yplace, direcetion));
		//t.start();
		
		
		UpdateThread ut = new UpdateThread(panel,tpanel);
		ut.start();
	}
	
	public JPanel setpanel(){
		JPanel panel = new MyPanel();
		panel.setBounds(18, 5, panelX, panelY);
		panel.setLayout(null);
		panel.setBackground(Color.black);
		
		return panel;
	}
	
	public JPanel settpanel(){
		JPanel tpanel = new TalkPanel();
		return tpanel;
	}
	
	public void callServer(Socket socket) throws UnknownHostException, IOException{
		try {
			//this is the initial server call to open initial connection
			//clientToServer toServer = new clientToServer("new user", 0, 0, 0);
			//Client.getConnection().addoutgoingpacket(new MessagePacket(null, all variable stuff));
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			outStream.writeObject(toServer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class PanelListenner extends KeyAdapter{
		
		//get read of keypressed, keep keyReleased
		
		public void keyPressed(KeyEvent e){
			int code = e.getKeyCode();
			if(tag==1){
				switch (code) {
				case KeyEvent.VK_UP:
					Player.up = true;
					Player.towards = 1;
					try {
						//ex send packet
						Client.getConnection().addOutgoingPacket(new MessagePacket(null, all variable stuff));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				case KeyEvent.VK_DOWN:
					Player.down = true;
					Player.towards = 2;
					try {
						sendTOServer(xplace, yplace, direcetion);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				case KeyEvent.VK_LEFT:
					Player.left = true;
					Player.towards = 3;
					try {
						sendTOServer(xplace, yplace, direcetion);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				case KeyEvent.VK_RIGHT:
					Player.right = true;
					Player.towards = 4;
					try {
						sendTOServer(xplace, yplace, direcetion);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				case KeyEvent.VK_G:
					if(Player.towards==1){
						int num = ReadMapFile.map3[Player.y/elesize-1][Player.x/elesize];
						if(num!=0){
							if(GetNPC.map.get(num)==null){
								GetNPC.getnpc(num);
								TalkPanel.gettalknpc(num);
							}
							tag = 2;
							tpanel.setBounds(28, 500, 630, 150);
							
							tpanel.repaint();
							System.out.println(1);
						}
					}else if(Player.towards==2){
						if(ReadMapFile.map3[Player.y/elesize+1][Player.x/elesize]!=0){
							tpanel.setBounds(28, 500, 630, 150);
							tag = 2;
							tpanel.repaint();
						}
					}else if(Player.towards==3){
						if(ReadMapFile.map3[Player.y/elesize][Player.x/elesize-1]!=0){
							tpanel.setBounds(28, 500, 630, 150);
							tag = 2;
							tpanel.repaint();
						}
					}else if(Player.towards==4){
						if(ReadMapFile.map3[Player.y/elesize][Player.x/elesize+1]!=0){
							tpanel.setBounds(28, 500, 630, 150);
							tag = 2;
							tpanel.repaint();
						}
					}
					try {
						sendTOServer(xplace, yplace, direcetion);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;

				default:
					break;
				}
			}else if(tag==2){
				if(code==KeyEvent.VK_G){
					tag=1;
					tpanel.setBounds(28, 500, 0, 0);
				}
			}
			
		}
		
		public void keyReleased(KeyEvent e){
			if(tag==1){
				int code = e.getKeyCode();
				switch (code) {
				case KeyEvent.VK_UP:
					Player.up = false;
					Player.up1 = 0;
					break;
				case KeyEvent.VK_DOWN:
					Player.down = false;
					Player.down1 = 0;
					break;
				case KeyEvent.VK_LEFT:
					Player.left = false;
					Player.left1 = 0;
					break;
				case KeyEvent.VK_RIGHT:
					Player.right = false;
					Player.right1 = 0;
					break;

				default:
					break;
				}
			}
		}
		
	}
	
	class MyPanel extends JPanel{
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			for(int i=Player.getI()-6;i<=Player.getI()+6;i++){
				for(int j=Player.getJ()-6;j<=Player.getJ()+6;j++){
					
					if(i>=0&&j>=0&&i<ReadMapFile.map1.length&&j<ReadMapFile.map1[0].length){
						
						ImageIcon icon = GetMap.int2icon(ReadMapFile.map1[i][j]);
						g.drawImage(icon.getImage(), (Player.px-elesize/2)+((j-Player.getJ())*elesize)-(Player.mx%elesize), (Player.py-elesize/2)+((i-Player.getI())*elesize)-(Player.my%elesize), elesize, elesize, null);
						
						if(ReadMapFile.map2[i][j]!=0){
							ImageIcon icon2 = GetMap.int2icon(ReadMapFile.map2[i][j]);
							g.drawImage(icon2.getImage(), (Player.px-elesize/2)+((j-Player.getJ())*elesize)-(Player.mx%elesize), (Player.py-elesize/2)+((i-Player.getI())*elesize)-(Player.my%elesize), elesize, elesize, null);
						}
						
						if(ReadMapFile.map3[i][j]!=0){
							ImageIcon icon3 = GetMap.int2npc(ReadMapFile.map3[i][j]);
							g.drawImage(icon3.getImage(), (Player.px-elesize/2)+((j-Player.getJ())*elesize)-(Player.mx%elesize), (Player.py-elesize/2)+((i-Player.getI())*elesize)-(Player.my%elesize), elesize, elesize, null);
						}
						
					}
				}
			}
			
			Player.draw(g);
			
			
			g.drawImage(shadow2.getImage(), 0, 0, 650, 650, null);
		}
	}
	public static int getTag() {
		return tag;
	}
	
	//added bellow
	
	class clientToServer implements Serializable
	{
		//the object to send to server
		volatile String whatDo; //calling, move, what tis is for
		volatile int xPlacement;
		volatile int yPlacement;
		volatile int directionFacing;
		
		public clientToServer(String whatDoesThisDo, int xplace, int yplace, int direaction){
			//whereTOTOSERVER=whereTo;
			whatDo=whatDoesThisDo;
			xPlacement=xplace;
			yPlacement=yplace;
			directionFacing=direaction;
			
		}
		public String whatsThisOBJ()
		{
			return whatDo;
		}
		public int returnxPlacement()
		{ 
			return xPlacement;   
		}
		
		public int returnyPlacement()
		{ 
			return yPlacement;   
		}
		
		public int returndirection()
		{ 
			return directionFacing;   
		}
	}

	class ServerHandler implements Runnable {
		Socket s; //this clients socket

		int xlocation;
		int yloaction;
		int direactionfacing;

		public ServerHandler(Socket socket, int xplace, int yplace, int direaction)
		{
			s=socket;
			xlocation=xplace;
			yloaction=yplace;
			direactionfacing=direaction;
		}  
	  
		public void run() {
			try {
				while(s.getInputStream()!=null)  
				{	// 1. USE THE SOCKET TO READ WHAT THE Server IS SENDING
					try{
						ObjectInputStream inputFromClient = new ObjectInputStream(s.getInputStream()); 
						Object obj = new Object();
						obj = inputFromClient.readObject();
						if(obj instanceof serverToClient)
						{
							xlocation=((serverToClient)obj).returnXPlacemtnt();
							yloaction=((serverToClient)obj).returnYPlacemtnt();
							direactionfacing=((serverToClient)obj).returnDirection();
							//historyTextArea.setText((((serverToClient) obj).returnTextField()).getText());
						}
					}
					catch(ClassNotFoundException e)
					{
						e.printStackTrace();
					} 
					// 2. PRINT WHAT THE CLIENT SENT
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
