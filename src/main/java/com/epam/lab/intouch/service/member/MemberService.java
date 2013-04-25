package com.epam.lab.intouch.service.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.member.DefaultMemberDAO;
import com.epam.lab.intouch.dao.member.MemberDAO;
import com.epam.lab.intouch.dao.project.DefaultProjectDAO;
import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.skill.DefaultSkillDAO;
import com.epam.lab.intouch.dao.skill.SkillDAO;
import com.epam.lab.intouch.dao.team.DefaultMemberTeamDAO;
import com.epam.lab.intouch.dao.team.DefaultProjectTeamDAO;
import com.epam.lab.intouch.dao.team.MemberTeamDAO;
import com.epam.lab.intouch.dao.team.ProjectTeamDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.project.Project;

public class MemberService {

	private MemberDAO memberDAO;
	private SkillDAO skillDAO;
	private ProjectTeamDAO projectTeamDAO;
	private ProjectDAO projectDAO;
	private MemberTeamDAO memberTeamDAO;

	public MemberService() {
		memberDAO = new DefaultMemberDAO();
		skillDAO = new DefaultSkillDAO();
		projectTeamDAO = new DefaultProjectTeamDAO();
		projectDAO = new DefaultProjectDAO();
		memberTeamDAO = new DefaultMemberTeamDAO();
	}

	public String create(Member member) throws DAOException {
		String loginMember = memberDAO.create(member);
		List<Skill> skills = member.getSkills();
		for (Skill skill : skills) {
			skillDAO.create(skill); 
		}

		return loginMember;
	}

	private List<Skill> getSkillsOfMember(String idMember){
		List<Skill> skills = skillDAO.getAllSkilsOfMember(idMember); // all skills of member
		return skills;
	}
	
	private List<Project> getListProjects(String idMember) throws DAOException{
	
		List<Long> idProjects = memberTeamDAO.getProjectID(idMember);
		List<Project> projects = new LinkedList<Project>();
		for (Long idProject : idProjects) {
				Project project = projectDAO.getById(idProject);
				projects.add(project);
		}
		return projects;
	}
	
	public Member getById(String idMember) throws DAOException {
		Member member = memberDAO.getById(idMember); // member without skills
		member.setSkills(getSkillsOfMember(idMember));
		member.setProjects(getListProjects(idMember));

		return member;
	}

	public void update(Member oldMember, Member newMember)
			throws DAOException {
		memberDAO.update(oldMember, oldMember);
	}

	public void delete(Member member) throws DAOException {
		memberDAO.delete(member);
	}

	public Collection<Member> getAll() throws DAOException {
		List<Member> members = new ArrayList<Member>();
		for (Member member : memberDAO.getAll()) {
			member.setSkills(getSkillsOfMember(member.getLogin()));
			member.setProjects(getListProjects(member.getLogin()));
			members.add(member);
		}

		return members;
	}

	public MemberDAO getMemberDAO() {
		return memberDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
}
