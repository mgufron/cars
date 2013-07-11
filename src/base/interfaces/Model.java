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
	public int countAll();
	public ArrayList<Map<String, String>> findAll();
	
}
