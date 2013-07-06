package cars;

import base.Cars;

public class Bus extends Cars{
	public String getType()
	{
		return "Bus";
	}
	public int getWheels(){
		return 4;
	}
}