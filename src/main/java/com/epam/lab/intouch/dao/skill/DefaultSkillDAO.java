package com.epam.lab.intouch.dao.skill;

import java.util.Collection;
import java.util.List;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.PersistenceException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class DefaultSkillDAO extends AbstractBaseDAO<Member, String> implements SkillDAO {

	@Override
	public String create(Member skill) throws PersistenceException {
		return null;

	}

	@Override
	public Member getById(String id) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Member oldSkill, Member newSkill) throws PersistenceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Member entity) throws PersistenceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Member> getAll() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Skill> getAllSkilsOfMember(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
