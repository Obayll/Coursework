// Do not modify this class. Once you have finished ShfitingQueue, you
// can use this main method to experiment with you queues.

import java.util.Scanner;

public class QueueExperimenter{
  public static void main(String args[]){
    Scanner input = new Scanner(System.in);

    System.out.println("Queue experimenter");
    System.out.println("Enter the capacity of the queue:");

    int capacity = input.nextInt();
    int [] queue = ShiftQueue.makeQueue(capacity);
    String action = "";
    while(!action.equals("exit")){
      System.out.println();
      System.out.printf( "Queue: '%s'\n",ShiftQueue.queueString(queue));
      System.out.println("Actions:");
      System.out.println("  add num : add a number to the back");
      System.out.println("  remove  : remove the front number");
      System.out.println("  exit    : end the program");
      System.out.println("Enter action:");
      action = input.next();
      if(action.equals("add")){
        int newItem = input.nextInt();
        boolean result = ShiftQueue.addToEndOfQueue(queue, newItem);
        System.out.printf("Added %d, result: %b\n",newItem,result);
      }
      else if(action.equals("remove")){
        boolean result = ShiftQueue.removeFromFrontOfQueue(queue);
        System.out.printf("Remove front, result: %b\n",result);
      }
    }
    return;
  }
}