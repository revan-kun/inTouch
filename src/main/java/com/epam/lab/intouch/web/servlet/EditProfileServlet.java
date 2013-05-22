package com.epam.lab.intouch.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.controller.skill.SkillController;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;

public class EditProfileServlet extends HttpServlet {

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
			List<Skill> list1 = skillController.getSkills(SkillType.LANGUAGE);
			List<Skill> list2 = skillController.getSkills(SkillType.PROGRAMMING);
			List<Skill> list3 = skillController.getSkills(SkillType.TECHNOLOGY);
		} catch (DataAccessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		try {
			request.setAttribute("languageSkills", skillController.getSkills(SkillType.LANGUAGE));
			request.setAttribute("programmingSkills", skillController.getSkills(SkillType.PROGRAMMING));
			request.setAttribute("technologySkills", skillController.getSkills(SkillType.TECHNOLOGY));
			if (memberController.getProgrammingSkills(loginedMember) != null)
				request.setAttribute("memberProgrammingSkills", memberController.getProgrammingSkills(loginedMember));
			if (memberController.getLanguageSkills(loginedMember) != null)
				request.setAttribute("memberLanguageSkills", memberController.getLanguageSkills(loginedMember));
			if (memberController.getTechnologySkills(loginedMember) != null)
				request.setAttribute("memberTechnologySkills", memberController.getTechnologySkills(loginedMember));

			getServletConfig().getServletContext().getRequestDispatcher("/pages/editProfile.jsp").forward(request, response);
		} catch (DataAccessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// response.sendRedirect("/InTouch/profile.jsp");
	}

}
