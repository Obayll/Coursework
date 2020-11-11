/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: PlaySwitch.java
 *
 * Description: This file SHOULD NOT be modified. This provides the 
 * implementation of a simple game with cards.  The game follows these steps:
 * 1. Creating a full deck of 52 cards
 * 2. Creates players and adds them to board
 * 3. Deal all cards to users one by one (all players may not receive same number of cards)
 * 4. Switch the first n cards between the players
 * 5. Player with the higher total points of all cards in hand is the winner
 * 
 ***************************************************************************/
import java.util.Scanner;

class PlaySwitch{

		// initialize deck of 52
		public static void init_deck(Deck<CardSwitch> deck){
			for(Card.Suit s: Card.Suit.values()) {
				for(Card.Rank r: Card.Rank.values()) {
					CardSwitch card = new CardSwitch(r,s);
					deck.addCard(card);
				}
			}
			deck.shuffle();
		}
		
		//create players
		public static void init_players(BoardSwitch<CardSwitch> myBoard){
			
			Scanner input = new Scanner(System.in);
			System.out.print("Enter the number of players: ");
			int n = input.nextInt();
			for(int i=0; i<n; i++) {
				System.out.println("Enter Name of Player "+(i+1)+": ");
				String name = input.next();
				myBoard.addPlayer(new Player<CardSwitch>(name));
			}
			
		}
		
		// deal to players, each gets numToDeal cards 
		public static void dealCards(BoardSwitch<CardSwitch> myBoard){
			while(!myBoard.getDeck().isEmpty()) {
					CardSwitch card = myBoard.getDeck().dealNextCard();
					myBoard.getCurrentPlayer().receiveCard(card);
					myBoard.changeTurn();
			}
		}
		
		public static void playRound(BoardSwitch<CardSwitch> myBoard){
			int numPlayer = myBoard.getNumPlayers();
			
			//switch one card between users
			for (int i=0; i<numPlayer; i++){
				CardSwitch card = myBoard.getCurrentPlayer().playCard(0);
				System.out.print("switch from " + myBoard.getCurrentPlayer().getName()+": ");
				System.out.print(" card " + card+", ");
				myBoard.changeTurn();
				myBoard.getCurrentPlayer().receiveCard(card);
				System.out.println(" switch to " + myBoard.getCurrentPlayer().getName());	 
			}						
		}
		

		public static void main(String[] args) {
		
			Deck<CardSwitch> deck = new Deck<CardSwitch>();
			init_deck(deck);
			
			BoardSwitch<CardSwitch> myBoard = new BoardSwitch<CardSwitch>(deck);
 
			init_players(myBoard);
		
			Scanner input = new Scanner(System.in);
		
			// deal all cards
			dealCards(myBoard);
			
			System.out.println("How many cards should be switched?");
			int numSwitches = input.nextInt();
			
			//switch cards
			System.out.println("-----------------------------------");
			for(int i=0; i<numSwitches; i++) {
				System.out.println("-Starting ROUND "+(i+1)+"-");
				playRound(myBoard);
			}

			System.out.println("-----------------------------------");


			Player<CardSwitch> winner = myBoard.findWinner();
			System.out.println("Winner is: "+winner.getName()+" with "+winner.getPoints() + " points" );

	}

}