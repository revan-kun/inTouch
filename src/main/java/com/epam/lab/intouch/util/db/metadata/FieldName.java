package com.epam.lab.intouch.util.db.metadata;

public class FieldName {

	// field name for Member table in DB
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String SURNAME = "surname";
	public static final String BIRTHDAY = "birthday";
	public static final String REGISTRATION = "registration";
	public static final String SEX = "sex";
	public static final String QLEVEL = "qlevel";
	public static final String EXPERIENCE = "experience";
	public static final String PHOTO_LINK = "photo_link";
	public static final String ADDITIONAL_INFO = "additional_info";
	public static final String ROLE = "role";
	public static final String RATING = "rating";	
	public static final String LIKE_ID = "like_id";

	// for Project table
	public static final String ID = "id";
	// name is present
	public static final String CREATED = "created";
	public static final String ESTIMATED_COMPLETION = "estimated_completion";
	public static final String COMPLETED = "completed";
	public static final String DESCRIPTION = "description";
	public static final String CUSTOMER = "customer";
	public static final String STATUS = "status";

	// for teams and history tables
	public static final String MEMBER_ID = "member_id";
	public static final String PROJECT_ID = "project_id";
	public static final String ENTER_DATE = "enter_date";
	public static final String ENTER_TIME = "enter_time";
	public static final String LEAVING_DATE = "leaving_date";
	public static final String LEAVING_TIME = "leaving_time";

	// for Member_Skills table
	// member_id
	public static final String SKILL_ID = "skill_id";
	// experience
	// description
	public static final String SELF_ASSESSED_LEVEL = "self_assessed_level";

	// for Skills table
	// id
	// name
	public static final String TYPE = "type";
	//for Member Likes
	public static final String OWNER_LOGIN = "owner_login";
	public static final String LIKER_LOGIN = "liker_login";
	public static final String LIKE = "[like]";
	
	//for Likes
	public static final String LIKE_VALE = "like_value";
}
