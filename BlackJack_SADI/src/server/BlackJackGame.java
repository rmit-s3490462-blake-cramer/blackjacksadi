package server;

import java.util.*;

import client.*;
import server.*;
import utility.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class BlackJackGame extends JFrame implements Runnable, GameStatus
{
	private BlackJackDeck deck;
	private Player dealer;
	private Vector <Player> players;
	private Vector <Player> waitingPool;
	private ScoreBoard scoreBoard;
	private int playersCount, waitingPlayersCount, whoseTurn, trials;

	private boolean isContinue;
	
	
	//To display out the messages
	private JTextArea jtaLog;
	/**
	 * @param args
	 */

	//Constructor of a new BlackJackGame
	public BlackJackGame() 
	{
		playersCount = 0;
		waitingPlayersCount = 0;
		trials = 1;
		dealer = new Player();
		players = new Vector<Player>();
		waitingPool = new Vector<Player>();
		whoseTurn = GameStatus.PLAYER1;
		setupFrame();
		createConnections();
	}
	
	
	/***************************************************
	 * PUBLIC METHODS
	****************************************************/
	//new game means another new 5 trials
	//initialise the deck, dealer and also players
	public void newGame()
	{
		deck = new BlackJackDeck();
		deck.shuffle();
	}
	
	//restart the game with another trial and less than 5
	public void restartGame()
	{
		//initialise the deck and clear off the players and dealer
		//then newGame()
		//then add players from the waiting pool
		deck = new BlackJackDeck();
		deck.shuffle();
	}
	
	public void results()
	{
		
	}
	
	/*****************Actions**********************/
	public void deal()
	{
		BlackJackCard aCard = deck.dealTopCard();
		players.elementAt(whoseTurn -1).addCardToHand(aCard);
	}
	
	public boolean hit()
	{
		BlackJackCard aCard = deck.dealTopCard();
		if (players.elementAt(whoseTurn-1).getHand().getCardsTotal() < 5)
		{
			players.elementAt(whoseTurn -1).addCardToHand(aCard);
			return true;
		}
		else
			return false;
	}
	
	public void stand()
	{
		this.nextPlayer();
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
	}
	
	/***************************************************
	 * MODIFIER
	****************************************************/
	public boolean addPlayer(Player player)
	{
		if(players.add(player))
		{
			
			return true;
		}
		return false;
	}
	
	public boolean addWaitingPlayers(Player player)
	{
		if(waitingPool.add(player))
			return true;
		return false;
	}
	
	public void removeWaitingPlayers()
	{
		waitingPool.removeAllElements();		
	}
	
	public int nextPlayer()
	{
		if(whoseTurn < players.size())
			whoseTurn ++;
		else
			whoseTurn = 1;
		return whoseTurn;
	}
	
	public int addTrial()
	{
		if(trials <= MAXTRIALS)
			trials ++;
		else
			trials = 1;
		return trials;
	}
	
	public void addPlayersCount()
	{
		playersCount++;
	}
	
	public void minusPlayersCount()
	{
		playersCount--;
	}
	
	public void addWaitingPlayersCount()
	{
		waitingPlayersCount++;
	}
	
	public void minusWaitingPlayersCount()
	{
		waitingPlayersCount--;
	}
	
	public void setScoreBoard(int numOfPlayers)
	{
		
	}
	
	/***************************************************
	 * ACCESSORS
	****************************************************/
	public Vector<Player> getPlayers()
	{
		return players;
	}
	
	public Player getDealer()
	{
		return dealer;
	}
	
	public Vector<Player> getWaitingPlayers()
	{
		return waitingPool;
	}
	
	public ScoreBoard getScoreBoard()
	{
		return scoreBoard;
	}
	
	public int getWhoseTurn()
	{
		return whoseTurn;
	}
	
	public int getTrial()
	{
		return trials;
	}
	
	public int getPlayersCount()
	{
		return playersCount;
	}
	
	/***************************************************
	 * PRIVATE METHODS
	****************************************************/
	//create connection
	private void createConnections()
	{
		try
		{
			//Create a server socket
			ServerSocket serverSocket = new ServerSocket(GameStatus.SERVERSOCKET);
			
			
			//Append a msg to the JTextArea
			append(new Date() + ": Server waiting for players to connect...\n" );
			
			HandleConnection connectionHandler = new HandleConnection(this);
			
			while(true)
			{
				//if(getPlayersCount() <= MAXPLAYERS)
					connectionHandler.connect(serverSocket);
				
				//Create a new thread for this session of 6 players			
				if (waitingPlayersCount == MAXPLAYERS)
				{
					HandleSession thread = new HandleSession(this);
					thread.start();
				}
			}
		}
		catch (IOException e)
		{
			append(e.toString());
		}
	}
	
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
		setTitle("BlackJack Game - SADI");
		setVisible(true);
	}
	
	
	
	/***************************************************
	 * DEFAULT METHODS
	****************************************************/
	void append(String msg)
	{
		jtaLog.append(msg);
	}
	
	public static void main (String args[])
	{
		BlackJackGame server = new BlackJackGame();
		server.newGame();
		
	}


	
}
