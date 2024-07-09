/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: RPSInterface.java file within PA1 file
                JAVDOC documentation: https://www.oracle.com/in/technical-resources/articles/java/javadoc-tool.html#:~:text=A%20doc%20comment%20is%20written,%40return%20%2C%20and%20%40see%20.
   
  This file is for CSE 12 PA1 in Winter 2023,
  and contains abstract definitions of RPS of some basic functionalities.
*/

import java.util.Scanner;

public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED =
            "Game not yet implemented.";
    /**
     * Construct a new instance of RPS with the given possible moves.
     *
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            System.arraycopy(args, 0, moves, 0, args.length);
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);
        // While user does not input "q", play game
        System.out.println(PROMPT_MOVE);
        String uMove = in.nextLine();
        while (!uMove.equals(QUIT)) {
            if(game.isValidMove(uMove)) {
                game.playRPS(uMove, game.genCPUMove());
                System.out.println(PROMPT_MOVE);
                uMove = in.nextLine();
            }
            else {
                System.out.println(INVALID_INPUT);
                System.out.println(PROMPT_MOVE);
                uMove = in.nextLine();
            }
        }
        game.displayStats();
        // TODO: Insert the code to play the game.
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written
        // to do most of the work! And don't forget Javadoc.

        in.close();
    }

    /**
     * Determines if player or cpu wins based on possible moves
     *
     * @param playerMove user input for game
     * @param cpuMove computer randomly-generated choice from game choices
     * @return -1, 0, 1, 2 depending on winner respectively: invalid, tie, user, cpu
     */
    protected static final int IMPOSSIBLE_MOVE = -1;

    @Override
    public int determineWinner(String playerMove, String cpuMove) {
        // TODO
        int pM = INVALID_INPUT_OUTCOME;
        int cM = INVALID_INPUT_OUTCOME;
        for (int i  = 0; i < possibleMoves.length; i++) {
            if (playerMove.equals(possibleMoves[i])){ pM = i; }
            if (cpuMove.equals(possibleMoves[i])){ cM = i; }
        }
        if (pM == INVALID_INPUT_OUTCOME || cM == INVALID_INPUT_OUTCOME) { return INVALID_INPUT_OUTCOME; }
        else if (((pM == possibleMoves.length - 1) && (cM == 0)) || (pM + 1 == cM)) { return PLAYER_WIN_OUTCOME; }
        else if (((cM == possibleMoves.length - 1) && (pM == 0)) || (cM + 1 == pM)) { return CPU_WIN_OUTCOME; }
        else { return TIE_OUTCOME; }
        // replace this when you implement the method
    }
}
