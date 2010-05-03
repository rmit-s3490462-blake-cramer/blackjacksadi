package client;

import utility.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;


public class Client extends JFrame implements Runnable, GameStatus 
{
	private JTextArea jtaLog;
	private Socket socket = null;
	private DataOutputStream out = null;
	private DataInputStream in = null;
	private String host = "localhost";
	//Talk to the server
	private BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); //userInput is here
	private String username = null;
	private String fromServer;//string from server
	private String fromClient;//string from client
	
	public Client()
	{
		setupFrame();
		/*try
		{
			System.out.println("Please insert your username: ");
			username = stdIn.readLine();//now input username
		}
		catch (IOException ex)
		{
			System.err.println();
		}*/
		username="test";
	}
	
	public void run()
	{
		connectToServer();
		/*try
		{
			//Get notification from the server which turn
			int playerTurn = in.readInt();
		}
		catch(Exception e)
		{
			
		}*/
	}
	
	/***************************************************
	 * PRIVATE METHODS
	****************************************************/
	//setup the frame for the server UI
	private void setupFrame()
	{
		jtaLog = new JTextArea();
		jtaLog.setEditable(false);
		
		//Create a scroll pane to hold text area
		JScrollPane scrollPane = new JScrollPane(jtaLog);
		
		//Add the scroll pane to the frame
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		setTitle("BlackJack Game - SADI (Client)");
		setVisible(true);
	}
	
	//Connect to server to be put in awaiting pool
	private void connectToServer()
	{
		//Try to connect to a server
		//catch exception if cannot connect to server
		try
		{
			//create a socket to connecto to server
			socket = new Socket (host, GameStatus.SERVERSOCKET);
			out = new DataOutputStream(socket.getOutputStream());//send to server
			in = new DataInputStream(socket.getInputStream());//receive from server
		}
		catch(UnknownHostException e)
		{
			System.err.println("Don't know about the host: " + host);
			System.exit(ABORT);
		}
		
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O for the connection to " + host);
			System.exit(ABORT);
		}
		
		try
		{
			out.writeUTF(username);			
			fromServer = in.readUTF(); //get acknowledgement after input username
			append("Server says: " + fromServer);
		}
		
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O for the connection to " + host);
			System.exit(ABORT);
		}
	}
	
	
	
	/***************************************************
	 * DEFAULT METHODS
	****************************************************/
	void append(String msg)
	{
		jtaLog.append(msg);
	}
	
	/***************************************************
	 * MAIN METHOD
	****************************************************/
	public static void main (String [] args) throws IOException
	{
		new Thread(new Client()).start();
		
	}
}