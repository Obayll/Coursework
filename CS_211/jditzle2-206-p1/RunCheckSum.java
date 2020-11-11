// This file should not be modified.  It contains methods to help test
// the Fletcher CheckSum methods.  These are low-level conversion
// methods to interchange between string/int-array formats.  There is
// also a main method to compute checksums for messages. You don't
// need to understand everything here but it is good practice to start
// reading other people's insane code.
public class RunCheckSum{

  // Convert a standard string of text to an array of integers
  public static int[] stringToInts(String text) {
    byte [] bytes;
    try{
      bytes = text.getBytes("UTF-8");
    }
    catch(Exception e){
      throw new RuntimeException("Problems converting "+text); // Punt on problems
    } 
    int intLen = bytes.length/4 + (bytes.length%4==0 ? 0 : 1);
    int [] ints = new int[intLen];
    int idx=0,bdx=0;
    for(idx=0; idx<ints.length; idx++){
      int code = 0;
      for(int j=0;j<4 && bdx<bytes.length; j++,bdx++){
        byte b = bytes[bdx];                // Shift 8-bit byte over to
        code += ((int) b) << ((4-1-j)*8);   // correct location in 32-bit int
      }
      ints[idx] = code;
    }
    return ints;
  }


        
  // Converts a string representing hexadecimal numbers to its
  // equivalent int array representation.
  public static int [] hexStringToInts(String s) {
    if(s.length() % 8 != 0){
      throw new RuntimeException("Badly formatted hex string: "+s);
    }
    int [] data = new int[s.length() / 8];
    for (int i = 0; i < data.length; i++){
      String digits = s.substring(i*8,(i+1)*8);      // Extract an 8-char hex
      data[i] = Integer.parseUnsignedInt(digits,16); // Convert it to an int
    }
    return data;
  }

  // Compute the checksum of a string given on the command line or
  // verify/refute a checksum matches a message
  public static void main(String args[]) throws Exception {
    if(args.length < 1){
      System.out.println("usage: java RunCheckSum 'a message here'");
      System.out.println("       java RunCheckSum 'a message here' expected");
      System.out.println();
      System.out.println("Computes the checksum for the given message and prints it on the screen.");
      System.out.println("If a second argument is given, it is interpretted as a the hexadecimal");
      System.out.println("representation of the expected checksum and compared the actual");
      System.out.println("checksum.");
      return;
    }

    String message = args[0];   // The message is the first command line argument
    // int [] data = stringToInts(message);
    int [] actualCheckSum = CheckSum.computeCheckSum(message); // CALL STUDENT CODE

    // If no expected checksum is given, just spit out the checksum and return
    if(args.length==1){
      System.out.printf("Checksum: %08X%08X\n",actualCheckSum[0],actualCheckSum[1]);
      return;
    }

    // Grab the expected checksum and verify it
    String expectedHex = args[1];
    // Inefficient but shows how the required methods are used
    boolean matching = CheckSum.verifyCheckSum(message, expectedHex); // CALL STUDENT CODE
    if(matching){
      System.out.printf("Message verified\n");
    }
    else{
      int [] expectedCheckSum = hexStringToInts(expectedHex);
      System.out.printf("checksum failed\nMessage: %s\n",message);
      System.out.printf("Expect: %08X%08X\n",expectedCheckSum[0],expectedCheckSum[1]);
      System.out.printf("Actual: %08X%08X\n",actualCheckSum[0],actualCheckSum[1]);
    }

    return;
  }

}

