package db.derby.remote; /**
 * 
 */

import java.sql.Connection;

//TODO: comment
/**
 * @author axrt
 *
 */
public interface ResultSaver {
    
	public int saveResultToDatabase(Object result, Connection databaseConnecton) throws Exception;
	
}
