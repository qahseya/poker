
/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

import java.util.ArrayList;
import java.io.*;


public class Hand {

   private Card[] hand;   // The cards in the hand.
   private int count; //A  universal variable to count the number of cards.

   
   /**
    * Create a hand that is initially empty.
    * Each hand is made up of 5 cards. The card object is a separate class.
    */
   public Hand() {
      hand = new Card[5];
	  count = 0; //initialize this counter to 0.
   }
   
   /**
    * Iterate through the hand to remove all the cards, and 
    */
   public void clear() {
	   
      for(int i=0 ; i<hand.length; i++){ 
    	  hand[i] = null; 
    	  }
	  count = 0;
   }
   
   /**
    * Add a card to the hand.  It is added at the end of the current hand.
    * @param c the non-null card to be added.
    * @throws NullPointerException if the parameter c is null.
    */
   public void addCard(Card c) {
      
	  for(int i=0 ; i<hand.length; i++){ 
		if (hand[i] == null){
			hand[i] = c;
			count+=1;
			break;
		}
	 }

	  
   }
   
   /**
    * Search for the card in question.
    * If found, remove.
    * If it is not in the hand, nothing is done.
    */
   public void removeCard(Card c) {

	for(int i=0 ; i<hand.length; i++){ 
		if (hand[i].equals(c)){
			hand[i] = null;
			count -=1;
			
		}
	}

   }
   
   /**
    * Remove a specific card in the hand, found by its position.
    * If the position is invalid, throw an IAE error because it does not exist.
    */
   public void removeCard(int position) {
		if (position < 0 || position >= hand.length)
			throw new IllegalArgumentException("This position does not exist in hand: " + position);
		hand[position] = null;
		count--;
	}

   /**
    * simple getter method to get the number of cards in the hand.
    */
   public int getCardCount() {
      return count;
   }
   
   /**
    * GETTER method to get a specific card, identified by its location.
    * It does NOT remove the card from the deck.
    */
   public Card getCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("This position does not exist in hand: "
               + position);
       return hand[position];
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same suit are
    * grouped together, and within a suit the cards are sorted by value.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortBySuit() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            Card c1 = hand[i];
			if (c1 != null){
				if ( c1.getSuit() < c.getSuit() ||
						(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same value are
    * grouped together.  Cards with the same value are sorted by suit.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortByValue() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { 
			 nonnull = nonnull+1; continue;
			 }
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            
			Card c1 = hand[i];
            if (c1 != null){
				if ( c1.getValue() < c.getValue() ||
						(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   /*
    * Print the entire hand, starting with the first card in the deck.
    */
   public void printHand(){
	   
	   for(int i=0; i<hand.length; i++){
		   if (hand[i] != null){
			   System.out.println(hand[i]);
		   }
	   }
	   System.out.println();
   }
   
 
   /*
    * The number of pairs in the hand.
    * Theory: After sorting by value, all pairs will be next to each other.
    * That means: If 2 cards next to each other have the same value AND the third card in line does not
    * have that same value (if this were true => this would be a triplet, not a pair) then mark pair as true.
    * Return the number of pairs in the hand.
    */
   public int numPairs() {
	  this.sortByValue();
	  int numPairs = 0; 
	  //Declare 5 cards, each corresponding to the 5 cards in the hand.
	  Card c1 = hand [0];
	  Card c2 = hand [1];
	  Card c3 = hand [2];
	  Card c4 = hand [3];
	  Card c5 = hand [4];
	  
	  //first, identify the pairs. increment the counter if there are pairs.
	  if (c1.getValue() == c2.getValue() )
		  numPairs ++;
	  if (c2.getValue() == c3.getValue() )
		  numPairs ++;
	  if (c3.getValue() == c4.getValue() )
		  numPairs ++;
	   if (c4.getValue() == c5.getValue()) 
		  numPairs ++;
	  
	  //second, identify if there are any triplets. decrement the counter for every triplet found.
	 if (c1.getValue() == c2.getValue() && c2.getValue() == c3.getValue() )
   		numPairs --;
	 if (c2.getValue() == c3.getValue() && c3.getValue() == c4.getValue()) 
		 numPairs --;
	 if (c3.getValue() == c4.getValue() && c4.getValue() == c5.getValue())
		 numPairs --;
	 
	 //if there are 4 values that are the same, count these as 2 pairs.
	 //logically, when sorted by value, 4 values that are the same either begin at the first position OR the second position.
	 if (c1.getValue() == c2.getValue() && c2.getValue() == c3.getValue() && c3.getValue() == c4.getValue() ||
		  c2.getValue() == c3.getValue() && c3.getValue() == c4.getValue() && c4.getValue() == c5.getValue() )
		 numPairs ++;
		
	return numPairs;	 
   }
   /*
    * Only return true if the hand has 3 cards of the same value.
    * Uses same logic as pairs-- after sorting, three cards of the same value will
    * appear as <33345> or <45556> or <45666>. These are the only cases that need to be accounted for.
    */
   public boolean hasTriplet() {
	   //do same thing as numPairs except compare three values
	   this.sortByValue();
	   boolean hasTriplet = false;
	   Card c1 = hand [0];
	   Card c2 = hand [1];
	   Card c3 = hand [2];
	   Card c4 = hand [3];
	   Card c5 = hand [4];

	   if( c1.getValue() == c2.getValue() && c2.getValue() == c3.getValue())
		   hasTriplet = true;
	   if(c2.getValue() == c3.getValue() && c3.getValue() == c4.getValue()) 
		   hasTriplet = true;
	   if(c3.getValue() == c4.getValue() && c4.getValue() == c5.getValue())
		   hasTriplet = true;

	   return hasTriplet;	  
   }
   
//    
   /*
    * Only returns true if all the cards are of the same suit.
    * Only 1 instance to account for turning the boolean value true-- check if all the 
    * suit values in a hand of cards are the same.
    */
   public boolean hasFlush() {
	 this.sortBySuit();
	 
	 boolean hasFlush = false;
	  Card c1 = hand [0];
	  Card c2 = hand [1];
	  Card c3 = hand [2];
	  Card c4 = hand [3];
	  Card c5 = hand [4];
	 
	  if(c1.getSuit() == c2.getSuit() && 
	     c2.getSuit() == c3.getSuit() && 
	     c3.getSuit() == c4.getSuit() &&
	     c4.getSuit() == c5.getSuit()) {
		  
		  hasFlush = true;
	  }
	return hasFlush;
   }
   //Returns true if this hand has 5 consecutive cards of any suit
   public boolean hasStraight() {
	   this.sortByValue();
	   boolean hasStraight = false;
	 
	   Card c1 = hand [0];
	   Card c2 = hand [1];
	   Card c3 = hand [2];
	   Card c4 = hand [3];
	   Card c5 = hand [4];
	   
	   if((c2.getValue() - c1.getValue() == c3.getValue() - c2.getValue() &&
		   c4.getValue() - c3.getValue() == c5.getValue()-c4.getValue()  ) ||
	      (hand [0]. getValue() == Card.ACE && hand[1].getValue() == 10 && hand[2].getValue() == Card.JACK && 
	       hand [3].getValue() == Card.QUEEN &&hand [4].getValue() == Card.KING)) {
		   
		   hasStraight = true;
	   }
	   return hasStraight;
   }
//    
//   //Returns true if this hand has a triplet and a pair of different 
//   //values
   public boolean hasFullHouse() {
	  boolean hasFullHouse = false;
	  this.sortByValue();
	  Card c1 = hand[0];
	  Card c2 = hand[1];
	  Card c3 = hand[2];
	  Card c4 = hand[3];
	  Card c5 = hand[4];
	  
	  /*
	   * since these cards are sorted by value, triplets will always appear right next to each other because their values are the same.
	   * therefore, there are 3 cases when triplets can exist next to each other in a hand:
	   * as the first, second, and third card... as the second, third, and fourth card... and the third, fourth, and fifth card.
	   * the only other condition you need to check is that the other cards don't equal each other. 3 if statements should do the trick.
	   */
	  
	  //triplet is in the 1st, 2nd, and 3rd card:
	  if (c1.getValue() == c2.getValue() && 
	      c2.getValue() == c3.getValue() && 
	      c3.getValue() != c4.getValue() && 
	      c4.getValue() == c5.getValue() ) {
		  
		  hasFullHouse = true;
		  
	  } else if (c2.getValue() == c3.getValue() && 
			     c3.getValue() == c4.getValue() && 
			     c1.getValue() != c2.getValue() && 
			     c4.getValue() != c5.getValue() && 
			     c1.getValue() == c5.getValue() ) {
		  
		  hasFullHouse = true;
		  
	  } else if (c3.getValue() == c4.getValue() && 
			     c4.getValue() == c5.getValue() &&
			     c2.getValue() != c3.getValue() &&
			     c1.getValue() == c2.getValue() ) {
		  
		  hasFullHouse = true;
		  
	  }
	  
	  return hasFullHouse;
   }
//   //Returns true if this hand has 4 cards that are of the same value
   public boolean hasFourOfAKind() {
	   boolean hasFourOfAKind = false; //does it make a difference if i put this before/after the sortByValue statement?
	   this.sortByValue();
	  
	   Card c1 = hand [0];
	   Card c2 = hand [1];
	   Card c3 = hand [2];
	   Card c4 = hand [3];
	   Card c5 = hand [4];
	   
	   if (c1.getValue() == c2.getValue() &&
		   c2.getValue() == c3.getValue() &&
		   c3.getValue() == c4.getValue()) {
		   
		   hasFourOfAKind = true; 
		   
	   } else if ( c2.getValue() == c3.getValue() &&
			       c3.getValue() == c4.getValue() &&
			       c4.getValue() == c5.getValue() ) {
		   
		   hasFourOfAKind = true;
		   
	   }
	   
	   return hasFourOfAKind;
   }
//    
//   //Returns the card with the highest value in the hand. When there is
//   //more than one highest value card, you may return any one of them
   public Card highestValue() {
	   this.sortByValue();
	   Card highest;
	   Card lowest = hand [0];
	   
	   if ( lowest.getValue() == 1 ) {
		   
		    highest = hand [0];
		    
	   } else {
		   
		    highest = hand [4];
	   }
	   
	   return highest;
	   
	   
   } 			 
//    
//   //Returns the highest valued Card of any pair or triplet found, -1 if none. When values are equal, you may return either
   public Card highestDuplicate() {
	   
	   this.sortByValue();
	   Card highest = this.getCard(4); 
	   Card lowest = this.getCard(0);
	   
	   if (this.numPairs() == 0 && this.hasTriplet() == false) {
		   return null;
	   }
	   
	   if (this.numPairs() == 1 && this.hasTriplet() == true) {
		   if (this.getCard(4).getValue() == this.getCard(3).getValue()) {
			   highest = hand [4];

		   } else  if (this.getCard(0).getValue() == this.getCard(1).getValue()) {
			 // System.out.println("Ayesha!!!");
			   highest = hand [0];

		   } else   if (this.getCard(1).getValue() == this.getCard(3).getValue()) {
			   highest = hand [2];
		   }
	   }
	    if (this.numPairs() > 1 && this.hasTriplet() == true) {
		   if ( lowest.getValue() == 1 ) {
			   
			    highest = hand [0];
			    
		   } else {
			//   System.out.println("Ayesha");
			    highest = hand [4];
		   }
		  
	   }
	   
	   
	   if (this.numPairs() == 2 && this.hasTriplet() == false) {
		   if (this.getCard(0).getValue() == this.getCard(1).getValue() && this.getCard(0).getValue() != 1 ) {
			   highest = hand [2];
		   } else if (this.getCard(0).getValue() == this.getCard(1).getValue() && this.getCard(0).getValue() == 1){
				   highest = hand [0];
			   }
		   
	   
		   if (this.getCard(1).getValue() == this.getCard(2).getValue() && this.getCard(1).getValue() != 1) {
			   highest = hand [4];  
		   } else if (this.getCard(1).getValue() == this.getCard(2).getValue() && this.getCard(1).getValue() == 1) {
			   highest = hand [1];
		   }
	   }
		   
		   return highest;
	   }
	   
 
   //Returns -1 if the instance hand loses to the parameter hand, 0 if 
//   //the hands are equal, and +1 if the instance hand wins over the 
//   //parameter hand. Hint: you might want to use some of the methods //above
   public int compareTo(Hand h) {
	   this.sortByValue();
	   h.sortByValue();
	   int hLoner = 2; int thisLoner = 3;
	   int hHighest = 4;  int thisHighest = 5;
	   int thisPair = 6; int thisTriplet = 7;
	   int hPair = 8; int hTriplet = 9;
	   int thisPair1 = 10; int thisPair2 = 11; int hPair1 = 12; int hPair2 = 13; int thisHighestPair = 14; int hHighestPair = 15; 
	   
	   
	   //STRAIGHT FLUSH
	   //if both have a straight flush:
	   if ( (h.hasStraight() == true && h.hasFlush() == true) && (this.hasStraight() == true && this.hasFlush() == true) ) {
		   //this.sortByValue(); h.sortByValue();
		   if ( h.getCard(4).getValue() > hand[4].getValue() ) {
		//	   System.out.println("a");
			   return 1;
		   } else if (h.getCard(4).getValue() < hand[4].getValue() ) {
			   return -1;
		   } else {
			   return 0;
		   }
	   }
	   // h has the straight flush
	   if ( (h.hasStraight() == true && h.hasFlush() == true) && (this.hasStraight() !=true || this.hasFlush() != true)) {
		//   System.out.println("b");
		   return 1;
	   // this has the straight flush
	   } else if  ( (h.hasStraight() != true || h.hasFlush() != true) && (this.hasStraight() == true && this.hasFlush() == true)) {
		   return -1;
	   } 
	   
	   
	   //FOUR OF A KIND
	   //if both have four of a kind: 
	   if (h.hasFourOfAKind() == true && this.hasFourOfAKind() == true) {
		   
		   if ( (h.getCard(0).getValue() == h.getCard(1).getValue()) || (h.getCard(0).getValue() == 1 && h.getCard(1).getValue() == 1) ) {
			   hHighest = h.getCard(0).getValue(); 
			   hLoner = h.getCard(4).getValue();
		   } else if ( (h.getCard(3).getValue() == h.getCard(4).getValue()) || (h.getCard(3).getValue() == 1 && h.getCard(4).getValue() == 1) ) {
			   hHighest = h.getCard(3).getValue();
			   hLoner = h.getCard(0).getValue();
		   }
		   
		   if ( (this.getCard(0).getValue() == this.getCard(1).getValue()) || (this.getCard(0).getValue() == 1 && this.getCard(1).getValue() == 1) ) {
			   thisHighest = this.getCard(0).getValue();
			   thisLoner = this.getCard(0).getValue();
		   } else if (this.getCard(3).getValue() == this.getCard(4).getValue() || 
				   (this.getCard(3).getValue() == 1 && this.getCard(4).getValue() == 1 ) ) {
			   thisHighest = this.getCard(3).getValue();
			   thisLoner = h.getCard(0).getValue();
		   }
		   
		   if (thisHighest == hHighest) {
			   if (thisLoner > hLoner)
				   return -1;
			   else if (hLoner > thisLoner) {
			//	   System.out.println("c");
				   return 1;
			   }
			   else if (hLoner == thisLoner)
				   return 0;
			   
		   } else if (thisHighest > hHighest || thisHighest == 1) {
			   return -1;
		   } else if (hHighest > thisHighest || hHighest == 1)
			//   System.out.println("d");
			   return 1;
		  
	   }
	   // h has the four of a kind
	   if (this.hasFourOfAKind() == true && h.hasFourOfAKind() == false) {
		   return -1;
		   
	   // this has the four of a kind
	   } else if (this.hasFourOfAKind() == false && h.hasFourOfAKind() == true ) {
		//   System.out.println("e");
		   return 1;
		   
	   } //END OF FOUR OF A KIND
	   
	   
	   
	   //FULL HOUSE
	   // if both have a full house. there's two options: one where there's no pairs also, and one where there are pairs.
	   //if there are no double pairs
	   if (this.hasFullHouse() == true && h.hasFullHouse() == true) {
		   int hHighestValue = h.getCard(2).getValue();
		   if (hHighestValue == this.highestDuplicate().getValue() ) {
			 //  if (this.)
			   return 0;
		   } 
		    if (hHighestValue > this.highestDuplicate().getValue()) {
			//   System.out.println("f");
			   return 1;
		    }
		   else 
			   return -1;
	   }
	   // if there are double pairs 
	   if ((this.hasTriplet() == true && this.numPairs() > 0) && (h.hasTriplet() == true && h.numPairs() > 0)) {
		   if (this.getCard(0).getValue() == this.getCard(2).getValue()) {
			   thisTriplet = this.getCard(1).getValue(); 
			   thisPair = this.getCard(4).getValue();
			  
		   } else if (this.getCard(2).getValue() == this.getCard(4).getValue()) {
			   thisTriplet = this.getCard(3).getValue();
			   thisPair = this.getCard(0).getValue();
		   }
		   if (h.getCard(0).getValue() ==  h.getCard(2).getValue()) {
			  hTriplet = h.getCard(1).getValue();
			  hPair = h.getCard(4).getValue();
		   } else if (h.getCard(2).getValue() == h.getCard(4).getValue()) {
			   hTriplet = h.getCard(3).getValue();
			   hPair = h.getCard(0).getValue();
		   }
		   
		   if (thisTriplet == hTriplet) {
			   if (thisPair > hPair) {
				   return -1;
			   } else if (hPair > thisPair) {
			//	   System.out.println("g");
				   return 1; 
			   } else if (hPair == thisPair)
				   return 0;
		   }
		   if (thisTriplet > hTriplet)
			   return -1;
		   if (hTriplet > thisTriplet)
			   return 1;
		   
	   } 
	   
	   
	   //FLUSH
	   // if this has flush and h doesn't:
	   if (this.hasFlush() == true && h.hasFlush() == false)
		   return -1;
	   if (this.hasFlush() == false && h.hasFlush() == true) {
		//   System.out.println("i");
		   return 1;
	   }
	   
	   if (this.hasFlush() == true && h.hasFlush() == true) {
		   if ( this.highestValue().getValue() > h.highestValue().getValue() ) {
			   return -1;
		   } else if ( h.highestValue().getValue() > this.highestValue().getValue() ) {
			//   System.out.println("j");
			   return 1;
		   } else if (h.highestValue().getValue() == this.highestValue().getValue()) {
			   return 0;
		   }
	   } 
	   
	   // STRAIGHT
	   
	   //if this has straight
	   if (this.hasStraight() == true && h.hasStraight() == false)
		   return -1;
	   //if h has a straight
	   else if (this.hasStraight() == false && h.hasStraight() == true) {
		 //  System.out.println("k");
		   return 1;
	   }
	   // if both have straights
	   if (this.hasStraight() == true && h.hasStraight() == true) {
		   if (this.highestValue().getValue() > h.highestValue().getValue())
			   return -1;
		   if (this.highestDuplicate().getValue() < h.highestValue().getValue()) {
			//   System.out.println("l");
			   return 1;
		   }
		   else if (this.highestDuplicate().getValue() == h.highestValue().getValue()) 
			   return 0;
	   }   //END OF STRAIGHT
	   
	   
	   //THREE OF A KIND
	   
	   //if only one of them has a triplet
	   if (this.hasTriplet() == true && h.hasTriplet() == false)
		   return -1;
	   
	   if (this.hasTriplet() == false && h.hasTriplet() == true) {

		   return 1;
	   }
	   else if (this.hasTriplet() == true && h.hasTriplet() == true) {  //if both have triplets
		   //if h is bigger
		   if (h.getCard(2).getValue() == this.getCard(2).getValue()) //if there is a tie
			   return 0;
		   else if (h.getCard(2).getValue() > this.getCard(2).getValue()) { //if h is greater
			//   System.out.println("n");
			   return 1;
		   }
		   else if (this.getCard(2).getValue() > h.getCard(2).getValue()) //if this is greater
			   return -1;
		   //accounting for ace
		   else if (this.getCard(2).getValue() == 1) 
			   return -1;
		   else if (h.getCard(2).getValue() == 1) {
			 //  System.out.println("o");
			   return 1;
		   }   
	   }
	   
	   //TWO PAIR
	   
	   if ( (this.numPairs() == 2 && this.hasTriplet() == false) && (h.numPairs() == 2 && h.hasTriplet() == false) ) {
		   
			   
			   thisHighestPair = this.highestDuplicate().getValue();
			   hHighestPair = h.highestDuplicate().getValue();
			   
			   if (thisHighestPair == hHighestPair)
				  return 0;
			   else if (hHighestPair > thisHighestPair || hHighestPair == 1) {
				  // System.out.println("q");
				   return 1;
			   }
			   else if (thisHighestPair > hHighestPair || thisHighestPair == 1) 
				   return -1;
			   
			   
		   }
	   
	   
	   //ONE PAIR
	   if (this.numPairs() == 1 && this.hasTriplet() == false && h.numPairs() == 1 && this.hasTriplet() == false) {
		   if (this.getCard(0).getValue() == this.getCard(1).getValue()) {
			   thisPair = this.getCard(0).getValue(); thisLoner = this.getCard(4).getValue();
		   }
		   if (h.getCard(0).getValue() == h.getCard(1).getValue()) {
			   hPair = h.getCard(0).getValue(); hLoner = h.getCard(4).getValue();
		   }
		   if (this.getCard(1).getValue() == this.getCard(2).getValue()) {
			   thisPair = this.getCard(1).getValue(); thisLoner = this.getCard(4).getValue();
		   }
		   if (h.getCard(1).getValue() == h.getCard(2).getValue()) {
			   hPair = h.getCard(1).getValue(); hLoner = h.getCard(4).getValue();
		   }
		   if (this.getCard(2).getValue() == this.getCard(3).getValue()) {
			   thisPair = this.getCard(2).getValue(); thisLoner = this.getCard(4).getValue();
		   }
		   if (h.getCard(2).getValue() == h.getCard(3).getValue()) {
			   hPair = h.getCard(2).getValue(); hLoner = h.getCard(4).getValue();
		   }
		   if (this.getCard(3).getValue() == this.getCard(4).getValue()) {
			   thisPair = this.getCard(3).getValue(); thisLoner = this.getCard(2).getValue();
		   }
		   if (h.getCard(3).getValue() == h.getCard(4).getValue()) {
			   hPair = h.getCard(3).getValue(); hLoner = h.getCard(2).getValue();
		   }
		   if (thisPair > hPair || thisPair ==1)
			   return -1;
		   if (thisPair < hPair || hPair ==1) {
			  // System.out.println("r");
			   return 1;
		   }
		   if (thisPair == hPair) {
			   if (thisLoner > hLoner || thisLoner == 1)
				   return -1;
			   if (thisLoner < hLoner || hLoner ==1) {
				  // System.out.println("s");
				   return 1;
			   }
			   if (thisLoner == hLoner)
				   return 0;
		   }
		   
		   //HIGHEST CARD
		   
		   if (this.highestValue().getValue() > h.highestValue().getValue()) 
			   return -1;
		   if (this.highestValue().getValue() < h.highestValue().getValue()) {
			   //System.out.println("t");
			   return 1;
		   }
		   if (this.highestValue().getValue() == h.highestValue().getValue())
			   return 0;
		   
		   
		   
		   
		   
	   }
	   
	   
	   return 3;
	   }

}