package com.epam.lab.intouch.model.project;

import java.util.Calendar;
import java.util.List;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;

public class Project {
	private String projectName;
	private Calendar creationDate;
	private Calendar estimatedCompletionDate;
	private Calendar completionDate;
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

	public void setEstimatedCompletionDate(final Calendar estimatedCompletionDate) {
		this.estimatedCompletionDate = estimatedCompletionDate;
	}

	public void setCompletionDate(final Calendar completionDate) {
		this.completionDate = completionDate;
	}

	public void setMembers(final List<Member> members) {
		this.members = members;
	}

	public void setProjectName(final String projectName) {
		this.projectName = projectName;
	}

	public void setCreationDate(final Calendar creationDate) {
		this.creationDate = creationDate;
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

	public Calendar getEstimatedCompletionDate() {
		return this.estimatedCompletionDate;
	}

	public Calendar getCompletionDate() {
		return this.completionDate;
	}

	public List<Member> getMembers() {
		return this.members;
	}

	public String getName() {
		return this.projectName;
	}

	public Calendar getCreationDate() {
		return this.creationDate;
	}

	public ProjectStatus getStatus() {
		return this.status;
	}

}
