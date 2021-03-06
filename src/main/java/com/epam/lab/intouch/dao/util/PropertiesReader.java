package com.epam.lab.intouch.dao.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesReader {
	
	private static final String RES_CONNECTION_TO_DB_INFO_PROPERTIES = "DBConnection.properties";
	private final static Logger LOG = LogManager.getLogger(PropertiesReader.class);
	static Properties property = new Properties();

	public static String getProperty(String key) {
		try {
		
			property.load(Thread.currentThread().getContextClassLoader()
		     .getResourceAsStream(RES_CONNECTION_TO_DB_INFO_PROPERTIES));
		//	property.load(new FileInputStream(RES_CONNECTION_TO_DB_INFO_PROPERTIES));
		} catch (IOException ex) {
			LOG.error("Cannot open properies to connect DB", ex);

		}
		return property.getProperty(key);
	}

}
