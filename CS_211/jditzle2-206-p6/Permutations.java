/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 *This program computes permutations of a string.
 */
public class Permutations
{
   /**Takes a command line input from the user and prints all 
     * possible permutations of the entered word.
     * @param args user input for the string to permute
     */ 
   public static void main(String[] args) {
     String input = "";
     Scanner SCAN = new Scanner(System.in);
     System.out.print("Please enter a word to permute: ");
     input = SCAN.nextLine();
     System.out.println(permutations(input));
   }

   /**
    *Gets all permutations of a given word.
    *@param word the string to permute
    *@return a list of all permutations
    */
   public static ArrayList<String> permutations(String word) {
     ArrayList<String> list = new ArrayList<String>();
     if (word.length() == 0) {
       list.add("");
       return list;
     } else {
       for (int i = 0; i < word.length(); i++) {
         ArrayList<String> newList = permutations(word.substring(0, i) + word.substring(i + 1));
         for (int j = 0; j < newList.size(); j++) {
           list.add(word.charAt(i) + newList.get(j));
         }
       }
       return list;
     }
   }
}

