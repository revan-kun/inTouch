package com.epam.lab.intouch.service.project;

import java.util.Collection;


import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.project.ProjectDAOImpl;
import com.epam.lab.intouch.model.project.Project;

public class ProjectProvider {
	
	private ProjectDAO projectDAO;
	
	public ProjectProvider(){
		setProjectDAO(new ProjectDAOImpl());
	}
	
	
	
	public void create(Project project){
		// TODO Auto-generated method stub

	}

	
	public Project getById(Long id){
		// TODO Auto-generated method stub
		return null;
	}

	
	public void update(Project oldEntity, Project newEntity) {
		// TODO Auto-generated method stub

	}


	public void delete(Project project){
		// TODO Auto-generated method stub

	}

	
	public Collection<Project> getAll(){
		// TODO Auto-generated method stub
		return null;
	}



	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}



	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}


}
