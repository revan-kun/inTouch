package com.epam.lab.intouch.web.servlet.api.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.member.common.MemberController;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.project.DefaultProjectDAO;
import com.epam.lab.intouch.dao.util.PropertiesReader;
import com.epam.lab.intouch.model.member.Member;

public class PhotoServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger(DefaultProjectDAO.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberLogin = request.getParameter("memberLogin");

		String path = PropertiesReader.getProperty("avatar_storage");

		MemberController memberController = new MemberController();
		Member member = null;

		try {
			member = memberController.memberWithActiveProjectId(memberLogin);

		} catch (DAOException e) {
			throw new IOException("Cannot access database!", e);
		} catch (DataAccessingException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		if (member != null) {
			String photoLocation = path + "/" + member.getPhotoLink();

			try (InputStream photo = new BufferedInputStream(new FileInputStream(new File(photoLocation)));) {

				LOG.debug("Photo location: " + photoLocation);

				// InputStream photo = getServletContext().getResourceAsStream(PHOTO_PATH + photoName);
				// InputStream photo = getServletContext().getResourceAsStream(photoLocation);

				if (photo != null) {
					response.setContentType("image/jpeg");
					OutputStream out = response.getOutputStream();

					byte[] data = new byte[2048];
					int read = 0;

					while ((read = photo.read(data)) != -1) {
						out.write(data, 0, read);
					}
				} 
			} catch (FileNotFoundException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

	}

}
