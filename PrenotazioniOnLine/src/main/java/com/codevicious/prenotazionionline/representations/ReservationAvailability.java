package com.codevicious.prenotazionionline.representations;

import com.codevicious.prenotazionionline.helper.PrenotazioniOnlineStatics;

public class ReservationAvailability {
	private final Reservation reservation;
	private final Availability availability;
	
	public ReservationAvailability(Reservation reservation, Availability availability) {
		this.reservation = reservation;
		this.availability = availability;
	}
	
	public long getId() {
		return reservation.getId();
	}

	public String getReservationDate() {
		return reservation.getReservationDate();
	}

	public String getName() {
		return reservation.getName();
	}

	public String getSurname() {
		return reservation.getSurname();
	}

	public String getAddress() {
		return reservation.getAddress();
	}
	
	public String getEmail() {
		return reservation.getEmail();
	}


	public String getBornDate() {		
		return reservation.getBornDate();
	}

	public String getPhone() {
		return reservation.getPhone();				
	}

	public String getNote() {
		return reservation.getNote();				
	}

}
