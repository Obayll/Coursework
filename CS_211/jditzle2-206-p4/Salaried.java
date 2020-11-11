/**
 * @authot Jordan Ditzler
 * @version 1.8.0_101
 */

/**
 * This class extends StaffMember as it is a subclass
 * Methods include: Constructor, pay(), getWorkType()
 */
public class Salaried extends StaffMember{
  
  /**
   * Constructor of class Salaried subclass of StaffMember
   * 
   * @param staff id, first and last name of staff member, rate of pay
   */
  public Salaried(int id, String firstName, String lastName, double rate){
    super(id, firstName, lastName, rate);
  }
  
  /**
   * Returns the rate divided by 12 due to salary being the total amount payed per year, and this calculates pay per month
   * 
   * @return rate / 12
   */
  public double pay(){
    return getRate() / 12;
  }
  
  /**
   * Alawyas return "(Salaried)" because the worker type has already been instantiated by using Volunteer constructor
   * 
   * @return "(Salaried)" always
   */
  public String getWorkType() {
    return "(Salaried)";
  }
}