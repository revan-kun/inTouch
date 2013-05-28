package com.epam.lab.intouch.dao.history.project;

import static com.epam.lab.intouch.util.db.metadata.FieldName.*;
import static com.epam.lab.intouch.util.db.metadata.FieldName.PROJECT_ID;
import static com.epam.lab.intouch.util.db.metadata.TableName.PROJECT_HISTORY;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAODeleteException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

/**
 * Class for manipulation data in Project_History table
 * @author Molodec
 *
 */
public class DefaultHistoryDAO extends AbstractBaseDAO<Member, String> implements HistoryDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultHistoryDAO.class);
	
	/**
	 * Method create new record in Project_History table DB
	 * @param member
	 * @return login 
	 * 
	 * @throws DAOCreateException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * 
	 * @see com.epam.lab.intouch.dao.BaseDAO#create(java.lang.Object)
	 */
	@Override
	public String create(Member member) throws DAOCreateException {
		
		StringBuilder queryInsert = new StringBuilder();
		queryInsert.append("INSERT INTO ").append(PROJECT_HISTORY).append(" (");
		queryInsert.append(MEMBER_ID).append(", ").append(PROJECT_ID).append(", ").append(LEAVING_DATE).append(", ").append(LEAVING_TIME).append(") ");
		queryInsert.append("VALUES(?,?,?,?)");
		
		List<Project> historyProjects = member.getHistoryProjects();
		String login = null;

		try (Connection connection = getConnection(); 
			PreparedStatement statementCreate = connection.prepareStatement(queryInsert.toString())) {
			login = member.getLogin();
			for (Project project : historyProjects) {
				statementCreate.setString(1, login);
				statementCreate.setLong(2, project.getId());
				statementCreate.setDate(3, new Date(new java.util.Date().getTime()));
				statementCreate.setTime(4, new Time(new java.util.Date().getTime()));
			
			}
			statementCreate.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with create history", e);
			throw new DAOCreateException("Problew with create history" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}

		return login;
	}

	/**
	 * This method for get member with his history project from DB by ID.
	 * 
	 * @param login
	 * @return Member
	 * @throws DAOReadException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * 
	 * @see com.epam.lab.intouch.dao.BaseDAO#getById(java.lang.Object)
	 */
	@Override
	public Member getById(String login) throws DAOReadException {

		StringBuilder queryReadById = new StringBuilder();
		queryReadById.append("SELECT * FROM ").append(PROJECT_HISTORY).append(" WHERE ").append(MEMBER_ID);
		queryReadById.append("=?");

		Member member = new Member();
		member.setLogin(login);

		List<Project> projectsWithId = new ArrayList<Project>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryReadById.toString())){
			
			statement.setString(1, login);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Project project = new Project();
				project.setId(result.getLong(PROJECT_ID));
				projectsWithId.add(project);
			}

		} catch (SQLException e) {
			LOG.error("Exception with read Member by ID", e);
			throw new DAOReadException("Exception with read Member by ID" + e);
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOReadException("Connection exception" + e.getMessage());
		}
		member.setHistoryProjects(projectsWithId);

		return member;
	}

	@Override
	public void update(Member oldMember, Member newMember)  throws DAOException{
		
		throw new UnsupportedOperationException("You can't update history");
	}

	/**
	 * Method delete all data from Project_History by member login.
	 * @param member
	 * @throws DAODeleteException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Member member) throws DAODeleteException {
		
		StringBuilder queryDelete = new StringBuilder();
		queryDelete.append("DELETE FROM ").append(PROJECT_HISTORY).append(" WHERE ").append(MEMBER_ID).append("=?");

		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(queryDelete.toString())) {
			statement.setString(1, member.getLogin());
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete member history ", e );
			throw new DAODeleteException("Problem with delete member history" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}

	}
	
	/**
	 * This method for getting all members with project history.
	 * @return List<Member>
	 * @throws DAOReadException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.BaseDAO#getAll()
	 */
	@Override
	public List<Member> getAll() throws DAOException {

		StringBuilder queryRead = new StringBuilder();
		queryRead.append("SELECT DISTINCT ").append(MEMBER_ID).append(" FROM ").append(PROJECT_HISTORY);
		
		StringBuilder queryReadMemberId = new StringBuilder();
		queryReadMemberId.append("SELECT ").append(PROJECT_ID).append(" FROM ").append(PROJECT_HISTORY).append(" WHERE ");
		queryReadMemberId.append(MEMBER_ID).append("=?");

		List<Member> members = new ArrayList<Member>();

		try (Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(queryRead.toString())) {


			while (result.next()) {

				Member member = new Member();
				member.setLogin(result.getString(MEMBER_ID));

				List<Project> projects = getMemberHistory(connection, member.getLogin());
				
				member.setHistoryProjects(projects);
				members.add(member);
				
			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all members", e);
			throw new DAOReadException("Problem with getting all members" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return members;
	}

	/**
	 * This method add project to member history
	 * @param member
	 * @param project
	 * @throws DAOCreateException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.team.TeamDAO#addMember(com.epam.lab.intouch.model.project.Project, com.epam.lab.intouch.model.member.Member)
	 */
	@Override
	public Long addProject(Member member, Project project, java.util.Date date) throws DAOCreateException {

		StringBuilder queryInsert = new StringBuilder();
		queryInsert.append("INSERT INTO ").append(PROJECT_HISTORY).append(" (");
		queryInsert.append(MEMBER_ID).append(", ").append(PROJECT_ID).append(", ").append(LEAVING_DATE).append(", ");
		queryInsert.append(LEAVING_TIME).append(", ").append(ENTER_DATE).append(", ").append(ENTER_TIME).append(") ");
		queryInsert.append("VALUES(?,?,?,?,?,?)");

		try (Connection connection = getConnection(); 
			PreparedStatement statementForAdd = connection.prepareStatement(queryInsert.toString())) {

			statementForAdd.setString(1, member.getLogin());
			statementForAdd.setLong(2, project.getId());
			statementForAdd.setDate(3, new Date(new java.util.Date().getTime()));
			statementForAdd.setTime(4, new Time(new java.util.Date().getTime()));
			statementForAdd.setDate(5, new Date(new java.util.Date().getTime()));
			statementForAdd.setTime(6, new Time(new java.util.Date().getTime()));
			
			statementForAdd.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with add project to history", e);
			throw new DAOCreateException("Problew with add project to history" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}

		return project.getId();
	}


	/**
	 * Method getting member history by member login
	 * @param connection
	 * @param login
	 * @return List<Project>
	 * @throws SQLException
	 */
	private List<Project> getMemberHistory(Connection connection, String login) throws SQLException{
		
		StringBuilder queryReadMemberId = new StringBuilder();
		queryReadMemberId.append("SELECT ").append(PROJECT_ID).append(" FROM ").append(PROJECT_HISTORY).append(" WHERE ");
		queryReadMemberId.append(MEMBER_ID).append("=?");
		
		List<Project> projects = new ArrayList<Project>();
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(queryReadMemberId.toString())){
				
			preparedStatement.setString(1, login);
				ResultSet projectResult = preparedStatement.executeQuery();

			while (projectResult.next()) {
				Project project = new Project();
				project.setId(projectResult.getLong(PROJECT_ID));
				projects.add(project);
				
			}
					
		} 
		return projects;
		
	}

	/**
	 * This method for getting all members with their project history by SQL query.
	 * @return List<Member>
	 * @throws DAOReadException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.team.TeamDAO#getAllFromSearch(String query)
	 */
	@Override
	public List<Member> getAllFromSearch(String query) throws DAOReadException {
		
		List<Member> members = new ArrayList<Member>();

		try (Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query)) {


			while (result.next()) {

				Member member = new Member();
				member.setLogin(result.getString(MEMBER_ID));

				List<Project> projects = getMemberHistory(connection, member.getLogin());
				
				member.setHistoryProjects(projects);
				members.add(member);
				
			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all members", e);
			throw new DAOReadException("Problem with getting all members" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return members;
	}

	/**
	 * Method returns all members which worked on project
	 * 
	 * @param project 
	 * @return List<Member> 
	 * @throws DAOReadException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.history.project.HistoryDAO#getProjectHistory(com.epam.lab.intouch.model.project.Project)
	 */
	@Override
	public List<Member> getProjectHistory(Project project) throws DAOReadException {	
		
		StringBuilder queryGetMembers = new StringBuilder();
		queryGetMembers.append("SELECT ").append(MEMBER_ID).append(" FROM ").append(PROJECT_HISTORY);
		queryGetMembers.append(" WHERE ").append(PROJECT_ID).append(" = ").append(project.getId());
		
		List<Member> members = new ArrayList<Member>();
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryGetMembers.toString());
				ResultSet result = statement.executeQuery()) {
			
				while (result.next()) {

					Member member = new Member();
					member.setLogin(result.getString(MEMBER_ID));
					members.add(member);
										
				}

		} catch (SQLException e) {
			LOG.error("Problem with getting all members from project history", e);
			throw new DAOReadException("Problem with getting all members from project history" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return members;
	}


}
