package com.epam.lab.intouch.web.util;

import java.util.Random;

/**
 * AttributeForgotPasword contain attribute for forgot password sending
 * 
 * @author Iryna
 *
 */
public class AttributeForgotPasword {
	
	private static final String RANDOM_SELECT = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * Method for getting random generated password
	 * 
	 * @return sb.toString()
	 */
	public static final String randomString() {
		Random rnd = new Random();
		int length  = 10;
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(RANDOM_SELECT.charAt(rnd.nextInt(RANDOM_SELECT.length())));
		return sb.toString();
	}
}
