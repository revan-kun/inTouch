package com.epam.lab.intouch.controller.util.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;
import com.epam.lab.intouch.service.skill.SkillService;

public class SkillCache {
	private final static Logger LOG = LogManager.getLogger(SkillCache.class);

	private List<Skill> skillNames;
	private List<SkillType> skillTypes;

	private static SkillCache instance;

	private SkillCache() throws DataAccessingException {
		skillNames = new ArrayList<Skill>();
		skillTypes = new ArrayList<SkillType>();

		SkillService skillService = new SkillService();

		try {
			skillNames = skillService.getAllSkills();
			skillTypes = skillService.getAllSkillsType();
		} catch (DAOException e) {
			LOG.error("Cannot access data for caching resources");
			throw new DataAccessingException();
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

	public List<Skill> getSkillNames() {
		return skillNames;
	}

	public List<SkillType> getSkillTypes() {
		return skillTypes;
	}
}
