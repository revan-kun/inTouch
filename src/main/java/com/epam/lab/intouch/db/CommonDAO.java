package com.epam.lab.intouch.db;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import com.epam.lab.task.exception.DAOException;

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
	
	/*void create(E entity) throws DAOException;

	E getById(V id) throws DAOException;

	void update(E oldEntity, E newEntity) throws DAOException;

	void delete(E entity) throws DAOException;

	Collection<E> getAll() throws DAOException;*/
	
}
