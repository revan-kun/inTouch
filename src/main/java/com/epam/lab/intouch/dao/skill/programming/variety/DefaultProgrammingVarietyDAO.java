package com.epam.lab.intouch.dao.skill.programming.variety;

import java.util.Collection;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;

public class DefaultProgrammingVarietyDAO extends AbstractBaseDAO<Member, String> implements ProgrammingVarietyDAO{

	@Override
	public String create(Member entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member getById(String id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Member oldEntity, Member newEntity) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Member entity) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Member> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
