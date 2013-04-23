package com.epam.lab.intouch.service.project;

import java.util.Collection;


import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.project.ProjectDAOImpl;
import com.epam.lab.intouch.dao.team.TeamDAO;
import com.epam.lab.intouch.dao.team.TeamDAOImpl;
import com.epam.lab.intouch.model.project.Project;

public class ProjectService {
	
	private ProjectDAO projectDAO;
	private TeamDAO teamDAO;
	
	public ProjectService(){
		projectDAO = new ProjectDAOImpl();
		teamDAO = new TeamDAOImpl();
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



	public TeamDAO getTeamDAO() {
		return teamDAO;
	}



	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}


}
