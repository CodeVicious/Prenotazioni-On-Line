package com.codevicious.prenotazionionline.representations;

import java.security.Principal;
import java.util.List;

public class User implements Principal{
	
	private long id;
	private String name;
	private String surname;
	private String username;
	

    private List<Role> roles;
    private List<Sector> sectors;
    
    public User(long id, String name, String surname, String username) {	
		this.name = name;
		this.surname = surname;
		this.username = username;
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
    
}
