package client;

import utility.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;


public class  Client extends JFrame implements Runnable, GameStatus 
{
	private JTextArea jtaLog;
	private String username = null;
	private BlackJackHand hand = null;
	private int turn = 0, whoseTurn = 0;
	private boolean isContinue = true, isGameEnd = false, myTurn = true;
	
	//Talk to the server
	private Socket socket = null;
	private DataOutputStream out = null;
	private DataInputStream in = null;
	
	private String host = "localhost";
	//private BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); //userInput is here
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
		hand = new BlackJackHand();
		connectToServer();
	}
	
	public void run()
	{
		//get notification from the server to start the game
		try
		{
			fromServer = in.readUTF();//get Game Started msg
			append("Server says: " + fromServer + "\n");
			this.turn = in.readInt();//get player turn
		}
		catch(IOException ex)
		{
			System.err.println(ex);
			System.exit(ABORT);
		}
		
		try
		{
			// get the dealt cards
			for (int j=0; j<2; j++)
			{
				//get the suit then value
				int suit = in.readInt();
				int value = in.readInt();
				hand.addCard(new BlackJackCard(suit, value));
			}
			append("The cards I have now are: " + hand.getCardsOnHand() + "\n");
			
		}
		catch(IOException ex)
		{
			System.err.println(ex);
			append("Can't get cards from server");
		} 
		catch (IllegalMonitorStateException e) 
		{
			// TODO Auto-generated catch block
			append(e.toString());
			e.printStackTrace();
		}
		
		try 
		{
			while(true)
			{
				String temp;
				//temp = in.readUTF();
				append("\nWhose turn? " + (whoseTurn = in.readInt()) + "\n"); //get whose turn
				System.out.println(whoseTurn);
				temp = in.readUTF();
				append("Server says: " + temp + "\n"); //get msg from server
				boolean hit = true;
				
				do
				{
					if (whoseTurn == turn)
					{
						sendAction();
						append("Cards on hand now: " + in.readUTF() + "\n");
					}
					//else
						//hit = false;
					hit = in.readBoolean();
					
					append("Player " + whoseTurn + " action: " + in.readUTF() + "\n");
					
					//System.out.println(hit);
				}while (hit);
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			//create a socket to connect to server
			socket = new Socket (host, GameStatus.SERVERSOCKET);
			
			//create I/O streams to send/receive from server
			out = new DataOutputStream(socket.getOutputStream());//send to server
			in = new DataInputStream(socket.getInputStream());//receive from server
		}
		catch(UnknownHostException e)
		{
			System.err.println("Don't know about the host: " + host + "\n");
			System.exit(ABORT);
		}
		
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O for the connection to " + host + "\n");
			System.exit(ABORT);
		}
		
		
		try
		{
			out.writeUTF(username);	//send username over to server		
			fromServer = in.readUTF(); //get acknowledgement after input username
			append("Server says: " + fromServer + "\n");
			
			
		}
		
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O for the connection to " + host + "\n");
			System.exit(ABORT);
		}


	}
	
	/******************THREAD ACTIONS********************/
	//Wait for the other player to hit/stand
	private void waitForOtherPlayer() throws InterruptedException
	{
		while(true)
		{
			Thread.sleep(1000);
		}
	}
	
	//send action to server
	private void sendAction() throws IOException
	{
		System.out.println("Please insert your action number: ");
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String inputAction = stdIn.readLine();
			
			
		boolean validInput = false;
		
		//input the action
		while (!validInput)
		{
			try
			{
		      // the String to int conversion happens here
		      int action = Integer.parseInt(inputAction.trim());

		      // send the action to server
		      out.writeInt(action);
		      validInput = true;
		      
		    }
		    catch (NumberFormatException nfe)
		    {
		      append("NumberFormatException: " + nfe.getMessage() + "\n");
		      append("Please try to input action again.\n");
		      System.out.println("Please insert your action number: ");
			  inputAction = stdIn.readLine();
			  
		    }						    
		}
	}
	
	//receive info from server
	private void receiveFromServer() throws IOException
	{
		//receive game status
		whoseTurn = in.readInt();//receive game status
		
		//if player_ won, stop playing
		if (whoseTurn >=7)
		{
			isGameEnd = true;
		}
		
		else
		{
			receiveMove();
			//myTurn = true;
		}
		//else if no winner, game draw
		//else if to continue, receiveMove()(from other players), then set whose turn
	}
	
	//get the other player's move by reading from server
	private void receiveMove()
	{
		try
		{
			int action = in.readInt();
		}
		catch (IOException e)
		{
			
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