import java.util.Vector;

public class Swarmodon extends Unit{
	
	private Overmind boss;
	private Vector<Swarmide> children;
	
	Swarmodon()
	{
		super();
		children = new Vector<Swarmide>();
		unitType = "Swarmodon";
	}
	
	public boolean canProduceSwarmide()
	{
		if (children.size() < 5)
			return true;
		else
			return false;
	}
	
	public void addSwarmide(Swarmide s)
	{
		children.add(s);
	}

	@Override
	protected void checkMessages() {
		
		if (!boiteMessages.isEmpty())
		{
			Message m = boiteMessages.remove();
			System.out.println("Swarmodon received message - " + m.type.toString());
			if (m.destinataire == "Swarmide")
			{
				sendMessageToSwarmides(m);
				return;
			}
			if (m.destinataire == "Overmind")
			{
				boss.receiveMessage(m);
				return;
			}
		}
		
	}

	@Override
	protected void act() {
		//System.out.println("Swarmodon");
		
	}
	
	protected void sendMessageToSwarmides(Message m)
	{
		for(Swarmide s : children)
		{
			s.receiveMessage(m);
		}
	}

	@Override
	protected void receiveMessage(Message m) {
		// TODO Auto-generated method stub
		
	}

}