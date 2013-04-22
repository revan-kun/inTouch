package com.epam.lab.intouch.dao.exception;

import java.sql.SQLException;

/**
 * This exception is thrown to indicate that DB is unaccessible
 * 
 * @author Revan
 * 
 */
public class DBAccessException extends SQLException {
	private static final long serialVersionUID = -5813118076739069567L;

	/**
	 * Constructs a <code>DBAccessException</code> with no detail message.
	 */
	public DBAccessException() {
		super();
	}

	/**
	 * Constructs a <code>DBAccessException</code> with the specified detail
	 * message.
	 * 
	 * @param info - the detail message.
	 */
	public DBAccessException(final String info) {
		super(info);
	}
	
    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * java.security.PrivilegedActionException}).
     *
     * @param  cause - the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
	public DBAccessException(final Throwable cause) {
        super(cause);
    }
}
