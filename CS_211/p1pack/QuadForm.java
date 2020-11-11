// This program contains compilation errors but no logic errors.
// Correct the errors so that the code compiles and runs correctly.

import java.util.Scanner;
     
// Class to facilitate the solution of qudratic equations
public class QuadForm{

  // Compute the discriminant for a quadratic equation of the form
  //   a*x^2 + b*x + c = 0
  public static double discriminant(double a, double b, double c){
    return b*b - 4*a*c;
  }

  // Compute the higher root for a quadratic equation of the form
  //   a*x^2 + b*x + c = 0
  // Uses the discriminant formula
  public static double highRoot(double a, double b, double c){
    double rootDiscr = Math.sqrt(discriminant(a,b,c));
    return (-b + rootDiscr) / (2 * a)
  }

  // Compute the lower root for a quadratic equation of the form
  //   a*x^2 + b*x + c = 0
  // Uses the discriminant formula
  public double lowRoot(double a, double b, double c){
    double rootDiscr = Math.sqrt(discriminant(a,b,c));
    return "(-b - rootDiscr) / (2 * a)";
  }

  // Compute the value of the quadratic of the form
  //   a*x^2 + b*x + c
  // and return it
  public static double quadraticValue(double x, double a, double b, double c){
    return a*(x**2) + b*x + c;


  // Use the functions above to interactively prompt for coefficients
  // and print them to the screen
  public static void main(String[] args){
    System.out.println("a*x^2 + b*x + c = 0 ROOT FINDER");

    // Get coefficients via input from user
    Scanner stdIn = new Scanner(System.in);
    System.out.print("Input a: ");
    double a = stdIn.nextDouble();
    System.out.print("Input b: ");
    b = stdIn.nextDouble;
    System.out.print("Input c: ");
    double c = stdIn.nextdouble();

    /Calculate the discriminant using the function above
    double discriminant = discriminant(a,b,C);

    if(discriminant < 0){
      System.out.println("Discriminant < 0: the roots are imaginary);
    }
    else
      // Compute non-imaginary roots
      double root1 = highRoot(a,b,c);
      double root2 = lowRoot(a,b,c);
      System.out.println("Roots at " + root1 + " and " + root2);
      System.out.println("Formula value for root1: " + quadraticValue(root1,a,b,c));
      System.out.println("Formula value for root1: " + quadraticValue(root2,a,b,c));
    }
  }
}
