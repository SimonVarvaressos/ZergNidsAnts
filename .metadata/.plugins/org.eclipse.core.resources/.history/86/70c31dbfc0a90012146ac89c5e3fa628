package Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class AppFrame extends JFrame implements WindowListener{
	
	public static final String TITLE = "We Are The Swarm";
	public static final String EXIT_TITLE = "Quitter";
	public static final String EXIT_MESS = "Voulez-vous vraiment quitter?";
	
	private static AppFrame windowFrame= null; 
	private BorderLayout _mainWindowLayout = new BorderLayout();
	private BorderLayout _mainWindowSubLayout1 = new BorderLayout();
	private BorderLayout _mainWindowSubLayout2 = new BorderLayout();
	
	//needed panels
	private JPanel _PrimePanel = new JPanel();
	private JPanel _SecondPanel = new JPanel();

	//components
	private ToolBar _toolbar = new ToolBar();
	private OvermindFrame _overmindFrame = new OvermindFrame();
	private StatisticFrame _stats = new StatisticFrame();
	
	private AppFrame(){
		super();
		
		this.setSize(800,640);
		this.setTitle(AppFrame.TITLE);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		this.setLayout(_mainWindowLayout);

		initGraphics();
		initToolbar();
		
		this.add(_toolbar,BorderLayout.NORTH);
		this.add(_PrimePanel, BorderLayout.CENTER);

		this.setVisible(true);
	}
	
	public static AppFrame getInstance(){
		if (AppFrame.windowFrame == null){
			AppFrame.windowFrame = new AppFrame();
		}
		return AppFrame.windowFrame;
	}
	
	private void initToolbar(){
		_toolbar.setFloatable(false);
		_toolbar.setRollover(true);
	}
	
	private void initGraphics(){
		//TODO adjust when the classes are done
		
		_SecondPanel.setLayout(_mainWindowSubLayout2);
		_SecondPanel.add(_overmindFrame, BorderLayout.SOUTH);
		//_SecondPanel.add([environment], BorderLayout.CENTER);
		//_SecondPanel.setPreferredSize(new Dimension(200,450));
		_SecondPanel.setVisible(true);
		
		_PrimePanel.setLayout(_mainWindowSubLayout1);
		_PrimePanel.add(_stats, BorderLayout.EAST);
		_PrimePanel.add(_SecondPanel, BorderLayout.CENTER);
		//_PrimePanel.setPreferredSize(new Dimension(200,450));
		_PrimePanel.setVisible(true);
		
		
		/*_agentPanel.setBorder(_brdAgents);
		_agentPanel.setLayout(_mainWindowSubLayout1);
		_agentPanel.add(_agent1.getForm());
		_agentPanel.add(_agent2.getForm());
		_agentPanel.add(_agent3.getForm());
		_agentPanel.setPreferredSize(new Dimension(200,450));
		_agentPanel.setVisible(true);*/
	}
	
	public void exitProgram(){
		int selection = JOptionPane.showConfirmDialog(AppFrame.getInstance(),AppFrame.EXIT_MESS,
													AppFrame.EXIT_TITLE,JOptionPane.YES_NO_OPTION,
													JOptionPane.QUESTION_MESSAGE);
		if(selection == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}
	public void windowClosing(WindowEvent arg0) {
		this.exitProgram();
	}
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub	
	}
}
