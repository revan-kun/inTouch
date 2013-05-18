package com.epam.lab.intouch.model.member.enums;

public enum LikeStatus {
	
	LIKE, DISLIKE, DONT_CARE;
	
	public static LikeStatus fromString(String string) {
		if (string != null) {
			for (LikeStatus status : values()) {
				if (string.equalsIgnoreCase(status.toString())) {
					return status;
				}
			}
		}
		return null;
	}

}
