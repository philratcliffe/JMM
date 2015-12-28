import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Indicators
{
    public static List<IndicatorCode> getIndicatorCode(List<Colour> code, List<Colour> guess)
    {

        // Should never be any null arguments 
        assert(code != null && guess != null);

        // If code and guess are not the same length something has gone wrong
        assert(code.size() == guess.size());

        // If code or guess are the wrong length something has gone wrong
        assert(code.size() <= Constants.MAX_CODE_LENGTH);
        assert(code.size() >= Constants.MIN_CODE_LENGTH);

        List<Colour> localCode = new ArrayList<Colour>(code);
        List<Colour> localGuess = new ArrayList<Colour>(guess);

        List<IndicatorCode> indicators = new ArrayList<>();

        // First deal with right colour, right position
        for (int i = 0; i < localGuess.size(); i++)
        {
            if (localGuess.get(i) == localCode.get(i))
            {
                indicators.add(IndicatorCode.b);

                // Mark any exact matches in guess and code as used
                // so that we don't count them again.
                localGuess.set(i, Colour.X);
                localCode.set(i, Colour.Z);
            }
        }

        // Now deal with right colour, wrong position
        while (localGuess.size() != 0)
        {
            Colour c = localGuess.get(0);
            if (localCode.contains(c))
                indicators.add(IndicatorCode.w);
                localCode.remove(c);
            localGuess.remove(c);

        }

        return indicators;
    }
}

