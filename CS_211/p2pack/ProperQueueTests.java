// Example of using unit tests for programming assignment 2.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the same project directory.
// 
// $> javac -cp .:junit-cs211.jar ProperQueueTests.java   #compile
// $> java -cp .:junit-cs211.jar ProperQueueTests         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar ProperQueueTests.java   #compile
// $> java -cp .;junit-cs211.jar ProperQueueTests         #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ProperQueueTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("ProperQueueTests");
  }
  
  /*
   building queues:
   - build small empty queue.   (2)
   - build larger empty queue. (11)
   - build length-zero queue.   (0)
   
   */  
  @Test(timeout=1000) public void ProperQueue_makeQueue_1(){
    String expected = "";
    ProperQueue q = new ProperQueue(2);
    String actual = q.toString();
    assertEquals(2, q.getCapacity());
    assertEquals(expected, actual);
  }
  @Test(timeout=1000) public void ProperQueue_makeQueue_2(){
    String expected = "";
    ProperQueue q = new ProperQueue(11);
    String actual = q.toString();
    assertEquals(11, q.getCapacity());
    assertEquals(expected, actual);
  }
  @Test(timeout=1000) public void Queue_makeQueue_3(){
    String expected = "";
    ProperQueue q = new ProperQueue(0);
    String actual = q.toString();
    assertEquals(0, q.getCapacity());
    assertEquals(expected, actual);
  }
  /*
   add/offer tests.
   - add a single value to a short queue.
   - fill up a small queue.
   - over-add to a queue and witness it struggle.
   - add many but don't finish filling a queue.
   - make size-zero queue, adds fail, check it's still empty.
   */
  @Test(timeout=1000) public void ProperQueue_add_1(){
    String expected = "5";
    ProperQueue q = new ProperQueue(3);
    boolean ans = q.add(5);
    String actual = q.toString();
    assertTrue(ans);
    assertEquals(1, q.getSize());
    assertEquals(expected, actual);
  }
  @Test(timeout=1000) public void ProperQueue_add_2(){
    ProperQueue q = new ProperQueue(3);
    
    boolean ans;
    ans = q.add(5);
    assertTrue(ans);
    
    ans = q.add(12);
    assertTrue(ans);
    
    ans = q.add(99);
    assertTrue(ans);
    
    String expected = "5 12 99";
    String actual = q.toString();
    assertEquals(3, q.getSize());
    assertEquals(expected, actual);
  }
  
  
  @Test(timeout=1000) public void ProperQueue_add_3(){
    
    ProperQueue q = new ProperQueue(3);
    
    boolean ans;
    
    ans = q.add(5);
    assertTrue(ans);
    
    q.add(12);
    assertTrue(ans);
    
    q.add(99);
    assertTrue(ans);
    
    // not enough room for this one!
    try {
      q.add(33);
      fail("should have raised an exception.");
    } catch (RuntimeException e){ 
      assertEquals("Queue full",e.getMessage());
    }
    
    assertEquals(3, q.getSize());
    assertEquals("5 12 99", q.toString());
  }
  
  
  @Test(timeout=1000) public void ProperQueue_add_4(){
    
    ProperQueue q = new ProperQueue(0);
    
    // not enough room for this one!
    try {
      q.add(5);
      fail("should have raised an exception.");
    } catch (RuntimeException e){
      assertEquals("Queue full",e.getMessage());
    }
    
    assertEquals(0, q.getSize());
    assertEquals(0, q.getCapacity());
    assertEquals("", q.toString());
  }
  
  
  @Test(timeout=1000) public void ProperQueue_offer_1(){
    String expected = "5";
    ProperQueue q = new ProperQueue(3);
    q.offer(5);
    String actual = q.toString();
    assertEquals(1, q.getSize());
    assertEquals(expected, actual);
  }
  @Test(timeout=1000) public void ProperQueue_offer_2(){
    String expected = "5 12 99";
    ProperQueue q = new ProperQueue(3);
    q.offer(5);
    q.offer(12);
    q.offer(99);
    String actual = q.toString();
    assertEquals(3, q.getSize());
    assertEquals(expected, actual);
  }
  
  
  @Test(timeout=1000) public void ProperQueue_offer_3(){
    
    ProperQueue q = new ProperQueue(3);
    
    boolean ans;
    ans = q.offer(5);
    assertTrue(ans);
    
    ans = q.offer(12);
    assertTrue(ans);
    
    ans = q.offer(99);
    assertTrue(ans);
    
    // not enough room for this one!
    ans = q.offer(33);
    assertFalse(ans);
    
    assertEquals(3, q.getSize());
    assertEquals("5 12 99", q.toString());
  }
  
  
  @Test(timeout=1000) public void ProperQueue_offer_4(){
    
    ProperQueue q = new ProperQueue(0);
    
    // not enough room for this one!
    boolean ans = q.offer(5);
    assertFalse(ans);
    
    assertEquals(0, q.getSize());
    assertEquals(0, q.getCapacity());
    assertEquals("", q.toString());
  }
  
  
  /*
   remove/poll tests.
   - remove the one elt, check queue is empty.
   - remove one of few elts, check rest are there and correctly placed.
   - remove all, check it's now empty.
   - try to remove too many, see it struggle.
   */
  @Test(timeout=1000) public void ProperQueue_remove_1(){
    ProperQueue q = new ProperQueue(3);
    Integer v1 = 17;
    q.add(v1);
    Integer ans = q.remove();
    assertEquals(v1,ans);
    assertTrue(q.isEmpty());
    assertFalse(q.isFull());
  }
  
  @Test(timeout=1000) public void ProperQueue_remove_2(){
    ProperQueue q = new ProperQueue(5);
    Integer v1 = 10, v2=12, v3=14, v4=16;
    q.add(v1);
    q.add(v2);
    q.add(v3);
    q.add(v4);
    
    Integer ans = q.remove();
    assertEquals(v1,ans);
    
    assertEquals("12 14 16", q.toString());
    assertFalse(q.isFull());
  }
  
  @Test(timeout=1000) public void ProperQueue_remove_3(){
    ProperQueue q = new ProperQueue(5);
    Integer v1 = 10, v2=12, v3=14, v4=16;
    q.add(v1);
    q.add(v2);
    q.add(v3);
    q.add(v4);
    
    Integer ans;
    ans = q.remove();
    assertEquals(v1,ans);
    
    ans = q.remove();
    assertEquals(v2,ans);
    
    ans = q.remove();
    assertEquals(v3,ans);
    
    ans = q.remove();
    assertEquals(v4,ans);
    
    
    assertEquals("", q.toString());
    assertTrue(q.isEmpty());
    assertFalse(q.isFull());
  }
  
  
  @Test(timeout=1000) public void ProperQueue_remove_4(){
    ProperQueue q = new ProperQueue(3);
    
    // nothing to remove!
    try {
      q.remove();
      fail ("should have raised exception: nothing to remove.");
    } catch (RuntimeException e) {
      assertEquals("Queue empty",e.getMessage());
    }
    
    assertEquals("", q.toString());
    assertTrue(q.isEmpty());
    assertFalse(q.isFull());
  }
  
  
  @Test(timeout=1000) public void ProperQueue_remove_5(){
    ProperQueue q = new ProperQueue(3);
    Integer v1 = 10, v2=12, v3=14;
    q.add(v1);
    q.add(v2);
    q.add(v3);
    
    Integer ans;
    ans = q.remove();
    assertEquals(v1,ans);
    
    ans = q.remove();
    assertEquals(v2,ans);
    
    ans = q.remove();
    assertEquals(v3,ans);
    
    // nothing left to remove!
    try {
      ans = q.remove();
      fail ("should have raised exception: nothing to remove.");
    } catch (RuntimeException e) { 
      assertEquals("Queue empty",e.getMessage());
    }
    
    assertEquals("", q.toString());
    assertTrue(q.isEmpty());
    assertFalse(q.isFull());
  }
  
  
  
  @Test(timeout=1000) public void ProperQueue_poll_1(){
    ProperQueue q = new ProperQueue(3);
    Integer v1 = 17;
    q.add(v1);
    
    Integer ans = q.poll();
    assertEquals(v1,ans);
    
    assertTrue(q.isEmpty());
    assertFalse(q.isFull());
  }
  
  @Test(timeout=1000) public void ProperQueue_poll_2(){
    ProperQueue q = new ProperQueue(5);
    Integer v1 = 10, v2=12, v3=14, v4=16;
    
    q.add(v1);
    q.add(v2);
    q.add(v3);
    q.add(v4);
    
    Integer ans = q.poll();
    assertEquals(v1,ans);
    
    assertEquals("12 14 16", q.toString());
    assertFalse(q.isFull());
  }
  
  @Test(timeout=1000) public void ProperQueue_poll_3(){
    ProperQueue q = new ProperQueue(5);
    Integer v1 = 10, v2=12, v3=14, v4=16;
    q.add(v1);
    q.add(v2);
    q.add(v3);
    q.add(v4);
    
    Integer ans;
    ans = q.poll();
    assertEquals(v1,ans);
    
    ans = q.poll();
    assertEquals(v2,ans);
    
    ans = q.poll();
    assertEquals(v3,ans);
    
    ans = q.poll();
    assertEquals(v4,ans);
    
    
    assertEquals("", q.toString());
    assertTrue(q.isEmpty());
    assertFalse(q.isFull());
  }
  
  
  @Test(timeout=1000) public void ProperQueue_poll_4(){
    ProperQueue q = new ProperQueue(3);
    
    // nothing to poll!
    Integer ans = q.poll();
    assertEquals(null, ans);
    
    assertEquals("", q.toString());
    assertTrue(q.isEmpty());
    assertFalse(q.isFull());
  }
  
  
  @Test(timeout=1000) public void ProperQueue_poll_5(){
    ProperQueue q = new ProperQueue(3);
    Integer v1 = 10, v2=12, v3=14;
    q.add(v1);
    q.add(v2);
    q.add(v3);
    
    Integer ans;
    ans = q.poll();
    assertEquals(v1,ans);
    
    ans = q.poll();
    assertEquals(v2,ans);
    
    ans = q.poll();
    assertEquals(v3,ans);
    
    // nothing left to poll!
    ans = q.poll();
    assertEquals(null,ans);
    
    assertEquals("", q.toString());
    assertTrue(q.isEmpty());
    assertFalse(q.isFull());
  }
  
  
  /*
   element/peek tests.
   - peek at empty queue, see it struggle.
   - peek at the only item; check it's still there.
   - peek at first of many; check they're all still there.
   - peek at full queue.
   */
  
  @Test(timeout=1000) public void ProperQueue_element_1(){
    ProperQueue q = new ProperQueue(50);
    
    // no items to peek at!
    try {
      Integer ans = q.element();
      fail ("should have thrown exception.");
    } catch (RuntimeException e) {
      assertEquals("Queue empty",e.getMessage());
    }
    
    assertEquals("", q.toString());
  }
  
  @Test(timeout=1000) public void ProperQueue_element_2(){
    ProperQueue q = new ProperQueue(50);
    q.add(51);
    
    Integer ans = q.element();
    assertEquals((Integer)51,ans);
    assertEquals("51",q.toString());
  }
  
  @Test(timeout=1000) public void ProperQueue_element_3(){
    ProperQueue q = new ProperQueue(50);
    q.add(51);
    q.add(-234);
    q.add(456);
    q.add(7979);
    
    Integer ans = q.element();
    assertEquals((Integer)51, ans);
    
    assertEquals("51 -234 456 7979",q.toString());
  }
  
  @Test(timeout=1000) public void ProperQueue_element4(){
    ProperQueue q = new ProperQueue(4);
    q.add(51);
    q.add(-234);
    q.add(456);
    q.add(7979);
    
    Integer ans = q.element();
    assertEquals((Integer)51,ans);
    
    // try it again...
    ans = q.element();
    assertEquals((Integer)51,ans);
    
    // try it again...
    ans = q.element();
    assertEquals((Integer)51,ans);
    
    assertEquals("51 -234 456 7979",q.toString());
  }
  
  
  @Test(timeout=1000) public void ProperQueue_peek_1(){
    ProperQueue q = new ProperQueue(50);
    
    // no items to peek at! We should get null.
    Integer ans = q.peek();
    assertEquals(null,ans);
    
    assertEquals("", q.toString());
  }
  
  @Test(timeout=1000) public void ProperQueue_peek_2(){
    ProperQueue q = new ProperQueue(50);
    q.add(51);
    
    Integer ans = q.peek();
    assertEquals((Integer)51,ans);
    assertEquals("51",q.toString());
  }
  
  @Test(timeout=1000) public void ProperQueue_peek_3(){
    ProperQueue q = new ProperQueue(50);
    q.add(51);
    q.add(-234);
    q.add(456);
    q.add(7979);
    
    Integer ans = q.peek();
    assertEquals((Integer)51, ans);
    
    assertEquals("51 -234 456 7979",q.toString());
  }
  
  @Test(timeout=1000) public void ProperQueue_peek4(){
    ProperQueue q = new ProperQueue(4);
    q.add(51);
    q.add(-234);
    q.add(456);
    q.add(7979);
    
    Integer ans = q.peek();
    assertEquals((Integer)51,ans);
    
    // try it again...
    ans = q.peek();
    assertEquals((Integer)51,ans);
    
    // try it again...
    ans = q.peek();
    assertEquals((Integer)51,ans);
    
    assertEquals("51 -234 456 7979",q.toString());
  }
  
}
