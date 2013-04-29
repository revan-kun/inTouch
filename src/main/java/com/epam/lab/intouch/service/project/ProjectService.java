package com.epam.lab.intouch.service.project;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAODeleteException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.exception.DAOUpdateException;
import com.epam.lab.intouch.model.project.Project;

public class ProjectService {

	
	public ProjectService() {
	
	}


	public Long create(Project project) throws DAOCreateException {


		return null;

	}


	public Project getById(Long id) throws DAOReadException {
		

		return null;
	}


	public void update(Project oldProject, Project newProject) throws DAOUpdateException {

		
	}

	
	public void delete(Project project) throws DAODeleteException {
		

	}

	
	public List<Project> getAll() throws DAOReadException {
		
		

		return null;
	}
	

}
