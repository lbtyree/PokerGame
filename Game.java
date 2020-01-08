/*Larissa Tyree
 * lbt2116
 * COMS-W1004
 * Poker Game 
 * April 2019
 * */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.Integer;

public class Game {
	
	private Player p;
    private Player p2;
	private Deck cards;
    private ArrayList<Card> hand;
    private ArrayList<Card> handMore;
    public double tokens; 
    public double initial;
    public static double payroll; 
    public double bets;// first bet
    public double bets2;// second bet (if raised)
    public double betsTotal; // tracks total bets made

	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
		
        Card card;
        char charC;
        int suit; // initialize suit/rank 
        int rank=1;
        String string;
        p= new Player();
        cards= new Deck();
            for(int i=0; i<5; i++){ //5 card hand  
                charC= testHand[i].charAt(0);
                if(testHand[i].length()>2){
                 string= testHand[i].substring(testHand[i].length()-2);
                    // for face cards 
                }else{
                 string= testHand[i].substring(1);   
                }
                rank= Integer.parseInt(string);//assign rank 
                if (charC=='d'){ //assign suit 
                    suit=1;
                }else if (charC=='c'){
                    suit=2;
                }else if (charC=='h'){
                    suit=3;
                }else if(charC=='s'){
                    suit=4;
                }else{
                    suit=0; // check validity 
                    System.out.println("Wrong suit!");
                }
            card= new Card(suit, rank);//create new card 
            p.addCard(card);//add card to player's hand 
            }  
        cards.shuffle();
        hand=new ArrayList<Card>(p.getHand()); // make new hand 
        hand=sortHand(hand); // sort the hand in ascending order 
        System.out.println(checkHand(hand));//print the hand 
        }
			
	public Game(){
		// This no-argument constructor is to actually play a normal game
		// much is same as above testHand method
		p= new Player();
        cards= new Deck();
        cards.shuffle();
        for(int i=1; i<6; i++){ // deal 5 cards 
            p.addCard(cards.deal());
        }
        hand= new ArrayList<Card>(p.getHand());
        hand=sortHand(hand);
    }

	
 public void play(){
     // this method should play the game	
	 //Declare variable
	 int userInput;
     String handH; // string to hold results of checkHand method
     initial=5.0;
     double tokens;
     double payroll=0.0; // checkHand token outcome 
     double yourBankroll= p.initialBankroll(initial); 
     int check=0; // case in that all tokens are bet 
     int round=0; // used with check 
     int game=0; //tracks round (Number of times betting has occured)
     int games=0; // tracks whole games (new deck, etc)
     //Create a Scanner class's object
         
     Scanner scn= new Scanner(System.in);
      //Prompt and read the input from the user
      System.out.println(""); // add space 
      System.out.println("Video Poker Game");
      System.out.println("This is 5-Card Draw.");
      System.out.print("You begin with 5 tokens.");
      System.out.print(" You will be prompted to bet twice ");
      System.out.print("unless you are happy with your ");
      System.out.println("original hand.");
      System.out.println("You may also fold before betting.");
      System.out.println("Ready? Begin!");
      System.out.println(""); // add space 
     
     int winn=(int) p.getBankroll(); // initial Bankroll
 
   while(games==0 && p.getBankroll()>0){  
      //Display  hand 
      System.out.println("Cards you were dealt:");
           hand=sortHand(hand);
           for (Card element: hand) {
           System.out.println(""+element);  
            }
        
      // get player's bet  
      System.out.println(""); // add space 
      System.out.print("You may bet or fold.");
      System.out.println(" Type 1 to BET, 2 to FOLD");
      int firstPlay= scn.nextInt();
         if(firstPlay==2){
             System.out.println("You've Folded! Goodbye!");
             System.exit(0); // causes termination
             } 
         else if (firstPlay==1){
          System.out.print("How many tokens (1-5)");
          System.out.print(" would you like to bet? ");
          System.out.print("You may not bet more tokens");
          System.out.println(" than in your bankroll.");
          bets= (double) scn.nextInt();
            while(bets<1||bets>5){ // check validity 
              System.out.print("You muse bet (1-5) ");
              System.out.print("How many tokens (1-5) ");
              System.out.println("would you like to bet?");
              bets= (double) scn.nextInt();
            }
             while(bets>p.getBankroll()){ // check validity 
              System.out.print("You muse bet a number ");
              System.out.print("less than or equal to the ");
              System.out.print("How many tokens (1-5) ");
              System.out.print("# of tokens in your bankroll");
              System.out.println("would you like to bet?");
              bets= (double) scn.nextInt();
             }
          betsTotal= bets; 
                if(bets==5.0){
                check++; // max tokens bet
                round++; //begin round now to account for max bet 
                  }           
         }
        
      p.bets(bets); // subtract tokens bet from bankroll
      System.out.print("You now have "+ (int)p.getBankroll());
      System.out.println(" tokens.");
   
     // loop to keep game going while player has tokens 
     
     while (p.getBankroll()>=0 && check<=round && game<2){      
      
       round++; // round has begun 
       game++; // player bets 2 times 
       
      if(game==1){ // can only get new cards once
       System.out.println(""); // add space 
       System.out.println("You may get new cards.");
       display("How many cards do you want to reject?(0-3): "); 
       userInput=scn.nextInt();
       if(userInput==0){
            System.out.println("Great! We'll keep going.");
            System.out.println(""); // add space 
            System.out.println("Your new hand: ");
               for (Card element: hand){
                System.out.println(""+ element);
               }
         }
      else{
          if(userInput>0 && userInput<=3){
            for(int i=0; i<userInput; i++){
              System.out.print("What card do you want to");
              System.out.println(" reject?(1-5): ");
              int rejected= scn.nextInt();
              p.removeCard(hand.get(rejected-1));                      
              p.addCard(cards.deal());    
              }
         }
           else{
             System.out.println("Please enter a number 0-3.");                     
             System.out.print("How many cards do you");
             System.out.print("want to reject?(0-3): "); //check this 
             userInput=scn.nextInt();
              for(int i=0; i<userInput; i++){
                System.out.print("What card do you want to");
                System.out.println(" reject?(1-5): ");
                int rejected= scn.nextInt();
                  while(rejected<1||rejected>5){
                    System.out.print("Cards must be ");
                    System.out.println("between 1-5.");
                    System.out.print("What card do you want to");
                    System.out.println(" reject?(1-5): ");
                    rejected= scn.nextInt();
                  }
                p.removeCard(hand.get(rejected-1));                      
                p.addCard(cards.deal());
                    }
                }
          System.out.println(""); // add space 
          hand=new ArrayList<Card>(p.getHand());
          System.out.println("Your new hand: ");
               hand=sortHand(hand);
               for (Card element: hand){
               System.out.println(""+ element);
               }
        }
     } // closes if (games==1){
          
          //check for continted play/ next bet
          //If user did not wish to get new cards, 
          //no new bet can be made (userInput!=0)
          if(p.getBankroll()>=1 && game==1){
             System.out.println(""); // add space 
             System.out.println("Would you like to continue playing?");
             System.out.print(" You may bet again, keep original");
             System.out.println(" bet, or fold.");
             System.out.print(" Type 1 to BET, 2 to KEEP");
             System.out.println(", or 3 to FOLD.");
             
             int playAgain= scn.nextInt();
             if(playAgain==1){
              System.out.println("Great! Continue Game.");
              System.out.print("How many tokens (1-5)");
              System.out.print(" would you like to bet?");
              System.out.print("You may not bet more tokens");
              System.out.println(" than in your bankroll.");
              bets2= (double) scn.nextInt();
              p.bets(bets2); // subtract tokens bet from bankroll
              betsTotal= bets + bets2; // to calculate winnings
              System.out.print("You now have ");
              System.out.println((int)p.getBankroll()+ " tokens");  

             } else if(playAgain==2){
                System.out.println(""); // add space 
                System.out.print("You keep your original bet of ");
                System.out.print( bets + " Tokens.");

             }else if(playAgain==3){
               System.out.println(""); // add space 
               System.out.println("You've Folded! Goodbye!");
               System.exit(0); // causes termination
             }
      }
          
         //round>check accounts for players
           // who go all in first round
           if(p.getBankroll()==0 && round>check && game<=1){
                 check++; // player bets all tokens 
                 System.out.print("You have 0 tokens "); 
                 System.out.println("and cannot up-bet.");               
           }else{
                break; 
                     }
          }
       
          // display this hand's winnings
           handH=checkHand(hand);        
           System.out.println(""); // add space 
           System.out.println("Your hand has a "+handH);   
           // calculate winnings then add them to existing 
           // bankroll. winn is the initial bankroll for this 
           // game 
           int win= (int)p.getBankroll()- (winn-(int)betsTotal);
           System.out.println("You win "+win+ " new tokens");
           System.out.print("Winnings give you bankroll of: ");
           System.out.println((int)p.getBankroll() + " tokens");
           
           if(p.getBankroll()>0){                  
            System.out.println(" ");
            System.out.println("Would you like to play again?");
            System.out.print("Type 1 to get a new"); 
            System.out.println(" hand, 2 to quit.");
            int anotherGame= scn.nextInt();
               if(anotherGame==1){
                 System.out.println(""); // add space 
                 System.out.print("You keep your tokens ");
                 System.out.print(" from previous round(s).");
                 System.out.print("You have "+(int)p.getBankroll());
                 System.out.println(" tokens.");
                 System.out.println(""); // add space 
                 winn=(int)p.getBankroll(); // update initial bankroll
                 betsTotal=0;// reset bets 
                 games=0; // reset games 
                 game=0; // reset times bet 
                 check=0; // reset check/round for max bets 
                 round=0;
                 cards.shuffle();
                 for(int i=0; i<5; i++){
                    p.removeCard(hand.get(i));
                    p.addCard(cards.deal());
                  }
                 handMore=new ArrayList<Card>(p.getHand());
                 hand=handMore;     
               }else{
               
                   games++;
               }
           }
                System.out.print("Don't have enough tokens for");
                System.out.println(" another round!");
        }
       System.out.println("Thanks for playing!");
    }
	
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String
		hand=sortHand(hand);
        if (royalflush(hand)==1){
             payroll = payroll + 250*betsTotal;
             p.winnings(payroll);
             return "Royal Flush with Payout of 250 Tokens per Token bet";
        }
        else if (straightflush(hand)==1){
            payroll = payroll + 50*betsTotal;
            p.winnings(payroll);
            return "Straight Flush with Payout of 50 Tokens per Token bet";
        }
        else if (fourkind(hand)==1){
            payroll = payroll + 25*betsTotal;
            p.winnings(payroll);
            return "Four Kind with Payout of 25 Tokens per Token bet";
        }
        else if(fullhouse(hand)==1){
            payroll = payroll + 6*betsTotal;
            p.winnings(payroll);
            return "Full House with Payout of 6 Tokens per Token bet";
        }
        else if (flush(hand)==1){
            payroll = payroll + 5*betsTotal;
            p.winnings(payroll);
            return "Flush with Payout of 5 Tokens per Token bet";
        }
        else if (straighthand(hand)==1){
            payroll = payroll + 4*betsTotal;
            p.winnings(payroll);
            return "Straight with Payout of 4 Tokens per Token bet";
        }
        else if (threeofkind(hand)==1){
            payroll = payroll + 3*betsTotal;
            p.winnings(payroll);
            return "Three of a kind with Payout of 3 Tokens per Token bet";
        }
        else if (twopairs(hand)==1){
            payroll = payroll + 2*betsTotal;
            p.winnings(payroll);
            return "Two Pairs with Payout of 2 Tokens per Token bet";
        }
        else if (onepair(hand)==1){
            payroll = payroll + 1*betsTotal;
            p.winnings(payroll);
            return "One pair with Payout of 1 Token per Token bet";
        }
        else{
            return "No Pair with no Payout";
        }
		
	}
	
    //This method returns the number of pairs
    //in the hand three of a kind count as 
    //one pair
    public int pair(ArrayList<Card> hand) {
        Card cardA; //temporary card 
        int pairs=0;    // initialize pairs    
        for (int i=1; i<hand.size();i++){
            cardA=hand.get(i-1); 
                // se if temp card matches any cards in deck 
                if (cardA.sameR(hand.get(i))){
                i++; // run through all cards 
                pairs++;//increase pairs 
                }
        }
        return pairs;
    }
    //returns 1 if the hand has one pair
    //and 0 otherwise
    public int onepair(ArrayList<Card> hand){
        if(pair(hand)==1){
                return 1;
            }
        return 2;
    }
    //returns 1 if the hand has two pairs
    //and 0 otherwise
    public int twopairs(ArrayList<Card> hand){
       if(pair(hand)==2){
        return 1;  
           
        }else{
           return 0;
        }
    }
    //returns 1 if the hand has there of a kind
    //and 0 otherwise
    public int threeofkind(ArrayList<Card> hand){
        //cards in ascending order so three 
        //of kind would be next to each other 
        if( hand.get(0).sameR(hand.get(2))
                || hand.get(1).sameR(hand.get(3))
                    || hand.get(2).sameR(hand.get(4))){
                return 1;
            
        }else if (pair(hand)==0){
            return 0;  // must have pair 
        
        }else{ 
            return 0;
        }
    }
   // returns 1 if the hand has a straight
   //and 0 otherwise
    public int straighthand(ArrayList<Card> hand){
        Card firstCard = hand.get(0);
        Card secondCard = hand.get(1);
        Card thirdCard = hand.get(2);
        Card fourthCard= hand.get(3);
        Card fithCard = hand.get(4);
        
        if (pair(hand) > 0){
            return 0;
        }else if((fithCard.getRank()-firstCard.getRank())==4){
            return 1;
            //ace high straight 
        }else if(firstCard.getRank()==1
          && fithCard.getRank()==13
                && thirdCard.getRank()==11){
            return 1;
            //ace low straight 
        }else if(firstCard.getRank()==1
          && fithCard.getRank()==5
                && secondCard.getRank()==2){
            return 1;
        }else{
            return 0;
        }      
    }
    //It returns 1 if the hand has a flush
    // 0 otherwise
    public int flush(ArrayList<Card> hand){
        
        Card firstCard = hand.get(0);
        Card secondCard = hand.get(1);
        Card thirdCard = hand.get(2);
        Card fourthCard= hand.get(3);
        Card fithCard = hand.get(4);
        
       if(pair(hand)>0)
        {
            return 0;
        }else if((hand.get(0).getSuit()==hand.get(1).getSuit())
                && (hand.get(1).getSuit()==hand.get(2).getSuit())
                && (hand.get(2).getSuit()==hand.get(3).getSuit())
                && (hand.get(3).getSuit()==hand.get(4).getSuit())){
           return 1;
       }else{
           return 0; 
       }
 
      }    
    //It returns 1 if the hand has a full house
    // 0 otherwise
    public int fullhouse(ArrayList<Card> hand){
        if(((hand.get(3).getRank()==hand.get(4).getRank())
            ||(hand.get(0).getRank()==hand.get(1).getRank()))
           && ( hand.get(0).sameR(hand.get(2))||
            hand.get(2).sameR(hand.get(4))) && fourkind(hand)==0){
            return 1;
        }else{
            return 0;
        }   
    }   
    //It returns 1 if the hand has 4 cards
    //with the same value
    public int fourkind(ArrayList<Card> hand){
        if(pair(hand)==2 && ((hand.get(0).sameR(hand.get(3))
                        || hand.get(1).sameR(hand.get(4)))) ){
                return 1;
            }
            return 0;                  
    }   
    //Method definition of straightFlush
    //It returns 1 if the hand has a straight flush
    public int straightflush(ArrayList<Card> hand){
       
        if(straighthand(hand)==1 && flush(hand)==1 ){
            return 1;
        }else{
            return 0;
        }
    }
    //Method definition of royalflush
    //It returns 1 if the hand has a royal flush
    public int royalflush(ArrayList<Card> hand){
        
       if(straightflush(hand)==1 && hand.get(0).getRank()==1
         &&  hand.get(4).getRank()==13){
            return 1;
        }else{
            return 0;
        }
    }
       
   
    //returns an ascending sorted hand
    public ArrayList<Card> sortHand(ArrayList<Card> hand){
        int small; //min variable 
        Card tempCard; //temporary card
        int i = 0;
        while(i < hand.size() - 1){
            small = i; //set the min to i
            for (int n = i +1; n < hand.size(); n++){
              //if card n is less than the min then set the min to n
              if (hand.get(n).getRank()<(hand.get(small).getRank())){
                  small = n;
               }
            }
            tempCard = hand.get(small); //set temp card to the min
            hand.set(small, hand.get(i)); //set the min to i
            hand.set(i,tempCard); //set i to temp 
            i++;  // loop through entire deck 
        }
        return hand; //return sorted hand
    }
    
    //Method definition of print
    public void display(String s){
        System.out.println(s);
    }
 
}
