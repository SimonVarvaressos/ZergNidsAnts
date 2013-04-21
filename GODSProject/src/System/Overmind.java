package System;
import java.util.Vector;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;


public class Overmind extends Unit{
	
	protected float energy;
	protected Vector<Swarmodon> children;
	private static Overmind _instance;
	
	Overmind(Vector2 pos)
	{
		super(pos);
		_frame = new UnitFrame(VisualType.OVERMIND);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		children = new Vector<Swarmodon>();
		unitType = "Overmind";
		goal = new Vector2();
	}
	
	public static Overmind getInstance(){
		if (Overmind._instance == null){
			Overmind._instance = new Overmind(new Vector2(550,180));
		}
		return Overmind._instance;
	}
	
	public boolean canProduceSwarmodons()
	{
		if (children.size() < Constants.swarmodonsMax)
			return true;
		else
			return false;
	}
	
	
	public void produceSwarmodon()
	{
		if (canProduceSwarmodons())
		{
			Swarmodon s = new Swarmodon(position);
			
			children.add(s);
		}
	}
	
	public void produceSwarmide()
	{
		for (Swarmodon s : children)
		{
			if (s.canProduceSwarmide())
			{
				Swarmide ide = new Swarmide(position);
				s.addSwarmide(ide);
				return;
			}
		}
	}
	
	public void produceSwarmling()
	{
		for (Swarmodon s : children)
		{
			Vector<Swarmide> theSwarmides = s.getSwarmides();
			for (Swarmide s2 : theSwarmides)
			{
				if (s2.canProduceSwarmlings())
				{
					Swarmling ing = new Swarmling(position);
					s2.addSwarmling(ing);
					return;
				}
			}
		}
	}
	
	
	protected void checkMessages()
	{
		
	}

	@Override
	protected void act() {
		
	}

	protected void sendMessageToSwarmodons(Message m)
	{
		for(Swarmodon s : children)
		{
			s.receiveMessage(m);
		}
	}

	@Override
	public void receiveMessage(Message m) {
		// TODO Auto-generated method stub
		
	}

}
