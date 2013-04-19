package com.epam.lab.intouch.model.member;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Sex;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.project.Project;

public abstract class Member {
	protected String firstName;
	protected String lastName;
	protected Sex sex;
	protected String login;
	protected Calendar birthday;
	protected Calendar registrationDate;
	protected QualificationLevel profLevel;
	protected List<Skill> skills;
	protected List<Project> projects;
	protected Double experience;
	protected URI photoURI;

	public Member() {
		
	}

	public void setBirthday(final Calendar birthday) {
		this.birthday = birthday;
	}

	public void setExperience(final Double experience) {
		this.experience = experience;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public void setPhotoURI(final URI photoURI) {
		this.photoURI = photoURI;
	}

	public void setProfLevel(final QualificationLevel profLevel) {
		this.profLevel = profLevel;
	}

	public void setProjects(final List<Project> projects) {
		this.projects = projects;
	}

	public void setRegistrationDate(final Calendar registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public void setSex(final Sex sex) {
		this.sex = sex;
	}

	public void setSkills(final List<Skill> skills) {
		this.skills = skills;
	}

	public Calendar getBirthday() {
		return this.birthday;
	}

	public Double getExperience() {
		return this.experience;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getLogin() {
		return this.login;
	}

	public URI getPhotoURI() {
		return this.photoURI;
	}

	public QualificationLevel getProfLevel() {
		return this.profLevel;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public Calendar getRegistrationDate() {
		return this.registrationDate;
	}

	public Sex getSex() {
		return this.sex;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}
}
