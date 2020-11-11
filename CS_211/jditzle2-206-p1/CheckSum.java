import java.util.Arrays;
public class CheckSum{
  // Compute Fletcher's checksum for the integers given. Refer to the
  // project specification for pseudocode of how to compute this.
  // Return the 64-bit checksum as an array of 2 integers.
  public static int [] computeCheckSum(int [] data){
    int sum1 = 0;
    int sum2 = 0;
    
    for(int i = 0;i < data.length;i++) {
      sum1 += data[i];
      sum2 += sum1;
    }
    
    int[] result = {sum1,sum2};
    
    return result;
  }
  // Convenience method. Convert the standard string message to an
  // array of integers then call the other version of computeCheckSum
  // on it. The provided class RunCheckSum class contains a useful
  // method for converting strings to arrays of integers.
  public static int [] computeCheckSum(String message){
    return computeCheckSum(RunCheckSum.stringToInts(message));
  }
  // Verify that the checksum computed from the array data matches the
  // given expected checksum. Argument expected is an array of two
  // integers which is the 64-bit checksum which is expected. If the
  // computed checksum matches the expected, return true. Otherwise
  // return false.
  public static boolean verifyCheckSum(int [] data, int [] expected){
    return Arrays.equals(computeCheckSum(data),expected);
  }
  // Convenience method. Convert the standard string message to an
  // array of ints. Convert the string expectedHex to an array of
  // integers as well but note that the format of this string is
  // different: it represents hexadecimal numbers and must be
  // converted with a different function from the RunCheckSum class.
  // Return true if the checksum computed from data matches the
  // expected; use the other version of verifyCheckSum for this.
  public static boolean verifyCheckSum(String message, String expectedHex){
    return Arrays.equals(computeCheckSum(message),RunCheckSum.hexStringToInts(expectedHex));
  }
}  