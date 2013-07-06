package base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import cars.*;

public class MobileBase implements MobileInterface{
	public static void main(String[] args)
	{
		
//		Begin process. Explaining something important
//		Welcome message
		MobileBase mobile = new MobileBase();
		
		mobile.console("Welcome. Anyway we have some cars");
		mobile.console("This is the list of the cars. Enter the name to choose your car");
//		Cars list
		mobile.prepareCar();
//		Getting car type and it will check if the class is exists or not
	}
	
	public void prepareCar()
	{
		this.console("\n");
		Map<String, Cars> carList = this.getCarList();

		Object[] carKeys = carList.keySet().toArray();
		for(int i=0;i<carKeys.length;i++)
		{
			String key=(String) carKeys[i];
			this.console("\t"+key+" \t\t"+"get "+carList.get(key).getType()+" car");
		}
		this.createCar(false);
	}
	public void createCar(boolean tryAgain)
	{
		if(tryAgain)
			this.console("\nCannot find car you type, Try again. type the car you want. ");
		else
			this.console("\nType your the car type you want to choose");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Cars carType;
		try {
			carType = this.getCar(br.readLine());
			if(carType instanceof Cars)
			{
				this.console("\nYour car: "+carType.getType());
				carType.start();
			}
			else
			{
				this.createCar(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Map<String, Cars> getCarList()
	{
		Map<String, Cars> carList =new HashMap<String, Cars>();
		carList.put("sedan", new Sedan());
		carList.put("truck", new Truck());
		carList.put("suv", new Suv());
		carList.put("bus", new Bus());
		return carList;
	}
	public Cars getCar(String carType)
	{
//		Registering cars type
		Map<String, Cars> carList = this.getCarList();
		if(carList.containsKey(carType))
			return (Cars) carList.get(carType);
		else
			return null;
	}
	public void console(String message)
	{
		System.out.println(message);
	}
	public static void restart()
	{
		MobileBase mobile = new MobileBase();
		mobile.console("Welcome back, here is the list of the cars");
		mobile.prepareCar();
	}
}
