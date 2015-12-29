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
        int width = tui.getNumPegs();

        CodeGenerator cg = new CodeGenerator(width);


        String player1 = "Player1";
        String player2 = "Player2";

        List<Colour> code = cg.generateRandomCode();

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
        if (gameType < 3)
        {
            tui.displayInfo();
        }
        Board board = new Board(width, code);
        Game game = new Game(tui, board, player2, gameType);
        game.play();

    }

}
