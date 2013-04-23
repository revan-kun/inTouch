package com.epam.lab.intouch.controller.exception;

public class InputDataFormatException extends Exception {
	private static final long serialVersionUID = -3461915314892821089L;

	public InputDataFormatException() {
		super("Input data has wrong format");
	}

	public InputDataFormatException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InputDataFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public InputDataFormatException(String message) {
		super(message);
	}

	public InputDataFormatException(Throwable cause) {
		super(cause);
	}

}
