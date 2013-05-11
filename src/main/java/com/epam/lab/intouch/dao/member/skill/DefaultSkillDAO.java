package com.epam.lab.intouch.dao.member.skill;

import static com.epam.lab.intouch.util.db.metadata.FieldName.ID;
import static com.epam.lab.intouch.util.db.metadata.FieldName.NAME;
import static com.epam.lab.intouch.util.db.metadata.FieldName.TYPE;
import static com.epam.lab.intouch.util.db.metadata.TableName.SKILLS;

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
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.exception.DAOUpdateException;
import com.epam.lab.intouch.dao.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;

/**
 * Class for create, update, delete, get all and other operation with Skill
 * @author Molodec
 *
 */
public class DefaultSkillDAO extends AbstractBaseDAO<Skill, Long> implements SkillDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultSkillDAO.class);

	/**
	 * This method is create Skill in DB table Skills
	 * @param skill 
	 * @return id This is skill id in DB
	 * @throws DAOCreateException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.BaseDAO#create(java.lang.Object)
	 */
	@Override
	public Long create(Skill skill) throws DAOCreateException {

		StringBuilder queryInsert = new StringBuilder();
		queryInsert.append("INSERT INTO ").append(SKILLS);
		queryInsert.append(" (").append(NAME).append(", ").append(TYPE).append(") ");
		queryInsert.append("VALUES(?,?)");
		
		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(queryInsert.toString(), Statement.RETURN_GENERATED_KEYS);
				) {
			
			statement.setString(1, skill.getName());
			statement.setString(2, skill.getSkillType().toString());
			statement.executeUpdate();
				
			 skill.setId(getID(statement));

		} catch (SQLException e) {
			LOG.error("Problew with create skill", e);
			throw new DAOCreateException("Problew with create skill" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}

		return skill.getId();
	}
	
	/**
	 * This method return last generated key in DB
	 * @param statement
	 * @return id This is skill id
	 * @throws SQLException
	 */
	private Long getID(PreparedStatement statement) throws SQLException{
		
		ResultSet autoIncKey = statement.getGeneratedKeys();
		Long skillID = null;
		 while (autoIncKey.next()) {
			 skillID = autoIncKey.getLong(1);
		 }
		 
		return skillID;
	}

	/**
	 * Method return skill from DB on id
	 * @param id This is id skill in DB
	 * @return Skill
	 * @throws DAOReadException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.BaseDAO#getById(java.lang.Object)
	 */
	@Override
	public Skill getById(Long id) throws DAOReadException {

		StringBuilder queryRead = new StringBuilder();
		queryRead.append("SELECT * FROM ").append(SKILLS).append(" WHERE ").append(ID).append("=").append(id);

		Skill skill = null;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryRead.toString());
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {

				skill = new Skill();

				skill.setId(result.getLong(ID));
				skill.setName(result.getString(NAME));
				skill.setSkillType(SkillType.fromString(result.getString(TYPE)));
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
	
	/**
	 * Method for update skill with old value on new value in DB
	 * @see com.epam.lab.intouch.dao.BaseDAO#update(java.lang.Object, java.lang.Object)
	 * @param oldSkill skill with old value
	 * @param newSkill skill with new value
	 * @throws DAOUpdateException 
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 */

	@Override
	public void update(Skill oldSkill, Skill newSkill) throws DAOUpdateException {

		StringBuilder queryUpdate = new StringBuilder();

		queryUpdate.append("UPDATE ").append(SKILLS).append(" SET ");
		queryUpdate.append(NAME).append(" = '").append(newSkill.getName()).append("', ");
		queryUpdate.append(TYPE).append(" = '").append(newSkill.getSkillType()).append("' ");
		queryUpdate.append("WHERE ").append(ID).append(" = ").append(oldSkill.getId());

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

	/**
	 * Method delete all skill information in DB 
	 * @param skill
	 * @throws DAODeleteException 
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Skill skill) throws DAODeleteException {
		
		StringBuilder queryDelete = new StringBuilder();
		queryDelete.append("DELETE FROM ").append(SKILLS).append(" WHERE ").append(ID).append("=").append(skill.getId());

		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(queryDelete.toString())) {

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete skill ", e);
			throw new DAODeleteException("Problem with delete skill " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}

	}

	/**
	 * Method for get all skill from DB
	 * @return List<Skill>
	 * @throws DAOReadException 
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.BaseDAO#getAll()
	 */
	@Override
	public List<Skill> getAll() throws DAOReadException {
		
		StringBuilder queryAll = new StringBuilder();
		queryAll.append("SELECT * FROM ").append(SKILLS);
		List<Skill> skills = new ArrayList<Skill>();

		try (Connection connection = getConnection(); 
			Statement statement = connection.createStatement(); 
			ResultSet result = statement.executeQuery(queryAll.toString())) {

			while (result.next()) {

				Skill skill = new Skill();
				skill.setId(result.getLong(ID));
				skill.setName(result.getString(NAME));
				skill.setSkillType(SkillType.fromString(result.getString(TYPE)));

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

	/**
	 * This method getting all skill from DB who match query
	 * @param query This query to DB
	 * @return List<Skill> 
	 * @throws DAOReadException 
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.BaseDAO#getAllFromSearch(java.lang.String)
	 */
	@Override
	public List<Skill> getAllFromSearch(String query) throws DAOReadException {
		
		List<Skill> skills = new ArrayList<Skill>();

		try (Connection connection = getConnection(); 
			Statement statement = connection.createStatement(); 
			ResultSet result = statement.executeQuery(query)) {

			while (result.next()) {

				Skill skill = new Skill();
				skill.setId(result.getLong(ID));
				skill.setName(result.getString(NAME));
				skill.setSkillType(SkillType.fromString(result.getString(TYPE)));

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
