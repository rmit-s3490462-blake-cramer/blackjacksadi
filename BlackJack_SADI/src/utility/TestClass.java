package utility;

public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
				
		BlackJackDeck deck = new BlackJackDeck();
		//System.out.println("-------------Before shuffle: ------------");
		//deck.printDeck();
		deck.shuffle();
		
		System.out.println("----------After shuffle: ---------");
		deck.printDeck();
		System.out.println("---------------------------------");
		
		System.out.println("Deal Top card = " + deck.dealTopCard());
		deck.printDeck();
		System.out.println("Deal Top card = " + deck.dealTopCard());
		System.out.println("---------------------------------");
		System.out.println("Top card now is = " + deck.topCard());
		System.out.println("---------------------------------");
		
	}

}
