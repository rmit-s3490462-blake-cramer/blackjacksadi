package utility;

import java.util.*;
import java.io.*;

public class BlackJackHand
{
	/**
	 * 
	 */
	private Vector<BlackJackCard> hand;
	private int cardsTotal;
	
	//initialise BlackJackHand with no cards and an empty "hand" vector
	public BlackJackHand()
	{
		hand = new Vector<BlackJackCard>();
		cardsTotal = 0;
	}
	
	
	/***************************************************
	 * ACCESSORS
	****************************************************/
	//return all the cards on hand
	public Vector <BlackJackCard>getCardsOnHand()
	{
		return hand;
	}
	//return number of cards
	public int getCardsTotal()
	{
		return cardsTotal;
	}
	
	//return last card added
	public BlackJackCard getLastCard()
	{
		return hand.elementAt(cardsTotal-1);
	}
	
	//return one particular card
	public BlackJackCard getCard(int atIndex)
	{
		return hand.elementAt(atIndex);
	}
	
	/***************************************************
	 * MODIFIER
	****************************************************/
	//add a card into hand
	public void addCard(BlackJackCard aCard)
	{
		hand.add(aCard);
		cardsTotal++;
	}
	
	/***************************************************
	 * CALCULATION
	****************************************************/
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
				//If total number of cards is 2, then Ace = 11
				if (isOnlyTwoCards())
					value = 11;
				
				//If total number of cards is 3, then Ace is 11 or 1
				else if (cardsTotal == 3)
				{
					if ((total+11)> 21 ) 
					{
						value = 1;
					}
					else
						value = 11;
				}
				
				//If total number of cards is 4 and above, Ace is 1
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
		if (isOnlyTwoCards())
		{
			BlackJackCard firstCard, secondCard;
			firstCard = hand.elementAt(0);
			secondCard = hand.elementAt(1);
			
			
			if((firstCard.getValue() == BlackJackCard.ACE && secondCard.getValue() == 10)||(firstCard.getValue() == 10 && secondCard.getValue() == BlackJackCard.ACE) )
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
		if (isOnlyTwoCards())
		{
			BlackJackCard firstCard, secondCard;
			firstCard = hand.elementAt(0);
			secondCard = hand.elementAt(1);
			
			if(firstCard.getValue() == BlackJackCard.ACE  && secondCard.getValue() == BlackJackCard.ACE )
				return true;
			else 
				return false;
		}
		else
			return false;
	}
	
	//return true if the cards in hand is equal to 21
	public boolean is21()
	{
		if(!isOnlyTwoCards())
		{
			if(calculateValue() == 21)
				return true;
			else
				return false;
		}
		else
			return false;
	
	}
	
	//check if the cards in hand is over 17
	public boolean isUnder17()
	{
		if (calculateValue() < 17 )
			return true;
		else
			return false;
	}
	
	//check if the cards in hand is over 17
	public boolean isOver17()
	{
		if (calculateValue() >= 17 )
			return true;
		else
			return false;
	}
	
	//check if the value in hand is less than 21
	public boolean isUnder21()
	{
		if (calculateValue() <= 21 )
			return true;
		else
			return false;
	}
	
	//check if the value in hand is already burst (more than 21)
	public boolean isBurst()
	{
		if (calculateValue() > 21)
			return true;
		else
			return false;
	}
	
	//check if the value in hand is a pair
	public boolean isPair()
	{
		if (isOnlyTwoCards())
		{
			BlackJackCard firstCard, secondCard;
			firstCard = hand.elementAt(0);
			secondCard = hand.elementAt(1);
			
			if (firstCard.getValue() == secondCard.getValue())
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
}
