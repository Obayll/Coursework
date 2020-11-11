/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 3
 * George Mason University
 *
 * File Name: Stack.java
 *
 * Description: Allows the custom creation of stack data types
 *
 ***************************************************************************/
public class Stack<AnyType>{
	private Node top;
	private Node bottom;

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
	* Constructor for Stack class
	*/
	public Stack() {
		top = bottom = null;
	}

	/**
	* Checks if stack is empty
	*
	* O(1)
	*
	* @return true if empty, false if not
	*/
	public boolean isEmpty() {
		return top == null;
	}

	/**
	* Returns the top of the stack without removing
	*
	* O(1)
	*
	* @return top of stack or null if doesn't exist
	*/
	public AnyType peek() {
		if (top == null) return null;
		return top.data;
	}

	/**
	* Puts a new node into the stack
	*
	* O(1)
	*
	* @param a value to put in the stack
	*/
	public void push(AnyType value) {
		Node newNode = new Node(value);
		if (isEmpty()) {
			top = bottom = newNode;
		} else {
			newNode.next  = top;
			top = newNode;
		}
	}

	/**
	* Returns the top of the stack with removal
	*
	* O(1)
	*
	* @return top of stack, or null if doesn't exist
	*/
	public AnyType pop() {
		if (isEmpty()) return null;
		Node returnNode = top;
		top = top.next;
		return returnNode.data;
	}

 //----------------------------------------------------
 // example testing code... make sure you pass all ...
 // and edit this as much as you want!

 public static void main(String[] args){
  Stack<Integer> iStack = new Stack<Integer>();
  if (iStack.isEmpty() && iStack.peek()==null){
   System.out.println("Yay 1");
  }
  
  iStack.push(new Integer(12));
  iStack.push(new Integer(20));
  iStack.push(new Integer(-233));
  
  if (iStack.pop()==-233 && iStack.peek()==20 ){
   System.out.println("Yay 2");
  }
  
  if (iStack.pop()==20 && iStack.pop()==12 && iStack.isEmpty() ){
   System.out.println("Yay 3");
  }

  
 }

}