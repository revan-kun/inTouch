package com.epam.lab.intouch.service.history;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public interface BaseHistoryService {

	String create(Member member) throws DAOException;

	Member getById(String login) throws DAOException;

	void update(Member oldMember, Member newMember) throws DAOException;

	void delete(Member member) throws DAOException;

	List<Member> getAll() throws DAOException;

	Long addProject(Member member, Project project) throws DAOException;

	List<Member> getAllFromSearch(String query) throws DAOException;
	
	List<Member> getProjectHistory(Project project) throws  DAOException;	

}
