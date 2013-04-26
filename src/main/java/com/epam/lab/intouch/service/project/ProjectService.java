package com.epam.lab.intouch.service.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.history.project.DefaultHistoryDAO;
import com.epam.lab.intouch.dao.history.project.HistoryDAO;
import com.epam.lab.intouch.dao.member.DefaultMemberDAO;
import com.epam.lab.intouch.dao.member.MemberDAO;
import com.epam.lab.intouch.dao.project.DefaultProjectDAO;
import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.skill.DefaultSkillDAO;
import com.epam.lab.intouch.dao.skill.SkillDAO;
import com.epam.lab.intouch.dao.team.DefaultTeamDAO;
import com.epam.lab.intouch.dao.team.TeamDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.service.member.MemberService;

public class ProjectService {

	private ProjectDAO projectDAO;
	private TeamDAO teamProjectDAO;
	private MemberDAO memberDAO;
	private SkillDAO skillDAO;
	private HistoryDAO memberTeamDAO;
	private MemberService memberService;

	public ProjectService() {
		projectDAO = new DefaultProjectDAO();
		teamProjectDAO = new DefaultTeamDAO();
		memberDAO = new DefaultMemberDAO();
		skillDAO = new DefaultSkillDAO();
		memberTeamDAO = new DefaultHistoryDAO();
		memberService = new MemberService();
	}

	public Long create(Project project) throws DAOException {
		long idProject = projectDAO.create(project);
		return idProject;
	}
	
	public List<Member> getListMember(Long idProject) throws DAOException{
		
		Project project = teamProjectDAO.getById(idProject);
		List<Member> members = project.getMembers();
		for (Member member : members) {
			Member fullMember = memberService.getById(member.getLogin());
		
			members.add(fullMember);
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

	public TeamDAO getTeamDAO() {
		return teamProjectDAO;
	}

	public void setTeamDAO(TeamDAO teamDAO) {
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

	public HistoryDAO getMemberTeamDAO() {
		return memberTeamDAO;
	}

	public void setMemberTeamDAO(HistoryDAO memberTeamDAO) {
		this.memberTeamDAO = memberTeamDAO;
	}

}
