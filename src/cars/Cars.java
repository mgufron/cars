package cars;
import cars.CarsThread;

import java.text.SimpleDateFormat;
import java.util.Date;

abstract public class Cars implements Runnable {
	volatile Thread t;
	Cars() {
		// Create a new, second thread
	}
	   
	// This is the entry point for the second thread.
	public void run() {
		while(t instanceof Thread && t.isAlive())
		{
			try {
				Date date = new Date();
				SimpleDateFormat formattedDate = new SimpleDateFormat("HH:mm");
				System.out.println("("+formattedDate.format(date).toString()+") "+this.getType()+": running");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(this.getType()+": interupted");
			}
		}
	}
	
	public int getWheels()
	{
		return 0;
	}
	
	public void openDoor(){
		System.out.println(this.getType()+": Opening door");
	}
	
	public void honk()
	{
		System.out.println(this.getType()+": Honking");
	}

	public void start()
	{
		System.out.println(this.getType()+": Start the engine");
		  t = new Thread(this, "Running car");
		t.start();// Start the thread
		new CarsThread(this) {
			
		};
	}
	
	public void stop()
	{
		System.out.println(this.getType()+" : stopping");
		
		if(t != null)
		{
			t=null;
		}
	}
	
	public boolean runnable()
	{
		return this.t instanceof Thread;
	}
	
	
	public String getType()
	{
		return "Car";
	}
}
