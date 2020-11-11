public class GeneralLE {
  private double a;
  private double b;
  private double c;
  private double x;
  private double y;
  private double m;
  private double intercept;
  // Public constructor that accepts values for the three constants a,
  // b, and c, as well as the variable x. This is the only public
  // constructor.
  public GeneralLE(double a, double b, double c, double x){
    this.a = a;
    this.b = b;
    this.c = c;
    this.x = x;
    m = -1 * this.a / this.b;
    intercept = this.c / this.b;
    y = (this.c - (this.a * this.x)) / this.b;
  }
  // Return the numeric value of the equation which is the numeric
  // quantity that should be on both left and right sides of the equal
  // sign.
  public double value(){
    return c;
  }
  // Return the current value of x
  public double getX(){
    return x;
  }
  // Return the current value of y
  public double getY(){
    return y;
  }
  // Set the value of x and update other values as appropriate to
  // preserve the equation.
  public void setX(double x){
    this.x = x;
    y = (c - (a * this.x)) / b;
  }
  // Set the value of y and update other values as appropriate to
  // preserve the equation.
  public void setY(double y){
    this.y = y;
    x = (c - (b * this.y)) / a;
  }
  // Return a String version of the general form of the equation. The
  // pretty version's general format should be
  //
  // A.AA * x + B.BB * y = C.CC
  //
  // A.AA, B.BB, and C.CC are the coefficients for the linear equation
  // with 2 decimal digits of accuracy. Examples:
  //
  // 1.23 * x + 45.68 * y = 2.00
  // -54.99 * x + -9.86 * y = 42.41
  public String toString(){
    return String.format("%.2f", a) + " * x + " + String.format("%.2f", b) + " * y = " + String.format("%.2f", c);
  }
  // Produce a version of this GeneralLE in slope intercept form. The
  // values of x and y must be preserved but the coefficients should
  // be converted to slope intercept form. The resulting
  // SlopeInterceptLE is not connected to the generating GeneralLE in
  // any way: changes to one do not affect the other.
  public SlopeInterceptLE toSlopeInterceptLE(){
    SlopeInterceptLE value = new SlopeInterceptLE(m, intercept, x);
    return value;
  }
}