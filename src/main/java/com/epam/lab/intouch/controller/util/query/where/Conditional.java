package com.epam.lab.intouch.controller.util.query.where;

import com.epam.lab.intouch.controller.util.query.Writable;

/**
 * Object that implements Conditional can be added in WHERE clause like a condition.
 * 
 * @author Zatorsky D.B
 * 
 */

public interface Conditional extends Writable {
	/**
	 * This method indicates whether condition is valid for adding it to WHERE clause.
	 * 
	 * @return true if condition is valid and it can be added to WHERE clause.
	 */
	Boolean isValid();
}
