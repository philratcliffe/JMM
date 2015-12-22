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
        Game game = new Game(tui, 4);
        game.play();

    }

}
