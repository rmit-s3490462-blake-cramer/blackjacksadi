package utility;

/* This class will contain all the possible action that can be done
 * either by dealer or player
 */

public class Action
{
	BlackJackHand hand;
	
	public Action()
	{
		
	}
	
	public BlackJackHand hit(BlackJackHand hand, BlackJackCard aCard)
	{
		hand.addCard(aCard);
		return hand;
	}
	
	public void stand()
	{
		
	}
	
	public void deal()
	{
		
	}
	
	//additional requirement
	//this method split a pair of cards into two hands
	public void split()
	{
		
	}
	

}
