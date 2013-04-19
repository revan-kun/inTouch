package com.epam.lab.intouch.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 
 * Class that manage all DataBase connections and helps easy change DB from one to another.
 * 
 * @author Revan
 *
 */
public class ConnectionManager {
	private final static Logger LOG = Logger.getLogger(ConnectionManager.class);

	private static ConnectionManager instance = null;

	private final String USERNAME = "revan";
	private final String PASSWORD = "qwerty";
	//private final String H_CONN_STRING = "jdbc:hsqldb:data/";
	private final String MS_CONN_STRING = "jdbc:";
	private final String M_CONN_STRING = "jdbc:mysql://localhost/";

	private static final DBType DEFAULT_DB = DBType.MSSQL;

	private DBType dbType = DEFAULT_DB;

	private Connection conn = null;

	/**
	 * Private constructor due to Singleton pattern
	 * 
	 */
	private ConnectionManager() {
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
			conn = DriverManager.getConnection(
					MS_CONN_STRING, USERNAME, PASSWORD);

		} else if (dbType == DBType.MYSQL) {
			conn = DriverManager.getConnection(
					M_CONN_STRING, USERNAME, PASSWORD);
		} else {
			throw new SQLException("Unable to identify DB");
		}

	}

	public Connection getConnection() {
		if (conn == null) {
			try {
				openConnection();
			} catch(SQLException ex) {
				LOG.fatal("Error occured while attempting to open DB connection", ex);
				return null;
			}
		}
		return conn;
	}

	public void close() {
		try {
			conn.close();
			conn = null;
		} catch (SQLException ex) {
			LOG.error("Error occured while closing DB connection", ex);
		}
	}

}