package com.epam.lab.intouch.controller.project;

import java.util.Collection;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.service.project.ProjectService;

public class ProjectController {
	private ProjectService controller;

	public ProjectController() {
		controller = new ProjectService();
	}

	public ProjectService getController() {
		return controller;
	}

	public void setController(ProjectService controller) {
		this.controller = controller;
	}

	public void create(Project project) throws DAOException {
		controller.create(project);
	}

	public Project getById(Long id) throws DAOException {
		return controller.getById(id);
	}

	public void update(Project oldProject, Project newProject) throws DAOException {
		controller.update(oldProject, newProject);
	}

	public void delete(Project project) throws DAOException {
		controller.delete(project);
	}

	public Collection<Project> getAll() throws DAOException {
		return controller.getAll();
	}
}
