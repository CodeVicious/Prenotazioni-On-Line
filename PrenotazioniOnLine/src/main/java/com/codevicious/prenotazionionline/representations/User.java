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
	private String telephon;
	private String mobile;

	private Optional<List<Role>> roles;
	private Optional<List<Sector>> sectors;

	public User(long id, String name, String surname, String username, String email, String telephon, String mobile,
			List<Role> roles, List<Sector> sectors) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.telephon = telephon;
		this.mobile = mobile;
		this.roles = Optional.of(roles);
		this.sectors = Optional.of(sectors);
	}

	public User(long id, String name, String surname, String username, String email, String telephon, String mobile) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.telephon = telephon;
		this.mobile = mobile;
		this.roles = Optional.absent();
		this.sectors = Optional.absent();
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

	public Optional<List<Role>> getRoles() {
		return roles;
	}

	public Optional<List<Sector>> getSectors() {
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
		this.roles = Optional.of(roles);
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = Optional.of(sectors);
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
