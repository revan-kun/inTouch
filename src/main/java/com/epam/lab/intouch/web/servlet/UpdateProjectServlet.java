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
import com.epam.lab.intouch.controller.project.ProjectController;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.web.util.RequestParser;


/**
 * @author Revan
 *
 */
public class UpdateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOG = LogManager.getLogger(UpdateProjectServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProjectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final long projectId = Long.valueOf(request.getParameter("projectId"));
		
		ProjectController controller = new ProjectController();
		Project project = null;
		
		try {
			project = RequestParser.getUpdatedProject(request, controller.getProject(projectId));	
			controller.updateProject(project);
			
			request.setAttribute("project", project);
			response.sendRedirect("project?id="+project.getId());
		} catch (InputDataFormatException e) {
			LOG.error("Unable to parse request while updating project", e);
		} catch (DataAccessingException e) {
			LOG.error("Unable to retrieve project", e);
		}
	}

}
