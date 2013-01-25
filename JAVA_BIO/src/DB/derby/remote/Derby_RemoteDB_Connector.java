package DB.derby.remote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class Derby_RemoteDB_Connector {

	/**
	 * The derby url, jdbc:derby:microblasterDB by default
	 */
	protected final String derbyURL;
	/**
	 * User, used as a username when connecting the derby database
	 */
	protected final String user;
	/**
	 * Password, used as a password when connecting the derby database
	 */
	protected final String password;
	/**
	 * Create, used to set the create database (if db not exists yet) when
	 * connecting the derby database
	 */
	protected final String create;
	/**
	 * Database connection
	 */
	protected Connection connection;
	/**
	 * Properties to connect the database
	 */
	private Properties derbyConnectionProperties;

	/**
	 * @param derbyURL
	 * @param user
	 * @param password
	 * @param create
	 */
	protected Derby_RemoteDB_Connector(String derbyURL, String user,
			String password, String create) {
		super();
		this.derbyURL = derbyURL;
		this.user = user;
		this.password = password;
		this.create = create;
		// Initializing and assembling properties
		this.derbyConnectionProperties = new Properties();
		this.derbyConnectionProperties.put("user", this.user);
		this.derbyConnectionProperties.put("password", this.password);
		this.derbyConnectionProperties.put("create", this.create);
	}

	/**
	 * The embedded driver, org.apache.derby.jdbc.EmbeddedDriver
	 */
	protected static final String derbyRemoteDriver = "org.apache.derby.jdbc.ClientDriver";

	/**
	 * Loads a driver for the embedded derby database
	 */
	protected static boolean loadDerbyDriver() throws ClassNotFoundException {

		Class.forName(Derby_RemoteDB_Connector.derbyRemoteDriver);
		return true;

	}

	/**
	 * Connects to the embedded derby database
	 * 
	 * @return {@code true} if connected, {@code false} if smth went wrong
	 * @throws SQLException
	 */
	private boolean connectToEmbeddedDerbyDatabase() throws SQLException,
			ClassNotFoundException {
		if (Derby_RemoteDB_Connector.loadDerbyDriver()) {
			this.connection = DriverManager.getConnection(this.derbyURL,
					derbyConnectionProperties);
			return true;
		} else {
			return false;
		}
	}

	public static Derby_RemoteDB_Connector newDefaultInstance(String derbyURL,
			String user, String password, boolean create) {
		return new Derby_RemoteDB_Connector(derbyURL, user, password,
				String.valueOf(create)) {
		};
	}

}
