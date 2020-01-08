/*Larissa Tyree
 * lbt2116
 * COMS-W1004
 * Poker Game 
 * April 2019
 * */
import java.util.ArrayList; 
public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll; // total of tokens left 
        private double bet; // total tokens bet (tracking bets)

	// you may choose to use more instance variables
		
	public Player(){		
	    // create a player here
	    hand= new ArrayList<Card>(); // initialize array list  
        bet = 0; //player yet to bet anything 
	}

	public void addCard(Card c){
	    // add card c to player's hand
	    hand.add(c);       
	   }

	   public void removeCard(Card c){
	      // remove the card c from the player's hand
	      hand.remove(c);
        
        }
		
        public void bets(double amt){
            // player makes a bet
            bankroll = bankroll - amt; //bankroll goes down by bet 
        }
        public double getBankroll(){ //used 
            // return current balance of bankroll
            return bankroll;
        }
        public void winnings(double odds){
            //adjust bankroll if player wins
            bankroll = bankroll + odds; 
            // player may or may not bet these winnings
        }
        public double initialBankroll(double initial ){ 
            // used to set every player's bankroll to 5 
            // at very beginning of very first game. 
            bankroll=initial;
            return bankroll;
        }
        
        public double getWinnings(double payroll){ //used 
           return payroll ;
        }
    
          //Method definition of getHand
        public ArrayList<Card> getHand()
        {
           return hand;
        }
    

}


