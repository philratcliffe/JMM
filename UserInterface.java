/**
 * Simple interface for a user interface
 * Allows different user interfaces to be used Polymorphically
 * Hoping to write a GUIUserInterface that implements this in 
 * addition to the TextUserInterface.
 * @author Phil 
 */
import java.util.List;

public interface UserInterface {
    List<Colour> getGuess();
    void displayCode(List<Colour> code);
    void displayGuess(List<Colour> code);
    void displayIndicatorCode(List<IndicatorCode> indicator);
    void displayBoard(Board board);
}
