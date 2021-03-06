package com.epam.lab.intouch.web.servlet;

import static com.epam.lab.intouch.web.util.RequestParser.getMember;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.exception.InputDataFormatException;
import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;

/**
 * Servlet implementation class MemberRegistration
 */
public class MemberRegistrationServlet extends HttpServlet {
	private final static Logger LOG = LogManager.getLogger(MemberRegistrationServlet.class);
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberController controller = new MemberController();

		try {
			Member currentMember = getMember(request);
			if (controller.registerNewMember(currentMember)) {
				
				request.getSession().setAttribute("member", controller.getById(currentMember.getLogin()));
				response.sendRedirect(request.getContextPath()+"/memberProfile");

			} else {
			getServletConfig().getServletContext().getRequestDispatcher(request.getContextPath()+"/registration").forward(request, response);
			}

		} catch (DataAccessingException | InputDataFormatException | DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
