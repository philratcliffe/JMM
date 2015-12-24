/**
 * Handles all the wrinting to and reading from
 * the Console. 
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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class TextUserInterface implements UserInterface
{
    private Console console = System.console();

    public void displayWelcome()
    {
        for (int i = 0; i < 12; i++)
            System.out.println();
        displayBanner();
        for (int i = 0; i < 4; i++)
            System.out.println();
    }

    private void displayBanner()
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader("banner"));
            String line;
            while((line = in.readLine()) != null)
            {
                System.out.println(line);
            }
            in.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found: ");
        } catch (IOException e) 
        {
            System.out.println("Unable to read file: ");
        }

    }

    public void displayCode(List<Colour> code)
    {
        String codeStr = code.toString();
        System.out.println("Code: " + codeStr);
    }


    public void displayYouLose()
    {
        System.out.println("You my friend are a Loser!");
    }

    public void displayYouWin()
    {
        System.out.println("You Win!");
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
        StringBuilder output = new StringBuilder("....");
        for(int i = 0; i < indStr.length(); i++)
            output.setCharAt(i, indStr.charAt(i));
        System.out.println("Indicator: " + output.toString());
    }

    public void displayBoard(Board board)
    {
        displayWelcome();
        List<Row> rows = board.getRows();
        for (Row row : rows)
        {
            List<Colour> guess = row.getGuess();
            for(Colour c : guess)
                System.out.print(c);
            System.out.print("  ");

            List<IndicatorCode> indicator = row.getIndicator();
            for(IndicatorCode c : indicator)
            {
                System.out.print(c);
                System.out.print(' ');
            }
            int diff_in_length = guess.size() - indicator.size();
            for (int i = 0; i < diff_in_length; i++)
            {
                System.out.print(". ");
            }
            System.out.println();
        }


        displayUnusedRows(board);

    }

    private void displayUnusedRows(Board board)
    {
        int unusedCount = Constants.MAX_GUESSES - board.getRowCount();
        for(int i = 0; i < unusedCount; i++)
        {
            for (int j = 0; j < board.getWidth(); j++)
            {
                System.out.print('.');
            }
            System.out.print("  ");
            for (int j = 0; j < board.getWidth(); j++)
            {
                System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println("\nYou have " + unusedCount + " guesses remaining.");
    }


    public int getNumPegs()
    {
        int codeLength = 4;

        boolean gotValidInput = false;
        while(!gotValidInput)
        {
            codeLength = 
                    Integer.parseInt(console.readLine("code length(3-8): "));
            if (codeLength >= Constants.MIN_CODE_LENGTH  
                    && codeLength <= Constants.MAX_CODE_LENGTH)
                gotValidInput = true;
            else
                System.out.println("Invalid length. Should be in 3-8");
        }

        return codeLength;
    }

    public List<Colour> getGuess(int letterCount)
    {
        String regex_for_letters = String.format("[RBYGPO]{%d}", letterCount);
        String guess = "";


        boolean gotValidGuess = false;
        while(!gotValidGuess)
        {
            guess = console.readLine("guess: ");
            if (guess.matches(regex_for_letters))
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
