package Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class StatisticFrame extends JPanel implements ActionListener{

	private static final String TXT_TITLE = " STATISTICS ";
	
	private static final String TXT_S = " Swarmling : ";
	private static final String TXT_M = " Swarmide : ";
	private static final String TXT_B = " Swarmodon : ";
	private static final String TXT_DIV = " / ";
	
	private static final String TXT_UPKEEP = " Upkeep : ";
	private static final String TXT_ENERGY = " Upkeep Delay : ";
	
	private static final int INIT_NULL = 0;
	private static final int INIT_FIRST = 1;
	private static final String INIT_MSG = " No message at the moment ";
	
	private static final String BTN_ACTION = "STATE_BUTTON";
	private static final String BTN_TXT_PAUSE = "Pause";
	private static final String BTN_TXT_UNPAUSE = "Unpause";

	private BorderLayout _MainLayout;
	private GridLayout _SubLayoutData;
	private GridLayout _SubLayoutNames;
	private GridLayout _SubLayoutUnion;
	private GridLayout _SubLayoutUpkeep;
	
	private TitledBorder _StatisticsBorder;
	
	private JPanel _firstPanel = new JPanel();
	private JPanel _secondPanel = new JPanel();
	private JPanel _thirdPanel = new JPanel();
	private JPanel _fourthPanel = new JPanel();
	
	private Label _lblS = new Label();
	private Label _lblSCurrent = new Label();
	private Label _lblSDivide = new Label();
	private Label _lblSMax = new Label();
	private Label _lblM = new Label();
	private Label _lblMCurrent = new Label();
	private Label _lblMDivide = new Label();
	private Label _lblMMax = new Label();
	private Label _lblB = new Label();
	private Label _lblBCurrent = new Label();
	private Label _lblBDivide = new Label();
	private Label _lblBMax = new Label();
	private Label _lblUpkeep = new Label();
	private Label _lblActualUpkeep = new Label();
	private Label _lblDelay = new Label();
	private Label _lblActualDelay = new Label();
	
	private JButton btnPause = new JButton();
	
	public StatisticFrame(){
		super();
		initGraphicData();
	}
	
	private void initGraphicData(){

		_lblS.setText(TXT_S);
		_lblSCurrent.setAlignment(Label.CENTER);
		_lblSCurrent.setText(String.valueOf(INIT_NULL));
		_lblSDivide.setAlignment(Label.CENTER);
		_lblSDivide.setText(TXT_DIV);
		_lblSMax.setAlignment(Label.CENTER);
		_lblSMax.setText(String.valueOf(INIT_NULL));
		
		_lblM.setText(TXT_M);
		_lblMCurrent.setAlignment(Label.CENTER);
		_lblMCurrent.setText(String.valueOf(INIT_NULL));
		_lblMDivide.setAlignment(Label.CENTER);
		_lblMDivide.setText(TXT_DIV);
		_lblMMax.setAlignment(Label.CENTER);
		_lblMMax.setText(String.valueOf(INIT_NULL));
		
		_lblB.setText(TXT_B);
		_lblBCurrent.setAlignment(Label.CENTER);
		_lblBCurrent.setText(String.valueOf(INIT_NULL));
		_lblBDivide.setAlignment(Label.CENTER);
		_lblBDivide.setText(TXT_DIV);
		_lblBMax.setAlignment(Label.CENTER);
		_lblBMax.setText(String.valueOf(INIT_FIRST));
		
		_lblUpkeep.setText(TXT_ENERGY);
		_lblActualUpkeep.setAlignment(Label.RIGHT);
		_lblActualUpkeep.setText(String.valueOf(INIT_NULL));
		
		_lblDelay.setText(TXT_ENERGY);
		_lblActualDelay.setAlignment(Label.RIGHT);
		_lblActualDelay.setText(String.valueOf(INIT_NULL));
		
		btnPause.addActionListener(this);
		btnPause.setActionCommand(BTN_ACTION);
		btnPause.setText(BTN_TXT_PAUSE);
		
		_MainLayout = new BorderLayout();
		_SubLayoutData = new GridLayout(3,3);
		_SubLayoutNames = new GridLayout(3,1);
		_SubLayoutUnion = new GridLayout(1,2);
		_SubLayoutUpkeep = new GridLayout(2,2);
		
		_StatisticsBorder = BorderFactory.createTitledBorder(TXT_TITLE);
		
		//_firstPanel.setPreferredSize(new Dimension(120,120));
		
		_firstPanel.setLayout(_SubLayoutData);
		_firstPanel.add(_lblSCurrent);	_firstPanel.add(_lblSDivide);	_firstPanel.add(_lblSMax);
		_firstPanel.add(_lblMCurrent);	_firstPanel.add(_lblMDivide);	_firstPanel.add(_lblMMax);
		_firstPanel.add(_lblBCurrent);	_firstPanel.add(_lblBDivide);	_firstPanel.add(_lblBMax);

		_secondPanel.setLayout(_SubLayoutNames);
		_secondPanel.add(_lblS);
		_secondPanel.add(_lblM);
		_secondPanel.add(_lblB);
		
		_thirdPanel.setLayout(_SubLayoutUnion);
		_thirdPanel.add(_secondPanel);	_thirdPanel.add(_firstPanel);
		
		_fourthPanel.setLayout(_SubLayoutUpkeep);
		_fourthPanel.add(_lblUpkeep);	_fourthPanel.add(_lblActualUpkeep);
		_fourthPanel.add(_lblDelay);	_fourthPanel.add(_lblActualDelay);
		
		this.setBorder(_StatisticsBorder);
		this.setLayout(_MainLayout);
		this.add(_thirdPanel, BorderLayout.NORTH);
		this.add(_fourthPanel, BorderLayout.CENTER);
		this.add(btnPause, BorderLayout.SOUTH);
		//this.setPreferredSize(new Dimension(275,125));
		
		this.setVisible(true);
	}
	
	public void updateAllData(int anAmountofS, int anAmountofM, int anAmountofB, int anUpkeep, float aDelay){
		_lblSCurrent.setText(String.valueOf(anAmountofS));
		_lblSMax.setText(String.valueOf(anAmountofM * 5));

		_lblMCurrent.setText(String.valueOf(anAmountofM));
		_lblMMax.setText(String.valueOf(anAmountofB * 3));

		_lblBCurrent.setText(String.valueOf(anAmountofB));
		_lblBMax.setText(String.valueOf(anAmountofB + 1));

		_lblActualUpkeep.setText(String.valueOf(anUpkeep));

		_lblActualDelay.setText(String.valueOf(aDelay));
	}
	
	public void updateSwarmling(int anAmountofS, int anAmountofM){
		_lblSCurrent.setText(String.valueOf(anAmountofS));
		_lblSMax.setText(String.valueOf(anAmountofM * 5));
	}
	
	public void updateSwarmide(int anAmountofM, int anAmountofB){
		_lblMCurrent.setText(String.valueOf(anAmountofM));
		_lblMMax.setText(String.valueOf(anAmountofB * 3));
	}
	
	public void updateSwarmodon(int anAmountofB){
		_lblBCurrent.setText(String.valueOf(anAmountofB));
		_lblBMax.setText(String.valueOf(anAmountofB + 1));
	}

	public void updateUpkeep(int anUpkeep){
		_lblActualUpkeep.setText(String.valueOf(anUpkeep));
	}
	
	public void updateDelay(float aDelay){
		_lblActualDelay.setText(String.valueOf(aDelay));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == BTN_ACTION)
		{  
			//TODO pause prompt
		} 
	}
}