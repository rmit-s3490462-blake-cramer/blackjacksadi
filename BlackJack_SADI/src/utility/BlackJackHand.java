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
	
	//calculate the values of blackjack
	public int calculateValue()
	{
		int total = 0;
		
		Iterator <BlackJackCard> ite = hand.iterator() ;
		while (ite.hasNext())
		{
			BlackJackCard current = ite.next();
			int value;
			
			if (current.getValue() == BlackJackCard.ACE)
			{
				if (this.cardsTotal <= 2)
					value = 11;
				
				else 
					value = 1;
			}
			
			else
				value = current.getValue();
			
			total += value;
		}
		
		return total;
	}
	
	//check if the total cards in hand is has only a number of TWO
	public boolean isOnlyTwoCards()
	{
		if (cardsTotal == 2)
		{
			return true;
		}
		else
			return false;
	}
	
	//check if the cards on hand is blackjack
	//blackjack is an ACE with any face card or 10
	public boolean isBlackJack()
	{
		BlackJackCard firstCard, secondCard;
		firstCard = hand.elementAt(0);
		secondCard = hand.elementAt(1);
		
		if (isOnlyTwoCards())
		{
			if((firstCard.getValue() == BlackJackCard.ACE || firstCard.getValue() == 10) &&(secondCard.getValue() == BlackJackCard.ACE || secondCard.getValue() == 10) )
				return true;
			else 
				return false;
		}
		else
			return false;
	}
	
	//check if the cards on hand are two Ace
	public boolean isTwoAce()
	{
		BlackJackCard firstCard, secondCard;
		firstCard = hand.elementAt(0);
		secondCard = hand.elementAt(1);
		
		if (isOnlyTwoCards())
		{
			if(firstCard.getValue() == BlackJackCard.ACE  && secondCard.getValue() == BlackJackCard.ACE )
				return true;
			else 
				return false;
		}
		else
			return false;
	}
	
	
}
