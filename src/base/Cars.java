package base;
import threads.CarsThread;
import interfaces.CarsInterface;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cars implements CarsInterface {
	
	private int wheelsNumber=4;
	private String type="Cars";
	public Cars(String type, int wheelsNumber)
	{
		this.type=type;
		this.wheelsNumber=wheelsNumber;
	}
	public int getWheels()
	{
		return wheelsNumber;
	}
	public void setWheels(int wheelsNumber)
	{
		this.wheelsNumber = wheelsNumber;
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
		return this.type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
