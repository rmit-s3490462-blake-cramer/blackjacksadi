package utility;

public interface GameStatus 
{
	public static final int DEALER      = 7; // Indicate dealer's turn
	public static final int PLAYER1     = 1; // Indicate player 1 turn
	public static final int PLAYER2     = 2; // Indicate player 2 turn
	public static final int PLAYER3     = 3; // Indicate player 3 turn
	public static final int PLAYER4     = 4; // Indicate player 4 turn
	public static final int PLAYER5     = 5; // Indicate player 5 turn
	public static final int PLAYER6     = 6; // Indicate player 6 turn
	public static final int  BEENKICKED = -100; //Tell the player he's being kicked out from the room 
	
	public static final int DEALER_WON  = 17; // Indicate dealer won
	public static final int PLAYER1_WON = 11; // Indicate player 1 won
	public static final int PLAYER2_WON = 12; // Indicate player 2 won
	public static final int PLAYER3_WON = 13; // Indicate player 3 won
	public static final int PLAYER4_WON = 14; // Indicate player 4 won
	public static final int PLAYER5_WON = 15; // Indicate player 5 won
	public static final int PLAYER6_WON = 16; // Indicate player 6 won
	public static final int DRAW        = -1; // Indicate a draw
	public static final int CONTINUE    = 100; // Indicate to continue
	
	public static final int MAXPLAYERS = 6;
	public static final int MAXTRIALS = 5;
	
	public static final int SERVERSOCKET = 8000;
	
	public static final int DEAL  = 20;
	public static final int HIT   = 21;
	public static final int STAND = 22;
	public static final int SPLIT = 23;

}
