package com.epam.lab.intouch.db.dao.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import com.epam.lab.intouch.db.exception.DBAccessException;
import com.epam.lab.intouch.db.exception.DBDeleteException;
import com.epam.lab.intouch.db.exception.DBUpdateException;
import com.epam.lab.intouch.db.util.ConnectionManager;
import com.epam.lab.intouch.model.project.Project;

public class ProjectDAO implements BaseProjectDAO {

	private static Connection conn = ConnectionManager.getInstance()
			.getConnection();

	/*
	 * @Override public Map<Integer, Project> readAll() throws DBAccessException
	 * {
	 * 
	 * final String sql = "SELECT * FROM projects";
	 * 
	 * Map<Integer, Project> map = new LinkedHashMap<Integer, Project>();
	 * 
	 * try (Statement statement = conn.createStatement(); ResultSet rs =
	 * statement.executeQuery(sql);) { while (rs.next()) { Project bean = new
	 * Project(); // TODO and so on ;)
	 * 
	 * map.put(rs.getInt("project_id"), bean); } } catch (SQLException ex) {
	 * throw new DBAccessException(ex); }
	 * 
	 * return map; }
	 */

	@Override
	public void create(final Project bean) throws DBAccessException {

		final String sql = "INSERT into projects "
				+ "(project_name, start_date, estimated_date, close_date, description, "
				+ "customer, status, members)" + "VALUES (?, ?, ?, ?, ?, ?)";
		ResultSet keys = null;

		try {
			try (PreparedStatement statement = conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);) {

				statement.setString(1, bean.getName());
				// TODO and so on ;)

				int affected = statement.executeUpdate();

				if (affected != 1) {
					throw new SQLException();
				}

			} catch (SQLException ex) {
				throw new DBAccessException(ex);
			} finally {
				if (keys != null)
					keys.close();
			}
		} catch (SQLException ex) {
			throw new DBAccessException(ex);
		}
	}

	@Override
	public Project read(final Integer projectId) throws DBAccessException {

		final String sql = "SELECT * FROM projects WHERE project_id = ?";
		ResultSet rs = null;

		try {
			try (PreparedStatement statement = conn.prepareStatement(sql);) {
				statement.setInt(1, projectId);
				rs = statement.executeQuery();

				if (rs.next()) {
					Project bean = new Project();
					bean.setProjectName(rs.getString("project_name"));
					// TODO and so on ;)

					return bean;
				} else {
					throw new SQLException();
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
		} catch (SQLException ex) {
			throw new DBAccessException(ex);
		}

	}

	@Override
	public void update(final Project bean, final Integer projectID)
			throws DBUpdateException {

		final String sql = "UPDATE projects SET "
				+ "project_name = ?, start_date = ?, estimated_date = ?, close_date = ?, "
				+ "description = ?, customer = ?, status = ?, members = ? "
				+ "WHERE project_id = ?";
		try (PreparedStatement statement = conn.prepareStatement(sql);) {

			statement.setString(1, bean.getName());
			// TODO and so on ;)

			int affected = statement.executeUpdate();
			if (affected == 1) {
				throw new SQLException("No rows were affected");
			}
		} catch (SQLException ex) {
			throw new DBUpdateException(ex);
		}
	}

	@Override
	public void delete(final Integer projectId) throws DBDeleteException {

		final String sql = "DELETE FROM projects WHERE project_id = ?";

		try (PreparedStatement statement = conn.prepareStatement(sql);) {

			statement.setInt(1, projectId);
			int affected = statement.executeUpdate();
			if (affected != 1) {
				throw new SQLException("No rows were affected");
			}
		} catch (SQLException ex) {
			throw new DBDeleteException(ex);
		}
	}

	@Override
	public Collection<Project> getAll() throws SQLException {
		List<Project> projects = null;

		return projects;
	}

}
