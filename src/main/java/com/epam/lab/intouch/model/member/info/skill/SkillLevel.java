package com.epam.lab.intouch.model.member.info.skill;

public enum SkillLevel {
	BEGINNER, INTERMEDIATE, ADVANCED, MASTER, GODLIKE;

	public static SkillLevel fromString(String string) {
		if (string != null) {
			for (SkillLevel skillLevel : values()) {
				if (string.equalsIgnoreCase(skillLevel.toString())) {
					return skillLevel;
				}
			}
		}
		return null;
	}
}
