import java.util.Random;

public class Game
{

    private int width;      // The width of the code and guess
    private Colour code[];  // Holds the code the user tries to guess

    public Game(int width)
    {
        this.width = width;

    }

    Colour[] generateCode()
    {
        int numColours = Colour.values().length;
        Colour[] colours = new Colour[this.width];

        Random rnd = new Random();

        // Iterate through 'colours' and populate array with randomised values
        for(int i = 0; i < this.width; i++)
            colours[i] = Colour.values()[rnd.nextInt(numColours)];

        return colours;


    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

}
