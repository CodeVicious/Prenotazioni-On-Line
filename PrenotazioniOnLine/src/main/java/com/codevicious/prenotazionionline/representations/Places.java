package com.codevicious.prenotazionionline.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Places {

	private String ID;
	private String place;
	private String address;
	private float lat;
	private float lon;
	private String color;
	
	
		public Places() {
		this.ID = null;
		this.place = null;
		this.address = null;
		this.lat = 0;
		this.lon = 0;
		this.color = null;				
	}

	public Places(String ID, String place, String address, float lat, float lon, String color) {
		this.ID = ID;
		this.place = place;
		this.address = address;
		this.lat = lat;
		this.lon = lon;
		this.color = color;
	}

	@JsonProperty("id")
	public String getID() {
		return ID;
	}

	@JsonProperty("place")
	public String getPlace() {
		return place;
	}
	
	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("lat")
	public float getLat() {
		return lat;
	}

	@JsonProperty("lon")
	public float getLon() {
		return lon;
	}

	@JsonProperty("color")
	public String getColor() {
		return color;
	}

	@JsonProperty("id")
	public void setID(String iD) {
		ID = iD;
	}

	@JsonProperty("place")
	public void setPlace(String place) {
		this.place = place;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty("lat")
	public void setLat(float lat) {
		this.lat = lat;
	}

	@JsonProperty("lon")
	public void setLon(float lon) {
		this.lon = lon;
	}

	@JsonProperty("color")
	public void setColor(String color) {
		this.color = color;
	}

}
