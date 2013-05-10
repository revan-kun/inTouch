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

public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 5703053454322958752L;
	private final static Logger LOG = LogManager.getLogger(ProjectServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectController projectController = new ProjectController();
		Project project = null;
		String jsonResponse = null;
		try {
			long projectID = Long.parseLong(request.getParameter("projectID"));
			project = projectController.getProject(projectID);

			Gson gson = new Gson();
			jsonResponse = gson.toJson(project);

		} catch (NumberFormatException e) {
			LOG.error("Input parameter has wrong format!", e);
		} catch (DataAccessingException e) {
			LOG.error("Cannot access data", e);
		}

		response.getWriter().write(jsonResponse);

	}
}
