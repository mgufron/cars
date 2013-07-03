package cars;
import cars.CarsThread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract public class Cars implements Runnable {
	Thread t;
	Cars() {
		// Create a new, second thread
	  t = new Thread(this, "Running car");
	}
	   
	// This is the entry point for the second thread.
	public void run() {
		while(t.isAlive())
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

	public void start() throws IOException
	{
		System.out.println(this.getType()+": Start the engine");
		CarsThread carThread = new CarsThread();
		if(!t.isAlive())
			t.start();// Start the thread
	}
	
	
	public String getType()
	{
		return "Car";
	}
}
