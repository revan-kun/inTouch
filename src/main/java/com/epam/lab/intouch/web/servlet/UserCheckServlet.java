package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.model.member.Member;
import com.google.gson.Gson;

/**
 * 
 * @author Revan
 * 
 */

public class UserCheckServlet extends HttpServlet {
	private static final long serialVersionUID = -3148083755614631111L;

	public UserCheckServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Member member = (Member) request.getSession().getAttribute("member");
		if (member != null)
			response.getWriter().write(new Gson().toJson(new SimpleMember(member.getFirstName())));
		else
			response.getWriter().write("Hello");

	}

	private class SimpleMember {
		public String login;

		public SimpleMember(String login) {
			this.login = login;
		}

	}
}
