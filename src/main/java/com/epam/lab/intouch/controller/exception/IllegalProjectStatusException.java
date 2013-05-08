package com.epam.lab.intouch.controller.exception;

public class IllegalProjectStatusException extends Exception {
	private static final long serialVersionUID = -2723929461360338734L;

	public IllegalProjectStatusException() {
		super("Updating project info when it is not open");
	}

	public IllegalProjectStatusException(String message) {
		super(message);
	}

	public IllegalProjectStatusException(Throwable cause) {
		super(cause);
	}

	public IllegalProjectStatusException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalProjectStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
