package com.epam.lab.intouch.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.project.ProjectController;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.service.history.HistoryService;

/**
 * @author Revan
 * 
 */
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger(ProjectServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long projectID = Long.valueOf(request.getParameter("id"));

		final Member member = (Member) request.getSession().getAttribute("member");

		Project project = null;
		List<Member> list = null;
		try {
			project = new ProjectController().getProject(projectID);
			list = new HistoryService().getProjectHistory(project);
		} catch (DataAccessingException | DAOException ex) {
			LOG.error("An error occurred while retrieving project and history", ex);
		}

		if (project != null) {
			request.setAttribute("project", project);
			request.setAttribute("history", list);

			if (!project.isClosed() && member.isManager() && project.getMembers().contains(member)) {
				getServletConfig().getServletContext().getRequestDispatcher("/pages/project_edit.jsp").forward(request, response);
			} else {
				getServletConfig().getServletContext().getRequestDispatcher("/pages/project_view.jsp").forward(request, response);
			}
		} else {
			LOG.warn("Something goes wrong with viewing project, id=" + projectID);
			response.sendRedirect("index.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
