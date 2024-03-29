package client;

import utilityClient.*;

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
	private int turn = 0, whoseTurn = 0, trials = 1;
	private boolean isContinue = true, isGameEnd = false, isMyTurn = true;
	
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
		username="Client";
		setupFrame();
		connectToServer();
	}
	
	public void run()
	{
		
		//get notification from the server to start the game
		try
		{
			append("Server says: " + in.readUTF() + "\n");
			turn = in.readInt();//get player turn
		}
		catch(IOException ex)
		{
			System.err.println(ex);
			System.exit(ABORT);
		}
			
		try 
		{
			
			for (int trial = 0; trial < MAXTRIALS; trial++)
			{

				hand = new BlackJackHand();
				// get the dealt cards (only two in the beginning)
				for (int j=0; j<2; j++)
				{
					//get the suit then value
					int suit = in.readInt();
					int value = in.readInt();
					hand.addCard(new BlackJackCard(suit, value));
				}
				append(in.readUTF());//get the trial number
				
				for (int play = 0; play < MAXPLAYERS; play++)
				{
					
					
					append("\n\nWhose turn?\n"); 
					whoseTurn = in.readInt();//get whose turn number
		
					//Server tell the client whose turn now
					append("Server says: " + in.readUTF() + "\n");
					
					
					append("Cards On Hand now: " + hand.getCardsOnHand() + "\n");
					append("Values on hand now = " + hand.calculateValue() + "\n");
					
					
					//if is my turn, i start hitting
					boolean hit = true;
					
					do
					{
						if (whoseTurn == turn)
						{
							sendAction();
							String actionString = in.readUTF();
							append("Player " + whoseTurn + " action: " + actionString + "\n");
							
							//get the suit then value of the card
							if (actionString.compareTo("HIT") == 0)
							{
								int suit = in.readInt();
								int value = in.readInt();
								hand.addCard(new BlackJackCard(suit, value));
								
								//show the card out in client side
								append("Cards on hand now: " + hand.getCardsOnHand() + "\n");
								append("Values on hand now = " + hand.calculateValue() + "\n");
							}
						}
						hit = in.readBoolean();
					}while (hit);
					
					
				}
				//Read from server isGameEnd?
				isGameEnd = in.readBoolean();
				
				//Read from server who's the winner
				int winner = (in.readInt());
				String winnerMsg = "";
							
				if (winner == DRAW)
				{
					winnerMsg = "This game is a draw";
				}
				else
				{
					if (winner == turn)
					{
						winnerMsg = "*******You Won! =D*******";
					}
					else if (winner != turn)
						winnerMsg = "You Lose! =(";
				}
				
				winnerMsg += "\n";
				
				append(winnerMsg);
			}
			
			append(in.readUTF());//get the scoreboard
			
			append("*****************Thank you for playing the Server-Client BlackJack Game******************************\n");
			append("See you again! =)\n\n");
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
	//setup the frame for the client UI
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
		setTitle("BlackJack Game - " + this.username);
		setVisible(true);
		
		append("*****************Welcome to the Server-Client BlackJack Game******************************\n");
		append("Game created by: Wo Su Shyan, Yee Sean Cheng\n");
		append("Please enjoy your stay with us =)\n\n");
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