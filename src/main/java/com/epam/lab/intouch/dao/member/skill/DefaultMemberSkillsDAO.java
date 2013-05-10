package com.epam.lab.intouch.dao.member.skill;

import static com.epam.lab.intouch.util.db.metadata.FieldName.DESCRIPTION;
import static com.epam.lab.intouch.util.db.metadata.FieldName.EXPERIENCE;
import static com.epam.lab.intouch.util.db.metadata.FieldName.MEMBER_ID;
import static com.epam.lab.intouch.util.db.metadata.FieldName.SELF_ASSESSED_LEVEL;
import static com.epam.lab.intouch.util.db.metadata.FieldName.SKILL_ID;
import static com.epam.lab.intouch.util.db.metadata.TableName.MEMBER_SKILLS;

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
import com.epam.lab.intouch.dao.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class DefaultMemberSkillsDAO extends AbstractBaseDAO<Member, String> implements MemberSkillsDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultMemberSkillsDAO.class);

	@Override
	public String create(Member member) throws DAOCreateException {

		StringBuilder queryInsert = new StringBuilder();
		queryInsert.append("INSERT INTO ").append(MEMBER_SKILLS);
		queryInsert.append(" (").append(MEMBER_ID).append(", ").append(SKILL_ID).append(", ");
		queryInsert.append(EXPERIENCE).append(", ").append(DESCRIPTION).append(", ");
		queryInsert.append(SELF_ASSESSED_LEVEL);
		queryInsert.append(" VALUES(?,?,?,?,?)");
		
		List<Skill> skills = member.getSkills();

		if (skills != null) {

			try (Connection connection = getConnection(); 
					PreparedStatement statementCreate = connection.prepareStatement(queryInsert.toString())) {

				for (Skill skill : skills) {
					statementCreate.setString(1, member.getLogin());
					statementCreate.setLong(2, skill.getId());
					statementCreate.setDouble(3, skill.getExperience());
					statementCreate.setString(4, skill.getDescription());
					statementCreate.setInt(5, skill.getLevel());

				}
				statementCreate.executeUpdate();

			} catch (SQLException e) {
				LOG.error("Problem with create skill", e);
				throw new DAOCreateException("Problew with create skill " + e.getMessage());
			} catch (DBConnectionException e) {
				LOG.error("Connection exception", e);
				throw new DAOCreateException("Connection exception " + e.getMessage());
			}
		}
		return member.getLogin();
	}

	@Override
	public Member getById(String login) throws DAOReadException {
		
		StringBuilder queryReadById = new StringBuilder();
		queryReadById.append("SELECT * FROM ").append(MEMBER_SKILLS);
		queryReadById.append(" WHERE ").append(MEMBER_ID).append("='").append(login).append("'");

		Member member = new Member();
		member.setLogin(login);

		List<Skill> skills = new ArrayList<Skill>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryReadById.toString());
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
		
		StringBuilder queryDelete = new StringBuilder();
		queryDelete.append("DELETE FROM ").append(MEMBER_SKILLS).append(" WHERE ").append(MEMBER_ID).append("='");
		queryDelete.append(member.getLogin()).append("'");
		
		try (Connection connection = getConnection(); 
				PreparedStatement statement = connection.prepareStatement(queryDelete.toString())) {

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete member skill ", e);
			throw new DAODeleteException("Problem with delete member skill" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}

	}

	@Override
	public List<Member> getAll() throws DAOReadException {
		
		StringBuilder queryReadAll = new StringBuilder();
		queryReadAll.append("SELECT DISTINCT ").append(MEMBER_ID).append(" FROM ").append(MEMBER_SKILLS);

		List<Member> members = new ArrayList<Member>();

		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(queryReadAll.toString())) {

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

	private List<Skill> getSkills(Connection connection, String login) throws SQLException {
		
		StringBuilder queryReadSkillMember = new StringBuilder();
		queryReadSkillMember.append("SELECT DISTINCT * FROM ").append(MEMBER_SKILLS);
		queryReadSkillMember.append(" WHERE ").append(MEMBER_ID).append("='").append(login).append("'");
		
		List<Skill> skills = new ArrayList<Skill>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(queryReadSkillMember.toString());
				ResultSet memberResult = preparedStatement.executeQuery()) {

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
	public String addSkill(Member member, Skill skill) throws DAOCreateException {

		StringBuilder queryAddSkill = new StringBuilder();
		queryAddSkill.append("INSERT INTO ").append(MEMBER_SKILLS);
		queryAddSkill.append(" (").append(MEMBER_ID).append(", ").append(SKILL_ID).append(", ");
		queryAddSkill.append(EXPERIENCE).append(", ").append(DESCRIPTION).append(", ");
		queryAddSkill.append(SELF_ASSESSED_LEVEL).append(")");
		queryAddSkill.append(" VALUES(?,?,?,?,?)");
		
		try (Connection connection = getConnection(); 
				PreparedStatement statementForAdd = connection.prepareStatement(queryAddSkill.toString())) {

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
	public void removeSkill(Member member, Skill skill) throws DAODeleteException {

		StringBuilder queryRemove = new StringBuilder();
		queryRemove.append("DELETE FROM ").append(MEMBER_SKILLS).append(" WHERE ").append(MEMBER_ID).append("=? ");
		queryRemove.append("AND ").append(SKILL_ID).append("=?");

		try (Connection connection = getConnection(); 
				PreparedStatement statementRemove = connection.prepareStatement(queryRemove.toString())) {
			
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
