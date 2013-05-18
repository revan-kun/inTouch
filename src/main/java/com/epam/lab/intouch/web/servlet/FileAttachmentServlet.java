package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.web.util.AttachmentProvider;

@MultipartConfig
public class FileAttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AttachmentProvider attachmentProvider;
	MemberController memberController;
	
	public void init(){
		attachmentProvider = new AttachmentProvider();
		memberController = new MemberController();
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//AttachmentProvider attachmentProvider = new AttachmentProvider();
		Member loginedMember = (Member) request.getSession().getAttribute("member");
		//Member updatedmember = new Member();
		Member oldMember = new Member();
		//request.getParameter(arg0)
		
		attachmentProvider.processRequest(request, loginedMember.getLogin());
		loginedMember.setPhotoLink(attachmentProvider.getUserPhotoName());
		//request.getSession().setAttribute("member", loginedMember);
		
		oldMember.setLogin(loginedMember.getLogin());
		try {
			memberController.updateProfile(oldMember, loginedMember);
		} catch (DataAccessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("member_profile.jsp");
		
	}

}
