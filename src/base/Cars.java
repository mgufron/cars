package base;
import threads.CarsThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			ResultSet find = null;
			if(this.getName()!="")
			{
				CarsCollection modelClass = new CarsCollection();
				find = modelClass.find(this.getName());
			}
			System.out.println("Check availability\n");
			if(model.count(name)>0)
				return this.setCarName(true, name);
			else
			{
				if(model.count(name)==0)
				model.insert(name, this.getType());
				else
					try {
						model.update(name, find.getString("car_name"));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			return name;
		}
		catch(IOException e){
			e.printStackTrace();
			return "";
		}
	}
}
