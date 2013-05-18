package com.epam.lab.intouch.controller.member.special;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.exception.IllegalProjectStatusException;
import com.epam.lab.intouch.controller.exception.PermissionException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;
import com.epam.lab.intouch.service.history.BaseHistoryService;
import com.epam.lab.intouch.service.history.HistoryService;
import com.epam.lab.intouch.service.project.BaseProjectService;
import com.epam.lab.intouch.service.project.ProjectService;
import com.epam.lab.intouch.service.team.BaseTeamService;
import com.epam.lab.intouch.service.team.TeamService;

public class ManagerController {
	private final static Logger LOG = LogManager.getLogger(ManagerController.class);

	private final BaseProjectService projectService;
	private final BaseHistoryService historyService;
	private final BaseTeamService teamService;

	public ManagerController() {
		projectService = new ProjectService();
		historyService = new HistoryService();
		teamService = new TeamService();
	}

	private void checkManagerPermission(Member member) throws PermissionException {
		if (member.isManager() == false) {
			LOG.warn("Member hasn't manager permissions");
			throw new PermissionException("Member hasn't manager permissions");
		}
	}

	private void openedForUpdating(Project project) throws IllegalProjectStatusException {
		if (project.isOpen() == false) {
			LOG.warn("Member cannot update project info until it is not open");
			throw new IllegalProjectStatusException("Member cannot update project info until it is not open");
		}
	}

	public Boolean removeMemberFromProject(Member manager, Member memberToDeletion, Project project) throws PermissionException, DataAccessingException,
			IllegalProjectStatusException {
		Boolean result = false;

		List<Member> projectMembers = project.getMembers();

		checkManagerPermission(manager);
		openedForUpdating(project);

		if (memberToDeletion.isManager() == false) {

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

	public Boolean addMemberToProject(Member manager, Member memberToInsertion, Project project) throws PermissionException, DataAccessingException,
			IllegalProjectStatusException {
		Boolean result = false;

		checkManagerPermission(manager);
		openedForUpdating(project);

		if (memberToInsertion.isManager() == false) {

			if (!project.getMembers().contains(memberToInsertion) && project.getMembers().contains(manager)) {

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

		checkManagerPermission(manager);

		project.setStatus(ProjectStatus.OPEN);
		project.setCreationDate(new Date());

		try {
			Long projectID = projectService.create(project);
			project.setId(projectID);
			manager.getActiveProjects().add(project);
			project.getMembers().add(manager);
		} catch (DAOException e) {
			LOG.error("Cannot access to data! ", e);
			throw new DataAccessingException("Cannot access to data: " + e);
		}

		return result;
	}

	public Boolean updateProjectInfo(Member manager, Project oldProject, Project newProject) throws DataAccessingException, PermissionException,
			IllegalProjectStatusException {
		Boolean result = false;

		checkManagerPermission(manager);
		openedForUpdating(oldProject);

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

		return result;
	}
	
	public Boolean closeProject(Member manager, Long projectID) throws DataAccessingException, PermissionException, IllegalProjectStatusException {
		Boolean result = false;
		
		Project project = null;
		
		this.checkManagerPermission(manager);				
		
		try {
			project = this.projectService.getById(projectID);
			
			this.openedForUpdating(project);
			
			project.setStatus(ProjectStatus.CLOSED);
			project.setCompletionDate(new Date());
		
				for (Member member : project.getMembers()) {
					historyService.addProject(member, project);
					teamService.removeMember(project, member);
				}
		
			projectService.update(project, project);
			result = true;
		} catch (DAOException e) {
			LOG.error("Cannot access to data! ", e);
			throw new DataAccessingException("Cannot access to data: " + e);
		}
		
		return result;
	}

}
