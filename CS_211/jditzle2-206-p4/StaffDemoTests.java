/**
 * @authot Jordan Ditzler
 * @version 1.8.0_101
 */

import student.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * This class consists of testing methods for the StaffDemo class
 * Methods tested include: main, createStaff(), payAll()
 */

public class StaffDemoTests extends TestCase {
  
  /**
   * The main method that allows this class to be called by the main project test method
   */
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("StaffDemoTest");
  }
  
  /**
   * Tests the main method of StaffDemo for proper output
   * 
   * An array of 4 staffmembers are created using data from a .txt file
   * Each element in the array is the printed in the output in a specific layout
   * 
   * Uses main, createStaff(), payAll() in CreateDemo
   */
  @Test
  public void testMain() {
    try {
      StaffDemo.main(null);
      String output = systemOut().getHistory();
      assertTrue(output.contains("Grace Hopper (Salaried): $10,000.00"));
      assertTrue(output.contains("James Gosling (Volunteer): $0.00"));
      assertTrue(output.contains("Dennis Ritchie (Hourly): $680.00"));
      assertTrue(output.contains("Donald Knuth (Executive): $28,616.67"));
    } catch (java.io.FileNotFoundException e) {
      System.out.println("Error: File not found.");
    }
  }
}