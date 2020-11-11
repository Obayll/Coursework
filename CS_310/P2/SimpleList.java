import java.util.Iterator;
/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 2
 * George Mason University
 *
 * File Name: SimpleList.java
 *
 * Description: Allows the creation of specific LinkedLists with Node<T>.
 * Used as a foundation for hash-table creation.
 *
 ***************************************************************************/
class SimpleList<T> implements Iterable<T>{

private class Node<T>{
 T data;
 Node<T> next;

/**
 * Constructor for inner Node<T> class
 *
 * @param data
 */
 public Node(T data){
  this.data = data;
  this.next = null;
 }
}

private Node<T> head;
private Node<T> tail;
private int size;

/**
 * Constructor for SimpleList class
 */
public SimpleList(){
 head = null;
 tail = null;
 size = 0;
}

/**
 * Add a value to a LinkedList by assigning it to a node and adding the
 * node to the list.
 *
 * O(1)
 *
 * @param value
 */
public void add(T value){
 Node<T> node = new Node<T>(value);
 if (size == 0) {
  head = node;
 } else {
  tail.next = node;
 }
 tail = node;
 size++;
}

/**
 * Removes a value from the LinkeList at the first occurrence.
 *
 * O(N)
 *
 * @param value
 * @return true if value removed, false if not removed
 */
public boolean remove(T value){
 int index = indexOf(value);
 if (index == -1) return false;
 Node<T> node = head;
 if (index == 0) {
  head = head.next;
  if (size == 1) {
   head = null;
  }
 } else {
  Node<T> prevNode = head;
  Node<T> curNode = head.next;
  for (int i = 0;i < index - 1;i++) {
   prevNode = curNode;
   curNode = curNode.next;
  }
 prevNode.next = curNode.next;
 if (index == size - 1) tail  = prevNode;
 }
 size--;
 return true;
}

/**
 * Returns the index of a value in a LinkedList. Returns -1 if not found.
 *
 * O(N)
 *
 * @param value
 * @return index of the value, -1 if not found
 */
public int indexOf(T value){
 Node<T> node = head;
 for (int i = 0;i < size;i++) {
  if (node.data.equals(value)) return i;
  node = node.next;
 }
 return -1;
}

/**
 * Returns if LinkedList contains a value.
 *
 * O(N)
 *
 * @param value
 * @return true if contains value, false if it does not
 */
public boolean contains(T value){
 Node<T> node = head;
 for (int i = 0;i < size;i++) {
  if (node.data.equals(value)) return true;
  node = node.next;
 }
 return false;
}

/**
 * Returns the value stored in the LinkedList.
 *
 * O(N)
 *
 * @param value
 * @return the found value, null if not found
 */
public T get(T value){
 Node<T> node = head;
 for (int i = 0;i < size;i++) {
  if (node.data.equals(value)) return node.data;
  node = node.next;
 }
 return null;
}

/**
 * Returns the variable size.
 *
 * O(1)
 *
 * @return size
 */
public int size(){
 return size;
}

/**
 * Basic constructor for the SimpleList iterator
 *
 * return ListIterator
 */
public Iterator<T> iterator(){
 return new ListIterator<T>();
}

@SuppressWarnings("unchecked")
public class ListIterator<T> implements Iterator<T> {
private Node<T> node = null;

/**
 * Checks to see if the current node has any linked values.
 *
 * @return true if the next node exists
 */
public boolean hasNext() {
 return node != tail;
}

/**
 * Returns the next node after the current node. If the node is
 * a head, just returns the head.
 *
 * @return next node
 */
public T next() {
 if (node == null) {
  node = (Node<T>)head;
  return node.data;
 } else {
  node = node.next;
  return node.data;
 }
}
}

 
 //----------------------------------------------------
 // example testing code... make sure you pass all ...
 // and edit this as much as you want!
 // also, consider add a toString() for your own debugging

 public static void main(String[] args){
  SimpleList<Integer> ilist = new SimpleList<Integer>();
  ilist.add(new Integer(11));
  ilist.add(new Integer(20));
  ilist.add(new Integer(5));
  
  SimpleList<Integer> nlist = new SimpleList<Integer>();
  nlist.add(new Integer(10));
  nlist.remove(new Integer(10));
  
  if (ilist.size()==3 && ilist.contains(new Integer(5)) && 
   !ilist.contains(new Integer(2)) && ilist.indexOf(new Integer(20)) == 1){
   System.out.println("Yay 1");
  }
  
  if (!ilist.remove(new Integer(16)) && ilist.remove(new Integer(11)) &&
   !ilist.contains(new Integer(11)) && ilist.get(new Integer(20)).equals(new Integer(20))){
   System.out.println("Yay 2");   
  } 
  
  Iterator iter = ilist.iterator();
  if (iter.hasNext() /*&& iter.next().equals(new Integer(20)) && iter.hasNext() &&
   iter.next().equals(new Integer(5)) && !iter.hasNext()*/){
   System.out.println("Yay 3");      
  }
 
 }

}