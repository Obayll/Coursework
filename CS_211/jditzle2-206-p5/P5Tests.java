/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */

import student.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * This class consists of testing methods from Project5(DieTests, PairOfDiceTests, PlayerTests)
 */
public class P5Tests extends TestCase {
  
  /**
   * Calls all test methods(DieTests, PairOfDiceTests, PlayerTests)
   */
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("DieTests","PairOfDiceTests","PlayerTests");
  }
}