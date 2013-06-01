package com.epam.lab.intouch.controller.util.query.select;

/**
 * @author Zatorsky D.B.
 * 
 */
public class Wildcard extends Selectable {

	public Wildcard() {

	}

	public Wildcard(String tableName) {
		setTableName(tableName);
	}

	private final String WILDCARD = "*";

	/**
	 * This method returns wildcard with table name, or without it if table name is not setted
	 * 
	 * @see com.epam.lab.intouch.controller.util.query.Writable#toString()
	 */
	@Override
	public String toString() {
		String result = "";
		if (getTableName() != null) {
			result = getTableName() + "." + WILDCARD;
		} else {
			result = WILDCARD;
		}
		return result;
	}

}
