package db.mysql;

import db.Connector;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A representation of a module that connects to MySQL database
 */
public class MySQL_Connector extends Connector {

    /**
     * The  driver, com.mysql.jdbc.Driver for MySQL database connection
     */
    protected static final String MuSQLDriver = "com.mysql.jdbc.Driver";

    /**
     *
     * @param URL {@link String} of the database
     * @param user  {@link String} user name for the database
     * @param password  {@link String} password for the given user
     */
    protected MySQL_Connector(String URL, String user, String password) {
        super(URL, user, password);
    }

    /**
     * Loads a driver for the MySQL database
     */
    @Override
    protected boolean loadDriver() throws ClassNotFoundException {
        // Dynamic class load the driver from the library
        Class.forName(MySQL_Connector.MuSQLDriver);
        return true;
    }

    @Override
    public boolean connectToDatabase() throws SQLException,
            ClassNotFoundException  {
        if (this.loadDriver()) {
            this.connection = DriverManager.getConnection(this.URL
                   , this.connectionProperties);
            return (this.connected = true);
        } else {
            return (this.connected = false);
        }
    }

    /**
     *
     * @param URL
     * @param user
     * @param password
     * @return
     */
    public static MySQL_Connector newDefaultInstance(String URL,String user, String password){
        return new MySQL_Connector(URL,user,password);
    }
}
