package com.epam.lab.intouch.controller.skill;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;
import com.epam.lab.intouch.service.skill.SkillService;

public class SkillController {
	private SkillService skillService;

	public SkillController() {
		skillService = new SkillService();
	}

	public SkillService getSkillService() {
		return skillService;
	}

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	public List<Skill> getSkillNames() throws DAOException {
		return skillService.getAllSkills();
	}

	public List<SkillType> getSkillTypes() throws DAOException {
		return skillService.getAllSkillsType();
	}
}
