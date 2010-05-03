package server;

import java.io.*;
import java.net.*;
import java.util.*;
import utility.*;

/**
 * 
 * @author Suzann
 * This class is to handle the game sessions between client and server
 */

class HandleSession extends Thread implements GameStatus
{
	private int sessionNo;
	private Socket playerSocket[];
	private DataInputStream fromPlayer;
	private DataOutputStream toPlayer;
	private BlackJackGame game;
	
	public HandleSession(BlackJackGame game)
	{
		/*Vector <Player> players = game.getPlayers();
		for (int i=0; i<players.size(); i++)
		{
			playerSocket[i] = players.elementAt(i).getSocket();
		}
		sessionNo = 1;*/
		
	}
	
	public void run()
	{
		
	}
	
	
}
