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

	public Integer setRating(Member owner, Member liker, String likeStatus) throws DAOException {
		
		LikeStatus status = LikeStatus.fromString(likeStatus);

		int rating = owner.getRating();
		//int result = 0;

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
