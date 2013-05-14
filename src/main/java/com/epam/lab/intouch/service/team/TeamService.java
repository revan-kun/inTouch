package com.epam.lab.intouch.service.team;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.team.DefaultTeamDAO;
import com.epam.lab.intouch.dao.team.TeamDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public class TeamService implements BaseTeamService {

	private final TeamDAO teamDAO;

	public TeamService() {
		
		teamDAO = new DefaultTeamDAO();

	}

	@Override
	public Long create(Project project) throws DAOException {
		
		Long idProject = teamDAO.create(project);

		return idProject;

	}

	@Override
	public Project getById(Long id) throws DAOException {

		Project project = teamDAO.getById(id);
		
		return project;
	}

	@Override
	public void update(Project oldProject, Project newProject) throws DAOException {

		throw new UnsupportedOperationException("You can't update team");

	}

	@Override
	public void delete(Project project) throws DAOException {
		
		teamDAO.delete(project);
	}

	@Override
	public List<Project> getAll() throws DAOException {
		
		List<Project> projects = (List<Project>) teamDAO.getAll();

		return projects;
	}

	@Override
	public String addMember(Project project, Member member) throws DAOException {
		
		String login = teamDAO.addMember(project, member);

		return login;
	}

	@Override
	public void removeMember(Project project, Member member) throws DAOException {
		
		teamDAO.removeMember(project, member);

	}

	@Override
	public List<Project> getAllFromSearch(String query) throws DAOException {
		
		List<Project> projects = (List<Project>) teamDAO.getAllFromSearch(query);
		
		return projects;
	}

	@Override
	public Member getActiveProjects(String login) throws DAOException {
		
		Member member = teamDAO.getActiveProjects(login);

		return member;
	}

}
