package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.controller.skill.SkillController;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.SkillType;
import com.sun.org.apache.regexp.internal.recompile;

public class CallProfileEditorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SkillController skillController;
	private MemberController memberController;

	@Override
	public void init() throws ServletException {
		super.init();
		skillController = new SkillController();
		memberController = new MemberController();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member loginedMember = (Member) request.getSession().getAttribute("member");
		
		
		try {
			request.setAttribute("languageSkills", skillController.getSkills(SkillType.LANGUAGE));
			request.setAttribute("programmingSkills", skillController.getSkills(SkillType.PROGRAMMING));
			request.setAttribute("technologySkills", skillController.getSkills(SkillType.TECHNOLOGY));
			request.setAttribute("memberProgrammingSkills", memberController.getProgrammingSkills(loginedMember));
			request.setAttribute("memberLanguageSkills", memberController.getLanguageSkills(loginedMember));
			request.setAttribute("memberTechnologySkills", memberController.getTechnologySkills(loginedMember));
			request.getRequestDispatcher("/profile.jsp").forward(request, response);
		} catch (DataAccessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.sendRedirect("/InTouch/profile.jsp");
	}
	

}
