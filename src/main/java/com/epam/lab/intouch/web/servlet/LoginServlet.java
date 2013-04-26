package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.model.member.SimpleMember;
import com.epam.lab.intouch.service.member.SimpleMemberService;
import com.google.gson.Gson;

/**
 * 
 * @author Revan
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -3148083755614631111L;
	
	private SimpleMemberService memberService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		memberService = new SimpleMemberService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		validateMember(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		validateMember(request, response);
	}
	
	private void validateMember(HttpServletRequest request, HttpServletResponse response) throws IOException{
		final String userName = request.getParameter("userName");
		final String password = request.getParameter("password");
		
		SimpleMember member = memberService.login(userName, password);
		
		response.getWriter().write(new Gson().toJson(member));
	}
}
