package com.epam.lab.intouch.dao.member.skill;

import com.epam.lab.intouch.dao.BaseDAO;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public interface MemberSkillsDAO extends BaseDAO<Member, String> {

	String addSkill(Member member, Skill skill) throws DAOException;
	
	void removeSkill(Member member, Skill skill) throws DAOException;

}
