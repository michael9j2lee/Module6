import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;



public class Main 
{
	static int NUM_CARDS_PER_HAND = 7;
   static int  NUM_PLAYERS = 2;
   

	public static void main(String[] args)
	{	

		CardView view = new CardView("TITLE",NUM_CARDS_PER_HAND,NUM_PLAYERS);
		CardModel model = new CardModel();
		CardController controller = new CardController(model,view);
		
		 int numPacksPerDeck = 1;
	    int numJokersPerPack = 2;
	    int numUnusedCardsPerPack = 0;
	    Card[] unusedCardsPerPack = null;
	     
		
		controller.startNewGame(numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack, unusedCardsPerPack, NUM_PLAYERS, NUM_CARDS_PER_HAND);
	   // show everything to the user
	}
}