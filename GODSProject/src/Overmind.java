import java.util.Vector;


public class Overmind extends Unit{
	
	protected float energy;
	protected Vector<Swarmodon> children;
	
	Overmind(Vector2 pos)
	{
		super(pos);
		children = new Vector<Swarmodon>();
		unitType = "Overmind";
	}
	
	public boolean canProduceSwarmodons()
	{
		if (children.size() < 5)
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
			if (s.canProduceSwarmide())
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
	}
	
	
	protected void checkMessages()
	{
		
	}

	@Override
	protected void act() {
		// TODO Auto-generated method stub
		
	}

	protected void sendMessageToSwarmodons(Message m)
	{
		for(Swarmodon s : children)
		{
			s.receiveMessage(m);
		}
	}

	@Override
	protected void receiveMessage(Message m) {
		// TODO Auto-generated method stub
		
	}

}
