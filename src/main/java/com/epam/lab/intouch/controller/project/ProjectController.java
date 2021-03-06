package com.epam.lab.intouch.controller.project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.service.project.BaseProjectService;
import com.epam.lab.intouch.service.project.ProjectService;
import com.epam.lab.intouch.web.notifier.concurrency.SenderThread;

public class ProjectController {
	private final static Logger LOG = LogManager.getLogger(ProjectController.class);

	private final BaseProjectService projectService;

	public ProjectController() {
		projectService = new ProjectService();
	}

	public Project getProject(Long projectID) throws DataAccessingException {

		Project resultProject = new Project();
		resultProject.setId(projectID);

		return getProject(resultProject);
	}

	public Project getSimpleProject(Long id) throws DataAccessingException {
		Project project = null;
		if (id != null) {
			try {
				project = projectService.getSimpleProject(id);
			} catch (DAOException e) {
				LOG.error("Cannot access data: ", e);
				throw new DataAccessingException(e);
			}
		}
		return project;
	}

	public Project getProject(Project project) throws DataAccessingException {
		Project resultProject = null;

		if (project != null && project.getId() != null) {
			try {
				resultProject = projectService.getById(project.getId());
			} catch (DAOException e) {
				LOG.error("Cannot access data: ", e);
				throw new DataAccessingException(e);
			}
		}

		return resultProject;
	}

	public List<Project> getAll() throws DAOException {
		return projectService.getAll();
	}

	/**
	 * Method for getting last registrated projects
	 * 
	 * @author Iryna
	 * @param number
	 * @return projects.subList(0, number < size ? number : size)
	 * @throws DAOException
	 */
	public List<Project> getLastRegisteredProjects(final int number) throws DAOException {
		final List<Project> projects = projectService.getAll();

		if (number == -1) {
			return projects;
		}
		
		Collections.sort(projects, new Comparator<Project>() {
			@Override
			public int compare(Project project1, Project project2) {
				final long first = project1.getCreationDate().getTime();
				final long second = project2.getCreationDate().getTime();

				return first < second ? 1 : -1;
			}
		});

		final int size = projects.size();
		return projects.subList(0, number < size ? number : size);
	}

	/**
	 * @author Revan
	 * @param project
	 */
	public void updateProject(Project project) {
		try {
			Thread sender = new Thread(new SenderThread(project, null));
			sender.start();
			projectService.update(project, project);
		} catch (DAOException e) {
			LOG.error("Cannot update project", e);
		}

	}
}
