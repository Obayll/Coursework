public class Salaried extends StaffMember{
  
  //calls constrcutor of StaffMember super class
  public Salaried(int id, String firstName, String lastName, double rate){
    super(id, firstName, lastName, rate);
  }
  
  //returns pay
  public double pay(){
    return getRate() / 12;
  }
  
  //returns the worker type
  public String getWorkType() {
    return "(Salaried)";
  }
}