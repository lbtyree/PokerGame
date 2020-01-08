/*Larissa Tyree
 * lbt2116
 * COMS-W1004
 * Poker Game 
 * April 2019
 * */


public class Card implements Comparable<Card>{
	
    private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
  public Card(int s, int r){
    //make a card with suit s and value v
    suit= s;
    rank=r;
}

  public int compareTo(Card c){
    // use this method to compare cards so they 
    // may be easily sorted
    if(this.getRank()- c.getRank()>0){ //compare ranks 
    return this.getRank()- c.getRank(); //return difference 
    }
    //return suit difference if rank dif n/a 
    return this.getSuit()- c.getSuit();

}

  public String toString(){

   // use this method to easily print a Card object
   String card_s= ""; // initialize strings 
   String card_r = "";

   //The suits represents diamonds(1),clubs(2)
   //hearts(3),spades(4)

  if(suit==1){
    card_s="diamonds"; // diamonds
    }
    else if(suit==2){
        card_s="clubs"; //clubs
    }
    else if(suit==3){
        card_s="hearts"; //hearts
    }
    else{
        card_s="spades";// spades 
    }

    // rank of cards 
    //includes face cards/ace 

    if(rank==1){
        card_r="Ace";
    }
    else if(rank>1 && rank<11){
        card_r=""+rank;
    }

    else if(rank==11){
        card_r= "Jack";
    }

    else if(rank==12){
        card_r= "Queen";
    }

    else if(rank==13){
        card_r= "King";
    }
     String newCard= card_r+" of "+ card_s+" is this card's rank and suit";
     return newCard; // return string 
    }

  public int getRank(){ //method to get the rank of a card
        return rank;
    }

  public int getSuit(){ //method that gets the suit of a card
        return suit;
    }

  public boolean sameS(Card c){
        boolean same= c.getSuit()==this.getSuit();
      return same;
    }
    //It returns true if the cards have
    //the same value and false otherwise
    
  public boolean sameR(Card c){
        boolean same= c.getRank()==this.getRank();
        return same; 
    }

 }









