package com.epam.lab.intouch.dao.team;

import static com.epam.lab.intouch.util.db.metadata.FieldName.*;
import static com.epam.lab.intouch.util.db.metadata.FieldName.PROJECT_ID;
import static com.epam.lab.intouch.util.db.metadata.TableName.TEAMS;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Class for manipulation data in Teams table   
 * @author Molodec
 *
 */
public class DefaultTeamDAO extends AbstractBaseDAO<Project, Long> implements TeamDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultTeamDAO.class);

	/**
	 * Method create new record in Teams table DB
	 * @param project
	 * @return id project 
	 * 
	 * @throws DAOCreateException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * 
	 * @see com.epam.lab.intouch.dao.BaseDAO#create(java.lang.Object)
	 */
	@Override
	public Long create(Project project) throws DAOCreateException {
		
		StringBuilder queryInsert = new StringBuilder();
		queryInsert.append("INSERT INTO ").append(TEAMS);
		queryInsert.append(" (").append(PROJECT_ID).append(", ").append(MEMBER_ID).append(ENTER_DATE).append(", ").append(ENTER_TIME).append(") ");
		queryInsert.append("VALUES (?,?)");
		
		List<Member> teams = project.getMembers();
		Long idProject = project.getId();

		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(queryInsert.toString())) {
			
			for (Member member : teams) {
				statement.setLong(1, idProject);
				statement.setString(2, member.getLogin());
				statement.setDate(3, new Date(new java.util.Date().getTime()));
				statement.setTime(4, new Time(new java.util.Date().getTime()));
			}
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("SQLException", e);
			throw new DAOCreateException("Problew with create" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}

		return idProject;

	}
	
	/**
	 * This method for get project with his team from DB by ID.
	 * 
	 * @param id 
	 * @return Project
	 * @throws DAOReadException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * 
	 * @see com.epam.lab.intouch.dao.BaseDAO#getById(java.lang.Object)
	 */
	@Override
	public Project getById(Long id) throws DAOReadException {
		
		StringBuilder queryReadById = new StringBuilder();
		queryReadById.append("SELECT * FROM ").append(TEAMS).append(" WHERE ").append(PROJECT_ID).append("= ").append(id);

		Project project = new Project();
		project.setId(id);

		List<Member> membersWithID = new ArrayList<Member>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryReadById.toString());
				ResultSet result = statement.executeQuery()) {
			
			while (result.next()) {
				Member member = new Member();
				member.setLogin(result.getString(MEMBER_ID));
				membersWithID.add(member);
			}

		} catch (SQLException e) {
			LOG.error("Exception with read Project by ID", e);
			throw new DAOReadException("Exception with read Project by ID" + e);
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}
		project.setMembers(membersWithID);

		return project;
	}

	@Override
	public void update(Project oldProject, Project newProject) throws DAOException {
		
		throw new UnsupportedOperationException("You can't update team");
		
	}
	
	/**
	 * Method delete all data from Teams by id project.
	 * @param project
	 * @throws DAODeleteException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Project project) throws DAODeleteException {
		
		StringBuilder queryDelete = new StringBuilder();
		queryDelete.append("DELETE FROM ").append(TEAMS).append(" WHERE ").append(PROJECT_ID).append("=?");
		
		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(queryDelete.toString())) {
			statement.setLong(1, project.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete project ", e);
			throw new DAODeleteException("Problem with delete project " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}

	}
	
	/**
	 * This method for getting all projects with their teams.
	 * @return List<Project>
	 * @throws DAOReadException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.BaseDAO#getAll()
	 */
	@Override
	public List<Project> getAll() throws DAOReadException {

		StringBuilder queryRead = new StringBuilder();
		queryRead.append("SELECT DISTINCT ").append(PROJECT_ID).append(" FROM ").append(TEAMS);
		
		List<Project> projects = new ArrayList<Project>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryRead.toString());
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {

				Project project = new Project();
				project.setId(result.getLong(PROJECT_ID));

				List<Member> members = getProjectTeam(connection, project.getId());
				project.setMembers(members);
				projects.add(project);
			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all projects", e);
			throw new DAOReadException("Problem with getting all projects" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return projects;
	}

	/**
	 * This method add member to team project
	 * @param project
	 * @param member
	 * @throws DAOCreateException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.team.TeamDAO#addMember(com.epam.lab.intouch.model.project.Project, com.epam.lab.intouch.model.member.Member)
	 */
	@Override
	public String addMember(Project project, Member member) throws DAOCreateException {

		StringBuilder queryInsert = new StringBuilder();
		queryInsert.append("INSERT INTO ").append(TEAMS);
		queryInsert.append(" (").append(PROJECT_ID).append(", ").append(MEMBER_ID).append(ENTER_DATE).append(", ").append(ENTER_TIME).append(") ");
		queryInsert.append("VALUES (?,?,?,?)");
		
		try (Connection connection = getConnection(); 
			PreparedStatement statementForAdd = connection.prepareStatement(queryInsert.toString())) {

			statementForAdd.setLong(1, project.getId());
			statementForAdd.setString(2, member.getLogin());
			statementForAdd.setDate(3, new Date(new java.util.Date().getTime()));
			statementForAdd.setTime(4, new Time(new java.util.Date().getTime()));
			
			statementForAdd.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with add member to teams", e);
			throw new DAOCreateException("Problew with add member to teams" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}
		return member.getLogin();
	}
	
	/**
	 * This method remove member from team project
	 * @param project
	 * @param member
	 * @throws DAODeleteException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.team.TeamDAO#addMember(com.epam.lab.intouch.model.project.Project, com.epam.lab.intouch.model.member.Member)
	 */
	@Override
	public void removeMember(Project project, Member member) throws DAODeleteException {
		
		StringBuilder queryRemove = new StringBuilder();
		queryRemove.append("DELETE FROM ").append(TEAMS).append(" WHERE ");
		queryRemove.append(PROJECT_ID).append("=? ").append(" AND ").append(MEMBER_ID).append("=?");

		try (Connection connection = getConnection(); 
			PreparedStatement statementRemove = connection.prepareStatement(queryRemove.toString())) {

			statementRemove.setLong(1, project.getId());
			statementRemove.setString(2, member.getLogin());

			statementRemove.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with remove member from team project ", e);
			throw new DAODeleteException("Problem with remove member from team project " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}

	}
	

	/**
	 * Method return project team
	 * @param connection
	 * @param id
	 * @return List<Member>
	 * @throws SQLException
	 */
	private List<Member> getProjectTeam(Connection connection, Long id) throws SQLException{
		
		StringBuilder queryReadMemberId = new StringBuilder();
		queryReadMemberId.append("SELECT ").append(MEMBER_ID).append(" FROM ").append(TEAMS);
		queryReadMemberId.append(" WHERE ").append(PROJECT_ID).append("=").append(id);
		
		List<Member> members = new ArrayList<Member>();
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(queryReadMemberId.toString());
				ResultSet memberResult = preparedStatement.executeQuery()){

			while (memberResult.next()) {
				Member member = new Member();
				member.setLogin(memberResult.getString(MEMBER_ID));
				members.add(member);
			}
					
		} 
		return members;
		
	}
	
	/**
	 * This method for getting all projects with their teams by SQL query.
	 * @return List<Project>
	 * @throws DAOReadException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.team.TeamDAO#getAllFromSearch(String query)
	 */
	@Override
	public List<Project> getAllFromSearch(String query) throws DAOReadException {
		
		List<Project> projects = new ArrayList<Project>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {

				Project project = new Project();
				project.setId(result.getLong(PROJECT_ID));

				List<Member> members = getProjectTeam(connection, project.getId());
				project.setMembers(members);
				projects.add(project);
			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all projects", e);
			throw new DAOReadException("Problem with getting all projects" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return projects;
	}
	/**
	 * Method return Member with active projects
	 * @returnMember
	 * @throws DAOReadException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 */
	@Override
	public Member getActiveProjects(String login) throws DAOReadException {
		
		StringBuilder queryGetProject = new StringBuilder();
		queryGetProject.append("SELECT ").append(PROJECT_ID).append(" FROM ").append(TEAMS).append(" WHERE ");
		queryGetProject.append(MEMBER_ID).append(" = '").append(login).append("'");
		
		Member member = new Member();
		member.setLogin(login);
		
		List<Project> projectsWithId = new ArrayList<Project>();
		
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryGetProject.toString());
				ResultSet result = statement.executeQuery()){
			
			while(result.next()){
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
		member.setActiveProjects(projectsWithId);
		
		return member;
	}

	@Override
	public java.util.Date getEnterDate(Member member, Project project) throws DAOReadException {
		
		StringBuilder queryGetDate = new StringBuilder();
		queryGetDate.append("SELECT * FROM ").append(TEAMS).append(" WHERE ").append(MEMBER_ID).append("='").append(member.getLogin()).append("' AND ");
		queryGetDate.append(PROJECT_ID).append("=").append(project.getId());
		
		Date enterDate = null;
		
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryGetDate.toString());
				ResultSet result = statement.executeQuery()){
			
			while(result.next()){
				enterDate = getEnterDate(result);
			}
			
		} catch (SQLException e) {
			LOG.error("Problem with getting enter date ", e);
			throw new DAOReadException("Problem with getting enter date " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOReadException("Connection exception" + e.getMessage());
		}
		return enterDate;
	}
	
	private Date getEnterDate(ResultSet result) throws SQLException{
		
		Date enterDate = result.getDate(ENTER_DATE);
		Time enterTime = result.getTime(ENTER_TIME);

		long fullEnterDate = enterDate.getTime() + enterTime.getTime();

		return new Date(fullEnterDate);
	}

}
