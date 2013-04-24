package com.epam.lab.intouch.dao.skill;

import java.util.List;

import com.epam.lab.intouch.dao.BaseDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public interface SkillDAO extends BaseDAO<Member, String> {

	List<Skill> getAllSkilsOfMember(String id);
}
