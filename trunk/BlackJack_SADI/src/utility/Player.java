package utility;

import utility.*;
import java.net.*;

public class Player implements GameStatus
{
	private String username;
	private int playerTurn;
	private boolean isMyTurn;
	private Socket socket;
	private BlackJackHand hand;
	
	public Player(String username, int playerTurn, Socket socket) //a normal player that needs a username
	{
		this.username = username;
		this.playerTurn = playerTurn;
		this.socket = socket;
		isMyTurn = false;
		hand = new BlackJackHand();
	}
	
	public Player(Socket socket) //a default player - dealer
	{
		this.playerTurn = GameStatus.DEALER;
		this.socket = socket;
		isMyTurn = false;
		hand = new BlackJackHand();
	}
	
	//deal one card at a time and add into hand
	public void deal()
	{
		//deal means acknowledge the dealer to give out TWO cards
		//so use the method hand.addCard(aCard); into hand
	}
	
	//request another card from dealer
	public void hit()
	{
		//acknowledge the server to give out one card
		//so use the method hand.addCard(aCard); into hand
	}
	
	public void stand()
	{
		//acknowledge the server to give turn to another player
	}
	
	//when the player gets two same value, the player has to choose which set
	//of card to use for playing next
	//acknowledge the server which set of card is being used
	public void split()
	{

	}

}
