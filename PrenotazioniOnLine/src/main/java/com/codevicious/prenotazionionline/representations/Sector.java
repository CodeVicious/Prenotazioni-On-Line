package com.codevicious.prenotazionionline.representations;

public class Sector {
	
	int id;
	int parentid;
	String code;
	String sector;
	String description;
	
	
	public Sector(int id, int parentid, String code, String sector, String description) {
		this.id = id;
		this.parentid = parentid;
		this.sector = sector;
		this.code = code;
		this.description = description;

	}

	public int getId() {
		return id;
	}
	
	public int getParentid() {
		return parentid;
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


	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

}
