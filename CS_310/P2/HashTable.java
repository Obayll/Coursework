import java.util.Iterator;
/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 2
 * George Mason University
 *
 * File Name: HashTable.java
 *
 * Description: Constructs a hash-table that allows the hashing of objects
 * to be have a unique location.
 *
 ***************************************************************************/
class HashTable<T> {
private int size;
private int numChain;
private int numNode;

/**
 * Constructor HashTable class
 */
public HashTable(){
 size = 0;
 numChain = 0;
 numNode = 0;
}

@SuppressWarnings("unchecked")
private SimpleList<T>[] table = new SimpleList[11];

/**
 * Adds a node to a LinkedList inside the table array. If the array is becoming
 * too slow, calls rehash to help distribute the load.
 *
 * O(M) worst case, where M = size returned by size()
 * O(1) or O(M/N) average case (where M/N is the load)
 *
 * @param value to add
 * @return true if successfully added, false if not
 */
public boolean add(T value) {
 int pos = Math.abs(value.hashCode()) % table.length;
 if (contains(value)) return false;
 if (table[pos] == null) {
  table[pos] = new SimpleList<T>();
  table[pos].add(value);
  numChain++;
  numNode++;
  size++;
 } else if (table[pos] != null) {
   table[pos].add(value);
  numNode++;
  size++;
 }
 while (getAvgChainLength() > 1.2) {
  rehash(nextPrime(2 * table.length));
 }
 return true;
}

/**
 * Removes a node from LinkedList, or if the list has size of 1, removes
 * the list entirely.
 *
 * O(M) worst case, where M = size returned by size()
 * O(1) or O(M/N) average case (where M/N is the load)
 *
 * @param value to remove
 * @return true if successfully removed, false if not
 */
 public boolean remove(T value) {
 if (!contains(value)) return false;
 int pos = Math.abs(value.hashCode()) % table.length;
 if (table[pos].size() == 1) {
  table[pos] = null;
  numChain--;
  numNode--;
  size--;
 } else {
  table[pos].remove(value);
  numNode--;
  size--;
 }
 return true;
}

/**
 * Finds if a LinkedList contains a value, and returns true if found.
 *
 * O(M) worst case, where M = size returned by size()
 * O(1) or O(M/N) average case (where M/N is the load)
 *
 * @param value to find
 * @return true if successfully found, false if not
 */
public boolean contains(T value) {
 int pos = Math.abs(value.hashCode()) % table.length;
 if (table[pos] != null && table[pos].contains(value)) return true;
 return false;
}

/**
 * Returns the value if the parameter value can be found inside the list.
 *
 * O(M) worst case, where M = size returned by size()
 * O(1) or O(M/N) average case (where M/N is the load)
 *
 * @param value to find
 * @return true if successfully found, false if not
 */
public T get(T value) {
 if (!this.contains(value)) return null;
 int pos = Math.abs(value.hashCode()) % table.length;
 return table[pos].get(value);
}

/**
 * Rehashes the given table with a new given capacity.
 *
 * O(N) when N>M; O(M) otherwise
 * where N is the table length and M = size returned by size()
 *
 * @param new size of table
 * @return true if successfully rehashed, false if not
 */
@SuppressWarnings("unchecked")
public boolean rehash(int newCapacity) {
 SimpleList<T>[] newTable = new SimpleList[newCapacity];
 SimpleList<T>[] oldTable = table;
 Object[] dataArray = valuesToArray();
 table = newTable;
 numNode = numChain = size = 0;
 for (int i = 0;i < dataArray.length;i++) {
  add((T)dataArray[i]);
 }
 if (getLoad() > 0.7) {
  table = oldTable;
  return false;
 }
 return true;
}

/**
 * Returns the variable size.
 *
 * O(1)
 *
 * @return size
 */
public int size() {
 return size;
}

/**
 * Returns the load of the table.
 *
 * O(1)
 *
 * @return size
 */
public double getLoad() {
 return (double)numNode / table.length;
}

/**
 * Returns the average chain length of the table.
 *
 * O(1)
 *
 * @return size
 */
public double getAvgChainLength(){
 return (double)numNode / numChain;
}

/**
 * Turns all of the data nodes into an array of only data.
 *
 * O(N) when N>M; O(M) otherwise
 * where N is the table length and M = size returned by size()
 *
 * @return Object array of node data
 */
 public Object[] valuesToArray() {
 int counter = 0;
 Object[] array = new Object[size()];
 for (int i = 0;i < table.length;i++) {
  if (table[i] != null) {
   Iterator iter = table[i].iterator();
   while (iter.hasNext()) {
   array[counter] = iter.next();
   counter++;
   }
  }
 }
 return array;
}

/**
 * Finds the next prime number after the given number.
 *
 * @param prime number
 * @return next prime number
 */
public int nextPrime(int x) {
 while(true) {
  boolean isPrime = true;
  for(int i = 2; i <= Math.sqrt(x); i++) {
   if(x % i == 0) {
   isPrime = false;
   break;
   }
  }
  if(isPrime) return x;
  x++;
 }
}

 //------------------------------------
 // example test code... edit this as much as you want!
 public static void main(String[] args) {
  HashTable<String> names = new HashTable<>();
  
  if(names.add("Alice") && names.add("Bob") && !names.add("Alice") 
   && names.size() == 2 && names.getAvgChainLength() == 1)  {
   System.out.println("Yay 1");
  }
  
  if(names.remove("Bob") && names.contains("Alice") && !names.contains("Bob") && names.get("Alice").equals("Alice")
   && names.valuesToArray()[0].equals("Alice")) {
   System.out.println("Yay 2");
  }

  boolean loadOk = true;
  if(names.getLoad() != 1/11.0 || !names.rehash(10) || names.getLoad() != 1/10.0 || names.rehash(1)) {
   loadOk = false;
  }
  
  boolean avgOk = true;
  HashTable<Integer> nums = new HashTable<>();
  for(int i = 1; i <= 70 && avgOk; i++) {
   nums.add(i);
   double avg = nums.getAvgChainLength();
   if(avg> 1.2 || (i < 12 && avg != 1) || (i >= 14 && i <= 23 && avg != 1) || 
    (i >= 28 && i <= 47 && avg != 1) || (i >= 57 && i <= 70 && avg!= 1)) {
    avgOk = false;
   }
   
  }
  if(loadOk && avgOk) {
   System.out.println("Yay 3");
  }
  
 }
}