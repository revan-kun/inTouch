package com.epam.lab.intouch.dao.project;

import static com.epam.lab.intouch.dao.util.FieldName.COMPLETED;
import static com.epam.lab.intouch.dao.util.FieldName.CREATED;
import static com.epam.lab.intouch.dao.util.FieldName.CUSTOMER;
import static com.epam.lab.intouch.dao.util.FieldName.DESCRIPTION;
import static com.epam.lab.intouch.dao.util.FieldName.ESTIMATED_COMPLETION;
import static com.epam.lab.intouch.dao.util.FieldName.ID;
import static com.epam.lab.intouch.dao.util.FieldName.NAME;
import static com.epam.lab.intouch.dao.util.FieldName.STATUS;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAODeleteException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.exception.DAOUpdateException;
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;

public class DefaultProjectDAO extends AbstractBaseDAO<Project, Long> implements ProjectDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultProjectDAO.class);

	@Override
	public Long create(Project project) throws DAOCreateException {

		String queryInsert = "INSERT INTO Project (" + ProjectAttribute.getAttributes() + ") VALUES (?,?,?,?,?,?,?,?)";

		Date creationDate = new Date(project.getCreationDate().getTime());
		Date estimatedCompletion = new Date(project.getEstimatedCompletionDate().getTime());
		Date completeDate = new Date(project.getCompletionDate().getTime());

		try (Connection connection = getConnection(); 
				PreparedStatement statement = connection.prepareStatement(queryInsert)) {

			statement.setLong(1, project.getId());
			statement.setString(2, project.getProjectName());
			statement.setDate(3, creationDate);
			statement.setDate(4, estimatedCompletion);
			statement.setDate(5, completeDate);
			statement.setString(6, project.getDescription());
			statement.setString(7, project.getCustomer());
			statement.setString(8, project.getStatus().toString());

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("SQLException" + e.getMessage());
			throw new DAOCreateException("Problew with create" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOCreateException("Connection exception" + e.getMessage());

		}

		return project.getId();

	}

	@Override
	public Project getById(Long id) throws DAOReadException {
		String queryById = "SELECT * FROM Project WHERE id = ? ";
		Project project = null;
		try (Connection connection = getConnection();
				PreparedStatement statement = prStatementProjectID(connection, queryById, id);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				project = new Project();
				project.setId(result.getLong(ID));
				project.setProjectName(result.getString(NAME));
				project.setCreationDate(result.getDate(CREATED));
				project.setEstimatedCompletionDate(result.getDate(ESTIMATED_COMPLETION));
				project.setCompletionDate(result.getDate(COMPLETED));
				project.setCustomer(result.getString(CUSTOMER));
				project.setDescription(result.getString(DESCRIPTION));
				project.setStatus(ProjectStatus.fromString(result.getString(STATUS)));

			}

		} catch (SQLException e) {
			LOG.error("Exception with read Project by ID", e);
			throw new DAOReadException("Exception with read Project by ID" + e);
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return project;
	}

	@Override
	public void update(Project oldProject, Project newProject) throws DAOUpdateException {

		StringBuilder queryUpdate = new StringBuilder();
		queryUpdate.append("UPDATE Project SET ");
		queryUpdate.append(NAME).append("= '").append(newProject.getProjectName()).append("', ");
		queryUpdate.append(CREATED).append("= '").append(newProject.getCreationDate()).append("', ");
		queryUpdate.append(ESTIMATED_COMPLETION).append("= '").append(newProject.getEstimatedCompletionDate()).append("', ");
		queryUpdate.append(COMPLETED).append("= '").append(newProject.getCompletionDate()).append("', ");
		queryUpdate.append(CUSTOMER).append("= '").append(newProject.getCustomer()).append("', ");
		queryUpdate.append(DESCRIPTION).append("= '").append(newProject.getDescription()).append("', ");
		queryUpdate.append(STATUS).append("= '").append(newProject.getStatus()).append("'");
		queryUpdate.append(" WHERE id = ").append(oldProject.getId());

		try (Connection connection = getConnection(); 
				PreparedStatement statement = connection.prepareStatement(queryUpdate.toString())) {

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with update project " + e.getMessage());
			throw new DAOUpdateException("Problem with update project " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection " + e.getMessage());
			throw new DAOUpdateException("Problem with conection " + e.getMessage());
		}

	}

	@Override
	public void delete(Project project) throws DAODeleteException {
		String queryDelete = "DELETE * FROM Project WHERE id = ?";

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

		String queryAll = "SELECT * FROM Project";
		List<Project> projects = new ArrayList<Project>();

		try (Connection connection = getConnection(); 
				Statement statement = connection.createStatement(); 
				ResultSet result = statement.executeQuery(queryAll)) {

			while (result.next()) {
				Project project = new Project();
				project.setId(result.getLong(ID));
				project.setProjectName(result.getString(NAME));
				project.setCreationDate(result.getDate(CREATED));
				project.setEstimatedCompletionDate(result.getDate(ESTIMATED_COMPLETION));
				project.setCompletionDate(result.getDate(COMPLETED));
				project.setCustomer(result.getString(CUSTOMER));
				project.setDescription(result.getString(DESCRIPTION));
				project.setStatus(ProjectStatus.fromString(result.getString(STATUS)));

				projects.add(project);

			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all Projects", e);
			throw new DAOReadException("Problem with getting all Projects" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return projects;
	}

	private PreparedStatement prStatementProjectID(Connection connection, String query, Long parametr) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, parametr);

		return preparedStatement;
	}

	@Override
	public List<Project> getAllFromSearch(String query) throws DAOReadException {
		
		List<Project> projects = new ArrayList<Project>();

		try (Connection connection = getConnection(); 
				Statement statement = connection.createStatement(); 
				ResultSet result = statement.executeQuery(query)) {

			while (result.next()) {
				Project project = new Project();
				project.setId(result.getLong(ID));
				project.setProjectName(result.getString(NAME));
				project.setCreationDate(result.getDate(CREATED));
				project.setEstimatedCompletionDate(result.getDate(ESTIMATED_COMPLETION));
				project.setCompletionDate(result.getDate(COMPLETED));
				project.setCustomer(result.getString(CUSTOMER));
				project.setDescription(result.getString(DESCRIPTION));
				project.setStatus(ProjectStatus.fromString(result.getString(STATUS)));

				projects.add(project);

			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all Projects", e);
			throw new DAOReadException("Problem with getting all Projects" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return projects;
	}

}
