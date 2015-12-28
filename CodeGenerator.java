import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CodeGenerator
{
    List<Object> guessedCodes;
    List<Colour> lastGuess;
    List<Colour> coloursToUse;
    List<Colour> eliminatedColours;

    int width;                          // Width of the code to generate.

    public CodeGenerator(int width)
    {
        this.coloursToUse = new ArrayList<>();
        this.guessedCodes = new ArrayList<>();
        this.width = width;

        // Maintain a list of the colours we know not to use.
        this.eliminatedColours = new ArrayList<>();  

        // We never want to generate a code with these 
        // values as they are not valid and only used
        // by the Indicator algorithm
        eliminatedColours.add(Colour.X);
        eliminatedColours.add(Colour.Z);
    }

    public List<Colour> generateRandomCode()
    {
        List<Colour> code = new ArrayList<>();

        for(int i = 0; i < this.width; i++)
        {
            code.add(getRandomColour());
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
            if (!eliminatedColours.contains(colour))
                foundValidColour = true;
        }
        return colour;
    }


    public List<Colour> generateCode()
    {
        System.out.println("coloursToUse: " + this.coloursToUse);
        System.out.println("eliminatedColours: " + this.eliminatedColours);
        List<Colour> localColoursToUse = new ArrayList<Colour>(coloursToUse);

        List<Colour> code = new ArrayList<>();

        Random rnd = new Random();

        // Populate with random colours
        while(localColoursToUse.size() != 0)
        {
            Colour colour = Colour.X; // Initialise to stop compiler warnings

            int index = rnd.nextInt(localColoursToUse.size());
            colour = localColoursToUse.remove(index);
            if (!eliminatedColours.contains(colour))
                code.add(colour);
        }
        while (code.size() < width)
        {
            Colour c = getRandomColour();
            code.add(c);
        }
        this.lastGuess = code;
        return code;
    }

    public void processIndicator(List<IndicatorCode> indicator)
    {
        assert(this.lastGuess.size() != 0);
        if (indicator.size() == 0)
        {
            // If no colours were correct in the previous guess,
            // we can eliminate all the last guess colours from
            // any future guesses.
            for (Colour c : this.lastGuess)
            {
                if (!eliminatedColours.contains(c))
                    this.eliminatedColours.add(c);
            }
        }

        List<Colour> localLastGuess = new ArrayList<Colour>(lastGuess);
        this.coloursToUse = new ArrayList<Colour>();

        Random rnd = new Random();

        for (int i = 0; i < indicator.size(); i++)
        {
            int index = rnd.nextInt(localLastGuess.size());
            this.coloursToUse.add(localLastGuess.remove(index));
        }
    }
}

