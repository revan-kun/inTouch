package com.epam.lab.intouch.dao.team;

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

public class DefaultProjectTeamDAO extends AbstractBaseDAO<Project, Long> implements ProjectTeamDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultProjectTeamDAO.class);

	@Override
	public Long create(Project project) throws DAOCreateException {
		String queryInsert = "INSERT INTO Teams (project_id, member_id) VALUES (?,?)";
		List<Member> teams = project.getMembers();
		Long idProject = project.getId();

		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(queryInsert);) {
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
		String queryReadById = "SELECT * FROM Teams WHERE project_id = '" + id + "'";

		Project project = new Project();
		project.setId(id);

		List<Member> membersWithID = new ArrayList<Member>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryReadById);
				ResultSet result = statement.executeQuery();) {

			while (result.next()) {
				Member member = new Member();
				member.setLogin(result.getString("member_id"));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Project project) throws DAODeleteException {
		String queryDelete = "DELETE * FROM Team WHERE project_id = " + project.getId();
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(queryDelete)) {

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
		String queryRead = "SELECT * FROM Teams";
		List<Project> projects = new ArrayList<Project>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryRead);
				ResultSet result = statement.executeQuery();) {

			while (result.next()) {
				Project project = new Project();
				project.setId(result.getLong("project_id"));
				List<Member> members = project.getMembers();

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
