package com.epam.lab.intouch.model.project;

import java.util.Date;
import java.util.List;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.enums.Status;

public class Project {
	//dfefdnjdjdnfjndfndjfjdf
	//dfdfdf
	private String projectName;
	private Date startDate;
	private Date estimatedFinishDate;
	private Date finishDate;
	private String description;
	private String customer;
	private List<Member> members;
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEstimatedFinishDate() {
		return estimatedFinishDate;
	}

	public void setEstimatedFinishDate(Date estimatedFinishDate) {
		this.estimatedFinishDate = estimatedFinishDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}
