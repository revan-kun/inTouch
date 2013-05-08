package com.epam.lab.intouch.controller.util.cache;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;
import com.epam.lab.intouch.service.skill.SkillService;

public class SkillCache {
	private final static Logger LOG = LogManager.getLogger(SkillCache.class);

	private Set<Skill> skills;
	private Set<SkillType> skillTypes;

	private static SkillCache instance;

	private SkillCache() throws DataAccessingException {
		skills = new LinkedHashSet<Skill>();
		skillTypes = new LinkedHashSet<SkillType>();

		initSkills();
	}

	private void initSkills() throws DataAccessingException {
		SkillService skillService = new SkillService();
		List<Skill> skills = new ArrayList<Skill>();
		List<SkillType> skillTypes = new ArrayList<SkillType>();

		try {
			skills = skillService.getAllSkills();
			skillTypes = skillService.getAllSkillsType();
		} catch (DAOException e) {
			LOG.error("Cannot access data for caching resources");
			throw new DataAccessingException();
		}

		for (Skill skill : skills) {
			this.skills.add(skill);
		}

		for (SkillType skillType : skillTypes) {
			this.skillTypes.add(skillType);
		}
	}

	public static SkillCache getInstance() throws DataAccessingException {

		if (instance == null) {
			synchronized (SkillCache.class) {
				if (instance == null) {
					instance = new SkillCache();
				}
			}
		}

		return instance;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public Set<SkillType> getSkillTypes() {
		return skillTypes;
	}

}
