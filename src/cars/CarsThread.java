package cars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CarsThread implements Runnable {
	Thread thread;
	Cars car;
	CarsThread(Cars car) {
		// Create a new, second thread
		this.car = car;
	  thread = new Thread(this, "Car Event invoker");
	  thread.start();
	}
	   
	// This is the entry point for the second thread.
	public void run() {

		try {
			while(thread.isAlive())
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String key = br.readLine();
				switch(key.toLowerCase())
				{
				case "j":
					if(!this.car.runnable())
					this.car.start();
					else
						System.out.println("Car already running, use 'b' to stop it");
				break;
				case "p":
					this.car.openDoor();
				break;
				case "k":
					this.car.honk();
				break;
				case "b":
					if(this.car.t.isAlive())
					this.car.stop();
					else
						System.out.println("Start the engine first by enter 'j' ");
				break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
