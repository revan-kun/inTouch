package com.epam.lab.intouch.dao.skill;

import java.util.Collection;
import java.util.List;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class DefaultSkillDAO extends AbstractBaseDAO<Skill, String> implements SkillDAO {

	@Override
	public String create(Skill skill) throws DAOException {
		return null;

	}

	@Override
	public Skill getById(String id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Skill oldSkill, Skill newSkill) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Skill skill) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Skill> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Skill> getAllSkilsOfMember(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
