package com.git.cs309.mmoclient.gui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

import com.git.cs309.mmoclient.gui.mainFrame.clientToServer;

/**
 * 
 * @author Jeffery Schons
 *
 */

public class demo2hackserver {

	public static void main(String[] args) {
		ArrayList<ClientHandler> arrayOfThread =new ArrayList<ClientHandler>();
		//server variables go here
		
		ServerSocket serverSocket = null;
		int clientNum = 0; // keeps track of how many clients were created
							//so can call correct client
		
		//initialized stuff here (like make map)

		// 1. CREATE A NEW SERVERSOCKET
		try {
			serverSocket = new ServerSocket(4444); // provide MYSERVICE at port 
													// use 4444 for local
			System.out.println(serverSocket);
		} catch (IOException e) {
			System.out.println("Could not listen on port: 4444");
			System.exit(-1);
		}

		// 2. LOOP FOREVER - SERVER IS ALWAYS WAITING TO PROVIDE SERVICE!
		while (true) {
			Socket clientSocket = null;
			try {
				// 2.1 WAIT FOR CLIENT TO TRY TO CONNECT TO SERVER
				System.out.println("Waiting for client " + (clientNum + 1) + " to connect!");
				clientSocket = serverSocket.accept();
				// 2.2 SPAWN A THREAD TO HANDLE CLIENT REQUEST
				System.out.println("Server got connected to a client"+ ++clientNum);
				//send (clientSocket, clientNum, arrayOfThread, veriables to send accross)
				ClientHandler test =new ClientHandler(clientSocket, clientNum, arrayOfThread);
				arrayOfThread.add(test);
				Thread t = new Thread(test);
				
				//the threads are put into an array and the area is sent to the client handler
				//before starting the thread so cann call all other threads
				
				//thread start
				t.start();

			} catch (IOException e) {
				System.out.println("Accept failed: 4444");
				System.exit(-1);
			}

			// 2.3 GO BACK TO WAITING FOR OTHER CLIENTS
		} // end while loop
	} // end of main method
} // end of class MyServer


class serverToClient implements Serializable
{
	//this entire class sent from the server to client
	//this class is for the variables that are sent between sever and client
	
	//these are local versions of variables to send
	int thisX;
	int thisY;
	int thisDirection;
	public serverToClient(int xplacement, int yplacement,int direction){
		thisX = xplacement;
		thisY=yplacement;
		thisDirection=direction;
	}
	int returnXPlacemtnt()
	{
		return thisX;
	}
	
	int returnYPlacemtnt()
	{
		return thisY;
	}
	
	int returnDirection()
	{
		return thisDirection;
	}
}

class ClientHandler implements Runnable  {
	Socket s; // this is socket on the server side that connects to the CLIENT
	int num; // keeps track of its number just for identifying purposes
	ArrayList<ClientHandler> threadArray =new ArrayList<ClientHandler>();
	//variables to send accross
	//ex
	int xLocation;
	int yLocation;
	int facingDerection;
	//private String clientMessage;
	//volitile if its something that is changed and sent to all clients
	//volatile JTextArea chatHistory;
	//volatile int[] arrayOfHoles =new int[14];
	

	ClientHandler(Socket s, int n,ArrayList<ClientHandler> arrayOfThread) 
	{
		this.s = s;
		threadArray=arrayOfThread;
		num = n-1;//to make previous first client be client 0
		//variables to send across
		//this.chatHistory = chatHistory;
		//arrayOfHoles = HoleArray;
	}

	// This is the client handling code
	public void run(){
		printSocketInfo(s); // just print some information at the server side about the connection
		try {
			while(s.getInputStream()!=null)
			{	// 1. USE THE SOCKET TO READ WHAT THE CLIENT IS SENDING
				try
				{
					ObjectInputStream inputFromClient = new ObjectInputStream(s.getInputStream());
					Object obj =new Object();
					obj=inputFromClient.readObject();
					if(obj instanceof clientToServer)
					{
						if (((clientToServer)obj).whatsThisOBJ().equalsIgnoreCase("new user"))
						{
							//do stuff	
							
						}
						else
						{
							xLocation=((clientToServer)obj).returnxPlacement();
							yLocation=((clientToServer)obj).returnyPlacement();
							facingDerection=((clientToServer)obj).returndirection();
						}

						// 2. PRINT WHAT THE CLIENT SENT
						// 3. send to other clients
						for (int i=1; i<threadArray.size();i++)
						{
							System.out.println("in loop for clients on server side");
							threadArray.get(i).printOut(xLocation, yLocation, facingDerection);
						}
					}
					//inputFromClient.close();
				}
				catch(ClassNotFoundException e)
				{
						e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end of method run()
	
	public void printSocketInfo(Socket s) {
		System.out.print("Socket on Server " + Thread.currentThread() + " ");
		System.out.print("Server socket Local Address: " + s.getLocalAddress() + ":" + s.getLocalPort());
		System.out.println("  Server socket Remote Address: " + s.getRemoteSocketAddress());
	} // end of printSocketInfo
	
	public void printOut(int xPlace, int yPlace, int direction)
	{
		try {
			serverToClient toClient =new serverToClient(xPlace,yPlace,direction);
			ObjectOutputStream outStream = new ObjectOutputStream(s.getOutputStream());
			outStream.writeObject(toClient);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//more functions to manipulate stuff

} // end of class ClientHandler
