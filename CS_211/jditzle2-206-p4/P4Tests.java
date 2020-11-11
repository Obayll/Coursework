/**
 * @authot Jordan Ditzler
 * @version 1.8.0_101
 */

import student.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * This class consists of testing methods from Project4(ExecutiveTests, HourlyTests, SalariedTests, VolunteerTests, StaffDemoTests)
 */

public class P4Tests extends TestCase {
  
  /**
   * Calls all test methods(ExecutiveTests, HourlyTests, SalariedTests, VolunteerTests, StaffDemoTests)
   */
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("VolunteerTests","HourlyTests","SalariedTests","ExecutiveTests","StaffDemoTests");
  }
}