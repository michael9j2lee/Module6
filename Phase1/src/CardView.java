import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;




public class CardView extends JFrame
{
   private static final long serialVersionUID = 1L;
   static int MAX_CARDS_PER_HAND = 56;
   static int MAX_PLAYERS = 2; // for now we only allow 2 person games
   private static int numCardsPerHand;
   private static int numPlayers;
   
   JLabel[] computerLabels;
   JButton[] humanButtons;  
   JLabel[] playedCardLabels; 
   JLabel[] playLabelText; 
   JLabel winLabel;
   
   JPanel pnlComputerHand;
   JPanel pnlHumanHand;
   JPanel pnlPlayArea;
   JLabel lblTimer;
   
   JButton btnStartStop;

   
   CardView()
   {
      super( );
//      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //setLayout(new BorderLayout());
   }
   
   // provite an acessor, but no mutator other than constructor.
   CardView(String title, int numCardsPerHand, int numPlayers)
   {
	   setSize(800, 600);
	   setLocationRelativeTo(null);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
      Dimension size = getPreferredSize();
      size.height = 150;
      
      this.numPlayers = numPlayers;
      this.numCardsPerHand = numCardsPerHand;
      System.out.printf("CardView:%nNumPlayers : %d, NumCards : %d %n",numPlayers, numCardsPerHand);
      
      //PANELS
      pnlComputerHand = new JPanel();
      pnlComputerHand.setLayout(new GridLayout(1,numCardsPerHand));
      
      pnlComputerHand.setPreferredSize(size);
      TitledBorder borderComp = new TitledBorder("Computer Hand");
      borderComp.setTitleJustification(TitledBorder.LEFT);
      borderComp.setTitlePosition(TitledBorder.TOP);
      pnlComputerHand.setBorder(borderComp);
      this.add(pnlComputerHand, BorderLayout.NORTH);
      
      pnlHumanHand = new JPanel();
      TitledBorder borderHuman = new TitledBorder("Your Hand");
      borderHuman.setTitleJustification(TitledBorder.LEFT);
      borderHuman.setTitlePosition(TitledBorder.TOP);
      pnlHumanHand.setBorder(borderHuman);
      pnlHumanHand.setLayout(new GridLayout(1,5));
      //pnlHumanHand.setBorder(BorderFactory.createLineBorder(Color.black));
      pnlHumanHand.setPreferredSize(size);
      this.add(pnlHumanHand, BorderLayout.SOUTH);
      
      computerLabels = new JLabel[numCardsPerHand];
      humanButtons = new JButton[numCardsPerHand];  
      playedCardLabels  = new JLabel[numPlayers]; 
      playLabelText  = new JLabel[numPlayers]; 
      
      
	   for (int a = 0; a < numCardsPerHand; a++)
	   {
	     computerLabels[a] = new JLabel();
	     humanButtons[a] = new JButton();
	     pnlHumanHand.add(humanButtons[a]);
	     pnlComputerHand.add(computerLabels[a]);
	     
	   }
      
     
      
	   //Iterate through number of players
	   for (int b = 0; b < numPlayers ; b++)
	   {
		   playedCardLabels[b] = new JLabel();
		   playedCardLabels[b].setHorizontalAlignment(JLabel.CENTER);
		   playLabelText[b] = new JLabel();
		   playLabelText[b].setHorizontalAlignment(JLabel.CENTER);
	   }
	  
      pnlPlayArea = new JPanel();
      TitledBorder borderPlay = new TitledBorder("Playing Area");
      borderPlay.setTitleJustification(TitledBorder.LEFT);
      borderPlay.setTitlePosition(TitledBorder.TOP);
      pnlPlayArea.setBorder(borderPlay);
      pnlPlayArea.setLayout(new GridLayout(2,3));
      pnlPlayArea.setPreferredSize(size);

      
	   Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 25);
	   winLabel = new JLabel();
	   winLabel.setFont(labelFont);
	   winLabel.setHorizontalAlignment(JLabel.CENTER);
	   
	   
	   // TIMER PANEL
	   
	   JPanel pnlTimer = new JPanel();
	   pnlTimer.setLayout(new GridLayout(2,0));
	   
	   lblTimer = new JLabel();
	   lblTimer = new JLabel();
	   lblTimer.setHorizontalAlignment(JLabel.CENTER);
	   lblTimer.setText("0");
	   lblTimer.setBorder(BorderFactory.createTitledBorder("Current Time"));
	   btnStartStop = new JButton();
	   btnStartStop.setSize(5,5);
	   btnStartStop.setText("Start/Stop");

	   pnlTimer.add(lblTimer);
	   pnlTimer.add(btnStartStop);
	   
	   
      
	  pnlPlayArea.add(playedCardLabels[0]); 
	  pnlPlayArea.add(winLabel);
	  pnlPlayArea.add(playedCardLabels[1]);  
	  pnlPlayArea.add(playLabelText[0]);
	  pnlPlayArea.add(pnlTimer);
	  pnlPlayArea.add(playLabelText[1]);
	  playLabelText[0].setText("Computer");
	  playLabelText[1].setText("You");
	  
      this.add(pnlPlayArea,BorderLayout.CENTER);
      
      setVisible(true);
   }
}