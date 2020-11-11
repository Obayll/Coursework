/**
 * @authot Jordan Ditzler
 * @version 1.8.0_101
 */

/**
 * This class extends StaffMember as it is a subclass
 * Methods include: Constructor, pay(), getWorkType(), addBonus(), getBonus()
 */
public class Executive extends Salaried {
  
  /**
   * Instantiates bonus variables to be used by mutiple methods
   */
  private double bonus;
  
  /**
   * Constructor of class Executive subclass of Salaried
   * 
   * Initiates bonus to 0
   * 
   * @param staff id, first and last name of staff member, rate of pay
   */
  public Executive(int id, String firstName, String lastName, double rate){
    super(id, firstName, lastName, rate);
    bonus = 0;
  }
  
  /**
   * Returns the rate divided by 12 due to salary being the total amount payed per year, and this calculates pay per month
   * Adds one-hundredth of the rate to pay
   * Adds accumulated executive bonus to pay
   * 
   * Resets executive bonus to 0
   * 
   * @return rate / 12 + rate * 0.01 + executive bonus
   */
  public double pay(){
    double pay = (getRate() + getRate() * 0.01) / 12 + this.bonus;
    this.bonus = 0;
    return pay;
  }
  
  /**
   * Alawyas return "(Executive)" because the worker type has already been instantiated by using Volunteer constructor
   * 
   * @return "(Executive)" always
   */
  public String getWorkType() {
    return "(Executive)";
  }
  
  /*
   * @return executive bonus
   */
  public double getBonus() {
    return bonus;
  }
  
  /*
   * Adds given bonus to current bonus
   * 
   * @param executive bonus
   */
  public void addBonus(double bonus) {
    this.bonus += bonus;
  }
}