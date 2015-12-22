/**
 * The text user interface 
 *
 * @author Phil Ratcliffe
 * @version 1.0
 */

import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class TextUserInterface implements UserInterface 
{

    public void displayWelcome()
    {
        System.out.println();
        System.out.println();
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
        String guessStr = guess.toString();
        System.out.println("Guess: " + guessStr);
    }

    public List<Colour> getGuess()
    {
        System.out.println("guess");
        int numColours = Colour.values().length;
        List<Colour> colours = new ArrayList<>();

        Random rnd = new Random();

        // Populate array with random colours 
        for(int i = 0; i < 4; i++)
            colours.add(Colour.values()[rnd.nextInt(numColours)]);

        return colours;

    }


}
