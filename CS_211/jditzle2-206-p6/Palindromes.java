/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */

/**
 * Program to check if a given string is a Palindrome or not.
 */ 
public class Palindromes
{
  /**
   * Tester method that checks and prints if the given strings are Palindromes.
   */ 
   public static void main(String[] args)
   {
      String sentence1 = "Madam, I'm Adam";      
      System.out.println(sentence1);
      System.out.println("Palindrome: " + isPalindrome(sentence1)); //Output:- Palindrome: true
      String sentence2 = "Sir, I'm Eve";      
      System.out.println(sentence2);
      System.out.println("Palindrome: " + isPalindrome(sentence2)); //Output:- Palindrome: false
      String sentence3 = "A man, a plan, a canal, Panama";      
      System.out.println(sentence3);
      System.out.println("Palindrome: " + isPalindrome(sentence3)); //Output:- Palindrome: true     
   }

   /**
    * Tests whether a text is a palindrome.
    * @param text a string that is being checked
    * @return true if text is a palindrome, false otherwise
    */
   public static boolean isPalindrome(String text)  {
     text = text.toLowerCase(); text = text.replace(",", ""); text = text.replace(" ", ""); text = text.replace("'", "");
     if ((text.length() == 1) || (text.length() == 0)) return true;
     if ((text.charAt(0) == text.charAt(text.length() - 1))) return isPalindrome(text.substring(1, text.length() - 1));
     else return false;
   }
}
