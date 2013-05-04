package com.epam.lab.intouch.model.project;

import java.util.Date;
import java.util.List;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project {
	@Expose
	@SerializedName("id")
	private Long id;

	@Expose
	@SerializedName("projectName")
	private String projectName;

	@Expose
	@SerializedName("creationDate")
	private Date creationDate;

	private Date estimatedCompletionDate;

	@Expose
	@SerializedName("completionDate")
	private Date completionDate;

	@Expose
	@SerializedName("description")
	private String description;

	private String customer;

	@Expose
	@SerializedName("members")
	private List<Member> members;

	@Expose
	@SerializedName("status")
	private ProjectStatus status;

	private Boolean checkStatus(ProjectStatus status) {
		Boolean result = false;

		if (this.getStatus() != null && this.getStatus() == status) {
			result = true;
		}

		return result;
	}

	public Boolean isOpen() {
		return checkStatus(ProjectStatus.OPEN);
	}

	public Boolean isClosed() {
		return checkStatus(ProjectStatus.CLOSED);
	}

	public Boolean isFrozen() {
		return checkStatus(ProjectStatus.FROZEN);
	}

	public Boolean isAbandoned() {
		return checkStatus(ProjectStatus.ABANDONED);
	}

	public Boolean addMember(Member member) {
		return members.add(member);
	}

	public Boolean removeMember(Member member) {
		return members.remove(member);
	}

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

	public String getProjectName() {
		return this.projectName;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public ProjectStatus getStatus() {
		return this.status;
	}

}
