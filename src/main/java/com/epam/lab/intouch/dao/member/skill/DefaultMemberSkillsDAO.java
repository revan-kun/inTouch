package com.epam.lab.intouch.dao.member.skill;



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

public class DefaultMemberSkillsDAO extends AbstractBaseDAO<Member, String> implements MemberSkillsDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultMemberSkillsDAO.class);

	@Override
	public String create(Member member) throws DAOCreateException {

		String queryInsert = "INSERT INTO MemberSkills (member_id, skill_id) VALUES (?,?)";

		List<Skill> skills = member.getSkills();

		try (Connection connection = getConnection(); 
			PreparedStatement statementCreate = connection.prepareStatement(queryInsert)) {

			for (Skill skill : skills) {
				statementCreate.setString(1, member.getLogin());
				statementCreate.setLong(2, skill.getId());

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
		
		String queryReadById = "SELECT * FROM MemberSkill WHERE member_id = ?";

		Member member = new Member();
		member.setLogin(login);
		
		List<Skill> skills = new ArrayList<Skill>();
		
		try (Connection connection = getConnection();
				PreparedStatement statement = prStatementMemberID(connection, queryReadById, login);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				Skill skill = new Skill();
				skill.setId(result.getLong("id"));
				skills.add(skill);
			}

		} catch (SQLException e) {
			LOG.error("Exception with read member by ID", e);
			throw new DAOReadException("Exception with read member by ID" + e);
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}
		
		member.setSkills(skills);
		
		return member;
	}

	@Override
	public void update(Member oldMember, Member newMember) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Member member) throws DAOException {

		String queryDelete = "DELETE * FROM MemberSkills WHERE member_id = ?";
		
		try (Connection connection = getConnection(); 
				PreparedStatement statement = connection.prepareStatement(queryDelete)) {
				
				statement.setString(1, member.getLogin());

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
	public List<Member> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private PreparedStatement prStatementMemberID(Connection connection, String query, String parametr) throws SQLException{
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, parametr);
		
		return preparedStatement;
	}

}
