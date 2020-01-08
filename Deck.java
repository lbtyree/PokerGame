/*Larissa Tyree
 * lbt2116
 * COMS-W1004
 * Poker Game 
 * April 2019
 * */
import java.util.ArrayList; 
import java.util.Collections;
public class Deck {
	
	 
	private int top; // the index of the top of the deck
    private ArrayList<Card> cards = new ArrayList<Card>();//new deck 
	
	public Deck(){
        
    //Initialize instance variables
     int cardtotal = 0; //number of cards in deck 
     top=0; // top of deck 
        
	// make a 52 card deck here
    // Add each of the 52 cards to deck
      int s=1; 
      while(s<5){ // 4 suits total  
         for (int c=1; c<14; c++){  // 13 cards of each suit 
             //new object of Card class w/ suite & rank 
             Card thisCard = new Card(s,c); 
             cards.add(thisCard); //adds new card to the deck
            }
           s++; // move to next suit 
        }
	}
	
	public void shuffle(){
		 Collections.shuffle(cards); //shuffle cards 
        
        }
	
	
	public Card deal(){
		// deal the top card in the deck
     if(top>51){ // if on 52nd card
        top++;// increase value of top 
        return cards.get(0); //return 52nd card
          }else{
          Card dealtCard = cards.get(top); //deal top
          top++; //add one to value of top 
          return dealtCard; //return the dealt card
          }
     }
}
	
	


