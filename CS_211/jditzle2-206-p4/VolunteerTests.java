/**
 * @authot Jordan Ditzler
 * @version 1.8.0_101
 */

import student.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * This class consists of testing methods for the Volunteer class and its superclass StaffMember
 * Methods tested include: Constructor, pay(), getWorkType()
 */

public class VolunteerTests extends TestCase {
  
  /**
   * Instantiates v1[] as object used to test
   * 
   * Instantiates ID[], First[], Last[], and Rate[] as reference for comparing
   */
  Volunteer v1[] = {
    new Volunteer(0123, "Bob", "Dom", 0),
    new Volunteer(0000, "Dillan", "Dom", 0),
    new Volunteer(4236, "Gene", "Dom", 0),
    new Volunteer(6686, "Jesus", "Christ", 0),
    new Volunteer(4235, "Bruce", "Almighty", 0),
    new Volunteer(9767, "Frodo", "Baggins", 0),
    new Volunteer(1012, "6", "7", 0),
    new Volunteer(1102, "GHASD", "sasdFGG", 0),
    new Volunteer(9999, "Man", "Woman", 0),
    new Volunteer(7676, "Bob", "Jackson", 0)
  };
  int ID[] = {0123,0000,4236,6686,4235,9767,1012,1102,9999,7676};
  String First[] = {"Bob","Dillan","Gene","Jesus","Bruce","Frodo","6","GHASD","Man","Bob"};
  String Last[] = {"Dom","Dom","Dom","Christ","Almighty","Baggins","7","sasdFGG","Woman","Jackson"};
  double Rate[] = {0,0,0,0,0,0,0,0,0,0};
  
  /**
   * The main method that allows this class to be called by the main project test method
   */
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("VolunteerTests");
  }

  /**
   * The tester method for the Volunteer class's constructor
   * 
   * Tests each part of the constructor for proper transferral of variable data
   * Uses the ID[], First[], Last[], & Rate[] as reference for comparing
   * 
   * Uses getID(), getFirstName(), getLastName(), getRate() methods from Volunteer class
   */
  @Test
  public void testConstructor() {
    for(int i = 0;i < v1.length;i++) {
      assertEquals(ID[i], v1[i].getID());
      assertEquals(First[i], v1[i].getFirstName());
      assertEquals(Last[i], v1[i].getLastName());
      assertEquals(Rate[i], v1[i].getRate(), 1e-6);
    }
  }
  
  /**
   * Tests v1[] for proper calculation of pay
   * 
   * Always returns 0 due to v1[] being a Volunteer, as Volunteers never receive compensation
   * 
   * Uses pay() from Volunteer class
   */
  @Test
  public void testPay() {
    for(int i = 0;i < v1.length;i++) {
      assertEquals(0, v1[i].pay(), 1e-6);
    }
  }
  
  /**
   * Tests v1[] for return of "(Volunteer)" every loop
   * 
   * Uses getWorkType() method from Volunteer class
   */
  @Test
  public void testType() {
    for(int i = 0;i < v1.length;i++) {
      assertEquals("(Volunteer)", v1[i].getWorkType());
    }
  }
}