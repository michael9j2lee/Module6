import java.util.Arrays;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;



public class CardModel {

	   private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
	   private static Icon iconBack;
	   static boolean iconsLoaded = false;
	   
	   void loadCardIcons()
	   {
		   for(int i = 0; i <= 13; i++)
		   {
			   for(int k = 0; k <=3; k++)
			   {
				   String filename = new String();
				   filename = "images/" + turnIntIntoCardValue(i) + turnIntIntoCardSuit(k) +".gif";
				   //System.out.print(filename);
				   Icon image= new ImageIcon(filename);
				   iconCards[i][k] = image;
			   }
			   
			   iconBack = new ImageIcon("images/bk.gif");
			   iconsLoaded = true;
			   
		   }
	   }
	   
	   //accessor for BackCard
		public Icon getBackCardIcon()
		{
			return iconBack;
		}
	   
		//insert Card, get Icon
		public Icon getIcon(Card card)
		{
			return iconCards[valueAsInt(card)][suitAsInt(card)];
		}
		
		
	    private static int valueAsInt(Card card)
	    {
	       char value = card.getValue();
	       //System.out.printf("Value : %s%n",value);
	       switch (value)
	       {
	       case 'A':
	          return 13;
	       case 'K':
	          return 12;
	       case 'Q':
	          return 11;
	       case 'J':
	          return 10;
	       case 'T':
	          return 9;
	       case 'X':
	          return 0;
	       default:
	          int val = value - '1';
	          //System.out.printf("DEFAULT : %d%n", val);
	          return (val);
	       }
	             
	    }
	    private static int suitAsInt(Card card)
	    {
	       Card.Suit suit = card.getSuit();
	       //System.out.println("CARD =" +card.toString());
	       if (suit == Card.Suit.SPADES)
	       {
	             return 3;      
	       }
	       else if (suit == Card.Suit.HEARTS)
	       {
	          return 2;
	       }
	       else if (suit == Card.Suit.DIAMONDS) {
	          return 1;
	       }
	       else if (suit == Card.Suit.CLUBS) {
	          return 0;
	       }
	       // Failed
	       else
	       {
	          return 4;
	       }
	    }
	    
	    
	   // turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
	   static String turnIntIntoCardValue(int k)
	   {
		   if ( k > 0 && k < 9)
		   {
			   return String.valueOf(k+1);
		   }
		   else if(k == 0)
		   {
			   return "X";
		   }
		   else if (k==9)
		   {
			   return "T";
		   }
		   else if (k==10)
		   {
			   return "J";
		   }
		   else if (k == 11)
		   {
			   return "Q";
		   }
		   else if (k == 12)
		   {
			   return "K";
		   }
		   else if (k == 13)
		   {
			   return "A";
		   }
		   else
		   {
			   return "F";
		   }

	      // an idea for a helper method (do it differently if you wish)
	   }
	   
	   // turns 0 - 3 into "C", "D", "H", "S"
	   static String turnIntIntoCardSuit(int j)
	   {
	  
		   switch(j)
		   {
		   case 0:
			   return "C";
		   case 1:
			   return "D";
		   case 2:
			   return "H";
		   case 3:
			   return "S";
		   }
		   return "F";
	      // an idea for another helper method (do it differently if you wish)
		   //FAILED
	   }
	   String comparing(Card b, Card a)
	   {
	      int suitA = getSuitNum(a);
	      int suitB = getSuitNum(b);
	      int valueA = valueAsInt(a);
	      int valueB = valueAsInt(b);
	      
	      System.out.println("YOU : " + a + " COMPUTER : " + b);
	      
	      if (valueB > valueA)
	      {
	         return "WIN";
	      }
	      else if (valueA == valueB && suitA > suitB)
	      {
	         return "WIN";
	      }
	      else if(a.equals(b))
	      {
	         return "TIE";
	      }
	      else
	         return "LOSE";
	   }
	 
	   
	   private static int getSuitNum(Card card)
	   {
	      Card.Suit suit = card.getSuit();
	      //System.out.println("CARD =" +card.toString());
	      if (suit == Card.Suit.SPADES)
	      {
	            return 0;      
	      }
	      else if (suit == Card.Suit.DIAMONDS)
	      {
	         return 1;
	      }
	      else if (suit == Card.Suit.HEARTS) {
	         return 2;
	      }
	      else if (suit == Card.Suit.CLUBS) {
	         return 3;
	      }
	      // Failed
	      else
	      {
	         return 4;
	      }
	   }
	}

//class CardGameFramework  ----------------------------------------------------
class CardGameFramework
{
 private static final int MAX_PLAYERS = 50;

 private int numPlayers;
 private int numPacks;            // # standard 52-card packs per deck
                                  // ignoring jokers or unused cards
 private int numJokersPerPack;    // if 2 per pack & 3 packs per deck, get 6
 private int numUnusedCardsPerPack;  // # cards removed from each pack
 private int numCardsPerHand;        // # cards to deal each player
 private Deck deck;               // holds the initial full deck and gets
                                  // smaller (usually) during play
 private Hand[] hand;             // one Hand for each player
 private Card[] unusedCardsPerPack;   // an array holding the cards not used
                                      // in the game.  e.g. pinochle does not
                                      // use cards 2-8 of any suit

 public CardGameFramework( int numPacks, int numJokersPerPack,
       int numUnusedCardsPerPack,  Card[] unusedCardsPerPack,
       int numPlayers, int numCardsPerHand)
 {
    int k;

    // filter bad values
    if (numPacks < 1 || numPacks > 6)
       numPacks = 1;
    if (numJokersPerPack < 0 || numJokersPerPack > 4)
       numJokersPerPack = 0;
    if (numUnusedCardsPerPack < 0 || numUnusedCardsPerPack > 50) //  > 1 card
       numUnusedCardsPerPack = 0;
    if (numPlayers < 1 || numPlayers > MAX_PLAYERS)
       numPlayers = 4;
    // one of many ways to assure at least one full deal to all players
    if  (numCardsPerHand < 1 ||
          numCardsPerHand >  numPacks * (52 - numUnusedCardsPerPack)
          / numPlayers )
       numCardsPerHand = numPacks * (52 - numUnusedCardsPerPack) / numPlayers;

    // allocate
    this.unusedCardsPerPack = new Card[numUnusedCardsPerPack];
    this.hand = new Hand[numPlayers];
    for (k = 0; k < numPlayers; k++)
       this.hand[k] = new Hand();
    deck = new Deck(numPacks);

    // assign to members
    this.numPacks = numPacks;
    this.numJokersPerPack = numJokersPerPack;
    this.numUnusedCardsPerPack = numUnusedCardsPerPack;
    this.numPlayers = numPlayers;
    this.numCardsPerHand = numCardsPerHand;
    for (k = 0; k < numUnusedCardsPerPack; k++)
       this.unusedCardsPerPack[k] = unusedCardsPerPack[k];

    // prepare deck and shuffle
    newGame();
 }

 // constructor overload/default for game like bridge
 public CardGameFramework()
 {
    this(1, 0, 0, null, 4, 13);
 }

 public Hand getHand(int k)
 {
    // hands start from 0 like arrays

    // on error return automatic empty hand
    if (k < 0 || k >= numPlayers)
       return new Hand();

    return hand[k];
 }

 public Card getCardFromDeck() { return deck.dealCard(); }

 public int getNumCardsRemainingInDeck() { return deck.getNumCards(); }

 public void newGame()
 {
    int k, j;

    // clear the hands
    for (k = 0; k < numPlayers; k++)
       hand[k].resetHand();

    // restock the deck
    deck.init(numPacks);

    // remove unused cards
    for (k = 0; k < numUnusedCardsPerPack; k++)
       deck.removeCard( unusedCardsPerPack[k] );

    // add jokers
    for (k = 0; k < numPacks; k++)
       for ( j = 0; j < numJokersPerPack; j++)
          deck.addCard( new Card('X', Card.Suit.values()[j]) );

    // shuffle the cards
    deck.shuffle();
 }

 public boolean deal()
 {
    // returns false if not enough cards, but deals what it can
    int k, j;
    boolean enoughCards;

    // clear all hands
    for (j = 0; j < numPlayers; j++)
       hand[j].resetHand();

    enoughCards = true;
    for (k = 0; k < numCardsPerHand && enoughCards ; k++)
    {
       for (j = 0; j < numPlayers; j++)
          if (deck.getNumCards() > 0)
             hand[j].takeCard( deck.dealCard() );
          else
          {
             enoughCards = false;
             break;
          }
    }

    return enoughCards;
 }

 void sortHands()
 {
    int k;

    for (k = 0; k < numPlayers; k++)
       hand[k].sort();
 }

 Card playCard(int playerIndex, int cardIndex)
 {
    // returns bad card if either argument is bad
    if (playerIndex < 0 ||  playerIndex > numPlayers - 1 ||
        cardIndex < 0 || cardIndex > numCardsPerHand - 1)
    {
       //Creates a card that does not work
       return new Card('M', Card.Suit.SPADES);      
    }
 
    // return the card played
    return hand[playerIndex].playCard(cardIndex);
 
 }

 
 boolean takeCard(int playerIndex)
 {
    // returns false if either argument is bad
    if (playerIndex < 0 || playerIndex > numPlayers - 1)
       return false;
   
     // Are there enough Cards?
     if (deck.getNumCards() <= 0)
        return false;

     return hand[playerIndex].takeCard(deck.dealCard());
 }
}

class Card
{
   public enum Suit {SPADES, HEARTS, CLUBS, DIAMONDS}; 
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   /** Default Constructor 
    * calls other constructor with default (A of SPADES) parameter 
    */
   Card()
   {
      this.value = 'A';
      this.suit = Suit.SPADES;
      this.errorFlag = false;
   }
   
   /** Constructor with specified values 
    * @param newValue   desired value for new Card
    * @param newSuit    desired suit for new Card
    */
   Card(char newValue, Suit newSuit)
   {
      if (isValid(newValue, newSuit))
      {
          this.value = Character.toUpperCase(newValue);
          this.suit = newSuit;
      }
      else
      {
          this.errorFlag = true;
      }
   }
   
   /** Copy Constructor 
    * calls other constructor with the parameters of a card to be copied
    * @param card    card to be copied 
    */
   Card(Card card)
   {
      this(card.value, card.suit); //TODO switch back to below lines if this doesn't work for some reason -ka
      //this.value = card.value;
      //this.suit = card.suit;
      //this.errorFlag = card.errorFlag;
   }
   
   /** checks whether 2 cards are the same
    * 
    * @param otherCard  card to be compared
    * @return           whether this and otherCard have same value and suit 
    */
   public boolean equals(Card otherCard)
   {
      return ((this.value == otherCard.value) && (this.suit ==  otherCard.suit));
   }
   
   /** toString overloader for Card class
    * @return string represention of the card
    *          formatted like "A of spades"
    */
   public String toString()
   {
      String cardString;
      
      if(this.errorFlag)
         cardString = "** illegal **";
      else
         cardString = Character.toString(this.value) + " of " + suit;
      
      return cardString;
   }
   
   /** Sets a Card's value and suit
    * 
    * @param value   desired value
    * @param suit    desired suit
    * @return        whether new values are valid & card successfully changed
    */
   public boolean set( char value, Suit suit)
   {
      value = Character.toUpperCase(value);
      if(isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         this.errorFlag = false;
      }
      else 
      {
         this.errorFlag = true;
      }
      
      return !this.errorFlag;
   }
   
   /** Accessor for Card value
    * 
    * @return  value of this card
    */
   public char getValue()
   {
      return this.value;
   }
   
   /** Accessor for Card suit
    * 
    * @return  suit of this card
    */
   public Suit getSuit()
   {
      return this.suit;
   }
   

   /** Checks whether Card is invalid (has an error flag)
    * 
    * @return  error state of this Card
    */
   public boolean getErrorFlag()
   {
      return this.errorFlag;
   }
   
   private boolean isValid(char value, Suit suit)
   {
      return "123456789TJQKAX".contains(Character.toString(value)) && suit!=null; 
   }
   

}

class Hand {
   private Card[] myCards;
   private int numCards;
   private static final int MAX_CARDS = 52;     // Instruction mentions public int value
   
   /** Default Constructor */
   Hand()
   {
      this.myCards = new Card[MAX_CARDS];
      numCards = 0;
   }
   
   /** Clears the current hand */
   public void resetHand()
   {
       this.numCards = 0;
   }
   
   /** adds copy of a card to the hand 
    * @param card    Card to be added to hand
    * @return        whether a card was added or not
    */
   public  boolean takeCard(Card card)
   {
       Card tempCard = new Card(card);
       //tempCard = card;
       boolean wasTaken = false;
       if (numCards <= MAX_CARDS-1)
       {
           this.myCards[this.numCards] = tempCard;
           numCards++;
           return true;
       }
       return wasTaken;
   }
   
   /** plays and removes the top card in hand 
    * @return  Card at the highest index 
    *          (or invalid Card if empty hand)
    */
   public Card playCard()
   {   
       Card tempCard = new Card('Z', Card.Suit.SPADES);
       if (this.numCards > 0)
       {
    	   this.numCards--;
           tempCard = this.myCards[this.numCards-1];
           
       }
       
       return tempCard;
   }
   
   /** 
    * @return   string representation of all cards in hand
                formatted like "A of spades, 2 of clubs, J of hearts"
    */ 
   public String toString()
   {
       String tempCards = "";
       for(int i = 0; i < numCards; i++)
       {
    	   if (i != numCards-1)
    	   {
    		   tempCards += myCards[i].toString() + ", ";  
    	   }
    	   else
    	   {
    		   tempCards += myCards[i].toString();  
    	   }
       }
       return tempCards;
   }
   
   
   /** Accessor for numCards
    * 
    * @return  number of cards in hand
    */
   public int getNumCards()
   {
       return this.numCards;
   }
   
   /** Inspect the card at the hand position.
    * 
    * @param k index of card to inspect in hand
    * @return  card at index k
    *          (or invalid card if k is invalid)
    */
   public Card inspectCard(int k)
   {
      if(k >= this.numCards || k < 0) //invalid k 
         return new Card('z',Card.Suit.SPADES); //invalid card w/ error flag
      else 
         return this.myCards[k];
   }
   
   public void sort()
   {
	   Arrays.sort(myCards);
   }
   
   public Card playCard(int cardIndex)
   {
      if ( numCards == 0 ) //error
      {
         //Creates a card that does not work
         return new Card('M', Card.Suit.SPADES);
      }
      //Decreases numCards.
      Card card = myCards[cardIndex];
      
      numCards--;
      for(int i = cardIndex; i < numCards; i++)
      {
         myCards[i] = myCards[i+1];
      }
      
      myCards[numCards] = null;
      
      return card;
    }
}


class Deck
{
   public static final int CARDS_IN_PACK = 56;
   public final int MAX_CARDS = CARDS_IN_PACK*6; //essentially a max of 6 card packs
   
   /** contains one full pack of cards, to be copied as necessary into the deck */
   private static Card[] masterPack = new Card[CARDS_IN_PACK];
   
   private Card[] cards = new Card[MAX_CARDS];
   private int topCard;
  
   /**Constructor with specified number of packs
    * @param numpacks   number of card packs in deck
   */
   Deck(int numPacks)
   {
      if (masterPack[0]==null)
      {
         allocateMasterPack();
      }

      init(numPacks);
      topCard = (CARDS_IN_PACK * numPacks) - 1;
   }

   /** Default Constructor (calls the constructor with numPacks=1 parameter) */
   Deck()
   {
      this(1);
   }
   
   /** Creates the master pack where all the card values will come from */
   private void allocateMasterPack()
   {
      char[] values = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A','X'};
      int arrayIndex = 0;
      for(char v:values)
         for(int s = 0; s < 4; s++)
            Deck.masterPack[arrayIndex++] = new Card(v, getSuit(s));
   }
   
   /** Switch for getting a suit from an integer
    * @param num  int from 0-3 corresponding to card suits
    * @return     the suit corresponding to the number input
   */
   private Card.Suit getSuit(int num)
   {
      Card.Suit suit  = Card.Suit.SPADES;
      
      switch(num) { 
         case 0:
            suit = Card.Suit.SPADES;
            break;
         case 1:
            suit = Card.Suit.HEARTS;
            break;
         case 2:
            suit = Card.Suit.CLUBS;
            break;
         case 3:
            suit = Card.Suit.DIAMONDS;
            break;
         default:
            //Included for degugging
            System.out.println("Encountered error");
      }

      return suit;
   }

   /** repopulate the deck
    * @param numPacks   number of card packs in deck
    */  
   public void init(int numPacks)
   {
      this.topCard = ((CARDS_IN_PACK * numPacks) - 1);

      // Number of packs
      for (int i = 0; i < numPacks; i++)
         for (int j = 0; j < CARDS_IN_PACK; j++)  
            this.cards[(i*CARDS_IN_PACK)+j] = new Card(masterPack[j]);
      		
      		//System.out.println( (i * CARDS_IN_PACK) + j);
   }
   
   /** 
    * @return reference to the top card (at cards[topCard])
    */
   public Card getTopCard()
   {
      if(topCard<0)
         return new Card('z', Card.Suit.SPADES);
      return inspectCard(topCard);
   }
   
   /** returns and removes the top card from the deck (uses getTopCard())
    * @return reference to the top card (or an illegal card if deck is empty)
    */
   public Card dealCard()
   {
      Card tempCard = getTopCard();
      topCard--;
      return tempCard;
   }

   /** peeks at a card in the deck without removing it
    * @param k index of the desired card to inspect
    * @return  reference to the card at index k
    */
   public Card inspectCard(int k)
   {
      //Return the card in position k
      if(k <= this.topCard)
         return this.cards[k];
      //Return an illegal card
      else
         return new Card('z',Card.Suit.SPADES);
   }

   /** shuffles cards in deck */
   public void shuffle()
   {
      Random rng = new Random();
      int index; 
      Card temp;
      for(int i=topCard; i>0; i--){
         index = rng.nextInt(i);
         //Swap cards[index] with cards[i]
         temp = this.cards[index];
         this.cards[index] = this.cards[i];
         this.cards[i] = temp;
      }
   }
   
   public boolean addCard(Card card)
   {
	   //Check to see if there are too many instances of the card in the deck
	   //return false if too many
	   if(this.topCard >= MAX_CARDS)
	   {
		   return false;
	   }
	   else
	   {
		   this.cards[this.topCard] = card;
		   this.topCard++;
		   return true;
	   }
   }
   
   //put all the cards in the deck back into the right order according to values.
   public void sort()
   {
	   Arrays.sort(cards);
   }
   
   //returns the number of cards remaining in the deck
   public int getNumCards()
   {
	   return topCard;
   }
   
   // Removes Card from array
   public boolean removeCard(Card card)
   {
	   boolean checkCardRemoved = false;
	   int counter = 0;
	   while(!checkCardRemoved && counter < CARDS_IN_PACK )
	   {
		   if (card.equals(masterPack[counter]))
		   {
			   masterPack[counter]= dealCard();
			   return true;
		   }
		   // not found
		   counter ++;
	   }
	   return false;
   }
   
}