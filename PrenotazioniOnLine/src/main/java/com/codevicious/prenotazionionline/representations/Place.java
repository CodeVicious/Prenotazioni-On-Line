package com.codevicious.prenotazionionline.representations;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "places")

public class Place {

    @Id
    @Column(name = "ID")
	private long ID;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "lat")
    private float lat;
    
    @Column(name = "lon")
    private float lon;
    
    @Column(name = "type")
    private String type;
    
    
    @OneToMany(
    		cascade = CascadeType.ALL,
    		targetEntity = Availability.class)    
    private List<Availability> availabilities;
    
	

	public Place() {
	}

	public Place(long ID, String name, String address, float lat, float lon, String type) {
		this.ID = ID;
		this.name = name;
		this.address = address;
		this.lat = lat;
		this.lon = lon;
		this.type = type;
	}

	public long getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public float getLat() {
		return lat;
	}

	public float getLon() {
		return lon;
	}

	public String getType() {
		return type;
	}

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}




	
}
