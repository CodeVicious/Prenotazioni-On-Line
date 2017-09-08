package com.codevicious.prenotazionionline.representations;

import java.sql.Time;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Availability {
	
	private int id;
	private DateTime data;	
	private Time ora;
	private int fKplaces;
	private String name;
	private String color;

	public Availability() {
		this.id = 0;		
		this.data = null;
		this.ora = null;
		this.fKplaces = 0;
		this.name = null;
		this.color = null;
	}

	public Availability(int id, DateTime data, Time ora, int fKplaces, String name, String color) {
		this.id = id;		
		this.data = data;
		this.ora = ora;
		this.fKplaces = fKplaces;
		this.name = name;
		this.color = color;
	}

	@JsonProperty("id")
	public int getId() {
		return id;
	}

	
	@JsonProperty("start")
	public String getStart() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
		return fmt.print(data);
	}

	
	@JsonProperty("data")
	public DateTime getData() {
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
	
	@JsonProperty("end")
	public String getEnd() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");		
		return fmt.print(data.plusMinutes(30));
	}

	@JsonProperty("title")
	public String getName() {
		return name;
	}

	@JsonProperty("textColor")
	public String getColor() {
		return color;
	}

}
