import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class CardController
{
   static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   private CardView view;
   static CardModel model;
   static Hand compHand;
   static Hand playerHand;

   
   public CardController(CardModel model, CardView view)
   {
	   this.view = view;
	   this.model = model;
	   compHand = null;
	   playerHand = null;
   }
   
   public void startNewGame(int numPacksPerDeck, int numJokersPerPack,
		   int numUnusedCardsPerPack, Card[] unusedCardsPerPack,
		   int NUM_PLAYERS, int NUM_CARDS_PER_HAND)
   {  
	     CardGameFramework highCardGame = new CardGameFramework( 
	             numPacksPerDeck, numJokersPerPack,  
	             numUnusedCardsPerPack, unusedCardsPerPack, 
	             NUM_PLAYERS, NUM_CARDS_PER_HAND);
		  
		  // start new game

		  highCardGame.newGame();
		  highCardGame.deal();
		  compHand = highCardGame.getHand(0);
		  playerHand = highCardGame.getHand(1);
  

	   int k;
		  
	   // establish main frame in which program will run
	  
	   
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
	     view.humanButtons[a].addActionListener(new Action(a,compHand, playerHand, winLabel, view.playedCardLabels));
	   }
	   
	   
	   //Iterate through number of players
	   for (int b = 0; b < NUM_PLAYERS ; b++)
	   {
		   view.playedCardLabels[b] = new JLabel();
		   view.playedCardLabels[b].setHorizontalAlignment(JLabel.CENTER);
		   view.playLabelText[b] = new JLabel();
		   view.playLabelText[b].setHorizontalAlignment(JLabel.CENTER);
	   }
	   view.pnlPlayArea.add(view.playedCardLabels[0]); 
	   view.pnlPlayArea.add(winLabel);
	  view.pnlPlayArea.add(view.playedCardLabels[1]);  
	  view.pnlPlayArea.add(view.playLabelText[0]);
	  view.pnlPlayArea.add(empty);
	  view.pnlPlayArea.add(view.playLabelText[1]);
	  view.playLabelText[0].setText("Computer");
	  view.playLabelText[1].setText("You");
	
	
	  model.loadCardIcons();
	  
	   // ADD LABELS TO PANELS -----------------------------------------
	   for (k = 0; k < NUM_CARDS_PER_HAND; k++)
	   {
		   view.computerLabels[k].setIcon(model.getBackCardIcon());
	       view.pnlComputerHand.add(view.computerLabels[k]);
	       
	       view.humanButtons[k].setIcon(model.getIcon(playerHand.inspectCard(k)));
	        view.pnlHumanHand.add(view.humanButtons[k]);
	        System.out.printf("%d%n", k);
	   }
	  view.setVisible(true);   
   }
	  
	static class Action implements ActionListener
	{
	   private int cardNumber;
	   private Hand compHand;
	   private Hand playerHand;
	   private JLabel winLabel;
	   private JLabel playedCardLabels[];
	   
	   public Action(int cardNumber, Hand compHand, Hand playerHand, JLabel winLabel, JLabel[] playedCardLabels)
	   {
	      this.cardNumber = cardNumber;
	      this.compHand = compHand;
	      this.playerHand = playerHand;
	      this.winLabel = winLabel;
	      this.playedCardLabels = playedCardLabels;
	   }
	   
	   public void actionPerformed(ActionEvent e)
	   {
	      playedCardLabels[0].setIcon(model.getIcon(compHand.inspectCard(cardNumber)));
	      playedCardLabels[1].setIcon(model.getIcon(playerHand.inspectCard(cardNumber)));
	      
	      Card compCard = compHand.inspectCard(cardNumber);
	      
	      Card playerCard = playerHand.inspectCard(cardNumber);
	      
	      System.out.println(compCard.toString());
	      System.out.println(playerCard.toString());
	      
	      if(model.comparing(playerCard, compCard) == "WIN")
	      {
	         this.winLabel.setText("YOU WIN!");
	      }
	      else if(model.comparing(playerCard, compCard) == "LOSE"){
	         winLabel.setText("YOU LOSE!");
	      }
	      else
	      {
	         winLabel.setText("YOU TIE!");
	      }
	      
	   }
	   
	   
	}
}