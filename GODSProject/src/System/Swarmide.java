package System;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;

enum SwarmideState implements State{
	Attack {
		public void act(Unit u)
		{
			ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies != null)
			{
				//((Swarmide)u).changeState(Attack);
				
				if(((Swarmide)u).isCloseEnoughTo(((Swarmide)u).goal)){		
					ennemies.get(0).takesDmg(4);
				}
				
				u.setGoal(ennemies.get(0).getPos());
				u.moveTo(u.getGoal());
			}
			else
			{
				((Swarmide)u).changeState(Roam);
			}
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
				((Swarmide)u).changeState(Attack);
				u.setGoal(ennemies.get(0).getPos());
				Message m = new Message(TypeMessage.EnnemyDetected, u.getGoal(), "Swarmodon");
				((Swarmide)u).sendMessagetoBoss(m);
			}
		}
	},
	GoingTo {
		public void act(Unit u)
		{
			ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies != null)
			{
				((Swarmide)u).changeState(Attack);
				u.setGoal(ennemies.get(0).getPos());
				Message m = new Message(TypeMessage.EnnemyDetected, u.getGoal(), "Swarmodon");
				((Swarmide)u).sendMessagetoBoss(m);
			}
			
			if (u.isCloseEnoughTo(u.getGoal()))
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
	
	Swarmide(Vector2 pos, Swarmodon b)
	{
		super(pos);
		
		life = 700;
		_frame = new UnitFrame(VisualType.SWARMIDE);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		
		children = new Vector<Swarmling>();
		unitType = "Swarmide";
		speed = Constants.swarmideSpeed;
		state = SwarmideState.Roam;
		boss = b;
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
				//setGoal(m.position);
				//changeState(SwarmideState.GoingTo);
			}
			else if (m.type == TypeMessage.EnergyDetected)
			{
				Message newM = new Message(TypeMessage.Loot, m.position, "Swarmling");
				sendMessageToSwarmlings(newM);
			}
			else if (m.type == TypeMessage.Loot)
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
	
	protected void sendMessagetoBoss(Message m)
	{
		boss.receiveMessage(m);
	}

	public void destroyChild(Swarmling aSwarmling){
		aSwarmling.isAlive = false;
		EnvironmentFrame.getInstance().removeUnit(aSwarmling._frame);
		children.remove(aSwarmling);
		aSwarmling = null;
		System.gc();
		Overmind.getInstance().updateStats();
	}
	
	@Override
	public synchronized void defeated(){
		destroyUnit();
	}
	
	@Override
	protected synchronized void destroyUnit(){
		
		for(int j=0;j<children.size();j++){
			if(children.get(j) != null){
				children.get(j).destroyUnit();
			}
		}
		EnvironmentFrame.getInstance().removeSwarm(this);
		boss.destroyChild(this);
		
	}
}
