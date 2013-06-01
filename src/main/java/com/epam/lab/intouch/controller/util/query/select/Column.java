package com.epam.lab.intouch.controller.util.query.select;

/**
 * This class represents field in database table
 * 
 * @author Zatorsky D.B
 * 
 */
public class Column extends Selectable {

	private String columnName;

	public Column(String columnName) {
		this.columnName = columnName;
	}

	public Column(String tableName, String columnName) {
		this.columnName = columnName;
		setTableName(tableName);
	}

	/**
	 * @return column name
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName
	 *            name of the column
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * This method returns column name
	 * 
	 * @see com.epam.lab.intouch.controller.util.query.Writable#toString()
	 */

	@Override
	public String toString() {
		String result = "";
		if (getTableName() != null) {
			result = getTableName() + "." + columnName;
		} else {
			result = columnName;
		}
		return result;
	}

}
