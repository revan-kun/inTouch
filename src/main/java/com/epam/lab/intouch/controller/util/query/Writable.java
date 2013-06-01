package com.epam.lab.intouch.controller.util.query;

/**
 * 
 * This interface determines that all parts of sql query must be rendered as String
 * 
 * @author Zatorsky D.B
 * 
 */
public interface Writable {
	/**
	 * @return string representation of query
	 */
	String toString();
}
