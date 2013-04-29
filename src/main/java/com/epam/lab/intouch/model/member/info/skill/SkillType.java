package com.epam.lab.intouch.model.member.info.skill;

public enum SkillType {

	LANGUAGE, PROGRAMMING, TECHNOLOGY;

	public static SkillType fromString(String string) {
		if (string != null) {
			for (SkillType skillType : values()) {
				if (string.equalsIgnoreCase(skillType.toString())) {
					return skillType;
				}
			}
		}
		return null;
	}

}
