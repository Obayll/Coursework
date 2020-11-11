public class Executive extends Salaried {
  
  //declares fields
  private double bonus;
  
  //calls constrcutor of StaffMember super class
  //initiates bonus to 0
  public Executive(int id, String firstName, String lastName, double rate){
    super(id, firstName, lastName, rate);
    bonus = 0;
  }
  
  //adds 1% of salary to current bonus and extra bonus
  public void addBonus(double bonus) {
    this.bonus += bonus;
  }
  
  //returns pay
  //sets execBonus back to 0
  public double pay(){
    double pay = (getRate() + getRate() * 0.01) / 12 + this.bonus;
    this.bonus = 0;
    return pay;
  }
  
  //returns the worker type
  public String getWorkType() {
    return "(Executive)";
  }
}