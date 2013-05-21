package com.epam.lab.intouch.controller.member.like;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.LikeStatus;
import com.epam.lab.intouch.service.like.BaseLikeService;
import com.epam.lab.intouch.service.like.LikeService;
import com.epam.lab.intouch.service.member.BaseMemberService;
import com.epam.lab.intouch.service.member.MemberService;

public class LikeControler {

	private final BaseLikeService likeService;
	private final BaseMemberService memberService;

	public LikeControler() {

		likeService = new LikeService();
		memberService = new MemberService();

	}

	public Integer setRating(Member owner, Member liker, LikeStatus status) throws DAOException {

		int rating = owner.getRating();
		int result = 0;

		LikeStatus statusInDB = likeService.getStatus(owner, liker);

		result = countRating(statusInDB, status, rating);
		owner.setRating(result);
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

	private Integer countRating(LikeStatus statusInDB, LikeStatus status, int rating) {

		int result = 0;
		if (statusInDB == null) {

			if (status == LikeStatus.LIKE) {
				result = rating + 1;
			}

			if (status == LikeStatus.DISLIKE) {
				result = rating - 1;
			}

			if (status == LikeStatus.DONT_CARE) {
				result = rating;
			}
		}

		if (statusInDB == LikeStatus.LIKE) {

			if (status == LikeStatus.DISLIKE) {
				result = rating - 2;
			}

			if (status == LikeStatus.DONT_CARE) {
				result = rating - 1;
			}

		}

		if (statusInDB == LikeStatus.DISLIKE) {

			if (status == LikeStatus.LIKE) {
				result = rating + 2;
			}

			if (status == LikeStatus.DONT_CARE) {
				result = rating + 1;

			}

		}

		if (statusInDB == LikeStatus.DONT_CARE) {

			if (status == LikeStatus.LIKE) {
				result = rating + 1;

			}

			if (status == LikeStatus.DISLIKE) {
				result = rating - 1;
			}

		}

		return result;
	}

}
