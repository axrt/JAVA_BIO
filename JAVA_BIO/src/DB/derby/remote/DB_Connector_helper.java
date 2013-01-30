/**
 * 
 */
package DB.derby.remote;

/**
 * Stores some common values used by the database-related classes
 * 
 * @author axrt
 * 
 */
public enum DB_Connector_helper {

	// Singleton instance
	Instance;

	/**
	 * Private constructor prevents instantiation
	 */
	private DB_Connector_helper() {
		//
	}

	/**
	 * user
	 */
	public static String user = "user";
	/**
	 * password
	 */
	public static String password = "password";
	/**
	 * create
	 */
	public static String create = "create";
}
