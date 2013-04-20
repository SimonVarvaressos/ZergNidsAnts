
enum TerranlingState implements State{
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

public class Terranling extends Unit {
	
	private TerranlingState state;
	
	Terranling(Vector2 pos)
	{
		super(pos);
		speed = 2.0f;
	}

	@Override
	protected void checkMessages() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void act() {
		state.act(this);
		
	}


	@Override
	protected void receiveMessage(Message m) {
		// TODO Auto-generated method stub
		
	}

}
