package System;
import java.util.ArrayList;
import java.util.Random;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;


enum TerranlingState implements State{
	Attack {
		public void act(Unit u)
		{
			ArrayList<Unit> ennemies = u.watchSurroundingsT();
			if (ennemies != null)
			{
				//((Terranling)u).changeState(Attack);
				
				if(((Terranling)u).isCloseEnoughTo(((Terranling)u).goal)){		
					ennemies.get(0).takesDmg(3);
				}
				
				u.setGoal(ennemies.get(0).getPos());
				u.moveTo(u.getGoal());
			}
			else
			{
				((Terranling)u).changeState(Roam);
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
			
			ArrayList<Unit> ennemies = u.watchSurroundingsT();
			if (ennemies != null)
			{
				((Terranling)u).changeState(Attack);
				u.setGoal(ennemies.get(0).getPos());
			}
		}
	},
	GoingTo {
		public void act(Unit u)
		{
			if (u.isCloseEnoughTo(u.getGoal()))
				((Terranling)u).changeState(Roam);
			else
				u.moveTo(u.getGoal());
		}
	}
}

public class Terranling extends Unit {
	
	private TerranlingState state;
	
	public Terranling(Vector2 pos)
	{
		super(pos);
		
		_frame = new UnitFrame(VisualType.TERANLING);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		
		life = 100;
		speed = Constants.terranlingSpeed;
		state = TerranlingState.Roam;
	}

	public void changeState(TerranlingState newState) {
		state = newState;
	}
	
	@Override
	public synchronized void defeated(){
		EnvironmentFrame.getInstance().addEnergyFromDefeatedTeran(this);
		EnvironmentFrame.getInstance().destroyTeranUnit(this);
	}
	
	@Override
	protected synchronized void destroyUnit(){
		EnvironmentFrame.getInstance().destroyTeranUnit(this);
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
