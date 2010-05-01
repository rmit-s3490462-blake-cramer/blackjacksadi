package server;

import java.util.*;
import client.*;
import server.*;
import utility.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class BlackJackGame extends JFrame implements GameStatus
{
	private BlackJackDeck deck;
	private Dealer dealer;
	private Vector <Player> players;
	private Vector <Player> waitingPool;
	private ScoreBoard scoreBoard;
	private int playersCount, whoseTurn, trials, sessionNo;
	private static final int MAXPLAYER = 6;
	private static final int MAXTRIALS = 5;
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
		trials = 1;
		sessionNo = 1;
		setupFrame();
		newGame();
	}
	
	
	/***************************************************
	 * PUBLIC METHODS
	****************************************************/
	//new game means another new 5 trials
	//initialise the deck, dealer and also players
	public void newGame()
	{
		deck = new BlackJackDeck();
		dealer = new Dealer();
		players = new Vector<Player>();
		HandleSession handler = new HandleSession(this);
		
		deck.shuffle();
	}
	
	//restart the game with another trial and less than 5
	public void restartGame()
	{
		//initialise the deck and clear off the players and dealer
		//then newGame()
		//then add players from the waiting pool
	}
	
	public void results()
	{
		
	}
	
	/***************************************************
	 * MODIFIER
	****************************************************/
	public boolean addPlayer(Player player)
	{
		if(players.add(player))
			return true;
		return false;
	}
	
	public boolean addWaitingPlayers(Player player)
	{
		if(waitingPool.add(player))
			return true;
		return false;
	}
	
	public int nextPlayer()
	{
		if(whoseTurn <= playersCount)
			whoseTurn ++;
		else
			whoseTurn = 0;
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
	
	public int addSession()
	{
		return sessionNo++;
	}
	
	/***************************************************
	 * ACCESSORS
	****************************************************/
	public Vector<Player> getPlayers()
	{
		return players;
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
	
	public int getSession()
	{
		return sessionNo;
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
	}
}
