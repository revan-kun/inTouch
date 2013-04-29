package com.epam.lab.intouch.dao.project;

public enum ProjectAttribute {
	
	ID(1, "id"), NAME(2, "name"), CREATED(3, "created"), 
	ESTIMATED_COMPLETION(4, "estimated_completion"), 
	COMPLETED(5, "completed"), DESCRIPTION(6,"description"), CUSTOMER(7, "customer"), 
			STATUS(8, "status");
	
	private int index;
	private String colName;

	private ProjectAttribute(int index, String colName) {
		this.index = index;
		this.colName = colName;
	}

	public static String getAttributes() {
		final StringBuilder builder = new StringBuilder();

		for (ProjectAttribute temp : values()) {
			builder.append(temp.toString().toLowerCase()).append(", ");
		}

		final int length = builder.length();
		builder.delete(length - 2, length);

		return builder.toString();
	}

	public int index() {
		return this.index;
	}

	public String getName() {
		return this.colName;
	}

}
