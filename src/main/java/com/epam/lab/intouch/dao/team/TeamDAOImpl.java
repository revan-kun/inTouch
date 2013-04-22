package com.epam.lab.intouch.dao.team;

import java.util.Collection;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.PersistenceException;
import com.epam.lab.intouch.model.project.Project;

public class TeamDAOImpl extends AbstractBaseDAO<Project, Long> implements
		TeamDAO {

	@Override
	public void create(Project project) throws PersistenceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Project getById(Long id) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Project oldEntity, Project newEntity)
			throws PersistenceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Project project) throws PersistenceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Project> getAll() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}
