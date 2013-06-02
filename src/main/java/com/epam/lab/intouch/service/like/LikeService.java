package com.epam.lab.intouch.service.like;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.member.like.DefaultLikeDAO;
import com.epam.lab.intouch.dao.member.like.LikeDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.LikeStatus;

/**
 * LikeService class combine different method from DAO class to get object
 * 
 * @author Iryna
 * 
 */
public class LikeService implements BaseLikeService {

	private final LikeDAO likeDAO;

	/**
	 * Initialization required DAO classes for Like Service
	 */
	public LikeService() {

		likeDAO = new DefaultLikeDAO();
	}

	/**
	 * Method for adding a "like status" from liker to content owner
	 * 
	 * @param owner
	 * @param liker
	 * @param status
	 * @throws DAOException
	 */
	@Override
	public void addLike(Member owner, Member liker, LikeStatus status) throws DAOException {

		likeDAO.addLike(owner, liker, status);

	}

	/**
	 * Method for removing a previously set "like status" by liker to content
	 * owner
	 * 
	 * @param owner
	 * @param liker
	 * @throws DAOException
	 */
	@Override
	public void removeLike(Member owner, Member liker) throws DAOException {

		likeDAO.removeLike(owner, liker);

	}

	/**
	 * Method for updating a previously set "like status" by liker to content
	 * owner
	 * 
	 * @param owner
	 * @param liker
	 * @param status
	 * @throws DAOException
	 */
	@Override
	public void updateLike(Member owner, Member liker, LikeStatus status) throws DAOException {

		likeDAO.updateLike(owner, liker, status);

	}

	/**
	 * Method for getting a previously set "like status" by liker to content
	 * owner
	 * 
	 * @param owner
	 * @param liker
	 * @return status
	 * @throws DAOException
	 */
	@Override
	public LikeStatus getStatus(Member owner, Member liker) throws DAOException {

		LikeStatus status = likeDAO.getStatus(owner, liker);

		return status;
	}

}
