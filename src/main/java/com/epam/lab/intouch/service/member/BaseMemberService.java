package com.epam.lab.intouch.service.member;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;

public interface BaseMemberService {
	
	String create(Member member) throws DAOException;
	
	Member getById(String login) throws DAOException;
	
	void update(Member oldMember, Member newMember) throws DAOException;
	
	void delete(Member member) throws DAOException;
	
	List<Member> getAll() throws DAOException;
	
	List<Member> getAllFromSearch(String query) throws DAOException;
	
	void updateRating(Member member) throws DAOException;
	
	

}
