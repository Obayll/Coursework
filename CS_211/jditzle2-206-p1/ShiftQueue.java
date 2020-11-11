import java.util.Arrays;
// Implements operations for an int queue. Queues are stored in an
// array with empty cells of the array containing -1. Queues add
// elements to the end and remove elements from the front. Each
// operation must deal with the fact that the queue does not carry the
// number elements it currently contains. The suggested way to do
// this is to keep all elements on the "left" of the array near index
// 0.
public class ShiftQueue{
// Create an empty integer queue with the given maximum capacity.
// Allocate an array of the given size and fill it with the empty
// element which is the integer -1. If capacity is negative, return
// null.
  public static int [] makeQueue(int capacity){
    if(capacity < 0) return null;
    int [] queue = new int[capacity];
    for(int i = 0;i < queue.length;i++){
      queue[i] = -1;
    }
    return queue;
  }
// Add newElement to the end of queue. Use a loop to search for the
// first occurrence of an empty element (-1) put newItem there.
// After a succesul add return true. If there are no empty slots or
// newItem is -1, return false.
  public static boolean addToEndOfQueue(int [] queue, int newItem){
    if((queue.length == 0) || (newItem == -1)){
      return false;
    }
    for(int i = 0;i < queue.length;i++){
      if(queue[i] == -1) {
        queue[i] = newItem;
        return true;
      }
    }
    return false;
  }
// Remove an element from the front of the queue which is index
// 0. Shift all existing elements to the left by one index. The new
// front of the queue should be at index 0 after this shift. Ensure
// that all empty slots have the value -1 in them. On successfully
// removing an element, return true. If queue is empty (length 0 or
// all elements are -1), return false.
  public static boolean removeFromFrontOfQueue(int [] queue){
    if((queue.length == 0) || (queue[0] == -1)){
      return false;
    }
    for(int i = 0;i < queue.length-1;i++){
      queue[i] = queue[i+1];
    }
    queue[queue.length-1] = -1;
    return true;
  }
// Produce a string representing the queue. This string should not
// contain any -1 elements: only the integers that have been
// added. Each element should be separated by a space with an
// additional space at the end after the last element.  
  public static String queueString(int [] queue){
    String x = "";
    for(int i = 0;i < queue.length;i++){
      if(queue[i] != -1) {
        x += queue[i] + " ";
      }
    }
    return x;
  }
}