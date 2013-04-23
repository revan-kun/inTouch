package com.epam.lab.intouch.controller.project;

import java.util.Collection;

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

	public void create(Project project) {
		controller.create(project);
	}

	public Project getById(Long id) {
		return controller.getById(id);
	}

	public void update(Project oldProject, Project newProject) {
		controller.update(oldProject, newProject);
	}

	public void delete(Project project) {
		controller.delete(project);
	}

	public Collection<Project> getAll() {
		return controller.getAll();
	}
}
