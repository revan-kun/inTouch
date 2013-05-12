package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.finder.MemberFinder;

public class SearchServlet extends HttpServlet {
	private final static Logger LOG = LogManager.getLogger(SearchServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberFinder memberFinder = new MemberFinder();
		try {
			request.setAttribute("members", memberFinder.gerAllMembers());
		} catch (DataAccessingException e) {
			LOG.error("Something wrong with data accessing! ", e);
		}
		
		getServletConfig().getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
