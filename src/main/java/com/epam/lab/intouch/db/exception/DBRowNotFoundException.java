package com.epam.lab.intouch.db.exception;

import java.sql.SQLException;

/**
 * This exception is thrown to indicate that specified row couldn't be found in DB
 * 
 * @author Revan
 * 
 */
public class DBRowNotFoundException extends SQLException {
	private static final long serialVersionUID = 4135628706751229181L;


	/**
	 * Constructs a <code>RowNotFoundException</code> with no detail message.
	 */
	public DBRowNotFoundException() {
		super();
	}

	/**
	 * Constructs a <code>RowNotFoundException</code> with the specified detail
	 * message.
	 * 
	 * @param info - the detail message.
	 */
	public DBRowNotFoundException(final String info) {
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
	public DBRowNotFoundException(final Throwable cause) {
        super(cause);
    }
}
