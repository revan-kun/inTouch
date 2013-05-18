package com.epam.lab.intouch.web.servlet;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;
import com.epam.lab.intouch.model.project.Project;

/**
 * 
 * @author Revan
 *
 */

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = -3148083755614631111L;
	
    public MemberServlet() {
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
		
		String logIn = request.getParameter("login");
		String password = request.getParameter("password");
		
//		Member result = null;
//		try {
//			result = new MemberController().authorizeMember(logIn, password);
//		} catch (DataAccessingException e) {
//			e.printStackTrace();
//		}
		
		Member result = getDummy(logIn, password);
			
		if (result != null) {
			request.setAttribute("member", result);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member_profile.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("index.html");
		}
			
	}
	
	private Member getDummy(final String logIn, final String password) {
		
		final Member member = new Member();
		if(logIn == null || logIn.isEmpty()) {
			member.setLogin("john.doe@epam.com");
		} else {
			member.setLogin(logIn);
		}
		member.setFirstName("John");
		member.setLastName("Doe");
		member.setPassword(password);
		member.setBirthday(new Date());
		member.setRegistrationDate(new Date());
		member.setPhotoURI("./img/zoom/vi.jpg");
		member.setQualificationLevel(QualificationLevel.JUNIOR);
		member.setSex(Sex.MALE);
		member.setRole(Role.DEVELOPER);
		member.setAdditionalInfo("\nAs I walk along the Bois de Boulogne\n" +
								 "With an independent air\n" +
								 "You can hear the girls declare\n" +
								 "\"He must be a Millionaire.\"\n" +
								 "You can hear them sigh and wish to die,\n" +
								 "You can see them wink the other eye\n" +
								 "At the man who broke the bank at Monte Carlo.");
		
		final Project project = new Project();
		project.setId(100500L);
		project.setProjectName("non-existing");
		project.setCreationDate(new Date());
		project.setCompletionDate(new Date());
		project.setDescription("We gonna done this project soon, I'm telling you...");
		
		final List<Member> temp = new ArrayList<Member>();
		temp.add(member);
		
		project.setMembers(temp);
		
		member.getProjects().add(project);
		
		return member;
	}
}
