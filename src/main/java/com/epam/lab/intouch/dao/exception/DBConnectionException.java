package com.epam.lab.intouch.dao.exception;

public class DBConnectionException extends Exception {

	private static final long serialVersionUID = 1L;

	public DBConnectionException() {
		super("DB connection has occured!");
		// TODO Auto-generated constructor stub
	}

	public DBConnectionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DBConnectionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DBConnectionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DBConnectionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
