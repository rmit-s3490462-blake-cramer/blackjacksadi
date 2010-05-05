package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CardDeckLabel extends JLabel {
	private String name;
	private Image backgroundImage;
	
	
	public CardDeckLabel(String name, Color color, int fontSize, boolean dealer) throws Exception {
		super(name, JLabel.CENTER);
		if(name.equals(null)) {
			throw new Exception("You have to enter a name for the JLabel.");
		} else if(color == null) {
			throw new Exception("Please enter a number.");
		} else if(fontSize == 0) {
			throw new Exception("Please enter a value for int fontSize, default is 11");
		} else {
			this.name = name;
			setForeground(color);
			setFont(new Font(getFont().getFontName(), Font.BOLD, fontSize));
			setOpaque(false);
			//Set background image for the dealer
			if(dealer)
				this.backgroundImage = new ImageIcon("img/labelBgDealer.png").getImage();
			
			//Set background image for a normal player
			else this.backgroundImage = new ImageIcon("img/labelBg.png").getImage();
		}
	}
	
	public void paintComponent(Graphics g) {
		if(backgroundImage != null) {
			if(name != null) {
				g.drawImage(backgroundImage, 0, 0, 100, 23, null, null);
				super.paintComponent(g);
				this.setText(name);
			}
		}
	}
	
	public JLabel getJLabel() {
		return this;
	}
}
