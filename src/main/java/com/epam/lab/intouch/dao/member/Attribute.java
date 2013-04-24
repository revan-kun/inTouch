package com.epam.lab.intouch.dao.member;

/**
 * Enumeration of all Member table attributes
 * 
 * @author Revan & Molodec))
 * @version 0.1
 * 
 */
public enum Attribute {
	LOGIN(1, "login"), 
	PASSWORD(2, "password"), 
	NAME(3, "name"), 
	SURNAME(4, "surname"), 
	BIRTHDAY(5, "birthday"), 
	REGISTRATION(6, "registration"), 
	SEX(7, "sex"), 
	QLEVEL(8, "qlevel"), 
	EXPERIENCE(9, "experience"), 
	PHOTO_LINK(10, "photo_link"), 
	ROLE(11, "role");

	private int index;
	private String colName;

	private Attribute(final int index, final String colName) {
		this.index = index;
		this.colName = colName;
	}

	/**
	 * Method returns all attributes of Member table
	 * 
	 * @return attributes
	 */
	public static String getAttributes() {
		final StringBuilder builder = new StringBuilder();

		for (Attribute temp : values()) {
			builder.append(temp.toString().toLowerCase()).append(", ");
		}

		final int length = builder.length();
		builder.delete(length, length - 1);

		return builder.toString();
	}

	/**
	 * Returns attribute index in Member table
	 * 
	 * @return index of attribute
	 */
	public int index() {
		return this.index;
	}

	public String getName() {
		return this.colName;
	}
}
