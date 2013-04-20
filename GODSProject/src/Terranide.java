import java.util.Random;


enum TerranideState implements State{
	Attack {
		public void act(Unit u)
		{
			//TO DO
		}
	},
	Roam {
		public void act(Unit u)
		{
			//TO DO : Si ennemi proche, on change pour Attack
			Vector2 newGoal = new Vector2();
			Random rand = new Random();
			newGoal.setX(u.getGoal().getX() + (rand.nextInt(4) - 2));
			newGoal.setY(u.getGoal().getY() + (rand.nextInt(4) - 2));
			u.setGoal(newGoal);
			u.moveTo(newGoal);
		}
	},
	GoingTo {
		public void act(Unit u)
		{
			if (u.getPos() == u.getGoal())
				((Terranide)u).changeState(Roam);
			else
				u.moveTo(u.getGoal());
		}
	}
}

public class Terranide extends Unit{
	
	private TerranideState state;
	
	Terranide(Vector2 pos)
	{
		super(pos);
		speed = Constants.terranideSpeed;
		goal = new Vector2();
		state = TerranideState.Roam;
	}

	public void changeState(TerranideState newState) {
		state = newState;
		
	}

	@Override
	protected void checkMessages() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void act() {
		if (state != null)
		{
			state.act(this);
			//System.out.println("Yolo");
		}
	}

}
