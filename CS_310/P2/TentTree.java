/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 2
 * George Mason University
 *
 * File Name: TentTree.java
 *
 * Description: Puts together a workable, playable board for the PA2.java to
 * work with and allow the user to play TentTree.
 *
 ***************************************************************************/
class TentTree{

private int numRows, numCols;
private HashMap<Position, String> grid;
private String treeSymbol, tentSymbol;

/**
 * Constructor of TentTree class.
 *
 * @param numRows, numCols, tent, tree
 */
public TentTree(int numRows, int numCols, String tent, String tree){
 this.numRows = numRows;
 this.numCols = numCols;
 tentSymbol = tent;
 treeSymbol = tree;
 grid = new HashMap<Position, String>();
}

/**
 * Overloaded constructor of TentTree class. Uses default "X" for tent
 * and "O" for tree.
 *
 * @param numRows, numCols
 */
 public TentTree(int numRows, int numCols){
 this.numRows = numRows;
 this.numCols = numCols;
 tentSymbol = "X";
 treeSymbol = "O";
 grid = new HashMap<Position, String>();
}

/**
 * Returns variables tentSymbol and treeSymbol respectfully.
 *
 * O(1)
 *
 * @return tentSymbol, treeSymbol
 */
public String getTentSymbol(){return tentSymbol;}
public String getTreeSymbol(){return treeSymbol;}

/**
 * Returns variables numRows and numCols respectfully.
 *
 * O(1)
 *
 * @return tentSymbol, treeSymbol
 */
public int numRows(){return numRows;}
public int numCols(){return numCols;}

/**
 * Checks if the position is valid by seeing if it is the grid bounds
 * and not below 0.
 *
 * O(1)
 *
 * @param position to check
 * @return true if successfully added, false if not
 */
public boolean isValidPosition(Position pos){
 return (numCols > pos.getCol() && numRows > pos.getRow() && !(pos.getCol() < 0 || pos.getRow() < 0));
}

/**
 * Checks if the string is a valid symbol.
 *
 * O(1)
 *
 * @param string to check
 * @return true if valid symbol, false if not
 */
public boolean isValidSymbol(String s){
 return (s.equals(treeSymbol) || s.equals(tentSymbol));
}

/**
 * Sets the given cell at a given position with a specified symbol. Checks if
 * the position and symbol is valid.
 *
 * O(1)
 *
 * @param position to add, symbol to add
 * @return true if successfully added, false if not
 */
 public boolean set(Position pos, String s){
 if (grid.contains(pos) || !(isValidPosition(pos) && isValidSymbol(s))) return false;
 return grid.add(pos, s);
}

/**
 * Returns the cell at a given position.
 *
 * O(1)
 *
 * @param position to check
 * @return symbol of cell
 */
public String get(Position pos){
 return grid.getValue(pos);
}

/**
 * Adds a tent at a given position.
 *
 * O(1)
 *
 * @param position to add tent
 * @return true if successfully added, false if not
 */
public boolean addTent(Position pos){
 return set(pos,tentSymbol);
}

/**
 * Removes a tent at a given position.
 *
 * O(1)
 *
 * @param position to remove tent
 * @return true if successfully removed, false if not
 */
 public boolean removeTent(Position pos){
 if (!(grid.getValue(pos) == tentSymbol)) return false;
 return grid.remove(pos);
}

/**
 * Adds a tree at a given position.
 *
 * O(1)
 *
 * @param position to add tree
 * @return true if successfully adds, false if not
 */
public boolean addTree(Position pos){
 return set(pos,treeSymbol);
}

/**
 * Checks if the cell has a tent.
 *
 * O(1)
 *
 * @param position to check
 * @return true if tent at position, false if not
 */
public boolean hasTent(Position pos){
 return (grid.getValue(pos).equals(tentSymbol));
}

/**
 * Checks the 4-way neighbors if they have the symbol specified.
 *
 * O(1)
 *
 * @param position to check, symbol to check
 * @return true if symbol exists, false if not
 */
public boolean posHasNbr(Position pos, String s){
 if (!isValidSymbol(s) || !isValidPosition(pos)) return false;
 Position U = new Position(pos.getRow() + 1, pos.getCol());
 Position L = new Position(pos.getRow(), pos.getCol() - 1);
 Position D = new Position(pos.getRow() - 1, pos.getCol());
 Position R = new Position(pos.getRow(), pos.getCol() + 1);
 
 if ((isValidPosition(U) && get(U) != null && get(U).equals(s)) || 
  (isValidPosition(L) && get(L) != null && get(L).equals(s)) ||
  (isValidPosition(D) && get(D) != null && get(D).equals(s)) ||
  (isValidPosition(R) && get(R) != null && get(R).equals(s))) return true;
 return false;
}

/**
 * Checks the 8-way neighbors if they have the symbol specified.
 *
 * O(1)
 *
 * @param position to check, symbol to check
 * @return true if symbol exists, false if not
 */
public boolean posTouching(Position pos, String s){
 if (!isValidSymbol(s) || !isValidPosition(pos)) return false;
 Position U = new Position(pos.getRow() + 1, pos.getCol());
 Position UL = new Position(pos.getRow() + 1, pos.getCol() - 1);
 Position L = new Position(pos.getRow(), pos.getCol() - 1);
 Position DL = new Position(pos.getRow() - 1, pos.getCol() - 1);
 Position D = new Position(pos.getRow() - 1, pos.getCol());
 Position DR = new Position(pos.getRow() - 1, pos.getCol() + 1);
 Position R = new Position(pos.getRow(), pos.getCol() + 1);
 Position UR = new Position(pos.getRow() + 1, pos.getCol() + 1);
 
 if ((isValidPosition(U) && get(U) != null && get(U).equals(s)) ||
  (isValidPosition(UL) && get(UL) != null && get(UL).equals(s)) ||
  (isValidPosition(L) && get(L) != null && get(L).equals(s)) ||
  (isValidPosition(DL) && get(DL) != null && get(DL).equals(s)) ||
  (isValidPosition(D) && get(D) != null && get(D).equals(s)) ||
  (isValidPosition(DR) && get(DR) != null && get(DR).equals(s)) ||
  (isValidPosition(R) && get(R) != null && get(R).equals(s)) ||
  (isValidPosition(UR) && get(UR) != null && get(UR).equals(s))) return true;
 return false;
}

/***
 * methods that return a string of the board representation
 * this has been implemented for you: DO NOT CHANGE
 * @param no parameters
 * @return a string
 */
@Override
public String toString(){
 StringBuilder sb = new StringBuilder("");
 for (int i=0; i<numRows; i++){
  for (int j =0; j<numCols; j++){
   Position pos = new Position(i,j);
  if (grid.contains(pos)) sb.append(String.format("%5s ",this.get(pos)));
  else sb.append(String.format("%5s ","-")); //empty cell
  }
 sb.append("\n");
 }
 return sb.toString();
}
 
  
  
 /***
  * EXTRA CREDIT:
  * methods that checks the status of the grid and returns:
  * 0: if the board is empty or with invalid symbols
  * 1: if the board is a valid and finished puzzle
  * 2: if the board is valid but not finished
  *     - should only return 2 if the grid missing some tent but otherwise valid
  *       i.e. no tent touching other tents; no 'orphan' tents not attached to any tree, etc. 
  * 3: if the board is invalid
  *     - note: only one issue need to be reported when the grid is invalid with multiple issues
  * @param no parameters
  * @return an integer to indicate the status
  * 
  * assuming HashMap overhead constant, O(R*C) 
  * where R is the number of rows and C is the number of columns
  * Note: feel free to add additional output to help the user locate the issue
  */
 public int checkStatus(){
  
  return 2;
  
 }
 
 
 
 //----------------------------------------------------
 // example testing code... make sure you pass all ...
 // and edit this as much as you want!

 // Note: you will need working Position and SimpleMap classes to make this class working
 
 public static void main(String[] args){
 
  TentTree g1 = new TentTree(4,5,"Tent","Tree");
  if (g1.numRows()==4 && g1.numCols()==5 && g1.getTentSymbol().equals("Tent")
   && g1.getTreeSymbol().equals("Tree")){
   System.out.println("Yay 1");
  }

  TentTree g2 = new TentTree(3,3);

  if (g2.set(new Position(1,0), "O") && !g2.set(new Position(1,0),"O") &&
   g2.addTree(new Position(1,2)) && !g2.addTree(new Position(1,5))){
   System.out.println("Yay 2");
  }
  
  if (g2.addTent((new Position(0,0))) && g2.addTent(new Position(0,1)) &&
   !g2.addTent((new Position(1,0))) && g2.get((new Position(0,0))).equals("X")){
   System.out.println("Yay 3");
  }
  
  if (g2.hasTent(new Position(0,0)) && g2.posHasNbr((new Position(0,0)),"O") &&
   g2.posTouching((new Position(1,2)),"X") && !g2.posHasNbr((new Position(1,2)),"X")){
   System.out.println("Yay 4");

  }
  if (g2.removeTent(new Position(0,1)) && !g2.removeTent(new Position(2,1))
   && g2.get(new Position(2,2))==null){
   System.out.println("Yay 5");
  }
 
 }


}