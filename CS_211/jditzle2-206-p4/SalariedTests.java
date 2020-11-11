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

public class SalariedTests extends TestCase {
  
  /**
   * Instantiates s1[] as object used to test
   * 
   * Instantiates ID[], First[], Last[], Rate[], and Pay[] as reference for comparing
   */
  Salaried s1[] = {
    new Salaried(0123, "Bob", "Dom", 500000),
    new Salaried(0000, "Dillan", "Dom", 100),
    new Salaried(4236, "Gene", "Dom", 80000),
    new Salaried(6686, "Jesus", "Christ", 2312445),
    new Salaried(4235, "Bruce", "Almighty", 5555555),
    new Salaried(9767, "Frodo", "Baggins", 666),
    new Salaried(1012, "6", "7", 12567),
    new Salaried(1102, "GHASD", "sasdFGG", 500),
    new Salaried(9999, "Man", "Woman", 44456),
    new Salaried(7676, "Bob", "Jackson", 456324)
  };
  int ID[] = {0123,0000,4236,6686,4235,9767,1012,1102,9999,7676};
  String First[] = {"Bob","Dillan","Gene","Jesus","Bruce","Frodo","6","GHASD","Man","Bob"};
  String Last[] = {"Dom","Dom","Dom","Christ","Almighty","Baggins","7","sasdFGG","Woman","Jackson"};
  double Rate[] = {500000,100,80000,2312445,5555555,666,12567,500,44456,456324};
  double Pay[] = {41666.666666666664,8.333333333333334,6666.666666666667,192703.75,462962.9166666667,55.5,1047.25,41.666666666666664,3704.6666666666665,38027.0};
  
  /**
   * The main method that allows this class to be called by the main project test method
   */
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("SalariedTests");
  }
  
  /**
   * The tester method for the Salaried class's constructor
   * 
   * Tests each part of the constructor for proper transferral of variable data
   * Uses ID[], First[], Last[], & Rate[] as reference for comparing
   * 
   * Uses getID(), getFirstName(), getLastName(), getRate() methods from Salaried class
   */
  @Test
  public void testConstructor() {
    for(int i = 0;i < s1.length;i++) {
      assertEquals(ID[i], s1[i].getID());
      assertEquals(First[i], s1[i].getFirstName());
      assertEquals(Last[i], s1[i].getLastName());
      assertEquals(Rate[i], s1[i].getRate(), 1e-6);
    }
  }
  
  /**
   * Tests s1[] for proper calculation of pay
   * 
   * Adds 500 bonus using addBonus() and tests e1[] for proper calculation
   * Uses Pay[] as reference for comparing
   * 
   * Uses pay() and addBonus() from Executive class
   */
  @Test
  public void testPay() {
    for(int i = 0;i < s1.length;i++) {
      assertEquals(Pay[i], s1[i].pay(), 1e-6);
    }
  }
  
  /**
   * Tests s1[] for return of "(Salaried)" every loop
   * 
   * Uses getWorkType() method from Salaried class
   */
  @Test
  public void testType() {
    for(int i = 0;i < s1.length;i++) {
      assertEquals("(Salaried)", s1[i].getWorkType());
    }
  }
  
  /**
   * Tests s1[] for proper change of rate variable
   * 
   * Uses setPayRate() and getRate() from Salaried class
   */
  @Test
  public void testRate() {
    for(int i = 0;i < s1.length;i++) {
      s1[i].setPayRate(404);
      assertEquals(404, s1[i].getRate(), 1e-6);
    }
  }
}