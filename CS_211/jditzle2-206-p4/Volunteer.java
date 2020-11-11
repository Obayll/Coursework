/**
 * @authot Jordan Ditzler
 * @version 1.8.0_101
 */

/**
 * This class extends StaffMember as it is a subclass
 * Methods include: Constructor, pay(), getWorkType()
 */
public class Volunteer extends StaffMember {
  
  /**
   * Constructor of class Volunteer subclass of StaffMember
   * 
   * @param staff id, first and last name of staff member, rate of pay
   */
  public Volunteer(int id, String firstName, String lastName, double rate){
    super(id, firstName, lastName, rate);
  }
  
  /**
   * Always return 0 because volunteers receive no monetary compensation
   * 
   * @return 0 always
   */
  public double pay(){
    return 0;
  }
  
  /**
   * Alawyas return "(Volunteer)" because the worker type has already been instantiated by using Volunteer constructor
   * 
   * @return "(Volunteer)" always
   */
  public String getWorkType() {
    return "(Volunteer)";
  }
}