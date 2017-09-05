package com.codevicious.prenotazionionline.representations;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "availability")

@NamedQueries({ @NamedQuery(name = "Availability.findAll", query = "from Availability"),
		@NamedQuery(name = "Availability.findPlaces", query = "SELECT DISTINCT p.ID as ID, p.name as name "
				+ "FROM Place p INNER JOIN Availability a on p.ID = a.FKplaces ORDER BY name"),
		@NamedQuery(name = "Availability.findByYMPlace", query = "SELECT p.name as place, a.ID as ID, concat(a.data,'T',a.ora) as start "
				+ " FROM Place p INNER JOIN Availability a ON p.ID = a.FKplaces"
				+ "	WHERE (MONTH(a.data) = :month AND a.FKplaces = :place AND YEAR(a.data) = :year)"
				+ "	ORDER BY a.data")
		/*
		 * @NamedQuery(name = "Availability.insertById", query = "INSERT INTO Availability (ID, Data, Ora, FK_places) VALUES (NULL,:Data,:Ora,:FKplaces)"),
		 * @NamedQuery(name = "Availability.updateById", query = "UPDATE Availability SET Data=:Data, Ora=:Ora,FKplaces=:FKplaces WHERE ID = :id"),
		 * @NamedQuery(name = "Availability.deleteById", query = "DELETE FROM availability WHERE ID = :id")*/ })

public class Availability {

	@Id
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "Data")
	private Date data;

	@Column(name = "Ora")
	private Time ora;

	@Column(name ="FKplaces")
	private long FKplaces;

	@ManyToOne(targetEntity = Place.class, cascade = CascadeType.ALL)
	private Place place;

	
	public Availability() {
	}

	public Availability(Date data, Place place) {
		this.data = data;
		this.place = place;
	}

	@JsonProperty("Id")
	public long getId() {
		return ID;
	}

	@JsonProperty("Data")
	public Date getData() {
		return data;
	}

	@JsonProperty("FKplaces")
	public Place getPlace() {
		return place;
	}

}
