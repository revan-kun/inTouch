package com.epam.lab.intouch.service.skill;

import java.util.Collection;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.skill.DefaultSkillDAO;
import com.epam.lab.intouch.dao.skill.SkillDAO;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class SkillService {
	
	private SkillDAO skillDAO;
	
	public SkillService(){
		skillDAO = new DefaultSkillDAO();
	}

	public String create(Skill skill) throws DAOException {
		String skillName = skillDAO.create(skill);
		return skillName;

	}

	public Skill getById(String id) throws DAOException {
		Skill idSkill = skillDAO.getById(id);
		return idSkill;
	}

	public void update(Skill oldSkill, Skill newSkill) throws DAOException {
		skillDAO.update(oldSkill, newSkill);

	}

	public void delete(Skill skill) throws DAOException {
		skillDAO.delete(skill);

	}

	public Collection<Skill> getAll() throws DAOException {
		List<Skill> skills = (List<Skill>) skillDAO.getAll();
		return skills;
	}

}
