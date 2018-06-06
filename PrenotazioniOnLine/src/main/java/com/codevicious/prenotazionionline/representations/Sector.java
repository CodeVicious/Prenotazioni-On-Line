package com.codevicious.prenotazionionline.representations;

public class Sector {
	
	long id;
	String sector;
	String description;
	
	public Sector(long id, String sector, String description) {
		this.id = id;
		this.sector = sector;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getSector() {
		return sector;
	}

	public String getDescription() {
		return description;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
