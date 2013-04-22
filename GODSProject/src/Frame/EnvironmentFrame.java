package Frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;
import System.Swarmide;
import System.Swarmling;
import System.Swarmodon;
import System.Unit;
import System.Vector2;

// !!! -> size of environment : max x = 568     max y = 389

public class EnvironmentFrame extends JPanel{


	private static final String TXT_TITLE = " ENVIRONMENT ";
	
	private BorderLayout _MainLayout = new BorderLayout();
	
	private JPanel _field = new JPanel();
	
	private TitledBorder _EnvironmentBorder = new TitledBorder(TXT_TITLE);
	
	private UnitFrame _overlord = new UnitFrame(VisualType.OVERMIND);
	
	private static EnvironmentFrame _instance;
	//private Thread _threadified = new Thread(this);
	
	private ArrayList<Unit> _terranList = new ArrayList<Unit>();
	private ArrayList<Unit> _swarmList = new ArrayList<Unit>();
	
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
	
	synchronized public void addTeran(Unit aUnit){
		_terranList.add(aUnit);
	}
	
	synchronized public void addSwarm(Unit aUnit){
		_swarmList.add(aUnit);
	}
	
	synchronized public void removeSwarm(Unit aUnit){
		_swarmList.remove(aUnit);
	}
	
	synchronized public ArrayList<Unit> lookAroundS(int aOriginX, int aOriginY, int aThreshold){
		ArrayList<Unit> _result = null;
	
		for(int i=0;i<_terranList.size();i++){
			
			int tempX = Math.abs(_terranList.get(i).getXi() - aOriginX);
			int tempY = Math.abs(_terranList.get(i).getYi() - aOriginY);
			
			if( tempX + tempY  <= aThreshold)
			{
				if(_result == null)
				{
					_result = new ArrayList<Unit>();
				}
				_result.add(_terranList.get(i));
				
				//to remove after detection tests
				//_terranList.get(i).getFrame().updateVisualType(VisualType.D_TERANLING);
				//_field.validate();
				//_field.repaint();
			}
		}
		
		return _result;
	}
	
	synchronized public ArrayList<Unit> lookAroundT(int aOriginX, int aOriginY, int aThreshold){
		ArrayList<Unit> _result = null;
	
		for(int i=0;i<_swarmList.size();i++){
			
			int tempX = Math.abs(_swarmList.get(i).getXi() - aOriginX);
			int tempY = Math.abs(_swarmList.get(i).getYi() - aOriginY);
			
			if( tempX + tempY  <= aThreshold)
			{
				if(_result == null)
				{
					_result = new ArrayList<Unit>();
				}
				_result.add(_swarmList.get(i));
				
				//to remove after detection tests
				//_swarmList.get(i).getFrame().updateVisualType(VisualType.D_TERANLING);
				//_field.validate();
				//_field.repaint();
			}
		}
		
		return _result;
	}
	
	synchronized public void addUnit(UnitFrame aUnitFrame,int aX, int aY){
		aUnitFrame.setLocation(aX,aY);
		_field.add(aUnitFrame);

		_field.validate();
		_field.repaint();
	}
	
	public void reset(){
		for (Unit s : _terranList)
		{
			s.turnOff();
			removeUnit(s.getFrame());
			s = null;
		}
	}
	
	synchronized public void removeUnit(UnitFrame aUnitFrame){
		_field.remove(aUnitFrame);
		
		_field.validate();
		_field.repaint();
	}
	
	synchronized public void destroyTeranUnit(Unit aUnit){
		aUnit.turnOff();
		removeUnit(aUnit.getFrame());
		_terranList.remove(aUnit);
		aUnit = null;
		System.gc();
	}

}
