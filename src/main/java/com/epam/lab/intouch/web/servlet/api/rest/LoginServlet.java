package com.epam.lab.intouch.web.servlet.api.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.model.member.Member;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -963277169431608297L;

	// Just for test purpose
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userlogin = request.getParameter("userlogin");
		String password = request.getParameter("password");

//		Member member = new Member();
//		member.setLogin(userName);
//		member.setPassword(password);
//		member.setQualificationLevel(QualificationLevel.JODA);
//		member.setFirstName("Danyyylo");
//		member.setSex(Sex.MALE);
//		member.setRole(Role.DEVELOPER);
//
//		Project project1 = new Project();
//		project1.setId(777L);
//		project1.setProjectName("Lenus");
//		project1.setCreationDate(new Date());
//		project1.setCompletionDate(new Date());
//		project1.setDescription("asdasdas asd asd assd ");
//		List<Member> list1 = new ArrayList<Member>();
//		// list1.add(member);
//		list1.add(new Member("newUser1", "111222333"));
//		project1.setMembers(list1);
//
//		member.getActiveProjects().add(project1);
		// Gson gson = new Gson();
		
		MemberController memberController=new MemberController();
		
		Member autorized=null;
		try {
			autorized = memberController.authorizeMember(userlogin, password);
		} catch (DataAccessingException e) {
			System.out.println("DATA accessing problems");
		}

		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		Gson gson = builder.create();
		String simpleMemberJSON = gson.toJson(autorized);

		response.getWriter().write(simpleMemberJSON);

	}

}
