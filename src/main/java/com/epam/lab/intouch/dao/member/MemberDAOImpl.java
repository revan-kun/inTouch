package com.epam.lab.intouch.dao.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.exception.PersistenceException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;

public class MemberDAOImpl extends AbstractBaseDAO<Member, String> implements
		MemberDAO {
	private final static Logger LOG = Logger.getLogger(MemberDAOImpl.class);

	@Override
	public void create(Member member) throws DAOCreateException {
		Connection connection = null;
		PreparedStatement statement = null;

		String firstName = member.getFirstName();
		String lastName = member.getLastName();
		String sex = member.getSex().toString();
		String login = member.getLogin();
		Date birthday = (Date) member.getBirthday().getTime();
		Date registrationDate = (Date) member.getRegistrationDate().getTime();
		String qLevel = member.getQualificationLevel().toString();
		Double experience = member.getExperience();
		String photoURI = member.getPhotoURI().toString();
		String projectRole = member.getRole().toString();

		String queryInsert = "INSERT INTO Member (email, login, password, name, surname, birthday, registration, sex, qlevel, experience, photo_link, role) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			connection = getConnection();
			statement = connection.prepareStatement(queryInsert);

			statement.setString(1, login);
			statement.setString(2, login);
			statement.setString(3, null); // must be a password
			statement.setString(4, firstName);
			statement.setString(5, lastName);
			statement.setDate(6, birthday);
			statement.setDate(7, registrationDate);
			statement.setString(8, sex);
			statement.setString(9, qLevel);
			statement.setDouble(10, experience);
			statement.setString(11, photoURI);
			statement.setString(12, projectRole);

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("SQLException" + e.getMessage());
			throw new DAOCreateException("Problew with create" + e.getMessage());
		}
	}

	@Override
	public Member getById(String id) throws DAOReadException {
		String queryReadById = "SELECT * FROM Member Where email = '" + id
				+ "'";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Member member = null;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(queryReadById);
			result = statement.executeQuery();

			while (result.next()) {
				String login = result.getString("login");
				String firstName = result.getString("name");
				String lastName = result.getString("surname");
				// Calendar birthday = result.getDate("birthday");
				// registration = result.getDate("registration");
				String sex = result.getString("sex");
				String qLevel = result.getString("qlevel");
				Double experience = result.getDouble("experience");
				String photoLink = result.getString("photo_link");
				String role = result.getString("role");

				member = new Member();
				member.setLogin(login);
				member.setFirstName(firstName);
				member.setLastName(lastName);
				// member.setBirthday(birthday);
				// member.setRegistrationDate(registrationDate);
				member.setSex(Sex.fromString(sex));
				member.setQualificationLevel(QualificationLevel
						.fromString(qLevel));
				member.setExperience(experience);
				// member.setPhotoURI(photoLink);
				member.setRole(Role.fromString(role));

			}

		} catch (SQLException e) {
			LOG.error("Exception with read Member by ID" + e.getMessage());
			throw new DAOReadException("Problem with red member by ID"
					+ e.getMessage());
		}
		return member;
	}

	@Override
	public void update(Member oldEntity, Member newEntity)
			throws PersistenceException {

	}

	@Override
	public void delete(Member member) throws PersistenceException {

	}

	@Override
	public Collection<Member> getAll() throws PersistenceException {
		return null;
	}

}
