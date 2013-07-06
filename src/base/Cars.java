package base;
import threads.CarsThread;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cars implements MobileInterface {
//	static variable, the way to access of the the variable must be the variable name only without this
//	or if it is declared as public and want to access from outside the class it should access like this
//	Cars.wheelsNumber;
	public static int wheelsNumber=0;
	
	public int getWheels()
	{
		return wheelsNumber;
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
