package com.epam.lab.intouch.model.member.enums;

public enum QualificationLevel {
	JUNIOR("Junior"), MIDDLE("Middle"), SENIOR("Senior"), JODA("Joda"), GODLIKE("Godlike");

	private final String value;

	private QualificationLevel(String value) {
		this.value = value;

	}

	public String getQualificationLevel() {
		return value;
	}

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
