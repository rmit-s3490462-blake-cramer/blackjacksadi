package server;

import java.io.*;
import java.net.*;
import java.util.*;

import utility.*;

public class HandleConnection extends Thread implements GameStatus
{
	public HandleConnection (BlackJackGame game)
	{
		try
		{
			//Create a server socket
			ServerSocket serverSocket = new ServerSocket(GameStatus.SERVERSOCKET);
			
			//Append a msg to the JTextArea
			game.append(new Date() + ": Server waiting for players to connect...\n" );
			
			//accept the players that are trying to connect to the server
			//and add them to the waitingPool in game
			while(true)
			{
				Socket playerSocket = serverSocket.accept();
				game.append("Accepted a client...\n" );
				
				PrintWriter out = new PrintWriter (playerSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader (new InputStreamReader (playerSocket.getInputStream()));
				String inputLine, outputLine;
				
				outputLine = "You are connected to server. Please input your username.";
				game.append("Client connected. Awaiting username...\n");
				out.println(outputLine);
				
				
				if ((inputLine = in.readLine())!= null)
				{
					String username = inputLine;//get the username as 
					
					game.append("Client has input username: "  + username + "\n");
					outputLine = "You have entered username: " + username;					
					out.println(outputLine);//send the msg to client side
					
					Player player = new Player(username,playerSocket);
					if (game.addWaitingPlayers(player))
					{	
						//game.append(game.getPlayers().elementAt(0).toString());
						outputLine="You are added into waiting list.\n Please wait for the other players";
					}
					else
					{	
						outputLine="You are not added into the game. Please try again.";
					}
					out.println(outputLine);
				}
				else
					break;
				
				
				/*out.close();
				in.close();
				playerSocket.close();
				serverSocket.close();*/
				
				/*String username = playerSocket.getInputStream().toString();
				Player player = new Player(username, playerSocket);
				game.addWaitingPlayers(player);*/
				
			}
		}
		catch(IOException ex)
		{
			System.err.println(ex);
			game.append(ex.toString());
		}
	}
	
	public void run()
	{
		
		
	}
	
	
}
