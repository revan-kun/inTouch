package com.epam.lab.intouch.controller.exception;

public class PermissionException extends Exception {
	private static final long serialVersionUID = -3707835759878484119L;

	public PermissionException() {
		super("Mmber hasn't permission for this action!");
	}

	public PermissionException(String message) {
		super(message);
	}

	public PermissionException(Throwable cause) {
		super(cause);
	}

	public PermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
