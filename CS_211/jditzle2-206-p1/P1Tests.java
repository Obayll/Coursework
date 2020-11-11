// Use this file to run all tests for the project at once
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class P1Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("QuadFormTests",
                                    "CountDescentsTests",
                                    "CheckSumTests",
                                    "ShiftQueueTests");
  }
}
