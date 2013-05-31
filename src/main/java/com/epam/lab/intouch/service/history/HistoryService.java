package com.epam.lab.intouch.service.history;

import java.util.LinkedList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.history.project.DefaultHistoryDAO;
import com.epam.lab.intouch.dao.history.project.HistoryDAO;
import com.epam.lab.intouch.dao.team.DefaultTeamDAO;
import com.epam.lab.intouch.dao.team.TeamDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.service.member.MemberService;

/**
 * HistoryService class combine different method from DAO class to get object
 * 
 * @author Ірина
 * 
 */
public class HistoryService implements BaseHistoryService {

	private final HistoryDAO historyDAO;
	private final TeamDAO teamDAO;

	/**
	 * Initialization required DAO classes for History Service
	 */
	public HistoryService() {
		historyDAO = new DefaultHistoryDAO();
		teamDAO = new DefaultTeamDAO();
	}

	/**
	 * Method for creating history records based on a member and his projects
	 * 
	 * @param member
	 * @return member login
	 * @throws DAOException
	 */
	@Override
	public String create(Member member) throws DAOException {

		String login = historyDAO.create(member);

		return login;
	}

	/**
	 * Method for getting member with filled history by member login
	 * 
	 * @param login
	 * @return member with filled history
	 * @throws DAOException
	 */
	@Override
	public Member getById(String login) throws DAOException {

		Member member = historyDAO.getById(login);

		return member;
	}

	@Override
	public void update(Member oldMember, Member newMember) throws DAOException {

		throw new UnsupportedOperationException("You can't update history");
	}

	/**
	 * Method for deleting history of member
	 * 
	 * @param member
	 * @throws DAOException
	 */
	@Override
	public void delete(Member member) throws DAOException {

		historyDAO.delete(member);

	}

	/**
	 * Method for getting all history related to members
	 * 
	 * @return list of all members with filled history
	 * @throws DAOException
	 */
	@Override
	public List<Member> getAll() throws DAOException {

		List<Member> members = (List<Member>) historyDAO.getAll();

		return members;
	}

	/**
	 * Method for adding a project to member's history
	 * 
	 * @param member
	 * @param project
	 * @return id of added project
	 * @throws DAOException
	 */
	@Override
	public Long addProject(Member member, Project project) throws DAOException {

		java.util.Date enterDate = teamDAO.getEnterDate(member, project);
		Long idProject = historyDAO.addProject(member, project, enterDate);

		return idProject;
	}

	/**
	 * Method for getting all history according to the search query
	 * 
	 * @param query
	 * @return list of members with history matching the search query
	 * @throws DAOException
	 */
	@Override
	public List<Member> getAllFromSearch(String query) throws DAOException {

		List<Member> members = (List<Member>) historyDAO.getAllFromSearch(query);

		return members;
	}

	/**
	 * Method for getting all history associated with the project
	 * 
	 * @param project
	 * @return list of members associated with the project's history
	 * @throws DAOException
	 */
	@Override
	public List<Member> getProjectHistory(Project project) throws DAOException {

		List<Member> members = new LinkedList<Member>();
		for (Member member : historyDAO.getProjectHistory(project)) {
			members.add(new MemberService().getById(member.getLogin()));
		}

		return members;
	}

}
