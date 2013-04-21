package System;
import java.util.Random;
import java.util.Vector;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;

enum SwarmodonState implements State {
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
				((Swarmodon)u).changeState(Roam);
			else
				u.moveTo(u.getGoal());
		}
	}
}

public class Swarmodon extends Unit{
	
	private Overmind boss;
	private Vector<Swarmide> children;
	private SwarmodonState state;
	
	Swarmodon(Vector2 pos)
	{
		super(pos);
		
		_frame = new UnitFrame(VisualType.SWARMODON);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		
		children = new Vector<Swarmide>();
		unitType = "Swarmodon";
		speed = Constants.swarmodonSpeed;
		goal = new Vector2();
		state = SwarmodonState.Roam;
	}
	
	public void changeState(SwarmodonState newState) {
		state = newState;
	}

	public boolean canProduceSwarmide()
	{
		if (children.size() < Constants.swarmidesMax)
			return true;
		else
			return false;
	}
	
	public void addSwarmide(Swarmide s)
	{
		children.add(s);
	}
	
	public Vector<Swarmide> getSwarmides()
	{
		return children;
	}

	@Override
	protected void checkMessages() {
		
		if (!boiteMessages.isEmpty())
		{
			Message m = boiteMessages.remove();
			System.out.println("Swarmodon received message - " + m.type.toString());
			if (m.destinataire == "Swarmide" && m.destinataire == "Swarmling")
			{
				sendMessageToSwarmides(m);
				return;
			}
			if (m.destinataire == "Overmind")
			{
				boss.receiveMessage(m);
				return;
			}
			///// Traiter les messages
			if (m.type == TypeMessage.Attack)
			{
				goal = m.position;
				state = SwarmodonState.Attack;
			}
			else if (m.type == TypeMessage.GoTo)
			{
				goal = m.position;
				state = SwarmodonState.GoingTo;
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
	
	protected void sendMessageToSwarmides(Message m)
	{
		for(Swarmide s : children)
		{
			s.receiveMessage(m);
		}
	}

}
