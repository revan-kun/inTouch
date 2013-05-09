package com.epam.lab.intouch.model.member;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.project.Project;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member extends SimpleMember {

	private Double experience;
	private List<Skill> skills;

	@Expose
	@SerializedName("projects")
	private List<Project> activeProjects;

	private List<Project> historyProjects;

	private QualificationLevel qLevel;
	private Integer rating;
	private String photoLink;
	private String additionalInfo;

	public Member() {
		activeProjects = new ArrayList<Project>();
		historyProjects = new ArrayList<Project>();
	}

	public Member(String login, String password) {
		super(login, password);
	}

	private Boolean checkRole(Role role) {
		Boolean result = false;

		if (this.getRole() != null && this.getRole() == role) {
			result = true;
		}

		return result;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Boolean isManager() {
		return checkRole(Role.MANAGER);
	}

	public Boolean isDeveloper() {
		return checkRole(Role.DEVELOPER);
	}

	public Boolean isTester() {
		return checkRole(Role.TESTER);
	}

	public Boolean addActiveProject(Project project) {
		return activeProjects.add(project);
	}

	public Boolean removeActiveProject(Project project) {
		return activeProjects.remove(project);
	}

	public Boolean addHistoryProject(Project project) {
		return historyProjects.add(project);
	}

	public Boolean removeHistoryProject(Project project) {
		return historyProjects.remove(project);
	}

	public void setExperience(final Double experience) {
		this.experience = experience;
	}

	public void setPhotoLink(final String photoLink) {
		this.photoLink = photoLink;
	}

	public void setQualificationLevel(final QualificationLevel qLevel) {
		this.qLevel = qLevel;
	}

	public Double getExperience() {
		return this.experience;
	}

	public String getPhotoLink() {
		return this.photoLink;
	}

	public QualificationLevel getQualificationLevel() {
		return this.qLevel;
	}

	public List<Project> getActiveProjects() {
		return activeProjects;
	}

	public void setActiveProjects(List<Project> activeProjects) {
		this.activeProjects = activeProjects;
	}

	public List<Project> getHistoryProjects() {
		return historyProjects;
	}

	public void setHistoryProjects(List<Project> historyProjects) {
		this.historyProjects = historyProjects;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("getLogin()=");
		builder.append(getLogin());
		builder.append(", \n getFirstName()=");
		builder.append(getFirstName());
		builder.append(", \n getLastName()=");
		builder.append(getLastName());
		builder.append(", \n getPassword()=");
		builder.append(getPassword());
		builder.append(", \n getRole()=");
		builder.append(getRole());
		builder.append(", \n getBirthday()=");
		builder.append(getBirthday());
		builder.append(", \n getSex()=");
		builder.append(getSex());
		builder.append("Member [getRating()=");
		builder.append(getRating());
		builder.append(", \n getExperience()=");
		builder.append(getExperience());
		builder.append(", \n getPhotoURI()=");
		builder.append(getPhotoLink());
		builder.append(", \n getQualificationLevel()=");
		builder.append(getQualificationLevel());
		builder.append(", \n getActiveProjects()=");
		builder.append(getActiveProjects());
		builder.append(", \n getHistoryProjects()=");
		builder.append(getHistoryProjects());
		builder.append(", \n getAdditionalInfo()=");
		builder.append(getAdditionalInfo());
		builder.append(", \n getSkills()=");
		builder.append(getSkills());
		builder.append(", \n getRegistrationDate()=");
		builder.append(getRegistrationDate());
		builder.append("]");
		return builder.toString();
	}

	public boolean equals(Object obj){
		Member member = (Member) obj;
		return member.getLogin().equals(this.getLogin());
	}

}
