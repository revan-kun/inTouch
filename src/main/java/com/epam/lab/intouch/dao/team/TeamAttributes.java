package com.epam.lab.intouch.dao.team;

public enum TeamAttributes {

	PROJECT_ID(1, "project_id"), MEMBER_ID(2, "member_id");

	private int index;
	private String colName;

	private TeamAttributes(int index, String colName) {
		this.index = index;
		this.colName = colName;
	}

	public int index() {
		return this.index;
	}

	public String getName() {
		return this.colName;
	}

}
