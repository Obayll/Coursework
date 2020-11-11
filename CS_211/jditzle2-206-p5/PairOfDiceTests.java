/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */

import student.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * This class consists of testing methods for the PairOfDice class
 * Methods tested include: Roll(), getTotal(), getDie1(), getDie2()
 */
public class PairOfDiceTests extends TestCase {
  
  /**
   * Instantiates d as PairOfDice object used to test
   */
  PairOfDice d = new PairOfDice();
  
  /**
   * The main method that allows this class to be called by the main project test method
   */
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("PairOfDiceTests");
  }
  
  /**
   * Tests d to make sure values range from 2 to 12
   * 
   * Uses roll() from PairOfDice class
   */
  @Test
  public void testRoll() {
    for (int i = 0;i < 10;i++) {
      assertTrue((d.roll() >= 2) && (d.roll() <= 12));
    }
  }
  
  /**
   * Tests d to make sure values range from 2 to 12
   * 
   * Uses getTotal() from PairOfDice class
   */
  @Test
  public void testGetTotal() {
    for (int i = 0;i < 10;i++) {
      d.roll();
      assertTrue((d.getTotal() >= 2) && (d.getTotal() <= 12));
    }
  }
  
  /**
   * Tests d to make sure values range from 1 to 6
   * 
   * Uses getDie1() from PairOfDice class
   */
  @Test
  public void testGetDie1() {
    for (int i = 0;i < 10;i++) {
      d.roll();
      assertTrue((d.getDie1() >= 1) && (d.getDie1() <= 6));
    }
  }
  
  /**
   * Tests d to make sure values range from 1 to 6
   * 
   * Uses getDie2() from PairOfDice class
   */
  @Test
  public void testGetDie2() {
    for (int i = 0;i < 10;i++) {
      d.roll();
      assertTrue((d.getDie2() >= 1) && (d.getDie2() <= 6));
    }
  }
}