package com.epam.lab.intouch.dao.team;

import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.project.Project;

public class DefaultProjectTeamDAO extends AbstractBaseDAO<Project, Long> implements
		ProjectTeamDAO {
	
	private final static Logger LOG = LogManager.getLogger(DefaultProjectTeamDAO.class);

	@Override
	public Long create(Project project) throws DAOException {
		return null;

	}

	@Override
	public Project getById(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Project oldProject, Project newProject)
			throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Project project) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Project> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMemberID(Long idProject) {
		// TODO Auto-generated method stub
		return null;
	}

}
