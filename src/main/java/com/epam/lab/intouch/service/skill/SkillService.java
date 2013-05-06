package com.epam.lab.intouch.service.skill;

import java.util.LinkedList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.member.skill.DefaultMemberSkillsDAO;
import com.epam.lab.intouch.dao.member.skill.DefaultSkillDAO;
import com.epam.lab.intouch.dao.member.skill.MemberSkillsDAO;
import com.epam.lab.intouch.dao.member.skill.SkillDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class SkillService {

	private SkillDAO skillDAO;
	private MemberSkillsDAO memberSkillsDAO;

	public SkillService() {

		skillDAO = new DefaultSkillDAO();
		memberSkillsDAO = new DefaultMemberSkillsDAO();

	}

	public Long create(Skill skill) throws DAOException {
		Long idSkill = skillDAO.create(skill);
		return idSkill;
	}

	public Member getById(String login) throws DAOException {
		Member member = memberSkillsDAO.getById(login);
		for (Skill skill : member.getSkills()) {
			Skill mainSkill = skillDAO.getById(skill.getId());
			skill.setName(mainSkill.getName());
			skill.setSkillType(mainSkill.getSkillType());

		}

		return member;
	}

	public void update(Member oldMember, Member newMember) throws DAOException {

		throw new UnsupportedOperationException("You can't update member skill");

	}

	public void delete(Member member) throws DAOException {

		memberSkillsDAO.delete(member);

	}

	private List<Member> getFullSkillsOfMember(List<Member> members) throws DAOException {
		List<Member> membersWithSkills = new LinkedList<Member>();
		for (Member member : members) {
			membersWithSkills.add(getById(member.getLogin()));
		}
		return membersWithSkills;
	}

	public List<Member> getAll() throws DAOException {

		List<Member> members = (List<Member>) memberSkillsDAO.getAll();
		List<Member> membersWithSkills = getFullSkillsOfMember(members);

		return membersWithSkills;
	}

	public List<Member> getAllFromSearch(String query) throws DAOException {

		List<Member> members = (List<Member>) memberSkillsDAO.getAllFromSearch(query);
		List<Member> membersWithSkills = getFullSkillsOfMember(members);

		return membersWithSkills;
	}

	public String addSkill(Member member, Skill skill) throws DAOException {
		String login = memberSkillsDAO.addSkill(member, skill);

		return login;
	}

	public void removeSkill(Member member, Skill skill) throws DAOException {
		memberSkillsDAO.removeSkill(member, skill);

	}

	public SkillDAO getSkillDAO() {
		return skillDAO;
	}

	public void setSkillDAO(SkillDAO skillDAO) {
		this.skillDAO = skillDAO;
	}

	public MemberSkillsDAO getMemberSkillsDAO() {
		return memberSkillsDAO;
	}

	public void setMemberSkillsDAO(MemberSkillsDAO memberSkillsDAO) {
		this.memberSkillsDAO = memberSkillsDAO;
	}

}
