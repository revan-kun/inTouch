package com.epam.lab.intouch.dao.member.skill;

import java.util.List;

import com.epam.lab.intouch.dao.AbstractBaseDAO;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class DefaultSkillDAO extends AbstractBaseDAO<Skill, Long> implements SkillDAO {

	@Override
	public Long create(Skill skill) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Skill getById(Long id) throws DAOException {
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
	public List<Skill> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
