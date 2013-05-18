package com.epam.lab.intouch.web.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@MultipartConfig
public class AttachmentProvider {
	public static final String ATTACHMENT_DIRECTORY = "C:/Users/Axel/git/inTouch/WebContent/img/user_avatar";

	private final static Logger LOG = LogManager.getLogger(AttachmentProvider.class);

	private String fullPath = "";
	private String userPhotoLink;
	private List<FileItem> uploadedItems;

	public void processRequest(HttpServletRequest request, String userLogin) throws ServletException, IOException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (!isMultipart) {
		} else {

			// File repository = new
			// File(request.getServletContext().getRealPath("/images"));

			// String newFileName = request.getParameter("newFileName");
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			diskFileItemFactory.setRepository(getRepository()); // set temporary
			// dir
			// where
			// uploaded
			// files
			// will be
			// stored
			diskFileItemFactory.setSizeThreshold(5 * 1024 * 1024); // set
																	// the
																	// file
																	// size
																	// threshold
																	// beyond
																	// from
																	// which
																	// file
																	// will
																	// be
																	// stored
																	// in
																	// the
																	// disk
			ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory); // initialize
																								// form
																								// handler
			try {
				uploadedItems = servletFileUpload.parseRequest(request);
			} catch (FileUploadException e) {
				LOG.error("Some problems with File Upload", e);
				e.printStackTrace();
			}

			for (FileItem fileItem : uploadedItems) {
				if (!fileItem.isFormField()) {
					setUserPhotoName(userLogin);
					fullPath = getRepository().getAbsolutePath() + "\\" + getUserPhotoName();
					File uploadedFile = new File(fullPath);
					System.out.println(fullPath);
					try {
						fileItem.write(uploadedFile);
					} catch (Exception e) {
						LOG.error("Problem with writing file to repository");
						e.printStackTrace();
					}

				}
			}
		}
	}

	private void setUserPhotoName(String userLogin) {
		String extension = "";
		

		for (FileItem fileItem : uploadedItems) {
			if (!fileItem.isFormField()) {

				extension = FilenameUtils.getExtension(fileItem.getName());
			}
		}

		this.userPhotoLink = userLogin+ '.' + extension;

	}

	public String getUserPhotoName() {
		return userPhotoLink;
	}

	private File getRepository() {
		File repository = new File(ATTACHMENT_DIRECTORY);
		if (!repository.exists()) {
			repository.mkdir();
		}

		return repository;

	}

}
