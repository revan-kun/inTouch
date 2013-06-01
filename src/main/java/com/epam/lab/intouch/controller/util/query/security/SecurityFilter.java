package com.epam.lab.intouch.controller.util.query.security;

import java.util.regex.Pattern;

/**
 * This class exists for protection from sql injections and can be extended for any other security reasons
 * 
 * @author Zatorsky D.B
 * 
 */
public class SecurityFilter {

	/**
	 * This method replaces every single or double quotes with whitespace
	 * 
	 * @param unsave
	 *            string, that can potentially include unsafe symbols
	 * @return safe string, that can be used in sql statement
	 */
	public String replaceUnsafeSymbols(String unsave) {
		String safe = null;

		if (unsave != null) {
			Pattern p = Pattern.compile("[\"\']");
			safe = p.matcher(unsave).replaceAll(" ");
		}
		return safe;
	}
}
