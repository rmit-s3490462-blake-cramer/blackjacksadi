package utility;

import java.util.*;
import client.*;
import server.*;

public class BlackJackGame 
{
	private BlackJackDeck deck;
	private Vector <Player> players;
	/**
	 * @param args
	 */
	public BlackJackGame() 
	{
		
		

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
