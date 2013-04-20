import java.util.Vector;

enum SwarmideState implements State{
	Attack {
		public void act(Unit u)
		{
			
		}
	},
	Roam {
		public void act(Unit u)
		{
			
		}
	}
}

public class Swarmide extends Unit{
	
	private Swarmodon boss;
	private Vector<Swarmling> children;
	
	Swarmide(Vector2 pos)
	{
		super(pos);
		children = new Vector<Swarmling>();
		unitType = "Swarmide";
		speed = 1.4f;
	}
	
	public boolean canProduceSwarmlings()
	{
		if (children.size() < 5)
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
			if (m.destinataire == "Swarmodon")
			{
				boss.receiveMessage(m);
				return;
			}
		}
		
	}

	@Override
	protected void act() {
		//System.out.println("Swarmide");
		
	}
	
	protected void sendMessageToSwarmlings(Message m)
	{
		for(Swarmling s : children)
		{
			s.receiveMessage(m);
		}
	}

	@Override
	protected void receiveMessage(Message m) {
		// TODO Auto-generated method stub
		
	}

}
