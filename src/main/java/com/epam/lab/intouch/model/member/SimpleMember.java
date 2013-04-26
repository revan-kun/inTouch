package com.epam.lab.intouch.model.member;

import java.util.Date;
import java.util.List;

import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.project.Project;

/**
 * @author Revan
 *
 */
public class SimpleMember {

	private String login;
	private String password;
	
	private String lastName;
	private String firstName;
	
	private Sex sex;
	
	private Role projectRole;
	
	private Date birthday;
	private Date registrationDate;

	public SimpleMember() {

	}

	public Role getRole() {
		return this.projectRole;
	}

	public void setRole(final Role role) {
		this.projectRole = role;
	}

	public void setBirthday(final Date birthday) {
		this.birthday = birthday;
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

	public void setRegistrationDate(final Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setSex(final Sex sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
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

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public Sex getSex() {
		return this.sex;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
