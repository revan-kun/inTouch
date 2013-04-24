package com.epam.lab.intouch.dao;

import java.util.Collection;

import com.epam.lab.intouch.dao.exception.PersistenceException;

public interface BaseDAO<E, I> {

	I create(E entity) throws PersistenceException;

	E getById(I id) throws PersistenceException;

	void update(E oldEntity, E newEntity) throws PersistenceException;

	void delete(E entity) throws PersistenceException;

	Collection<E> getAll() throws PersistenceException;

}
