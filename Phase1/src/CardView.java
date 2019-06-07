import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class CardView extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int MAX_CARDS_PER_HAND = 56;
	static int MAX_PLAYERS = 2; // for now we only allow 2 person games
	private int numCardsPerHand;
	private int numPlayers;
	
	public JPanel pnlComputerHand;
	public JPanel pnlHumanHand;
	public JPanel pnlPlayArea;
	
	
	
	CardView()
	{
		super( );
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(new BorderLayout());
	}
	
	// provite an acessor, but no mutator other than constructor.
	CardView(String title, int numCardsPerHand, int numPlayers)
	{
		Dimension size = getPreferredSize();
		size.height = 150;
		
		this.numPlayers = numPlayers;
		this.numCardsPerHand = numCardsPerHand;
		
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
		
		
		pnlPlayArea = new JPanel();
		TitledBorder borderPlay = new TitledBorder("Playing Area");
		borderPlay.setTitleJustification(TitledBorder.LEFT);
		borderPlay.setTitlePosition(TitledBorder.TOP);
		pnlPlayArea.setBorder(borderPlay);
		pnlPlayArea.setLayout(new GridLayout(2,3));
		pnlPlayArea.setPreferredSize(size);
		this.add(pnlPlayArea,BorderLayout.CENTER);
	}
}