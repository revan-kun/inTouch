package com.epam.lab.intouch.controller.util.query.security;

import java.util.regex.Pattern;

public class SecurityFilter {
	public String replaceUnsafeSymbols(String unsave) {
		String safe = null;

		if (unsave != null) {
			Pattern p = Pattern.compile("[\"\']");
			safe = p.matcher(unsave).replaceAll(" ");
		}
		return safe;
	}
}
