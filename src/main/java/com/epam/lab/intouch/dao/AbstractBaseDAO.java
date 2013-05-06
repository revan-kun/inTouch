package com.epam.lab.intouch.dao;

import java.sql.Connection;

import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.db.util.ConnectionManager;

public abstract class AbstractBaseDAO<E, I> implements BaseDAO<E, I> {
	
	protected Connection getConnection() throws DBConnectionException {
		Connection connection = null;
		ConnectionManager connectionManager = ConnectionManager.getInstance();
		connection = connectionManager.getConnection();
		return connection;
	}
}
