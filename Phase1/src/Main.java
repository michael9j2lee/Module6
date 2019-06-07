import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Main {
	static int NUM_CARDS_PER_HAND = 7;
    static int  NUM_PLAYERS = 2;
   
	
	
	public static void main(String[] args)
	{	  
		CardModel model = new CardModel();
		CardView view = new CardView("TITLE",NUM_CARDS_PER_HAND,NUM_PLAYERS);
		CardController controller = new CardController(model,view);
		
		 int numPacksPerDeck = 1;
	     int numJokersPerPack = 2;
	     int numUnusedCardsPerPack = 0;
	     Card[] unusedCardsPerPack = null;
	     
		
		controller.startNewGame(numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack, unusedCardsPerPack, NUM_PLAYERS, NUM_CARDS_PER_HAND);
	 //Instatiate highCardGame

		
	   CardView myCardTable = new CardView("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
	   myCardTable.setSize(800, 600);
	   myCardTable.setLocationRelativeTo(null);
	   myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   // show everything to the user
	   myCardTable.setVisible(true);
	}
}