package com.epam.lab.intouch.dao.member.skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;

public class DefaultSkillDAO extends AbstractBaseDAO<Skill, Long> implements SkillDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultSkillDAO.class);

	@Override
	public Long create(Skill skill) throws DAOCreateException {
		
		String queryInsert = "INSERT INTO Skills (id, name, type, [level], experience, description) VALUES (?,?,?,?,?,?)";

		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(queryInsert)) {
			
			statement.setLong(1, skill.getId());
			statement.setString(2, skill.getName());
			statement.setString(3, skill.getSkillType().toString());
			statement.setInt(4, skill.getLevel());
			statement.setDouble(5, skill.getExperience());
			statement.setString(6, skill.getDescription());
			
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
	public Skill getById(Long id) throws DAOException {
		
		String queryRead = "SELECT * FROM Skills WHERE id = ?";
		
		Skill skill = null;
		try (Connection connection = getConnection();
				PreparedStatement statement = prStatementSkillID(connection, queryRead, id);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				
				skill = new Skill();
				
				skill.setId(result.getLong("id"));
				skill.setName(result.getString("name"));
				skill.setSkillType(SkillType.fromString(result.getString("type")));
				skill.setLevel(result.getInt("[level]"));
				skill.setExperience(result.getDouble("experience"));
				skill.setDescription(result.getString("description"));
				
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
	public void update(Skill oldSkill, Skill newSkill) throws DAOException {

		

	}

	@Override
	public void delete(Skill skill) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Skill> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private PreparedStatement prStatementSkillID(Connection connection, String query, Long parametr) throws SQLException{
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, parametr);
		
		return preparedStatement;
	}

}
