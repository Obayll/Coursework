public abstract class StaffMember {
  
  //declares fields
  private int id;
  private double rate;
  String firstName, lastName;
  
  //starts constructor and accept id, name, and rate
  public StaffMember(int id, String firstName, String lastName, double rate){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.rate = rate;
  }
  
  //sets rate of pay for specific worker
  public void setPayRate(double rate){
    this.rate = rate;
  }
  
  //returns rate of pay
  public double getRate() {
    return rate;
  }
  
  //instantiates pay() method for all subclasses
  abstract public double pay();
  
  //instiates  getWorkType() for all subclasses
  abstract public String getWorkType();
  
  //returns first name
  public String getFirstName() {
    return firstName;
  }
  
  //returns last name
  public String getLastName() {
    return lastName;
  }
}