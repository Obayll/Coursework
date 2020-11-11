/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */
 
import java.util.*;

/**
 * This class contains the main logic behind the Pig game
 * 
 * Imports java.util for Scanner
 * 
 * Methods include: Constructor, playGame()
 */
public class PigGame {
  
  /**
   * Instantiates Player variables
   * Instantiates Scanner
   * Initializes boolean variable to dtermine if it is the start of a turn
   */
  private Player player1, player2;
  private Scanner scan;
  private boolean playerTurn;
  
  /**
   * Constructor of class PigGame
   *
   * Declares player1 and player2 to param variables x and y respectively
   * Declares playerTurn to true so the game start with the user going first
   * 
   * @param players to be vs. each other in Pig
   */
  public PigGame(Player x, Player y) {
    player1 = x;
    player2 = y;
    playerTurn = true;
  }
  
  /**
   * Declares FLAG variable to 100 due to that being the cutt-off points
   * Decalres stOfTurn as true to determine if it is the start of the turn
   * Instantiates a new Scanner scan
   * The entire of the following code is a while loop only breaking when either player1 or player2's bank gets above FLAG value
   * Player 1's Turn:
   * Displays that it's the start of the turn if stOfTurn = true
   * Rolls for player1
   * Checks for 1's rolled
   * If two 1's rolled then bank and points are reset and information is displayed
   * If one 1 is rolled then points are reset and information is displayed
   * If no 1's rolled then displays information and asks to roll again with "y" or "n" being the responses
   * If y then roll again
   * If n then points go into bank and display information
   * Player 2's turn:
   * Displays that it's the start of the turn if stOfTurn = true
   * Rolls for player2
   * Checks for 1's rolled
   * If two 1's rolled then bank and points are reset and information is displayed
   * If one 1 is rolled then points are reset and information is displayed
   * If no 1's rolled then displays information
   * If points > 20 then points go into bank and information is displayed
   * If points < 20 then roll again
   * Out of loop:
   * Checks for which player broke the loop and returns their name
   * 
   * @return name of winner
   */
  public String playGame() {
    int FLAG = 100;
    boolean stOfTurn = true;
    scan = new Scanner(System.in);
    while ((player1.getBank() < FLAG) && (player2.getBank() < FLAG)) {
      if (playerTurn == true) {
        if (stOfTurn == true) {
          System.out.println(player1.getName() + "'s Turn:");
          stOfTurn = false;
        }
        player1.addPoints(player1.roll());
        if ((player1.getDie1() == 1) && (player1.getDie2() == 1)) {
          player1.resetPoints();
          player1.resetBank();
          System.out.print("Roll: " + player1.getDie1() + " + " + player1.getDie2() + " = " + player1.getTotal() + "   Points: " + player1.getPoints() + "   Bank: " + player1.getBank());
          System.out.println("    Snakeyes! Turn skipped; points and bank reset.");
          stOfTurn = true;
          playerTurn = false;
        } else if ((player1.getDie1() == 1) || (player1.getDie2() == 1)) {
          player1.resetPoints();
          System.out.print("Roll: " + player1.getDie1() + " + " + player1.getDie2() + " = " + player1.getTotal() + "   Points: " + player1.getPoints() + "   Bank: " + player1.getBank());
          System.out.println("    Rolled a 1! Turn skipped; points reset.");
          stOfTurn = true;
          playerTurn = false;
        } else {
          System.out.print("Roll: " + player1.getDie1() + " + " + player1.getDie2() + " = " + player1.getTotal() + "   Points: " + player1.getPoints() + "   Bank: " + player1.getBank());
          System.out.print("    Roll again (y/n)?: ");
          String temp = scan.nextLine();
          if (temp.equals("n")) {
            player1.addBank(player1.getPoints());
            player1.resetPoints();
            System.out.println("Points: " + player1.getPoints() + "   Bank: " + player1.getBank() + "    Turn passed.");
            stOfTurn = true;
            playerTurn = false;
          }
          if (player1.getBank() >= FLAG) break;
        }
        System.out.println();
      }
      if (playerTurn == false) {
        if (stOfTurn == true) {
          System.out.println(player2.getName() + "'s Turn:");
          stOfTurn = false;
        }
        player2.addPoints(player2.roll());
        if ((player2.getDie1() == 1) && (player2.getDie2() == 1)) {
          player2.resetPoints();
          player2.resetBank();
          System.out.print("Roll: " + player2.getDie1() + " + " + player2.getDie2() + " = " + player2.getTotal() + "   Points: " + player2.getPoints() + "   Bank: " + player2.getBank());
          System.out.println("    Snakeyes! Points and bank reset.");
          stOfTurn = true;
          playerTurn = true;
        } else if ((player2.getDie1() == 1) || (player2.getDie2() == 1)) {
          player2.resetPoints();
          System.out.print("Roll: " + player2.getDie1() + " + " + player2.getDie2() + " = " + player2.getTotal() + "   Points: " + player2.getPoints() + "   Bank: " + player2.getBank());
          System.out.println("    Rolled a 1! Points reset.");
          stOfTurn = true;
          playerTurn = true;
        } else {
          if ((player2.getPoints() >= 20) || (player2.getPoints() + player2.getBank() >= FLAG)){
            System.out.print("Roll: " + player2.getDie1() + " + " + player2.getDie2() + " = " + player2.getTotal() + "   Points: " + player2.getPoints() + "   Bank: " + player2.getBank());
            player2.addBank(player2.getPoints());
            player2.resetPoints();
            System.out.println("    Not rolling again.");
            System.out.println("Points: " + player2.getPoints() + "   Bank: " + player2.getBank() + "    Turn passed.");
            stOfTurn = true;
            playerTurn = true;
          } else {
            System.out.print("Roll: " + player2.getDie1() + " + " + player2.getDie2() + " = " + player2.getTotal() + "   Points: " + player2.getPoints() + "   Bank: " + player2.getBank());
            System.out.println("    Rolling again.");
          }
        }
        System.out.println();
      }
    }
    if (player1.getBank() >= FLAG) return player1.getName();
    else return player2.getName();
  }
}