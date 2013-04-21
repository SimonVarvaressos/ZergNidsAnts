package Frame;
import System.Overmind;
import System.Terranide;
import System.Terranling;
import System.Terranodon;
import System.Vector2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;


public class ToolBar extends JToolBar implements ActionListener{

	//constantes de chemin des ressources graphiques
	private static final String SMALL_S_ICON_PATH = "Ressources/AddSS.PNG";
	private static final String MEDIUM_S_ICON_PATH = "Ressources/AddMS.PNG";
	private static final String BIG_S_ICON_PATH = "Ressources/AddBS.PNG";
	private static final String SMALL_T_ICON_PATH = "Ressources/AddST.PNG";
	private static final String MEDIUM_T_ICON_PATH = "Ressources/AddMT.PNG";
	private static final String BIG_T_ICON_PATH = "Ressources/AddBT.PNG";
	private static final String SWARMICIDE_ICON_PATH = "Ressources/Swarmicide.PNG";
	private static final String OVERDRIVE_ICON_PATH = "Ressources/Overdrive.PNG";

	//constantes de commandes d'actions pour les bouttons
	private static final String ASS_ACTION = "ADD_SMALLS_BUTTON";
	private static final String AMS_ACTION = "ADD_MEDIUMS_BUTTON";
	private static final String ABS_ACTION = "ADD_BIGS_BUTTON";
	private static final String AST_ACTION = "ADD_SMALLT_BUTTON";
	private static final String AMT_ACTION = "ADD_MEDIUMT_BUTTON";
	private static final String ABT_ACTION = "ADD_BIGT_BUTTON";
	private static final String SWARMICIDE_ACTION = "SWARMICIDE_BUTTON";
	private static final String OVERDRIVE_ACTION = "OVERDRIVE_BUTTON";
	
	//constantes de texte pour les tooltips
	private static final String TXT_ADD_SWARMLING = "Add a swarmling";
	private static final String TXT_ADD_SWARMIDE = "Add a swarmide";
	private static final String TXT_ADD_SWARMODON = "Add a swarmodon";
	
	private static final String TXT_ADD_TERANLING = "Add a teranling";
	private static final String TXT_ADD_TERANIDE = "Add a teranide";
	private static final String TXT_ADD_TERANODON = "Add a teranodon";
		
	private static final String TXT_SWARNICIDE = "Activate swarmicide";
	private static final String TXT_OVERDRIVE = "!";

	
	//Buttons
	private JButton btnAddSLing = new JButton();
	private JButton btnAddSIde = new JButton();
	private JButton btnAddSOdon = new JButton();
	
	private JButton btnAddTLing = new JButton();
	private JButton btnAddTIde = new JButton();
	private JButton btnAddTOdon = new JButton();
	
	private JButton btnSwarmicide = new JButton();
	private JButton btnOP = new JButton();

	
	/**
	 * @author Nicolas Bourgault
	 */
	public ToolBar(){
		super();
		
		btnAddSLing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                Overmind.getInstance().produceSwarmling();
            }
		});
		btnAddSLing.setActionCommand(ASS_ACTION);
		btnAddSLing.setIcon(new ImageIcon(this.getClass().getResource(SMALL_S_ICON_PATH)));
		btnAddSLing.setToolTipText(TXT_ADD_SWARMLING);
		add(this.btnAddSLing);
		
		btnAddSIde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                Overmind.getInstance().produceSwarmide();
            }
		});
		btnAddSIde.setActionCommand(AMS_ACTION);
		btnAddSIde.setIcon(new ImageIcon(this.getClass().getResource(MEDIUM_S_ICON_PATH)));
		btnAddSIde.setToolTipText(TXT_ADD_SWARMIDE);
		this.add(this.btnAddSIde);
		
		btnAddSOdon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                Overmind.getInstance().produceSwarmodon();
            }
		});
		btnAddSOdon.setActionCommand(ABS_ACTION);
		btnAddSOdon.setIcon(new ImageIcon(this.getClass().getResource(BIG_S_ICON_PATH)));
		btnAddSOdon.setToolTipText(TXT_ADD_SWARMODON);
		this.add(this.btnAddSOdon);
		

		this.add(new JSeparator(SwingConstants.HORIZONTAL));

		
		btnAddTLing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				Vector2 vect = new Vector2(0,180);
                Terranling ling = new Terranling(vect);
            }
		});
		btnAddTLing.setActionCommand(AST_ACTION);
		btnAddTLing.setIcon(new ImageIcon(this.getClass().getResource(SMALL_T_ICON_PATH)));
		btnAddTLing.setToolTipText(TXT_ADD_TERANLING);
		add(this.btnAddTLing);
		
		btnAddTIde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				Vector2 vect = new Vector2(0,180);
                Terranide ide = new Terranide(vect);
            }
		});
		btnAddTIde.setActionCommand(AMT_ACTION);
		btnAddTIde.setIcon(new ImageIcon(this.getClass().getResource(MEDIUM_T_ICON_PATH)));
		btnAddTIde.setToolTipText(TXT_ADD_TERANIDE);
		this.add(this.btnAddTIde);
		
		btnAddTOdon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
				Vector2 vect = new Vector2(0,180);
                Terranodon odon = new Terranodon(vect);
            }
		});
		btnAddTOdon.setActionCommand(ABT_ACTION);
		btnAddTOdon.setIcon(new ImageIcon(this.getClass().getResource(BIG_T_ICON_PATH)));
		btnAddTOdon.setToolTipText(TXT_ADD_TERANODON);
		this.add(this.btnAddTOdon);
		
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		btnSwarmicide.addActionListener(this);
		btnSwarmicide.setActionCommand(SWARMICIDE_ACTION);
		btnSwarmicide.setIcon(new ImageIcon(this.getClass().getResource(SWARMICIDE_ICON_PATH)));
		btnSwarmicide.setToolTipText(TXT_SWARNICIDE);
		this.add(this.btnSwarmicide);
		
		btnOP.addActionListener(this);
		btnOP.setActionCommand(OVERDRIVE_ACTION);
		btnOP.setIcon(new ImageIcon(this.getClass().getResource(OVERDRIVE_ICON_PATH)));
		btnOP.setToolTipText(TXT_OVERDRIVE);
		this.add(this.btnOP);
		
	}
	

	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == ASS_ACTION)
		{  
			//TODO
		} 
		else if (e.getActionCommand() == AMS_ACTION)
		{ 
			//TODO
		} 
		else if (e.getActionCommand() == ABS_ACTION)
		{ 
			//TODO
		} 
		else if(e.getActionCommand() == AST_ACTION)
		{
			//TODO
		}
		else if (e.getActionCommand() == AMT_ACTION)
		{
			//TODO	
		} 
		else if (e.getActionCommand() == ABT_ACTION)
		{ 
			//TODO
		} 
		else if(e.getActionCommand() == SWARMICIDE_ACTION)
		{ 
			//TODO
		} 
		else if(e.getActionCommand() == OVERDRIVE_ACTION)
		{
			//TODO
		}
		
	}
	
}
