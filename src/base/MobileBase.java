package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import cars.*;

public class MobileBase extends MobileInterface{
	public static void main(String[] args)
	{
		
//		Begin process. Explaining something important
//		Welcome message
		MobileBase.console("Welcome. Anyway we have some cars");
		MobileBase.console("This is the list of the cars. Enter the name to choose your car");
//		Cars list
		MobileBase.prepareCar();
//		Getting car type and it will check if the class is exists or not
	}
	public static void prepareCar()
	{
		MobileBase.console("\n");
		Map<String, Cars> carList = MobileBase.getCarList();

		Object[] carKeys = carList.keySet().toArray();
		for(int i=0;i<carKeys.length;i++)
		{
			String key=(String) carKeys[i];
			MobileBase.console("\t"+key+" \t\t"+"get "+carList.get(key).getType()+" car");
		}
		MobileBase.createCar(false);
	}
	public static void createCar(boolean tryAgain)
	{
		if(tryAgain)
			MobileBase.console("\nCannot find car you type, Try again. type the car you want. ");
		else
			MobileBase.console("\nType your the car type you want to choose");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Cars carType;
		try {
			carType = MobileBase.getCar(br.readLine());
			if(carType instanceof Cars)
			{
				MobileBase.console("\nYour car: "+carType.getType());
				carType.start();
			}
			else
			{
				MobileBase.createCar(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Map<String, Cars> getCarList()
	{
		Map<String, Cars> carList =new HashMap<String, Cars>();
		carList.put("sedan", new Sedan());
		carList.put("truck", new Truck());
		carList.put("suv", new Suv());
		carList.put("bus", new Bus());
		return carList;
	}
	public static Cars getCar(String carType)
	{
//		Registering cars type
		Map<String, Cars> carList = MobileBase.getCarList();
		if(carList.containsKey(carType))
			return (Cars) carList.get(carType);
		else
			return null;
	}
	public static void console(String message)
	{
		System.out.println(message);
	}
	public static void restart()
	{
		MobileBase.console("Welcome back, here is the list of the cars");
		MobileBase.prepareCar();
	}
}
