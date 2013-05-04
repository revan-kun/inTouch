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
	private List<Project> projects;

	private QualificationLevel qLevel;
	private String photoURI;
	private String additionalInfo;

	public Member() {
		projects = new ArrayList<Project>();
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

	public Boolean isManager() {
		return checkRole(Role.MANAGER);
	}

	public Boolean isDeveloper() {
		return checkRole(Role.DEVELOPER);
	}

	public Boolean isTester() {
		return checkRole(Role.TESTER);
	}

	public Boolean addProject(Project project) {
		return projects.add(project);
	}

	public Boolean removeProject(Project project) {
		return projects.remove(project);
	}

	public void setExperience(final Double experience) {
		this.experience = experience;
	}

	public void setPhotoURI(final String photoURI) {
		this.photoURI = photoURI;
	}

	public void setQualificationLevel(final QualificationLevel qLevel) {
		this.qLevel = qLevel;
	}

	public void setProjects(final List<Project> projects) {
		this.projects = projects;
	}

	public Double getExperience() {
		return this.experience;
	}

	public String getPhotoURI() {
		return this.photoURI;
	}

	public QualificationLevel getQualificationLevel() {
		return this.qLevel;
	}

	public List<Project> getProjects() {
		return this.projects;
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

}
