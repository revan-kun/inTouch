package com.epam.lab.intouch.service.team;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.team.DefaultTeamDAO;
import com.epam.lab.intouch.dao.team.TeamDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public class TeamService {

	private TeamDAO teamDAO;

	public TeamService() {
		setTeamDAO(new DefaultTeamDAO());

	}

	public Long create(Project project) throws DAOException {
		Long idProject = teamDAO.create(project);

		return idProject;

	}

	public Project getById(Long id) throws DAOException {

		Project project = teamDAO.getById(id);
		return project;
	}

	public void update(Project oldProject, Project newProject) throws DAOException {

		throw new UnsupportedOperationException("You can't update team");

	}

	public void delete(Project project) throws DAOException {
		teamDAO.delete(project);
	}

	public List<Project> getAll() throws DAOException {
		List<Project> projects = (List<Project>) teamDAO.getAll();

		return projects;
	}

	public String addMember(Project project, Member member) throws DAOException {
		String login = teamDAO.addMember(project, member);

		return login;
	}

	public void removeMember(Project project, Member member) throws DAOException {
		teamDAO.removeMember(project, member);

	}

	public List<Project> getAllFromSearch(String query) throws DAOException {

		return null;
	}

	public TeamDAO getTeamDAO() {
		return teamDAO;
	}

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

}
