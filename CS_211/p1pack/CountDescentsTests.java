// Example of using unit tests for programming assignment 1.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-4.12.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar CountDescentsTests.java  #compile
// $> java -cp .:junit-cs211.jar CountDescentsTests.java   #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar CountDescentsTests.java  #compile
// $> java -cp .;junit-cs211.jar CountDescentsTests.java   #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class CountDescentsTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("CountDescentsTests");
  }
  
  @Test (timeout=1000) public void test_countDescents_1(){
    assertEquals(0, CountDescents.countDescents(new int[]{}));
  }
  @Test (timeout=1000) public void test_countDescents_2(){
    assertEquals(3, CountDescents.countDescents(new int[]{3,2,1,5,4,3,2,1,8,7,6}));
  }
  @Test (timeout=1000) public void test_countDescents_3(){
    assertEquals(1, CountDescents.countDescents(new int[]{10,8,3,2}));
  }
  @Test (timeout=1000) public void test_countDescents_4(){
    assertEquals(2, CountDescents.countDescents(new int[]{5,4,4,3,3,2,1,8,7,6,5,4}));
  }
  @Test (timeout=1000) public void test_countDescents_5(){
    assertEquals(2, CountDescents.countDescents(new int[]{3,2,1,4}));
  }
  @Test (timeout=1000) public void test_countDescents_6(){
    assertEquals(5, CountDescents.countDescents(new int[]{1,2,3,4,5}));
  }
  @Test (timeout=1000) public void test_countDescents_7(){
    assertEquals(6, CountDescents.countDescents(new int[]{3,2,1,4,3,2,5,4,3,4,5,6}));
  }
  @Test (timeout=1000) public void test_countDescents_8(){
    assertEquals(1, CountDescents.countDescents(new int[]{3,3,3}));
  }
  @Test (timeout=1000) public void test_countDescents_9(){
    assertEquals(1, CountDescents.countDescents(new int[]{6,6,6,5,4}));
  }
  @Test (timeout=1000) public void test_countDescents_10(){
    assertEquals(2, CountDescents.countDescents(new int[]{7,6,6,4,2,2,3,1,1,1,-5,-8}));
  }
  
}
