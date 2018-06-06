package com.codevicious.prenotazionionline.representations;

import java.security.Principal;
import java.util.List;

public class User implements Principal {



	private long id;
	private String name;
	private String surname;
	private String username;
	private String email;
	private String telephon;
	private String mobile;

	private List<Role> roles;
	private List<Sector> sectors;

	public User(long id, String name, String surname, String username, String email, String telephon, String mobile,
			List<Role> roles, List<Sector> sectors) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.telephon = telephon;
		this.mobile = mobile;
		this.roles = roles;
		this.sectors = sectors;
	}

	public User(long id, String name, String surname, String username, String email, String telephon, String mobile) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.telephon = telephon;
		this.mobile = mobile;
		this.roles = null;
		this.sectors = null;
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

	public List<Role> getRoles() {
		return roles;
	}

	public List<Sector> getSectors() {
		return sectors;
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

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public String getTelephon() {
		return telephon;
	}

	public String getMobile() {
		return mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephon(String telephon) {
		this.telephon = telephon;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
