/**
 * @authot Jordan Ditzler
 * @version 1.8.0_101
 */

/**
 * This class extends StaffMember as it is a subclass
 * Methods include: Constructor, pay(), getWorkType(), getHours(), addHours()
 */
public class Hourly extends StaffMember {
  
  /**
   * Instantiates bonus variables to be used by mutiple methods
   */
  private int hours;
  
  /**
   * Constructor of class Hourly subclass of StaffMember
   * 
   * Intiates hours to 80
   * 
   * @param staff id, first and last name of staff member, rate of pay
   */
  public Hourly(int id, String firstName, String lastName, double rate){
    super(id, firstName, lastName, rate);
    hours = 80;
  }
  
  /**
   * Returns rate of pay * 80 due to wage being per hour
   * 
   * Resets hours to 80
   * 
   * @return rate * 80
   */
  public double pay(){
    double pay = hours * getRate();
    this.hours = 80;
    return pay;
  }
  
  /**
   * Alawyas return "(Hourly)" because the worker type has already been instantiated by using Volunteer constructor
   * 
   * @return "(Hourly)" always
   */
  public String getWorkType() {
    return "(Hourly)";
  }
  
  /**
   * @return hours worked
   */
  public int getHours() {
    return hours;
  }
  
  /**
   * Adds extra hours worked to accumulated hours worked
   * 
   * @param hours worked extra
   */
  public void addHours(int hours) {
    this.hours += hours;
  }
}