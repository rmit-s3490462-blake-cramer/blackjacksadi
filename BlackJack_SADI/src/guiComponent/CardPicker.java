
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author daanvanmanen
 * CardPicker class returns a card (as JLabel added in a JPanel) based on a value given to method getCard(value)
 */

public class CardPicker extends JPanel {
	private JPanel[] clubs = new JPanel[13];
	private JPanel[] diamonds = new JPanel[13];
	private JPanel[] hearts = new JPanel[13];
	private JPanel[] spades = new JPanel[13];
	
	public CardPicker() {}
	
	
	/**
	 * defines the card JLabels implemented in JPanels
	 */
	
	public void setCards() {
		
		//set club cards
		for(int i = 0; i<clubs.length; i++) {
			this.clubs[i] = new JPanel();
			this.clubs[i].add(new JLabel(new ImageIcon("data/cards/clubs/" + (i+1) + ".png")));
			this.clubs[i].setOpaque(false);
		}
		
		//set diamond cards
		for(int i = 0; i<diamonds.length; i++) {
			this.diamonds[i] = new JPanel();
			this.diamonds[i].add(new JLabel(new ImageIcon("data/cards/diamonds/" + (i+1) + ".png")));
			this.diamonds[i].setOpaque(false);
		}
		
		//set heart cards
		for(int i = 0; i<hearts.length; i++) {
			this.hearts[i] = new JPanel();
			this.hearts[i].add(new JLabel(new ImageIcon("data/cards/hearts/" + (i+1) + ".png")));
			this.hearts[i].setOpaque(false);
		}
		
		//set spade cards
		for(int i = 0; i<spades.length; i++) {
			this.spades[i] = new JPanel();
			this.spades[i].add(new JLabel(new ImageIcon("data/cards/spades/" + (i+1) + ".png")));
			this.spades[i].setOpaque(false);
		}
		
	}
	
	/**
	 * 
	 * @param cardType is the type of card that must be returned, e.g. h(heart)5 -> h5 -> returns card Heart with value 5
	 * @return
	 */
	
	public JPanel getCardBackground() {
		JPanel card = new JPanel();
		card.add(new JLabel(new ImageIcon("data/cards/cardbg.png")));
		card.setOpaque(false);
		return card;
	}
	
	public JPanel getCard(String cardType) {
		//return clubs
		if(cardType.equals("c2")) return clubs[0];
		else if(cardType.equals("c3")) return clubs[1];
		else if(cardType.equals("c4")) return clubs[2];
		else if(cardType.equals("c5")) return clubs[3];
		else if(cardType.equals("c6")) return clubs[4];
		else if(cardType.equals("c7")) return clubs[5];
		else if(cardType.equals("c8")) return clubs[6];
		else if(cardType.equals("c9")) return clubs[7];
		else if(cardType.equals("c10")) return clubs[8];
		else if(cardType.equals("cJack")) return clubs[9];
		else if(cardType.equals("cQueen")) return clubs[10];
		else if(cardType.equals("cKing")) return clubs[11];
		else if(cardType.equals("cAce")) return clubs[12];
		//return diamonds
		else if(cardType.equals("d2")) return diamonds[0];
		else if(cardType.equals("d3")) return diamonds[1];
		else if(cardType.equals("d4")) return diamonds[2];
		else if(cardType.equals("d5")) return diamonds[3];
		else if(cardType.equals("d6")) return diamonds[4];
		else if(cardType.equals("d7")) return diamonds[5];
		else if(cardType.equals("d8")) return diamonds[6];
		else if(cardType.equals("d9")) return diamonds[7];
		else if(cardType.equals("d10")) return diamonds[8];
		else if(cardType.equals("dJack")) return diamonds[9];
		else if(cardType.equals("dQueen")) return diamonds[10];
		else if(cardType.equals("dKing")) return diamonds[11];
		else if(cardType.equals("dAce")) return diamonds[12];
		//return hearts
		else if(cardType.equals("h2")) return hearts[0];
		else if(cardType.equals("h3")) return hearts[1];
		else if(cardType.equals("h4")) return hearts[2];
		else if(cardType.equals("h5")) return hearts[3];
		else if(cardType.equals("h6")) return hearts[4];
		else if(cardType.equals("h7")) return hearts[5];
		else if(cardType.equals("h8")) return hearts[6];
		else if(cardType.equals("h9")) return hearts[7];
		else if(cardType.equals("h10")) return hearts[8];
		else if(cardType.equals("hJack")) return hearts[9];
		else if(cardType.equals("hQueen")) return hearts[10];
		else if(cardType.equals("hKing")) return hearts[11];
		else if(cardType.equals("hAce")) return hearts[12];
		//return spades
		else if(cardType.equals("s2")) return spades[0];
		else if(cardType.equals("s3")) return spades[1];
		else if(cardType.equals("s4")) return spades[2];
		else if(cardType.equals("s5")) return spades[3];
		else if(cardType.equals("s6")) return spades[4];
		else if(cardType.equals("s7")) return spades[5];
		else if(cardType.equals("s8")) return spades[6];
		else if(cardType.equals("s9")) return spades[7];
		else if(cardType.equals("s10")) return spades[8];
		else if(cardType.equals("sJack")) return spades[9];
		else if(cardType.equals("sQueen")) return spades[10];
		else if(cardType.equals("sKing")) return spades[11];
		else if(cardType.equals("sAce")) return spades[12];
		
		return null;
	}
}
