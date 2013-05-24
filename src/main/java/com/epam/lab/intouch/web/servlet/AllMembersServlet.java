package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.dao.exception.DAOException;

/**
 * @author Revan
 *
 */
public class AllMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger(AllMembersServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllMembersServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberController controller = new MemberController();
		
		int number = -1;
		
		String temp = request.getParameter("number");
		if(temp != null && !temp.isEmpty()) {
			number = Integer.parseInt(temp) ;
		} else {
			request.setAttribute("all", true);
		}
	
		try {	
			request.setAttribute("members", controller.getLastRegisteredMembers(number));

			getServletConfig().getServletContext().getRequestDispatcher("/pages/allMembers.jsp").forward(request, response);
		} catch (DAOException e) {
			LOG.error("Can't retrieve last registered members", e);
		}
	}
}
