package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private final static Logger LOG = LogManager.getLogger(GoHomeServlet.class);
	
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public GoHomeServlet(){
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletConfig().getServletContext().getRequestDispatcher("/pages/welcome.jsp").forward(request, response);


	}

	
	
}
