package com.epam.lab.intouch.controller.util.query.select;

import com.epam.lab.intouch.controller.util.query.Writable;

public abstract class Selectable implements Writable {
	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
