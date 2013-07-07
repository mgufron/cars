/**
 * 
 */
package base.interfaces;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Mochamad Gufron
 * @since 1.0
 * 
 */
public interface Model {
//	Make where conditional statement before creating query
	public Model where(String where);
//	Make order statement before creating query
	public Model order(String order);
//	if it is new record
	public boolean save();
//	Saving new record
	public boolean insert();
//	updating current record
	public boolean update();
	
	public ArrayList<Map<String, String>> findAll();
	
	public Map<String, String> find();
	
	public Model setAttributes(Map<String, String> attributes);
}
