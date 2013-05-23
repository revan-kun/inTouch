package com.epam.lab.intouch.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.controller.member.like.LikeControler;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;

/**
 * Servlet implementation class MemberFeelingServlet
 */

public class MemberFeelingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LikeControler likeControler;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFeelingServlet() {
        likeControler = new LikeControler();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member liker = (Member) request.getSession().getAttribute("member");
		String strOwner  = request.getParameter("memberLiker");
		MemberController controller = new MemberController();
		
		
		
		String status = request.getParameter("status");
		
		/*String own = "grispin@gmail.com";
		//Member ownM = new Member();
		
		
		
		String lik = "dobkin@epam.com";
		Member likM = new Member();
		likM.setLogin(lik);
		String st = "like";*/
		
		try {
			likeControler.setRating(controller.getById(strOwner), liker, status );
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("/InTouch/member?login="+strOwner);
	}

}
