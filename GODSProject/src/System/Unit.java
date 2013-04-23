package System;

import java.util.ArrayList;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;

public abstract class Unit extends Agent {

	protected Vector2 position;
	protected Vector2 goal;
	protected float speed;
	protected float life;
	protected String unitType;
	protected UnitFrame _frame;
	protected int _detectionThreshold;
	protected Unit _target;
	
	Unit(Vector2 pos)
	{
		super();
		_detectionThreshold = 60;
		//_frame = new UnitFrame();
		//EnvironmentFrame.getInstance().addUnit(this);
		//_frame.setLocation((int)pos.getX(), (int)pos.getY());
		position = new Vector2();
		position.setX(pos.getX());
		position.setY(pos.getY());
		goal = new Vector2(180, 180);
	}
	
	public Vector2 getPos()
	{
		return position;
	}
	public Vector2 getGoal() { return goal; }
	public void setGoal(Vector2 newGoal) { goal = newGoal;}
	public String getUnitType() { return unitType; }
	
	// !!! -> size of environment : max x = 568     max y = 389
	protected void moveTo(Vector2 destination)
	{
		Vector2 direction = new Vector2(destination.getX() - position.getX(), destination.getY() - position.getY());
		
		direction.normalize();

		Vector2 movement = new Vector2(direction.getX()*speed,direction.getY()*speed);
		
		if ((position.getX() + movement.getX() < 555) && (position.getY() + movement.getY() < 380) && (position.getX() + movement.getX() > 0) && (position.getY() + movement.getY() > 0))
		{
			position.setX(position.getX()+movement.getX());
			position.setY(position.getY()+movement.getY());
		}
		else
		{
			goal.setX(position.getX()-movement.getX());
			goal.setY(position.getY()-movement.getY());
		}
		
		_frame.setLocation((int)position.getX(), (int)position.getY());
	}
	
	public UnitFrame getFrame(){
		return _frame;
	}
	
	public int getXi(){
		return (int)position.getX();
	}
	
	public int getYi(){
		return (int)position.getY();
	}
	
	protected ArrayList<Unit> watchSurroundings(){
		ArrayList<Unit> _result = EnvironmentFrame.getInstance().lookAroundS(getXi(), getYi(), _detectionThreshold);
		if(_result != null){
			return _result;
		}
		else{
			return null;
		}
		
	}
	
	protected ArrayList<Unit> watchSurroundingsT(){
		ArrayList<Unit> _result = EnvironmentFrame.getInstance().lookAroundT(getXi(), getYi(), _detectionThreshold);
		if(_result != null){
			return _result;
		}
		else{
			return null;
		}
		
	}

	protected ArrayList<EnergyBall> watchSurroundingsForFood(){
		ArrayList<EnergyBall> _result = EnvironmentFrame.getInstance().lookAroundForFood(getXi(), getYi(), _detectionThreshold);
		if(_result != null){
			return _result;
		}
		else{
			return null;
		}
		
	}
	
	public boolean isCloseEnoughTo(Vector2 aPos){
		int tempX = Math.abs((int)(position.getX() - aPos.getX()));
		int tempY = Math.abs((int)(position.getY() - aPos.getY()));
		
		if( tempX + tempY  <= 5)
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	public void takesDmg(int aDmgAmount){
		life = life - aDmgAmount;
		if(life <= 0){
			isAlive = false;
			defeated();
		}
	}
	
	public int getValue(){
		return 0;
	}
	public abstract void defeated();
	protected abstract void destroyUnit();
}
