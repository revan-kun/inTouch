package com.epam.lab.intouch.model.member.trash;

import java.util.Date;
import java.util.List;

import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.project.Project;

public class Member {
	private String firstName;
	private String lastName;
	private Sex sex;
	private String login;
	private String password;
	private Date birthday;
	private Date registrationDate;
	private QualificationLevel qLevel;
	private List<Skill> skills;
	private List<Project> projects;
	private Double experience;
	private String photoURI;
	private Role projectRole;

	public Role getRole() {
		return projectRole;
	}

	public void setRole(final Role role) {
		this.projectRole = role;
	}

	public Member() {

	}

	public void setBirthday(final Date birthday) {
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

	public void setPhotoURI(final String photoURI) {
		this.photoURI = photoURI;
	}

	public void setQualificationLevel(final QualificationLevel qLevel) {
		this.qLevel = qLevel;
	}

	public void setProjects(final List<Project> projects) {
		this.projects = projects;
	}

	public void setRegistrationDate(final Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setSex(final Sex sex) {
		this.sex = sex;
	}

	public void setSkills(final List<Skill> skills) {
		this.skills = skills;
	}

	public Date getBirthday() {
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

	public String getPhotoURI() {
		return this.photoURI;
	}

	public QualificationLevel getQualificationLevel() {
		return this.qLevel;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public Sex getSex() {
		return this.sex;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
