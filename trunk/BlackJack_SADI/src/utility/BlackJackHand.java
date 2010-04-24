package utility;

import java.util.*;

public class BlackJackHand 
{
	private Vector<BlackJackCard> hand;
	private int cardsTotal;
	
	//initialise BlackJackHand with no cards and an empty "hand" vector
	public BlackJackHand()
	{
		hand = new Vector<BlackJackCard>();
		cardsTotal = 0;
	}
	
	//add a card into hand
	public void addCard(BlackJackCard aCard)
	{
		hand.add(aCard);
		cardsTotal++;
	}
	
	//return all the cards on hand
	public Vector <BlackJackCard>getCardsOnHand()
	{
		return hand;
	}
	
	//return last card added
	public BlackJackCard getLastCard()
	{
		return hand.elementAt(cardsTotal);
	}
	
	public boolean isBlackJack()
	{
		return true;
	}
}
