/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 *
 * File Name: MyPriorityQueue.java
 *
 * Description: Allows for the creation of PriorityQueue object
 *
 ***************************************************************************/

public class MyPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {

	static final int INF = Integer.MAX_VALUE; // the max priority to use: infinity
	private static final int DEFAULT_CAPACITY = 100;
	private int size;
	private Object[ ] heap;

	/**
	* Private class for Pair
	*/
	private class Pair<T extends Comparable<T>> {
		T data;
		int priority;

	/**
	* Constructor for private Pair class with no parameters
	*/
	public Pair() {
		data = null;
		priority = 0;
	}

	/**
	* Constructor for private Pair class with parameters
	*/
	public Pair(T d, int p) {
		data = d;
		priority = p;
	}

	/**
	* Private getter method for Pair class
	* O(1)
	* @return data
	*/
	private T getData() {
		return data;
	}

	/**
	* Private getter method for Pair class
	* O(1)
	* @return priority
	*/
	private int getPriority() {
		return priority;
	}

	/**
	* Private setter method for Pair class
	* O(1)
	* @param data to set
	*/
	private void setData(T d) {
		data = d;
	}

	/**
	* Private setter method for Pair class
	* O(1)
	* @param priority to set
	*/
	private void setPriority(int p) {
		priority = p;
	}

	/**
	* Helper method to determine if pairs are equal
	* O(1)
	* @param Object to detect equality
	*/
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if(o instanceof Pair) {
			Pair<T> pair = (Pair<T>)o;
			return pair.data.equals(data);  
		}
		return false;
	}

	/**
	* Helper method to compare two pairs
	* O(1)
	* @param pair1 first pair to compare
	* @param pair2 second pair to compare
	* @return 1 if pair2 is >, -1 if <, 0 if =
	*/
	private int compare(Pair<T> pair1, Pair<T> pair2) {
		if (pair2.getPriority() > pair1.getPriority()) return 1;
		if (pair2.getPriority() < pair1.getPriority()) return -1;
		else return pair2.getData().compareTo(pair1.getData());
	}

	}

	/**
	* Constructor for PriorityQueue
	*/
	@SuppressWarnings("unchecked")
	public MyPriorityQueue() {
		size = 0;
		heap = new Object[DEFAULT_CAPACITY];
	}

	/**
	* Returns size of heap
	* O(1)
	* @return size of heap
	*/
	public int size() {
		return size;
	}

	/**
	* Returns top item (max priority)
	* O(1)
	* @return top item
	*/
	@SuppressWarnings("unchecked")
	public T peek() {
		return (T)((Pair)heap[1]).getData();
	}

	/**
	* Removes top item (max priority) from queue
	* Original code is Weiss's code
	* O(logN): N is the number of items in priority queue
	* @return item to be deleted, null if doesn't exist
	*/
	public T remove() {
		if (size() == 0) return null;
		T rVal = peek();
		heap[ 1 ] = heap[ size-- ];
		percolateDown(1);
		return rVal;
	}

	/**
	* Inserts an item v with priority p into the priority queue
	* Original code is Weiss's code
	* O(logN): N is the number of items in priority queue
	* @param v data to store
	* @param p priority of v
	*/
	@SuppressWarnings("unchecked")
	public void insert(T v, int p) {
		if (size + 1 == heap.length ) doubleArray();
		Pair<T> x = new Pair<T>(v, p);
		int hole = ++size;
		heap[0] = x;
		for( ; x.compare( x, (Pair)heap[ hole / 2 ] ) < 0; hole /= 2 ) heap[ hole ] = heap[ hole / 2 ];
		heap[ hole ] = x;
	}

	/**
	* Perform a priority update for items in the priority queue based on the following rules
	* O(N): N is the number of items in priority queue
	* @param v data to check
	* @param p priority of v
	*/
	@SuppressWarnings("unchecked")
	public void updatePriority(T v, int p) {
		for (int i = 0;i < size();i++) {
		if (((Pair)heap[i]).getData().equals(v)) {
			((Pair)heap[i]).setPriority(p);
		} else if (((Pair)heap[i]).getPriority() <= p) {
			((Pair)heap[i]).setPriority(((Pair)heap[i]).getPriority() - 1);
		}
		}
	}

	/**
	* Check whether there is a value v associated with priority p in the priority queue
	* O(N): N is the number of items in priority queue
	* @param v data to check
	* @param p priority of v
	* @return true if is contained, false if not
	*/
	@SuppressWarnings("unchecked")
	public boolean contains(T v, int p) {
		int rVal = 0;
		for (int i = 0;i < size();i++) if (((Pair)heap[i]).getData().equals(v) && ((Pair)heap[i]).getPriority() == p) return true;
		return false;
	}

	/**
	* Private helper method to double the heap array when heap is full
	* Original code is Weiss's code
	*/
	@SuppressWarnings("unchecked")
		private void doubleArray( ) {
		Pair<T> [ ] newArray;
		newArray = (Pair[]) new Object[ heap.length * 2 ];
		for( int i = 0; i < heap.length; i++ )
			newArray[ i ] = (Pair)heap[ i ];
		heap = newArray;
	}

	/**
	* Private helper method to shift down and properly sort the heap array when removing
	* Original code is Weiss's code
	* @param hole start index
	*/
	@SuppressWarnings("unchecked")
	private void percolateDown( int hole ) {
		int child;
		Pair tmp = (Pair)heap[ hole ];
		for( ; hole * 2 <= size; hole = child ) {
			child = hole * 2;
			if ( child != size && tmp.compare( (Pair)heap[ child + 1 ], (Pair)heap[ child ] ) < 0 ) child++;
			if ( tmp.compare( (Pair)heap[ child ], tmp ) < 0 ) heap[ hole ] = heap[ child ];
			else break;
		}
		heap[ hole ] = tmp;
	}

 //------------------------------------
 // example test code... edit this as much as you want!
 // note: you might want to add a method like printPQ() for debugging purpose

 public static void main(String[] args){
  MyPriorityQueue<String> pq = new MyPriorityQueue<String>();
  
  if(pq.size()==0 && pq.remove()==null && !pq.contains("a", 4))  {
   System.out.println("Yay 1");
  }
  
  pq.insert("a",4);
  pq.insert("b",10);
  pq.insert("h",2);
  
  if(pq.size()==3 && (pq.peek()).equals("b") && pq.contains("a", 4) && pq.contains("h", 2)
   && pq.contains("b",10)) {
   System.out.println("Yay 2");
  }

  if((pq.remove()).equals("b") && !pq.contains("b",10) & pq.size()==2 
   && (pq.peek().equals("a")) ) {
   System.out.println("Yay 3");
  }

  pq.insert("d",4);
  if ((pq.peek()).equals("d")) {System.out.println("Yay 4");}
    
  pq.insert("b",10);
  pq.insert("f",3);
  pq.updatePriority("a",3);
  if (pq.size() == 5 && pq.contains("a",3) && pq.contains("b",10) && pq.contains("d",4) 
   && pq.contains("f",2) && pq.contains("h",1)) {
   System.out.println("Yay 5");
  }


 }


}




