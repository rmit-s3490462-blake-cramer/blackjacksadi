package server;

import java.util.*;
import client.*;
import server.*;
import utility.BlackJackDeck;
import utility.Dealer;
import utility.Player;

public class BlackJackGame 
{
	private BlackJackDeck deck;
	private Dealer dealer;
	private Vector <Player> players;
	/**
	 * @param args
	 */
	public BlackJackGame() 
	{
		dealer = new Dealer();
		players = new Vector<Player>();		

	}
	
	public void newGame()
	{
		deck = new BlackJackDeck();
		
		deck.shuffle();
	}
	
	public void restartGame()
	{
		deck = new BlackJackDeck();
		deck.shuffle();
	}
	
	public void results()
	{
		
	}


}
