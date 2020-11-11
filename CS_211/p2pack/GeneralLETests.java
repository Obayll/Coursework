import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneralLETests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("GeneralLETests");
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

  double abc[][] = {
    {1,1,1},
    {-1,-1,-1},
    { -4.3810360,  1.31013905,  -4.2555079},
    {  4.6521795, -2.96686450,  -1.5034664},
    { -5.5530020, -0.07466137, -10.5151204},
    { -2.0316994, -3.56582247,   0.7437177},
    { -3.6281803,  0.88159491,  -3.6308654},
    { -3.1358276,  4.10381492,  -1.8107256},
    { -0.9577728,  3.19738233,   1.2587501},
    { -0.5418069,  3.29817934,   2.9059544},
    {  0.9078392,  0.86891798,   6.9180241},
    { -2.1004032,  2.44431050,   1.0439996},
    { -124.92516, -130.86220,  -52.52619},
    {   35.27666,   60.17681,   15.86421},
    {   12.26413,  -47.39753,  -60.51635},
    {  -41.13847,  121.76543, -173.14434},
    { -209.19907,   79.51877,   76.03148},
    {  -38.92228,  -42.72746,   72.24226},
    {   99.72863, -179.62653,   79.09085},
  };
  
  // Repeatedly call the constructor on various data
  @Test(timeout=1000) public void sample_tests_GeneralLE_constructor(){
    for(int i=0; i<abc.length; i++){
      for(int j=0; j<x_y.length; j++){
        double a=abc[i][0], b=abc[i][1], c=abc[i][2];
        double x=x_y[j][0];
        double y = (c - a*x)/b;
        GeneralLE e = new GeneralLE(a,b,c,x);
        String msg;
        msg = String.format("i %d j %d: a %.2f b %.2f c %.2f x %.2f",i,j,a,b,c,x);
        assertEquals(msg,x,e.getX(),tol);
        assertEquals(msg,y,e.getY(),tol);
        assertEquals(msg,c,e.value(),tol);
      }
    }
  }
  
  // Test whether changing x alters y and vice versa; run repeatedly
  // on all the data in the data arrays above. Uses the first constructor.
  @Test(timeout=1000) public void sample_tests_GeneralLE_changing(){
    for(int i=0; i<abc.length; i++){
      for(int j=0; j<x_y.length; j++){
        double a=abc[i][0], b=abc[i][1], c=abc[i][2];
        double x=x_y[j][0];
        GeneralLE e = new GeneralLE(a,b,c,x);
        String msg;
        double x2 = x_y[j][1];
        e.setX(x2);
        msg = String.format("i %d j %d: a %.2f b %.2f c %.2f x %.2f",i,j,a,b,c,x2);
        assertEquals(msg,x2,e.getX(),tol);
        double y2 = (c - a*x2)/b;
        assertEquals(msg,y2,e.getY(),tol);
        assertEquals(msg,c,e.value(),tol);
        double y3 = x_y[1][0];
        e.setY(y3);
        msg = String.format("i %d j %d: a %.2f b %.2f c %.2f y %.2f",i,j,a,b,c,y3);
        assertEquals(msg,y3,e.getY(),tol);
        assertEquals(msg,c,e.value(),tol);
        double x3 = (c - b*y3)/a;
        assertEquals(msg,x3,e.getX(),tol);
      }
    }
  }
  
  // Data type for toString() tests
  class GFS {
    public final double a,b,c;
    public final String s;
    public GFS(double a, double b, double c, String s){
      this.a=a; this.b=b; this.c=c; this.s=s;
    }
  }

  // Data for toString() tests
  GFS gfs_tests[] = {
    new GFS(1,1,1,"1.00 * x + 1.00 * y = 1.00"),
    new GFS(-1,-1,-1,"-1.00 * x + -1.00 * y = -1.00"),
    new GFS( 1.00, 0.00, 1.00, "1.00 * x + 0.00 * y = 1.00"),
    new GFS(-1.00, 0.00, -1.44, "-1.00 * x + 0.00 * y = -1.44"),
    new GFS( 1.2345, -4.21, 15.82, "1.23 * x + -4.21 * y = 15.82"),
    new GFS(-1.2345, -4.21, 1.987, "-1.23 * x + -4.21 * y = 1.99"),
    new GFS(-1.999, 4.266, -0.129, "-2.00 * x + 4.27 * y = -0.13"),
    new GFS(225.765, -14.1111, 0.66666, "225.77 * x + -14.11 * y = 0.67"),
    new GFS(-124.92516, -130.86220,  -52.52619, "-124.93 * x + -130.86 * y = -52.53"),
    new GFS( 35.27666,   60.17681,   15.86421, "35.28 * x + 60.18 * y = 15.86"),
    new GFS(12.26413,  -47.39753,  -60.51635, "12.26 * x + -47.40 * y = -60.52"),
    new GFS(-41.13847,  121.76543, -173.14434, "-41.14 * x + 121.77 * y = -173.14"),
    new GFS(-209.19907, 79.51877,   76.03148, "-209.20 * x + 79.52 * y = 76.03"),
    new GFS(-38.92228, -42.72746, 72.24226, "-38.92 * x + -42.73 * y = 72.24"),
    new GFS(99.72863, -179.62653,   79.09085, "99.73 * x + -179.63 * y = 79.09"),
  };

  // Test toString() on the inputs above
  @Test(timeout=1000) public void sample_tests_GeneralLE_toString(){
    for(int i=0; i<gfs_tests.length; i++){
      GFS gfs = gfs_tests[i];
      GeneralLE e;
      e = new GeneralLE(gfs.a,gfs.b,gfs.c,i);
      assertEquals(gfs.s,e.toString());
      e.setX(4.0);
      assertEquals(gfs.s,e.toString());
      e.setY(-52.1);
      assertEquals(gfs.s,e.toString());
    }      
  }
  
  // Test whether conversion to SlopeInterceptLE objects works
  // correctly
  @Test(timeout=1000) public void conversion(){
    for(int i=0; i<abc.length; i++){
      for(int j=0; j<x_y.length; j++){
        double a=abc[i][0], b=abc[i][1], c=abc[i][2];
        double x=x_y[j][0];
        double y = (c - a*x)/b;
        GeneralLE ge = new GeneralLE(a,b,c,x);
	double v = ge.value();
	SlopeInterceptLE si = ge.toSlopeInterceptLE();
	assertEquals(v,ge.value(),tol);
	assertEquals(x,si.getX(),tol);
	assertEquals(y,si.getY(),tol);
	assertEquals(y,si.value(),tol);
	si.setX(4.2);
	assertEquals(4.2,si.getX(),tol);
	assertEquals(x,ge.getX(),tol);
      }
    }
  }

}
