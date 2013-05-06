package com.epam.lab.intouch.controller.exception;

public class DataAccessingException extends Exception {
	private static final long serialVersionUID = -8730291987805865990L;

	public DataAccessingException() {
		super("Cannot access to data");
	}

	public DataAccessingException(String message) {
		super(message);
	}

	public DataAccessingException(Throwable cause) {
		super(cause);
	}

	public DataAccessingException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
