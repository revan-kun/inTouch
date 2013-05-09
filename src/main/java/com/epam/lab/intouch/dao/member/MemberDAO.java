package com.epam.lab.intouch.dao.member;

import com.epam.lab.intouch.dao.BaseDAO;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;

public interface MemberDAO extends BaseDAO<Member, String> {
	
	void updateRating(Member member) throws DAOException;	
	
}
