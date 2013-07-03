package cars;

import cars.CarsThread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cars implements Runnable {
	Thread t;
	   
	// This is the entry point for the second thread.
	public void run() {
		while(this.runable())
		{
			try {
				Date date = new Date();
				SimpleDateFormat formattedDate = new SimpleDateFormat("HH:mm");
				System.out.println("("+formattedDate.format(date).toString()+") "+this.getType()+": running");
				Thread.sleep(1000*60);
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
		System.out.println("Allright, this is the manual. Remember this thing, enter");
		System.out.println("\tj \t\t to run the car");
		System.out.println("\tk \t\t to make a honk");
		System.out.println("\tp \t\t to open the door");
		System.out.println("\tb \t\t to stop the car");
		System.out.println("Have fun. :D");
		new CarsThread(this);
	}
	
	public void startEngine()
	{
		t = new Thread(this, "Running car");
		t.start();// Start the thread
	}
	
	public void stop()
	{
		System.out.println(this.getType()+" : stopping");
		
		if(t != null)
		{
			t=null;
		}
	}
	
	public boolean runable()
	{
		return this.t instanceof Thread;
	}
	
	
	public String getType()
	{
		return "Car";
	}
	
}
