package com.epam.lab.intouch.controller.member.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.service.member.BaseMemberService;
import com.epam.lab.intouch.service.member.MemberService;

public class MemberController {
	private final static Logger LOG = LogManager.getLogger(MemberController.class);

	private final BaseMemberService memberService;

	public MemberController() {
		memberService = new MemberService();
	}

	private Boolean checkMemberIfExists(Member member) throws DataAccessingException {
		Boolean exists = false;

		try {
			Member memberInDB = memberService.getById(member.getLogin());

			if (memberInDB != null) {
				exists = true;
			}

		} catch (DAOException e) {
			LOG.error("Cannot access data: ", e);
			throw new DataAccessingException(e);
		}

		return exists;
	}

	public Member authorizeMember(String login, String password) throws DataAccessingException {

		Member member = new Member();
		member.setLogin(login);
		member.setPassword(password);

		return authorizeMember(member);
	}

	public Member authorizeMember(Member member) throws DataAccessingException {
		Member authorizedMember = null;

		if (member != null && member.getLogin() != null && member.getPassword() != null) {

			try {
				Member memberInDB = memberService.getById(member.getLogin());

				if (memberInDB != null) {

					if (member.getPassword().equals(memberInDB.getPassword())) {
						authorizedMember = memberInDB;
					}
				}

			} catch (DAOException e) {
				LOG.error("Cannot access data: ", e);
				throw new DataAccessingException(e);
			}

		}

		return authorizedMember;
	}

	public void updateProfile(Member oldMember, Member newMember) throws DataAccessingException {
		Boolean memberExists = checkMemberIfExists(oldMember);

		if (memberExists) {
			try {
				memberService.update(oldMember, newMember);
			} catch (DAOException e) {
				LOG.error("Cannot access data for updating member profile: ", e);
				throw new DataAccessingException(e);
			}
		}
	}

	public Boolean registerNewMember(Member member) throws DataAccessingException {
		Boolean result = false;
		Boolean memberExists = checkMemberIfExists(member);

		if (!memberExists) {
			try {
				member.setRegistrationDate(new Date());
				memberService.create(member);
				result = true;
			} catch (DAOException e) {
				LOG.error("Cannot access data holder for new user registration: ", e);
				throw new DataAccessingException(e);
			}
		}

		return result;
	}

	private List<Skill> getSkills(Member member, SkillType type) {
		List<Skill> programmingSkills = new ArrayList<Skill>();
		if (member.getSkills() != null) {
			for (Skill skill : member.getSkills()) {
				if (skill.getSkillType() == type) {
					programmingSkills.add(skill);
				}
			}
		}
		return programmingSkills;
	}

	public List<Skill> getProgrammingSkills(Member member) {
		return getSkills(member, SkillType.PROGRAMMING);
	}

	public List<Skill> getLanguageSkills(Member member) {
		return getSkills(member, SkillType.LANGUAGE);
	}

	public List<Skill> getTechnologySkills(Member member) {
		return getSkills(member, SkillType.TECHNOLOGY);
	}

	public List<Member> getLastRegistrationMember(int count) throws DAOException {

		List<Member> members = memberService.getAll();

		Collections.sort(members, new Comparator<Member>() {

			@Override
			public int compare(Member member1, Member member2) {

				Long first = member1.getRegistrationDate().getTime();
				Long second = member2.getRegistrationDate().getTime();

				return first < second ? 1 : -1;
			}
		});

		List<Member> selectedMembers = new LinkedList<Member>();

		int minSize = Math.min(count, members.size());

		for (int i = 0; i < minSize; i++) {

			selectedMembers.add(members.get(i));

		}

		return selectedMembers;
	}

	public Member memberWithActiveProjectInfo(String login) throws DAOException {

		Member member = memberService.memberWithActiveProjectInfo(login);

		return member;
	}

	public Member memberWithFullActiveProject(String login) throws DAOException {

		Member member = memberService.memberWithFullActiveProject(login);

		return member;
	}

	public Member memberWithActiveProjectId(String login) throws DAOException {

		Member member = memberService.memberWithActiveProjectId(login);

		return member;
	}

	public Member getById(String login) throws DAOException {

		Member member = memberService.getById(login);

		return member;
	}

	public List<Project> getMemberProjectsHistory(String login) {
		List<Project> allProjects = new LinkedList<Project>();

		try {
			allProjects.addAll(memberService.getMemberProjectsHistory(login));
		} catch (DAOException e) {
			LOG.error("Cannot access data holder for new user registration: ", e);
		}

		return allProjects;
	}

	public Member getMemberByLogin(String login) throws DAOException {
		Member memberFromDB = memberService.getById(login);

		return memberFromDB;
	}

}
