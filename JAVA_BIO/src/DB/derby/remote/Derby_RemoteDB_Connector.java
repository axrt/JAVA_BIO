package DB.derby.remote;

import java.net.URL;
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
	 * User, used as a user name when connecting the derby database
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
	 * A flag to track whether the connection to the database has been
	 * established
	 */
	protected boolean connected = false;

	/**
	 * @param derbyURL
	 *            {@link URL} of the database
	 * @param user
	 *            {@link String} user name
	 * @param password
	 *            {@link String} password
	 * @param create
	 *            {@code true} if the database should be created, {@code false}
	 *            if the database is assumed to exist already
	 */
	protected Derby_RemoteDB_Connector(String derbyURL, String user,
			String password, String create) {
		super();
		this.derbyURL = derbyURL;
		this.user = user;
		this.password = password;
		this.create = ";" + DB_Connector_helper.create + "=" + create;
		// Initializing and assembling properties
		this.derbyConnectionProperties = new Properties();
		this.derbyConnectionProperties.put(DB_Connector_helper.user, this.user);
		this.derbyConnectionProperties.put(DB_Connector_helper.password,
				this.password);
	}

	/**
	 * 
	 * @return {@link Connection} connection to the database
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * The embedded driver, org.apache.derby.jdbc.EmbeddedDriver
	 */
	protected static final String derbyRemoteDriver = "org.apache.derby.jdbc.ClientDriver";

	/**
	 * Loads a driver for the embedded derby database
	 */
	protected static boolean loadDerbyDriver() throws ClassNotFoundException {
		// Dynamic class load the driver from the library
		Class.forName(Derby_RemoteDB_Connector.derbyRemoteDriver);
		return true;

	}

	/**
	 * Connects to the embedded derby database
	 * 
	 * @return {@code true} if connected, {@code false} if smth went wrong
	 * @throws SQLException
	 */
	public boolean connectToRemoteDerbyDatabase() throws SQLException,
			ClassNotFoundException {
		if (Derby_RemoteDB_Connector.loadDerbyDriver()) {
			this.connection = DriverManager.getConnection(this.derbyURL
					+ this.create, this.derbyConnectionProperties);
			return (this.connected = true);
		} else {
			return (this.connected = false);
		}
	}

	/**
	 * 
	 * @return {@code true} in case the database connection has been
	 *         established, {@code false} otherwise
	 */
	public boolean isConnected() {
		return this.connected;
	}

	/**
	 * A static factory that creates new instances of database connections
	 * 
	 * @param derbyURL
	 *            {@link URL} of the database
	 * @param user
	 *            {@link String} user name
	 * @param password
	 *            {@link String} password
	 * @param create
	 *            {@code true} if the database should be created, {@code false}
	 *            if the database is assumed to exist already
	 * @return a new instance of {@link Derby_RemoteDB_Connector} from the given
	 *         set of parameters
	 */
	public static Derby_RemoteDB_Connector newDefaultInstance(String derbyURL,
			String user, String password, boolean create) {
		return new Derby_RemoteDB_Connector(derbyURL, user, password,
				String.valueOf(create)) {
		};
	}

}
