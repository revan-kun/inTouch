package com.epam.lab.intouch.controller.project;

import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.team.DefaultTeamDAO;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.service.project.ProjectService;

public class ProjectController {
	private final static Logger LOG = LogManager.getLogger(ProjectController.class);

	private ProjectService projectService;

	public ProjectController() {
		projectService = new ProjectService();
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public Project getProjectInfo(Long projectID) throws DataAccessingException {
		
		Project resultProject = new Project();
		resultProject.setId(projectID);
		
		return getProjectInfo(resultProject);
	}

	public Project getProjectInfo(Project project) throws DataAccessingException {
		Project resultProject = null;

		if (project != null && project.getId() != null) {
			try {
				resultProject = projectService.getById(project.getId());
			} catch (DAOException e) {
				LOG.error("Cannot access data: ", e);
				throw new DataAccessingException(e);
			}
		}

		return resultProject;
	}

	public List<Project> getAll() throws DAOException {
		return projectService.getAll();
	}
}
