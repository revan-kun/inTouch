package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.exception.InputDataFormatException;
import com.epam.lab.intouch.controller.exception.PermissionException;
import com.epam.lab.intouch.controller.member.special.ManagerController;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.web.util.RequestParser;

/**
 * @author Revan
 * 
 */
public class CreateProjectSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager .getLogger(CreateProjectSevlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateProjectSevlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletConfig().getServletContext().getRequestDispatcher("/pages/createProject.jsp").forward(request, response);
		// response.sendRedirect("/pages/createProject.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerController controller = new ManagerController();

		Member manager = (Member) request.getSession().getAttribute("member");
		Project project = null;
		try {
			project = RequestParser.getProject(request);
			controller.createNewProject(manager, project);
		} catch (PermissionException | DataAccessingException | InputDataFormatException e) {
			LOG.error("", e);
		}

		if (project != null) {
			response.sendRedirect("project?id=" + project.getId());
		}
	}

}
