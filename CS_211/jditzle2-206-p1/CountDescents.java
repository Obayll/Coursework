public class CountDescents {
  public static int countDescents (int[] xs) {
    int numDescent = 1; //initiliazes the counter to 2
    if(xs.length == 0) //if the array is empty, return 0
      return 0;
    for(int i = 1;i < xs.length;i++) { //starts loop to last the length of the array
      if(xs[i-1] < xs[i]) //if the previous number is less than the current number, add 1 to the counter
        numDescent++;
    }
    return numDescent; //returns numbers of descending sequences
  }
}