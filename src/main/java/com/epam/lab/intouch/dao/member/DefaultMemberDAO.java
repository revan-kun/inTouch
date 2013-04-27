package com.epam.lab.intouch.dao.member;

import static com.epam.lab.intouch.dao.member.MemberAttribute.ADDITIONAL_INFO;
import static com.epam.lab.intouch.dao.member.MemberAttribute.BIRTHDAY;
import static com.epam.lab.intouch.dao.member.MemberAttribute.EXPERIENCE;
import static com.epam.lab.intouch.dao.member.MemberAttribute.LOGIN;
import static com.epam.lab.intouch.dao.member.MemberAttribute.NAME;
import static com.epam.lab.intouch.dao.member.MemberAttribute.PASSWORD;
import static com.epam.lab.intouch.dao.member.MemberAttribute.PHOTO_LINK;
import static com.epam.lab.intouch.dao.member.MemberAttribute.QLEVEL;
import static com.epam.lab.intouch.dao.member.MemberAttribute.REGISTRATION;
import static com.epam.lab.intouch.dao.member.MemberAttribute.ROLE;
import static com.epam.lab.intouch.dao.member.MemberAttribute.SEX;
import static com.epam.lab.intouch.dao.member.MemberAttribute.SURNAME;

import java.sql.Connection;
import java.sql.Date;
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
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.exception.DAOUpdateException;
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;

public class DefaultMemberDAO extends AbstractBaseDAO<Member, String> implements MemberDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultMemberDAO.class);

	@Override
	public String create(Member member) throws DAOCreateException {

		Date birthday = new Date(member.getBirthday().getTime());
		Date registrationDate = new Date(member.getRegistrationDate().getTime());

		final String queryInsert = "INSERT INTO Member (" + MemberAttribute.getAttributes() + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

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
			statement.setString(ADDITIONAL_INFO.index(), member.getAdditionalInfo());
			statement.setString(ROLE.index(), member.getRole().toString());

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("SQLException" + e.getMessage());
			throw new DAOCreateException("Problew with create member" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}

		return member.getLogin();
	}

	@Override
	public Member getById(String login) throws DAOReadException {
		final String queryReadById = "SELECT * FROM Member Where email = '?'";
		Member member = null;

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryReadById);
				ResultSet result = statement.executeQuery()) {
			
			statement.setString(1, login);
			statement.executeUpdate();

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
	public void update(Member oldMember, Member newMember) throws DAOUpdateException {

		StringBuilder queryUpdate = new StringBuilder();
		queryUpdate.append("UPDATE Member SET ");
		queryUpdate.append(LOGIN.getName()).append("= '").append(newMember.getLogin()).append("', ");
		queryUpdate.append(PASSWORD.getName()).append("= '").append(newMember.getPassword()).append("', ");
		queryUpdate.append(NAME.getName()).append("= '").append(newMember.getFirstName()).append("', ");
		queryUpdate.append(SURNAME.getName()).append("= '").append(newMember.getLastName()).append("', ");
		queryUpdate.append(BIRTHDAY.getName()).append("= '").append(newMember.getBirthday()).append("', ");
		queryUpdate.append(REGISTRATION.getName()).append("= '").append(newMember.getRegistrationDate()).append("', ");
		queryUpdate.append(SEX.getName()).append("= '").append(newMember.getSex()).append("', ");
		queryUpdate.append(QLEVEL.getName()).append("= '").append(newMember.getQualificationLevel()).append("', ");
		queryUpdate.append(EXPERIENCE.getName()).append("= '").append(newMember.getExperience()).append("', ");
		queryUpdate.append(PHOTO_LINK.getName()).append("= '").append(newMember.getPhotoURI()).append("', ");
		queryUpdate.append(ADDITIONAL_INFO.getName()).append("= '").append(newMember.getAdditionalInfo()).append("', ");
		queryUpdate.append(ROLE.getName()).append("= '").append(newMember.getRole()).append("' ");
		queryUpdate.append("WHERE login = '").append(oldMember.getLogin()).append("'");

		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(queryUpdate.toString())) {

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with update member " + e.getMessage());
			throw new DAOUpdateException("Problem with update member " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection " + e.getMessage());
			throw new DAOUpdateException("Problem with conection " + e.getMessage());
		}
	}

	@Override
	public void delete(Member member) throws DAODeleteException {
		String queryDelete = "Delete * FROM Member WHERE login = '?'";

		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(queryDelete)) {
			statement.setString(1, member.getLogin());
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete member " + e.getMessage());
			throw new DAODeleteException("Problem with delete member " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection " + e.getMessage());
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}

	}

	@Override
	public List<Member> getAll() throws DAOReadException {
		String queryGetAll = "SELECT * FROM Member";
		List<Member> members = new ArrayList<Member>();
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryGetAll);
				ResultSet result = statement.executeQuery();) {

			while (result.next()) {
				Member member = new Member();
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
				member.setAdditionalInfo(result.getString(ADDITIONAL_INFO.getName()));
				member.setRole(Role.fromString(result.getString(ROLE.getName())));

				members.add(member);

			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all Members", e);
			throw new DAOReadException("Problem with getting all Members" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return members;
	}

}
