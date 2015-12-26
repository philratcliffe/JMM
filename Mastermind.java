/**
 * This is the top level class for the game
 *
 * @author Phil Ratcliffe
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mastermind
{
    public static void main(String args[])
    {
        TextUserInterface tui = new TextUserInterface();

        tui.displayWelcome();

        String player1 = "Player1";
        String player2 = "Player2";

        int width = tui.getNumPegs();
        List<Colour> code = CodeGenerator.generateCode(width);

        int gameType = tui.getGameType();
        switch (gameType)
        {
            case 1:
                code = tui.getGuessOrCode("Player1 set a code: ",
                        width);
                break;
            case 3:
                player1 = "Computer";
                player2 = "Computer";
                break;
        }
        Board board = new Board(width, code);
        Game game = new Game(tui, board, player2, gameType);
        game.play();

    }
    

}
