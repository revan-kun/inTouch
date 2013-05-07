package com.epam.lab.intouch.controller.project;

import java.util.Collection;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.service.project.ProjectService;

//Obsolete version. It's better to use common.MemberController or ManagerController instead of it. 
//I will delete this controller when everybody has actual version of repository
@Deprecated
public class ProjectController {
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

	public void create(Project project) throws DAOException {
		projectService.create(project);
	}

	public Project getById(Long id) throws DAOException {
		return projectService.getById(id);
	}

	public void update(Project oldProject, Project newProject) throws DAOException {
		projectService.update(oldProject, newProject);
	}

	public void delete(Project project) throws DAOException {
		projectService.delete(project);
	}

	public Collection<Project> getAll() throws DAOException {
		return projectService.getAll();
	}
}
