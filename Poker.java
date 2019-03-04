/**
 * Ayesha Qureshi, Rutgers University 
 * This project is a poker game that runs in the console.
 * It is coded to be as simple as possible, relying on object oriented programming
 * to run a game that can be played between 2 different players.
 * 
 /*
  * Instructions: Make sure this file, poker.java into the same directory as IO.java, Hand.java, Card.java, and Player.java
  * Compile via: javac Poker.java
  * After compiling, run via: java Poker
  */


import java.util.Random; // We import Random for shuffling cards in between hands. In the instance of creating an AI player, this can be used.
import java.io.*;


public class Poker{

	private static Random rand = new Random();

	public static void main(String[] args){
		
		// Create two players, which will both start with a balance of $100.00
		Player player1 = new Player(100.00);
		Player player2 = new Player(100.00);

		gameLoop(player1, player2);

	}

	// Until the user prompts the game to end, this loop will continue to run.
	public static void gameLoop(Player p1, Player p2){
		Card[] playerdeck = getDeck();
		double player1Wager = 0, player2Wager = 0;
		do{
			displayBalances(p1, p2);
			System.out.println();

			
			shuffle(playerdeck);
			dealFullHand(playerdeck, p1);
			dealFullHand(playerdeck, p2);

			// Now we get how much player one wants to bet.
			// IF player one folds, then nothing happens and we try to play another hand.
			System.out.println("=======================================");
			System.out.println("Player 1, it is your turn.");
			player1Wager = getWager(p1, 0);

			if(player1Wager == -1){
				System.out.println("Play another hand? [y/n]");
				p2.winnings(0);
				p1.winnings(0);
				continue;
			}
			System.out.println("=======================================");
			System.out.println();

			// Now we ask player two to make a bet of equivalent value.
			// IF they fold, we have to reimburse player 1 with the money we took from them for their bet. 
			System.out.println("=======================================");
			System.out.println("Player 2, it is your turn.");
			player2Wager = getWager(p2, player1Wager);

			if(player2Wager == -1){
				System.out.println("Player 2 folded");
				System.out.println("Play another hand? [y/n]");
				p2.winnings(0);
				p1.winnings(player1Wager);
				continue;
			}
			System.out.println("=======================================");
			System.out.println();

			// Now, we ask each player if they would like to discard any cards, and redeal the number of cards that they do discard.
			askdiscardCardsAndDealNew(p1, p2, playerdeck);

			// After redealing, we are now able to display the hands and determine who won.
			System.out.println("\n\nHere are your final hands:");

			System.out.println("=======================================");
			System.out.println("Player 1:");
			p1.showHand().printHand();
			System.out.println("=======================================");
			System.out.println();

			System.out.println("=======================================");
			System.out.println("Player 2:");
			p2.showHand().printHand();
			System.out.println("=======================================");
			System.out.println();

			int result = p1.showHand().compareTo(p2.showHand());
			if(result == 0){
				System.out.println("A tie!");
				p1.winnings(player1Wager);
				p2.winnings(player2Wager);
			}else if(result < 0){
				System.out.println("Player 2 wins!");
				p1.winnings(0);
				p2.winnings(player1Wager + player2Wager);
			}else if(result > 0){
				System.out.println("Player 1 wins!");
				p1.winnings(player1Wager + player2Wager);
				p2.winnings(0);
			}
			System.out.println("\n\n");

			System.out.println("Play another hand? [y/n]");
		}while((IO.readString()).toLowerCase().equals("y"));
	}

	// Prints out the balances of each player at the current time.
	public static void displayBalances(Player p1, Player p2){
		System.out.println("=======================================");
		System.out.println("Player 1 balance: " + p1.getBalance());
		System.out.println("Player 2 balance: " + p2.getBalance());
		System.out.println("=======================================");
	}

	// Asks each player what cards they want to discard, then discards them, and then deals new ones.
	public static void askdiscardCardsAndDealNew(Player p1, Player p2, Card[] cards){
			System.out.println("=======================================");
			System.out.println("Player 1, it is your turn.");
			discardCardsAndDealNew(cards, p1);
			System.out.println("=======================================");
			System.out.println();

			System.out.println("=======================================");
			System.out.println("Player 2, it is your turn.");
			discardCardsAndDealNew(cards, p2);
			System.out.println("=======================================");
			System.out.println();

	}

	// Gets the cards from the player that they want to get rid of and actually does the getting rid of them.
	public static void discardCardsAndDealNew(Card[] deck, Player player){
		Card[] cards = player.discard();
		for(int i = 0; i < cards.length; i ++){
			player.deal(getNextCard(deck));	
		}
	}

	// Shows the user their hand and then asks them for their wager.
	// Will return the amount that they wagered so that we can keep track of it for winnings.
	public static double getWager(Player player, double minimum){
		player.showHand().printHand();
		return player.wager(minimum);
	}

	// Deals enough cards to the player such that they have a full hand.
	public static void dealFullHand(Card[] deck, Player player){
		int numCards = 5 - player.showHand().getCardCount();
		for(int i = 0; i < numCards; i ++){
			player.deal(getNextCard(deck));
		}
	}

	// A placeholder so that we can keep returning the next card that needs to be dealt, without modifying the array for every withdraw.
	private static int cardPos = 0;

	// Returns the next card to deal to the player.
	public static Card getNextCard(Card[] cards){
		return cards[cardPos ++];
	}

	// Shuffles the cards by going through each one and moving it to some random position in the deck.
	// Also resets our position, such that we pull from the 0th index on the next getNextCard call.
	public static void shuffle(Card[] cards){
		cardPos = 0;
		for(int i = 0; i < 52; i ++){
			int newPos = rand.nextInt(52);
			Card temp = cards[i];
			cards[i] = cards[newPos];
			cards[newPos] = temp;
		}
	}

	// Returns the standard 52 card deck of AS,2S,...,KS,AH,....,KH,....
	public static Card[] getDeck(){
		Card[] cards = new Card[52];
		int pos = 0;

		for(int i = 1; i <= 13; i ++){
			for(int j = 0; j <= 3; j ++){
				Card c = new Card(i, j);
				cards[pos ++] = c;
			}
		}

		return cards;
	}

}