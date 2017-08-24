package com.codevicious.prenotazionionline.representations;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Availability {
	
	private final int id;
	private final Date data;	
	private final Time ora;
	private final int fKplaces;

	public Availability() {
		this.id = 0;		
		this.data = null;
		this.ora = null;
		this.fKplaces = 0;
	}

	public Availability(int id, Date data, Time ora, int fKplaces) {
		this.id = id;		
		this.data = data;
		this.ora = ora;
		this.fKplaces = fKplaces;
	}

	@JsonProperty("Id")
	public int getId() {
		return id;
	}
	
	@JsonProperty("Data")
	public Date getData() {
		return data;
	}

	@JsonProperty("Ora")
	public Time getOra() {
		return ora;
	}

	@JsonProperty("FKplaces")
	public int getfKplaces() {
		return fKplaces;
	}

}
