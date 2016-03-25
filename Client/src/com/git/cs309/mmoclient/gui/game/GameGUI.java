package com.git.cs309.mmoclient.gui.game;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.git.cs309.mmoclient.Client;
import com.git.cs309.mmoclient.Config;
import com.git.cs309.mmoclient.gui.interfaces.ChatBox;

public class GameGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6023633314141474861L;
	
	private static final GameGUI SINGLETON = new GameGUI();
	
	private volatile boolean leftPressed = false;
	private volatile boolean rightPressed = false;
	
	private final Thread cameraThread = new Thread(new Runnable() {

		@Override
		public void run() {
			System.out.println("Here");
			while (true) {
				synchronized (SINGLETON) {
					try {
						SINGLETON.wait(10);
					} catch (InterruptedException e) {
					}
				}
				while (leftPressed || rightPressed) {
					System.out.println("Exited Wait & is pressed");
					if (leftPressed) {
						Client.addRotation(Config.ROTATION_INCREMENT);
					}
					if (rightPressed) {
						Client.addRotation(Config.ROTATION_INCREMENT);
					}
					GameGUI.this.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
					}
				}
			}
		}
		
	});
	
	private GameGUI() {
		cameraThread.start();
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent arg0) {
				Component chatBox = ChatBox.getInstance();
				Component viewPanel = ViewPanel.getInstance();
				ChatBox.getInstance().setLocation(0, viewPanel.getHeight() - chatBox.getHeight());
			}
			
		});
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				System.out.println("Pressed");
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				System.out.println("Released");
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				System.out.println("Typed");
			}
			
		});
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(ViewPanel.getInstance());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static GameGUI getSingleton() {
		return SINGLETON;
	}
	
	@Override
	public void setVisible(boolean state) {
		super.setVisible(state);
	}
}
