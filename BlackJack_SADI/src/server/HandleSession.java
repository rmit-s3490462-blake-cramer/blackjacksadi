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
	private Vector <Player> players;
	private boolean isGameEnd = false;
	private int whoseTurn = 0;
	
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
		}game.append("players count: " + game.getPlayersCount() + "\n");
		game.addPlayer(game.getDealer());
		//game.append("i added dealer into " + game.getPlayers().size() + "\n");
		players = game.getPlayers();//reusing
		game.removeWaitingPlayers();//to change for connection
	}
	
	//start
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
				int turn = players.elementAt(i).getPlayerTurn();
				toPlayer[i].writeUTF("Game started, your turn is: " + turn);
				toPlayer[i].writeInt(turn);
			}
			
			//start a new game
			game.newGame();
			
			//distribute to each player 2 cards (dealer the last)
			for (int j=0; j<2; j++)
			{
				for (int i=0; i<game.getPlayersCount() +1 /*including the dealer */; i++)
				{
					game.deal();					
					game.nextPlayer();
				}
			}
			
			//List down the cards that each players has in server side
			for (int i=0; i<players.size() && i < GameStatus.MAXPLAYERS + 1; i++)
			{
				BlackJackHand hand = game.getPlayers().elementAt(i).getHand();
				if(i<MAXPLAYERS)
					game.append("Player " + (i+1) + " has " + hand.getCardsOnHand() + "\n");
				else
					game.append("Dealer has "+ hand.getCardsOnHand() + "\n");
				
				//tell the players the two card they are given 
				//This approach if implemented for GUI based
				for (int j=0; j<2; j++)
				{
					if(i<MAXPLAYERS)
					{
						//write the suit then value
						int suit = hand.getCard(j).getSuit();
						int value = hand.getCard(j).getValue();
						
						toPlayer[i].writeInt(suit);
						toPlayer[i].writeInt(value);
					}
				}
			}
		
			//tell the players whose turn now
			whoseTurn = game.getWhoseTurn();
			game.append("Now is Player " + game.getWhoseTurn() + " turn.\n Waiting player to perform action.\n");
			
			//Notify player to start
			//toPlayer[game.getWhoseTurn()-1].writeInt(game.getWhoseTurn());
			
			
			for (int i=0; i<players.size() && i < GameStatus.MAXPLAYERS; i++)
			{
				//send an integer to client indicating whose turn now
				toPlayer[i].writeInt(game.getWhoseTurn());
				
				if ((i+1) == game.getWhoseTurn())
				{
					toPlayer[i].writeUTF("Your turn now.\nYou may type 1 to HIT, or 2 to stand.\n");
					
				}
				else
					toPlayer[i].writeUTF("Now is Player " + game.getWhoseTurn() + " turn.\nWaiting player to perform action...\n");
			
			}
			boolean continueHit = true;
			String actionString = "", cards = "";
			
			while (continueHit)
			{
				int action = fromPlayer[game.getWhoseTurn() - 1].readInt();
				
				if (action == HIT)
				{
					if(game.getPlayers().elementAt(game.getWhoseTurn() - 1).getHand().getCardsTotal() < 5)
					{
						actionString ="HIT";
						game.hit();
						continueHit = true;
					}
					else
						action = STAND;
				}
				if (action == STAND)
				{
					actionString = "STAND";
					continueHit = false;
				}
				else if(action == HIT)
					actionString ="HIT";
				else
				{
					actionString = "INVALID";
					continueHit = true;
				}
				toPlayer[game.getWhoseTurn() - 1].writeBoolean(continueHit);
				cards = game.getPlayers().elementAt(game.getWhoseTurn() - 1).getHand().getCardsOnHand().toString();
				toPlayer[game.getWhoseTurn() - 1].writeUTF(cards);
				
				
				String msg = "Player action: " + actionString + "\n";
				game.append(msg);
				game.append("Player " + game.getWhoseTurn() + " has cards: "  + cards + "\n");
				
				for (int i=0; i<players.size() && i < GameStatus.MAXPLAYERS; i++)
				{
					toPlayer[i].writeUTF(actionString);
				}
				if(!continueHit)
				{
					game.stand();
				}
			}
			
			
			
			
			
			/*
			int action = fromPlayer[whoseTurn - 1].readInt();
			for (int i=0; i<players.size() && i < GameStatus.MAXPLAYERS; i++)
			{
				toPlayer[i].writeUTF("Player " + whoseTurn + "action: " + action);
			}
			
			boolean stillHit = true;
			
			while(stillHit)
			{
				action = fromPlayer[whoseTurn - 1].readInt();
				if (action == HIT)
				{
					game.append("Player Hits.\n");
					
				}
				else if (action == STAND)
				{
					game.append("Player Stands.\n");
					stillHit = false;
					game.stand();
				}
				
				toPlayer[whoseTurn - 1].writeBoolean(stillHit);
					
			}*/
			}
			/*while (!isGameEnd)
			{
				//tell the players whose turn now
				game.append("Now is Player " + game.getWhoseTurn() + " turn.\n Waiting player to perform action.\n");
				for (int i=0; i<players.size() && i < GameStatus.MAXPLAYERS; i++)
				{
					//send an integer to client indicating whose turn now
					toPlayer[i].writeInt(game.getWhoseTurn());
					
					if ((i+1) == game.getWhoseTurn())
					{
						toPlayer[i].writeUTF("Your turn now.\nYou may type 1 to HIT, or 2 to stand.\n");
						
					}
					else
						toPlayer[i].writeUTF("Now is Player " + game.getWhoseTurn() + " turn.\nWaiting player to perform action...\n");
				}
				
				
				
				
				
				
				
				
				boolean isContinueHit = true;
				
				//get client action
				while (isContinueHit)
				{
					//get client input action
					int action = fromPlayer[game.getWhoseTurn()-1].readInt();
					
					if (action == HIT)
					{
						if (!game.hit())
						{
							
							isContinueHit = false;
							game.append("Player cannot hit anymore.\n");
							//display out the cards
							game.append("Player " + (game.getWhoseTurn()) + " has " + game.getPlayers().elementAt(game.getWhoseTurn()-1).getHand().getCardsOnHand() + "\n");
							toPlayer[game.getWhoseTurn()-1].writeUTF("You now have cards: " + game.getPlayers().elementAt(game.getWhoseTurn()-1).getHand().getCardsOnHand() + "\n" );
							game.stand();
						}
						else
						{
							game.append("Player hits.\n");
							//display out the cards
							game.append("Player " + (game.getWhoseTurn()) + " has " + game.getPlayers().elementAt(game.getWhoseTurn()-1).getHand().getCardsOnHand() + "\n");
							toPlayer[game.getWhoseTurn()-1].writeUTF("You now have cards: " + game.getPlayers().elementAt(game.getWhoseTurn()-1).getHand().getCardsOnHand() + "\n" );
						}
							
					}
					
					else if(action == STAND)
					{
						//display out the cards
						game.append("Player " + (game.getWhoseTurn()) + " has " + game.getPlayers().elementAt(game.getWhoseTurn()-1).getHand().getCardsOnHand() + "\n");
						toPlayer[game.getWhoseTurn()-1].writeUTF("You now have cards: " + game.getPlayers().elementAt(game.getWhoseTurn()-1).getHand().getCardsOnHand() + "\n" );
						game.stand();
						game.append("Player stands.\n");
						isContinueHit = false;
					}

					//show whether to continue hit or not
					toPlayer[game.getWhoseTurn()-1].writeBoolean(isContinueHit);
				}
				for (int i=0; i<players.size() && i < GameStatus.MAXPLAYERS; i++)
				{
					//send an integer to client indicating whose turn now
					//toPlayer[i].writeBoolean(isGameEnd);
				}
			}
			//
				
			*/
		
		catch(IOException ex)
		{
			game.append(ex.toString());
		}
	}
	
	
}
