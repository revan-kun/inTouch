package com.epam.lab.intouch.web.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public class MemberProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 5751447342506043798L;
	
	private MemberController controller;

	@Override
	public void init() throws ServletException {
		super.init();
		controller = new MemberController();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member loginedMember = (Member) req.getSession().getAttribute("member");
		List<Project> memberProjectsHistory = new LinkedList<Project>();
		memberProjectsHistory = controller.getMemberProjectsHistory(loginedMember.getLogin());
		
		req.setAttribute("memberProjectsHistory", memberProjectsHistory);
		getServletConfig().getServletContext().getRequestDispatcher("/pages/memberProfile.jsp").forward(req, resp);
		
	}
	
	

}
