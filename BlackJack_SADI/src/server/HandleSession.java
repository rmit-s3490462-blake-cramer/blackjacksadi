package server;

import java.io.*;
import java.net.*;
import java.util.*;
import utility.*;

class HandleSession extends Thread implements GameStatus
{
	private int port = 8000, sessionNo;
	private DataInputStream fromPlayer;
	private DataOutputStream toPlayer;
	
	public HandleSession(BlackJackGame game)
	{
		sessionNo = 1;
		try
		{
			//Create a server socket
			ServerSocket serverSocket = new ServerSocket(port);
			
			//Append a msg to the JTextArea
			game.append(new Date() + ": Server statred at socket " + port + "\n" );
			
			//Ready to create a session for every players
			while(true)
			{
				game.append(new Date() + ": Wait for players to join session " + sessionNo + "\n");
				
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
