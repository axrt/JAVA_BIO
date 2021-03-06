package db.derby.remote;

import db.Connector;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
public class Derby_RemoteDB_Connector extends Connector {

	/**
	 * Create, used to set the create database (if db not exists yet) when
	 * connecting the derby database
	 */
	protected final String create;

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
		super(derbyURL,user,password);
		this.create = ";" + create + "=" + create;
		// Initializing and assembling properties
		this.connectionProperties = new Properties();
		this.connectionProperties.put(user, this.user);
		this.connectionProperties.put(password,
				this.password);
	}

	/**
	 * The remote driver, org.apache.derby.jdbc.EmbeddedDriver
	 */
	protected static final String derbyRemoteDriver = "org.apache.derby.jdbc.ClientDriver";

	/**
	 * Loads a driver for the remote derby database
	 */
    @Override
	protected boolean loadDriver() throws ClassNotFoundException {
		// Dynamic class load the driver from the library
		Class.forName(Derby_RemoteDB_Connector.derbyRemoteDriver);
		return true;
	}

	/**
	 * Connects to the remote derby database
	 * 
	 * @return {@code true} if connected, {@code false} if smth went wrong
	 * @throws SQLException
	 */
    @Override
	public boolean connectToDatabase() throws SQLException,
			ClassNotFoundException {
		if (this.loadDriver()) {
			this.connection = DriverManager.getConnection(this.URL
					+ this.create, this.connectionProperties);
			return (this.connected = true);
		} else {
			return (this.connected = false);
		}
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
