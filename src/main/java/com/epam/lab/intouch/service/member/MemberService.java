package com.epam.lab.intouch.service.member;

import java.util.Collection;
import java.util.List;

import com.epam.lab.intouch.dao.exception.PersistenceException;
import com.epam.lab.intouch.dao.member.DefaultMemberDAO;
import com.epam.lab.intouch.dao.member.MemberDAO;
import com.epam.lab.intouch.dao.skill.DefaultSkillDAO;
import com.epam.lab.intouch.dao.skill.SkillDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class MemberService {

	private MemberDAO memberDAO;
	private SkillDAO skillDAO;

	public MemberService() {
		memberDAO = new DefaultMemberDAO();
		skillDAO = new DefaultSkillDAO();
	}

	public String create(Member member) throws PersistenceException {
		String loginMember = memberDAO.create(member);
//		List<Skill> skills = member.getSkills();
//		for (Skill skill : skills) {
//			skillDAO.create(skill);
//		}

		return loginMember;
	}

	public Member getById(String id) throws PersistenceException {
		Member member = null;
		member = memberDAO.getById(id); // member without skills
		List<Skill> skills = skillDAO.getAllSkilsOfMember(id); // all skills of member
		member.setSkills(skills);

		return member;
	}

	public void update(Member oldEntity, Member newEntity) {
		// TODO Auto-generated method stub

	}

	public void delete(Member member) {
		// TODO Auto-generated method stub

	}

	public Collection<Member> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public MemberDAO getMemberDAO() {
		return memberDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
}
