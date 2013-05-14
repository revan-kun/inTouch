package com.epam.lab.intouch.service.project;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.project.Project;

public interface BaseProjectService {

	Long create(Project project) throws DAOException;

	Project getById(Long id) throws DAOException;

	void update(Project oldProject, Project newProject) throws DAOException;

	void delete(Project project) throws DAOException;

	List<Project> getAll() throws DAOException;

	List<Project> getAllFromSearch(String query) throws DAOException;
	
	public Project getSimpleProject(Long id) throws DAOException;
	

}
