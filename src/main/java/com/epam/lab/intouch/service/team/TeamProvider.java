package com.epam.lab.intouch.service.team;

import java.util.Collection;


import com.epam.lab.intouch.dao.team.TeamDAO;
import com.epam.lab.intouch.dao.team.TeamDAOImpl;
import com.epam.lab.intouch.model.project.Project;


public class TeamProvider {
	
	private TeamDAO teamDAO;
	
	public TeamProvider(){
		setTeamDAO(new TeamDAOImpl());
	}
	
	private void createTeam(Project member){
		// TODO Auto-generated method stub
	}
	
	public Project getByIdTeam(String id){
		// TODO Auto-generated method stub
		return null;
	}

	
	public void updateTeam(Project oldEntity, Project newEntity){
		// TODO Auto-generated method stub

	}

	
	public void deleteTeam(Project member){
		// TODO Auto-generated method stub
	}

	
	public Collection<Project> getAllTeam(){
		// TODO Auto-generated method stub
		return null;
	}

	public TeamDAO getTeamDAO() {
		return teamDAO;
	}

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

}
