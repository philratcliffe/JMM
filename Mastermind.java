/**
 * This is the top level class for the game
 *
 * @author Phil Ratcliffe
 * @version 1.0
 */
public class Mastermind
{
    public static void main(String args[])
    {
        TextUserInterface tui = new TextUserInterface();
        tui.displayWelcome();
        int width = tui.getNumPegs();
        Board board = new Board(width);
        Game game = new Game(tui, board);
        game.play();

    }

}
