package com.codevicious.prenotazionionline.representations;

import java.sql.Time;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reservation {
	
	private int id;
	private DateTime reservationDate;	
	private String name;
	private String surname;
	private String bornPlace;
	private Date bornDate;
	private String phone;
	private String note;	
	private int fKavailability;
	

	public Reservation() {

	}

	public Reservation(int id, DateTime data, int fKplaces, Boolean reserved, String name, String color) {
		this.id = id;		
		this.data = data;
		this.fKplaces = fKplaces;
		this.name = name;
		this.color = color;
		this.reserved = reserved;
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

	@JsonProperty("reserved")
	public Boolean getReserved() {
		return reserved;
	}

	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("start")
	public void setData(DateTime data) {
		this.data = data;
	}

	@JsonProperty("FKplaces")
	public void setfKplaces(int fKplaces) {
		this.fKplaces = fKplaces;
	}

	@JsonProperty("reserved")
	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
