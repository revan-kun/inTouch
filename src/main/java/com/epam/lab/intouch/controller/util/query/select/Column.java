package com.epam.lab.intouch.controller.util.query.select;

public class Column extends Selectable {

	private String columnName;

	public Column(String columnName) {
		this.columnName = columnName;
	}

	public Column(String tableName, String columnName) {
		this.columnName = columnName;
		setTableName(tableName);
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

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
