/**
 * @author Jordan Ditzler
 * @version 1.8.0_101
 */

import java.util.ArrayList;

/**
  *A tower containing disks in the Towers of Hanoi puzzle.
  */
public class Tower
{
   private ArrayList<Integer> disks;

   /**
     *Constructs a tower holding a given number of disks of decreasing size.
     *@param ndisks the number of disks
     */
   public Tower(int ndisks) {
     disks = new ArrayList<Integer>();
     for (int i = ndisks;i > 0;i--) {
       disks.add(i);
     }
   }

   /**
     *Removes the top disk from this tower.
     *@return the size of the removed disk
     */
   public int remove() {
     int temp = disks.get(disks.size() - 1);
     disks.remove(disks.size() - 1);
     return temp;
   }

   /**
     *Adds a disk to this tower.
     *@param size the size of the disk to add
     */
   public void add(int size) {
     disks.add(size);
   }
   
   /**
    * Returns a string representation of the array list.
    * @return string representation of disks.
    */ 
   public String toString() { 
     return disks.toString(); 
   }   
}

