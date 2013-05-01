package com.epam.lab.intouch.dao.member;

public enum MemberAttribute {
	LOGIN, 
	PASSWORD, 
	NAME, 
	SURNAME, 
	BIRTHDAY, 
	REGISTRATION, 
	SEX, 
	QLEVEL, 
	EXPERIENCE, 
	PHOTO_LINK, 
	ADDITIONAL_INFO, 
	ROLE;

	/**
	 * Method returns all attributes of Member table
	 * 
	 * @return attributes
	 */
	public static String getAttributes() {
		final StringBuilder builder = new StringBuilder();

		for (MemberAttribute temp : values()) {
			builder.append(temp.toString().toLowerCase()).append(", ");
		}

		final int length = builder.length();
		builder.delete(length - 2, length);

		return builder.toString();
	}

}
