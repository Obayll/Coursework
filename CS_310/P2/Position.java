/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 2
 * George Mason University
 *
 * File Name: Position.java
 *
 * Description: Serves as a basis for having the Position object used in 
 * TentTree.java
 *
 ***************************************************************************/
class Position{
 private int row;
 private int col;

/**
 * Constructor for Position class
 *
 * @param row, col
 */
public Position(int row, int col){
 this.row = row;
 this.col = col;
}

/**
 * Returns variables row and col respectfully.
 *
 * @return row, col
 */
public int getRow(){return row;}
public int getCol(){return col;}

/**
 * Displays the Position object in String format
 */
public String toString(){
 return "<" + row + "," + col + ">";
}

/**
 * Overrides native equals() method to use Position objects. Returns true if
 * equal, otherwise false.
 *
 * @param obj
 * @return true if equal, false if not equal
 */
@Override
public boolean equals(Object obj){
Position c = (Position) obj;
return Integer.compare(row, c.row) == 0 && Integer.compare(col, c.col) == 0;
}

/**
 * Computers a unique hash-code for a Position object.
 *
 * @return hash-code
 */
@Override
public int hashCode(){
int result = 17;
result = 31 * result + ((Integer)row).hashCode();
result = 31 * result + ((Integer)col).hashCode();
return result;
}



 //----------------------------------------------------
 // example testing code... make sure you pass all ...
 // and edit this as much as you want!


 public static void main(String[] args){
  Position p1 = new Position(3,5);
  Position p2 = new Position(3,6);
  Position p3 = new Position(2,6);
  
  if (p1.getRow()==3 && p1.getCol()==5 && p1.toString().equals("<3,5>")){
   System.out.println("Yay 1");
  }

  if (!p1.equals(p2) && !p2.equals(p3) && p1.equals(new Position(3,5))){
   System.out.println("Yay 2");
  }
  
  if (p1.hashCode()!=p3.hashCode() && p1.hashCode()!=(new Position(5,3)).hashCode() &&
   p1.hashCode() == (new Position(3,5)).hashCode()){
   System.out.println("Yay 3");   
  }
  
  
 }
 
}