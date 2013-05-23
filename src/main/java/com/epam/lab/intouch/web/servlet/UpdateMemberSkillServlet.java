package com.epam.lab.intouch.web.servlet;

import static com.epam.lab.intouch.web.util.RequestParser.getUpdatedMemberSkills;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.member.skill.UpdateMemberSkillsController;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class UpdateMemberSkillServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5921508540693733134L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member loginedMember = (Member) req.getSession().getAttribute("member");
		List<Skill> skills = getUpdatedMemberSkills(req);
		UpdateMemberSkillsController controller = new UpdateMemberSkillsController();
		controller.updateMemberSkills(skills, loginedMember);
		resp.sendRedirect("/InTouch/memberProfile");
	}

}
