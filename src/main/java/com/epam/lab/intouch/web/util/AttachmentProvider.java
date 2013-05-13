package com.epam.lab.intouch.web.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

@MultipartConfig
public class AttachmentProvider {
	
	private String fullPath = "";
	private String newFileName = "";

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		PrintWriter out = response.getWriter();
		if (!isMultipart) {
		} else {
			
			File repository = new File(request.getServletContext().getRealPath("/images"));
			if (!repository.exists()) {
				repository.mkdir();
			}
			try {
				// String newFileName = request.getParameter("newFileName");
				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
				diskFileItemFactory.setRepository(repository); // set temporary
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
				List<FileItem> uploadedItems = servletFileUpload.parseRequest(request); // get
																						// form
				// fields

				for (FileItem fileItem : uploadedItems) {
					if (fileItem.isFormField()) { // if file item is a normal
													// form
													// // filed // take some
													// action
						String fieldName = fileItem.getFieldName();

						if (fieldName.equals("newFileName")) {
							newFileName = fileItem.getString();
						}

						// System.out.println("FIELDNAME:" + newFileName);

					}
				}

				for (FileItem fileItem : uploadedItems) {
					if (!fileItem.isFormField()) {

						String name = fileItem.getName();

//						System.out.println("NAME:" + name);
//
//						System.out.println(repository.getAbsolutePath());

						String extension = FilenameUtils.getExtension(name);
						fullPath = repository.getAbsolutePath() + "\\" + newFileName + "." + extension;
						File uploadedFile = new File(fullPath);
						System.out.println(fullPath);
						fileItem.write(uploadedFile);
						
						

					}
				}

			} catch (Exception e) {

				e.printStackTrace();
			} finally {
				out.close();
			}
		}

	}
	


}
