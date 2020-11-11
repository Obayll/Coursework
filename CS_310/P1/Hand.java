/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Hand.java
 *
 * Description: The Hand class contains methods which allow access to the
 * cards in a players hand as well as mutating the list in which they
 * exist. Provides information such as the index of a card and whether or
 * not a card exists in a hand.
 * 
 ***************************************************************************/
public class Hand<T extends Card>{

private T [] cards;
private int numCards;

/**
 * Constructor of class Hand.
 * Creates a new Card object with default size 5.
 */
public Hand(){
	this.cards = (T[]) new Card[5];
}

/**
 * Returns the variable numCards when called.
 *
 * @return	numCards variable
 */
public int numCards(){
	return numCards;
}

/**
 * Returns the card at the specified index.
 *
 * @param	index	the index of the card to be returned
 * @return			card at given index
 */
public T getCard(int index){
	try {
		if (index >= numCards || index < 0) throw new RuntimeException("Invalid index.");
		return cards[index];
	} catch (Exception e) {
		System.out.println("Caught error: " + e);
	}
	return null;
}

/**
 * Places a card at a given index.
 *
 * @param	index	the index of the card to be set
 */
public void setCard(int index, T c){
	try {
		if (index >= numCards || index < 0) throw new RuntimeException("Invalid index.");
		cards[index] = c;
	} catch (Exception e) {
		System.out.println("Caught error: " + e);
	}
}

/**
 * Adds a card to the list of cards. Makes room if no slots available
 *
 * @param	c	the card to be added
 */
public void addCard(T c){
	if (cards[cards.length - 1] != null) {
		T tempCards[] = (T[]) new Card[cards.length + 1];
		for (int i = 0; i < cards.length; i++) tempCards[i] = cards[i];
		cards = (T[])tempCards;
		cards[cards.length - 1] = c;
	}
	for (int i = 0; i < cards.length; i++) {
		if (cards[i] == null) {
			cards[i] = c;
			break;
		}
	}
	numCards++;
}

/**
 * Returns the index of the certain card. If card does not exist, returns -1.
 *
 * @param	c	the card to find the index of
 * @return		1 if card if found, -1 otherwise
 */
public int indexOf(T c){
	for (int i = 0; i < cards.length; i++) {
		if (cards[i] == null) continue;
		if (cards[i].equals(c)) return i;
	}
	return -1;
}

/**
 * Removes a card at a given index and returns it.
 *
 * @param	index	the index of the card to be removed
 * @return			the removed card
 */
public T removeCard(int index){
	try {
		if (index >= numCards || index < 0) throw new RuntimeException("Invalid index.");
		T tempCards[] = (T[]) new Card[1]; 
		tempCards[0] = cards[index];
		cards[index] = null;
		for (int i = 0; i < cards.length - 1; i++) {
			if (cards[i] == null) {
				cards[i] = cards[i + 1];
				cards[i + 1] = null;
			}
		}
		numCards--;
		return tempCards[0];
	} catch (Exception e) {
		System.out.println("Caught error: " + e);
	}
	return null;
}

/**
 * Removes a specific card and returns true if successful. Returns false
 * if the card was not found.
 *
 * @param	c	the card to be removed
 * @return		true if removed card, otherwise false
 */
public boolean removeCard(T c){
	boolean rVal = false;
	for (int i = 0; i < cards.length; i++) {
		if (cards[i].equals(c)) {
			cards[i] = null;
			rVal = true;
			break;
		}
	}
	for (int i = 0; i < cards.length - 1; i++) {
		if (cards[i] == null) {
			cards[i] = cards[i + 1];
			cards[i + 1] = null;
		}
	}
	numCards--;
	return rVal;
}

 // --------------------------------------------------------
 // example test code... edit this as much as you want!
 // you will need a working CardSwitch class to run the given code


 // Not required, update for your testing purpose
 @Override
 public String toString(){
  // return string representation of hand
  // update if you want to include information for all cards in hand
  return "Hand with "+numCards+" cards";
  
  
   }


 public static void main(String[] args) {
 
  CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
  CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
  CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
  
  
  Hand<CardSwitch> myHand = new Hand<CardSwitch>();
  myHand.addCard(card1);
  myHand.addCard(card2);
  
  if ((myHand.numCards() == 2) && (myHand.getCard(0).equals(card1))){
   System.out.println("Yay 1");
  }
  
  myHand.addCard(card3);
  
  if (card2.equals(myHand.removeCard(1)) && myHand.getCard(1).equals(card3)){
   System.out.println("Yay 2");
  }

  if ((myHand.indexOf(card1)==0) && (myHand.indexOf(card2) == -1 )){
   System.out.println("Yay 3");
  }

 }


}