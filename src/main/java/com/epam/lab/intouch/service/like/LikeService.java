package com.epam.lab.intouch.service.like;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.member.like.DefaultLikeDAO;
import com.epam.lab.intouch.dao.member.like.LikeDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.LikeStatus;

public class LikeService implements BaseLikeService {
	
	private final LikeDAO likeDAO;
	
	public LikeService(){
		
		likeDAO = new DefaultLikeDAO();
	}

	@Override
	public void addLike(Member owner, Member liker, LikeStatus status) throws DAOException {
		
		likeDAO.addLike(owner, liker, status);
		
	}

	@Override
	public void removeLike(Member owner, Member liker) throws DAOException {
		
		likeDAO.removeLike(owner, liker);
		
	}

	@Override
	public void updateLike(Member owner, Member liker, LikeStatus status) throws DAOException {
		
		likeDAO.updateLike(owner, liker, status);
		
	}

	@Override
	public LikeStatus getStatus(Member owner, Member liker) throws DAOException {
		
		LikeStatus status = likeDAO.getStatus(owner, liker);
		
		return status;
	}
	
	

}
