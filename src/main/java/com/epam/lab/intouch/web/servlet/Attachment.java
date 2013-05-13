package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.web.util.AttachmentProvider;

@MultipartConfig
public class Attachment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AttachmentProvider attachmentProvider;
	
	public void init(){
		attachmentProvider = new AttachmentProvider();
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//AttachmentProvider attachmentProvider = new AttachmentProvider();
		attachmentProvider.processRequest(request, response);
	}

}
