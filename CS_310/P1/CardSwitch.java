/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: CardSwitch.java
 *
 * Description: The CardSwitch class provides methods to compare and
 * identify certain cards as well as convert them to string format.
 * 
 ***************************************************************************/
public class CardSwitch extends Card {

/**
 * Constructor of class CardSwitch.
 * Calls on super class for construction.
 *
 * @param	r	rank of card
 * @param	s	suit of card
 */
public CardSwitch(Rank r, Suit s) {
	super(r, s);
}

/**
 * Compares two cards to another to test for equality.
 *
 * @param	anotherCard	a card to be compared to the object card
 * @return				true if cards are equal, otherwise false
 */
@Override
public boolean equals(Card anotherCard) {
	if (super.getRank().equals(anotherCard.getRank()) && super.getSuit().equals(anotherCard.getSuit())) return true;
	return false;
}

/**
 * Calculates points based on the number value of the card.
 *
 * @return	point value of card
 */
@Override
public int getPoints() {
	switch (super.rank) {
		case ACE: return 1;
		case TWO: return 2;
		case THREE: return 3;
		case FOUR: return 4;
		case FIVE: return 5;
		case SIX: return 6;
		case SEVEN: return 7;
		case EIGHT: return 8;
		case NINE: return 9;
		case TEN:
		case JACK:
		case QUEEN:
		case KING: return 10;
	}
	return 0;
}

/**
 * Prints out the card's rank and suit in (RANK,SUIT) format
 *
 * @return	string of card's rank and suit
 */
@Override
public String toString() {
	return ("(" + super.getRank() + "," + super.getSuit() + ")"); 
}

 //----------------------------------------------------
 //example test code... edit this as much as you want!
 public static void main(String[] args) {
  CardSwitch card = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
  
  if (card.getRank().equals(Card.Rank.ACE)){
   System.out.println("Yay 1");
  }

  if (card.toString().equals("(ACE,SPADES)")){
   System.out.println("Yay 2");
  }

  if (card.getPoints()==1){
   System.out.println("Yay 3");
  }
 }

}