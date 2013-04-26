package com.epam.lab.intouch.model.member;

import java.util.List;

import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.project.Project;

public class Member extends SimpleMember{

	private Double experience;
	
	private List<Skill> skills;
	private List<Project> projects;
	private QualificationLevel qLevel;
	
	private String photoURI;

	public Member() {

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

	public void setSkills(final List<Skill> skills) {
		this.skills = skills;
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

	public List<Skill> getSkills() {
		return this.skills;
	}
}
