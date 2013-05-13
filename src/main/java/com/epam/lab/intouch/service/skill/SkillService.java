package com.epam.lab.intouch.service.skill;

import static com.epam.lab.intouch.util.db.metadata.FieldName.ID;
import static com.epam.lab.intouch.util.db.metadata.FieldName.NAME;
import static com.epam.lab.intouch.util.db.metadata.FieldName.TYPE;
import static com.epam.lab.intouch.util.db.metadata.TableName.SKILLS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.exception.DBConnectionException;
import com.epam.lab.intouch.dao.member.skill.DefaultMemberSkillsDAO;
import com.epam.lab.intouch.dao.member.skill.DefaultSkillDAO;
import com.epam.lab.intouch.dao.member.skill.MemberSkillsDAO;
import com.epam.lab.intouch.dao.member.skill.SkillDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;

public class SkillService implements BaseSkillService {

	private final SkillDAO skillDAO;
	private final MemberSkillsDAO memberSkillsDAO;

	public SkillService() {

		skillDAO = new DefaultSkillDAO();
		memberSkillsDAO = new DefaultMemberSkillsDAO();

	}

	@Override
	public Long create(Skill skill) throws DAOException {
		Long idSkill = skillDAO.create(skill);
		return idSkill;
	}
	
	public Skill getById(Long id) throws DAOException {
		Skill skill = skillDAO.getById(id);
		return skill;
	}

	@Override
	public Member getById(String login) throws DAOException {
		Member member = memberSkillsDAO.getById(login);
		if (member != null) {
			List<Skill> skills = member.getSkills();
			List<Skill> fullSkill = new LinkedList<Skill>();
			for (Skill skill : skills ) {
				Skill mainSkill = skillDAO.getById(skill.getId());
				skill.setName(mainSkill.getName());
				skill.setSkillType(mainSkill.getSkillType());
				fullSkill.add(skill);
			}
			member.setSkills(fullSkill);
		}
		return member;
	}

	@Override
	public void update(Member oldMember, Member newMember) throws DAOException {

		throw new UnsupportedOperationException("You can't update member skill");

	}

	@Override
	public void delete(Member member) throws DAOException {

		memberSkillsDAO.delete(member);

	}

	@Override
	public void delete(Skill skill) throws DAOException {

		skillDAO.delete(skill);
	}

	private List<Member> getFullSkillsOfMember(List<Member> members) throws DAOException {
		List<Member> membersWithSkills = new LinkedList<Member>();
		for (Member member : members) {
			membersWithSkills.add(getById(member.getLogin()));
		}
		return membersWithSkills;
	}

	@Override
	public List<Member> getAll() throws DAOException {

		List<Member> members = (List<Member>) memberSkillsDAO.getAll();
		List<Member> membersWithSkills = getFullSkillsOfMember(members);

		return membersWithSkills;
	}

	@Override
	public List<Skill> getAllSkills() throws DAOException {
		List<Skill> skills = (List<Skill>) skillDAO.getAll();
		return skills;
	}

	@Override
	public List<SkillType> getAllSkillsType() throws DAOException {
		List<Skill> skills = (List<Skill>) skillDAO.getAll();
		List<SkillType> skillsType = new LinkedList<SkillType>();
		for (Skill skill : skills) {
			if (!skillsType.contains(skill.getSkillType())) {
				skillsType.add(skill.getSkillType());
			}
		}
		return skillsType;
	}

	@Override
	public List<Member> getAllFromSearch(String query) throws DAOException {

		List<Member> members = (List<Member>) memberSkillsDAO.getAllFromSearch(query);
		List<Member> membersWithSkills = getFullSkillsOfMember(members);

		return membersWithSkills;
	}

	@Override
	public String addSkill(Member member, Skill skill) throws DAOException {
		String login = memberSkillsDAO.addSkill(member, skill);

		return login;
	}

	@Override
	public void removeSkill(Member member, Skill skill) throws DAOException {
		memberSkillsDAO.removeSkill(member, skill);

	}

}
