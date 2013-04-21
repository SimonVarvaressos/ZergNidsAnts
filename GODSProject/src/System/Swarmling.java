package System;
import java.util.Random;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;

enum SwarmlingState implements State
{
	Attack {
		public void act(Unit u)
		{
			//TO DO
		}
	},
	Roam {
		public void act(Unit u)
		{
			//System.out.println("Grrr");
			//TO DO : Si ennemi proche, on change pour Attack
			
			
			Vector2 newGoal = new Vector2();
			Random rand = new Random();
			newGoal.setX(u.getGoal().getX() + (rand.nextInt(4) - 2));
			newGoal.setY(u.getGoal().getY() + (rand.nextInt(4) - 2));
			u.setGoal(newGoal);
			u.moveTo(newGoal); 
		}
	},
	GoingTo {
		public void act(Unit u)
		{
			if (u.getPos() == u.getGoal())
				((Swarmling)u).changeState(Roam);
			else
				u.moveTo(u.getGoal());
		}
	},
	Gather {
		public void act(Unit u)
		{
			
		}
	}
}

public class Swarmling extends Unit{
	
	private Swarmide boss;
	private SwarmlingState state;
	
	
	
	Swarmling(Vector2 pos)
	{
		super(pos);
		_frame = new UnitFrame(VisualType.SWARMLING);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		unitType = "Swarmling";
		speed = Constants.swarmlingSpeed;
		goal = new Vector2();
		state = SwarmlingState.Roam;
	}
	
	public void changeState(SwarmlingState newState)
	{
		state = newState;
	}

	@Override
	protected void checkMessages() {
		if (!boiteMessages.isEmpty())
		{
			Message m = boiteMessages.remove();
			System.out.println("Swarmling received message - " + m.type.toString());
			if (m.type == TypeMessage.Attack)
			{
				goal = m.position;
				state = SwarmlingState.Attack;
			}
			else if (m.type == TypeMessage.GoTo)
			{
				goal = m.position;
				state = SwarmlingState.GoingTo;
			}
			else if (m.type == TypeMessage.Loot)
			{
				goal = m.position;
				state = SwarmlingState.Gather;
			}
		}
		
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