package com.epam.lab.intouch.service.member;

import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAODeleteException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.dao.exception.DAOUpdateException;
import com.epam.lab.intouch.dao.history.project.DefaultHistoryDAO;
import com.epam.lab.intouch.dao.history.project.HistoryDAO;
import com.epam.lab.intouch.dao.member.DefaultMemberDAO;
import com.epam.lab.intouch.dao.member.MemberDAO;
import com.epam.lab.intouch.dao.member.skill.DefaultMemberSkillsDAO;
import com.epam.lab.intouch.dao.member.skill.DefaultSkillDAO;
import com.epam.lab.intouch.dao.member.skill.MemberSkillsDAO;
import com.epam.lab.intouch.dao.member.skill.SkillDAO;
import com.epam.lab.intouch.dao.project.DefaultProjectDAO;
import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.team.DefaultTeamDAO;
import com.epam.lab.intouch.dao.team.TeamDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.project.Project;

public class MemberService {
	
	private MemberDAO memberDAO;
	private ProjectDAO projectDAO;
	private TeamDAO teamDAO;
	private HistoryDAO historyDAO;
	private SkillDAO skillDAO;
	private MemberSkillsDAO memberSkillsDAO;
	

	public MemberService() {
		memberDAO = new DefaultMemberDAO();
		projectDAO = new DefaultProjectDAO();
		teamDAO = new DefaultTeamDAO();
		historyDAO = new DefaultHistoryDAO();
		skillDAO = new DefaultSkillDAO();
		memberSkillsDAO = new DefaultMemberSkillsDAO();
	
	}


	public String create(Member member) throws DAOException {
		String memberLogin = memberDAO.create(member);
		List<Skill> skills = member.getSkills();
		for(Skill skill: skills){
			skillDAO.create(skill);
			memberSkillsDAO.create(member);
			
			
		}
		
		return memberLogin;
	}

	
	public Member getById(String login) throws DAOException {
		Member member = memberDAO.getById(login);
		
	
		List<Skill> skills = memberSkillsDAO.getById(login).getSkills();
		for(Skill skill : skills){
			Skill fullSkill = skillDAO.getById(skill.getId());
			member.getSkills().add(fullSkill);
		}
		
		
	
		List<Project> projects = historyDAO.getById(login).getProjects();
		for(Project project: projects){
			Project fullProject = projectDAO.getById(project.getId());
			member.getProjects().add(fullProject);
		}
		
		

		return member;
	}


	public void update(Member oldMember, Member newMember) throws DAOException {
		
		memberDAO.update(oldMember, newMember);
	}

	
	public void delete(Member member) throws DAOException {
		
		memberDAO.delete(member);
	}


	public List<Member> getAll() throws DAOException {
		
		List<Member> members = (List<Member>) memberDAO.getAll();
		
		List<Member> membersWithProjects = (List<Member>) historyDAO.getAll();
		for(Member member: membersWithProjects){
			member.getProjects();
		}

		return members;
	}


	public MemberDAO getMemberDAO() {
		return memberDAO;
	}


	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}


	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}


	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}


	public TeamDAO getTeamDAO() {
		return teamDAO;
	}


	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}


	public HistoryDAO getHistoryDAO() {
		return historyDAO;
	}


	public void setHistoryDAO(HistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
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
