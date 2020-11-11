public class Volunteer extends StaffMember {
  
  //calls constrcutor of StaffMember super class
  public Volunteer(int id, String firstName, String lastName, double rate){
    super(id, firstName, lastName, rate);
  }
  
  //fills in abstract class of StaffMember
  //returns 0 because worker is a volunteer
  public double pay(){
    return 0;
  }
  
  //returns the worker type
  public String getWorkType() {
    return "(Volunteer)";
  }
}