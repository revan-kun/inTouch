package com.epam.lab.intouch.service.like;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.LikeStatus;

public interface BaseLikeService {
	
	void addLike(Member owner, Member liker, LikeStatus status) throws DAOException;

	void removeLike(Member owner, Member liker) throws DAOException;

	void updateLike(Member owner, Member liker, LikeStatus status) throws DAOException; 
	
	LikeStatus getStatus(Member owner, Member liker) throws  DAOException;

}
