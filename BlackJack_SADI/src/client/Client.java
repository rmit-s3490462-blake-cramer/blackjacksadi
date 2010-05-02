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
	private PrintWriter out = null;
	private BufferedReader in = null;
	private String host = "localhost";
	
	public Client()
	{
		setupFrame();
	}
	
	//Connect to server to be put in awaiting pool
	public void connectToServer()
	{
		//Try to connect to a server
		//catch exception if cannot connect to server
		try
		{
			socket = new Socket (host, GameStatus.SERVERSOCKET);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
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
		
		//Talk to the server
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); //userInput is here
		String fromServer;//string from server
		String fromClient;//string from client
		try
		{
			while ((fromServer = in.readLine())!= null)//while there are still more msg from server, go get it
			{
				append("Server: " + fromServer);
				
				//now client want to talk to server
				fromClient = stdIn.readLine();
				if (fromClient != null)
				{
					append("Client: " + fromClient);//print out what have client told server
					out.println(fromClient);
				}
				else
					break;
			}
			
			/*out.close();
		    in.close();
		    stdIn.close();
		    socket.close();*/
		}
		
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O for the connection to " + host);
			System.exit(ABORT);
		}
		
		
	}
	
	public void run()
	{
		connectToServer();
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