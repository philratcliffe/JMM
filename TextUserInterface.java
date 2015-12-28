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
            System.out.println("Welcome to Mastermind.\n");
            System.out.println("When choosing a colour, only enter the first " +
                    "letter of the colour. For example, R for Red, \n" + 
                    "B for Blue, O for Orange etc.\n");
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


    public void displayYouLose(String name)
    {
        System.out.println("You, " + name + " are a loser!");
    }

    public void displayYouWin(String name)
    {
        System.out.println("Well done " + name + " YOU WIN!");
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
        displayBanner();
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
        System.out.println("\nYou have " + unusedCount + " guesses left.");
    }

    public int getGameType()
    {
        System.out.println("\nSelect the type of game you wish to play.");
        System.out.println();
        System.out.println("\t1. Human vs Human");
        System.out.println("\t2. Human vs Computer");
        System.out.println("\t3. Computer vs Computer");
        System.out.println();

        int gameType = -1;
        boolean validInput = false;
        while (!validInput)
        {
            gameType = Integer.parseInt(console.readLine("Choice: "));
            if (gameType < 1 || gameType > 3)
                System.out.println("That's not an option I recognise.");
            else
                validInput = true;
        }
        return gameType;
    }

    public int getNumPegs()
    {
        int codeLength = 4;

        boolean gotValidInput = false;
        while(!gotValidInput)
        {
            try
            {
                codeLength = 
                        Integer.parseInt(console.readLine("What length of code would" +
                                    " you like to play with(3-8): "));
                if (codeLength >= Constants.MIN_CODE_LENGTH  
                        && codeLength <= Constants.MAX_CODE_LENGTH)
                    gotValidInput = true;
                else
                    System.out.println("I don't understand that. Expecting a number between 3 and 8");
            }
            catch (NumberFormatException e)
            {
                System.out.println("I don't understand this.");
            }
        }

        return codeLength;
    }


    public List<Colour> getGuessOrCode(String prompt, int letterCount)
    {
        String regex_for_letters = String.format("[RBYGPO]{%d}", letterCount);
        String guess = "";


        boolean gotValidInput = false;
        while(!gotValidInput)
        {
            guess = console.readLine(prompt);
            if (guess.matches(regex_for_letters))
                gotValidInput = true;
            else
                System.out.println("That's not something I recognise.");
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
