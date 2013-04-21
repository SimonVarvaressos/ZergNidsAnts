package System;
import java.util.ArrayList;
import java.util.Vector;

import Frame.EnvironmentFrame;
import Frame.OvermindFrame;
import Frame.StatisticFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;


public class Overmind extends Unit{
	
	protected int energy;
	protected Vector<Swarmodon> children;
	private static Overmind _instance;
	
	Overmind(Vector2 pos)
	{
		super(pos);
		energy = 7005;
		life = 3000;
		_frame = new UnitFrame(VisualType.OVERMIND);
		EnvironmentFrame.getInstance().addUnit(_frame, (int)pos.getX(), (int)pos.getY());
		children = new Vector<Swarmodon>();
		unitType = "Overmind";
		goal = new Vector2();
	}
	
	public static Overmind getInstance(){
		if (Overmind._instance == null){
			Overmind._instance = new Overmind(new Vector2(550,180));
		}
		return Overmind._instance;
	}
	
	public boolean canProduceSwarmodons()
	{
		if (children.size() < Constants.swarmodonsMax)
			return true;
		else
			return false;
	}
	
	private void updateStats(){
		int i = 0;
		int j = 0;
		for (Swarmodon s : children)
		{
			Vector<Swarmide> theSwarmides = s.getSwarmides();
			for (Swarmide s2 : theSwarmides)
			{
				j = j + s2.getSwarmlings().size();
			}
			i = i + theSwarmides.size();
		}
		children.size(); //swarmodon
		
		StatisticFrame.getInstance().updateSwarm(j, i, children.size());
	}
	
	public void produceSwarmodon()
	{
		if(energy - Constants.swarmodonCost < 0){
			OvermindFrame.getInstance().updateMsg(Constants.NO_ENERGY);
		}
		else{
			if (canProduceSwarmodons())
			{
				Swarmodon s = new Swarmodon(position);
				
				children.add(s);
				
				energy = energy - Constants.swarmodonCost;
				OvermindFrame.getInstance().updateEnergy(energy);
				setUpkeep();
			}
			else{
				OvermindFrame.getInstance().updateMsg(Constants.NO_ODON);
			}
			updateStats();
		}
	}
	
	public void produceSwarmide()
	{
		if(energy - Constants.swarmideCost < 0){
			OvermindFrame.getInstance().updateMsg(Constants.NO_ENERGY);
		}
		else{
			for (Swarmodon s : children)
			{
				if (s.canProduceSwarmide())
				{
					Swarmide ide = new Swarmide(position);
					s.addSwarmide(ide);
					updateStats();
					
					energy = energy - Constants.swarmideCost;
					OvermindFrame.getInstance().updateEnergy(energy);
					setUpkeep();
					return;
				}
			}
			OvermindFrame.getInstance().updateMsg(Constants.NO_IDE);
		}
	}
	
	public void produceSwarmling()
	{
		int i = 0;
		if(energy - Constants.swarmlingCost < 0){
			OvermindFrame.getInstance().updateMsg(Constants.NO_ENERGY);
		}
		else{
			for (Swarmodon s : children)
			{
				Vector<Swarmide> theSwarmides = s.getSwarmides();
				for (Swarmide s2 : theSwarmides)
				{
					i++;
					if (s2.canProduceSwarmlings())
					{
						Swarmling ing = new Swarmling(position);
						s2.addSwarmling(ing);
						updateStats();
						
						energy = energy - Constants.swarmlingCost;
						OvermindFrame.getInstance().updateEnergy(energy);
						setUpkeep();
						return;
					}
				}
			}
			OvermindFrame.getInstance().updateMsg(Constants.NO_ING);
		}
	}
	
	private int swarmicideEnergy(){
		int i = 0;
		int j = 0;
		for (Swarmodon s : children)
		{
			Vector<Swarmide> theSwarmides = s.getSwarmides();
			for (Swarmide s2 : theSwarmides)
			{
				j = j + s2.getSwarmlings().size();
			}
			i = i + theSwarmides.size();
		}
		return energy + children.size() * 2250 + i * 500 + j * 150;
	}
	
	public void swarmicide() {
		
		energy = swarmicideEnergy();
		
		for (Swarmodon s : children)
		{
			Vector<Swarmide> theSwarmides = s.getSwarmides();
			for (Swarmide s2 : theSwarmides)
			{
				Vector<Swarmling> theSwarmlings = s2.getSwarmlings();
				for (Swarmling s3 : theSwarmlings)
				{
					s3.isAlive = false;
					EnvironmentFrame.getInstance().removeUnit(s3._frame);
					s3 = null;
				}
				s2.isAlive = false;
				EnvironmentFrame.getInstance().removeUnit(s2._frame);
				s2 = null;
			}
			s.isAlive = false;
			EnvironmentFrame.getInstance().removeUnit(s._frame);
			s = null;
		}
		children.clear();
		System.gc();
		
		
		
		StatisticFrame.getInstance().updateSwarm(0, 0, 0);
		OvermindFrame.getInstance().updateEnergy(energy);
		OvermindFrame.getInstance().updateMsg("Swarmicide committed");
	}
	
	public void reset() {
		
		energy = 10000;
		
		for (Swarmodon s : children)
		{
			Vector<Swarmide> theSwarmides = s.getSwarmides();
			for (Swarmide s2 : theSwarmides)
			{
				Vector<Swarmling> theSwarmlings = s2.getSwarmlings();
				for (Swarmling s3 : theSwarmlings)
				{
					s3.isAlive = false;
					EnvironmentFrame.getInstance().removeUnit(s3._frame);
					s3 = null;
				}
				s2.isAlive = false;
				EnvironmentFrame.getInstance().removeUnit(s2._frame);
				s2 = null;
			}
			s.isAlive = false;
			EnvironmentFrame.getInstance().removeUnit(s._frame);
			s = null;
		}
		children.clear();
		System.gc();
		
		
		
		StatisticFrame.getInstance().updateAllData(0, 0, 0, 0, 5.0f);
		OvermindFrame.getInstance().updateHP(3000);
		OvermindFrame.getInstance().updateEnergy(energy);
		OvermindFrame.getInstance().updateMsg(" No message at the moment ");
	}
	
	public synchronized void payUpkeep(){
		int i = 0;
		int j = 0;
		for (Swarmodon s : children)
		{
			Vector<Swarmide> theSwarmides = s.getSwarmides();
			for (Swarmide s2 : theSwarmides)
			{
				j = j + s2.getSwarmlings().size();
			}
			i = i + theSwarmides.size();
		}
		children.size(); //swarmodon
		
		energy = energy - (children.size() * 10 + i * 5 + j * 2);
		if(energy < 0){
			swarmicide();
			StatisticFrame.getInstance().pauseSystem();
		}
		else{
			OvermindFrame.getInstance().updateEnergy(energy);
		}
		
	}
	
	public synchronized void setUpkeep(){
		int i = 0;
		int j = 0;
		for (Swarmodon s : children)
		{
			Vector<Swarmide> theSwarmides = s.getSwarmides();
			for (Swarmide s2 : theSwarmides)
			{
				j = j + s2.getSwarmlings().size();
			}
			i = i + theSwarmides.size();
		}
		children.size(); //swarmodon
		
		StatisticFrame.getInstance().updateUpkeep((children.size() * 10 + i * 5 + j * 2));
	}
	
	protected void checkMessages()
	{
		
	}

	@Override
	protected void act() {
		
	}

	protected void sendMessageToSwarmodons(Message m)
	{
		for(Swarmodon s : children)
		{
			s.receiveMessage(m);
		}
	}

	@Override
	public void receiveMessage(Message m) {
		// TODO Auto-generated method stub
		
	}

}
