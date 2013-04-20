
enum TerranodonState implements State{
	Attack {
		public void act(Unit u)
		{
			
		}
	},
	Roam {
		public void act(Unit u)
		{
			
		}
	},
	GoingTo {
		public void act(Unit u)
		{
			
		}
	}
}

public class Terranodon extends Unit {
	
	private TerranodonState state;
	
	Terranodon(Vector2 pos)
	{
		super(pos);
		speed = 1.0f;
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
