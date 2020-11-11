/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Board.java
 *
 * Description: Abstract Board class from which a board class specific to
 * any game can be constructed. This file SHOULD NOT be modified.
 * 
 ***************************************************************************/

public abstract class Board<T extends Card> {
	
	protected Player<T> currentPlayer;
	protected int numPlayer;
	protected Deck<T> deck;
	
	
	public Board(Deck<T> deck){
		this.currentPlayer = null;
		this.numPlayer = 0;
		this.deck = deck;
	}
	
	abstract Player<T> getCurrentPlayer();
	
	abstract int getNumPlayers();
	
	abstract Deck<T> getDeck();
	
	abstract boolean changeTurn();
	
	abstract void addPlayer(Player<T> x);

}
