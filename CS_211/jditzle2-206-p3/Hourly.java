public class Hourly extends StaffMember {
  
  //declares fields
  private int hours;
  
  //calls constrcutor of StaffMember super class
  //initiates hours to 80
  public Hourly(int id, String firstName, String lastName, double rate){
    super(id, firstName, lastName, rate);
    hours = 80;
  }
  
  //adds hours from parameters
  public void addHours(int hours) {
    this.hours += hours;
  }
  
  //returns pay
  //sets hours back to defaualt 80
  public double pay(){
    double pay = hours * getRate();
    this.hours = 80;
    return pay;
  }
  
  //returns the worker type
  public String getWorkType() {
    return "(Hourly)";
  }
}