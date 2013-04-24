package com.epam.lab.intouch.db.exception;

public class DBCloseConnectionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DBCloseConnectionException() {
		super("Problem with close connection to DB!");

	}

	public DBCloseConnectionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DBCloseConnectionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DBCloseConnectionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DBCloseConnectionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
