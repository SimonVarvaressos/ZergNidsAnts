package System;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public abstract class Agent extends Thread{

	protected boolean isAlive;
	protected BlockingQueue<Message> boiteMessages;
	
	Agent()
	{
		boiteMessages = new ArrayBlockingQueue<Message>(100);
		isAlive = true;
		System.out.print("Started");
		start();
	}
	
	public void run()
	{
		try {
			while (isAlive)
			{
				checkMessages();
				act();
				Thread.sleep(10);
			}
			
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	}
	
	
	protected abstract void checkMessages();
	protected abstract void act();
	
	public void receiveMessage(Message m)
	{
		boiteMessages.add(m);
	}
	
	
}
