package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.lab.intouch.controller.exception.InputDataFormatException;
import com.epam.lab.intouch.controller.member.MemberController;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.web.util.RequestParser;

/**
 * Servlet implementation class MemberRegistration
 */
public class MemberRegistration extends HttpServlet {
	private final static Logger logger = Logger.getLogger(MemberRegistration.class);
	private static final String MEMBER_REGISTRATION_VIEW = "/WEB-INF/pages/Registration.jsp";
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestParser parser = new RequestParser();
		MemberController controller = new MemberController();

		try {
			controller.create(parser.getMemberFromRequest(request));
		} catch (InputDataFormatException e) {
			logger.error("Input data is not valid: " + e);
		} catch (DAOException e) {
			logger.error("User was not saved: " + e);
		}

		response.getWriter().write("Success!");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(MEMBER_REGISTRATION_VIEW).forward(req, resp);
	}

}
