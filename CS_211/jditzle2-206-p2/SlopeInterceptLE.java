// Fill in the definition for this class
public class SlopeInterceptLE {
  // Fields required for the class
  private double m;
  private double b;
  private double x;
  private double y;
  // Fill in the definitions of all methods
  public SlopeInterceptLE(double m, double b){
    this.m = m;
    this.b = b;
    x = 0;
    y = this.m * x + this.b;
  }

  public SlopeInterceptLE(double m, double b, double x){
    this.m = m;
    this.b = b;
    this.x = x;
    y = this.m * this.x + this.b;
  }

  public double value(){
    return y;
  }
  
  public double getX(){
    return x;
  }
  
  public double getY(){
    return y;
  }

  public void setX(double x){
    this.x = x;
    y = m * this.x + b;
  }
  public void setY(double y){
    this.y = y;
    x = (this.y - b) / m;
  }

  public String toString(){
    return "y = " + String.format("%.2f", m) + " * x + " + String.format("%.2f", b);
  }
  
  public double getM(){
    return m;
  }
  public double getB(){
    return b;
  }
}
