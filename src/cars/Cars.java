package cars;

abstract public class Cars extends Thread {
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
	}
	
	
	public String getType()
	{
		return "Car";
	}
}
