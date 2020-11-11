/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 3
 * George Mason University
 *
 * File Name: Queue.java
 *
 * Description: Allows the custom creation of queue data types
 *
 ***************************************************************************/
public class Queue<AnyType>{
	private Node front;
	private Node back;

	/**
	* Private node class
	*/
	private class Node {
		AnyType data;
		Node next;

	/**
	* Constructor for Node class
	*
	* @param data
	*/
	public Node(AnyType data) {
		this.data = data;
		next = null;
	}
}

	/**
	* Constructor for Queue class
	*/
	public Queue() {
		front = back = null;
	}

	/**
	* Checks if queue is empty
	*
	* O(1)
	*
	* @return true if empty, false if not
	*/
	public boolean isEmpty() {
		return front == null;
	}

	/**
	* Returns the front of the queue without removing
	*
	* O(1)
	*
	* @return front of queue, null if doesn't exist
	*/
	public AnyType getFront() {
		if (front == null) return null;
		return front.data;
	}

	/**
	* Puts a new node into the queue
	*
	* O(1)
	*
	* @param a value to put in the queue
	*/
	public void enqueue(AnyType value) {
		Node newNode = new Node(value);
		if (isEmpty()) {
			front = back = newNode;
		} else {
			back.next = newNode;
			back = newNode;
		}
	}

	/**
	* Returns the front of the queue with removal
	*
	* O(1)
	*
	* @return front of queue, or null if doesn't exist
	*/
	public AnyType dequeue() {
		if (isEmpty()) return null;
		Node returnNode = front;
		front = front.next;
		return returnNode.data;
	}

 //----------------------------------------------------
 // example testing code... make sure you pass all ...
 // and edit this as much as you want!

 public static void main(String[] args){
  Queue<Integer> iq = new Queue<Integer>();
  if (iq.isEmpty() && iq.getFront()==null){
   System.out.println("Yay 1");
  }

  iq.enqueue(new Integer(12));
  iq.enqueue(new Integer(20));
  iq.enqueue(new Integer(-233));
  
  if (iq.dequeue()==12 && iq.getFront()==20 ){
   System.out.println("Yay 2");
  }

  if (iq.dequeue()==20 && iq.dequeue()==-233 && iq.isEmpty() ){
   System.out.println("Yay 3");
  }

 }

  }