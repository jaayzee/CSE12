/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: RPSInterface.java file within PA1 file
                JAVDOC documentation: https://www.oracle.com/in/technical-resources/articles/java/javadoc-tool.html#:~:text=A%20doc%20comment%20is%20written,%40return%20%2C%20and%20%40see%20.
   
  This file is for CSE 12 PA1 in Winter 2023,
  and contains abstract definitions of RPS of some basic functionalities.
*/

import java.util.Random;

/**
 * An abstract that determines the functionality of RPS (Rock Paper Scissors)
 */
public abstract class RPSAbstract implements RPSInterface {
    protected static final int SEED = 12;
    protected final Random rand = new Random(SEED);

    // The moves allowed in this version of RPS
    protected String[] possibleMoves;

    // The number of games, player wins, CPU wins and ties
    protected int numGames;
    protected int numPlayerWins;
    protected int numCPUWins;
    protected int numTies;

    // The complete history of the moves
    protected String[] playerMoves;
    protected String[] cpuMoves;

    // The default moves.  Use for the basic implementation of the game.
    protected static final String[] DEFAULT_MOVES = {"scissors", "paper",
            "rock"};

    /* Important: Use the following constants to avoid output matching issues
       and magic numbers! */

    // Messages for the game.
    protected static final String INVALID_INPUT =
            "That is not a valid move. Please try again.";
    protected static final String PLAYER_WIN = "You win.";
    protected static final String CPU_WIN = "I win.";
    protected static final String TIE = "It's a tie.";
    protected static final String CPU_MOVE = "I chose %s. ";
    protected static final String THANKS =
            "Thanks for playing!\nOur most recent games were:";
    protected static final String PROMPT_MOVE =
            "Let's play! What's your move? (Type the move or q to quit)";

    protected static final String OVERALL_STATS =
            "Our overall stats are:\n" +
                    "I won: %.2f%%\nYou won: %.2f%%\nWe tied: %.2f%%\n";
    protected static final String CPU_PLAYER_MOVES = "Me: %s, You: %s\n";


    // Game outcome constants.
    protected static final int CPU_WIN_OUTCOME = 2;
    protected static final int PLAYER_WIN_OUTCOME = 1;
    protected static final int TIE_OUTCOME = 0;
    protected static final int INVALID_INPUT_OUTCOME = -1;

    // Other game control constants.
    protected static final int MAX_GAMES = 100;
    protected static final int MIN_POSSIBLE_MOVES = 3;
    protected static final int NUM_ROUNDS_TO_DISPLAY = 10;
    protected static final int PERCENTAGE_RESIZE = 100;
    protected static final String QUIT = "q";

    /**
     * Checks if input is usable in the game of RPS (Rock Paper Scissors)
     * @return whether the input is useable in the game
     */
    @Override
    public boolean isValidMove(String move) {
        // TODO
        // Use a loop here
        for (String i : possibleMoves) {
                if (move == null) {return false; }
                else if (move.equals(i)) { return true; }
        }
        return false;
    }

    /**
     * Runs through a single game of RPS (Rock Paper Scissors)
     * @param playerMove user input for game
     * @param cpuMove computer randomly-generated choice from game choices
     * @see user prompted for input into game
     */
    @Override
    public void playRPS(String playerMove, String cpuMove) {
        // TODO

        // Use determineWinner to determine who won
        int result = determineWinner(playerMove, cpuMove);
        //increments number of games
        numGames++;
        //compares determineWinner result to each situation, as well as incrementing 
        //respective results
        if (result == INVALID_INPUT_OUTCOME) {
                System.out.println(INVALID_INPUT);
        }
        else if (result == TIE_OUTCOME) {
                numTies++;
                System.out.printf(CPU_MOVE, cpuMove);
                System.out.println(TIE);
        }
        else if (result == PLAYER_WIN_OUTCOME) {
                numPlayerWins++;
                System.out.printf(CPU_MOVE, cpuMove);
                System.out.println(PLAYER_WIN);
        }
        else if (result == CPU_WIN_OUTCOME){
                numCPUWins++;
                System.out.printf(CPU_MOVE, cpuMove);
                System.out.println(CPU_WIN);
        }
        // Record the moves made
        // Loop over previous array, initialize separate array
        String[] temp1 = new String[numGames];
        for (int i = 0; i < temp1.length - 1; i++) { temp1[i] = playerMoves[i]; }
        temp1[temp1.length - 1] = playerMove;
        playerMoves = temp1;

        String[] temp2 = new String[numGames];
        for (int i = 0; i < temp2.length - 1; i++) { temp2[i] = cpuMoves[i]; }
        temp2[temp2.length - 1] = cpuMove;
        cpuMoves = temp2;
        // Add one to the appropriate statistics
                //ADDED IT AS AN ADDITIONAL TO THE DETERMINE WINNER SECTION ^^
        // Add appropriate Javadoc method header
    }


    // The following methods have been already implemented. Do not change them.

    /**
     * Generates next cpu move
     *
     * @return representing the move, depending on random int
     */
    @Override
    public String genCPUMove() {
        // Generate new random number
        int num = rand.nextInt(possibleMoves.length);
        // Get move based on random number
        return possibleMoves[num];
    }

    /**
     * Print out the end game stats: moves played and win percentages
     */
    @Override
    public void displayStats() {
        float cpuWinPercent = (float) numCPUWins /
                (float) numGames * PERCENTAGE_RESIZE;
        float playerWinPercent = (float) numPlayerWins /
                (float) numGames * PERCENTAGE_RESIZE;
        float tiedPercent = (float) numTies /
                (float) numGames * PERCENTAGE_RESIZE;

        System.out.println(THANKS);

        // Get which index to print to
        int printTo = (numGames < NUM_ROUNDS_TO_DISPLAY)
                ? 0 : numGames - NUM_ROUNDS_TO_DISPLAY;

        // Print system and user moves
        for (int i = numGames - 1; i >= printTo; i--) {
            System.out.printf(CPU_PLAYER_MOVES, this.cpuMoves[i],
                    this.playerMoves[i]);
        }

        System.out.printf(OVERALL_STATS, cpuWinPercent, playerWinPercent,
                tiedPercent);
    }
}
