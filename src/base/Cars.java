package base;
import threads.CarsThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import models.CarsCollection;

public class Cars implements base.interfaces.Cars {
	
	private int wheelsNumber=4;
	private String type="Cars";
	private String carName = "";
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
		String carName = this.getName();
		if(carName=="")
			carName = this.getType();
		System.out.println("("+formattedDate.format(date).toString()+") "+carName+" : "+message);
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
	public void setName(String name)
	{
		this.carName = name;
	}
	public String getName()
	{
		return this.carName;
	}

	public String setCarName(Boolean tryAgain, String lastTry)
	{
		if(tryAgain)
			this.console("\nOops, \""+lastTry+"\" has been taken. Try another one");
		else
			this.console("\nType to save your desired car name for current car");
		CarsCollection model = new CarsCollection();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String name = br.readLine();
			Map<String, String> find = new HashMap<String, String>();
			if(this.getName()!="")
			{
				CarsCollection modelClass = new CarsCollection();
				find = modelClass.where("car_name='"+this.getName()+"'").find();
				model.where("car_id!='"+find.get("car_id")+"'");
			}
			System.out.println("Check availability\n");
			if(model.where("car_name='"+name+"'").find()!=null)
				return this.setCarName(true, name);
			else
			{
				Map<String, String> attributes = new HashMap<String, String>();
				attributes.put("car_name", name);
				if(find.isEmpty())
				{
					Date date = new Date();
					SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					attributes.put("car_type", this.getType());
					attributes.put("car_saved", formattedDate.format(date));
				}
				else
				{
					model.setUpdateCondition("car_id='"+find.get("car_id")+"'");
				}
				model.setAttributes(attributes).save();
			}
			return name;
		}
		catch(IOException e){
			e.printStackTrace();
			return "";
		}
	}
}
