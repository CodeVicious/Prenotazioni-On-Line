package com.codevicious.prenotazionionline.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Places {

	private final String ID;
	private final String place;

	public Places() {
		this.ID = null;
		this.place = null;
	}

	public Places(String ID, String place) {
		this.ID = ID;
		this.place = place;
	}

	@JsonProperty("ID")
	public String getId() {
		return ID;
	}

	@JsonProperty("place")
	public String getPlace() {
		return place;
	}

}
