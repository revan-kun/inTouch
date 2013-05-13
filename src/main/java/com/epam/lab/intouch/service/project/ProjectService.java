package com.epam.lab.intouch.service.project;

import java.util.LinkedList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.member.DefaultMemberDAO;
import com.epam.lab.intouch.dao.member.MemberDAO;
import com.epam.lab.intouch.dao.project.DefaultProjectDAO;
import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.team.DefaultTeamDAO;
import com.epam.lab.intouch.dao.team.TeamDAO;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public class ProjectService implements BaseProjectService {

	private final MemberDAO memberDAO;
	private final ProjectDAO projectDAO;
	private final TeamDAO teamDAO;

	public ProjectService() {
		memberDAO = new DefaultMemberDAO();
		projectDAO = new DefaultProjectDAO();
		teamDAO = new DefaultTeamDAO();

	}

	@Override
	public Long create(Project project) throws DAOException {
		projectDAO.create(project);
		teamDAO.create(project);
		return project.getId();

	}

	@Override
	public Project getById(Long id) throws DAOException {
		Project project = teamDAO.getById(id);
		Project fullProject = projectDAO.getById(id);
		if (fullProject != null) {
			List<Member> members = project.getMembers();
			List<Member> fullMembers = new LinkedList<Member>();
			for (Member member : members) {
				Member fullMember = memberDAO.getById(member.getLogin());
				fullMembers.add(fullMember);
			}

			fullProject.setMembers(fullMembers);
		}
		return fullProject;
	}

	@Override
	public void update(Project oldProject, Project newProject) throws DAOException {
		projectDAO.update(oldProject, newProject);

	}

	@Override
	public void delete(Project project) throws DAOException {
		projectDAO.delete(project);

	}

	private List<Project> getFullProjects(List<Project> projects) throws DAOException {
		List<Project> fullProjects = new LinkedList<Project>();

		for (Project project : projects) {

			fullProjects.add(getById(project.getId()));
		}

		return fullProjects;
	}

	@Override
	public List<Project> getAll() throws DAOException {
		List<Project> projects = (List<Project>) projectDAO.getAll();
		List<Project> fullProjects = getFullProjects(projects);

		return fullProjects;
	}

	@Override
	public List<Project> getAllFromSearch(String query) throws DAOException {
		List<Project> projects = (List<Project>) projectDAO.getAllFromSearch(query);
		List<Project> fullProjects = getFullProjects(projects);
		return fullProjects;
	}
	@Override
	public Project getSimpleProject(Long id) throws DAOException{
		Project project = projectDAO.getById(id);
		return project;
	}
	@Override
	public Project getProjectWithMember(Long id) throws DAOException{
		Project project = projectDAO.getById(id);
		Project projectId = teamDAO.getById(id);
		List<Member> membersId = projectId.getMembers();
		List<Member> fullMembers = new LinkedList<Member>();
		for(Member member : membersId){
			Member memberInfo = memberDAO.getById(member.getLogin());
			fullMembers.add(memberInfo);
			
		}
		project.setMembers(fullMembers);
		return project;
	}

}
