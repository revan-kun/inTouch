package com.epam.lab.intouch.web.servlet.api.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.exception.MemberAuthorizationException;
import com.epam.lab.intouch.controller.member.MemberController;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.SimpleMember;
import com.google.gson.Gson;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Just for test purpose
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userlogin");
		String password = request.getParameter("password");

		Member member = new Member();
		member.setLogin(userName);
		member.setPassword(password);

		MemberController controller = new MemberController();

		Member user;
		try {
			user = controller.authorizeMember(member);

			SimpleMember simpleMember = new SimpleMember();
			simpleMember.setFirstName(user.getFirstName());
			simpleMember.setLastName(user.getLastName());
			simpleMember.setLogin(user.getLogin());
			simpleMember.setPassword(user.getPassword());

			Gson gson = new Gson();
			String simpleMemberJSON = gson.toJson(simpleMember);

			response.getWriter().write(simpleMemberJSON);

		} catch (MemberAuthorizationException e) {
			response.getWriter().write("FAIL!!!!" + e);
		}

	}

}
