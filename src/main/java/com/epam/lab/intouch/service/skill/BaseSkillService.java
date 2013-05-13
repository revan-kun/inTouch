package com.epam.lab.intouch.service.skill;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;

public interface BaseSkillService {

	Long create(Skill skill) throws DAOException;

	Member getById(String login) throws DAOException;

	void update(Member oldMember, Member newMember) throws DAOException;

	void delete(Member member) throws DAOException;

	void delete(Skill skill) throws DAOException;

	List<Member> getAll() throws DAOException;

	List<Skill> getAllSkills() throws DAOException;

	List<SkillType> getAllSkillsType() throws DAOException;

	List<Member> getAllFromSearch(String query) throws DAOException;

	String addSkill(Member member, Skill skill) throws DAOException;

	void removeSkill(Member member, Skill skill) throws DAOException;

}
