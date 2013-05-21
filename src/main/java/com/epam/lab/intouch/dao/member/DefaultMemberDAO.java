package com.epam.lab.intouch.dao.member;

import static com.epam.lab.intouch.util.db.metadata.FieldName.ADDITIONAL_INFO;
import static com.epam.lab.intouch.util.db.metadata.FieldName.BIRTHDAY;
import static com.epam.lab.intouch.util.db.metadata.FieldName.EXPERIENCE;
import static com.epam.lab.intouch.util.db.metadata.FieldName.LOGIN;
import static com.epam.lab.intouch.util.db.metadata.FieldName.NAME;
import static com.epam.lab.intouch.util.db.metadata.FieldName.PASSWORD;
import static com.epam.lab.intouch.util.db.metadata.FieldName.PHOTO_LINK;
import static com.epam.lab.intouch.util.db.metadata.FieldName.QLEVEL;
import static com.epam.lab.intouch.util.db.metadata.FieldName.RATING;
import static com.epam.lab.intouch.util.db.metadata.FieldName.REGISTRATION;
import static com.epam.lab.intouch.util.db.metadata.FieldName.ROLE;
import static com.epam.lab.intouch.util.db.metadata.FieldName.SEX;
import static com.epam.lab.intouch.util.db.metadata.FieldName.SURNAME;
import static com.epam.lab.intouch.util.db.metadata.TableName.MEMBER;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;

/**
 * In this class we do operation with member create him in DB delete,
 * update, get all from DB, get member by his login. 
 * 
 * @author Molodec
 *
 */
public class DefaultMemberDAO extends AbstractBaseDAO<Member, String> implements MemberDAO {

	private final static Logger LOG = LogManager.getLogger(DefaultMemberDAO.class);
	
	/** 
	 * This method is create member in DB
	 * @see com.epam.lab.intouch.dao.BaseDAO#create(java.lang.Object)
	 * @param member
	 * @throws DAOCreateException 
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @return login
	 */
	@Override
	public String create(Member member) throws DAOCreateException {

		String login = null;
		
		StringBuilder queryInsert = new StringBuilder();
		queryInsert.append("INSERT INTO ").append(MEMBER).append(" (");
		queryInsert.append(LOGIN).append(", ").append(PASSWORD).append(", ");
		queryInsert.append(NAME).append(", ").append(SURNAME).append(", ");
		queryInsert.append(BIRTHDAY).append(", ").append(REGISTRATION).append(", ");
		queryInsert.append(SEX).append(", ").append(QLEVEL).append(", ");
		queryInsert.append(EXPERIENCE).append(", ").append(PHOTO_LINK).append(", ");
		queryInsert.append(ADDITIONAL_INFO).append(", ").append(RATING).append(", ");
		queryInsert.append(ROLE).append(") ");
		queryInsert.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		try (Connection connection = getConnection(); 
				PreparedStatement statement = connection.prepareStatement(queryInsert.toString())) {

			statement.setString(1, member.getLogin());
			statement.setString(2, member.getPassword());
			statement.setString(3, member.getFirstName());
			statement.setString(4, member.getLastName());
		
			statement.setNull(5, Types.DATE);
			
			statement.setDate(6, getRegDate(member));
			statement.setString(7, checkSexNull(member.getSex()));
			statement.setString(8, checkQLevelNull(member.getQualificationLevel()));
			statement.setDouble(9, checkDoubleOnNull(member.getExperience()));
			statement.setString(10, member.getPhotoLink());
			statement.setString(11, member.getAdditionalInfo());
			statement.setInt(12, checkIntOnNull(member.getRating()));
			statement.setString(13, checkRoleNull(member.getRole()));
		
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
	
	/**
	 * Check integer value on NULL before entering into a DB
	 * @param value
	 * @return value if value not null otherwise return 0
	 */
	private Integer checkIntOnNull(Integer value){
		Integer parameter = 0;
		if (value != null){
			return value;
		}else{
			return parameter;
		}
	}
	
	/**
	 * Check member Sex on NULL before entering into a DB
	 * @param sex
	 * @return sex.toString() if sex != NULL otherwise null
	 */
	private String checkSexNull(Sex sex){
				
		if (sex != null){
			return sex.toString();
		}
		return null;
	}
	
	/**
	 * Check member Role on NULL before entering into a DB
	 * @param role
	 * @return role.toString() if role != NULL otherwise null
	 */
	private String checkRoleNull(Role role){
		if (role != null){
			return role.toString();
		}
		return null;
	}
	
	/**
	 * Check member qualification level on NULL before entering into a DB
	 * @param qlevel
	 * @return qlevel.toString() if qlevel != NULL otherwise null
	 */
	private String checkQLevelNull(QualificationLevel qlevel){
		if(qlevel != null){
			return qlevel.toString();
		}
		return null;
	}
	
	/**
	 * Check double value on NULL before entering into a DB
	 * @param value
	 * @return value if value not null otherwise return 0.0
	 */
	private Double checkDoubleOnNull(Double value){
		
		Double parametr = 0.0;
		
		if (value != null) {
			return value;
		} else {
			return parametr;
		}
	}

	/**
	 * Check member birthday date on NULL before entering into a DB
	 * @param member
	 * @return birthday type sql.Date
	 */
	private Date getBirthdayDate(Member member) {
		
		Date birthday = null;
		
		if (member.getBirthday() != null) {
			return birthday = new Date(member.getBirthday().getTime());
		}
		return birthday;
	}
	
	/**
	 * Check member registration date on NULL before entering into a DB
	 * @param member
	 * @return regDate type sql.Date
	 */

	private Date getRegDate(Member member) {
		
		Date regDate = null;
		if (member.getRegistrationDate() != null) {
			return regDate = new Date(member.getRegistrationDate().getTime());
		}
		return regDate;
	}
	
	/** 
	 * This method is get member by id from DB
	 * @see com.epam.lab.intouch.dao.BaseDAO#getById(java.lang.Object)
	 * @param login
	 * @throws DAOReadException 
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @return Member
	 */
	@Override
	public Member getById(String login) throws DAOReadException {

		StringBuilder queryReadById = new StringBuilder();
		queryReadById.append("SELECT * FROM ").append(MEMBER).append(" WHERE ").append(LOGIN).append("='");
		queryReadById.append(login).append("'");
		Member member = null;

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(queryReadById.toString());
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
				member.setPhotoLink(result.getString(PHOTO_LINK));
				member.setAdditionalInfo(result.getString(ADDITIONAL_INFO));
				member.setRating(result.getInt(RATING));
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

	/**
	 * Method for update member with old value on new value in DB
	 * @see com.epam.lab.intouch.dao.BaseDAO#update(java.lang.Object, java.lang.Object)
	 * @param oldMember member with old value
	 * @param newMember member with new value
	 * @throws DAOUpdateException 
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 */
	@Override
	public void update(Member oldMember, Member newMember) throws DAOUpdateException {

		StringBuilder queryUpdate = new StringBuilder();
		queryUpdate.append("UPDATE ").append(MEMBER).append(" SET ");
		queryUpdate.append(LOGIN).append("= ?, ");
		queryUpdate.append(PASSWORD).append("= ?, ");
		queryUpdate.append(NAME).append("= ?, ");
		queryUpdate.append(SURNAME).append("= ?, ");
		queryUpdate.append(BIRTHDAY).append("= ?, ");
		queryUpdate.append(SEX).append("= ?, ");
		queryUpdate.append(QLEVEL).append("= ?, ");
		queryUpdate.append(EXPERIENCE).append("= ?, ");
		queryUpdate.append(ADDITIONAL_INFO).append("= ?, ");
		queryUpdate.append(PHOTO_LINK).append("= ?, ");
		queryUpdate.append(RATING).append("=?, ");
		queryUpdate.append(ROLE).append("= ? ");
		queryUpdate.append(" WHERE ").append(LOGIN).append("= ?");

		try (Connection connection = getConnection(); 
				PreparedStatement statement = connection.prepareStatement(queryUpdate.toString())) {
			
			statement.setString(1, newMember.getLogin());
			statement.setString(2, newMember.getPassword());
			statement.setString(3, newMember.getFirstName());
			statement.setString(4, newMember.getLastName());
			statement.setDate(5, getBirthdayDate(newMember));
			statement.setString(6, newMember.getSex().toString());
			statement.setString(7, newMember.getQualificationLevel().toString());
			statement.setDouble(8, newMember.getExperience());
			statement.setString(9, newMember.getAdditionalInfo());
			statement.setString(10, newMember.getPhotoLink());
			statement.setInt(11, newMember.getRating());
			statement.setString(12, newMember.getRole().toString());
			statement.setString(13, oldMember.getLogin());
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with update member ", e);
			throw new DAOUpdateException("Problem with update member " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAOUpdateException("Problem with conection " + e.getMessage());
		}
	}

	/**
	 * Method for delete member in DB
	 * @param member 
	 * @throws DAODeleteException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.BaseDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Member member) throws DAODeleteException {

		StringBuilder queryDelete = new StringBuilder();
		queryDelete.append("DELETE FROM ").append(MEMBER).append(" WHERE ").append(LOGIN).append("= ?");

		try (Connection connection = getConnection(); 
				PreparedStatement statement =connection.prepareStatement(queryDelete.toString())) {
			
			statement.setString(1, member.getLogin());
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with delete member ", e);
			throw new DAODeleteException("Problem with delete member " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}

	}

	/**
	 * Method for getting all member from DB 
	 * @see com.epam.lab.intouch.dao.BaseDAO#getAll()
	 * @return List<Member>  
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @throws DAOReadException
	 */
	@Override
	public List<Member> getAll() throws DAOReadException {
		
		StringBuilder queryGetAll = new StringBuilder();
		queryGetAll.append("SELECT * FROM ").append(MEMBER);
		
		List<Member> members = new ArrayList<Member>();
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(queryGetAll.toString())) {

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
				member.setPhotoLink(result.getString(PHOTO_LINK));
				member.setAdditionalInfo(result.getString(ADDITIONAL_INFO));
				member.setRating(result.getInt(RATING));
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
	
	/**
	 * Method for getting all member from DB who match the query
	 * @see com.epam.lab.intouch.dao.BaseDAO#getAll()
	 * @param query This is query to DB
	 * @return List<Member> 
	 * @throws DAOReadException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 */
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
				member.setPhotoLink(result.getString(PHOTO_LINK));
				member.setAdditionalInfo(result.getString(ADDITIONAL_INFO));
				member.setRating(result.getInt(RATING));
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

	/**
	 * Method for updating member rating in DB
	 * @param member
	 * @throws DAOUpdateException
	 * @exception SQLException if problem in SQL query or other
	 * @exception DBConnectionException if problem with connection
	 * @see com.epam.lab.intouch.dao.member.MemberDAO#updateRating(com.epam.lab.intouch.model.member.Member)
	 */
	@Override
	public void updateRating(Member member) throws DAOUpdateException {
		
		StringBuilder queryUpdate = new StringBuilder();
		queryUpdate.append("UPDATE ").append(MEMBER).append(" SET ");
		queryUpdate.append(RATING).append("=").append(member.getRating());
		queryUpdate.append(" WHERE ").append(LOGIN).append(" = ? ");
		
		try (Connection connection = getConnection(); 
				PreparedStatement statement = connection.prepareStatement(queryUpdate.toString())) {
			
			statement.setString(1, member.getLogin());
			statement.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with update member rating ", e);
			throw new DAOUpdateException("Problem with update member rating " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAOUpdateException("Problem with conection " + e.getMessage());
		}
	
	}

}
