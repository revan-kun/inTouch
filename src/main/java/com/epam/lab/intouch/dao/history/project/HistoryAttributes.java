package com.epam.lab.intouch.dao.history.project;

public enum HistoryAttributes {
	
	MEMBER_ID(1, "member_id"), PROJECT_ID(2, "project_id");
	
	private int index;
	private String colName;
	
	private HistoryAttributes(int index, String colName){
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
