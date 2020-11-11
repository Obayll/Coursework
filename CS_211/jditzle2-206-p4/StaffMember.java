/**
 * @authot Jordan Ditzler
 * @version 1.8.0_101
 */

/**
 * This abstract class consists of the methods to be used in subclasses
 * Methods include: Constructor, setPayRate(), getRate(), getFirstName(), getLastName(), getID()
 * Abstract methods include: pay(), getWorkType()
 */
public abstract class StaffMember {
  
  /**
   * Instantiates variables for placeholder use to be later used when the constructor is called
   */
  private int id;
  private double rate;
  String firstName, lastName;
  
  /**
   * Constructor of superclass StaffMember
   * 
   * @param staff id, first and last name of staff member, rate of pay
   */
  public StaffMember(int id, String firstName, String lastName, double rate){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.rate = rate;
  }
  
  /**
   * Asssigns given pay rate as the new default pay rate
   * 
   * @param rate of pay
   */
  public void setPayRate(double rate){
    this.rate = rate;
  }
  
  /**
   * @return rate of pay
   */
  public double getRate() {
    return rate;
  }
  
  /**
   * Placeholder for subclasses to implement pay() method
   */
  abstract public double pay();
  
  /**
   * Placeholder for subclasses to implement getWorkType() method
   */
  abstract public String getWorkType();
  
  /**
   * @return first name of staff member
   */
  public String getFirstName() {
    return firstName;
  }
  
  /**
   * @return last name of staff member
   */
  public String getLastName() {
    return lastName;
  }
  
  /**
   * @return id of staff member
   */
  public int getID() {
    return id;
  }
}