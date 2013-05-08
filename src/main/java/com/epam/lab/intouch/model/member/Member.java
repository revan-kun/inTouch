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

	public void setPhotoLink(final String photoURI) {
		this.photoLink = photoURI;
	}

	public void setQualificationLevel(final QualificationLevel qLevel) {
		this.qLevel = qLevel;
	}

	public Double getExperience() {
		return this.experience;
	}

	public String getPhotoURI() {
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
		builder.append("Member [experience=");
		builder.append(experience);
		builder.append(", \n skills=");
		builder.append(skills);
		builder.append(", \n activeProjects=");
		builder.append(activeProjects);
		builder.append(", \n historyProjects=");
		builder.append(historyProjects);
		builder.append(", \n qLevel=");
		builder.append(qLevel);
		builder.append(", \n rating=");
		builder.append(rating);
		builder.append(", \n photoLink=");
		builder.append(photoLink);
		builder.append(", \n additionalInfo=");
		builder.append(additionalInfo);
		builder.append(", \n getRole()=");
		builder.append(getRole());
		builder.append(", \n getBirthday()=");
		builder.append(getBirthday());
		builder.append(", \n getFirstName()=");
		builder.append(getFirstName());
		builder.append(", \n getLastName()=");
		builder.append(getLastName());
		builder.append(", \n getLogin()=");
		builder.append(getLogin());
		builder.append(", \n getRegistrationDate()=");
		builder.append(getRegistrationDate());
		builder.append(", \n getSex()=");
		builder.append(getSex());
		builder.append(", \n getPassword()=");
		builder.append(getPassword());
		builder.append("]");
		return builder.toString();
	}

}
