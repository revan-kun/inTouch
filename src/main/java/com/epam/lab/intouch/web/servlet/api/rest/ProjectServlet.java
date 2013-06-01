package com.epam.lab.intouch.web.servlet.api.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.project.ProjectController;
import com.epam.lab.intouch.model.project.Project;
import com.google.gson.Gson;

/**
 * This servlet provides information about project for API users
 * 
 * @author Zatorsky D.B
 * 
 */
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 5703053454322958752L;
	private final static Logger LOG = LogManager.getLogger(ProjectServlet.class);

	private static final String PROJECT_ID = "projectID";
	private static final String PROJECT_TYPE = "projectType";
	private static final String MIDDLE_PROJECT = "middleProject";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProjectController projectController = new ProjectController();
		Project project = null;
		String jsonResponse = null;
		try {
			long projectID = Long.parseLong(request.getParameter(PROJECT_ID));
			String projectType = request.getParameter(PROJECT_TYPE);

			if (MIDDLE_PROJECT.equals(projectType)) {
				project = projectController.getProject(projectID);
			} else {
				project = projectController.getSimpleProject(projectID);
			}

			project = projectController.getProject(projectID);

			Gson gson = new Gson();
			jsonResponse = gson.toJson(project);

		} catch (NumberFormatException e) {
			LOG.error("Input parameter has wrong format!", e);
		} catch (DataAccessingException e) {
			LOG.error("Cannot access data", e);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		response.getWriter().write(jsonResponse);

	}
}
