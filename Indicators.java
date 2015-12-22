import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Indicators
{
    public List<IndicatorCode> getIndicatorCode(List<Colour> code, List<Colour> guess)
    {

        // Should never be any null arguments 
        assert(code != null && guess != null);

        // If code and guess are not the same length something has gone wrong
        assert(code.size() == guess.size());

        // If code or guess are the wrong length something has gone wrong
        assert(code.size() <= Constants.MAX_CODE_LENGTH);
        assert(code.size() <= Constants.MIN_CODE_LENGTH);

        List<IndicatorCode> indicators = new ArrayList<>();
        return indicators;

        /*
        for (Colour c : code)
        {

        }

            if (Arrays.asList(code).contains(guess[i]))
            {

                indicators

            }
        */


    }
}

