package com.epam.lab.intouch.web.util;

import java.util.Random;

public class AttributeForgotPasword {
	private static final String RANDOM_SELECT = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String randomString() {
		Random rnd = new Random();
		int length  = 10;
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(RANDOM_SELECT.charAt(rnd.nextInt(RANDOM_SELECT.length())));
		return sb.toString();
	}
}
