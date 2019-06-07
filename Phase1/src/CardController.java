import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



class CardController extends CardModel
{
	CardController()
	{
		super();
	}
   public static void main(String[] args)
   {
	 //Instatiate highCardGame
	 int numPacksPerDeck = 1;
     int numJokersPerPack = 2;
     int numUnusedCardsPerPack = 0;
     Card[] unusedCardsPerPack = null;
     
     CardGameFramework highCardGame = new CardGameFramework( 
             numPacksPerDeck, numJokersPerPack,  
             numUnusedCardsPerPack, unusedCardsPerPack, 
             NUM_PLAYERS, NUM_CARDS_PER_HAND);
     
	  // start new game
	  highCardGame.newGame();
	  highCardGame.deal();
	  Hand compHand = highCardGame.getHand(0);
	  Hand playerHand = highCardGame.getHand(1);
	  
	  System.out.println(compHand.toString());
	  System.out.println(playerHand.toString() + "\n\n");
	  
      int k;
     
      // establish main frame in which program will run
      CardView myCardTable = new CardView("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
      myCardTable.setSize(800, 600);
      myCardTable.setLocationRelativeTo(null);
      myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // show everything to the user
      myCardTable.setVisible(true);
      
      // Create a win label
      Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
      JLabel winLabel = new JLabel();
      winLabel.setFont(labelFont);
      winLabel.setHorizontalAlignment(JLabel.CENTER);
      JLabel empty = new JLabel();
      
      
      // CREATE LABELS ----------------------------------------------------
      //Iterate through number of cards
      for (int a = 0; a < NUM_CARDS_PER_HAND; a++)
      {
    	 computerLabels[a] = new JLabel();
    	 humanButtons[a] = new JButton();
    	 humanButtons[a].addActionListener(new Action(a,compHand, playerHand, winLabel));
      }
      //Iterate through number of players
      for (int b = 0; b < NUM_PLAYERS ; b++)
      {
    	  playedCardLabels[b] = new JLabel();
    	  playedCardLabels[b].setHorizontalAlignment(JLabel.CENTER);
    	  playLabelText[b] = new JLabel();
    	  playLabelText[b].setHorizontalAlignment(JLabel.CENTER);
      }
      myCardTable.pnlPlayArea.add(playedCardLabels[0]); 
      myCardTable.pnlPlayArea.add(winLabel);
	  myCardTable.pnlPlayArea.add(playedCardLabels[1]);  
	  myCardTable.pnlPlayArea.add(playLabelText[0]);
	  myCardTable.pnlPlayArea.add(empty);
	  myCardTable.pnlPlayArea.add(playLabelText[1]);
      playLabelText[0].setText("Computer");
	  playLabelText[1].setText("You");
 

	  GUICard.loadCardIcons();
	  
      // ADD LABELS TO PANELS -----------------------------------------
      for (k = 0; k < NUM_CARDS_PER_HAND; k++)
      {
    	  computerLabels[k].setIcon(CardController.GUICard.getBackCardIcon());
          myCardTable.pnlComputerHand.add(computerLabels[k]);
          
          humanButtons[k].setIcon(CardController.GUICard.getIcon(playerHand.inspectCard(k)));
      	  myCardTable.pnlHumanHand.add(humanButtons[k]);
      	  System.out.printf("%d%n", k);
      }
	  myCardTable.setVisible(true);
   }
   
   static class Action implements ActionListener
   {
	   private int cardNumber;
	   private Hand compHand;
	   private Hand playerHand;
	   private JLabel winLabel;
	   
	   public Action(int cardNumber, Hand compHand, Hand playerHand, JLabel winLabel)
	   {
		   this.cardNumber = cardNumber;
		   this.compHand = compHand;
		   this.playerHand = playerHand;
		   this.winLabel = winLabel;
	   }
	   
	   public void actionPerformed(ActionEvent e)
	   {
	      playedCardLabels[0].setIcon(CardController.GUICard.getIcon(compHand.inspectCard(cardNumber)));
	      playedCardLabels[1].setIcon(CardController.GUICard.getIcon(playerHand.inspectCard(cardNumber)));
	      
	      Card compCard = compHand.inspectCard(cardNumber);
	      
	      Card playerCard = playerHand.inspectCard(cardNumber);
	      
	      System.out.println(compCard.toString());
	      System.out.println(playerCard.toString());
	      
	      if(comparing(playerCard, compCard) == "WIN")
	      {
	         this.winLabel.setText("YOU WIN!");
	      }
	      else if(comparing(playerCard, compCard) == "LOSE"){
	    	  winLabel.setText("YOU LOSE!");
	      }
	      else
	      {
	    	  winLabel.setText("YOU TIE!");
	      }
	      
	   }
	   
	   static String comparing(Card b, Card a)
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
			   int val = value - '0';
			   //System.out.printf("DEFAULT : %d%n", val);
			   return (val);
		   }   
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
  }
