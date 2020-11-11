/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 *
 * File Name: MySymbolTable.java
 *
 * Description: Allows for the creation of SymbolTable object
 *
 ***************************************************************************/
import java.util.HashMap;

public class MySymbolTable<SymbolType, RecordType> implements SymbolTable<SymbolType, RecordType>{

	private HashMap<SymbolType, RecordType> symbolTable;

	/**
	* Constructor for MySymbolTable class
	*/
	public MySymbolTable(){
		symbolTable = new HashMap<SymbolType, RecordType>();
	}

	/**
	* Returns size of table
	* O(1)
	* @return size of table
	*/
	public int size(){
		return symbolTable.size();
	}

	/**
	* Checks if table has symbol
	* O(1)
	* @param symbol to check
	* @return true if symbol exists, false if not
	*/
	public boolean hasSymbol(SymbolType s) {
	return symbolTable.containsKey(s);
	}

	/**
	* Returns record with symbol key
	* O(1)
	* @param symbol to check
	* @return record associated with symbol
	*/
	public RecordType getRecord(SymbolType s) {
		return symbolTable.get(s);
	}

	/**
	* Puts symbol in the table
	* If s is not present, insert
	* If s is present replace s to be r
	* O(1)
	* @param s symbol to check
	* @param r record to replace
	*/
	public void putRecord(SymbolType s, RecordType r) {
		if (hasSymbol(s)) {
			symbolTable.replace(s, r);
		} else {
			symbolTable.put(s, r);
		}
	}

	/**
	* Returns record associated with s
	* O(1)
	* @param symbol to check
	* @return record associated with symbol
	*/
	public RecordType removeSymbol(SymbolType s) {
		return symbolTable.remove(s);
	}

 //------------------------------------
 // example test code... edit this as much as you want!
 public static void main(String[] args){
  MySymbolTable<String,Integer> table = new MySymbolTable<String,Integer>();
  
  if(table.size()==0 && !table.hasSymbol("a"))  {
   System.out.println("Yay 1");
  }

  table.putRecord("a",new Integer(136));
  table.putRecord("b",new Integer(112));
  
  if(table.size()==2 && table.hasSymbol("a") && table.getRecord("b").equals(new Integer(112)))  {
   System.out.println("Yay 2");
  }

  table.putRecord("b",new Integer(211));
  Integer v = table.removeSymbol("a");
  if(table.size()==1 && v.equals(new Integer(136)) && !table.hasSymbol("a") 
   && table.getRecord("b").equals(new Integer(211)))  {
   System.out.println("Yay 3");
  }
 
 }


}


