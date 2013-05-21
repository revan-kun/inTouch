package com.epam.lab.intouch.service.team;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public interface BaseTeamService {

	Long create(Project project) throws DAOException;

	Project getById(Long id) throws DAOException;

	void update(Project oldProject, Project newProject) throws DAOException;

	void delete(Project project) throws DAOException;

	List<Project> getAll() throws DAOException;

	String addMember(Project project, Member member) throws DAOException;

	void removeMember(Project project, Member member) throws DAOException;

	List<Project> getAllFromSearch(String query) throws DAOException;
	
	Member getActiveProjects(String login) throws  DAOException;
	
	java.util.Date getEnterDate(Member member, Project project) throws DAOException;
	
	

}
