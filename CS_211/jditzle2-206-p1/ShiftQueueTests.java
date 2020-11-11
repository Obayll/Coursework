// Example of using unit tests for programming assignment 1.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar ShiftQueueTests.java   #compile
// $> java -cp .:junit-cs211.jar ShiftQueueTests.java    #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar ShiftQueueTests.java   #compile
// $> java -cp .;junit-cs211.jar ShiftQueueTests.java    #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ShiftQueueTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("ShiftQueueTests");
  }
  
  @Test(timeout=1000) public void ShiftQueue_makeQueue_1(){
    int [] expect = {-1, -1};
    int [] actual = ShiftQueue.makeQueue(2);
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_makeQueue_2(){
    int [] expect = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    int [] actual = ShiftQueue.makeQueue(11);
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_makeQueue_empty(){
    int [] expect = {};
    int [] actual = ShiftQueue.makeQueue(0);
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_makeQueue_null(){
    int [] expect = null;
    int [] actual = ShiftQueue.makeQueue(-2);
    assertEquals(expect,actual);
  }
  
  @Test(timeout=1000) public void ShiftQueue_addToEndOfQueue_1(){
    int [] expect = {5, -1};
    int [] actual = ShiftQueue.makeQueue(2);
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 5));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_addToEndOfQueue_2(){
    int [] expect = {5, 7};
    int [] actual = ShiftQueue.makeQueue(2);
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 5));
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 7));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_addToEndOfQueue_3(){
    int [] expect = {5, -7};
    int [] actual = ShiftQueue.makeQueue(2);
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 5));
    assertTrue(ShiftQueue.addToEndOfQueue(actual, -7));
    assertFalse(ShiftQueue.addToEndOfQueue(actual,1));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_addToEndOfQueue_4(){
    int [] expect = {1,4,7,-1,-1};
    int [] actual = ShiftQueue.makeQueue(5);
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 1));
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 4));
    assertTrue(ShiftQueue.addToEndOfQueue(actual,7));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_addToEndOfQueue_5(){
    int [] expect = {1,4,7,2,3};
    int [] actual = ShiftQueue.makeQueue(5);
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 1));
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 4));
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 7));
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 2));
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 3));
    assertFalse(ShiftQueue.addToEndOfQueue(actual, 2));
    assertFalse(ShiftQueue.addToEndOfQueue(actual, 12));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_addToEndOfQueue_negatives(){
    int [] expect = {-6, -2, -9, -4, -1};
    int [] actual = ShiftQueue.makeQueue(5);
    assertTrue(ShiftQueue.addToEndOfQueue(actual, -6));
    assertTrue(ShiftQueue.addToEndOfQueue(actual, -2));
    assertTrue(ShiftQueue.addToEndOfQueue(actual, -9));
    assertTrue(ShiftQueue.addToEndOfQueue(actual, -4));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_addToEndOfQueue_len0(){
    int [] expect = {};
    int [] actual = ShiftQueue.makeQueue(0);
    assertFalse(ShiftQueue.addToEndOfQueue(actual, 1));
    assertFalse(ShiftQueue.addToEndOfQueue(actual, 4));
    assertFalse(ShiftQueue.addToEndOfQueue(actual,7));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_addToEndOfQueue_neg1(){
    int [] expect = {1,4,-1,-1};
    int [] actual = ShiftQueue.makeQueue(4);
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 1));
    assertTrue(ShiftQueue.addToEndOfQueue(actual, 4));
    assertFalse(ShiftQueue.addToEndOfQueue(actual,-1));
    assertFalse(ShiftQueue.addToEndOfQueue(actual,-1));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_removeFromFrontOfQueue_1(){
    int [] expect = {-1,-1};
    int [] actual = {1,-1};
    assertTrue(ShiftQueue.removeFromFrontOfQueue(actual));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_removeFromFrontOfQueue_2(){
    int [] expect = {4,-1};
    int [] actual = {1,4};
    assertTrue(ShiftQueue.removeFromFrontOfQueue(actual));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_removeFromFrontOfQueue_3(){
    int [] expect = {-1,-1,-1,-1,-1};
    int [] actual = {1,4,12,-1,-1};
    assertTrue(ShiftQueue.removeFromFrontOfQueue(actual));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(actual));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(actual));
    assertFalse(ShiftQueue.removeFromFrontOfQueue(actual));
    assertFalse(ShiftQueue.removeFromFrontOfQueue(actual));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_removeFromFrontOfQueue_4(){
    int [] expect = {13,-1,-1,-1,-1};
    int [] actual = {1,4,12,9,13};
    assertTrue(ShiftQueue.removeFromFrontOfQueue(actual));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(actual));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(actual));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(actual));
    assertArrayEquals(expect,actual);
  }
  @Test(timeout=1000) public void ShiftQueue_shiftQueueString_1(){
    int [] queue = {1,4,12,9,13};
    assertEquals("1 4 12 9 13 ",ShiftQueue.queueString(queue));
  }
  @Test(timeout=1000) public void ShiftQueue_shiftQueueString_2(){
    int [] queue = {1,-4,12,-1,-1};
    assertEquals("1 -4 12 ",ShiftQueue.queueString(queue));
  }
  @Test(timeout=1000) public void ShiftQueue_shiftQueueString_3(){
    int [] queue = {-1,-1,-1};
    assertEquals("",ShiftQueue.queueString(queue));
  }
  @Test(timeout=1000) public void ShiftQueue_shiftQueueString_4(){
    int [] queue = {};
    assertEquals("",ShiftQueue.queueString(queue));
  }
  @Test(timeout=1000) public void ShiftQueue_shiftQueueString_5(){
    int [] queue = {1,2,3,-4,5,10,-12,-14,-1,-1,-1,-1};
    assertEquals("1 2 3 -4 5 10 -12 -14 ",ShiftQueue.queueString(queue));
  }
  @Test(timeout=1000) public void ShiftQueue_shiftQueueString_6(){
    int [] queue = {21,-1,-1,-1,-1,-1,-1,-1};
    assertEquals("21 ",ShiftQueue.queueString(queue));
  }
  @Test(timeout=1000) public void ShiftQueue_stress_1(){
    int [] q1 = ShiftQueue.makeQueue(4);
    assertFalse(ShiftQueue.addToEndOfQueue(q1,-1));
    assertTrue(ShiftQueue.addToEndOfQueue(q1,5));
    assertTrue(ShiftQueue.addToEndOfQueue(q1,7));
    assertTrue(ShiftQueue.addToEndOfQueue(q1,2));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q1));
    assertEquals("7 2 ",ShiftQueue.queueString(q1));

    assertTrue(ShiftQueue.addToEndOfQueue(q1,21));
    assertTrue(ShiftQueue.addToEndOfQueue(q1,-17));
    assertFalse(ShiftQueue.addToEndOfQueue(q1, 42));
    assertEquals("7 2 21 -17 ",ShiftQueue.queueString(q1));
    
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q1));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q1));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q1));
    assertEquals("-17 ",ShiftQueue.queueString(q1));
    
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q1));
    assertFalse(ShiftQueue.removeFromFrontOfQueue(q1));
    assertFalse(ShiftQueue.removeFromFrontOfQueue(q1));
    assertEquals("",ShiftQueue.queueString(q1));
  }

  @Test(timeout=1000) public void ShiftQueue_stress_2(){
    int [] q2 = ShiftQueue.makeQueue(7);
    assertFalse(ShiftQueue.addToEndOfQueue(q2,-1));
    assertTrue(ShiftQueue.addToEndOfQueue(q2,5));
    assertTrue(ShiftQueue.addToEndOfQueue(q2,8));
    assertTrue(ShiftQueue.addToEndOfQueue(q2,2));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q2));
    assertEquals("8 2 ",ShiftQueue.queueString(q2));

    assertTrue(ShiftQueue.addToEndOfQueue(q2,21));
    assertTrue(ShiftQueue.addToEndOfQueue(q2,-6));
    assertTrue(ShiftQueue.addToEndOfQueue(q2, 42));
    assertEquals("8 2 21 -6 42 ",ShiftQueue.queueString(q2));
    
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q2));
    assertTrue(ShiftQueue.addToEndOfQueue(q2, 31));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q2));
    assertEquals("21 -6 42 31 ",ShiftQueue.queueString(q2));
    
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q2));
    assertEquals("-6 42 31 ",ShiftQueue.queueString(q2));
  }

  @Test(timeout=1000) public void ShiftQueue_stress_3(){
    int [] q1 = ShiftQueue.makeQueue(4);
    int [] q2 = ShiftQueue.makeQueue(7);

    assertFalse(ShiftQueue.addToEndOfQueue(q1,-1));
    assertTrue(ShiftQueue.addToEndOfQueue(q1,5));

    assertFalse(ShiftQueue.addToEndOfQueue(q2,-1));

    assertTrue(ShiftQueue.addToEndOfQueue(q1,7));
    assertTrue(ShiftQueue.addToEndOfQueue(q1,2));

    assertTrue(ShiftQueue.addToEndOfQueue(q2,5));
    assertTrue(ShiftQueue.addToEndOfQueue(q2,8));

    assertTrue(ShiftQueue.removeFromFrontOfQueue(q1));
    assertEquals("7 2 ",ShiftQueue.queueString(q1));

    assertTrue(ShiftQueue.addToEndOfQueue(q2,2));
    assertTrue(ShiftQueue.addToEndOfQueue(q2,21));

    assertTrue(ShiftQueue.removeFromFrontOfQueue(q2));
    assertEquals("8 2 21 ",ShiftQueue.queueString(q2));

    assertTrue(ShiftQueue.addToEndOfQueue(q2,-6));
    assertTrue(ShiftQueue.addToEndOfQueue(q2, 42));
    assertEquals("8 2 21 -6 42 ",ShiftQueue.queueString(q2));

    assertTrue(ShiftQueue.addToEndOfQueue(q1,21));
    assertTrue(ShiftQueue.addToEndOfQueue(q1,-17));

    assertTrue(ShiftQueue.removeFromFrontOfQueue(q2));
    assertTrue(ShiftQueue.addToEndOfQueue(q2, 31));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q2));
    assertEquals("21 -6 42 31 ",ShiftQueue.queueString(q2));

    assertFalse(ShiftQueue.addToEndOfQueue(q1, 42));
    assertEquals("7 2 21 -17 ",ShiftQueue.queueString(q1));
    
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q1));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q1));
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q1));
    assertEquals("-17 ",ShiftQueue.queueString(q1));
    
    assertTrue(ShiftQueue.removeFromFrontOfQueue(q2));
    assertEquals("-6 42 31 ",ShiftQueue.queueString(q2));

    assertTrue(ShiftQueue.removeFromFrontOfQueue(q1));
    assertFalse(ShiftQueue.removeFromFrontOfQueue(q1));
    assertFalse(ShiftQueue.removeFromFrontOfQueue(q1));
    assertEquals("",ShiftQueue.queueString(q1));
  }

}
