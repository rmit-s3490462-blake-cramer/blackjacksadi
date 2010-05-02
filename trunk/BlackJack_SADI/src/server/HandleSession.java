package server;

import java.io.*;
import java.net.*;
import java.util.*;
import utility.*;

/**
 * 
 * @author Suzann
 * This class is to handle the connections between client and server
 */

class HandleSession extends Thread implements GameStatus
{
	private int sessionNo;
	private Socket playerSocket;
	private DataInputStream fromPlayer;
	private DataOutputStream toPlayer;
	
	
	public HandleSession(BlackJackGame game)
	{
		sessionNo = 1;
		try
		{
			//Create a server socket
			ServerSocket serverSocket = new ServerSocket(GameStatus.SERVERSOCKET);
			
			
			
			//Ready to create a session for every players
			while(true)
			{
				//game.append(new Date() + ": Wait for players to join session " + sessionNo + "\n");
				
				//Accept to player and add to waitingpool			
				
				//get player's username and store inside player's vector.
				
			}
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
	}
	
	
}
