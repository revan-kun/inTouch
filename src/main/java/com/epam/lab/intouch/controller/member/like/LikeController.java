package com.epam.lab.intouch.controller.member.like;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.member.like.DefaultLikeDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.LikeStatus;
import com.epam.lab.intouch.service.like.BaseLikeService;
import com.epam.lab.intouch.service.like.LikeService;
import com.epam.lab.intouch.service.member.BaseMemberService;
import com.epam.lab.intouch.service.member.MemberService;

/**
 * LikeController class for setting rating and status(like, dislike, dont_care)  
 * 
 * @author Ірина
 *
 */
public class LikeController {
	
	private final static Logger LOG = LogManager.getLogger(DefaultLikeDAO.class);
	private final BaseLikeService likeService;
	private final BaseMemberService memberService;

	/**
	 * Initialization required Service classes for Like Controller
	 */
	public LikeController() {

		likeService = new LikeService();
		memberService = new MemberService();

	}
	
	/**
	 * Method for getting status from DB
	 * 
	 * @param owner
	 * @param liker
	 * @exception DAOException
	 * @return statusInDB
	 */
	public LikeStatus getStatusFromDB(Member owner, Member liker) {
		LikeStatus statusInDB = null;
		try {
			 statusInDB = likeService.getStatus(owner, liker);
		} catch (DAOException e) {
			LOG.error("Problem with getting status from DB" + e);

		}
		return statusInDB;
	}

	/**
	 * Method for setting appropriate status
	 * 
	 * @param owner
	 * @param liker
	 * @param likeStatus
	 * @return owner.getRating()
	 * @throws DAOException
	 */
	public Integer setRating(Member owner, Member liker, String likeStatus) throws DAOException {
		
		LikeStatus status = LikeStatus.fromString(likeStatus);

		int rating = owner.getRating();
	

		LikeStatus statusInDB = likeService.getStatus(owner, liker);

		int tmp = countRating(statusInDB, status, rating);
		
		owner.setRating(tmp);
		memberService.updateRating(owner);

		if (statusInDB == null) {

			likeService.addLike(owner, liker, status);

		} else {

			likeService.updateLike(owner, liker, status);

		}

		if (statusInDB == status) {

		}

		return owner.getRating();
	}

	/**
	 * Method for counting rating by status
	 * 
	 * @param statusInDB
	 * @param status
	 * @param rating
	 * @return rating
	 */
	private int countRating(LikeStatus statusInDB, LikeStatus status, int rating) {

		
		if (statusInDB == null) {

			if (status == LikeStatus.LIKE) {
				rating = rating + 1;
			}

			if (status == LikeStatus.DISLIKE) {
				rating = rating - 1;
			}

			if (status == LikeStatus.DONT_CARE) {
				 ;
			}
		}

		if (statusInDB == LikeStatus.LIKE) {

			if (status == LikeStatus.DISLIKE) {
				rating = rating - 2;
			}

			if (status == LikeStatus.DONT_CARE) {
				rating = rating - 1;
			}

		}

		if (statusInDB == LikeStatus.DISLIKE) {

			if (status == LikeStatus.LIKE) {
				rating = rating + 2;
			}

			if (status == LikeStatus.DONT_CARE) {
				rating = rating + 1;

			}

		}

		if (statusInDB == LikeStatus.DONT_CARE) {

			if (status == LikeStatus.LIKE) {
				rating = rating + 1;

			}

			if (status == LikeStatus.DISLIKE) {
				rating = rating - 1;
			}

		}
		return rating;

		
	}

}
