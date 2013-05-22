package com.epam.lab.intouch.web.notifier.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.dao.member.DefaultMemberDAO;

public class PropertiesReader {

	private final static Logger LOG = LogManager.getLogger(DefaultMemberDAO.class);

	private static Properties property = new Properties();

	public static String getProperty(String path, String key) {
		try {

			property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));

		} catch (IOException ex) {
			LOG.error("Cannot open properies for mail sender", ex);

		}
		return property.getProperty(key);
	}
}
