/*Larissa Tyree
 * lbt2116
 * COMS-W1004
 * Poker Game 
 * April 2019
 * */

CARD.JAVA

In Card.java, I set up the suits and ranks of possible cards and made these 
suits/ranks retrievable and comparable. Within this comparable interface, 
the compareTo method returns differences in card rank first then differences 
in card suit if there is no difference in rank.The toString method assigns 
the suits- diamond, club, heart, spade- as well as the rank with 1 asAce, 2-10 as 
number cards, 11 as Jack, 12 as queen, and 13 as king. The card id then stored as a string 
newString to compile the cards rank and suit into a single card. The newString is 
what is returned. The accessor methods getRank and getSuit simply return the rank and 
suit of the card in question while the boolean methods sameS and sameR check 
for suits/ranks that equal each other and return T/F accordingly. 

DECK.JAVA 

In Deck.java, there is an array list containing a collection of Card objects called 
cards as well as a prirate int top for the top of the deck that we are intitially provided 
with. In the deck method, I created a 52 card deck looping through the 4 suits for 13 
cards in each of the suits. In the shuffle method, I employed Collections.shuffle() from 
Collections.util.java on the cards array to shuffle the deck. In the deal method, I 
returned the top card for the and increased top by one to insure that the dealt card 
would always be the next card in the deck. The top card is returned in the deal method. 

PLAYER.JAVA

In Player.java, there is a private array list hand initially provided. In the public 
method player, hand is initialized and the players bets are initialized as zero. The 
addCard and removeCard methods are mutator methods that allow the player's hand to 
be modified during the game. The bets method decreases the player's bankroll by the 
bet amount, and the get Bankroll method returns the player's current bankroll. The 
winnings method adds the odds (player's won tokens) to the bankroll. The initial 
bankroll method is the method I used to set every player's bankroll to 5 at the 
very beginning of their very first game(bankroll=initial). In theory, a player would 
have to buy in at the cost of 5 tokens. The getWinnings methods returns the payroll 
only (only the won tokens in the given round) and the getHand method returns the 
player's current hand. 

GAME.JAVA

Game.java utilizes the methods I created in the above three classes. The 
public Game(String[] testHand) method is only accessed in PokerTest if (args.length<1),
aka an argument was sent in to test the code. In this method, I created a new player 
object and initialized the given deck 'cards. When this method is called, the user
will input a test hand array as an argument: testhand = {s1, s13, s12, s11, s10}.
The method then acesses the rank at .substring(1) and the suit by charAt(0). Then, 
the method shuffles the cards, creates a hand from the test hand, sorts the hand 
in ascending order using the sortHand method, assess the hand for Flush, One Pair,
etc. using checkHand and outpoints the results. In the public Game() class, much of 
the same as above occurs (player object created, deck object created, and cards shuffled
& hand made.) In my the play method, the player is given an initill bankroll of 5
tokens and the game begins. The player is given a hand and asked to bet, then the player
may remove cards and bet again so long as they have >0 tokens (while loop). There are also 
while loops for the tokens bet/cards removed to ensure that the player bets a valid amount
and removes a correct amount of cards. After the player bets each time, the bankroll is updated
and the player is made aware of how many tokens they have left. After the second betting round,
the checkHand method is employed to determine the payroll (winnings) for this round 
and the player is made aware of their winnings and total bankroll. If the player's
bankroll is > 0 using if loop) they are asked if they want to play again. If yes, all
values (except total bankroll) are reset and the player may play again (back to initial while
loop). The onepair, two pair, threeofkind, fourkind etc. methods determine the outcome of the 
hand (use comparison methods- sameS, sameR- to evaluate cards). Finally, the sortHand method
sorts the hand in ascending order so that all of the above methods can compare the cards via
their order in the hand. 



