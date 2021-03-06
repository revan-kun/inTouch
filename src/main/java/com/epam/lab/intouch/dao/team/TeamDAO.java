package com.epam.lab.intouch.dao.team;

import java.util.Date;

import com.epam.lab.intouch.dao.BaseDAO;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public interface TeamDAO extends BaseDAO<Project, Long> {

	void removeMember(Project project, Member member) throws DAOException;

	String addMember(Project project, Member member) throws DAOException;
	
	Member getActiveProjects(String login) throws DAOException;
	
	Date getEnterDate(Member member, Project project) throws DAOException;

}
