import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CodeGenerator
{
    List<Object> guessedCodes;
    List<Colour> lastGuess;
    List<Colour> coloursToUse;

    int width;                          // Width of the code to generate.

    public CodeGenerator(int width)
    {
        this.coloursToUse = new ArrayList<>();
        this.guessedCodes = new ArrayList<>();
        this.width = width;
    }

    public List<Colour> generateRandomCode()
    {
        int numColours = Colour.values().length;
        List<Colour> code = new ArrayList<>();

        Random rnd = new Random();

        // Populate with random colours
        for(int i = 0; i < this.width; i++)
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

        this.guessedCodes.add(code);
        this.lastGuess = code;
        return code;
    }


    public Colour getRandomColour()
    {
        int numColours = Colour.values().length;
        Random rnd = new Random();
        Colour colour = Colour.X; // Initialise to stop compiler warnings

        boolean foundValidColour = false;
        while (!foundValidColour)
        {
            colour = Colour.values()[rnd.nextInt(numColours)];
            if (colour != colour.X && colour != colour.Z)
                foundValidColour = true;
        }
        return colour;
    }


    public List<Colour> generateCode()
    {
        if (coloursToUse.size() == 0)
        {
            return generateRandomCode();
        } 

        List<Colour> localColoursToUse = new ArrayList<Colour>(coloursToUse);

        List<Colour> code = new ArrayList<>();

        Random rnd = new Random();

        // Populate with random colours
        while(localColoursToUse.size() != 0)
        {
            Colour colour = Colour.X; // Initialise to stop compiler warnings

            int index = rnd.nextInt(localColoursToUse.size());
            colour = localColoursToUse.remove(index);
            code.add(colour);
        }
        while (code.size() < width)
        {
            Colour c = getRandomColour();
            code.add(c);
        }
        return code;
    }

    public void processIndicator(List<IndicatorCode> indicator)
    {
        // If all indicators set, then we know the Colours
        if (indicator.size() == this.width)
        {
            this.coloursToUse = new ArrayList<Colour>();
            for (int i = 0; i < this.lastGuess.size(); i++)
            {
                this.coloursToUse.add(this.lastGuess.get(i));
            }
        }
    }


}

