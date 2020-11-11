
/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Card.java
 *
 * Description: Abstract Card class from which a card class specific to
 * any game can be constructed. This file SHOULD NOT be modified.
 * 
 ***************************************************************************/

public abstract class Card {
	
	enum Rank{
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	}

	enum Suit{
		HEARTS, CLUBS, DIAMONDS, SPADES;
	}
	
	protected Rank rank;
	protected Suit suit;
	
	public Card(Rank r, Suit s){
		rank = r;
		suit = s;
	}

	public Rank getRank(){
		return rank;
	}
	
	public Suit getSuit(){
		return suit;
	}
	
	abstract boolean equals(Card c);
    
    abstract int getPoints();
		
	@Override
	public abstract String toString();

}