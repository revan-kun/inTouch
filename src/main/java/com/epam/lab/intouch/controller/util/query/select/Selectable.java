package com.epam.lab.intouch.controller.util.query.select;

import com.epam.lab.intouch.controller.util.query.Writable;

/**
 * Child classes of this class represent arguments in SELECT section of the query
 * 
 * @author Zatorsky D.B
 * 
 */
public abstract class Selectable implements Writable {
	private String tableName;

	/**
	 * @return String - name of the parent table of column or wildcard
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            name of the parent table
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
