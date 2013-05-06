package com.epam.lab.intouch.web.servlet.api.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;
import com.epam.lab.intouch.model.project.Project;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String userName = request.getParameter("userlogin");
//		String password = request.getParameter("password");
//
//		Member member = new Member();
//		member.setLogin(userName);
//		member.setPassword(password);
//
//		Gson gson = new Gson();
//		String simpleMemberJSON = gson.toJson(member);
//
//		response.getWriter().write(simpleMemberJSON);

	}

	// Just for test purpose
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String userName = request.getParameter("userlogin");
//		String password = request.getParameter("password");
//
//		Member member = new Member();
//		member.setLogin(userName);
//		member.setPassword(password);
//
//		MemberController controller = new MemberController();
//
//		Member user;
//		try {
//			user = controller.authorizeMember(member);
//
//			SimpleMember simpleMember = new SimpleMember();
//			simpleMember.setFirstName(user.getFirstName());
//			simpleMember.setLastName(user.getLastName());
//			simpleMember.setLogin(user.getLogin());
//			simpleMember.setPassword(user.getPassword());
//
//			Gson gson = new Gson();
//			String simpleMemberJSON = gson.toJson(simpleMember);
//
//			response.getWriter().write(simpleMemberJSON);
//
//		} catch (MemberAuthorizationException e) {
//			response.getWriter().write("FAIL!!!!" + e);
//		}

		String userName = request.getParameter("userlogin");
		String password = request.getParameter("password");

		Member member = new Member();
		member.setLogin(userName);
		member.setPassword(password);
		member.setQualificationLevel(QualificationLevel.JODA);
		member.setFirstName("Danyyylo");
		member.setSex(Sex.MALE);
		member.setRole(Role.DEVELOPER);
		
		
		Project project1 = new Project();
		project1.setId(777L);
		project1.setProjectName("Lenus");
		project1.setCreationDate(new Date());
		project1.setCompletionDate(new Date());
		project1.setDescription("asdasdas asd asd assd ");
		List<Member> list1=new ArrayList<Member>();
		//list1.add(member);
		list1.add(new Member("newUser1","111222333"));
		project1.setMembers(list1);
		
		member.getProjects().add(project1);
//		Gson gson = new Gson();
		
		GsonBuilder builder = new GsonBuilder();
	    builder.excludeFieldsWithoutExposeAnnotation();

	    Gson gson = builder.create();
		String simpleMemberJSON = gson.toJson(member);

		response.getWriter().write(simpleMemberJSON);
		
	}

}
