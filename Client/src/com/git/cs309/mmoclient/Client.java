package com.git.cs309.mmoclient;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.git.cs309.mmoclient.connection.Connection;
import com.git.cs309.mmoclient.gui.login.LoginGUI;

public final class Client {
	private static volatile Connection connection;
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static boolean debug = false;
	
	private static int selfId = -1;
	
	public static void main(String[] args) {
		for (String arg : args) {
			switch (arg.toLowerCase()) {
			case "-d":
				debug = true;
				break;
			}
		}
		try {
			connection = new Connection(new Socket(debug ? "localhost" : "proj-309-21.cs.iastate.edu", 43594));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to connect to the server.");
			return;
		}
		LoginGUI.getSingleton().setVisible(true);
	}
	
	public static void setSelfId(int selfId) {
		Client.selfId = selfId;
	}
	
	public static int getSelfId() {
		return selfId;
	}
	
	public static boolean isDebug() {
		return debug;
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static Dimension getScreenSize() {
		return SCREEN_SIZE;
	}
}
