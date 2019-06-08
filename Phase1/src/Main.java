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



/* Testing loading cards
      // prepare the image icon array
      CardModel.loadCardIcons();
      
      // establish main frame in which program will run
      JFrame frmMyWindow = new JFrame("Card Room");
      frmMyWindow.setSize(1150, 650);
      frmMyWindow.setLocationRelativeTo(null);
      frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // set up layout which will control placement of buttons, etc.
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);   
      frmMyWindow.setLayout(layout);
      
      // prepare the image label array
      int k=0;
      JLabel[] labels = new JLabel[CardModel.NUM_CARD_IMAGES];
      for (int i = 0; i <= 13; i++)
      {
      	for (int j = 0; j <=3; j++)
      	{
      		labels[k] = new JLabel(CardModel.iconCards[i][j]);
      		k++;
      	}
      }
      
      int c = 1;
      // place your 3 controls into frame
      for (k = 0; k < CardModel.NUM_CARD_IMAGES-1; k++)
      {   
      	System.out.println(c);
      	c++;
      	frmMyWindow.add(labels[k]);
      
      }
      // show everything to the user
      frmMyWindow.setVisible(true);
*/
	}
}