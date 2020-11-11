/**
 * @authot Jordan Ditzler
 * @version 1.8.0_101
 */

import student.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * This class consists of testing methods for the Hourly class and its superclass StaffMember
 * Methods tested include: Constructor, pay(), addHours(), getHours(), getWorkType(), setPayRate(), getRate()
 */

public class HourlyTests extends TestCase {
  
  /**
   * Instantiates h1[] as object used to test
   * 
   * Instantiates ID[], First[], Last[], Rate[], and Pay[] as reference for comparing
   */
  Hourly h1[] = {
    new Hourly(0123, "Bob", "Dom", 4.5),
    new Hourly(0000, "Dillan", "Dom", 8),
    new Hourly(4236, "Gene", "Dom", 6.1),
    new Hourly(6686, "Jesus", "Christ", 4.2),
    new Hourly(4235, "Bruce", "Almighty", 6.9),
    new Hourly(9767, "Frodo", "Baggins", 5.55),
    new Hourly(1012, "6", "7", 7.77),
    new Hourly(1102, "GHASD", "sasdFGG", 0.01),
    new Hourly(9999, "Man", "Woman", 5.05),
    new Hourly(7676, "Bob", "Jackson", 6)
  };
  int ID[] = {0123,0000,4236,6686,4235,9767,1012,1102,9999,7676};
  String First[] = {"Bob","Dillan","Gene","Jesus","Bruce","Frodo","6","GHASD","Man","Bob"};
  String Last[] = {"Dom","Dom","Dom","Christ","Almighty","Baggins","7","sasdFGG","Woman","Jackson"};
  double Rate[] = {4.5,8,6.1,4.2,6.9,5.55,7.77,0.01,5.05,6};
  double Pay[] = {360,640,488,336,552,444,621.5999999999999,0.8,404,480};
  
  /**
   * The main method that allows this class to be called by the main project test method
   */
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("HourlyTests");
  }
  
  /**
   * The tester method for the Hourly class's constructor
   * 
   * Tests each part of the constructor for proper transferral of variable data
   * Uses ID[], First[], Last[], & Rate[] as reference for comparing
   * 
   * Uses getID(), getFirstName(), getLastName(), getRate() methods from Hourly class
   */
  @Test
  public void testConstructor() {
    for(int i = 0;i < h1.length;i++) {
      assertEquals(ID[i], h1[i].getID());
      assertEquals(First[i], h1[i].getFirstName());
      assertEquals(Last[i], h1[i].getLastName());
      assertEquals(Rate[i], h1[i].getRate(), 1e-6);
    }
  }
  
  /**
   * Tests h1[] for proper calculation of pay
   * Adds 20 hours by using addBonus() and tests e1[] for proper pay calculation
   * 
   * Uses Pay[] as reference for comparing
   * 
   * Uses pay() and addBonus() from Hourly class
   */
  @Test
  public void testPay() {
    for(int i = 0;i < h1.length;i++) {
      assertEquals(Pay[i], h1[i].pay(), 1e-6);
      h1[i].addHours(20);
      assertEquals(Pay[i] * 1.25, h1[i].pay(), 1e-6);
    }
  }
  
  /**
   * Tests h1[] for return of "(Hourly)" every loop
   * 
   * Uses getWorkType() method from Hourly class
   */
  @Test
  public void testType() {
    for(int i = 0;i < h1.length;i++) {
      assertEquals("(Hourly)", h1[i].getWorkType());
    }
  }
  
  /**
   * Tests h1[] for proper change of rate variable
   * 
   * Uses setPayRate() and getRate() from Hourly class
   */
  @Test
  public void testRate() {
    for(int i = 0;i < h1.length;i++) {
      h1[i].setPayRate(5.5);
      assertEquals(5.5, h1[i].getRate(), 1e-6);
      assertEquals(440, h1[i].pay(), 1e-6);
    }
  }
  
  /**
   * Tests h1[] for proper change of hours variable
   * 
   * Uses addHours() and getHours() from Hourly class
   */
  @Test
  public void testHours() {
    for(int i = 0;i < h1.length;i++) {
      h1[i].addHours(40);
      assertEquals(120, h1[i].getHours());
    }
  }
}