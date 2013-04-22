package com.epam.lab.intouch.db.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesReader {
	private final static Logger LOG = Logger.getLogger(PropertiesReader.class);
	static Properties property = new Properties();

	public static String getProperty(String key) {
		try {
			property.load(new FileInputStream("res/connection_to_DB_info.properties"));
		} catch (IOException ex) {
			LOG.error("Cannot open properies to connect DB", ex);

		}
		return property.getProperty(key);
	}

}
