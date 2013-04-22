package com.epam.lab.intouch.dao.exception;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1L;

	public PersistenceException() {
		super("Persistence exception has occured!");
	}

	public PersistenceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}

}
