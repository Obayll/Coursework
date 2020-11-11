/**
 * @authot Jordan Ditzler
 * @version 1.8.0_101
 */

import student.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * This class consists of testing methods for the Executive class and its superclass StaffMember
 * Methods tested include: Constructor, pay(), addBonus(), getBonus(), getWorkType(), setPayRate(), getRate()
 */

public class ExecutiveTests extends TestCase {
  
  /**
   * Instantiates e1[] as object used to test
   * 
   * Instantiates ID[], First[], Last[], Rate[], and Pay[] as reference for comparing
   */
  Executive e1[] = {
    new Executive(0123, "Bob", "Dom", 500000),
    new Executive(0000, "Dillan", "Dom", 100),
    new Executive(4236, "Gene", "Dom", 80000),
    new Executive(6686, "Jesus", "Christ", 2312445),
    new Executive(4235, "Bruce", "Almighty", 5555555),
    new Executive(9767, "Frodo", "Baggins", 666),
    new Executive(1012, "6", "7", 12567),
    new Executive(1102, "GHASD", "sasdFGG", 500),
    new Executive(9999, "Man", "Woman", 44456),
    new Executive(7676, "Bob", "Jackson", 456324)
  };
  int ID[] = {0123,0000,4236,6686,4235,9767,1012,1102,9999,7676};
  String First[] = {"Bob","Dillan","Gene","Jesus","Bruce","Frodo","6","GHASD","Man","Bob"};
  String Last[] = {"Dom","Dom","Dom","Christ","Almighty","Baggins","7","sasdFGG","Woman","Jackson"};
  double Rate[] = {500000,100,80000,2312445,5555555,666,12567,500,44456,456324};
  double Pay[] = {42083.333333333336,8.416666666666666,6733.333333333333,194630.7875,467592.54583333334,56.055,1057.7225,42.083333333333336,3741.713333333333,38407.27};
  
  /**
   * The main method that allows this class to be called by the main project test method
   */
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("ExecutiveTests");
  }
  
  /**
   * The tester method for the Executive class's constructor
   * 
   * Tests each part of the constructor for proper transferral of variable data
   * Uses ID[], First[], Last[], & Rate[] as reference for comparing
   * 
   * Uses getID(), getFirstName(), getLastName(), getRate() methods from Executive class
   */
  @Test
  public void testConstructor() {
    for(int i = 0;i < e1.length;i++) {
      assertEquals(ID[i], e1[i].getID());
      assertEquals(First[i], e1[i].getFirstName());
      assertEquals(Last[i], e1[i].getLastName());
      assertEquals(Rate[i], e1[i].getRate(), 1e-6);
    }
  }
  
  /**
   * Tests e1[] for proper calculation of pay
   * Adds 500 bonus using addBonus() and tests e1[] for proper pay calculation
   * 
   * Uses Pay[] as reference for comparing
   * 
   * Uses pay() and addBonus() from Executive class
   */
  @Test
  public void testPay() {
    for(int i = 0;i < e1.length;i++) {
      assertEquals(Pay[i], e1[i].pay(), 1e-6);
      e1[i].addBonus(500);
      assertEquals(Pay[i] + 500, e1[i].pay(), 1e-6);
    }
  }
  
  /**
   * Tests e1[] for return of "(Executive)" every loop
   * 
   * Uses getWorkType() method from Executive class
   */
  @Test
  public void testType() {
    for(int i = 0;i < e1.length;i++) {
      assertEquals("(Executive)", e1[i].getWorkType());
    }
  }
  
  /**
   * Tests e1[] for proper change of rate variable
   * 
   * Uses setPayRate() and getRate() from Executive class
   */
  @Test
  public void testRate() {
    for(int i = 0;i < e1.length;i++) {
      e1[i].setPayRate(404);
      assertEquals(404, e1[i].getRate(), 1e-6);
    }
  }
  
  /**
   * Tests e1[] for proper change of bonus variable
   * 
   * Uses getBonus() and addBonus() from Executive class
   */
  @Test
  public void testBonus() {
    for(int i = 0;i < e1.length;i++) {
      assertEquals(0, e1[i].getBonus(), 1e-6);
      e1[i].addBonus(500);
      assertEquals(500, e1[i].getBonus(), 1e-6);
    }
  }
}