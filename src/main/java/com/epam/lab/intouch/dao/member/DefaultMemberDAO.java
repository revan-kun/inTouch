package com.epam.lab.intouch.dao.member;

import static com.epam.lab.intouch.dao.member.Attribute.BIRTHDAY;
import static com.epam.lab.intouch.dao.member.Attribute.EXPERIENCE;
import static com.epam.lab.intouch.dao.member.Attribute.LOGIN;
import static com.epam.lab.intouch.dao.member.Attribute.NAME;
import static com.epam.lab.intouch.dao.member.Attribute.PASSWORD;
import static com.epam.lab.intouch.dao.member.Attribute.PHOTO_LINK;
import static com.epam.lab.intouch.dao.member.Attribute.QLEVEL;
import static com.epam.lab.intouch.dao.member.Attribute.REGISTRATION;
import static com.epam.lab.intouch.dao.member.Attribute.ROLE;
import static com.epam.lab.intouch.dao.member.Attribute.SEX;
import static com.epam.lab.intouch.dao.member.Attribute.SURNAME;

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
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;

public class DefaultMemberDAO extends AbstractBaseDAO<Member, String> implements MemberDAO {

	private final static Logger LOG = Logger.getLogger(DefaultMemberDAO.class);

	@Override
	public String create(Member member) throws DAOCreateException {

		Date birthday = new Date(member.getBirthday().getTime());
		Date registrationDate = new Date(member.getRegistrationDate().getTime());

		final String queryInsert = "INSERT INTO Member (" + Attribute.getAttributes() + ") VALUES (?,?,?,?,?,?,?,?,?,?,?);";

		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(queryInsert);) {

			statement.setString(LOGIN.index(), member.getLogin());
			statement.setString(PASSWORD.index(), member.getPassword());
			statement.setString(NAME.index(), member.getFirstName());
			statement.setString(SURNAME.index(), member.getLastName());
			statement.setDate(BIRTHDAY.index(), birthday);
			statement.setDate(REGISTRATION.index(), registrationDate);
			statement.setString(SEX.index(), member.getSex().toString());
			statement.setString(QLEVEL.index(), member.getQualificationLevel().toString());
			statement.setDouble(EXPERIENCE.index(), member.getExperience());
			statement.setString(PHOTO_LINK.index(), member.getPhotoURI().toString());
			statement.setString(ROLE.index(), member.getRole().toString());

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("SQLException" + e.getMessage());
			throw new DAOCreateException("Problew with create" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}

		return member.getLogin();
	}

	@Override
	public Member getById(String id) throws DAOReadException {
		final String queryReadById = "SELECT * FROM Member Where email = '" + id + "'";
		Member member = null;

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryReadById);
				ResultSet result = statement.executeQuery();) {

			while (result.next()) {
				member = new Member();
				member.setLogin(result.getString(LOGIN.getName()));
				member.setPassword(result.getString(PASSWORD.getName()));
				member.setFirstName(result.getString(NAME.getName()));
				member.setLastName(result.getString(SURNAME.getName()));
				member.setBirthday(result.getDate(BIRTHDAY.getName()));
				member.setRegistrationDate(result.getDate(REGISTRATION.getName()));
				member.setSex(Sex.fromString(result.getString(SEX.getName())));
				member.setQualificationLevel(QualificationLevel.fromString(result.getString(QLEVEL.getName())));
				member.setExperience(result.getDouble(EXPERIENCE.getName()));
				member.setPhotoURI(result.getString(PHOTO_LINK.getName()));
				member.setRole(Role.fromString(result.getString(ROLE.getName())));
			}

		} catch (SQLException e) {
			LOG.error("Exception with read Member by ID", e);
			throw new DAOReadException("Exception with read Member by ID" + e);
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return member;
	}

	@Override
	public void update(Member oldMember, Member newMember) throws PersistenceException {

	}

	@Override
	public void delete(Member member) throws PersistenceException {

	}

	@Override
	public Collection<Member> getAll() throws PersistenceException {
		return null;
	}

}
