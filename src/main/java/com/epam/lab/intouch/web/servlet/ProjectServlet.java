package com.epam.lab.intouch.web.servlet;



import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;

/**
 * Servlet implementation class ProjectServlet
 */

public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project project = new Project();
		project.setProjectName("Enigma");
		project.setDescription("Genuine WWII Enigma Machine");
		project.setCustomer("Wehrmacht");
		
		String date = "06.07.1932";
		String date2 = "06.07.1932";
		try {
			project.setCreationDate(new SimpleDateFormat("dd.MM.yyyy").parse(date));
			project.setEstimatedCompletionDate(new SimpleDateFormat("dd.MM.yyyy").parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		project.setStatus(ProjectStatus.OPEN);
		project.setId(4747L);
		
		List<Member> members = new ArrayList<>();
		members.add(getDummy("joe.doe@epam.com", "44", Role.MANAGER)); 
		members.add(getDummy("jannet.doe@epam.com", "45", Role.TESTER));
		members.add(getDummy("joe.doe@epam.com", "46", Role.DEVELOPER));
		
		project.setMembers(members);

		if (project != null) {
			request.setAttribute("project", project);
			RequestDispatcher dispatcher = request.getRequestDispatcher("project.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("index.html");
		}
		
	}
	
	private Member getDummy(final String logIn, final String password, final Role role) {
		
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
		member.setRole(role);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
