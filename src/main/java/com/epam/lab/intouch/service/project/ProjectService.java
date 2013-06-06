package com.epam.lab.intouch.service.project;

import java.util.LinkedList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.factory.AbstractDAOFactory;
import com.epam.lab.intouch.dao.member.MemberDAO;
import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.team.TeamDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

/**
 * ProjectService class combine different method from DAO class to get object
 * 
 * @author Iryna
 * 
 */
public class ProjectService implements BaseProjectService {

	private final MemberDAO memberDAO;
	private final ProjectDAO projectDAO;
	private final TeamDAO teamDAO;

	/**
	 * Initialization required DAO classes for Project Service
	 */
	public ProjectService() {
		AbstractDAOFactory factory = AbstractDAOFactory
				.getInstance(AbstractDAOFactory.MS_SERVER);
		memberDAO = factory.getMemberDAO();
		projectDAO = factory.getProjectDAO();
		teamDAO = factory.getTeamDAO();

	}

	/**
	 * Method for creating a project
	 * 
	 * @param project
	 * @return id of the created project
	 * @throws DAOException
	 */
	@Override
	public Long create(Project project) throws DAOException {

		projectDAO.create(project);
		return project.getId();

	}

	/**
	 * Method for getting a fully populated project by it's id
	 * 
	 * @param id
	 * @return a fully populated project with members according to the project
	 *         id
	 * @throws DAOException
	 */
	@Override
	public Project getById(Long id) throws DAOException {

		Project project = teamDAO.getById(id);
		Project fullProject = projectDAO.getById(id);

		if (fullProject != null) {
			List<Member> members = project.getMembers();
			List<Member> fullMembers = new LinkedList<Member>();

			for (Member member : members) {
				Member fullMember = memberDAO.getById(member.getLogin());
				fullMembers.add(fullMember);
			}

			fullProject.setMembers(fullMembers);
		}

		return fullProject;
	}

	/**
	 * Method for updating project information from oldproject to newproject
	 * 
	 * @param oldProject
	 * @param newProject
	 * @throws DAOException
	 */
	@Override
	public void update(Project oldProject, Project newProject)
			throws DAOException {

		projectDAO.update(oldProject, newProject);

	}

	/**
	 * Method for deleting a project
	 * 
	 * @param project
	 * @throws DAOException
	 */
	@Override
	public void delete(Project project) throws DAOException {

		projectDAO.delete(project);

	}

	/**
	 * Method for populating projects with all required information
	 * 
	 * @param projects
	 * @return list of projects with all required information
	 * @throws DAOException
	 */
	private List<Project> getFullProjects(List<Project> projects)
			throws DAOException {

		List<Project> fullProjects = new LinkedList<Project>();

		for (Project project : projects) {
			fullProjects.add(getById(project.getId()));
		}

		return fullProjects;
	}

	/**
	 * Method for getting all projects populated with all required information
	 * 
	 * @return list of all projects with all required information
	 * @throws DAOException
	 */
	@Override
	public List<Project> getAll() throws DAOException {

		List<Project> projects = (List<Project>) projectDAO.getAll();
		List<Project> fullProjects = getFullProjects(projects);

		return fullProjects;
	}

	/**
	 * Method for getting projects populated with all required information based
	 * on a search query
	 * 
	 * @param query
	 * @return list of projects with all required information that match the
	 *         search query
	 * @throws DAOException
	 */
	@Override
	public List<Project> getAllFromSearch(String query) throws DAOException {

		List<Project> projects = (List<Project>) projectDAO
				.getAllFromSearch(query);
		List<Project> fullProjects = getFullProjects(projects);

		return fullProjects;
	}

	/**
	 * Method for getting basic project information by it's id
	 * 
	 * @param id
	 * @return basic project information according to the project id
	 * @throws DAOException
	 */
	@Override
	public Project getSimpleProject(Long id) throws DAOException {

		Project project = projectDAO.getById(id);

		return project;
	}

}
