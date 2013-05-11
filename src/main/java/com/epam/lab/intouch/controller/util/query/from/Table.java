package com.epam.lab.intouch.controller.util.query.from;

import com.epam.lab.intouch.controller.util.query.Writable;
import com.epam.lab.intouch.controller.util.query.select.Column;
import com.epam.lab.intouch.controller.util.query.select.Wildcard;

public class Table implements Writable {
	private String name;

	public Table(String name) {
		this.name = name;
	}

	public Column getColumn(String name) {
		Column column = new Column(name);
		column.setTableName(this.name);
		return column;
	}

	public Wildcard getWildcard() {
		Wildcard wildcard = new Wildcard(name);
		return wildcard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
