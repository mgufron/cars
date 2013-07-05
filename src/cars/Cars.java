package cars;

import java.text.SimpleDateFormat;
import java.util.Date;

import threads.CarsThread;

public class Cars {
	public int getWheels()
	{
		return 0;
	}
	
	public void openDoor(){
		this.console("Opening Door");
	}
	
	public void honk()
	{
		this.console("Honking");
	}
	
	public void console(String message)
	{
		Date date = new Date();
		SimpleDateFormat formattedDate = new SimpleDateFormat("HH:mm");
		System.out.println("("+formattedDate.format(date).toString()+") "+this.getType()+": "+message);
	}

	public void start()
	{
		new CarsThread(this);
	}
	
	
	public String getType()
	{
		return "Car";
	}
	
}
