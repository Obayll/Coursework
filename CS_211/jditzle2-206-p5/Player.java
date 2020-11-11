/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */
 
/**
 * This class allows PigGame to interact easily between two players
 * Methods include: Constructor, roll(), addBank(), addPoints(), resetBank(), resetPoints(), getPoints(), getBank(), getTotal(), getName(), getDie1(), getDie2()
 */
public class Player {
  
  /**
   * Instantiates PairOfDice variable
   * Initializes name to hold name of player
   * Initializes bank and points to hold accumulated values
   */
  private PairOfDice pair;
  private String name;
  private int bank, points;
  
  /**
   * Constructor of class Player
   *
   * Instantiates pair to a new PairOfDice
   * Declares name to param variable n
   * Declares accumlaters bank and points to 0
   * 
   * @param name of player
   */
  public Player(String n) {
    pair = new PairOfDice();
    name = n;
    bank = 0;
    points = 0;
  }
  
  /**
   * Calls roll() method of class PairOfDice on pair
   * 
   * @return random integer 2 to 12
   */
  public int roll() {
    return pair.roll();
  }
  
  /**
   * Adds param variable x to current bank value
   *
   * @param bank amount to add
   */
  public void addBank(int x) {
    bank += x;
  }
  
  /**
   * Adds param variable x to current point value
   *
   * @param point amount to add
   */
  public void addPoints(int x) {
    points += x;
  }
  
  /**
   * Resets bank to 0
   */
  public void resetBank() {
    bank = 0;
  }
  
  /**
   * Resets points to 0
   */
  public void resetPoints() {
    points = 0;
  }
  
  /**
   * @return points value
   */
  public int getPoints() {
    return points;
  }
  
  /**
   * @return bank value
   */
  public int getBank() {
    return bank;
  }
  
  /**
   * @return name of player
   */
  public String getName() {
    return name;
  }
  
  /**
   * Calls getTotal() method of class PairOfDice
   * 
   * @return total value of dice
   */
  public int getTotal() {
    return pair.getTotal();
  }
  
  /**
   * Calls getDie1() method of class PairOfDice on pair
   * 
   * @return die1 value (value between 1-6)
   */
  public int getDie1() {
    return pair.getDie1();
  }
  
  /**
   * Calls getDie2() method of class PairOfDice on pair
   * 
   * @return die2 value (value between 1-6)
   */
  public int getDie2() {
    return pair.getDie2();
  }
}