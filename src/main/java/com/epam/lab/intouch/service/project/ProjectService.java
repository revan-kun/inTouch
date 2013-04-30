package com.epam.lab.intouch.service.project;

import java.util.LinkedList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
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
import com.epam.lab.intouch.model.project.Project;

public class ProjectService {

	private MemberDAO memberDAO;
	private ProjectDAO projectDAO;
	private TeamDAO teamDAO;
	private HistoryDAO historyDAO;
	private SkillDAO skillDAO;
	private MemberSkillsDAO memberSkillsDAO;

	public ProjectService() {
		memberDAO = new DefaultMemberDAO();
		projectDAO = new DefaultProjectDAO();
		teamDAO = new DefaultTeamDAO();
		historyDAO = new DefaultHistoryDAO();
		skillDAO = new DefaultSkillDAO();
		memberSkillsDAO = new DefaultMemberSkillsDAO();
	}

	public Long create(Project project) throws DAOException {
		projectDAO.create(project);
		teamDAO.create(project);
		return project.getId();

	}

	public Project getById(Long id) throws DAOException {
		Project project = teamDAO.getById(id);
		Project fullProject = projectDAO.getById(id);
		List<Member> members = project.getMembers();
		List<Member> fullMembers = new LinkedList<Member>();
		for (Member member : members) {
			Member fullMember = memberDAO.getById(member.getLogin());
			fullMembers.add(fullMember);
		}
	
		fullProject.setMembers(fullMembers);

		return fullProject;
	}

	public void update(Project oldProject, Project newProject)
			throws DAOException {
		projectDAO.update(oldProject, newProject);

	}

	public void delete(Project project) throws DAOException {
		projectDAO.delete(project);

	}
	
	private List<Project> getFullProjects(List<Project> projects) throws DAOException{
		List<Project> fullProjects = new LinkedList<Project>();
		
		for(Project project : projects){
			
			fullProjects.add(getById(project.getId()));
		}
		
		return fullProjects;
	}

	public List<Project> getAll() throws DAOException {
		List<Project> projects = (List<Project>) projectDAO.getAll();
		List<Project> fullProjects = getFullProjects(projects);

		return fullProjects;
	}

	public List<Project> getAllFromSearch(String query) throws DAOException {
		List<Project> projects = (List<Project>) projectDAO.getAllFromSearch(query);
		List<Project> fullProjects = getFullProjects(projects);
		return fullProjects;
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
