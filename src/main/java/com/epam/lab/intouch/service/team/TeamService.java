package com.epam.lab.intouch.service.team;

import java.util.Date;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.team.DefaultTeamDAO;
import com.epam.lab.intouch.dao.team.TeamDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

/**
 * TeamService class combine different method from DAO class to get object
 * 
 * @author Ірина
 * 
 */
public class TeamService implements BaseTeamService {

	private final TeamDAO teamDAO;

	/**
	 * Initialization required DAO classes for Skill Service
	 */
	public TeamService() {

		teamDAO = new DefaultTeamDAO();

	}

	/**
	 * Method for creating a team based on the project and it's members
	 * 
	 * @param project
	 * @return id of the project
	 * @throws DAOException
	 */
	@Override
	public Long create(Project project) throws DAOException {

		Long idProject = teamDAO.create(project);

		return idProject;

	}

	/**
	 * Method for getting a team consisting of a project with it's member ids by
	 * project id
	 * 
	 * @param id
	 * @return project with it's members
	 * @throws DAOException
	 */
	@Override
	public Project getById(Long id) throws DAOException {

		Project project = teamDAO.getById(id);

		return project;
	}

	@Override
	public void update(Project oldProject, Project newProject) throws DAOException {

		throw new UnsupportedOperationException("You can't update team");

	}

	/**
	 * Method for deleting a team bound to a project
	 * 
	 * @param project
	 * @throws DAOException
	 */
	@Override
	public void delete(Project project) throws DAOException {

		teamDAO.delete(project);
	}

	/**
	 * Method for getting all teams based on projects
	 * 
	 * @return list of all teams consisting of projects and their members
	 * @throws DAOException
	 */
	@Override
	public List<Project> getAll() throws DAOException {

		List<Project> projects = (List<Project>) teamDAO.getAll();

		return projects;
	}

	/**
	 * Method for adding a member to a team bound to a project
	 * 
	 * @param project
	 * @param member
	 * @return member login
	 * @throws DAOException
	 */
	@Override
	public String addMember(Project project, Member member) throws DAOException {

		String login = teamDAO.addMember(project, member);

		return login;
	}

	/**
	 * Method for removing a member from a team bound to a project
	 * 
	 * @param project
	 * @param member
	 * @throws DAOException
	 */
	@Override
	public void removeMember(Project project, Member member) throws DAOException {

		teamDAO.removeMember(project, member);

	}

	/**
	 * Method for getting teams based projects with their member ids according
	 * to the search query
	 * 
	 * @param query
	 * @return list of projects with their member ids according to the search
	 *         query
	 * @throws DAOException
	 */
	@Override
	public List<Project> getAllFromSearch(String query) throws DAOException {

		List<Project> projects = (List<Project>) teamDAO.getAllFromSearch(query);

		return projects;
	}

	/**
	 * Method for getting active projects for a member based on his login
	 * 
	 * @param login
	 * @return member with active projects
	 * @throws DAOException
	 */
	@Override
	public Member getActiveProjects(String login) throws DAOException {

		Member member = teamDAO.getActiveProjects(login);

		return member;
	}

	/**
	 * Method for getting the date on which a member joined a team bound to a
	 * project
	 * 
	 * @param member
	 * @param project
	 * @return date on which a member joined a team bound to a project
	 * @throws DAOException
	 */
	@Override
	public Date getEnterDate(Member member, Project project) throws DAOException {

		java.util.Date date = teamDAO.getEnterDate(member, project);

		return date;
	}

}
