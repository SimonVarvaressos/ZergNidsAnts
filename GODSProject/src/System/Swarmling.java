package System;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;

enum SwarmlingState implements State
{
	Attack {
		public synchronized void act(Unit u)
		{
			ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies != null)
			{
				Unit enemy = ennemies.get(0);
				((Swarmling)u).changeState(Attack);
				u.setGoal(enemy.getPos());
				u.moveTo(u.getGoal());
				
				if(((Swarmling)u).isCloseEnoughTo(u.getGoal())){		
					enemy.takesDmg(1);
				}
				
				((Swarmling)u).changeState(Roam);
			}
			else
			{
				((Swarmling)u).changeState(Roam);
			}
		}
	},
	Roam {
		public void act(Unit u)
		{
			Vector2 newGoal = new Vector2();
			Random rand = new Random();
			newGoal.setX(u.getGoal().getX() + (rand.nextInt(600) - 300));
			newGoal.setY(u.getGoal().getY() + (rand.nextInt(600) - 300));
			u.setGoal(newGoal);
			u.moveTo(u.getGoal()); 
			
			ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies != null)
			{
				((Swarmling)u).changeState(Attack);
				u.setGoal(ennemies.get(0).getPos());
				Message m = new Message(TypeMessage.EnnemyDetected, u.getGoal(), "Swarmide");
				((Swarmling)u).sendMessagetoBoss(m);
			}
			else{
				ArrayList<EnergyBall> energy = u.watchSurroundingsForFood();
				if (energy != null)
				{
					((Swarmling)u).changeState(Gather);
					((Swarmling)u).setFoodPos(energy.get(0).getPos());
					
					u.setGoal(((Swarmling)u).getFoodPos());
					Message m = new Message(TypeMessage.Loot, u.getGoal(), "Swarmide");
					((Swarmling)u).sendMessagetoBoss(m);
				}
				
				
				
			}
		}
	},
	GoingTo {
		public void act(Unit u)
		{
			ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies != null)
			{
				((Swarmling)u).changeState(Attack);
				u.setGoal(ennemies.get(0).getPos());
				Message m = new Message(TypeMessage.EnnemyDetected, u.getGoal(), "Swarmide");
				((Swarmling)u).sendMessagetoBoss(m);
			}
			
			if (u.getPos().equals(u.getGoal()))
				((Swarmling)u).changeState(Roam);
			else
				u.moveTo(u.getGoal());
			
			//System.out.println(u.getGoal().getX() + " and " + u.getGoal().getY());
		}
	},
	Gather {
		public void act(Unit u)
		{
			ArrayList<Unit> ennemies = u.watchSurroundings();
			if (ennemies == null)													//NO_ENEMIES
			{
				if(((Swarmling)u).isCarryingFood()){								//WHEN CARRYING FOOD
					Vector2 overmindPos= new Vector2(555, 190);
					if(((Swarmling)u).isCloseEnoughTo(overmindPos)){
						((Swarmling)u).depositsFoodatOverming();
					}
					u.setGoal(overmindPos);
					u.moveTo(u.getGoal());
				}
				else{
					Vector2 foodPos = ((Swarmling)u).getFoodPos();
					if(EnvironmentFrame.getInstance().validFoodSource(foodPos)){	//FOOD SOURCE STILL ACTIVE
						if(((Swarmling)u).isCloseEnoughTo(foodPos)){		
							EnvironmentFrame.getInstance().takeFoodFromSource(foodPos);
							((Swarmling)u).startsCarryingFood();
						}
						u.setGoal(foodPos);
						u.moveTo(u.getGoal());
					}
					else{															//FOOD SOURCE DEPLETED		
						//nullify last food source coord? 
						((Swarmling)u).changeState(Roam);
					}
				}
			}
			else{																	//ENEMIES FOUND
				if(((Swarmling)u).isCarryingFood()){
					((Swarmling)u).stopsCarryingFood();
				}
				((Swarmling)u).changeState(Attack);
				u.setGoal(ennemies.get(0).getPos());
				Message m = new Message(TypeMessage.EnnemyDetected, u.getGoal(), "Swarmide");
				((Swarmling)u).sendMessagetoBoss(m);
			}
		}
	}
}

public class Swarmling extends Unit{
	
	private Swarmide boss;
	private SwarmlingState state;
	private boolean _isCarryingFood;
	private Point _lastFoodSight;
	
	
	Swarmling(Vector2 pos, Swarmide b)
	{
		super(pos);
		_isCarryingFood = false;
		_lastFoodSight = new Point(0,0);
		_frame = new UnitFrame(VisualType.SWARMLING);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		unitType = "Swarmling";
		speed = Constants.swarmlingSpeed;
		state = SwarmlingState.Roam;
		boss = b;
	}
	
	public void startsCarryingFood(){
		_isCarryingFood = true;
		_frame.updateVisualType(VisualType.SWARMLING_CARRIER);
	}
	
	public void stopsCarryingFood(){
		_isCarryingFood = false;
		_frame.updateVisualType(VisualType.SWARMLING);
		//To be used when food is "dropped/disposed of" in order to prioritize attacking an enemy (for instance)
	}

	public void depositsFoodatOverming(){
		if(_isCarryingFood){
			_isCarryingFood = false;
			Overmind.getInstance().resplenishEnergy();
			_frame.updateVisualType(VisualType.SWARMLING);
		}
	}
	
	public Vector2 getFoodPos(){
		return new Vector2(_lastFoodSight.x, _lastFoodSight.y);
	}
	
	public void setFoodPos(Vector2 aPos){
		_lastFoodSight.x = (int)aPos.getX(); 
		_lastFoodSight.y = (int)aPos.getY();
	}
	
	public boolean isCarryingFood(){
		return _isCarryingFood;
	}
	
	public void changeState(SwarmlingState newState)
	{
		state = newState;
	}

	@Override
	protected void checkMessages() {
		if (!boiteMessages.isEmpty())
		{
			Message m = boiteMessages.remove();
			if (m.type == TypeMessage.Attack)
			{
				goal = m.position;
				state = SwarmlingState.GoingTo;
			}
			else if (m.type == TypeMessage.GoTo)
			{
				goal = m.position;
				state = SwarmlingState.GoingTo;
			}
			else if (m.type == TypeMessage.Loot)
			{
				if(state != SwarmlingState.Attack){
					setFoodPos(m.position);
					goal = m.position;
					state = SwarmlingState.Gather;
				}	
			}
		}
		
	}

	@Override
	protected void act() {
		if (state != null)
		{
			state.act(this);
			//System.out.println("Yolo");
		}
		
	}
	
	protected void sendMessagetoBoss(Message m)
	{
		boss.receiveMessage(m);
	}
	
	@Override
	public synchronized void defeated(){
		destroyUnit();
	}
	
	@Override
	protected synchronized void destroyUnit(){
		EnvironmentFrame.getInstance().removeSwarm(this);
		boss.destroyChild(this);
		
	}
}
