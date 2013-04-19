package com.epam.lab.intouch.model.member;

import java.net.URI;
import java.util.Date;
import java.util.List;

import com.epam.lab.intouch.model.member.enums.ProfessionLevel;
import com.epam.lab.intouch.model.member.enums.Sex;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.project.Project;

public abstract class Member {
	private String firstName;
	private String lastName;
	private Sex sex;
	private String login;
	private Date birthday;
	private Date registrationDate;
	private ProfessionLevel profLevel;
	private List<Skill> skills;
	private List<Project> projects;
	private Double experience;
	private URI photoURI;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public ProfessionLevel getProfLevel() {
		return profLevel;
	}

	public void setProfLevel(ProfessionLevel profLevel) {
		this.profLevel = profLevel;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Double getExperience() {
		return experience;
	}

	public void setExperience(Double experience) {
		this.experience = experience;
	}

	public URI getPhotoURI() {
		return photoURI;
	}

	public void setPhotoURI(URI photoURI) {
		this.photoURI = photoURI;
	}
	
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

}
