/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 3
 * George Mason University
 *
 * File Name: ExpressionFCNSTree.java
 *
 * Description: Allows the custom creation, and mutation of, a custom FCNS tree
 *
 ***************************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ExpressionFCNSTree{

 //==========================
 // DO NOT CHANGE
 
 FCNSTreeNode root;
 public ExpressionFCNSTree(){
  root = null;
 }
 
 public ExpressionFCNSTree(FCNSTreeNode root){
  this.root = root;
 }

 public boolean equals(ExpressionFCNSTree another){
  return root.equals(another.root);
 
 }
 
 // END OF DO NOT CHANGE
 //==========================
 
	/**
	* Returns the number of nodes in the tree
	*
	* O(N) where N = number of nodes
	*
	* @return number of nodes
	*/
	public int size() {
		return size(root);
	}

	/**
	* Private recursive function for size()
	* Recurs through the tree and adds 1 for every node it sees
	*
	* O(N) where N = number of nodes
	*
	* @param node = root of tree
	* @return number of nodes
	*/
	private int size(FCNSTreeNode node) {
		if (node == null) return 0;
		return 1 + size(node.firstChild) + size(node.nextSibling);
	}

	/**
	* Returns the height of the tree
	*
	* O(N) where N = number of nodes
	*
	* @return number of nodes
	*/
	public int height() {
		return height(root);
	}

	/**
	* Private recursive function for height()
	* Recurs through the tree finds the height of the largest path
	*
	* O(N) where N = number of nodes
	*
	* @param node = root of tree
	* @return height of tree
	*/
	private int height(FCNSTreeNode node) {
		if (node == null) return -1;
		return 1 + Math.max(height(node.firstChild), height(node.nextSibling));
	}

	/**
	* Returns the number of nodes that equal the parameter
	*
	* O(N) where N = number of nodes
	*
	* @param s = string to compare
	* @return number of nodes that equal s
	*/
	public int countNode(String s) {
		return countNode(root, s);
	}

	/**
	* Private recursive function for countNode()
	* Recurs through the tree finds the number of nodes that equal s parameter
	*
	* O(N) where N = number of nodes
	*
	* @param node = root of tree
	* @param s = string to compare
	* @return number of nodes that equal s
	*/
	private int countNode(FCNSTreeNode node, String s) {
		if (node == null) return 0;
		if (node.element.equals(s)) return 1 + countNode(node.firstChild, s) + countNode(node.nextSibling, s);
		else return countNode(node.firstChild, s) + countNode(node.nextSibling, s);
	}

	/**
	* Returns the number of nodes that are not a number
	*
	* O(N) where N = number of nodes
	*
	* @return number of nodes that aren't a number
	*/
	public int countNan() {
		return countNan(root);
	}

	/**
	* Private recursive function for countNan()
	* Recurs through the tree finds the number of nodes that aren't a number
	*
	* O(N) where N = number of nodes
	*
	* @param node = root of tree
	* @return number of nodes that aren't a number
	*/
	private int countNan(FCNSTreeNode node) {
		if (node == null) return 0;
		if (node.nan) return 1 + countNan(node.firstChild) + countNan(node.nextSibling);
		else return countNan(node.firstChild) + countNan(node.nextSibling);
	}

	/**
	* Returns a string of the tree in pre-order format
	*
	* O(N) where N = number of nodes
	*
	* @return tree in string pre-order format
	*/
	public String toStringPreFix() {
		String s = new String();
		return toStringPreFix(root, s);
	}

	/**
	* Private iterative function for toStringPreFix()
	* Iterates through the tree in pre-order format to create a string in that format
	*
	* O(N) where N = number of nodes
	*
	* @param node = root of tree
	* @param s = pre-order format string
	* @return pre-order format string
	*/
	private String toStringPreFix(FCNSTreeNode node, String s) {
		if (node == null) return "";
		Stack<FCNSTreeNode> nodeStack = new Stack<FCNSTreeNode>();
		nodeStack.push(root);
		while (!nodeStack.isEmpty()) {
			FCNSTreeNode newNode = nodeStack.peek();
			s += newNode.element + " ";
			nodeStack.pop();
			if (newNode.nextSibling != null) nodeStack.push(newNode.nextSibling);
			if (newNode.firstChild != null) nodeStack.push(newNode.firstChild);
		}
		return s;
	}

	/**
	* Returns a string of the tree in post-order format
	*
	* O(N) where N = number of nodes
	*
	* @return tree in string post-order format
	*/
	public String toStringPostFix() {
		String s = new String();
		return toStringPostFix(root, s);
	}

	/**
	* Private recursive function for toStringPostFix()
	* Recurs through the tree in post-order format to create a string in that format
	*
	* O(N) where N = number of nodes
	*
	* @param node = root of tree
	* @param s = post-order format string
	* @return post-order format string
	*/
	private String toStringPostFix(FCNSTreeNode node, String s) {
		if (node == null) return s;
		return toStringPostFix(node.firstChild, s) + toStringPostFix(node.nextSibling, s) + node.element + " ";
	}

	/**
	* Returns a string of the tree in level-order format
	*
	* O(N) where N = number of nodes
	*
	* @return tree in string level-order format
	*/
	public String toStringLevelOrder() {
		String s = new String();
		int h = height(root);
		for (int i = 0;i <= h;i++) s += toStringLevelOrder(root, s, i);
		return s;
	}

	/**
	* Private recursive function for toStringLevelOrder()
	* Recurs through the tree in level-order format to create a string in that format
	*
	* O(N) where N = number of nodes
	*
	* @param node = root of tree
	* @param s = level-order format string
	* @return level-order format string
	*/
	private String toStringLevelOrder(FCNSTreeNode node, String s, int level) {
		if (node == null) return "";
		if (level == 0) return node.element + " ";
		else return toStringLevelOrder(node.firstChild, s, level - 1) + toStringLevelOrder(node.nextSibling, s, level - 1);
	}

	/**
	* Builds a FCNS tree from a given file (prefix format)
	* Sets the root to the new tree root, or null if any errors occur
	*
	* O(N) where N = number of nodes
	*
	* @param fileName = name of file to get expression from
	*/
	public void buildTree(String fileName) throws FileNotFoundException {
		try {
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			String s = scan.nextLine();
			String[] split = s.split(" ");
			FCNSTreeNode fc, ns;
			Stack<FCNSTreeNode> nodeStack = new Stack<FCNSTreeNode>();
			for(int i = split.length - 1;i >= 0;i--) {
				FCNSTreeNode node = new FCNSTreeNode(split[i]);
				if(isOperator(split[i])) {
					fc = nodeStack.pop();
					ns = nodeStack.pop();
					node.firstChild = fc;
					fc.nextSibling = ns;
				}
				nodeStack.push(node);
			}
			root = nodeStack.pop();
		} catch(FileNotFoundException e) {
			System.out.println("File not found.");
			root = null;
		}
	}

	/**
	* Builds a binary tree from a given FCNS tree
	*
	* O(N) where N = number of nodes
	*
	* @return tree root of new binary tree
	*/
	public ExpressionBinaryTree buildBinaryTree() {
		String s = toStringPreFix();
		String[] split = s.split(" ");
		Stack<BinaryTreeNode> nodeStack = new Stack<BinaryTreeNode>();
		for(int i = split.length - 1;i >= 0;i--) {
			if (isOperator(split[i])) {
				BinaryTreeNode node = new BinaryTreeNode(split[i]);
				node.left = nodeStack.pop();
				node.right = nodeStack.pop();
				nodeStack.push(node);
			} else {
				nodeStack.push(new BinaryTreeNode(split[i]));
			}
		}
		ExpressionBinaryTree tree = new ExpressionBinaryTree();
		tree.root = nodeStack.pop();
		return tree;
	}

	/**
	* Returns a string of the tree in human-readable in-order format
	* Goes through the tree and uses a stack to help in its algorithm
	*
	* O(N) where N = number of nodes
	*
	* @return tree in string human-readable in-order format
	*/
	public String toStringPrettyInFix() {
		String s  = toStringPreFix();
		String[] split = s.split(" ");
		Stack<String> stringStack = new Stack<String>();
		int length = split.length;
		for (int i = length - 1;i >= 0;i--) {
			if (isOperator(split[i])) {
				if (split[i].equals("~")) {
					String c = stringStack.pop();
					String temp = new String();
					if (c.length() == 1) {
						temp = "(-(" + c + "))";
					} else {
						temp = "(-" + c + ")";
					}
				stringStack.push(temp);
				} else {
					String a = stringStack.pop();
					String b = stringStack.pop();
					String temp = "(" + a + split[i] + b + ")";
					stringStack.push(temp);
				}
			} else {
				stringStack.push(split[i]);
			}
		}
		return stringStack.peek();
	}

	/**
	* Private function to detect if a string is an operator
	*
	* O(1)
	*
	* @param s = string to compare
	* @return true if operator, false if not
	*/
	private boolean isOperator(String s) {
		return (s.equals("+") || s.equals("-")  || s.equals("*") || s.equals("/") || s.equals("~") || s.equals("%")) ? true : false;
	}

	/**
	* Evaluates the current FCNS tree as an expression (recursive)
	*
	* WIP
	*
	* O(N) where N = number of nodes
	*
	* @return value of the FCNS tree expression
	*/
	public Integer evaluate() {
		if (size() == 0) return null;
		return 0;
	}

	/**
	* Evaluates the current FCNS tree as an expression (non-recursive)
	*
	* O(N) where N = number of nodes
	*
	* @return value of the FCNS tree expression
	*/
	public Integer evaluateNonRec() {
		if (size() == 0) return null;
		return evaluateNonRec(root);
	}

	/**
	* Private function to evaluate the expression (non-recursive)
	*
	* O(N) where N = number of nodes
	*
	* @param node = root of the tree
	* @return value of the FCNS tree expression
	*/
	private Integer evaluateNonRec(FCNSTreeNode node) {
		String s  = toStringPreFix();
		String[] split = s.split(" ");
		Stack<String> stack = new Stack<String>();
		int length = split.length;
		int result = 0;
		for (int i = length - 1;i >= 0;i--) {
			if (isOperator(split[i])) {
				if (split[i].equals("~")) {
				int c = Integer.parseInt(stack.pop());
				result = compute(split[i], c, 0);
				} else {
					int a = Integer.parseInt(stack.pop());
					int b = Integer.parseInt(stack.pop());
					result = compute(split[i], a, b);
				}
			stack.push(Integer.toString(result));
			} else {
				stack.push(split[i]);
			}
		}
		return Integer.parseInt(stack.peek());
	}

	/**
	* Private function to compute the algebraic expression between two integers
	*
	* O(1)
	*
	* @param s = operator
	* @param fc, ns = two operands to calculate with
	* @return value of expression
	*/
	private Integer compute(String s, int fc, int ns) {
		switch (s) {
			case "+":
				return fc + ns;
			case "-":
				return fc - ns;
			case "*":
				return fc * ns;
			case "/":
				return fc / ns;
			case "%":
				return fc % ns;
			case "~":
				return -1 * fc;
		}
		return 0;
	}

 //----------------------------------------------------
 // example testing code... make sure you pass all ...
 // and edit this as much as you want!

 public static void main(String[] args) throws FileNotFoundException{
 
  //     *                   *
  //   /  \                  /
  //  /    \                1
  //  1     +   ==>    \
  //       / \                +
  //      2   3             /
  //                        2
  //                          \
  //                           3
  //
  // prefix: * 1 + 2 3 (expr1.txt)

   FCNSTreeNode n1 = new FCNSTreeNode("3");
   FCNSTreeNode n2 = new FCNSTreeNode("2",null,n1);
   FCNSTreeNode n3 = new FCNSTreeNode("+",n2,null);
   FCNSTreeNode n4 = new FCNSTreeNode("1",null,n3);
   FCNSTreeNode n5 = new FCNSTreeNode("*",n4,null);
   ExpressionFCNSTree etree = new ExpressionFCNSTree(n5);
  
  if (etree.size()==5 && etree.height()==4 && etree.countNan()==0 && etree.countNode("+") == 1){
   System.out.println("Yay 1");
  }
  
  if (etree.toStringPreFix().equals("* 1 + 2 3 ") && etree.toStringPrettyInFix().equals("(1*(2+3))")){
   System.out.println("Yay 2");
  }
  
  if (etree.toStringPostFix().equals("3 2 + 1 * ") && etree.toStringLevelOrder().equals("* 1 + 2 3 ")){
   System.out.println("Yay 3");
  }

  if (etree.evaluateNonRec() == 5)
   System.out.println("Yay 4");
  
  
  if (etree.evaluate() == 5  && n4.value==1 && n3.value==5 && !n5.nan){
   System.out.println("Yay 5");
  }
  
  ExpressionFCNSTree etree2 = new ExpressionFCNSTree();
  etree2.buildTree("expressions/expr1.txt"); // construct expression tree from pre-fix notation
  
  if (etree2.equals(etree)){
   System.out.println("Yay 6");
  }
  
  
  BinaryTreeNode bn1 = new BinaryTreeNode("1");
  BinaryTreeNode bn2 = new BinaryTreeNode("2");
  BinaryTreeNode bn3 = new BinaryTreeNode("3");
  BinaryTreeNode bn4 = new BinaryTreeNode("+",bn2,bn3);
  BinaryTreeNode bn5 = new BinaryTreeNode("*",bn1,bn4);
  ExpressionBinaryTree btree = new ExpressionBinaryTree(bn5);
  
  //construct binary tree from first-child-next-sibling tree
  ExpressionBinaryTree btree2 = etree.buildBinaryTree(); 
  if (btree2.equals(btree)){
   System.out.println("Yay 7");
  }
  
  
  ExpressionFCNSTree etree3 = new ExpressionFCNSTree();
  etree3.buildTree("expressions/expr5.txt"); // an example of an expression with division-by-zero
  if (etree3.evaluate() == null && etree3.countNan() == 1){
   System.out.println("Yay 8");
  
  }
  
   
 }
}


//=======================================
// Tree node class implemented for you
// DO NOT CHANGE
class FCNSTreeNode{
     
 //members
 String element; //symbol represented by the node, can be either operator or operand (integer)
 Boolean nan; //boolean flag, set to be true if the expression is not-a-number
 Integer value;  //integer value associated with the node, used in evaluation
 FCNSTreeNode firstChild;
 FCNSTreeNode nextSibling;
     
 //constructors
 public FCNSTreeNode(String el){
  element = el;
  nan = false;
  value = null;
  firstChild = null;
  nextSibling = null;
   }
   
 //constructors
 public FCNSTreeNode(String el,FCNSTreeNode fc, FCNSTreeNode ns ){
  element = el;
  nan = false;
  value = null;
  firstChild = fc;
  nextSibling = ns;
   }
   
    
    // toString
    @Override 
    public String toString(){
     return element.toString();
    }
    
    // compare two nodes 
    // return true if: 1) they have the same element; and
    //                 2) their have matching firstChild (subtree) and nextSibling (subtree)
    public boolean equals(FCNSTreeNode another){
     if (another==null)
      return false;
      
     if (!this.element.equals(another.element))
      return false;
     
    if (this.firstChild==null){
      if (another.firstChild!=null)
       return false;
     }
     else if (!this.firstChild.equals(another.firstChild))
      return false;
      
     if (this.nextSibling==null){
      if (another.nextSibling!=null)
       return false;
     }
     else if (!this.nextSibling.equals(another.nextSibling))
      return false;
      
     return true;
    
    }

}