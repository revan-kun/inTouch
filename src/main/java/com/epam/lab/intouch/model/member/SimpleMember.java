package com.epam.lab.intouch.model.member;

import java.util.Date;

import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Revan
 * 
 */
public class SimpleMember {
	@Expose
	@SerializedName("login")
	private String login;

	@Expose
	@SerializedName("password")
	private String password;

	@Expose
	@SerializedName("lastName")
	private String lastName;

	@Expose
	@SerializedName("firstName")
	private String firstName;

	private Sex sex;

	@Expose
	@SerializedName("projectRole")
	private Role projectRole;

	private Date birthday;
	private Date registrationDate;

	public SimpleMember() {

	}

	public SimpleMember(String login, String password) {
		this.login = login;
		this.password = password;
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
