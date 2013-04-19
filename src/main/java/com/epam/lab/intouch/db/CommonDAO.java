package com.epam.lab.intouch.db;

import java.sql.SQLException;
import java.util.Map;

/**
 * Common DataAccessObject interface
 * 
 * @author Revan
 *
 * @param <Type>
 */
public interface CommonDAO<Type> {
	
	Map<Integer, Type> readAll() throws SQLException;

	void create(Type bean) throws SQLException;
	
	Type read(int beanId) throws SQLException; 

	void update(Type bean, int beanId) throws SQLException;
	
	void delete(int beanId) throws SQLException;
	
}
