package base.ar;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model extends Connector implements base.interfaces.Model{
	private String tableName;
	private Map<String, ArrayList<String>> statements=new HashMap<String, ArrayList<String>>();
	private Map<String, String> attributes = new HashMap<String, String>();
	private String condition="";
	public boolean isNewRecord = true;
	public Model(String tableName) {
		this.tableName = tableName;
		this.isNewRecord = true;
	}
	
	public Map<String, String> find()
	{
		ArrayList<Map<String, String>> results = this.findAll();
		if(results.size()>0)
		return results.get(0);
		else return null;
	}
	public ArrayList<Map<String, String>> findAll()
	{
		String where = this.buildStatement("where"," and ");
		if(where !="")
		{
			where = " WHERE "+where;
		}
		String query = "SELECT * FROM "+this.tableName+where;
		return this.query(query);
	}
	public String buildStatement(String key, String separator)
	{
		if(this.statements.isEmpty())
			return "";
		ArrayList<String> value= this.statements.get(key);
		if(value != null)
		{
			StringBuilder buf = new StringBuilder();
			for (int i=0;i<value.size();i++)
			{
				buf.append(value.get(i));
				if(i!=value.size()-1)
					buf.append(separator);
			}
			String s = buf.toString();
			return s;
		}
		else return "";
	}
//	Add some WHERE conditional statement
	public base.interfaces.Model where(String where)
	{
		if(this.statements.get("where")==null)
			this.statements.put("where", new ArrayList<String>());
		this.statements.get("where").add(where);
		return this;
	}
//	Add some ORDER conditional statement
	@Override
	public base.interfaces.Model order(String order) {
		// TODO Auto-generated method stub

		if(this.statements.get("order")==null)
			this.statements.put("order", new ArrayList<String>());
		this.statements.get("order").add(order);
		return this;
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		boolean result = this.isNewRecord?this.insert():this.update();
		this.resetAll();
		return result;
	}

	@Override
	public boolean insert() {
		// TODO Auto-generated method stub
		ArrayList<String> keys = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		for(String key: this.attributes.keySet())
		{
			keys.add(key.toString());
			values.add(this.attributes.get(key.toString()).toString());
		}
		String fields="";
		String fieldsValue="";
		for(int i=0;i<keys.size();i++)
		{
			if(i!=keys.size()-1)
			{
				fields = fields+keys.get(i)+",";
				fieldsValue = fieldsValue+"'"+values.get(i)+"',";
			}
			else
			{
				fields = fields+keys.get(i);
				fieldsValue = fieldsValue+"'"+values.get(i)+"'";
			}
		}
		String query = "INSERT INTO "+this.tableName
				+"("+fields+")"+
				" VALUES("+fieldsValue+")";
		return this.execute(query);
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		ArrayList<String> keys = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		for(String key: this.attributes.keySet())
		{
			keys.add(key.toString());
			values.add(this.attributes.get(key.toString()).toString());
		}
		String updateValue="";
		for(int i=0;i<keys.size();i++)
		{
			if(i!=keys.size()-1)
			{
				updateValue = updateValue+keys.get(i)+"='"+values.get(i)+"', ";
			}
			else
			{
				updateValue = updateValue+keys.get(i)+"='"+values.get(i)+"'";
			}
		}
		String query = "UPDATE "+this.tableName+
				" SET "+updateValue+" WHERE "+this.condition;
		return this.execute(query);
	}
	public base.interfaces.Model setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
		// TODO Auto-generated method stub
		return this;
	}

	public ArrayList<Map<String, String>> query(String query)
	{
		this.open();
		try{
			Statement state = this.connection.createStatement();
			ResultSet results=state.executeQuery(query);
			while(results.next())
			{
				ResultSetMetaData columns = results.getMetaData();
				Map <String, String> map = new HashMap<String, String>();
				int columnsNumber = columns.getColumnCount();
				for(int i=1;i<=columnsNumber;i++)
				{
					map.put(columns.getColumnLabel(i), results.getString(columns.getColumnLabel(i)));
				}
				this.results.add(map);
			}
			state.close();
			this.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			this.close();
		}
		this.resetAll();
		return this.results;
	}
	public void setUpdateCondition(String condition)
	{
		this.isNewRecord = false;
		this.condition = condition;
	}
	public void resetAll()
	{
		this.condition = "";
		this.statements = new HashMap<String, ArrayList<String>>();
	}
}
