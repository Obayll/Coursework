/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 *
 * File Name: MySequence.java
 *
 * Description: Allows for the creation of Sequence object
 *
 ***************************************************************************/
import java.util.TreeSet;

public class MySequence<T extends Comparable<T>> implements Sequence<T>{

	private TreeSet<T> sequence;
	int counter;

	/**
	* Constructor for MySequence class
	*/
	public MySequence(){
		sequence = new TreeSet<T>();
		counter = 0;
	}

	/**
	* Inserts value v into the collection
	* O(logN) given N as the number of values stored in sequence
	* @return true if successful, false if not
	*/
	public boolean insert (T v) {
		return sequence.add(v);
	}

	/**
	* Removes value v into the collection
	* O(logN) given N as the number of values stored in sequence
	* @return true if successful, false if not
	*/
	public boolean remove (T v) {
		return sequence.remove(v);
	}

	/**
	* Checks the sequence for value v
	* O(logN) given N as the number of values stored in sequence
	* @return true if value is found, false if not
	*/
	public boolean contains (T v) {
		return sequence.contains(v);
	}

	/**
	* Counts how many values in sequence are greater than or equal to v
	* O(logN) given N as the number of values stored in sequence
	* @return number of values greater than or equal to v
	*/
	public int countNoSmallerThan (T v) {
		int counter = 0;
		T smallGreatest = sequence.ceiling(v);
		while (smallGreatest != null) {
			counter++;
			T temp = sequence.higher(smallGreatest);
			smallGreatest = temp;
		}
		return counter;
	}

	/**
	* Returns size of sequence
	* O(1)
	* @return size
	*/
	public int size() {
		return sequence.size();
	}

	/**
	* Returns all values of the sequence in ascending order
	* O(1)
	* @return string with values of the sequence in ascending order
	*/
	public String toStringAscendingOrder() {
		String s = new String();
		Object[] array = sequence.toArray();
		for (int i = 0;i < array.length;i++) s += array[i] + " ";
		return s;
	}

 //------------------------------------
 // example test code... edit this as much as you want!
 public static void main(String[] args){
  MySequence<Integer> seq = new MySequence<Integer>();
  
  if(seq.size()==0 && !seq.contains(11) && seq.countNoSmallerThan(10) == 0
   && seq.toStringAscendingOrder().equals(""))  {
   System.out.println("Yay 1");
  }
  
  seq.insert(11);
  seq.insert(5);
  
  if(seq.insert(200) && seq.size()==3 && seq.contains(11) 
   && seq.countNoSmallerThan(10) == 2 && !seq.remove(20))  {
   System.out.println("Yay 2");
  }
  
  seq.insert(112);
  seq.insert(50);
  seq.insert(20);
  
  if(seq.remove(20) && !seq.contains(20) && !seq.insert(200)
   && seq.countNoSmallerThan(50) == 3 
   && seq.toStringAscendingOrder().equals("5 11 50 112 200 "))  {
   System.out.println("Yay 3");
  }

 }
}


