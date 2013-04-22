package com.epam.lab.intouch.dao.member;

import java.util.Collection;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.PersistenceException;
import com.epam.lab.intouch.model.member.Member;

public class MemberDAOImpl extends AbstractBaseDAO<Member, String> implements
		MemberDAO {

	@Override
	public void create(Member member) throws PersistenceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Member getById(String id) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Member oldEntity, Member newEntity)
			throws PersistenceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Member member) throws PersistenceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Member> getAll() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}
