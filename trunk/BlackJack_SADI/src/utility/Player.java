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
	
	public Player(String username, Socket socket) //a normal player that needs a username
	{
		this.username = username;
		this.socket = socket;
		isMyTurn = false;
		hand = new BlackJackHand();
	}
	
	public Player() //a default player - dealer
	{
		this.playerTurn = GameStatus.DEALER;
		isMyTurn = false;
		hand = new BlackJackHand();
	}
	
	/*//deal one card at a time and add into hand
	public void deal(BlackJackCard card)
	{
		//deal means acknowledge the dealer to give out TWO cards
		//so use the method hand.addCard(aCard); into hand
		hand.addCard(card);
	}
	
	//request another card from dealer
	public void hit(BlackJackCard card)
	{
		//acknowledge the server to give out one card
		//so use the method hand.addCard(aCard); into hand
		hand.addCard(card);
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

	}*/
	/***************************************************
	 * MODIFIER
	****************************************************/
	public void setPlayerTurn(int turn)
	{
		playerTurn = turn;
	}
	
	public void addCardToHand(BlackJackCard aCard)
	{
		hand.addCard(aCard);
	}
	
	/***************************************************
	 * ACCESSORS
	****************************************************/
	public Socket getSocket()
	{
		return socket;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public int getPlayerTurn()
	{
		return playerTurn;
	}
	
	public boolean isMyTurn()
	{
		return isMyTurn;
	}
	
	public BlackJackHand getHand()
	{
		return hand;
	}
	
	

}
