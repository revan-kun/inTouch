package com.epam.lab.intouch.service.history;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.history.project.DefaultHistoryDAO;
import com.epam.lab.intouch.dao.history.project.HistoryDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public class HistoryService {

	private HistoryDAO historyDAO;

	public HistoryService() {
		setHistoryDAO(new DefaultHistoryDAO());
	}

	public String create(Member member) throws DAOException {
		String login = historyDAO.create(member);

		return login;
	}

	public Member getById(String login) throws DAOException {
		Member member = historyDAO.getById(login);

		return member;
	}

	public void update(Member oldMember, Member newMember) throws DAOException {

		throw new UnsupportedOperationException("You can't update history");
	}

	public void delete(Member member) throws DAOException {

		historyDAO.delete(member);

	}

	public List<Member> getAll() throws DAOException {
		List<Member> members = (List<Member>) historyDAO.getAll();

		return members;
	}

	public Long addProject(Member member, Project project) throws DAOException {

		Long idProject = historyDAO.addProject(member, project);
		return idProject;
	}

	public List<Member> getAllFromSearch(String query) throws DAOException {

		List<Member> members = (List<Member>) historyDAO.getAllFromSearch(query);

		return members;
	}

	public HistoryDAO getHistoryDAO() {
		return historyDAO;
	}

	public void setHistoryDAO(HistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}
}
