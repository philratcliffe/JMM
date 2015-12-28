import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

class CodeGenerator
{
    List<Object> guessedCodes;
    List<Colour> lastGuess;
    List<Colour> coloursToTry;
    List<Colour> definateColours;
    List<Colour> eliminatedColours;
    int width;                          // Width of the code to generate.

    public CodeGenerator(int width)
    {
        assert(width >= Constants.MIN_CODE_LENGTH);
        assert(width <= Constants.MAX_CODE_LENGTH);
        this.width = width;

        this.coloursToTry = getSingleColourCode(Colour.O); 
        this.guessedCodes = new ArrayList<>();      // Codes tried in previous guesses
        this.definateColours = new ArrayList<>();   // Codes tried in previous guesses

        // Maintain a list of the colours we know not to use.
        this.eliminatedColours = new ArrayList<>();  

        // We never want to generate a code with these 
        // values as they are not valid and only used
        // by the Indicator algorithm
        eliminatedColours.add(Colour.X);
        eliminatedColours.add(Colour.Z);
    }

    private boolean isSingleColourCode(List<Colour> code)
    {
        assert(code.size() == this.width);

        for (int i = 0; i < code.size() - 1; i++)
        {
            if (code.get(i) != code.get(i+1))
                return false;
        }
        return true;
    }

    private List<Colour> getSingleColourCode(Colour c)
    {
        List<Colour> code = new ArrayList<>();
        for (int i = 0; i < width; i++)
        {
            code.add(c);
        }
        return code;
    }

    public List<Colour> generateRandomCode()
    {
        List<Colour> code = new ArrayList<>();

        for(int i = 0; i < this.width; i++)
        {
            code.add(getRandomColour());
        }

        this.guessedCodes.add(code);
        assert(guessedCodes.size() <= Constants.MAX_GUESSES);

        this.lastGuess = code;
        return code;
    }


    private Colour getRandomColour()
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

    private void shuffleArrayList(List<Colour> list)
    {
        long seed = System.nanoTime();
        Collections.shuffle(list, new Random(seed));
    }

    public List<Colour> generateCode()
    {
        System.out.println("coloursToTry: " + this.coloursToTry);
        System.out.println("eliminatedColours: " + this.eliminatedColours);

        List<Colour> localColoursToTry = new ArrayList<Colour>(coloursToTry);
        List<Colour> code = new ArrayList<>();

        Random rnd = new Random();
        for (int i = 0; i < definateColours.size(); i++)
        {
            code.add(definateColours.get(i));
        }

        while(localColoursToTry.size() != 0 && code.size() <  this.width)
        {
            Colour colour = Colour.X; // Initialise to stop compiler warnings

            int index = rnd.nextInt(localColoursToTry.size());
            colour = localColoursToTry.remove(index);
            if (!eliminatedColours.contains(colour))
            {
                assert(code.size() < this.width);
                code.add(colour);
            }

        }
        while (code.size() < width)
        {
            Colour c = getRandomColour();
            code.add(c);
        }

        System.out.println("before shuff: " + code);
        shuffleArrayList(code);
        System.out.println("after shuff: " + code);
        this.lastGuess = code;

        this.guessedCodes.add(code);
        assert(guessedCodes.size() <= Constants.MAX_GUESSES);

        return code;
    }

    public void processIndicator(List<IndicatorCode> indicator)
    {
        assert(definateColours.size() <= this.width);
        assert(indicator.size() <= this.width);
        assert(this.lastGuess.size() == this.width);

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
            return;
        }

        if (isSingleColourCode(this.lastGuess))
        {
            if (!this.definateColours.contains(lastGuess.get(0)))
                this.definateColours.add(lastGuess.get(0));

            System.out.println("lastGuess: " + lastGuess);
            System.out.println("def: " + definateColours);
        }

        List<Colour> localLastGuess = new ArrayList<Colour>(lastGuess);
        this.coloursToTry = new ArrayList<Colour>();

        Random rnd = new Random();

        for (int i = 0; i < indicator.size(); i++)
        {
            int index = rnd.nextInt(localLastGuess.size());
            this.coloursToTry.add(localLastGuess.remove(index));
        }
    }
}

