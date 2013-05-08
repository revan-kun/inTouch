package com.epam.lab.intouch.controller.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.util.cache.SkillCache;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;

public class SkillController {

	public List<Skill> getSkills(SkillType type) throws DataAccessingException {
		SkillCache scillCache = SkillCache.getInstance();
		List<Skill> skills = new ArrayList<Skill>();

		for (Skill skill : scillCache.getSkills()) {
			if (skill.getSkillType() == type) {
				skills.add(skill);
			}
		}

		return skills;
	}

	public Set<SkillType> getSkillTypes() throws DataAccessingException {
		return SkillCache.getInstance().getSkillTypes();
	}

}
