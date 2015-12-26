import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CodeGenerator
{

    public static List<Colour> generateCode(int width)
    {
        int numColours = Colour.values().length;
        List<Colour> code = new ArrayList<>();

        Random rnd = new Random();

        // Populate with random colours
        for(int i = 0; i < width; i++)
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
        return code;
    }
}

