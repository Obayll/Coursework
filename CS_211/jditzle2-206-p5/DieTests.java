/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */

import student.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * This class consists of testing methods for the Die class
 * Methods tested include: Roll()
 */
public class DieTests extends TestCase {
  
  /**
   * Instantiates d as die object used to test
   */
  Die d = new Die();
  
  /**
   * The main method that allows this class to be called by the main project test method
   */
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("DieTests");
  }
  
  /**
   * Tests d to make sure values range from 1 to 6
   * 
   * Uses roll() from Die class
   */
  @Test
  public void testRoll() {
    for (int i = 0;i < 10;i++) {
      assertTrue((d.roll() >= 1) && (d.roll() <= 6));
    }
  }
}