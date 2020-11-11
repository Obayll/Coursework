/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */
 
/**
 * This class allows the construction of the Die object, which is the basis for the Pig Game
 * Methods include: Constructor, roll()
 */
public class Die {
  
  /**
   * Initializes variable to hold die value
   */
  private int d;
  
  /**
   * Constructor of class Die
   *
   * Declares die value to 1
   */
  public Die() {
    d = 1;
  }
  
  /**
   * Returns a random integer value 1 to 6
   * This is to simulate rolling a real die
   * 
   * @return random integer (value between 1-6)
   */
  public int roll() {
    d = (int)(Math.random() * 6 + 1);
    return d;
  }
}