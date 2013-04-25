package com.epam.lab.intouch.dao;

import java.util.Collection;

import com.epam.lab.intouch.dao.exception.DAOException;

public interface BaseDAO<E, I> {

	I create(E entity) throws DAOException;

	E getById(I id) throws DAOException;

	void update(E oldEntity, E newEntity) throws DAOException;

	void delete(E entity) throws DAOException;

	Collection<E> getAll() throws DAOException;

}
