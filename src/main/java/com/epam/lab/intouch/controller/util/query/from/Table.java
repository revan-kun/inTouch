package com.epam.lab.intouch.controller.util.query.from;

import com.epam.lab.intouch.controller.util.query.Writable;
import com.epam.lab.intouch.controller.util.query.select.Column;
import com.epam.lab.intouch.controller.util.query.select.Wildcard;

/**
 * Table is the main object in FROM section of the query
 * 
 * @author Zatorsky D.B
 * 
 */
public class Table implements Writable {
	private String name;

	public Table(String name) {
		this.name = name;
	}

	/**
	 * @param name
	 *            name of the Column you want to create. Result name will be something like TableName.ColumnName
	 * @return Column object with this table name inside
	 */
	public Column getColumn(String name) {
		Column column = new Column(name);
		column.setTableName(this.name);
		return column;
	}

	/**
	 * Use it when you want get all the information from this table. Result will be something like TableName.*
	 * 
	 * @return Wildcard object with this table name inside
	 */
	public Wildcard getWildcard() {
		Wildcard wildcard = new Wildcard(name);
		return wildcard;
	}

	/**
	 * Returns name of the table
	 * 
	 * @return name of the table
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of the table
	 * 
	 * @param name
	 *            name of the table
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns table name
	 * 
	 * @see com.epam.lab.intouch.controller.util.query.Writable#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
}
