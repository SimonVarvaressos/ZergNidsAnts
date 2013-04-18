import java.util.Vector;


public class Overmind extends Unit{
	
	protected float energy;
	protected Vector<Swarmodon> children;
	
	Overmind()
	{
		super();
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
