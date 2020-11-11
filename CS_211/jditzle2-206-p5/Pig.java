/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */
 
import java.util.*;

/**
 * This class is the driver for the Pig game
 * 
 * Imports java.util for Scanner
 * 
 * Methods include: Main method
 */
public class Pig {
  
  /**
   * Main method of class Pig
   *
   * Instantiates scan as a new Scanner to be used to take in user input
   * Asks user for player1 and player2's name
   * Instantiates newGame as a new PigGane with both player names as parameters
   * Declares player as a String to equal the result of the instantiatiation of a new PigGame with param variables of both player names
   * Congratulates the winner afterwards
   */
  public static void main(String[] args) {
    
    Scanner scan = new Scanner(System.in);
    
    System.out.println("Welcome to PIG!");
    System.out.print("Please enter Player1's name: ");
    Player player1 = new Player(scan.nextLine());
    System.out.print("Please enter Player2's name: ");
    Player player2 = new Player(scan.nextLine());
    System.out.println("Let's get started!");
    System.out.println();
    
    PigGame newGame = new PigGame(player1, player2);
    String player = newGame.playGame();
    
    System.out.println();
    System.out.println("Congratulations " + player + "! You win!");
  }
}