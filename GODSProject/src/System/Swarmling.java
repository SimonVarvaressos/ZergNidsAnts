package System;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;

enum SwarmlingState implements State
{
	Attack {
		public void act(Unit u)
		{
			ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies != null)
			{
				((Swarmling)u).changeState(Attack);
				u.setGoal(ennemies.get(0).getPos());
				u.moveTo(u.getGoal());
			}
			else
			{
				((Swarmling)u).changeState(Roam);
			}
		}
	},
	Roam {
		public void act(Unit u)
		{
			//System.out.println("Grrr");
			//TO DO : Si ennemi proche, on change pour Attack
			
			
			Vector2 newGoal = new Vector2();
			Random rand = new Random();
			newGoal.setX(u.getGoal().getX() + (rand.nextInt(600) - 300));
			newGoal.setY(u.getGoal().getY() + (rand.nextInt(600) - 300));
			u.setGoal(newGoal);
			u.moveTo(u.getGoal()); 
			
			ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies != null)
			{
				((Swarmling)u).changeState(Attack);
				u.setGoal(ennemies.get(0).getPos());
				Message m = new Message(TypeMessage.EnnemyDetected, u.getGoal(), "Swarmide");
				((Swarmling)u).sendMessagetoBoss(m);
			}
		}
	},
	GoingTo {
		public void act(Unit u)
		{
			ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies != null)
			{
				((Swarmling)u).changeState(Attack);
				u.setGoal(ennemies.get(0).getPos());
				Message m = new Message(TypeMessage.EnnemyDetected, u.getGoal(), "Swarmide");
				((Swarmling)u).sendMessagetoBoss(m);
			}
			
			if (u.getPos().equals(u.getGoal()))
				((Swarmling)u).changeState(Roam);
			else
				u.moveTo(u.getGoal());
			
			//System.out.println(u.getGoal().getX() + " and " + u.getGoal().getY());
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
	
	
	
	Swarmling(Vector2 pos, Swarmide b)
	{
		super(pos);
		_frame = new UnitFrame(VisualType.SWARMLING);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		unitType = "Swarmling";
		speed = Constants.swarmlingSpeed;
		state = SwarmlingState.Roam;
		boss = b;
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
			if (m.type == TypeMessage.Attack)
			{
				goal = m.position;
				state = SwarmlingState.GoingTo;
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
	
	protected void sendMessagetoBoss(Message m)
	{
		boss.receiveMessage(m);
	}
	
	@Override
	protected void destroyUnit(){
		EnvironmentFrame.getInstance().removeSwarm(this);
		boss.destroyChild(this);
		
	}
}
