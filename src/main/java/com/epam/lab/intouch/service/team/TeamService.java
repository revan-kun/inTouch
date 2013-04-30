package com.epam.lab.intouch.service.team;

import static com.epam.lab.intouch.dao.util.FieldName.PROJECT_ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAODeleteException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public class TeamService {

	
	public Long create(Project project) throws DAOCreateException {


		return null;

	}


	public Project getById(Long id) throws DAOReadException {
		

		return null;
	}


	public void update(Project oldProject, Project newProject) throws DAOException {
		
		throw new UnsupportedOperationException("You can't update team");
		
	}


	public void delete(Project project) throws DAODeleteException {

	}


	public List<Project> getAll() throws DAOReadException {



		return null;
	}


	public String addMember(Project project, Member member) throws DAOCreateException {


		return null;
	}


	public void removeMember(Project project, Member member) throws DAODeleteException {



	}
	
	public List<Project> getAllFromSearch(String query) throws DAOException {
		

		return null;
	}

}
