package utility;

public interface GameStatus 
{
	public static int DEALER      = 0; // Indicate dealer's turn
	public static int PLAYER1     = 1; // Indicate player 1 turn
	public static int PLAYER2     = 2; // Indicate player 2 turn
	public static int PLAYER3     = 3; // Indicate player 3 turn
	public static int PLAYER4     = 4; // Indicate player 4 turn
	public static int PLAYER5     = 5; // Indicate player 5 turn
	public static int PLAYER6     = 6; // Indicate player 6 turn
	public static int DEALER_WON  = 10; // Indicate dealer won
	public static int PLAYER1_WON = 11; // Indicate player 1 won
	public static int PLAYER2_WON = 21; // Indicate player 2 won
	public static int PLAYER3_WON = 31; // Indicate player 3 won
	public static int PLAYER4_WON = 41; // Indicate player 4 won
	public static int PLAYER5_WON = 51; // Indicate player 5 won
	public static int PLAYER6_WON = 61; // Indicate player 6 won
	public static int DRAW        = -1; // Indicate a draw
	public static int CONTINUE    = 100; // Indicate to continue
	
	public static final int MAXPLAYERS = 6;
	public static final int MAXTRIALS = 5;
	
	public static final int SERVERSOCKET = 8000;

}
