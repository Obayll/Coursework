// Example of using unit tests for programming assignment 1.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar CheckSumTests.java   #compile
// $> java -cp .:junit-cs211.jar CheckSumTests.java    #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar CheckSumTests.java   #compile
// $> java -cp .;junit-cs211.jar CheckSumTests.java    #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class CheckSumTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("CheckSumTests");
  }
  
  void test_computeCheckSum(int [] message, int [] expect){
    int [] actual = CheckSum.computeCheckSum(message);
    String msg = "Checksum for ints does not match\n";
    msg += String.format("message: %s\nexpect: %s\nactual: %s\n",
                         Arrays.toString(message),Arrays.toString(expect),Arrays.toString(actual));
    msg += String.format("expect hex: %08X%08X\nactual hex; %08X%08X\n",
                         expect[0],expect[1],actual[0],actual[1]);
    assertArrayEquals(msg, expect, actual);
  }    

  @Test (timeout=1000) public void CheckSum_computeCheckSum_1(){
    int [] message = RunCheckSum.stringToInts("You've been jammed.");
    int [] expect = {83861270, 51977861};
    test_computeCheckSum(message, expect);
  }
  @Test (timeout=1000) public void CheckSum_computeCheckSum_2(){
    int [] message = RunCheckSum.stringToInts("One of my most productive days was throwing away 1000 lines of code.");
    int [] expect = {-1842523139, 205580869};
    test_computeCheckSum(message, expect);
  }
  @Test (timeout=1000) public void CheckSum_computeCheckSum_3(){
    int [] message = RunCheckSum.stringToInts("When the Lisa team was pushing to finalize their software in 1982, project managers started requiring programmers to submit weekly forms reporting on the number of lines of code they had written. Bill Atkinson thought that was silly. For the week in which he had rewritten QuickDraw's region calculation routines to be six times faster and 2000 lines shorter, he put '-2000' on the form. After a few more weeks the managers stopped asking him to fill out the form, and he gladly complied.");
    int [] expect = {-1899950594, 1923369480};
    test_computeCheckSum(message, expect);
  }
  @Test (timeout=1000) public void CheckSum_computeCheckSum_4(){
    int [] message = RunCheckSum.stringToInts("The Analytical Engine has no pretensions whatever to originate anything. It can do whatever we know how to order it to perform. It can follow analysis; but it has no power of anticipating any analytical relations or truths. Its province is to assist us in making available what we are already acquainted with.");
    int [] expect = {-497615290, -488987077};
    test_computeCheckSum(message, expect);
  }

  @Test (timeout=1000) public void CheckSum_computeCheckSum_empty(){
    int [] message = RunCheckSum.stringToInts("");
    int [] expect = {0, 0};
    test_computeCheckSum(message, expect);
  }
  
  void test_computeCheckSum(String message, int [] expect){
    int [] actual = CheckSum.computeCheckSum(message);
    String msg = "Checksum for ints does not match\n";
    msg += String.format("message: %s\nexpect: %s\nactual: %s\n",
                         message,Arrays.toString(expect),Arrays.toString(actual));
    msg += String.format("expect hex: %08X%08X\nactual hex; %08X%08X\n",
                         expect[0],expect[1],actual[0],actual[1]);
    assertArrayEquals(msg, expect, actual);
  }    

  @Test (timeout=1000) public void CheckSum_computeCheckSum_string_1(){
    String message = "I was aiming for his head.";
    int [] expect = {-284195465, 1569093891};
    test_computeCheckSum(message, expect);
  }
  @Test (timeout=1000) public void CheckSum_computeCheckSum_string_2(){
    String message = "Action expresses priorities.";
    int [] expect = {-1778533455, 937975891};
    test_computeCheckSum(message, expect);
  }
  @Test (timeout=1000) public void CheckSum_computeCheckSum_string_3(){
    String message = "Cats are intended to teach us that not everything in nature has a purpose.";
    int [] expect = {-1634744286, 555081667};
    test_computeCheckSum(message, expect);
  }
  @Test (timeout=1000) public void CheckSum_computeCheckSum_string_4(){
    String message = "It is not a daily increase, but a daily decrease. Hack away at the inessentials.";
    int [] expect = {-471991660, 1502615145};
    test_computeCheckSum(message, expect);
  }
  @Test (timeout=1000) public void CheckSum_computeCheckSum_string_5(){
    String message = "If in physics there's something you don't understand, you can always hide behind the uncharted depths of nature. You can always blame God. You didn't make it so complex yourself. But if your program doesn't work, there is no one to hide behind. You cannot hide behind an obstinate nature. If it doesn't work, you've messed up.";
    int [] expect = {-40064390, -1081429916};
    test_computeCheckSum(message, expect);
  }

  @Test (timeout=1000) public void CheckSum_verifyCheckSum_1(){
    int [] message = RunCheckSum.stringToInts("You've been jammed.");
    int [] checksum = {83861270, 51977861};
    boolean expect = true;
    boolean actual = CheckSum.verifyCheckSum(message,checksum);
    assertEquals(expect, actual);
  }
  @Test (timeout=1000) public void CheckSum_verifyCheckSum_2(){
    int [] message = RunCheckSum.stringToInts("One of my most productive days was throwing away 1000 lines of code.");
    int [] checksum = {-1842523139, 205580869};
    boolean expect = true;
    boolean actual = CheckSum.verifyCheckSum(message,checksum);
    assertEquals(expect, actual);
  }
  @Test (timeout=1000) public void CheckSum_verifyCheckSum_3(){
    int [] message = RunCheckSum.stringToInts("When the Lisa team was pushing to finalize their software in 1982, project managers started requiring programmers to submit weekly forms reporting on the number of lines of code they had written. Bill Atkinson thought that was silly. For the week in which he had rewritten QuickDraw's region calculation routines to be six times faster and 2000 lines shorter, he put '-2000' on the form. After a few more weeks the managers stopped asking him to fill out the form, and he gladly complied.");
    int [] checksum = {-1899950594, 1923369480};
    boolean expect = true;
    boolean actual = CheckSum.verifyCheckSum(message,checksum);
    assertEquals(expect, actual);
  }
  @Test (timeout=1000) public void CheckSum_verifyCheckSum_4(){
    int [] message = RunCheckSum.stringToInts("The Analytical Engine has no pretensions whatever to originate anything. It can do whatever we know how to order it to perform. It can follow analysis; but it has no power of anticipating any analytical relations or truths. Its province is to assist us in making available what we are already acquainted with.");
    int [] checksum = {-497615290, -123};
    boolean expect = false;
    boolean actual = CheckSum.verifyCheckSum(message,checksum);
    assertEquals(expect, actual);
  }
  @Test (timeout=1000) public void CheckSum_verifyCheckSum_5(){
    int [] message = RunCheckSum.stringToInts("You've been jammed.");
    int [] checksum = {51977861, 83861270};
    boolean expect = false;
    boolean actual = CheckSum.verifyCheckSum(message,checksum);
    assertEquals(expect, actual);
  }


  @Test (timeout=1000) public void CheckSum_verifyCheckSum_string_1(){
    String message = "You've been jammed.";
    String checksum = "04FF9F1603191E85";
    boolean expect = true;
    boolean actual = CheckSum.verifyCheckSum(message,checksum);
    assertEquals(expect, actual);
  }
  @Test (timeout=1000) public void CheckSum_verifyCheckSum_string_2(){
    String message = "One of my most productive days was throwing away 1000 lines of code.";
    String checksum = "922D53FD0C40EA45";
    boolean expect = true;
    boolean actual = CheckSum.verifyCheckSum(message,checksum);
    assertEquals(expect, actual);
  }
  @Test (timeout=1000) public void CheckSum_verifyCheckSum_string_3(){
    String message = "When the Lisa team was pushing to finalize their software in 1982, project managers started requiring programmers to submit weekly forms reporting on the number of lines of code they had written. Bill Atkinson thought that was silly. For the week in which he had rewritten QuickDraw's region calculation routines to be six times faster and 2000 lines shorter, he put '-2000' on the form. After a few more weeks the managers stopped asking him to fill out the form, and he gladly complied.";
    String checksum = "1010101010101010";
    boolean expect = false;
    boolean actual = CheckSum.verifyCheckSum(message,checksum);
    assertEquals(expect, actual);
  }
  @Test (timeout=1000) public void CheckSum_verifyCheckSum_string_4(){
    String message = "The Analytical Engine has no pretensions whatever to originate anything. It can do whatever we know how to order it to perform. It can follow analysis; but it has no power of anticipating any analytical relations or truths. Its province is to assist us in making available what we are already acquainted with.";
    String checksum = "E256FE46E2DAA63B";
    boolean expect = true;
    boolean actual = CheckSum.verifyCheckSum(message,checksum);
    assertEquals(expect, actual);
  }
  @Test (timeout=1000) public void CheckSum_verifyCheckSum_string_5(){
    String message = "You've been jammed.";
    String checksum = "AAAAAAAAAAAAAAAA";
    boolean expect = false;
    boolean actual = CheckSum.verifyCheckSum(message,checksum);
    assertEquals(expect, actual);
  }
  
}
