package com.epam.lab.intouch.controller.util.query.security;

import java.util.regex.Pattern;

public class SecurityFilter {
	public String replaceUnsafeSymbols(String unsave) {
		Pattern p = Pattern.compile("[%\"\']");
		return p.matcher(unsave).replaceAll("");
	}
}
