package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.model.member.Member;


/**
 * @author Revan
 * 
 */
public class UserCheckServlet extends HttpServlet {
	private static final long serialVersionUID = -3148083755614631111L;
	private final static Logger LOG = LogManager.getLogger(MemberRegistrationServlet.class);

	private MemberController controller;

	public UserCheckServlet() {
		super();
		controller = new MemberController();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String login = request.getParameter("login");
		final String password = request.getParameter("password");

		boolean result = false;
		try {
			if (this.checkParam(login)) {
				result = this.checkLogin(login);
			} else if (this.checkParam(password)) {
				final Member temp = (Member) request.getSession().getAttribute("member");
				result = this.checkPassword(temp.getLogin(), password);
			} 
		} catch (DataAccessingException e) {
			LOG.error("Cannot access data while checking user");
		}

		response.getWriter().write(Boolean.toString(result));
	}

	/**
	 * Checks if given String is a valid  request parameter (exists and not empty)
	 * 
	 * @param parameter
	 * @return
	 */
	private boolean checkParam(final String parameter) {
		return parameter != null && !parameter.isEmpty();
	}

	/**
	 * Checks if login already exists in DB, if it exists then method returns {@code true}
	 * 
	 * @param login
	 * @return
	 * @throws DataAccessingException
	 */
	private boolean checkLogin(final String login) throws DataAccessingException {
		return this.controller.checkMemberIfExists(login);
	}

	/**
	 * Checks if given Member password is valid and returns {@code true}, if not - returns {@code false}
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws DataAccessingException
	 */
	private boolean checkPassword(final String login, final String password) throws DataAccessingException {
		boolean flag = false;

		if (this.controller.authorizeMember(login, password) != null) {
			flag = true;
		}

		return flag;
	}
}
