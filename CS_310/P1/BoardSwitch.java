/**************************************************************************
 * @author Jordan Ditzler
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: BoardSwitch.java
 *
 * Description: The BoardSwitch class that acts as a basis on how players
 * interact with one another. Oversees the adding of players, changing
 * current turn, and finding the winner at the end of the game.
 * 
 ***************************************************************************/
public class BoardSwitch<T extends Card> extends Board<T>{

/**
 * Constructor of class BoardSwitch.
 * Calls on super class for construction.
 *
 * @param	deck	the deck to use while playing
 */
public BoardSwitch(Deck<T> deck) {
	super(deck);
}

/**
 * Returns the variable currentPlayer when called.
 *
 * @return	currentPlayer variable
 */
@Override
public Player<T> getCurrentPlayer() {
	return currentPlayer;
}

/**
 * Returns the variable numPlayer when called.
 *
 * @return	numPlayer variable
 */
@Override
public int getNumPlayers() {
	return numPlayer;
}

/**
 * Returns the variable deck when called.
 *
 * @return	deck variable
 */
@Override
public Deck<T> getDeck(){
	return deck;
}

/**
 * Changes to currentPlayer to the next in the list, if there is one.
 * Returns true if successful, otherwise returns false.
 *
 * @return	true if there is a next node in the list, otherwise false
 */
@Override
public boolean changeTurn() {
	if (currentPlayer.hasNext()) {
		currentPlayer = currentPlayer.getNext();
		return true;
	}
return false;
}

/**
 * Adds a player to the left of the current node. Links in a circular
 * pattern, so no need for a head/tail. Updates numPlayer accordingly.
 *
 * @param	x	the player to add to the circular linked list
 * @return		true if there is a next node in the list, otherwise false
 */
@Override
public void addPlayer(Player<T> x) {
	if (getNumPlayers() == 0) {
		currentPlayer = x;
		x.setNext(x);
		numPlayer++;
	} else {
		Player<T> prevNode = this.currentPlayer;
		for (int i = 0; i < getNumPlayers(); i++) {
			if (prevNode.getNext().equals(this.currentPlayer)) {
				x.setNext(currentPlayer);
				prevNode.setNext(x);
			}
		prevNode = prevNode.getNext();  
		}
	numPlayer++;
	}
}

/**
 * Finds the winner based on the points of each players hand. The player
 * with the highest score wins.
 *
 * @return	player with the highest score in the list
 */
public Player<T> findWinner(){
	Player<T> highPlayer = this.currentPlayer;
	Player<T> tempPlayer = this.currentPlayer;
	for (int i = 0; i < getNumPlayers(); i++) {
		if (tempPlayer.getPoints() > highPlayer.getPoints()) {
			highPlayer = getCurrentPlayer();
		}
		tempPlayer = tempPlayer.getNext();
	}
	return tempPlayer;
}

 //-----------------------------------------------------
 // example test code... edit this as much as you want!
 // you will need working CardSwitch, Hand, Player, Deck and PlaySwitch classes to run the given code
 
 public static void main(String[] args) {
  Deck<CardSwitch> deck = new Deck<CardSwitch>();
  PlaySwitch.init_deck(deck);
   
  BoardSwitch<CardSwitch> myBoard = new BoardSwitch<CardSwitch>(deck);
  Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
  Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");

  myBoard.addPlayer(player1);
  
  if (myBoard.getNumPlayers() ==1 && myBoard.getCurrentPlayer() == player1
   && player1.getNext() == player1){
   System.out.println("Yay 1");
  }

  myBoard.addPlayer(player2);
  if (myBoard.getNumPlayers() ==2  && myBoard.getCurrentPlayer() == player1
   && (myBoard.changeTurn()==true) && myBoard.getCurrentPlayer() == player2){
   System.out.println("Yay 2");
  }
  
  player1.receiveCard(new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES));
  player1.receiveCard(new CardSwitch(Card.Rank.JACK, Card.Suit.CLUBS));
  player2.receiveCard(new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS));
  player2.receiveCard(new CardSwitch(Card.Rank.THREE, Card.Suit.SPADES));

  if (player1.getNext() == player2 && player2.getNext() == player1
   && myBoard.findWinner() == player2){
   System.out.println("Yay 3");
  }
  
 
 }
 

}