package com.epam.lab.intouch.service.history;

import java.util.LinkedList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.history.project.DefaultHistoryDAO;
import com.epam.lab.intouch.dao.history.project.HistoryDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.service.member.MemberService;

public class HistoryService implements BaseHistoryService {

	private final HistoryDAO historyDAO;

	public HistoryService() {
		historyDAO = new DefaultHistoryDAO();
	}

	@Override
	public String create(Member member) throws DAOException {
		
		String login = historyDAO.create(member);

		return login;
	}

	@Override
	public Member getById(String login) throws DAOException {
		
		Member member = historyDAO.getById(login);

		return member;
	}

	@Override
	public void update(Member oldMember, Member newMember) throws DAOException {

		throw new UnsupportedOperationException("You can't update history");
	}

	@Override
	public void delete(Member member) throws DAOException {

		historyDAO.delete(member);

	}

	@Override
	public List<Member> getAll() throws DAOException {
		
		List<Member> members = (List<Member>) historyDAO.getAll();

		return members;
	}

	@Override
	public Long addProject(Member member, Project project) throws DAOException {

		Long idProject = historyDAO.addProject(member, project);
		
		return idProject;
	}

	@Override
	public List<Member> getAllFromSearch(String query) throws DAOException {

		List<Member> members = (List<Member>) historyDAO.getAllFromSearch(query);

		return members;
	}
	
	@Override
	public List<Member> getProjectHistory(Project project) throws DAOException {	
		
		List<Member> members = new LinkedList<Member>();
		for(Member member : historyDAO.getProjectHistory(project)) {
			members.add(new MemberService().getById(member.getLogin()));
		}		
		
		return members;
	}

}
