package com.epam.lab.intouch.dao.project;

public enum ProjectAttribute {

	ID, NAME, CREATED, ESTIMATED_COMPLETION, COMPLETED, DESCRIPTION, CUSTOMER, STATUS;

	public static String getAttributes() {
		final StringBuilder builder = new StringBuilder();

		for (ProjectAttribute temp : values()) {
			builder.append(temp.toString().toLowerCase()).append(", ");
		}

		final int length = builder.length();
		builder.delete(length - 2, length);

		return builder.toString();
	}

}
