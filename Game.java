import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game
{

    final int MAX_GUESSES = 12; // Max number of guesses allowed per game
    private int width;          // The width of the code and guess
    private List<Colour> code;  // Holds the code the user tries to guess
    private UserInterface ui;   // The object to interact with the UI

    public Game(UserInterface ui, int width)
    {
        this.width = width;
        this.ui = ui;
    }

    public void play()
    {
        code = generateCode();
        List<Colour> guess = ui.getGuess();
        ui.displayCode(code);
        ui.displayGuess(guess);
    }

    private List<Colour> generateCode()
    {
        int numColours = Colour.values().length;
        List<Colour> colours = new ArrayList<>();

        Random rnd = new Random();

        // Populate array with random colours
        for(int i = 0; i < this.width; i++)
            colours.add(Colour.values()[rnd.nextInt(numColours)]);

        return colours;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

}
