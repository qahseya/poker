/*
 * This is the player class, which contains:
 * Manipulations of the player's balance
 * Manipulations of the player's hand
 */


public class Player{

	private double balance; //the money that the player has
	private Hand playerHand; //the player's hand.
	private Card [] removed; //stores 1 card that's removed from a player's hand


	public Player(double balance){
	
		this.balance = balance; //money in the player's balance
		playerHand = new Hand (); //the player's hand
	
	}

	/*
	 * Deal the card. Add one card to the deck.
	 */
	public void deal(Card c){
		playerHand.addCard(c);
		
	}

	//Returns an array of Cards that the Player wishes to discard.
	//The game engine will call deal() on this player for each card
	//that exists in the return value. 
	//MS2 Instructions: Print the hand to
	//the terminal using System.out.println and ask the user which cards 
	//they would like to discard. The user will first the number of cards they
	//wish to discard, followed by the indices, one at a time, of
	//the card(s) they would like to discard, 
	//Return an array with the appropriate Card objects
	//that have been discarded, and remove the Card objects from this
	//player's hand. Use IO.readInt() for all inputs. In cases of error
	//re-ask the user for input.
	//
	// Example call to discard():
	//
	// This is your hand:
	// 0: Ace of Hearts
	// 1: 2 of Diamonds
	// 2: 5 of Hearts
	// 3: Jack of Spades
	// 4: Ace of Clubs
	// How many cards would you like to discard?
	// 2
	// 1
	// 2
	//
	// The resultant array will contain the 2 of Diamonds and the 5 of hearts in that order
	// This player's hand will now only have 3 cards	
	/*
	 * Prompt the user to
	 */
	public Card[] discard(){
		int numberRemoved; 

		System.out.println("Here is your hand: "); // or can I use the .printhand method in the Hand class?
		for (int i = 0; i < 5; i ++) {
			System.out.println( i + ": " + playerHand.getCard(i) ); 
		}
		
//		System.out.println("How many cards would you like to remove?");
		numberRemoved = 1;
		Card [] removed = new Card [numberRemoved];

				int position = 3;
				
//				while ( position < 0 || numberRemoved > 5) { //change this to using a while loop
//					System.out.println("Sorry, this card doesn't exist. Enter another value.");
//					IO.reportBadInput();
//					position = IO.readInt();
//				} 
				removed[0] = playerHand.getCard(position);
					playerHand.removeCard(position);
//				}
				
		return removed;
		}
	
	
	

	//Returns the amount that this player would like to wager, returns
	//-1.0 to fold hand. Any non zero wager should immediately be deducted
	//from this player's balance. This player's balance can never be below
	// 0 at any time. This player's wager must be >= to the parameter min
	// MS2 Instructions: Show the user the minimum bet via the terminal 
	//(System.out.println), and ask the user for their wager. Use
	//IO.readDouble() for input. In cases of error re-ask the user for 
	//input.
	// 
	// Example call to wager()
	//

	//
	// This will result in this player's balance reduced by 200

	public double wager(double min){
		
		double wager = min + 1;
		
		
		if (wager < min) {
			wager = min +1;
		} else if (wager > balance) {
			wager = balance;
		} else if (balance - wager <= 0) {
			wager = min;
		}
	
		if (wager == -1) {
			System.out.println("You have folded.");
			return -1; //lmao how do u fold wtf
		} 
		 
		balance -= wager;
	//	System.out.println("Your wager is " + wager + "dollars. You have " + (balance) +"dollars left.");
		return wager;
	}

	//Returns this player's hand
	public Hand showHand(){ 
	//	System.out.println("showHand this is your hand: ");
		
		playerHand.printHand();
		
		return playerHand;
	
		
	}

	//Returns this player's current balance
	public double getBalance(){
		
		return balance;
		
	
	}

	//Increase player's balance by the amount specified in the parameter,
	//then reset player's hand in preparation for next round. Amount will
	//be 0 if player has lost hand
	public void winnings(double amount){
		
		
		double winnings = amount;
		
		if (winnings > 0) {
			balance += winnings;
		} else if (winnings < 0) {
			
			while (winnings < 0) {
				System.out.println("That's a bad input. You can't have negative winnings.");
				IO.reportBadInput();
				
			}
		
		}
	
		
		playerHand.clear();
		

	}
	


}