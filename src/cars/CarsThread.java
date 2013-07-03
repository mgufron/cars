package cars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class CarsThread implements Runnable {
	Thread t;
	Cars car;
	CarsThread(Cars car) {
		// Create a new, second thread
		this.car = car;
	  t = new Thread(this.car, "Car Event invoker");
	  t.start();
	}
	   
	// This is the entry point for the second thread.
	public void run() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String key = br.readLine();
			switch(key)
			{
			case "j":
			break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.start(); // Start the thread
		System.out.println(car.getType()+": running");
	}

}
