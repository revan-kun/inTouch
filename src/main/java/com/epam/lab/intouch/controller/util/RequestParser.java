package com.epam.lab.intouch.controller.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletRequest;

import org.apache.log4j.Logger;

import com.epam.lab.intouch.controller.exception.InputDataFormatException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;

public class RequestParser {
	private final static Logger logger = Logger.getLogger(RequestParser.class);

	// we need localization to be able to do date format in more wide way
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	public Project getProjectFromRequest(ServletRequest request) throws InputDataFormatException {
		Project project = new Project();
		project.setId(Long.valueOf(request.getParameter(Attribute.PROJECT_ID)));
		project.setProjectName(request.getParameter(Attribute.PROJECT_NAME));

		Calendar creationDate = parseDate(request.getParameter(Attribute.PROJECT_CREATED));
		project.setCreationDate(creationDate);

		Calendar estimatedDate = parseDate(request.getParameter(Attribute.PROJECT_ESTIMATED_COMPLETION));
		project.setEstimatedCompletionDate(estimatedDate);

		Calendar completionDate = parseDate(request.getParameter(Attribute.PROJECT_COMPLETED));
		project.setEstimatedCompletionDate(completionDate);

		project.setDescription(request.getParameter(Attribute.PROJECT_DESCRIPTION));
		project.setCustomer(request.getParameter(Attribute.PROJECT_CUSTOMER));
		project.setStatus(ProjectStatus.fromString(request.getParameter(Attribute.PROJECT_STATUS)));

		return project;
	}

	public Member getMemberFromRequest(ServletRequest request) throws InputDataFormatException {
		Member member = new Member();
		member.setFirstName(request.getParameter(Attribute.MEMBER_NAME));
		member.setLastName(request.getParameter(Attribute.MEMBER_SURNAME));
		member.setLogin(request.getParameter(Attribute.MEMBER_LOGIN));
		member.setPassword(request.getParameter(Attribute.MEMBER_PASSWORD));

		Calendar birthdayDate = parseDate(request.getParameter(Attribute.MEMBER_BIRTHDAY));
		member.setBirthday(birthdayDate);

		Calendar registrationDate = parseDate(request.getParameter(Attribute.MEMBER_REGISTRATION_DATE));
		member.setRegistrationDate(registrationDate);

		Sex sex = Sex.fromString(request.getParameter(Attribute.MEMBER_SEX));
		member.setSex(sex);

		String qualification = request.getParameter(Attribute.MEMBER_QUALIFICATION);
		QualificationLevel qlevel = QualificationLevel.fromString(qualification);
		member.setQualificationLevel(qlevel);

		member.setExperience(Double.valueOf(request.getParameter(Attribute.MEMBER_EXPERIENCE)));
		member.setPhotoURI(request.getParameter(Attribute.MEMBER_PHOTO));
		member.setRole(Role.fromString(request.getParameter(Attribute.MEMBER_PROJECT_ROLE)));

		return member;
	}

	private Calendar parseDate(String date) throws InputDataFormatException {
		Calendar calendar;
		try {
			Date birthdayDate = new SimpleDateFormat(DATE_FORMAT).parse(date);
			calendar = GregorianCalendar.getInstance();
			calendar.setTime(birthdayDate);
		} catch (ParseException e) {
			logger.error("Wrong input format: " + e);
			throw new InputDataFormatException("Wrong input format: " + e);
		}

		return calendar;
	}

}
