package utility;

import java.util.*;

import server.*;
import client.*;
/*
 * BlackJackGame will assign each player with a special ID
 * from GameStatus (0-6)
 * by passing in the number of players, we can know how many
 * players to be in scoreboard
 */
public class ScoreBoard 
{
	private Vector <Score> scores;
	private int numberOfPlayers; 

	public ScoreBoard(int numberOfPlayers)
	{
		scores = new Vector <Score>();
		this.numberOfPlayers = numberOfPlayers;
		for (int i=1; i<=numberOfPlayers; i++)
		{
			scores.add(new Score(i));
		}
	}
	
	//Not useful in this case
	public Vector<Integer> getWinner()
	{
		int highestPoint = 0, temp;
		Score score;
		Vector <Integer>winner = new Vector<Integer>();
		Iterator<Score> ite = scores.iterator();
		if(!scores.isEmpty())
		{
			while (ite.hasNext())
			{
				score = ite.next();
				temp = score.getPoints();
				if (temp == highestPoint )
				{
					winner.add(score.getPlayer());
				}
				else if( temp > highestPoint)
				{
					highestPoint = temp;
					winner.clear();
					winner.add(score.getPlayer());
				}
			}	
			return winner;
		}
		else
			return null;	
	}
	
	//Set the winner of the game for that trial
	public void setWinner(int player)
	{
		this.scores.elementAt(player - 1).addPoint();
	}
	
	//get number of players in the scoreboard
	public int getNumberOfPlayers()
	{
		return numberOfPlayers;
	}

}
