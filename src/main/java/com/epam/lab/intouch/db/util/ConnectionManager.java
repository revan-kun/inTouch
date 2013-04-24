package com.epam.lab.intouch.db.util;

import static com.epam.lab.intouch.db.util.PropertiesReader.getProperty;

import java.beans.PropertyVetoException;
import java.sql.Connection;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.lab.intouch.db.exception.DBCloseConnectionException;
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * Class that manage all DataBase connections and helps easy change DB from one
 * to another.
 * 
 * @author Axel
 * 
 */
public class ConnectionManager {
	private final static Logger LOG = Logger.getLogger(ConnectionManager.class);

	private static ConnectionManager instance = null;

	private Connection connection = null;

	/**
	 * Private constructor due to Singleton pattern
	 * 
	 */
	private ConnectionManager() {

	}

	private String getConnectionUrl() {
		StringBuilder builder = new StringBuilder();
		builder.append(getProperty("db_url"));
		builder.append(getProperty("server_name"));
		builder.append(":");
		builder.append(getProperty("port_number"));
		builder.append(";").append("databaseName=");
		builder.append(getProperty("db_name"));
		return builder.toString();
	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}

		return instance;
	}

	private void openConnection() throws DBConnectionException {

		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try {

			cpds.setDriverClass(getProperty("driver_class"));
			cpds.setJdbcUrl(getConnectionUrl());
			cpds.setUser(getProperty("user_name"));
			cpds.setPassword(getProperty("user_password"));

			// the settings below are optional -- c3p0 can work with defaults
			cpds.setMinPoolSize(5);
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(20);

			connection = cpds.getConnection();
		} catch (PropertyVetoException | SQLException e) {
			LOG.error("Error occured while open connection to DB", e);
			throw new DBConnectionException();

		}

	}

	public Connection getConnection() throws DBConnectionException  {
		if (connection == null) {
			openConnection();
		}
		return connection;
	}

	public void close() throws DBCloseConnectionException {
		try {
			connection.close();
			connection = null;
		} catch (SQLException ex) {
			LOG.error("Error occured while closing DB connection", ex);
			throw new DBCloseConnectionException();
		}
	}

}