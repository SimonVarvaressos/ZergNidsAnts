package System;
import java.util.Random;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;


enum TerranodonState implements State{
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
				((Terranodon)u).changeState(Roam);
			else
				u.moveTo(u.getGoal());
		}
	}
}

public class Terranodon extends Unit {
	
	private TerranodonState state;
	
	public Terranodon(Vector2 pos)
	{
		super(pos);
		
		_frame = new UnitFrame(VisualType.TERANODON);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		
		speed = Constants.terranodonSpeed;
		state = TerranodonState.Roam;
	}

	public void changeState(TerranodonState newState) {
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
