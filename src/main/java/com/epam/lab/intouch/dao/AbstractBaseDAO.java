package com.epam.lab.intouch.dao;

import java.sql.Connection;

import com.epam.lab.intouch.dao.exception.DBConnectionException;
import com.epam.lab.intouch.dao.util.ConnectionManager;

public abstract class AbstractBaseDAO<E, I> implements BaseDAO<E, I> {
	
	/**
	 * Method to getting connection with Data Base
	 * @return Connection
	 * @throws DBConnectionException
	 */
	protected Connection getConnection() throws DBConnectionException {
		Connection connection = null;
		ConnectionManager connectionManager = ConnectionManager.getInstance();
		connection = connectionManager.getConnection();
		return connection;
	}
}
