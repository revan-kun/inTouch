package com.epam.lab.intouch.dao.team;

import java.util.Collection;
import java.util.List;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;

public class DefaulmMemberTeamDAO extends AbstractBaseDAO<Member, String> implements MemberTeamDAO {

	@Override
	public String create(Member entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member getById(String login) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Member oldMember, Member newMember) throws DAOException {
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

	@Override
	public List<Long> getProjectID(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
