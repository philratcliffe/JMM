/**
 * java org.junit.runner.JUnitCore Tests
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Tests {

  @Test
  public void generateCodeTest1() {

    // MyClass is tested
    int width_of_code = 4;
    Game game = new Game(width_of_code);

    // assert statements
    assertEquals(width_of_code, game.generateCode().length);
  }

} 


