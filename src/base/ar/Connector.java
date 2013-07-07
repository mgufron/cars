package base.ar;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Connector {
	public Connection connection=null;
	static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost/cars";
	static final String DB_USER="root";
	static final String DB_PASS="root";
	public ArrayList<Map<String, String>> results = new ArrayList<Map<String, String>>();
	public Connector() {
		// TODO Auto-generated constructor stub
	}
	public void open()
	{
		try{
			Class.forName(JDBC_DRIVER);
			this.connection = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void close()
	{
		try{
			this.connection.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	public boolean execute(String query)
	{
		this.open();
		try{
			Statement state = this.connection.createStatement();
			boolean result = state.execute(query);
			this.close();
			return result;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			this.close();
			return false;
		}
	}
	public String makeQuery()
	{
		return "";
	}
	public String createStatement()
	{
		return "";
		
	}
}
