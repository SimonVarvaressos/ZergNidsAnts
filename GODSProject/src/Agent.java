
public class Agent extends Thread{

	protected boolean isAlive;
	
	Agent()
	{
		isAlive = true;
		System.out.print("Started");
		start();
	}
	
	public void run()
	{
		while (isAlive)
		{
			
		}
	}
}
