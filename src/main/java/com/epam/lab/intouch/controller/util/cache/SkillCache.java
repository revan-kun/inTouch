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
import com.epam.lab.intouch.service.skill.BaseSkillService;
import com.epam.lab.intouch.service.skill.SkillService;

public class SkillCache {
	private final static Logger LOG = LogManager.getLogger(SkillCache.class);

	private Boolean isUpToDate;

	private Set<Skill> skills;
	private Set<SkillType> skillTypes;

	private static SkillCache instance;

	private SkillCache() throws DataAccessingException {
		skills = new LinkedHashSet<Skill>();
		skillTypes = new LinkedHashSet<SkillType>();

		initSkills();
	}
	
	public Boolean isUpToDate() {
		return isUpToDate;
	}

	public void setIsUpToDate(Boolean isUpToDate) {
		this.isUpToDate = isUpToDate;
	}

	private void initSkills() throws DataAccessingException {
		BaseSkillService skillService = new SkillService();
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

		isUpToDate = true;
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

	private void initIfOutdated() throws DataAccessingException {
		if (!isUpToDate) {
			initSkills();
		}
	}

	public Set<Skill> getSkills() throws DataAccessingException {
		initIfOutdated();
		return skills;
	}

	public Set<SkillType> getSkillTypes() throws DataAccessingException {
		initIfOutdated();
		return skillTypes;
	}



}
