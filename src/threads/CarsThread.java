package threads;
import threads.CarsAction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import base.Cars;
import base.MobileBase;
import base.CarsThreadBase;
public class CarsThread extends CarsThreadBase {
	Thread thread;
	Cars car;
	CarsAction carActions;
	private boolean active=false;
	public CarsThread(Cars car) {
		System.out.println("Allright, this is the manual. Remember this thing, enter\n");
		System.out.println("\tj \t\t to run the car");
		System.out.println("\tk \t\t to make a honk");
		System.out.println("\tp \t\t to open the door");
		System.out.println("\tb \t\t to stop the car");
		System.out.println("\tc \t\t to change your current car.");
		System.out.println("\nHave fun. :D");
		// Create a new, second thread
		this.car = car;
		thread = new Thread(this, "Car Event invoker");
		this.carActions = new CarsAction(car);
		thread.start();
		active=true;
	}
	   
	// This is the entry point for the second thread.
	public void run() {
		try {
			while(active)
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String key = br.readLine();
				switch(key.toLowerCase())
				{
				case "j":
					if(!this.carActions.runable())
					this.carActions.startEngine();
					else
						this.car.console("already running, use 'b' to stop it");
				break;
				case "p":
					this.car.openDoor();
				break;
				case "k":
					this.car.honk();
				break;
				case "b":
					if(this.carActions.runable())
						this.carActions.stop();
					else
						this.car.console("Start the engine first by enter 'j' or enter 'c' to go back to choose your desired car");
				break;
				case "c":
					if(this.carActions.runable())
						this.car.console("Please stop the car first before you want to choose other car");
					else
					{
						this.car = null;
						this.thread= null;
						MobileBase.restart();
					}
				break;
				default:
					this.car.console("Sorry, we don't know that command. Try 'j','k','p','c' or 'b'");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
