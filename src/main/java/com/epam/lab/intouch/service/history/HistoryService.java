package com.epam.lab.intouch.service.history;



import static com.epam.lab.intouch.dao.util.FieldName.MEMBER_ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAODeleteException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public class HistoryService {
	
	public String create(Member member) throws DAOCreateException {


		return null;
	}

	public Member getById(String login) throws DAOReadException {



		return null;
	}


	public void update(Member oldMember, Member newMember)  throws DAOException{
		
		throw new UnsupportedOperationException("You can't update history");
	}

	
	public void delete(Member member) throws DAODeleteException {
		


	}


	public List<Member> getAll() throws DAOException {


		return null;
	}


	public Long addProject(Member member, Project project) throws DAOCreateException {


		return null;
	}

	
	private PreparedStatement prStatementMemberID(Connection connection, String query, String parametr) throws SQLException{
		

		
		return null;
	}
	
	private List<Project> getMemberHistory(Connection connection, String query, String login) throws SQLException{
		

		return null;
		
	}
	
	public List<Member> getAllFromSearch(String query) throws DAOReadException {
		


		return null;
	}
}
