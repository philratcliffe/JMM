import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game
{

    private List<Colour> code;  // Holds the code the user tries to guess
    private UserInterface ui;   // The object to interact with the UI
    private Board board;

    public Game(UserInterface ui, Board board)
    {
        this.ui = ui;
        this.board = board;
    }

    public void play()
    {
        code = generateCode();

        boolean gameFinished  = false;
        while(!gameFinished  )
        {
            ui.displayBoard(this.board);
            List<Colour> guess = ui.getGuess(this.board.getWidth());
            List<IndicatorCode> indicator = Indicators.getIndicatorCode(this.code, guess);
            Row row = new Row(guess, indicator);
            board.addRow(row);
            if (board.getRowCount() >= Constants.MAX_GUESSES)
            {
                ui.displayBoard(board);
                ui.displayYouLose();
                ui.displayCode(code);
                gameFinished = true;
            }
            if (guess.equals(code))
            {
                ui.displayBoard(board);
                ui.displayYouWin();
                ui.displayCode(code);
                gameFinished = true;
            }
        }

    }


    private List<Colour> generateCode()
    {
        int numColours = Colour.values().length;
        List<Colour> code = new ArrayList<>();

        Random rnd = new Random();

        // Populate array with random colours
        for(int i = 0; i < this.board.getWidth(); i++)
        {
            Colour colour = Colour.X; // Initialise to stop compiler warnings

            boolean foundValidColour = false;
            while (!foundValidColour)
            {
                colour = Colour.values()[rnd.nextInt(numColours)];
                if (colour != Colour.X && colour != Colour.Z)
                    foundValidColour = true;
            }
            code.add(colour);

        }
        return code;
    }
}
