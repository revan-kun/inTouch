package com.epam.lab.intouch.service.project;

import java.util.Collection;

import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.project.ProjectDAOImpl;
import com.epam.lab.intouch.model.project.Project;

public class ProjectService {

	private ProjectDAO projectDAO;

	public ProjectService() {
		setProjectDAO(new ProjectDAOImpl());
	}

	public void create(Project project) {

	}

	public Project getById(Long id) {
		return null;
	}

	public void update(Project oldEntity, Project newEntity) {

	}

	public void delete(Project project) {

	}

	public Collection<Project> getAll() {
		return null;
	}

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

}
