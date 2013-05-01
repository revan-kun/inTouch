package com.epam.lab.intouch.dao.member;

import static com.epam.lab.intouch.dao.util.FieldName.ADDITIONAL_INFO;
import static com.epam.lab.intouch.dao.util.FieldName.BIRTHDAY;
import static com.epam.lab.intouch.dao.util.FieldName.EXPERIENCE;
import static com.epam.lab.intouch.dao.util.FieldName.LOGIN;
import static com.epam.lab.intouch.dao.util.FieldName.NAME;
import static com.epam.lab.intouch.dao.util.FieldName.PASSWORD;
import static com.epam.lab.intouch.dao.util.FieldName.PHOTO_LINK;
import static com.epam.lab.intouch.dao.util.FieldName.QLEVEL;
import static com.epam.lab.intouch.dao.util.FieldName.REGISTRATION;
import static com.epam.lab.intouch.dao.util.FieldName.ROLE;
import static com.epam.lab.intouch.dao.util.FieldName.SEX;
import static com.epam.lab.intouch.dao.util.FieldName.SURNAME;

import java.sql.Connection;
import java.sql.Date;
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
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;

public class DefaultMemberDAO extends AbstractBaseDAO<Member, String> implements MemberDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultMemberDAO.class);

	@Override
	public String create(Member member) throws DAOCreateException {

		Date birthday = getBithdayDate(member);
		Date registrationDate = getRegDate(member);
		String login = null;

		final String queryInsert = "INSERT INTO Member (" + MemberAttribute.getAttributes() + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(queryInsert)) {

			statement.setString(1, member.getLogin());
			statement.setString(2, member.getPassword());
			statement.setString(3, member.getFirstName());
			statement.setString(4, member.getLastName());
			statement.setDate(5, birthday);
			statement.setDate(6, registrationDate);
			statement.setString(7, member.getSex().toString());
			statement.setString(8, member.getQualificationLevel().toString());
			statement.setDouble(9, member.getExperience());
			statement.setString(10, member.getPhotoURI().toString());
			statement.setString(11, member.getAdditionalInfo());
			statement.setString(12, member.getRole().toString());

			statement.executeUpdate();
			login = member.getLogin();

		} catch (SQLException e) {
			LOG.error("Problew with create member", e);
			throw new DAOCreateException("Problew with create member" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}

		return login;
	}
	
	private Date getBithdayDate(Member member){
		Date bithday = null;
		if (member.getBirthday() != null){
			return bithday = new Date(member.getBirthday().getTime());
		}
		return bithday;
	}
	
	private Date getRegDate(Member member){
		Date regDate = null;
		if (member.getRegistrationDate() != null){
			return regDate = new Date(member.getRegistrationDate().getTime());
		}
		return regDate;
	}

	@Override
	public Member getById(String login) throws DAOReadException {
		final String queryReadById = "SELECT * FROM Member WHERE login = ?";
		Member member = null;

		try (Connection connection = getConnection();
				PreparedStatement statement = prStatementMemberID(connection, queryReadById, login);
				ResultSet result = statement.executeQuery()) {
			
			while (result.next()) {
				member = new Member();
				member.setLogin(result.getString(LOGIN));
				member.setPassword(result.getString(PASSWORD));
				member.setFirstName(result.getString(NAME));
				member.setLastName(result.getString(SURNAME));
				member.setBirthday(result.getDate(BIRTHDAY));
				member.setRegistrationDate(result.getDate(REGISTRATION));
				member.setSex(Sex.fromString(result.getString(SEX)));
				member.setQualificationLevel(QualificationLevel.fromString(result.getString(QLEVEL)));
				member.setExperience(result.getDouble(EXPERIENCE));
				member.setPhotoURI(result.getString(PHOTO_LINK));
				member.setAdditionalInfo(result.getString(ADDITIONAL_INFO));
				member.setRole(Role.fromString(result.getString(ROLE)));
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
		queryUpdate.append(LOGIN).append("= '").append(newMember.getLogin()).append("', ");
		queryUpdate.append(PASSWORD).append("= '").append(newMember.getPassword()).append("', ");
		queryUpdate.append(NAME).append("= '").append(newMember.getFirstName()).append("', ");
		queryUpdate.append(SURNAME).append("= '").append(newMember.getLastName()).append("', ");
		queryUpdate.append(BIRTHDAY).append("= '").append(getBithdayDate(newMember)).append("', ");
		queryUpdate.append(REGISTRATION).append("= '").append(getRegDate(newMember)).append("', ");
		queryUpdate.append(SEX).append("= '").append(newMember.getSex()).append("', ");
		queryUpdate.append(QLEVEL).append("= '").append(newMember.getQualificationLevel()).append("', ");
		queryUpdate.append(EXPERIENCE).append("= '").append(newMember.getExperience()).append("', ");
		queryUpdate.append(PHOTO_LINK).append("= '").append(newMember.getPhotoURI()).append("', ");
		queryUpdate.append(ADDITIONAL_INFO).append("= '").append(newMember.getAdditionalInfo()).append("', ");
		queryUpdate.append(ROLE).append("= '").append(newMember.getRole()).append("' ");
		queryUpdate.append("WHERE login = '").append(oldMember.getLogin()).append("'");

		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(queryUpdate.toString())) {

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
		String queryDelete = "Delete FROM Member WHERE login = ?";

		try (Connection connection = getConnection(); 
			PreparedStatement statement = prStatementMemberID(connection, queryDelete, member.getLogin())) {

			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete member ", e);
			throw new DAODeleteException("Problem with delete member " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}

	}

	@Override
	public List<Member> getAll() throws DAOReadException {
		String queryGetAll = "SELECT * FROM Member";
		List<Member> members = new ArrayList<Member>();
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(queryGetAll)) {

			while (result.next()) {
				Member member = new Member();
				member.setLogin(result.getString(LOGIN));
				member.setPassword(result.getString(PASSWORD));
				member.setFirstName(result.getString(NAME));
				member.setLastName(result.getString(SURNAME));
				member.setBirthday(result.getDate(BIRTHDAY));
				member.setRegistrationDate(result.getDate(REGISTRATION));
				member.setSex(Sex.fromString(result.getString(SEX)));
				member.setQualificationLevel(QualificationLevel.fromString(result.getString(QLEVEL)));
				member.setExperience(result.getDouble(EXPERIENCE));
				member.setPhotoURI(result.getString(PHOTO_LINK));
				member.setAdditionalInfo(result.getString(ADDITIONAL_INFO));
				member.setRole(Role.fromString(result.getString(ROLE)));

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
	
	private PreparedStatement prStatementMemberID(Connection connection, String query, String parametr) throws SQLException{
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, parametr);
		
		return preparedStatement;
	}

	@Override
	public List<Member> getAllFromSearch(String query) throws DAOReadException {
		
		List<Member> members = new ArrayList<Member>();
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(query)) {

			while (result.next()) {
				Member member = new Member();
				member.setLogin(result.getString(LOGIN));
				member.setPassword(result.getString(PASSWORD));
				member.setFirstName(result.getString(NAME));
				member.setLastName(result.getString(SURNAME));
				member.setBirthday(result.getDate(BIRTHDAY));
				member.setRegistrationDate(result.getDate(REGISTRATION));
				member.setSex(Sex.fromString(result.getString(SEX)));
				member.setQualificationLevel(QualificationLevel.fromString(result.getString(QLEVEL)));
				member.setExperience(result.getDouble(EXPERIENCE));
				member.setPhotoURI(result.getString(PHOTO_LINK));
				member.setAdditionalInfo(result.getString(ADDITIONAL_INFO));
				member.setRole(Role.fromString(result.getString(ROLE)));

				members.add(member);

			}

		} catch (SQLException e) {
			LOG.error("Problem with getting all members", e);
			throw new DAOReadException("Problem with getting all members" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception" + e.getMessage());
			throw new DAOReadException("Connection exception" + e.getMessage());
		}

		return members;
	}

}
