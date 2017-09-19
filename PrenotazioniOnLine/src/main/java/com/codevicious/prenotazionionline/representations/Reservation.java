package com.codevicious.prenotazionionline.representations;

import org.joda.time.DateTime;

public class Reservation {

	private long id;
	private DateTime reservationDate;
	private String name;
	private String surname;
	private String address;
	private String email;
	private DateTime bornDate;
	private String phone;
	private String note;
	private long fKavailability;

	public Reservation() {

	}

	public Reservation(long id, DateTime reservationDate, String name, String surname, String address, String email, DateTime bornDate,
			String phone, String note, long fKavailability) {
		this.id = id;
		this.reservationDate = reservationDate;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.bornDate = bornDate;
		this.phone = phone;
		this.note = note;
		this.fKavailability = fKavailability;
	}

	public long getId() {
		return id;
	}

	public DateTime getReservationDate() {
		return reservationDate;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getAddress() {
		return address;
	}
	
	public String getEmail() {
		return email;
	}


	public DateTime getBornDate() {
		return bornDate;
	}

	public String getPhone() {
		return phone;
	}

	public String getNote() {
		return note;
	}

	public long getfKavailability() {
		return fKavailability;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setReservationDate(DateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}


	public void setBornDate(DateTime bornDate) {
		this.bornDate = bornDate;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setfKavailability(long fKavailability) {
		this.fKavailability = fKavailability;
	}

}
