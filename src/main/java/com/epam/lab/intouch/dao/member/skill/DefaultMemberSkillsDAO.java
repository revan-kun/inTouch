package com.epam.lab.intouch.dao.member.skill;

import static com.epam.lab.intouch.dao.util.FieldName.DESCRIPTION;
import static com.epam.lab.intouch.dao.util.FieldName.EXPERIENCE;
import static com.epam.lab.intouch.dao.util.FieldName.MEMBER_ID;
import static com.epam.lab.intouch.dao.util.FieldName.SELF_ASSESSED_LEVEL;
import static com.epam.lab.intouch.dao.util.FieldName.SKILL_ID;

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
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class DefaultMemberSkillsDAO extends AbstractBaseDAO<Member, String> implements MemberSkillsDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultMemberSkillsDAO.class);

	@Override
	public String create(Member member) throws DAOCreateException {

		String queryInsert = "INSERT INTO Member_Skills (member_id, skill_id, experience, description, self_assessed_level) VALUES (?,?,?,?,?)";

		List<Skill> skills = member.getSkills();

		try (Connection connection = getConnection(); 
			PreparedStatement statementCreate = connection.prepareStatement(queryInsert)) {

			for (Skill skill : skills) {
				statementCreate.setString(1, member.getLogin());
				statementCreate.setLong(2, skill.getId());
				statementCreate.setDouble(3, skill.getExperience());
				statementCreate.setString(4, skill.getDescription());
				statementCreate.setInt(5, skill.getLevel());

				statementCreate.executeUpdate();
			}

		} catch (SQLException e) {
			LOG.error("Problem with create skill", e);
			throw new DAOCreateException("Problew with create skill " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception " + e.getMessage());
		}

		return member.getLogin();
	}

	@Override
	public Member getById(String login) throws DAOReadException {
		
		String queryReadById = "SELECT * FROM Member_Skills WHERE member_id = ?";

		Member member = new Member();
		member.setLogin(login);
		
		List<Skill> skills = new ArrayList<Skill>();
		
		try (Connection connection = getConnection();
				PreparedStatement statement = prStatementMemberID(connection, queryReadById, login);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				Skill skill = new Skill();
				skill.setId(result.getLong(SKILL_ID));
				skill.setExperience(result.getDouble(EXPERIENCE));
				skill.setDescription(result.getString(DESCRIPTION));
				skill.setLevel(result.getInt(SELF_ASSESSED_LEVEL));
				
				skills.add(skill);
			}

		} catch (SQLException e) {
			LOG.error("Exception with read member skill by ID", e);
			throw new DAOReadException("Exception with read member skill by ID" + e);
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}
		
		member.setSkills(skills);
		
		return member;
	}

	@Override
	public void update(Member oldMember, Member newMember) throws DAOException {
		
		throw new UnsupportedOperationException("You can't update member skill");

	}

	@Override
	public void delete(Member member) throws DAODeleteException {

		String queryDelete = "DELETE * FROM Member_Skills WHERE member_id = ?";
		
		try (Connection connection = getConnection(); 
				PreparedStatement statement = prStatementMemberID(connection, queryDelete, member.getLogin())) {
			
				statement.executeUpdate();

			} catch (SQLException e) {
				LOG.error("Problem with delete member skill ", e );
				throw new DAODeleteException("Problem with delete member skill" + e.getMessage());
			} catch (DBConnectionException e) {
				LOG.error("Problem with conection ", e);
				throw new DAODeleteException("Problem with conection " + e.getMessage());
			}


	}

	@Override
	public List<Member> getAll() throws DAOReadException {
		
		String queryReadAll = "SELECT * FROM Member_Skills";
		
		List<Member> members = new ArrayList<Member>();
		
		try (Connection connection = getConnection(); 
				Statement statement = connection.createStatement(); 
				ResultSet result = statement.executeQuery(queryReadAll)) {

			while (result.next()) {
				Member member = new Member();
				member.setLogin(result.getString(MEMBER_ID));
				
				List<Skill> skills = getSkills(connection, member.getLogin());
				member.setSkills(skills);
				members.add(member);
			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all members with skills", e);
			throw new DAOReadException("Problem with getting all members with skills" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return members;
	}
	
	private PreparedStatement prStatementMemberID(Connection connection, String query, String parametr) throws SQLException{
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, parametr);
		
		return preparedStatement;
	}
	
	private List<Skill> getSkills(Connection connection,  String login) throws SQLException{
		
		String queryReadSkillMember = "SELECT * FROM Member_Skills WHERE member_id = ?";
		List<Skill> skills = new ArrayList<Skill>();
		
		try(PreparedStatement preparedStatement = prStatementMemberID(connection, queryReadSkillMember, login);
				ResultSet memberResult = preparedStatement.executeQuery()){

			while (memberResult.next()) {
				Skill skill = new Skill();
				skill.setId(memberResult.getLong(SKILL_ID));
				skill.setExperience(memberResult.getDouble(EXPERIENCE));
				skill.setDescription(memberResult.getString(DESCRIPTION));
				skill.setLevel(memberResult.getInt(SELF_ASSESSED_LEVEL));
				skills.add(skill);
			}
			
		} 
		
		return skills;
	}

	@Override
	public List<Member> getAllFromSearch(String query) throws DAOReadException {
		
		List<Member> members = new ArrayList<Member>();
		
		try (Connection connection = getConnection(); 
				Statement statement = connection.createStatement(); 
				ResultSet result = statement.executeQuery(query)) {

			while (result.next()) {
				Member member = new Member();
				member.setLogin(result.getString(MEMBER_ID));
				
				List<Skill> skills = getSkills(connection, member.getLogin());
				member.setSkills(skills);
				members.add(member);
			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all members with skills", e);
			throw new DAOReadException("Problem with getting all members with skills" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return members;
	}

	@Override
	public String addSkill(Member member, Skill skill) throws DAOCreateException{

		String queryAddSkill = "INSERT INTO Member_Skills (member_id, skill_id, experience, description, self_assessed_level) VALUES (?,?,?,?,?)";
		
		try (Connection connection = getConnection(); 
				PreparedStatement statementForAdd = connection.prepareStatement(queryAddSkill)) {

				statementForAdd.setString(1, member.getLogin());
				statementForAdd.setLong(2, skill.getId());
				statementForAdd.setDouble(3, skill.getExperience());
				statementForAdd.setString(4, skill.getDescription());
				statementForAdd.setInt(5, skill.getLevel());

				statementForAdd.executeUpdate();

			} catch (SQLException e) {
				LOG.error("Problem with add member skill", e);
				throw new DAOCreateException("Problew with add member skill" + e.getMessage());
			} catch (DBConnectionException e) {
				LOG.error("Connection exception", e);
				throw new DAOCreateException("Connection exception" + e.getMessage());
			}
		return member.getLogin();
	}

	@Override
	public void removeSkill(Member member, Skill skill) throws DAODeleteException{

		String queryRemove = "DELETE * From Member_Skills WHERE member_id =? AND skill_id = ?";

		try (Connection connection = getConnection(); 
			PreparedStatement statementRemove = connection.prepareStatement(queryRemove)) {
			statementRemove.setString(1, member.getLogin());
			statementRemove.setLong(2, skill.getId());

			statementRemove.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with remove member skill ", e);
			throw new DAODeleteException("Problem with remove member skill " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}
		
	}

}
