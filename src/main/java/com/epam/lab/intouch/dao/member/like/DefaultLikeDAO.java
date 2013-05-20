package com.epam.lab.intouch.dao.member.like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAODeleteException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.exception.DAOUpdateException;
import com.epam.lab.intouch.dao.exception.DBConnectionException; 
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.LikeStatus;
import static com.epam.lab.intouch.util.db.metadata.TableName.*;
import static com.epam.lab.intouch.util.db.metadata.FieldName.*;

/**
 * Class for manipulating data in Member_Likes table
 * @author Molodec
 *
 */
public class DefaultLikeDAO extends AbstractBaseDAO<Member, String> implements LikeDAO{
	
	private final static Logger LOG = LogManager.getLogger(DefaultLikeDAO.class);

	@Override
	public String create(Member member) throws DAOException {
		
		throw new UnsupportedOperationException("You can't use this method to like");
		
	}

	@Override
	public Member getById(String id) throws DAOException {
		
		throw new UnsupportedOperationException("You can't use this method to like");
	}

	@Override
	public void update(Member oldEntity, Member newEntity) throws DAOException {

		throw new UnsupportedOperationException("You can't use this method to like");
	}

	@Override
	public void delete(Member member) throws DAOException {

		throw new UnsupportedOperationException("You can't use this method to like");
	}

	@Override
	public Collection<Member> getAll() throws DAOException {

		throw new UnsupportedOperationException("You can't use this method to like");
	}

	@Override
	public Collection<Member> getAllFromSearch(String query)
			throws DAOException {

		throw new UnsupportedOperationException("You can't use this method to like");
	}

	@Override
	public void addLike(Member owner, Member liker, LikeStatus status) throws DAOCreateException {
		
		StringBuilder queryAdd = new StringBuilder();
		queryAdd.append("INSERT INTO ").append(MEMBER_LIKES).append(" (");
		queryAdd.append(OWNER_LOGIN).append(", ").append(LIKER_LOGIN).append(", ");
		queryAdd.append(LIKE).append(") ").append(" VALUES(?,?,?)");
		
		try (Connection connection = getConnection(); 
				PreparedStatement statementForAdd = connection.prepareStatement(queryAdd.toString())) {

				statementForAdd.setString(1, owner.getLogin());
				statementForAdd.setString(2, liker.getLogin());
				statementForAdd.setString(3, status.toString());

				statementForAdd.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with add like", e);
			throw new DAOCreateException("Problew with add like" + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Connection exception", e);
			throw new DAOCreateException("Connection exception" + e.getMessage());
		}
		
	}

	@Override
	public void removeLike(Member owner, Member liker) throws DAODeleteException {
		
		StringBuilder queryRemove = new StringBuilder();
		queryRemove.append("DELETE FROM ").append(MEMBER_LIKES).append(" WHERE ");
		queryRemove.append(OWNER_LOGIN).append("=? ").append(" AND ");
		queryRemove.append(LIKER_LOGIN).append("= ? ");
		
		try (Connection connection = getConnection(); 
				PreparedStatement statementRemove = connection.prepareStatement(queryRemove.toString())) {
				statementRemove.setString(1, owner.getLogin());
				statementRemove.setString(2, liker.getLogin());
				statementRemove.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Problem with remove like ", e);
			throw new DAODeleteException("Problem with remove like " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAODeleteException("Problem with conection " + e.getMessage());
		}
	}

	@Override
	public void updateLike(Member owner, Member liker, LikeStatus status) throws DAOUpdateException {
		
		StringBuilder queryUpdate = new StringBuilder();
		queryUpdate.append("UPDATE ").append(MEMBER_LIKES).append(" SET ");
		queryUpdate.append(LIKE).append("=? ");
		queryUpdate.append(" WHERE ");
		queryUpdate.append(OWNER_LOGIN).append("=? AND ");
		queryUpdate.append(LIKER_LOGIN).append("= ? ");
		
		try(Connection connection = getConnection();
				PreparedStatement statementUpdate = connection.prepareStatement(queryUpdate.toString())){
			
			statementUpdate.setString(1, status.toString());
			statementUpdate.setString(2, owner.getLogin());
			statementUpdate.setString(3, liker.getLogin());
			statementUpdate.executeUpdate();
			
		} catch (SQLException e) {
			LOG.error("Problem with update status like ", e);
			throw new DAOUpdateException("Problem with update status like " + e.getMessage());
		} catch (DBConnectionException e) {
			LOG.error("Problem with conection ", e);
			throw new DAOUpdateException("Problem with conection " + e.getMessage());
		}
				
	}

}
