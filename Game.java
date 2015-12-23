import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game
{

    private int width;          // The width of the code and guess
    private List<Colour> code;  // Holds the code the user tries to guess
    private UserInterface ui;   // The object to interact with the UI
    private Board board;

    public Game(UserInterface ui, int width)
    {
        this.width = width;
        this.ui = ui;
        board = new Board();
    }

    public void play()
    {
        code = generateCode();

        boolean gameFinished  = false;
        while(!gameFinished  )
        {
            ui.displayBoard(board);
            List<Colour> guess = ui.getGuess();
            List<IndicatorCode> indicator = Indicators.getIndicatorCode(code, guess);
            Row row = new Row(guess, indicator);
            board.addRow(row);
            if (board.getRowCount() >= Constants.MAX_GUESSES)
                gameFinished = true;
        }

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
