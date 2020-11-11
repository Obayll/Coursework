import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class SlopeInterceptLETests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("SlopeInterceptLETests");
  }

  static final double tol = 1e-6; // tolerance for floating point comparisons

  // These arrays are data for tests
  double x_y[][] = {
    {1.0, 0.0},
    {-1.0, 1.0},
    {0.256, -1.234},
    {12.098, 4.32},
    {0.25, 0.5},
    {-10.1, 91.4},
    {0.562, +1.234},
    {0.098, -43.2},
  };
  
  double slope_intercept[][] = {
    {1.0, 0.0},
    {-1.0, 1.0},
    {0.256, -1.234},
    {12.098, 4.32},
    {8.2, 0.01},
    {-21.7, 1.5},
    {7.256, -1.234},
    {812.098, 6.23}
  };

  // Repeatedly call the constructor on various data
  @Test(timeout=1000) public void sample_tests_SlopeInterceptLE_constructor1(){
    for(int i=0; i<slope_intercept.length; i++){
      double m=slope_intercept[i][0], b=slope_intercept[i][1];
      SlopeInterceptLE e = new SlopeInterceptLE(m,b);
      String msg;
      msg = String.format("Iter %d: slope %.2f intercept %.2f",i,m,b);
      assertEquals(msg,0.0,e.getX(),tol);
      assertEquals(msg,b,e.getY(),tol);
      assertEquals(msg,b,e.value(),tol);
    }
  }

  @Test(timeout=1000) public void sample_tests_SlopeInterceptLE_constructor2(){
    for(int i=0; i<slope_intercept.length; i++){
      for(int j=0; j<x_y.length; j++){
        double m=slope_intercept[i][0], b=slope_intercept[i][1];
        double x=x_y[j][0];
        SlopeInterceptLE e = new SlopeInterceptLE(m,b,x);
        String msg;
        msg = String.format("i %d j %d: slope %.2f intercept %.2f x %.2f",i,j,m,b,x);
        assertEquals(msg,x,e.getX(),tol);
        double val = m*x+b;
        assertEquals(msg,val,e.getY(),tol);
        assertEquals(msg,val,e.value(),tol);
      }
    }
  }

  // Test whether changing x alters y and vice versa; run repeatedly
  // on all the data in the data arrays above. Uses the first constructor.
  @Test(timeout=1000) public void sample_tests_SlopeInterceptLE_changing(){
    for(int i=0; i<slope_intercept.length; i++){
      for(int j=0; j<x_y.length; j++){
        double m=slope_intercept[i][0], b=slope_intercept[i][1];
        SlopeInterceptLE e = new SlopeInterceptLE(m,b);
        String msg;
        double x2 = x_y[j][1];
        e.setX(x2);
        msg = String.format("i %d j %d: slope %.2f intercept %.2f x %.2f",i,j,m,b,x2);
        assertEquals(msg,x2,e.getX(),tol);
        double val = m*x2+b;
        assertEquals(msg,val,e.getY(),tol);
        assertEquals(msg,val,e.value(),tol);
        double y = x_y[1][0];
        e.setY(y);
        msg = String.format("i %d j %d: slope %.2f intercept %.2f y %.2f",i,j,m,b,y);
        assertEquals(msg,y,e.getY(),tol);
        assertEquals(msg,y,e.value(),tol);
        double x = (y-b) / m;
        assertEquals(msg,x,e.getX(),tol);
      }
    }
  }

  // Test whether changing x alters y and vice versa; run repeatedly
  // on all the data in the data arrays above. Uses the second
  // constructor.
  @Test(timeout=1000) public void sample_tests_SlopeInterceptLE_changing2(){
    for(int i=0; i<slope_intercept.length; i++){
      for(int j=0; j<x_y.length; j++){
        double m=slope_intercept[i][0], b=slope_intercept[i][1];
        double x=x_y[j][0];
        SlopeInterceptLE e = new SlopeInterceptLE(m,b,x);
        String msg;
        double x2 = x_y[j][1];
        e.setX(x2);
        msg = String.format("i %d j %d: slope %.2f intercept %.2f x %.2f",i,j,m,b,x2);
        assertEquals(msg,x2,e.getX(),tol);
        double val = m*x2+b;
        assertEquals(msg,val,e.getY(),tol);
        assertEquals(msg,val,e.value(),tol);
        double y = x_y[1][0];
        e.setY(y);
        msg = String.format("i %d j %d: slope %.2f intercept %.2f y %.2f",i,j,m,b,y);
        assertEquals(msg,y,e.getY(),tol);
        assertEquals(msg,y,e.value(),tol);
        x = (y-b) / m;
        assertEquals(msg,x,e.getX(),tol);
      }
    }
  }

  // Data type for toString() tests
  class SIS {
    public final double m, b;
    public final String s;
    public SIS(double m, double b, String s){
      this.m=m; this.b=b; this.s=s;
    }
  }

  // Data for checking toString()
  SIS sis_tests[] = {
    new SIS( 1.00, 0.00, "y = 1.00 * x + 0.00"),
    new SIS(-1.00, 0.00, "y = -1.00 * x + 0.00"),
    new SIS( 1.2345, -4.21, "y = 1.23 * x + -4.21"),
    new SIS(-1.2345, -4.21, "y = -1.23 * x + -4.21"),
    new SIS(-1.999, 4.266, "y = -2.00 * x + 4.27"),
    new SIS(225.765, -14.1111, "y = 225.77 * x + -14.11"),
    new SIS( 60.17681 ,   15.86421, "y = 60.18 * x + 15.86"),
    new SIS(-124.92516, -130.86220, "y = -124.93 * x + -130.86"),
    new SIS( 99.72863,  -179.62653, "y = 99.73 * x + -179.63"),
    new SIS(-209.19907,   79.09085, "y = -209.20 * x + 79.09"),
    new SIS(-1, 1, "y = -1.00 * x + 1.00"),
    new SIS(-47.39753,  -60.51635, "y = -47.40 * x + -60.52"),
  };    
  
  // Run tests on the toString() data above
  @Test(timeout=1000) public void sample_tests_SlopeInterceptLE_toString(){
    for(int i=0; i<sis_tests.length; i++){
      SIS sis = sis_tests[i];
      SlopeInterceptLE e;
      e = new SlopeInterceptLE(sis.m,sis.b);
      assertEquals(sis.s,e.toString());
      e.setX(4.0);
      assertEquals(sis.s,e.toString());
      e.setY(-52.1);
      assertEquals(sis.s,e.toString());
      
      e = new SlopeInterceptLE(sis.m,sis.b,i);
      assertEquals(sis.s,e.toString());
      e.setX(4.0);
      assertEquals(sis.s,e.toString());
      e.setY(-52.1);
      assertEquals(sis.s,e.toString());
    }      
  }

}
