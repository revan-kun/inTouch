package com.epam.lab.intouch.web.servlet.api.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
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
		} catch (DataAccessingException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		if (member != null) {
			try {
				String photoName = member.getPhotoLink();

				InputStream photo = getServletContext().getResourceAsStream(PHOTO_PATH + photoName);

				if (photo != null) {
					response.setContentType("image/jpeg");
					OutputStream out = response.getOutputStream();

					byte[] data = new byte[2048];
					int read = 0;

					while ((read = photo.read(data)) != -1) {
						out.write(data, 0, read);
					}
				}else{
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
			} catch (FileNotFoundException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

	}

}
