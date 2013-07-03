import cars.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
public class Mobile {
	public static void main(String[] args)
	{
		
//		Begin process. Explaining something important 
		Mobile.console("Welcome. Anyway we have some cars");
		Mobile.console("This is the list of the cars. Enter the name to choose your car");
		Mobile.console("");
		Mobile.console("\tsedan \t\t get Sedan car");
		Mobile.console("\tsuv \t\t get SUV car");
		Mobile.console("\ttruck \t\t get Truck");
		Mobile.console("\tbus \t\t get Bus");
		Mobile.console("");
		Mobile.createCar(false);
//		Getting car type and it will check if the class is exists or not
	}
	public static void createCar(boolean tryAgain)
	{
		if(tryAgain)
			Mobile.console("Cannot find car you type, Try again. type the car you want. ");
		else
		Mobile.console("Type your the car type you want to choose");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Cars carType;
		try {
			carType = Mobile.getCar(br.readLine());
			if(carType instanceof Cars)
			{
				Mobile.console("Your car: "+carType.getType());
				carType.start();
			}
			else
			{
				Mobile.createCar(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Cars getCar(String carType)
	{
//		Registering cars type
		Map<String, Cars> carList =new HashMap<String, Cars>();
		carList.put("sedan", new Sedan());
		carList.put("truck", new Truck());
		carList.put("suv", new Suv());
		carList.put("bus", new Bus());
		if(carList.containsKey(carType))
			return carList.get(carType);
		else
			return null;
	}
	public static void console(String message)
	{
		System.out.println(message);
	}
}
