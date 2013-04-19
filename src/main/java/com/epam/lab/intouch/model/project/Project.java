package com.epam.lab.intouch.model.project;

import java.util.Calendar;
import java.util.List;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;

public class Project {
	private String projectName;
	private Calendar startDate;
	private Calendar estimatedFinishDate;
	private Calendar finishDate;
	private String description;
	private String customer;
	private List<Member> members;
	private ProjectStatus status;

	public void setCustomer(final String customer) {
		this.customer = customer;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setEstimatedFinishDate(final Calendar estimatedFinishDate) {
		this.estimatedFinishDate = estimatedFinishDate;
	}

	public void setFinishDate(final Calendar finishDate) {
		this.finishDate = finishDate;
	}

	public void setMembers(final List<Member> members) {
		this.members = members;
	}

	public void setProjectName(final String projectName) {
		this.projectName = projectName;
	}

	public void setStartDate(final Calendar startDate) {
		this.startDate = startDate;
	}

	public void setStatus(final ProjectStatus status) {
		this.status = status;
	}
	
	public String getCustomer() {
		return this.customer;
	}

	public String getDescription() {
		return this.description;
	}

	public Calendar getEstimatedFinishDate() {
		return this.estimatedFinishDate;
	}

	public Calendar getFinishDate() {
		return this.finishDate;
	}

	public List<Member> getMembers() {
		return this.members;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public Calendar getStartDate() {
		return this.startDate;
	}

	public ProjectStatus getStatus() {
		return this.status;
	}

}
