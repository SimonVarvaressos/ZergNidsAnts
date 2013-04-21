package Frame;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;
import System.Unit;

// !!! -> size of environment : max x = 568     max y = 389

public class EnvironmentFrame extends JPanel{


	private static final String TXT_TITLE = " ENVIRONMENT ";
	
	private BorderLayout _MainLayout = new BorderLayout();
	
	private JPanel _field = new JPanel();
	
	private TitledBorder _EnvironmentBorder = new TitledBorder(TXT_TITLE);
	
	private UnitFrame _overlord = new UnitFrame(VisualType.OVERMIND);
	
	private static EnvironmentFrame _instance;
	//private Thread _threadified = new Thread(this);
	
	
	public static EnvironmentFrame getInstance(){
		if (EnvironmentFrame._instance == null){
			EnvironmentFrame._instance = new EnvironmentFrame();
		}
		return EnvironmentFrame._instance;
	}
	
	private EnvironmentFrame(){
		super();
		initField();
		//_threadified.start();
	}
	
	private void initField(){
		this.setLayout(_MainLayout);
		this.setBorder(_EnvironmentBorder);
		_field.setLayout(null);
		_field.setBackground(java.awt.Color.GRAY);
		this.add(_field,BorderLayout.CENTER);

		//addUnit(_overlord, 545, 170);
	}
	
	synchronized public void addUnit(UnitFrame aUnitFrame,int aX, int aY){
		aUnitFrame.setLocation(aX,aY);
		_field.add(aUnitFrame);

		_field.validate();
		_field.repaint();
	}
	
	synchronized public void removeUnit(UnitFrame aUnitFrame){
		_field.remove(aUnitFrame);
		
		_field.validate();
		_field.repaint();
	}
	
}
