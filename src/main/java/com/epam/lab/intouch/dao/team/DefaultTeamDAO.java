package com.epam.lab.intouch.dao.team;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.PersistenceException;
import com.epam.lab.intouch.model.project.Project;

public class DefaultTeamDAO extends AbstractBaseDAO<Project, Long> implements
		ProjectTeamDAO {
	private final static Logger LOG = Logger.getLogger(DefaultTeamDAO.class);

	@Override
	public Long create(Project project) throws PersistenceException {
		return null;

	}

	@Override
	public Project getById(Long id) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Project oldProject, Project newProject)
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
