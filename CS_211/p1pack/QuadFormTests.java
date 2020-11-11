// Example of using unit tests for programming assignment 1.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar QuadFormTests.java.java #compile
// $> java -cp .:junit-cs211.jar QuadFormTests.java       #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar QuadFormTests.java.java #compile
// $> java -cp .;junit-cs211.jar QuadFormTests.java       #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import java.io.*;

public class QuadFormTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("QuadFormTests");
  }

  double tol = 1e-6;            // tolerance for double equality check

  @Test (timeout=1000) public void QuadForm_discriminant_1(){
    assertEquals(5.0, QuadForm.discriminant(1.0,3.0,1.0),tol);
  }
  @Test (timeout=1000) public void QuadForm_discriminant_2(){
    assertEquals(-7.0, QuadForm.discriminant(2.0,3.0,2.0),tol);
  }
  @Test (timeout=1000) public void QuadForm_discriminant_3(){
    assertEquals(1.0, QuadForm.discriminant(2.0,5.0,3.0),tol);
  }
  @Test (timeout=1000) public void QuadForm_discriminant_4(){
    assertEquals(60.0, QuadForm.discriminant(5.0,10.0,2.0),tol);
  }
  @Test (timeout=1000) public void QuadForm_highRoot_1(){
    assertEquals(0.2612038749637415, QuadForm.highRoot(7,2,-1),tol);
  }
  @Test (timeout=1000) public void QuadForm_highRoot_2(){
    assertEquals(-0.52051760426961, QuadForm.highRoot(-9,3,4),tol);
  }
  @Test (timeout=1000) public void QuadForm_highRoot_3(){
    assertEquals(0.36037961002806324, QuadForm.highRoot(12,4,-3),tol);
  }
  @Test (timeout=1000) public void QuadForm_lowRoot_1(){
    assertEquals(-0.5469181606780272, QuadForm.lowRoot(7,2,-1),tol);
  }
  @Test (timeout=1000) public void QuadForm_lowRoot_2(){
    assertEquals(0.8538509376029434, QuadForm.lowRoot(-9,3,4),tol);
  }
  @Test (timeout=1000) public void QuadForm_lowRoot_3(){
    assertEquals(-0.6937129433613967, QuadForm.lowRoot(12,4,-3),tol);
  }
  @Test (timeout=1000) public void QuadForm_quadraticValue_1(){
    assertEquals(9.670000000000002, QuadForm.quadraticValue(1.1, 7,2,-1),tol);
  }
  @Test (timeout=1000) public void QuadForm_quadraticValue_2(){
    assertEquals(-440.96, QuadForm.quadraticValue(7.2, -9,3,4),tol);
  }
  @Test (timeout=1000) public void QuadForm_quadraticValue_3(){
    assertEquals(62.0, QuadForm.quadraticValue(-2.5, 12,4,-3),tol);
  }

  String newline = System.getProperty("line.separator");

  @Test (timeout=1000) public void QuadForm_main1(){
    ByteArrayOutputStream localOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(localOut));
    ByteArrayInputStream in = new ByteArrayInputStream("-9.5 3.14 -0.61".getBytes());
    System.setIn(in);
    QuadForm.main(new String[]{});
    System.setIn(System.in);
    System.setOut(System.out);
  
    String output =
      "a*x^2 + b*x + c = 0 ROOT FINDER"+newline+
      "Input a: Input b: Input c: Discriminant < 0: the roots are imaginary"+newline+
      "";
    assertEquals(output, localOut.toString());
  }
  @Test (timeout=1000) public void QuadForm_main2(){
    ByteArrayOutputStream localOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(localOut));
    ByteArrayInputStream in = new ByteArrayInputStream("3.14 -9.5 -0.61".getBytes());
    System.setIn(in);
    QuadForm.main(new String[]{});
    System.setIn(System.in);
    System.setOut(System.out);
  
    String output =
      "a*x^2 + b*x + c = 0 ROOT FINDER"+newline+
      "Input a: Input b: Input c: Roots at 3.0883804227763796 and -0.0629027157700102"+newline+
      "Formula value for root1: -5.551115123125783E-16"+newline+
      "Formula value for root1: -8.881784197001252E-16"+newline+
      "";
    assertEquals(output, localOut.toString());
  }

}
