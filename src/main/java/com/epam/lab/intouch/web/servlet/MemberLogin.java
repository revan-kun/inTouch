package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.exception.InputDataFormatException;
import com.epam.lab.intouch.controller.exception.MemberAuthorizationException;
import com.epam.lab.intouch.controller.member.MemberController;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.web.util.RequestParser;

public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member=null;
		try {
			member=RequestParser.getMember(request);
			MemberController controller= new MemberController();
			controller.authorizeMember(member);
			
			response.getWriter().write("User "+member.getLogin()+" was authorized!");
			
		} catch (InputDataFormatException e) {
			response.getWriter().write("Data is not valid");
		} catch (MemberAuthorizationException e) {
			response.getWriter().write("Member"+member+" is not registered!");
		}
		
	}

}
