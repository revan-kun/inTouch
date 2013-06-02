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
import com.epam.lab.intouch.model.member.info.skill.SkillType;

/**
 * SkillService class combine different method from DAO class to get object
 * 
 * @author Iryna
 * 
 */
public class SkillService implements BaseSkillService {

	private final SkillDAO skillDAO;
	private final MemberSkillsDAO memberSkillsDAO;

	/**
	 * Initialization required DAO classes for Skill Service
	 */
	public SkillService() {

		skillDAO = new DefaultSkillDAO();
		memberSkillsDAO = new DefaultMemberSkillsDAO();

	}

	/**
	 * Method for creating a skill
	 * 
	 * @param skill
	 * @return id of the created skill
	 * @throws DAOException
	 */
	@Override
	public Long create(Skill skill) throws DAOException {

		Long idSkill = skillDAO.create(skill);

		return idSkill;
	}

	/**
	 * Method for getting a skill by it's id
	 * 
	 * @param id
	 * @return the required skill
	 * @throws DAOException
	 */
	public Skill getById(Long id) throws DAOException {

		Skill skill = skillDAO.getById(id);

		return skill;
	}

	/**
	 * Method for getting all skills for a member by his login
	 * 
	 * @param login
	 * @return member with populated skills according to the member login
	 * @throws DAOException
	 */
	@Override
	public Member getById(String login) throws DAOException {

		Member member = memberSkillsDAO.getById(login);
		if (member != null) {
			List<Skill> skills = member.getSkills();
			List<Skill> fullSkill = new LinkedList<Skill>();

			for (Skill skill : skills) {
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

	/**
	 * Method for deleting all skills of member
	 * 
	 * @param member
	 * @throws DAOException
	 */
	@Override
	public void delete(Member member) throws DAOException {

		memberSkillsDAO.delete(member);

	}

	/**
	 * Method for deleting a single skill
	 * 
	 * @param skill
	 * @throws DAOException
	 */
	@Override
	public void delete(Skill skill) throws DAOException {

		skillDAO.delete(skill);
	}

	/**
	 * Method for getting all skills for members
	 * 
	 * @param members
	 * @return list of members with populated skills
	 * @throws DAOException
	 */
	private List<Member> getFullSkillsOfMember(List<Member> members) throws DAOException {

		List<Member> membersWithSkills = new LinkedList<Member>();
		for (Member member : members) {
			membersWithSkills.add(getById(member.getLogin()));
		}

		return membersWithSkills;
	}

	/**
	 * Method for getting all skills for all members
	 * 
	 * @return list of all members with populated skills
	 * @throws DAOException
	 */
	@Override
	public List<Member> getAll() throws DAOException {

		List<Member> members = (List<Member>) memberSkillsDAO.getAll();
		List<Member> membersWithSkills = getFullSkillsOfMember(members);

		return membersWithSkills;
	}

	/**
	 * Method for getting all existing unique skills
	 * 
	 * @return list of all existing unique skills
	 * @throws DAOException
	 */
	@Override
	public List<Skill> getAllSkills() throws DAOException {

		List<Skill> skills = (List<Skill>) skillDAO.getAll();

		return skills;
	}

	/**
	 * Method for getting all unique skill types
	 * 
	 * @return list of all unique skill types
	 * @throws DAOException
	 */
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

	/**
	 * Method for getting all skills of members that match a search query
	 * 
	 * @param query
	 * @return list of members with skills that that match the search query
	 * @throws DAOException
	 */
	@Override
	public List<Member> getAllFromSearch(String query) throws DAOException {

		List<Member> members = (List<Member>) memberSkillsDAO.getAllFromSearch(query);
		List<Member> membersWithSkills = getFullSkillsOfMember(members);

		return membersWithSkills;
	}

	/**
	 * Method for adding a skill to a member
	 * 
	 * @param member
	 * @param skill
	 * @return member login
	 * @throws DAOException
	 */
	@Override
	public String addSkill(Member member, Skill skill) throws DAOException {

		String login = memberSkillsDAO.addSkill(member, skill);

		return login;
	}

	/**
	 * Method for removing a skill from a member
	 * 
	 * @param member
	 * @param skill
	 * @throws DAOException
	 */
	@Override
	public void removeSkill(Member member, Skill skill) throws DAOException {

		memberSkillsDAO.removeSkill(member, skill);

	}

	/**
	 * Method for updating the skill list of a member
	 * 
	 * @param member
	 * @return member login
	 * @throws DAOException
	 */
	@Override
	public String updateMemberSkills(Member member) throws DAOException {

		String memberlogin = memberSkillsDAO.create(member);

		return memberlogin;
	}

}
