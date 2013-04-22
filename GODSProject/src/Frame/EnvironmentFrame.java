package Frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;
import System.EnergyBall;
import System.Swarmide;
import System.Swarmling;
import System.Swarmodon;
import System.Terranide;
import System.Terranling;
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
	private ArrayList<EnergyBall> _energyList = new ArrayList<EnergyBall>();
	
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
	
	synchronized public void addEnergy(EnergyBall aEnergyBall){
		_energyList.add(aEnergyBall);
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
				
			}
		}
		
		return _result;
	}
	
	synchronized public ArrayList<EnergyBall> lookAroundForFood(int aOriginX, int aOriginY, int aThreshold){
		ArrayList<EnergyBall> _result = null;
	
		for(int i=0;i<_energyList.size();i++){
			
			int tempX = Math.abs(_energyList.get(i).getXi() - aOriginX);
			int tempY = Math.abs(_energyList.get(i).getYi() - aOriginY);
			
			if( tempX + tempY  <= aThreshold)
			{
				if(_result == null)
				{
					_result = new ArrayList<EnergyBall>();
				}
				_result.add(_energyList.get(i));
				
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
	
	public synchronized void reset(){
		for (Unit s : _terranList)
		{
			s.turnOff();
			removeUnit(s.getFrame());
			s = null;
		}
		for (EnergyBall s : _energyList)
		{
			removeUnit(s.getFrame());
			s = null;
		}
		_energyList.clear();
		_terranList.clear();
		_swarmList.clear();
		System.gc();
	}
	
	synchronized public void clearSwarm(){
		_swarmList.clear();
	}
	
	synchronized public void removeUnit(UnitFrame aUnitFrame){
		_field.remove(aUnitFrame);
		
		_field.validate();
		_field.repaint();
	}
	
	synchronized public void removeSwarm(Unit aUnit){
		_swarmList.remove(aUnit);
	}
	
	synchronized public void addEnergyFromDefeatedTeran(Unit aUnit){
		
		EnergyBall temp;
		if(aUnit instanceof Terranling){
			temp = new EnergyBall(4, aUnit.getXi(), aUnit.getYi());
		}
		else if(aUnit instanceof Terranide){
			temp = new EnergyBall(8, aUnit.getXi(), aUnit.getYi());
		}
		else{
			temp = new EnergyBall(12, aUnit.getXi(), aUnit.getYi());
		}
		
		addUnit(temp.getFrame(), temp.getXi(), temp.getYi());
		_energyList.add(temp);
		//destroyTeranUnit(aUnit);
	}
	
	synchronized public void destroyTeranUnit(Unit aUnit){
		aUnit.turnOff();
		removeUnit(aUnit.getFrame());
		_terranList.remove(aUnit);
		aUnit = null;
		System.gc();
	}
	
	synchronized public void removeEnergyBall(EnergyBall aEnergyBall){
		_energyList.remove(aEnergyBall);
		removeUnit(aEnergyBall.getFrame());
		aEnergyBall = null;
	}
	
	synchronized public boolean validFoodSource(Vector2 aLocation){
		for(int i=0;i<_energyList.size();i++){
			if((_energyList.get(i).getXi() == aLocation.getX())&&(_energyList.get(i).getYi() == aLocation.getY()))
			{
				return true;
			}
		}
		return false;
	}
	
	synchronized public boolean validUnit(Unit aUnit){
		for(int i=0;i<_swarmList.size();i++){
			if(_swarmList.get(i) == aUnit)
			{
				System.out.println("its found!");
				return true;
			}
		}
		for(int j=0;j<_terranList.size();j++){
			if(_terranList.get(j) == aUnit)
			{
				System.out.println("its found!");
				return true;
			}
		}
		return false;
	}
	
	synchronized public void takeFoodFromSource(Vector2 aLocation){
		for(int i=0;i<_energyList.size();i++){
			if((_energyList.get(i).getXi() == aLocation.getX())&&(_energyList.get(i).getYi() == aLocation.getY()))
			{
				_energyList.get(i).takeFoodUnit();
				return;
			}
		}
	}
		


}
