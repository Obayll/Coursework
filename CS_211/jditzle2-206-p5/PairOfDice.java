/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */
 
/**
 * This class combines two dies into a pair, allowing ease-of-access for Pig
 * Methods include: Constructor, roll(), getTotal(), getDie1(), getDie2()
 */
public class PairOfDice {
  
  /**
   * Instantiates two Die variables to be used together
   * Initializes values to to represent die values and total value of sum
   */
  private Die die1, die2;
  private int total, value1, value2;
  
  /**
   * Constructor of class PairOfDice
   * 
   * Instantiates die1 and die2 to new Die
   * Declares value1 and value2 to 1
   * Adds both values into total
   */
  public PairOfDice() {
    die1 = new Die();
    die2 = new Die();
    value1 = 1;
    value2 = 1;
    total = value1 + value2;
  }
  
  /**
   * Calls roll() method on both die1 and die2
   * Value1 and value2 are given both die roll values respectively
   * Adds both value into total
   * 
   * @return total of two dice rolled (value between 2-12)
   */
  public int roll() {
    value1 = die1.roll();
    value2 = die2.roll();
    total = value1 + value2;
    return total;
  }
  
  /**
   * @return total value
   */
  public int getTotal() {
    return total;
  }
	
  /**
   * @return value of die1
   */
  public int getDie1() {
    return value1;
  }
  
  /**
   * @return value of die2
   */
  public int getDie2() {
    return value2;
  }
}