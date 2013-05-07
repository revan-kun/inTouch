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

/**
 * Servlet implementation class MemberRegistration
 */
public class MemberRegistration extends HttpServlet {
	private final static Logger LOG = LogManager.getLogger(MemberRegistration.class);
	private static final String MEMBER_REGISTRATION_VIEW = "/registration.jsp";
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 MemberController controller = new MemberController();
		
		 try {
			controller.registerNewMember(getMember(request));
		} catch (DataAccessingException | InputDataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		

		response.sendRedirect("/index.jsp");
		
	}

	
}
