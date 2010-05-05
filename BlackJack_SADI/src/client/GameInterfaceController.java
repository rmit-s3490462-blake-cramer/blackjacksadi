//Packed in client.gui.controller
package client;

//Imports
import java.io.IOException;

//import client.GameserverConnection;
//import client.Launch;
import client.Interface;


/**
 * Controller for the GameInterface
 */
public class GameInterfaceController 
{
	
	/**
	 * GameInterface belonging to this instance
	 */
	private Interface Interface;
	
	/**
	 * GameserverConnection
	 */
	//private GameserverConnection _gsc;
	
	/**
	 * Number of Players in a game
	 */
	private int numberOfPlayers;
	
	/**
	 * Constructs a new GameInterfaceController
	 */
	public GameInterfaceController(Interface g) 
	{
		Interface = g;
		//_gameInterface.setUsername(Launch.getUsername());
		Interface.setPlayerHandAmounts("Hand 0 of 5");
		Interface.setGameAmounts("");
		//gsc = Launch.getGameserverConnection();
	}
	
	/**
	 * Set available options for client
	 * 
	 * @param split Whether split should be enabled
	 * @param Double Whether double should be enabled
	 * @param hit Whether hit should be enabled
	 * @param stand Whether stand should be enabled
	 */
	public void setOptions(boolean split,boolean hit, boolean stand) 
	{
		Interface.setOptions(split,hit, stand);
	}

	/**
	 * Operation to perform on Split
	 */
	public void splitOperation() 
	{
		/*try {
			_gsc.write("req,option,split");
		} catch (IOException e) {
			System.out.println(e);
		}*/
	}
	
	/**
	 * Operation to perform on Double
	 */
	
	/**
	 * Operation to perform on Stand
	 */
	public void standOperation() 
	{
		/*try {
			_gsc.write("req,option,stand");
		} catch (IOException e) {
			System.out.println(e);
		}*/
	}

	/**
	 * Operation to perform on Hit
	 */
	public void hitOperation() 
	{
		/*try {
			_gsc.write("req,option,hit");
		} catch (IOException e) {
			System.out.println(e);
		}*/
	}
	
	public Interface getInterface()
	{
		return this.Interface;
	}

		
	public void setNumberPlayers(int n) 
	{
		numberOfPlayers = n;
		Interface.setAmountOfPlayers(n);
		
	}
	public int getNumberPlayers()
	{
		return numberOfPlayers;
	}

	public void roundcount(int parseInt) 
	{
		Interface.roundcount(parseInt);
		
	}
	
	public void leave()
	{
		/*try {
			_gsc.write("req,leave");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}


}