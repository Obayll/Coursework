/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */

import student.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * This class consists of testing methods for the Player class
 * Methods tested include: roll(), addBank(), addPoints(), resetBank(), resetPoints(), getPoints(), getBank(), getTotal(), getName(), getDie1(), getDie2()
 */
public class PlayerTests extends TestCase {
  
  /**
   * Instantiates d[] as Player object used to test
   * Initializes actual[] as String to test d[] against
   */
  Player d[] = {
    new Player("Dom"),
    new Player("Big"),
    new Player("Gottem"),
    new Player("ASDASD"),
    new Player("asdSDADSad"),
    new Player("Bob"),
    new Player("LMao"),
    new Player("Test case"),
    new Player("Jesus"),
    new Player("Me")
  };
  String actual[] = {"Dom","Big","Gottem","ASDASD","asdSDADSad","Bob","LMao","Test case","Jesus","Me"};
  
  /**
   * The main method that allows this class to be called by the main project test method
   */
  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("PlayerTests");
  }
  
  /**
   * Calls roll() method of class Player on pair
   * 
   * @return random integer 2 to 12
   */
  @Test
  public void testRoll() {
    for (int i = 0;i < 10;i++) {
      assertTrue((d[i].roll() >= 2) && (d[i].roll() <= 12));
    }
  }
  
  /**
   * Tests temp for proper adding, subtraction, and reset of bank value
   * 
   * Uses getBank(), addBank(), and resetBank() from Player class
   */
  @Test
  public void testBank() {
    Player temp = new Player("");
    assertEquals(0,temp.getBank());
    temp.addBank(50);
    assertEquals(50,temp.getBank());
    temp.addBank(-10);
    assertEquals(40,temp.getBank());
    temp.resetBank();
    assertEquals(0,temp.getBank());
  }
  
  /**
   * Tests temp for proper adding, subtraction, and reset of points value
   * 
   * Uses getPoints(), addPoints(), and resetPoints() from Player class
   */
  @Test
  public void testPoints() {
    Player temp = new Player("");
    assertEquals(0,temp.getPoints());
    temp.addPoints(50);
    assertEquals(50,temp.getPoints());
    temp.addPoints(-10);
    assertEquals(40,temp.getPoints());
    temp.resetPoints();
    assertEquals(0,temp.getPoints());
  }
  
  /**
   * Tests d[] for proper storage of name variable
   * Uses actual[] for reference
   * 
   * Uses getName() from Player class
   */
  @Test
  public void testName() {
    for (int i = 0;i < d.length;i++) {
      assertEquals(actual[i], d[i].getName());
    }
  }
  
  /**
   * Tests temp to make sure values range from 2 to 12
   * 
   * Uses getTotal() from Player class
   */
  @Test
  public void testGetTotal() {
    Player temp = new Player("");
    for (int i = 0;i < 10;i++) {
      temp.roll();
      assertTrue((temp.getTotal() >= 2) && (temp.getTotal() <= 12));
    }
  }
  
  /**
   * Tests temp to make sure values range from 1 to 6
   * 
   * Uses getDie1() from Player class
   */
  @Test
  public void testGetDie1() {
    Player temp = new Player("");
    for (int i = 0;i < 10;i++) {
      temp.roll();
      assertTrue((temp.getDie1() >= 1) && (temp.getDie1() <= 6));
    }
  }
  
  /**
   * Tests temp to make sure values range from 1 to 6
   * 
   * Uses getDie2() from Player class
   */
  @Test
  public void testGetDie2() {
    Player temp = new Player("");
    for (int i = 0;i < 10;i++) {
      temp.roll();
      assertTrue((temp.getDie2() >= 1) && (temp.getDie2() <= 6));
    }
  }
}