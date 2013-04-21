package System;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;

public abstract class Unit extends Agent {

	protected Vector2 position;
	protected Vector2 goal;
	protected float speed;
	protected float life;
	protected String unitType;
	protected UnitFrame _frame;
	
	Unit(Vector2 pos)
	{
		super();
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
	
}
