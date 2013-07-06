package cars;

import base.Cars;

public class Truck extends Cars{
	public String getType()
	{
		return "Truck";
	}
	public int getWheels(){
		return 6;
	}
}