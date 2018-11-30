package com.codevicious.prenotazionionline.representations;

public class Role {
	
	int id;
	String role;
	String description;
	String sigla;
	
	public Role(int id, String role, String description, String sigla) {
		this.id = id;
		this.role = role;
		this.description = description;
		this.sigla = sigla;
	}
	public int getId() {
		return id;
	}
	public String getRole() {
		return role;
	}
	public String getDescription() {
		return description;
	}
	public String getSigla() {
		return sigla;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}	

}
