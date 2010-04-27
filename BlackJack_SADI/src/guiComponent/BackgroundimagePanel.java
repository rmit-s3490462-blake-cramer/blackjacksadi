
package guiComponent;

//Imports
import java.awt.*;
import javax.swing.*;

/**
 * JComponent Panel with background support
 */
@SuppressWarnings("serial")
public class BackgroundimagePanel extends JPanel {
	
	/**
	 * Backgroundimage of Panel
	 * 
	 * @var Image
	 */
	private Image _backgroundimage;
 
	/**
	 * Constructor for BackgroundimagePanel
	 * creates a new BackgroundimagePanel
	 */
	public BackgroundimagePanel(Image bg) {
		super();
		_backgroundimage = bg;
	}
 
	/**
	 * Set a new Backgroundimage
	 * 
	 * @param bg Backgroundimage
	 */
	public void setBackgroundImage(Image bg) {
		_backgroundimage = bg;
	}
	
	/**
	 * PaintComponent method
	 */
	@Override
	protected void paintComponent(Graphics g) {
		if(_backgroundimage != null) {
			g.drawImage(_backgroundimage,0,0,this);
		}
	}
 
}
