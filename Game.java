import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game
{

    private UserInterface ui;   // The object to interact with the UI
    private Board board;
    private String player2;
    private int gameType;

    public Game(UserInterface ui, Board board, String player2, int gameType)
    {
        this.ui = ui;
        this.board = board;
        this.player2 = player2;
        this.gameType = gameType;
    }

    public void play()
    {
        CodeGenerator cg = new CodeGenerator(this.board.getWidth());
        boolean gameFinished = false;
        while(!gameFinished  )
        {
            ui.displayBoard(this.board);
            List<Colour> guess;
            if (this.gameType == 3)
                guess = cg.generateCode();
            else
                guess = ui.getGuessOrCode("Guess: ", this.board.getWidth());

            List<IndicatorCode> indicator = Indicators.getIndicatorCode(
                    this.board.getCode(), guess);
            if (this.gameType == 3)
            {
                cg.processIndicator(indicator);
            }

            Row row = new Row(guess, indicator);
            board.addRow(row);
            if (board.getRowCount() >= Constants.MAX_GUESSES)
            {
                ui.displayBoard(board);
                ui.displayYouLose(this.player2);
                ui.displayCode(this.board.getCode());
                gameFinished = true;
            }
            if (guess.equals(this.board.getCode()))
            {
                ui.displayBoard(board);
                ui.displayYouWin(this.player2);
                ui.displayCode(this.board.getCode());
                gameFinished = true;
            }
        }
    }
}
