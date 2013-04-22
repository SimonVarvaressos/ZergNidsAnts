package System;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;

enum SwarmodonState implements State {
	Attack {
		public void act(Unit u)
		{
			/*ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies != null)
			{
				//((Swarmodon)u).changeState(Attack);
				
				if(((Swarmodon)u).isCloseEnoughTo(((Swarmodon)u).goal)){		
					ennemies.get(0).takesDmg(1);
				}
				
				u.setGoal(ennemies.get(0).getPos());
				u.moveTo(u.getGoal());
			}
			else
			{
				((Swarmodon)u).changeState(Roam);
			}*/
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
			
			ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies != null)
			{
				((Swarmodon)u).changeState(Attack);
				u.setGoal(ennemies.get(0).getPos());
				Message m = new Message(TypeMessage.GoTo, u.getGoal(), "Swarmide");
				((Swarmodon)u).sendMessageToSwarmides(m);
			}
		}
	},
	GoingTo {
		public void act(Unit u)
		{
			ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies != null)
			{
				((Swarmodon)u).changeState(Attack);
				u.setGoal(ennemies.get(0).getPos());
				Message m = new Message(TypeMessage.GoTo, u.getGoal(), "Swarmide");
				((Swarmodon)u).sendMessageToSwarmides(m);
			}
			
			if (u.getPos().equals(u.getGoal()))
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
	
	Swarmodon(Vector2 pos, Overmind b)
	{
		super(pos);
		
		_frame = new UnitFrame(VisualType.SWARMODON);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		
		children = new Vector<Swarmide>();
		unitType = "Swarmodon";
		speed = Constants.swarmodonSpeed;
		state = SwarmodonState.Roam;
		boss = b;
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
			//System.out.println("Swarmodon received message - " + m.type.toString());
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
			else if (m.type == TypeMessage.EnnemyDetected)
			{
				Message newM = new Message(TypeMessage.Attack, m.position, "Swarmide");
				sendMessageToSwarmides(newM);
				//System.out.println("SWARMODON ENNEMYDETECTED!");
				//setGoal(m.position);
				//changeState(SwarmideState.GoingTo);
			}
		}
		
	}

	@Override
	protected void act() {
		if (state != null)
		{
			watchSurroundings();
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
	
	
	
	public void destroyChild(Swarmide aSwarmide){
		aSwarmide.isAlive = false;
		EnvironmentFrame.getInstance().removeUnit(aSwarmide._frame);
		children.remove(aSwarmide);
		aSwarmide = null;
		System.gc();
	}
	
	@Override
	public synchronized void defeated(){
		destroyUnit();
	}
	
	@Override
	protected synchronized void destroyUnit(){
		for (Swarmide s2 : children)
		{
			s2.destroyUnit();
		}
		EnvironmentFrame.getInstance().removeSwarm(this);
		boss.destroyChild(this);
	}
}
