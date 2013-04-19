package com.epam.lab.intouch.model.project.enums;

public enum ProjectStatus {
	OPEN, CLOSED, FROZEN, ABANDONED;

	public static ProjectStatus fromString(final String string) {
		if (string != null) {
			for (ProjectStatus status : values()) {
				if (string.equalsIgnoreCase(status.toString())) {
					return status;
				}
			}
		}
		return null;
	}
}
