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

        this.coloursToTry = new ArrayList<>();
        this.guessedCodes = new ArrayList<>();      // Codes tried in previous guesses
        this.definateColours = new ArrayList<>();
        this.lastGuess = new ArrayList<>();

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
        System.out.println("guessed code size: " + this.guessedCodes.size());
        System.out.println("definateColours: " + this.definateColours);
        System.out.println("eliminatedColours: " + this.eliminatedColours);


        if (this.guessedCodes.size() == 0)
        {
            List<Colour> code = getSingleColourCode(Colour.O);
            this.lastGuess = code;
            this.guessedCodes.add(this.lastGuess);
            return code;
        }

        if (this.guessedCodes.size() == 1)
        {
            List<Colour> code = getSingleColourCode(Colour.B);
            this.lastGuess = code;
            this.guessedCodes.add(this.lastGuess);
            return code;
        }

        if (this.guessedCodes.size() == 2)
        {
            List<Colour> code = getSingleColourCode(Colour.Y);
            this.lastGuess = code;
            this.guessedCodes.add(this.lastGuess);
            return code;
        }

        if (this.guessedCodes.size() == 3)
        {
            List<Colour> code = getSingleColourCode(Colour.G);
            this.lastGuess = code;
            this.guessedCodes.add(this.lastGuess);
            return code;
        }

        if (this.guessedCodes.size() == 4)
        {
            List<Colour> code = getSingleColourCode(Colour.P);
            this.lastGuess = code;
            this.guessedCodes.add(this.lastGuess);
            return code;
        }


        Random rnd = new Random();

        boolean foundNewCode = false;
        while(!foundNewCode)
        {

            assert(this.guessedCodes.size() < Constants.MAX_GUESSES);

            List<Colour> code = new ArrayList<>();
            List<Colour> localColoursToTry = new ArrayList<Colour>(coloursToTry);

            // First we need to add the colours we know are in the code
            for (int i = 0; i < definateColours.size(); i++)
            {
                code.add(definateColours.get(i));
            }

            // Now we add possibles from the last guess 
            while(localColoursToTry.size() != 0 && code.size() <  this.width)
            {
                Colour colour = Colour.X; // Initialise to stop compiler warnings

                int index = rnd.nextInt(localColoursToTry.size());
                colour = localColoursToTry.remove(index);
                if (!eliminatedColours.contains(colour) && !definateColours.contains(colour))
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
            assert(code.size() == width);

            shuffleArrayList(code);
            if (!guessedCodes.contains(code))
            {
                foundNewCode = true;
                this.lastGuess = code;
            }

        }
        this.guessedCodes.add(this.lastGuess);
        assert(guessedCodes.size() <= Constants.MAX_GUESSES);
        assert(this.lastGuess.size() == width);
        return this.lastGuess;
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

        // Cool, we know all the colours now
        if (indicator.size() == width)
        {
            this.definateColours = new ArrayList<Colour>();
            for (int i = 0; i < lastGuess.size(); i++)
                this.definateColours.add(lastGuess.get(i));
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

