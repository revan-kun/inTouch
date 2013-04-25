package com.epam.lab.intouch.service.project;

import java.util.Collection;


import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.project.DefaultProjectDAO;
import com.epam.lab.intouch.dao.team.ProjectTeamDAO;
import com.epam.lab.intouch.dao.team.DefaultProjectTeamDAO;
import com.epam.lab.intouch.model.project.Project;

public class ProjectService {
	
	private ProjectDAO projectDAO;
	private ProjectTeamDAO teamDAO;
	
	public ProjectService(){
		projectDAO = new DefaultProjectDAO();
		teamDAO = new DefaultProjectTeamDAO();
	}
	
	
	
	public String create(Project project){
		// TODO Auto-generated method stub
		return null;
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



	public ProjectTeamDAO getTeamDAO() {
		return teamDAO;
	}



	public void setTeamDAO(ProjectTeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}


}
