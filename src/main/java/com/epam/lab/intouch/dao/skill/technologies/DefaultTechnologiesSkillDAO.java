package com.epam.lab.intouch.dao.skill.technologies;

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
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class DefaultTechnologiesSkillDAO extends AbstractBaseDAO<Member, String> implements TechnologiesSkillDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultTechnologiesSkillDAO.class);

	@Override
	public String create(Member member) throws DAOCreateException {

		String queryInsert = "INSERT INTO Technologies_Skills (member_id, technology, experience, description, skill_level) VALUES(?,?,?,?,?)";

		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(queryInsert);) {

			List<Skill> technologySkills = member.getTechnologySkills();

			for (Skill skill : technologySkills) {

				statement.setString(1, member.getLogin());
				statement.setString(2, skill.getName());
				statement.setDouble(3, skill.getExperience());
				statement.setString(4, skill.getDescription());
				statement.setString(5, skill.getLevel());

				statement.executeUpdate();

			}

		} catch (SQLException e) {
			LOG.error("Problew with create member technology skills", e);
			throw new DAOCreateException("Problew with create member technology skills" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}
		return member.getLogin();
	}

	@Override
	public Member getById(String login) throws DAOReadException {

		String queryGetByID = "SELECT * FROM Technologies_Skills WHERE member_id = '?'";

		Member member = new Member();
		member.setLogin(login);
		List<Skill> technologySkills = new ArrayList<Skill>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryGetByID);
				ResultSet result = statement.executeQuery()) {

			statement.setString(1, login);
			statement.executeUpdate();

			while (result.next()) {
				Skill skill = new Skill();
				skill.setName(result.getString("technology"));
				skill.setExperience(result.getDouble("expirience"));
				skill.setDescription(result.getString("description"));
				skill.setLevel(result.getString("skill_level"));

				technologySkills.add(skill);
			}
			member.setTechnologySkills(technologySkills);

		} catch (SQLException e) {
			LOG.error("Exception with read technology skills by ID member", e);
			throw new DAOReadException("Exception with read technology skills by ID member" + e);
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}
		return member;
	}

	@Override
	public void update(Member oldMember, Member newMember) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Member member) throws DAODeleteException {
		String queryDelete = "DELETE * FROM Technologies_Skills WHERE member_id = '?'";

		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(queryDelete)) {
			statement.setString(1, member.getLogin());
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete skill technology ", e);
			throw new DAODeleteException("Problem with delete skill technology " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}
	}

	@Override
	public List<Member> getAll() throws DAOReadException {

		String queryReadAll = "SELECT * FROM Technologies_Skills";
		String queryReadSkillByID = "SELECT * FROM Technologies_Skills WHERE member_id ='?'";
		List<Member> members = new ArrayList<Member>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryReadAll);
				PreparedStatement statmentID = connection.prepareStatement(queryReadSkillByID);
				ResultSet result = statement.executeQuery();
				ResultSet resultByID = statmentID.executeQuery()) {

			while (result.next()) {
				Member member = new Member();
				member.setLogin(result.getString("member_id"));

				List<Skill> technology = new ArrayList<Skill>();

				statmentID.setString(1, member.getLogin());
				statmentID.executeUpdate();

				while (resultByID.next()) {
					Skill skill = new Skill();
					skill.setName(result.getString("technology"));
					skill.setExperience(result.getDouble("expirience"));
					skill.setDescription(result.getString("description"));
					skill.setLevel(result.getString("skill_level"));

					technology.add(skill);
				}
				member.setTechnologySkills(technology);
				members.add(member);
			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all members with technology skills", e);
			throw new DAOReadException("Problem with getting all members with technology skills" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOReadException("Connection exception" + e.getMessage());
		}
		return members;
	}

}
