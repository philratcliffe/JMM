/**
 * The text user interface 
 *
 * @author Phil Ratcliffe
 * @version 1.0
 */

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TextUserInterface implements UserInterface
{
    private Console console = System.console();
    private static final String REGEX_FOR_LETTERS = "[RBYGPO]{4}";

    public void displayWelcome()
    {
        System.out.println();
        System.out.println("--------------------------------");
        System.out.println("           Mastermind           ");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println();
    }

    public void displayCode(List<Colour> code)
    {
        String codeStr = code.toString();
        System.out.println("Code: " + codeStr);
    }

    public void displayGuess(List<Colour> guess)
    {
        for(Colour c : guess)
            System.out.println("c: " + c); 
        String guessStr = guess.toString(); 
        System.out.println("Guess: " + guessStr); 
    }

    public void displayIndicatorCode(List<IndicatorCode> indicator)
    {
        String indStr = indicator.toString();
        System.out.println("Indicator: " + indStr);
    }

    public void displayBoard(Board board)
    {
        System.out.println("\n-------------------");
        List<Row> rows = board.getRows();
        for (Row row : rows)
        {
            
            List<Colour> guess = row.getGuess();
            for(Colour c : guess)
                System.out.print(c);
            System.out.print(" ");
            List<IndicatorCode> indicator = row.getIndicator();
            for(IndicatorCode ic : indicator)
                System.out.print(ic);
            System.out.println();
        }


        displayUnusedRows(rows);

    }

    private void displayUnusedRows(List<Row> rows)
    {
        int unusedCount = Constants.MAX_GUESSES - rows.size();
        for(int i = 0; i < unusedCount; i++)
        {
            System.out.println(".... ....");
        }
        System.out.println("\nYou have " + unusedCount + " guesses remaining.");
    }

    public List<Colour> getGuess()
    {
        String guess = "";


        boolean gotValidGuess = false;
        while(!gotValidGuess)
        {
            guess = console.readLine("guess: ");
            if (guess.matches(REGEX_FOR_LETTERS))
                gotValidGuess = true;
            else
                System.out.println("Invalid guess");
        }

        int numColours = Colour.values().length;
        List<Colour> colours = new ArrayList<>();
        for(char c : guess.toCharArray()) 
        {
            colours.add(Colour.valueOf(String.valueOf(c)));
        } 

        return colours;

    }


}
