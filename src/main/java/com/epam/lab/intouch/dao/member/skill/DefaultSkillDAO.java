package com.epam.lab.intouch.dao.member.skill;

import java.sql.Connection;
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
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.exception.DAOUpdateException;
import com.epam.lab.intouch.dao.util.FieldName;
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;

public class DefaultSkillDAO extends AbstractBaseDAO<Skill, Long> implements SkillDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultSkillDAO.class);

	@Override
	public Long create(Skill skill) throws DAOCreateException {

		String queryInsert = "INSERT INTO Skills (id, name, type) VALUES (?,?,?)";
		

		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(queryInsert)) {

			statement.setLong(1, skill.getId());
			statement.setString(2, skill.getName());
			statement.setString(3, skill.getSkillType().toString());

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problew with create skill", e);
			throw new DAOCreateException("Problew with create skill" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}

		return skill.getId();
	}

	@Override
	public Skill getById(Long id) throws DAOReadException {

		String queryRead = "SELECT * FROM Skills WHERE id = ?";

		Skill skill = null;
		try (Connection connection = getConnection();
				PreparedStatement statement = prStatementSkillID(connection, queryRead, id);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {

				skill = new Skill();

				skill.setId(result.getLong(FieldName.ID));
				skill.setName(result.getString(FieldName.NAME));
				skill.setSkillType(SkillType.fromString(result.getString(FieldName.TYPE)));
			}

		} catch (SQLException e) {
			LOG.error("Exception with read skill by ID", e);
			throw new DAOReadException("Exception with read skill by ID" + e);
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return skill;
	}

	@Override
	public void update(Skill oldSkill, Skill newSkill) throws DAOUpdateException {

		StringBuilder queryUpdate = new StringBuilder();

		queryUpdate.append("UPDATE Skills SET ");
		queryUpdate.append(FieldName.ID).append(" = ").append(newSkill.getId()).append(", ");
		queryUpdate.append(FieldName.NAME).append(" = '").append(newSkill.getName()).append("', ");
		queryUpdate.append(FieldName.TYPE).append(" = '").append(newSkill.getSkillType()).append("' ");
		queryUpdate.append("WHERE id = ").append(oldSkill.getId());

		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(queryUpdate.toString())) {

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with update skill " + e.getMessage());
			throw new DAOUpdateException("Problem with update skill " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection " + e.getMessage());
			throw new DAOUpdateException("Problem with conection " + e.getMessage());
		}

	}

	@Override
	public void delete(Skill skill) throws DAODeleteException {

		String queryDelete = "DELETE * FROM Skills WHERE id = ?";

		try (Connection connection = getConnection(); 
			PreparedStatement statement = prStatementSkillID(connection, queryDelete, skill.getId())) {

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete skill ", e);
			throw new DAODeleteException("Problem with delete skill " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}

	}

	@Override
	public List<Skill> getAll() throws DAOReadException {

		String queryAll = "SELECT * FROM Skills";

		List<Skill> skills = new ArrayList<Skill>();

		try (Connection connection = getConnection(); 
			Statement statement = connection.createStatement(); 
			ResultSet result = statement.executeQuery(queryAll)) {

			while (result.next()) {

				Skill skill = new Skill();
				skill.setId(result.getLong(FieldName.ID));
				skill.setName(result.getString(FieldName.NAME));
				skill.setSkillType(SkillType.fromString(result.getString(FieldName.TYPE)));

				skills.add(skill);
			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all skills", e);
			throw new DAOReadException("Problem with getting all skills" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}
		
		return skills;
	}

	private PreparedStatement prStatementSkillID(Connection connection, String query, Long parametr) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, parametr);

		return preparedStatement;
	}

	@Override
	public List<Skill> getAllFromSearch(String query) throws DAOException {
		
		List<Skill> skills = new ArrayList<Skill>();

		try (Connection connection = getConnection(); 
			Statement statement = connection.createStatement(); 
			ResultSet result = statement.executeQuery(query)) {

			while (result.next()) {

				Skill skill = new Skill();
				skill.setId(result.getLong(FieldName.ID));
				skill.setName(result.getString(FieldName.NAME));
				skill.setSkillType(SkillType.fromString(result.getString(FieldName.TYPE)));

				skills.add(skill);
			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all skills", e);
			throw new DAOReadException("Problem with getting all skills" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}
		
		return skills;
	}

}
