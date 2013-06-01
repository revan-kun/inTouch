package com.epam.lab.intouch.web.servlet;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.controller.member.like.LikeController;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.LikeStatus;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.web.util.RequestParser;


/**
 * 
 * @author Revan
 *
 */

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = -3148083755614631111L;
	private MemberController controller;
	private final LikeController likeControler;
	
    public MemberServlet() {
        super();
        controller = new MemberController();
        likeControler = new LikeController();
    }
    
	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginedMember = (Member) request.getSession().getAttribute("member"); 
		
		String login = RequestParser.changeEncoding(request.getParameter("login"));
		
		if(checkMember(request, login)){
			response.sendRedirect("/InTouch/memberProfile");
			return;
		}
			
		
		List<Project> memberProjectsHistory = new LinkedList<Project>();
		Member result = null;
		try {
			memberProjectsHistory = controller.getMemberProjectsHistory(login);
			result = controller.getMemberByLogin(login);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		if (result != null ) {
			request.setAttribute("member", result);
			request.setAttribute("memberProjectsHistory", memberProjectsHistory);
			
			LikeStatus st = likeControler.getStatusFromDB(result, loginedMember);
			request.setAttribute("statusInDB", st);
			
			getServletConfig().getServletContext().getRequestDispatcher("/pages/memberProfile.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
	}
	
	private boolean checkMember(HttpServletRequest request, String login){
		Member loginedMember = (Member) request.getSession().getAttribute("member");
		return loginedMember.getLogin().equals(login);
			
		
	}
}
