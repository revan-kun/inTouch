package com.epam.lab.intouch.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.finder.MemberFinder;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.web.util.RequestQueryParser;

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
		MemberFinder finder = new MemberFinder();
		RequestQueryParser parser = new RequestQueryParser();
		String query = parser.getQuery(request);

		LOG.debug("Result query: "+query);
		List<Member> members = new ArrayList<Member>();
		try {
			members = finder.findMembers(query);
		} catch (DataAccessingException e) {
			LOG.error("Problems with data accessing!", e);
		}

		request.setAttribute("members", members);
		getServletConfig().getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
	}

}
