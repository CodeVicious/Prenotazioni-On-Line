package com.codevicious.prenotazionionline.representations;

public class Sector {
	
	int id;
	String code;
	String sector;
	String description;
	int parentid;
	
	public Sector(int id, String code, String sector, String description, int parentid) {
		this.id = id;
		this.sector = sector;
		this.code = code;
		this.description = description;
		this.parentid = parentid;
	}

	public int getId() {
		return id;
	}

	public String getSector() {
		return sector;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

}
