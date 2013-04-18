

public class Swarmling extends Unit{
	
	private Swarmide boss;
	
	Swarmling()
	{
		super();
		unitType = "Swarmling";
	}

	@Override
	protected void checkMessages() {
		if (!boiteMessages.isEmpty())
		{
			Message m = boiteMessages.remove();
			System.out.println("Swarmling received message - " + m.type.toString());
		}
		
	}

	@Override
	protected void act() {
		//System.out.println("Swarmling");
		
	}

	@Override
	protected void sendMessage(Message m) {
		boss.receiveMessage(m);
	}

	@Override
	protected void receiveMessage(Message m) {
		boiteMessages.add(m);
		
	}

}
