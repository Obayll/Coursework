/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Player.java
 *
 * Description: The Player class contains methods that allow the Player object
 * to be accessed and mutated. It's method allows cards to be played, found,
 * or removed from their hand.
 * 
 ***************************************************************************/
class Player <T extends Card> {

private String name;
private int points;
private Hand<T> hand; 
private Player<T> next;

/**
 * Constructor of class Player.
 * Makes current name equal given name.
 * Initializes points to 0.
 * Creates a new Hand object and assigns it to hand.
 * Initializes next to null.
 *
 * @param	name	name of player
 */
public Player(String name){
	this.name = name;
	points = 0;
	hand = new Hand();
	next = null;
}

/**
 * Sets the next variable to the given variable p.
 *
 * @param	p	player to set to next
 */
public void setNext(Player<T> p){
	next = p;
}

/**
 * Returns the variable next when called.
 *
 * @return	next variable
 */
public Player<T> getNext(){
	return next;
}

/**
 * Returns true if the player had a next variable that is not null.
 * Returns false if not.
 * Returns false if not.
 *
 * @return	true if next variable exists, otherwise false
 */
public boolean hasNext() {
	if (next != null) return true;
	return false;
}

/**
 * Returns the variable points when called.
 *
 * @return	points variable
 */
public int getPoints(){
	return points;
}
/**
 * Returns the variable name when called.
 *
 * @return	name variable
 */
public String getName(){
	return name;
}

/**
 * Adds a card the a players hand and updates points. Returns true if
 * successful, otherwise false.
 *
 * @param	c	the card to add to the hand
 * @return		true if successful, false otherwise
 */
public boolean receiveCard(T c){
	if (hand.indexOf(c) == -1) {
		hand.addCard(c);
		points += c.getPoints();
		return true;
	} else {
		return false;
	}
}

/**
 * Checks the players hand for a specific card and returns true if
 * card is found.
 *
 * @param	c	the card to find
 * @return		true if found, false otherwise
 */
public boolean hasCard(T c){
	if (hand.indexOf(c) == -1) {
		return false;
	} else {
		return true;
	}
}

/**
 * Checks the players hand for a specific card, removes the card, updates
 * points and returns true if the card exists in the hand. If not, just
 * returns false.
 *
 * @param	c	the card to play
 * @return		true if found, false otherwise
 */
public boolean playCard(T c){
	if (hand.indexOf(c) == -1) {
		return false;
	} else {
		hand.removeCard(c);
		points -= c.getPoints();
		return true;
	}
}

/**
 * Checks the players hand for a card at an index, removes the card, updates
 * points and returns the card if it exists at the index.
 *
 * @param	index	the index of the card to play
 * @return			the card found at the index
 */
public T playCard(int index){
	try {
		if (index >= hand.numCards() || index < 0) throw new RuntimeException("Invalid index.");
		if (hand.getCard(index) != null) {
			T tempCards[] = (T[]) new Card[1]; 
			tempCards[0] = hand.getCard(index);
			hand.removeCard(index);
			points -= tempCards[0].getPoints();
			return tempCards[0];
		}
	} catch (Exception e) {
		System.out.println("Caught error: " + e);
	}
	return null;
}

 //---------------------------------------------------
 //example test code... edit this as much as you want!
 // you will need working CardSwitch and Hand classes to run the given code
 
 
 public String toString(){
  // Not required; edit for your own testing 
  return "Player "+ name;
 }


 public static void main(String[] args) {
  CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
  CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
  CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
  Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
  Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");

  player1.receiveCard(card2);
  player1.receiveCard(card3);
  player2.receiveCard(card1);
  player1.setNext(player2);
  if (player1.getName().equals("Tom") && player1.getNext() == player2){
   System.out.println("Yay 1");
  }
  if (player1.hasCard(card2) == true && player1.getPoints() == 19){
   System.out.println("Yay 2");
  }
  if ((player2.hasNext()==false) && player1.playCard(0) == card2){
   System.out.println("Yay 3");
  }
 
 }


}