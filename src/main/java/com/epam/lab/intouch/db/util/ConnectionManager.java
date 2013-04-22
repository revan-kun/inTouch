package com.epam.lab.intouch.db.util;

import static com.epam.lab.intouch.db.util.PropertiesReader.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
/**
 * 
 * Class that manage all DataBase connections and helps easy change DB from one to another.
 * 
 * @author Axel
 *
 */
public class ConnectionManager {
	private final static Logger LOG = Logger.getLogger(ConnectionManager.class);

	private static ConnectionManager instance = null;

	

	private static final DBType DEFAULT_DB = DBType.MSSQL;

	private DBType dbType = DEFAULT_DB;
	
	

	private Connection connection = null;

	/**
	 * Private constructor due to Singleton pattern
	 * 
	 */
	private ConnectionManager() {

	}
	
	private String getConnectionUrl(){
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

	public void setDBType(DBType dbType) {
		this.dbType = dbType;
	}

	private void openConnection() throws SQLException {
		if (dbType == DBType.MSSQL) {
			connection = DriverManager.getConnection(getConnectionUrl(), getProperty("user_name"), getProperty("user_password"));
		} else if (dbType == DBType.MYSQL) {
			connection = DriverManager.getConnection(getConnectionUrl(), getProperty("user_name"), getProperty("user_password"));
		} else {
			throw new SQLException("Unable to identify DB");
		}

	}

	public Connection getConnection() {
		if (connection == null) {
			try {
				openConnection();
			} catch(SQLException ex) {
				System.out.println(ex);
				LOG.fatal("Error occured while attempting to open DB connection", ex);
			}
		}
		return connection;
	}

	public void close() {
		try {
			connection.close();
			connection = null;
		} catch (SQLException ex) {
			LOG.error("Error occured while closing DB connection", ex);
		}
	}

}