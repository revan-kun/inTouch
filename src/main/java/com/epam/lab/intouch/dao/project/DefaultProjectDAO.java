package com.epam.lab.intouch.dao.project;

import static com.epam.lab.intouch.dao.project.ProjectAttribute.COMPLETED;
import static com.epam.lab.intouch.dao.project.ProjectAttribute.CREATED;
import static com.epam.lab.intouch.dao.project.ProjectAttribute.CUSTOMER;
import static com.epam.lab.intouch.dao.project.ProjectAttribute.DESCRIPTION;
import static com.epam.lab.intouch.dao.project.ProjectAttribute.ESTIMATED_COMPLETION;
import static com.epam.lab.intouch.dao.project.ProjectAttribute.ID;
import static com.epam.lab.intouch.dao.project.ProjectAttribute.NAME;
import static com.epam.lab.intouch.dao.project.ProjectAttribute.STATUS;

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
			
			statement.setLong(ID.index(), project.getId());
			statement.setString(NAME.index(), project.getProjectName());
			statement.setDate(CREATED.index(), creationDate);
			statement.setDate(ESTIMATED_COMPLETION.index(), estimatedCompletion);
			statement.setDate(COMPLETED.index(), completeDate);
			statement.setString(DESCRIPTION.index(), project.getDescription());
			statement.setString(CUSTOMER.index(), project.getCustomer());
			statement.setString(STATUS.index(), project.getStatus().toString());

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
				project.setId(result.getLong(ID.getName()));
				project.setProjectName(result.getString(NAME.getName()));
				project.setCreationDate(result.getDate(CREATED.getName()));
				project.setEstimatedCompletionDate(result.getDate(ESTIMATED_COMPLETION.getName()));
				project.setCompletionDate(result.getDate(COMPLETED.getName()));
				project.setCustomer(result.getString(CUSTOMER.getName()));
				project.setDescription(result.getString(DESCRIPTION.getName()));
				project.setStatus(ProjectStatus.fromString(result.getString(STATUS.getName())));

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
		queryUpdate.append(NAME.getName()).append("= '").append(newProject.getProjectName()).append("', ");
		queryUpdate.append(CREATED.getName()).append("= '").append(newProject.getCreationDate()).append("', ");
		queryUpdate.append(ESTIMATED_COMPLETION.getName()).append("= '").append(newProject.getEstimatedCompletionDate()).append("', ");
		queryUpdate.append(COMPLETED.getName()).append("= '").append(newProject.getCompletionDate()).append("', ");
		queryUpdate.append(CUSTOMER.getName()).append("= '").append(newProject.getCustomer()).append("', ");
		queryUpdate.append(DESCRIPTION.getName()).append("= '").append(newProject.getDescription()).append("', ");
		queryUpdate.append(STATUS.getName()).append("= '").append(newProject.getStatus()).append("'");
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
			PreparedStatement statement = connection.prepareStatement(queryDelete)) {

			statement.setLong(ID.index(), project.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete project " + e.getMessage());
			throw new DAODeleteException("Problem with delete project " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection " + e.getMessage());
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
				project.setId(result.getLong(ID.getName()));
				project.setProjectName(result.getString(NAME.getName()));
				project.setCreationDate(result.getDate(CREATED.getName()));
				project.setEstimatedCompletionDate(result.getDate(ESTIMATED_COMPLETION.getName()));
				project.setCompletionDate(result.getDate(COMPLETED.getName()));
				project.setCustomer(result.getString(CUSTOMER.getName()));
				project.setDescription(result.getString(DESCRIPTION.getName()));
				project.setStatus(ProjectStatus.fromString(result.getString(STATUS.getName())));

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
	
	private PreparedStatement prStatementProjectID(Connection connection, String query, Long parametr) throws SQLException{
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(ID.index(), parametr);
		
		return preparedStatement;
	}
	
}
