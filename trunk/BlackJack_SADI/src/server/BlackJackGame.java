package server;

import java.util.*;
import client.*;
import server.*;
import utility.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class BlackJackGame extends JFrame implements GameStatus
{
	private BlackJackDeck deck;
	private Dealer dealer;
	private Vector <Player> players;
	private int playersCount, whoseTurn, rounds, sessionNo;
	private boolean isContinue;
	private ScoreBoard scores;
	private int port = 8000;
	
	private JTextArea jtaLog;
	/**
	 * @param args
	 */
	public BlackJackGame() 
	{
		playersCount = 0;
		setupFrame();
		newGame();
	}
	
	/********Private methods************/
	//setup the frame for the server UI
	private void setupFrame()
	{
		jtaLog = new JTextArea();
		
		//Create a scroll pane to hold text area
		JScrollPane scrollPane = new JScrollPane(jtaLog);
		
		//Add the scroll pane to the frame
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300);
		setTitle("BlackJack Game - SADI");
		setVisible(true);
	}
	
	private void append(String msg)
	{
		jtaLog.append(msg);
	}
	
	class HandleSession extends Thread implements GameStatus
	{
		public HandleSession()
		{
			try
			{
				//Create a server socket
				ServerSocket serverSocket = new ServerSocket(port);
				append(new Date() + ": Server statred at socket" + port + "\n" );
				
				//Number a session
				sessionNo = 1;
				
				//Ready to create a session for every players
				while(true)
				{
					append(new Date() + ": Wait for players to join session " + sessionNo + "\n");
					
					//Connect to player 1
					
					
					//get player's username and store inside player's vector.
					
				}
			}
			catch(IOException ex)
			{
				System.err.println(ex);
			}
		}
	}
	
	//new game means another new 5 trials
	public void newGame()
	{
		dealer = new Dealer();
		players = new Vector<Player>();	
		deck = new BlackJackDeck();
		
		
		
		
		deck.shuffle();
	}
	
	//restart the game with another trial and less than 5
	public void restartGame()
	{
		deck = new BlackJackDeck();
		deck.shuffle();
	}
	
	public void results()
	{
		
	}
	public static void main (String args[])
	{
		BlackJackGame server = new BlackJackGame();
	}


}
