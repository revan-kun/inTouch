package com.epam.lab.intouch.dao.team;

import static com.epam.lab.intouch.dao.util.FieldName.MEMBER_ID;
import static com.epam.lab.intouch.dao.util.FieldName.PROJECT_ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAODeleteException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public class DefaultTeamDAO extends AbstractBaseDAO<Project, Long> implements TeamDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultTeamDAO.class);

	@Override
	public Long create(Project project) throws DAOCreateException {
		String queryInsert = "INSERT INTO Teams (project_id, member_id) VALUES (?,?)";
		List<Member> teams = project.getMembers();
		Long idProject = project.getId();

		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(queryInsert)) {
			
			for (Member member : teams) {
				statement.setLong(1, idProject);
				statement.setString(2, member.getLogin());
				statement.executeUpdate();
			}

		} catch (SQLException e) {
			LOG.error("SQLException", e);
			throw new DAOCreateException("Problew with create" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}

		return idProject;

	}

	@Override
	public Project getById(Long id) throws DAOReadException {
		
		String queryReadById = "SELECT * FROM Teams WHERE project_id = ? ";

		Project project = new Project();
		project.setId(id);

		List<Member> membersWithID = new ArrayList<Member>();

		try (Connection connection = getConnection();
				PreparedStatement statement = prStatementProjectID(connection, queryReadById, id);
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

	@Override
	public void delete(Project project) throws DAODeleteException {
		
		String queryDelete = "DELETE FROM Team WHERE project_id = ?";
		
		try (Connection connection = getConnection(); 
			PreparedStatement statement = prStatementProjectID(connection, queryDelete, project.getId())) {
			
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete project ", e);
			throw new DAODeleteException("Problem with delete project " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}

	}

	@Override
	public List<Project> getAll() throws DAOReadException {

		String queryRead = "SELECT DISTINCT project_id FROM Teams";
		String queryReadMemberId = "SELECT member_id FROM Teams WHERE project_id = ?";
		List<Project> projects = new ArrayList<Project>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryRead);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {

				Project project = new Project();
				project.setId(result.getLong(PROJECT_ID));

				List<Member> members = getProjectTeam(connection, queryReadMemberId, project.getId());
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

	@Override
	public String addMember(Project project, Member member) throws DAOCreateException {

		String queryAdd = "INSERT INTO Teams (project_id, member_id) VALUES (?, ?)";

		try (Connection connection = getConnection(); 
			PreparedStatement statementForAdd = connection.prepareStatement(queryAdd)) {

			statementForAdd.setLong(1, project.getId());
			statementForAdd.setString(2, member.getLogin());

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

	@Override
	public void removeMember(Project project, Member member) throws DAODeleteException {

		String queryRemove = "DELETE project_id, member_id From Teams WHERE project_id =? AND member_id = ?";

		try (Connection connection = getConnection(); 
			PreparedStatement statementRemove = connection.prepareStatement(queryRemove)) {

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
	
	private PreparedStatement prStatementProjectID(Connection connection, String query, Long parametr) throws SQLException{
			
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, parametr);
		
		return preparedStatement;
	}
	
	private List<Member> getProjectTeam(Connection connection, String query, Long id) throws SQLException{
		
		List<Member> members = new ArrayList<Member>();
		
		try(PreparedStatement preparedStatement = prStatementProjectID(connection, query, id);
				ResultSet memberResult = preparedStatement.executeQuery()){

			while (memberResult.next()) {
				Member member = new Member();
				member.setLogin(memberResult.getString(MEMBER_ID));
				members.add(member);
			}
					
		} 
		return members;
		
	}

	@Override
	public List<Project> getAllFromSearch(String query) throws DAOException {
		
		String queryReadMemberId = "SELECT member_id FROM Teams WHERE project_id = ?";
		List<Project> projects = new ArrayList<Project>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {

				Project project = new Project();
				project.setId(result.getLong(PROJECT_ID));

				List<Member> members = getProjectTeam(connection, queryReadMemberId, project.getId());
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

}
