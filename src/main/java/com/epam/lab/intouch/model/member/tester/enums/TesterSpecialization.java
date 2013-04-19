package com.epam.lab.intouch.model.member.tester.enums;

public enum TesterSpecialization {
	MANUAL, AUTOMATION, QUALITY_CONTROL, QUALITY_ASSURANCE;

	public static TesterSpecialization fromString(final String string) {
		if (string != null) {
			for (TesterSpecialization spec : values()) {
				if (string.equalsIgnoreCase(spec.toString())) {
					return spec;
				}
			}
		}
		return null;
	}
}
