package com.epam.lab.intouch.controller.exception;

public class MemberAuthorizationException extends Exception {
	
	private static final long serialVersionUID = -8368128446124359165L;

	public MemberAuthorizationException() {
		super("Member not found");
	}

	public MemberAuthorizationException(String message) {
		super(message);
	}

	public MemberAuthorizationException(Throwable cause) {
		super(cause);
	}

	public MemberAuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MemberAuthorizationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
