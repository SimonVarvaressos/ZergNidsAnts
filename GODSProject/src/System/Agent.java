package System;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import Frame.StatisticFrame;


public abstract class Agent extends Thread{

	protected boolean isAlive;
	protected BlockingQueue<Message> boiteMessages;
	
	Agent()
	{
		boiteMessages = new ArrayBlockingQueue<Message>(100);
		isAlive = true;
		System.out.println("Started");
		start();
	}
	
	public void run()
	{
		try {
			while (isAlive)
			{
				if(StatisticFrame.getInstance().isActive())
				{
					checkMessages();
					act();
					Thread.sleep(10);
				}
			}
			
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	}
	
	public void turnOff()
	{
		isAlive = false;
	}
	
	protected abstract void checkMessages();
	protected abstract void act();
	
	public synchronized void receiveMessage(Message m)
	{
		if (boiteMessages.remainingCapacity() != 0)
			boiteMessages.add(m);
	}
	
	
}
