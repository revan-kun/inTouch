package com.epam.lab.intouch.service.member;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public interface BaseMemberService {

	String create(Member member) throws DAOException;

	Member getById(String login) throws DAOException;

	void update(Member oldMember, Member newMember) throws DAOException;

	void delete(Member member) throws DAOException;

	Member memberWithActiveProjectId(String login) throws DAOException;

	Member memberWithActiveProjectInfo(String login) throws DAOException;

	Member memberWithFullActiveProject(String login) throws DAOException;

	List<Member> getAll() throws DAOException;

	List<Member> getAllFromSearch(String query) throws DAOException;

	void updateRating(Member member) throws DAOException;

	List<Project> getMemberProjectsHistory(String login) throws DAOException;

}
