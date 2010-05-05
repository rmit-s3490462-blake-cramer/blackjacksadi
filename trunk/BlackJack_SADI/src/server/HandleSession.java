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
	private static int  sessionNo = 1;
	private Socket playerSocket[]  = null;
	private DataInputStream fromPlayer[]  = null;
	private DataOutputStream toPlayer[]  = null;
	private BlackJackGame game = null;
	Vector <Player> players;
	
	public HandleSession(BlackJackGame game)
	{
		players = game.getWaitingPlayers();
		playerSocket = new Socket [MAXPLAYERS];
		fromPlayer = new DataInputStream[MAXPLAYERS];
		toPlayer = new DataOutputStream [MAXPLAYERS];
		this.game = game;
		for (int i=0; i<players.size() && i < GameStatus.MAXPLAYERS; i++)
		{
			if(game.addPlayer(players.elementAt(i)))
			{
				game.addPlayersCount();
				game.minusWaitingPlayersCount();
				game.append("i added player into player " + i + "\n");
			}
		}game.append("players count: " + players.size() + game.getPlayersCount());
		game.addPlayer(game.getDealer());
		game.append("i added dealer into " + game.getPlayers().size() + "\n");
		players = game.getPlayers();//reusing
		game.removeWaitingPlayers();
	}
	
	public void run()
	{
		
		try
		{
			//initilise all the sockets and data I/O streams
			for (int i=0; i<game.getPlayersCount(); i++)
			{
				playerSocket[i] = players.elementAt(i).getSocket();
				fromPlayer[i] = new DataInputStream(playerSocket[i].getInputStream());
				toPlayer[i] = new DataOutputStream(playerSocket[i].getOutputStream());
			}
			
			//write to players and notify their turn
			for (int i=0; i<game.getPlayersCount(); i++)
			{
				players.elementAt(i).setPlayerTurn(i+1);
				toPlayer[i].writeUTF("Game started, your turn is: " + players.elementAt(i).getPlayerTurn());
				//send and integer indicating the player's turn
				//toPlayer[i].writeInt(i+1);
			}
			
			//start a new game
			game.newGame();
			//distribute to each player 2 cards (dealer the last)
			for (int j=0; j<2; j++)
			{
				for (int i=0; i<game.getPlayersCount() +1; i++)
				{
					game.deal();
					game.nextPlayer();
				}
			}
			
			for (int i=0; i<players.size() && i < GameStatus.MAXPLAYERS + 1; i++)
			{
				game.append("Player " + i + "has " + game.getPlayers().elementAt(i).getHand().getCardsOnHand() + "\n");
			}
			
			
			
			
			//
				
			
		}
		catch(IOException ex)
		{
			game.append(ex.toString());
		}
	}
	
	
}
