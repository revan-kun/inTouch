package com.epam.lab.intouch.dao.exception;

public class DAOReadException extends DAOException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>DAOReadException</code> with no detail message.
	 */

	public DAOReadException() {
		super();
	}

	/**
	 * Constructs a <code>DAOReadException</code> with the specified detail
	 * message.
	 * 
	 * @param info
	 *            - the detail message.
	 */
	public DAOReadException(final String info) {
		super(info);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message
	 * of <tt>(cause==null ? null : cause.toString())</tt> (which typically
	 * contains the class and detail message of <tt>cause</tt>). This
	 * constructor is useful for exceptions that are little more than wrappers
	 * for other throwables (for example,
	 * {@link java.security.PrivilegedActionException}).
	 * 
	 * @param cause
	 *            - the cause (which is saved for later retrieval by the
	 *            {@link #getCause()} method). (A <tt>null</tt> value is
	 *            permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 */
	public DAOReadException(final Throwable cause) {
		super(cause);
	}

}
