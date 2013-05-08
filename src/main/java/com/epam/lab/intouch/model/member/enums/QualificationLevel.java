package com.epam.lab.intouch.model.member.enums;

public enum QualificationLevel {
	JUNIOR, MIDDLE, SENIOR, JODA, GODLIKE;

	public static QualificationLevel fromString(String string) {
		if (string != null) {
			for (QualificationLevel level : values()) {
				if (string.equalsIgnoreCase(level.toString())) {
					return level;
				}
			}
		}
		return null;
	}
}
