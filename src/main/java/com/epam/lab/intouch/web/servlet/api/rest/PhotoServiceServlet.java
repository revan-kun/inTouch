package com.epam.lab.intouch.web.servlet.api.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;

public class PhotoServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PHOTO_PATH = "/img/user_avatar/";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberLogin = request.getParameter("memberLogin");

		MemberController memberController = new MemberController();
		Member member = null;

		try {
			member = memberController.memberWithActiveProjectId(memberLogin);
		} catch (DAOException e) {
			// this block will be deleted
			throw new IOException("Cannot access database!", e);
		}

		if (member != null) {

			String photoName = member.getPhotoLink();

			InputStream photo = getServletContext().getResourceAsStream(PHOTO_PATH + photoName);

			System.out.print(photo);
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();

			byte[] data = new byte[2048];
			int read = 0;

			while ((read = photo.read(data)) != -1) {
				out.write(data, 0, read);
			}

		} else {
			response.getOutputStream().print(null);
		}

	}

}
