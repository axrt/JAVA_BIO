package db;

import java.sql.Connection;
import java.util.Properties;
/**
 * Java Bio is a free open source library for routine bioinformatics tasks.
 * Copyright (C) 2013  Alexander Tuzhikov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * An abstract class to extend for a certain type of a database used.
 */
public abstract class Connector {

    /**
     * The derby url
     */
    protected final String URL;
    /**
     * User, used as a user name when connecting the derby database
     */
    protected final String user;
    /**
     * Password, used as a password when connecting the derby database
     */
    protected final String password;
    /**
     * Database connection
     */
    protected Connection connection;
    /**
     * Properties to connect the database
     */
    protected Properties connectionProperties;
    /**
     * A flag to track whether the connection to the database has been
     * established
     */
    protected boolean connected = false;
    /**
     * @param URL
     *            {@link String} of the database
     * @param user
     *            {@link String} user name
     * @param password
     *            {@link String} password
     */
    protected Connector(String URL, String user,
                                       String password) {
        super();
        this.URL = URL;
        this.user = user;
        this.password = password;

        // Initializing and assembling properties
        this.connectionProperties = new Properties();
        this.connectionProperties.put(ConnectorHelper.user, this.user);
        this.connectionProperties.put(ConnectorHelper.password,
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
     * Loads a driver for the database
     */
    protected abstract boolean loadDriver() throws Exception;

    /**
     * Connects to the remote derby database
     *
     * @return {@code true} if connected, {@code false} if smth went wrong
     * @throws Exception
     */
    public abstract boolean connectToDatabase() throws Exception;

    /**
     *
     * @return {@code true} in case the database connection has been
     *         established, {@code false} otherwise
     */
    public boolean isConnected() {
        return this.connected;
    }

}
