package System;
import java.util.Random;
import java.util.Vector;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;

enum SwarmideState implements State{
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
				((Swarmide)u).changeState(Roam);
			else
				u.moveTo(u.getGoal());
		}
	}
}

public class Swarmide extends Unit{
	
	private Swarmodon boss;
	private SwarmideState state;
	private Vector<Swarmling> children;
	
	Swarmide(Vector2 pos)
	{
		super(pos);
		
		_frame = new UnitFrame(VisualType.SWARMIDE);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		
		children = new Vector<Swarmling>();
		unitType = "Swarmide";
		speed = Constants.swarmideSpeed;
		state = SwarmideState.Roam;
	}
	
	public void changeState(SwarmideState newState) {
		state = newState;
	}

	public boolean canProduceSwarmlings()
	{
		if (children.size() < Constants.swarmlingsMax)
			return true;
		else
			return false;
	}
	
	public void addSwarmling(Swarmling s)
	{
		children.add(s);
	}
	
	public Vector<Swarmling> getSwarmlings()
	{
		return children;
	}

	@Override
	protected void checkMessages() {

		if (!boiteMessages.isEmpty())
		{
			Message m = boiteMessages.remove();
			System.out.println("Swarmide received message - " + m.type.toString());
			if (m.destinataire == "Swarmling")
			{
				sendMessageToSwarmlings(m);
				return;
			}
			
			if (m.destinataire == "Swarmodon" && m.destinataire == "Overmind")
			{
				boss.receiveMessage(m);
				return;
			}
			
			if (m.type == TypeMessage.Attack)
			{
				goal = m.position;
				state = SwarmideState.Attack;
			}
			else if (m.type == TypeMessage.GoTo)
			{
				goal = m.position;
				state = SwarmideState.GoingTo;
			}
			else if (m.type == TypeMessage.EnnemyDetected)
			{
				Message newM = new Message(TypeMessage.Attack, m.position, "Swarmling");
				sendMessageToSwarmlings(newM);
			}
			else if (m.type == TypeMessage.EnergyDetected)
			{
				Message newM = new Message(TypeMessage.Loot, m.position, "Swarmling");
				sendMessageToSwarmlings(newM);
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
	
	protected void sendMessageToSwarmlings(Message m)
	{
		for(Swarmling s : children)
		{
			s.receiveMessage(m);
		}
	}

	public void destroyChild(Swarmling aSwarmling){
		aSwarmling.isAlive = false;
		EnvironmentFrame.getInstance().removeUnit(aSwarmling._frame);
		children.remove(aSwarmling);
		aSwarmling = null;
		System.gc();
	}
	
	@Override
	protected void destroyUnit(){
		for (Swarmling s2 : children)
		{
			s2.destroyUnit();
		}
		boss.destroyChild(this);
	}
}
