import javax.swing.JFrame;




public class Main {
	static int NUM_CARDS_PER_HAND = 7;
    static int  NUM_PLAYERS = 2;
   
	
	
	public static void main(String[] args)
	{	
		CardView view = new CardView("TITLE",NUM_CARDS_PER_HAND,NUM_PLAYERS);
		CardModel model = new CardModel();
		CardController controller = new CardController(model,view);
		

	   //Instatiate highCardGame

		
	   CardView myCardTable = new CardView("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
	   myCardTable.setSize(800, 600);
	   myCardTable.setLocationRelativeTo(null);
	   myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
		 int numPacksPerDeck = 1;
	     int numJokersPerPack = 2;
	     int numUnusedCardsPerPack = 0;
	     Card[] unusedCardsPerPack = null;
	     
		
		controller.startNewGame(numPacksPerDeck, numJokersPerPack, numUnusedCardsPerPack, unusedCardsPerPack, NUM_PLAYERS, NUM_CARDS_PER_HAND);
	   // show everything to the user
	   myCardTable.setVisible(true);
	}
}