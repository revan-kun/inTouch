package com.epam.lab.intouch.controller.credential.special;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.exception.PermissionException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;
import com.epam.lab.intouch.service.history.HistoryService;
import com.epam.lab.intouch.service.project.ProjectService;
import com.epam.lab.intouch.service.team.TeamService;

public class ManagerCredential {
	private final static Logger LOG = LogManager.getLogger(ManagerCredential.class);

	private ProjectService projectService;
	private HistoryService historyService;
	private TeamService teamService;

	public ManagerCredential() {
		projectService = new ProjectService();
		historyService = new HistoryService();
		teamService = new TeamService();
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public Boolean removeMemberFromProject(Member manager, Member memberToDeletion, Project project) throws PermissionException, DataAccessingException {
		Boolean result = false;

		List<Member> projectMembers = project.getMembers();
		if (!manager.isManager()) {
			LOG.warn("Member hasn't enough permissions for deleting users from project!");
			throw new PermissionException("Member hasn't enough permissions for deleting users from project!");
		}

		if (!memberToDeletion.isManager()) {

			if (projectMembers.contains(memberToDeletion) && projectMembers.contains(manager)) {

				try {
					teamService.removeMember(project, memberToDeletion);
					result = project.removeMember(memberToDeletion);

					historyService.addProject(memberToDeletion, project);
				} catch (DAOException e) {
					LOG.error("Cannot access to data! ", e);
					throw new DataAccessingException("Cannot access to data: " + e);
				}

			}

		}

		return result;
	}

	public Boolean addMemberToProject(Member manager, Member memberToInsertion, Project project) throws PermissionException, DataAccessingException {
		Boolean result = false;

		if (!manager.isManager()) {
			LOG.warn("Member hasn't enough permissions for adding users to projects!");
			throw new PermissionException("Member hasn't enough permissions for adding users to projects!");
		}

		if ((!memberToInsertion.isManager()) && project.isOpen()) {

			if (!project.getMembers().contains(memberToInsertion)) {

				try {
					teamService.addMember(project, memberToInsertion);
					result = project.addMember(memberToInsertion);
				} catch (DAOException e) {
					LOG.error("Cannot access to data! ", e);
					throw new DataAccessingException("Cannot access to data: " + e);
				}

			}

		}

		return result;
	}

	public Boolean createNewProject(Member manager, Project project) throws PermissionException, DataAccessingException {
		Boolean result = false;

		if (manager.isManager()) {

			project.setStatus(ProjectStatus.OPEN);
			project.setCreationDate(new Date());

			try {
				Long projectID = projectService.create(project);
				project.setId(projectID);
				manager.getProjects().add(project);
				project.getMembers().add(manager);
			} catch (DAOException e) {
				LOG.error("Cannot access to data! ", e);
				throw new DataAccessingException("Cannot access to data: " + e);
			}

		} else {
			LOG.warn("Member hasn't enough permissions");
			throw new PermissionException("Member hasn't enough permissions for creating new projects!");
		}

		return result;
	}

	public Boolean updateProjectInfo(Member manager, Project oldProject, Project newProject) throws DataAccessingException, PermissionException {
		Boolean result = false;

		if (!manager.isManager()) {
			LOG.warn("Member hasn't enough permissions for updating project info!");
			throw new PermissionException("Member hasn't enough permissions for updating project info!");
		}

		if (!oldProject.isClosed()) {

			try {
				if (newProject.isClosed()) {
					newProject.setCompletionDate(new Date());

					for (Member member : oldProject.getMembers()) {
						historyService.addProject(member, oldProject);
						teamService.removeMember(oldProject, member);
					}
				}

				projectService.update(oldProject, newProject);
				result = true;
			} catch (DAOException e) {
				LOG.error("Cannot access to data! ", e);
				throw new DataAccessingException("Cannot access to data: " + e);
			}

		}

		return result;
	}

}
