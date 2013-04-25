package com.epam.lab.intouch.service.project;

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
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.service.member.MemberService;

public class ProjectService {

	private ProjectDAO projectDAO;
	private ProjectTeamDAO teamProjectDAO;
	private MemberDAO memberDAO;
	private SkillDAO skillDAO;
	private MemberTeamDAO memberTeamDAO;
	private MemberService memberService;

	public ProjectService() {
		projectDAO = new DefaultProjectDAO();
		teamProjectDAO = new DefaultProjectTeamDAO();
		memberDAO = new DefaultMemberDAO();
		skillDAO = new DefaultSkillDAO();
		memberTeamDAO = new DefaultMemberTeamDAO();
		memberService = new MemberService();
	}

	public Long create(Project project) throws DAOException {
		long idProject = projectDAO.create(project);
		return idProject;
	}
	
	public List<Member> getListMember(Long idProject) throws DAOException{
		List<Member> members = new LinkedList<Member>();
		List<String> idMembers = teamProjectDAO.getMemberID(idProject);
		for (String idMember : idMembers) {
			Member member = memberService.getById(idMember);
		
			members.add(member);
		}
		return members;
	} 

	public Project getById(Long id) throws DAOException {

		Project project = projectDAO.getById(id);
		project.setMembers(getListMember(id));
		return project;
	}

	public void update(Project oldProject, Project newProject)
			throws DAOException {
		projectDAO.update(oldProject, newProject);

	}

	public void delete(Project project) throws DAOException {
		projectDAO.delete(project);

	}

	public Collection<Project> getAll() throws DAOException {
		List<Project> projects = new ArrayList<Project>();

		for (Project project : projectDAO.getAll()) {
			project.setMembers(getListMember(project.getId()));
			projects.add(project);

		}

		return projects;
	}

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	public ProjectTeamDAO getTeamDAO() {
		return teamProjectDAO;
	}

	public void setTeamDAO(ProjectTeamDAO teamDAO) {
		this.teamProjectDAO = teamProjectDAO;
	}

	public MemberDAO getMemberDAO() {
		return memberDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	public SkillDAO getSkillDAO() {
		return skillDAO;
	}

	public void setSkillDAO(SkillDAO skillDAO) {
		this.skillDAO = skillDAO;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public MemberTeamDAO getMemberTeamDAO() {
		return memberTeamDAO;
	}

	public void setMemberTeamDAO(MemberTeamDAO memberTeamDAO) {
		this.memberTeamDAO = memberTeamDAO;
	}

}
