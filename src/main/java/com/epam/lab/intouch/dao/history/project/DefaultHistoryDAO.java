package com.epam.lab.intouch.dao.history.project;

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

public class DefaultHistoryDAO extends AbstractBaseDAO<Member, String> implements HistoryDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultHistoryDAO.class);

	@Override
	public String create(Member member) throws DAOCreateException {
		String queryInsert = "INSERT INTO Project_History (project_id, member_id) VALUES(?,?)";
		List<Project> historyProjects = member.getProjects();
		String login = member.getLogin();

		try (Connection connection = getConnection(); PreparedStatement statementCreate = connection.prepareStatement(queryInsert);) {

			for (Project project : historyProjects) {
				statementCreate.setLong(1, project.getId());
				statementCreate.setString(2, login);

				statementCreate.executeUpdate();
			}

		} catch (SQLException e) {
			LOG.error("SQLException", e);
			throw new DAOCreateException("Problew with create" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}

		return login;
	}

	@Override
	public Member getById(String login) throws DAOReadException {

		String queryReadById = "SELECT * FROM Project_History WHERE member_id = '" + login + "'";

		Member member = new Member();
		member.setLogin(login);

		List<Project> projectsWithId = new ArrayList<Project>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryReadById);
				ResultSet result = statement.executeQuery();) {

			while (result.next()) {
				Project project = new Project();
				project.setId(result.getLong("project_id"));
				projectsWithId.add(project);
			}

		} catch (SQLException e) {
			LOG.error("Exception with read Member by ID", e);
			throw new DAOReadException("Exception with read Member by ID" + e);
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}
		member.setProjects(projectsWithId);

		return member;
	}

	@Override
	public void update(Member oldMember, Member newMember) throws DAOException {

	}

	@Override
	public void delete(Member member) throws DAODeleteException {
		String queryDelete = "DELETE * FROM Project_History WHERE member_id = '" + member.getLogin() + "'";

		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(queryDelete)) {

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete member " + e.getMessage());
			throw new DAODeleteException("Problem with delete member " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}

	}

	@Override
	public List<Member> getAll() throws DAOException {

		String queryRead = "SELECT DISTINCT member_id FROM Project_History";

		List<Member> members = new ArrayList<Member>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryRead);
				ResultSet result = statement.executeQuery()) {

			String queryReadMemberId = "SELECT project_id FROM Project_History WHERE member_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(queryReadMemberId);

			while (result.next()) {

				Member member = new Member();
				member.setLogin(result.getString("member_id"));

				List<Project> projects = new ArrayList<Project>();

				preparedStatement.setString(1, member.getLogin());
				ResultSet projectResult = preparedStatement.executeQuery();

				while (projectResult.next()) {
					Project project = new Project();
					project.setId(projectResult.getLong("project_id"));
					projects.add(project);

				}
				member.setProjects(projects);
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

	@Override
	public Long addProject(Member member, Project project) throws DAOException {

		String queryAdd = "INSERT INTO Project_History (member_id, project_id) VALUES (?, ?)";

		try (Connection connection = getConnection(); PreparedStatement statementForAdd = connection.prepareStatement(queryAdd)) {

			statementForAdd.setString(1, member.getLogin());
			statementForAdd.setLong(2, project.getId());

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

}
