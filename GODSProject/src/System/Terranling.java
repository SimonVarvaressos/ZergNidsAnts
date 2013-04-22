package System;
import java.util.Random;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;


enum TerranlingState implements State{
	Attack {
		public void act(Unit u)
		{
			//TO DO
		}
	},
	Roam {
		public void act(Unit u)
		{
			//TO DO : Si ennemi proche, on change pour Attack
			Vector2 newGoal = new Vector2();
			Random rand = new Random();
			newGoal.setX(u.getGoal().getX() + (rand.nextInt(600) - 300));
			newGoal.setY(u.getGoal().getY() + (rand.nextInt(600) - 300));
			u.setGoal(newGoal);
			u.moveTo(newGoal);
		}
	},
	GoingTo {
		public void act(Unit u)
		{
			if (u.getPos().equals(u.getGoal()))
				((Terranling)u).changeState(Roam);
			else
				u.moveTo(u.getGoal());
		}
	}
}

public class Terranling extends Unit {
	
	private TerranlingState state;
	
	public Terranling(Vector2 pos)
	{
		super(pos);
		
		_frame = new UnitFrame(VisualType.TERANLING);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		
		speed = Constants.terranlingSpeed;
		state = TerranlingState.Roam;
	}

	public void changeState(TerranlingState newState) {
		state = newState;
	}

	@Override
	protected void destroyUnit(){
		EnvironmentFrame.getInstance().destroyTeranUnit(this);
	}
	
	@Override
	protected void checkMessages() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void act() {
		if (state != null)
		{
			state.act(this);
			//System.out.println("Yolo");
		}
		
	}


}
