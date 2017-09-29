package com.codevicious.prenotazionionline.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.codevicious.prenotazionionline.helper.PrenotazioniOnlineStatics;
import com.codevicious.prenotazionionline.representations.Availability;
import com.codevicious.prenotazionionline.representations.Reservation;
import com.codevicious.prenotazionionline.representations.ReservationAvailability;

public class ReservationAvailabilityMapper implements ResultSetMapper<ReservationAvailability> {

	public ReservationAvailability map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		Reservation reservation = new Reservation(r.getLong("id"),
				PrenotazioniOnlineStatics.longDateFormatTime.parseDateTime(r.getString("reservationdate")),
				r.getString("name"), r.getString("surname"), r.getString("address"), r.getString("email"),
				PrenotazioniOnlineStatics.shortDateFormatyyyyMMdd.parseDateTime(r.getString("borndate")),
				r.getString("phone"), r.getString("notes"), r.getLong("fkavailability"));

		Availability availability = new Availability(r.getInt("ID"),
				PrenotazioniOnlineStatics.longDateFormatTime.parseDateTime(r.getString("Data")), r.getInt("FKplaces"),
				r.getBoolean("reserved"), r.getString("name"), r.getString("color"));

		return new ReservationAvailability(reservation, availability);

	}

}