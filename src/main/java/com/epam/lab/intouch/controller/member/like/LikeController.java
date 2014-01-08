package com.epam.lab.intouch.controller.member.like;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.member.like.state.Context;
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
 * @author Iryna
 * 
 */
public class LikeController {

	private final static Logger LOG = LogManager
			.getLogger(DefaultLikeDAO.class);
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
	public Integer setRating(Member owner, Member liker, String likeStatus)
			throws DAOException {

		LikeStatus chosenLikeStatus = LikeStatus.fromString(likeStatus);

		Integer rating = owner.getRating();

		LikeStatus statusInDB = likeService.getStatus(owner, liker);

		Integer tmp = countRating(statusInDB, chosenLikeStatus, rating);

		owner.setRating(tmp);
		memberService.updateRating(owner);

		if (statusInDB == null) {
			likeService.addLike(owner, liker, chosenLikeStatus);
		} else {
			likeService.updateLike(owner, liker, chosenLikeStatus);
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

	private Context getLikeContext(LikeStatus statusInDB) {
		Context context = new Context();

		if (statusInDB == LikeStatus.LIKE) {
			context.setLikedState();
		} else if (statusInDB == LikeStatus.DISLIKE) {
			context.setDislikedState();
		}

		return context;
	}

	private Context executeLikeAction(Context context,
			LikeStatus chosenLikeStatus) {
		if (chosenLikeStatus == LikeStatus.DONT_CARE) {
			context.neutralize();
		} else if (chosenLikeStatus == LikeStatus.LIKE) {
			context.like();
		} else if (chosenLikeStatus == LikeStatus.DISLIKE) {
			context.dislike();
		}

		return context;
	}

	private int countRating(LikeStatus statusInDB, LikeStatus chosenLikeStatus,
			int rating) {

		Context context = getLikeContext(statusInDB);
		context.setRating(rating);

		executeLikeAction(context, chosenLikeStatus);

		return context.getRating();
	}

}
