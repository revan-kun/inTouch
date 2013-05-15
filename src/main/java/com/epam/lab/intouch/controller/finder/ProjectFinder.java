package com.epam.lab.intouch.controller.finder;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.service.project.BaseProjectService;
import com.epam.lab.intouch.service.project.ProjectService;

public class ProjectFinder {
	private final static Logger LOG = LogManager.getLogger(ProjectFinder.class);

	public List<Project> gerAllProjects() throws DataAccessingException {
		BaseProjectService service = new ProjectService();
		List<Project> projects = new ArrayList<Project>();

		try {
			projects = service.getAll();
		} catch (DAOException e) {
			LOG.error("Data cannot be accessed!: ", e);
			throw new DataAccessingException(e);
		}
		return projects;
	}

	public List<Project> findProjects(String query) throws DataAccessingException {
		BaseProjectService service = new ProjectService();
		List<Project> projects = new ArrayList<Project>();

		try {
			projects = service.getAllFromSearch(query);
		} catch (DAOException e) {
			LOG.error("Data cannot be accessed!: ", e);
			throw new DataAccessingException(e);
		}
		return projects;
	}
}
