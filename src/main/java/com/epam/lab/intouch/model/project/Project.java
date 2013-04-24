package com.epam.lab.intouch.model.project;

import java.util.Date;
import java.util.List;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;

public class Project {
	private Long id;
	private String projectName;
	private Date creationDate;
	private Date estimatedCompletionDate;
	private Date completionDate;
	private String description;
	private String customer;
	private List<Member> members;
	private ProjectStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCustomer(final String customer) {
		this.customer = customer;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setEstimatedCompletionDate(final Date estimatedCompletionDate) {
		this.estimatedCompletionDate = estimatedCompletionDate;
	}

	public void setCompletionDate(final Date completionDate) {
		this.completionDate = completionDate;
	}

	public void setMembers(final List<Member> members) {
		this.members = members;
	}

	public void setProjectName(final String projectName) {
		this.projectName = projectName;
	}

	public void setCreationDate(final Date creationDate) {
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

	public Date getEstimatedCompletionDate() {
		return this.estimatedCompletionDate;
	}

	public Date getCompletionDate() {
		return this.completionDate;
	}

	public List<Member> getMembers() {
		return this.members;
	}

	public String getName() {
		return this.projectName;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public ProjectStatus getStatus() {
		return this.status;
	}

}
