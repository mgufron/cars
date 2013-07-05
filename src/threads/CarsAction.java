package threads;

import cars.Cars;

public class CarsAction implements Runnable
{
	Cars car;
	Thread t;
	CarsAction(Cars car)
	{
		this.car = car;
	}
	   
	// This is the entry point for the second thread.
	public void run() {

		while(this.runable())
		{
			try {
				this.car.console("running");
				Thread.sleep(1000*60);
			} catch (InterruptedException e) {
				this.car.console("interrupted");
			}
		}
	}

	
	public void stop()
	{
		this.car.console("stopping. Enter 'j' again to make the car running");
		
		if(t != null)
		{
			t=null;
		}
	}
	
	public boolean runable()
	{
		return this.t instanceof Thread;
	}
	
	public void startEngine()
	{
		this.t= new Thread(this, "Car Actions");
		this.t.start();
	}
}