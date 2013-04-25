package com.epam.lab.intouch.dao.skill;

import java.util.List;

import com.epam.lab.intouch.dao.BaseDAO;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public interface SkillDAO extends BaseDAO<Skill, String> {

	List<Skill> getAllSkilsOfMember(String login);
}
