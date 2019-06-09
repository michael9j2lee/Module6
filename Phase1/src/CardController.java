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
   static Timer timer;
   static boolean startOrStop;

   
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
	   int k;
	     CardGameFramework highCardGame = new CardGameFramework( 
	             numPacksPerDeck, numJokersPerPack,  
	             numUnusedCardsPerPack, unusedCardsPerPack, 
	             NUM_PLAYERS, NUM_CARDS_PER_HAND);
		  
		  // start new game

		  highCardGame.newGame();
		  highCardGame.deal();
		  compHand = highCardGame.getHand(0);
		  playerHand = highCardGame.getHand(1);
  

	   
		  
	   // ActionListener add
	   for (int a = 0; a < NUM_CARDS_PER_HAND; a++)
	   {
		   view.humanButtons[a].addActionListener(new playCard(a,compHand, playerHand, view.winLabel, view.playedCardLabels));
	   }
	   
	   view.btnStartStop.addActionListener(new ActionListener()
	   {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(startOrStop)
			{
				timer.stop();
				startOrStop = false;
			}
			else
			{
				int count = Integer.valueOf(view.lblTimer.getText());
				
				TimeClass timeClass = new TimeClass(count);
				timer = new Timer(1000,timeClass);
				timer.start();
				startOrStop = true;
			}

		}
	   });
	   
	  model.loadCardIcons();
	  
	   // ADD LABELS TO PANELS -----------------------------------------
	   for (k = 0; k < NUM_CARDS_PER_HAND; k++)
	   {
		   view.computerLabels[k].setIcon(model.getBackCardIcon());    
	       view.humanButtons[k].setIcon(model.getIcon(playerHand.inspectCard(k)));
	       System.out.printf("%d%n", k);
	   }
	  view.setVisible(true);   
   }
   
   private class TimeClass implements ActionListener
   {
	   private int counter;
	   
	   TimeClass(int count)
	   {
		   this.counter = count;
	   }
	   @Override
	   public void actionPerformed(ActionEvent e)
	   {
		   counter++;
		   
		   view.lblTimer.setText(Integer.toString(counter));
		   
	   }
   }// end packer inner class 

	static class playCard implements ActionListener
	{
	   private int cardNumber;
	   private Hand compHand;
	   private Hand playerHand;
	   private JLabel winLabel;
	   private JLabel playedCardLabels[];
	   
	   public playCard(int cardNumber, Hand compHand, Hand playerHand, JLabel winLabel, JLabel[] playedCardLabels)
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
