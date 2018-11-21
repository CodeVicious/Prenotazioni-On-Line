package com.codevicious.prenotazionionline.representations;

import java.security.Principal;
import java.util.List;

import com.google.common.base.Optional;

public class User implements Principal {

	private long id;
	private String name;
	private String surname;
	private String username;
	private String email;
	private String telephone;
	private String mobile;
	private String password;
	
	public User() {}


	public User(long id, String name, String surname, String username, String email, String telephone, String mobile, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.telephone = telephone;
		this.mobile = mobile;
		this.password = password;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getUsername() {
		return username;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
