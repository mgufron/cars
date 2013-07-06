package threads;

import base.Cars;
import base.CarsThreadBase;

public class CarsAction extends CarsThreadBase
{
	Cars car;
	Thread t;
	private int timeout=1000*60;

//	i actually still don't understand why it need volatile, because it sometimes cause problems with 
//	threads and cpu's performance. Volatile will affect on threads only.
//	On methods who read volatile variable, it will get the latest value of volatile variable
//	when cpu is busy enough or system is hanging
	private volatile boolean active=false;
	CarsAction(Cars car)
	{
		this.car = car;
		this.t= new Thread(this, "Car Actions");
		this.t.start();
	}
	   
	// This is the entry point for the second thread.
	public void run() {
		while(t.isAlive())
		{
			// the while loop see the change of active variable is still true or false when the machine is busy enough
			// false means it will not do the loop
			// and true will do the loop
			boolean currentStatus = active;
			if(currentStatus)
			{
				try {
					this.car.console("running");
					Thread.sleep(timeout);
				} catch (InterruptedException e) {
					this.car.console("interrupted");
				}
			}
		}
	}

	public void stop()
	{
		active = false;
		this.car.console("stopping. Enter 'j' again to make the car running");
	}
	
	public boolean runable()
	{
		return active;
	}
	public void setRunable()
	{
		active = true;
	}
	public void startEngine()
	{
		this.setRunable();
	}
}