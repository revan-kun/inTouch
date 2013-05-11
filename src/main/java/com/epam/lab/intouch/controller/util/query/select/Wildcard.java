package com.epam.lab.intouch.controller.util.query.select;

public class Wildcard extends Selectable {

	public Wildcard() {

	}

	public Wildcard(String tableName) {
		setTableName(tableName);
	}

	private final String WILDCARD = "*";

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
