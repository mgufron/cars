package models;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import base.ar.Connector;
public class CarsCollection extends Connector implements base.interfaces.Model{
	public static String tableName="car_collection";
	
	public boolean insert(String carName, String carType)
	{
		String query= "INSERT INTO "+tableName+"(car_name,car_type,car_saved) VALUES(?,?,?)";
		boolean result = false;
		try {
			this.open();
			PreparedStatement state = this.connection.prepareStatement(query);
			state.setString(1, carName);
			state.setString(2, carType);
			Date date = new Date(new java.util.Date().getTime());
			state.setDate(3, date);
			result = state.execute();
			state.close();
			this.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public boolean update(String carName, String oldName)
	{
		String query= "UPDATE "+tableName+" SET car_name=? WHERE car_name=?";
		boolean result = false;
		try {
			this.open();
			PreparedStatement state = this.connection.prepareStatement(query);
			state.setString(1, carName);
			state.setString(2, oldName);
			Date date = new Date(new java.util.Date().getTime());
			state.setDate(3, date);
			result = state.execute();
			state.close();
			this.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public int count(String carName)
	{
		String query= "SELECT COUNT(*) as counted FROM "+tableName+" WHERE car_name=?";
		int result = 0;
		try {
			this.open();
			PreparedStatement state = this.connection.prepareStatement(query);
			state.setString(1, carName);
			ResultSet data =state.executeQuery();
			if(data.next())
			result = data.getInt("counted");
			state.close();
			this.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public ResultSet find(String carName)
	{
		ResultSet result = null;
		String query= "SELECT * FROM "+tableName+" WHERE car_name=?";
		try{
			this.open();
			PreparedStatement state = this.connection.prepareStatement(query);
			state.setString(1, carName);
			result = state.executeQuery();
			this.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		return result;
	}
	public int countAll()
	{
		String query= "SELECT COUNT(*) as counted FROM"+tableName;
		int result = 0;
		try {
			this.open();
			Statement state = this.connection.createStatement();
			ResultSet data =state.executeQuery(query);
			if(data.next())
				result = data.getInt("counted");
			state.close();
			this.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<Map<String, String>> findAll()
	{
		String query = "SELECT * FROM "+tableName;
		ArrayList<Map<String, String>> results = new ArrayList<Map<String,String>>(); 
		try{
			this.open();
			Statement state = this.connection.createStatement();
			ResultSet result=state.executeQuery(query);
			while(result.next())
			{
				ResultSetMetaData columns = result.getMetaData();
				Map <String, String> map = new HashMap<String, String>();
				int columnsNumber = columns.getColumnCount();
				for(int i=1;i<=columnsNumber;i++)
				{
					map.put(columns.getColumnLabel(i), result.getString(columns.getColumnLabel(i)));
				}
				results.add(map);
			}
			state.close();
			this.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			this.close();
		}
		return results;
	}
}
